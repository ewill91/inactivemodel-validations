package inactive.model.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates the type of the validator annotation and is required by
 * the {@class RecordValidator} to determine which {@code ValidationCommand}
 * has to be used.
 *
 * When implementing a new {@class EachValidator} annotation, this annotation's
 * value has to be set to the concrete {@class AbstractEachValidator} class. This
 * can be done by setting that class as the default value.
 *
 * @see inactive.model.validators.Length as an example.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EachValidator {}
