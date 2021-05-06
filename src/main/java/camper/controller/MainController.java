package camper.controller;

import camper.Manager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController implements Controller {
    private static final Manager manager = Manager.getInstance();

    @FXML
    BorderPane root;

    @FXML
    Button btnAutoCampers, btnCustomers, btnRentals, btnReports, btnReservations;

    @FXML
    public void initialize() {
        btnAutoCampers.setUserData(Manager.FXML.AUTO_CAMPERS);
        btnCustomers.setUserData(Manager.FXML.CUSTOMERS);
        btnRentals.setUserData(Manager.FXML.RENTALS);
        btnReports.setUserData(Manager.FXML.REPORTS);
        btnReservations.setUserData(Manager.FXML.RESERVATIONS);
    }

    @FXML
    public void handleButtons(Event event) {
        Button btn = (Button) event.getSource();

        root.setCenter(manager.getRoot((Manager.FXML) btn.getUserData()));
    }
}