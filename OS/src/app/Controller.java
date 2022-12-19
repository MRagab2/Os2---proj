package app;

import app.Model.Fork;
import app.Model.Philosopher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static boolean RUNNING;

    @FXML
    private TextArea logTextArea;

    //Philosophers
    @FXML
    private ImageView el_Far;
    private Image[] el_FarImg = new Image[3];
    @FXML
    private ImageView ragabola;
    private Image[] ragabolaImg = new Image[3];
    @FXML
    private ImageView hobz;
    private Image[] hobzImg = new Image[3];
    @FXML
    private ImageView ali;
    private Image[] aliImg = new Image[3];
    @FXML
    private ImageView caren;
    private Image[] carenImg = new Image[3];

    //Forks
    @FXML
    private ImageView fork1;
    @FXML
    private ImageView fork2;
    @FXML
    private ImageView fork3;
    @FXML
    private ImageView fork4;
    @FXML
    private ImageView fork5;

    //Generate our philosophers and forks
    private Philosopher[] philosophers = new Philosopher[5];
    private Fork[] forks = new Fork[5];

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        el_FarImg[0] = new Image(getClass().getResourceAsStream("img/eyad-think.png"));
        el_FarImg[1] = new Image(getClass().getResourceAsStream("img/eyad-hungry.png"));
        el_FarImg[2] = new Image(getClass().getResourceAsStream("img/eyad-eat.png"));

        ragabolaImg[0] = new Image(getClass().getResourceAsStream("img/ragab-think.png"));
        ragabolaImg[1] = new Image(getClass().getResourceAsStream("img/ragab-hungry.png"));
        ragabolaImg[2] = new Image(getClass().getResourceAsStream("img/ragab-eat.png"));

        hobzImg[0] = new Image(getClass().getResourceAsStream("img/ehab-think.png"));
        hobzImg[1] = new Image(getClass().getResourceAsStream("img/ehab-hungry.png"));
        hobzImg[2] = new Image(getClass().getResourceAsStream("img/ehab-eat.png"));

        aliImg[0] = new Image(getClass().getResourceAsStream("img/ali-think.png"));
        aliImg[1] = new Image(getClass().getResourceAsStream("img/ali-hungry.png"));
        aliImg[2] = new Image(getClass().getResourceAsStream("img/ali-eat.png"));

        carenImg[0] = new Image(getClass().getResourceAsStream("img/caren-think.png"));
        carenImg[1] = new Image(getClass().getResourceAsStream("img/caren-hungry.png"));
        carenImg[2] = new Image(getClass().getResourceAsStream("img/caren-eat.png"));

        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork(i, logTextArea);
        }
        assignForkToItsView();

        //Create philosophers
        philosophers[0] = new Philosopher(forks[0], forks[1], 0, "el-Far", el_Far, el_FarImg[0], el_FarImg[1], el_FarImg[2], logTextArea);
        philosophers[1] = new Philosopher(forks[1], forks[2], 1, "Ragabola", ragabola, ragabolaImg[0], ragabolaImg[1], ragabolaImg[2], logTextArea);
        philosophers[2] = new Philosopher(forks[2], forks[3], 2, "Hobz", hobz, hobzImg[0], hobzImg[1], hobzImg[2], logTextArea);
        philosophers[3] = new Philosopher(forks[3], forks[4], 3, "Ali", ali, aliImg[0], aliImg[1], aliImg[2], logTextArea);
        philosophers[4] = new Philosopher(forks[4], forks[0], 4, "Caren", caren, carenImg[0], carenImg[1], carenImg[2], logTextArea);
    }

    public void startAction(ActionEvent actionEvent) {
        RUNNING = true;

        for (Philosopher p : philosophers) {
            new Thread(p).start();
        }
    }

    public void stopAction(ActionEvent actionEvent) {
        System.out.println("\nStopped Simulation");
        logTextArea.appendText("\nStopped Simulation, wait for each philosopher to finish \n");
        RUNNING = false;
    }

    private void assignForkToItsView() {
        forks[0].setForkView(fork5);
        forks[1].setForkView(fork1);
        forks[2].setForkView(fork2);
        forks[3].setForkView(fork3);
        forks[4].setForkView(fork4);
    }

}
