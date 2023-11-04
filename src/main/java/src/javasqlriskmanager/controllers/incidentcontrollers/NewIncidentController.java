package src.javasqlriskmanager.controllers.incidentcontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import src.javasqlriskmanager.MainApplication;
import src.javasqlriskmanager.utils.ConnectToDB;

import java.io.IOException;
import java.sql.*;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class NewIncidentController {

    @FXML
    TextField id_incidentTitle;

    @FXML
    TextArea id_incidentDesc;

    @FXML
    ChoiceBox<String> ListSeveridad;

    @FXML
    protected void initialize() {
        // Load incident severities from the database and populate the ChoiceBox
        loadIncidentSeverities();
        loadIncidentDepartaments();
    }

    private void loadIncidentSeverities() {
        ObservableList<String> severities = FXCollections.observableArrayList();
        String selectQuery = "SELECT Name FROM Incident_Severity_Types";

        try {
            Connection con = ConnectToDB.connectToDB();
            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                severities.add(resultSet.getString("Name"));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ListSeveridad.setItems(severities);
    }

    @FXML
    ChoiceBox<String> ListDep;

    private void loadIncidentDepartaments() {
        ObservableList<String> departaments = FXCollections.observableArrayList();
        String selectQuery = "SELECT Name FROM Department_Types";

        try {
            Connection con = ConnectToDB.connectToDB();
            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                departaments.add(resultSet.getString("Name"));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ListDep.setItems(departaments);
    }

    @FXML
    protected void createIncident() throws IOException {

        String insertQuery = "INSERT INTO Incidents " +
                "(Title, Description, CreatedAt, UpdateDate, ID_Status, ID_Severity, ID_CreatorUser, ID_AssignedUser, ID_Department)" +
                " VALUES (?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL)";
        try {
            Connection con = ConnectToDB.connectToDB();
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, id_incidentTitle.getText());
            preparedStatement.setString(2,id_incidentDesc.getText());
            preparedStatement.setDate(3,new Date(System.currentTimeMillis()));
            preparedStatement.setDate(4,new Date(System.currentTimeMillis()));
            //preparedStatement.setInt(5,1);
            //preparedStatement.setInt(6,1);
            //preparedStatement.setInt(7,1);
            //preparedStatement.setInt(8,1);
            //preparedStatement.setInt(9,1);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatIncidencias.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Catálogo de incidencias");
        principalStage.setScene(scene);
        principalStage.setResizable(false);
        principalStage.show();

    }

    public void irAtras(ActionEvent actionEvent) throws IOException {
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatIncidencias.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Catálogo de incidencias");
        principalStage.setScene(scene);
        principalStage.setResizable(false);
        principalStage.show();
    }
}
