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
     * Default constructor
     */
    public Attraction() {
    }


    @Override
    public void open() {

    }

    @Override
    public void close() {

    }


    public int getCleanLevel() {
        return cleanLevel;
    }


    @Override
    public boolean is_clean() {

        if(cleanLevel > 50)
            return true;

        return false;
    }

    @Override
    public void clean() {
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