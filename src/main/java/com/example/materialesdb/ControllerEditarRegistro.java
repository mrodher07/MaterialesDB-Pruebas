package com.example.materialesdb;

import com.example.materialesdb.dao.MaterialDAO;
import com.example.materialesdb.modelos.Material;
import com.example.materialesdb.modelos.datosTecnicos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static javafx.collections.FXCollections.emptyObservableList;

public class ControllerEditarRegistro {

    private MaterialDAO materialDAO = new MaterialDAO();
    private Material materialAux;
    Material m = Material.getInstance();
    private ObservableList<Material> datos;
    private ObservableList<datosTecnicos> datosTecnicos;
    @FXML
    private GridPane gridPaneCuerpo;
    @FXML
    private Label botonVolverInicio;
    @FXML
    private Button cabeceraNuevoRegistro;
    @FXML
    private TextField nombreER;
    @FXML
    private TextField fabricanteER;
    @FXML
    private TextField materialER;
    @FXML
    private ComboBox indicadorPeligroER;
    @FXML
    private DatePicker fechaInicioER;
    @FXML
    private DatePicker fechaFinER;
    @FXML
    private Button botonActualizarRegistro;
    @FXML
    private TextField precioER;
    @FXML
    private TextArea composicionER;
    @FXML
    private TextField pesoER;
    @FXML
    private TextField colorER;
    @FXML
    private TextField herramientaUsoER;
    @FXML
    private TextField lugarUsoER;
    @FXML
    private TextField recipienteER;
    @FXML
    private Button botonBorrarRegistro;

