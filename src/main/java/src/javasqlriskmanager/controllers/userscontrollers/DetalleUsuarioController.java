package src.javasqlriskmanager.controllers.userscontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import src.javasqlriskmanager.MainApplication;
import src.javasqlriskmanager.models.Usuario;
import src.javasqlriskmanager.singletons.UserSingleton;

import java.io.IOException;
import java.net.URL;
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
}
