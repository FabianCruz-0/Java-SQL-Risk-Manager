package src.javasqlriskmanager.controllers.userscontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import src.javasqlriskmanager.MainApplication;
import src.javasqlriskmanager.models.Usuario;
import src.javasqlriskmanager.singletons.IncidentSingleton;
import src.javasqlriskmanager.singletons.UserSingleton;
import src.javasqlriskmanager.utils.ConnectToDB;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class DetalleUsuarioController implements Initializable {

    @FXML
    TextField idText;

    @FXML
    TextField name;

    @FXML
    TextField email;

    @FXML
    TextField password;

    @FXML
    TextField posicion;

    @FXML
    TextField rol;

    @FXML
    TextField department;

    public void irCatalogo() throws IOException {
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatUsuarios.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Catálogo de usuarios");
        principalStage.setScene(scene);
        principalStage.setResizable(false);principalStage.setResizable(false);
        principalStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Usuario usuario = UserSingleton.getInstance().getUsuario();

        idText.setText(usuario.getID().toString());
        name.setText(usuario.getName());
        email.setText(usuario.getEmail());
        password.setText(usuario.getPassword());
        posicion.setText(usuario.getPassword());
        rol.setText(usuario.getID_Role().toString());
        department.setText(usuario.getID_Department().toString());

    }

    public void delete() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación de eliminar.");
        alert.setContentText("¿Desea confirmar proceder con la eliminación del registro?");
        alert.showAndWait();

        if(alert.getResult().getText().equals("Aceptar")) {

            try {
                Connection con = ConnectToDB.connectToDB();

                String query = "UPDATE Incidents SET ID_CreatorUser = NULL WHERE ID_CreatorUser = ?";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setLong(1, UserSingleton.getInstance().getUsuario().getID());
                preparedStatement.execute();

                query = "UPDATE Incidents SET ID_AssignedUser = NULL WHERE ID_AssignedUser = ?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setLong(1, UserSingleton.getInstance().getUsuario().getID());
                preparedStatement.execute();

                query = "DELETE FROM Users WHERE ID = ?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setLong(1, UserSingleton.getInstance().getUsuario().getID());
                preparedStatement.execute();

                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            irCatalogo();
        }
    }
}
