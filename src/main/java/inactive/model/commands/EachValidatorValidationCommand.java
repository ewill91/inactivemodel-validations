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
