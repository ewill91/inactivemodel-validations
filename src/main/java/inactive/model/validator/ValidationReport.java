package inactive.model.validator;

import inactive.model.record.Record;
import lombok.Getter;
import org.slf4j.helpers.MessageFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * An instance of class {@code ValidationReport} is automatically created for classes
 * that implement the {@code Record} interface upon calling the {@link Record#validate()}
 * method.
 *
 * When creating validators, errors can be written to this {@code ValidationReport}
 * instance.
 */
public class ValidationReport {

    @Getter
    private List<String> errors = new ArrayList<>();

    public void addError(String error) {
        errors.add(error);
    }

    public void addError(String format, Object... args) {
        String errMsg = MessageFormatter.arrayFormat(format, args).getMessage();

        errors.add(errMsg);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

}
