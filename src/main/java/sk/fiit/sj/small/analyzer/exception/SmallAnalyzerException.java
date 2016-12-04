package sk.fiit.sj.small.analyzer.exception;

/**
 *
 * @author Andrej Mlyncar <a.mlyncar@gmail.com>
 */
public class SmallAnalyzerException extends Exception {

    private final String validationOutput;

    public SmallAnalyzerException(String message, String validationOutput) {
        super(message);
        this.validationOutput = validationOutput;
    }

    public SmallAnalyzerException(String message, Throwable cause, String validationOutput) {
        super(message, cause);
        this.validationOutput = validationOutput;
    }

    public String getValidationOutput() {
        return this.validationOutput;
    }
}
