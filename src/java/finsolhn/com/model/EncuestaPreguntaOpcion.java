/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.model;

import java.util.Objects;

/**
 *
 * @author ds010106
 */
public class EncuestaPreguntaOpcion {
    private String cod_pregunta_opcion;
    private String cod_encuesta_pregunta;
    private String nombre;
    private String justificacion;
    private String orden;

    public String getCod_pregunta_opcion() {
        return cod_pregunta_opcion;
    }

    public void setCod_pregunta_opcion(String cod_pregunta_opcion) {
        this.cod_pregunta_opcion = cod_pregunta_opcion;
    }

    public String getCod_encuesta_pregunta() {
        return cod_encuesta_pregunta;
    }

    public void setCod_encuesta_pregunta(String cod_encuesta_pregunta) {
        this.cod_encuesta_pregunta = cod_encuesta_pregunta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.cod_pregunta_opcion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EncuestaPreguntaOpcion other = (EncuestaPreguntaOpcion) obj;
        if (!Objects.equals(this.cod_pregunta_opcion, other.cod_pregunta_opcion)) {
            return false;
        }
        return true;
        
        
    }

    public EncuestaPreguntaOpcion(String cod_pregunta_opcion, String cod_encuesta_pregunta, String nombre, String justificacion, String orden) {
        this.cod_pregunta_opcion = cod_pregunta_opcion;
        this.cod_encuesta_pregunta = cod_encuesta_pregunta;
        this.nombre = nombre;
        this.justificacion = justificacion;
        this.orden = orden;
    }
    
    
}
