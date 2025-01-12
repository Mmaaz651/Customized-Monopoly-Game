package upei.project;

/**
 * Represents a Railroad place on the game board.
 * A RailRoad extends Place and contains a charge for using it.
 */
public class RailRoad extends Place {
    private double charge;
    Bank bank;

    /**
     * Constructor to initialize the Railroad with its name and a default charge.
     * @param name The name of the railroad.
     */
    public RailRoad(String name) {
        super(name);
        this.bank=new Bank();
        this.charge = 200; // Default charge for RailRoad
    }

    /**
     * Gets the charge for using the railroad.
     * @return the charge for the railroad.
     */
    public double getCharge() {
        return charge;
    }


    /**
     * Pays the charge for using the railroad by depositing the money into the bank.
     * @param money the amount of money to be deposited to the bank as the charge for using the railroad.
     */
    public void payCharge(double money){
        this.bank.deposit(money);
    }
}
