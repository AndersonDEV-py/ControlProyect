/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdUsuarios;
import finsolhn.com.data.AdVSolicitudes;
import finsolhn.com.data.AdVVisitas;
import finsolhn.com.model.Login;
import static finsolhn.com.util.Constantes.sdf_dmy;
import static finsolhn.com.util.Constantes.sdf_dmy_ham;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

/**
 *
 * @author ds010106
 */
@Stateless
public class AdVVisitasFacade extends AbstractFacade<AdVVisitas> implements AdVVisitasFacadeLocal {

    @PersistenceContext(unitName = "posgresPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public AdVVisitasFacade() {
        super(AdVVisitas.class);
    }

    @Override
    public List<AdVVisitas> listarVisitas(
              String v
            , String filtroAgencia
            , String filtroAsesor
            , String filtroNombre
            , String filtroTipoV
            , String filtroResultadoV
            , Date filtroFechaR1
            , Date filtroFechaR2
            , Date filtroFechaC1
            , Date filtroFechaC2) {
       
        List<Predicate> predicates=new ArrayList<>();
        
        /// OBTENER PARAMETROS
        List<String> lstAgencias=Arrays.asList(filtroAgencia.split(","));
        List<String> lstAsesores=Arrays.asList(filtroAsesor.split(","));

        
        try{
            if (filtroFechaR1 != null) {
                String f1 = sdf_dmy.format(filtroFechaR1) + " 12:00 AM";
                filtroFechaR1 = sdf_dmy_ham.parse(f1);  
            }

            if (filtroFechaR2 != null) {
                String f1 = sdf_dmy.format(filtroFechaR2) + " 11:59 PM";
                filtroFechaR2 = sdf_dmy_ham.parse(f1);            
            }
   
            if (filtroFechaC1 != null) {
                String f1 = sdf_dmy.format(filtroFechaC1) + " 12:00 AM";
                filtroFechaC1 = sdf_dmy_ham.parse(f1);  
            }
            if (filtroFechaC2 != null) {
                String f1 = sdf_dmy.format(filtroFechaC2) + " 11:59 PM";
                filtroFechaC2 = sdf_dmy_ham.parse(f1);  
            }
           
           
        }catch(Exception e){} 
        
        ///FIN OBTENER PARAMETROS
        
        /*#INICIO# INSTANCIAR TABLA crear Select y From*/
        //LIMPIAR CACHE
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        //1. Se crea el Constructor BUILDER
        CriteriaBuilder cbuilder=em.getCriteriaBuilder(); 
        //2. Se crea Criteria Query  y tipo de resultado
        CriteriaQuery<AdVVisitas> cquery=cbuilder.createQuery(AdVVisitas.class);  
        //3. Se indica la Raiz FROM
        Root<AdVVisitas> raiz=cquery.from(AdVVisitas.class); 
        //4. Se crea la consulta
        cquery.select(raiz); 
        /*#FIN# INSTANCIAR TABLA crear Select y From*/
        
        if( !(lstAgencias.contains("100") || lstAgencias.contains("101") || lstAgencias.contains("102")) )
            predicates.add(raiz.get("idagencia").in(lstAgencias));
        
        predicates.add(raiz.get("usragenda").in(lstAsesores));
        if(!filtroTipoV.isEmpty())
            predicates.add(raiz.get("tipovisita").in(filtroTipoV));
        
        
        if(filtroResultadoV.equalsIgnoreCase("0"))
        {
            //predicates.add(raiz.get("resultadovisita").in(""));
        }else{
            predicates.add(raiz.get("resultadovisita").in(filtroResultadoV));
        }
        
        if(!filtroNombre.isEmpty())
        {
            Predicate predicateOr1_NombreCliente = cbuilder.like(raiz.get("nombre"), "%"+filtroNombre.toUpperCase()+"%");
            
            Predicate predicateOr2_NombrePromo = cbuilder.like(raiz.get("nombrenuevo"), "%"+filtroNombre.toUpperCase()+"%");
            
            Predicate predicateForNombreAnNombre2 = cbuilder.or(predicateOr1_NombreCliente, predicateOr2_NombrePromo);
            
            predicates.add(predicateForNombreAnNombre2);
            //predicates.add(cbuilder.like(raiz.get("nombre"), "%"+filtroNombre.toUpperCase()+"%"));
        }
        
        
        
        
        if(filtroFechaR1==null && filtroFechaR2==null && filtroFechaC1==null && filtroFechaC2==null)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            predicates.add(cbuilder.greaterThanOrEqualTo(raiz.get("fechaprovisita"), calendar));
        }else{
             if(filtroFechaR1!=null)
             {
                 predicates.add(cbuilder.greaterThanOrEqualTo(raiz.get("fechaprovisita"), filtroFechaR1));
             }
             if(filtroFechaR2!=null)
             {
                 predicates.add(cbuilder.lessThanOrEqualTo(raiz.get("fechaprovisita"), filtroFechaR2));
             }
             //predicates.add(cbuilder.between(raiz.<Date>get("fechaprovisita"),filtroFechaR1 , filtroFechaR2));
             
            if(filtroFechaC1!=null)
            {
                predicates.add(cbuilder.greaterThanOrEqualTo(raiz.get("fechacompleto"), filtroFechaC1));
            }
            if(filtroFechaC2!=null)
            {
                predicates.add(cbuilder.lessThanOrEqualTo(raiz.get("fechacompleto"), filtroFechaC2));
            }             
             
        }
                
