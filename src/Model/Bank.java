package Model;

import java.sql.Timestamp;

/**
 * 
 */
public class Bank extends Model {

    /**
     *
     */
    private Timestamp loaningDate;

    /**
     *
     */
    private double loaningAmount;

    /**
     *
     */
    private double maxAmount;


    /**
     * Bank instance (singleton)
     */
    private static Bank INSTANCE;


    /**
     * Default bank constructor
     */
    private Bank() {

    } // Bank()


    /**
     * Function getInstance()
     * Singleton
     * @return INSTANCE
     */
    public static Bank getInstance() {
        if(INSTANCE == null)
            INSTANCE = new Bank();

        return INSTANCE;
    }


    /**
     * 
     */
    public void takeLoan() {
        // TODO implement here
    }

    /**
     * 
     */
    public void payLoan() {
        // TODO implement here
    }


    @Override
    public String toString() {
        return "Bank{" +
                "loaningDate=" + loaningDate +
                ", loaningAmount=" + loaningAmount +
                ", maxAmount=" + maxAmount +
                '}';
    }
}