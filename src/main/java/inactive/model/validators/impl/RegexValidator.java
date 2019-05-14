package inactive.model.validators.impl;

import inactive.model.validator.AbstractEachValidator;
import inactive.model.validators.UsingRegex;

import java.util.regex.Pattern;

public class RegexValidator extends AbstractEachValidator {

    @Override
    public void validate() {
        if (value == null) {
            return;
        }

        try {
            String regex = (String) getElementFromAnnotation(UsingRegex.class, "regex");

            Pattern pattern = Pattern.compile(regex);

            if (!matchesPattern(pattern)) {
                validationReport.addError("Value of '{}' does not match regex", fieldName);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean matchesPattern(Pattern pattern) {
        return pattern.matcher(value.toString()).find();
    }
}
