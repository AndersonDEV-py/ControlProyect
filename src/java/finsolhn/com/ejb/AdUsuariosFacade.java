/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdUsuarios;
import finsolhn.com.model.Login;
import java.util.ArrayList;
import java.util.List;
//import jakarta.ejb.Stateless;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

/**
 *
 * @author ds010106
 */
@Stateless
public class AdUsuariosFacade extends AbstractFacade<AdUsuarios> implements AdUsuariosFacadeLocal {

    @PersistenceContext(unitName = "posgresPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdUsuariosFacade() {
        super(AdUsuarios.class);
    }
    
    
    
     public AdUsuarios validarUsuariosByUserClave(AdUsuarios user)
    {
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        
        AdUsuarios usuario = null;
        try{
            String consulta="SELECT a FROM AdUsuarios a WHERE "
                    + "UPPER(a.usuariotopaz) = '"+user.getUsuariotopaz().toUpperCase()+"' and a.contrasenia='"+user.getContrasenia()+"' ";
            //System.out.println(""+consulta);
            Query query=em.createQuery(consulta);
            try{
               usuario=(AdUsuarios)(query.getSingleResult()); 
            }catch(Exception e1){
                System.out.println("No Existe USuario");
            }
            
        }catch(Exception e){
            System.err.println("Error UsuarioFacade validaUsuarioxClave"+e);
        }
        
        return usuario;
    }
    
    public List<AdUsuarios> listarUsuariosByNombreOrId(String param)
    {
        //getEntityManager().flush();
        //getEntityManager().clear();
        //getEntityManager().getEntityManagerFactory().getCache().evictAll();
        List<AdUsuarios> lista = null;
        try{
            String consulta="SELECT a FROM AdUsuarios a WHERE UPPER(a.nombre) like '%"+
                    param.toUpperCase()+"%' OR CAST(a.empleadoid as text) like '%"+param+"%' "
                    + "OR UPPER(a.agencia) like UPPER('%"+param.toUpperCase()+"%') order by a.agenciaid,a.usuariotopaz asc";
            Query query=em.createQuery(consulta);
            lista=query.getResultList();
        }catch(Exception e){
            System.err.println("Error UsuarioFacade"+e);
        }
        
        return lista;
    }
    
    public List<AdUsuarios> listarUsuariosAsesoresByAgencia(String where, Login user)
    {
        List<AdUsuarios> lista =new ArrayList();
        List<AdUsuarios> listaTemp =new ArrayList();
        AdUsuarios obj;
        
        if (user.getC_puesto().equalsIgnoreCase("81") || user.getC_puesto().equalsIgnoreCase("16") 
                || user.getTipousuario().equalsIgnoreCase("2")) {
            where = where + " and a.empleadoid='" + user.getSiscodigo() + "' ";
        } else {

            obj = new AdUsuarios();
            obj.setEmpleadoid(0);
            obj.setUsuariotopaz("0");
            obj.setNombre("TODOS");
            lista.add(obj);

        }

        try{
            String consulta="SELECT a FROM AdUsuarios a WHERE a.puestoid in(81,16,63 ,91,31) "+where;//System.out.println(""+consulta);
            Query query=em.createQuery(consulta);
            listaTemp=query.getResultList();
            for (int i = 0; i < listaTemp.size(); i++) {
                lista.add(listaTemp.get(i));
            }
            listaTemp.clear();
        }catch(Exception e){
            System.err.println("Error UsuarioFacade"+e);
        }
        
        return lista;
    }

    @Override
    public List<AdUsuarios> findAll() {
        
       // getEntityManager().flush();
       // getEntityManager().clear();
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        CriteriaBuilder cb=em.getCriteriaBuilder();  
        CriteriaQuery<AdUsuarios> cq=cb.createQuery(AdUsuarios.class);  
        
        Root<AdUsuarios> stud=cq.from(AdUsuarios.class);  
        
        cq.orderBy(cb.asc(stud.get("agenciaid")));  
        
        CriteriaQuery<AdUsuarios> select = cq.select(stud);  
        TypedQuery<AdUsuarios> q = em.createQuery(select);  
        
        return q.getResultList();
        
        //return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdUsuarios find(Object id) {
        
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
