/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.data;

import java.io.Serializable;
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
@Table(name = "ad_visitas", catalog = "ad_testing", schema = "agenda_digital")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdVisitas.findAll", query = "SELECT a FROM AdVisitas a")
    , @NamedQuery(name = "AdVisitas.findByIdcliente", query = "SELECT a FROM AdVisitas a WHERE a.idcliente = :idcliente")
    , @NamedQuery(name = "AdVisitas.findByPrestamo", query = "SELECT a FROM AdVisitas a WHERE a.prestamo = :prestamo")
    , @NamedQuery(name = "AdVisitas.findBySaldosjtsoid", query = "SELECT a FROM AdVisitas a WHERE a.saldosjtsoid = :saldosjtsoid")
    , @NamedQuery(name = "AdVisitas.findByTipovisita", query = "SELECT a FROM AdVisitas a WHERE a.tipovisita = :tipovisita")
    , @NamedQuery(name = "AdVisitas.findByResultadovisita", query = "SELECT a FROM AdVisitas a WHERE a.resultadovisita = :resultadovisita")
    , @NamedQuery(name = "AdVisitas.findByComentario", query = "SELECT a FROM AdVisitas a WHERE a.comentario = :comentario")
    , @NamedQuery(name = "AdVisitas.findByFecharegistro", query = "SELECT a FROM AdVisitas a WHERE a.fecharegistro = :fecharegistro")
    , @NamedQuery(name = "AdVisitas.findByFechaprovisita", query = "SELECT a FROM AdVisitas a WHERE a.fechaprovisita = :fechaprovisita")
    , @NamedQuery(name = "AdVisitas.findByHoraprovisita", query = "SELECT a FROM AdVisitas a WHERE a.horaprovisita = :horaprovisita")
    , @NamedQuery(name = "AdVisitas.findByFechacompleto", query = "SELECT a FROM AdVisitas a WHERE a.fechacompleto = :fechacompleto")
    , @NamedQuery(name = "AdVisitas.findByHorainiciovisita", query = "SELECT a FROM AdVisitas a WHERE a.horainiciovisita = :horainiciovisita")
    , @NamedQuery(name = "AdVisitas.findByHorafinvisita", query = "SELECT a FROM AdVisitas a WHERE a.horafinvisita = :horafinvisita")
    , @NamedQuery(name = "AdVisitas.findByUsragenda", query = "SELECT a FROM AdVisitas a WHERE a.usragenda = :usragenda")
    , @NamedQuery(name = "AdVisitas.findByUsrvisito", query = "SELECT a FROM AdVisitas a WHERE a.usrvisito = :usrvisito")
    , @NamedQuery(name = "AdVisitas.findByLatvisita", query = "SELECT a FROM AdVisitas a WHERE a.latvisita = :latvisita")
    , @NamedQuery(name = "AdVisitas.findByLonvisita", query = "SELECT a FROM AdVisitas a WHERE a.lonvisita = :lonvisita")
    , @NamedQuery(name = "AdVisitas.findByEstadoCrud", query = "SELECT a FROM AdVisitas a WHERE a.estadoCrud = :estadoCrud")
    , @NamedQuery(name = "AdVisitas.findByNombrenuevo", query = "SELECT a FROM AdVisitas a WHERE a.nombrenuevo = :nombrenuevo")
    , @NamedQuery(name = "AdVisitas.findByTelefononuevo", query = "SELECT a FROM AdVisitas a WHERE a.telefononuevo = :telefononuevo")
    , @NamedQuery(name = "AdVisitas.findByDireccionnuevo", query = "SELECT a FROM AdVisitas a WHERE a.direccionnuevo = :direccionnuevo")
    , @NamedQuery(name = "AdVisitas.findByLiteuid", query = "SELECT a FROM AdVisitas a WHERE a.liteuid = :liteuid")
    , @NamedQuery(name = "AdVisitas.findByFechacompromiso", query = "SELECT a FROM AdVisitas a WHERE a.fechacompromiso = :fechacompromiso")})
public class AdVisitas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "idcliente")
    private Long idcliente;
    @Column(name = "prestamo")
    private Long prestamo;
    @Column(name = "saldosjtsoid")
    private Long saldosjtsoid;
    @Size(max = 30)
    @Column(name = "tipovisita")
    private String tipovisita;
    @Size(max = 30)
    @Column(name = "resultadovisita")
    private String resultadovisita;
    @Size(max = 200)
    @Column(name = "comentario")
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
    @Column(name = "usragenda")
    private String usragenda;
    @Size(max = 30)
    @Column(name = "usrvisito")
    private String usrvisito;
    @Size(max = 30)
    @Column(name = "latvisita")
    private String latvisita;
    @Size(max = 30)
    @Column(name = "lonvisita")
    private String lonvisita;
    @Size(max = 20)
    @Column(name = "estado_crud")
    private String estadoCrud;
    @Size(max = 100)
    @Column(name = "nombrenuevo")
    private String nombrenuevo;
    @Size(max = 20)
    @Column(name = "telefononuevo")
    private String telefononuevo;
    @Size(max = 200)
    @Column(name = "direccionnuevo")
    private String direccionnuevo;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "liteuid")
    private String liteuid;
    @Column(name = "fechacompromiso")
    @Temporal(TemporalType.DATE)
    private Date fechacompromiso;

    public AdVisitas() {
    }

    public AdVisitas(String liteuid) {
        this.liteuid = liteuid;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
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

    public String getLiteuid() {
        return liteuid;
    }

    public void setLiteuid(String liteuid) {
        this.liteuid = liteuid;
    }

    public Date getFechacompromiso() {
        return fechacompromiso;
    }

    public void setFechacompromiso(Date fechacompromiso) {
        this.fechacompromiso = fechacompromiso;
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
        if (!(object instanceof AdVisitas)) {
            return false;
        }
        AdVisitas other = (AdVisitas) object;
        if ((this.liteuid == null && other.liteuid != null) || (this.liteuid != null && !this.liteuid.equals(other.liteuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdVisitas[ liteuid=" + liteuid + " ]";
    }
    
}
