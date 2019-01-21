import impl.AnnotatedTestClass;
import impl.TestRecord;
import inactive.model.record.RecordValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestRecordTest {

    @Test
    void test() {
        TestRecord testRecord = new TestRecord();
        boolean isValid = testRecord.validate();

        assertTrue(isValid);
    }
}
