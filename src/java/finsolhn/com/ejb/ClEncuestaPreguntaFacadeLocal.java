/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.ClEncuestaPregunta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ds010106
 */
@Local
public interface ClEncuestaPreguntaFacadeLocal {

    void create(ClEncuestaPregunta clEncuestaPregunta);

    void edit(ClEncuestaPregunta clEncuestaPregunta);

    void remove(ClEncuestaPregunta clEncuestaPregunta);

    ClEncuestaPregunta find(Object id);

    List<ClEncuestaPregunta> findAll();

    List<ClEncuestaPregunta> findRange(int[] range);

    int count();
    
}
