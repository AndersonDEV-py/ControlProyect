/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.model;

import finsolhn.com.util.Constantes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.file.DefaultStreamedContent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author ds010106
 */
public class Archivo {
    
    
    private int idArchivo;
    private String idReq;
    private String nombre_archivo;
    private String tipo_archivo;
    private String tipo_proceso;
    private String comentario;
    private String comentario_nuevo;
    private String fecha_pide;
    private String fecha_envia;
    private String fecha_sube;
    private String fecha_completado;
    
    
    private String ruta_raiz;
    private String ruta_folder;
    public UploadedFile file;
    public UploadedFile file2;
    public UploadedFile file3;
    public UploadedFile file4;
    public UploadedFile file_master;
    private int estado;
    private boolean existe;

    private String c_usuario_sol;
    private String d_usuario_sol;
    private String c_usuario_procesa;
    private String d_usuario_procesa;

    
    private String archivo3;//nuevo1
    private String archivo4;//nuevo2
    private String archivo1;//anterior1
    private String archivo2;//anterior2
    
    private boolean visibleBtIndicador1;//archivo nuevo#1
    private boolean visibleBtIndicador2;//archivo nuevo#2
    private boolean visibleBtIndicador3;//archivo anterior#1
    private boolean visibleBtIndicador4;//archivo anterior#2
    
    private String colorBack;
    
    private static String gris="#9E9DAB";
    private static String amarillo="#F7DF31";
    private static String azul="#06F2F9";
    private static String verde="#AEF77F";
    private static String rojo="#FC5342";
    
    public String getColorBack() {
        
        if(estado==1 && tipo_proceso.equalsIgnoreCase("DESCARGA"))
        {
            colorBack=gris;
        }else if(estado==1 && tipo_proceso.equalsIgnoreCase("CARGA_NUEVO"))
        {
            colorBack=amarillo;
        }else if(estado==2 && tipo_proceso.equalsIgnoreCase("DESCARGA"))
        {
            colorBack=azul;
        }else if(estado==2 && tipo_proceso.equalsIgnoreCase("CARGA_MODIFICADO"))
        {
           colorBack=amarillo; 
        }else if(estado==3)
        {
            colorBack=verde;
                    
        }else if(estado==4)
        {
            colorBack=rojo;
        }

        
        return colorBack;
    }

   

    public void setColorBack(String colorBack) {
        this.colorBack = colorBack;
    }
    
    
    
    
    private DefaultStreamedContent download;
    
    public Archivo()
    {
        
    }

    public String getComentario_nuevo() {
        return comentario_nuevo;
    }

    public void setComentario_nuevo(String comentario_nuevo) {
        this.comentario_nuevo = comentario_nuevo;
    }
    
    public String getArchivo1() {
        return archivo1;
    }

    public void setArchivo1(String archivo1) {
        this.archivo1 = archivo1;
    }

    public String getArchivo2() {
        return archivo2;
    }

    public void setArchivo2(String archivo2) {
        this.archivo2 = archivo2;
    }
    
    
    
    public boolean isVisibleBtIndicador1() {
        return visibleBtIndicador1;
    }

    public void setVisibleBtIndicador1(boolean visibleBtIndicador1) {
        this.visibleBtIndicador1 = visibleBtIndicador1;
    }

    public boolean isVisibleBtIndicador2() {
        return visibleBtIndicador2;
    }

    public void setVisibleBtIndicador2(boolean visibleBtIndicador2) {
        this.visibleBtIndicador2 = visibleBtIndicador2;
    }

    public boolean isVisibleBtIndicador3() {
        return visibleBtIndicador3;
    }

    public void setVisibleBtIndicador3(boolean visibleBtIndicador3) {
        this.visibleBtIndicador3 = visibleBtIndicador3;
    }

    public boolean isVisibleBtIndicador4() {
        return visibleBtIndicador4;
    }

    public void setVisibleBtIndicador4(boolean visibleBtIndicador4) {
        this.visibleBtIndicador4 = visibleBtIndicador4;
    }
    
    

