package inactive.model.validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
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
