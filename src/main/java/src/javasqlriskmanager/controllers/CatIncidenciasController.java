package src.javasqlriskmanager.controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import src.javasqlriskmanager.MainApplication;
import src.javasqlriskmanager.models.Incident;
import src.javasqlriskmanager.utils.ConnectToDB;
import src.javasqlriskmanager.utils.IncidentDTO;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class CatIncidenciasController{
    public CatIncidenciasController() {
        setIncidentList();
    }

    private Parent root;

    @FXML
    TableView tbl_incidencias;

    @FXML
    void setIncidentList()  {

        String getQuery = "SELECT * FROM Incidents";
        ObservableList<Incident> incidentList = FXCollections.observableArrayList();

        try {
            Connection con = ConnectToDB.connectToDB();
            PreparedStatement preparedStatement = con.prepareStatement(getQuery);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String title = rs.getString("Title");
                Long id = rs.getLong("id");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("createdAt");
                Date updateDate = rs.getDate("updateDate");
                Long id_status = rs.getLong("ID_Status");
                Long id_severity = rs.getLong("ID_Severity");
                Long id_creatorUser = rs.getLong("ID_creatorUser");
                Long id_assignedUser = rs.getLong("ID_assignedUser");
                Long id_department = rs.getLong("ID_Department");
                Incident incident = new Incident(title,id,description,createdAt,updateDate,id_status,id_severity,id_creatorUser,id_assignedUser,id_department);
                if(incident!=null)
                    incidentList.add(incident);
            }

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //tbl_incidencias.setItems(incidentList);

    }

    @FXML
    protected void creaIncident(ActionEvent event) throws IOException {
        //principalStage.close();
        FXMLLoader loader = new FXMLLoader(MainMenuController.class.getResource("new-incident.fxml"));
        //Scene scene = new Scene(loader.load());
        root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Nueva Incidencia");
        stage.show();
    }
}
