package finsolhn.com.ejb;

import finsolhn.com.data.AdUsuarios;
import finsolhn.com.data.AdVSolicitudes;
import static finsolhn.com.util.Constantes.sdf_dmy;
import static finsolhn.com.util.Constantes.sdf_dmy_ham;
import java.util.*;
import javax.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

 // Autor:Ingeniero Renan Cardona

@Stateless
public class AdVSolicitudesFacade extends AbstractFacade<AdVSolicitudes> implements AdVSolicitudesFacadeLocal {

    @PersistenceContext(unitName = "posgresPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    //Usando JPA
    public List<AdVSolicitudes> findWithParam(String filtroagencia
            ,String filtroasesor,String filtroestado,String filtronombre
            ,String filtromotivo,Date filtroFechaR1, Date filtroFechaR2) {
        
        List<String> lstAgencias=Arrays.asList(filtroagencia.split(","));
        List<String> lstAsesores=Arrays.asList(filtroasesor.split(","));
        List<String> lstEstado=Arrays.asList(filtroestado.split(","));
        List<String> lstMotivo=Arrays.asList(filtromotivo.split(","));
        
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        //1. Se crea el Constructor BUILDER
        CriteriaBuilder cbuilder=em.getCriteriaBuilder(); 
        //2. Se crea Criteria Query  y tipo de resultado
        CriteriaQuery<AdVSolicitudes> cquery=cbuilder.createQuery(AdVSolicitudes.class);  
        //3. Se indica la Raiz FROM
        Root<AdVSolicitudes> raiz=cquery.from(AdVSolicitudes.class); 
        //4. Se crea la consulta
        cquery.select(raiz); 
        List<Predicate> predicates=new ArrayList<Predicate>();
        predicates.add(raiz.get("agenciaid").in(lstAgencias));
        predicates.add(raiz.get("usuariotopaz").in(lstAsesores));
        predicates.add(raiz.get("estadosolicitud").in(lstEstado));
        
       //Iniciar filtro para solicitudes rechazadas 
        if(filtroestado.equalsIgnoreCase("RECHAZADA"))
            predicates.add(raiz.get("motivorechazo").in(lstMotivo));
       
        predicates.add(cbuilder.like(raiz.get("nombrecompleto"), "%"+filtronombre.toUpperCase()+"%"));
                
        try{
            if (filtroFechaR1 != null) {
                String f1 = sdf_dmy.format(filtroFechaR1) + " 12:00 AM";
                filtroFechaR1 = sdf_dmy_ham.parse(f1);  
            }

            if (filtroFechaR2 != null) {
                String f1 = sdf_dmy.format(filtroFechaR2) + " 11:59 PM";
                filtroFechaR2 = sdf_dmy_ham.parse(f1);            
            }
            if(filtroFechaR1==null || filtroFechaR2==null)
            {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, -3);
                predicates.add(cbuilder.greaterThanOrEqualTo(raiz.get("fecharegistro"), calendar));
            }else{
                 predicates.add(cbuilder.between(raiz.<Date>get("fecharegistro"),filtroFechaR1 , filtroFechaR2));
            }
           
           
        }catch(Exception e){}                   
         
        cquery.where(predicates.toArray(new Predicate[]{}));
        
        cquery.orderBy(cbuilder.desc(raiz.get("fecharegistro")));  
        //5. Se Ejecuta
        TypedQuery<AdVSolicitudes> q = em.createQuery(cquery);  
        
        return q.getResultList();
     }
    
    //Buscar todasd las solicitudes
    @Override
    public List<AdVSolicitudes> findAll() {
        
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        //1. Se crea el Constructor BUILDER
        CriteriaBuilder cbuilder=em.getCriteriaBuilder(); 
        //2. Se crea Criteria Query  y tipo de resultado
        CriteriaQuery<AdVSolicitudes> cquery=cbuilder.createQuery(AdVSolicitudes.class);  
        //3. Se indica la Raiz FROM
        Root<AdVSolicitudes> raiz=cquery.from(AdVSolicitudes.class);  
        //Se ordena Opcional
        cquery.orderBy(cbuilder.desc(raiz.get("fecharegistro")));  
        //4. Se Indica SELECT
        cquery.select(raiz);  
        //5. Se Ejecuta
        TypedQuery<AdVSolicitudes> q = em.createQuery(cquery);  
        
        return q.getResultList();
        
    }
    
    //Buscar una solicitud
    @Override
    public AdVSolicitudes find(Object id) {
        
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        return super.find(id); 
    }
    
    public AdVSolicitudesFacade() {
        super(AdVSolicitudes.class);
    }
    
    /*
    //Usando JPQL
    public List<AdVSolicitudes> findWithParam(String filtroagencia
            ,String filtroasesor,String filtroestado,String filtronombre
            ,String filtromotivo,Date filtroFechaR1, Date filtroFechaR2) {
      
        List<String> lstAgencias=Arrays.asList(filtroagencia.split(","));
        List<String> lstAsesores=Arrays.asList(filtroasesor.split(","));
        List<String> lstEstado=Arrays.asList(filtroestado.split(","));
        List<String> lstMotivo=Arrays.asList(filtromotivo.split(","));
        List<AdVSolicitudes> listaR = null;
        
         String and_rechazo="";
         if(filtroestado.equalsIgnoreCase("RECHAZADA"))
             and_rechazo=" and a.motivorechazo in :list4";
         String and_fechas="";
         Calendar calendar = Calendar.getInstance();
         try{
            if (filtroFechaR1 != null) {
                String f1 = sdf_dmy.format(filtroFechaR1) + " 12:00 AM";
                filtroFechaR1 = sdf_dmy_ham.parse(f1);
            }

            if (filtroFechaR2 != null) {
                String f1 = sdf_dmy.format(filtroFechaR2) + " 11:59 PM";
                filtroFechaR2 = sdf_dmy_ham.parse(f1);            
            }
             
            if(filtroFechaR1==null || filtroFechaR2==null)
            {
                calendar.add(Calendar.MONTH, -3);
                filtroFechaR1=calendar.getTime();
                and_fechas=" and a.fecharegistro >= :fecha1  ";
            }else{
                and_fechas=" and a.fecharegistro BETWEEN :fecha1 and :fecha2 ";
            }
           
                
         }catch(Exception e){}
          
        try {
            String consulta = "SELECT a FROM AdVSolicitudes a WHERE a.agenciaid in :list1 "
                    + "and a.usuariotopaz in :list2 and a.estadosolicitud in :list3 "
                    + and_rechazo
                    + and_fechas
                    + " and a.nombrecompleto like '%"+filtronombre+"%' order by a.fecharegistro desc";
            Query query = em.createQuery(consulta);
            query.setParameter("list1", lstAgencias);
            query.setParameter("list2", lstAsesores);
            query.setParameter("list3", lstEstado);
            if(filtroestado.equalsIgnoreCase("RECHAZADA"))
                query.setParameter("list4", lstMotivo);
            if(filtroFechaR1==null || filtroFechaR2==null)
                query.setParameter("fecha1", filtroFechaR1);
            else{
                query.setParameter("fecha1", filtroFechaR1);
                query.setParameter("fecha2", filtroFechaR2);
            }
            listaR = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error AdVSoliciutdes obtener lista" + e);
        }
        
        return listaR;
    }
    */
    
}
