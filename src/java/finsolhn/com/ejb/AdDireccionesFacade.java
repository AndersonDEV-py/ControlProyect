/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdDirecciones;
import java.util.ArrayList;
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
public class AdDireccionesFacade extends AbstractFacade<AdDirecciones> implements AdDireccionesFacadeLocal {

    @PersistenceContext(unitName = "posgresPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdDireccionesFacade() {
        super(AdDirecciones.class);
    }
    
    
    
    public List<AdDirecciones> listarDireccionesByIdClientes(List<String> list1)
    {
        List<AdDirecciones> lista = new ArrayList();
       // List<AdVisitas> listaVisitas = null;
        try{
            String consulta="SELECT a FROM AdDirecciones a WHERE a.adDireccionesPK.idcliente in :list1";
            Query query=em.createQuery(consulta);
            query.setParameter("list1", list1);
            lista=query.getResultList();
        }catch(Exception e){
            System.err.println("Error DireccionesFacade obtener direcciones "+e);
        }
        
        return lista;
        
    }
    
    
}
