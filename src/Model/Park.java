package Model;

import java.util.ArrayList;

public class Park extends Model {

    /**
     * Name of the Park
     */
    private String name;

    /**
     * Park status (open | closed)
     */
    private boolean status;

    private int dayNumber = 0;

    /**
     * Park size
     * Default : 10 Hectare
     */
    private static final int size = 10;

    /**
     * Ticket prices to visit the park
     */
    private double prices;

    /**
     * Area list
     */
    private ArrayList<Area> areas = new ArrayList<>();

    /**
     * Attraction list
     * Contain list of uninstalled attractions
     */
    private ArrayList<Attraction> stock = new ArrayList<>();

    /**
     * Park money
     */
    private double money = 500000;

    /**
     * Park instance (Singleton)
     */
	private static Park INSTANCE;
    private int day;


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


    @Override
    public String toString() {
        return "Park{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", prices=" + prices +
                ", Areas=" + areas +
                ", stock=" + stock +
                ", money=" + money +
                '}';
    }

    public ArrayList<Attraction> getStock() {
        return stock;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public boolean pickMoney(double price) {
        if (this.money < price) {
            return false;
        }
        else {
            money -= price;
            return true;
        }
    }

    public void appendToStock(Attraction attraction) {
        this.stock.add(attraction);
    }

    public boolean appendToAreas(Area area) {
        if (this.areas.size() >= Park.getInstance().getSize()) {
            return false;
        } else {
            this.areas.add(area);
            return true;
        }

    }

    public void removeFromStock(Attraction attraction) {
        this.stock.remove(attraction);
    }

    public void addMoney(double value) {
        this.money += value;
    }

    public double getMoney() {
        return money;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
