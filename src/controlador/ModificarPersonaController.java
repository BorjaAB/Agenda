package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void modificarPersona(ActionEvent event) {
    }
    
}
