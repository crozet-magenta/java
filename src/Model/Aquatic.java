package Model;

/**
 * Aquatic Attraction
 */
public class Aquatic extends Attraction {

    /**
     * Default constructor
     */
    public Aquatic() {
    }


    /**
     * Parameter constructor
     *
     * @param name name of the attraction
     * @param minAge min age required to play
     * @param maxAge max age authorized
     * @param price price to buy the attraction
     */
    public Aquatic(String name, int minAge, int maxAge, double price) {
        super(name, minAge, maxAge, price);
    }

}