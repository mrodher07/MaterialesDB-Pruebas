package com.example.materialesdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    /**

     Inicia la aplicación y configura el escenario con el ancho y alto mínimo especificado,
     carga el archivo FXML "viewInicio.fxml" y agrega la hoja de estilo "misestilos.css" a la escena.
     El título del escenario se establece en "Inicio".
     @param stage El escenario principal de esta aplicación.
     @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("viewInicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 500);
        scene.getStylesheets().add(HelloApplication.class.getResource("css/misestilos.css").toExternalForm());
        stage.setTitle("Inicio");
        stage.setMinWidth(640);
        stage.setMinHeight(500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}