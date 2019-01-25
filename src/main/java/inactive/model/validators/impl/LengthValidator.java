package inactive.model.validators.impl;

import inactive.model.validator.AbstractEachValidator;
import inactive.model.validators.Length;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

import static inactive.model.util.ReflectionUtil.getDeclaredFieldAnnotations;
import static inactive.model.util.ReflectionUtil.getValueFromAnnotation;

public class LengthValidator extends AbstractEachValidator {

    @Override
    public void validate() {
        Annotation[] annotations = getDeclaredFieldAnnotations(record, fieldName);

        Annotation annotation = Stream.of(annotations)
                .filter(a -> a.annotationType().getName().equals(Length.class.getName()))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        int min = (int)getValueFromAnnotation(annotation, "min");
        int max = (int)getValueFromAnnotation(annotation, "max");


        if (value.toString().length() < min || value.toString().length() > max) {
            validationReport.addError("Value '" + fieldName + "' is too long or too short!");
        }
    }
}
