package upei.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {
    private Utility waterBill;
    private Utility electricBill;
    private Utility incomeTax;
    private Utility luxuryTax;
    private Utility unknownUtility;

    @BeforeEach
    void setUp() {
        // Initialize various utilities for testing
        waterBill = new Utility("Water Bill");
        electricBill = new Utility("Electric Bill");
        incomeTax = new Utility("Income Tax");
        luxuryTax = new Utility("Luxury Tax");
        unknownUtility = new Utility("Unknown Utility"); // For edge case testing
    }

    @Test
    void testBillInitialization() {
        // Verify that each utility is assigned the correct bill amount
        assertEquals(150, waterBill.getBill(), "Water Bill amount should be correctly initialized.");
        assertEquals(150, electricBill.getBill(), "Electric Bill amount should be correctly initialized.");
        assertEquals(200, incomeTax.getBill(), "Income Tax amount should be correctly initialized.");
        assertEquals(750, luxuryTax.getBill(), "Luxury Tax amount should be correctly initialized.");
        assertEquals(0, unknownUtility.getBill(), "Unknown utility should default to a bill amount of 0.");
    }

    @Test
    void testPayBill() {
        // Simulate paying a utility bill and verify that the bank's balance increases
        Bank mockBank = new Bank();
        double initialBalance = mockBank.getBankBalance();
        waterBill.payBill(waterBill.getBill());
        assertTrue(mockBank.getBankBalance() > initialBalance,
                "Bank's balance should increase by the bill amount after payment.");
    }

    @Test
    void testInvalidUtilityName() {
        // Ensure that invalid utility names result in a bill amount of 0
        Utility invalidUtility = new Utility("Fake Utility");
        assertEquals(0, invalidUtility.getBill(),
                "Bill amount for an invalid utility should default to 0.");
    }

    @Test
    void testUtilityWithNoMatchingBill() {
        // Test behavior for a utility with an empty or unmatched name
        Utility noBillUtility = new Utility("");
        assertEquals(0, noBillUtility.getBill(),
                "Utility with no matching name should default to a bill amount of 0.");
    }

    @Test
    void testUtilityNameCaseSensitivity() {
        // Verify that utility names are case-sensitive
        Utility caseMismatchUtility = new Utility("water bill");
        assertEquals(0, caseMismatchUtility.getBill(),
                "Utility names should be case-sensitive and not match improperly.");
    }
}
