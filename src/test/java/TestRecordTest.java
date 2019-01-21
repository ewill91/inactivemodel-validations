import impl.TestRecord;
import inactive.model.validator.ValidationReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class TestRecordTest {

    @Test
    void test() {
        TestRecord testRecord = new TestRecord();
        ValidationReport report = testRecord.validate();


        assertFalse(report.hasErrors());
    }
}