    public String getArchivo3() {
        return archivo3;
    }

    public void setArchivo3(String archivo3) {
        this.archivo3 = archivo3;
    }

    public String getArchivo4() {
        return archivo4;
    }

    public void setArchivo4(String archivo4) {
        this.archivo4 = archivo4;
    }

    
    public String getFecha_pide() {
        return fecha_pide;
    }

    public void setFecha_pide(String fecha_pide) {
        this.fecha_pide = fecha_pide;
    }

    public String getFecha_envia() {
        return fecha_envia;
    }

    public void setFecha_envia(String fecha_envia) {
        this.fecha_envia = fecha_envia;
    }

    public String getFecha_sube() {
        return fecha_sube;
    }

    public void setFecha_sube(String fecha_sube) {
        this.fecha_sube = fecha_sube;
    }

    public String getFecha_completado() {
        return fecha_completado;
    }

    public void setFecha_completado(String fecha_completado) {
        this.fecha_completado = fecha_completado;
    }

    

    public UploadedFile getFile3() {
        return file3;
    }

    public void setFile3(UploadedFile file3) {
        this.file3 = file3;
    }

    public UploadedFile getFile4() {
        return file4;
    }

    public void setFile4(UploadedFile file4) {
        this.file4 = file4;
    }
    
    

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public Archivo(int id,String nombre)
    {
        this.idArchivo=id;
        this.nombre_archivo=nombre;
    }
    
    public String getTipo_archivo() {
        return tipo_archivo;
    }

    public void setTipo_archivo(String tipo_archivo) {
        this.tipo_archivo = tipo_archivo;
    }

    public String getTipo_proceso() {
        return tipo_proceso;
    }

    public void setTipo_proceso(String tipo_proceso) {
        this.tipo_proceso = tipo_proceso;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    
    
    
    public String getC_usuario_sol() {
        return c_usuario_sol;
    }

    public void setC_usuario_sol(String c_usuario_sol) {
        this.c_usuario_sol = c_usuario_sol;
    }

    public String getD_usuario_sol() {
        return d_usuario_sol;
    }

    public void setD_usuario_sol(String d_usuario_sol) {
        this.d_usuario_sol = d_usuario_sol;
    }

    public String getC_usuario_procesa() {
        return c_usuario_procesa;
    }

    public void setC_usuario_procesa(String c_usuario_procesa) {
        this.c_usuario_procesa = c_usuario_procesa;
    }

    public String getD_usuario_procesa() {
        return d_usuario_procesa;
    }

    public void setD_usuario_procesa(String d_usuario_procesa) {
        this.d_usuario_procesa = d_usuario_procesa;
    }
    
    
    
    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getIdReq() {
        return idReq;
    }

    public void setIdReq(String idReq) {
        this.idReq = idReq;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public String getRuta_raiz() {
        return ruta_raiz;
    }

    public void setRuta_raiz(String ruta_raiz) {
        this.ruta_raiz = ruta_raiz;
    }

    public String getRuta_folder() {
        return ruta_folder;
    }

    public void setRuta_folder(String ruta_folder) {
        this.ruta_folder = ruta_folder;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getFile2() {
        return file2;
    }

    public void setFile2(UploadedFile file2) {
        this.file2 = file2;
    }

    
    
    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    public void setDownload(DefaultStreamedContent download) {
           this.download = download;
       }

    public DefaultStreamedContent getDownload() throws Exception {
        return download;
    }
    public void prepDownload(/*Archivo obj,*/String extension,String tipo) throws Exception {
        String folder="";
      if(tipo.equalsIgnoreCase("D"))
          folder=Constantes.folder_despues;
      else
          folder=Constantes.folder_antes;
      
        
      //String name=obj.getRuta_raiz()+folder+obj.getNombre_archivo()+extension;
      String name=getRuta_raiz()+folder+getNombre_archivo()+extension;
      File file = new File(name);
      InputStream input = new FileInputStream(file);
      ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
     
      //DefaultStreamedContent str=DefaultStreamedContent.builder().stream(input.).build();
      
      //setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
    
    }
    
    
}
