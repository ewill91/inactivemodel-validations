package inactive.model.util;

import inactive.model.validator.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {
    public static <T extends Validator> T instantiateCustomValidator(String className, Class<T> type) {
        // TODO instantiation fails?
        T validator = null;
        try {
            validator = type.cast(Class.forName(className).newInstance());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public static Class<?> getValidatorClassFromAnnotation(Annotation annotation, String methodName) {
        return (Class) getValueFromAnnotation(annotation, methodName);
    }

    public static Object getValueFromAnnotation(Annotation annotation, String methodName) {
        Object value = null;
        try {
            value = annotation.getClass().getMethod(methodName).invoke(annotation);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return value;
    }

    public static Annotation[] getDeclaredFieldAnnotations(Object record, String fieldName) {
        Annotation[] annotations = null;

        try {
            annotations = record.getClass().getDeclaredField(fieldName).getAnnotations();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return annotations;
    }

}
