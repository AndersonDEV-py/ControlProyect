/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdVVisitas;
import finsolhn.com.model.Login;
import java.util.Date;
import java.util.List;
import jakarta.ejb.Local;

/**
 *
 * @author ds010106
 */
@Local
public interface AdVVisitasFacadeLocal {

    void create(AdVVisitas adVVisitas);

    void edit(AdVVisitas adVVisitas);

    void remove(AdVVisitas adVVisitas);

    AdVVisitas find(Object id);

    List<AdVVisitas> findAll();

    List<AdVVisitas> findRange(int[] range);
    
     //Encontrar las visitas segun los filtros indicados y agregas a una lista
    List<AdVVisitas> listarVisitas(
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

    List<AdVVisitas> obtenerVisitasByTipo(String tipo,Date filtroFechaC1,Date filtroFechaC2,Login user);
            
    int count();
    
}
