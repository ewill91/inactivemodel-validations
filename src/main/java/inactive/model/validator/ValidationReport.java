package inactive.model.validator;

import java.util.List;

public class ValidationReport {

    private ValidationErrors validationErrors;


    public ValidationReport() {
        validationErrors = new ValidationErrors();
    }

    public void addError(String error) {
        validationErrors.add(error);
    }

    public List<String> getErrors() {
        return validationErrors.getErrors();
    }

    public boolean hasErrors() {
        return !validationErrors.getErrors().isEmpty();
    }

}
