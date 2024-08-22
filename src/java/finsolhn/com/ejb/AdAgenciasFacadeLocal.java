/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdAgencias;
import finsolhn.com.model.Login;
import java.util.List;
import jakarta.ejb.Local;

/**
 *
 * @author ds010106
 */
@Local
public interface AdAgenciasFacadeLocal {

    void create(AdAgencias adAgencias);

    void edit(AdAgencias adAgencias);

    void remove(AdAgencias adAgencias);

    AdAgencias find(Object id);

    List<AdAgencias> findAll();

    List<AdAgencias> findRange(int[] range);

    int count();
    
    //public List<AdAgencias> listarAgenciasxUser(String where, Login user);
    
}
