package finsolhn.com.ejb;

import finsolhn.com.data.AdVSolicitudes;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

// Autor:Ingeniero Renan Cardona
 
@Local
public interface AdVSolicitudesFacadeLocal {
    
    //Crear solicitud
    void create(AdVSolicitudes adVSolicitudes);
    
    //Editar solicitud
    void edit(AdVSolicitudes adVSolicitudes);

   //Eliminar solicitud
    void remove(AdVSolicitudes adVSolicitudes);

    //Encontrar solicitud especifica
    AdVSolicitudes find(Object id);

    //Hacer lista de todas las solicitudes
    List<AdVSolicitudes> findAll();

    //Hacer una lista de solicitudes acorde a los parametros ingresados
    List<AdVSolicitudes> findWithParam(String filtroagencia,String filtroasesor
            ,String filtroestado,String filtronombre,String filtromotivo
            ,Date filtroFechaR1,Date filtroFechaR2);
    
    List<AdVSolicitudes> findRange(int[] range);

    int count();
    
}
