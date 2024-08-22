/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

//import com.sun.xml.rpc.processor.modeler.j2ee.xml.string; comentado por KEVIN ANDERSON SANTOS porque dice unused import
import finsolhn.com.data.ClEncuesta;
import java.util.List;
import jakarta.ejb.Local;

/**
 *
 * @author ds010106
 */
@Local
public interface ClEncuestaFacadeLocal {

    void create(ClEncuesta clEncuesta);

    void edit(ClEncuesta clEncuesta);

    void remove(ClEncuesta clEncuesta);

    ClEncuesta find(Object id);

    List<ClEncuesta> findAll();

    List<ClEncuesta> findRange(int[] range);
   
    int count();
}
