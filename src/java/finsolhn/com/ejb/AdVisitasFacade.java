package finsolhn.com.ejb;

import finsolhn.com.data.AdClientes;
import finsolhn.com.data.AdPrestamos;
import finsolhn.com.data.AdUsuarios;
import finsolhn.com.data.AdVisitas;
import finsolhn.com.model.ActividadesMoviles;
import finsolhn.com.model.Login;
import static finsolhn.com.util.Constantes.sdf_dmy;
import static finsolhn.com.util.Constantes.sdf_dmy_ham;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TemporalType;

// Autor:Ingeniero Renan Cardona

@Stateless
public class AdVisitasFacade extends AbstractFacade<AdVisitas> implements AdVisitasFacadeLocal {

    @PersistenceContext(unitName = "posgresPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdVisitasFacade() {
        super(AdVisitas.class);
    }

    public EntityManager getEM() {
        return em;
    }

    //Hacer una lista con las visitas
    public List<ActividadesMoviles> listarVisitas(
            String v,
            String filtroAgencia,
            String filtroAsesor,
            String filtroNombre,
            String filtroTipoV,
            String filtroResultadoV,
            Date filtroFechaR1,
            Date filtroFechaR2,
            Date filtroFechaC1,
            Date filtroFechaC2) {

        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        List<ActividadesMoviles> lista = new ArrayList();
        List<AdVisitas> listaVisitas = null;

        String whereAsesor = " ";

        //Filtrar por usuarios
        if (!filtroAsesor.isEmpty()) {
            whereAsesor = whereAsesor + " and u.usuariotopaz in(" + filtroAsesor + ") ";
        } else {

        }
        //Filtrar por agencias
        String consultaAgencia="";
        if(filtroAgencia.trim().equalsIgnoreCase("100, 101, 102") || filtroAgencia.equalsIgnoreCase("101") || filtroAgencia.equalsIgnoreCase("102"))
        {
            
        }else{
            consultaAgencia=" and u.agenciaid in(" + filtroAgencia + ")";
        }
        //O0btener asesores
        List<AdUsuarios> listaAsesores = null;
        String consultaInUser = "SELECT u.usuariotopaz FROM AdUsuarios u WHERE u.puestoid in(81,16,63 ,91,31) " + consultaAgencia + " " + whereAsesor;
      
        //Obtener visitas por asesor
        try {
            String consulta = "SELECT u FROM AdUsuarios u WHERE u.puestoid in(81,16,63 ,91,31) " + consultaAgencia + " " + whereAsesor;
            Query query = em.createQuery(consulta);
            listaAsesores = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error VisitasFacade obtener asesores" + e);
        }

        //Seleccionar visitas que no se han completado por asesor
        try {
            if (v.equalsIgnoreCase("f")) {
                String consulta = "SELECT a FROM AdVisitas a WHERE a.fecharegistro>=:fecharegistro and a.usragenda in( " + consultaInUser + " ) "
                        + " and a.fechacompleto is null ";

                Query query = em.createQuery(consulta);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, -2);
                query.setParameter("fecharegistro", calendar, TemporalType.DATE);
                listaVisitas = query.getResultList();

            } else {

                //Filtrar por tipo de visita
                String whereTipoV = "";
                if (!filtroTipoV.isEmpty()) {
                    whereTipoV = " and a.tipovisita = " + filtroTipoV + " ";
                }
                //Flitrar por resultado de visita
                String whereResultadoV = "";
                if (!filtroResultadoV.isEmpty()) {
                    whereResultadoV = " and a.resultadovisita = " + filtroResultadoV + " ";
                }

                //Filtrar por fecha
                String whereFechaRegistro = "";
                if (filtroFechaR1 != null) {
                    whereFechaRegistro = whereFechaRegistro + " and a.fechaprovisita>=:fechaR1 ";
                }
                if (filtroFechaR2 != null) {
                    whereFechaRegistro = whereFechaRegistro + " and a.fechaprovisita<=:fechaR2 ";
                }

                String whereFechaCompleto = "";

                if (filtroFechaC1 != null) {
                    String f1 = sdf_dmy.format(filtroFechaC1) + " 12:00 AM";
                    filtroFechaC1 = sdf_dmy_ham.parse(f1);
                    whereFechaCompleto = whereFechaCompleto + " and a.fechacompleto >=:fechaC1 ";
                }

                if (filtroFechaC2 != null) {
                    String f1 = sdf_dmy.format(filtroFechaC2) + " 11:59 PM";
                    filtroFechaC2 = sdf_dmy_ham.parse(f1);
                    whereFechaCompleto = whereFechaCompleto + " and a.fechacompleto <=:fechaC2 ";
                }

                //Filtrar por nombre de usuario
                String whereNombre = "";
                if (filtroNombre != null && filtroNombre.isEmpty() == false) {
                    whereNombre = whereNombre + " and (a.idcliente \n"
                            + "in(select b.idcliente from AdClientes b where UPPER(b.nombre) like UPPER('%"+filtroNombre+"%'))\n"
                            + "or UPPER(a.nombrenuevo) like UPPER('%"+filtroNombre+"%'))";
                }

                String consulta = "SELECT a FROM AdVisitas a WHERE a.usragenda in( " + consultaInUser + " ) " + whereTipoV
                        + whereResultadoV + whereFechaRegistro + whereFechaCompleto + whereNombre ;//System.out.println(""+consulta);

                Query query = em.createQuery(consulta).setMaxResults(15000);
                if (filtroFechaR1 != null) {
                    query.setParameter("fechaR1", filtroFechaR1, TemporalType.DATE);
                }
                if (filtroFechaR2 != null) {
                    query.setParameter("fechaR2", filtroFechaR2, TemporalType.DATE);
                }
                if (filtroFechaC1 != null) {
                    query.setParameter("fechaC1", filtroFechaC1, TemporalType.TIMESTAMP);
                }
                if (filtroFechaC2 != null) {
                    query.setParameter("fechaC2", filtroFechaC2, TemporalType.TIMESTAMP);
                }
     
                listaVisitas = query.getResultList();

            }

        } catch (Exception e) {
            System.err.println("Error VisitasFacade obtener visitas" + e);
        }

