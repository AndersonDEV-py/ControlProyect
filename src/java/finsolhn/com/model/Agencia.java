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
public class Agencia {
    
    private String agenciaId;
    private String agencia;
    private String distritoId;

    public Agencia() {
    }

    public Agencia(String agenciaId, String agencia) {
        this.agenciaId = agenciaId;
        this.agencia = agencia;
    }

    public Agencia(String agenciaId, String agencia, String distritoId) {
        this.agenciaId = agenciaId;
        this.agencia = agencia;
        this.distritoId = distritoId;
    }

    
    
    public String getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(String agenciaId) {
        this.agenciaId = agenciaId;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getDistritoId() {
        return distritoId;
    }

    public void setDistritoId(String distritoId) {
        this.distritoId = distritoId;
    }
    
    
    
    
}
