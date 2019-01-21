package inactive.model.record;

import inactive.model.validator.*;
import inactive.model.validators.Uuid;
import inactive.model.validators.ValidateWith;
import inactive.model.validators.impl.UuidValidator;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

import static inactive.model.util.ReflectionUtil.getAnnotationValue;
import static inactive.model.util.ReflectionUtil.instantiateCustomValidator;
import static inactive.model.util.ReflectionUtil.invokeValidatorMethod;

@Getter
public class RecordValidator {

    private ValidationErrors validationErrors; // TODO make Report

    private Record record;

    public RecordValidator(Record record) {
        this.record = record;
        validationErrors = new ValidationErrors();
    }

    public boolean isValid() {
        try {
            validate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return !hasErrors();
    }

    public boolean hasErrors() {
        return !validationErrors.getErrors().isEmpty();
    }

    private void validate() throws IllegalAccessException {
        Field[] fields = record.getClass().getDeclaredFields();

        for (Field field : fields) {

            try { validateField(field); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    // TODO(ewill): cleanup
    private void validateField(Field field) throws IllegalAccessException {
        Annotation[] classValidators = getClassValidators(field);
        for (Annotation classValidator : classValidators) {
//            Annotation validatorAnnotation = field.getAnnotation(classValidator.annotationType());
            Class<?> validatorClass = getAnnotationValue(classValidator, "value");

            Validator validator = instantiateCustomValidator(validatorClass.getName());
            invokeValidatorMethod(validator, "setRecord", Object.class, record);
            validator.setFieldName(field.getName());
            validator.setValidationErrors(validationErrors);
            validator.validate();
        }

        Annotation[] eachValidators = getEachValidators(field);
        for (Annotation eachValidator : eachValidators) {
            Class<?> validatorClass = getAnnotationValue(eachValidator, "value");

            Validator validator = instantiateCustomValidator(validatorClass.getName());
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            invokeValidatorMethod(validator, "setValue", Object.class, field.get(record));
            validator.setFieldName(field.getName());
            validator.setValidationErrors(validationErrors);
            invokeValidatorMethod(validator, "setValidationErrors", ValidationErrors.class, validationErrors);
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

//    private boolean isClassValidator(Annotation annotation) {
//        Annotation[] annotations = annotation.annotationType().getDeclaredAnnotations()
//        return Arrays.stream(annotations)
//                .filter(a -> {
//                    a.getClass().isAnnotationPresent(ClassValidator.class);
//                })
//    }
}
