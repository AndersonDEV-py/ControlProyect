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
public class ActividadesMovilDAO2 extends DAO2 {

    //@Resource(mappedName = "jdbc/credisol_base")
    //@Resource(mappedName = "jdbc/datasource_credisol")
    //private DataSource ds;
    private MetodosSQL2 m = new MetodosSQL2();
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//("dd/MM/yyyy");
    // SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");//("yyyy-MM-dd");


    public List<Agencia> listarAgencias(String where, Login user) {
        List<Agencia> lista = new ArrayList();
        Agencia obj;

        if (user.getC_puesto().equalsIgnoreCase("60")) {
            obj = new Agencia();
            obj.setAgenciaId("0");
            obj.setAgencia("TODO EL DISTRITO");
            lista.add(obj);

            where = where + " AND CLAVE='" + user.getUsuario() + "' ";
        } else {
            where = where + " AND c6021='" + user.getC_agencia() + "' ";
        }

        ResultSet rs;

        String sql = "SELECT  c6021 agenciaId\n"
                + "        ,c6020 agencia\n"
                + "        ,clave distrito\n"
                + "FROM tc_sucursales sc INNER JOIN tc_agencias_x_distrito ad ON sc.c6021=ad.SUCURSAL \n"
                + "WHERE sc.tz_lock=0 " + where;

        try {
            m.Conectar();
            PreparedStatement st = m.getCon().prepareCall(sql);

            rs = st.executeQuery();

            while (rs.next()) {
                obj = new Agencia();
                obj.setAgenciaId(rs.getString(1));
                obj.setAgencia(rs.getString(2).replaceAll("AGENCIA", ""));
                obj.setDistritoId(rs.getString(3));

                lista.add(obj);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas asesores" + e+" \n"+sql);

        } finally {
            m.Cerrar();
        }

        return lista;
    }

    public List<Empleado> listarAsesores(String where, Login user) {
        List<Empleado> lista = new ArrayList();

        Empleado obj;

        if (user.getC_puesto().equalsIgnoreCase("81") || user.getC_puesto().equalsIgnoreCase("16")) {
            where = where + " and Rf.codfuncionario='" + user.getSiscodigo() + "' ";
        } else {

            obj = new Empleado();
            obj.setId_empleado("0");
            obj.setUsuario("0");
            obj.setNombre("TODOS");
            lista.add(obj);

        }

        ResultSet rs;

        String sql = "SELECT \n"
                + "       Rf.codfuncionario, \n"
                + "       RF.Nombre,\n"
                + "       rf.usuariotopaz ,\n"
                + "       RHC.CODCARGO, \n"
                + "       RHC.Descripcion,\n"
                + "       RHE.Codigo_Agencia,\n"
                + "       tcs.c6020\n"
                + " FROM AU_RELFUNCIONARIOUSR Rf\n"
                + "INNER JOIN RH_CARGOS RHC ON Rf.codcargo=RHC.Codcargo\n"
                + "INNER JOIN RH_EMPLEADOS RHE ON Rf.Codfuncionario=RHE.Codigo_Empleado\n"
                + "INNER JOIN Tc_Sucursales tcs ON RHE.codigo_agencia=tcs.c5834\n"
                + "WHERE RHE.estado='A' AND RHC.CODCARGO in(81,16)\n"
                //+ "AND codigo_agencia='" + codAgencia + "'\n"
                + " " + where
                + " ORDER BY RHE.codigo_agencia,RHC.Codcargo";
        //System.out.println(sql);
        try {
            m.Conectar();
            PreparedStatement st = m.getCon().prepareCall(sql);
            
            rs = st.executeQuery();

            while (rs.next()) {
                obj = new Empleado();
                obj.setId_empleado(rs.getString(1));
                obj.setNombre(rs.getString(2));
                obj.setUsuario(rs.getString(3));
                obj.setCodCargo(rs.getString(4));
                obj.setDesCargo(rs.getString(5));
                obj.setC_agencia(rs.getString(6));
                obj.setD_agencia(rs.getString(7));

                lista.add(obj);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas asesores" + e+ " "+sql);

        } finally {
            m.Cerrar();
        }

        return lista;
    }

  
}
