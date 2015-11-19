package Model;

import java.util.ArrayList;

/**
 * 
 */
public class Shop extends Model {

    /**
     * Attraction list
     */
    private ArrayList<Attraction> attractionList;


    /**
     * Shop instance (singleton)
     */
    private static Shop INSTANCE;


    /**
     * Default shop constructor
     */
    private Shop() {

    } // Shop()


    /**
     * Function getInstance()
     * Singleton
     * @return INSTANCE
     */
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


    @Override
    public String toString() {
        return "Shop{" +
                "attractionList=" + attractionList +
                '}';
    }
}