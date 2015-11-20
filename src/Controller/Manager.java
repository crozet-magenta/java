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
    public void executeAction() {
        // TODO implement here
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