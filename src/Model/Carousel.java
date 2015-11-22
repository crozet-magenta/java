package Model;

/**
 * Carousel Attraction
 */
public class Carousel extends Attraction {

    /**
     * Default constructor
     */
    public Carousel(){}

    /**
     * Parameter constructor
     * @param name
     * @param minAge
     * @param maxAge
     * @param price
     */
    public Carousel(String name, int minAge, int maxAge, double price) {
        super(name, minAge, maxAge, price);
    }
}