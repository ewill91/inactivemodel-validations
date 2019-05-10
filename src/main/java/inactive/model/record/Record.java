package inactive.model.record;

import inactive.model.validator.ValidationReport;

/**
 * Interface which has to be implemented by a class in order to run
 * the {@code ClassValidator} and {@code EachValidator} validators.
 *
 * As it only provides a default method, no extra implementation work
 * has to be done inside the validation target.
 */
public interface Record {

    default ValidationReport validate() {
        RecordValidator recordValidator = new RecordValidator(this);
        return recordValidator.validate();
    }
}
