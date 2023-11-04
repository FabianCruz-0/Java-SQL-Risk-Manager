package src.javasqlriskmanager.controllers.userscontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import src.javasqlriskmanager.MainApplication;

import java.io.IOException;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class NewUserController {
    public void createUser(ActionEvent actionEvent) {
    }

    public void irAtras() throws IOException {
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatUsuarios.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Catálogo de usuarios");
        principalStage.setScene(scene);
        principalStage.setResizable(false);
        principalStage.show();
    }
}
