package src.javasqlriskmanager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.javasqlriskmanager.MainApplication;
import src.javasqlriskmanager.controllers.incidentcontrollers.CatIncidenciasController;
import src.javasqlriskmanager.models.Usuario;
import src.javasqlriskmanager.utils.ConnectToDB;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class LoginController {

    public Long idUser;
    @FXML
    TextField emailLogin, emailRegister, nameRegister;

    @FXML
    PasswordField passLogin,passRegister;

    @FXML
    public void register() throws IOException {
        boolean registerSuccess = false;

        if(!nameRegister.getText().isBlank() && !emailRegister.getText().isBlank() && !passRegister.getText().isBlank() ) {
            String insertQuery = "INSERT INTO Users " +
                    "(Name, Email, Password, ID_Role, ID_Department)" +
                    " VALUES (?, ?, ?, 1, 1)";
            try {
                Connection con = ConnectToDB.connectToDB();
                PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
                preparedStatement.setString(1, nameRegister.getText());
                preparedStatement.setString(2,emailRegister.getText());
                preparedStatement.setString(3,passRegister.getText());
                preparedStatement.executeUpdate();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            registerSuccess = true;
        }

        if(registerSuccess == true) {
            principalStage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            principalStage.setTitle("CREAR INCIDENCIA");
            principalStage.setScene(scene);
        principalStage.setResizable(false);
            principalStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se pudo continuar con el registro.");
            alert.showAndWait();
        }
    }

    public void login(ActionEvent event) throws IOException, SQLException {
        boolean loginSuccess = false;
        Usuario user = null;

        if(!emailLogin.getText().isBlank() && !passLogin.getText().isBlank()) {
                String selectQuery = "SELECT * FROM Users WHERE email = ?";
            try {
                Connection con = ConnectToDB.connectToDB();
                PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
                preparedStatement.setString(1,emailLogin.getText());
                ResultSet rs = preparedStatement.executeQuery();

                while(rs.next()) {
                    if(rs.getString("Password").equals(passLogin.getText())) {
                        loginSuccess = true;
                        user = new Usuario(rs.getLong("ID"), rs.getString("Name"), rs.getString("Email"), rs.getString("Position"),
                                rs.getLong("ID_Role"), rs.getLong("ID_Department"), rs.getString("Password"));
                    }
                }

                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if(loginSuccess == true) {
            if(user.getID_Role() == 3){
                idUser = user.getID_Role();
                principalStage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatIncidencias.fxml"));
                //CatIncidenciasController controller = new CatIncidenciasController();

                Scene scene = new Scene(fxmlLoader.load());
                principalStage.setTitle("CREAR INCIDENCIA");
                principalStage.setScene(scene);
                principalStage.setResizable(false);

                principalStage.show();
            }else{
                principalStage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-menu.fxml"));

                MainMenuController controller = new MainMenuController();

                Scene scene = new Scene(fxmlLoader.load());
                principalStage.setTitle("Menu Principal");
                principalStage.setScene(scene);
                principalStage.setResizable(false);
                controller.init(user.getID(),principalStage,this);
                principalStage.show();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se pudo iniciar sesión.");
            alert.showAndWait();
        }

    }
}
