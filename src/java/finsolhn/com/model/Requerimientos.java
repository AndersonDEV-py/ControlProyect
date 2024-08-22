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
import java.util.ArrayList;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.event.FacesEvent;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.DefaultStreamedContent;
/**
 *
 * @author ds010106
 */
public class Requerimientos implements Serializable{
    
    
    private int id;
    private String nombre_corto;
    private String fecha;//fecha creado por usuario
    private String fecha_autorizado;//fecha autorizado por jefe p
    private String fecha_asginado_dev; //fecha asignado a desarrollo
    private String fecha_completado; //fecha completado por desarrollo
    private String fecha_aceptado; //fecha aceptado por usuario
    private String fecha_finalizado; //fecha finalizado (jefe proyecto)
    private String fecha_rechazo; //fecha rechazo sol
    private String circular;
    private String prioridad;
    private String nuevo;
    private String c_agencia;
    private String d_agencia;
    private String c_usuario;//usuario solicita
    private String d_usuario;
    private String descripcion;
    private String ruta;
    //private String tipo_reg;
    private String[] aplicativo;
    private String estado;
    private String especifique_otro;
    
    private String fecha_autorizo_area;
    private String c_usuario_area;
    private String d_usuario_area;
    private String c_usuario_jefe;
    private String d_usuario_jefe;
    private String c_area;
    private String d_area;
    private String[] tipo_comando;
    private String ambiente;
    private int cant_reg_afec;
    private String tipo_objeto;
    private String nombre_objeto;///posible master detail
    private String c_developer; // posible master detail
    private String d_developer;
    private String c_dba;
    private String d_dba;
    private String fecha_inicio_permiso;
    private String fecha_fin_permiso;
    
    
    private String id_req_padre;
    private String tipo_registro;
    private String d_tipo_registro;
    private List<MyFile> lstDocument=new ArrayList();
    private MyFile selectMyFile;
    
    ////nuevos
    private String tarea;
   // private String tipo_archivo;
    private String objetoTarea;
    //private String des_usuarios_utilizan;
    private List<String> des_usuarios_utilizan;
    //private String[] des_usuarios_utilizan;
    private String[] actividades_usuarios;
    private String comentario;
    private String notas;
   // private boolean visibleBtIndicador1;//archivo anterior#1
   // private boolean visibleBtIndicador2;//archivo nuevo#1
    private String scriptSQL;
    private String scriptSQLAnterior;
    
    private TablaSQL tbl;
    private List<TablaSQL> lstTabla;
    
    
    private UploadedFile  file;
    //private UploadedFiles files;
    
    private DefaultStreamedContent download;
    
    
    
    ///////////colores
    private String colorBack_Est;//fondo estructuras
    private String colorBack_R; //fondo estado req y casos
    private String colorBack_T; //fondo por tipo Caso o Estr
    private String colorBack_P; //fondo para permiso obj
    
    private static String gris="#9E9DAB";
    private static String amarillo="#F7DF31";
    private static String azul="#06F2F9";
    private static String verde="#AEF77F";
    private static String rojo="#FC5342";

    private static String naranja="#F8AC30";
    private static String naranja0="#F4C477";
    private static String azul2="#00B6BC";
    private static String morado="#D098F7";
    private static String morado0="#E4C7F8";
    private static String verde2="#7FCA4F";

    public String getColorBack_P() {
        
        if(estado.equalsIgnoreCase(Constantes.estado_autorizado_ti) || estado.equalsIgnoreCase(Constantes.estado_en_dba))
        {
            colorBack_P=morado;
        }else  if(estado.equalsIgnoreCase(Constantes.estado_ini_permiso))
        {
            colorBack_P=amarillo;
        }else if(estado.equalsIgnoreCase(Constantes.estado_fin_permiso))
        {
            colorBack_P=verde;
        }
        
        
        return colorBack_P;
    }

    public void setColorBack_P(String colorBack_P) {
        this.colorBack_P = colorBack_P;
    }
    
    
    
    
    public String getColorBack_T() {
        
        if(tipo_registro.equalsIgnoreCase("C"))
        {
            colorBack_T=azul;
        }else if(tipo_registro.equalsIgnoreCase("A"))
        {
            colorBack_T=naranja;
        }
        else if(tipo_registro.equalsIgnoreCase("B"))
        {
            colorBack_T=naranja0;
        }else if(tipo_registro.equalsIgnoreCase("R")){
            colorBack_T=azul2;
        }else if(tipo_registro.equalsIgnoreCase("E"))
        {
            colorBack_T=morado;
        }else if(tipo_registro.equalsIgnoreCase("P"))
        {
            colorBack_T=morado0;
            if(estado.equalsIgnoreCase(Constantes.estado_ini_permiso))
            {
                colorBack_T=amarillo;
            }else if(estado.equalsIgnoreCase(Constantes.estado_completado_dba))
            {
                colorBack_T=gris;
            }
            
            
        }
        
        
        
        return colorBack_T;
    }

