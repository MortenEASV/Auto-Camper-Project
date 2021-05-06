package camper.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservationsController {
    @FXML
    BorderPane root;

    @FXML
    Button btnAllReservations, btnNewReservation, btnDeleteReservation;

    @FXML
    private Pane paneReservations;
    private BorderPane paneNewReservation;
    private DialogPane paneDeleteReservation;

    @FXML
    public void initialize() throws IOException {
        paneReservations = (Pane) root.getCenter();
        paneNewReservation = FXMLLoader.load(getClass().getResource("../fxml/NewReservation.fxml"));
        paneDeleteReservation = FXMLLoader.load(getClass().getResource("../fxml/DeleteReservation.fxml"));

        Stage popup = new Stage();
        popup.setScene(new Scene(paneDeleteReservation));

        btnAllReservations.setOnAction(e -> root.setCenter(paneReservations));
        btnNewReservation.setOnAction(e -> root.setCenter(paneNewReservation));
        btnDeleteReservation.setOnAction(e -> popup.show());
    }
}
