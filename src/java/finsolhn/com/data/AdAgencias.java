/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.data;

import java.io.Serializable;
import java.util.List;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ds010106
 */
@Entity
@Table(name = "ad_agencias", catalog = "ad_testing", schema = "agenda_digital")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdAgencias.findAll", query = "SELECT a FROM AdAgencias a")
    , @NamedQuery(name = "AdAgencias.findByAgenciaid", query = "SELECT a FROM AdAgencias a WHERE a.agenciaid = :agenciaid")
    , @NamedQuery(name = "AdAgencias.findByAgencia", query = "SELECT a FROM AdAgencias a WHERE a.agencia = :agencia")
    , @NamedQuery(name = "AdAgencias.findByEstado", query = "SELECT a FROM AdAgencias a WHERE a.estado = :estado")})
public class AdAgencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "agenciaid")
    private Short agenciaid;
    @Size(max = 100)
    @Column(name = "agencia")
    private String agencia;
    @Column(name = "estado")
    private Boolean estado;
    @Size(max = 30)
    @Column(name = "zona")
    private String zona;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "agenciaid", fetch = FetchType.LAZY)
    //private List<AdUsuariosxagencia> adUsuariosxagenciaList;

    public AdAgencias() {
    }

    public AdAgencias(Short agenciaid) {
        this.agenciaid = agenciaid;
    }

    public Short getAgenciaid() {
        return agenciaid;
    }

    public void setAgenciaid(Short agenciaid) {
        this.agenciaid = agenciaid;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
   /* @XmlTransient
    public List<AdUsuariosxagencia> getAdUsuariosxagenciaList() {
        return adUsuariosxagenciaList;
    }

    public void setAdUsuariosxagenciaList(List<AdUsuariosxagencia> adUsuariosxagenciaList) {
        this.adUsuariosxagenciaList = adUsuariosxagenciaList;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agenciaid != null ? agenciaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdAgencias)) {
            return false;
        }
        AdAgencias other = (AdAgencias) object;
        if ((this.agenciaid == null && other.agenciaid != null) || (this.agenciaid != null && !this.agenciaid.equals(other.agenciaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdAgencias[ agenciaid=" + agenciaid + " ]";
    }


    
}