    public void setColorBack_T(String colorBack_T) {
        this.colorBack_T = colorBack_T;
    }
    
    
    
    
    
    public String getColorBack_R() {
        
        if(estado.equalsIgnoreCase(Constantes.estado_en_espera))
        {
            colorBack_R=naranja;
        }else if(estado.equalsIgnoreCase(Constantes.estado_completado))
        {
            colorBack_R=azul2;
        }else if(estado.equalsIgnoreCase(Constantes.estado_en_desarrollo))
        {
            colorBack_R=azul;
        }else if(estado.equalsIgnoreCase(Constantes.estado_sin_autortizar))
        {
            colorBack_R=naranja;
        }else if(estado.equalsIgnoreCase(Constantes.estado_sin_autortizar_area))
        {
            colorBack_R=naranja0;
        }else if(estado.equalsIgnoreCase(Constantes.estado_rechazado))
        {
            colorBack_R=rojo;
        }else if(estado.equalsIgnoreCase(Constantes.estado_aceptado))
        {
            colorBack_R=verde;
        }else if(estado.equalsIgnoreCase(Constantes.estado_finalizado))
        {
            colorBack_R=verde2;
        }else if(estado.equalsIgnoreCase(Constantes.estado_en_dba))
        {
            colorBack_R=gris;
        }
        
        
        return colorBack_R;
    }

    public void setColorBack_R(String colorBack_R) {
        this.colorBack_R = colorBack_R;
    }
    
    
    
    
    
    public String getColorBack_Est() {
        
        if( estado.equalsIgnoreCase(Constantes.estado_en_dba) 
                && tipo_registro.equalsIgnoreCase("B"))
        {
            colorBack_Est=gris;
        }else if(estado.equalsIgnoreCase(Constantes.estado_completado_dba)==false 
                && tipo_registro.equalsIgnoreCase("A"))
        {
            colorBack_Est=amarillo;
        }else if(estado.equalsIgnoreCase(Constantes.estado_completado_dba) 
                && tipo_registro.equalsIgnoreCase("U"))
        {
            colorBack_Est=azul;
        }/*else if(estado==2 && tipo_proceso.equalsIgnoreCase("CARGA_MODIFICADO"))
        {
           colorBack=amarillo; 
        }*/else if(estado.equalsIgnoreCase(Constantes.estado_completado_dba) 
                && tipo_registro.equalsIgnoreCase("A") )
        {
            colorBack_Est=verde;
                    
        }else if(estado.equalsIgnoreCase(Constantes.estado_rechazado))
        {
            colorBack_Est=rojo;
        }

        
        return colorBack_Est;
    }

   

    public void setColorBack_Est(String colorBack_Est) {
        this.colorBack_Est = colorBack_Est;
    }
    
    
    
    
    
    public UploadedFile  getFile() {
        return file;
    }

    public String getD_tipo_registro() {
        
        if(tipo_registro.equalsIgnoreCase("R"))
            d_tipo_registro="Requerimiento";
        else if(tipo_registro.equalsIgnoreCase("C"))
            d_tipo_registro="Caso";
        else if(tipo_registro.equalsIgnoreCase("E"))
            d_tipo_registro="Ejecucion Script";
        else if(tipo_registro.equalsIgnoreCase("P"))
            d_tipo_registro="Permiso sobre objeto";
        else if(tipo_registro.equalsIgnoreCase("A") || tipo_registro.equalsIgnoreCase("U"))
            d_tipo_registro="Cambio en Estructura BD.";
        
        
        return d_tipo_registro;
    }

    public List<String> getDes_usuarios_utilizan() {
        return des_usuarios_utilizan;
    }

    public void setDes_usuarios_utilizan(List<String> des_usuarios_utilizan) {
        this.des_usuarios_utilizan = des_usuarios_utilizan;
    }

    
    public String getScriptSQLAnterior() {
        return scriptSQLAnterior;
    }

    public void setScriptSQLAnterior(String scriptSQLAnterior) {
        this.scriptSQLAnterior = scriptSQLAnterior;
    }

    
    
    public String getScriptSQL() {
        return scriptSQL;
    }

    public void setScriptSQL(String scriptSQL) {
        this.scriptSQL = scriptSQL;
    }
    
    

    public TablaSQL getTbl() {
        return tbl;
    }

    public void setTbl(TablaSQL tbl) {
        this.tbl = tbl;
    }

    public List<TablaSQL> getLstTabla() {
        return lstTabla;
    }

