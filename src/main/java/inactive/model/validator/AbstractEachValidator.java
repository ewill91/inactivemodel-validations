package inactive.model.validator;

import inactive.model.util.ReflectionUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

@Slf4j
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractEachValidator implements EachValidator {

    protected Object record;

    protected Object value;

    protected String fieldName;

    protected ValidationReport validationReport;

    protected Annotation[] fieldAnnotations;

    protected Object getValueFromAnnotation(Class<? extends Annotation> annotationClass, String methodName) {
        Annotation annotation = Stream.of(fieldAnnotations)
                .filter(a -> a.annotationType().getName().equals(annotationClass.getName()))
                .findFirst()
                .orElse(null);

        if (annotation == null) {
            log.error("Field '{}' does not have annotation '{}'", fieldName, annotationClass.getName());

            throw new RuntimeException();
        }

        return ReflectionUtil.getValueFromAnnotation(annotation, methodName);
    }

    // TODO provide functionality for easy access to field's annotations
}
