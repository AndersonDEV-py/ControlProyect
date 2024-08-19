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
public class Menu {
    
   private int c_menu;
   private String descripcion;
   private int orden;
   private int estado;
   private boolean activo;
   
    public int getC_menu() {
        return c_menu;
    }

    public void setC_menu(int c_menu) {
        this.c_menu = c_menu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
/////////////////////////////////////////////
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
    
    
   
   
    
}