    public void setLstTabla(List<TablaSQL> lstTabla) {
        this.lstTabla = lstTabla;
    }


    public void setD_tipo_registro(String d_tipo_registro) {
        this.d_tipo_registro = d_tipo_registro;
    }
    
    

    public void setFile(UploadedFile  file) {
        this.file = file;
    }
    
    public void setDownload(DefaultStreamedContent download) {
           this.download = download;
    }

    public DefaultStreamedContent getDownload() throws Exception {
        return download;
    }
    
    

    public void prepDownload(MyFile f) throws Exception {
        
      String date=f.getRuta_raiz()+f.getRuta_folder()+f.getNombre();
      File file = new File(date);
      InputStream input = new FileInputStream(file);
      ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      //buscar remplazo / deprecate
      //setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
    
    }

    public void quitarDoc(MyFile f)
    {
       selectMyFile=f;
       
        for (int i = 0; i < lstDocument.size(); i++) {
            
            if(lstDocument.get(i).equals(f))
            {
                lstDocument.get(i).setEstado(0);
                System.out.println("Estado actualizado "+lstDocument.get(i).getEstado());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Se elminara al guardar"));
            }
            
        }
       
              
    }
    
    
    public void handleFileUpload(FileUploadEvent event) throws FileNotFoundException, IOException {
        
         /*file = event.getFile();*/ /*Comentado ´por Kevin Anderson*/
         if (file != null) {
         
            String raiz=Constantes.ruta_raiz_doc;
            String folder="";
            String nombreFinal=(int)Math.floor(Math.random()*10000+100)+"_"+file.getFileName();
            String destino=raiz+folder+nombreFinal;
            
            System.out.println(destino);
            
            OutputStream out = new FileOutputStream(new File(destino));
            
            InputStream in = file.getInputStream();
            int read=0;
            byte[] bytes = new byte[1024]; 
            
            while ( (read=in.read(bytes)) != -1  ) {                
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();
        
            
            ////////////////////////////////////////////////////////////
            //String nombre_ext_Img[]=file.getFileName().split(".");
            
            MyFile mf=new MyFile();
            mf.setNombre(nombreFinal);
            mf.setRuta_raiz(raiz);
            mf.setRuta_folder(folder);
            mf.setFile(file);
            mf.setEstado(1);
            mf.setExiste(false);
            mf.setUsuario(c_usuario);
            mf.setD_usuario(d_usuario);
            lstDocument.add(mf);
            
            
            /*FacesMessage msg = new FacesMessage("Compleado", event.getFile().getFileName() + " cargado.");
            FacesContext.getCurrentInstance().addMessage(null, msg);*/ /*Comentado ´por Kevin Anderson*/


             
        }

    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    

    
    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getObjetoTarea() {
        return objetoTarea;
    }

    public void setObjetoTarea(String objetoTarea) {
        this.objetoTarea = objetoTarea;
    }

    public String[] getActividades_usuarios() {
        return actividades_usuarios;
    }

    public void setActividades_usuarios(String[] actividades_usuarios) {
        this.actividades_usuarios = actividades_usuarios;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    
    public String getD_developer() {
        return d_developer;
    }

    public void setD_developer(String d_developer) {
        this.d_developer = d_developer;
    }

    public String getFecha_autorizo_area() {
        return fecha_autorizo_area;
    }

    public void setFecha_autorizo_area(String fecha_autorizo_area) {
        this.fecha_autorizo_area = fecha_autorizo_area;
    }
    
   

    public String getC_area() {
        return c_area;
    }

    public void setC_area(String c_area) {
        this.c_area = c_area;
    }

    public String[] getTipo_comando() {
        return tipo_comando;
    }

    public void setTipo_comando(String[] tipo_comando) {
        this.tipo_comando = tipo_comando;
    }

    

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public int getCant_reg_afec() {
        return cant_reg_afec;
    }

    public void setCant_reg_afec(int cant_reg_afec) {
        this.cant_reg_afec = cant_reg_afec;
    }


    public String getTipo_objeto() {
        return tipo_objeto;
    }

    public void setTipo_objeto(String tipo_objeto) {
        this.tipo_objeto = tipo_objeto;
    }

    public String getNombre_objeto() {
        return nombre_objeto;
    }

    public void setNombre_objeto(String nombre_objeto) {
        this.nombre_objeto = nombre_objeto;
    }

    public String getC_developer() {
        return c_developer;
    }

    public void setC_developer(String c_developer) {
        this.c_developer = c_developer;
    }

    public String getC_dba() {
        return c_dba;
    }

    public void setC_dba(String c_dba) {
        this.c_dba = c_dba;
    }

    public String getFecha_inicio_permiso() {
        return fecha_inicio_permiso;
    }

    public void setFecha_inicio_permiso(String fecha_inicio_permiso) {
        this.fecha_inicio_permiso = fecha_inicio_permiso;
    }

    public String getFecha_fin_permiso() {
        return fecha_fin_permiso;
    }

    public void setFecha_fin_permiso(String fecha_fin_permiso) {
        this.fecha_fin_permiso = fecha_fin_permiso;
    }

    public String getFecha_autorizado() {
        return fecha_autorizado;
    }

    public void setFecha_autorizado(String fecha_autorizado) {
        this.fecha_autorizado = fecha_autorizado;
    }

    public String getFecha_asginado_dev() {
        return fecha_asginado_dev;
    }

    public void setFecha_asginado_dev(String fecha_asginado_dev) {
        this.fecha_asginado_dev = fecha_asginado_dev;
    }

    public String getFecha_completado() {
        return fecha_completado;
    }

    public void setFecha_completado(String fecha_completado) {
        this.fecha_completado = fecha_completado;
    }

    public String getFecha_aceptado() {
        return fecha_aceptado;
    }

    public void setFecha_aceptado(String fecha_aceptado) {
        this.fecha_aceptado = fecha_aceptado;
    }

    public String getFecha_finalizado() {
        return fecha_finalizado;
    }

    public void setFecha_finalizado(String fecha_finalizado) {
        this.fecha_finalizado = fecha_finalizado;
    }

    
    
    
    
    public String[] getAplicativo() {
        return aplicativo;
    }

    public void setAplicativo(String[] aplicativo) {
        this.aplicativo = aplicativo;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_corto() {
        return nombre_corto;
    }

    public void setNombre_corto(String nombre_corto) {
        this.nombre_corto = nombre_corto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCircular() {
        return circular;
    }

    public void setCircular(String circular) {
        this.circular = circular;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getNuevo() {
        return nuevo;
    }

    public void setNuevo(String nuevo) {
        this.nuevo = nuevo;
    }

    public String getC_agencia() {
        return c_agencia;
    }

    public void setC_agencia(String c_agencia) {
        this.c_agencia = c_agencia;
    }

    public String getD_agencia() {
        return d_agencia;
    }

    public void setD_agencia(String d_agencia) {
        this.d_agencia = d_agencia;
    }

    public String getC_usuario() {
        return c_usuario;
    }

    public void setC_usuario(String c_usuario) {
        this.c_usuario = c_usuario;
    }

    public String getD_usuario() {
        return d_usuario;
    }

    public void setD_usuario(String d_usuario) {
        this.d_usuario = d_usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<MyFile> getLstDocument() {
        return lstDocument;
    }

    public void setLstDocument(List<MyFile> lstDocument) {
        this.lstDocument = lstDocument;
    }

    public MyFile getSelectMyFile() {
        return selectMyFile;
    }

    public void setSelectMyFile(MyFile selectMyFile) {
        this.selectMyFile = selectMyFile;
    }

    public String getC_usuario_area() {
        return c_usuario_area;
    }

    public void setC_usuario_area(String c_usuario_area) {
        this.c_usuario_area = c_usuario_area;
    }

    public String getD_usuario_area() {
        return d_usuario_area;
    }

    public void setD_usuario_area(String d_usuario_area) {
        this.d_usuario_area = d_usuario_area;
    }

    public String getC_usuario_jefe() {
        return c_usuario_jefe;
    }

    public void setC_usuario_jefe(String c_usuario_jefe) {
        this.c_usuario_jefe = c_usuario_jefe;
    }

    public String getD_usuario_jefe() {
        return d_usuario_jefe;
    }

    public void setD_usuario_jefe(String d_usuario_jefe) {
        this.d_usuario_jefe = d_usuario_jefe;
    }

    public String getD_area() {
        return d_area;
    }

    public void setD_area(String d_area) {
        this.d_area = d_area;
    }

    public String getD_dba() {
        return d_dba;
    }

    public void setD_dba(String d_dba) {
        this.d_dba = d_dba;
    }

    public String getId_req_padre() {
        return id_req_padre;
    }

    public void setId_req_padre(String id_req_padre) {
        this.id_req_padre = id_req_padre;
    }

    public String getTipo_registro() {
        return tipo_registro;
    }

    public void setTipo_registro(String tipo_registro) {
        this.tipo_registro = tipo_registro;
    }

    public String getEspecifique_otro() {
        return especifique_otro;
    }

    public void setEspecifique_otro(String especifique_otro) {
        this.especifique_otro = especifique_otro;
    }

    public String getFecha_rechazo() {
        return fecha_rechazo;
    }

    public void setFecha_rechazo(String fecha_rechazo) {
        this.fecha_rechazo = fecha_rechazo;
    }
    
    
    
    
    
}
