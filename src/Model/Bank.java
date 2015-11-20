package Model;

import java.sql.Timestamp;

/**
 * 
 */
public class Bank extends Model {

    /**
     *
     */
    private int loaningDay;

    /**
     *
     */
    private double loaningAmount;

    private double interest = 0.005;

    /**
     *
     */
    private double maxAmount = 50000;


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
    public String takeLoan(double amount) {
        String message;
        if (amount > this.maxAmount) {
            message = "Refusé (max " + this.maxAmount + "€)";
        } else {
            if (this.loaningAmount != 0) {
                message = "Vous avez déjà un prêt en cours";
            } else {
                this.loaningAmount = amount;
                this.loaningDay    = Park.getInstance().getDay();
                Park.getInstance().addMoney(amount);
                message = "Accepté";
            }
        }
        return message;
    }

    /**
     * 
     */
    public String payLoan() {
        String message;
        if (this.loaningAmount == 0) {
            message = "Vous n'avez pas de prêt en cours";
        } else {
            double value = this.loaningAmount + (this.interest * this.loaningAmount * (Park.getInstance().getDay() - this.loaningDay));
            if (Park.getInstance().pickMoney(value)) {
                message = "Prêt remboursé (" + value + "€)";
                this.loaningAmount = 0;
            } else {
                message = "Vous n'avez pas assez d'argent";
            }
        }
        return message;
    }

    public String getLoanStatus() {
        String message;
        if (this.loaningAmount == 0) {
            message = "Vous n'avez pas de prêt en cours";
        } else {
            double value = this.loaningAmount + (this.interest * this.loaningAmount * (Park.getInstance().getDay() - this.loaningDay));
            message = "Prêt de " + this.loaningAmount + "€ effectué il y a " + (Park.getInstance().getDay() - this.loaningDay) + " jours. (" + value + "€ à rembourser)";
        }
        return message;
    }


    @Override
    public String toString() {
        return "Bank{" +
                "loaningDay=" + loaningDay +
                ", loaningAmount=" + loaningAmount +
                ", maxAmount=" + maxAmount +
                '}';
    }
}