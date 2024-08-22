/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.data;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ad_solicitudes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdSolicitudes.findAll", query = "SELECT a FROM AdSolicitudes a"),
    @NamedQuery(name = "AdSolicitudes.findByLiteuid", query = "SELECT a FROM AdSolicitudes a WHERE a.liteuid = :liteuid"),
    @NamedQuery(name = "AdSolicitudes.findByLiteuidcasc", query = "SELECT a FROM AdSolicitudes a WHERE a.liteuidcasc = :liteuidcasc"),
    @NamedQuery(name = "AdSolicitudes.findByFecharegistro", query = "SELECT a FROM AdSolicitudes a WHERE a.fecharegistro = :fecharegistro"),
    @NamedQuery(name = "AdSolicitudes.findBySecuencia", query = "SELECT a FROM AdSolicitudes a WHERE a.secuencia = :secuencia"),
    @NamedQuery(name = "AdSolicitudes.findByAgenciaid", query = "SELECT a FROM AdSolicitudes a WHERE a.agenciaid = :agenciaid"),
    @NamedQuery(name = "AdSolicitudes.findByUsuariotopaz", query = "SELECT a FROM AdSolicitudes a WHERE a.usuariotopaz = :usuariotopaz"),
    @NamedQuery(name = "AdSolicitudes.findBySucesion", query = "SELECT a FROM AdSolicitudes a WHERE a.sucesion = :sucesion"),
    @NamedQuery(name = "AdSolicitudes.findByNombresucesor", query = "SELECT a FROM AdSolicitudes a WHERE a.nombresucesor = :nombresucesor"),
    @NamedQuery(name = "AdSolicitudes.findByParentescosucesor", query = "SELECT a FROM AdSolicitudes a WHERE a.parentescosucesor = :parentescosucesor"),
    @NamedQuery(name = "AdSolicitudes.findByMontosolicitado", query = "SELECT a FROM AdSolicitudes a WHERE a.montosolicitado = :montosolicitado"),
    @NamedQuery(name = "AdSolicitudes.findByPlazo", query = "SELECT a FROM AdSolicitudes a WHERE a.plazo = :plazo"),
    @NamedQuery(name = "AdSolicitudes.findByTi", query = "SELECT a FROM AdSolicitudes a WHERE a.ti = :ti"),
    @NamedQuery(name = "AdSolicitudes.findByFormapago", query = "SELECT a FROM AdSolicitudes a WHERE a.formapago = :formapago"),
    @NamedQuery(name = "AdSolicitudes.findByCuota", query = "SELECT a FROM AdSolicitudes a WHERE a.cuota = :cuota"),
    @NamedQuery(name = "AdSolicitudes.findByCuotasolicitada", query = "SELECT a FROM AdSolicitudes a WHERE a.cuotasolicitada = :cuotasolicitada"),
    @NamedQuery(name = "AdSolicitudes.findByFechapagosolicitada", query = "SELECT a FROM AdSolicitudes a WHERE a.fechapagosolicitada = :fechapagosolicitada"),
    @NamedQuery(name = "AdSolicitudes.findByDepartamento", query = "SELECT a FROM AdSolicitudes a WHERE a.departamento = :departamento"),
    @NamedQuery(name = "AdSolicitudes.findByMunicipio", query = "SELECT a FROM AdSolicitudes a WHERE a.municipio = :municipio"),
    @NamedQuery(name = "AdSolicitudes.findByBarrio", query = "SELECT a FROM AdSolicitudes a WHERE a.barrio = :barrio"),
    @NamedQuery(name = "AdSolicitudes.findBySector", query = "SELECT a FROM AdSolicitudes a WHERE a.sector = :sector"),
    @NamedQuery(name = "AdSolicitudes.findByFechafirma", query = "SELECT a FROM AdSolicitudes a WHERE a.fechafirma = :fechafirma"),
    @NamedQuery(name = "AdSolicitudes.findByGarantia", query = "SELECT a FROM AdSolicitudes a WHERE a.garantia = :garantia"),
    @NamedQuery(name = "AdSolicitudes.findByEstadosolicitud", query = "SELECT a FROM AdSolicitudes a WHERE a.estadosolicitud = :estadosolicitud"),
    @NamedQuery(name = "AdSolicitudes.findByMotivorechazo", query = "SELECT a FROM AdSolicitudes a WHERE a.motivorechazo = :motivorechazo"),
    @NamedQuery(name = "AdSolicitudes.findByComentario", query = "SELECT a FROM AdSolicitudes a WHERE a.comentario = :comentario"),
    @NamedQuery(name = "AdSolicitudes.findByComentariojefe", query = "SELECT a FROM AdSolicitudes a WHERE a.comentariojefe = :comentariojefe"),
    @NamedQuery(name = "AdSolicitudes.findByEstadoCrud", query = "SELECT a FROM AdSolicitudes a WHERE a.estadoCrud = :estadoCrud")})
