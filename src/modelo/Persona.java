package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
            System.out.println(ex.getMessage());
        }
        return obs;
    }
    
}