    /**
     Método que se ejecuta al inicializar la vista, establece las opciones del selector de indicador de peligro,
     carga los datos en los campos, establece las acciones para los botones de "nuevo registro", "volver al inicio",
     "actualizar registro" y "borrar registro".
     */
    @FXML
    protected void initialize(){

        ObservableList<String> olOptions = FXCollections.observableArrayList(
                "A",
                "B",
                "C",
                "D",
                "E"
        );

        indicadorPeligroER.setItems(olOptions);

        cargarDatosCampos();

        cabeceraNuevoRegistro.setOnMouseClicked(new EventHandler(){
            @Override
            public void handle(Event event) {
                try {
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("viewNuevoRegistro.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                    stage.setTitle("Editar");
                    stage.setMinWidth(640);
                    stage.setMinHeight(500);
                    stage.setScene(scene);
                    stage.show();
                    Node source = (Node) event.getSource();
                    Stage stageActual = (Stage) source.getScene().getWindow();
                    stageActual.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        botonVolverInicio.setOnMouseClicked(new EventHandler(){
            @Override
            public void handle(Event event) {
                try {
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("viewInicio.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                    stage.setTitle("Editar");
                    stage.setMinWidth(640);
                    stage.setMinHeight(500);
                    stage.setScene(scene);
                    stage.show();
                    Node source = (Node) event.getSource();
                    Stage stageActual = (Stage) source.getScene().getWindow();
                    stageActual.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        botonActualizarRegistro.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {

                try {
                    actualizarCampos();
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("viewInicio.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                    stage.setTitle("Inicio");
                    stage.setMinWidth(640);
                    stage.setMinHeight(500);
                    stage.setScene(scene);
                    stage.show();
                    Node source = (Node) event.getSource();
                    Stage stageActual = (Stage) source.getScene().getWindow();
                    stageActual.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        botonBorrarRegistro.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    if(borrarRegistro()){
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("viewInicio.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                        stage.setTitle("Inicio");
                        stage.setMinWidth(640);
                        stage.setMinHeight(500);
                        stage.setScene(scene);
                        stage.show();
                        Node source = (Node) event.getSource();
                        Stage stageActual = (Stage) source.getScene().getWindow();
                        stageActual.close();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });


    }

    /**

     Método que carga los datos del material en los campos correspondientes de la vista de edición de registros.
     Obtiene los datos del material y datos técnicos desde la base de datos utilizando el ID del material actual.
     Si existen datos técnicos, los carga en los campos correspondientes, de lo contrario, los campos técnicos quedan vacíos.
     */
    public void cargarDatosCampos(){

        int id = m.getIdMaterial();
        datos = materialDAO.buscarDatosMaterial(id);
        nombreER.setText(datos.get(0).getNombreMaterial());
        fabricanteER.setText(datos.get(0).getFabricante());
        materialER.setText(datos.get(0).getMaterial());
        precioER.setText(String.valueOf(datos.get(0).getPrecio()));
        indicadorPeligroER.setValue(datos.get(0).getIndicadorPeligro());
        fechaInicioER.setValue(LocalDate.parse(datos.get(0).getFechaInicioVenta()));
        fechaFinER.setValue(LocalDate.parse(datos.get(0).getFechaFinVenta()));

        datosTecnicos = materialDAO.buscarDatosTecnicos(id);
        //System.out.println(olAux);
        //System.out.println(datosTecnicos);
        if(!datosTecnicos.isEmpty()){
            //System.out.println("a");
            pesoER.setText(String.valueOf(datosTecnicos.get(0).getPeso()));
            colorER.setText(datosTecnicos.get(0).getColor());
            herramientaUsoER.setText(datosTecnicos.get(0).getHerramientaUso());
            lugarUsoER.setText(datosTecnicos.get(0).getLugarUso());
            recipienteER.setText(datosTecnicos.get(0).getRecipiente());
            composicionER.setText(datosTecnicos.get(0).getComposicion());
        }else{

        }

    }

    /**
     Actualiza los campos de un material en la base de datos utilizando los valores ingresados en los campos de la interfaz gráfica.
     @throws NumberFormatException Si alguno de los valores numéricos ingresados no es un número válido.
     */
    public void actualizarCampos(){
        int id = m.getIdMaterial();

        String nombre = nombreER.getText();
        String fabricante = fabricanteER.getText();
        String material = materialER.getText();
        double precio;
        if (precioER.getText().isEmpty()){
            precio = 0.00;
        }else{
            precio = Double.parseDouble(precioER.getText());
        }
        String indicadorPeligro;
        if(indicadorPeligroER.getValue()==null){
            indicadorPeligro="";
        }else{
            indicadorPeligro = indicadorPeligroER.getValue().toString();
        }
        LocalDate fechaInicioValor = fechaInicioER.getValue();
        LocalDate fechaFinValor = fechaFinER.getValue();

        /*CharSequence fechaIni = fechaInicioValor.toString();
        CharSequence fechain = fechaFinValor.toString();*/

        Date fechaInicio = Date.from(fechaInicioValor.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date fechaFin = Date.from(fechaFinValor.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        double peso;
        if (pesoER.getText().isEmpty()){
            peso = 0.00;
        }else{
            peso = Double.parseDouble(pesoER.getText());
        }
        String color = colorER.getText();
        String herramientaUso = herramientaUsoER.getText();
        String lugarUso = lugarUsoER.getText();
        String recipiente = recipienteER.getText();
        String composicion = composicionER.getText();

        materialDAO.editarMaterial(id, nombre, fabricante, material, precio, indicadorPeligro, fechaInicio, fechaFin);

        if(!materialDAO.anadirNuevoDatosTecnicos(id, peso, color, herramientaUso, lugarUso, recipiente, composicion)){
            materialDAO.editarDatosTecnicos(id, peso, color, herramientaUso, lugarUso, recipiente, composicion);
        }

    }
    /**
     Método que permite borrar un registro de la base de datos.
     @return true si se borra el registro correctamente, false en caso contrario.
     */
    public boolean borrarRegistro(){

        int id = m.getIdMaterial();
        if(materialDAO.borrarRegistro(id, colorER.getText())){
            return true;
        }else{
            return false;
        }

    }

}