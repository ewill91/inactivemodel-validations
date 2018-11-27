package ewil.validations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Currently supported annotations for validation:
 *  - ValidateWith(Validator.class)
 */

@NoArgsConstructor
public abstract class Record {

    @Getter
    @JsonIgnore
    private ValidationErrors validationErrors = new ValidationErrors();

    public boolean isValid() {
        validate();
        return !hasErrors();
    }

    public boolean hasErrors() {
        return !validationErrors.getErrors().isEmpty();
    }

    private void validate() {
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
                invokeValidatorMethod(validator, "setValueKey", String.class, field.getName());
                validator.validate();
            }
        }
    }

    private Validator instantiateCustomValidator(String className) {
        Validator validator = null;
        try {
            validator = (Validator) Class.forName(className).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace(); // TODO(ewill): What to do with all those exceptions? :(
        }

        return validator;
    }

    private void invokeValidatorMethod(Validator validator, String methodName, Class<?> parameterType, Object argument) {
        try {
            Method targetMethod = validator.getClass().getMethod(methodName, parameterType);
            if (!targetMethod.isAccessible()) {
                targetMethod.setAccessible(true);
            }

            targetMethod.invoke(validator, argument);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); // TODO(ewill): What to do with all those exceptions? :(
        }
    }

}
