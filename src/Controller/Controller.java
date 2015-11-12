package Controller;

import Model.Model;
import View.View;

public abstract class Controller implements java.awt.event.ActionListener {

	Model model;
	View view;

	public Controller() {} //Controller()


	public abstract void actionPerformed(java.awt.event.ActionEvent e); //actionPerformed()


	public void addModel(Model m){
		this.model = m;
	} //addModel()

	public void addView(View v){
		this.view = v;
	} //addView()

} // Controller
