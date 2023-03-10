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
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;
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

    /**

     Este m??todo es llamado al inicializar la ventana de la aplicaci??n. Se encarga de cargar los datos en la tabla,
     configurar las opciones del ComboBox, cargar la funcionalidad de doble click en la edici??n de registros,
     configurar los botones para generar los reportes y navegar a otras ventanas, y manejar los eventos de selecci??n
     de fecha en los DatePickers.
     @return void
     */
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
            try {
                JavaFXJasperReport jfx = new JavaFXJasperReport();
                jfx.showReportSimple();
            } catch (JRException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        subReporte.setOnMouseClicked(event->{

            try {
                JavaFXJasperReport jfx = new JavaFXJasperReport();
                jfx.showReportConSubreport("Fassa Bortolo");
            } catch (JRException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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

    /**

     Carga los datos de todos los materiales en la tabla de la interfaz gr??fica.
     Se encarga de obtener los datos de la base de datos mediante el objeto MaterialDAO y
     establece la correspondencia entre los atributos de la clase Material y las columnas
     de la tabla. Finalmente, establece los datos en la tabla de la interfaz gr??fica.
     */
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

    /**

     Carga los datos de la tabla con los materiales que coinciden con los criterios de b??squeda especificados.
     Los criterios de b??squeda son obtenidos de los campos de texto e ??tems seleccionados en la interfaz gr??fica.
     Si un campo est?? vac??o, se considera como criterio vac??o. Si el campo precio est?? vac??o, se asigna un valor predeterminado.
     Los datos son obtenidos mediante una consulta a la base de datos utilizando el objeto materialDAO.
     Los datos son asignados a la tabla y visualizados en la interfaz gr??fica.
     */
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
    /**
     Carga el evento de doble clic en una fila de la tabla para editar el registro seleccionado.
     */
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