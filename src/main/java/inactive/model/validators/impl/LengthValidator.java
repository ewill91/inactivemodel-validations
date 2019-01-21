package inactive.model.validators.impl;

import inactive.model.validator.AbstractEachValidator;

public class LengthValidator extends AbstractEachValidator {

    @Override
    public void validate() {

        validationReport.addError("Help");
    }

}
