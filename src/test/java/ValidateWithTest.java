import impl.AnnotatedTestClass;
import impl.TestRecord;
import inactive.model.record.RecordValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidateWithTest {

    @Test
    void testAnnotatedTestClass_valid() {
        AnnotatedTestClass annotatedTestClass = new AnnotatedTestClass();
        annotatedTestClass.setFriendlyString("hello");

        assertTrue(annotatedTestClass.isValid());
    }

    @Test
    void testAnnotatedTestClass_invalid() {
        AnnotatedTestClass annotatedTestClass = new AnnotatedTestClass();
        annotatedTestClass.setFriendlyString("definitelynothello");

        assertFalse(annotatedTestClass.isValid());
        assertTrue(annotatedTestClass.hasErrors());
    }

    @Test
    void testAnnotatedTestClass_validUuid() {
        AnnotatedTestClass annotatedTestClass = new AnnotatedTestClass();
        annotatedTestClass.setFriendlyString("hello");
        annotatedTestClass.setUuid("f0ce421e-b2ae-41e4-a166-6b3218c840b7");

        assertTrue(annotatedTestClass.isValid());
        assertFalse(annotatedTestClass.hasErrors());
    }

    @Test
    void testAnnotatedTestClass_invalidUuid() {
        AnnotatedTestClass annotatedTestClass = new AnnotatedTestClass();
        annotatedTestClass.setFriendlyString("hello");
        annotatedTestClass.setUuid("not-a-uuid");

        assertFalse(annotatedTestClass.isValid());
        assertTrue(annotatedTestClass.hasErrors());

        annotatedTestClass.getValidationErrors().prettyPrint();
    }

    @Test
    void testTestRecordClass_valid() {
        TestRecord testRecord = new TestRecord();
        testRecord.setUuid("f0ce421e-b2ae-41e4-a166-6b3218c840b7");
        testRecord.setRandomString("hello");

        RecordValidator recordValidator = new RecordValidator(testRecord);

        assertTrue(recordValidator.isValid());
        assertFalse(recordValidator.hasErrors());
    }
}
