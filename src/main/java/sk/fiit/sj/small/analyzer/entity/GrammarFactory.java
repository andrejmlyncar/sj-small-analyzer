package sk.fiit.sj.small.analyzer.entity;

/**
 *
 * @author Andrej Mlyncar <a.mlyncar@gmail.com>
 */
public class GrammarFactory {

    public Grammar createSmallGrammar() {

        Grammar grammar = new Grammar();
        GrammarRule rule1 = new GrammarRule(1, "program", new String[]{"BEGIN", "statement_list", "END"});
        GrammarRule rule2 = new GrammarRule(2, "statement_list", new String[]{"statement", "statement_list'"});
        GrammarRule rule3 = new GrammarRule(3, "statement_list'", new String[]{"statement_list"});
        GrammarRule rule4 = new GrammarRule(4, "statement_list'", new String[]{"E"});
        GrammarRule rule5 = new GrammarRule(5, "statement", new String[]{"ident", ":=", "expression", ";"});
        GrammarRule rule6 = new GrammarRule(6, "statement", new String[]{"READ", "(", "id_list", ")", ";"});
        GrammarRule rule7 = new GrammarRule(7, "statement", new String[]{"WRITE", "(", "expr_list", ")", ";"});
        GrammarRule rule8 = new GrammarRule(8, "statement", new String[]{"IF", "bexpr", "THEN", "statement", "statement'"});
        GrammarRule rule9 = new GrammarRule(9, "statement'", new String[]{"ELSE", "statement", ";"});
        GrammarRule rule10 = new GrammarRule(10, "statement'", new String[]{";"});

        GrammarRule rule11 = new GrammarRule(11, "id_list", new String[]{"ident", "id_list'"});
        GrammarRule rule12 = new GrammarRule(12, "id_list'", new String[]{",", "id_list"});
        GrammarRule rule13 = new GrammarRule(13, "id_list'", new String[]{"E"});
        GrammarRule rule14 = new GrammarRule(14, "expr_list", new String[]{"expression", "expr_list'"});
        GrammarRule rule15 = new GrammarRule(15, "expr_list'", new String[]{",", "expr_list"});
        GrammarRule rule16 = new GrammarRule(16, "expr_list'", new String[]{"E"});
        GrammarRule rule17 = new GrammarRule(17, "expression", new String[]{"factor", "expression'"});
        GrammarRule rule18 = new GrammarRule(18, "expression'", new String[]{"op", "factor", "expression'"});
        GrammarRule rule19 = new GrammarRule(19, "expression'", new String[]{"E"});
        GrammarRule rule20 = new GrammarRule(20, "factor", new String[]{"(", "expression", ")"});

        GrammarRule rule21 = new GrammarRule(21, "factor", new String[]{"ident"});
        GrammarRule rule22 = new GrammarRule(22, "factor'", new String[]{"number"});
        GrammarRule rule23 = new GrammarRule(23, "op", new String[]{"+"});
        GrammarRule rule24 = new GrammarRule(24, "op", new String[]{"-"});
        GrammarRule rule25 = new GrammarRule(25, "bexpr'", new String[]{"bterm", "bexpr'"});
        GrammarRule rule26 = new GrammarRule(26, "bexpr'", new String[]{"OR", "bterm", "bexpr'"});
        GrammarRule rule27 = new GrammarRule(27, "bexpr'", new String[]{"E"});
        GrammarRule rule28 = new GrammarRule(28, "bterm", new String[]{"bfactor", "bterm'"});
        GrammarRule rule29 = new GrammarRule(29, "bterm'", new String[]{"AND", "bfactor"});
        GrammarRule rule30 = new GrammarRule(30, "bterm'", new String[]{"E"});

        GrammarRule rule31 = new GrammarRule(31, "bfactor", new String[]{"TRUE"});
        GrammarRule rule32 = new GrammarRule(32, "bfactor", new String[]{"FALSE"});
        GrammarRule rule33 = new GrammarRule(33, "bfactor", new String[]{"NOT", "bfactor"});
        GrammarRule rule34 = new GrammarRule(34, "bfactor", new String[]{"(", "bexpr", ")"});
        GrammarRule rule35 = new GrammarRule(35, "ident", new String[]{"letter", "ident''"});
        GrammarRule rule36 = new GrammarRule(36, "ident''", new String[]{"ident'"});
        GrammarRule rule37 = new GrammarRule(37, "ident''", new String[]{"E"});
        GrammarRule rule38 = new GrammarRule(38, "ident'", new String[]{"digit09", "ident'"});
        GrammarRule rule39 = new GrammarRule(39, "ident'", new String[]{"letter", "ident'"});
        GrammarRule rule40 = new GrammarRule(40, "ident'", new String[]{"E"});

        GrammarRule rule41 = new GrammarRule(41, "number", new String[]{"+", "digit19", "number'"});
        GrammarRule rule42 = new GrammarRule(42, "number", new String[]{"-", "digit19", "number'"});
        GrammarRule rule43 = new GrammarRule(43, "number", new String[]{"digit19", "number'"});
        GrammarRule rule44 = new GrammarRule(44, "number'", new String[]{"digit09", "number'"});
        GrammarRule rule45 = new GrammarRule(45, "number'", new String[]{"E"});
        GrammarRule rule46 = new GrammarRule(46, "digit09", new String[]{"digit19"});
        GrammarRule rule47 = new GrammarRule(47, "digit19", new String[]{"digit09"});
        GrammarRule rule48 = new GrammarRule(48, "letter", new String[]{"alpha"});

        Ll1TableRow row = new Ll1TableRow("program");
        row.addLl1TableRecord(new Ll1TableRecord("BEGIN", rule1));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("statement_list");
        row.addLl1TableRecord(new Ll1TableRecord("READ", rule2));
        row.addLl1TableRecord(new Ll1TableRecord("WRITE", rule2));
        row.addLl1TableRecord(new Ll1TableRecord("IF", rule2));
        row.addLl1TableRecord(new Ll1TableRecord("alpha", rule2));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("statement_list'");
        row.addLl1TableRecord(new Ll1TableRecord("READ", rule3));
        row.addLl1TableRecord(new Ll1TableRecord("WRITE", rule3));
        row.addLl1TableRecord(new Ll1TableRecord("IF", rule3));
        row.addLl1TableRecord(new Ll1TableRecord("alpha", rule3));
        row.addLl1TableRecord(new Ll1TableRecord("END", rule4));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("statement");
        row.addLl1TableRecord(new Ll1TableRecord("READ", rule6));
        row.addLl1TableRecord(new Ll1TableRecord("WRITE", rule7));
        row.addLl1TableRecord(new Ll1TableRecord("IF", rule8));
        row.addLl1TableRecord(new Ll1TableRecord("alpha", rule5));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("statement'");
        row.addLl1TableRecord(new Ll1TableRecord("ELSE", rule9));
        row.addLl1TableRecord(new Ll1TableRecord(";", rule10));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("id_list");
        row.addLl1TableRecord(new Ll1TableRecord("alpha", rule11));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("id_list'");
        row.addLl1TableRecord(new Ll1TableRecord(",", rule12));
        row.addLl1TableRecord(new Ll1TableRecord(")", rule13));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("expr_list");
        row.addLl1TableRecord(new Ll1TableRecord("(", rule14));
        row.addLl1TableRecord(new Ll1TableRecord("+", rule14));
        row.addLl1TableRecord(new Ll1TableRecord("-", rule14));
        row.addLl1TableRecord(new Ll1TableRecord("digit19", rule14));
        row.addLl1TableRecord(new Ll1TableRecord("alpha", rule14));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("expr_list'");
        row.addLl1TableRecord(new Ll1TableRecord(",", rule15));
        row.addLl1TableRecord(new Ll1TableRecord(")", rule16));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("expression");
        row.addLl1TableRecord(new Ll1TableRecord("(", rule17));
        row.addLl1TableRecord(new Ll1TableRecord("+", rule17));
        row.addLl1TableRecord(new Ll1TableRecord("-", rule17));
        row.addLl1TableRecord(new Ll1TableRecord("digit19", rule17));
        row.addLl1TableRecord(new Ll1TableRecord("alpha", rule17));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("expression'");
        row.addLl1TableRecord(new Ll1TableRecord("+", rule18));
        row.addLl1TableRecord(new Ll1TableRecord("-", rule18));
        row.addLl1TableRecord(new Ll1TableRecord(",", rule19));
        row.addLl1TableRecord(new Ll1TableRecord(";", rule19));
        row.addLl1TableRecord(new Ll1TableRecord(")", rule19));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("factor");
        row.addLl1TableRecord(new Ll1TableRecord("(", rule20));
        row.addLl1TableRecord(new Ll1TableRecord("alpha", rule21));
        row.addLl1TableRecord(new Ll1TableRecord("digit19", rule22));
        row.addLl1TableRecord(new Ll1TableRecord("+", rule22));
        row.addLl1TableRecord(new Ll1TableRecord("-", rule22));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("op");
        row.addLl1TableRecord(new Ll1TableRecord("+", rule23));
        row.addLl1TableRecord(new Ll1TableRecord("-", rule24));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("bexpr");
        row.addLl1TableRecord(new Ll1TableRecord("TRUE", rule25));
        row.addLl1TableRecord(new Ll1TableRecord("FALSE", rule25));
        row.addLl1TableRecord(new Ll1TableRecord("NOT", rule25));
        row.addLl1TableRecord(new Ll1TableRecord("(", rule25));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("bexpr'");
        row.addLl1TableRecord(new Ll1TableRecord("OR", rule26));
        row.addLl1TableRecord(new Ll1TableRecord("THEN", rule27));
        row.addLl1TableRecord(new Ll1TableRecord(")", rule27));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("bterm");
        row.addLl1TableRecord(new Ll1TableRecord("(", rule28));
        row.addLl1TableRecord(new Ll1TableRecord("TRUE", rule28));
        row.addLl1TableRecord(new Ll1TableRecord("FALSE", rule28));
        row.addLl1TableRecord(new Ll1TableRecord("NOT", rule28));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("bterm'");
        row.addLl1TableRecord(new Ll1TableRecord("AND", rule29));
        row.addLl1TableRecord(new Ll1TableRecord("OR", rule30));
        row.addLl1TableRecord(new Ll1TableRecord("THEN", rule30));
        row.addLl1TableRecord(new Ll1TableRecord(")", rule30));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("bfactor");
        row.addLl1TableRecord(new Ll1TableRecord("TRUE", rule31));
        row.addLl1TableRecord(new Ll1TableRecord("FALSE", rule32));
        row.addLl1TableRecord(new Ll1TableRecord("NOT", rule33));
        row.addLl1TableRecord(new Ll1TableRecord("(", rule34));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("ident");
        row.addLl1TableRecord(new Ll1TableRecord("alpha", rule35));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("ident''");
        row.addLl1TableRecord(new Ll1TableRecord("alpha", rule36));
        row.addLl1TableRecord(new Ll1TableRecord("digit09", rule36));
        row.addLl1TableRecord(new Ll1TableRecord(";", rule37));
        row.addLl1TableRecord(new Ll1TableRecord(",", rule37));
        row.addLl1TableRecord(new Ll1TableRecord("+", rule37));
        row.addLl1TableRecord(new Ll1TableRecord("-", rule37));
        row.addLl1TableRecord(new Ll1TableRecord(":=", rule37));
        row.addLl1TableRecord(new Ll1TableRecord(")", rule37));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("ident'");
        row.addLl1TableRecord(new Ll1TableRecord("digit09", rule38));
        row.addLl1TableRecord(new Ll1TableRecord("alpha", rule39));
        row.addLl1TableRecord(new Ll1TableRecord(";", rule40));
        row.addLl1TableRecord(new Ll1TableRecord(",", rule40));
        row.addLl1TableRecord(new Ll1TableRecord("+", rule40));
        row.addLl1TableRecord(new Ll1TableRecord("-", rule40));
        row.addLl1TableRecord(new Ll1TableRecord(":=", rule40));
        row.addLl1TableRecord(new Ll1TableRecord(")", rule40));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("number");
        row.addLl1TableRecord(new Ll1TableRecord("+", rule41));
        row.addLl1TableRecord(new Ll1TableRecord("-", rule42));
        row.addLl1TableRecord(new Ll1TableRecord("digit09", rule43));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("number'");
        row.addLl1TableRecord(new Ll1TableRecord("digit09", rule44));
        row.addLl1TableRecord(new Ll1TableRecord(";", rule45));
        row.addLl1TableRecord(new Ll1TableRecord(",", rule45));
        row.addLl1TableRecord(new Ll1TableRecord("+", rule45));
        row.addLl1TableRecord(new Ll1TableRecord("-", rule45));
        row.addLl1TableRecord(new Ll1TableRecord(")", rule45));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("digit09");
        row.addLl1TableRecord(new Ll1TableRecord("digit09", rule46));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("digit09");
        row.addLl1TableRecord(new Ll1TableRecord("digit19", rule47));
        grammar.addLl1TableRow(row);

        row = new Ll1TableRow("letter");
        row.addLl1TableRecord(new Ll1TableRecord("alpha", rule48));
        grammar.addLl1TableRow(row);

        return grammar;
    }

}
