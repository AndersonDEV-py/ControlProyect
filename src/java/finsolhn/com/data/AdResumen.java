/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.data;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ds010106
 */
@Entity
@Table(name = "ad_resumen", catalog = "ad_testing", schema = "agenda_digital", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idcliente"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdResumen.findAll", query = "SELECT a FROM AdResumen a")
    , @NamedQuery(name = "AdResumen.findByIdcliente", query = "SELECT a FROM AdResumen a WHERE a.idcliente = :idcliente")
    , @NamedQuery(name = "AdResumen.findByCuentasahorro", query = "SELECT a FROM AdResumen a WHERE a.cuentasahorro = :cuentasahorro")
    , @NamedQuery(name = "AdResumen.findByDepositoaplazo", query = "SELECT a FROM AdResumen a WHERE a.depositoaplazo = :depositoaplazo")
    , @NamedQuery(name = "AdResumen.findByRemesas", query = "SELECT a FROM AdResumen a WHERE a.remesas = :remesas")
    , @NamedQuery(name = "AdResumen.findByPrestamosactivos", query = "SELECT a FROM AdResumen a WHERE a.prestamosactivos = :prestamosactivos")
    , @NamedQuery(name = "AdResumen.findByPrestamoscancelados", query = "SELECT a FROM AdResumen a WHERE a.prestamoscancelados = :prestamoscancelados")
    , @NamedQuery(name = "AdResumen.findByCondonaciones", query = "SELECT a FROM AdResumen a WHERE a.condonaciones = :condonaciones")
    , @NamedQuery(name = "AdResumen.findByGarantia", query = "SELECT a FROM AdResumen a WHERE a.garantia = :garantia")
    , @NamedQuery(name = "AdResumen.findByRecordAnterior", query = "SELECT a FROM AdResumen a WHERE a.recordAnterior = :recordAnterior")
    , @NamedQuery(name = "AdResumen.findByMontoanterior", query = "SELECT a FROM AdResumen a WHERE a.montoanterior = :montoanterior")
    , @NamedQuery(name = "AdResumen.findByRecordactual", query = "SELECT a FROM AdResumen a WHERE a.recordactual = :recordactual")
    , @NamedQuery(name = "AdResumen.findByMontoactualsolicitud", query = "SELECT a FROM AdResumen a WHERE a.montoactualsolicitud = :montoactualsolicitud")})
public class AdResumen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcliente", nullable = false)
    private Long idcliente;
    @Column(name = "cuentasahorro")
    private Integer cuentasahorro;
    @Column(name = "depositoaplazo")
    private Integer depositoaplazo;
    @Column(name = "remesas")
    private Integer remesas;
    @Column(name = "prestamosactivos")
    private Integer prestamosactivos;
    @Column(name = "prestamoscancelados")
    private Integer prestamoscancelados;
    @Column(name = "condonaciones")
    private Integer condonaciones;
    @Column(name = "garantia")
    private Integer garantia;
    @Size(max = 20)
    @Column(name = "record_anterior", length = 20)
    private String recordAnterior;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montoanterior", precision = 18, scale = 2)
    private BigDecimal montoanterior;
    @Size(max = 20)
    @Column(name = "recordactual", length = 20)
    private String recordactual;
    @Column(name = "montoactualsolicitud", precision = 18, scale = 2)
    private BigDecimal montoactualsolicitud;

    public AdResumen() {
    }

    public AdResumen(Long idcliente) {
        this.idcliente = idcliente;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getCuentasahorro() {
        return cuentasahorro;
    }

    public void setCuentasahorro(Integer cuentasahorro) {
        this.cuentasahorro = cuentasahorro;
    }

    public Integer getDepositoaplazo() {
        return depositoaplazo;
    }

    public void setDepositoaplazo(Integer depositoaplazo) {
        this.depositoaplazo = depositoaplazo;
    }

    public Integer getRemesas() {
        return remesas;
    }

    public void setRemesas(Integer remesas) {
        this.remesas = remesas;
    }

    public Integer getPrestamosactivos() {
        return prestamosactivos;
    }

    public void setPrestamosactivos(Integer prestamosactivos) {
        this.prestamosactivos = prestamosactivos;
    }

    public Integer getPrestamoscancelados() {
        return prestamoscancelados;
    }

    public void setPrestamoscancelados(Integer prestamoscancelados) {
        this.prestamoscancelados = prestamoscancelados;
    }

    public Integer getCondonaciones() {
        return condonaciones;
    }

    public void setCondonaciones(Integer condonaciones) {
        this.condonaciones = condonaciones;
    }

    public Integer getGarantia() {
        return garantia;
    }

    public void setGarantia(Integer garantia) {
        this.garantia = garantia;
    }

    public String getRecordAnterior() {
        return recordAnterior;
    }

    public void setRecordAnterior(String recordAnterior) {
        this.recordAnterior = recordAnterior;
    }

    public BigDecimal getMontoanterior() {
        return montoanterior;
    }

    public void setMontoanterior(BigDecimal montoanterior) {
        this.montoanterior = montoanterior;
    }

    public String getRecordactual() {
        return recordactual;
    }

    public void setRecordactual(String recordactual) {
        this.recordactual = recordactual;
    }

    public BigDecimal getMontoactualsolicitud() {
        return montoactualsolicitud;
    }

    public void setMontoactualsolicitud(BigDecimal montoactualsolicitud) {
        this.montoactualsolicitud = montoactualsolicitud;
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
        if (!(object instanceof AdResumen)) {
            return false;
        }
        AdResumen other = (AdResumen) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdResumen[ idcliente=" + idcliente + " ]";
    }
    
}
