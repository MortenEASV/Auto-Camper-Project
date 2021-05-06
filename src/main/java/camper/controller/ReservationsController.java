package camper.controller;

import camper.Main;
import camper.model.AutoCamper;
import camper.model.DateInterval;
import camper.model.Reservation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;


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
    TableView<Reservation> tableView = new TableView();

    @FXML
    TableColumn<Reservation, Integer> colReservationID;
    @FXML
    TableColumn<Reservation, Integer> colCustomerID;
    @FXML
    TableColumn<Reservation, String> colCustomerName;
    @FXML
    TableColumn<Reservation, LocalDate> colFrom;
    @FXML
    TableColumn<Reservation, LocalDate> colUntil;

    @FXML
    public void initialize() throws IOException {
        paneReservations = (Pane) root.getCenter();
        paneNewReservation = FXMLLoader.load(getClass().getResource("../fxml/NewReservation.fxml"));
        paneDeleteReservation = FXMLLoader.load(getClass().getResource("../fxml/DeleteReservation.fxml"));

        initializeReservationTable();

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

    public void initializeReservationTable() {
        colReservationID.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
        colUntil.setCellValueFactory(new PropertyValueFactory<>("to"));
        tableView.getColumns().addAll(colReservationID, colCustomerID, colCustomerName, colFrom, colUntil);
        tableView.setItems(Main.reservations);

        tableView.getItems().forEach(System.out::println);


    }

    public void deleteReservation(int ID) {
        String deleteProcedure = "EXECUTE sp_deleteReservation @ID = " + ID;
        try (Connection conn = DriverManager.getConnection(Main.URL); Statement stmt = conn.createStatement()) {
            stmt.execute(deleteProcedure);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
