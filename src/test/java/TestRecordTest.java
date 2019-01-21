import impl.TestRecord;
import inactive.model.validator.ValidationReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class TestRecordTest {

    @Test
    void test() {
        TestRecord testRecord = new TestRecord();
        testRecord.setSayHello("hello");
        testRecord.setUuid("ffdfea9d-d85d-4b69-be6b-f2cd5b0b7dfe");
        testRecord.setComment("asdfaklsdflaksdfs");
        ValidationReport report = testRecord.validate();


        assertFalse(report.hasErrors());
    }
}
