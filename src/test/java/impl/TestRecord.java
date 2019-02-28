package impl;

import inactive.model.record.Record;
import inactive.model.validators.Length;
import inactive.model.validators.UsingRegex;
import inactive.model.validators.Uuid;
import inactive.model.validators.ValidateWith;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestRecord implements Record {

    @Uuid
    private String uuid;

    @ValidateWith(HelloValidator.class)
    private String sayHello;

    @Length(min = 1, max = 10)
    private String stringWithLengthValidator;

    @Length(max = 15)
    @UsingRegex(regex = "[a-zA-Z0-9]+")
    private String alphanumericMax15Chars;
}
