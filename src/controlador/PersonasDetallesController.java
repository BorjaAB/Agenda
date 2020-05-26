package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Persona;

public class PersonasDetallesController implements Initializable {

    @FXML
    private TableColumn clNombre;
    @FXML
    private TableColumn clApellido1;
    @FXML
    private TableColumn clApellido2;
    @FXML
    private TableColumn clNumero;
    @FXML
    private TableColumn clEmail;
    @FXML
    private TableView<Persona> tblPersonas;
    private ObservableList<Persona> personas;
    private ObservableList<Persona> filtroPersonas;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Label lbFiltro;
    @FXML
    private TextField tfFiltro;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        personas = FXCollections.observableArrayList();
        filtroPersonas = FXCollections.observableArrayList();
        
        this.tblPersonas.setItems(personas);
        
        this.clNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.clApellido1.setCellValueFactory(new PropertyValueFactory("apellido1"));
        this.clApellido2.setCellValueFactory(new PropertyValueFactory("apellido2"));
        this.clNumero.setCellValueFactory(new PropertyValueFactory("numero"));
        this.clEmail.setCellValueFactory(new PropertyValueFactory("email"));
    }    

    @FXML
    private void agregarPersona(ActionEvent event){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/AgregarPersonaVista.fxml"));
                Parent root = loader.load();
                
                AgregarPersonaController controlador = loader.getController();
                controlador.initAttributes(personas);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                
                Persona p = controlador.getPersona();
                if (p != null) {
                    this.personas.add(p);
                    if (p.getNombre().contains(this.tfFiltro.getText().toLowerCase())) {
                        this.filtroPersonas.add(p);
                    }
                    this.tblPersonas.refresh();
                }
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
    }

    @FXML
    private void modificarPersona(ActionEvent event) {
        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();
        
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Selecciona una persona");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/AgregarPersonaVista.fxml"));
                Parent root = loader.load();
                
                AgregarPersonaController controlador = loader.getController();
                controlador.initAttributes(personas, p);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                
                Persona aux = controlador.getPersona();
                if (aux != null) {
                    if (!aux.getNombre().contains(this.tfFiltro.getText().toLowerCase())) {
                        this.filtroPersonas.remove(aux);
                    }
                    this.tblPersonas.refresh();
                }
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void eliminarPersona(ActionEvent event) {
        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();
        
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Selecciona una persona");
            alert.showAndWait();
        } else {
            this.personas.remove(p);
            this.filtroPersonas.remove(p);
            this.tblPersonas.refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Persona borrada");
            alert.showAndWait();
        }
    }

    @FXML
    private void filtrar(KeyEvent event) {
        String filtroNombre = this.tfFiltro.getText();
        if (filtroNombre.isEmpty()) {
            this.tblPersonas.setItems(personas);
        } else {
            this.filtroPersonas.clear();
            
            for (Persona persona : this.personas) {
                if (persona.getNombre().toLowerCase().contains(filtroNombre.toLowerCase())) {
                    this.filtroPersonas.add(persona);
                }
            }
            this.tblPersonas.setItems(filtroPersonas);
        }
    }
    
}