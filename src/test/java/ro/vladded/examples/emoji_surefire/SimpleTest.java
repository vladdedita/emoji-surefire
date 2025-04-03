package ro.vladded.examples.emoji_surefire;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleTest {

    @Test
    void testSuccess() {
        assertTrue(true);
    }

    @Test
    void testFailure() {
        assertEquals(1, 2);
    }
}
