package upei.project;

/**
 * Represents a utility place on the game board, such as water bill, electric bill, income tax or luxury tax.
 * A Utility extends Place and handles utility payments.
 */
public class Utility extends Place {
    private static final String[] utilities = {"Water Bill", "Electric Bill", "Income Tax", "Luxury Tax"};
    private static final double[] utilitiesBill = {150, 150, 200, 750};

    private double bill;
    private Bank bank;

    /**
     * Constructor to initialize the utility with its name.
     * The method sets the correct utility bill based on the name.
     * @param name The name of the utility.
     */
    public Utility(String name) {
        super(name);
        bank = new Bank();
        for (int i = 0; i < utilities.length; i++) {
            if (utilities[i].equals(name)) {
                this.bill = utilitiesBill[i];
                break;
            }
        }
    }

    /**
     * Pays the utility bill to the bank.
     * @param money The money to pay for the utility bill.
     */
    public void payBill(double money){
        bank.deposit(money);
    }

    /**
     * Gets the bill amount for the utility.
     * @return the bill amount for the utility.
     */
    public double getBill() {
        return bill;
    }
}
