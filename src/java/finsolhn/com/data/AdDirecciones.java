/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.data;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ds010106
 */
@Entity
@Table(name = "ad_direcciones", catalog = "ad_testing",schema = "agenda_digital", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idcliente", "formato", "tipodir"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdDirecciones.findAll", query = "SELECT a FROM AdDirecciones a")
    , @NamedQuery(name = "AdDirecciones.findByIdcliente", query = "SELECT a FROM AdDirecciones a WHERE a.adDireccionesPK.idcliente = :idcliente")
    , @NamedQuery(name = "AdDirecciones.findByFormato", query = "SELECT a FROM AdDirecciones a WHERE a.adDireccionesPK.formato = :formato")
    , @NamedQuery(name = "AdDirecciones.findByTipodir", query = "SELECT a FROM AdDirecciones a WHERE a.adDireccionesPK.tipodir = :tipodir")
    , @NamedQuery(name = "AdDirecciones.findByPais", query = "SELECT a FROM AdDirecciones a WHERE a.pais = :pais")
    , @NamedQuery(name = "AdDirecciones.findByIddepartamento", query = "SELECT a FROM AdDirecciones a WHERE a.iddepartamento = :iddepartamento")
    , @NamedQuery(name = "AdDirecciones.findByDepartamento", query = "SELECT a FROM AdDirecciones a WHERE a.departamento = :departamento")
    , @NamedQuery(name = "AdDirecciones.findByIdmunicipio", query = "SELECT a FROM AdDirecciones a WHERE a.idmunicipio = :idmunicipio")
    , @NamedQuery(name = "AdDirecciones.findByMunicipio", query = "SELECT a FROM AdDirecciones a WHERE a.municipio = :municipio")
    , @NamedQuery(name = "AdDirecciones.findByIdbarrio", query = "SELECT a FROM AdDirecciones a WHERE a.idbarrio = :idbarrio")
    , @NamedQuery(name = "AdDirecciones.findByBarrio", query = "SELECT a FROM AdDirecciones a WHERE a.barrio = :barrio")
    , @NamedQuery(name = "AdDirecciones.findByReferenciadir", query = "SELECT a FROM AdDirecciones a WHERE a.referenciadir = :referenciadir")
    , @NamedQuery(name = "AdDirecciones.findByTelefonomovil", query = "SELECT a FROM AdDirecciones a WHERE a.telefonomovil = :telefonomovil")
    , @NamedQuery(name = "AdDirecciones.findByTelefonofijo", query = "SELECT a FROM AdDirecciones a WHERE a.telefonofijo = :telefonofijo")
    , @NamedQuery(name = "AdDirecciones.findByEmail", query = "SELECT a FROM AdDirecciones a WHERE a.email = :email")
    , @NamedQuery(name = "AdDirecciones.findByTipovivienda", query = "SELECT a FROM AdDirecciones a WHERE a.tipovivienda = :tipovivienda")
    , @NamedQuery(name = "AdDirecciones.findByNombrearrendatario", query = "SELECT a FROM AdDirecciones a WHERE a.nombrearrendatario = :nombrearrendatario")
    , @NamedQuery(name = "AdDirecciones.findByTiempohabitar", query = "SELECT a FROM AdDirecciones a WHERE a.tiempohabitar = :tiempohabitar")
    , @NamedQuery(name = "AdDirecciones.findByBloque", query = "SELECT a FROM AdDirecciones a WHERE a.bloque = :bloque")
    , @NamedQuery(name = "AdDirecciones.findByEtapa", query = "SELECT a FROM AdDirecciones a WHERE a.etapa = :etapa")
    , @NamedQuery(name = "AdDirecciones.findByAve", query = "SELECT a FROM AdDirecciones a WHERE a.ave = :ave")
    , @NamedQuery(name = "AdDirecciones.findByCalle", query = "SELECT a FROM AdDirecciones a WHERE a.calle = :calle")
    , @NamedQuery(name = "AdDirecciones.findByCasa", query = "SELECT a FROM AdDirecciones a WHERE a.casa = :casa")
    , @NamedQuery(name = "AdDirecciones.findByLat", query = "SELECT a FROM AdDirecciones a WHERE a.lat = :lat")
    , @NamedQuery(name = "AdDirecciones.findByLon", query = "SELECT a FROM AdDirecciones a WHERE a.lon = :lon")
    , @NamedQuery(name = "AdDirecciones.findByTelarrendador", query = "SELECT a FROM AdDirecciones a WHERE a.telarrendador = :telarrendador")
    , @NamedQuery(name = "AdDirecciones.findByIdsector", query = "SELECT a FROM AdDirecciones a WHERE a.idsector = :idsector")
    , @NamedQuery(name = "AdDirecciones.findBySector", query = "SELECT a FROM AdDirecciones a WHERE a.sector = :sector")})
