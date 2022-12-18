package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/view.fxml"));

        primaryStage.setTitle("غذاء الفلاسفة");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/Table.png")));
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }
    public void stop() throws Exception {
        Controller.RUNNING = false;
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
