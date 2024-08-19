/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdAgencias;
import finsolhn.com.data.AdUsuariosxagencia;
import finsolhn.com.model.Login;
import java.util.ArrayList;
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
public class AdUsuariosxagenciaFacade extends AbstractFacade<AdUsuariosxagencia> implements AdUsuariosxagenciaFacadeLocal {

    @PersistenceContext(unitName = "posgresPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdUsuariosxagenciaFacade() {
        super(AdUsuariosxagencia.class);
    }
       
    public boolean eliminarUserxAgencia(String user)
    {
         try{
            String consulta="DELETE FROM AdUsuariosxagencia a WHERE a.usuariotopaz='"+user+"'";
            Query query=em.createQuery(consulta);
            query.executeUpdate();
            return true;
        }catch(Exception e){
            System.err.println("Error eliminar UsuarioXAgenciaFacade"+e);
        }
         return false;
    }
    
    
    public String getMaxId()
    {
        //AdUsuariosxagencia us=null;
        Long id=null;
        try{
            String consulta="Select max(a.id)+1 FROM AdUsuariosxagencia a";
            Query query=em.createQuery(consulta);
            id= (Long)query.getSingleResult();
        }catch(Exception e){
            System.err.println("Error ObtenerId UsuarioXAgenciaFacade"+e);
        }
        if(id==null)
            return "1";
        return id.toString();
    }

    @Override
    public void create(AdUsuariosxagencia entity) {
      //  getEntityManager().flush();
      //  getEntityManager().clear();
        super.create(entity);
       // getEntityManager().merge(entity);
        //To change body of generated methods, choose Tools | Templates.
        //getEntityManager().refresh(entity);
    }

