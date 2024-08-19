/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.ClEncuestaPreguntaOpcion;
import javax.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author ds010106
 */
@Stateless
public class ClEncuestaPreguntaOpcionFacade extends AbstractFacade<ClEncuestaPreguntaOpcion> {

    @PersistenceContext(unitName = "oraclePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClEncuestaPreguntaOpcionFacade() {
        super(ClEncuestaPreguntaOpcion.class);
    }
    
}
