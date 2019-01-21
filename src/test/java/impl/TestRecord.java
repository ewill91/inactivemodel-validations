package impl;

import inactive.model.record.Record;
import inactive.model.validators.Uuid;
import inactive.model.validators.ValidateWith;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestRecord implements Record {
    
    @Uuid
    private String uuid;

    @ValidateWith(CustomStringValidation.class)
    private String randomString;

    public TestRecord() {
        uuid = "ffdfea9d-d85d-4b69-be6b-f2cd5b0b7dfe";
        randomString = "hello";
    }

}
