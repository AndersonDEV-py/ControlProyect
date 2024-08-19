/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.model;

import finsolhn.com.util.Constantes;

import java.io.Serializable;

/**
 *
 * @author ds010106
 */
public class UsuariosMoviles implements Serializable{
    
    
    private String empleadoId;
    private String usuarioTopaz;
    private String nombre;
    private String puestoId;
    private String puesto;
    private String correo;
    private String intentos;
    private String agenciaId;
    private String agencia;   
    private String estado;
    private String ultimaSyn;
    private String ultimaSynAuto;
    private String ultimaLat;
    private String ultimaLon;
    private String imei;
    private String ultimoCambio;
    private String contrasenia;
    
    
    
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
        
 
        
        
        return colorBack_P;
    }

    public void setColorBack_P(String colorBack_P) {
        this.colorBack_P = colorBack_P;
    }
    
    
    
    
    public String getColorBack_T() {
        /*
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
        
        
        */
        return colorBack_T;
    }

    public void setColorBack_T(String colorBack_T) {
        this.colorBack_T = colorBack_T;
    }
    
    
    
    
    
    public String getColorBack_R() {
        
     
        
        return colorBack_R;
    }

    public void setColorBack_R(String colorBack_R) {
        this.colorBack_R = colorBack_R;
    }
    
    
    
    
    
    public String getColorBack_Est() {
        /*
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
        }else if(estado.equalsIgnoreCase(Constantes.estado_completado_dba) 
                && tipo_registro.equalsIgnoreCase("A") )
        {
            colorBack_Est=verde;
                    
        }else if(estado.equalsIgnoreCase(Constantes.estado_rechazado))
        {
            colorBack_Est=rojo;
        }

        */
        return colorBack_Est;
    }

   

    public void setColorBack_Est(String colorBack_Est) {
        this.colorBack_Est = colorBack_Est;
    }

    public String getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(String empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getUsuarioTopaz() {
        return usuarioTopaz;
    }

    public void setUsuarioTopaz(String usuarioTopaz) {
        this.usuarioTopaz = usuarioTopaz;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuestoId() {
        return puestoId;
    }

    public void setPuestoId(String puestoId) {
        this.puestoId = puestoId;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getIntentos() {
        return intentos;
    }

    public void setIntentos(String intentos) {
        this.intentos = intentos;
    }

    public String getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(String agenciaId) {
        this.agenciaId = agenciaId;
    }

    public String getAgencia() {
        return agencia.replaceAll("AGENCIA", "").replaceAll("OFICINA", "");
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUltimaSyn() {
        return ultimaSyn;
    }

    public void setUltimaSyn(String ultimaSyn) {
        this.ultimaSyn = ultimaSyn;
    }

    public String getUltimaLat() {
        return ultimaLat;
    }

    public void setUltimaLat(String ultimaLat) {
        this.ultimaLat = ultimaLat;
    }

    public String getUltimaLon() {
        return ultimaLon;
    }

    public void setUltimaLon(String ultimaLon) {
        this.ultimaLon = ultimaLon;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getUltimoCambio() {
        if(ultimoCambio!=null)
        if(ultimoCambio.equalsIgnoreCase("1900-01-01 00:00:00.0") || ultimoCambio.equalsIgnoreCase("1900-01-01")
                || ultimoCambio.equalsIgnoreCase("1900-01-01 00:00:00"))
        {
            return "";
        }
        return ultimoCambio;
    }

    public void setUltimoCambio(String ultimoCambio) {
        this.ultimoCambio = ultimoCambio;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUltimaSynAuto() {
        return ultimaSynAuto;
    }

    public void setUltimaSynAuto(String ultimaSynAuto) {
        this.ultimaSynAuto = ultimaSynAuto;
    }
    

    
    
    
}
