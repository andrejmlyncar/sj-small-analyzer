package sk.fiit.sj.small.analyzer.impl;

import java.util.ArrayList;
import java.util.List;
import sk.fiit.sj.small.analyzer.ValidationResult;

/**
 *
 * @author Andrej Mlyncar <a.mlyncar@gmail.com>
 */
public class SmallValidationResult implements ValidationResult {

    private final List<String> validationErrors = new ArrayList<>();
    private final List<String> validationCorrections = new ArrayList<>();

    @Override
    public boolean isInputValid() {
        return validationErrors.isEmpty();
    }

    @Override
    public boolean hasCorrections() {
        return !validationCorrections.isEmpty();
    }

    @Override
    public List<String> getCorrections() {
        return this.validationCorrections;
    }

    @Override
    public List<String> getErrors() {
        return this.validationErrors;
    }

    @Override
    public void addCorrection(String correction) {
        this.validationCorrections.add(correction);
    }

    @Override
    public void addError(String error) {
        this.validationErrors.add(error);
    } 
   
}
