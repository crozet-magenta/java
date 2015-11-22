package Model;

/**
 * RollerCoaster Attraction
 */
public class RollerCoaster extends Attraction {

    /**
     * Default constructor
     */
    public RollerCoaster() {
    }


    /**
     * Parameter constructor
     * @param name
     * @param minAge
     * @param maxAge
     * @param price
     */
    public RollerCoaster(String name, int minAge, int maxAge, double price) {
        super(name, minAge, maxAge, price);
    }
}