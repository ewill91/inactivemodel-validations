package inactive.model.validator;

import java.lang.annotation.Annotation;

public interface EachValidator extends Validator {

    Object getRecord();

    void setRecord(Object record);

    Object getValue();

    void setValue(Object value);

    Annotation[] getFieldAnnotations();

    void setFieldAnnotations(Annotation[] annotations);

}
