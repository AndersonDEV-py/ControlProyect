/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdUsuarios;
import finsolhn.com.model.Login;
import java.util.List;
//import jakarta.ejb.Local;
import jakarta.ejb.Local;

/**
 *
 * @author ds010106
 */
@Local
public interface AdUsuariosFacadeLocal {

    void create(AdUsuarios adUsuarios);

    void edit(AdUsuarios adUsuarios);

    void remove(AdUsuarios adUsuarios);

    AdUsuarios find(Object id);
    
    AdUsuarios validarUsuariosByUserClave(AdUsuarios user);

    List<AdUsuarios> findAll();

    List<AdUsuarios> findRange(int[] range);

    List<AdUsuarios> listarUsuariosByNombreOrId(String param);
    
    List<AdUsuarios> listarUsuariosAsesoresByAgencia(String where, Login user);
            
    int count();
    
    
}
