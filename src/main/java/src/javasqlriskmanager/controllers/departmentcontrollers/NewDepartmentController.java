package src.javasqlriskmanager.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import src.javasqlriskmanager.MainApplication;
import src.javasqlriskmanager.models.Incident;
import src.javasqlriskmanager.models.Rol;
import src.javasqlriskmanager.utils.ConnectToDB;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class CatRolesController implements Initializable {

    @FXML
    TableView<Rol> tbl_Roles;

    @FXML
    private TableColumn<Rol, Long> col_id;
    @FXML
    private TableColumn<Rol, String> col_nombre;

    @FXML
    void setRolList()  {

        String getQuery = "SELECT * FROM User_Roles";
        ObservableList<Rol> rolList = FXCollections.observableArrayList();

        try {
            Connection con = ConnectToDB.connectToDB();
            PreparedStatement preparedStatement = con.prepareStatement(getQuery);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String Name = rs.getString("Name");
                Long id = rs.getLong("ID");
                Rol rol = new Rol(id, Name);
                if(rol!=null)
                    rolList.add(rol);
            }

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tbl_Roles.setItems(rolList);

        tbl_Roles.setRowFactory( tv -> {
            TableRow<Rol> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Rol rowData = row.getItem();
                    System.out.println(rowData.toString());

                    try {
                        irDetalle();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });

    }

    public void irMenuPrincipal() throws IOException {
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Menú Principal");
        principalStage.setScene(scene);
        principalStage.setResizable(false);
        principalStage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_id.setCellValueFactory(new PropertyValueFactory<Rol, Long>("ID")); //Nombre segun como se llama en el model
        col_nombre.setCellValueFactory(new PropertyValueFactory<Rol, String>("Name"));
        setRolList();
    }

    public void irDetalle() throws IOException {
        principalStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("DetalleRol.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        principalStage.setTitle("Detalle de rol");
        principalStage.setScene(scene);
        principalStage.setResizable(false);
        principalStage.show();
    }
}
