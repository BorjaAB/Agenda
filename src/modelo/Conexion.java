package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    public Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:borja/borja@localhost:1521:XE";
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.out.println("Error Clase no encontrada.");
        } catch (SQLException e) {
            System.out.println("Exeption SQL " + e.getMessage());
        }
        return con;
    }
    
    public void cerrarConexion (Connection con) {
        try {
            con.close();
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
    }
    
}
