package inactive.model.record;


public interface Record {

    default boolean validate() {
        RecordValidator recordValidator = new RecordValidator(this); // TODO find out if `this` is what i think it is

        return recordValidator.isValid(); // TODO should return a Report
    }
}
