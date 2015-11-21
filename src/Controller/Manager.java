package Controller;

import Model.*;
import View.View;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 
 */
public class Manager extends Controller {

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

    /**
     * switch to Boss mode or Visitor mode
     */
    public void changeMode() {
        // TODO implement here
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void addModel(Model m) {
        super.addModel(m);
    }

    public void showMainMenu() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1: Accéder au magasin");
        strings.add("2: Accéder à la banque");
        strings.add("3: Accéder à la gestion du parc '" + Park.getInstance().getName() + "'");
        strings.add("4: Visiter le parc '" + Park.getInstance().getName() + "'");
        strings.add("Q: Quitter le programme");
        String[] valid = {"1", "2", "3", "4", "Q", "q"};
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
            case "4":
                if (!Park.getInstance().is_open()) {
                    this.view.print("Le parc est fermé !");
                    this.view.waitEnter();
                    this.showMainMenu();
                } else {
                    this.showVisitorMenu();
                }
                break;
            case "Q":
            case "q":
                this.quitProgram();
                break;
        }
    }

    private void showVisitorMenu() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("R: Retour");
        String[] valid = {"R", "r"};
        String action = this.view.showMenu("TODO", strings, valid);
        switch (action) {
            case "R":
            case "r":
                this.showMainMenu();
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
                String result = (String) this.executeAction("Bank.takeLoan", amount);
                if ("OK".equals(result)) {
                    this.view.print("Argent disponible : " + Park.getInstance().getMoney());
                } else {
                    this.view.print(result);
                }
                this.view.waitEnter();
                break;
            case "3":
                this.view.print((String)this.executeAction("Bank.getLoanStatus", null));
                this.view.waitEnter();
                break;
            case "4":
                this.view.print((String)this.executeAction("Bank.payLoan", null));
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

    }

    private void showShopBuyAttractionMenu() {

    }

    private void showParkMenu() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1: Ouvrir le parc");
        strings.add("2: Voir les finances du parc");
        strings.add("3: Lister les attractions installées");
        strings.add("R: Retour");
        String[] valid = {"1", "2", "3", "R", "r"};
        String action = this.view.showMenu(Park.getInstance().getName(), strings, valid);
        switch (action) {
            case "1":
                //TODO
                break;
            case "2":
                //TODO
                break;
            case "3":
                //TODO
                break;
            case "R":
            case "r":
                this.showMainMenu();
                break;
        }
    }

    private void quitProgram() {
        this.view.print("Merci d'avoir joué !");
        System.exit(0);
    }
}