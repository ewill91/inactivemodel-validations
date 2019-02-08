package impl;

import inactive.model.validator.AbstractValidator;

public class HelloValidator extends AbstractValidator<TestRecord> {

    @Override
    public void validate() {
        isHello();
    }


    private void isHello() {
        if (!record.getSayHello().equals("Hello")) {
            validationReport.addError("Provided value was not `Hello`!");
        }
    }
}
