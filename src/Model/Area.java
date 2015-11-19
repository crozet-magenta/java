package Model;

import java.util.ArrayList;

/**
 * 
 */
public class Area extends Model {

    /**
     * Area type (Aquatic, Carousel, Child, DropTower, RollerCoaster)
     */
    protected int type;

    /**
     * Number of maximum attraction in the same area
     */
    protected final int Capacity = 4;

    /**
     * Attraction list
     */
    private ArrayList<Attraction> Attractions;


    /**
     * Default constructor
     */
    public Area() {
    }


    /**
     * 
     */
    public void install() {
        // TODO implement here
    }

    /**
     * 
     */
    public void uninstall() {
        // TODO implement here
    }


    @Override
    public String toString() {
        return "Area{" +
                "type=" + type +
                ", Capacity=" + Capacity +
                ", Attractions=" + Attractions +
                '}';
    }
}