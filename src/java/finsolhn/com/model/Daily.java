/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.model;

/**
 *
 * @author ds010106
 */
public class Daily {
    
    private int id;
    private String c_desarrolador;
    private String d_desarrolador;
    private String fecha;
    private String detalle="";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getD_desarrolador() {
        return d_desarrolador;
    }

    public void setD_desarrolador(String d_desarrolador) {
        this.d_desarrolador = d_desarrolador;
    }
    
    
    public String getC_desarrolador() {
        return c_desarrolador;
    }

    public void setC_desarrolador(String c_desarrolador) {
        this.c_desarrolador = c_desarrolador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
    
    
}
