/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.data;

import finsolhn.com.util.Constantes;
//import groovy.transform.Immutable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.xml.bind.annotation.XmlRootElement;
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
@Table(name = "ad_v_visitas",/* catalog = "db_dafinsol",*/ schema = "agenda_digital")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdVVisitas.findAll", query = "SELECT a FROM AdVVisitas a"),
    @NamedQuery(name = "AdVVisitas.findByIdcliente", query = "SELECT a FROM AdVVisitas a WHERE a.idcliente = :idcliente"),
    @NamedQuery(name = "AdVVisitas.findByNombre", query = "SELECT a FROM AdVVisitas a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AdVVisitas.findByPrestamo", query = "SELECT a FROM AdVVisitas a WHERE a.prestamo = :prestamo"),
    @NamedQuery(name = "AdVVisitas.findBySaldosjtsoid", query = "SELECT a FROM AdVVisitas a WHERE a.saldosjtsoid = :saldosjtsoid"),
    @NamedQuery(name = "AdVVisitas.findByTipovisita", query = "SELECT a FROM AdVVisitas a WHERE a.tipovisita = :tipovisita"),
    @NamedQuery(name = "AdVVisitas.findByResultadovisita", query = "SELECT a FROM AdVVisitas a WHERE a.resultadovisita = :resultadovisita"),
    @NamedQuery(name = "AdVVisitas.findByComentario", query = "SELECT a FROM AdVVisitas a WHERE a.comentario = :comentario"),
    @NamedQuery(name = "AdVVisitas.findByFecharegistro", query = "SELECT a FROM AdVVisitas a WHERE a.fecharegistro = :fecharegistro"),
    @NamedQuery(name = "AdVVisitas.findByFechaprovisita", query = "SELECT a FROM AdVVisitas a WHERE a.fechaprovisita = :fechaprovisita"),
    @NamedQuery(name = "AdVVisitas.findByHoraprovisita", query = "SELECT a FROM AdVVisitas a WHERE a.horaprovisita = :horaprovisita"),
    @NamedQuery(name = "AdVVisitas.findByFechacompleto", query = "SELECT a FROM AdVVisitas a WHERE a.fechacompleto = :fechacompleto"),
    @NamedQuery(name = "AdVVisitas.findByHorainiciovisita", query = "SELECT a FROM AdVVisitas a WHERE a.horainiciovisita = :horainiciovisita"),
    @NamedQuery(name = "AdVVisitas.findByHorafinvisita", query = "SELECT a FROM AdVVisitas a WHERE a.horafinvisita = :horafinvisita"),
    @NamedQuery(name = "AdVVisitas.findByUsragenda", query = "SELECT a FROM AdVVisitas a WHERE a.usragenda = :usragenda"),
    @NamedQuery(name = "AdVVisitas.findByUsrvisito", query = "SELECT a FROM AdVVisitas a WHERE a.usrvisito = :usrvisito"),
    @NamedQuery(name = "AdVVisitas.findByEstadoCrud", query = "SELECT a FROM AdVVisitas a WHERE a.estadoCrud = :estadoCrud"),
    @NamedQuery(name = "AdVVisitas.findByNombrenuevo", query = "SELECT a FROM AdVVisitas a WHERE a.nombrenuevo = :nombrenuevo"),
    @NamedQuery(name = "AdVVisitas.findByTelefononuevo", query = "SELECT a FROM AdVVisitas a WHERE a.telefononuevo = :telefononuevo"),
    @NamedQuery(name = "AdVVisitas.findByDireccionnuevo", query = "SELECT a FROM AdVVisitas a WHERE a.direccionnuevo = :direccionnuevo"),
    @NamedQuery(name = "AdVVisitas.findByMoneda", query = "SELECT a FROM AdVVisitas a WHERE a.moneda = :moneda"),
    @NamedQuery(name = "AdVVisitas.findByMontodesembolsado", query = "SELECT a FROM AdVVisitas a WHERE a.montodesembolsado = :montodesembolsado"),
    @NamedQuery(name = "AdVVisitas.findBySaldocapital", query = "SELECT a FROM AdVVisitas a WHERE a.saldocapital = :saldocapital"),
    @NamedQuery(name = "AdVVisitas.findByInteres", query = "SELECT a FROM AdVVisitas a WHERE a.interes = :interes"),
    @NamedQuery(name = "AdVVisitas.findByFormapago", query = "SELECT a FROM AdVVisitas a WHERE a.formapago = :formapago"),
    @NamedQuery(name = "AdVVisitas.findByNcuotas", query = "SELECT a FROM AdVVisitas a WHERE a.ncuotas = :ncuotas"),
    @NamedQuery(name = "AdVVisitas.findByCuotasres", query = "SELECT a FROM AdVVisitas a WHERE a.cuotasres = :cuotasres"),
    @NamedQuery(name = "AdVVisitas.findByValorcuotavig", query = "SELECT a FROM AdVVisitas a WHERE a.valorcuotavig = :valorcuotavig"),
    @NamedQuery(name = "AdVVisitas.findBySaldoatrasado", query = "SELECT a FROM AdVVisitas a WHERE a.saldoatrasado = :saldoatrasado"),
    @NamedQuery(name = "AdVVisitas.findByFechadesembolso", query = "SELECT a FROM AdVVisitas a WHERE a.fechadesembolso = :fechadesembolso"),
    @NamedQuery(name = "AdVVisitas.findByFechavence", query = "SELECT a FROM AdVVisitas a WHERE a.fechavence = :fechavence"),
    @NamedQuery(name = "AdVVisitas.findByFechaproxima", query = "SELECT a FROM AdVVisitas a WHERE a.fechaproxima = :fechaproxima"),
    @NamedQuery(name = "AdVVisitas.findByDiasatraso", query = "SELECT a FROM AdVVisitas a WHERE a.diasatraso = :diasatraso"),
    @NamedQuery(name = "AdVVisitas.findByNomasesor", query = "SELECT a FROM AdVVisitas a WHERE a.nomasesor = :nomasesor"),
    @NamedQuery(name = "AdVVisitas.findByIdagencia", query = "SELECT a FROM AdVVisitas a WHERE a.idagencia = :idagencia"),
    @NamedQuery(name = "AdVVisitas.findByNomagencia", query = "SELECT a FROM AdVVisitas a WHERE a.nomagencia = :nomagencia"),
    @NamedQuery(name = "AdVVisitas.findByFechaultimavisita", query = "SELECT a FROM AdVVisitas a WHERE a.fechaultimavisita = :fechaultimavisita"),
    @NamedQuery(name = "AdVVisitas.findByLatvisita", query = "SELECT a FROM AdVVisitas a WHERE a.latvisita = :latvisita"),
    @NamedQuery(name = "AdVVisitas.findByLonvisita", query = "SELECT a FROM AdVVisitas a WHERE a.lonvisita = :lonvisita"),
    @NamedQuery(name = "AdVVisitas.findByVFechacompromiso", query = "SELECT a FROM AdVVisitas a WHERE a.vFechacompromiso = :vFechacompromiso"),
    @NamedQuery(name = "AdVVisitas.findByPFechacompromiso", query = "SELECT a FROM AdVVisitas a WHERE a.pFechacompromiso = :pFechacompromiso"),
    @NamedQuery(name = "AdVVisitas.findByFechaultimopago", query = "SELECT a FROM AdVVisitas a WHERE a.fechaultimopago = :fechaultimopago"),
    @NamedQuery(name = "AdVVisitas.findByIdgestor", query = "SELECT a FROM AdVVisitas a WHERE a.idgestor = :idgestor"),
    @NamedQuery(name = "AdVVisitas.findByEmpleadoid", query = "SELECT a FROM AdVVisitas a WHERE a.empleadoid = :empleadoid"),
    @NamedQuery(name = "AdVVisitas.findByIdclienteasesor", query = "SELECT a FROM AdVVisitas a WHERE a.idclienteasesor = :idclienteasesor"),
    @NamedQuery(name = "AdVVisitas.findByCuentaasesor", query = "SELECT a FROM AdVVisitas a WHERE a.cuentaasesor = :cuentaasesor")})
