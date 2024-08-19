/*
 * CLASE MODELO DE LA TABLA CL_ENCUESTA
 *
 */
package finsolhn.com.model;

import java.util.Objects;

/**
 *
 * @author ds010106
 */
public class Encuesta {
    
    private String cod_Encuesta;
    private String nombre;
    private String estado;

    public Encuesta(String cod_Encuesta, String nombre, String estado) {
        this.cod_Encuesta = cod_Encuesta;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Encuesta() {
    }

    public String getCod_Encuesta() {
        return cod_Encuesta;
    }

    public void setCod_Encuesta(String cod_Encuesta) {
        this.cod_Encuesta = cod_Encuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.cod_Encuesta);
        hash = 67 * hash + Objects.hashCode(this.estado);
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
        final Encuesta other = (Encuesta) obj;
        if (!Objects.equals(this.cod_Encuesta, other.cod_Encuesta)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        return true;
    }
   
    
    
}
