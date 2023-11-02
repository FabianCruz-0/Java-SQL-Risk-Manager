package src.javasqlriskmanager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.javasqlriskmanager.MainApplication;
import src.javasqlriskmanager.utils.ConnectToDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class MainMenuController {

    private Parent root;
    @FXML
    protected void  goToIncidents() throws IOException {
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatIncidencias.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Catálogo de incidencias");
        principalStage.setScene(scene);
        principalStage.setResizable(false);
        principalStage.show();
    }

    @FXML
    protected void goToUsers() throws IOException{
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatUsuarios.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Catálogo de usuarios");
        principalStage.setScene(scene);
        principalStage.setResizable(false);
        principalStage.show();
    }

    @FXML
    protected void goToDeptos() throws IOException{
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatDepartamentos.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Catálogo de departamentos");
        principalStage.setScene(scene);
        principalStage.setResizable(false);
        principalStage.show();
    }

    @FXML
    protected void goToRoles() throws IOException{
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("CatRoles.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Catálogo de roles");
        principalStage.setScene(scene);
        principalStage.setResizable(false);
        principalStage.show();
    }
}
