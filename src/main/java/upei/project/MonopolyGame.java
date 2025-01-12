package upei.project;

import java.util.ArrayList;

/**
 * Represents the Monopoly game. This class manages the game mechanics, including the players,
 * the game board, and the turns. It allows players to roll dice, move around the board,
 * and purchase properties. The game ends after each player has completed their turns,
 * and the player with the highest wealth wins.
 */
public class MonopolyGame {
    private Node<Place> startLocation;   // The current location on the board
    private Node<Player> currentPlayer;    // The current player in the game
    private int numberPlayers;             // The number of players in the game

    ArrayList<Place> places;       // List of places (board spaces) in the game
    String[] placeNames = {        // The names of the board spaces
            "Go", "UPEI", "Browns Court", "Income Tax",
            "RailRoad", "Queens Street", "Kirkwood Drive", "Euston Street",
            "Visit Jail", "Fitzroy Street", "Electric Bill", "Victory Avenue", "Belmont Street",
            "RailRoad", "Exhibition Drive", "Allen Street",
            "Saint Peters Road", "Free Parking", "FalconWood Drive",
            "Sherwood", "Hillsborough Park", "Belvedere Avenue", "Maloney Drive",
            "Lewis Point", "Water Bill", "Beach Grove", "Go To Jail", "East Royalty",
            "Royalty Crossing", "MayPoint Road", "RailRoad", "West Royalty",
            "Luxury Tax", "Burns Avenue"
    };
    int[] placePrices = {          // The prices for properties and other costs
            200, 60, 60, 200, 200, 100, 100, 120, 0, 140, 150, 140, 160, 200, 100, 160, 200,
            0, 220, 220, 240, 200, 260, 260, 150, 260, 200, 300, 300, 320, 200, 350, 750, 400
    };

    /**
     * Constructor to initialize the Monopoly game.
     * This method sets up the board and initializes the players.
     * @param playersNames the names of the players participating in the game.
     */
    public MonopolyGame(ArrayList<String> playersNames) {
        places = new ArrayList<>();
        initializeBoard();  // Initializes the game board with different places
        numberPlayers = playersNames.size();
        initializePlayers(playersNames);  // Initializes players and their starting positions
    }

    /**
     * Initializes the players for the game. Each player is assigned a starting position on the board,
     * and the list of players is linked in a circular fashion.
     * @param playersNames a list of player names to be initialized in the game.
     */
    public void initializePlayers(ArrayList<String> playersNames){
        currentPlayer = new Node<>(new Player(playersNames.get(0)));  // Create the first player
        currentPlayer.getData().setLocation(this.startLocation);  // Set their starting location
        Node<Player> first = currentPlayer;  // Save reference to first player

        // Initialize other players and link them in a circular manner
        for (int i = 1; i < numberPlayers; i++) {
            Node<Player> newPlayer = new Node<>(new Player(playersNames.get(i)));
            newPlayer.getData().setLocation(this.startLocation);
            currentPlayer.next = newPlayer;  // Link current player to the new player
            currentPlayer = newPlayer;
        }
        currentPlayer.next = first;  // Link the last player to the first player (circular list)
        currentPlayer = first;  // Set the current player to the first player
    }

    /**
     * Initializes the game board by creating places (spaces) such as properties, utilities, and special spaces.
     * Each place is created and added to the `places` list, which represents the game board.
     */
    public void initializeBoard() {
        // Create the list of places on the board
        places = new ArrayList<>();
        for (int i = 0; i < placeNames.length; i++) {
            Place place;

            // Create different types of places based on the name
            switch (placeNames[i]) {
                case "Water Bill":
                case "Electric Bill":
                case "Income Tax":
                case "Luxury Tax":
                    place = new Utility(placeNames[i]);  // Utility places (e.g., bills)
                    break;
                case "RailRoad":
                    place = new RailRoad(placeNames[i]);  // Special RailRoad places
                    break;
                case "Go To Jail":
                case "Visit Jail":
                    place = new Jail(placeNames[i]);  // Special Jail places
                    break;
                default:
                    place = new Property(placeNames[i], placePrices[i]);  // Regular property
            }
            places.add(place);  // Add created place to the list of places
        }

        // Initialize the linked list for the places
        if (places.size() > 0) {
            startLocation = new Node<>(places.get(0));  // Start the circular linked list with the first place
            Node<Place> previousNode = startLocation;

            // Loop through the remaining places and link them in a circular manner
            for (int i = 1; i < places.size(); i++) {
                Node<Place> newNode = new Node<>(places.get(i));  // Create new node for the place
                previousNode.next = newNode;  // Link the previous node to the new node
                previousNode = newNode;  // Move to the next node
            }

            // After the loop, link the last node to the first node to make the list circular
            previousNode.next = startLocation;
        }
    }


