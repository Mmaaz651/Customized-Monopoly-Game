package upei.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Property class.
 */
class PropertyTest {

    private Player alice;
    private Player bob;
    private Property property;

    @BeforeEach
    void setUp() {
        alice = new Player("Alice");
        bob = new Player("Bob");
        property = new Property("UPEI Property", 200);
    }

    @Test
    void testPropertyInitialization() {
        // Check if the property name is set correctly
        assertEquals("UPEI Property", property.getName());

        // Check if the price is set correctly
        assertEquals(200, property.getPrice());

        // Initially, the property should have no owner
        assertNull(property.getOwner());

        // The property should have 0 storeys at initialization
        assertEquals(0, property.getStoreys());

        // Check the default rent value for the property
        assertEquals(4, property.getRent());
    }

    @Test
    void testPropertyPurchase() {
        // Alice purchases the property
        property.setOwner(alice);

        // Alice should now be the owner of the property
        assertEquals(alice, property.getOwner());

        // Check if the property price matches the initial value
        assertEquals(200, property.getPrice());

        // Alice should pay the purchase cost (this test assumes the payment goes to the bank)
        property.payPurchaseCost(property.getPrice());
        // Assuming the bank’s deposit method is tracked, you could add assertions here
        // to check if the bank balance was updated. This depends on how the Bank class is implemented.
    }


    @Test
    void testAddStorey() {
        // Alice purchases the property
        property.setOwner(alice);

        // Initially, the property has 0 storeys
        assertEquals(0, property.getStoreys());

        // Alice adds a storey to the property
        property.addStorey();

        // After adding a storey, the property should have 1 storey
        assertEquals(1, property.getStoreys());

        // Alice should have paid 50 to the bank for the storey upgrade
        // You can add assertions to verify the bank balance or Alice’s account balance, depending on implementation
    }

    @Test
    void testRentAfterStoreyAddition() {
        // Alice purchases the property
        property.setOwner(alice);

        // Check initial rent for the property (before adding storeys)
        double initialRent = property.getRent();
        assertEquals(4, initialRent);

        // Add a storey and check the rent again
        property.addStorey();
        assertEquals(10, property.getRent());  // Rent should increase after storey is added

        // Add another storey and check rent
        property.addStorey();
        assertEquals(30, property.getRent());  // Rent should increase with each storey
    }

    @Test
    void testSetOwner() {
        // Initially, the property has no owner
        assertNull(property.getOwner());

        // Set Alice as the owner
        property.setOwner(alice);
        assertEquals(alice, property.getOwner());

        // Set Bob as the new owner
        property.setOwner(bob);
        assertEquals(bob, property.getOwner());
    }


}

