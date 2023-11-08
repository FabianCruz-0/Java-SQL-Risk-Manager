package src.javasqlriskmanager.controllers.departmentcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import src.javasqlriskmanager.MainApplication;
import src.javasqlriskmanager.models.Department;
import src.javasqlriskmanager.singletons.DepartmentSingleton;
import src.javasqlriskmanager.singletons.UserSingleton;
import src.javasqlriskmanager.utils.ConnectToDB;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public void delete() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación de eliminar.");
        alert.setContentText("¿Desea confirmar proceder con la eliminación del registro?");
        alert.showAndWait();

        if(alert.getResult().getText().equals("Aceptar")) {

            try {
                Connection con = ConnectToDB.connectToDB();

                String query = "UPDATE Incidents SET ID_Department = NULL WHERE ID_Department = ?";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setLong(1, DepartmentSingleton.getInstance().getDepartment().getID());
                preparedStatement.execute();

                query = "UPDATE Users SET ID_Department = NULL WHERE ID_Department = ?";
                 preparedStatement = con.prepareStatement(query);
                preparedStatement.setLong(1, DepartmentSingleton.getInstance().getDepartment().getID());
                preparedStatement.execute();

                query = "DELETE FROM Departments WHERE ID = ?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setLong(1, DepartmentSingleton.getInstance().getDepartment().getID());
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