public class AdSolicitudes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "liteuid")
    private String liteuid;
    @Size(max = 60)
    @Column(name = "liteuidcasc")
    private String liteuidcasc;
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @Basic(optional = false)
    @Column(name = "secuencia")
    private int secuencia;
    @Size(max = 20)
    @Column(name = "agenciaid")
    private String agenciaid;
    @Size(max = 20)
    @Column(name = "usuariotopaz")
    private String usuariotopaz;
    @Size(max = 20)
    @Column(name = "sucesion")
    private String sucesion;
    @Size(max = 100)
    @Column(name = "nombresucesor")
    private String nombresucesor;
    @Size(max = 20)
    @Column(name = "parentescosucesor")
    private String parentescosucesor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montosolicitado")
    private BigDecimal montosolicitado;
    @Column(name = "plazo")
    private Long plazo;
    @Column(name = "ti")
    private BigDecimal ti;
    @Size(max = 20)
    @Column(name = "formapago")
    private String formapago;
    @Column(name = "cuota")
    private BigDecimal cuota;
    @Column(name = "cuotasolicitada")
    private BigDecimal cuotasolicitada;
    @Column(name = "fechapagosolicitada")
    @Temporal(TemporalType.DATE)
    private Date fechapagosolicitada;
    @Size(max = 50)
    @Column(name = "departamento")
    private String departamento;
    @Size(max = 50)
    @Column(name = "municipio")
    private String municipio;
    @Size(max = 50)
    @Column(name = "barrio")
    private String barrio;
    @Size(max = 50)
    @Column(name = "sector")
    private String sector;
    @Size(max = 50)
    @Column(name = "fechafirma")
    private String fechafirma;
    @Size(max = 50)
    @Column(name = "garantia")
    private String garantia;
    @Size(max = 20)
    @Column(name = "estadosolicitud")
    private String estadosolicitud;
    @Size(max = 50)
    @Column(name = "motivorechazo")
    private String motivorechazo;
    @Size(max = 500)
    @Column(name = "comentario")
    private String comentario;
    @Size(max = 500)
    @Column(name = "comentariojefe")
    private String comentariojefe;
    @Size(max = 20)
    @Column(name = "estado_crud")
    private String estadoCrud;

    public AdSolicitudes() {
    }

    public AdSolicitudes(String liteuid) {
        this.liteuid = liteuid;
    }

    public AdSolicitudes(String liteuid, int secuencia) {
        this.liteuid = liteuid;
        this.secuencia = secuencia;
    }

    public String getLiteuid() {
        return liteuid;
    }

    public void setLiteuid(String liteuid) {
        this.liteuid = liteuid;
    }

    public String getLiteuidcasc() {
        return liteuidcasc;
    }

    public void setLiteuidcasc(String liteuidcasc) {
        this.liteuidcasc = liteuidcasc;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getAgenciaid() {
        return agenciaid;
    }

    public void setAgenciaid(String agenciaid) {
        this.agenciaid = agenciaid;
    }

    public String getUsuariotopaz() {
        return usuariotopaz;
    }

    public void setUsuariotopaz(String usuariotopaz) {
        this.usuariotopaz = usuariotopaz;
    }

    public String getSucesion() {
        return sucesion;
    }

    public void setSucesion(String sucesion) {
        this.sucesion = sucesion;
    }

    public String getNombresucesor() {
        return nombresucesor;
    }

    public void setNombresucesor(String nombresucesor) {
        this.nombresucesor = nombresucesor;
    }

    public String getParentescosucesor() {
        return parentescosucesor;
    }

    public void setParentescosucesor(String parentescosucesor) {
        this.parentescosucesor = parentescosucesor;
    }

    public BigDecimal getMontosolicitado() {
        return montosolicitado;
    }

    public void setMontosolicitado(BigDecimal montosolicitado) {
        this.montosolicitado = montosolicitado;
    }

    public Long getPlazo() {
        return plazo;
    }

    public void setPlazo(Long plazo) {
        this.plazo = plazo;
    }

    public BigDecimal getTi() {
        return ti;
    }

    public void setTi(BigDecimal ti) {
        this.ti = ti;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public BigDecimal getCuota() {
        return cuota;
    }

    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }

    public BigDecimal getCuotasolicitada() {
        return cuotasolicitada;
    }

    public void setCuotasolicitada(BigDecimal cuotasolicitada) {
        this.cuotasolicitada = cuotasolicitada;
    }

    public Date getFechapagosolicitada() {
        return fechapagosolicitada;
    }

    public void setFechapagosolicitada(Date fechapagosolicitada) {
        this.fechapagosolicitada = fechapagosolicitada;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getFechafirma() {
        return fechafirma;
    }

    public void setFechafirma(String fechafirma) {
        this.fechafirma = fechafirma;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getEstadosolicitud() {
        return estadosolicitud;
    }

    public void setEstadosolicitud(String estadosolicitud) {
        this.estadosolicitud = estadosolicitud;
    }

    public String getMotivorechazo() {
        return motivorechazo;
    }

    public void setMotivorechazo(String motivorechazo) {
        this.motivorechazo = motivorechazo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentariojefe() {
        return comentariojefe;
    }

    public void setComentariojefe(String comentariojefe) {
        this.comentariojefe = comentariojefe;
    }

    public String getEstadoCrud() {
        return estadoCrud;
    }

    public void setEstadoCrud(String estadoCrud) {
        this.estadoCrud = estadoCrud;
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
        if (!(object instanceof AdSolicitudes)) {
            return false;
        }
        AdSolicitudes other = (AdSolicitudes) object;
        if ((this.liteuid == null && other.liteuid != null) || (this.liteuid != null && !this.liteuid.equals(other.liteuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdSolicitudes[ liteuid=" + liteuid + " ]";
    }
    
}
