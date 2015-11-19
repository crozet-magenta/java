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

        System.out.println(APP_NAME + " - V" + VERSION + "\n\n");

        Park park = Park.getInstance();

        System.out.print("Nom du parc : ");

        Scanner sc = new Scanner(System.in);

        park.setName(sc.nextLine());



        // MVC
        // Model : accès View
        // View  : accès Controller
        // Controller : accès Model / View


        /**
         * Models
         **/

        // Attractions models
        Aquatic aquatic = new Aquatic();
        Carousel carousel = new Carousel();
        Child child = new Child();
        DropTower dropTower = new DropTower();
        RollerCoaster rollerCoaster = new RollerCoaster();


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



        /**
         * Views
         **/
        App app = new App();



        // Links models to controller
        manager.addModel(aquatic);
        manager.addModel(carousel);
        manager.addModel(child);
        manager.addModel(dropTower);
        manager.addModel(rollerCoaster);
        manager.addModel(shop);
        manager.addModel(bank);
        manager.addModel(area);



        // Link view to controller
        manager.addView(app);


        /**
         * Put code Here
         */

        System.out.println(park.toString());
        System.out.println(area.toString());
        System.out.println(bank.toString());
        System.out.println(shop.toString());



    } //main()

} //Main