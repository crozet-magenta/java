package Model;

import Interfaces.ICleanable;
import Interfaces.IOpenable;

/**
 * 
 */
public abstract class Attraction extends Model implements IOpenable, ICleanable {

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
     * Attraction maximum clean;
     */
    private final int clean = 100;


    /**
     * Current clean level of the attraction
     */
    private int cleanLevel = clean;

    /**
     * Price to clean the attraction
     */
    private final int cleanPrice = 50;

    /**
     * Attraction status (0 : closed | 1 : open )
     * Default open
     */
    private boolean status = true;



    /**
     * Default constructor
     */
    public Attraction() {
    }

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
     * @return int
     */
    public int getCleanLevel() {
        return cleanLevel;
    }


    /**
     * Check if the attraction is clean
     * Return true if cleanLevel > 50
     * @return boolean
     */
    @Override
    public boolean is_clean() {

        if(this.cleanLevel > 50)
            return true;

        return false;
    }

    /**
     * Clean the attraction
     */
    @Override
    public void clean() {
        if(Park.getInstance().pickMoney(cleanPrice))
            this.cleanLevel = clean;
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
}