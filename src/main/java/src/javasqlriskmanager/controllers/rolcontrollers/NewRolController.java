package src.javasqlriskmanager.controllers.rolcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import src.javasqlriskmanager.MainApplication;

import java.io.IOException;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class NewRolController {
    public void irAtras() throws IOException {
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatRoles.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Catálogo de roles");
        principalStage.setScene(scene);
        principalStage.setResizable(false);
        principalStage.show();
    }

    public void createRol(ActionEvent actionEvent) {
    }
}
