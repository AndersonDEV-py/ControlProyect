/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdDirecciones;
import java.util.List;
import jakarta.ejb.Local;

/**
 *
 * @author ds010106
 */
@Local
public interface AdDireccionesFacadeLocal {

    void create(AdDirecciones adDirecciones);

    void edit(AdDirecciones adDirecciones);

    void remove(AdDirecciones adDirecciones);

    AdDirecciones find(Object id);

    List<AdDirecciones> findAll();

    List<AdDirecciones> findRange(int[] range);
    
    List<AdDirecciones> listarDireccionesByIdClientes(List<String> list1);

    int count();
    
}
