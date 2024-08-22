/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;


import finsolhn.com.data.AdAgencias;
import finsolhn.com.model.Login;
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
public class AdAgenciasFacade extends AbstractFacade<AdAgencias> implements AdAgenciasFacadeLocal {

    @PersistenceContext(unitName = "posgresPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdAgenciasFacade() {
        super(AdAgencias.class);
    }
    
    @Override
    public List<AdAgencias> findAll() {
        return em.createQuery("select a from AdAgencias a where a.estado=1 order by a.agenciaid").getResultList();
        //super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }
    /*
    public List<AdAgencias> listarAgenciasxUser(String where, Login user)
    {
        List<AdAgencias> lista = new ArrayList();
        List<AdAgencias> listaTem = new ArrayList();
        AdAgencias obj;
        if (user.getC_puesto().equalsIgnoreCase("60")) {
            obj = new AdAgencias();
            obj.setAgenciaid( (short) 0 );
            obj.setAgencia("TODAS");
            lista.add(obj);

            where = where + " AND b.usuariotopaz ='" +  user.getUsuario() + "' ";
        } else {
            where = where + " AND a.agenciaid='" + user.getC_agencia() + "' ";
        }

        
        
         try{
            String consulta="SELECT a FROM AdAgencias a INNER JOIN AdUsuariosxagencia"
                    + " b on a.agenciaid=b.agenciaid WHERE 1=1 "+where;
            Query query=em.createQuery(consulta);
            listaTem=query.getResultList();
             for (int i = 0; i < listaTem.size(); i++) {
                 lista.add(listaTem.get(i));
             }
        }catch(Exception e){
            System.err.println("Error AdAgencias listarAgenciasxUser "+e);
        }
        
        return lista;
    }
    
    */

 
    
}
