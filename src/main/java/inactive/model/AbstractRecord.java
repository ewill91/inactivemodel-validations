package inactive.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inactive.model.validators.Uuid;
import inactive.model.validators.ValidateWith;
import inactive.model.validators.impl.UuidValidator;
import inactive.model.validator.AbstractValidator;
import inactive.model.validator.ValidationErrors;
import inactive.model.validator.Validator;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

import static inactive.model.util.ReflectionUtil.instantiateCustomValidator;
import static inactive.model.util.ReflectionUtil.invokeValidatorMethod;

/**
 * Currently supported annotations for validation:
 *  - ValidateWith(Validator.class)
 */

@NoArgsConstructor
public abstract class AbstractRecord {

    @Getter
    @JsonIgnore
    private ValidationErrors validationErrors = new ValidationErrors();

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
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            // TODO(ewill): Make more generic for different kinds of annotations
            if (field.isAnnotationPresent(ValidateWith.class)) {
                ValidateWith validateWith = field.getAnnotation(ValidateWith.class);
                Class<? extends AbstractValidator> validatorClass = validateWith.value();

                // Because of inheritance, it is more convenient to use the default constructor
                // and then set both fields via their setters.
                // Otherwise, every custom validator would have to implement a constructor
                // with a call to super().
                Validator validator = instantiateCustomValidator(validatorClass.getName());
                invokeValidatorMethod(validator, "setRecord", Object.class, this);
                invokeValidatorMethod(validator, "setFieldName", String.class, field.getName());
                validator.validate();
            }

            if (field.isAnnotationPresent(Uuid.class)) {
                UuidValidator uuidValidator = new UuidValidator();
                uuidValidator.setValidationErrors(validationErrors);
                uuidValidator.setFieldName(field.getName());

                field.setAccessible(true);
                uuidValidator.setValue(field.get(this));
                uuidValidator.validate();
            }
        }
    }
}
