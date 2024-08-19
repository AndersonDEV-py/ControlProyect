package finsolhn.com.ejb;

import finsolhn.com.data.AdVisitas;
import finsolhn.com.model.ActividadesMoviles;
import finsolhn.com.model.Login;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

// Autor:Ingeniero Renan Cardona

@Local
public interface AdVisitasFacadeLocal {
    
    //Crear visita
    void create(AdVisitas adVisitas);

    //Editar visita
    void edit(AdVisitas adVisitas);

    //Eliminar visita
    void remove(AdVisitas adVisitas);

    //Encontrar visita por id
    AdVisitas find(Object id);

    //Encontrar todas las visitas y agregarlas a una lista
    List<AdVisitas> findAll();

    List<AdVisitas> findRange(int[] range);
    
    //Encontrar las visitas segun los filtros indicados y agregas a una lista
    List<ActividadesMoviles> listarVisitas(
            String v,
            String filtroAgencia,
            String filtroAsesor,
            String filtroNombre,
            String filtroTipoV,
            String filtroResultadoV,
            Date filtroFechaR1,
            Date filtroFechaR2,
            Date filtroFechaC1,
            Date filtroFechaC2
            );
    
    //Obtener visitas cuyo jts se encuentre en la lista establecida
    List<AdVisitas> obtenerVisitasAntByJtsIn(List<String> list1);
         
    //Obtener visitas segun el tipo
    List<AdVisitas> obtenerVisitasByTipo(String tipo,Date filtroFechaC1,Date filtroFechaC2,Login user);
    
    int count();
    
    public EntityManager getEM();
}
