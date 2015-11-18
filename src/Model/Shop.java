package Model;

import java.util.ArrayList;

/**
 * 
 */
public class Shop extends Model {

    /**
     *
     */
    private ArrayList<Attraction> attractionList;


    /**
     *
     */
    private static Shop INSTANCE;


    /**
     * Default constructor
     */
    public Shop() {
    }


    public static Shop getInstance() {
        if(INSTANCE == null)
            INSTANCE = new Shop();

        return INSTANCE;
    }


    /**
     * 
     */
    public void buy() {
        // TODO implement here
    }

    /**
     * 
     */
    public void sell() {
        // TODO implement here
    }

    /**
     * 
     */
    public void sort() {
        // TODO implement here
    }

    /**
     * 
     */
    public void extendPark() {
        // TODO implement here
    }

}