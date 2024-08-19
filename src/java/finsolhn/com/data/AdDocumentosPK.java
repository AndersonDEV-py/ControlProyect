/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.data;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author ds010106
 */
@Embeddable
public class AdDocumentosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idcliente", nullable = false)
    private long idcliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "pais", nullable = false, length = 2)
    private String pais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "tipodoc", nullable = false, length = 3)
    private String tipodoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nrodocumento", nullable = false, length = 20)
    private String nrodocumento;

    public AdDocumentosPK() {
    }

    public AdDocumentosPK(long idcliente, String pais, String tipodoc, String nrodocumento) {
        this.idcliente = idcliente;
        this.pais = pais;
        this.tipodoc = tipodoc;
        this.nrodocumento = nrodocumento;
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    public String getNrodocumento() {
        return nrodocumento;
    }

    public void setNrodocumento(String nrodocumento) {
        this.nrodocumento = nrodocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcliente;
        hash += (pais != null ? pais.hashCode() : 0);
        hash += (tipodoc != null ? tipodoc.hashCode() : 0);
        hash += (nrodocumento != null ? nrodocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdDocumentosPK)) {
            return false;
        }
        AdDocumentosPK other = (AdDocumentosPK) object;
        if (this.idcliente != other.idcliente) {
            return false;
        }
        if ((this.pais == null && other.pais != null) || (this.pais != null && !this.pais.equals(other.pais))) {
            return false;
        }
        if ((this.tipodoc == null && other.tipodoc != null) || (this.tipodoc != null && !this.tipodoc.equals(other.tipodoc))) {
            return false;
        }
        if ((this.nrodocumento == null && other.nrodocumento != null) || (this.nrodocumento != null && !this.nrodocumento.equals(other.nrodocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdDocumentosPK[ idcliente=" + idcliente + ", pais=" + pais + ", tipodoc=" + tipodoc + ", nrodocumento=" + nrodocumento + " ]";
    }
    
}
