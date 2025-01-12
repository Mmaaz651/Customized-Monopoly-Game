package upei.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JailTest {

    private Jail jail;

    @BeforeEach
    void setUp() {
        // Initialize the Jail instance with a test name
        jail = new Jail("Test Jail");
    }

    @Test
    void testJailConstructor() {
        // Check that the Jail is initialized with the correct name
        assertEquals("Test Jail", jail.getName(), "Jail name should match the provided name.");
    }

    @Test
    void testJailIsPlaceInstance() {
        // Check that Jail is an instance of Place (since Jail extends Place)
        assertTrue(jail instanceof Place, "Jail should be an instance of Place.");
    }
}
