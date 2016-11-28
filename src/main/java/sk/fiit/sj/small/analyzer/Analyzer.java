package sk.fiit.sj.small.analyzer;

import sk.fiit.sj.small.analyzer.exception.SmallAnalyzerException;

/**
 *
 * @author Andrej Mlyncar <a.mlyncar@gmail.com>
 */
public interface Analyzer {
    
    public ValidationResult validateInput(String textInput) throws SmallAnalyzerException;
    
}
