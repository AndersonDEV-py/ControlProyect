/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.dao;

import finsolhn.com.model.ActividadesMoviles;
import finsolhn.com.model.Agencia;
import finsolhn.com.model.Direccion;
import finsolhn.com.model.Empleado;
import finsolhn.com.model.Login;
import finsolhn.com.model.MyFile;
import finsolhn.com.model.Requerimientos;
import finsolhn.com.model.TablaSQL;
import finsolhn.com.model.UsuariosMoviles;
import finsolhn.com.util.Constantes;
import java.sql.Connection;

//import java.sql.Date;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;



/**
 *
 * @author rcardona
 */
public class ActividadesMovilDAO3 extends DAO3 {

    //@Resource(mappedName = "jdbc/credisol_base")
    //@Resource(mappedName = "jdbc/datasource_credisol")
    //private DataSource ds;
    private MetodosSQL2 m = new MetodosSQL2();
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//("dd/MM/yyyy");
    // SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");//("yyyy-MM-dd");

    public List<ActividadesMoviles> listarActividades(String where) {
        List<ActividadesMoviles> lista = null;
        /*
        String fechaProceso = "to_Date('" + m.obtenerString("SELECT MAX(fecha) FROM saldos_diarios").substring(0, 10) + "','yyyy-mm-dd' )";
        //to_Date('25/02/2020','dd/mm/yyyy')
        // System.out.println("fecha:" + fechaProceso);
        if (fechaProceso.length() < 30) {
            //res.setError(new ErrorResponse("009", "Error al obtener fecha de proceso"));
            //return gson.toJson(res);
            return lista;
        }

        
        
        String sql = "SELECT \n"
                + "  secuencia\n"
                + ", idCliente\n"
                + ", CL.C1000 nombre --\n"
                + ", prestamo\n"
                + ", saldosJtsoid\n"
                + ", tipovisita\n"
                + ", nvl(resultadovisita,' ') resultadoVisita\n"
                + ", nvl(comentario,' ') comentario\n"
                + ", fechaRegistro\n"
                + ", NVL(TO_CHAR(fechaProvisita,'yyyy-MM-dd' ) , ' ') fechaProvisita \n"
                + ", NVL(TO_CHAR(horaprovisita,'HH:MI AM' ) , ' ') horaprovisita\n"
                + ", NVL(TO_CHAR(fechacompleto,'yyyy-MM-dd HH:MI AM' ) , ' ') fechaCompleto\n"
                + ", NVL(TO_CHAR(horainiciovisita,'HH:MI AM' ) ,' ') horaIniciovisita\n"
                + ", NVL(TO_CHAR(horafinvisita,'HH:MI AM' ) , ' ') horafinVisita\n"
                + ", usrAgenda\n"
                + ", nvl(usrvisito,' ') usrVisito\n"
                + ", latVisita\n"
                + ", lonVisita\n"
                + ", estado_crud\n"
                + ", nvl(nombreNuevo,' ') nombreNuevo\n"
                + ", nvl(telefonoNuevo,' ') telefonoNuevo\n"
                + ", nvl(direccionNuevo,' ') direccionNuevo\n"
                + ",s.moneda\n"
                + " ,s.c1601 montoDesembolso\n"
                + " ,ABS(s.c1604) capital                 \n"
                + " ,( NVL(s.c1609,0)-NVL(s.c1610,0)) interes  \n"
                + " ,DECODE(sc.c5230,'S','SEMANAL','Q','QUINCENAL','M','MENSUAL','O','OTRO','B','BIMESTRAL','D','DIARIA','T','SEMESTRAL') formaPago\n"
                + " ,s.c1644 nCuotas\n"
                + " ,(s.c1644-s.c1645) cuotasRestantes\n"
                + " ,(nvl(PP.c2309,0)+NVL(PP.c2310,0)+NVL(PP.Gastos,0) ) cuotaVigente\n"
                + " ,(nvl(PP_A.c2309,0)+NVL(PP_A.c2310,0)+NVL(PP_A.Gastos,0)+NVL(sj.mora_contabilizada ,0) ) cuotaAtrasada\n"
                + " ,sc.c5053 fechaDesembolso\n"
                + " ,s.c1627 fechaVence\n"
                + " ,s.c1628 fechaProxima\n"
                + " ,s.c1649 diasAtraso\n"
                + "-- ,s.usutopaz asesor\n"
                + " ,(SELECT nombre FROM Au_Relfuncionariousr WHERE USUARIOTOPAZ=MV.Usragenda AND tz_lock=0 AND ROWNUM=1) nomAsesor \n"
                + " ,s.sucursal idagencia\n"
                + " ,(SELECT c6020 FROM tc_sucursales WHERE c6021=s.sucursal AND tz_lock=0) nomAgencia\n"
                + " ,NVL(   TO_CHAR((SELECT max(FECHACOMPLETO) FROM WS_MOV_VISITAS WHERE PRESTAMO=s.cuenta AND len(RESULTADOVISITA)>1),'yyyy-MM-dd HH:MI AM')   ,'NO HAY REGISTRO') fechaUltimaVisita "
                + " FROM ws_mov_visitas MV \n"
                + "LEFT JOIN cl_clientes CL ON MV.Idcliente=CL.C0902 AND CL.Tz_Lock=0\n"
                + "LEFT JOIN SALDOS S ON MV.Saldosjtsoid=S.Jts_Oid AND S.tz_lock=0\n"
                + "      LEFT JOIN sl_solicitudcredito sc ON s.jts_oid=sc.saldos_jts_oid\n"
                + "      LEFT JOIN saldos_diarios sj ON sj.saldo_jts_oid=s.jts_oid AND sj.fecha=" + fechaProceso + "\n"
                + "      LEFT JOIN\n"
                + "      (\n"
                + "           SELECT lPP.saldo_jts_oid, c2300, c2302_original,c2309,c2310,NVL((SELECT SUM(SALDO_GASTO) FROM gastos_por_cuota WHERE saldos_jts_oid=lPP.Saldo_Jts_Oid AND numero_cuota=lPP.C2300 AND tz_lock=0),0) Gastos \n"
                + "           FROM bs_planpagos lPP WHERE --c2302_original>" + fechaProceso + "--to_Date('25/02/2020', 'dd/mm/yyyy')\n"
                + "            (c2309>0 OR c2310>0) AND tz_lock=0\n"
                + "           AND c2302_original< ADD_MONTHS(" + fechaProceso + ",+7)\n"
                + "      ) PP ON PP.saldo_jts_oid=s.jts_oid AND PP.c2302_original=s.c1628\n"
                + "      LEFT JOIN\n"
                + "      (\n"
                + "         SELECT lPP.saldo_jts_oid,sum(nvl(c2309,0)) c2309,sum(nvl(c2310,0)) c2310\n"
                + "         , sum(G.gasto) Gastos--,COUNT(lPP.saldo_jts_oid)\n"
                + "        FROM bs_planpagos lPP \n"
                + "         LEFT JOIN(SELECT saldos_jts_oid, numero_cuota,SUM(SALDO_GASTO) gasto FROM gastos_por_cuota WHERE saldo_gasto>0 AND tz_lock=0\n"
                + "                  GROUP BY saldos_jts_oid,numero_cuota\n"
                + "                  ) G ON G.saldos_jts_oid=lPP.Saldo_Jts_Oid AND G.numero_cuota=lPP.C2300\n"
                + "         WHERE c2302_original<=" + fechaProceso + "\n"
                + "          AND (c2309>0 OR c2310>0) AND tz_lock=0\n"
                + "         GROUP BY lPP.saldo_jts_oid\n"
                + "      ) PP_A ON PP_A.saldo_jts_oid=s.jts_oid "
                + " WHERE 1=1 " + where;
         */
        String sql = "SELECT \n"
                + "  '0' secuencia\n"
                + ",  MV.idCliente\n"
                + ", COALESCE(CL.nombre,MV.nombrenuevo ) nombre\n"
                + ", prestamo\n"
                + ", saldosJtsoid\n"
                + ", tipovisita\n"
                + ", COALESCE(resultadovisita,' ') resultadoVisita\n"
                + ", COALESCE(comentario,' ') comentario\n"
                + ", fechaRegistro\n"
                + ", COALESCE(TO_CHAR(fechaProvisita,'yyyy-MM-dd' ) , ' ') fechaProvisita \n"
                + ", COALESCE(TO_CHAR(horaprovisita,'HH:MI AM' ) , ' ') horaprovisita\n"
                + ", COALESCE(TO_CHAR(fechacompleto,'yyyy-MM-dd HH:MI AM' ) , ' ') fechaCompleto\n"
                + ", COALESCE(TO_CHAR(horainiciovisita,'HH:MI AM' ) ,' ') horaIniciovisita\n"
                + ", COALESCE(TO_CHAR(horafinvisita,'HH:MI AM' ) , ' ') horafinVisita\n"
                + ", usrAgenda\n"
                + ", COALESCE(usrvisito,' ') usrVisito\n"
                + ", latVisita\n"
                + ", lonVisita\n"
                + ", estado_crud\n"
                + ", COALESCE(nombreNuevo,' ') nombreNuevo\n"
                + ", COALESCE(telefonoNuevo,' ') telefonoNuevo\n"
                + ", COALESCE(direccionNuevo,' ') direccionNuevo\n"
                + ",s.moneda\n"
                + " ,s.montoDesembolsado montoDesembolso\n"
                + " ,ABS(s.saldoCapital) capital                 \n"
                + " ,COALESCE(s.interes,0) interes  \n"
                + " ,COALESCE(s.formaPago,' ') formaPago \n"
                + " ,s.nCuotas nCuotas\n"
                + " ,s.cuotasRes cuotasRestantes\n"
                + " ,s.valorCuotaVig cuotaVigente\n"
                + " ,s.saldoAtrasado cuotaAtrasada\n"
                + " , COALESCE(TO_CHAR(s.fechaDesembolso,'yyyy-MM-dd' ) , ' ') fechaDesembolso \n"
                + " ,COALESCE(TO_CHAR(s.fechaVence,'yyyy-MM-dd' ) , ' ') fechaVence\n"
                + " ,COALESCE(TO_CHAR(s.fechaProxima,'yyyy-MM-dd' ) , ' ') fechaProxima\n"
                + " ,s.diasAtraso\n"
                + "-- ,s.usutopaz asesor\n"
                + " ,COALESCE(s.nomAsesor ,(select nomAsesor from ad_prestamos where idasesor=MV.usragenda limit 1) ) nomAsesor \n"
                + " ,COALESCE(s.idagencia,(select idAgencia from ad_prestamos where idasesor=MV.usragenda limit 1) ) idagencia\n"
                + " ,COALESCE(s.nomAgencia, (select nomAgencia from ad_prestamos where idasesor=MV.usragenda limit 1) ) nomAgencia\n"
                + " ,COALESCE(   TO_CHAR((SELECT max(FECHACOMPLETO) FROM AD_VISITAS WHERE PRESTAMO=s.cuenta AND LENGTH(RESULTADOVISITA)>1),'yyyy-MM-dd HH:MI AM')   ,'NO HAY REGISTRO') fechaUltimaVisita  \n"
                + " , COALESCE(TO_CHAR(MV.fechacompromiso,'yyyy-MM-dd' ) , ' ') fechacompromiso"
                + " ,COALESCE(	TO_CHAR(S.fechaUltimoPago,'yyyy-MM-dd' ) , ' ') fechaUltimoPago       \n"
                + " FROM ad_visitas MV \n"
                + " LEFT JOIN ad_clientes CL ON MV.Idcliente=CL.IdCliente\n"
                + " LEFT JOIN ad_prestamos S ON MV.Saldosjtsoid=S.JtsOid\n"
                + " WHERE 1=1 " + where;

        // System.out.println(sql);
        ResultSet rs;
        try {;

            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            ActividadesMoviles obj;
            while (rs.next()) {
                obj = new ActividadesMoviles();
                obj.setSecuencia(rs.getString("secuencia"));
                obj.setIdCliente(rs.getString("idCliente"));
                obj.setNombre(rs.getString("nombre"));
                obj.setPrestamo(rs.getString("prestamo"));
                obj.setSaldosJtsOid(rs.getString("saldosJtsOid"));
                obj.setTipoVisita(rs.getString("tipoVisita"));
                obj.setResultadoVisita(rs.getString("resultadoVisita"));
                obj.setComentario(rs.getString("comentario"));
            //    obj.setFechaRegistro(rs.getString("fechaRegistro"));
            //    obj.setHoraProVisita(rs.getString("horaProVisita"));
            //    obj.setFechaProvisita(rs.getString("fechaProvisita"));
            //    obj.setFechaCompleto(rs.getString("fechaCompleto"));
            //    obj.setHoraInicioVisita(rs.getString("horaInicioVisita"));
            //    obj.setHoraFinVisita(rs.getString("horaFinVisita"));
                obj.setUsrAgenda(rs.getString("usrAgenda"));
                obj.setUsrVisito(rs.getString("usrVisito"));
                obj.setLatVisita(rs.getString("latVisita"));
                obj.setLonVisita(rs.getString("lonVisita"));
                obj.setEstado_crud(rs.getString("estado_crud"));
                obj.setNombreNuevo(rs.getString("nombreNuevo"));
                obj.setTelefonoNuevo(rs.getString("telefonoNuevo"));
                obj.setDireccioNnuevo(rs.getString("direccioNnuevo"));
                obj.setMoneda(rs.getString("moneda"));
                obj.setMontoDesembolso(rs.getDouble("montoDesembolso"));
                obj.setCapital(rs.getDouble("capital"));
                obj.setInteres(rs.getDouble("interes"));
                obj.setFormaPago(rs.getString("formaPago"));
//                obj.setnCuotas(rs.getString("nCuotas"));
//                obj.setCuotasRestantes(rs.getString("cuotasRestantes"));
                obj.setCuotaVigente(rs.getDouble("cuotaVigente"));
                obj.setCuotaAtrasada(rs.getDouble("cuotaAtrasada"));
              //  obj.setFechaDesembolso(rs.getString("fechaDesembolso"));
              //  obj.setFechaVence(rs.getString("fechaVence"));
              //  obj.setFechaProxima(rs.getString("fechaProxima"));
//                obj.setDiasAtraso(rs.getString("diasAtraso"));
                obj.setNomAsesor(rs.getString("nomAsesor"));
                obj.setIdAgencia(rs.getString("idAgencia"));
                obj.setNomAgencia(rs.getString("nomAgencia"));
              //  obj.setFechaUltimaVisita(rs.getString("fechaUltimaVisita"));
              //  obj.setFechaCompromiso(rs.getString("fechacompromiso"));
              //  obj.setFechaUltimoPago(rs.getString("fechaUltimoPago"));
                lista.add(obj);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas actividades" + e);

        } finally {
            this.Cerrar();
        }

        return lista;
    }

    public List<Direccion> listarDirecciones(String where) {
        List<Direccion> lista = null;

        ResultSet rs;
        /*
        String sql = "Select\n"
                + "      d.id codigoCliente, \n"
                + "      nvl( DECODE(d.formato,'P','Personal','C','Cliente','N','Negocio','G','Garantia') ,' ') formato,\n"
                + "      NVL(DECODE(d.tipodireccion,'R','Real','O','Conctacto','A','Laboral','C','Correspondencia') ,' ') tipoDireccion,\n"
                + "      d.pais,\n"
                + "      d.dim1 idDepartamento, dim1.c1481 departamento,      \n"
                + "      d.dim2 idMunicipio, dim2.c1207 municipio,      \n"
                + "      d.dim4 idBarrio, dim4.nomdim4 barrio,\n"
                + "      nvl(d.referencia,' ') referencia_Direccion,\n"
                + "      nvl(d.celular,'0') telefonoMovil,\n"
                + "      nvl(d.tel1,'0') telefonoFijo,\n"
                + "      nvl(d.email,' ') email,   \n"
                + "      nvl( DECODE(d.tipovivienda,'A','Alquilada','F','Familiar','P','Propia','O','Otros') ,' ') tipoVivienda --(A:Alquilada,F:Familiar,P:Propia,O:Otros)\n"
                + "      ,nvl(d.nomarrend,'N/A') nombreArrendatario\n"
                + "      ,nvl(d.tiempohab,' ') TiempoHabitar\n"
                + "      ,nvl(d.bloque,'N/T') Bloque\n"
                + "      ,NVL(d.etapa,'N/T') Etapa\n"
                + "      ,nvl(d.avenida,'N/T') AVE\n"
                + "      ,nvl(d.calle,'N/T') CALLE\n"
                + "      ,nvl(d.casa,'N/T') CASA\n"
                + "      ,nvl(d.latitud,'NT') LAT\n"
                + "      ,nvl(d.longitud,'NT') LON\n"
                + "      ,nvl( trim(d.telefono_arrendador),'0') tel_Arrendador     \n"
                + "       ,d.dim3 idSector,dim3.c1216 sector"
                + "     -- ,d.* \n"
                + "From tc_direcciones d\n"
                + "Inner Join tc_dir_dim1 dim1 On(dim1.c1480 = d.dim1 And dim1.tz_lock = 0)\n"
                + "Inner Join tc_dir_dim2 dim2 On(d.dim1 = dim2.c1205 And d.dim2 = dim2.c1206 And dim2.tz_lock = 0)\n"
                + "Inner Join tc_dir_dim3 dim3 On(d.dim1 = dim3.c1212 And d.dim2 = dim3.c1214 And d.dim3 = dim3.c1215 And   dim3.tz_lock = 0)\n"
                + "Inner Join tc_dir_dim4 dim4 On(d.dim1 = dim4.coddim1 And d.dim2 = dim4.coddim2 And d.dim3 = dim4.coddim3 And d.dim4 = dim4.coddim4 And dim4.tz_lock = 0)\n"
                + "Where \n"
                + "      d.tz_lock = 0\n"
                + "      AND d.formato='P' AND d.tipodireccion='R'"
                + " " + where;
         */
        String sql = "select \n"
                + "	 idCliente, \n"
                + "     COALESCE( formato,' ') formato,\n"
                + "	  COALESCE(tipodir,' ') tipodir,\n"
                + "	  pais,\n"
                + "	  idDepartamento, \n"
                + "	  departamento,      \n"
                + "	  idMunicipio, \n"
                + "	  municipio,      \n"
                + "	  idBarrio, \n"
                + "	  barrio,\n"
                + "	  COALESCE(referenciaDir,' ') referencia_Direccion,\n"
                + "	  COALESCE(telefonomovil,'0') telefonoMovil,\n"
                + "	  COALESCE(telefonofijo,'0') telefonoFijo,\n"
                + "	  COALESCE(email,' ') email,   \n"
                + "	  COALESCE(tipoVivienda,' ') tipoVivienda \n"
                + "	  ,COALESCE(nombreArrendatario,'N/A') nombreArrendatario\n"
                + "	  ,COALESCE(tiempoHabitar,' ') TiempoHabitar\n"
                + "	  ,COALESCE(bloque,'N/T') Bloque\n"
                + "	  ,COALESCE(etapa,'N/T') Etapa\n"
                + "	  ,COALESCE(ave,'N/T') AVE\n"
                + "	  ,COALESCE(calle,'N/T') CALLE\n"
                + "	  ,COALESCE(casa,'N/T') CASA\n"
                + "	  ,COALESCE(lat,'NT') LAT\n"
                + "	  ,COALESCE(lon,'NT') LON\n"
                + "	  ,COALESCE( trim(telArrendador),'0') tel_Arrendador     \n"
                + "	   ,idSector\n"
                + "	   ,sector\n"
                + "	 -- ,d.* \\n\"\n"
                + " From ad_direcciones \n"
                + "Where \n"
                + "   formato in('Personal','Negocio') AND tipodir='Real'"
                + " " + where;
        //System.out.println(sql);
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            Direccion obj;
            while (rs.next()) {
                obj = new Direccion();
                obj.setIdCliente(rs.getString(1));
                obj.setFormato(rs.getString(2));
                obj.setTipoDireccion(rs.getString(3));
                obj.setPais(rs.getString(4));
                obj.setIdDepartamento(rs.getString(5));
                obj.setDepartamento(rs.getString(6));
                obj.setIdMunicipio(rs.getString(7));
                obj.setMunicipio(rs.getString(8));
                obj.setIdBarrio(rs.getString(9));
                obj.setBarrio(rs.getString(10));
                obj.setReferenciaDireccion(rs.getString(11));
                obj.setTelefonoMovil(rs.getString(12));
                obj.setTelefonoFijo(rs.getString(13));
                obj.setEmail(rs.getString(14));
                obj.setTipoVivienda(rs.getString(15));
                obj.setNombreArrendatario(rs.getString(16));
                obj.setTiempoHabitar(rs.getString(17));
                obj.setBloque(rs.getString(18));
                obj.setEtapa(rs.getString(19));
                obj.setAve(rs.getString(20));
                obj.setCalle(rs.getString(21));
                obj.setCasa(rs.getString(22));
                obj.setLAT(rs.getString(23));
                obj.setLON(rs.getString(24));
                obj.setTelArrendador(rs.getString(25));
                obj.setIdSector(rs.getString(26));
                obj.setSector(rs.getString(27));

                lista.add(obj);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas direcciones" + e+" \n"+sql);

        } finally {
            this.Cerrar();
        }

        return lista;
    }


    //////////////////////////////SIN VISITA
    public List<ActividadesMoviles> listarSinVisita(String where) {
        List<ActividadesMoviles> lista = null;

        String sql = "select \n"
                + "  p.nomasesor\n"
                + ", c.nombre \n"
                + ", p.saldocapital\n"
                + ", p.diasatraso\n"
                + ", p.saldoatrasado\n"
                + ", p.cuenta\n"
                + " ,COALESCE(   TO_CHAR((SELECT max(FECHACOMPLETO) FROM AD_VISITAS WHERE PRESTAMO=p.cuenta AND LENGTH(RESULTADOVISITA)>1),'yyyy-MM-dd HH:MI AM')   ,' ') fechaultimavisita  \n"
               // + ", p.fechaultimavisita\n"
                + ", p.nomagencia\n"
                + ", p.idcliente\n"
                + "from ad_prestamos p inner join ad_clientes c on (p.IDCLIENTE=c.IDCLIENTE)\n"
                + " WHERE 1=1 " + where;

        // System.out.println(sql);
        ResultSet rs;
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            ActividadesMoviles obj;
            while (rs.next()) {
                obj = new ActividadesMoviles();
              
                obj.setNombre(rs.getString("nombre"));
                obj.setPrestamo(rs.getString("cuenta"));
                obj.setCapital(rs.getDouble("saldocapital"));
                obj.setCuotaAtrasada(rs.getDouble("saldoatrasado"));
            //    obj.setDiasAtraso(rs.getString("diasAtraso"));
                obj.setNomAsesor(rs.getString("nomAsesor"));
            //   obj.setFechaUltimaVisita(rs.getString("fechaUltimaVisita"));
                obj.setNomAgencia(rs.getString("nomAgencia"));
                obj.setIdCliente(rs.getString("idcliente"));
                lista.add(obj);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas actividades" + e);

        } finally {
            this.Cerrar();
        }

        return lista;
    }

}
