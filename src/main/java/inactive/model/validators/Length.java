package inactive.model.validators;

import inactive.model.validator.AbstractEachValidator;
import inactive.model.validator.annotation.EachValidator;
import inactive.model.validators.impl.LengthValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@EachValidator
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Length {

    Class<? extends AbstractEachValidator> value() default LengthValidator.class;

    int min() default 0;

    // TODO: Use system max of int as default so arg can be omitted?
    int max();
}
