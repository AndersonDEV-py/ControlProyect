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
@Table(name = "ad_lista_oscura", catalog = "ad_testing", schema = "agenda_digital")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdListaOscura.findAll", query = "SELECT a FROM AdListaOscura a")
    , @NamedQuery(name = "AdListaOscura.findById", query = "SELECT a FROM AdListaOscura a WHERE a.id = :id")
    , @NamedQuery(name = "AdListaOscura.findByIdentidad", query = "SELECT a FROM AdListaOscura a WHERE a.identidad = :identidad")
    , @NamedQuery(name = "AdListaOscura.findByNombre", query = "SELECT a FROM AdListaOscura a WHERE a.nombre = :nombre")})
public class AdListaOscura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;
    @Size(max = 50)
    @Column(name = "identidad", length = 50)
    private String identidad;
    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;

    public AdListaOscura() {
    }

    public AdListaOscura(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof AdListaOscura)) {
            return false;
        }
        AdListaOscura other = (AdListaOscura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdListaOscura[ id=" + id + " ]";
    }
    
}
