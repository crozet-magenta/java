package Controller;

import Model.*;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 */
public class Manager extends Controller {
    public static final double VERSION = 0.3;
    public static final String APP_NAME = "Theme Park";


    /**
     * Default constructor
     */
    public Manager() {
    }

    /**
     * Boss action list
     */
    private static ArrayList<String> actionsBoss;

    /**
     * game mode (Boss | Visitor)
     */
    private int mode;

    /**
     * Visitor action list
     */
    private static ArrayList<String> actionVisitor;

    /**
     * @deprecated
     */
    public <T> Object executeAction(String query, T params) {
        String model  = query.split("[.]")[0];
        String action = query.split("[.]")[1];
        Model modelClass;

        switch (model) {
            case "Shop":
                modelClass = Shop.getInstance();
                break;
            case "Bank":
                modelClass = Bank.getInstance();
                break;
            case "Park":
                modelClass = Park.getInstance();
                break;
            default:
                throw new Error("Classe inconnue");
        }

        try {
            Method m;
            if (params == null) {
                m = modelClass.getClass().getMethod(action);
                return m.invoke(modelClass);
            } else {
                if (params.getClass() == java.lang.Double.class) {
                    m = modelClass.getClass().getMethod(action, double.class);
                } else if (params.getClass() == java.lang.Integer.class) {
                    m = modelClass.getClass().getMethod(action, int.class);
                } else {
                    m = modelClass.getClass().getMethod(action, params.getClass());
                }
                return m.invoke(modelClass, params);
            }
        } catch (NoSuchMethodException e) {
            System.out.print("[DEBUG]");
            System.out.println(query);
            System.out.print("[DEBUG]");
            System.out.println(params.getClass());
            throw new Error("Action inconnue");
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new Object();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void addModel(Model m) {
        super.addModel(m);
    }

    private void showMainMenu() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1: Accéder au magasin");
        strings.add("2: Accéder à la banque");
        strings.add("3: Accéder à la gestion du parc '" + Park.getInstance().getName() + "'");
//        strings.add("4: Visiter le parc '" + Park.getInstance().getName() + "'");
        strings.add("Q: Quitter le programme");
        String[] valid = {"1", "2", "3", /*"4",*/ "Q", "q"};
        String action = this.view.showMenu("Main menu", strings, valid);
        switch (action) {
            case "1":
                this.showShopMenu();
                break;
            case "2":
                this.showBankMenu();
                break;
            case "3":
                this.showParkMenu();
                break;
            case "Q":
            case "q":
                this.quitProgram();
                break;
        }
    }


    private void showBankMenu() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1: Voir l'argent disponible");
        strings.add("2: Effectuer un emprunt");
        strings.add("3: Voir le statut de l'emprunt");
        strings.add("4: Rembourser l'emprunt");
        strings.add("R: Retour");
        String[] valid = {"1", "2", "3", "4", "R", "r"};
        String action = this.view.showMenu("Banque des parcs", strings, valid);
        this.view.separator();
        switch (action) {
            case "1":
                this.view.print("Argent disponible : " + Park.getInstance().getMoney());
                this.view.waitEnter();
                break;
            case "2":
                double amount = Double.parseDouble(this.view.prompt("Somme d'argent à emprunter (max " + Bank.getInstance().getMaxAmount() + "€)"));
                String result = Bank.getInstance().takeLoan(amount);
                if ("OK".equals(result)) {
                    this.view.print("Argent disponible : " + Park.getInstance().getMoney());
                } else {
                    this.view.print(result);
                }
                this.view.waitEnter();
                break;
            case "3":
                this.view.print(Bank.getInstance().getLoanStatus());
                this.view.waitEnter();
                break;
            case "4":
                this.view.print(Bank.getInstance().payLoan());
                this.view.waitEnter();
                break;
            case "R":
            case "r":
                this.showMainMenu();
                break;
        }
        showBankMenu();
    }

