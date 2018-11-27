package ewil.validations;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors {

    @Getter
    private List<String> errors = new ArrayList<>();

    public void add(String error) {
        errors.add(error);
    }

    public void prettyPrint() {
        for (String s : errors) {
            System.out.println(s);
        }
    }
}
