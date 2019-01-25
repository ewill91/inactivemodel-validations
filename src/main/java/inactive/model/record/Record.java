package inactive.model.record;


import inactive.model.validator.ValidationReport;

public interface Record {

    default ValidationReport validate() {
        RecordValidator recordValidator = new RecordValidator(this); // TODO find out if `this` is what i think it is
        return recordValidator.validate();
    }
}
