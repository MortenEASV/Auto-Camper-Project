package camper;

import camper.controller.Controller;
import camper.controller.CustomersController;
import camper.controller.NewReservationController;
import camper.controller.ReservationsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Manager {
    private static Manager instance;

    static {
        try {
            instance = new Manager();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private final BorderPane rootCustomers, rootNewReservation, rootReservations;

    private final CustomersController controllerCustomers;
    private final NewReservationController controllerNewReservation;
    private final ReservationsController controllerReservations;

    private Manager() throws IOException {
        FXMLLoader loaderCustomers = new FXMLLoader(getClass().getResource("fxml/Customers.fxml"));
        rootCustomers = loaderCustomers.load();
        controllerCustomers = loaderCustomers.getController();

        FXMLLoader loaderNewReservation = new FXMLLoader(getClass().getResource("fxml/NewReservation.fxml"));
        rootNewReservation = loaderNewReservation.load();
        controllerNewReservation = loaderNewReservation.getController();

        FXMLLoader loaderReservations = new FXMLLoader(getClass().getResource("fxml/Reservations.fxml"));
        rootReservations = loaderReservations.load();
        controllerReservations = loaderReservations.getController();
    }

    public BorderPane getRoot(FXML fxml) {
        switch (fxml) {
            case CUSTOMERS:
                System.out.println("Yep");

                return rootCustomers;
            case NEW_RESERVATION:
                System.out.println("Test");

                return rootNewReservation;
            case RESERVATIONS:
                System.out.println("AAH");

                return rootReservations;
            default:
                System.out.println("?");

                return null;
        }
    }

    public Controller getController(FXML fxml) {
        switch (fxml) {
            case CUSTOMERS:
                return controllerCustomers;
            case NEW_RESERVATION:
                return controllerNewReservation;
            case RESERVATIONS:
                return controllerReservations;
            default:
                return null;
        }
    }

    public static Manager getInstance() {
        return instance;
    }

    public enum FXML {
        AUTO_CAMPERS, CUSTOMERS, NEW_RESERVATION, RENTALS, REPORTS, RESERVATIONS;
    }
}
