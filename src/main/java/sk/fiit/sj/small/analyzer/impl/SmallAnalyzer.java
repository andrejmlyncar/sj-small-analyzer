package sk.fiit.sj.small.analyzer.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.ArrayUtils;
import sk.fiit.sj.small.analyzer.Analyzer;
import sk.fiit.sj.small.analyzer.ValidationResult;
import sk.fiit.sj.small.analyzer.entity.Grammar;
import sk.fiit.sj.small.analyzer.entity.GrammarFactory;
import sk.fiit.sj.small.analyzer.entity.Ll1TableRecord;
import sk.fiit.sj.small.analyzer.entity.Ll1TableRow;
import sk.fiit.sj.small.analyzer.exception.SmallAnalyzerException;

/**
 *
 * @author Andrej Mlyncar <a.mlyncar@gmail.com>
 */
public class SmallAnalyzer implements Analyzer {

    private final List<String> stringTerminals = new ArrayList<>(Arrays.asList(new String[]{"BEGIN", "END", "READ", "WRITE", "IF", "THEN", "ELSE", "TRUE", "FALSE", "NOT", "AND", "OR"}));
    private final List<String> delimiterTerminals = new ArrayList<>(Arrays.asList(new String[]{"(", ")", ",", ";"}));
    private final List<String> operatorTerminals = new ArrayList<>(Arrays.asList(new String[]{":=", "+", "-"}));
    private final static Logger LOGGER = Logger.getLogger(SmallAnalyzer.class.getName());
    private String output;

    @Override
    public ValidationResult validateInput(String textInput) throws SmallAnalyzerException {
        ValidationResult result = new SmallValidationResult();
        syntaxAnalysis(lexikalAnalysis(textInput, result), result);
        return result;
    }

    private void syntaxAnalysis(List<String> tokens, ValidationResult result) throws SmallAnalyzerException {
        output = "";
        GrammarFactory factory = new GrammarFactory();
        Grammar grammar = factory.createSmallGrammar();
        String[] stack = {"program"};
        for (String token : tokens) {
            LOGGER.log(Level.INFO, "Starting to pop {0}", token);
            output += String.format("\nStarting to pop %s", token);
            while (stack.length != 0) {
                String[] tmpStack = updateStack(stack, grammar.getLl1Table(), token, result);
                if (tmpStack.length >= stack.length) {
                    stack = tmpStack;
                    stack = removeEpsilon(stack);
                } else {
                    stack = tmpStack;
                    stack = removeEpsilon(stack);
                    break;
                }
            }
        }
        if (stack.length != 0) {
            throw new SmallAnalyzerException("Missing statements at the end of the program: " + Arrays.toString(stack), output);
        }
        result.setOutput(output);
    }

