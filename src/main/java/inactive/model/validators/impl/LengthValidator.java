package inactive.model.validators.impl;

import inactive.model.validator.AbstractEachValidator;
import inactive.model.validators.Length;

public class LengthValidator extends AbstractEachValidator {

    @Override
    public void validate() {
        if (value == null) {
            return;
        }

        try {
            int min = (int) getElementFromAnnotation(Length.class, "min");
            int max = (int) getElementFromAnnotation(Length.class, "max");

            if (value.toString().length() < min) {
                validationReport.addError("Value of '{}' is too short. Min length is {}", fieldName, min);
            } else if (value.toString().length() > max) {
                validationReport.addError("Value of '{}' is too long. Max length is {}", fieldName, max);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
