package src.javasqlriskmanager.controllers.departmentcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import src.javasqlriskmanager.MainApplication;
import src.javasqlriskmanager.models.Department;
import src.javasqlriskmanager.singletons.DepartmentSingleton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class DetalleDepartamentoController implements Initializable {

    @FXML
    TextField name;

    @FXML
    TextField email;

    @FXML
    TextField phone;

    @FXML
    TextField type;

    public void irCatalogo() throws IOException {
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatDepartamentos.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Catálogo de departamentos");
        principalStage.setScene(scene);
        principalStage.setResizable(false);principalStage.setResizable(false);
        principalStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Department department = DepartmentSingleton.getInstance().getDepartment();
        name.setText(department.getName());
        email.setText(department.getEmail());
        phone.setText(department.getPhone());
        type.setText(department.getID_DepType().toString());
    }
}
