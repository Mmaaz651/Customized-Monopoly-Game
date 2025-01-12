package upei.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RailRoadTest {

    private RailRoad railRoad;
    private Bank bank;

    @BeforeEach
    void setUp() {
        // Initialize the RailRoad and Bank instances for testing
        railRoad = new RailRoad("TestRailRoad");
        bank = new Bank();
    }

    @Test
    void testRailRoadConstructor() {
        // Test that the RailRoad is initialized with the correct name and default charge
        assertEquals("TestRailRoad", railRoad.getName(), "RailRoad name should match the provided name.");
        assertEquals(200, railRoad.getCharge(), "RailRoad default charge should be 200.");
    }

    @Test
    void testGetCharge() {
        // Test that the charge for using the railroad is correctly returned
        assertEquals(200, railRoad.getCharge(), "RailRoad charge should return the correct amount.");
    }

    @Test
    void testPayCharge() {
        // Test that the bank correctly receives the money when paying the charge
        railRoad.payCharge(100);
        assertEquals(100+bank.getInitialBalance(), bank.getBankBalance(), "Bank balance should reflect the paid charge of 100.");
    }

    @Test
    void testPayCharge_multiplePayments() {
        // Test multiple payments to the railroad's bank
        railRoad.payCharge(50);
        railRoad.payCharge(150);
        assertEquals(200+bank.getInitialBalance(), bank.getBankBalance(), "Bank balance should reflect the total paid charge of 200.");
    }

    @Test
    void testRailRoadIsPlaceInstance() {
        // Check that RailRoad is an instance of Place (since RailRoad extends Place)
        assertTrue(railRoad instanceof Place, "RailRoad should be an instance of Place.");
    }
}
