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

    /**
     * Park size
     * Default : 100 Hectare
     */
    private static final int size = 100;

    /**
     * Ticket prices to visit the park
     */
    private double prices;

    /**
     * Area list
     */
    private ArrayList<Area> Areas;

    /**
     * Attraction list
     * Contain list of uninstalled attractions
     */
    private ArrayList<Attraction> stock;

    /**
     * Park money
     */
    private double money;

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
                ", Areas=" + Areas +
                ", stock=" + stock +
                ", money=" + money +
                '}';
    }
} //RunMVC
