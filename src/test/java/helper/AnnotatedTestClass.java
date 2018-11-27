package helper;

import ewil.validations.ValidateWith;
import ewil.validations.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnotatedTestClass extends Record {

    @ValidateWith(CustomStringValidation.class)
    private String friendlyString;

    private TestClassProperty testClassProperty;

}
