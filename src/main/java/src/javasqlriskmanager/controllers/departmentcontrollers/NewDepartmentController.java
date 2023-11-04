package src.javasqlriskmanager.controllers.departmentcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import src.javasqlriskmanager.MainApplication;

import java.io.IOException;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class NewDepartmentController {
    public void irAtras() throws IOException {
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatDepartamentos.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Catálogo de departamentos");
        principalStage.setScene(scene);
        principalStage.setResizable(false);
        principalStage.show();
    }

    public void createDepartment(ActionEvent actionEvent) {
    }
}
