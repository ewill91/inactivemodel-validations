package impl;

import inactive.model.validator.AbstractValidator;

public class CustomStringValidation extends AbstractValidator<TestRecord> {

    @Override
    public void validate() {
        isHello();
    }


    private void isHello() {
        if (!record.getRandomString().equals("hello")) {
            validationErrors.add("Provided value was not hello!");
        }
    }
}
