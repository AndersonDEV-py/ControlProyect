/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdEquifax;
import java.util.List;
import javax.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 *
 * @author ds010106
 */
@Stateless
public class AdEquifaxFacade extends AbstractFacade<AdEquifax> implements AdEquifaxFacadeLocal {

    @PersistenceContext(unitName = "posgresPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    public AdEquifax findByIdentidad(String identidad)
    {
        AdEquifax Result = null;
        try {
            String consulta = "AdEquifax.findByIdentidad";
            Query query = em.createNamedQuery(consulta,AdEquifax.class);
            query.setParameter("identidad", identidad);
            Result = (AdEquifax)query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error AdEquifax obtener lista" + e);
        }
        return Result;
    }
    
    public AdEquifaxFacade() {
        super(AdEquifax.class);
    }
    
}
