package inactive.model.validators;

import inactive.model.validator.AbstractValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateWith {

    /*
     * Class that is used to validate the annotated field
     */
    Class<? extends AbstractValidator> value();
}
