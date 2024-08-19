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
public class ArchivoSistema {
    
    
    private int id;
    private String idReq;
    private String fecha;
    private String c_usuario_sol;
    private String c_usuario_procesa;

    public String getIdReq() {
        return idReq;
    }

    public void setIdReq(String idReq) {
        this.idReq = idReq;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getC_usuario_sol() {
        return c_usuario_sol;
    }

    public void setC_usuario_sol(String c_usuario_sol) {
        this.c_usuario_sol = c_usuario_sol;
    }

    public String getC_usuario_procesa() {
        return c_usuario_procesa;
    }

    public void setC_usuario_procesa(String c_usuario_procesa) {
        this.c_usuario_procesa = c_usuario_procesa;
    }
    
    
    
}
