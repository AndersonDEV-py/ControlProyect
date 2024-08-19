/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdClientes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ds010106
 */
@Local
public interface AdClientesFacadeLocal {

    void create(AdClientes adClientes);

    void edit(AdClientes adClientes);

    void remove(AdClientes adClientes);

    AdClientes find(Object id);

    List<AdClientes> findAll();

    List<AdClientes> findRange(int[] range);
    
    List<AdClientes> obtenerClientesByidIn(List<String> list1);

    int count();
    
}
