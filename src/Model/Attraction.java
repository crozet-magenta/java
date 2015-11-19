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


    @Override
    public boolean is_clean() {
        return false;
    }

    @Override
    public void clean() {

    }
}