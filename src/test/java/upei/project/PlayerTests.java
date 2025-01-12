package upei.project;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;
    private Property property;

    @BeforeEach
    void setUp() {
        // Initialize the player and a property for testing
        player = new Player("TestPlayer");
        property = new Property("TestProperty", 200);
    }

    @Test
    void testPurchaseProperty_successfulPurchase() {
        player.purchaseProperty(property);
        assertEquals(1300, player.getMoneyAvailable(), "Player's money should decrease by the property's price.");
        assertTrue(player.getProperties().contains(property), "Property should be added to the player's properties.");
        assertEquals(player, property.getOwner(), "The player should be the owner of the purchased property.");
    }

    @Test
    void testPurchaseProperty_insufficientFunds() {
        player.reduceMoney(1400); // Reduce money so the player can't afford the property
        player.purchaseProperty(property);
        assertEquals(100, player.getMoneyAvailable(), "Player's money should not change.");
        assertFalse(player.getProperties().contains(property), "Property should not be added if player can't afford it.");
        assertNull(property.getOwner(), "Property should not have an owner if purchase fails.");
    }

    @Test
    void testRollDice() {
        int diceResult = player.rollDice();
        assertTrue(diceResult >= 1 && diceResult <= 6, "Dice roll should return a value between 1 and 6.");
        assertEquals(1, player.getNumberOfTurns(),"Number of turns should increment after rolling dice.");
    }

    @Test
    void testCalculateWealth() {
        player.purchaseProperty(property); // Buy one property worth 200
        assertEquals(1500, player.calculateWealth(), "Wealth should include money and property value.");
    }

    @Test
    void testPay() {
        double amountPaid = player.pay(500);
        assertEquals(1000, player.getMoneyAvailable(), "Player's money should decrease by the paid amount.");
        assertEquals(500, amountPaid, "Paid amount should match the input amount.");
    }

    @Test
    void testReduceMoney() {
        player.reduceMoney(300);
        assertEquals(1200, player.getMoneyAvailable(),"Player's money should decrease by the specified amount.");
    }

    @Test
    void testGetCurrentLocation() {
        Node<Place> location = new Node<>(new Place("Start"));
        player.setLocation(location);
        assertEquals(location, player.getCurrentLocation(), "Player's current location should match the set location.");
    }

}

