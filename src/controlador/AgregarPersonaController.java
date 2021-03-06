package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Persona;

public class AgregarPersonaController implements Initializable {

    @FXML
    private Label lbNombre;
    @FXML
    private TextField tfNombre;
    @FXML
    private Label lbApellido1;
    @FXML
    private TextField tfApellido1;
    @FXML
    private Label lbApellido2;
    @FXML
    private TextField tfApellido2;
    @FXML
    private Label lbNumero;
    @FXML
    private TextField tfNumero;
    @FXML
    private Label lbEmail;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btnAgregarPersona;
    
    private Persona persona;
    private ObservableList<Persona> personas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void initAttributes (ObservableList<Persona> personas) {
        this.personas = personas;
    }
    
    public void initAttributes (ObservableList<Persona> personas, Persona p) {
        this.personas = personas;
        this.persona = p;
        this.tfNombre.setText(p.getNombre());
        this.tfApellido1.setText(p.getApellido1());
        this.tfApellido2.setText(p.getApellido2());
        this.tfNumero.setText(p.getNumero()+"");
        this.tfEmail.setText(p.getEmail());
    }
    
    @FXML
    private void agregarPersona(ActionEvent event) {
        String nombre = this.tfNombre.getText();
        String apellido1 = this.tfApellido1.getText();
        String apellido2 = this.tfApellido2.getText();
        int numero = Integer.parseInt(this.tfNumero.getText());
        String email = this.tfEmail.getText();
        
        Persona p = new Persona(nombre, apellido1, apellido2, numero, email);
        
        p.anadirPersona(p);
        
        Stage stage = (Stage) this.btnAgregarPersona.getScene().getWindow();
        stage.close();
    }

    public Persona getPersona() {
        return persona;
    }
    
}