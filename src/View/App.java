package View;

import java.awt.event.ActionListener;
import java.io.Console;
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

    public static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                for (int i = 0; i < 50; ++i) System.out.println();
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
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
            this.clearConsole();
            if (err) {
                this.print("=================");
                this.print(" Action invalide");
                this.print("=================");
                this.print("");
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

    public void waitEnter() {
        Console c = System.console();
        if (c != null) {
            c.format("\nPress ENTER to proceed.\n");
            c.readLine();
        } else {
            try {
                System.out.println("\n     Appuyer sur ENTER pour continuer...\n");
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void separator() {
        print("___________________\n\n");
    }
}