    private void showShopMenu() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1: Acheter attraction");
        strings.add("2: Vendre attraction");
        strings.add("3: Acheter zone");
        strings.add("R: Retour");
        String[] valid = {"1", "2", "3", "R", "r"};
        String action = this.view.showMenu("Magasin du parc (argent restant : " + Park.getInstance().getMoney() + "€)", strings, valid);
        this.view.separator();
        switch (action) {
            case "1":
                this.showShopBuyAttractionMenu();
                break;
            case "2":
                this.showShopSellAttractionMenu();
                break;
            case "3":
                this.showShopAreaMenu();
                break;
            case "R":
            case "r":
                this.showMainMenu();
                break;
        }
    }

    private void showShopAreaMenu() {
        ArrayList<String> strings = Shop.getInstance().areaList();
        strings.add("R: Retour");
        String[] valid = {"1", "2", "3", "4", "5", "R", "r"};
        String action = this.view.showMenu("Acheter des zones (argent restant : " + Park.getInstance().getMoney() + "€)", strings, valid);
        this.view.separator();
        switch (action) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                String result = Shop.getInstance().buyArea(Integer.parseInt(action)-1);
                if ("OK".equals(result)) {
                    this.view.print("Argent disponible : " + Park.getInstance().getMoney());
                } else {
                    this.view.print(result);
                }
                this.view.waitEnter();
                break;
            case "R":
            case "r":
                this.showShopMenu();
                break;
        }
        this.showShopAreaMenu();
    }

    private void showShopSellAttractionMenu() {
        ArrayList<String> strings = Park.getInstance().stockList();
        String[] range = this.generateRange(1, strings.size());

        strings.add("R: Retour");
        String[] prev = {"R", "r"};

        String[] valid = Stream.concat(Arrays.stream(range), Arrays.stream(prev)).toArray(String[]::new);
        String action = this.view.showMenu("Vendre des attractions (argent restant : " + Park.getInstance().getMoney() + "€)", strings, valid);
        this.view.separator();
        switch (action) {
            case "R":
            case "r":
                this.showShopMenu();
                break;
            default:
               if (Shop.getInstance().sell(Integer.parseInt(action)-1) != 0) {
                   this.view.print("Vendu");
               }
        }
        this.showShopSellAttractionMenu();
    }

    private String[] generateRange(int start, int stop) {
        String[] result = new String[(stop - start)+1];
        int j = 0;
        for (int i = start; i <= stop; i++) {
            result[j] = Integer.toString(i);
            j++;
        }
        return result;
    }

    private void showShopBuyAttractionMenu() {
        ArrayList<String> strings = Shop.getInstance().attractionList();
        String[] range = this.generateRange(1, strings.size());

        strings.add("R: Retour");
        String[] prev = {"R", "r"};

        String[] valid = Stream.concat(Arrays.stream(range), Arrays.stream(prev)).toArray(String[]::new);
        String action = this.view.showMenu("Acheter des attractions (argent restant : " + Park.getInstance().getMoney() + "€)", strings, valid);
        this.view.separator();
        switch (action) {
            case "R":
            case "r":
                this.showShopMenu();
                break;
            default:
                this.view.print(Shop.getInstance().buyAttraction(Integer.parseInt(action)-1));
                this.view.waitEnter();
        }
        this.showShopBuyAttractionMenu();
    }

    private void showParkMenu() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1: Ouvrir/fermer le parc " + (Park.getInstance().is_open()?"(ouvert)":"(fermé)"));
        strings.add("2: Changer le prix d'entrée (" + Park.getInstance().getPrice() + "€)");
        strings.add("3: Lister les zones disponibles");
        strings.add("4: Lister les attractions installées");
        strings.add("5: Lister les attractions en stock");
        strings.add("6: Installer des attractions");
        strings.add("7: Désinstaller des attractions");
        strings.add("R: Retour");
        String[] valid = {"1", "2", "3", "4", "5", "6", "7", "R", "r"};
        String action = this.view.showMenu("Gestion du parc " + Park.getInstance().getName(), strings, valid);
        this.view.separator();
        switch (action) {
            case "1":
                if (Park.getInstance().is_open()){
                    Park.getInstance().close();
                    this.view.print("Parc fermé");
                } else {
                    Park.getInstance().open();
                    this.view.print("Parc ouvert");
                }
                this.view.waitEnter();
                break;
            case "2":
                if (Park.getInstance().is_open()){
                    this.view.print("Le parc doit être fermé pour effectuer cette action");
                } else {

                    Park.getInstance().setPrice(Double.parseDouble(this.view.prompt("Nouveau prix")));
                    this.view.print("Effectué");
                }
                this.view.waitEnter();
                break;
            case "3":
                this.view.print(Park.getInstance().listAreas());
                this.view.waitEnter();
                break;
            case "4":
                this.view.print(Park.getInstance().listAttractions(true));
                this.view.waitEnter();
                break;
            case "5":
                this.view.print(Park.getInstance().listAttractions(false));
                this.view.waitEnter();
                break;
            case "6":
                showParkInstallAttractionMenu();
                break;
            case "7":
                showParkUninstallAttractionMenu();
                break;
            case "R":
            case "r":
                this.showMainMenu();
                break;
        }
        showParkMenu();
    }

    private void showParkInstallAttractionMenu() {
        ArrayList<String> strings = Park.getInstance().stockList();
        String[] range = this.generateRange(1, strings.size());

        strings.add("R: Retour");
        String[] prev = {"R", "r"};

        String[] valid = Stream.concat(Arrays.stream(range), Arrays.stream(prev)).toArray(String[]::new);
        String action = this.view.showMenu("Installer une attraction", strings, valid);
        this.view.separator();
        switch (action) {
            case "R":
            case "r":
                this.showParkMenu();
                break;
            default:
                Attraction attraction = Park.getInstance().getStock().get(Integer.parseInt(action)-1);
                Area area = Park.getInstance().findFreeAreaByType(attraction.getType());
                if (area == null) {
                    this.view.print("Pas de zone disponible !");
                } else {
                    this.view.print(area.install(attraction));
                }
                this.view.waitEnter();
        }
        this.showParkInstallAttractionMenu();
    }

    private void showParkUninstallAttractionMenu() {
        ArrayList<String> strings = Park.getInstance().InstalledList();
        String[] range = this.generateRange(1, strings.size());

        strings.add("R: Retour");
        String[] prev = {"R", "r"};

        String[] valid = Stream.concat(Arrays.stream(range), Arrays.stream(prev)).toArray(String[]::new);
        String action = this.view.showMenu("Désinstaller une attraction", strings, valid);
        this.view.separator();
        switch (action) {
            case "R":
            case "r":
                this.showParkMenu();
                break;
            default:
                Attraction attraction = Park.getInstance().getInstalledAttractions().get(Integer.parseInt(action)-1);
                Area area = Park.getInstance().findAreaContainsAttraction(attraction);
                if (area == null) {
                    this.view.print("Attraction non trouvée");
                } else {
                    this.view.print(area.uninstall(attraction));
                }
                this.view.waitEnter();
        }
        this.showParkUninstallAttractionMenu();
    }



    private void quitProgram() {
        this.view.print("Merci d'avoir joué !");
        System.exit(0);
    }

    public void init() {
        this.view.debug(APP_NAME + " - V" + VERSION);
        Park.getInstance().setName(this.view.prompt("Nom du parc"));
        this.showMainMenu();
    }
}