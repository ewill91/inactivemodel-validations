package inactive.model.validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEachValidator implements Validator {

    protected Object value;

    protected String fieldName;

    protected ValidationErrors validationErrors; // TODO(ewill): Move to interface?
}
