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
public class EncuestaPregunta {
    private String cod_encuesta_pregunta;
    private String cod_encuesta;
    private String detalle_pregunta;
    private String cod_tipo_pregunta;
    private String cant_respuesta_req;
    private String NombreTipoPregunta;
    private String orden;
    
    public String getNombreTipoPregunta() {
        return NombreTipoPregunta;
    }

    public void setNombreTipoPregunta(String NombreTipoPregunta) {
        this.NombreTipoPregunta = NombreTipoPregunta;
    }
   

    public EncuestaPregunta(String cod_encuesta_pregunta, String cod_encuesta, String detalle_pregunta, String cod_tipo_pregunta, String cant_respuesta_req, String orden) {
        this.cod_encuesta_pregunta = cod_encuesta_pregunta;
        this.cod_encuesta = cod_encuesta;
        this.detalle_pregunta = detalle_pregunta;
        this.cod_tipo_pregunta = cod_tipo_pregunta;
        this.cant_respuesta_req = cant_respuesta_req;
        this.orden = orden;
    }

    public String getCod_encuesta_pregunta() {
        return cod_encuesta_pregunta;
    }

    public void setCod_encuesta_pregunta(String cod_encuesta_pregunta) {
        this.cod_encuesta_pregunta = cod_encuesta_pregunta;
    }

    public String getCod_encuesta() {
        return cod_encuesta;
    }

    public void setCod_encuesta(String cod_encuesta) {
        this.cod_encuesta = cod_encuesta;
    }

    public String getDetalle_pregunta() {
        return detalle_pregunta;
    }

    public void setDetalle_pregunta(String detalle_pregunta) {
        this.detalle_pregunta = detalle_pregunta;
    }

    public String getCod_tipo_pregunta() {
        return cod_tipo_pregunta;
    }

    public void setCod_tipo_pregunta(String cod_tipo_pregunta) {
        this.cod_tipo_pregunta = cod_tipo_pregunta;
    }

    public String getCant_respuesta_req() {
        return cant_respuesta_req;
    }

    public void setCant_respuesta_req(String cant_respuesta_req) {
        this.cant_respuesta_req = cant_respuesta_req;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.cod_encuesta_pregunta);
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
        final EncuestaPregunta other = (EncuestaPregunta) obj;
        if (!Objects.equals(this.cod_encuesta_pregunta, other.cod_encuesta_pregunta)) {
            return false;
        }
        return true;
    }
    
    public EncuestaPregunta(){}
}
