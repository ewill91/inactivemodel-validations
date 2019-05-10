package inactive.model.record;

import inactive.model.validator.ValidationReport;
import inactive.model.validator.commands.ClassValidatorValidationCommand;
import inactive.model.validator.commands.EachValidatorValidationCommand;
import inactive.model.validator.annotation.ClassValidator;
import inactive.model.validator.annotation.EachValidator;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

@Getter
class RecordValidator {

    private ValidationReport validationReport;

    private Record record;

    RecordValidator(Record record) {
        this.record = record;
        validationReport = new ValidationReport();
    }

    ValidationReport validate() {
        Field[] fields = record.getClass().getDeclaredFields();

        for (Field field : fields) {
            validateField(field);
        }

        return validationReport;
    }

    private void validateField(Field field) {
        Arrays.stream(getValidatorAnnotations(field, ClassValidator.class)).forEach(classValidatorAnnotation ->
            new ClassValidatorValidationCommand(record, field, classValidatorAnnotation, validationReport).validate()
        );

        Arrays.stream(getValidatorAnnotations(field, EachValidator.class)).forEach(eachValidatorAnnotation ->
            new EachValidatorValidationCommand(record, field, eachValidatorAnnotation, validationReport).validate()
        );
    }

    private Annotation[] getValidatorAnnotations(Field field, Class<? extends Annotation> validatorType) {
        return Arrays.stream(field.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType().isAnnotationPresent(validatorType))
                .toArray(Annotation[]::new);
    }
}
