package src.javasqlriskmanager.controllers.rolcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;
import src.javasqlriskmanager.MainApplication;
import src.javasqlriskmanager.models.Rol;
import src.javasqlriskmanager.singletons.RolSingleton;
import src.javasqlriskmanager.singletons.SesionSingleton;
import src.javasqlriskmanager.singletons.UserSingleton;
import src.javasqlriskmanager.utils.ConnectToDB;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class DetalleRolController implements Initializable {

    @FXML
    TextField id;

    @FXML
    TextField name;

    public void irCatalogo() throws IOException {
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatRoles.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Catálogo de roles");
        principalStage.setScene(scene);
        principalStage.setResizable(false);principalStage.setResizable(false);
        principalStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Rol rol = RolSingleton.instance.getRol();

        id.setText(rol.getID().toString());
        name.setText(rol.getName());
    }

    public void delete() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación de eliminar.");
        alert.setContentText("¿Desea confirmar proceder con la eliminación del registro?");
        alert.showAndWait();

        if(alert.getResult().getText().equals("Aceptar")) {

            if(RolSingleton.getInstance().getRol().getID().equals(SesionSingleton.getInstance().getUsuario().getID_Role())) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("No puedes eliminar el rol de adminisitrador.");
                alert.showAndWait();
            } else {
                try {
                    Connection con = ConnectToDB.connectToDB();

                    String query = "UPDATE Users SET ID_Role = NULL WHERE ID_Role = ?";
                    PreparedStatement preparedStatement = con.prepareStatement(query);
                    preparedStatement.setLong(1, RolSingleton.getInstance().getRol().getID());
                    preparedStatement.execute();

                    query = "DELETE FROM User_Roles WHERE ID = ?";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setLong(1, RolSingleton.getInstance().getRol().getID());
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
}
