package inactive.model.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates the type of the validator annotation and is required by
 * the {@code RecordValidator} to determine which {@code ValidationCommand}
 * has to be used.
 *
 * When implementing a new {@code ClassValidator} annotation, this annotation's
 * value has to be set to the concrete {@code AbstractClassValidator} class. This
 * can be done by setting that class as the default value.
 *
 * There is also a generic annotation {@link inactive.model.validators.ValidateWith}
 * that can be used to register custom class validators by providing them as the
 * annotation's argument.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassValidator {}
