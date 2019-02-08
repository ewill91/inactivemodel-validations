package inactive.model.commands;

import inactive.model.record.Record;
import inactive.model.validator.ValidationReport;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public class ClassValidatorValidationCommand extends AbstractValidationCommand {

    public ClassValidatorValidationCommand(Record record,
                                           Field field,
                                           Annotation validatorAnnotation,
                                           ValidationReport validationReport) {

        super(record, field, validatorAnnotation, validationReport);
    }
}
