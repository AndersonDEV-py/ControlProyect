/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdTiposusuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ds010106
 */
@Local
public interface AdTiposusuariosFacadeLocal {

    void create(AdTiposusuarios adTiposusuarios);

    void edit(AdTiposusuarios adTiposusuarios);

    void remove(AdTiposusuarios adTiposusuarios);

    AdTiposusuarios find(Object id);

    List<AdTiposusuarios> findAll();

    List<AdTiposusuarios> findRange(int[] range);

    int count();
    
}
