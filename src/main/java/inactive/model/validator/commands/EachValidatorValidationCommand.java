package inactive.model.validator.commands;

import inactive.model.record.Record;
import inactive.model.validator.EachValidator;
import inactive.model.validator.ValidationReport;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static inactive.model.validator.reflect.AnnotationReflectionUtil.getValidatorClassFromAnnotation;
import static inactive.model.validator.reflect.ValidatorReflectionUtil.instantiateValidator;

@Slf4j
public class EachValidatorValidationCommand implements ValidationCommand {

    private EachValidator validator;

    public EachValidatorValidationCommand(Record record,
                                          Field field,
                                          Annotation validatorAnnotation,
                                          ValidationReport validationReport) {

        String fieldName = field.getName();

        Class<?> validatorClass = getValidatorClassFromAnnotation(validatorAnnotation, "value");
        validator = instantiateValidator(EachValidator.class, validatorClass.getName());

        validator.setRecord(record);
        validator.setValidationReport(validationReport);

        validator.setFieldName(fieldName);

        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        try {
            Object value = field.get(record);
            validator.setValue(value);
        } catch (IllegalAccessException e) {
            log.error("Failed to access field {} of record {}", fieldName, record.getClass().getName());

            // TODO: Don't cause program to quit.
            e.printStackTrace();
        }

        // Do we really have to add *all* declared annotations to the validator?
        // Technically, it only needs the current one.
        validator.setFieldAnnotations(field.getDeclaredAnnotations());
    }

    public void validate() {
        validator.validate();
    }
}
