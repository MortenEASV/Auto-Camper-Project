package camper;

import camper.model.AutoCamper;
import camper.model.Customer;
import camper.model.DateInterval;
import camper.model.Reservation;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    public static ArrayList<AutoCamper> cacheAutoCampers;
    public static ArrayList<Customer> cacheCustomers;
    public static HashMap<Integer, ArrayList<DateInterval>> cacheReservations = new HashMap<>();
    public static ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    public static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=DB_WagnerAutocampers;user=sa;password=kristensen";

    @Override
    public void start(Stage stage) throws IOException {
        downloadCache();

        Parent root = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));

        stage.setTitle("Auto Camper Rental");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void downloadCache() {
        String sqlCustomers = "SELECT * FROM [Customers]";
        String sqlReservations = "SELECT * FROM [Reservations]";
        String sqlAutoCampers = "SELECT * FROM [Autocampers]";
        cacheAutoCampers = new ArrayList<>();
        cacheCustomers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlReservations);

            while (rs.next()) {

                int id = rs.getInt("fld_AutoCamperID");
                LocalDate from = (rs.getDate("fld_DateFrom").toLocalDate());
                LocalDate to = (rs.getDate("fld_DateUntil").toLocalDate());
                int customerID = rs.getInt("fld_CustomerId");
                int reservationID = rs.getInt("fld_ReservationID");
                String firstName = rs.getString("fld_FirstName");
                String lastName = rs.getString("fld_LastName");
                String fullName = firstName + " " + lastName;


                Reservation reservation = new Reservation(from, to, customerID, reservationID, fullName);
                reservations.add(reservation);
                if (!cacheReservations.containsKey(id)) {
                    cacheReservations.put(id, new ArrayList<>());
                }

                cacheReservations.get(id).add(new DateInterval(rs.getDate("fld_DateFrom").toLocalDate(), rs.getDate("fld_DateUntil").toLocalDate()));
            }

            rs = stmt.executeQuery(sqlAutoCampers);

            while (rs.next()) {
                int id = rs.getInt("fld_AutoCamperID");
                String price = rs.getString("fld_PriceType");
                int seats = rs.getInt("fld_Seats");
                int sleeps = rs.getInt("fld_Sleeps");
                boolean wc = rs.getBoolean("fld_WC");
                boolean kitchen = rs.getBoolean("fld_Kitchen");
                double width = rs.getDouble("fld_Width");
                double height = rs.getDouble("fld_Height");
                double length = rs.getDouble("fld_Length");
                String transmission = rs.getString("fld_TransmissionType");
                String fuelType = rs.getString("fld_FuelType");

                if (cacheReservations.containsKey(id)) {
                    cacheAutoCampers.add(new AutoCamper(id, price, seats, sleeps, wc, kitchen, width, height, length, transmission, fuelType, cacheReservations.get(id)));
                }
            }

            //Customers
            rs = stmt.executeQuery(sqlCustomers);
            while (rs.next()) {
                int id = rs.getInt("fld_CustomerID");
                String firstName = rs.getString("fld_FirstName");
                String lastName = rs.getString("fld_LastName");
                String phoneNo = rs.getString("fld_PhoneNo");
                String countryName = rs.getString("fld_CountryName");
                String cityPostalCode = rs.getString("fld_CityPostalCode");
                String cityName = rs.getString("fld_CityName");
                String street = rs.getString("fld_Street");
                String aptNumber = rs.getString("fld_AptNumber");
                String floor = rs.getString("fld_Floor");

                cacheCustomers.add(new Customer(id, firstName, lastName, phoneNo, countryName, cityPostalCode, cityName, street, aptNumber, floor));

            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
