/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdSolicitudes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ds010106
 */
@Local
public interface AdSolicitudesFacadeLocal {

    void create(AdSolicitudes adSolicitudes);

    void edit(AdSolicitudes adSolicitudes);

    void remove(AdSolicitudes adSolicitudes);

    AdSolicitudes find(Object id);

    List<AdSolicitudes> findAll();

    List<AdSolicitudes> findRange(int[] range);

    int count();
    
}
