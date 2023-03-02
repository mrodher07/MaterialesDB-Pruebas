package com.example.materialesdb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
public class JavaFXJasperReport extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     *
     * Funcion que devuelve y muestra el reporte simple que hemos creado
     *
     * @throws JRException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void showReportSimple() throws JRException, ClassNotFoundException, SQLException {
        String servidor = "jdbc:mariadb://localhost:5555/noinch?useSSL=false";
        String usuario = "adminer";
        String passwd = "adminer";

        Connection conexionBBDD;
        // Nos conectamos
        conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);

        //  Block of code to try
        String reportSrcFile = "src/main/resources/com/example/materialesdb/jasperreport/informeMateriales.jrxml";

        // First, compile jrxml file.
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("company", "MAROTHIA TECHS");
        parameters.put("receipt_no", "RE101".toString());

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conexionBBDD);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
        System.out.print("Done!");
    }

    /**
     * Funcion que muestra el reporte con un subreporte que hemos creado
     * @param fabricante
     * @throws JRException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void showReportConSubreport(String fabricante) throws JRException, ClassNotFoundException, SQLException {

        String servidor = "jdbc:mariadb://localhost:5555/noinch?useSSL=false";
        String usuario = "adminer";
        String passwd = "adminer";

        Connection conexionBBDD;
        // Nos conectamos
        conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);

        //  Block of code to try
        String reportSrcFile = "src/main/resources/com/example/materialesdb/jasperreport/MaterialesDBMaestro.jrxml";
        String subReportSrcFile = "src/main/resources/com/example/materialesdb/jasperreport/MaterialesDBDetalles.jrxml";

        // First, compile jrxml file.
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        JasperReport jasperSubReport = JasperCompileManager.compileReport(subReportSrcFile);


        // Debemos pasar el subreport como un parámetro para que se muestre correctamente el informe
        // El parámetro debe estar definido como del tipo net.sf.jasperreports.engine.JasperReport

        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("fabricante", fabricante);
        parameters.put("Subreport", jasperSubReport);

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conexionBBDD);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
        System.out.print("Done!");

    }
}
