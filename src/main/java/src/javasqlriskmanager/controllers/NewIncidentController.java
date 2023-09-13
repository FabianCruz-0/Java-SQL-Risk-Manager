package src.javasqlriskmanager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import src.javasqlriskmanager.utils.ConnectToDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewIncidentController {

    @FXML
    TextField id_incidentTitle;

    @FXML
    TextArea id_incidentDesc;

    @FXML
    protected void createIncident() {

        String insertQuery = "INSERT INTO Incidents " +
                "(Title, Description, CreatedAt, UpdateDate, ID_Status, ID_Severity, ID_CreatorUser, ID_AssignedUser, ID_Department)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = ConnectToDB.connectToDB();
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, id_incidentTitle.getText());
            preparedStatement.setString(2,id_incidentDesc.getText());
            preparedStatement.setDate(3,new Date(System.currentTimeMillis()));
            preparedStatement.setDate(4,new Date(System.currentTimeMillis()));
            preparedStatement.setInt(5,1);
            preparedStatement.setInt(6,1);
            preparedStatement.setInt(7,1);
            preparedStatement.setInt(8,1);
            preparedStatement.setInt(9,1);
            preparedStatement.executeUpdate();
            con.close();
            System.out.println("Status creado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
