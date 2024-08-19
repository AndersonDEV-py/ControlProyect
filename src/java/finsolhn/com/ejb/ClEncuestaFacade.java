/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

///import com.sun.xml.rpc.processor.modeler.j2ee.xml.string; se comento porque dice unused import por KEVIN ANDERSON SANTOS
import finsolhn.com.data.ClEncuesta;
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
public class ClEncuestaFacade extends AbstractFacade<ClEncuesta> implements ClEncuestaFacadeLocal {

    @PersistenceContext(unitName = "oraclePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClEncuestaFacade() {
        super(ClEncuesta.class);
    }
    public EntityManager getEM() {
        return em;
    }
    
}

