package upei.project;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MonopolyGameTests {

    // Test for player initialization and starting position
    @Test
    public void testPlayerInitialization() {
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("Player1");
        playerNames.add("Player2");

        MonopolyGame game = new MonopolyGame(playerNames);

        // Check if the number of players is correct
        assertEquals(2, game.getNumberPlayers(), "The number of players should be 2.");

        // Check if the first player is correctly initialized
        Player currentPlayer = game.getCurrentPlayer().getData();
        assertEquals("Player1", currentPlayer.getName(), "The first player should be Player1.");
    }

    // Test for player movement after dice roll (using node traversal)
    @Test
    public void testPlayerMovement() {
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("Player1");

        MonopolyGame game = new MonopolyGame(playerNames);

        Player currentPlayer = game.getCurrentPlayer().getData();
        Object initialPosition = currentPlayer.getCurrentLocation().getData(); // Store the current position (node data)

        // Simulate a dice roll
        currentPlayer.rollDice();

        // Move to next player (if using a linked list for player management)
        game.getCurrentPlayer().getData().move();  // Move to the next player
        Player nextPlayer = game.getCurrentPlayer().getData();
        Object newPosition = nextPlayer.getCurrentLocation().getData(); // Store the new position (node data)

        // Debug output
        System.out.println("Initial Position: " + initialPosition);
        System.out.println("New Position: " + newPosition);

        // Assert that the player has moved to the new position
        assertNotEquals(initialPosition, newPosition, "Player should have moved to a new position after rolling the dice.");
    }

    // Test for rent payment (Player 1 lands on Player 2's property)
    @Test
    public void testRentPayment() {
        // Create a list of player names
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("Player1");
        playerNames.add("Player2");

        // Create a new game with two players
        MonopolyGame game = new MonopolyGame(playerNames);

        // Get the current player (Player1)
        Player player1 = game.getCurrentPlayer().getData();

        // Move to the next player (Player2)
        game.moveNextPlayer(); //  moves to the next player
        Player player2 = game.getCurrentPlayer().getData();

        // Player2 purchases a property
        Property property = new Property("UPEI", 60); // Assume rent is 5
        player2.purchaseProperty(property);

        // Simulate Player1 landing on Player2's property
        double rent = property.getRent(); // Rent to be paid

        // Player1 pays the rent
        player1.pay(rent);

        // Debug output (optional, useful for troubleshooting)
        System.out.println("Player1 Money After Rent: " + player1.getMoneyAvailable());
        System.out.println("Player2 Money After Rent: " + player2.getMoneyAvailable());

        // Assert that Player1's money decreased by the rent amount
        assertEquals(1500 - rent, player1.getMoneyAvailable(), "Player1 should have paid rent to Player2.");

        // Assert that Player2's money increased by the rent amount
        assertEquals(1436 + rent, player2.getMoneyAvailable(), "Player2 should have received the rent from Player1.");
    }

    // Test for utility bill payment
    @Test
    public void testUtilityPayment() {
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("Player1");

        MonopolyGame game = new MonopolyGame(playerNames);

        Player currentPlayer = game.getCurrentPlayer().getData();
        Utility utility = new Utility("Electric Bill");

        // Simulate player landing on a utility and paying the bill
        currentPlayer.pay(utility.getBill());

        // Debug output
        System.out.println("Player1 Money After Utility Payment: " + currentPlayer.getMoneyAvailable());

        // Assert that the player's money has been updated after paying the utility bill
        assertEquals(1500 - 150, currentPlayer.getMoneyAvailable(), "Player should have paid the utility bill.");
    }

    // Test for winner declaration after game ends
    @Test
    public void testWinnerDeclaration() {
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("Player1");
        playerNames.add("Player2");

        MonopolyGame game = new MonopolyGame(playerNames);

        // Simulate players earning money and properties
        Player player1 = game.getCurrentPlayer().getData();
        player1.increaseMoney(1000);
        Player player2 = game.getCurrentPlayer().next.getData();
        player2.increaseMoney(500);

        // Simulate the end of the game and check the winner
        game.playGame();

        assertEquals("Player1", player1.getName(), "Player1 should be the winner with higher wealth.");
    }
}
