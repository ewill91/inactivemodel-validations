package impl;

import inactive.model.validator.AbstractValidator;

public class CustomStringValidation extends AbstractValidator<TestRecord> {

    @Override
    public void validate() {
        isHello();
    }


    private void isHello() {
        if (!record.getSayHello().equals("hello")) {
            validationReport.addError("Provided value was not hello!");
        }
    }
}