        if (listaVisitas.isEmpty()) {
            return lista;
        }

        List<String> lstidclientes = new ArrayList();
        List<String> lstjts = new ArrayList();
        ActividadesMoviles obj;
        //Asignar datos especificos provenientes de la consulta a variables determinadas
        for (int i = 0; i < listaVisitas.size(); i++) {
            obj = new ActividadesMoviles();
            AdVisitas data = listaVisitas.get(i);
            obj.setSecuencia(data.getLiteuid());
            obj.setIdCliente(data.getIdcliente().toString());
            obj.setPrestamo(data.getPrestamo().toString());
            obj.setSaldosJtsOid(data.getSaldosjtsoid().toString());
            obj.setTipoVisita(data.getTipovisita());
            obj.setResultadoVisita(data.getResultadovisita());
            obj.setComentario(data.getComentario());
            obj.setFechaRegistro(data.getFecharegistro());
            obj.setHoraProVisita(data.getHoraprovisita());
            obj.setFechaProvisita(data.getFechaprovisita());
            obj.setFechaCompleto(data.getFechacompleto());
            obj.setHoraInicioVisita(data.getHorainiciovisita());
            obj.setHoraFinVisita(data.getHorafinvisita());
            obj.setUsrAgenda(data.getUsragenda());
            obj.setUsrVisito(data.getUsrvisito());
            obj.setLatVisita(data.getLatvisita());
            obj.setLonVisita(data.getLonvisita());
            obj.setEstado_crud(data.getEstadoCrud());
            obj.setNombreNuevo(data.getNombrenuevo().toUpperCase());
            obj.setTelefonoNuevo(data.getTelefononuevo());
            obj.setDireccioNnuevo(data.getDireccionnuevo().toUpperCase());
            obj.setFechaCompromiso(data.getFechacompromiso());
            lista.add(obj);

            lstidclientes.add(obj.getIdCliente());
            lstjts.add(obj.getSaldosJtsOid());

        }

        //Obtener clientes
        List<AdClientes> listaClientes = null;
        try {
            String consulta = "SELECT a FROM AdClientes a WHERE a.idcliente in :list1";
            Query query = em.createQuery(consulta);
            query.setParameter("list1", lstidclientes);
            listaClientes = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error VisitasFacade obtener clientes" + e);
        }

        //Obtener prestamos
        List<AdPrestamos> listaPrestamos = null;
        try {
            String consulta = "SELECT a FROM AdPrestamos a WHERE a.jtsoid in :list1";
            Query query = em.createQuery(consulta);
            query.setParameter("list1", lstjts);
            listaPrestamos = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error VisitasFacade obtener prestamos" + e);
        }

