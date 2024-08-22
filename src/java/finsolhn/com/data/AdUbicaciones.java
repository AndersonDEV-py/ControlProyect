/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.data;

import java.io.Serializable;
import java.util.Date;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ds010106
 */
@Entity
@Table(name = "ad_ubicaciones", catalog = "ad_testing", schema = "agenda_digital")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdUbicaciones.findAll", query = "SELECT a FROM AdUbicaciones a")
    , @NamedQuery(name = "AdUbicaciones.findByIdcliente", query = "SELECT a FROM AdUbicaciones a WHERE a.idcliente = :idcliente")
    , @NamedQuery(name = "AdUbicaciones.findByFormato", query = "SELECT a FROM AdUbicaciones a WHERE a.formato = :formato")
    , @NamedQuery(name = "AdUbicaciones.findByTipodir", query = "SELECT a FROM AdUbicaciones a WHERE a.tipodir = :tipodir")
    , @NamedQuery(name = "AdUbicaciones.findByLat", query = "SELECT a FROM AdUbicaciones a WHERE a.lat = :lat")
    , @NamedQuery(name = "AdUbicaciones.findByLon", query = "SELECT a FROM AdUbicaciones a WHERE a.lon = :lon")
    , @NamedQuery(name = "AdUbicaciones.findByFecharegistro", query = "SELECT a FROM AdUbicaciones a WHERE a.fecharegistro = :fecharegistro")
    , @NamedQuery(name = "AdUbicaciones.findByIdasesor", query = "SELECT a FROM AdUbicaciones a WHERE a.idasesor = :idasesor")
    , @NamedQuery(name = "AdUbicaciones.findByLiteuid", query = "SELECT a FROM AdUbicaciones a WHERE a.liteuid = :liteuid")
    , @NamedQuery(name = "AdUbicaciones.findByEstadoCrud", query = "SELECT a FROM AdUbicaciones a WHERE a.estadoCrud = :estadoCrud")
    , @NamedQuery(name = "AdUbicaciones.findByIdvisita", query = "SELECT a FROM AdUbicaciones a WHERE a.idvisita = :idvisita")})
public class AdUbicaciones implements Serializable {

    private static final long serialVersionUID = 1L;
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
    @Size(max = 30)
    @Column(name = "lat", length = 30)
    private String lat;
    @Size(max = 30)
    @Column(name = "lon", length = 30)
    private String lon;
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @Size(max = 8)
    @Column(name = "idasesor", length = 8)
    private String idasesor;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "liteuid", nullable = false, length = 60)
    private String liteuid;
    @Size(max = 20)
    @Column(name = "estado_crud", length = 20)
    private String estadoCrud;
    @Size(max = 60)
    @Column(name = "idvisita", length = 60)
    private String idvisita;

    public AdUbicaciones() {
    }

    public AdUbicaciones(String liteuid) {
        this.liteuid = liteuid;
    }

    public AdUbicaciones(String liteuid, long idcliente, String formato, String tipodir) {
        this.liteuid = liteuid;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getIdasesor() {
        return idasesor;
    }

    public void setIdasesor(String idasesor) {
        this.idasesor = idasesor;
    }

    public String getLiteuid() {
        return liteuid;
    }

    public void setLiteuid(String liteuid) {
        this.liteuid = liteuid;
    }

    public String getEstadoCrud() {
        return estadoCrud;
    }

    public void setEstadoCrud(String estadoCrud) {
        this.estadoCrud = estadoCrud;
    }

    public String getIdvisita() {
        return idvisita;
    }

    public void setIdvisita(String idvisita) {
        this.idvisita = idvisita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (liteuid != null ? liteuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdUbicaciones)) {
            return false;
        }
        AdUbicaciones other = (AdUbicaciones) object;
        if ((this.liteuid == null && other.liteuid != null) || (this.liteuid != null && !this.liteuid.equals(other.liteuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdUbicaciones[ liteuid=" + liteuid + " ]";
    }
    
}
