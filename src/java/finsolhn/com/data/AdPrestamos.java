/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ds010106
 */
@Entity
@Table(name = "ad_prestamos", catalog = "ad_testing", schema = "agenda_digital")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdPrestamos.findAll", query = "SELECT a FROM AdPrestamos a")
    , @NamedQuery(name = "AdPrestamos.findByCuenta", query = "SELECT a FROM AdPrestamos a WHERE a.cuenta = :cuenta")
    , @NamedQuery(name = "AdPrestamos.findByJtsoid", query = "SELECT a FROM AdPrestamos a WHERE a.jtsoid = :jtsoid")
    , @NamedQuery(name = "AdPrestamos.findByIdcliente", query = "SELECT a FROM AdPrestamos a WHERE a.idcliente = :idcliente")
    , @NamedQuery(name = "AdPrestamos.findByMoneda", query = "SELECT a FROM AdPrestamos a WHERE a.moneda = :moneda")
    , @NamedQuery(name = "AdPrestamos.findByMontodesembolsado", query = "SELECT a FROM AdPrestamos a WHERE a.montodesembolsado = :montodesembolsado")
    , @NamedQuery(name = "AdPrestamos.findBySaldocapital", query = "SELECT a FROM AdPrestamos a WHERE a.saldocapital = :saldocapital")
    , @NamedQuery(name = "AdPrestamos.findByInteres", query = "SELECT a FROM AdPrestamos a WHERE a.interes = :interes")
    , @NamedQuery(name = "AdPrestamos.findByFormapago", query = "SELECT a FROM AdPrestamos a WHERE a.formapago = :formapago")
    , @NamedQuery(name = "AdPrestamos.findByNcuotas", query = "SELECT a FROM AdPrestamos a WHERE a.ncuotas = :ncuotas")
    , @NamedQuery(name = "AdPrestamos.findByCuotasres", query = "SELECT a FROM AdPrestamos a WHERE a.cuotasres = :cuotasres")
    , @NamedQuery(name = "AdPrestamos.findByValorcuotavig", query = "SELECT a FROM AdPrestamos a WHERE a.valorcuotavig = :valorcuotavig")
    , @NamedQuery(name = "AdPrestamos.findBySaldoatrasado", query = "SELECT a FROM AdPrestamos a WHERE a.saldoatrasado = :saldoatrasado")
    , @NamedQuery(name = "AdPrestamos.findByFechadesembolso", query = "SELECT a FROM AdPrestamos a WHERE a.fechadesembolso = :fechadesembolso")
    , @NamedQuery(name = "AdPrestamos.findByFechavence", query = "SELECT a FROM AdPrestamos a WHERE a.fechavence = :fechavence")
    , @NamedQuery(name = "AdPrestamos.findByFechaproxima", query = "SELECT a FROM AdPrestamos a WHERE a.fechaproxima = :fechaproxima")
    , @NamedQuery(name = "AdPrestamos.findByDiasatraso", query = "SELECT a FROM AdPrestamos a WHERE a.diasatraso = :diasatraso")
    , @NamedQuery(name = "AdPrestamos.findByIdasesor", query = "SELECT a FROM AdPrestamos a WHERE a.idasesor = :idasesor")
    , @NamedQuery(name = "AdPrestamos.findByNomasesor", query = "SELECT a FROM AdPrestamos a WHERE a.nomasesor = :nomasesor")
    , @NamedQuery(name = "AdPrestamos.findByIdagencia", query = "SELECT a FROM AdPrestamos a WHERE a.idagencia = :idagencia")
    , @NamedQuery(name = "AdPrestamos.findByNomagencia", query = "SELECT a FROM AdPrestamos a WHERE a.nomagencia = :nomagencia")
    , @NamedQuery(name = "AdPrestamos.findByFechaultimavisita", query = "SELECT a FROM AdPrestamos a WHERE a.fechaultimavisita = :fechaultimavisita")
    , @NamedQuery(name = "AdPrestamos.findByFechaultimopago", query = "SELECT a FROM AdPrestamos a WHERE a.fechaultimopago = :fechaultimopago")
    , @NamedQuery(name = "AdPrestamos.findByFechacompromiso", query = "SELECT a FROM AdPrestamos a WHERE a.fechacompromiso = :fechacompromiso")
    , @NamedQuery(name = "AdPrestamos.findByIdcompromiso", query = "SELECT a FROM AdPrestamos a WHERE a.idcompromiso = :idcompromiso")
    , @NamedQuery(name = "AdPrestamos.findByTipoprestamo", query = "SELECT a FROM AdPrestamos a WHERE a.tipoprestamo = :tipoprestamo")})
