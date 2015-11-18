package Controller;

import Model.Model;
import View.View;

import java.awt.event.ActionEvent;
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
     * 
     */
    private static ArrayList<String> actionsBoss;

    /**
     * 
     */
    private int mode;

    /**
     * 
     */
    private static ArrayList<String> actionVisitor;

    /**
     * 
     */
    public void executeAction() {
        // TODO implement here
    }

    /**
     * 
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