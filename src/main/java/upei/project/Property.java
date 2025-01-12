package upei.project;

/**
 * Represents a property on the game board.
 * A Property extends Place and contains details specific to properties,
 * such as price, rent values, and ownership information.
 */
public class Property extends Place {
    private double price;
    private int[] rent;
    private int storeys;
    private Player owner;
    private Bank bank;

    /**
     * Constructor to initialize the property with a name and price.
     * Sets the default rent values for the property.
     * @param name The name of the property.
     * @param price The price of the property.
     */
    public Property(String name, double price) {
        super(name);
        this.price = price;
        rent = new int[]{4, 10, 30, 50, 70, 120};
        storeys = 0;
        owner = null;
        this.bank=new Bank();
    }

    /**
     * Pays the purchase cost of the property to the bank.
     * @param money The money to pay for the property.
     */
    public void payPurchaseCost(double money){
        bank.deposit(money);
    }

    /**
     * Gets the price of the property.
     * @return the price of the property.
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Gets the owner of the property.
     * @return the owner of the property.
     */
    public Player getOwner(){
        return this.owner;
    }

    /**
     * Sets a new owner for the property.
     * @param newOwner the player who will own the property.
     */
    public void setOwner(Player newOwner){
        this.owner = newOwner;
    }


    public void payRent(double money){
        this.owner.increaseMoney(money);
    }


    public double getRent(){
        return this.rent[this.storeys];
    }

    public void addStorey(){
        this.storeys+=1;
        this.bank.deposit(this.getOwner().pay(50));
    }

    public int getStoreys(){
        return this.storeys;
    }

}
