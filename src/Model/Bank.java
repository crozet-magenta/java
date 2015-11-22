package Model;


/**
 *
 * Bank Model
 */
public class Bank extends Model {

    /**
     * Date of Loan
     * Bank instance (singleton)
     */
    private static Bank INSTANCE;
    /**
     * date when the loan is taken
     */
    private int loaningDay;

    /**
     * amount of the loan
     */
    private double loaningAmount;

    /**
     * daily interest of the loan
     */
    private double interest = 0.005;

    /**
     * max loan amount
     */
    private double maxAmount = 500000;


    /**
     * Default bank constructor
     */
    private Bank() {

    } // Bank()


    /**
     * Function getInstance()
     * Singleton
     *
     * @return INSTANCE
     */
    public static Bank getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Bank();

        return INSTANCE;
    }


    /**
     * take loan of the given amount
     * @param amount amount of the wanted loan
     * @return OK if success, error message otherwise
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
                this.loaningDay = Park.getInstance().getDay();
                Park.getInstance().addMoney(amount);
                message = "OK";
            }
        }
        return message;
    }

    /**
     * pay the current loan
     * @return status message
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

    /**
     * Check the loan status
     * @return status message
     */
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

    /**
     * Get the max amount allowed
     * @return double
     */
    public double getMaxAmount() {
        return maxAmount;
    }
}