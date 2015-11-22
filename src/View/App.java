package View;

import java.awt.event.ActionListener;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Scanner;

/**
 * Created by jonathan on 18/11/15.
 */
public class App extends View {

    public App() {
        super();
    }

    /**
     * tool function to clean console.
     */
    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                for (int i = 0; i < 50; ++i) System.out.println();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable obs, Object obj) {

    }

    @Override
    public void addController(ActionListener controller) {

    }

    /**
     * print data for debug purpose
     * @param data data to print
     * @param <T> any type supported by System.out.println()
     */
    public <T> void debug(T data) {
        System.out.print("[DEBUG]");
        System.out.println(data);
    }

    /**
     * displays a text and prompts the user for an input
     * @param text text to display before prompt
     * @return text entered by the user
     */
    public String prompt(String text) {
        Scanner sc = new Scanner(System.in);
        System.out.print(text + " : ");
        return sc.nextLine();
    }

    /**
     * wrapper for System.out.println()
     * @param text text to display
     */
    public void print(String text) {
        System.out.println(text);
    }

    /**
     * display a menu with a title, options and a prompt. The function checks if the user input is valid
     * @param title Title of the menu
     * @param data Lines of the menu options
     * @param valid array of all valid inputs
     * @return the verified user input
     */
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
            for (String item : data) {
                this.print(item);
            }
            action = prompt("Action à effectuer ");
            err = true;
        } while (!Arrays.asList(valid).contains(action));

        return action;
    }

    /**
     * tool function to pause the program until the user press enter
     */
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

    /**
     * tool function to display a separator for better reading when a clear screen is not needed
     */
    public void separator() {
        print("___________________\n\n");
    }
}
