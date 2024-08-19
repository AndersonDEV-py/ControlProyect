/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.model;

import java.util.List;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author rcardona
 */
public class Login {
    
    private String title="ATENCION INTERNA WEB";
    
    private String usuario="";
    private String clave="";
    private String nombre="";
    private String siscodigo;
    private String correo="";
    private String estado="";
    private String tipousuario="";
    private int intentos=0;
    //private String base_datos="Base-Sele";
    //private String host_server="Host-Selec";
    
    private List<MenuOpcion> lstMenuOpcion;


    /*
    private int b_gerente;
    private int b_vice_gerente;
    private int b_promotor;
    private int b_sistemas;
    */
    private boolean b_gerente_;
    private boolean b_promotor_;
    private boolean b_erc_;
    private boolean b_g_zona_;
    private boolean b_g_regional_;
    private boolean b_g_general_;
    private boolean b_sistemas_;
    private boolean b_call_center_;
    private boolean b_operativo_;
    //private String ipcliente="";
    
    private String c_agencia="";
    private String d_agencia="";
    private String c_depto="";
    private String d_depto="";
    private String c_puesto="";
    private String d_puesto="";
    
    private MenuModel model;

    public String getC_puesto() {
        return c_puesto;
    }

    public void setC_puesto(String c_puesto) {
        this.c_puesto = c_puesto;
    }

    public String getD_puesto() {
        return d_puesto;
    }

    public void setD_puesto(String d_puesto) {
        this.d_puesto = d_puesto;
    }

     
     
     
    public String getSiscodigo() {
        return siscodigo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setSiscodigo(String siscodigo) {
        this.siscodigo = siscodigo;
    }

     
     
     
    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public boolean isB_operativo_() {
        return b_operativo_;
    }

    public void setB_operativo_(boolean b_operativo_) {
        this.b_operativo_ = b_operativo_;
    }
    
    

    public boolean isB_call_center_() {
        return b_call_center_;
    }

    public void setB_call_center_(boolean b_call_center_) {
        this.b_call_center_ = b_call_center_;
    }

    
    
    public boolean isB_sistemas_() {
        return b_sistemas_;
    }

    public void setB_sistemas_(boolean b_sistemas_) {
        this.b_sistemas_ = b_sistemas_;
    }
     
     
    public void setB_g_zona_(boolean b_g_zona_) {
        this.b_g_zona_ = b_g_zona_;
    }

    public boolean isB_g_zona_() {
        return b_g_zona_;
    }

    
    
    public boolean isB_gerente_() {
        return b_gerente_;
    }

    public void setB_gerente_(boolean b_gerente_) {
        this.b_gerente_ = b_gerente_;
    }

    public boolean isB_promotor_() {
        return b_promotor_;
    }

    public void setB_promotor_(boolean b_promotor_) {
        this.b_promotor_ = b_promotor_;
    }

    public boolean isB_erc_() {
        return b_erc_;
    }

    public void setB_erc_(boolean b_erc_) {
        this.b_erc_ = b_erc_;
    }

    public boolean isB_g_regional_() {
        return b_g_regional_;
    }

    public void setB_g_regional_(boolean b_g_regional_) {
        this.b_g_regional_ = b_g_regional_;
    }

    public boolean isB_g_general_() {
        return b_g_general_;
    }

    public void setB_g_general_(boolean b_g_general_) {
        this.b_g_general_ = b_g_general_;
    }

  
 

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getC_agencia() {
        return c_agencia;
    }

    public void setC_agencia(String c_agencia) {
        this.c_agencia = c_agencia;
    }

    public String getD_agencia() {
        return d_agencia;
    }

    public void setD_agencia(String d_agencia) {
        this.d_agencia = d_agencia;
    }

    
   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public List<MenuOpcion> getLstMenuOpcion() {
        return lstMenuOpcion;
    }

    public void setLstMenuOpcion(List<MenuOpcion> lstMenuOpcion) {
        this.lstMenuOpcion = lstMenuOpcion;
    }

    public String getC_depto() {
        return c_depto;
    }

    public void setC_depto(String c_depto) {
        this.c_depto = c_depto;
    }

    public String getD_depto() {
        return d_depto;
    }

    public void setD_depto(String d_depto) {
        this.d_depto = d_depto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }
    
    
    
    
}
