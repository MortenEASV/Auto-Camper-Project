package camper.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    @FXML
    BorderPane root;

    @FXML
    public void initialize() {
        try {
            root.setCenter(FXMLLoader.load(getClass().getResource("../fxml/Reservations.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}