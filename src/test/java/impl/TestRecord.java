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
}
