package inactive.model.validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Problem:
// EachValidators with variables (eg Length(min = ..., max = ...)) require a way
// to get to the specified values.
// Either the initializer for the EachValidator classes has to be refactored
// to allow for args or we pass the record to the validator so we can reflect
// on the field again and get the specific values from the annotation.
//
// Update:
// Cannot merge with AbstractValidator! Else, we would need to know the class
// we're validating, which is too much of an inconvenience for an EachValidator.
//
// Solution:
// Add the Record to the validator, so we can get the annotation again
// by reflecting on the fieldName and thus get the values from the annotation.

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEachValidator implements Validator {

    protected Object record;

    protected Object value;

    protected String fieldName;

    protected ValidationReport validationReport;
}
