package camper.controller;

import camper.Main;
import camper.model.AutoCamper;
import camper.model.DateInterval;
import camper.model.Reservation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ReservationsController {
    @FXML
    BorderPane root;

    @FXML
    Button btnAllReservations, btnNewReservation, btnDeleteReservation;

    @FXML
    private Pane paneReservations;
    private BorderPane paneNewReservation;
    private DialogPane paneDeleteReservation;

    Button btnCancel;
    Button btnApply;

    @FXML
    public void initialize() throws IOException {
        paneReservations = (Pane) root.getCenter();
        paneNewReservation = FXMLLoader.load(getClass().getResource("../fxml/NewReservation.fxml"));
        paneDeleteReservation = FXMLLoader.load(getClass().getResource("../fxml/DeleteReservation.fxml"));

        btnCancel = (Button) paneDeleteReservation.lookupButton(ButtonType.CANCEL);
        btnApply = (Button) paneDeleteReservation.lookupButton(ButtonType.APPLY);
        Stage popup = new Stage();
        popup.setTitle("Delete Reservation");
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setScene(new Scene(paneDeleteReservation));

        btnCancel.setOnAction(event -> popup.close());
        btnApply.setOnAction(event -> deleteReservation(Integer.parseInt(paneDeleteReservation.getContentText())));

        btnAllReservations.setOnAction(e -> root.setCenter(paneReservations));
        btnNewReservation.setOnAction(e -> root.setCenter(paneNewReservation));
        btnDeleteReservation.setOnAction(e -> popup.show());
    }
    public void deleteReservation(int ID){
        String deleteProcedure = "EXECUTE sp_deleteReservation @ID = "+ ID;
        try (Connection conn = DriverManager.getConnection(Main.URL); Statement stmt = conn.createStatement()) {
            stmt.execute(deleteProcedure);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
