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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ds010106
 */
@Entity
@Table(name = "ad_clientes", catalog = "ad_testing", schema = "agenda_digital")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdClientes.findAll", query = "SELECT a FROM AdClientes a")
    , @NamedQuery(name = "AdClientes.findByIdcliente", query = "SELECT a FROM AdClientes a WHERE a.idcliente = :idcliente")
    , @NamedQuery(name = "AdClientes.findByTipocliente", query = "SELECT a FROM AdClientes a WHERE a.tipocliente = :tipocliente")
    , @NamedQuery(name = "AdClientes.findByNombre", query = "SELECT a FROM AdClientes a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "AdClientes.findByIdclienterep", query = "SELECT a FROM AdClientes a WHERE a.idclienterep = :idclienterep")})
public class AdClientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcliente")
    private Long idcliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "tipocliente")
    private String tipocliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "idclienterep")
    private Long idclienterep;

    public AdClientes() {
    }

    public AdClientes(Long idcliente) {
        this.idcliente = idcliente;
    }

    public AdClientes(Long idcliente, String tipocliente, String nombre) {
        this.idcliente = idcliente;
        this.tipocliente = tipocliente;
        this.nombre = nombre;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public String getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(String tipocliente) {
        this.tipocliente = tipocliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdclienterep() {
        return idclienterep;
    }

    public void setIdclienterep(Long idclienterep) {
        this.idclienterep = idclienterep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdClientes)) {
            return false;
        }
        AdClientes other = (AdClientes) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdClientes[ idcliente=" + idcliente + " ]";
    }
    
}
