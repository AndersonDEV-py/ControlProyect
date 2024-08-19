/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.model;

import java.util.Date;

/**
 *
 * @author ds010106
 */
public class EncuestasCreadas {
    private String codigoEncuestaCliente;
    private Date Fecha;
    private String codCliente;
    private String cuenta;
    private String codEncuesta;
    private String tipoEncuesta;
    private String usuario;

    public EncuestasCreadas(String codigoEncuestaCliente, Date Fecha, String codCliente, String cuenta, String codEncuesta, String usuario) {
        this.codigoEncuestaCliente = codigoEncuestaCliente;
        this.Fecha = Fecha;
        this.codCliente = codCliente;
        this.cuenta = cuenta;
        this.codEncuesta = codEncuesta;
        this.usuario = usuario;
    }

    
    
    public EncuestasCreadas() {
       
    }

    public String getCodigoEncuestaCliente() {
        return codigoEncuestaCliente;
    }

    public void setCodigoEncuestaCliente(String codigoEncuestaCliente) {
        this.codigoEncuestaCliente = codigoEncuestaCliente;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipoEncuesta() {
        return tipoEncuesta;
    }

    public void setTipoEncuesta(String tipoEncuesta) {
        this.tipoEncuesta = tipoEncuesta;
    }

    public String getCodEncuesta() {
        return codEncuesta;
    }

    public void setCodEncuesta(String codEncuesta) {
        this.codEncuesta = codEncuesta;
    }
    
    
    
}
