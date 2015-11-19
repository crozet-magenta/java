package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class Area extends Model {

    /**
     * Define all types of attractions
     */
    private Map<String, String> AttractionTypes = new HashMap<String, String>();

    /**
     * Area type (Aquatic, Carousel, Child, DropTower, RollerCoaster)
     */
    private int type = 0;

    /**
     * Number of maximum attraction in the same area
     */
    private final int Capacity = 4;

    /**
     *  Space available in the area to install new attractions
     */
    private int freeSpace = Capacity;

    /**
     * Space size used by one attraction
     */
    private final int attractionSize = 1;

    /**
     * Attraction list
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
        AttractionTypes.put("0", "Aquatic");
        AttractionTypes.put("1", "Carousel");
        AttractionTypes.put("2", "Child");
        AttractionTypes.put("3", "DropTower");
        AttractionTypes.put("4", "RollerCoaster");
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
            case 0:
                if(attraction instanceof Aquatic) return true;
                break;

            // Carousel
            case 1:
                if(attraction instanceof Carousel) return true;
                break;

            // Child
            case 2:
                if(attraction instanceof Child) return true;
                break;

            // DropTower
            case 3:
                if(attraction instanceof DropTower) return true;
                break;

            // RollerCoaster
            case 4:
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
            return 0;


        int sumCleanLevel = 0;

        // for each area
        for (Attraction attraction : Attractions) {

            // get attraction clean level
            sumCleanLevel += attraction.getCleanLevel();
        }


        return sumCleanLevel / Attractions.size();
    }


    /**
     * Cleaning all attractions
     */
    public void cleanAttractions() {

        // for each attraction
        for (Attraction attraction : Attractions) {
            // cleaning attraction
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
                ", Capacity=" + Capacity +
                ", Attractions=" + Attractions +
                '}';
    }
}