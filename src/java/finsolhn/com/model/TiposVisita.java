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
public class TiposVisita {
    
    private String tipoVisita;
    private String descripcion;

    public TiposVisita(String tipoVisita, String descripcion) {
        this.tipoVisita = tipoVisita;
        this.descripcion = descripcion;
    }

    public TiposVisita() {
    }

    
    public String getTipoVisita() {
        return tipoVisita;
    }

    public void setTipoVisita(String tipoVisita) {
        this.tipoVisita = tipoVisita;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    
}
