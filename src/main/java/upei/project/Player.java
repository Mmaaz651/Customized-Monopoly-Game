package upei.project;

import java.util.ArrayList;

/**
 * Represents a player in the game.
 * The Player class contains information about the player's current location, properties, money, and actions.
 */
public class Player {
    private Node<Place> currentLocation;
    private int numberOfTurns;
    private String name;
    private double money;
    private ArrayList<Place> properties;
    private double bankLoan;
    private Bank bank;

    /**
     * Constructor to initialize the player with their name.
     * Sets the initial money, properties, and turn number for the player.
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.money = 1500; // Starting money
        this.properties = new ArrayList<>();
        this.numberOfTurns = 0;
        this.bankLoan=0;
        this.bank=new Bank();
    }

    /**
     * Allows the player to purchase a property if they have enough money and the property is unowned.
     * @param property The property to be purchased.
     */
    public void purchaseProperty(Property property) {
        if (money >= property.getPrice() && property.getOwner() == null) {
            property.payPurchaseCost(money);
            money -= property.getPrice();
            property.setOwner(this);
            properties.add(property);
            System.out.println(name + " purchased " + property.getName() +" for $"+property.getPrice()+"\n");
        }
    }

    /**
     * Gets the name of the player.
     * @return the name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Rolls a dice to generate a random number between 1 and 6.
     * @return the result of the dice roll.
     */
    public int rollDice() {
        Dice dice = new Dice();
        this.numberOfTurns++;
        return dice.roll();
    }

    /**
     * Calculates the total wealth of the player, including money and the value of owned properties.
     * @return the total wealth of the player.
     */
    public double calculateWealth() {
        double totalWealth = money;
        for (Place property : properties) {
            if (property instanceof Property) {
                totalWealth += ((Property) property).getPrice();
            }
        }
        return totalWealth;
    }

    /**
     * Pays an amount of money, deducting it from the player's balance.
     * Taking loan from bank if the amount of money is more than available balance
     * @param money the money to be paid.
     * @return the amount of money paid.
     */
    public double pay(double money){
        if(this.money<money){
            this.takeLoan(money);
            System.out.println(this.name+" Has taken loan of "+money);
        }
        this.money -= money;
        return money;
    }

    /**
     * Retrieves the list of properties owned by the player.
     * @return An ArrayList containing the properties owned by the player.
     */
    public ArrayList<Place> getProperties() {
        return this.properties;
    }

    /**
     * Retrieves the amount of money currently available to the player.
     * @return A double representing the player's available money.
     */
    public double getMoneyAvailable() {
        return this.money;
    }


    /**
     * Reduces the player's money by a specified amount.
     * This method subtracts the given amount from the player's current balance, effectively reducing their total money.
     * @param amount the amount of money to be deducted from the player's balance.
     */
    public void reduceMoney(int amount) {
        this.money -= amount;
    }

    /**
     * Gets the current location of the player on the game board.
     * @return the current location of the player.
     */
    public Node<Place> getCurrentLocation() {
        return this.currentLocation;
    }

    /**
     * Allows the player to take a loan from the bank.
     * Increases the player's money by the loan amount and tracks the total loan taken.
     * @param money The amount of money to be borrowed as a loan.
     */
    public void takeLoan(double money) {
        this.bank.giveLoan(money);
        this.bankLoan += money;
        this.money += money;
    }

    /**
     * Sets the player's current location on the game board.
     * Updates the player's position to a specified node.
     * @param location The new location of the player on the game board.
     */
    public void setLocation(Node<Place> location) {
        this.currentLocation = location;
    }

    /**
     * Moves the player to the next location on the game board.
     * Updates the player's current location to the next node in the board's structure.
     */
    public void move() {
        this.currentLocation = this.currentLocation.next;
    }

    /**
     * Increases the player's available money by a specified amount.
     * Adds the given amount to the player's current balance.
     * @param money The amount of money to be added.
     */
    public void increaseMoney(double money) {
        this.money += money;
    }

    /**
     * Retrieves the number of turns the player has taken in the game.
     * @return An integer representing the total number of turns taken by the player.
     */
    public int getNumberOfTurns() {
        return numberOfTurns;
    }


}
