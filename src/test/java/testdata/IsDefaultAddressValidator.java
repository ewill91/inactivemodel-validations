package testdata;

import inactive.model.validator.AbstractClassValidator;

public class IsDefaultAddressValidator extends AbstractClassValidator<UserRecord> {

    public void validate() {
        if (record.getDefaultAddress() == null ||
                !record.getDefaultAddress().isDefault()) {
            validationReport.addError("Default address was incorrectly set");
        }
    }
}
