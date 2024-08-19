/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.model;

/**
 *
 * @author ds010106
 */
public class EncuestaCuentas {
    private String Cuenta;
    private String Producto;
    private String NombreProducto;
    private String UsuarioTopaz;
    private String NombreUsuario;

    public EncuestaCuentas(String Cuenta, String Producto, String NombreProducto, String UsuarioTopaz, String NombreUsuario) {
        this.Cuenta = Cuenta;
        this.Producto = Producto;
        this.NombreProducto = NombreProducto;
        this.UsuarioTopaz = UsuarioTopaz;
        this.NombreUsuario = NombreUsuario;
    }

    public EncuestaCuentas() {
       
    }

    public String getCuenta() {
        return Cuenta;
    }

    public void setCuenta(String Cuenta) {
        this.Cuenta = Cuenta;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public String getUsuarioTopaz() {
        return UsuarioTopaz;
    }

    public void setUsuarioTopaz(String UsuarioTopaz) {
        this.UsuarioTopaz = UsuarioTopaz;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }
    
    
    
}
