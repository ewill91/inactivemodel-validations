package inactive.model.validators.impl;

import inactive.model.validator.AbstractEachValidator;
import inactive.model.validators.Length;

public class LengthValidator extends AbstractEachValidator {

    @Override
    public void validate() {
        try {
            int min = (int) getValueFromAnnotation(Length.class, "min");
            int max = (int) getValueFromAnnotation(Length.class, "max");

            if (value.toString().length() < min) {
                validationReport.addError("Value '" + fieldName + "' is too short. Minimum length is " + min);
            } else if (value.toString().length() > max) {
                validationReport.addError("Value '" + fieldName + "' is too long. Maximum length is " + max);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
