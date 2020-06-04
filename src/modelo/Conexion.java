package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class Conexion {
    
    public Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:borja/borja@localhost:1521:XE";
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("JDBC no cargado en el proyecto.");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Usuario o contraseña no válidos");
            alert.showAndWait();
        }
        return con;
    }
    
    public void cerrarConexion (Connection con) {
        try {
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Conexión con la BBDD cerrada.");
            alert.showAndWait();
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
    }
    
}
