/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ds010106
 */
@Entity
@Table(name = "ad_usuarios", catalog = "ad_testing", schema = "agenda_digital")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdUsuarios.findAll", query = "SELECT a FROM AdUsuarios a")
    , @NamedQuery(name = "AdUsuarios.findByEmpleadoid", query = "SELECT a FROM AdUsuarios a WHERE a.empleadoid = :empleadoid")
    , @NamedQuery(name = "AdUsuarios.findByUsuariotopaz", query = "SELECT a FROM AdUsuarios a WHERE a.usuariotopaz = :usuariotopaz")
    , @NamedQuery(name = "AdUsuarios.findByNombre", query = "SELECT a FROM AdUsuarios a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "AdUsuarios.findByPuestoid", query = "SELECT a FROM AdUsuarios a WHERE a.puestoid = :puestoid")
    , @NamedQuery(name = "AdUsuarios.findByPuesto", query = "SELECT a FROM AdUsuarios a WHERE a.puesto = :puesto")
    , @NamedQuery(name = "AdUsuarios.findByCorreo", query = "SELECT a FROM AdUsuarios a WHERE a.correo = :correo")
    , @NamedQuery(name = "AdUsuarios.findByIntentos", query = "SELECT a FROM AdUsuarios a WHERE a.intentos = :intentos")
    , @NamedQuery(name = "AdUsuarios.findByAgenciaid", query = "SELECT a FROM AdUsuarios a WHERE a.agenciaid = :agenciaid")
    , @NamedQuery(name = "AdUsuarios.findByAgencia", query = "SELECT a FROM AdUsuarios a WHERE a.agencia = :agencia")
    , @NamedQuery(name = "AdUsuarios.findByEstado", query = "SELECT a FROM AdUsuarios a WHERE a.estado = :estado")
    , @NamedQuery(name = "AdUsuarios.findByUltimasynManual", query = "SELECT a FROM AdUsuarios a WHERE a.ultimasynManual = :ultimasynManual")
    , @NamedQuery(name = "AdUsuarios.findByUltimasynAutomatica", query = "SELECT a FROM AdUsuarios a WHERE a.ultimasynAutomatica = :ultimasynAutomatica")
    , @NamedQuery(name = "AdUsuarios.findByUltimalat", query = "SELECT a FROM AdUsuarios a WHERE a.ultimalat = :ultimalat")
    , @NamedQuery(name = "AdUsuarios.findByUltimalon", query = "SELECT a FROM AdUsuarios a WHERE a.ultimalon = :ultimalon")
    , @NamedQuery(name = "AdUsuarios.findByImei", query = "SELECT a FROM AdUsuarios a WHERE a.imei = :imei")
    , @NamedQuery(name = "AdUsuarios.findByUltimocambio", query = "SELECT a FROM AdUsuarios a WHERE a.ultimocambio = :ultimocambio")
    , @NamedQuery(name = "AdUsuarios.findByContrasenia", query = "SELECT a FROM AdUsuarios a WHERE a.contrasenia = :contrasenia")
    , @NamedQuery(name = "AdUsuarios.findByEstadoCrud", query = "SELECT a FROM AdUsuarios a WHERE a.estadoCrud = :estadoCrud")})
