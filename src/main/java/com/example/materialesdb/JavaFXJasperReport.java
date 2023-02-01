package com.example.materialesdb;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class JavaFXJasperReport {

    private static final long serialVersionUID = 1L;

    public void showReportSimple() throws JRException, ClassNotFoundException, SQLException {
        String servidor = "jdbc:mariadb://localhost:5555/noinch?useSSL=false";
        String usuario = "adminer";
        String passwd = "adminer";

        Connection conexionBBDD;
        // Nos conectamos
        conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);

        //  Block of code to try
        String reportSrcFile = "src/main/java/com/example/javafxjasperreport/Clientes.jrxml";

        // First, compile jrxml file.
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

        HashMap<String, Object> parameters = new HashMap<String, Object>();
    }

}
