package inactive.model.record;

import inactive.model.commands.ClassValidatorValidationCommand;
import inactive.model.commands.EachValidatorValidationCommand;
import inactive.model.validator.*;
import inactive.model.validator.annotation.ClassValidator;
import inactive.model.validator.annotation.EachValidator;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;


@Getter
public class RecordValidator {

    private ValidationReport validationReport;

    private Record record;

    public RecordValidator(Record record) {
        this.record = record;
        validationReport = new ValidationReport();
    }

    public ValidationReport validate() {
        Field[] fields = record.getClass().getDeclaredFields();

        for (Field field : fields) {
            validateField(field);
        }

        return validationReport;
    }

    // TODO(ewill): cleanup/split up
    private void validateField(Field field) {
        Arrays.stream(getClassValidatorAnnotations(field)).forEach(classValidatorAnnotation ->
            new ClassValidatorValidationCommand(record, field, classValidatorAnnotation, validationReport).validate()
        );

        Arrays.stream(getEachValidatorAnnotations(field)).forEach(eachValidatorAnnotation ->
            new EachValidatorValidationCommand(record, field, eachValidatorAnnotation, validationReport).validate()
        );
    }

    private Annotation[] getClassValidatorAnnotations(Field field) {
        return Arrays.stream(field.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType().isAnnotationPresent(ClassValidator.class))
                .toArray(Annotation[]::new);
    }

    private Annotation[] getEachValidatorAnnotations(Field field) {
        return Arrays.stream(field.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType().isAnnotationPresent(EachValidator.class))
                .toArray(Annotation[]::new);
    }
}
