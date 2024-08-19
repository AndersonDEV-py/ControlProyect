/*
 * CLASE ENTITY DE LA TABLA CL_ENCUESTA_PREGUNTA_OPCION
 *
 */
package finsolhn.com.data;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ds010106
 */
@Entity
@Table(name = "CL_ENCUESTA_PREGUNTA_OPCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClEncuestaPreguntaOpcion.findAll", query = "SELECT c FROM ClEncuestaPreguntaOpcion c"),
    @NamedQuery(name = "ClEncuestaPreguntaOpcion.findByCodPreguntaOpcion", query = "SELECT c FROM ClEncuestaPreguntaOpcion c WHERE c.codPreguntaOpcion = :codPreguntaOpcion"),
    @NamedQuery(name = "ClEncuestaPreguntaOpcion.findByNombre", query = "SELECT c FROM ClEncuestaPreguntaOpcion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "ClEncuestaPreguntaOpcion.findByJustificacion", query = "SELECT c FROM ClEncuestaPreguntaOpcion c WHERE c.justificacion = :justificacion"),
    @NamedQuery(name = "ClEncuestaPreguntaOpcion.findByOrden", query = "SELECT c FROM ClEncuestaPreguntaOpcion c WHERE c.orden = :orden")})
public class ClEncuestaPreguntaOpcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PREGUNTA_OPCION")
    private Integer codPreguntaOpcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "JUSTIFICACION")
    private short justificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN")
    private short orden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ENCUESTA_PREGUNTA")
    private short codEncuestaPregunta;

    public short getCodEncuestaPregunta() {
        return codEncuestaPregunta;
    }

    public void setCodEncuestaPregunta(short codEncuestaPregunta) {
        this.codEncuestaPregunta = codEncuestaPregunta;
    }

    public ClEncuestaPreguntaOpcion() {
    }

    public ClEncuestaPreguntaOpcion(Integer codPreguntaOpcion) {
        this.codPreguntaOpcion = codPreguntaOpcion;
    }

    public ClEncuestaPreguntaOpcion(Integer codPreguntaOpcion, String nombre, short justificacion, short orden,short codEncuestaregunta) {
        this.codPreguntaOpcion = codPreguntaOpcion;
        this.nombre = nombre;
        this.justificacion = justificacion;
        this.orden = orden;
        this.codEncuestaPregunta=codEncuestaregunta;
    }

    public Integer getCodPreguntaOpcion() {
        return codPreguntaOpcion;
    }

    public void setCodPreguntaOpcion(Integer codPreguntaOpcion) {
        this.codPreguntaOpcion = codPreguntaOpcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(short justificacion) {
        this.justificacion = justificacion;
    }

    public short getOrden() {
        return orden;
    }

    public void setOrden(short orden) {
        this.orden = orden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPreguntaOpcion != null ? codPreguntaOpcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClEncuestaPreguntaOpcion)) {
            return false;
        }
        ClEncuestaPreguntaOpcion other = (ClEncuestaPreguntaOpcion) object;
        if ((this.codPreguntaOpcion == null && other.codPreguntaOpcion != null) || (this.codPreguntaOpcion != null && !this.codPreguntaOpcion.equals(other.codPreguntaOpcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + getCodPreguntaOpcion() +","
                + getCodEncuestaPregunta()+","
                + getNombre()+","
                +getJustificacion()+" ]";
    }
    
}
