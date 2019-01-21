package inactive.model.validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO merge with AbstractValidator?
// EachValidators with variables (eg Length(min = ..., max = ...)) require a way
// to get to the specified values.
// Either the initializer for the EachValidator classes has to be refactored
// to allow for args or we pass the record to the validator so we can reflect
// on the field again and get the specific values from the annotation.

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEachValidator implements Validator {

    protected Object value;

    protected String fieldName;

    protected ValidationReport validationReport;
}
