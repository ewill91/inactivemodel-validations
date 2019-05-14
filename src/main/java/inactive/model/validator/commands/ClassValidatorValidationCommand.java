package inactive.model.validator.commands;

import inactive.model.record.Record;
import inactive.model.validator.ValidationReport;
import inactive.model.validator.Validator;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static inactive.model.validator.reflect.AnnotationReflectionUtil.getValidatorClassFromAnnotation;
import static inactive.model.validator.reflect.ValidatorReflectionUtil.instantiateValidator;

@Slf4j
public class ClassValidatorValidationCommand implements ValidationCommand {

    private Validator validator;

    public ClassValidatorValidationCommand(Record record,
                                           Field field,
                                           Annotation validatorAnnotation,
                                           ValidationReport validationReport) {

        Class<?> validatorClass = getValidatorClassFromAnnotation(validatorAnnotation, "value");
        validator = instantiateValidator(Validator.class, validatorClass.getName());

        addRecordToValidator(record);
        validator.setFieldName(field.getName());
        validator.setValidationReport(validationReport);
    }

    @Override
    public void validate() {
        validator.validate();
    }

    private void addRecordToValidator(Record record) {
        try {
            // Why does it have to be of type Object here? setRecord takes T as input.
            Method targetMethod = validator.getClass().getMethod("setRecord", Object.class);

            if (!targetMethod.isAccessible()) {
                targetMethod.setAccessible(true);
            }

            targetMethod.invoke(validator, record);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("Failed to add record to '{}'", validator.getClass());

            // TODO: Don't cause program to quit.
            e.printStackTrace();
        }
    }
}
