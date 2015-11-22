package Model;

import java.util.ArrayList;

/**
 *
 */
public class Shop extends Model {

    /**
     * Shop instance (singleton)
     */
    private static Shop INSTANCE;
    private final double sellPrice = 0.5;
    /**
     * Attraction list : contain available attractions
     */
    private ArrayList<Attraction> attractionList = new ArrayList<>();
    /**
     * AreaList list : contain available areas
     */
    private ArrayList<Area> areaList = new ArrayList<>();


    /**
     * Default shop constructor
     */
    private Shop() {
        /*
         * ADD SOME ATTRACTIONS AND AREAS
         */
        this.attractionList.add(new Carousel("Manège pour enfants", 3, 12, 1500));
        this.attractionList.add(new Carousel("Manège pour adultes", 18, 99, 2500));
        this.attractionList.add(new Carousel("Manège voitures", 7, 18, 2000));
        this.attractionList.add(new Aquatic("Bateau a moteur", 12, 99, 2500));
        this.attractionList.add(new Aquatic("Bateau a voile", 18, 99, 2500));
        this.attractionList.add(new Aquatic("Ballade en mer", 3, 12, 2500));

        this.areaList.add(new Area(Area.AQUATIC));
        this.areaList.add(new Area(Area.CAROUSEL));
        this.areaList.add(new Area(Area.CHILD));
        this.areaList.add(new Area(Area.DROP_TOWER));
        this.areaList.add(new Area(Area.ROLLER_COASTER));
    } // Shop()


    /**
     * Function getInstance()
     * Singleton
     *
     * @return INSTANCE
     */
    public static Shop getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Shop();

        return INSTANCE;
    }


    /**
     * Return list of attractions
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> attractionList() {
        ArrayList<String> data = new ArrayList<>();
        int i = 1;
        for (Attraction item : this.attractionList) {
            data.add("" + i + ": " + item.getName() + " - " + item.getClass().getSimpleName() + "(" + item.price + "€)");
            i += 1;
        }
        return data;
    }


    /**
     * Return list of areas
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> areaList() {
        ArrayList<String> data = new ArrayList<>();
        int i = 1;
        for (Area item : this.areaList) {
            data.add("" + i + ": " + item.getTypeAsString() + "(" + item.price + "€)");
            i += 1;
        }
        return data;
    }


    /**
     * Buy an attraction
     *
     * @param id int id of the attraction in the list
     * @return String
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
     * Buy an area
     *
     * @param id int id of the attraction in the list
     * @return String
     */
    public String buyArea(int id) {
        Area area = this.areaList.get(id);
        String message;
        Park park = Park.getInstance();

        if (park.pickMoney(area.price)) {
            if (park.appendToAreas(area)) {
                message = "OK";
            } else {
                message = "Plus de place :'(";
            }
        } else {
            message = "Pas assez d'argent :(";
        }
        return message;
    }


    /**
     * Sell an attraction
     *
     * @param id int id of the attraction in the stock list
     * @return double
     */
    public double sell(int id) {
        Attraction attraction = Park.getInstance().findAttractionById(id);

        if (attraction == null)
            return 0;

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

    public double getSellPrice() {
        return sellPrice;
    }
}