import inactive.model.validator.ValidationReport;
import org.junit.jupiter.api.Test;
import testdata.UserRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserRecordTest {

    @Test
    public void test() {
        UserRecord rec = new UserRecord();
        rec.setUuid("123e4567-e89b-12d3-a456-426655440000");
        rec.setAccountName("bestaccounteu");
        rec.setAddresses(new UserRecord.Address[] {
                new UserRecord.Address(false),
                new UserRecord.Address(true),
                new UserRecord.Address(false)
        });

        rec.setDefaultAddress(Stream.of(rec.getAddresses())
                .filter(UserRecord.Address::isDefault)
                .findAny()
                .orElse(null));

        List<UserRecord.Something> somethings = new ArrayList<>();
        somethings.add(new UserRecord.Something());
        rec.setSomethings(somethings);

        ValidationReport report = rec.validate();

        report.getErrors().forEach(System.out::println);
    }
}
