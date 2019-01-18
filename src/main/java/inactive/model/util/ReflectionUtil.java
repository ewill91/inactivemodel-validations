package inactive.model.util;

import inactive.model.validator.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {
    public static Validator instantiateCustomValidator(String className) {
        Validator validator = null;
        try {
            validator = (Validator) Class.forName(className).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace(); // TODO(ewill): What to do with all those exceptions? :(
        }

        return validator;
    }

    public static void invokeValidatorMethod(Validator validator, String methodName, Class<?> parameterType, Object argument) {
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

    public static Object getAnnotationValue(Annotation annotation, String methodName) {
        Object validatorClass = null;
        try {
            validatorClass = annotation.getClass().getMethod(methodName).invoke(annotation);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); // TODO(ewill): What to do with all those exceptions? :(
        }

        return validatorClass;
    }
}
