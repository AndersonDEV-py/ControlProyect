/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.data;

//import groovy.lang.Immutable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.primefaces.PrimeFaces;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author ds010106
 */
@Entity
@Table(name = "ad_v_solicitudes", catalog = "ad_testing", schema = "agenda_digital")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdVSolicitudes.findAll", query = "SELECT a FROM AdVSolicitudes a"),
    @NamedQuery(name = "AdVSolicitudes.findByLiteuidsol", query = "SELECT a FROM AdVSolicitudes a WHERE a.liteuidsol = :liteuidsol"),
    @NamedQuery(name = "AdVSolicitudes.findByLiteudcasc", query = "SELECT a FROM AdVSolicitudes a WHERE a.liteudcasc = :liteudcasc"),
    @NamedQuery(name = "AdVSolicitudes.findByFecharegistro", query = "SELECT a FROM AdVSolicitudes a WHERE a.fecharegistro = :fecharegistro"),
    @NamedQuery(name = "AdVSolicitudes.findBySecuencia", query = "SELECT a FROM AdVSolicitudes a WHERE a.secuencia = :secuencia"),
    @NamedQuery(name = "AdVSolicitudes.findByAgenciaid", query = "SELECT a FROM AdVSolicitudes a WHERE a.agenciaid = :agenciaid"),
    @NamedQuery(name = "AdVSolicitudes.findByAgenciaDet", query = "SELECT a FROM AdVSolicitudes a WHERE a.agenciaDet = :agenciaDet"),
    @NamedQuery(name = "AdVSolicitudes.findByUsuariotopaz", query = "SELECT a FROM AdVSolicitudes a WHERE a.usuariotopaz = :usuariotopaz"),
    @NamedQuery(name = "AdVSolicitudes.findByNombreasesor", query = "SELECT a FROM AdVSolicitudes a WHERE a.nombreasesor = :nombreasesor"),
    @NamedQuery(name = "AdVSolicitudes.findByMontosolicitado", query = "SELECT a FROM AdVSolicitudes a WHERE a.montosolicitado = :montosolicitado"),
    @NamedQuery(name = "AdVSolicitudes.findByPlazo", query = "SELECT a FROM AdVSolicitudes a WHERE a.plazo = :plazo"),
    @NamedQuery(name = "AdVSolicitudes.findByTi", query = "SELECT a FROM AdVSolicitudes a WHERE a.ti = :ti"),
    @NamedQuery(name = "AdVSolicitudes.findByFormapago", query = "SELECT a FROM AdVSolicitudes a WHERE a.formapago = :formapago"),
    @NamedQuery(name = "AdVSolicitudes.findByCuotasolicitada", query = "SELECT a FROM AdVSolicitudes a WHERE a.cuotasolicitada = :cuotasolicitada"),
    @NamedQuery(name = "AdVSolicitudes.findByCuota", query = "SELECT a FROM AdVSolicitudes a WHERE a.cuota = :cuota"),
    @NamedQuery(name = "AdVSolicitudes.findByGarantia", query = "SELECT a FROM AdVSolicitudes a WHERE a.garantia = :garantia"),
    @NamedQuery(name = "AdVSolicitudes.findByFechafirma", query = "SELECT a FROM AdVSolicitudes a WHERE a.fechafirma = :fechafirma"),
    @NamedQuery(name = "AdVSolicitudes.findByEstadosolicitud", query = "SELECT a FROM AdVSolicitudes a WHERE a.estadosolicitud = :estadosolicitud"),
    @NamedQuery(name = "AdVSolicitudes.findByMotivorechazo", query = "SELECT a FROM AdVSolicitudes a WHERE a.motivorechazo = :motivorechazo"),
    @NamedQuery(name = "AdVSolicitudes.findByComentario", query = "SELECT a FROM AdVSolicitudes a WHERE a.comentario = :comentario"),
    @NamedQuery(name = "AdVSolicitudes.findByFecharegistroCliente", query = "SELECT a FROM AdVSolicitudes a WHERE a.fecharegistroCliente = :fecharegistroCliente"),
    @NamedQuery(name = "AdVSolicitudes.findByIdcliente", query = "SELECT a FROM AdVSolicitudes a WHERE a.idcliente = :idcliente"),
    @NamedQuery(name = "AdVSolicitudes.findByTipocliente", query = "SELECT a FROM AdVSolicitudes a WHERE a.tipocliente = :tipocliente"),
    @NamedQuery(name = "AdVSolicitudes.findByTipoclienteDet", query = "SELECT a FROM AdVSolicitudes a WHERE a.tipoclienteDet = :tipoclienteDet"),
    @NamedQuery(name = "AdVSolicitudes.findByIdentidad", query = "SELECT a FROM AdVSolicitudes a WHERE a.identidad = :identidad"),
    @NamedQuery(name = "AdVSolicitudes.findByNrodocumentojuridico", query = "SELECT a FROM AdVSolicitudes a WHERE a.nrodocumentojuridico = :nrodocumentojuridico"),
    @NamedQuery(name = "AdVSolicitudes.findByRazonsocialjuridico", query = "SELECT a FROM AdVSolicitudes a WHERE a.razonsocialjuridico = :razonsocialjuridico"),
    @NamedQuery(name = "AdVSolicitudes.findByTiemporelacioninstitucion", query = "SELECT a FROM AdVSolicitudes a WHERE a.tiemporelacioninstitucion = :tiemporelacioninstitucion"),
    @NamedQuery(name = "AdVSolicitudes.findByTiemporelacioninstitucionDet", query = "SELECT a FROM AdVSolicitudes a WHERE a.tiemporelacioninstitucionDet = :tiemporelacioninstitucionDet"),
    @NamedQuery(name = "AdVSolicitudes.findByNombre1", query = "SELECT a FROM AdVSolicitudes a WHERE a.nombre1 = :nombre1"),
    @NamedQuery(name = "AdVSolicitudes.findByNombre2", query = "SELECT a FROM AdVSolicitudes a WHERE a.nombre2 = :nombre2"),
    @NamedQuery(name = "AdVSolicitudes.findByApellido1", query = "SELECT a FROM AdVSolicitudes a WHERE a.apellido1 = :apellido1"),
    @NamedQuery(name = "AdVSolicitudes.findByApellido2", query = "SELECT a FROM AdVSolicitudes a WHERE a.apellido2 = :apellido2"),
    @NamedQuery(name = "AdVSolicitudes.findByFechanacimiento", query = "SELECT a FROM AdVSolicitudes a WHERE a.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "AdVSolicitudes.findByGeneros", query = "SELECT a FROM AdVSolicitudes a WHERE a.generos = :generos"),
    @NamedQuery(name = "AdVSolicitudes.findByDirecciontelefonomovil", query = "SELECT a FROM AdVSolicitudes a WHERE a.direcciontelefonomovil = :direcciontelefonomovil"),
    @NamedQuery(name = "AdVSolicitudes.findByGpslatitud", query = "SELECT a FROM AdVSolicitudes a WHERE a.gpslatitud = :gpslatitud"),
    @NamedQuery(name = "AdVSolicitudes.findByGpslongitud", query = "SELECT a FROM AdVSolicitudes a WHERE a.gpslongitud = :gpslongitud"),
    @NamedQuery(name = "AdVSolicitudes.findByPuntoreferencia", query = "SELECT a FROM AdVSolicitudes a WHERE a.puntoreferencia = :puntoreferencia"),
    @NamedQuery(name = "AdVSolicitudes.findByGpsfecha", query = "SELECT a FROM AdVSolicitudes a WHERE a.gpsfecha = :gpsfecha"),
    @NamedQuery(name = "AdVSolicitudes.findByNegnombrenegocio", query = "SELECT a FROM AdVSolicitudes a WHERE a.negnombrenegocio = :negnombrenegocio"),
    @NamedQuery(name = "AdVSolicitudes.findByNegtiponegocio", query = "SELECT a FROM AdVSolicitudes a WHERE a.negtiponegocio = :negtiponegocio"),
    @NamedQuery(name = "AdVSolicitudes.findByNegtiponegocioDet", query = "SELECT a FROM AdVSolicitudes a WHERE a.negtiponegocioDet = :negtiponegocioDet"),
    @NamedQuery(name = "AdVSolicitudes.findByNegcategoriaactividad", query = "SELECT a FROM AdVSolicitudes a WHERE a.negcategoriaactividad = :negcategoriaactividad"),
    @NamedQuery(name = "AdVSolicitudes.findByNegcategoriaactividadDet", query = "SELECT a FROM AdVSolicitudes a WHERE a.negcategoriaactividadDet = :negcategoriaactividadDet"),
    @NamedQuery(name = "AdVSolicitudes.findByNegactividadinterna", query = "SELECT a FROM AdVSolicitudes a WHERE a.negactividadinterna = :negactividadinterna"),
    @NamedQuery(name = "AdVSolicitudes.findByNegactividadinternaDet", query = "SELECT a FROM AdVSolicitudes a WHERE a.negactividadinternaDet = :negactividadinternaDet"),
    @NamedQuery(name = "AdVSolicitudes.findByNegexperienciaenactividad", query = "SELECT a FROM AdVSolicitudes a WHERE a.negexperienciaenactividad = :negexperienciaenactividad"),
    @NamedQuery(name = "AdVSolicitudes.findByNegventasmensuales", query = "SELECT a FROM AdVSolicitudes a WHERE a.negventasmensuales = :negventasmensuales"),
    @NamedQuery(name = "AdVSolicitudes.findByNeglatitud", query = "SELECT a FROM AdVSolicitudes a WHERE a.neglatitud = :neglatitud"),
    @NamedQuery(name = "AdVSolicitudes.findByNeglongitud", query = "SELECT a FROM AdVSolicitudes a WHERE a.neglongitud = :neglongitud"),
    @NamedQuery(name = "AdVSolicitudes.findByNegfechacapturaubicacion", query = "SELECT a FROM AdVSolicitudes a WHERE a.negfechacapturaubicacion = :negfechacapturaubicacion"),
    @NamedQuery(name = "AdVSolicitudes.findByNegpuntoreferencia", query = "SELECT a FROM AdVSolicitudes a WHERE a.negpuntoreferencia = :negpuntoreferencia"),
    @NamedQuery(name = "AdVSolicitudes.findByFechafirmaCasc", query = "SELECT a FROM AdVSolicitudes a WHERE a.fechafirmaCasc = :fechafirmaCasc")})
