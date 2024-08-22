/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.ClEncuestaPregunta;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author ds010106
 */
@Stateless
public class ClEncuestaPreguntaFacade extends AbstractFacade<ClEncuestaPregunta> implements ClEncuestaPreguntaFacadeLocal {

    @PersistenceContext(unitName = "oraclePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClEncuestaPreguntaFacade() {
        super(ClEncuestaPregunta.class);
    }
    
}
