package View;

import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Created by jonathan on 12/11/15.
 */
public class Boss extends PersonView {


    public Boss() {
        // Trouver comment récupérer le nom et l'age proprement
        System.out.println("Boss View : Je suis " + Model.Boss.getInstance().getName() + " Boss !!!");
        System.out.println("J'ai " + Model.Boss.getInstance().getAge() + " ans.");
    }



    @Override
    public void update(Observable obs, Object obj) {

    }

    @Override
    public void addController(ActionListener controller) {
        System.out.println("Adding Boss Controller");
    }
}
