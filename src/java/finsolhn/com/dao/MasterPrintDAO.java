/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.dao;

import finsolhn.com.util.Constantes;
import java.io.File;
import java.util.Map;


import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;



/**
 *
 * @author rcardona
 */
public class MasterPrintDAO extends DAO3 {
    
     //Conexion Oracle
    private MetodosSQL2 m = new MetodosSQL2();
    
    
    public void generarPDF(String nombre, Map parameter,String p)
    {
       // Connection conexion;
       // Class.forName("com.mysql.jdbc.Driver").newInstance();
       // conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdreporte", "root", "root");
        
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String rutaFinal = servletContext.getRealPath(Constantes.ruta_jasper+nombre+".jasper");
        String ruta_logo = servletContext.getRealPath(Constantes.ruta_logo)+"/";
        String ruta_subjasper = servletContext.getRealPath(Constantes.ruta_jasper);
        parameter.put("logo", ruta_logo);
        //parameter.put("SUBREPORT_DIR", ruta_subjasper+"/");
        
              
        
        System.out.println("rFinal"+rutaFinal);
        //Se definen los parametros si es que el reporte necesita
         this.Conectar();
        
        try {
           // System.out.println("RUTAFINAL:"+rutaFinal);
            File file = new File(rutaFinal);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            //httpServletResponse.setContentType("application/pdf");
            //httpServletResponse.addHeader("Content-Type", "application/pdf");

            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, this.getCon());

            JRExporter jrExporter = null;                      
            jrExporter = new JRPdfExporter();
            jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpServletResponse.getOutputStream());

            if (jrExporter != null) {
                try {
                    jrExporter.exportReport();
                } catch (JRException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
              System.out.println("err print "+e);
            e.printStackTrace();
        } finally {
             this.Cerrar();
             //System.out.println("fin print");
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    
    //Funcion : Iniciazila los valores en el reporte de encuesta
    public void generarPDFEncuesta(String nombre, Map parameter)
    {
        System.out.println(nombre);
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        
        //Seteo de variable con la ruta de los reportes de encuesta
        String rutaFinal = servletContext.getRealPath(Constantes.ruta_jasper_encuesta+nombre+".jasper");
        String ruta_logo = servletContext.getRealPath(Constantes.ruta_logo)+"/";
        
        //Agregamos la ruta del logo
        parameter.put("logo", ruta_logo);
        
        System.out.println("rFinal"+rutaFinal);
        
        //Conectamos
         m.Conectar();       
        try {
           // System.out.println("RUTAFINAL:"+rutaFinal);
            File file = new File(rutaFinal);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, m.getCon());

            JRExporter jrExporter = null;                      
            jrExporter = new JRPdfExporter();
            jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpServletResponse.getOutputStream());

            if (jrExporter != null) {
                try {
                    jrExporter.exportReport();
                } catch (JRException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
              System.out.println("err print "+e);
            e.printStackTrace();
        } finally {
             m.Cerrar();
             //System.out.println("fin print");
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
    
     public void generarPDF2(String rutaCompleta, Map parameter,String p)
    {
       // Connection conexion;
       // Class.forName("com.mysql.jdbc.Driver").newInstance();
       // conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdreporte", "root", "root");      
        String rutaFinal = rutaCompleta;

        //Se definen los parametros si es que el reporte necesita
        this.Conectar();
        try {
            System.out.println("RUTAFINAL:"+rutaFinal);
            File file = new File(rutaFinal);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.addHeader("Content-Type", "application/pdf");

            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, this.getCon());

            JRExporter jrExporter = null;                      
            jrExporter = new JRPdfExporter();
            jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpServletResponse.getOutputStream());

            if (jrExporter != null) {
                try {
                    jrExporter.exportReport();
                } catch (JRException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
              System.out.println("err print "+e);
            e.printStackTrace();
        } finally {
             this.Cerrar();
             //System.out.println("fin print");
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public void generarPDFEQUIFAX(String nombre)
    {
        
        String pdfName = "Equifax.pdf";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String mPath = servletContext.getRealPath(Constantes.ruta_jasper+""+pdfName+".jasper");


        System.out.println("Ruta:"+mPath);
        
        
    }
}
