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
@Table(name = "ad_usuariosxagencia", catalog = "ad_testing", schema = "agenda_digital", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdUsuariosxagencia.findAll", query = "SELECT a FROM AdUsuariosxagencia a")
    , @NamedQuery(name = "AdUsuariosxagencia.findById", query = "SELECT a FROM AdUsuariosxagencia a WHERE a.id = :id")
    , @NamedQuery(name = "AdUsuariosxagencia.findByUsuariotopaz", query = "SELECT a FROM AdUsuariosxagencia a WHERE a.usuariotopaz = :usuariotopaz")
    , @NamedQuery(name = "AdUsuariosxagencia.findByAgenciaid", query = "SELECT a FROM AdUsuariosxagencia a WHERE a.agenciaid = :agenciaid")})
public class AdUsuariosxagencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "usuariotopaz", nullable = false, length = 8)
    private String usuariotopaz;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agenciaid", nullable = false)
    private short agenciaid;

    public AdUsuariosxagencia() {
    }

    public AdUsuariosxagencia(Long id) {
        this.id = id;
    }

    public AdUsuariosxagencia(Long id, String usuariotopaz, short agenciaid) {
        this.id = id;
        this.usuariotopaz = usuariotopaz;
        this.agenciaid = agenciaid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuariotopaz() {
        return usuariotopaz;
    }

    public void setUsuariotopaz(String usuariotopaz) {
        this.usuariotopaz = usuariotopaz;
    }

    public short getAgenciaid() {
        return agenciaid;
    }

    public void setAgenciaid(short agenciaid) {
        this.agenciaid = agenciaid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdUsuariosxagencia)) {
            return false;
        }
        AdUsuariosxagencia other = (AdUsuariosxagencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdUsuariosxagencia[ id=" + id + " ]";
    }
    
}
