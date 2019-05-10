package inactive.model.validator;

import inactive.model.record.Record;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * An instance of class {@class ValidationReport} is automatically created for classes
 * that implement the interface {@class Record} upon calling the {@link Record#validate()}
 * method.
 *
 * When creating validators, errors can be written to those {@class ValidationReport}
 * instances.
 */
public class ValidationReport {

    @Getter
    private List<String> errors = new ArrayList<>();

    public void addError(String error) {
        errors.add(error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

}
