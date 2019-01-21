package inactive.model.validators.impl;

import inactive.model.validator.AbstractEachValidator;

import java.util.regex.Pattern;

public class UuidValidator extends AbstractEachValidator {

    private static final String UUID_PATTERN = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";

    public void validate() {
        if (!isUuid()) {
            validationReport.addError(String.format("'%s' is not a valid UUID", fieldName));
        }
    }

    private boolean isUuid() {
        Pattern pattern = Pattern.compile(UUID_PATTERN);
        // split up for debugging
        boolean match = pattern.matcher(value.toString()).find();
        return match;
    }
}
