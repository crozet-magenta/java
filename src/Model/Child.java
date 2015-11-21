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
     * @param name
     * @param minAge
     * @param maxAge
     * @param price
     */
    public Child(String name, int minAge, int maxAge, double price) {
        super(name, minAge, maxAge, price);
    }
}