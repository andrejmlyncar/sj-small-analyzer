package sk.fiit.sj.small.analyzer.test;

import org.junit.Test;
import sk.fiit.sj.small.analyzer.Analyzer;
import sk.fiit.sj.small.analyzer.exception.SmallAnalyzerException;
import sk.fiit.sj.small.analyzer.impl.SmallAnalyzer;

/**
 *
 * @author Andrej Mlyncar <a.mlyncar@gmail.com>
 */
public class AnalysisTest {

    @Test
    public void testLexikalAnalysis() throws SmallAnalyzerException {
        Analyzer analyzer = new SmallAnalyzer();

        analyzer.validateInput("BEGIN\n"
                + "READ(a,b1c,c);Write(a, c, a3); IF TRUE THEN c:=1321312;; a:=a+1;END");
           
    }
    
}
