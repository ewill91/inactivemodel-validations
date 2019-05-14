package inactive.model.record;

import inactive.model.validator.ValidationReport;
import inactive.model.validator.commands.ClassValidatorValidationCommand;
import inactive.model.validator.commands.EachValidatorValidationCommand;
import inactive.model.validator.annotation.ClassValidator;
import inactive.model.validator.annotation.EachValidator;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
class RecordValidator {

    private ValidationReport validationReport;

    private Record record;

    RecordValidator(Record record) {
        this.record = record;
        validationReport = new ValidationReport();
    }

    ValidationReport validate() {

        // To make inheritance work, the fields have to be collected from all superclasses.
        List<Field> fields = collectFields(record.getClass(), new ArrayList<>());
        fields.forEach(this::validateField);

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

    private List<Field> collectFields(Class c, List<Field> fields) {
        if (c == null) {
            return fields;
        }

        fields.addAll(Arrays.asList(c.getDeclaredFields()));

        collectFields(c.getSuperclass(), fields);

        return fields;
    }
}
