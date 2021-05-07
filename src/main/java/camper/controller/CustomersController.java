package camper.controller;

import camper.Main;
import camper.model.AutoCamper;
import camper.model.Customer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.function.Predicate;

public class CustomersController {

    @FXML
    TableView<Customer> viewTable;

    @FXML
    TableColumn<AutoCamper, Integer> colID;

    @FXML
    TableColumn<AutoCamper, String> colFirstName, colLastName, colPhoneNo, colCountryName, colCityPostalCode, colCityName, colStreet, colAptNumber, colFloor;

    @FXML
    TextField fldCustomerFirstName, fldCustomerLastName, fldCustomerID, fldCustomerAddress, fldCustomerPhoneNo;
    @FXML
    Button btnCancel, btnApply, btnDeleteCustomer;
    private DialogPane paneDeleteCustomer;



    public void initialize() throws IOException {
        paneDeleteCustomer = FXMLLoader.load(getClass().getResource("../fxml/DeleteCustomer.fxml"));

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        colCountryName.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        colCityPostalCode.setCellValueFactory(new PropertyValueFactory<>("cityPostalCode"));
        colCityName.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        colStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        colAptNumber.setCellValueFactory(new PropertyValueFactory<>("aptNumber"));
        colFloor.setCellValueFactory(new PropertyValueFactory<>("floor"));


        btnCancel = (Button) paneDeleteCustomer.lookupButton(ButtonType.CANCEL);
        btnApply = (Button) paneDeleteCustomer.lookupButton(ButtonType.APPLY);
        Stage popup = new Stage();
        popup.setTitle("Delete Customer");
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setScene(new Scene(paneDeleteCustomer));

        btnDeleteCustomer.setOnAction(event -> popup.show());

        btnCancel.setOnAction(event -> popup.close());
        btnApply.setOnAction(event -> {
            TextField text = (TextField)paneDeleteCustomer.getContent();
            deleteCustomer(Integer.parseInt(text.getText()));
        });





        initializeFilters();
    }

    public void deleteCustomer(int ID) {
        String deleteProcedure = "{call sp_deleteCustomer(?)}";
        try (Connection conn = DriverManager.getConnection(Main.URL); CallableStatement stmt = conn.prepareCall(deleteProcedure)) {
            stmt.setInt("ID",ID);
            stmt.execute();
            for (Customer c:Main.cacheCustomers) {
                System.out.println(c.getId());
            }
            System.out.println(ID);
            System.out.println(Main.cacheCustomers.removeIf(customer -> customer.getId() == ID));

            initializeFilters();



        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    public void initializeFilters(){
        FilteredList<Customer> filteredItems = new FilteredList<>(FXCollections.observableList(Main.cacheCustomers));
        ObjectProperty<Predicate<Customer>> filterFirstName = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Customer>> filterLastName = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Customer>> filterID = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Customer>> filterAddress = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Customer>> filterPhoneNo = new SimpleObjectProperty<>();



        filterFirstName.bind(Bindings.createObjectBinding(() -> Customer
                -> Customer.getFirstName().toLowerCase().contains(fldCustomerFirstName.getText().toLowerCase())));

        filterLastName.bind(Bindings.createObjectBinding(() -> Customer
                -> Customer.getLastName().toLowerCase().contains(fldCustomerLastName.getText().toLowerCase())));

        filterID.bind(Bindings.createObjectBinding(() -> Customer
                -> Customer.getIdToString().toLowerCase().contains(fldCustomerID.getText().toLowerCase())));

        filterAddress.bind(Bindings.createObjectBinding(() -> Customer
                -> Customer.getAddress().toLowerCase().contains(fldCustomerAddress.getText().toLowerCase())));

        filterPhoneNo.bind(Bindings.createObjectBinding(() -> Customer
                -> Customer.getPhoneNo().toLowerCase().contains(fldCustomerPhoneNo.getText().toLowerCase())));


        filteredItems.predicateProperty().bind(Bindings.createObjectBinding(() ->
                filterFirstName.get()
                .and(filterLastName.get()
                .and(filterID.get()
                .and(filterAddress.get()
                .and(filterPhoneNo.get())))),
                fldCustomerFirstName.textProperty(),
                fldCustomerLastName.textProperty(),
                fldCustomerID.textProperty(),
                fldCustomerAddress.textProperty(),
                fldCustomerPhoneNo.textProperty(),Main.cacheCustomers));

        viewTable.setItems(filteredItems);

    }

}
