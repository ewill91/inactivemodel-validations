package inactive.model.validator.reflect;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

@Slf4j
public class AnnotationReflectionUtil {

    /**
     * Returns the {@code Object} object of an element from the given
     * annotation. The {@code String} elementName specifies the name of
     * the element that holds the desired value.
     *
     * If the element does not exist or cannot be accessed, the program
     * quits.
     *
     * @param annotation the annotation that holds the element.
     * @param elementName the name of the element.
     * @return the {@code Object} element.
     */
    public static Object getElementFromAnnotation(Annotation annotation, String elementName) {
        Object value = null;

        try {
            value = annotation.getClass().getMethod(elementName).invoke(annotation);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("Failed to get element '{}' from annotation '{}'",
                    elementName, annotation.annotationType().getName());

            // TODO: Don't cause program to quit.
            e.printStackTrace();
        }

        return value;
    }

    /**
     * Returns the {@code Class} class of an element from the given
     * annotation. The {@code String} elementName specifies the name of
     * the element that holds the desired class.
     *
     * If the element does not exist or cannot be accessed, the program
     * quits.
     *
     * @param annotation the annotation that holds the element.
     * @param elementName the name of the element.
     * @return the {@code Class} class.
     */
    public static Class<?> getValidatorClassFromAnnotation(Annotation annotation, String elementName) {
        return (Class) getElementFromAnnotation(annotation, elementName);
    }

    /**
     * Returns the {@code Annotation[]} objects that decorate a given field.
     * The {@code String} fieldName specifies the name of the field from which
     * the decorating annotations are returned.
     *
     * If the field does not exist, the program quits.
     *
     * @param record the record that holds the field.
     * @param fieldName the name of the field.
     * @return the decorating {@code Annotation[]} objects.
     */
    @Deprecated
    public static Annotation[] getDeclaredFieldAnnotations(Object record, String fieldName) {
        Annotation[] annotations = null;

        try {
            annotations = record.getClass().getDeclaredField(fieldName).getAnnotations();
        } catch (NoSuchFieldException e) {
            log.error("Field '{}' does not exist", fieldName);

            // TODO: Don't cause program to quit.
            e.printStackTrace();
        }

        return annotations;
    }
}
