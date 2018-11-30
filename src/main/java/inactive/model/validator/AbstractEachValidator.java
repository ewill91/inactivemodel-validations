package inactive.model.validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEachValidator implements Validator {

    private ValidationErrors validationErrors;

    private String field;

    private Object value;
}
