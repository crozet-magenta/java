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
     *
     * @param name name of the attraction
     * @param minAge min age required to play
     * @param maxAge max age authorized
     * @param price price to buy the attraction
     */
    public DropTower(String name, int minAge, int maxAge, double price) {
        super(name, minAge, maxAge, price);
    }
}