//@Immutable
public class AdVSolicitudes implements Serializable {

    @Size(max = 2147483647)
    @Column(name = "nombrecompleto")
    private String nombrecompleto;

    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 60)
    @Column(name = "liteuidsol", length = 60)
    private String liteuidsol;
    @Size(max = 60)
    @Column(name = "liteudcasc", length = 60)
    private String liteudcasc;
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @Column(name = "secuencia")
    private Integer secuencia;
    @Size(max = 20)
    @Column(name = "agenciaid", length = 20)
    private String agenciaid;
    @Size(max = 100)
    @Column(name = "agencia_det", length = 100)
    private String agenciaDet;
    @Size(max = 20)
    @Column(name = "usuariotopaz", length = 20)
    private String usuariotopaz;
    @Size(max = 100)
    @Column(name = "nombreasesor", length = 100)
    private String nombreasesor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montosolicitado", precision = 9, scale = 2)
    private BigDecimal montosolicitado;
    @Column(name = "plazo")
    private Long plazo;
    @Column(name = "ti", precision = 9, scale = 2)
    private BigDecimal ti;
    @Size(max = 20)
    @Column(name = "formapago", length = 20)
    private String formapago;
    @Size(max = 500)
    @Column(name = "formapago_det")
    private String formapagoDet;
    @Column(name = "cuotasolicitada", precision = 9, scale = 2)
    private BigDecimal cuotasolicitada;
    @Column(name = "cuota", precision = 9, scale = 2)
    private BigDecimal cuota;
    @Size(max = 50)
    @Column(name = "garantia", length = 50)
    private String garantia;
    @Size(max = 50)
    @Column(name = "fechafirma", length = 50)
    private String fechafirma;
    @Size(max = 20)
    @Column(name = "estadosolicitud", length = 20)
    private String estadosolicitud;
    @Size(max = 50)
    @Column(name = "motivorechazo", length = 50)
    private String motivorechazo;
    @Size(max = 500)
    @Column(name = "comentario", length = 500)
    private String comentario;
    @Size(max = 500)
    @Column(name = "comentariojefe")
    private String comentariojefe;
    @Column(name = "fecharegistro_cliente")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistroCliente;
    @Column(name = "idcliente")
    private Long idcliente;
    @Size(max = 20)
    @Column(name = "tipocliente", length = 20)
    private String tipocliente;
    @Size(max = 2147483647)
    @Column(name = "tipocliente_det", length = 2147483647)
    private String tipoclienteDet;
    @Size(max = 30)
    @Column(name = "identidad", length = 30)
    private String identidad;
    @Size(max = 30)
    @Column(name = "nrodocumentojuridico", length = 30)
    private String nrodocumentojuridico;
    @Size(max = 100)
    @Column(name = "razonsocialjuridico", length = 100)
    private String razonsocialjuridico;
    @Size(max = 20)
    @Column(name = "tiemporelacioninstitucion", length = 20)
    private String tiemporelacioninstitucion;
    @Size(max = 500)
    @Column(name = "tiemporelacioninstitucion_det", length = 500)
    private String tiemporelacioninstitucionDet;
    @Size(max = 50)
    @Column(name = "nombre1", length = 50)
    private String nombre1;
    @Size(max = 50)
    @Column(name = "nombre2", length = 50)
    private String nombre2;
    @Size(max = 50)
    @Column(name = "apellido1", length = 50)
    private String apellido1;
    @Size(max = 50)
    @Column(name = "apellido2", length = 50)
    private String apellido2;
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Size(max = 20)
    @Column(name = "generos", length = 20)
    private String generos;
    @Size(max = 20)
    @Column(name = "direcciontelefonomovil", length = 20)
    private String direcciontelefonomovil;
    @Size(max = 30)
    @Column(name = "gpslatitud", length = 30)
    private String gpslatitud;
    @Size(max = 30)
    @Column(name = "gpslongitud", length = 30)
    private String gpslongitud;
    @Size(max = 100)
    @Column(name = "puntoreferencia", length = 100)
    private String puntoreferencia;
    @Column(name = "gpsfecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gpsfecha;
    @Size(max = 50)
    @Column(name = "negnombrenegocio", length = 50)
    private String negnombrenegocio;
    @Size(max = 25)
    @Column(name = "negtiponegocio", length = 25)
    private String negtiponegocio;
    @Size(max = 500)
    @Column(name = "negtiponegocio_det", length = 500)
    private String negtiponegocioDet;
    @Size(max = 40)
    @Column(name = "negcategoriaactividad", length = 40)
    private String negcategoriaactividad;
    @Size(max = 500)
    @Column(name = "negcategoriaactividad_det", length = 500)
    private String negcategoriaactividadDet;
    @Size(max = 60)
    @Column(name = "negactividadinterna", length = 60)
    private String negactividadinterna;
    @Size(max = 500)
    @Column(name = "negactividadinterna_det", length = 500)
    private String negactividadinternaDet;
    @Size(max = 100)
    @Column(name = "negexperienciaenactividad", length = 100)
    private String negexperienciaenactividad;
    @Column(name = "negventasmensuales", precision = 12, scale = 2)
    private BigDecimal negventasmensuales;
    @Size(max = 30)
    @Column(name = "neglatitud", length = 30)
    private String neglatitud;
    @Size(max = 30)
    @Column(name = "neglongitud", length = 30)
    private String neglongitud;
    @Column(name = "negfechacapturaubicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date negfechacapturaubicacion;
    @Size(max = 100)
    @Column(name = "negpuntoreferencia", length = 100)
    private String negpuntoreferencia;
    @Size(max = 30)
    @Column(name = "fechafirma_casc", length = 30)
    private String fechafirmaCasc;

    @Column(name = "diastranscurridos")
    private Integer diastranscurridos;
     
    public AdVSolicitudes() {
        nombre1="";
        nombre2="";
        apellido1="";
        apellido2="";
    }

    public String getLiteuidsol() {
        return liteuidsol;
    }

    public void setLiteuidsol(String liteuidsol) {
        this.liteuidsol = liteuidsol;
    }

    public String getLiteudcasc() {
        return liteudcasc;
    }

    public void setLiteudcasc(String liteudcasc) {
        this.liteudcasc = liteudcasc;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public String getAgenciaid() {
        return agenciaid;
    }

    public void setAgenciaid(String agenciaid) {
        this.agenciaid = agenciaid;
    }

    public String getAgenciaDet() {
        return agenciaDet;
    }

    public void setAgenciaDet(String agenciaDet) {
        this.agenciaDet = agenciaDet;
    }

    public String getUsuariotopaz() {
        return usuariotopaz;
    }

    public void setUsuariotopaz(String usuariotopaz) {
        this.usuariotopaz = usuariotopaz;
    }

    public String getNombreasesor() {
        return nombreasesor;
    }

    public void setNombreasesor(String nombreasesor) {
        this.nombreasesor = nombreasesor;
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

    public BigDecimal getCuotasolicitada() {
        return cuotasolicitada;
    }

    public void setCuotasolicitada(BigDecimal cuotasolicitada) {
        this.cuotasolicitada = cuotasolicitada;
    }

    public BigDecimal getCuota() {
        return cuota;
    }

    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getFechafirma() {
        return fechafirma;
    }

    public void setFechafirma(String fechafirma) {
        this.fechafirma = fechafirma;
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

    public Date getFecharegistroCliente() {
        return fecharegistroCliente;
    }

    public void setFecharegistroCliente(Date fecharegistroCliente) {
        this.fecharegistroCliente = fecharegistroCliente;
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

    public String getTipoclienteDet() {
        return tipoclienteDet;
    }

    public void setTipoclienteDet(String tipoclienteDet) {
        this.tipoclienteDet = tipoclienteDet;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public String getNrodocumentojuridico() {
        return nrodocumentojuridico;
    }

    public void setNrodocumentojuridico(String nrodocumentojuridico) {
        this.nrodocumentojuridico = nrodocumentojuridico;
    }

    public String getRazonsocialjuridico() {
        return razonsocialjuridico;
    }

    public void setRazonsocialjuridico(String razonsocialjuridico) {
        this.razonsocialjuridico = razonsocialjuridico;
    }

    public String getTiemporelacioninstitucion() {
        return tiemporelacioninstitucion;
    }

    public void setTiemporelacioninstitucion(String tiemporelacioninstitucion) {
        this.tiemporelacioninstitucion = tiemporelacioninstitucion;
    }

    public String getTiemporelacioninstitucionDet() {
        return tiemporelacioninstitucionDet;
    }

    public void setTiemporelacioninstitucionDet(String tiemporelacioninstitucionDet) {
        this.tiemporelacioninstitucionDet = tiemporelacioninstitucionDet;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public String getDirecciontelefonomovil() {
        return direcciontelefonomovil;
    }

    public void setDirecciontelefonomovil(String direcciontelefonomovil) {
        this.direcciontelefonomovil = direcciontelefonomovil;
    }

    public String getGpslatitud() {
        return gpslatitud;
    }

    public void setGpslatitud(String gpslatitud) {
        this.gpslatitud = gpslatitud;
    }

    public String getGpslongitud() {
        return gpslongitud;
    }

    public void setGpslongitud(String gpslongitud) {
        this.gpslongitud = gpslongitud;
    }

    public String getPuntoreferencia() {
        return puntoreferencia;
    }

    public void setPuntoreferencia(String puntoreferencia) {
        this.puntoreferencia = puntoreferencia;
    }

    public Date getGpsfecha() {
        return gpsfecha;
    }

    public void setGpsfecha(Date gpsfecha) {
        this.gpsfecha = gpsfecha;
    }

    public String getNegnombrenegocio() {
        return negnombrenegocio;
    }

    public void setNegnombrenegocio(String negnombrenegocio) {
        this.negnombrenegocio = negnombrenegocio;
    }

    public String getNegtiponegocio() {
        return negtiponegocio;
    }

    public void setNegtiponegocio(String negtiponegocio) {
        this.negtiponegocio = negtiponegocio;
    }

    public String getNegtiponegocioDet() {
        return negtiponegocioDet;
    }

    public void setNegtiponegocioDet(String negtiponegocioDet) {
        this.negtiponegocioDet = negtiponegocioDet;
    }

    public String getNegcategoriaactividad() {
        return negcategoriaactividad;
    }

    public void setNegcategoriaactividad(String negcategoriaactividad) {
        this.negcategoriaactividad = negcategoriaactividad;
    }

    public String getNegcategoriaactividadDet() {
        return negcategoriaactividadDet;
    }

    public void setNegcategoriaactividadDet(String negcategoriaactividadDet) {
        this.negcategoriaactividadDet = negcategoriaactividadDet;
    }

    public String getNegactividadinterna() {
        return negactividadinterna;
    }

    public void setNegactividadinterna(String negactividadinterna) {
        this.negactividadinterna = negactividadinterna;
    }

    public String getNegactividadinternaDet() {
        return negactividadinternaDet;
    }

    public void setNegactividadinternaDet(String negactividadinternaDet) {
        this.negactividadinternaDet = negactividadinternaDet;
    }

    public String getNegexperienciaenactividad() {
        return negexperienciaenactividad;
    }

    public void setNegexperienciaenactividad(String negexperienciaenactividad) {
        this.negexperienciaenactividad = negexperienciaenactividad;
    }

    public BigDecimal getNegventasmensuales() {
        return negventasmensuales;
    }

    public void setNegventasmensuales(BigDecimal negventasmensuales) {
        this.negventasmensuales = negventasmensuales;
    }

    public String getNeglatitud() {
        return neglatitud;
    }

    public void setNeglatitud(String neglatitud) {
        this.neglatitud = neglatitud;
    }

    public String getNeglongitud() {
        return neglongitud;
    }

    public void setNeglongitud(String neglongitud) {
        this.neglongitud = neglongitud;
    }

    public Date getNegfechacapturaubicacion() {
        return negfechacapturaubicacion;
    }

    public void setNegfechacapturaubicacion(Date negfechacapturaubicacion) {
        this.negfechacapturaubicacion = negfechacapturaubicacion;
    }

    public String getNegpuntoreferencia() {
        return negpuntoreferencia;
    }

    public void setNegpuntoreferencia(String negpuntoreferencia) {
        this.negpuntoreferencia = negpuntoreferencia;
    }

    public String getFechafirmaCasc() {
        return fechafirmaCasc;
    }

    public void setFechafirmaCasc(String fechafirmaCasc) {
        this.fechafirmaCasc = fechafirmaCasc;
    }
  
    public String getNombreCorto()
    {
        return  getNombre1()+" "
                +(getApellido1().length()>0?getApellido1()+" ":"");
    }

   @Transient
    private MapModel simpleModelH;
    
    public void iniMapH() {

        Marker markerH;
        simpleModelH = new DefaultMapModel();

        if (gpslatitud.equalsIgnoreCase("0")) {
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('wdlUbicacion').hide();");
            PrimeFaces.current().executeScript("PF('wdlUbicacionH').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Hay Ubicacion Registrada"));
            return;
        }
        //Shared coordinates
        LatLng coord1 = new LatLng(Double.parseDouble(gpslatitud), Double.parseDouble(gpslongitud));
        //Draggable
        markerH = new Marker(coord1, "Finsol");

        simpleModelH.addOverlay(markerH);

    }
    @Transient
    private MapModel simpleModelN;
    

    public void iniMapN() {

        Marker markerN;
        simpleModelN = new DefaultMapModel();

        if (neglatitud.equalsIgnoreCase("0")) {
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('wdlUbicacion').hide();");
            PrimeFaces.current().executeScript("PF('wdlUbicacionN').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Hay Ubicacion Registrada"));
            return;
        }
        //Shared coordinates
        LatLng coord1 = new LatLng(Double.parseDouble(neglatitud), Double.parseDouble(neglongitud));
        //Draggable
        markerN = new Marker(coord1, "Finsol");

        simpleModelN.addOverlay(markerN);

    }
    
     public MapModel getSimpleModelH() {
        return simpleModelH;
    }
    public MapModel getSimpleModelN() {
        return simpleModelN;
    }
    public Integer getDiastranscurridos() {
        return diastranscurridos;
    }

    public void setDiastranscurridos(Integer diastranscurridos) {
        this.diastranscurridos = diastranscurridos;
    }

    public String getFormapagoDet() {
        return formapagoDet;
    }

    public void setFormapagoDet(String formapagoDet) {
        this.formapagoDet = formapagoDet;
    }

    public String getComentariojefe() {
        return comentariojefe;
    }

    public void setComentariojefe(String comentariojefe) {
        this.comentariojefe = comentariojefe;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }
}
