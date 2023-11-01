package src.javasqlriskmanager.controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import src.javasqlriskmanager.MainApplication;
import src.javasqlriskmanager.models.Incident;
import src.javasqlriskmanager.utils.ConnectToDB;
import src.javasqlriskmanager.utils.IncidentDTO;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class CatIncidenciasController implements Initializable {
    //public CatIncidenciasController() {
    //    setIncidentList();
    //}

    private Parent root;

    @FXML
    TableView<Incident> tbl_incidencias;

    @FXML
    private TableColumn<Incident, Long> col_id;
    @FXML
    private TableColumn<Incident, String> col_Title;
    @FXML
    private TableColumn<Incident, String> col_Description;
    @FXML
    private TableColumn<Incident, Date> col_Created;
    @FXML
    private TableColumn<Incident, Date> col_Update;
    @FXML
    private TableColumn<Incident, Long> col_Status;
    @FXML
    private TableColumn<Incident, Long> col_Severity;
    @FXML
    private TableColumn<Incident, Long> col_Creator;
    @FXML
    private TableColumn<Incident, Long> col_Assigned;
    @FXML
    private TableColumn<Incident, Long> col_Department;

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
                Long id = rs.getLong("ID");
                String description = rs.getString("Description");
                Date createdAt = rs.getDate("CreatedAt");
                Date updateDate = rs.getDate("UpdateDate");
                Long id_status = rs.getLong("ID_Status");
                Long id_severity = rs.getLong("ID_Severity");
                Long id_creatorUser = rs.getLong("ID_CreatorUser");
                Long id_assignedUser = rs.getLong("ID_AssignedUser");
                Long id_department = rs.getLong("ID_Department");
                Incident incident = new Incident(title,id,description,createdAt,updateDate,id_status,id_severity,id_creatorUser,id_assignedUser,id_department);
                if(incident!=null)
                    incidentList.add(incident);
            }

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tbl_incidencias.setItems(incidentList);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_id.setCellValueFactory(new PropertyValueFactory<Incident, Long>("id")); //Nombre segun como se llama en el model
        col_Title.setCellValueFactory(new PropertyValueFactory<Incident, String>("title"));
        col_Description.setCellValueFactory(new PropertyValueFactory<Incident, String>("description"));
        col_Created.setCellValueFactory(new PropertyValueFactory<Incident, Date>("createdAt"));
        col_Update.setCellValueFactory(new PropertyValueFactory<Incident, Date>("updateDate"));
        col_Status.setCellValueFactory(new PropertyValueFactory<Incident, Long>("id_status"));
        col_Severity.setCellValueFactory(new PropertyValueFactory<Incident, Long>("id_severity"));
        col_Creator.setCellValueFactory(new PropertyValueFactory<Incident, Long>("id_creatorUser"));
        col_Assigned.setCellValueFactory(new PropertyValueFactory<Incident, Long>("id_assignedUser"));
        col_Department.setCellValueFactory(new PropertyValueFactory<Incident, Long>("id_department"));
        setIncidentList();
    }
}
