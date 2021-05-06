package camper.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    @FXML
    BorderPane root;

    @FXML
    Button btnAutoCampers, btnCustomers, btnRentals, btnReports, btnReservations;

    private BorderPane paneAutoCampers, paneCustomers, paneRentals, paneReports, paneReservations;

    public MainController() throws IOException {
        paneAutoCampers = FXMLLoader.load(getClass().getResource("../fxml/AutoCampers.fxml"));
        paneCustomers = FXMLLoader.load(getClass().getResource("../fxml/Customers.fxml"));
        // paneRentals = FXMLLoader.load(getClass().getResource("fxml/Rentals.fxml"));
        // paneReports= FXMLLoader.load(getClass().getResource("fxml/Reports.fxml"));
        paneReservations= FXMLLoader.load(getClass().getResource("../fxml/Reservations.fxml"));
    }

    @FXML
    public void initialize() {
        btnAutoCampers.setOnAction(e -> root.setCenter(paneAutoCampers));
        btnCustomers.setOnAction(e -> root.setCenter(paneCustomers));
        // btnRentals.setOnAction(e -> root.setCenter(paneRentals));
        // btnReports.setOnAction(e -> root.setCenter(paneReports));
        btnReservations.setOnAction(e -> root.setCenter(paneReservations));
    }
}