public class AdPrestamos implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Column(name = "cuenta")
    private Long cuenta;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "jtsoid")
    private Long jtsoid;
    @Column(name = "idcliente")
    private Long idcliente;
    @Column(name = "moneda")
    private Integer moneda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montodesembolsado")
    private BigDecimal montodesembolsado;
    @Column(name = "saldocapital")
    private BigDecimal saldocapital;
    @Column(name = "interes")
    private BigDecimal interes;
    @Size(max = 20)
    @Column(name = "formapago")
    private String formapago;
    @Column(name = "ncuotas")
    private Integer ncuotas;
    @Column(name = "cuotasres")
    private Integer cuotasres;
    @Column(name = "valorcuotavig")
    private BigDecimal valorcuotavig;
    @Column(name = "saldoatrasado")
    private BigDecimal saldoatrasado;
    @Column(name = "fechadesembolso")
    @Temporal(TemporalType.DATE)
    private Date fechadesembolso;
    @Column(name = "fechavence")
    @Temporal(TemporalType.DATE)
    private Date fechavence;
    @Column(name = "fechaproxima")
    @Temporal(TemporalType.DATE)
    private Date fechaproxima;
    @Column(name = "diasatraso")
    private Integer diasatraso;
    @Size(max = 10)
    @Column(name = "idasesor")
    private String idasesor;
    @Size(max = 50)
    @Column(name = "nomasesor")
    private String nomasesor;
    @Column(name = "idagencia")
    private Short idagencia;
    @Size(max = 50)
    @Column(name = "nomagencia")
    private String nomagencia;
    @Column(name = "fechaultimavisita")
    @Temporal(TemporalType.DATE)
    private Date fechaultimavisita;
    @Column(name = "fechaultimopago")
    @Temporal(TemporalType.DATE)
    private Date fechaultimopago;
    @Column(name = "fechacompromiso")
    @Temporal(TemporalType.DATE)
    private Date fechacompromiso;
    @Size(max = 50)
    @Column(name = "idcompromiso")
    private String idcompromiso;
    @Size(max = 20)
    @Column(name = "tipoprestamo")
    private String tipoprestamo;
    @Size(max = 10)
    @Column(name = "reservatipo")
    private String reservatipo;
    @Column(name = "reservacapitalinteres")
    private BigDecimal reservacapitalinteres;
    @Size(max = 10)
    @Column(name = "idgestor")
    private String idgestor;
    public AdPrestamos() {
    }

    public AdPrestamos(Long jtsoid) {
        this.jtsoid = jtsoid;
    }

    public Long getCuenta() {
        return cuenta;
    }

    public void setCuenta(Long cuenta) {
        this.cuenta = cuenta;
    }

    public Long getJtsoid() {
        return jtsoid;
    }

    public void setJtsoid(Long jtsoid) {
        this.jtsoid = jtsoid;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getMoneda() {
        return moneda;
    }

    public void setMoneda(Integer moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getMontodesembolsado() {
        return montodesembolsado;
    }

    public void setMontodesembolsado(BigDecimal montodesembolsado) {
        this.montodesembolsado = montodesembolsado;
    }

    public BigDecimal getSaldocapital() {
        return saldocapital;
    }

    public void setSaldocapital(BigDecimal saldocapital) {
        this.saldocapital = saldocapital;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public Integer getNcuotas() {
        return ncuotas;
    }

    public void setNcuotas(Integer ncuotas) {
        this.ncuotas = ncuotas;
    }

    public Integer getCuotasres() {
        return cuotasres;
    }

    public void setCuotasres(Integer cuotasres) {
        this.cuotasres = cuotasres;
    }

    public BigDecimal getValorcuotavig() {
        return valorcuotavig;
    }

    public void setValorcuotavig(BigDecimal valorcuotavig) {
        this.valorcuotavig = valorcuotavig;
    }

    public BigDecimal getSaldoatrasado() {
        return saldoatrasado;
    }

    public void setSaldoatrasado(BigDecimal saldoatrasado) {
        this.saldoatrasado = saldoatrasado;
    }

    public Date getFechadesembolso() {
        return fechadesembolso;
    }

    public void setFechadesembolso(Date fechadesembolso) {
        this.fechadesembolso = fechadesembolso;
    }

    public Date getFechavence() {
        return fechavence;
    }

    public void setFechavence(Date fechavence) {
        this.fechavence = fechavence;
    }

    public Date getFechaproxima() {
        return fechaproxima;
    }

    public void setFechaproxima(Date fechaproxima) {
        this.fechaproxima = fechaproxima;
    }

    public Integer getDiasatraso() {
        return diasatraso;
    }

    public void setDiasatraso(Integer diasatraso) {
        this.diasatraso = diasatraso;
    }

    public String getIdasesor() {
        return idasesor;
    }

    public void setIdasesor(String idasesor) {
        this.idasesor = idasesor;
    }

    public String getNomasesor() {
        return nomasesor;
    }

    public void setNomasesor(String nomasesor) {
        this.nomasesor = nomasesor;
    }

    public Short getIdagencia() {
        return idagencia;
    }

    public void setIdagencia(Short idagencia) {
        this.idagencia = idagencia;
    }

    public String getNomagencia() {
        return nomagencia;
    }

    public void setNomagencia(String nomagencia) {
        this.nomagencia = nomagencia;
    }

    public Date getFechaultimavisita() {
        return fechaultimavisita;
    }

    public void setFechaultimavisita(Date fechaultimavisita) {
        this.fechaultimavisita = fechaultimavisita;
    }

    public Date getFechaultimopago() {
        return fechaultimopago;
    }

    public void setFechaultimopago(Date fechaultimopago) {
        this.fechaultimopago = fechaultimopago;
    }

    public Date getFechacompromiso() {
        return fechacompromiso;
    }

    public void setFechacompromiso(Date fechacompromiso) {
        this.fechacompromiso = fechacompromiso;
    }

    public String getIdcompromiso() {
        return idcompromiso;
    }

    public void setIdcompromiso(String idcompromiso) {
        this.idcompromiso = idcompromiso;
    }

    public String getTipoprestamo() {
        return tipoprestamo;
    }

    public void setTipoprestamo(String tipoprestamo) {
        this.tipoprestamo = tipoprestamo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jtsoid != null ? jtsoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdPrestamos)) {
            return false;
        }
        AdPrestamos other = (AdPrestamos) object;
        if ((this.jtsoid == null && other.jtsoid != null) || (this.jtsoid != null && !this.jtsoid.equals(other.jtsoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdPrestamos[ jtsoid=" + jtsoid + " ]";
    }

    public String getReservatipo() {
        return reservatipo;
    }

    public void setReservatipo(String reservatipo) {
        this.reservatipo = reservatipo;
    }

    public BigDecimal getReservacapitalinteres() {
        return reservacapitalinteres;
    }

    public void setReservacapitalinteres(BigDecimal reservacapitalinteres) {
        this.reservacapitalinteres = reservacapitalinteres;
    }

    public String getIdgestor() {
        return idgestor;
    }

    public void setIdgestor(String idgestor) {
        this.idgestor = idgestor;
    }
    
}
