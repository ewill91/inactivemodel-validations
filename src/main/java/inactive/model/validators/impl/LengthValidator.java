package inactive.model.validators.impl;

import inactive.model.validator.AbstractEachValidator;
import inactive.model.validators.Length;

import java.util.Collection;
import java.util.List;

public class LengthValidator extends AbstractEachValidator {

    private int min;
    private int max;

    @Override
    public void validate() {
        if (value == null) {
            return;
        }

        min = (int)getElementFromAnnotation(Length.class, "min");
        max = (int)getElementFromAnnotation(Length.class, "max");

        // TODO: There has to be a more elegant solution to this problem.
        // Maybe more specific LengthValidator implementations for different
        // types?
        if (value instanceof Object[]) {
            Object[] arr = (Object[])value;
            validateContainer(arr.length);
        } else if (value instanceof Collection) {
            List l = (List)value;
            validateContainer(l.size());
        } else {
            validateString();
        }
    }

    private void validateString() {
        if (value.toString().length() < min) {
            errTooShort();
        } else if (value.toString().length() > max) {
            errTooLong();
        }
    }

    private void validateContainer(int length) {
        if (length < min) {
            errTooShort();
        } else if (length > max) {
            errTooLong();
        }
    }

    private void errTooLong() {
        validationReport.addError("'{}' is too long. Max length is {}", fieldName, min);
    }

    private void errTooShort() {
        validationReport.addError("'{}' is too short. Min length is {}", fieldName, min);
    }
}
