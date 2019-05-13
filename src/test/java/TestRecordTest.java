import impl.TestRecord;
import inactive.model.validator.ValidationReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class TestRecordTest {

    @Test
    void test() {
        TestRecord testRecord = new TestRecord();
        testRecord.setUuid("ffdfea9d-d85d-4b69-be6b-f2cd5b0b7dfe");
        testRecord.setStringWithLengthValidator("123456789011111111111111111111");
        testRecord.setAlphanumericMax15Chars("abc123");
        ValidationReport report = testRecord.validate();

        if (report.hasErrors()) {
            report.getErrors().forEach(System.out::println);
        }

//        assertFalse(report.hasErrors());
    }
}
