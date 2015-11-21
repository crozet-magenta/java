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
    private Map<String, String> AttractionTypes = new HashMap<String, String>();

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



    private void initAttractionTypes() {
        AttractionTypes.put("1", "Aquatic");
        AttractionTypes.put("2", "Carousel");
        AttractionTypes.put("3", "Child");
        AttractionTypes.put("4", "DropTower");
        AttractionTypes.put("5", "RollerCoaster");
    }

    public String getType() {
        return this.AttractionTypes.get(Integer.toString(this.type));
    }


    /**
     * Check if there is enough free space to install new attraction
     * @return boolean
     */
    private boolean has_space() {
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
     */
    public void install(Attraction attraction) throws Exception {

        if(!has_space())
            throw new Exception("Vous n'avez pas assez de place pour installer une nouvelle attraction !");

        if(!is_type(attraction))
            throw new Exception("Votre attraction n'est pas du type : " + AttractionTypes.get(Integer.toString(type)));

        if(Attractions.contains(attraction))
            throw new Exception("Vous ne pouvez pas installer plusieurs fois la même attraction !");

        this.freeSpace -= attractionSize;
        Attractions.add(attraction);
    }


    /**
     * Uninstall an attraction in area
     * @param attraction
     */
    public void uninstall(Attraction attraction) throws Exception {

        if(!Attractions.contains(attraction))
            throw new Exception("Impossible de supprimer une attraction qui n'est pas installée !");

        this.freeSpace += attractionSize;
        Attractions.remove(attraction);
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