/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.model;

/**
 *
 * @author rcardona
 */
public class MenuOpcion {
    
   private int c_opcion;
   private String descripcion;
   private int c_menu;
   private String descripcionPadre;
   private int es_titulo;
   private int c_titulo;
   private int orden;
   private String url;
   private int estado=1;

    
   
   private boolean activo=true;
   private boolean title;

   
 ///  private boolean title;
   
   
    public int getC_opcion() {
        return c_opcion;
    }

    public void setC_opcion(int c_opcion) {
        this.c_opcion = c_opcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getC_menu() {
        return c_menu;
    }

    public void setC_menu(int c_menu) {
        this.c_menu = c_menu;
    }

    public String getDescripcionPadre() {
        return descripcionPadre;
    }

    public void setDescripcionPadre(String descripcionPadre) {
        this.descripcionPadre = descripcionPadre;
    }

 
    public int getC_titulo() {
        return c_titulo;
    }

    public void setC_titulo(int c_titulo) {
        this.c_titulo = c_titulo;
    }
    

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    
    ////////////////
  public int getEstado() {
         if(activo)
            estado=1;
        else estado=0;
         
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
        
        if(estado==1)
            activo=true;
        else activo=false;
    }

    public boolean isActivo() {
        
        if(estado==1)
            activo=true;
        else activo=false;
        
        return activo;
    }

    public void setActivo(boolean activo) {
        
        if(activo)
            estado=1;
        else estado=0;
        
        this.activo = activo;
        
    }
   
    ///////////////////

    public int getEs_titulo() {
         if(title)
            es_titulo=1;
        else es_titulo=0;
        
        
        return es_titulo;
    }

    public void setEs_titulo(int es_titulo) {
        this.es_titulo = es_titulo;
        
         if(es_titulo==1)
            title=true;
        else title=false;
    }
    
    public boolean isTitle() {
        
        
        if(es_titulo==1)
            title=true;
        else title=false;
        
        return title;
    }

    public void setTitle(boolean title) {
        
        if(title)
            es_titulo=1;
        else es_titulo=0;
        
        this.title = title;
    }
    
   

    
    
    
}