        cquery.where(predicates.toArray(new Predicate[]{}));
        
        //cquery.orderBy(cbuilder.desc(raiz.get("fecharegistro")));  
        //5. Se Ejecuta
        TypedQuery<AdVVisitas> q = em.createQuery(cquery);  
        
        return q.getResultList();
    }
    
    public List<AdVVisitas> obtenerVisitasByTipo(String tipo,Date filtroFechaC1,Date filtroFechaC2,Login user){
    
        try{
            if (filtroFechaC1 != null) {
                String f1 = sdf_dmy.format(filtroFechaC1) + " 12:00 AM";
                filtroFechaC1 = sdf_dmy_ham.parse(f1);
               // whereFechaCompleto = whereFechaCompleto + " and a.fechacompleto >=:fechaC1 ";
            }

            if (filtroFechaC2 != null) {
                String f1 = sdf_dmy.format(filtroFechaC2) + " 11:59 PM";
                filtroFechaC2 = sdf_dmy_ham.parse(f1);
                //whereFechaCompleto = whereFechaCompleto + " and a.fechacompleto <=:fechaC2 ";
            }
            
        }catch(Exception e){
            System.out.println("Error VisitasFacade obtenerVisitasByTipo fechas"+e);
        }
        List<Predicate> predicates=new ArrayList<>();
        /*#INICIO# INSTANCIAR TABLA crear Select y From*/
        //LIMPIAR CACHE
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        //1. Se crea el Constructor BUILDER
        CriteriaBuilder cbuilder=em.getCriteriaBuilder(); 
        //2. Se crea Criteria Query  y tipo de resultado
        CriteriaQuery<AdVVisitas> cquery=cbuilder.createQuery(AdVVisitas.class);  
        //3. Se indica la Raiz FROM
        Root<AdVVisitas> raiz=cquery.from(AdVVisitas.class); 
        //4. Se crea la consulta
        cquery.select(raiz); 
        /*#FIN# INSTANCIAR TABLA crear Select y From*/
        if(!tipo.isEmpty()){
            predicates.add(raiz.get("tipovisita").in(tipo));
        }
        //sub  query
        CriteriaQuery<AdUsuarios> subCquery=cbuilder.createQuery(AdUsuarios.class); 
        Root<AdUsuarios> subRaiz=subCquery.from(AdUsuarios.class); 
        subCquery.select(subRaiz); 
        List<Predicate> subPredicates=new ArrayList<>();
        List<String> gestores=Arrays.asList("91","31");
        subPredicates.add(subRaiz.get("puestoid").in(gestores));
        subCquery.where(subPredicates.toArray(new Predicate[]{}));
        List<AdUsuarios> users=em.createQuery(subCquery).getResultList();
         //fin sub  query
        if(!user.getTipousuario().equalsIgnoreCase("2") && !user.getTipousuario().equalsIgnoreCase("5"))
        {
            predicates.add(raiz.get("usragenda").in(users).not());
        }else{
            predicates.add(raiz.get("usragenda").in(users));
        }
        if(filtroFechaC1!=null)
        {
            predicates.add(cbuilder.greaterThanOrEqualTo(raiz.get("fechacompleto"), filtroFechaC1));
        }
        if(filtroFechaC2!=null)
        {
            predicates.add(cbuilder.lessThanOrEqualTo(raiz.get("fechacompleto"), filtroFechaC2));
        }           
        
        
         cquery.where(predicates.toArray(new Predicate[]{}));
        
        //cquery.orderBy(cbuilder.desc(raiz.get("fecharegistro")));  
        //# Se Ejecuta
        TypedQuery<AdVVisitas> q = em.createQuery(cquery);  
        
        return q.getResultList();
    }
}
