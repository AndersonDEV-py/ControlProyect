/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.model;

import java.io.Serializable;

/**
 *
 * @author ds010106
 */
public class Empleado implements Serializable{
    
   private String id_empleado;
   private String nombre;
   private String c_agencia="";
   private String d_agencia="";
   private String c_depto="";
   private String d_depto="";
   private String nivel;
   
   //asesor
    private String codCargo;
    private String desCargo;
    private String usuario;

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getC_depto() {
        return c_depto;
    }

    public void setC_depto(String c_depto) {
        this.c_depto = c_depto;
    }

    public String getD_depto() {
        return d_depto;
    }

    public void setD_depto(String d_depto) {
        this.d_depto = d_depto;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getCodCargo() {
        return codCargo;
    }

    public void setCodCargo(String codCargo) {
        this.codCargo = codCargo;
    }

    public String getDesCargo() {
        return desCargo;
    }

    public void setDesCargo(String desCargo) {
        this.desCargo = desCargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
   
   
    
}
