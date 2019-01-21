package inactive.model.record;

import inactive.model.validator.*;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

import static inactive.model.util.ReflectionUtil.getAnnotationValue;
import static inactive.model.util.ReflectionUtil.instantiateCustomValidator;
import static inactive.model.util.ReflectionUtil.invokeValidatorMethod;

@Getter
public class RecordValidator {

    private ValidationReport validationReport;

    private Record record;

    public RecordValidator(Record record) {
        this.record = record;
        validationReport = new ValidationReport();
    }

    // TODO move to ValidationReport.class
    public boolean isValid() {
        try {
            validate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return !hasErrors();
    }

    public boolean hasErrors() {
        return !validationReport.getErrors().isEmpty();
    }

    public ValidationReport validate() {
        Field[] fields = record.getClass().getDeclaredFields();

        for (Field field : fields) {

            try { validateField(field); } catch (Exception e) { e.printStackTrace(); }
        }

        return validationReport;
    }

    // TODO(ewill): cleanup/split up
    private void validateField(Field field) throws IllegalAccessException {

        // CLASS VALIDATORS
        Arrays.stream(getClassValidators(field)).forEach(classValidator -> {
            Class<?> validatorClass = getAnnotationValue(classValidator, "value");

            Validator validator = instantiateCustomValidator(validatorClass.getName());
            invokeValidatorMethod(validator, "setRecord", Object.class, record);
            validator.setFieldName(field.getName());
            validator.setValidationReport(validationReport);
            validator.validate();
        });

        // EACH VALIDATORS
        Annotation[] eachValidators = getEachValidators(field);
        for (Annotation eachValidator : eachValidators) {
            Class<?> validatorClass = getAnnotationValue(eachValidator, "value");

            Validator validator = instantiateCustomValidator(validatorClass.getName());
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            invokeValidatorMethod(validator, "setValue", Object.class, field.get(record));
            validator.setFieldName(field.getName());
            validator.setValidationReport(validationReport);
            validator.validate();
        }
    }

    private Annotation[] getClassValidators(Field field) {
        return Arrays.stream(field.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType().isAnnotationPresent(ClassValidator.class))
                .toArray(Annotation[]::new);
    }

    private Annotation[] getEachValidators(Field field) {
        return Arrays.stream(field.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType().isAnnotationPresent(EachValidator.class))
                .toArray(Annotation[]::new);
    }
}
