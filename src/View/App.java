package View;

import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Observable;

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
}
