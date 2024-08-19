/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.dao;

import finsolhn.com.data.AdUsuarios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;


/**
 *
 * @author rcardona
 */
public class UsuariosMovilDAO3 extends DAO3 {

    //@Resource(mappedName = "jdbc/credisol_base")
    //@Resource(mappedName = "jdbc/datasource_credisol")
    //private DataSource ds;
    private MetodosSQL2 m = new MetodosSQL2();
    private MetodosSQL3 m3 = new MetodosSQL3();
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//("dd/MM/yyyy");
    // SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");//("yyyy-MM-dd");

    /*
    public List<AdUsuarios> listarUsuarios(String where) {
        List<AdUsuarios> lista = null;

        ResultSet rs;
     
        String sql = "select\n"
                + "     empleadoid\n"
                + "	 ,usuariotopaz\n"
                + "	 ,nombre\n"
                + "	 ,puestoid\n"
                + "	 ,COALESCE(puesto,'N/A') puesto\n"
                + "	 ,correo\n"
                + "	 ,COALESCE(intentos,0)\n"
                + "	 ,agenciaid\n"
                + "	 ,agencia\n"
                + "	 ,estado\n"
                + "	 ,ultimasyn_manual\n"
                + "	 ,ultimasyn_automatica\n"
                + "	 ,ultimalat\n"
                + "	 ,ultimalon\n"
                + "	 ,imei\n"
                + "	 ,ultimocambio\n"
                + "	 ,contrasenia \n"
                + "	FROM ad_usuarios \n"
                + "WHERE 1=1 " + where+" order by nombre";

        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            AdUsuarios obj;
            while (rs.next()) {
                obj = new AdUsuarios();
                obj.setEmpleadoid(rs.getInt(1));
                obj.setUsuariotopaz(rs.getString(2));
                obj.setNombre(rs.getString(3));
                obj.setPuestoid(rs.getInt(4));
                obj.setPuesto(rs.getString(5));
                obj.setCorreo(rs.getString(6));
                obj.setIntentos(rs.getInt(7));
                obj.setAgenciaid(rs.getInt(8));
                obj.setAgencia(rs.getString(9));
                obj.setEstado(rs.getString(10));
                obj.setUltimasynManual(rs.getDate(11));
                obj.setUltimasynAutomatica(rs.getDate(12));
                obj.setUltimalat(rs.getString(13));
                obj.setUltimalon(rs.getString(14));
                obj.setImei(rs.getString(15));
                obj.setUltimocambio(rs.getDate(16));
                obj.setContrasenia(rs.getString(17));

                lista.add(obj);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas usuarios" + e);

        } finally {
            this.Cerrar();
        }

        return lista;
    }
   
    
    public UsuariosMoviles traeUsuario(String where) {
        

        ResultSet rs;
      
        String sql = "select\n"
                + "     empleadoid\n"
                + "	 ,usuariotopaz\n"
                + "	 ,nombre\n"
                + "	 ,puestoid\n"
                + "	 ,COALESCE(puesto,'N/A') puesto\n"
                + "	 ,correo\n"
                + "	 ,COALESCE(intentos,0)\n"
                + "	 ,agenciaid\n"
                + "	 ,agencia\n"
                + "	 ,estado\n"
                + "	 ,ultimasyn_manual\n"
                + "	 ,ultimasyn_automatica\n"
                + "	 ,ultimalat\n"
                + "	 ,ultimalon\n"
                + "	 ,imei\n"
                + "	 ,ultimocambio\n"
                + "	 ,contrasenia \n"
                + "	FROM ad_usuarios \n"
                + "WHERE 1=1 " + where;

        UsuariosMoviles obj=new UsuariosMoviles();
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            
            while (rs.next()) {
                
                obj.setEmpleadoId(rs.getString(1));
                obj.setUsuarioTopaz(rs.getString(2));
                obj.setNombre(rs.getString(3));
                obj.setPuestoId(rs.getString(4));
                obj.setPuesto(rs.getString(5));
                obj.setCorreo(rs.getString(6));
                obj.setIntentos(rs.getString(7));
                obj.setAgenciaId(rs.getString(8));
                obj.setAgencia(rs.getString(9));
                obj.setEstado(rs.getString(10));
                obj.setUltimaSyn(rs.getString(11));
                obj.setUltimaSynAuto(rs.getString(12));
                obj.setUltimaLat(rs.getString(13));
                obj.setUltimaLon(rs.getString(14));
                obj.setImei(rs.getString(15));
                obj.setUltimoCambio(rs.getString(16));
                obj.setContrasenia(rs.getString(17));


            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error obtener usuarios" + e);

        } finally {
            this.Cerrar();
        }

        return obj;
    }
    */
    public List<AdUsuarios> listarUsuariosNoRegistrados(String where) {
        List<AdUsuarios> lista = null;

        
        String sqlData[]=m3.traeArrayX("select empleadoid from ad_usuarios");
        String codigosRegistrados="";
        for (String string : sqlData) {
            codigosRegistrados=codigosRegistrados+string+",";
        }
        if(sqlData.length>0)
            codigosRegistrados=codigosRegistrados.substring(0,codigosRegistrados.length()-1);
        
        ResultSet rs;

        String sql = "SELECT \n"
                + "       Rf.codfuncionario empleado_id, \n"
                + "       rf.usuariotopaz ,\n"
                + "       RF.Nombre,\n"
                + "       RHC.CODCARGO cargo_id, \n"
                + "       RHC.Descripcion cargo,\n"
                + "       RHE.Codigo_Agencia agencia_id,\n"
                + "       tcs.c6020 agencia\n"
                + " FROM AU_RELFUNCIONARIOUSR Rf\n"
                + "INNER JOIN RH_CARGOS RHC ON Rf.codcargo=RHC.Codcargo\n"
                + "INNER JOIN RH_V_EMPLEADOS_ACTIVOS RHE ON Rf.Codfuncionario=RHE.Codigo_Empleado\n"
                + "INNER JOIN Tc_Sucursales tcs ON RHE.codigo_agencia=tcs.c5834\n"
                + (sqlData.length>0 || where.length()>0 ?"WHERE \n": "")
                // "RHE.estado='A' \n"
                //   + "AND RHC.CODCARGO in(81,16)\n"
                + (sqlData.length>0?"Rf.codfuncionario NOT IN("+codigosRegistrados+")\n":" ")
                + "" + where
                + " ORDER BY RHE.codigo_agencia,RHC.Codcargo";
        
        try {
            m.Conectar();
            PreparedStatement st = m.getCon().prepareCall(sql);
            System.out.println(sql);
            rs = st.executeQuery();

            lista = new ArrayList();
            AdUsuarios obj;
            while (rs.next()) {
                obj = new AdUsuarios();
                //obj.setAdUsuariosPK(new AdUsuariosPK(rs.getInt(1), rs.getString(2)));
                obj.setEmpleadoid(rs.getInt(1));
                obj.setUsuariotopaz(rs.getString(2));
                obj.setNombre(rs.getString(3));
                obj.setPuestoid(rs.getInt(4));
                obj.setPuesto(rs.getString(5));
                obj.setAgenciaid(rs.getShort(6));
                obj.setAgencia(rs.getString(7));

                lista.add(obj);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas usuarios" + e+"\n"+sql);

        } finally {
            m.Cerrar();
        }

        return lista;
    }
/*
    public void insertar(UsuariosMoviles obj) {

        String existe = "select count(*) from ad_usuarios where empleadoid='" + obj.getEmpleadoId() + "'";
        existe = m3.obtenerString(existe);
        if (existe.trim().equalsIgnoreCase("0")) {

            String sql = "INSERT INTO ad_usuarios(\n"
                    + "           empleadoid\n"
                    + "         , usuariotopaz\n"
                    + "         , nombre\n"
                    + "         , puestoid\n"
                    + "         , puesto\n"
                    + "         , correo\n"
                    + "         , intentos\n"
                    + "         , agenciaid\n"
                    + "         , agencia\n"
                    + "         , estado\n"
                    + "         , imei\n"
                    + "         , ultimocambio\n"
                    + "         , contrasenia\n"
                    + "         )\n"
                    + "VALUES\n"
                    + "  (\n"
                    + "       '" + obj.getEmpleadoId() + "'\n"
                    + "     , '" + obj.getUsuarioTopaz() + "'\n"
                    + "     , '" + obj.getNombre() + "'\n"
                    + "     , '" + obj.getPuestoId() + "'\n"
                    + "     , '" + obj.getPuesto() + "'\n"
                    + "     , '" + obj.getCorreo() + "'\n"
                    + "     , '" + obj.getIntentos() + "'\n"
                    + "     , '" + obj.getAgenciaId() + "'\n"
                    + "     , '" + obj.getAgencia() + "'\n"
                    + "     , '" + obj.getEstado() + "'\n"
                    + "     , '" + obj.getImei() + "'\n"
                    + "     , to_timestamp( '1900-01-01' , 'yyyy-MM-dd')\n"
                    + "     ,  md5('" + obj.getContrasenia() + "') \n"
                    + "     )";
            try {
                this.Conectar();
                PreparedStatement st = this.getCon().prepareStatement(sql);

                ///  st.setString(1, cat.getDETALLE());
                st.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wdlUsuario').hide();");

                st.close();
             

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + e));
                System.err.println("Error al insertar " + e + "\n" + sql);

            } finally {
                this.Cerrar();

            }

        } else {
            System.err.println("ya existe ");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Esta usuario ya esta Guardado"));
        }

    }

    public void modificar(UsuariosMoviles obj) {

        try {
            String sql = "UPDATE ad_usuarios\n"
                    + "   SET \n"
                    + "       correo = '" + obj.getCorreo() + "',\n"
                    + "       intentos = '" + obj.getIntentos() + "',\n"
                    //+ "       estado = '" + obj.getEstado() + "',\n"
                    + "       imei = '" + obj.getImei() + "', \n"
                    + "       estado_crud = 'MP' \n"
                    + "  " + (obj.getContrasenia().isEmpty() ? " ,estado = '" + obj.getEstado() + "' " : " , estado ='2', contrasenia = md5 ('" + obj.getContrasenia() + "') ")
                    + " WHERE empleadoid = '" + obj.getEmpleadoId() + "'";
            //System.out.println(sql);
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);

            st.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wdlUsuario').hide();");

            st.close();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
            //System.err.println("Error al modificar " + e);

        } finally {
            this.Cerrar();
        }

    }*/

}
