/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ds010106
 */
@Entity
@Table(name = "ad_tiposusuarios", catalog = "ad_testing", schema = "agenda_digital", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idtipousuario"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdTiposusuarios.findAll", query = "SELECT a FROM AdTiposusuarios a")
    , @NamedQuery(name = "AdTiposusuarios.findByIdtipousuario", query = "SELECT a FROM AdTiposusuarios a WHERE a.idtipousuario = :idtipousuario")
    , @NamedQuery(name = "AdTiposusuarios.findByDescripcion", query = "SELECT a FROM AdTiposusuarios a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "AdTiposusuarios.findByEstado", query = "SELECT a FROM AdTiposusuarios a WHERE a.estado = :estado")})
public class AdTiposusuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtipousuario", nullable = false)
    private Integer idtipousuario;
    @Size(max = 30)
    @Column(name = "descripcion", length = 30)
    private String descripcion;
    @Column(name = "estado")
    private Boolean estado;

    public AdTiposusuarios() {
    }

    public AdTiposusuarios(Integer idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public Integer getIdtipousuario() {
        return idtipousuario;
    }

    public void setIdtipousuario(Integer idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipousuario != null ? idtipousuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdTiposusuarios)) {
            return false;
        }
        AdTiposusuarios other = (AdTiposusuarios) object;
        if ((this.idtipousuario == null && other.idtipousuario != null) || (this.idtipousuario != null && !this.idtipousuario.equals(other.idtipousuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdTiposusuarios[ idtipousuario=" + idtipousuario + " ]";
    }
    
}
