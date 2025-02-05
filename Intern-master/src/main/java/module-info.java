module Intern {
    exports TestFx;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.fxml;
    opens Controllers to javafx.fxml;
    opens Models to javafx.base;

    requires javafx.controls;
    requires com.gluonhq.maps;
    requires javafx.web;
    requires java.desktop;


}