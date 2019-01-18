package inactive.model.validator;

public interface Validator {
    void validate();

    String getFieldName();

    void setFieldName(String fieldName);

    ValidationErrors getValidationErrors();

    void setValidationErrors(ValidationErrors validationErrors);
}
