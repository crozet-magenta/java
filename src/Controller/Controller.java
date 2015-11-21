package Controller;

import Model.Model;
import View.*;

/**
 * Abstract Class Controller
 * Extend your controller classes with this class to respect MVC architecture
 * If you put code here, it will be available for all controller classes
 */
public abstract class Controller implements java.awt.event.ActionListener {

	Model model;
	App view = new App();

    /**
     * Controller constructor
     */
	public Controller() {} //Controller()


	public abstract void actionPerformed(java.awt.event.ActionEvent e); //actionPerformed()


    /**
     * Function addModel
     * Link the model associated to your controller
     * @param m model to link
     */
	public void addModel(Model m){
		this.model = m;
	} //addModel()

} // Controller
