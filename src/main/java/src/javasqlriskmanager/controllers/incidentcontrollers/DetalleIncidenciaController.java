package src.javasqlriskmanager.controllers.incidentcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.w3c.dom.Text;
import src.javasqlriskmanager.MainApplication;
import src.javasqlriskmanager.models.Incident;
import src.javasqlriskmanager.singletons.IncidentSingleton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class DetalleIncidenciaController implements Initializable {

    @FXML
    Label incidentDetail;

    @FXML
    Label title;

    @FXML
    Label idNumber;

    @FXML
    Label status;

    @FXML
    TextArea description;

    @FXML
    Label createdDate;

    @FXML
    Label department;

    @FXML
    Label severity;

    @FXML
    Label assignedUser;

    @FXML
    Label creatorUser;

    @FXML
    Label updateDate;

    public void irCatalogo() throws IOException {
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatIncidencias.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Catálogo de incidencias");
        principalStage.setScene(scene);
        principalStage.setResizable(false);principalStage.setResizable(false);
        principalStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Incident incident = IncidentSingleton.getInstance().getIncident();
        incidentDetail.setText("Detalle de incidencia #" + incident.getId());
        title.setText(incident.getTitle());
        status.setText(incident.getId_status().toString());
        description.setText(incident.getDescription());
        createdDate.setText(incident.getCreatedAt().toString());
        department.setText(incident.getId_department().toString());
        severity.setText(incident.getId_severity().toString());
        assignedUser.setText(incident.getId_assignedUser().toString());
        creatorUser.setText(incident.getId_creatorUser().toString());
        updateDate.setText(incident.getUpdateDate().toString());



    }
}
