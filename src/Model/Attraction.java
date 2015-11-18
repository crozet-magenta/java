package Model;

import Interfaces.ICleanable;
import Interfaces.IOpenable;

/**
 * 
 */
public abstract class Attraction extends Model implements IOpenable, ICleanable {

    /**
     *
     */
    private String name;

    /**
     *
     */
    private int minAge;

    /**
     *
     */
    private double price;

    /**
     *
     */
    private int maxAge;



    /**
     * Default constructor
     */
    public Attraction() {
    }





}