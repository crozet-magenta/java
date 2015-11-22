package Model;

/**
 *
 */
public class Child extends Attraction {

    /**
     * Default constructor
     */
    public Child() {
    }

    /**
     * Parameter constructor
     *
     * @param name name of the attraction
     * @param minAge min age required to play
     * @param maxAge max age authorized
     * @param price price to buy the attraction
     */
    public Child(String name, int minAge, int maxAge, double price) {
        super(name, minAge, maxAge, price);
    }
}