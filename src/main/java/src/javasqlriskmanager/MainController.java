package src.javasqlriskmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import src.javasqlriskmanager.utils.ConnectToDB;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");

        String url = "jdbc:sqlserver://localhost:1433;databaseName=risksManager;encrypt=false";
        String user = "sa";
        String password = "Admin123";

        ConnectToDB.connectToDB(url,user,password);
    }
}