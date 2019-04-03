package inactive.model.validators;

import inactive.model.validator.AbstractValidator;
import inactive.model.validator.annotation.ClassValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ClassValidator
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateWith {

    /*
     * Class that is used to validate the annotated fieldName
     */
    Class<? extends AbstractValidator> value();
}
