package Model;

/**
 *
 */
public class Carousel extends Attraction {

    /**
     * Default constructor
     */
    public Carousel() {
    }

    /**
     * Parameter constructor
     *
     * @param name name of the attraction
     * @param minAge min age required to play
     * @param maxAge max age authorized
     * @param price price to buy the attraction
     */
    public Carousel(String name, int minAge, int maxAge, double price) {
        super(name, minAge, maxAge, price);
    }
}