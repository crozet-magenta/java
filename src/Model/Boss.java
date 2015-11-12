package Model;

/**
 * Created by jonathan on 12/11/15.
 */
public class Boss extends PersonModel {

    private static Boss INSTANCE;


    public static Boss getInstance() {
        if(INSTANCE == null)
            INSTANCE = new Boss();

        return INSTANCE;
    }


    private Boss() {
        name = "Hugo";
        sex = true;
        age = 42;
        System.out.println("Boss Model");
    } // Boss()

}
