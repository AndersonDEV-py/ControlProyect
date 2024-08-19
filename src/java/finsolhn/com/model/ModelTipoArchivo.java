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
public class ModelTipoArchivo {
    
    
    private int id;
    private String tipo_d;
    private String extension;//separr con ;

    public ModelTipoArchivo(int id, String tipo_d, String extension) {
        this.id = id;
        this.tipo_d = tipo_d;
        this.extension = extension;
    }

    public ModelTipoArchivo() {
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo_d() {
        return tipo_d;
    }

    public void setTipo_d(String tipo_d) {
        this.tipo_d = tipo_d;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    
    
}
