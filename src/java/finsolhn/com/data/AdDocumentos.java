/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.data;

import java.io.Serializable;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ds010106
 */
@Entity
@Table(name = "ad_documentos", catalog = "ad_testing", schema = "agenda_digital", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idcliente", "pais", "tipodoc", "nrodocumento"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdDocumentos.findAll", query = "SELECT a FROM AdDocumentos a")
    , @NamedQuery(name = "AdDocumentos.findByIdcliente", query = "SELECT a FROM AdDocumentos a WHERE a.adDocumentosPK.idcliente = :idcliente")
    , @NamedQuery(name = "AdDocumentos.findByPais", query = "SELECT a FROM AdDocumentos a WHERE a.adDocumentosPK.pais = :pais")
    , @NamedQuery(name = "AdDocumentos.findByTipodoc", query = "SELECT a FROM AdDocumentos a WHERE a.adDocumentosPK.tipodoc = :tipodoc")
    , @NamedQuery(name = "AdDocumentos.findByDescripcion", query = "SELECT a FROM AdDocumentos a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "AdDocumentos.findByNrodocumento", query = "SELECT a FROM AdDocumentos a WHERE a.adDocumentosPK.nrodocumento = :nrodocumento")
    , @NamedQuery(name = "AdDocumentos.findByPrincipal", query = "SELECT a FROM AdDocumentos a WHERE a.principal = :principal")})
public class AdDocumentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AdDocumentosPK adDocumentosPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "principal", nullable = false, length = 1)
    private String principal;

    public AdDocumentos() {
    }

    public AdDocumentos(AdDocumentosPK adDocumentosPK) {
        this.adDocumentosPK = adDocumentosPK;
    }

    public AdDocumentos(AdDocumentosPK adDocumentosPK, String descripcion, String principal) {
        this.adDocumentosPK = adDocumentosPK;
        this.descripcion = descripcion;
        this.principal = principal;
    }

    public AdDocumentos(long idcliente, String pais, String tipodoc, String nrodocumento) {
        this.adDocumentosPK = new AdDocumentosPK(idcliente, pais, tipodoc, nrodocumento);
    }

    public AdDocumentosPK getAdDocumentosPK() {
        return adDocumentosPK;
    }

    public void setAdDocumentosPK(AdDocumentosPK adDocumentosPK) {
        this.adDocumentosPK = adDocumentosPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adDocumentosPK != null ? adDocumentosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdDocumentos)) {
            return false;
        }
        AdDocumentos other = (AdDocumentos) object;
        if ((this.adDocumentosPK == null && other.adDocumentosPK != null) || (this.adDocumentosPK != null && !this.adDocumentosPK.equals(other.adDocumentosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdDocumentos[ adDocumentosPK=" + adDocumentosPK + " ]";
    }
    
}
