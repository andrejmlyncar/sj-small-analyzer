package sk.fiit.sj.small.analyzer;

import java.util.List;

/**
 *
 * @author Andrej Mlyncar <a.mlyncar@gmail.com>
 */
public interface ValidationResult {

    public boolean isInputValid();

    public boolean hasCorrections();

    public List<String> getCorrections();

    public List<String> getErrors();

    public void addCorrection(String correction);

    public void addError(String error);

    public void setOutput(String output);

    public String getOutput();
}
