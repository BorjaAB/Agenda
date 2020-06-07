package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Persona;

public class ModificarPersonaController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfPrimerApellido;
    @FXML
    private TextField tfSegundoApellido;
    @FXML
    private TextField tfNumero;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btnModificarPersona;
    private Persona persona;
    private ObservableList<Persona> personas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void initAttributes (ObservableList<Persona> personas, Persona p) {
        this.personas = personas;
        this.persona = p;
        this.tfNombre.setText(p.getNombre());
        this.tfPrimerApellido.setText(p.getApellido1());
        this.tfSegundoApellido.setText(p.getApellido2());
        this.tfNumero.setText(p.getNumero()+"");
        this.tfEmail.setText(p.getEmail());
    }
    
    @FXML
    private void modificarPersona(ActionEvent event) {
        String nombre = this.tfNombre.getText();
        String apellido1 = this.tfPrimerApellido.getText();
        String apellido2 = this.tfSegundoApellido.getText();
        int numero = Integer.parseInt(this.tfNumero.getText());
        String email = this.tfEmail.getText();
        
        Persona p = new Persona(nombre, apellido1, apellido2, numero, email);
        p.modificarPersona(p);
        
        Stage stage = (Stage) this.btnModificarPersona.getScene().getWindow();
        stage.close();
    }
    
}
