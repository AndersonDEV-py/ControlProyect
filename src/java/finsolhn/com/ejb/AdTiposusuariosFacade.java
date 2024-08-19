/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdAgencias;
import finsolhn.com.data.AdTiposusuarios;
import java.util.List;
import javax.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author ds010106
 */
@Stateless
public class AdTiposusuariosFacade extends AbstractFacade<AdTiposusuarios> implements AdTiposusuariosFacadeLocal {

    @PersistenceContext(unitName = "posgresPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdTiposusuariosFacade() {
        super(AdTiposusuarios.class);
    }
    
    @Override
    public List<AdTiposusuarios> findAll() {
        return em.createQuery("select a from AdTiposusuarios a where a.estado=1 order by a.idtipousuario").getResultList();
        //super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
