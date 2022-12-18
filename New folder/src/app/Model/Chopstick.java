package app.Model;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.util.concurrent.locks.ReentrantLock;


public class Chopstick extends ReentrantLock {

    private int id;
    private boolean taken;
    private ImageView chopstickView;
    private TextArea loggingConsole;

    public Chopstick(int id, TextArea loggingConsole) {
        this.id = id;
        this.loggingConsole = loggingConsole;
    }

    public void setChopstickView(ImageView chopstickView) {
        this.chopstickView = chopstickView;
    }


    boolean pick(Philosopher philosopher) {
        if (tryLock()) {
            taken = true;
            Platform.runLater(()-> {
                chopstickView.setVisible(false);
                loggingConsole.appendText("\n"+philosopher + " picked up chopstick #" + id + "\n");
            });
            System.out.println("\n"+philosopher + " picked up chopstick #" + id);
            return true;
        }
        return false;
    }

    boolean drop(Philosopher philosopher) {
        if (isHeldByCurrentThread()) {
            unlock();

            taken = false;
            Platform.runLater(()-> {
                chopstickView.setVisible(true);
                loggingConsole.appendText("\n"+philosopher + " dropped chopstick #" + id + "\n");
            });
            System.out.println("\n"+philosopher + " dropped chopstick #" + id);
            return true;
        }
        return false;
    }

}