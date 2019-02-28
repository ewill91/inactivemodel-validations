package inactive.model.validators;

import inactive.model.validator.AbstractEachValidator;
import inactive.model.validators.impl.RegexValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsingRegex {

    Class<? extends AbstractEachValidator> value() default RegexValidator.class;

    /**
     * Regex used to validate the field
     */
    String regex();
}
