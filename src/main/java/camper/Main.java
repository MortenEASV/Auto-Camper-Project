package camper;

import camper.model.AutoCamper;
import camper.model.DateInterval;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    public static ArrayList<AutoCamper> cacheAutoCampers;
    public static HashMap<Integer, ArrayList<DateInterval>> cacheReservations = new HashMap<>();

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=DB_WagnerAutocampers;user=sa;password=cokanovic";

    @Override
    public void start(Stage stage) throws IOException {
        downloadCache();

        Parent root = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));

        stage.setTitle("Auto Camper Rental");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void downloadCache() {
        String sqlReservations = "SELECT * FROM reservations";
        String sqlAutoCampers = "SELECT * FROM autoCampers";
        cacheAutoCampers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlReservations);

            while (rs.next()) {
                int id = rs.getInt("fld_AutoCamperID");

                if (cacheReservations.containsKey(id)) {
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

                cacheAutoCampers.add(new AutoCamper(id, price, seats, sleeps, wc, kitchen, width, height, length, transmission, fuelType, cacheReservations.get(id)));
                System.out.println(cacheAutoCampers.get(cacheAutoCampers.size() - 1));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
