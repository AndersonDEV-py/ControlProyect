/*
 * CLASE ENTITY DE LA TABLA CL_ENCUESTA
 *
 *
 * 
 */
package finsolhn.com.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ds010106
 */
@Entity
@Table(name = "CL_ENCUESTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClEncuesta.findAll", query = "SELECT c FROM ClEncuesta c"),
    @NamedQuery(name = "ClEncuesta.findByCodEncuesta", query = "SELECT c FROM ClEncuesta c WHERE c.codEncuesta = :codEncuesta"),
    @NamedQuery(name = "ClEncuesta.findByNombre", query = "SELECT c FROM ClEncuesta c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "ClEncuesta.findByTipoEncuesta", query = "SELECT c FROM ClEncuesta c WHERE c.tipoEncuesta = :tipoEncuesta"),
    @NamedQuery(name = "ClEncuesta.findByFechaCreacion", query = "SELECT c FROM ClEncuesta c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ClEncuesta.findByEstado", query = "SELECT c FROM ClEncuesta c WHERE c.estado = :estado")})
public class ClEncuesta implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codEncuesta")


    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ENCUESTA")
    private Integer codEncuesta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "TIPO_ENCUESTA")
    private String tipoEncuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private short estado;

    public ClEncuesta() {
    }

    public ClEncuesta(Integer codEncuesta) {
        this.codEncuesta = codEncuesta;
    }

    public ClEncuesta(Integer codEncuesta, String nombre, String tipoEncuesta, Date fechaCreacion, short estado) {
        this.codEncuesta = codEncuesta;
        this.nombre = nombre;
        this.tipoEncuesta = tipoEncuesta;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Integer getCodEncuesta() {
        return codEncuesta;
    }

    public void setCodEncuesta(Integer codEncuesta) {
        this.codEncuesta = codEncuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoEncuesta() {
        return tipoEncuesta;
    }

    public void setTipoEncuesta(String tipoEncuesta) {
        this.tipoEncuesta = tipoEncuesta;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEncuesta != null ? codEncuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClEncuesta)) {
            return false;
        }
        ClEncuesta other = (ClEncuesta) object;
        if ((this.codEncuesta == null && other.codEncuesta != null) || (this.codEncuesta != null && !this.codEncuesta.equals(other.codEncuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[codEncuesta=" + getCodEncuesta() + " nombre="+ getNombre() +"]";
    }

  
    
}
