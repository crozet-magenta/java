package Model;

import Interfaces.ICleanable;
import Interfaces.IOpenable;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class Park extends Model implements IOpenable, ICleanable {

    /**
     * Name of the Park
     */
    private String name = "Theme-Park";

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
     * Ticket price to visit the park
     */
    private double price = 0;

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
    public void setPrice(double price) {
        this.price = price;
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
     *@return message
     */
    @Override
    public String clean() {
        if (status == true)
            return "Vous devez fermer le parc pour procéder au nettoyage !!!";

        // for each area
        for (Area area : areas) {
            // clean area
            area.cleanAttractions();
        }

        this.cleanLevel = clean;

        return "Parc nettoyé :)";
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
     * @return message
     */
    public String addArea(Area area) {

        if(!has_space())
            return "Vous n'avez pas assez de place pour ajouter une nouvelle zone !";

        if(this.areas.contains(area))
            return "Vous ne pouvez pas ajouter plusieurs fois la même zone !";

        this.freeSpace -= areaSize;
        this.areas.add(area);

        return "Zone ajoutée :)";
    }


    /**
     * Remove an area in the park
     * @param area
     * @return message
     */
    public String removeArea(Area area) {

        if(!areas.contains(area))
            return "Vous ne pouvez pas supprimer une zone qui n'existe pas !";

        if(!area.Attractions.isEmpty())
            return "Vous ne pouvez pas supprimer une zone qui n'est pas vide !";

        this.freeSpace += areaSize;
        areas.remove(area);

        return "Zone supprimée :)";
    }


    /**
     * Open the park and attractions
     */
    @Override
    public void open() {
        openAllAttractions();
        this.status = true;
    }


    /**
     * Close the park and attractions
     */
    @Override
    public void close() {
        closeAllAttractions();
        this.status = false;
    }


    @Override
    public String toString() {
        return "Park{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", Free space=" + freeSpace +
                ", price=" + price +
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
    /**
     * Open all installed attractions
     */
    public void openAllAttractions() {

        if(areas.isEmpty())
            return;

        for(Area area : areas) {

            area.openAttractions();

        }
    }


    /**
     * Close all installed attractions
     */
    public void closeAllAttractions() {

        if(areas.isEmpty())
            return;

        for(Area area : areas) {

            area.closeAttractions();

        }
    }


    /**
     * Search by name attraction in stock
     * @param name
     * @return Attraction || null
     */
    public Attraction findAttractionByName(String name) {

        if(stock.isEmpty())
            return null;

        // for each attraction
        for (Attraction a : stock) {

            if(a.getName().equals(name))
                return a;

        }

        return null;
    }



    /**
     * Search by id attraction in stock
     * @param id
     * @return Attraction || null
     */
    public Attraction findAttractionById(int id) {

        if(stock.isEmpty())
            return null;

        if(stock.get(id) != null)
            return stock.get(id);

        return null;
    }


    /**
     * Search by type list of Area in areas
     * @param type
     * @return ArrayList<Area> || null
     */
    public ArrayList<Area> findAreasByType(int type) {

        if(areas.isEmpty())
            return null;


        ArrayList<Area> list = new ArrayList();

        for(Area a : areas) {

            if (a.getType() == type)
                list.add(a);
        }


        if(list.isEmpty())
            return null;


        return list;
    }


    /**
     * Search first area of Type which have freeSpace
     * @param type
     * @return Area || null
     */
    public Area findFreeAreaByType(int type) {

        // Get list areas of Type
        ArrayList<Area> list = findAreasByType(type);

        // if no results
        if(list == null)
            return null;

        // find first with enough space
        for(Area a : list) {

            if(a.has_space())
                return a;

        }


        return null;

    }




    public boolean is_in_stock(Attraction attraction) {
        if(stock.contains(attraction))
            return true;

        return false;
    }


    public ArrayList<String> stockList() {
        ArrayList<String> data = new ArrayList<>();
        int i = 1;
        for (Attraction item : this.stock) {
            data.add("" + i + ": " + item.getName() + " - " + item.getClass().getSimpleName() + " (Valeur : "+ item.price*Shop.getInstance().getSellPrice() +"€)");
            i+=1;
        }
        return data;
    }

    public ArrayList<String> InstalledList() {
        ArrayList<String> data = new ArrayList<>();
        int i = 1;
        for (Attraction item : this.getInstalledAttractions()) {
            data.add("" + i + ": " + item.getName() + " - " + item.getClass().getSimpleName() + " (Valeur : "+ item.price*Shop.getInstance().getSellPrice() +"€)");
            i+=1;
        }
        return data;
    }

    public double getPrice() {
        return price;
    }

    public String listAreas() {
        String data = "Zones du parc :\n";
        for (Area item : this.areas) {
            data += "- Zone de type "+item.getTypeAsString()+" "+item.getfreeSpace()+" emplacement(s) libre(s)\n";
        }
        return data;
    }

    public String listAttractions(boolean installed) {
        ArrayList<Attraction> data = installed?this.getInstalledAttractions():this.stock;

        String list = "Attractions :\n";
        for (Attraction item : data) {
            list += "- "+item.getName() + " - " + item.getClass().getSimpleName()+"\n";
        }
        return list;
    }

    public ArrayList<Attraction> getInstalledAttractions() {
        ArrayList<Attraction> list = new ArrayList<>();
        for (Area item : this.areas) {
            list.addAll(item.getAttractions());
        }
        return list;
    }

    public Area findAreaContainsAttraction(Attraction attraction) {
        if(areas.isEmpty()) return null;
        for(Area a : areas) {
            if (a.getAttractions().contains(attraction)) return a;
        }
        return null;
    }
}
