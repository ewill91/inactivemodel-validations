package inactive.model.validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractClassValidator<T> implements Validator {

    protected T record;

    protected String fieldName;

    protected ValidationReport validationReport;

}