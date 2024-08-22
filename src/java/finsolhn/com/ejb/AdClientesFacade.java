/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdClientes;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 *
 * @author ds010106
 */
@Stateless
public class AdClientesFacade extends AbstractFacade<AdClientes> implements AdClientesFacadeLocal {

    @PersistenceContext(unitName = "posgresPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdClientesFacade() {
        super(AdClientes.class);
    }
    
    public List<AdClientes> obtenerClientesByidIn(List<String> list1)
    {
        //obtener clientes
        List<AdClientes> lista = null;
        try {
            String consulta = "SELECT a FROM AdClientes a WHERE a.idcliente in :list1  ";
            
            //System.out.println("2.2.create query clientes  ");
            Query query = em.createQuery(consulta);
            query.setParameter("list1", list1);

            lista = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error ClientesFacade obtener clientes" + e);
        }
        
        return lista;
    }
    
}
