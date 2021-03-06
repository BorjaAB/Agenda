package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Persona {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int numero;
    private String email;
    
    public Persona() {
    }
    
    public Persona(String nombre, String apellido1, String apellido2, int numero, String email) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.numero = numero;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido1, other.apellido1)) {
            return false;
        }
        if (!Objects.equals(this.apellido2, other.apellido2)) {
            return false;
        }
        return true;
    }
    
    public ObservableList<Persona> getPersonas(){
        
        ObservableList<Persona> obs = FXCollections.observableArrayList();
        try {
        Conexion conexion = new Conexion();
        Connection con = conexion.getConexion();
        
        Statement s = null;
            
        s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM CONTACTOS");
        
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                int numero = rs.getInt("numero");
                String email = rs.getString("email");

                Persona p = new Persona(nombre, apellido1, apellido2, numero, email);
                obs.add(p);
            }
        conexion.cerrarConexion(con);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("La tabla no existe.");
            alert.showAndWait();
        }
        return obs;
    }
    
    public void anadirPersona(Persona p) {
        try {
            Conexion conexion = new Conexion();
            Connection con = conexion.getConexion();

            PreparedStatement s = null;

            s = con.prepareStatement("INSERT INTO CONTACTOS VALUES (?,?,?,?,?)");

            s.setString(1,p.getNombre());
            s.setString(2,p.getApellido1());
            s.setString(3,p.getApellido2());
            s.setInt(4,p.getNumero());
            s.setString(5,p.getEmail());
            s.executeUpdate();

            conexion.cerrarConexion(con);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void borrarPersona(Persona p) {
        try {
            Conexion conexion = new Conexion();
            Connection con = conexion.getConexion();
            
            PreparedStatement s = null;
            
            s = con.prepareStatement("DELETE FROM CONTACTOS WHERE numero = ?");
            s.setInt(1,p.getNumero());
            s.executeUpdate();

            conexion.cerrarConexion(con);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error en el método borrarPersona");
            alert.showAndWait();
        }
    }
    
    public void modificarPersona(Persona p) {
        try {
            Conexion conexion = new Conexion();
            Connection con = conexion.getConexion();

            PreparedStatement s = null;
            
            s = con.prepareStatement("UPDATE CONTACTOS SET NOMBRE = ?, APELLIDO1 = ?, APELLIDO2 = ?, EMAIL = ?  WHERE NUMERO = ?");
            s.setString(1,p.getNombre());
            s.setString(2,p.getApellido1());
            s.setString(3,p.getApellido2());
            s.setString(4,p.getEmail());
            s.setInt(5,p.getNumero());
            s.executeUpdate();

            conexion.cerrarConexion(con);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error en el método modificarPersona");
            alert.showAndWait();
        }
    }
    
    public ObservableList<Persona> filtro(String filtro){
        
        ObservableList<Persona> obs = FXCollections.observableArrayList();
        try {
            Conexion conexion = new Conexion();
            Connection con = conexion.getConexion();

            PreparedStatement s = null;
            
            s = con.prepareStatement("SELECT * FROM CONTACTOS WHERE NOMBRE LIKE ?");
            s.setString(1, "%"+filtro+"%");
            ResultSet rs = s.executeQuery();
            
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                int numero = rs.getInt("numero");
                String email = rs.getString("email");

                Persona p = new Persona(nombre, apellido1, apellido2, numero, email);
                obs.add(p);
            }
            
            conexion.cerrarConexion(con);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Fallo");
            alert.showAndWait();
        }
        return obs;
    }
    
}