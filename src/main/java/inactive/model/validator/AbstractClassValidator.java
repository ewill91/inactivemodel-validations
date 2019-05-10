package inactive.model.validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Parent class for validators that require more than just the validated
 * field as context for the validation. For this, the validator needs the
 * concrete type of the record for which the validation is being used.
 *
 * @param <T> the type of the class that is being validated.
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractClassValidator<T> implements Validator {

    protected T record;

    protected String fieldName;

    protected ValidationReport validationReport;

}
