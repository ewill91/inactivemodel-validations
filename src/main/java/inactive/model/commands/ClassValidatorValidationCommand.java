package inactive.model.commands;

import inactive.model.record.Record;
import inactive.model.validator.ValidationReport;
import inactive.model.validator.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static inactive.model.util.ReflectionUtil.getValidatorClassFromAnnotation;
import static inactive.model.util.ReflectionUtil.instantiateCustomValidator;
import static inactive.model.util.ReflectionUtil.invokeValidatorMethod;


public class ClassValidatorValidationCommand implements ValidationCommand {

    private Validator validator;

    public ClassValidatorValidationCommand(Record record,
                                           Field field,
                                           Annotation validatorAnnotation,
                                           ValidationReport validationReport) {

        Class<?> validatorClass = getValidatorClassFromAnnotation(validatorAnnotation, "value");
        validator = instantiateCustomValidator(validatorClass.getName(), Validator.class);

        invokeValidatorMethod(validator, "setRecord", Object.class, record);
        validator.setFieldName(field.getName());
        validator.setValidationReport(validationReport);
    }

    @Override
    public void validate() {
        validator.validate();
    }
}
