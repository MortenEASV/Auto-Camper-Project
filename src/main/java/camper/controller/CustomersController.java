package camper.controller;

import camper.Main;
import camper.model.AutoCamper;
import camper.model.Customer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.soap.Text;
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





    public void initialize(){

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

        initializeFilters();
    }
    public void initializeFilters(){
        FilteredList<Customer> filteredItems = new FilteredList<>(FXCollections.observableList(Main.cacheCustomers));
        ObjectProperty<Predicate<Customer>> filterFirstName = new SimpleObjectProperty<>();


        filterFirstName.bind(Bindings.createObjectBinding(() -> Customer
                -> Customer.getFirstName().contains(fldCustomerFirstName.getText())));


        filteredItems.predicateProperty().bind(Bindings.createObjectBinding(() -> filterFirstName.get(),fldCustomerFirstName.textProperty()));


        //viewTable.getItems().add(new Customer(1, "John", "Doesen", "2142069","Denmark","6200","Aabenraa","Nygade 1","null","null"));


        viewTable.setItems(filteredItems);

        System.out.println(viewTable.getItems().toString());

    }

}
