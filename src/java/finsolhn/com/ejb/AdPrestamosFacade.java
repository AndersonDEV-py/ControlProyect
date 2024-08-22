/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.ejb;

import finsolhn.com.data.AdClientes;
import finsolhn.com.data.AdPrestamos;
import finsolhn.com.data.AdUsuarios;
import finsolhn.com.data.AdVisitas;
import finsolhn.com.model.ActividadesMoviles;
import static finsolhn.com.util.Constantes.sdf_dmy;
import static finsolhn.com.util.Constantes.sdf_dmy_ham;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TemporalType;

/**
 *
 * @author ds010106
 */
@Stateless
public class AdPrestamosFacade extends AbstractFacade<AdPrestamos> implements AdPrestamosFacadeLocal {

    @PersistenceContext(unitName = "posgresPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdPrestamosFacade() {
        super(AdPrestamos.class);
    }

    public List<ActividadesMoviles> listarClienteSinVisitas(
            String filtroAgencia,
            String filtroAsesor,
            String filtroNombre,
            Date filtroFechaUV) {
        List<ActividadesMoviles> lista = new ArrayList();
        List<AdPrestamos> listaPres = null;

        String whereAsesor = " ";

        if (!filtroAsesor.isEmpty()) {
            whereAsesor = whereAsesor + " and p.idasesor in(" + filtroAsesor + ") ";
            //whereAsesor2=whereAsesor2+" and u.usuariotopaz in("+filtroAsesor+") ";
        } else {

        }
        String whereAgencia = " ";

        if (filtroAgencia != null) {
            if (!filtroAgencia.isEmpty()) {
                whereAgencia = whereAgencia + " and p.idagencia in(" + filtroAgencia + ") ";
                //whereAsesor2=whereAsesor2+" and u.usuariotopaz in("+filtroAsesor+") ";
            } else {

            }
        }
        boolean GESTOR=false;
        if(filtroAgencia.trim().equalsIgnoreCase("100, 101, 102") || filtroAgencia.equalsIgnoreCase("101") || filtroAgencia.equalsIgnoreCase("102"))
        {
             GESTOR=true;   
            if(!filtroAsesor.isEmpty())
            {
                whereAgencia=" ";
                whereAsesor = " and p.idgestor in( SELECT u.codigogestorcobro FROM AdUsuarios u WHERE u.usuariotopaz in(" + filtroAsesor + ") ) ";
   
            }else{
               String zona  = (filtroAgencia.equalsIgnoreCase("101")?"'NORTE'":(filtroAgencia.equalsIgnoreCase("102")?"'CENTRO-SUR'":"'NORTE','CENTRO-SUR'"));
                whereAgencia=" and p.idagencia in(select b.agenciaid from AdAgencias b where b.zona in("+zona+") ) and p.idgestor<>'0' and p.idgestor!=null ";
            }
            if(filtroFechaUV==null)
            {
                Calendar c = Calendar.getInstance();
                Date now = c.getTime();
                c.add(Calendar.DATE, -720);
                Date nowMinus2Year = c.getTime();
                filtroFechaUV=nowMinus2Year;
            }
            
        }
        
        
        String whereNombre = "";
        if (filtroNombre != null && filtroNombre.isEmpty() == false) {
            whereNombre = whereNombre + " and P.idcliente \n"
                    + "in(select b.idcliente from AdClientes b where UPPER(b.nombre) like UPPER('%" + filtroNombre + "%')) ";

        }
        //obtener asesores
        //List<AdUsuarios> listaAsesores = null;
        // String consultaInUser = "";
        // if(!whereAsesor.isEmpty())
        //  {//   consultaInUser = " And p.idasesor in(SELECT u.usuariotopaz FROM AdUsuarios u WHERE u.puestoid in(81,16,63) and u.agenciaid in("+filtroAgencia+") "+whereAsesor  +" )";

        // }
        /*try {
            String consulta = "SELECT u FROM AdUsuarios u WHERE u.puestoid in(81,16,63) and u.agenciaid in("+filtroAgencia+") "+whereAsesor;
            System.out.println(""+consulta);
            Query query = em.createQuery(consulta);
            listaAsesores = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error PrestamosFacade obtener asesores" + e);
        }*/
        String whereFechaUV = "";

        try {
            if (filtroFechaUV != null) {
                String f1 = sdf_dmy.format(filtroFechaUV) + " 12:00 AM";
                filtroFechaUV = sdf_dmy_ham.parse(f1);
                whereFechaUV = whereFechaUV + "select a.prestamo from AdVisitas a where a.fechacompleto >=:fechaUV ";
            } else {
                //String f1 = "19/03/2021 12:00 AM";
                //filtroFechaUV = sdf_dmy_ham.parse(f1);
                whereFechaUV = whereFechaUV + "select a.prestamo from AdVisitas a where a.fechacompleto is not null";
            }
            

            String consulta = "select p from AdPrestamos p "
                    + " where 1=1 " + whereAsesor + whereAgencia + whereNombre + " and  p.cuenta not in(" + whereFechaUV + ") ";
            System.out.println("" + consulta);
            Query query = em.createQuery(consulta);
            if (filtroFechaUV != null) {
                query.setParameter("fechaUV", filtroFechaUV, TemporalType.TIMESTAMP);
            }
            try {
                listaPres = query.getResultList();
            } catch (Exception e) {
                System.out.println("2.Error PrestamosFacade obtener prestamos" + e);
            }

        } catch (Exception e) {
            System.err.println("Error PrestamosFacade obtener prestamos" + e);
        }

        if (listaPres.isEmpty()) {
            return lista;
        }
        //System.out.println("1.prestamos t " + listaPres.size());
        //StringBuilder codClientes = new StringBuilder();
        //StringBuilder jtsoid = new StringBuilder();
        List<String> lstidclientes = new ArrayList();
        List<String> lstjts = new ArrayList();
        ActividadesMoviles obj;
        for (int i = 0; i < listaPres.size(); i++) {
            obj = new ActividadesMoviles();
            AdPrestamos data = listaPres.get(i);
            obj.setSecuencia("0");

            obj.setPrestamo(data.getCuenta().toString());
            obj.setSaldosJtsOid(data.getJtsoid().toString());
            obj.setTipoVisita("NA");
            obj.setCapital(data.getSaldocapital().doubleValue());
            obj.setCuotaAtrasada(data.getSaldoatrasado().doubleValue());
            obj.setDiasAtraso(data.getDiasatraso());
            obj.setNomAsesor(data.getNomasesor());
            obj.setNomAgencia(data.getNomagencia());
            obj.setIdCliente(data.getIdcliente().toString());
            //obj.setNombre(rs.getString("nombre"));
            obj.setFechaUltimaVisita(data.getFechaultimavisita()); //("fechaUltimaVisita"));
            obj.setIdgestor(data.getIdgestor());
            lista.add(obj);

            //codClientes.append(obj.getIdCliente() + ",");
            //jtsoid.append(obj.getSaldosJtsOid() + ",");
            lstidclientes.add(obj.getIdCliente());
            lstjts.add(obj.getSaldosJtsOid());
        }
        listaPres.clear();
        /*System.out.println("2.sin visitas t " + lista.size());
        if (codClientes.length() > 1) {
            codClientes.deleteCharAt(codClientes.length() - 1);
            jtsoid.deleteCharAt(jtsoid.length() - 1);
        }
         */

        //obtener clientes
        List<AdClientes> listaClientes = null;
        try {
            String consulta = "SELECT a FROM AdClientes a WHERE a.idcliente in :list1  ";
            //System.out.println("2.2.create query clientes  ");
            Query query = em.createQuery(consulta);
            query.setParameter("list1", lstidclientes);
            listaClientes = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error ClientesFacade obtener clientes" + e);
        }
        //obtener asesores/gestores
        List<AdUsuarios> listaAsesores = null;
        if(GESTOR)
        {
             try {
                //String consulta = "SELECT u FROM AdUsuarios u WHERE u.puestoid in(81,16,63 ,91,31) and u.agenciaid in(" + filtroAgencia + ") " + whereAsesor;System.out.println(""+consulta);
                String consulta = "SELECT u FROM AdUsuarios u WHERE u.puestoid in(91,31) and u.estado<>'0' and u.codigogestorcobro notnull";//System.out.println(""+consulta);
                Query query = em.createQuery(consulta);
                listaAsesores = query.getResultList();
            } catch (Exception e) {
                System.err.println("Error PrestamosFacade obtener asesores" + e);
            }
        }
        
        
        //obtener visitas anteriores
        /*List<AdVisitas> listaVisitas = null;
        try {
            String consulta = "SELECT a FROM AdVisitas a WHERE a.fechacompleto is not null and  a.saldosjtsoid in :list1"
                    + " order by a.fechacompleto desc";
            Query query = em.createQuery(consulta);
            query.setParameter("list1", lstjts);
            listaVisitas = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error VisitasFacade obtener visitas anteriores" + e);
        }*/
       

        for (int i = 0; i < lista.size(); i++) {
            String idcliente = lista.get(i).getIdCliente();
            String jts = lista.get(i).getSaldosJtsOid();
            String gestor =  (GESTOR?lista.get(i).getIdgestor():"000");
            //String usuarioAgenda = listaPres.get(i).getUsragenda().toString();
            if (listaClientes == null) {
                continue;
            }
            Optional<AdClientes> cliente = listaClientes.stream().filter(
                    p -> p.getIdcliente().toString().equalsIgnoreCase(idcliente)).findFirst();
            if (cliente.isPresent()) {
                lista.get(i).setNombre(cliente.get().getNombre());
            }
            if(GESTOR)
            {
                if (listaAsesores == null) {
                    continue;
                }
                Optional<AdUsuarios> asesor = listaAsesores.stream().filter(
                    p -> p.getCodigogestorcobro().equalsIgnoreCase(gestor)).findFirst();
                System.out.print(gestor);
                if (asesor.isPresent()) {
                    System.out.println("-"+asesor.get().getNombre());
                    lista.get(i).setNomAsesor(asesor.get().getNombre());
                    lista.get(i).setUsrAgenda(asesor.get().getUsuariotopaz());
                }
            }
            
           /* try {
                if (listaVisitas == null) {
                    continue;
                }
                Optional<AdVisitas> visitaAnt = listaVisitas.stream().filter(
                        p -> Long.toString(p.getSaldosjtsoid()).equalsIgnoreCase(jts)).findFirst();

                if (visitaAnt.isPresent()) {
                    lista.get(i).setFechaUltimaVisita(visitaAnt.get().getFechacompleto());
                    System.out.println("Asigno "+visitaAnt.get().getFechacompleto());
                }
            } catch (Exception e) {
                System.err.println("EX:" + e);
            }*/

        }
        
        return lista;
    }

}
