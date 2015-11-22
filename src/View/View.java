package View;

import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Abstract Class View
 * Extend your view classes with this class to respect MVC architecture
 * If you put code here, it will be available for all view classes
 */
public abstract class View implements java.util.Observer {

    /**
     * View constructor
     */
    public View() {
    } //View()


    public abstract void update(Observable obs, Object obj); //update()


    /**
     * Function addController
     * Link the controller associated to your view
     *
     * @param controller controller to link
     */
    public abstract void addController(ActionListener controller);//addController()


} //View