    @Override
    public List<AdUsuariosxagencia> findAll() {
       // getEntityManager().flush();
       // getEntityManager().clear();
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }
     public List<AdAgencias> findAgenciasByUsuarioGestor(String usuariotopaz, Login user)
    {
        AdAgencias obj=null;
        List<AdAgencias> listaTemp=new ArrayList();
        List<AdAgencias> lista=new ArrayList();
    
        try{
            
            String consulta="select a from AdAgencias a where a.agenciaid in(Select b.agenciaid FROM AdUsuariosxagencia b where b.usuariotopaz='"+usuariotopaz+"' )";
            
            
            
            Query query=em.createQuery(consulta);
            listaTemp =query.getResultList();
            
            
            if(listaTemp.size()>1)
            {   if(user!=null)
                {
                    if(!user.getTipousuario().equalsIgnoreCase("5"))
                    {
                        obj=new AdAgencias(); 
                        obj.setAgenciaid((short)(0));
                        obj.setAgencia("TODAS");
                        obj.setEstado(true);
                        lista.add(obj);
                    }
                    
                    
                    if(user.getC_puesto().equalsIgnoreCase("53") || user.getC_puesto().equalsIgnoreCase("20") 
                            || user.getTipousuario().equalsIgnoreCase("5") || user.getC_puesto().equalsIgnoreCase("38")
                            || user.getTipousuario().equalsIgnoreCase("6") )
                    {
                        obj=new AdAgencias(); 
                        obj.setAgenciaid((short)(100));
                        obj.setAgencia("TODAS Z.(GESTORES)");
                        obj.setEstado(true);
                        lista.add(obj);
                    }
                }  
            }
            for (int i = 0; i < listaTemp.size(); i++) {
                if(user!=null && (user.getTipousuario().equalsIgnoreCase("2") ||user.getTipousuario().equalsIgnoreCase("5") 
                        || (user.getC_puesto().equalsIgnoreCase("53")) || (user.getC_puesto().equalsIgnoreCase("20")) || user.getC_puesto().equalsIgnoreCase("38")
                        || user.getTipousuario().equalsIgnoreCase("6"))   )
                {
                    if(listaTemp.get(i).getZona().equalsIgnoreCase("NORTE"))
                    {
                        obj=new AdAgencias(); 
                        obj.setAgenciaid((short)(101));
                        obj.setAgencia("Z. "+listaTemp.get(i).getZona()+" (GESTORES)");
                        obj.setZona(listaTemp.get(i).getZona());
                        obj.setEstado(true);
                        
                        
                    }
                    if(listaTemp.get(i).getZona().equalsIgnoreCase("CENTRO-SUR"))
                    {
                        obj=new AdAgencias(); 
                        obj.setAgenciaid((short)(102));
                        obj.setAgencia("Z. "+listaTemp.get(i).getZona()+" (GESTORES)");
                        obj.setZona(listaTemp.get(i).getZona());
                        obj.setEstado(true);
                    }
                    if(!lista.contains(obj))
                    {
                            lista.add(obj);
                    }
                    if(user.getC_puesto().equalsIgnoreCase("53") || (user.getC_puesto().equalsIgnoreCase("20")) || user.getC_puesto().equalsIgnoreCase("38") 
                            || user.getTipousuario().equalsIgnoreCase("6"))    
                        lista.add(listaTemp.get(i));
                }else{
                    lista.add(listaTemp.get(i));
                }
                    
            }
            
        }catch(Exception e){
            System.err.println("Error AgenciasByUsuario UsuarioXAgenciaFacade "+e);
        }
      
        return lista;
    }
    
    
    public List<AdAgencias> findAgenciasByUsuario(String usuariotopaz, Login user)
    {
        AdAgencias obj=null;
        List<AdAgencias> listaTemp=new ArrayList();
        List<AdAgencias> lista=new ArrayList();
    
        try{
            
            String consulta="select a from AdAgencias a where a.agenciaid in(Select b.agenciaid FROM AdUsuariosxagencia b where b.usuariotopaz='"+usuariotopaz+"' )";
            
            
            
            Query query=em.createQuery(consulta);
            listaTemp =query.getResultList();
            
            
            if(listaTemp.size()>1)
            {   if(user!=null)
                {
                    if(!user.getTipousuario().equalsIgnoreCase("5"))
                    {
                        obj=new AdAgencias(); 
                        obj.setAgenciaid((short)(0));
                        obj.setAgencia("TODAS");
                        obj.setEstado(true);
                        lista.add(obj);
                    }
                    
                    /*
                    if(user.getC_puesto().equalsIgnoreCase("53") || user.getC_puesto().equalsIgnoreCase("20") 
                            || user.getTipousuario().equalsIgnoreCase("5") || user.getC_puesto().equalsIgnoreCase("38"))
                    {
                        obj=new AdAgencias(); 
                        obj.setAgenciaid((short)(100));
                        obj.setAgencia("TODAS Z.(GESTORES)");
                        obj.setEstado(true);
                        lista.add(obj);
                    }*/
                }  
            }
            for (int i = 0; i < listaTemp.size(); i++) {
                /*
                if(user!=null && (user.getTipousuario().equalsIgnoreCase("2") ||user.getTipousuario().equalsIgnoreCase("5") 
                        || (user.getC_puesto().equalsIgnoreCase("53")) || (user.getC_puesto().equalsIgnoreCase("20")) || user.getC_puesto().equalsIgnoreCase("38") ))
                {
                    if(listaTemp.get(i).getZona().equalsIgnoreCase("NORTE"))
                    {
                        obj=new AdAgencias(); 
                        obj.setAgenciaid((short)(101));
                        obj.setAgencia("Z. "+listaTemp.get(i).getZona()+" (GESTORES)");
                        obj.setZona(listaTemp.get(i).getZona());
                        obj.setEstado(true);
                        
                        
                    }
                    if(listaTemp.get(i).getZona().equalsIgnoreCase("CENTRO-SUR"))
                    {
                        obj=new AdAgencias(); 
                        obj.setAgenciaid((short)(102));
                        obj.setAgencia("Z. "+listaTemp.get(i).getZona()+" (GESTORES)");
                        obj.setZona(listaTemp.get(i).getZona());
                        obj.setEstado(true);
                    }
                    if(!lista.contains(obj))
                    {
                            lista.add(obj);
                    }
                    if(user.getC_puesto().equalsIgnoreCase("53") || (user.getC_puesto().equalsIgnoreCase("20")) || user.getC_puesto().equalsIgnoreCase("38"))    
                        lista.add(listaTemp.get(i));
                }else{
                    lista.add(listaTemp.get(i));
                }*/
                    lista.add(listaTemp.get(i));
            }
            
        }catch(Exception e){
            System.err.println("Error AgenciasByUsuario UsuarioXAgenciaFacade "+e);
        }
      
        return lista;
    }
    
    
}
