package Model;

import java.util.ArrayList;

/**
 * 
 */
public class Area extends Model {

    public static final int AQUATIC        = 1;
    public static final int CAROUSEL       = 2;
    public static final int CHILD          = 3;
    public static final int DROP_TOWER     = 4;
    public static final int ROLLER_COASTER = 5;

    public int price = 5000;

    /**
     * Area type (Aquatic, Carousel, Child, DropTower, RollerCoaster)
     */
    protected int type;

    /**
     * Number of maximum attraction in the same area
     */
    protected final int capacity = 4;

    /**
     * Attraction list
     */
    private ArrayList<Attraction> Attractions;


    /**
     * Default constructor
     */
    public Area() {
    }

    public Area(int type) {
        this.type  = type;
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
                ", price=" + price +
                ", Capacity=" + capacity +
                ", Attractions=" + Attractions +
                '}';
    }
}