//@Immutable
public class AdVVisitas implements Serializable {

    @Size(max = 2)
    @Column(name = "pais")
    private String pais;
    @Column(name = "iddepartamento")
    private Short iddepartamento;
    @Size(max = 60)
    @Column(name = "departamento")
    private String departamento;
    @Column(name = "idmunicipio")
    private Short idmunicipio;
    @Size(max = 60)
    @Column(name = "municipio")
    private String municipio;
    @Size(max = 3)
    @Column(name = "idsector")
    private String idsector;
    @Size(max = 60)
    @Column(name = "sector")
    private String sector;
    @Column(name = "idbarrio")
    private Short idbarrio;
    @Size(max = 2147483647)
    @Column(name = "barrio")
    private String barrio;
    @Size(max = 2147483647)
    @Column(name = "referenciadir")
    private String referenciadir;
    @Size(max = 2147483647)
    @Column(name = "telefonofijo")
    private String telefonofijo;
    @Size(max = 2147483647)
    @Column(name = "telefonomovil")
    private String telefonomovil;
    @Size(max = 30)
    @Column(name = "formato")
    private String formato;

    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 60)
    @Column(name = "liteuidvisita", length = 60)
    private String liteuidvisita;
    
    @Column(name = "idcliente")
    private Long idcliente;
    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;
    @Column(name = "prestamo")
    private Long prestamo;
    @Column(name = "saldosjtsoid")
    private Long saldosjtsoid;
    @Size(max = 30)
    @Column(name = "tipovisita", length = 30)
    private String tipovisita;
    @Size(max = 30)
    @Column(name = "resultadovisita", length = 30)
    private String resultadovisita;
    @Size(max = 200)
    @Column(name = "comentario", length = 200)
    private String comentario;
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @Column(name = "fechaprovisita")
    @Temporal(TemporalType.DATE)
    private Date fechaprovisita;
    @Column(name = "horaprovisita")
    @Temporal(TemporalType.TIME)
    private Date horaprovisita;
    @Column(name = "fechacompleto")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacompleto;
    @Column(name = "horainiciovisita")
    @Temporal(TemporalType.TIME)
    private Date horainiciovisita;
    @Column(name = "horafinvisita")
    @Temporal(TemporalType.TIME)
    private Date horafinvisita;
    @Size(max = 30)
    @Column(name = "usragenda", length = 30)
    private String usragenda;
    @Size(max = 30)
    @Column(name = "usrvisito", length = 30)
    private String usrvisito;
    @Size(max = 20)
    @Column(name = "estado_crud", length = 20)
    private String estadoCrud;
    @Size(max = 100)
    @Column(name = "nombrenuevo", length = 100)
    private String nombrenuevo;
    @Size(max = 30)
    @Column(name = "telefononuevo", length = 30)
    private String telefononuevo;
    @Size(max = 200)
    @Column(name = "direccionnuevo", length = 200)
    private String direccionnuevo;
    @Column(name = "moneda")
    private Integer moneda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montodesembolsado", precision = 18, scale = 2)
    private BigDecimal montodesembolsado;
    @Column(name = "saldocapital", precision = 18, scale = 2)
    private BigDecimal saldocapital;
    @Column(name = "interes", precision = 18, scale = 2)
    private BigDecimal interes;
    @Size(max = 20)
    @Column(name = "formapago", length = 20)
    private String formapago;
    @Column(name = "ncuotas")
    private Integer ncuotas;
    @Column(name = "cuotasres")
    private Integer cuotasres;
    @Column(name = "valorcuotavig", precision = 18, scale = 2)
    private BigDecimal valorcuotavig;
    @Column(name = "saldoatrasado", precision = 18, scale = 2)
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
    @Size(max = 50)
    @Column(name = "nomasesor", length = 50)
    private String nomasesor;
    @Size(max = 50)
    @Column(name = "idagencia")
    private String idagencia;
    @Size(max = 50)
    @Column(name = "nomagencia", length = 50)
    private String nomagencia;
    @Column(name = "fechaultimavisita")
    @Temporal(TemporalType.DATE)
    private Date fechaultimavisita;
    @Size(max = 30)
    @Column(name = "latvisita", length = 30)
    private String latvisita;
    @Size(max = 30)
    @Column(name = "lonvisita", length = 30)
    private String lonvisita;
    @Column(name = "v_fechacompromiso")
    @Temporal(TemporalType.DATE)
    private Date vFechacompromiso;
    @Column(name = "p_fechacompromiso")
    @Temporal(TemporalType.DATE)
    private Date pFechacompromiso;
    @Column(name = "fechaultimopago")
    @Temporal(TemporalType.DATE)
    private Date fechaultimopago;
    @Size(max = 10)
    @Column(name = "idgestor", length = 10)
    private String idgestor;
    @Column(name = "empleadoid")
    private Integer empleadoid;
    @Size(max = 20)
    @Column(name = "idclienteasesor", length = 20)
    private String idclienteasesor;
    @Size(max = 20)
    @Column(name = "cuentaasesor", length = 20)
    private String cuentaasesor;

    
    @Transient
    private String porpagado = "";
    
    
    public String getFechaHoraProVisita() {
        if (fechaprovisita == null) {
            return "";
        }
        return Constantes.sdf_dmy.format(fechaprovisita) + " " + Constantes.sdf_ham.format(horaprovisita);
    }

    
    
    public AdVVisitas() {
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Long prestamo) {
        this.prestamo = prestamo;
    }

    public Long getSaldosjtsoid() {
        return saldosjtsoid;
    }

    public void setSaldosjtsoid(Long saldosjtsoid) {
        this.saldosjtsoid = saldosjtsoid;
    }

    public String getTipovisita() {
        return tipovisita;
    }

    public void setTipovisita(String tipovisita) {
        this.tipovisita = tipovisita;
    }

    public String getResultadovisita() {
        return resultadovisita;
    }

    public void setResultadovisita(String resultadovisita) {
        this.resultadovisita = resultadovisita;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Date getFechaprovisita() {
        return fechaprovisita;
    }

    public void setFechaprovisita(Date fechaprovisita) {
        this.fechaprovisita = fechaprovisita;
    }

    public Date getHoraprovisita() {
        return horaprovisita;
    }

    public void setHoraprovisita(Date horaprovisita) {
        this.horaprovisita = horaprovisita;
    }

    public Date getFechacompleto() {
        return fechacompleto;
    }

    public void setFechacompleto(Date fechacompleto) {
        this.fechacompleto = fechacompleto;
    }

    public Date getHorainiciovisita() {
        return horainiciovisita;
    }

    public void setHorainiciovisita(Date horainiciovisita) {
        this.horainiciovisita = horainiciovisita;
    }

    public Date getHorafinvisita() {
        return horafinvisita;
    }

    public void setHorafinvisita(Date horafinvisita) {
        this.horafinvisita = horafinvisita;
    }

    public String getUsragenda() {
        return usragenda;
    }

    public void setUsragenda(String usragenda) {
        this.usragenda = usragenda;
    }

    public String getUsrvisito() {
        return usrvisito;
    }

    public void setUsrvisito(String usrvisito) {
        this.usrvisito = usrvisito;
    }

    public String getEstadoCrud() {
        return estadoCrud;
    }

    public void setEstadoCrud(String estadoCrud) {
        this.estadoCrud = estadoCrud;
    }

    public String getNombrenuevo() {
        return nombrenuevo;
    }

    public void setNombrenuevo(String nombrenuevo) {
        this.nombrenuevo = nombrenuevo;
    }

    public String getTelefononuevo() {
        return telefononuevo;
    }

    public void setTelefononuevo(String telefononuevo) {
        this.telefononuevo = telefononuevo;
    }

    public String getDireccionnuevo() {
        return direccionnuevo;
    }

    public void setDireccionnuevo(String direccionnuevo) {
        this.direccionnuevo = direccionnuevo;
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

    public String getNomasesor() {
        return nomasesor;
    }

    public void setNomasesor(String nomasesor) {
        this.nomasesor = nomasesor;
    }

    public String getIdagencia() {
        return idagencia;
    }

    public void setIdagencia(String idagencia) {
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

    public String getLatvisita() {
        return latvisita;
    }

    public void setLatvisita(String latvisita) {
        this.latvisita = latvisita;
    }

    public String getLonvisita() {
        return lonvisita;
    }

    public void setLonvisita(String lonvisita) {
        this.lonvisita = lonvisita;
    }

    public Date getVFechacompromiso() {
        return vFechacompromiso;
    }

    public void setVFechacompromiso(Date vFechacompromiso) {
        this.vFechacompromiso = vFechacompromiso;
    }

    public Date getPFechacompromiso() {
        return pFechacompromiso;
    }

    public void setPFechacompromiso(Date pFechacompromiso) {
        this.pFechacompromiso = pFechacompromiso;
    }

    public Date getFechaultimopago() {
        return fechaultimopago;
    }

    public void setFechaultimopago(Date fechaultimopago) {
        this.fechaultimopago = fechaultimopago;
    }

    public String getIdgestor() {
        return idgestor;
    }

    public void setIdgestor(String idgestor) {
        this.idgestor = idgestor;
    }

    public Integer getEmpleadoid() {
        return empleadoid;
    }

    public void setEmpleadoid(Integer empleadoid) {
        this.empleadoid = empleadoid;
    }

    public String getIdclienteasesor() {
        return idclienteasesor;
    }

    public void setIdclienteasesor(String idclienteasesor) {
        this.idclienteasesor = idclienteasesor;
    }

    public String getCuentaasesor() {
        return cuentaasesor;
    }

    public void setCuentaasesor(String cuentaasesor) {
        this.cuentaasesor = cuentaasesor;
    }

    public String getLiteuidvisita() {
        return liteuidvisita;
    }

    public void setLiteuidvisita(String liteuidvisita) {
        this.liteuidvisita = liteuidvisita;
    }

    public String getPorpagado() {
        return porpagado;
    }

    public void setPorpagado(String porpagado) {
        this.porpagado = porpagado;
    }
    
    
    
     private String getPorcentajePagado() {

        if (saldocapital == null || montodesembolsado == null) {
            return "";
        }

        if (getSaldocapital().compareTo(BigDecimal.ZERO)<=0 || getMontodesembolsado().compareTo(BigDecimal.ZERO)<=0 ) {
            return "";
        }
        double v = Math.abs(((getSaldocapital().doubleValue() * 100) / getMontodesembolsado().doubleValue()) - 100);

        return " | Pagado:(" + Math.round(v) + "%) |";

    }
     
    @Transient 
    private MapModel simpleModel;
    @Transient
    private Marker marker;

    public void iniMap() {

        simpleModel = new DefaultMapModel();

        if (resultadovisita != null) {
            if (resultadovisita.isEmpty()) {

                //RequestContext context = RequestContext.getCurrentInstance();
                //context.execute("PF('wdlUbicacion').hide();");
                PrimeFaces.current().executeScript("PF('wdlUbicacion').hide();");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "La visita no ha sido completada"));
                return;
            }
        }
        if (latvisita.equalsIgnoreCase("0")) {
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('wdlUbicacion').hide();");
            PrimeFaces.current().executeScript("PF('wdlUbicacion').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Hay Ubicacion Registrada para esta visita"));
            return;
        }
        //Shared coordinates
        LatLng coord1 = new LatLng(Double.parseDouble(latvisita), Double.parseDouble(lonvisita));
        //Draggable
        marker = new Marker(coord1, "Finsol");

        simpleModel.addOverlay(marker);

    }
       public MapModel getSimpleModel() {
        return simpleModel;
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

    public String getTelefonofijo() {
        return telefonofijo;
    }

    public void setTelefonofijo(String telefonofijo) {
        this.telefonofijo = telefonofijo;
    }

    public String getTelefonomovil() {
        return telefonomovil;
    }

    public void setTelefonomovil(String telefonomovil) {
        this.telefonomovil = telefonomovil;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
    
}
