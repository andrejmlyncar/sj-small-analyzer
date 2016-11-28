package sk.fiit.sj.small.analyzer.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @Override
    public ValidationResult validateInput(String textInput) throws SmallAnalyzerException {
        return syntaxAnalysis(lexikalAnalysis(textInput));
    }

    private ValidationResult syntaxAnalysis(List<String> tokens) throws SmallAnalyzerException {
        ValidationResult validationResult = new SmallValidationResult();

        GrammarFactory factory = new GrammarFactory();
        Grammar grammar = factory.createSmallGrammar();
        String[] stack = {"program"};
        for (String token : tokens) {
            System.out.println("Starting to pop " + token);
            while (stack.length != 0) {
                String[] tmpStack = updateStack(stack, grammar.getLl1Table(), token);

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

        return validationResult;
    }

    private String[] updateStack(String[] currentStack, List<Ll1TableRow> rows, String token) throws SmallAnalyzerException {
        int stackIndex = 0;
        for (String stackValue : currentStack) {
            if (stackValue.equals(token) || checkIfLetterMatchesTerminal(token, stackValue) || checkIfDigit19MatchesTerminal(token, stackValue) || checkIfDigitMatchesTerminal(token, stackValue)) {
                System.out.println("Poping value " + token);
                String[] popedStack = Arrays.copyOfRange(currentStack, 1, currentStack.length);
                System.out.println("NEW STACK: " + Arrays.toString(popedStack));
                return popedStack;
            }
            for (Ll1TableRow row : rows) {
                if (row.getNonTerminal().equals(stackValue)) {
                    for (Ll1TableRecord record : row.getRowRecords()) {
                        if (record.getTerminal().equals(token) || checkIfLetterMatchesTerminal(token, record.getTerminal()) || checkIfDigit19MatchesTerminal(token, record.getTerminal()) || checkIfDigitMatchesTerminal(token, record.getTerminal())) {
                            System.out.println(stackValue + " can be replaced by " + Arrays.toString(record.getGrammarRule().getRightSide()) + " Rule n." + record.getGrammarRule().getRuleNumber());
                            String[] newStackBefore = {};
                            String[] newStackAfter = {};
                            if (stackIndex != 0) {
                                newStackBefore = Arrays.copyOfRange(currentStack, 0, stackIndex);
                            }
                            if (currentStack.length != 0) {
                                newStackAfter = Arrays.copyOfRange(currentStack, stackIndex + 1, currentStack.length);
                            }
                            String[] mergedStack = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(newStackBefore, record.getGrammarRule().getRightSide()), newStackAfter);
                            System.out.println("NEW STACK: " + Arrays.toString(mergedStack));
                            return mergedStack;
                        }
                    }
                }
            }
            stackIndex++;
        }
        throw new SmallAnalyzerException("Could not update stack. Invalid placement of token " + token);
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

    private List<String> lexikalAnalysis(String textInput) {
        List<String> tokens = new ArrayList<>();

        String parsedText[] = textInput.replaceAll("\\s+", " ").split("(?!^)");
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
                    tokens.add(tokenWord.toUpperCase());
                } else {
                    tokens.add(character);
                }
            }
        }

        System.out.println("TOKENS:");
        for (String str : tokens) {
            System.out.println(str);
        }

        return tokens;
    }
}
