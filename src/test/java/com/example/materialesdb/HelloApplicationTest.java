package com.example.materialesdb;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.ComboBoxMatchers;
import org.testfx.matcher.control.TableViewMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class HelloApplicationTest {
    Pane mainroot;
    Stage mainStage;

    @Start
    public void start(Stage stage) throws IOException {
        mainroot = (Pane) FXMLLoader.load(getClass().getResource("viewInicio.fxml"));
        mainStage = stage;
        stage.setScene(new Scene(mainroot));
        stage.show();
        stage.toFront();
    }

    @Test
    public void validarEscribirEnCampoTexto(FxRobot robot) throws InterruptedException {
        robot.clickOn("#btnNuevo");
        robot.sleep(500);

        robot.clickOn("#tfNombre");
        robot.write("Miguel");
        FxAssert.verifyThat("#tfNombre", TextInputControlMatchers.hasText("Miguel"));

        robot.clickOn("#tfFabricante");
        robot.write("YesoCo");
        FxAssert.verifyThat("#tfFabricante", TextInputControlMatchers.hasText("YesoCo"));

        robot.clickOn("#tfMaterial");
        robot.write("Yeso");
        FxAssert.verifyThat("#tfMaterial", TextInputControlMatchers.hasText("Yeso"));

        robot.clickOn("#tfPrecio");
        robot.write("5.67");
        FxAssert.verifyThat("#tfPrecio", TextInputControlMatchers.hasText("5.67"));

        robot.clickOn("#tfIndicador");
        robot.clickOn("D");
        FxAssert.verifyThat("#tfIndicador", ComboBoxMatchers.hasSelectedItem("D"));

        robot.clickOn("#tfInicioVenta");
        robot.write("9/1/2023");

        //FxAssert.verifyThat("#huevo", TextInputControlMatchers.hasText("9/1/2023"));

        robot.clickOn("#tfFinVenta");
        robot.write("23/3/2023");
        //FxAssert.verifyThat("#tfInicioVenta", TextInputControlMatchers.hasText("23/3/2023"));

        robot.clickOn("#btnAnadirNuevoMaterial");

        robot.sleep(500);
        FxAssert.verifyThat("#tvMateriales", TableViewMatchers.containsRow(20, "Miguel", "YesoCo", "Yeso", 5.67, "D", "1970-01-01", "1970-01-01"));

        robot.sleep(500);

        robot.clickOn("#btnNuevo");
        robot.sleep(500);

        /*robot.clickOn("#tfNombre");
        robot.write("Claudiu");
        FxAssert.verifyThat("#tfNombre", TextInputControlMatchers.hasText("Claudiu"));


         */
        robot.clickOn("#tfFabricante");
        robot.write("Benz");
        //FxAssert.verifyThat("#tfFabricante", TextInputControlMatchers.hasText("Benz"));

        robot.clickOn("#tfMaterial");
        robot.write("Pladur");
        //FxAssert.verifyThat("#tfMaterial", TextInputControlMatchers.hasText("Pladur"));

        robot.clickOn("#tfPrecio");
        robot.write("5.20");
        //FxAssert.verifyThat("#tfPrecio", TextInputControlMatchers.hasText("5.20"));

        robot.clickOn("#tfIndicador");
        robot.clickOn("A");
        //FxAssert.verifyThat("#tfIndicador", ComboBoxMatchers.hasSelectedItem("A"));

        robot.clickOn("#tfInicioVenta");
        robot.write("8/10/2023");

        //FxAssert.verifyThat("#huevo", TextInputControlMatchers.hasText("9/1/2023"));

        robot.clickOn("#tfFinVenta");
        robot.write("21/11/2023");
        //FxAssert.verifyThat("#tfInicioVenta", TextInputControlMatchers.hasText("23/3/2023"));

        robot.clickOn("#btnAnadirNuevoMaterial");

    }

}