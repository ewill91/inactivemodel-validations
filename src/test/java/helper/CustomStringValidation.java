package helper;

import ewil.validations.AbstractValidator;

public class CustomStringValidation extends AbstractValidator<AnnotatedTestClass> {

    @Override
    public void validate() {
        isHello();
    }


    private void isHello() {
        if (!record.getFriendlyString().equals("hello")) {
            record.getValidationErrors().add("Provided value was not hello!");
        }
    }
}
