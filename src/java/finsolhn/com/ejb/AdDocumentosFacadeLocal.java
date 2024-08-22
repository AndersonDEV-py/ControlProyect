/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdDocumentos;
import java.util.List;
import jakarta.ejb.Local;

/**
 *
 * @author ds010106
 */
@Local
public interface AdDocumentosFacadeLocal {

    void create(AdDocumentos adDocumentos);

    void edit(AdDocumentos adDocumentos);

    void remove(AdDocumentos adDocumentos);

    AdDocumentos find(Object id);

    List<AdDocumentos> findAll();

    List<AdDocumentos> findRange(int[] range);

    int count();
    
}
