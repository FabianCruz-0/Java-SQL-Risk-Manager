package src.javasqlriskmanager.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import src.javasqlriskmanager.MainApplication;
import src.javasqlriskmanager.utils.ConnectToDB;
import src.javasqlriskmanager.utils.IncidentDTO;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static src.javasqlriskmanager.MainApplication.principalStage;

public class CatIncidenciasController{

    private Parent root;
    @FXML
    public void traeIncidencias()  {

        List<IncidentDTO> lstIncidencias = new List<IncidentDTO>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NotNull
            @Override
            public Iterator<IncidentDTO> iterator() {
                return null;
            }

            @NotNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NotNull
            @Override
            public <T> T[] toArray(@NotNull T[] a) {
                return null;
            }

            @Override
            public boolean add(IncidentDTO incidentDTO) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NotNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NotNull Collection<? extends IncidentDTO> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NotNull Collection<? extends IncidentDTO> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NotNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NotNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public IncidentDTO get(int index) {
                return null;
            }

            @Override
            public IncidentDTO set(int index, IncidentDTO element) {
                return null;
            }

            @Override
            public void add(int index, IncidentDTO element) {

            }

            @Override
            public IncidentDTO remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @NotNull
            @Override
            public ListIterator<IncidentDTO> listIterator() {
                return null;
            }

            @NotNull
            @Override
            public ListIterator<IncidentDTO> listIterator(int index) {
                return null;
            }

            @NotNull
            @Override
            public List<IncidentDTO> subList(int fromIndex, int toIndex) {
                return null;
            }
        }; //se instancia la lista de incidencias
        String getQuery = "SELECT * FROM Incidents"; //select que trae las incidencias de la BD

        try {
            Connection con = ConnectToDB.connectToDB();
            PreparedStatement preparedStatement = con.prepareStatement(getQuery);

            ResultSet rs = preparedStatement.executeQuery();



            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void creaIncident(ActionEvent event) throws IOException {
        //principalStage.close();
        FXMLLoader loader = new FXMLLoader(MainMenuController.class.getResource("new-incident.fxml"));
        //Scene scene = new Scene(loader.load());
        root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Nueva Incidencia");
        stage.show();
    }
}
