package sk.fiit.sj.small.analyzer.exception;

/**
 *
 * @author Andrej Mlyncar <a.mlyncar@gmail.com>
 */
public class SmallAnalyzerException extends Exception {

    public SmallAnalyzerException(String message) {
        super(message);
    }

    public SmallAnalyzerException(String message, Throwable cause) {
        super(message, cause);
    }
}
