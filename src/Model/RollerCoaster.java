package Model;

/**
 *
 */
public class RollerCoaster extends Attraction {

    /**
     * Default constructor
     */
    public RollerCoaster() {
    }


    /**
     * Parameter constructor
     *
     * @param name name of the attraction
     * @param minAge min age required to play
     * @param maxAge max age authorized
     * @param price price to buy the attraction
     */
    public RollerCoaster(String name, int minAge, int maxAge, double price) {
        super(name, minAge, maxAge, price);
    }
}