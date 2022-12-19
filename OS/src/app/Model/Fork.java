package app.Model;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.util.concurrent.locks.ReentrantLock;


public class Fork extends ReentrantLock {

    private int id;
    private boolean taken;
    private ImageView forkView;
    private TextArea loggingConsole;

    public Fork(int id, TextArea loggingConsole) {
        this.id = id;
        this.loggingConsole = loggingConsole;
    }

    public void setForkView(ImageView forkView) {
        this.forkView = forkView;
    }


    boolean pick(Philosopher philosopher) {
        if (tryLock()) {
            taken = true;
            Platform.runLater(()-> {
                forkView.setVisible(false);
                loggingConsole.appendText("\n"+philosopher + " picked up fork #" + id + "\n");
            });
            System.out.println("\n"+philosopher + " picked up fork #" + id);
            return true;
        }
        return false;
    }

    boolean drop(Philosopher philosopher) {
        if (isHeldByCurrentThread()) {
            unlock();

            taken = false;
            Platform.runLater(()-> {
                forkView.setVisible(true);
                loggingConsole.appendText("\n"+philosopher + " dropped fork #" + id + "\n");
            });
            System.out.println("\n"+philosopher + " dropped fork #" + id);
            return true;
        }
        return false;
    }

}
