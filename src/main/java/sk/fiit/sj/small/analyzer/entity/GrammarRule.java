package sk.fiit.sj.small.analyzer.entity;

public class GrammarRule {

    private final int ruleNumber;
    private final String[] rightSide;
    private final String leftNonTerminal;

    public GrammarRule(int ruleNumber, String leftNonTerminal, String[] rightSide) {
        this.ruleNumber = ruleNumber;
        this.leftNonTerminal = leftNonTerminal;
        this.rightSide = rightSide;
    }

    public int getRuleNumber() {
        return this.ruleNumber;
    }

    public String[] getRightSide() {
        return this.rightSide;
    }

    public String getLeftNonTerminal() {
        return this.leftNonTerminal;
    }

}
