package Model;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 */
public class Shop extends Model {

    /**
     * Attraction list : contain available attractions
     */
    private ArrayList<Attraction> attractionList = new ArrayList<>();

    /**
     * AreaList list : contain available areas
     */
    private ArrayList<Area> areaList = new ArrayList<>();


    /**
     * Shop instance (singleton)
     */
    private static Shop INSTANCE;

    private final double sellPrice = 0.5;


    /**
     * Default shop constructor
     */
    private Shop() {
        /**
         * ADD SOME ATTRACTIONS AND AREAS
         */
        this.attractionList.add(new Carousel("Manège pour enfants", 3, 12, 1500));
        this.attractionList.add(new Carousel("Manège pour adultes", 18, 99, 2500));
        this.attractionList.add(new Carousel("Manège voitures", 7, 18, 2000));

        this.areaList.add(new Area(Area.AQUATIC));
        this.areaList.add(new Area(Area.CAROUSEL));
        this.areaList.add(new Area(Area.CHILD));
        this.areaList.add(new Area(Area.DROP_TOWER));
        this.areaList.add(new Area(Area.ROLLER_COASTER));
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

    public ArrayList<String> attractionList() {
        return this.attractionList.stream().map(Object::toString).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<String> areaList() {
        return this.areaList.stream().map(Object::toString).collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * 
     */
    public String buyAttraction(int id) {
        Attraction attraction = this.attractionList.get(id);
        String message;
        Park park = Park.getInstance();

        if (park.pickMoney(attraction.price)) {
            message = "Acheté :)";
            park.appendToStock(attraction);
        } else {
            message = "Pas assez d'argent :(";
        }
        return message;
    }

    /**
     *
     */
    public String buyArea(int id) {
        Area area = this.areaList.get(id);
        String message;
        Park park = Park.getInstance();

        if (park.pickMoney(area.price)) {
            if (park.appendToAreas(area)) {
                message = "Acheté :)";
            }else {
                message = "Plus de place :'(";
            }
        } else {
            message = "Pas assez d'argent :(";
        }
        return message;
    }

    /**
     * 
     */
    public double sell(int id) {
        Attraction attraction = Park.getInstance().getStock().get(id);
        double value = attraction.price * this.sellPrice;
        Park.getInstance().removeFromStock(attraction);
        Park.getInstance().addMoney(value);
        return value;
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