package testdata;

import inactive.model.record.Record;
import inactive.model.validators.Length;
import inactive.model.validators.NonNull;
import inactive.model.validators.UsingRegex;
import inactive.model.validators.Uuid;
import inactive.model.validators.ValidateWith;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRecord implements Record {

    @NonNull
    @Uuid
    private String uuid;

    @NonNull
    @Length(min = 6, max = 20)
    @UsingRegex(regex = "[a-zA-Z0-9]+")
    private String accountName;

    @ValidateWith(IsDefaultAddressValidator.class)
    private Address defaultAddress;

    private Address[] addresses;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Address {

        private boolean isDefault;
    }
}
