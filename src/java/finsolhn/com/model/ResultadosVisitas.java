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
public class ResultadosVisitas {
    
    
    private String tipoVisita;
    private String resultado;
    private String descripcion;

    public ResultadosVisitas() {
    }

    public ResultadosVisitas(String tipoVisita, String resultado, String descripcion) {
        this.tipoVisita = tipoVisita;
        this.resultado = resultado;
        this.descripcion = descripcion;
    }

    public ResultadosVisitas(String tipoVisita, String resultado) {
        this.tipoVisita = tipoVisita;
        this.resultado = resultado;
    }

    
    
    
    public String getTipoVisita() {
        return tipoVisita;
    }

    public void setTipoVisita(String tipoVisita) {
        this.tipoVisita = tipoVisita;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    
}
