package com.example.materialesdb;

import com.example.materialesdb.dao.MaterialDAO;
import com.example.materialesdb.modelos.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ControllerInicio {

    private MaterialDAO materialDAO = new MaterialDAO();
    private Material materialAux;
    Material m = Material.getInstance();
    private Date fechaInicio;
    private Date fechaFin;
    private ObservableList<Material> datos;
    @FXML
    private Label botonVolverInicio;
    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputFabricante;
    @FXML
    private TextField inputMaterial;
    @FXML
    private TextField inputPrecio;
    @FXML
    private ComboBox cbPeligro;
    @FXML
    private DatePicker fechaInicioVenta;
    @FXML
    private DatePicker fechaFinVenta;
    @FXML
    private Button botonBuscar;
    @FXML
    private Button cabeceraNuevoRegistro;
    @FXML
    private TableView tvMateriales;
    @FXML
    private TableColumn tcIDMaterial;
    @FXML
    private TableColumn tcNombreMaterial;
    @FXML
    private TableColumn tcFabricante;
    @FXML
    private TableColumn tcMaterial;
    @FXML
    private TableColumn tcPrecio;
    @FXML
    private TableColumn tcIndicadorPeligro;
    @FXML
    private TableColumn tcFechaInicioVenta;
    @FXML
    private TableColumn tcFechaFinVenta;
    @FXML
    private Button reporteSimple;
    @FXML
    private Button subReporte;

    @FXML
    protected void initialize(){

        cargarDatosTabla();

        ObservableList<String> olOptions = FXCollections.observableArrayList(
                "A",
                "B",
                "C",
                "D",
                "E"
        );

        cbPeligro.setItems(olOptions);

        cargarDobleClickEdicion();

        botonBuscar.setOnMouseClicked(event->{
            if(inputNombre.getText().isEmpty() && inputFabricante.getText().isEmpty() && inputMaterial.getText().isEmpty() && inputPrecio.getText().isEmpty() && cbPeligro.getValue()==null && fechaInicioVenta.getValue()==null && fechaFinVenta.getValue()==null){
                cargarDatosTabla();
            }else{
                cargarDatosConsultaTabla();
            }
        });

        reporteSimple.setOnMouseClicked(event->{

        });

        cabeceraNuevoRegistro.setOnMouseClicked(new EventHandler(){
            @Override
            public void handle(Event event) {
                try {
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("viewNuevoRegistro.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                    stage.setTitle("Nuevo Registro");
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

        fechaInicioVenta.setOnAction(event -> {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            fechaInicio = Date.from(fechaInicioVenta.getValue().atStartOfDay(defaultZoneId).toInstant());
            //System.out.println(fechaInicio);
        });

        fechaFinVenta.setOnAction(event -> {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            fechaFin = Date.from(fechaFinVenta.getValue().atStartOfDay(defaultZoneId).toInstant());
            //System.out.println(fechaFin);
        });

    }

    private void cargarDatosTabla(){
        datos = materialDAO.obtenerMaterialesTotal();

        tcIDMaterial.setCellValueFactory(new PropertyValueFactory<Material, Integer>("idMaterial"));
        tcNombreMaterial.setCellValueFactory(new PropertyValueFactory<Material, String>("nombreMaterial"));
        tcFabricante.setCellValueFactory(new PropertyValueFactory<Material, String>("fabricante"));
        tcMaterial.setCellValueFactory(new PropertyValueFactory<Material, String>("material"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory<Material, Double>("precio"));
        tcIndicadorPeligro.setCellValueFactory(new PropertyValueFactory<Material, String>("indicadorPeligro"));
        tcFechaInicioVenta.setCellValueFactory(new PropertyValueFactory<Material, String>("fechaInicioVenta"));
        tcFechaFinVenta.setCellValueFactory(new PropertyValueFactory<Material, String>("fechaFinVenta"));
        tvMateriales.setItems(datos);

    }
    public void cargarDatosConsultaTabla(){

        String nombre;
        if(inputNombre.getText().isEmpty()){
            nombre = "";
        }else{
            nombre = inputNombre.getText();
        }
        String fabricante;
        if(inputFabricante.getText().isEmpty()){
            fabricante = "";
        }else{
            fabricante = inputFabricante.getText();
        }
        String material;
        if(inputMaterial.getText().isEmpty()){
            material = "";
        }else{
            material = inputMaterial.getText();
        }
        double precio;
        if (!inputPrecio.getText().isEmpty()) {
            precio = Double.parseDouble(inputPrecio.getText());
        }else{
            precio=3.21;
        }
        String indicadorPeligro;
        if(cbPeligro.getValue()==null){
            indicadorPeligro="";
        }else{
            indicadorPeligro = cbPeligro.getValue().toString();
        }
        /*LocalDate fechaInicioValor = fechaInicioVenta.getValue();
        LocalDate fechaFinValor = fechaFinVenta.getValue();
        if(fechaInicioValor == null){
            CharSequence cs = "2000/1/1";
            fechaInicioValor = LocalDate.parse(cs,DateTimeFormatter.ISO_LOCAL_DATE);
        }else{


        }
        if(fechaFinValor == null){
            CharSequence cs = "2000/1/1";
            fechaFinValor = LocalDate.parse(cs, DateTimeFormatter.ISO_LOCAL_DATE);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        CharSequence fechaIni = fechaInicioValor.toString();
        CharSequence fechain = fechaFinValor.toString();

        Date fechaInicio = (Date) dtf.parse(fechaIni);
        Date fechaFin = (Date) dtf.parse(fechain);*/

        datos = materialDAO.buscarMateriales(nombre, fabricante, material, precio, indicadorPeligro, fechaInicio, fechaFin);

        tcIDMaterial.setCellValueFactory(new PropertyValueFactory<Material, Integer>("idMaterial"));
        tcNombreMaterial.setCellValueFactory(new PropertyValueFactory<Material, String>("nombreMaterial"));
        tcFabricante.setCellValueFactory(new PropertyValueFactory<Material, String>("fabricante"));
        tcMaterial.setCellValueFactory(new PropertyValueFactory<Material, String>("material"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory<Material, Double>("precio"));
        tcIndicadorPeligro.setCellValueFactory(new PropertyValueFactory<Material, String>("indicadorPeligro"));
        tcFechaInicioVenta.setCellValueFactory(new PropertyValueFactory<Material, String>("fechaInicioVenta"));
        tcFechaFinVenta.setCellValueFactory(new PropertyValueFactory<Material, String>("fechaFinVenta"));
        tvMateriales.setItems(datos);

    }

    private void cargarDobleClickEdicion(){
        tvMateriales.setRowFactory(tv->{
            TableRow<Material> row = new TableRow<>();
            row.setOnMouseClicked(event->{
                if(event.getClickCount()==2 && (!row.isEmpty())){
                    try {
                        m.setIdMaterial(row.getItem().getIdMaterial());
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("viewEditarRegistro.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                        stage.setTitle("Editar");
                        stage.setMinWidth(640);
                        stage.setMinHeight(500);
                        stage.setScene(scene);
                        stage.show();
                        Node node = (Node) event.getSource();
                        Stage stageActual = (Stage) node.getScene().getWindow();
                        stageActual.close();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
            return row;
        });
    }

}