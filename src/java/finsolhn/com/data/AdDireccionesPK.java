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
public class AdDireccionesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idcliente", nullable = false)
    private long idcliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "formato", nullable = false, length = 30)
    private String formato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tipodir", nullable = false, length = 30)
    private String tipodir;

    public AdDireccionesPK() {
    }

    public AdDireccionesPK(long idcliente, String formato, String tipodir) {
        this.idcliente = idcliente;
        this.formato = formato;
        this.tipodir = tipodir;
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getTipodir() {
        return tipodir;
    }

    public void setTipodir(String tipodir) {
        this.tipodir = tipodir;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcliente;
       // hash += (formato != null ? formato.hashCode() : 0);
       // hash += (tipodir != null ? tipodir.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdDireccionesPK)) {
            return false;
        }
        AdDireccionesPK other = (AdDireccionesPK) object;
        if (this.idcliente != other.idcliente) {
            return false;
        }
       /* if ((this.formato == null && other.formato != null) || (this.formato != null && !this.formato.equals(other.formato))) {
            return false;
        }
        if ((this.tipodir == null && other.tipodir != null) || (this.tipodir != null && !this.tipodir.equals(other.tipodir))) {
            return false;
        }*/
        return true;
    }

    @Override
    public String toString() {
        //return "finsolhn.com.data.AdDireccionesPK[ idcliente=" + idcliente + ", formato=" + formato + ", tipodir=" + tipodir + " ]";
        return "finsolhn.com.data.AdDireccionesPK[ idcliente=" + idcliente + " ]";
    }
    
}
