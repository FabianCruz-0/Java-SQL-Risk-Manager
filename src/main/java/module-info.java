module src.javasqlriskmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.dotenv;
    requires annotations;

    opens src.javasqlriskmanager to javafx.fxml;
    exports src.javasqlriskmanager;
    exports src.javasqlriskmanager.controllers;
    opens src.javasqlriskmanager.controllers to javafx.fxml;

    opens src.javasqlriskmanager.models to javafx.base;
}