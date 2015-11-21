package Controller;

import Model.*;
import View.View;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.awt.event.ActionEvent;
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
     * 
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
                    m = modelClass.getClass().getMethod(action, double.class);
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

    @Override
    public void addView(View v) {
        super.addView(v);
    }
}