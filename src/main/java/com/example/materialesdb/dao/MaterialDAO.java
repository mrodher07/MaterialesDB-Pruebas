package com.example.materialesdb.dao;

import com.example.materialesdb.modelos.Material;
import com.example.materialesdb.modelos.datosTecnicos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.Date;

public class MaterialDAO {

    private String SQL = "";
    private final String servidor = "jdbc:mariadb://localhost:5555/noinch?useSSL=false";
    private final String usuario = "adminer";
    private final String passwd = "adminer";

    private Connection conexionBBDD;

    public ObservableList<Material> obtenerMaterialesTotal(){

        ObservableList<Material> datosResultadoConsulta = FXCollections.observableArrayList();

        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "SELECT * "+
                    "FROM material "+
                    "ORDER By idMaterial";

            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

            while(resultadoConsulta.next()){
                datosResultadoConsulta.add(new Material(
                        resultadoConsulta.getInt("idMaterial"),
                        resultadoConsulta.getString("nombreMaterial"),
                        resultadoConsulta.getString("fabricante"),
                        resultadoConsulta.getString("material"),
                        resultadoConsulta.getDouble("precio"),
                        resultadoConsulta.getString("indicadorPeligro"),
                        resultadoConsulta.getString("fechaInicioVenta"),
                        resultadoConsulta.getString("fechaFinVenta")
                ));
                System.out.println("Row[1] added"+resultadoConsulta.toString());
            }
            conexionBBDD.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            return datosResultadoConsulta;
        }

    }

    public boolean anadirNuevoRegistro(String nombreMaterial, String fabricante, String material, Double precio, String indicadorPeligro, Date fechaInicio, Date fechaFin){
        int registrosAfectadosConsulta = 0;
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "INSERT INTO material ("+
                    "nombreMaterial, "+
                    "fabricante, "+
                    "material, "+
                    "precio, "+
                    "indicadorPeligro, "+
                    "fechaInicioVenta, "+
                    "fechaFinVenta)"+
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            java.sql.Date dateInicio = new java.sql.Date(fechaInicio.getDate());
            java.sql.Date dateFin = new java.sql.Date(fechaFin.getDate());

            PreparedStatement st = conexionBBDD.prepareStatement(SQL);
            st.setString(1, nombreMaterial);
            st.setString(2, fabricante);
            st.setString(3, material);
            st.setDouble(4, precio);
            st.setString(5, indicadorPeligro);
            st.setDate(6, dateInicio);
            st.setDate(7, dateFin);

            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta == 1){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean anadirNuevoDatosTecnicos(int id, double peso, String color, String herramientaUso, String lugarUso, String recipiente, String composicion){
        int registrosAfectadosConsulta = 0;
        try {

            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "INSERT INTO datostecnicos ("+
                    "idMaterial, "+
                    "Peso, "+
                    "Color, "+
                    "HerramientaUso, "+
                    "LugarUso, "+
                    "Recipiente, "+
                    "Composicion)"+
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = conexionBBDD.prepareStatement(SQL);
            st.setInt(1, id);
            st.setDouble(2, peso);
            st.setString(3, color);
            st.setString(4, herramientaUso);
            st.setString(5, lugarUso);
            st.setString(6, recipiente);
            st.setString(7, composicion);

            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta == 1){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean editarMaterial(int id, String nombreMaterial, String fabricante, String material, Double precio, String indicadorPeligro, Date fechaInicio, Date fechaFin){
        int registrosAfectadosConsulta = 0;
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            java.sql.Date dateInicio = new java.sql.Date(fechaInicio.getDate());
            java.sql.Date dateFin = new java.sql.Date(fechaFin.getDate());
            String SQL = "UPDATE material "+
                    "SET nombreMaterial='"+nombreMaterial+
                    "',fabricante='"+fabricante+
                    "',material='"+material+
                    "',precio="+precio+
                    ",indicadorPeligro='"+indicadorPeligro+
                    "',fechaInicioVenta='"+dateInicio+
                    "',fechaFinVenta='"+dateFin+
                    "' WHERE idMaterial="+id+";";



            PreparedStatement st = conexionBBDD.prepareStatement(SQL);
            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta == 1){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean editarDatosTecnicos(int id, Double peso, String color, String herramientaUso, String lugarUso, String recipiente, String composicion){
        int registrosAfectadosConsulta = 0;
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "UPDATE datostecnicos "+
                    "SET Peso="+peso+
                    ", Color='"+color+
                    "',HerramientaUso='"+herramientaUso+
                    "',LugarUso='"+lugarUso+
                    "',Recipiente='"+recipiente+
                    "',Composicion='"+composicion+
                    "' WHERE idMaterial="+id+";";

            PreparedStatement st = conexionBBDD.prepareStatement(SQL);

            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta == 1){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ObservableList<Material> buscarMateriales(String nombreMaterial, String fabricante, String material, Double precio, String indicadorPeligro, Date fechaInicio, Date fechaFin){
        ObservableList<Material> datosResultadoConsulta = FXCollections.observableArrayList();

        try {
            /*System.out.println(nombreMaterial);
            System.out.println(fabricante);
            System.out.println(material);
            System.out.println(precio);
            System.out.println(indicadorPeligro);
            System.out.println(fechaInicio);
            System.out.println(fechaFin);*/
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            /*java.sql.Date dateInicio = new java.sql.Date(fechaInicio.getTime());
            java.sql.Date dateFin = new java.sql.Date(fechaFin.getTime());*/
            //System.out.println(dateInicio);
            //System.out.println(dateFin);
            /*if(fechaInicio != null && fechaFin != null){
                String SQL = "SELECT * "+
                        "FROM material "+
                        "WHERE (nombreMaterial LIKE '%" +nombreMaterial+
                        "%' OR fabricante LIKE '%" +fabricante+
                        "%' OR material LIKE '%" +material+
                        "%' OR precio LIKE '%" +precio+
                        "%' OR indicadorPeligro LIKE '%" +indicadorPeligro+ "%');";
            } else if (fechaInicio == null && fechaFin != null) {
                
            }*/
            String SQL = "SELECT * "+
                    "FROM material "+
                    "WHERE nombreMaterial LIKE '%" +nombreMaterial+
                    "%' OR fabricante LIKE '%" +fabricante+
                    "%' OR material LIKE '%" +material+
                    "%' OR precio >= " +precio+
                    " OR indicadorPeligro LIKE '%" +indicadorPeligro+ "%';";

            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

            while(resultadoConsulta.next()){
                datosResultadoConsulta.add(new Material(
                        resultadoConsulta.getInt("idMaterial"),
                        resultadoConsulta.getString("nombreMaterial"),
                        resultadoConsulta.getString("fabricante"),
                        resultadoConsulta.getString("material"),
                        resultadoConsulta.getDouble("precio"),
                        resultadoConsulta.getString("indicadorPeligro"),
                        resultadoConsulta.getString("fechaInicioVenta"),
                        resultadoConsulta.getString("fechaFinVenta")
                ));
                System.out.println("Row[1] added"+resultadoConsulta.toString());
            }
            conexionBBDD.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            return datosResultadoConsulta;
        }
    }
    public ObservableList<datosTecnicos> buscarDatosTecnicos(int id){
        ObservableList<datosTecnicos> datosResultadoConsulta = FXCollections.observableArrayList();

        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "SELECT * "+
                    "FROM datostecnicos "+
                    "WHERE idMaterial="+id+";";

            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

            while(resultadoConsulta.next()){
                datosResultadoConsulta.add(new datosTecnicos(
                        resultadoConsulta.getInt("idMaterial"),
                        resultadoConsulta.getDouble("peso"),
                        resultadoConsulta.getString("color"),
                        resultadoConsulta.getString("HerramientaUso"),
                        resultadoConsulta.getString("LugarUso"),
                        resultadoConsulta.getString("Recipiente"),
                        resultadoConsulta.getString("Composicion")
                ));
                System.out.println("Row[1] added"+resultadoConsulta.toString());
            }
            conexionBBDD.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            return datosResultadoConsulta;
        }
    }

    public ObservableList<Material> buscarDatosMaterial(int id){
        ObservableList<Material> datosResultadoConsulta = FXCollections.observableArrayList();

        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "SELECT * "+
                    "FROM material "+
                    "WHERE idMaterial="+id+";";

            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

            while(resultadoConsulta.next()){
                datosResultadoConsulta.add(new Material(
                        resultadoConsulta.getInt("idMaterial"),
                        resultadoConsulta.getString("nombreMaterial"),
                        resultadoConsulta.getString("fabricante"),
                        resultadoConsulta.getString("material"),
                        resultadoConsulta.getDouble("precio"),
                        resultadoConsulta.getString("indicadorPeligro"),
                        resultadoConsulta.getString("fechaInicioVenta"),
                        resultadoConsulta.getString("fechaFinVenta")
                ));
                System.out.println("Row[1] added"+resultadoConsulta.toString());
            }
            conexionBBDD.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            return datosResultadoConsulta;
        }
    }

    public boolean borrarRegistro(int id, String color){
        int registrosAfectadosConsulta = 0;
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            System.out.println(color);
            if(!color.isEmpty()){
                SQL = "DELETE material.*, datostecnicos.* " +
                        "FROM material, datostecnicos " +
                        "WHERE material.idMaterial=datostecnicos.idMaterial " +
                        "AND material.idMaterial="+id+";";
            }else{
                SQL = "DELETE material.*" +
                        "FROM material " +
                        "WHERE material.idMaterial="+id+";";
            }

            PreparedStatement st = conexionBBDD.prepareStatement(SQL);
            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta >= 1){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int obtenermaterial (String nombreMaterial, String fabricante, String material, Double precio, String indicadorPeligro, Date fechaInicio, Date fechaFin){
        ObservableList<Material> datosResultadoConsulta = FXCollections.observableArrayList();

        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);

            String SQL = "SELECT * "+
                    "FROM material "+
                    "WHERE nombreMaterial LIKE '%" +nombreMaterial+
                    "%' OR fabricante LIKE '%" +fabricante+
                    "%' OR material LIKE '%" +material+
                    "%' OR precio >= " +precio+
                    " OR indicadorPeligro LIKE '%" +indicadorPeligro+ "%';";

            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

            while(resultadoConsulta.next()){
                datosResultadoConsulta.add(new Material(
                        resultadoConsulta.getInt("idMaterial"),
                        resultadoConsulta.getString("nombreMaterial"),
                        resultadoConsulta.getString("fabricante"),
                        resultadoConsulta.getString("material"),
                        resultadoConsulta.getDouble("precio"),
                        resultadoConsulta.getString("indicadorPeligro"),
                        resultadoConsulta.getString("fechaInicioVenta"),
                        resultadoConsulta.getString("fechaFinVenta")
                ));
                System.out.println("Row[1] added"+resultadoConsulta.toString());
            }
            conexionBBDD.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            return datosResultadoConsulta.size();
        }
    }
}
