package upei.project;

/**
 * Represents a bank that holds the total money in the game.
 * The Bank class allows depositing money.
 */
public class Bank {
    private static double bank;
    private static double initialBalance=5000;

    /**
     * Constructor to initialize the bank.
     */
    public Bank(){Bank.bank=initialBalance;}

    /**
     * Deposits money into the bank.
     * @param money the money to be deposited.
     */
    public void deposit(double money){
        Bank.bank += money;
    }

    /**
     * Gets the current balance of the bank.
     * @return the current bank balance.
     */
    public double getBankBalance(){
        return Bank.bank;
    }

    /**
     * Gets the initial balance of the bank.
     * @return the current bank balance.
     */
    public double getInitialBalance(){
        return Bank.initialBalance;
    }

    public double giveLoan(double money){
        Bank.bank-=money;
        return money;
    }
}
