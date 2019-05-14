package inactive.model.validators.impl;

import inactive.model.validator.AbstractEachValidator;

public class NonNullValidator extends AbstractEachValidator {

    public void validate() {
        if (value == null) {
            validationReport.addError("Value of '{}' must not be null.", fieldName);
        }
    }
}
