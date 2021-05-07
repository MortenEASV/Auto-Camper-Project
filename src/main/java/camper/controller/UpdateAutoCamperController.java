package camper.controller;

import camper.Main;
import camper.model.AutoCamper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.sql.*;

public class UpdateAutoCamperController {
    @FXML
    Button btnApply, btnCancel;

    @FXML
    TextField fldLicensePlate, fldKilometrage, fldPriceGroup, fldGarage, fldSeats, fldSleeps;

    @FXML
    CheckBox checkKitchen, checkHeating, checkWC;

    private int autoCamperID = 0;

    @FXML
    public void initialize() {
        btnApply.setOnAction(e -> {
            String sql = "{call updateAutoCamper(?, ?,?,?,?,?,?,?,?,?)}";

            try (Connection con = DriverManager.getConnection(Main.URL); CallableStatement stmt = con.prepareCall(sql)) {
                stmt.setInt("AutoCamperID", autoCamperID);
                stmt.setString("LicensePlate", fldLicensePlate.getText());
                stmt.setDouble("Kilometrage", Double.parseDouble(fldKilometrage.getText()));
                stmt.setInt("PriceGroup", Integer.parseInt(fldPriceGroup.getText()));
                stmt.setInt("Garage", Integer.parseInt(fldGarage.getText()));
                stmt.setInt("Seats", Integer.parseInt(fldSeats.getText()));
                stmt.setInt("Sleeps", Integer.parseInt(fldSleeps.getText()));
                stmt.setBoolean("Kitchen", checkKitchen.isSelected());
                stmt.setBoolean("Heating", checkHeating.isSelected());
                stmt.setBoolean("WC", checkWC.isSelected());

                stmt.executeQuery();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }

    public void initializeFields(AutoCamper camper) {
        autoCamperID = camper.getId();
        fldSeats.setText(String.valueOf(camper.getSeats()));
        fldSleeps.setText(String.valueOf(camper.getSleeps()));
        fldPriceGroup.setText(String.valueOf(PriceCategory.getEnum(camper.getPrice()).id));
        checkKitchen.setSelected(camper.isKitchen());
        checkWC.setSelected(camper.isWc());
    }
}
