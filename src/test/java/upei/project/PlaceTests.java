package upei.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaceTest {
    private Place place;

    @BeforeEach
    void setUp() {
        // Set up a place before each test
        place = new Place("Park Place");
    }

    @Test
    void testGetName() {
        // Test if the name of the place is correctly returned
        assertEquals("Park Place", place.getName(), "The name of the place should be 'Park Place'.");
    }

    @Test
    void testNameSetInConstructor() {
        // Test if the name is correctly set during object construction
        Place newPlace = new Place("Boardwalk");
        assertEquals("Boardwalk", newPlace.getName(), "The name of the place should be 'Boardwalk'.");
    }
}
