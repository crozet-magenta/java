// MVC based on : http://www.austintek.com/mvc/

import Controller.Manager;
import Model.*;
import View.App;

import java.util.Scanner;

/**
 * class Main
 *
 * @author jonathan, cassian
 * @version 0.3
 */
public class Main {

    public static final double VERSION = 0.3;

    public static final String APP_NAME = "Theme Park";

    public static void main(String[] args) throws Exception {

        /**
         * Views
         **/
        App app = new App();


        app.debug(APP_NAME + " - V" + VERSION + "\n\n");

        Park park = Park.getInstance();

        park.setName(app.prompt("Nom du parc"));



        // MVC
        // Model : accès View
        // View  : accès Controller
        // Controller : accès Model / View


        /**
         * Models
         **/

//        // Attractions models
//        Aquatic aquatic = new Aquatic();
//        Carousel carousel = new Carousel();
//        Child child = new Child();
//        DropTower dropTower = new DropTower();
//        RollerCoaster rollerCoaster = new RollerCoaster();


        // Shop model
        Shop shop = Shop.getInstance();

        // Bank model
        Bank bank = Bank.getInstance();

        // Area model
        Area area = new Area();



        /**
         * Controllers
         **/

        // Manager Controller
        Manager manager = new Manager();



        // Links models to controller
//        manager.addModel(aquatic);
//        manager.addModel(carousel);
//        manager.addModel(child);
//        manager.addModel(dropTower);
//        manager.addModel(rollerCoaster);
        manager.addModel(shop);
        manager.addModel(bank);
        manager.addModel(area);

        /**
         * Put code Here
         */

        Park.getInstance().setDay(1);
        app.debug(manager.executeAction("Park.getMoney", null));
        app.debug(manager.executeAction("Bank.takeLoan", (double)5000));
        app.debug(manager.executeAction("Park.getMoney", null));
        app.debug(manager.executeAction("Shop.buyAttraction", 1));

        Park.getInstance().setDay(10);
        app.debug(manager.executeAction("Park.getMoney", null));

        Park.getInstance().setDay(103);
        app.debug(manager.executeAction("Bank.getLoanStatus", null));

        app.debug(manager.executeAction("Shop.sell", 0));
        app.debug(manager.executeAction("Park.getMoney", null));

        app.debug(manager.executeAction("Bank.payLoan", null));
        app.debug(manager.executeAction("Bank.getLoanStatus", null));
        app.debug(manager.executeAction("Park.getMoney", null));

        manager.showMainMenu();
    } //main()

} //Main