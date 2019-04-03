package inactive.model.validators.impl;

import inactive.model.validator.AbstractEachValidator;
import inactive.model.validators.UsingRegex;

import java.util.regex.Pattern;

public class RegexValidator extends AbstractEachValidator {

    @Override
    public void validate() {

        String regex = (String)getValueFromAnnotation(UsingRegex.class, "regex");

        Pattern pattern = Pattern.compile(regex);

        if (!matchesPattern(pattern)) {
            validationReport.addError("Value '" + fieldName +"' does not match regex!");
        }
    }

    private boolean matchesPattern(Pattern pattern) {
        return pattern.matcher(value.toString()).find();
    }
}
