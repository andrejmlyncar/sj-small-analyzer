package sk.fiit.sj.small.analyzer.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Mlyncar <a.mlyncar@gmail.com>
 */
public class Grammar {

    private final List<Ll1TableRow> ll1Table = new ArrayList<>();
    
    public List<Ll1TableRow> getLl1Table() {
        return this.ll1Table;
    }
    
    public void addLl1TableRow(Ll1TableRow row) {
        this.ll1Table.add(row);
    } 
    
}
