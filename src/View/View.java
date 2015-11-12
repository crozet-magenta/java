package View;

import java.util.Observable;
import java.awt.event.ActionListener;


public abstract class View implements java.util.Observer {


	
	public View() {} //View()


	public abstract void update(Observable obs, Object obj); //update()


	public abstract void addController(ActionListener controller);//addController()



} //View
