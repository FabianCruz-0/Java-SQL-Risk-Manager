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
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("CatIncidencias.fxml"));
        //Scene scene = new Scene(loader.load());
        root = loader.load();

        CatIncidenciasController catIncidenciasController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Catalogo de incidencias");
        stage.show();

        //CatIncidenciasController incidenciasController = loader.getController();
    }

    @FXML
    protected void goToUsers() throws IOException{
        principalStage.close();
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("CatUsuarios.fxml"));
        //Scene scene = new Scene(loader.load());
        root = loader.load();

        //CatIncidenciasController catIncidenciasController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Catalogo de usuarios");
        stage.show();
    }

    @FXML
    protected void goToDeptos() throws IOException{
        principalStage.close();
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("CatDepartamentos.fxml"));
        //Scene scene = new Scene(loader.load());
        root = loader.load();

        //CatIncidenciasController catIncidenciasController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Catalogo de departamentos");
        stage.show();
    }

    @FXML
    protected void goToRoles() throws IOException{
        principalStage.close();
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("CatRoles.fxml"));
        //Scene scene = new Scene(loader.load());
        root = loader.load();

        //CatIncidenciasController catIncidenciasController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Catalogo de roles");
        stage.show();
    }
}
