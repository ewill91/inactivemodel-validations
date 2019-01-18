package inactive.model.validators;

import inactive.model.validator.AbstractValidator;
import inactive.model.validator.ClassValidator;

import java.lang.annotation.*;

@ClassValidator
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateWith {

    /*
     * Class that is used to validate the annotated fieldName
     */
    Class<? extends AbstractValidator> value();
}
