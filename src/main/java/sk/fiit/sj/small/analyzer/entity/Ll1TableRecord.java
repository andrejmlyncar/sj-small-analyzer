package sk.fiit.sj.small.analyzer.entity;

/**
 *
 * @author Andrej Mlyncar <a.mlyncar@gmail.com>
 */
public class Ll1TableRecord {

    private final String terminal;
    private final GrammarRule grammarRule;

    public Ll1TableRecord(String terminal, GrammarRule grammarRule) {
        this.terminal = terminal;
        this.grammarRule = grammarRule;
    }

    public String getTerminal() {
        return this.terminal;
    }

    public GrammarRule getGrammarRule() {
        return this.grammarRule;
    }

}
