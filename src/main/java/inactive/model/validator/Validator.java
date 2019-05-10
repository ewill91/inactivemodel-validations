package inactive.model.validator;

public interface Validator {

    void validate();

    String getFieldName();

    void setFieldName(String fieldName);

    ValidationReport getValidationReport();

    void setValidationReport(ValidationReport validationReport);
}
