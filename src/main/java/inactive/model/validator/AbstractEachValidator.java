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

    protected ValidationReport validationReport;
}
