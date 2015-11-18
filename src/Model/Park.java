package Model;

import java.util.ArrayList;

public class Park extends Model {

    /**
     *
     */
    private String name;

    /**
     *
     */
    private boolean status;

    /**
     *
     */
    private static int size;

    /**
     *
     */
    private double prices;

    /**
     *
     */
    private ArrayList<Area> Areas;

    /**
     *
     */
    private ArrayList<Attraction> stock;

    /**
     *
     */
    private double money;

    /**
     *
     */
	private static Park INSTANCE;


	public static Park getInstance() {
        if(INSTANCE == null)
            INSTANCE = new Park();

		return INSTANCE;
	}


	private Park() {



	} // Model.Park()


    public void setName(String name) {
        this.name = name;
    }
} //RunMVC
