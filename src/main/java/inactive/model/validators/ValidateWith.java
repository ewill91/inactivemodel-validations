package inactive.model.validators;

import inactive.model.validator.AbstractClassValidator;
import inactive.model.validator.annotation.ClassValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ClassValidator
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateWith {

    /**
     * The class which is used to validate the annotated fieldName.
     */
    Class<? extends AbstractClassValidator> value();
}
