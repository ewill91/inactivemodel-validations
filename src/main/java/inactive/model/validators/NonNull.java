package inactive.model.validators;

import inactive.model.validator.AbstractEachValidator;
import inactive.model.validator.annotation.EachValidator;
import inactive.model.validators.impl.NonNullValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@EachValidator
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NonNull {

    Class<? extends AbstractEachValidator> value() default NonNullValidator.class;
}
