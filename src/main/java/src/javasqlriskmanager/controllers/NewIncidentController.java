package src.javasqlriskmanager.controllers;

import javafx.fxml.FXML;
import src.javasqlriskmanager.utils.ConnectToDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewIncidentController {

    @FXML
    protected void createIncident() {

        String insertQuery = "INSERT INTO Incident_Status (Name) VALUES (?)";
        try {
            Connection con = ConnectToDB.connectToDB();
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, "CERRADO");
            preparedStatement.executeUpdate();
            con.close();
            System.out.println("Status creado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
