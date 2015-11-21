package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
     * Define all types of attractions
     */
    private Map<Integer, String> AttractionTypes = new HashMap<Integer, String>();

    /**
     * Area type (Aquatic, Carousel, Child, DropTower, RollerCoaster)
     * Default : Aquatic
     */
    private int type = AQUATIC;

    /**
     * Number of maximum attraction in the same area
     */
    private final int capacity = 4;

    /**
     *  Space available in the area to install new attractions
     */
    private int freeSpace = capacity;

    /**
     * Space size used by one attraction
     */
    private final int attractionSize = 1;

    /**
     * Attraction list : contain installed attractions
     */
    public ArrayList<Attraction> Attractions = new ArrayList();


    /**
     * Default constructor
     */
    public Area() {
        initAttractionTypes();
    }

    /**
     * Constructor with type
     * @param type int
     */
    public Area(int type) {
        initAttractionTypes();
        this.type = type;
    }


    /**
     * Initialize AttractionTypes
     */
    private void initAttractionTypes() {
        AttractionTypes.put(AQUATIC, "Aquatic");
        AttractionTypes.put(CAROUSEL, "Carousel");
        AttractionTypes.put(CHILD, "Child");
        AttractionTypes.put(DROP_TOWER, "DropTower");
        AttractionTypes.put(ROLLER_COASTER, "RollerCoaster");
    }



    /**
     * Check if there is enough free space to install new attraction
     * @return boolean
     */
    public boolean has_space() {
        return freeSpace >= attractionSize;
    }


    /**
     * Function is_type()
     * Check if attraction has the same type of area
     *
     * @param attraction
     * @return boolean
     */
    private boolean is_type(Attraction attraction) {

        switch (this.type) {
            // Aquatic
            case AQUATIC:
                if(attraction instanceof Aquatic) return true;
                break;

            // Carousel
            case CAROUSEL:
                if(attraction instanceof Carousel) return true;
                break;

            // Child
            case CHILD:
                if(attraction instanceof Child) return true;
                break;

            // DropTower
            case DROP_TOWER:
                if(attraction instanceof DropTower) return true;
                break;

            // RollerCoaster
            case ROLLER_COASTER:
                if(attraction instanceof RollerCoaster) return true;
                break;

            default:
                break;
        }

        return false;
    }


    /**
     * Install an attraction in area
     * @param attraction
     * @return String
     */
    public String install(Attraction attraction) {

        if(!has_space())
            return "Vous n'avez pas assez de place pour installer une nouvelle attraction !";

        if(!is_type(attraction))
            return "Votre attraction n'est pas du type : " + AttractionTypes.get(type);

        if(Attractions.contains(attraction))
            return "Vous ne pouvez pas installer plusieurs fois la même attraction !";


        if(!Park.getInstance().is_in_stock(attraction)) {
            return "Vous n'avez pas acheté cette attraction ! (NotFound in stock)";
        }

        if(Attractions.add(attraction)) {
            this.freeSpace -= attractionSize;
            Park.getInstance().removeFromStock(attraction);
            return "Attraction installée :)";
        }


        return null;
    }


    /**
     * Uninstall an attraction in area
     * @param attraction
     * @return String
     */
    public String uninstall(Attraction attraction) {


        if(attraction.is_open())
            attraction.close();

        if(!Attractions.contains(attraction))
            return "Impossible de supprimer une attraction qui n'est pas installée !";


        if(Attractions.remove(attraction)) {
            this.freeSpace += attractionSize;
            Park.getInstance().appendToStock(attraction);
            return "Attraction désinstallée :)";
        }


        return null;
    }



    /**
     * Calculates the currently area clean level
     * @return int
     */
    public int calculateAreaCleanLevel() {

        // Don't need to calculate if no attractions
        if(Attractions.isEmpty())
            return 100;


        int sumCleanLevel = 0;

        // for each area
        for (Attraction attraction : Attractions) {

            // get attraction clean level
            sumCleanLevel += attraction.getCleanLevel();
        }


        return sumCleanLevel / Attractions.size();
    }


    /**
     * Cleaning all attractions which cleanLevel <= 50
     */
    public void cleanAttractions() {

        // for each attraction
        for (Attraction attraction : Attractions) {
            // cleaning attraction
            if(!attraction.is_clean())
                attraction.clean();
        }
    }


    /**
     * Function showAttractions()
     * show installed attractions
     */
    public void showAttractions() {
        // for each attraction
        for (Attraction attraction : Attractions) {
            // show attraction
            System.out.println(attraction.toString());
        }
    }


    /**
     * Open attractions in current area
     */
    public void openAttractions() {
        for (Attraction attraction : Attractions) {
            attraction.open();
        }
    }


    /**
     * Close attractions in current area
     */
    public void closeAttractions() {
        for (Attraction attraction : Attractions) {
            attraction.close();
        }
    }


    /**
     * Return area type (Aquatic, Carousel, ...)
     * @return int
     */
    public int getType() {
        return type;
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