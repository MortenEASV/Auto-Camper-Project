package camper.controller;

import camper.Manager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class ReservationsController implements Controller {
    private static final Manager manager = Manager.getInstance();

    @FXML
    BorderPane root;

    @FXML
    Button btnNewReservation, btnDeleteReservation;

    @FXML
    public void initialize() {
        btnNewReservation.setUserData(Manager.FXML.NEW_RESERVATION);
}

    @FXML
    public void handleButtons(Event event) {
        Button btn = (Button) event.getSource();

        root.setCenter(manager.getRoot((Manager.FXML) btn.getUserData()));
    }
}
