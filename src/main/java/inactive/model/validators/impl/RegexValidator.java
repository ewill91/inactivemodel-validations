package inactive.model.validators.impl;

import inactive.model.validator.AbstractEachValidator;
import inactive.model.validators.UsingRegex;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static inactive.model.util.ReflectionUtil.getDeclaredFieldAnnotations;
import static inactive.model.util.ReflectionUtil.getValueFromAnnotation;

public class RegexValidator extends AbstractEachValidator {

    @Override
    public void validate() {

        // TODO: Value(s) from annotation should be set during instantiation (see also LengthValidator)

        Annotation[] annotations = getDeclaredFieldAnnotations(record, fieldName);

        Annotation annotation = Stream.of(annotations)
                .filter(a -> a.annotationType().getName().equals(UsingRegex.class.getName()))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        String regex = (String)getValueFromAnnotation(annotation, "regex");

        Pattern pattern = Pattern.compile(regex);

        if (!matchesPattern(pattern)) {
            validationReport.addError("Value '" + fieldName +"' does not match regex!");
        }
    }

    private boolean matchesPattern(Pattern pattern) {
        return pattern.matcher(value.toString()).find();
    }
}