public class AdUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "empleadoid")
    private int empleadoid;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "usuariotopaz")
    private String usuariotopaz;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "puestoid")
    private Integer puestoid;
    @Size(max = 50)
    @Column(name = "puesto")
    private String puesto;
    @Size(max = 50)
    @Column(name = "correo")
    private String correo;
    @Column(name = "intentos")
    private Short intentos;
    @Column(name = "agenciaid")
    private Short agenciaid;
    @Size(max = 100)
    @Column(name = "agencia")
    private String agencia;
    @Size(max = 20)
    @Column(name = "estado")
    private String estado;
    @Column(name = "ultimasyn_manual")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimasynManual;
    @Column(name = "ultimasyn_automatica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimasynAutomatica;
    @Size(max = 30)
    @Column(name = "ultimalat")
    private String ultimalat;
    @Size(max = 30)
    @Column(name = "ultimalon")
    private String ultimalon;
    @Size(max = 100)
    @Column(name = "imei")
    private String imei;
    @Column(name = "ultimocambio")
    @Temporal(TemporalType.DATE)
    private Date ultimocambio;
    @Size(max = 200)
    @Column(name = "contrasenia")
    private String contrasenia;
    @Size(max = 20)
    @Column(name = "estado_crud")
    private String estadoCrud;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariotopaz", fetch = FetchType.LAZY)
    //private List<AdUsuariosxagencia> adUsuariosxagenciaList;
    @Column(name = "tipousuario")
    private Integer tipousuario;
    @Size(max = 20)
    @Column(name = "codigogestorcobro")
    private String codigogestorcobro;
    @Column(name = "fechacorte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacorte;
    @Size(max = 10)
    @Column(name = "token")
    private String token;
    @Size(max = 20)
    @Column(name = "idcliente")
    private String idcliente;
    @Size(max = 20)
    @Column(name = "cuenta")
    private String cuenta;
        ///////////colores
    @Transient
    private String colorBack_Est;//fondo estructuras
    @Transient
    private String colorBack_R; //fondo estado req y casos
    @Transient
    private String colorBack_T; //fondo por tipo Caso o Estr
    @Transient
    private String colorBack_P; //fondo para permiso obj
    
    private static String gris="#9E9DAB";
    private static String amarillo="#F7DF31";
    private static String azul="#06F2F9";
    private static String verde="#AEF77F";
    private static String rojo="#FC5342";

    private static String naranja="#F8AC30";
    private static String naranja0="#F4C477";
    private static String azul2="#00B6BC";
    private static String morado="#D098F7";
    private static String morado0="#E4C7F8";
    private static String verde2="#7FCA4F";

    public String getColorBack_P() {
        
 
        
        
        return colorBack_P;
    }

    public void setColorBack_P(String colorBack_P) {
        this.colorBack_P = colorBack_P;
    }
    
    
    
    
    public String getColorBack_T() {
        /*
        if(tipo_registro.equalsIgnoreCase("C"))
        {
            colorBack_T=azul;
        }else if(tipo_registro.equalsIgnoreCase("A"))
        {
            colorBack_T=naranja;
        }
        else if(tipo_registro.equalsIgnoreCase("B"))
        {
            colorBack_T=naranja0;
        }else if(tipo_registro.equalsIgnoreCase("R")){
            colorBack_T=azul2;
        }else if(tipo_registro.equalsIgnoreCase("E"))
        {
            colorBack_T=morado;
        }else if(tipo_registro.equalsIgnoreCase("P"))
        {
            colorBack_T=morado0;
            if(estado.equalsIgnoreCase(Constantes.estado_ini_permiso))
            {
                colorBack_T=amarillo;
            }else if(estado.equalsIgnoreCase(Constantes.estado_completado_dba))
            {
                colorBack_T=gris;
            }
            
            
        }
        
        
        */
        return colorBack_T;
    }

    public void setColorBack_T(String colorBack_T) {
        this.colorBack_T = colorBack_T;
    }
    
    
    
    
    
    public String getColorBack_R() {
        
     
        
        return colorBack_R;
    }

    public void setColorBack_R(String colorBack_R) {
        this.colorBack_R = colorBack_R;
    }
    
    
    
    
    
    public String getColorBack_Est() {
        /*
        if( estado.equalsIgnoreCase(Constantes.estado_en_dba) 
                && tipo_registro.equalsIgnoreCase("B"))
        {
            colorBack_Est=gris;
        }else if(estado.equalsIgnoreCase(Constantes.estado_completado_dba)==false 
                && tipo_registro.equalsIgnoreCase("A"))
        {
            colorBack_Est=amarillo;
        }else if(estado.equalsIgnoreCase(Constantes.estado_completado_dba) 
                && tipo_registro.equalsIgnoreCase("U"))
        {
            colorBack_Est=azul;
        }else if(estado.equalsIgnoreCase(Constantes.estado_completado_dba) 
                && tipo_registro.equalsIgnoreCase("A") )
        {
            colorBack_Est=verde;
                    
        }else if(estado.equalsIgnoreCase(Constantes.estado_rechazado))
        {
            colorBack_Est=rojo;
        }

        */
        return colorBack_Est;
    }

   

    public void setColorBack_Est(String colorBack_Est) {
        this.colorBack_Est = colorBack_Est;
    }
    
    
    public AdUsuarios() {
    }

    public AdUsuarios(String usuariotopaz) {
        this.usuariotopaz = usuariotopaz;
    }

    public AdUsuarios(String usuariotopaz, int empleadoid) {
        this.usuariotopaz = usuariotopaz;
        this.empleadoid = empleadoid;
    }

    public int getEmpleadoid() {
        return empleadoid;
    }

    public void setEmpleadoid(int empleadoid) {
        this.empleadoid = empleadoid;
    }

    public String getUsuariotopaz() {
        return usuariotopaz;
    }

    public void setUsuariotopaz(String usuariotopaz) {
        this.usuariotopaz = usuariotopaz;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPuestoid() {
        return puestoid;
    }

    public void setPuestoid(Integer puestoid) {
        this.puestoid = puestoid;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Short getIntentos() {
        return intentos;
    }

    public void setIntentos(Short intentos) {
        this.intentos = intentos;
    }

    public Short getAgenciaid() {
        return agenciaid;
    }

    public void setAgenciaid(Short agenciaid) {
        this.agenciaid = agenciaid;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getUltimasynManual() {
        return ultimasynManual;
    }

    public void setUltimasynManual(Date ultimasynManual) {
        this.ultimasynManual = ultimasynManual;
    }

    public Date getUltimasynAutomatica() {
        return ultimasynAutomatica;
    }

    public void setUltimasynAutomatica(Date ultimasynAutomatica) {
        this.ultimasynAutomatica = ultimasynAutomatica;
    }

    public String getUltimalat() {
        return ultimalat;
    }

    public void setUltimalat(String ultimalat) {
        this.ultimalat = ultimalat;
    }

    public String getUltimalon() {
        return ultimalon;
    }

    public void setUltimalon(String ultimalon) {
        this.ultimalon = ultimalon;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Date getUltimocambio() {
        return ultimocambio;
    }

    public void setUltimocambio(Date ultimocambio) {
        this.ultimocambio = ultimocambio;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEstadoCrud() {
        return estadoCrud;
    }

    public void setEstadoCrud(String estadoCrud) {
        this.estadoCrud = estadoCrud;
    }

    public String getCodigogestorcobro() {
        return codigogestorcobro;
    }

    public void setCodigogestorcobro(String codigogestorcobro) {
        this.codigogestorcobro = codigogestorcobro;
    }
    
    

   /* @XmlTransient
    public List<AdUsuariosxagencia> getAdUsuariosxagenciaList() {
        return adUsuariosxagenciaList;
    }

    public void setAdUsuariosxagenciaList(List<AdUsuariosxagencia> adUsuariosxagenciaList) {
        this.adUsuariosxagenciaList = adUsuariosxagenciaList;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuariotopaz != null ? usuariotopaz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdUsuarios)) {
            return false;
        }
        AdUsuarios other = (AdUsuarios) object;
        if ((this.usuariotopaz == null && other.usuariotopaz != null) || (this.usuariotopaz != null && !this.usuariotopaz.equals(other.usuariotopaz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdUsuarios[ usuariotopaz=" + usuariotopaz + " ]";
    }

    public Integer getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(Integer tipousuario) {
        this.tipousuario = tipousuario;
    }

    public Date getFechacorte() {
        return fechacorte;
    }

    public void setFechacorte(Date fechacorte) {
        this.fechacorte = fechacorte;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
    
}
