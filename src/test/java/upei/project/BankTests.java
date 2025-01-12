package upei.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private Bank bank;

    @BeforeEach
    void setUp() {
        // Reset the bank's static variable to ensure a clean state for each test
        bank = new Bank(); // Create a new bank object for each test
    }

    @Test
    void testInitialBalance() {
        assertEquals(5000, bank.getInitialBalance(), "The initial balance of the bank should be 5000.");
    }

    @Test
    void testDeposit() {
        bank.deposit(1000);
        assertEquals(6000, bank.getBankBalance(), "The bank balance should be 6000 after depositing 1000.");
    }

    @Test
    void testGetBankBalance() {
        assertEquals(5000, bank.getBankBalance(), "The current bank balance should be 5000.");
    }

    @Test
    void testGiveLoan() {
        double loanAmount = bank.giveLoan(2000);
        assertEquals(3000, bank.getBankBalance(), "The bank balance should be 3000 after giving a loan of 2000.");
        assertEquals(2000, loanAmount, "The loan amount given should be 2000.");
    }

    @Test
    void testMultipleDeposits() {
        bank.deposit(1000);
        bank.deposit(500);
        assertEquals(6500, bank.getBankBalance(), "The bank balance should be 7000 after depositing 1000 and 500.");
    }

    @Test
    void testNegativeDeposit() {
        bank.deposit(-1000);
        assertEquals(4000, bank.getBankBalance(), "The bank balance should be 4000 after depositing -1000.");
    }
}
