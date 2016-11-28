package sk.fiit.sj.small.analyzer.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Mlyncar <a.mlyncar@gmail.com>
 */
public class Ll1TableRow {

    private final String nonTerminal;
    private final List<Ll1TableRecord> rowRecords = new ArrayList<>();

    public Ll1TableRow(String nonTerminal) {
        this.nonTerminal = nonTerminal;
    }

    public String getNonTerminal() {
        return this.nonTerminal;
    }

    public List<Ll1TableRecord> getRowRecords() {
        return this.rowRecords;
    }
    
    public void addLl1TableRecord(Ll1TableRecord record) {
        this.rowRecords.add(record);
    }

}
