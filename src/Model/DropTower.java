package Model;

/**
 * DropTower Attraction
 */
public class DropTower extends Attraction {

    /**
     * Default constructor
     */
    public DropTower() {
    }


    /**
     * Parameter constructor
     * @param name
     * @param minAge
     * @param maxAge
     * @param price
     */
    public DropTower(String name, int minAge, int maxAge, double price) {
        super(name, minAge, maxAge, price);
    }
}