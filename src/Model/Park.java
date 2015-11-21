package Model;

import Interfaces.ICleanable;
import Interfaces.IOpenable;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class Park extends Model implements IOpenable, ICleanable {

    /**
     * Name of the Park
     */
    private String name;

    /**
     * Park status (0 : closed | 1 : open )
     */
    private boolean status = false;

    /**
     * Start day
     */
    private int day = 0;

    /**
     * Park size
     * Default : 100 Hectare
     */
    private final int size = 100;

    /**
     * Space available on the park to add new areas
     */
    private int freeSpace = size;


    /**
     * Space size used by one area
     */
    private final int areaSize = 10;

    /**
     * Ticket prices to visit the park
     */
    private double prices = 0;

    /**
     * Area list
     */
    private ArrayList<Area> areas = new ArrayList();

    /**
     * Attraction list
     * Contain list of uninstalled bought attractions
     */
    private ArrayList<Attraction> stock = new ArrayList();

    /**
     * Park money
     */
    private double money = 4000;


    /**
     * Park maximum clean;
     */
    private final int clean = 100;


    /**
     * Current clean level of the park
     */
    private int cleanLevel = 100;

    /**
     * Park instance (Singleton)
     */
	private static Park INSTANCE;


    /**
     * Default park constructor
     */
	private Park() {

	} // Park()


    /**
     * Function getInstance()
     * Singleton
     * @return INSTANCE
     */
    public static Park getInstance() {
        if(INSTANCE == null)
            INSTANCE = new Park();

        return INSTANCE;
    }

    /**
     * Return park size
     * @return int
     */
    public int getSize() {
        return size;
    }


    /**
     * Define the name of your park
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Function setPrices()
     * Define the new price for the ticket
     */
    public void setPrices(double prices) {
        this.prices = prices;
    }


    /**
     * Calculates the currently park clean level
     * @return int
     */
    public int calculateParkCleanLevel() {

        // park is clean if no areas
        if (areas.isEmpty())
            return clean;

        int sumGlobalCleanLevel = 0;

        // for each area
        for (Area area : areas) {

            // get global clean level
           sumGlobalCleanLevel += area.calculateAreaCleanLevel();

        }

        return sumGlobalCleanLevel / areas.size();
    }


    /**
     * Recalculate the new value of park cleanLevel
     */
    public void updateCleanLevel() {
        cleanLevel = calculateParkCleanLevel();
    }


    /**
     * Check if park is clean (cleanLevel >= 50)
     *
     * @return boolean
     */
    @Override
    public boolean is_clean() {

        updateCleanLevel();

        if(cleanLevel >= 50)
            return true;


        return false;
    }


    /**
     * Function clean()
     * Try to clean all the park
     * @throws Exception
     */
    @Override
    public void clean() throws Exception {
        if (status == true)
            throw new Exception("Vous devez fermer le parc pour procéder au nettoyage !!!");

        // for each area
        for (Area area : areas) {
            // clean area
            area.cleanAttractions();
        }

        this.cleanLevel = clean;
    }


    /**
     * Check if there is enough free space to add new area
     * @return boolean
     */
    private boolean has_space() {
        return freeSpace > areaSize;
    }


    /**
     * Add an area in the park
     * @param area
     */
    public void addArea(Area area) throws Exception {

        if(!has_space())
            throw new Exception("Vous n'avez pas assez de place pour ajouter une nouvelle zone !");

        if(this.areas.contains(area))
            throw new Exception("Vous ne pouvez pas ajouter plusieurs fois la même zone !");

        this.freeSpace -= areaSize;
        this.areas.add(area);
    }


    /**
     * Remove an area in the park
     * @param area
     */
    public void removeArea(Area area) throws Exception {

        if(!areas.contains(area))
            throw new Exception("Vous ne pouvez pas supprimer une zone qui n'existe pas !");

        this.freeSpace += areaSize;
        areas.remove(area);
    }


    /**
     * Open the park
     */
    @Override
    public void open() {
        this.status = true;
    }


    /**
     * Close the park
     */
    @Override
    public void close() {
        this.status = false;
    }


    @Override
    public String toString() {
        return "Park{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", Free space=" + freeSpace +
                ", prices=" + prices +
                ", Areas=" + areas +
                ", stock=" + stock +
                ", money=" + money +
                ", cleanLevel='" + cleanLevel + '\'' +
                '}';
    }


    /**
     * Return uninstalled attractions
     * @return ArrayList<Attraction>
     */
    public ArrayList<Attraction> getStock() {
        return stock;
    }


    /**
     * Return list of Areas added in the park
     * @return ArrayList<Area>
     */
    public ArrayList<Area> getAreas() {
        return areas;
    }


    /**
     * Retire price in park money
     * Return true if success
     * @param price
     * @return boolean
     */
    public boolean pickMoney(double price) {
        if (this.money < price) {
            return false;
        }
        else {
            money -= price;
            return true;
        }
    }


    /**
     * Add uninstalled attraction to stock
     * @param attraction
     */
    public void appendToStock(Attraction attraction) {
        this.stock.add(attraction);
    }


    /**
     * Please use addArea()
     * @deprecated
     */
    public boolean appendToAreas(Area area) {

        // Gitan
        try {
            addArea(area);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*if (this.areas.size() >= Park.getInstance().getSize()) {
            return false;
        } else {
            this.areas.add(area);
            return true;
        }*/

        return false;

    }



    public void removeFromStock(Attraction attraction) {
        this.stock.remove(attraction);
    }


    /**
     * Add value to park money
     * @param value
     */
    public void addMoney(double value) {
        this.money += value;
    }


    /**
     * Get park money
     * @return double
     */
    public double getMoney() {
        return money;
    }

    /**
     * Return current day
     * @return int
     */
    public int getDay() {
        return day;
    }

    /**
     * Define current day
     * @param day
     */
    public void setDay(int day) {
        this.day = day;
    }


    /**
     * Show attractions in stock
     * @return ArrayList<String>
     */
    public ArrayList<String> showStock() {
        return this.stock.stream().map(Object::toString).collect(Collectors.toCollection(ArrayList::new));
    }


    public String getName() {
        return name;
    }

    public boolean is_open() {
        return status;
    }
}
