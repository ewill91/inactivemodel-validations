package inactive.model.record;

import inactive.model.validator.ValidationReport;

/**
 * TODO
 */
public interface Record {

    default ValidationReport validate() {
        RecordValidator recordValidator = new RecordValidator(this);
        return recordValidator.validate();
    }
}
