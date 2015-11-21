package Model;

/**
 * 
 */
public class Aquatic extends Attraction {

    /**
     * Default constructor
     */
    public Aquatic() {
    }


    /**
     * Parameter constructor
     * @param name
     * @param minAge
     * @param maxAge
     * @param price
     */
    public Aquatic(String name, int minAge, int maxAge, double price) {
        super(name, minAge, maxAge, price);
    }

}