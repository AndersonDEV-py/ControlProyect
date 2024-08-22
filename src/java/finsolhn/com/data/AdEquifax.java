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
@Table(name = "ad_equifax")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdEquifax.findAll", query = "SELECT a FROM AdEquifax a")
    ,@NamedQuery(name = "AdEquifax.findByIdentidad", query = "SELECT a FROM AdEquifax a where a.identidad = :identidad")})

public class AdEquifax implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "identidad")
    private String identidad;
    @Size(max = 60)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 60)
    @Column(name = "direccion")
    private String direccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "scoreequifax")
    private BigDecimal scoreequifax;
    @Column(name = "deudatotal")
    private BigDecimal deudatotal;
    @Column(name = "cuotamensual")
    private BigDecimal cuotamensual;
    @Column(name = "cantidadreferencias")
    private Integer cantidadreferencias;
    @Size(max = 60)
    @Column(name = "ctdeudaactual")
    private String ctdeudaactual;
    @Size(max = 60)
    @Column(name = "ctdeudaactualu12")
    private String ctdeudaactualu12;
    @Size(max = 60)
    @Column(name = "cbcategoriabancaactual")
    private String cbcategoriabancaactual;
    @Size(max = 60)
    @Column(name = "cbcategoriabancau12")
    private String cbcategoriabancau12;
    @Column(name = "cobroadministrativo")
    private BigDecimal cobroadministrativo;
    @Column(name = "cobrojudicialcomercial")
    private BigDecimal cobrojudicialcomercial;
    @Column(name = "mesesdeudaantigua")
    private Integer mesesdeudaantigua;
    @Column(name = "fechaprimerregristro")
    @Temporal(TemporalType.DATE)
    private Date fechaprimerregristro;
    @Column(name = "fechaultimaactualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaultimaactualizacion;
    @Column(name = "fechaultimaconsulta")
    @Temporal(TemporalType.DATE)
    private Date fechaultimaconsulta;
    @Size(max = 60)
    @Column(name = "usuariocreo")
    private String usuariocreo;
    @Size(max = 60)
    @Column(name = "usuarioultimaconsulta")
    private String usuarioultimaconsulta;
    @Size(max = 60)
    @Column(name = "usuarioultimaactualizacion")
    private String usuarioultimaactualizacion;
    @Size(max = 2147483647)
    @Column(name = "pdffile")
    private String pdffile;

    public AdEquifax() {
    }

    public AdEquifax(String identidad) {
        this.identidad = identidad;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigDecimal getScoreequifax() {
        return scoreequifax;
    }

    public void setScoreequifax(BigDecimal scoreequifax) {
        this.scoreequifax = scoreequifax;
    }

    public BigDecimal getDeudatotal() {
        return deudatotal;
    }

    public void setDeudatotal(BigDecimal deudatotal) {
        this.deudatotal = deudatotal;
    }

    public BigDecimal getCuotamensual() {
        return cuotamensual;
    }

    public void setCuotamensual(BigDecimal cuotamensual) {
        this.cuotamensual = cuotamensual;
    }

    public Integer getCantidadreferencias() {
        return cantidadreferencias;
    }

    public void setCantidadreferencias(Integer cantidadreferencias) {
        this.cantidadreferencias = cantidadreferencias;
    }

    public String getCtdeudaactual() {
        return ctdeudaactual;
    }

    public void setCtdeudaactual(String ctdeudaactual) {
        this.ctdeudaactual = ctdeudaactual;
    }

    public String getCtdeudaactualu12() {
        return ctdeudaactualu12;
    }

    public void setCtdeudaactualu12(String ctdeudaactualu12) {
        this.ctdeudaactualu12 = ctdeudaactualu12;
    }

    public String getCbcategoriabancaactual() {
        return cbcategoriabancaactual;
    }

    public void setCbcategoriabancaactual(String cbcategoriabancaactual) {
        this.cbcategoriabancaactual = cbcategoriabancaactual;
    }

    public String getCbcategoriabancau12() {
        return cbcategoriabancau12;
    }

    public void setCbcategoriabancau12(String cbcategoriabancau12) {
        this.cbcategoriabancau12 = cbcategoriabancau12;
    }

    public BigDecimal getCobroadministrativo() {
        return cobroadministrativo;
    }

    public void setCobroadministrativo(BigDecimal cobroadministrativo) {
        this.cobroadministrativo = cobroadministrativo;
    }

    public BigDecimal getCobrojudicialcomercial() {
        return cobrojudicialcomercial;
    }

    public void setCobrojudicialcomercial(BigDecimal cobrojudicialcomercial) {
        this.cobrojudicialcomercial = cobrojudicialcomercial;
    }

    public Integer getMesesdeudaantigua() {
        return mesesdeudaantigua;
    }

    public void setMesesdeudaantigua(Integer mesesdeudaantigua) {
        this.mesesdeudaantigua = mesesdeudaantigua;
    }

    public Date getFechaprimerregristro() {
        return fechaprimerregristro;
    }

    public void setFechaprimerregristro(Date fechaprimerregristro) {
        this.fechaprimerregristro = fechaprimerregristro;
    }

    public Date getFechaultimaactualizacion() {
        return fechaultimaactualizacion;
    }

    public void setFechaultimaactualizacion(Date fechaultimaactualizacion) {
        this.fechaultimaactualizacion = fechaultimaactualizacion;
    }

    public Date getFechaultimaconsulta() {
        return fechaultimaconsulta;
    }

    public void setFechaultimaconsulta(Date fechaultimaconsulta) {
        this.fechaultimaconsulta = fechaultimaconsulta;
    }

    public String getUsuariocreo() {
        return usuariocreo;
    }

    public void setUsuariocreo(String usuariocreo) {
        this.usuariocreo = usuariocreo;
    }

    public String getUsuarioultimaconsulta() {
        return usuarioultimaconsulta;
    }

    public void setUsuarioultimaconsulta(String usuarioultimaconsulta) {
        this.usuarioultimaconsulta = usuarioultimaconsulta;
    }

    public String getUsuarioultimaactualizacion() {
        return usuarioultimaactualizacion;
    }

    public void setUsuarioultimaactualizacion(String usuarioultimaactualizacion) {
        this.usuarioultimaactualizacion = usuarioultimaactualizacion;
    }

    public String getPdffile() {
        return pdffile;
    }

    public void setPdffile(String pdffile) {
        this.pdffile = pdffile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identidad != null ? identidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdEquifax)) {
            return false;
        }
        AdEquifax other = (AdEquifax) object;
        if ((this.identidad == null && other.identidad != null) || (this.identidad != null && !this.identidad.equals(other.identidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdEquifax[ identidad=" + identidad + " ]";
    }
    
}
