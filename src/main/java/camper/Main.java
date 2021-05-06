package camper;

import camper.model.AutoCamper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    public static ArrayList<AutoCamper> cacheAutoCampers;

    @Override
    public void start(Stage stage) throws IOException {
        cacheAutoCampers = new ArrayList<>();
        cacheAutoCampers.add(new AutoCamper(1, "Basic", 5, 2, true, false, 10.0, 20.0, 100.0, "Manual", "Diesel"));
        cacheAutoCampers.add(new AutoCamper(1, "Basic", 7, 3, true, false, 10.0, 20.0, 100.0, "Manual", "Diesel"));

        Parent root = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));

        stage.setTitle("Auto Camper Rental");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
