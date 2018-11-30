package impl;

import inactive.model.validators.ValidateWith;
import inactive.model.AbstractRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnotatedTestClass extends AbstractRecord {

    @ValidateWith(CustomStringValidation.class)
    private String friendlyString;

    private TestClassProperty testClassProperty;

}
