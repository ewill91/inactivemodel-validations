package impl;

import inactive.model.validator.AbstractClassValidator;

public class HelloValidator extends AbstractClassValidator<TestRecord> {

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
