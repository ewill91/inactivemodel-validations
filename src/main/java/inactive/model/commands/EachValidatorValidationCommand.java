package inactive.model.commands;

import inactive.model.record.Record;
import inactive.model.validator.ValidationReport;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static inactive.model.util.ReflectionUtil.invokeValidatorMethod;

public class EachValidatorValidationCommand extends AbstractValidationCommand {

    public EachValidatorValidationCommand(Record record,
                                          Field field,
                                          Annotation validatorAnnotation,
                                          ValidationReport validationReport) {

        super(record, field, validatorAnnotation, validationReport);
        // Assumption: The `value` of the annotation is always the validation class
        // that has to be instantiated.
        //
        // To get all the additional parameters, the following should be done:
        // 1) Get the types of the members from annotationType.memberTypes.
        // Maybe copy the memberTypes map minus `value` (usually the first entry)
        // The member type will be required to reflectively add the member values
        // to the validator class (either by calling setters or using an AllArgs
        // constructor)
        //
        // 2) Create validator object (duh...)

        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        try {
            invokeValidatorMethod(validator, "setValue", Object.class, field.get(record));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
