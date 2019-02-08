package inactive.model.commands;

import inactive.model.record.Record;
import inactive.model.validator.ValidationReport;
import inactive.model.validator.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static inactive.model.util.ReflectionUtil.getValidatorClassFromAnnotation;
import static inactive.model.util.ReflectionUtil.instantiateCustomValidator;
import static inactive.model.util.ReflectionUtil.invokeValidatorMethod;


public abstract class AbstractValidationCommand {

    protected Validator validator;

    protected AbstractValidationCommand(Record record,
                                        Field field,
                                        Annotation validatorAnnotation,
                                        ValidationReport validationReport) {

        Class<?> validatorClass = getValidatorClassFromAnnotation(validatorAnnotation, "value");
        validator = instantiateCustomValidator(validatorClass.getName());

        invokeValidatorMethod(validator, "setRecord", Object.class, record);
        validator.setFieldName(field.getName());
        validator.setValidationReport(validationReport);
    }

    public void validate() {
        validator.validate();
    }
}
