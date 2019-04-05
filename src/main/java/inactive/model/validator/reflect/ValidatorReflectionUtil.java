package inactive.model.validator.reflect;

import inactive.model.validator.Validator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidatorReflectionUtil {

    /**
     * Returns a {@code Validator} object. The instantiation happens with
     * the default constructor.
     *
     * If the instantiation throws an exception, the program quits.
     *
     * @param className the fully qualified name of the desired class.
     * @param type the type of the class that should be returned.
     * @param <T> the type of param {@code type}. Has to implement {@link Validator}.
     * @return the {@code Validator} object.
     */
    public static <T extends Validator> T instantiateValidator(Class<T> type, String className) {

        T validator = null;

        try {
            validator = type.cast(Class.forName(className).newInstance());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            log.error("Failed to instantiate validator class '{}'", className);

            e.printStackTrace();
        }

        return validator;
    }
}
