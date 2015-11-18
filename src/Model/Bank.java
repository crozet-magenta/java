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
     *
     */
    private static Bank INSTANCE;


    /**
     * Default constructor
     */
    public Bank() {
    }


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

}