    private String[] updateStack(String[] currentStack, List<Ll1TableRow> rows, String token, ValidationResult result) throws SmallAnalyzerException {
        int stackIndex = 0;
        for (String stackValue : currentStack) {
            if (stackValue.equals(token) || checkIfLetterMatchesTerminal(token, stackValue) || checkIfDigit19MatchesTerminal(token, stackValue) || checkIfDigitMatchesTerminal(token, stackValue)) {
                if (stackIndex == 0) {
                    LOGGER.log(Level.INFO, "Poping value {0}", token);
                    output += String.format("\nPoping value %s", token);
                    String[] popedStack = Arrays.copyOfRange(currentStack, 1, currentStack.length);
                    LOGGER.log(Level.INFO, "NEW STACK: {0}", Arrays.toString(popedStack));
                    output += String.format("\nNEW STACK: %s", Arrays.toString(popedStack));
                    return popedStack;
                } else {
                    throw new SmallAnalyzerException("Syntax error. Missing character before " + token, output);
                }
            }
            for (Ll1TableRow row : rows) {
                if (row.getNonTerminal().equals(stackValue)) {
                    for (Ll1TableRecord record : row.getRowRecords()) {
                        LOGGER.log(Level.INFO, "Terminal to find in table: {0}. In Row {1}", new Object[]{record.getTerminal(), row.getNonTerminal()});
                        if (record.getTerminal().equals(token) || checkIfLetterMatchesTerminal(token, record.getTerminal()) || checkIfDigit19MatchesTerminal(token, record.getTerminal()) || checkIfDigitMatchesTerminal(token, record.getTerminal())) {
                            LOGGER.log(Level.INFO, "{0} can be replaced by {1} Rule n.{2}", new Object[]{stackValue, Arrays.toString(record.getGrammarRule().getRightSide()), record.getGrammarRule().getRuleNumber()});
                            output += String.format("\n%s can be replaced by %s Rule n.%d", stackValue, Arrays.toString(record.getGrammarRule().getRightSide()), record.getGrammarRule().getRuleNumber());
                            String[] newStackBefore = {};
                            String[] newStackAfter = {};
                            if (stackIndex != 0) {
                                newStackBefore = Arrays.copyOfRange(currentStack, 0, stackIndex);
                            }
                            if (currentStack.length != 0) {
                                newStackAfter = Arrays.copyOfRange(currentStack, stackIndex + 1, currentStack.length);
                            }
                            String[] mergedStack = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(newStackBefore, record.getGrammarRule().getRightSide()), newStackAfter);
                            LOGGER.log(Level.INFO, "NEW STACK: {0}", Arrays.toString(mergedStack));
                            output += String.format("\nNEW STACK: %s", Arrays.toString(mergedStack));
                            return mergedStack;
                        }
                    }
                }
            }
            stackIndex++;
        }
        throw new SmallAnalyzerException("Could not update stack. Invalid placement of token " + token, output);
    }

    private String[] removeEpsilon(String[] stack) {
        int iterator = 0;
        for (String str : stack) {
            if (str.equals("E")) {
                iterator++;
            }
        }
        if (iterator != 0) {
            stack = Arrays.copyOfRange(stack, iterator, stack.length);
        }
        return stack;

    }

    private boolean checkIfLetterMatchesTerminal(String token, String terminal) {
        return terminal.equals("alpha") && token.length() == 1 && token.matches("[A-Za-z]+");
    }

    private boolean checkIfDigitMatchesTerminal(String token, String terminal) {
        return terminal.equals("digit09") && token.length() == 1 && token.matches("[0-9]+");
    }

    private boolean checkIfDigit19MatchesTerminal(String token, String terminal) {
        return terminal.equals("digit19") && token.length() == 1 && token.matches("[1-9]+");
    }

    private String getAlphaNumbericalWord(int increment, String[] parsedText) {
        String word = "";
        while (increment < parsedText.length && parsedText[increment].matches("[A-Za-z0-9]+")) {
            word += parsedText[increment];
            increment++;
        }
        return word;
    }

    private List<String> lexikalAnalysis(String textInput, ValidationResult result) {
        List<String> tokens = new ArrayList<>();

        String parsedText[] = textInput.replaceAll("\\s+", " ").split("(?!^)");
        String previousStringCharacter = "";
        for (int i = 0; i < parsedText.length; i++) {
            String character = parsedText[i];
            if (delimiterTerminals.contains(character)) {
                tokens.add(character);
            } else if (operatorTerminals.contains(character)) {
                tokens.add(character);
            } else if (character.equals(":") && "=".equals(parsedText[i + 1])) {
                tokens.add(":=");
                i++;
            } else if (character.matches("[A-Za-z0-9]+")) {
                String tokenWord = getAlphaNumbericalWord(i, parsedText);
                if (stringTerminals.contains(tokenWord.toUpperCase())) {
                    i += tokenWord.length() - 1;
                    if (previousStringCharacter.equals(tokenWord)) {
                        LOGGER.log(Level.INFO, "Removed duplucity of character {0}", tokenWord);
                        result.addCorrection("Removed duplicty of character " + tokenWord);
                    } else {
                        tokens.add(tokenWord.toUpperCase());
                    }
                    previousStringCharacter = tokenWord;
                } else {
                    tokens.add(character);
                }
            } else if (!character.equals(" ")) {
                result.addCorrection("Removed unknown character " + character);
            }
        }

        LOGGER.log(Level.INFO, "TOKENS DETECTED:");
        for (String str : tokens) {
            LOGGER.log(Level.INFO, str);
        }
        return tokens;
    }
}
