package camper.controller;

import camper.model.AutoCamper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class NewReservationController {
    @FXML
    TableView<AutoCamper> viewTable;

    @FXML
    TableColumn<AutoCamper, Integer> colID, colSeats, colSleeps;

    @FXML
    TableColumn<AutoCamper, Double> colSize, colPrice;

    @FXML
    TableColumn<AutoCamper, Boolean> colKitchen, colWC;

    @FXML
    TableColumn<AutoCamper, String> colFuelType, colTransmission;

    @FXML
    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colSeats.setCellValueFactory(new PropertyValueFactory<>("seats"));
        colSleeps.setCellValueFactory(new PropertyValueFactory<>("sleeps"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colKitchen.setCellValueFactory(new PropertyValueFactory<>("kitchen"));
        colWC.setCellValueFactory(new PropertyValueFactory<>("wc"));
        colFuelType.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        colTransmission.setCellValueFactory(new PropertyValueFactory<>("transmission"));
    }
}