    /**
     * Starts the game and handles the turns for all players.
     * Each player rolls the dice, moves on the board, and takes actions based on the place they land on.
     * The game continues until all players have taken their turns.
     */
    public void playGame() {
        // Each player gets 20 turns, so we keep track of how many turns each player has taken
        int totalTurns = 20 * numberPlayers; // Total number of turns in the game

        // Loop through each player until all turns are completed
        while (totalTurns > 0) {
            Player player = currentPlayer.getData();

            // Print the player name and the current turn
            System.out.println("Player playing: " + player.getName());

            // Roll the dice to determine movement
            int result = player.rollDice();
            System.out.println("Dice result: " + result);

            // Move the player by the number of spaces rolled
            for (int i = 0; i < result; i++) {
                player.move();
            }

            // Get the place the player landed on
            Place place = player.getCurrentLocation().getData();
            System.out.println("Player: "+player.getName()+" reached "+place.getName());
            if(place.getName().equals("Go")){
                player.increaseMoney(200);
                System.out.println("Player: "+player.getName()+" reached the initial point Go so giving $200\n");
            }


            // Check for different types of places and handle accordingly
            if (place instanceof Property && !place.getName().equals("Go") && !place.getName().equals("Free Parking")) {
                Property property = (Property) place;
                if (property.getOwner() == null) {
                    player.purchaseProperty(property);  // Player purchases the property if it's unowned
                } else if (player.getProperties().contains(property)) {
                    property.addStorey();
                    System.out.println(player.getName()+" Own "+property.getName()+" so built another storey for $50. This place is now "+property.getStoreys()+" storey building\n");
                } else{
                    property.payRent(player.pay(property.getRent()));
                    System.out.println(player.getName()+" Payed rent of amount $"+property.getRent()+" to the owner "+property.getOwner().getName()+"\n");
                }
            }else if (place instanceof Utility) {
                Utility utility = (Utility) place;
                utility.payBill(player.pay(utility.getBill()));  // Player pays utility bill
                System.out.println(player.getName()+" Payed "+utility.getName() +" of amount $"+utility.getBill()+"\n");
            }else if(place instanceof RailRoad){
                RailRoad railRoad = (RailRoad) place;
                railRoad.payCharge(player.pay(railRoad.getCharge()));
                System.out.println(player.getName()+" Payed "+railRoad.getName() +" charge of amount $"+railRoad.getCharge()+"\n");
            }
            else if (place instanceof Jail) {
                // If the player lands on "Go to Jail", reduce their money by $200
                if(place.getName().equals("Go To Jail")){
                    System.out.println(player.getName() + " landed on 'Go to Jail'. Reducing money by $200.\n");
                    player.reduceMoney(200);  // Call a method to reduce player's money
                }
            }

            // Move to the next player in the list
            currentPlayer = currentPlayer.next;

            // Decrement the total turns after each player's turn
            totalTurns--;
        }

        declareWinner();  // Declare the winner after all turns are complete
    }


    /**
     * Declares the winner of the game based on the total wealth of each player.
     * The player with the highest total wealth (money and property value) wins.
     */
    private void declareWinner() {
        Player winner = null;
        double maxWealth = 0;

        Node<Player> temp = currentPlayer;
        // Loop through all players to calculate their wealth and find the winner
        for (int i = 0; i < numberPlayers; i++) {
            Player player = temp.getData();
            double totalWealth = player.calculateWealth();  // Calculate the player's total wealth

            // Update the winner if this player has more wealth
            if (totalWealth > maxWealth) {
                maxWealth = totalWealth;
                winner = player;
            }
            temp = temp.next;  // Move to the next player in the list
        }
        System.out.println("Each player played 20 turns\n");
        System.out.println("The winner is: " + winner.getName()+" with $"+maxWealth+" amount of money and worth of property");  // Print the winner's name
        System.out.println(winner.getName()+" owns");
        for(int i=0;i<winner.getProperties().size();i++){
            System.out.println(winner.getProperties().get(i).getName());
        }
    }
    public Node<Player> getCurrentPlayer(){
        return this.currentPlayer;
    }
    public int getNumberPlayers(){
        return this.numberPlayers;
    }
    public void moveNextPlayer(){
        this.currentPlayer=this.currentPlayer.next;
    }
}