        for (int i = 0; i < lista.size(); i++) {
            String idcliente = listaVisitas.get(i).getIdcliente().toString();
            String jts = listaVisitas.get(i).getSaldosjtsoid().toString();
            String usuarioAgenda = listaVisitas.get(i).getUsragenda().toString();
          
            //Datos cliente
            Optional<AdClientes> cliente = listaClientes.stream().filter(
                    p -> p.getIdcliente().toString().equalsIgnoreCase(idcliente)).findFirst();
            if (cliente.isPresent()) {
                lista.get(i).setNombre(cliente.get().getNombre());
            }
            //usuarios //empleado id
            Optional<AdUsuarios> asesorIDs = listaAsesores.stream().filter(
                        p -> p.getUsuariotopaz().equalsIgnoreCase(usuarioAgenda)).findFirst();
                if (asesorIDs.isPresent()) {
                    lista.get(i).setEmpleadoId(asesorIDs.get().getEmpleadoid()+"");
                }
            
            //Datos prestamo
            Optional<AdPrestamos> prestamo = listaPrestamos.stream().filter(
                    p -> p.getJtsoid().toString().equalsIgnoreCase(jts)).findFirst();
            if (prestamo.isPresent()) {
                lista.get(i).setMoneda(prestamo.get().getMoneda().toString());
                lista.get(i).setMontoDesembolso(prestamo.get().getMontodesembolsado().doubleValue());
                lista.get(i).setCapital(prestamo.get().getSaldocapital().doubleValue());
                lista.get(i).setInteres(prestamo.get().getInteres().doubleValue());
                lista.get(i).setFormaPago(prestamo.get().getFormapago());
                lista.get(i).setnCuotas(prestamo.get().getNcuotas());
                lista.get(i).setCuotasRestantes(prestamo.get().getCuotasres());
                lista.get(i).setCuotaVigente(prestamo.get().getValorcuotavig().doubleValue());
                lista.get(i).setCuotaAtrasada(prestamo.get().getSaldoatrasado().doubleValue());
                lista.get(i).setFechaDesembolso(prestamo.get().getFechadesembolso());
                lista.get(i).setFechaVence(prestamo.get().getFechavence());
                lista.get(i).setFechaProxima(prestamo.get().getFechaproxima());
                lista.get(i).setDiasAtraso(prestamo.get().getDiasatraso());
                lista.get(i).setIdAgencia(prestamo.get().getIdagencia().toString());
                lista.get(i).setNomAgencia(prestamo.get().getNomagencia());
                lista.get(i).setFechaUltimaVisita(prestamo.get().getFechaultimavisita());
                lista.get(i).setFechaUltimoPago(prestamo.get().getFechaultimopago());

            }
            if (lista.get(i).getNomAsesor() == null) {

                Optional<AdUsuarios> asesor = listaAsesores.stream().filter(
                        p -> p.getUsuariotopaz().equalsIgnoreCase(usuarioAgenda)).findFirst();
                if (asesor.isPresent()) {
                    lista.get(i).setNomAsesor(asesor.get().getNombre());
                    lista.get(i).setIdClienteAsesor(asesor.get().getIdcliente());
                    lista.get(i).setCuentaAsesor(asesor.get().getCuenta());
                    
                    
                    if (lista.get(i).getNomAgencia() == null) {
                        lista.get(i).setNomAgencia(asesor.get().getAgencia());
                    }
                    if (lista.get(i).getNombre() == null) {
                        lista.get(i).setNombre(lista.get(i).getNombreNuevo());
                    }

                }

            }

        }

        return lista;
    }

    
   //Obtener lista de visitas que se han completado y cuyo saldojts esta en una lista
   public List<AdVisitas> obtenerVisitasAntByJtsIn(List<String> list1){
       
       
        List<AdVisitas> lista = null;
        try {
            String consulta = "SELECT a FROM AdVisitas a WHERE a.fechacompleto is not null and  a.saldosjtsoid in :list1"
                    + " order by a.fechacompleto desc";
            Query query = em.createQuery(consulta);
            query.setParameter("list1", list1);
            lista = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error VisitasFacade obtener visitas anteriores" + e);
        }
       
        return lista;
   } 
   
    //Obtener visitas segun el tipo
      public List<AdVisitas> obtenerVisitasByTipo(String tipo,Date filtroFechaC1,Date filtroFechaC2,Login user){
       
       
        List<AdVisitas> lista = null;
        
        String whereFechaCompleto = "";

        try{
            if (filtroFechaC1 != null) {
                String f1 = sdf_dmy.format(filtroFechaC1) + " 12:00 AM";
                filtroFechaC1 = sdf_dmy_ham.parse(f1);
                whereFechaCompleto = whereFechaCompleto + " and a.fechacompleto >=:fechaC1 ";
            }

            if (filtroFechaC2 != null) {
                String f1 = sdf_dmy.format(filtroFechaC2) + " 11:59 PM";
                filtroFechaC2 = sdf_dmy_ham.parse(f1);
                whereFechaCompleto = whereFechaCompleto + " and a.fechacompleto <=:fechaC2 ";
            }
        }catch(Exception e){
            System.out.println("Error VisitasFacade obtenerVisitasByTipo fechas"+e);
        }
        String tipoV="";
        if(!tipo.isEmpty())
        {
            tipoV=" and a.tipovisita='"+tipo+"'";
        }
        try {
            String consulta="";
            if(!user.getTipousuario().equalsIgnoreCase("2") && !user.getTipousuario().equalsIgnoreCase("5"))
                consulta = "SELECT a FROM AdVisitas a WHERE 1=1 "+tipoV+" "+whereFechaCompleto+" and a.usragenda not in(SELECT u.usuariotopaz FROM AdUsuarios u WHERE u.puestoid in(91,31) ) ";
            else
                consulta = "SELECT a FROM AdVisitas a WHERE 1=1 "+tipoV+" "+whereFechaCompleto+" and a.usragenda in(SELECT u.usuariotopaz FROM AdUsuarios u WHERE u.puestoid in(91,31) ) ";
            
            
            Query query = em.createQuery(consulta);
             if (filtroFechaC1 != null) {
                    query.setParameter("fechaC1", filtroFechaC1, TemporalType.TIMESTAMP);
                }
                if (filtroFechaC2 != null) {
                    query.setParameter("fechaC2", filtroFechaC2, TemporalType.TIMESTAMP);
                }
            lista = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error VisitasFacade obtener visitas por tipo" + e);
        }
       
        return lista;
   } 
    
    
}