public class AdDirecciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AdDireccionesPK adDireccionesPK;
    @Size(max = 2)
    @Column(name = "pais", length = 2)
    private String pais;
    @Column(name = "iddepartamento")
    private Short iddepartamento;
    @Size(max = 60)
    @Column(name = "departamento", length = 60)
    private String departamento;
    @Column(name = "idmunicipio")
    private Short idmunicipio;
    @Size(max = 60)
    @Column(name = "municipio", length = 60)
    private String municipio;
    @Column(name = "idbarrio")
    private Short idbarrio;
    @Size(max = 60)
    @Column(name = "barrio", length = 60)
    private String barrio;
    @Size(max = 150)
    @Column(name = "referenciadir", length = 150)
    private String referenciadir;
    @Size(max = 50)
    @Column(name = "telefonomovil", length = 50)
    private String telefonomovil;
    @Size(max = 50)
    @Column(name = "telefonofijo", length = 50)
    private String telefonofijo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 60)
    @Column(name = "email", length = 60)
    private String email;
    @Size(max = 20)
    @Column(name = "tipovivienda", length = 20)
    private String tipovivienda;
    @Size(max = 30)
    @Column(name = "nombrearrendatario", length = 30)
    private String nombrearrendatario;
    @Size(max = 255)
    @Column(name = "tiempohabitar", length = 255)
    private String tiempohabitar;
    @Size(max = 20)
    @Column(name = "bloque", length = 20)
    private String bloque;
    @Size(max = 20)
    @Column(name = "etapa", length = 20)
    private String etapa;
    @Size(max = 20)
    @Column(name = "ave", length = 20)
    private String ave;
    @Size(max = 20)
    @Column(name = "calle", length = 20)
    private String calle;
    @Size(max = 10)
    @Column(name = "casa", length = 10)
    private String casa;
    @Size(max = 30)
    @Column(name = "lat", length = 30)
    private String lat;
    @Size(max = 30)
    @Column(name = "lon", length = 30)
    private String lon;
    @Size(max = 50)
    @Column(name = "telarrendador", length = 50)
    private String telarrendador;
    @Size(max = 3)
    @Column(name = "idsector", length = 3)
    private String idsector;
    @Size(max = 60)
    @Column(name = "sector", length = 60)
    private String sector;

    public AdDirecciones() {
    }

    public AdDirecciones(AdDireccionesPK adDireccionesPK) {
        this.adDireccionesPK = adDireccionesPK;
    }

    public AdDirecciones(long idcliente, String formato, String tipodir) {
        this.adDireccionesPK = new AdDireccionesPK(idcliente, formato, tipodir);
    }

    public AdDireccionesPK getAdDireccionesPK() {
        return adDireccionesPK;
    }

    public void setAdDireccionesPK(AdDireccionesPK adDireccionesPK) {
        this.adDireccionesPK = adDireccionesPK;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Short getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Short iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Short getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Short idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Short getIdbarrio() {
        return idbarrio;
    }

    public void setIdbarrio(Short idbarrio) {
        this.idbarrio = idbarrio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getReferenciadir() {
        return referenciadir;
    }

    public void setReferenciadir(String referenciadir) {
        this.referenciadir = referenciadir;
    }

    public String getTelefonomovil() {
        return telefonomovil;
    }

    public void setTelefonomovil(String telefonomovil) {
        this.telefonomovil = telefonomovil;
    }

    public String getTelefonofijo() {
        return telefonofijo;
    }

    public void setTelefonofijo(String telefonofijo) {
        this.telefonofijo = telefonofijo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipovivienda() {
        return tipovivienda;
    }

    public void setTipovivienda(String tipovivienda) {
        this.tipovivienda = tipovivienda;
    }

    public String getNombrearrendatario() {
        return nombrearrendatario;
    }

    public void setNombrearrendatario(String nombrearrendatario) {
        this.nombrearrendatario = nombrearrendatario;
    }

    public String getTiempohabitar() {
        return tiempohabitar;
    }

    public void setTiempohabitar(String tiempohabitar) {
        this.tiempohabitar = tiempohabitar;
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getAve() {
        return ave;
    }

    public void setAve(String ave) {
        this.ave = ave;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
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

    public String getTelarrendador() {
        return telarrendador;
    }

    public void setTelarrendador(String telarrendador) {
        this.telarrendador = telarrendador;
    }

    public String getIdsector() {
        return idsector;
    }

    public void setIdsector(String idsector) {
        this.idsector = idsector;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adDireccionesPK != null ? adDireccionesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdDirecciones)) {
            return false;
        }
        AdDirecciones other = (AdDirecciones) object;
        if ((this.adDireccionesPK == null && other.adDireccionesPK != null) || (this.adDireccionesPK != null && !this.adDireccionesPK.equals(other.adDireccionesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdDirecciones[ adDireccionesPK=" + adDireccionesPK + " ]";
    }
    
}
