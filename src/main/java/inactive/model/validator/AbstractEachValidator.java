package inactive.model.validator;

import inactive.model.validator.reflect.AnnotationReflectionUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

/**
 * Parent class for validators that validate individual fields in isolation.
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractEachValidator implements EachValidator {

    protected Object record;

    // TODO: Ideally, this should reflect the actual type of the object.
    protected Object value;

    protected String fieldName;

    protected ValidationReport validationReport;

    protected Annotation[] fieldAnnotations;

    /**
     * Returns the {@code Object} object from an annotation. Return values have to
     * be explicitly casted to the desired primitive.
     *
     * @param annotationClass the annotation where the element is declared.
     * @param elementName the name of the element that holds the desired value.
     * @return the {@code Object} object with the value from the annotation.
     */
    protected Object getElementFromAnnotation(Class<? extends Annotation> annotationClass, String elementName) {
        // TODO
        // Is there even a point to storing all the annotations here?
        // Without it, there would be no `null` to handle...
        Annotation annotation = Stream.of(fieldAnnotations)
                .filter(a -> a.annotationType().getName().equals(annotationClass.getName()))
                .findFirst()
                .orElse(null);

        if (annotation == null) {
            log.error("Field '{}' does not have annotation '{}'", fieldName, annotationClass.getName());
            return null;
        }

        return AnnotationReflectionUtil.getElementFromAnnotation(annotation, elementName);
    }
}
