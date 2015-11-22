package Model;

import Interfaces.ICleanable;
import Interfaces.IOpenable;

/**
 *
 */
public abstract class Attraction extends Model implements IOpenable, ICleanable {

    /**
     * Attraction maximum clean;
     */
    private final int clean = 100;
    /**
     * Price to clean the attraction
     */
    private final int cleanPrice = 50;
    /**
     * Name of the Attraction
     */
    protected String name = "Unamed Attraction";
    /**
     * Minimal age required to do the attraction
     */
    protected int minAge = 0;
    /**
     * price of the attraction (Boss mode : buy attraction)
     */
    protected double price = 0;
    /**
     * Maximum age required to do the attraction
     */
    protected int maxAge = 120;
    /**
     * Current clean level of the attraction
     */
    private int cleanLevel = clean;
    /**
     * Attraction status (0 : closed | 1 : open )
     * Default close
     */
    private boolean status = false;


    /**
     * Default constructor
     */
    public Attraction() {
    }


    /**
     * Attraction constructor
     *
     * @param name
     * @param minAge
     * @param maxAge
     * @param price
     */
    public Attraction(String name, int minAge, int maxAge, double price) {
        this.name = name;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.price = price;
    }


    /**
     * Open attraction
     */
    @Override
    public void open() {
        this.status = true;
    }


    /**
     * Close attraction
     */
    @Override
    public void close() {
        this.status = false;
    }


    /**
     * Return attraction cleanLevel
     *
     * @return int
     */
    public int getCleanLevel() {
        return cleanLevel;
    }


    /**
     * Check if the attraction is clean
     * Return true if cleanLevel > 50
     *
     * @return boolean
     */
    @Override
    public boolean is_clean() {
        return this.cleanLevel > 50;
    }

    /**
     * Clean the attraction
     */
    @Override
    public String clean() {
        if (Park.getInstance().pickMoney(cleanPrice)) {
            this.close();
            this.cleanLevel = clean;
            this.open();
        }

        return null;
    }


    @Override
    public String toString() {
        return "Attraction{" +
                "cleanLevel=" + cleanLevel +
                ", maxAge=" + maxAge +
                ", price=" + price +
                ", minAge=" + minAge +
                ", name='" + name + '\'' +
                '}';
    }


    /**
     * Return attraction name
     *
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * Check if attraction is opened
     *
     * @return boolean
     */
    public boolean is_open() {
        return status;
    }

    public int getType() {
        if (this instanceof Aquatic) return Area.AQUATIC;
        if (this instanceof Carousel) return Area.CAROUSEL;
        if (this instanceof Child) return Area.CHILD;
        if (this instanceof DropTower) return Area.DROP_TOWER;
        if (this instanceof RollerCoaster) return Area.ROLLER_COASTER;
        return -1;
    }
}