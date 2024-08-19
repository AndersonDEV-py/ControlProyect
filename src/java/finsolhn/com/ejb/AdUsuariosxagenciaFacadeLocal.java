/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdAgencias;
import finsolhn.com.data.AdUsuariosxagencia;
import finsolhn.com.model.Login;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ds010106
 */
@Local
public interface AdUsuariosxagenciaFacadeLocal {

    void create(AdUsuariosxagencia adUsuariosxagencia);

    void edit(AdUsuariosxagencia adUsuariosxagencia);

    void remove(AdUsuariosxagencia adUsuariosxagencia);
    
    boolean eliminarUserxAgencia(String user);

    String getMaxId();
            
    AdUsuariosxagencia find(Object id);

    List<AdUsuariosxagencia> findAll();

    List<AdUsuariosxagencia> findRange(int[] range);
    
    List<AdAgencias> findAgenciasByUsuarioGestor(String usuariotopaz,Login user);
    
    List<AdAgencias> findAgenciasByUsuario(String usuariotopaz,Login user);

    int count();
    
}
