/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.model;

import java.io.Serializable;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author ds010106
 */
public class MyFile implements Serializable {
    
    
    private int id_doc;
    private int id_req;
    private String nombre;
    private String ruta_raiz;
    private String ruta_folder;
    private UploadedFile file;
    private int estado;
    private boolean existe;
    private String usuario;
    private String d_usuario;
    private String fecha;

    private String color;

    public String getColor() {
        if(estado==1)
            color="#FFFFFF";
        else
            color="#FF0000";
        return color;
    }

    public void setColor(String color) {
        
        this.color = color;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getD_usuario() {
        return d_usuario;
    }

    public void setD_usuario(String d_usuario) {
        this.d_usuario = d_usuario;
    }
    
    
    
    public int getId_doc() {
        
        return id_doc;
    }

    public void setId_doc(int id_doc) {
        this.id_doc = id_doc;
    }

    public int getId_req() {
        return id_req;
    }

    public void setId_req(int id_req) {
        this.id_req = id_req;
    }
    
    
    
  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta_raiz() {
        return ruta_raiz;
    }

    public void setRuta_raiz(String ruta_raiz) {
        this.ruta_raiz = ruta_raiz;
    }

    public String getRuta_folder() {
        return ruta_folder;
    }

    public void setRuta_folder(String ruta_folder) {
        this.ruta_folder = ruta_folder;
    }

   

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
    
    
}
