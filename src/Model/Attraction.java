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
     * Maximum age required to do the attraction
     */
    protected int maxAge = 120;

    /**
     * price of the attraction (Boss mode : buy attraction)
     */
    protected double price = 0;



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

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }


    @Override
    public boolean is_clean() {
        return false;
    }

    @Override
    public void clean() {

    }

    @Override
    public String toString() {
        return "Attraction{" +
                "name='" + name + '\'' +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", price=" + price +
                '}';
    }
}