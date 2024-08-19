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
public class Aceptacion implements Serializable{
    
    
    private int id;
    private String fecha;//fecha creado por usuario
    private String fecha_completado; //fecha completado por desarrollo
    private String fecha_aceptado; //fecha aceptado por usuario
    private String fecha_finalizado; //fecha finalizado (jefe proyecto)
    private String fecha_rechazo; //fecha rechazo sol
    private String c_agencia;
    private String d_agencia;
    private String c_usuario;//usuario solicita
    private String d_usuario;
    private String descripcion_causa;
    private String descripcion_solucion;
    private String observacion_pruebas;
    private String[] aplicativo;
    private String[] aplicativo_primario;
    private String[] tipo_archivo;
    private String[] origen_problema;
    private String estado;
    private String especifique_otro;
    private String especifique_otro_primario;
    private String especifique_otro_origen;
    private String especifique_otro_tipo_archivo;    
    private String ubicacion_archivo;    
    private String persona_pruebas;
    private String duraccion_pruebas;
    private String resolucion;
    private String descripcion_acepto;
    private String descripcion_rechazo;
    private String descripcion_refurmulo;
    
    private String c_usuario_area;
    private String d_usuario_area;
    private String c_usuario_jefe;
    private String d_usuario_jefe;
    private String c_area;
    private String d_area;
    
    private String c_developer; // posible master detail
    private String d_developer;  
    
    private String id_req_padre;

    private String nombre;

    private String colorBack_R; //fondo estado req y casos
     
    private static String gris="#9E9DAB";
    private static String azul="#06F2F9";
    private static String verde="#AEF77F";
    private static String rojo="#FC5342";

    private static String naranja="#F8AC30";
    private static String naranja0="#F4C477";
    private static String azul2="#00B6BC";
    private static String verde2="#7FCA4F";    
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
     
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getDescripcion_acepto() {
        return descripcion_acepto;
    }

    public void setDescripcion_acepto(String descripcion_acepto) {
        this.descripcion_acepto = descripcion_acepto;
    }

    public String getDescripcion_rechazo() {
        return descripcion_rechazo;
    }

    public void setDescripcion_rechazo(String descripcion_rechazo) {
        this.descripcion_rechazo = descripcion_rechazo;
    }

    public String getDescripcion_refurmulo() {
        return descripcion_refurmulo;
    }

    public void setDescripcion_refurmulo(String descripcion_refurmulo) {
        this.descripcion_refurmulo = descripcion_refurmulo;
    }

    
    
    
    
    public String getObservacion_pruebas() {
        return observacion_pruebas;
    }

    public void setObservacion_pruebas(String observacion_pruebas) {
        this.observacion_pruebas = observacion_pruebas;
    }

    
    
    public String getDuraccion_pruebas() {
        return duraccion_pruebas;
    }

    public void setDuraccion_pruebas(String duraccion_pruebas) {
        this.duraccion_pruebas = duraccion_pruebas;
    }

    
    
    public String getPersona_pruebas() {
        return persona_pruebas;
    }

    public void setPersona_pruebas(String persona_pruebas) {
        this.persona_pruebas = persona_pruebas;
    }

    
    
    
    public String getUbicacion_archivo() {
        return ubicacion_archivo;
    }

    public void setUbicacion_archivo(String ubicacion_archivo) {
        this.ubicacion_archivo = ubicacion_archivo;
    }

    
    
    public String getEspecifique_otro_tipo_archivo() {
        return especifique_otro_tipo_archivo;
    }

    public void setEspecifique_otro_tipo_archivo(String especifique_otro_tipo_archivo) {
        this.especifique_otro_tipo_archivo = especifique_otro_tipo_archivo;
    }

    
    
    
    public String[] getTipo_archivo() {
        return tipo_archivo;
    }

    public void setTipo_archivo(String[] tipo_archivo) {
        this.tipo_archivo = tipo_archivo;
    }

    
    
    
    public String getDescripcion_causa() {
        return descripcion_causa;
    }

    public void setDescripcion_causa(String descripcion_causa) {
        this.descripcion_causa = descripcion_causa;
    }

    
    
    
    public String[] getOrigen_problema() {
        return origen_problema;
    }

    public void setOrigen_problema(String[] origen_problema) {
        this.origen_problema = origen_problema;
    }

    public String getEspecifique_otro_primario() {
        return especifique_otro_primario;
    }

    public void setEspecifique_otro_primario(String especifique_otro_primario) {
        this.especifique_otro_primario = especifique_otro_primario;
    }

    public String getEspecifique_otro_origen() {
        return especifique_otro_origen;
    }

    public void setEspecifique_otro_origen(String especifique_otro_origen) {
        this.especifique_otro_origen = especifique_otro_origen;
    }

    
    
    
    public String getDescripcion_solucion() {
        return descripcion_solucion;
    }

    public void setDescripcion_solucion(String descripcion_solucion) {
        this.descripcion_solucion = descripcion_solucion;
    }

    public String[] getAplicativo_primario() {
        return aplicativo_primario;
    }

    public void setAplicativo_primario(String[] aplicativo_primario) {
        this.aplicativo_primario = aplicativo_primario;
    }
                
    
    
    public String getD_developer() {
        return d_developer;
    }

    public void setD_developer(String d_developer) {
        this.d_developer = d_developer;
    }
  

    public String getC_area() {
        return c_area;
    }

    public void setC_area(String c_area) {
        this.c_area = c_area;
    }


    public String getC_developer() {
        return c_developer;
    }

    public void setC_developer(String c_developer) {
        this.c_developer = c_developer;
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

   
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

  
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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


    public String getId_req_padre() {
        return id_req_padre;
    }

    public void setId_req_padre(String id_req_padre) {
        this.id_req_padre = id_req_padre;
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
