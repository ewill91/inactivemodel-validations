package inactive.model.validators;

import inactive.model.validator.AbstractEachValidator;
import inactive.model.validator.annotation.EachValidator;
import inactive.model.validators.impl.UuidValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@EachValidator
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
// TODO(ewill) @UsingRegex("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$")
public @interface Uuid {

    Class<? extends AbstractEachValidator> value() default UuidValidator.class;
}
