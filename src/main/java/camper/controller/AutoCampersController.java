package camper.controller;

import camper.Main;
import camper.model.AutoCamper;
import camper.model.DateInterval;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Predicate;

public class AutoCampersController {
    @FXML
    TableView<AutoCamper> viewTable;

    @FXML
    TableColumn<AutoCamper, Integer> colID, colSeats, colSleeps;

    @FXML
    TableColumn<AutoCamper, Double> colWidth, colHeight, colLength;

    @FXML
    TableColumn<AutoCamper, Boolean> colKitchen, colWC;

    @FXML
    TableColumn<AutoCamper, String> colFuelType, colTransmission, colPrice;

    @FXML
    ChoiceBox<String> choiceCategory, choiceTransmission, choiceFuelType;

    @FXML
    ChoiceBox<Integer> choiceSleeps, choiceSeats;

    @FXML
    ChoiceBox<Boolean> choiceWC, choiceKitchen;

    @FXML
    TextField fldMinWidth, fldMinHeight, fldMinLength;

    @FXML
    DatePicker dateFrom, dateTo;

    @FXML
    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSeats.setCellValueFactory(new PropertyValueFactory<>("seats"));
        colSleeps.setCellValueFactory(new PropertyValueFactory<>("sleeps"));
        colWC.setCellValueFactory(new PropertyValueFactory<>("wc"));
        colKitchen.setCellValueFactory(new PropertyValueFactory<>("kitchen"));
        colWidth.setCellValueFactory(new PropertyValueFactory<>("width"));
        colHeight.setCellValueFactory(new PropertyValueFactory<>("height"));
        colLength.setCellValueFactory(new PropertyValueFactory<>("length"));
        colFuelType.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        colTransmission.setCellValueFactory(new PropertyValueFactory<>("transmission"));

        initializeChoiceBoxes();
        initializeFilters();
    }

    private void initializeChoiceBoxes() {
        for (PriceCategory priceCategory : PriceCategory.values()) {
            choiceCategory.getItems().add(priceCategory.name);
        }

        for (int i = 1; i <= 10; i++) {
            choiceSeats.getItems().add(i);
            choiceSleeps.getItems().add(i);
        }

        choiceWC.getItems().addAll(false, true);
        choiceKitchen.getItems().addAll(false, true);

        for (Transmission transmission : Transmission.values()) {
            choiceTransmission.getItems().add(transmission.name);
        }

        for (FuelType fuelType : FuelType.values()) {
            choiceFuelType.getItems().add(fuelType.name);
        }

        choiceCategory.setValue(PriceCategory.BASIC.name);
        choiceSeats.setValue(choiceSeats.getItems().get(0));
        choiceSleeps.setValue(choiceSleeps.getItems().get(0));
        choiceWC.setValue(choiceWC.getItems().get(0));
        choiceKitchen.setValue(choiceKitchen.getItems().get(0));
        choiceTransmission.setValue(choiceTransmission.getItems().get(0));
        choiceFuelType.setValue(choiceFuelType.getItems().get(0));
    }

    private void initializeFilters() {
        ObjectProperty<Predicate<AutoCamper>> filterDate = new SimpleObjectProperty<>();
        filterDate.bind(Bindings.createObjectBinding(() -> autoCamper -> {
            ArrayList<DateInterval> dateIntervals = autoCamper.getReservedDates();
            boolean isReserved = false;

            for (DateInterval interval : dateIntervals) {
                if (!(dateTo.getValue().isBefore(interval.getFrom()) || dateFrom.getValue().isAfter(interval.getTo()))) {
                    isReserved = true;

                    break;
                }
            }

            return isReserved;
        }));

        ObjectProperty<Predicate<AutoCamper>> filterPriceCategory = new SimpleObjectProperty<>();
        filterPriceCategory.bind(Bindings.createObjectBinding(() -> autoCamper
                -> autoCamper.getPrice().equals(choiceCategory.getValue())));

        ObjectProperty<Predicate<AutoCamper>> filterSeats = new SimpleObjectProperty<>();
        filterSeats.bind(Bindings.createObjectBinding(() -> autoCamper
                -> autoCamper.getSeats() >= choiceSeats.getValue()));

        ObjectProperty<Predicate<AutoCamper>> filterSleeps = new SimpleObjectProperty<>();
        filterSleeps.bind(Bindings.createObjectBinding(() -> autoCamper
                -> autoCamper.getSleeps() >= choiceSleeps.getValue()));

        ObjectProperty<Predicate<AutoCamper>> filterWC = new SimpleObjectProperty<>();
        filterWC.bind(Bindings.createObjectBinding(() -> autoCamper
                -> autoCamper.isWc() == choiceWC.getValue()));

        ObjectProperty<Predicate<AutoCamper>> filterKitchen = new SimpleObjectProperty<>();
        filterKitchen.bind(Bindings.createObjectBinding(() -> autoCamper
                -> autoCamper.isKitchen() == choiceKitchen.getValue()));

        ObjectProperty<Predicate<AutoCamper>> filterWidth = new SimpleObjectProperty<>();
        filterWidth.bind(Bindings.createObjectBinding(() -> autoCamper
                -> autoCamper.getWidth() >= Double.parseDouble(fldMinWidth.getText())));

        ObjectProperty<Predicate<AutoCamper>> filterHeight = new SimpleObjectProperty<>();
        filterHeight.bind(Bindings.createObjectBinding(() -> autoCamper
                -> autoCamper.getHeight() >= Double.parseDouble(fldMinHeight.getText())));

        ObjectProperty<Predicate<AutoCamper>> filterLength = new SimpleObjectProperty<>();
        filterLength.bind(Bindings.createObjectBinding(() -> autoCamper
                -> autoCamper.getLength() >= Double.parseDouble(fldMinLength.getText())));

        ObjectProperty<Predicate<AutoCamper>> filterTransmission = new SimpleObjectProperty<>();
        filterTransmission.bind(Bindings.createObjectBinding(() -> autoCamper
                -> autoCamper.getTransmission().equals(choiceTransmission.getValue())));

        ObjectProperty<Predicate<AutoCamper>> filterFuelType = new SimpleObjectProperty<>();
        filterFuelType.bind(Bindings.createObjectBinding(() -> autoCamper
                -> autoCamper.getFuelType().equals(choiceFuelType.getValue())));

        FilteredList<AutoCamper> filteredItems = new FilteredList<>(FXCollections.observableList(Main.cacheAutoCampers));
        filteredItems.predicateProperty().bind(Bindings.createObjectBinding(() -> filterPriceCategory.get()
                        .and(filterSeats.get()
                                .and(filterSleeps.get()
                                        .and(filterWC.get()
                                                .and(filterKitchen.get()
                                                        .and(filterWidth.get()
                                                                .and(filterHeight.get()
                                                                        .and(filterLength.get()
                                                                                .and(filterTransmission.get()
                                                                                        .and(filterFuelType.get()))))))))),
                choiceCategory.valueProperty(),
                choiceSeats.valueProperty(),
                choiceSleeps.valueProperty(),
                choiceWC.valueProperty(),
                choiceKitchen.valueProperty(),
                fldMinWidth.textProperty(),
                fldMinHeight.textProperty(),
                fldMinLength.textProperty(),
                choiceTransmission.valueProperty(),
                choiceFuelType.valueProperty()));

        viewTable.setItems(filteredItems);
    }
}
