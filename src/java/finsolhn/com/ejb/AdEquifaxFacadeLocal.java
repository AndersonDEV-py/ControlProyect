/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdEquifax;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ds010106
 */
@Local
public interface AdEquifaxFacadeLocal {

    void create(AdEquifax adEquifax);

    void edit(AdEquifax adEquifax);

    void remove(AdEquifax adEquifax);

    AdEquifax find(Object id);

    List<AdEquifax> findAll();

    List<AdEquifax> findRange(int[] range);
    
    AdEquifax findByIdentidad(String identidad);

    int count();
    
}
