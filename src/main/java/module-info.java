module com.iesfranciscodelosrios.Proyecto_RedSocial {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
    requires java.xml.bind;

    opens com.iesfranciscodelosrios.Proyecto_RedSocial to javafx.fxml;
    exports com.iesfranciscodelosrios.Proyecto_RedSocial;
}
