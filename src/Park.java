import Model.Boss;
import Model.PersonModel;
import View.PersonView;

import java.awt.event.ActionListener;
import java.util.Observable;

public class Park {

	private static Park INSTANCE;


	public static Park getInstance() {
        if(INSTANCE == null)
            INSTANCE = new Park();

		return INSTANCE;
	}


	private Park() {

        //create Model and View
        Boss bossModel = Boss.getInstance();
        View.Boss bossView 	= new View.Boss();


        //tell Model about View.
        bossModel.addObserver(bossView);


        //create Controller. tell it about Model and View, initialise model
        Controller.Boss bossController = new Controller.Boss();
        bossController.addModel(bossModel);
        bossController.addView(bossView);


        //tell View about Controller
        bossView.addController(bossController);



	} // Park()


	/*public Park() {

		//create Model.Model and View.View
		Model myModel 	= new Model();
		View myView 	= new View();

		//tell Model.Model about View.View.
		myModel.addObserver(myView);
		*//*
			init model after view is instantiated and can show the status of the model
			(I later decided that only the controller should talk to the model
			and moved initialisation to the controller (see below).)
		*//*
		//uncomment to directly initialise Model.Model
		//myModel.setValue(start_value);	

		//create Controller.Controller. tell it about Model.Model and View.View, initialise model
		Controller myController = new Controller();
		myController.addModel(myModel);
		myController.addView(myView);
		myController.initModel(start_value);

		//tell View.View about Controller.Controller
		myView.addController(myController);
		//and Model.Model,
		//this was only needed when the view inits the model
		//myView.addModel(myModel);

	} //RunMVC()*/

} //RunMVC
