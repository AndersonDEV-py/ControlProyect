/*
 * CLASE ENTITY DE LA TABLA CL_ENCUESTA_PREGUNTA
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
@Table(name = "CL_ENCUESTA_PREGUNTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClEncuestaPregunta.findAll", query = "SELECT c FROM ClEncuestaPregunta c"),
    @NamedQuery(name = "ClEncuestaPregunta.findByCodEncuestaPregunta", query = "SELECT c FROM ClEncuestaPregunta c WHERE c.codEncuestaPregunta = :codEncuestaPregunta"),
    @NamedQuery(name = "ClEncuestaPregunta.findByDetallePregunta", query = "SELECT c FROM ClEncuestaPregunta c WHERE c.detallePregunta = :detallePregunta"),
    @NamedQuery(name = "ClEncuestaPregunta.findByCantRespuestaReq", query = "SELECT c FROM ClEncuestaPregunta c WHERE c.cantRespuestaReq = :cantRespuestaReq"),
    @NamedQuery(name = "ClEncuestaPregunta.findByOrden", query = "SELECT c FROM ClEncuestaPregunta c WHERE c.orden = :orden")})
public class ClEncuestaPregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ENCUESTA_PREGUNTA")
    private String codEncuestaPregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DETALLE_PREGUNTA")
    private String detallePregunta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_RESPUESTA_REQ")
    private short cantRespuestaReq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN")
    private short orden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_TIPO_PREGUNTA")
    private Integer codTipoPregunta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOMBRE_TIPO_PREGUNTA")
    private String NombreTipoPregunta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OBLIGATORIA")
    private String obligatoria;

    public String getNombreTipoPregunta() {
        return NombreTipoPregunta;
    }

    public void setNombreTipoPregunta(String NombreTipoPregunta) {
        this.NombreTipoPregunta = NombreTipoPregunta;
    }

    public Integer getCodTipoPregunta() {
        return codTipoPregunta;
    }

    public void setCodTipoPregunta(Integer codTipoPregunta) {
        this.codTipoPregunta = codTipoPregunta;
    }


    public ClEncuestaPregunta() {
    }

    public ClEncuestaPregunta(String codEncuestaPregunta) {
        this.codEncuestaPregunta = codEncuestaPregunta;
    }

    public ClEncuestaPregunta(String codEncuestaPregunta, String detallePregunta, short cantRespuestaReq, short orden) {
        this.codEncuestaPregunta = codEncuestaPregunta;
        this.detallePregunta = detallePregunta;
        this.cantRespuestaReq = cantRespuestaReq;
        this.orden = orden;
    }

    public String getCodEncuestaPregunta() {
        return codEncuestaPregunta;
    }

    public void setCodEncuestaPregunta(String codEncuestaPregunta) {
        this.codEncuestaPregunta = codEncuestaPregunta;
    }

    public String getDetallePregunta() {
        return detallePregunta;
    }

    public void setDetallePregunta(String detallePregunta) {
        this.detallePregunta = detallePregunta;
    }

    public short getCantRespuestaReq() {
        return cantRespuestaReq;
    }

    public void setCantRespuestaReq(short cantRespuestaReq) {
        this.cantRespuestaReq = cantRespuestaReq;
    }

    public short getOrden() {
        return orden;
    }

    public void setOrden(short orden) {
        this.orden = orden;
    }

    public String getObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(String obligatoria) {
        this.obligatoria = obligatoria;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEncuestaPregunta != null ? codEncuestaPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClEncuestaPregunta)) {
            return false;
        }
        ClEncuestaPregunta other = (ClEncuestaPregunta) object;
        if ((this.codEncuestaPregunta == null && other.codEncuestaPregunta != null) || (this.codEncuestaPregunta != null && !this.codEncuestaPregunta.equals(other.codEncuestaPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  getDetallePregunta();
                   
    }
    
}
