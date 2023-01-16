package com.example.materialesdb.Dao;

import com.example.materialesdb.dao.MaterialDAO;
import com.example.materialesdb.modelos.Material;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MaterialDAOTest {

    MaterialDAO materialDAO = new MaterialDAO();
    Material m = new Material(1, "Miguel", "YesoCo", "Yeso", 5.67, "D", "1970-01-01", "1970-01-01");

    /*@Test
    void insertarRegistro(){
        Date fechaInicio = new Date(1970, 01, 01);
        Date fechaFin = new Date(1970, 01, 01);
        assertEquals(true, materialDAO.anadirNuevoRegistro("Miguel", "YesoCo", "Yeso", 5.67, "D", fechaInicio, fechaFin));

    }*/
    /*@Test
    void validarRegistro(){
        Date fechaInicio = new Date(1970, 01, 01);
        Date fechaFin = new Date(1970, 01, 01);
        assertEquals(1, materialDAO.obtenermaterial("Miguel", "YesoCo", "Yeso", 5.67, "D", fechaInicio, fechaFin));
    }*/

    @Test
    void borrarRegistro(){
        assertEquals(true, materialDAO.borrarRegistro(1, ""));
    }

}