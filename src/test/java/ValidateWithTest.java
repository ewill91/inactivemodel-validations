import helper.AnnotatedTestClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidateWithTest {

    @Test
    void testAnnotatedTestClass_isValid() {
        AnnotatedTestClass annotatedTestClass = new AnnotatedTestClass();
        annotatedTestClass.setFriendlyString("hello");

        assertTrue(annotatedTestClass.isValid());
    }

    @Test
    void testAnnotatedTestClass_isNotValid() {
        AnnotatedTestClass annotatedTestClass = new AnnotatedTestClass();
        annotatedTestClass.setFriendlyString("definitelynothello");

        assertFalse(annotatedTestClass.isValid());
        assertTrue(annotatedTestClass.hasErrors());
    }
}
