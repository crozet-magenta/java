package View;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

/**
 * Created by jonathan on 18/11/15.
 */
public class App extends View {

    public App() {
        super();
    }

    @Override
    public void update(Observable obs, Object obj) {

    }

    @Override
    public void addController(ActionListener controller) {

    }

    public <T> void debug(T data) {
        System.out.print("[DEBUG]");
        System.out.println(data);
    }

    public String prompt(String text) {
        Scanner sc = new Scanner(System.in);
        System.out.print(text + " : ");
        return sc.nextLine();
    }

    public void print(String text) {
        System.out.println(text);
    }

    public String showMenu(String title, ArrayList<String> data, String[] valid) {
        String action;
        boolean err = false;
        do {
            try {
                Runtime.getRuntime().exec("clear");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (err) {
                this.print("_______________");
                this.print("Action invalide");
            }
            this.print(title);
            this.print(new String(new char[title.length()]).replace("\0", "="));
            for (String item: data) {
                this.print(item);
            }
            action = prompt("Action à effectuer ");
            err = true;
        } while (!Arrays.asList(valid).contains(action));

        return action;
    }
}
