/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdPrestamos;
import finsolhn.com.model.ActividadesMoviles;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ds010106
 */
@Local
public interface AdPrestamosFacadeLocal {

    void create(AdPrestamos adPrestamos);

    void edit(AdPrestamos adPrestamos);

    void remove(AdPrestamos adPrestamos);

    AdPrestamos find(Object id);

    List<AdPrestamos> findAll();

    List<AdPrestamos> findRange(int[] range);
    
    List<ActividadesMoviles> listarClienteSinVisitas(
            String filtroAgencia,
            String filtroAsesor,
            String filtroNombre,
            Date filtroFechaUV);

    int count();
    
}
