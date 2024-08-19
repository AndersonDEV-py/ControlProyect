/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.dao;

import finsolhn.com.model.Aceptacion;
import finsolhn.com.model.Archivo;
import finsolhn.com.model.Daily;
import finsolhn.com.model.MyFile;
import finsolhn.com.model.Requerimientos;
import finsolhn.com.model.TablaSQL;
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
import org.primefaces.PrimeFaces;



/**
 *
 * @author rcardona
 */
public class AdmDesarrolloDAO extends DAO {

    //@Resource(mappedName = "jdbc/credisol_base")
    //@Resource(mappedName = "jdbc/datasource_credisol")
    //private DataSource ds;
    public MetodosSQL m = new MetodosSQL();
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//("dd/MM/yyyy");
    // SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");//("yyyy-MM-dd");

    public String obtenerUltimaSec(Requerimientos obj) {

        String valor = "";
        try {
            this.Conectar();
            ResultSet rs;
            String sql = "SELECT MAX([id]) VALOR FROM [SGR].[dbo].[GP_REQUERIMIENTO] where c_usuario_sol = '" + obj.getC_usuario() + "' ";
            // System.out.println(sql);
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            while (rs.next()) {
                valor = rs.getString("VALOR");
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            System.err.println("error consulta ultimo:" + e);
        } finally {
            this.Cerrar();
        }

        return valor;
    }

    public List<Requerimientos> listarMisRequerimientos(String user) {
        List<Requerimientos> lista = null;

        ResultSet rs;

        String sql = "SELECT [id]\n"
                + "		\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_creado],103)+' '+CONVERT(varchar,[fecha_creado],108) ,'1900-01-01 00:00:00') [fecha_creado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_autorizado],103)+' '+CONVERT(varchar,[fecha_autorizado],108),'1900-01-01 00:00:00') [fecha_autorizado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_asigno_desarrollo],103)+' '+CONVERT(varchar,[fecha_asigno_desarrollo],108),'1900-01-01 00:00:00') [fecha_asigno_desarrollo]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_completado],103)+' '+CONVERT(varchar,[fecha_completado],108),'1900-01-01 00:00:00') [fecha_completado]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_aceptado],103)+' '+CONVERT(varchar,[fecha_aceptado],108),'1900-01-01 00:00:00') [fecha_aceptado]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_finalizado],103)+' '+CONVERT(varchar,[fecha_finalizado],108),'1900-01-01 00:00:00') [fecha_finalizado]\n"
                + "      ,[circular]\n"
                + "      ,[prioridad]\n"
                + "      ,[nuevo]\n"
                + "      ,[c_usuario_sol]\n"
                + "	  ,E.Nombre as d_usuario\n"
                + "      ,[c_agencia]\n"
                + "      ,[descripcion]\n"
                + "      ,R.[nombre]\n"
                + "      ,[c_desarrollador]\n"
                + "	  ,E2.Nombre as d_desarrollador\n"
                + "	  ,R.estado\n"
                + "	  ,R.tipo_registro\n"
                + "	  ,R.c_area\n"
                + "  FROM [SGR].[dbo].[GP_REQUERIMIENTO] R \n"
                + "  inner join [SGR].[dbo].Empleados E on R.c_usuario_sol=E.Empleado_Id \n"
                + "  left join [SGR].[dbo].Empleados E2 on R.c_desarrollador=E2.Empleado_Id where fecha_rechazado is null and tipo_registro='R' and  c_desarrollador='" + user + "' and R.estado not in('" + Constantes.estado_finalizado + "','" + Constantes.estado_aceptado + "') ";

        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            Requerimientos obj;
            while (rs.next()) {
                obj = new Requerimientos();
                obj.setId(rs.getInt("id"));
                obj.setFecha(rs.getString("fecha_creado"));
                obj.setNombre_corto(rs.getString("nombre"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setFecha_autorizado("" + rs.getString("fecha_autorizado"));
                obj.setFecha_asginado_dev("" + rs.getString("fecha_asigno_desarrollo"));
                obj.setFecha_completado(rs.getString("fecha_completado"));
                obj.setFecha_aceptado(rs.getString("fecha_aceptado"));
                obj.setFecha_finalizado(rs.getString("fecha_finalizado"));
                obj.setC_developer(rs.getString("c_desarrollador"));
                obj.setD_developer(rs.getString("d_desarrollador"));
                obj.setC_usuario(rs.getString("c_usuario_sol"));
                obj.setD_usuario(rs.getString("d_usuario"));
                obj.setPrioridad(rs.getString("prioridad"));
                obj.setCircular(rs.getString("circular"));
                obj.setNuevo(rs.getString("nuevo"));
                obj.setEstado(rs.getString("estado"));
                obj.setTipo_registro(rs.getString("tipo_registro"));
                obj.setC_agencia(rs.getString("c_agencia"));
                obj.setC_area(rs.getString("c_area"));
                lista.add(obj);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas req" + e);

        } finally {
            this.Cerrar();
        }

        return lista;
    }

    public List<Requerimientos> listarCasos(String user) {
        List<Requerimientos> lista = null;

        ResultSet rs;

        String sql = "SELECT [id]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_creado],103)+' '+CONVERT(varchar,[fecha_creado],108) ,'1900-01-01 00:00:00') [fecha_creado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_autorizado],103)+' '+CONVERT(varchar,[fecha_autorizado],108),'1900-01-01 00:00:00') [fecha_autorizado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_asigno_desarrollo],103)+' '+CONVERT(varchar,[fecha_asigno_desarrollo],108),'1900-01-01 00:00:00') [fecha_asigno_desarrollo]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_completado],103)+' '+CONVERT(varchar,[fecha_completado],108),'1900-01-01 00:00:00') [fecha_completado]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_aceptado],103)+' '+CONVERT(varchar,[fecha_aceptado],108),'1900-01-01 00:00:00') [fecha_aceptado]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_finalizado],103)+' '+CONVERT(varchar,[fecha_finalizado],108),'1900-01-01 00:00:00') [fecha_finalizado]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_autorizado_area],103)+' '+CONVERT(varchar,[fecha_autorizado_area],108),'1900-01-01 00:00:00') [fecha_autorizado_area]\n"
                + "	  ,[aplicativo]\n"
                + "	  ,[especifique_otros]\n"
                + "	  ,[c_area]\n"
                + "      ,[c_usuario_sol]\n"
                + "	  ,E.Nombre as d_usuario\n"
                + "      ,[c_agencia]\n"
                + "      ,(select Descripcion_agencia from Sucursales where Cod_Agencia=R.c_agencia) d_agencia\n"
                + "      ,[descripcion]\n"
                + "      ,R.[nombre]\n"
                + "      ,[c_desarrollador]\n"
                + "	 ,E2.Nombre as d_desarrollador\n"
                + "	 ,R.estado \n"
                + "	 ,R.tipo_registro\n"
                + "  FROM [SGR].[dbo].[GP_REQUERIMIENTO] R \n"
                + "  inner join [SGR].[dbo].Empleados E on R.c_usuario_sol=E.Empleado_Id \n"
                + "  left join [SGR].[dbo].Empleados E2 on R.c_desarrollador=E2.Empleado_Id  where  fecha_rechazado is null and tipo_registro='C' and R.estado not in('" + Constantes.estado_finalizado + "','" + Constantes.estado_aceptado + "') ";

        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            Requerimientos obj;
            while (rs.next()) {
                obj = new Requerimientos();
                obj.setId(rs.getInt("id"));
                obj.setFecha(rs.getString("fecha_creado"));
                obj.setNombre_corto(rs.getString("nombre"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setFecha_autorizado("" + rs.getString("fecha_autorizado"));
                obj.setFecha_asginado_dev("" + rs.getString("fecha_asigno_desarrollo"));
                obj.setFecha_completado(rs.getString("fecha_completado"));
                obj.setFecha_aceptado(rs.getString("fecha_aceptado"));
                obj.setFecha_finalizado(rs.getString("fecha_finalizado"));
                obj.setFecha_autorizo_area(rs.getString("fecha_autorizado_area"));
                obj.setC_developer(rs.getString("c_desarrollador"));
                obj.setD_developer(rs.getString("d_desarrollador"));
                obj.setC_usuario(rs.getString("c_usuario_sol"));
                obj.setD_usuario(rs.getString("d_usuario"));

                try {
                    obj.setAplicativo(rs.getString("aplicativo").split(" "));
                } catch (Exception e) {
                    // System.out.println("Error obteniendo aplicativo.." + e);
                }
                obj.setEspecifique_otro(rs.getString("especifique_otros"));
                obj.setC_area(rs.getString("c_area"));
                obj.setC_agencia(rs.getString("c_agencia"));
                obj.setD_agencia(rs.getString("d_agencia").replaceAll("AGENCIA ", ""));

                obj.setEstado(rs.getString("estado"));
                obj.setTipo_registro(rs.getString("tipo_registro"));

                lista.add(obj);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas req" + e);

        } finally {
            this.Cerrar();
        }

        return lista;
    }

    public List<Requerimientos> listarHistorial(String where, String tip) {
        List<Requerimientos> lista = null;

        ResultSet rs;

        String sql = "SELECT [id]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_creado],103)+' '+CONVERT(varchar,[fecha_creado],108) ,'1900-01-01 00:00:00') [fecha_creado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_autorizado],103)+' '+CONVERT(varchar,[fecha_autorizado],108),'1900-01-01 00:00:00') [fecha_autorizado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_asigno_desarrollo],103)+' '+CONVERT(varchar,[fecha_asigno_desarrollo],108),'1900-01-01 00:00:00') [fecha_asigno_desarrollo]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_completado],103)+' '+CONVERT(varchar,[fecha_completado],108),'1900-01-01 00:00:00') [fecha_completado]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_aceptado],103)+' '+CONVERT(varchar,[fecha_aceptado],108),'1900-01-01 00:00:00') [fecha_aceptado]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_finalizado],103)+' '+CONVERT(varchar,[fecha_finalizado],108),'1900-01-01 00:00:00') [fecha_finalizado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_autorizado_area],103)+' '+CONVERT(varchar,[fecha_autorizado_area],108),'1900-01-01 00:00:00') [fecha_autorizado_area]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_rechazado],103)+' '+CONVERT(varchar,[fecha_rechazado],108),'1900-01-01 00:00:00') [fecha_rechazado]\n"
                + "      ,[circular]\n"
                + "      ,[prioridad]\n"
                + "      ,[nuevo]\n"
                + "      ,[c_usuario_sol]\n"
                + "	  ,E.Nombre as d_usuario\n"
                + "      ,[c_agencia]\n"
                + "      ,[descripcion]\n"
                + "      ,R.[nombre]\n"
                + "      ,[c_desarrollador]\n"
                + "	  ,E2.Nombre as d_desarrollador\n"
                + "	  ,R.estado\n"
                + "	  ,R.tipo_registro\n"
                + "	  ,R.aplicativo\n"
                + "	  ,R.especifique_otros\n"
                + "      ,(select Descripcion_agencia from Sucursales where Cod_Agencia=R.c_agencia) d_agencia\n"
                + "  FROM [SGR].[dbo].[GP_REQUERIMIENTO] R \n"
                + "  inner join [SGR].[dbo].Empleados E on R.c_usuario_sol=E.Empleado_Id \n"
                + "  left join [SGR].[dbo].Empleados E2 on R.c_desarrollador=E2.Empleado_Id \n"
                + "  where  tipo_registro like '%" + tip + "%' " + where;
        System.out.println("" + sql);
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            Requerimientos obj;
            while (rs.next()) {
                obj = new Requerimientos();
                obj.setId(rs.getInt("id"));
                obj.setFecha(rs.getString("fecha_creado"));
                obj.setNombre_corto(rs.getString("nombre"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setFecha_autorizado(rs.getString("fecha_autorizado"));
                obj.setFecha_asginado_dev(rs.getString("fecha_asigno_desarrollo"));
                obj.setFecha_completado(rs.getString("fecha_completado"));
                obj.setFecha_aceptado(rs.getString("fecha_aceptado"));
                obj.setFecha_finalizado(rs.getString("fecha_finalizado"));
                obj.setFecha_autorizo_area(rs.getString("fecha_autorizado_area"));
                obj.setFecha_rechazo(rs.getString("fecha_rechazado"));
                obj.setC_developer(rs.getString("c_desarrollador"));
                obj.setD_developer(rs.getString("d_desarrollador"));
                obj.setC_usuario(rs.getString("c_usuario_sol"));
                obj.setD_usuario(rs.getString("d_usuario"));
                obj.setPrioridad(rs.getString("prioridad"));
                obj.setCircular(rs.getString("circular"));
                obj.setNuevo(rs.getString("nuevo"));
                obj.setEstado(rs.getString("estado"));
                obj.setTipo_registro(rs.getString("tipo_registro"));

                try {
                    obj.setAplicativo(rs.getString("aplicativo").split(" "));
                } catch (Exception e) {
                    System.out.println("aplcat " + e);
                }

                obj.setEspecifique_otro(rs.getString("especifique_otros"));
                obj.setD_agencia(rs.getString("d_agencia"));

                lista.add(obj);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas req" + e);

        } finally {
            this.Cerrar();
        }

        return lista;
    }

    public List<Requerimientos> listarDocumentacionRequerimiento(Requerimientos objA) {
        List<Requerimientos> lista = null;

        ResultSet rs;

        String sql = "SELECT [id]\n"
                + "	  ,[id_req_padre]\n"
                + "      ,[nombre]      \n"
                + "      ,[tipo_registro]\n"
                + "      ,[estado]\n"
                + "  FROM [SGR].[dbo].[GP_REQUERIMIENTO]\n"
                + "  where id_req_padre='" + objA.getId() + "' and fecha_rechazado is null";
        //System.out.println(" "+sql);
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            Requerimientos obj;
            while (rs.next()) {
                obj = new Requerimientos();
                obj.setId(rs.getInt("id"));
                obj.setId_req_padre(rs.getString("id_req_padre"));
                obj.setNombre_corto(rs.getString("nombre"));
                obj.setTipo_registro(rs.getString("tipo_registro"));
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);
            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas req" + e);

        } finally {
            this.Cerrar();
        }

        return lista;
    }

    public Requerimientos obtenerObjetoReq(String id) {
        Requerimientos obj = new Requerimientos();

        ResultSet rs;

        String sql = "SELECT [id]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_creado],103)+' '+CONVERT(varchar,[fecha_creado],108) ,'1900-01-01 00:00:00') [fecha_creado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_autorizado],103)+' '+CONVERT(varchar,[fecha_autorizado],108),'1900-01-01 00:00:00') [fecha_autorizado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_asigno_desarrollo],103)+' '+CONVERT(varchar,[fecha_asigno_desarrollo],108),'1900-01-01 00:00:00') [fecha_asigno_desarrollo]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_completado],103)+' '+CONVERT(varchar,[fecha_completado],108),'1900-01-01 00:00:00') [fecha_completado]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_aceptado],103)+' '+CONVERT(varchar,[fecha_aceptado],108),'1900-01-01 00:00:00') [fecha_aceptado]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_finalizado],103)+' '+CONVERT(varchar,[fecha_finalizado],108),'1900-01-01 00:00:00') [fecha_finalizado]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_autorizado_area],103)+' '+CONVERT(varchar,[fecha_autorizado_area],108),'1900-01-01 00:00:00') [fecha_autorizado_area]\n"
                + "	  ,[aplicativo]\n"
                + "	  ,[prioridad]\n"
                + "	  ,[nuevo]\n"
                + "	  ,[circular]\n"
                + "	  ,[especifique_otros]\n"
                + "	  ,[c_area]\n"
                + "      ,[c_usuario_sol]\n"
                + "	  ,E.Nombre as d_usuario\n"
                + "      ,[c_agencia]\n"
                + "      ,(select Descripcion_agencia from Sucursales where Cod_Agencia=R.c_agencia) d_agencia\n"
                + "      ,[descripcion]\n"
                + "      ,R.[nombre]\n"
                + "      ,[c_desarrollador]\n"
                + "	 ,E2.Nombre as d_desarrollador\n"
                + "	 ,R.estado \n"
                + "	 ,R.tipo_registro \n"
                + "	 ,R.ambiente \n"
                + "	 ,R.tipo_comando \n"
                + "	 ,R.cantidad_registros_a \n"
                + "	 ,R.tipo_objeto \n"
                + "	 ,R.nombre_objeto \n"
                + "      ,[est_tarea]\n"
                // + "      ,[est_tipo_archivo]\n"
                + "      ,[est_objeto_tarea]\n"
                + "      ,[est_des_usuario_utilizan]\n"
                + "      ,[est_actividades_usuarios]\n"
                + "      ,[est_comentario]\n"
                + "      ,[est_notas]\n"
                + "      ,[scriptSQL]\n"
                + "      ,[scriptSQLAnterior]\n"
                + "  FROM [SGR].[dbo].[GP_REQUERIMIENTO] R \n"
                + "  inner join [SGR].[dbo].Empleados E on R.c_usuario_sol=E.Empleado_Id \n"
                + "  left join [SGR].[dbo].Empleados E2 on R.c_desarrollador=E2.Empleado_Id where id='" + id + "'  ";

        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            while (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setFecha(rs.getString("fecha_creado"));
                obj.setNombre_corto(rs.getString("nombre"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setFecha_autorizado("" + rs.getString("fecha_autorizado"));
                obj.setFecha_asginado_dev("" + rs.getString("fecha_asigno_desarrollo"));
                obj.setFecha_completado(rs.getString("fecha_completado"));
                obj.setFecha_aceptado(rs.getString("fecha_aceptado"));
                obj.setFecha_finalizado(rs.getString("fecha_finalizado"));
                obj.setFecha_autorizo_area(rs.getString("fecha_autorizado_area"));
                obj.setC_developer(rs.getString("c_desarrollador"));
                obj.setD_developer(rs.getString("d_desarrollador"));
                obj.setC_usuario(rs.getString("c_usuario_sol"));
                obj.setD_usuario(rs.getString("d_usuario"));
                obj.setTipo_registro(rs.getString("tipo_registro"));
                obj.setCant_reg_afec(rs.getInt("cantidad_registros_a"));
                obj.setTipo_objeto(rs.getString("tipo_objeto"));
                obj.setNombre_objeto(rs.getString("nombre_objeto"));

                try {
                    obj.setAplicativo(rs.getString("aplicativo").split(" "));
                } catch (Exception e) {
                    //System.out.println("Error obteniendo aplicativo.." + e);
                }
                try {
                    obj.setTipo_comando(rs.getString("tipo_comando").split(" "));
                } catch (Exception e) {
                    //System.out.println("Error obteniendo aplicativo.." + e);
                }
                obj.setPrioridad(rs.getString("prioridad"));
                obj.setNuevo(rs.getString("nuevo"));
                obj.setCircular(rs.getString("circular"));
                obj.setAmbiente(rs.getString("ambiente"));

                obj.setEspecifique_otro(rs.getString("especifique_otros"));
                obj.setC_area(rs.getString("c_area"));
                obj.setC_agencia(rs.getString("c_agencia"));
                obj.setD_agencia(rs.getString("d_agencia").replaceAll("AGENCIA ", ""));

                obj.setEstado(rs.getString("estado"));

                
                 obj.setTarea(rs.getString("est_tarea"));
                obj.setObjetoTarea(rs.getString("est_objeto_tarea"));
                //obj.setDes_usuarios_utilizan(rs.getString("est_des_usuario_utilizan"));
                obj.setComentario(rs.getString("est_comentario"));
                obj.setNotas(rs.getString("est_notas"));
                obj.setScriptSQL(rs.getString("scriptSQL"));
                obj.setScriptSQLAnterior(rs.getString("scriptSQLAnterior"));
                try {
                    obj.setActividades_usuarios(rs.getString("est_actividades_usuarios").split(" "));
                } catch (Exception e) {
                    //System.out.println("aplcat " + e);
                }
                try {

                    //String tmpStr=rs.getString("est_des_usuario_utilizan");
                    //String arr1[]=tmpStr.split(" ");
                    obj.setDes_usuarios_utilizan(Arrays.asList(rs.getString("est_des_usuario_utilizan").split(" ")));

                } catch (Exception e) {
                    //System.out.println("aplcat " + e);
                }

                
                
                
            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas req" + e);

        } finally {
            this.Cerrar();
        }

        return obj;
    }

    public List<Requerimientos> listarAyduas(String user) {
        List<Requerimientos> lista = null;

        ResultSet rs;

        String sql = "SELECT [id],[id_req_padre]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_creado],103)+' '+CONVERT(varchar,[fecha_creado],108) ,'1900-01-01 00:00:00') [fecha_creado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_autorizado],103)+' '+CONVERT(varchar,[fecha_autorizado],108),'1900-01-01 00:00:00') [fecha_autorizado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_asigno_desarrollo],103)+' '+CONVERT(varchar,[fecha_asigno_desarrollo],108),'1900-01-01 00:00:00') [fecha_asigno_desarrollo]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_completado],103)+' '+CONVERT(varchar,[fecha_completado],108),'1900-01-01 00:00:00') [fecha_completado]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_aceptado],103)+' '+CONVERT(varchar,[fecha_aceptado],108),'1900-01-01 00:00:00') [fecha_aceptado]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_finalizado],103)+' '+CONVERT(varchar,[fecha_finalizado],108),'1900-01-01 00:00:00') [fecha_finalizado]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_autorizado_area],103)+' '+CONVERT(varchar,[fecha_autorizado_area],108),'1900-01-01 00:00:00') [fecha_autorizado_area]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_inicio_permiso],103)+' '+CONVERT(varchar,[fecha_inicio_permiso],108),'1900-01-01 00:00:00') [fecha_inicio_permiso]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_fin_permiso],103)+' '+CONVERT(varchar,[fecha_fin_permiso],108),'1900-01-01 00:00:00') [fecha_fin_permiso]\n"
                + "	  ,[tipo_comando]\n"
                + "	  ,[ambiente]\n"
                + "	  ,[cantidad_registros_a]\n"
                + "	  ,[tipo_objeto]\n"
                + "	  ,[nombre_objeto]\n"
                + "	  ,[aplicativo]\n"
                + "	  ,[especifique_otros]\n"
                + "	  ,[c_area]\n"
                + "      ,[c_usuario_sol]\n"
                + "	  ,E.Nombre as d_usuario\n"
                + "      ,[c_agencia]\n"
                + "      ,(select Descripcion_agencia from Sucursales where Cod_Agencia=R.c_agencia) d_agencia\n"
                + "      ,[descripcion]\n"
                + "      ,R.[nombre]\n"
                + "      ,[c_dba]\n"
                + "	 ,E2.Nombre as d_dba\n"
                + "	 ,R.estado \n"
                + "	 ,R.tipo_registro \n"
                + "  FROM [SGR].[dbo].[GP_REQUERIMIENTO] R \n"
                + "  inner join [SGR].[dbo].Empleados E on R.c_usuario_sol=E.Empleado_Id \n"
                + "  left join [SGR].[dbo].Empleados E2 on R.c_desarrollador=E2.Empleado_Id  where  fecha_completado is null and fecha_rechazado is null and tipo_registro in('E','P')  and  c_usuario_sol='" + user + "'";

        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            Requerimientos obj;
            while (rs.next()) {
                obj = new Requerimientos();
                obj.setId(rs.getInt("id"));
                obj.setId_req_padre(rs.getString("id_req_padre"));
                obj.setFecha(rs.getString("fecha_creado"));
                obj.setNombre_corto(rs.getString("nombre"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setFecha_autorizado("" + rs.getString("fecha_autorizado"));
                obj.setFecha_asginado_dev("" + rs.getString("fecha_asigno_desarrollo"));
                obj.setFecha_completado(rs.getString("fecha_completado"));
                obj.setFecha_aceptado(rs.getString("fecha_aceptado"));
                obj.setFecha_finalizado(rs.getString("fecha_finalizado"));
                obj.setFecha_autorizo_area(rs.getString("fecha_autorizado_area"));
                //obj.setC_developer(rs.getString("c_desarrollador"));
                //obj.setD_developer(rs.getString("d_desarrollador"));
                obj.setC_dba(rs.getString("c_dba"));
                obj.setD_dba(rs.getString("d_dba"));
                obj.setC_usuario(rs.getString("c_usuario_sol"));
                obj.setD_usuario(rs.getString("d_usuario"));

                try {
                    obj.setTipo_comando(rs.getString("tipo_comando").split(" "));
                } catch (Exception e) {

                }
                obj.setAmbiente(rs.getString("ambiente"));
                obj.setCant_reg_afec(rs.getInt("cantidad_registros_a"));
                obj.setTipo_registro(rs.getString("tipo_registro"));
                obj.setEspecifique_otro(rs.getString("especifique_otros"));
                obj.setNombre_objeto(rs.getString("nombre_objeto"));
                obj.setTipo_objeto(rs.getString("tipo_objeto"));

                obj.setC_area(rs.getString("c_area"));
                obj.setC_agencia(rs.getString("c_agencia"));
                obj.setD_agencia(rs.getString("d_agencia").replaceAll("AGENCIA ", ""));

                obj.setEstado(rs.getString("estado"));

                lista.add(obj);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas ayudas" + e);

        } finally {
            this.Cerrar();
        }

        return lista;
    }

    public List<MyFile> listarDocumentos(Requerimientos obj) {

        List<MyFile> lista = null;

        ResultSet rs;

        String sql = "SELECT [ID_DOC]\n"
                + "      ,[ID_REQUERIMIENTO]\n"
                + "      ,[NOMBRE]\n"
                + "      ,[RAIZ]\n"
                + "      ,[FOLDER]\n"
                + "      ,[ESTADO]\n"
                + "  FROM [SGR].[dbo].[GP_DOCUMENTOS]\n"
                + "  WHERE ID_REQUERIMIENTO='" + obj.getId() + "' AND ESTADO='1'";

        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            MyFile file;
            while (rs.next()) {
                file = new MyFile();
                file.setId_doc(rs.getInt("ID_DOC"));
                file.setId_req(rs.getInt("ID_REQUERIMIENTO"));
                file.setNombre(rs.getString("NOMBRE"));
                file.setRuta_raiz(rs.getString("RAIZ"));
                file.setRuta_folder(rs.getString("FOLDER"));
                file.setEstado(rs.getInt("ESTADO"));
                file.setExiste(true);

                lista.add(file);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listar doc" + e);

        } finally {
            this.Cerrar();
        }

        return lista;

    }

    public void actulizaDescripcionAyuda(Requerimientos obj) {
        String sql = "UPDATE [dbo].[GP_REQUERIMIENTO] SET DESCRIPCION=? WHERE ID=?";

        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);
            st.setString(1, obj.getDescripcion());
            st.setInt(2, obj.getId());

            st.executeUpdate();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al actualizar descripcion" + e));
            System.err.println("Error al actualizar descripcion " + e + "\n" + sql);
        } finally {
            this.Cerrar();
        }

    }

    public void insertarCasoEjecucion(Requerimientos obj) {

        String existe = "select count(*) from [dbo].[GP_REQUERIMIENTO] where ID='" + obj.getId() + "';";

        existe = m.obtenerString(existe);
        if (existe.trim().equalsIgnoreCase("0")) {
            String tipocomando = "";
            for (int i = 0; i < obj.getTipo_comando().length; i++) {
                String tm[] = obj.getTipo_comando();
                tipocomando = tipocomando + " " + tm[i];
            }

            String sql = "INSERT INTO [dbo].[GP_REQUERIMIENTO]\n"
                    + "           ([fecha_creado]\n"
                    + "           ,[c_usuario_sol]\n"
                    + "           ,[c_desarrollador]\n"
                    + "           ,[c_agencia]\n"
                    + "	      ,[tipo_comando]\n"
                    + "           ,[ambiente]\n"
                    // + "           ,[descripcion]\n"
                    + "           ,[nombre]\n"
                    + "           ,[c_area]\n"
                    + "           ,[tipo_registro]\n"
                    + "           ,[estado]\n"
                    + "           ,[id_req_padre]\n"
                    + "           ,[cantidad_registros_a]\n"
                    + "		   )\n"
                    + "     VALUES\n"
                    + "           (\n"
                    + "	       GETDATE() \n"
                    + "           ,'" + obj.getC_usuario() + "'\n"
                    + "           ,'" + obj.getC_usuario() + "'\n"
                    + "           ,'" + obj.getC_agencia() + "'\n"
                    + "	      ,'" + tipocomando.trim() + "'\n"
                    + "           ,'" + obj.getAmbiente() + "'\n"
                    //+ "           ,'" + obj.getDescripcion() + "'\n"
                    + "           ,'" + obj.getNombre_corto() + "'\n"
                    + "           ,'" + obj.getC_area() + "'\n"
                    + "           ,'E'\n"
                    + "           ,'" + Constantes.estado_sin_autortizar_area + "'\n"
                    + "           ,'" + obj.getId_req_padre() + "'\n"
                    + "           ,'" + obj.getCant_reg_afec() + "'\n"
                    + "		   )";
            try {
                this.Conectar();
                PreparedStatement st = this.getCon().prepareStatement(sql);

                ///  st.setString(1, cat.getDETALLE());
                st.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
                //////RequestContext context = RequestContext.getCurrentInstance();
                //PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').hide();");
                PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').hide();");

                st.close();
                try {
                    obj.setId(Integer.parseInt(obtenerUltimaSec(obj)));
                    actulizaDescripcionAyuda(obj);
                    //insertarDoc(obj);
                } catch (Exception e) {
                    System.out.println("Error obtienendo ultimo ID \n" + e);
                }

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + e));
                System.err.println("Error al insertar " + e + "\n" + sql);

            } finally {
                this.Cerrar();

            }
        } else {
            System.err.println("ya existe ");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Esta Ayuda ya esta Guardada"));
        }

    }

    public void insertarCasoPermiso(Requerimientos obj) {

        String existe = "select count(*) from [dbo].[GP_REQUERIMIENTO] where ID='" + obj.getId() + "';";
        existe = m.obtenerString(existe);
        if (existe.trim().equalsIgnoreCase("0")) {
            String tipopermiso = "";
            for (int i = 0; i < obj.getTipo_comando().length; i++) {
                String tm[] = obj.getTipo_comando();
                tipopermiso = tipopermiso + " " + tm[i];
            }

            String sql = "INSERT INTO [dbo].[GP_REQUERIMIENTO]\n"
                    + "           ([fecha_creado]\n"
                    + "           ,[c_usuario_sol]\n"
                    + "           ,[c_desarrollador]\n"
                    + "           ,[c_agencia]\n"
                    + "	      ,[tipo_comando]\n"
                    + "           ,[ambiente]\n"
                    //+ "           ,[descripcion]\n"
                    + "           ,[nombre]\n"
                    + "           ,[c_area]\n"
                    + "           ,[tipo_registro]\n"
                    + "           ,[estado]\n"
                    + "           ,[id_req_padre]\n"
                    + "           ,[nombre_objeto]\n"
                    + "           ,[tipo_objeto]\n"
                    + "		   )\n"
                    + "     VALUES\n"
                    + "           (\n"
                    + "	       GETDATE() \n"
                    + "           ,'" + obj.getC_usuario() + "'\n"
                    + "           ,'" + obj.getC_usuario() + "'\n"
                    + "           ,'" + obj.getC_agencia() + "'\n"
                    + "	      ,'" + tipopermiso.trim() + "'\n"
                    + "           ,'" + obj.getAmbiente() + "'\n"
                    //  + "           ,'" + obj.getDescripcion() + "'\n"
                    + "           ,'" + obj.getNombre_corto() + "'\n"
                    + "           ,'" + obj.getC_area() + "'\n"
                    + "           ,'P'\n"
                    + "           ,'" + Constantes.estado_sin_autortizar_area + "'\n"
                    + "           ,'" + obj.getId_req_padre() + "'\n"
                    + "           ,'" + obj.getNombre_objeto() + "'\n"
                    + "           ,'" + obj.getTipo_objeto() + "'\n"
                    + "		   )";
            try {
                this.Conectar();
                PreparedStatement st = this.getCon().prepareStatement(sql);

                ///  st.setString(1, cat.getDETALLE());
                st.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
                //////RequestContext context = RequestContext.getCurrentInstance();
                //PrimeFaces.current().executeScript("PF('wdlPermiso').hide();");
                PrimeFaces.current().executeScript("PF('wdlPermiso').hide();");

                st.close();

                try {
                    obj.setId(Integer.parseInt(obtenerUltimaSec(obj)));
                    actulizaDescripcionAyuda(obj);
                    //insertarDoc(obj);
                } catch (Exception e) {
                    System.out.println("Error obtienendo ultimo ID \n" + e);
                }
                /*try {
                obj.setId(Integer.parseInt(obtenerUltimaSec(obj)));
                insertarDoc(obj);
            } catch (Exception e) {
                System.out.println("Error obtienendo ultimo ID para vincular Documentos\n" + e);
            }*/

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + e));
                System.err.println("Error al insertar " + e + "\n" + sql);

            } finally {
                this.Cerrar();

            }

        } else {
            System.err.println("ya existe ");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Esta Ayuda ya esta Guardada"));
        }

    }

    public void insertarEstructura(Requerimientos obj) {

        /*
            String tipopermiso = "";
            for (int i = 0; i < obj.getTipo_comando().length; i++) {
                String tm[] = obj.getTipo_comando();
                tipopermiso = tipopermiso + " " + tm[i];
            }*/
        String sql = "INSERT INTO [dbo].[GP_REQUERIMIENTO]\n"
                + "           ([fecha_creado]\n"
                //    + "           ,[fecha_autorizado]\n"
                + "           ,[c_usuario_sol]\n"
                + "           ,[c_desarrollador]\n"
                + "           ,[c_agencia]\n"
                + "           ,[nombre]\n"
                + "           ,[est_tarea]\n"
                + "           ,[tipo_registro]\n"
                + "           ,[estado]\n"
                + "           ,[id_req_padre]\n"
                + "           ,[c_area]\n"
                + "           ,[est_objeto_tarea]\n"
                + "           ,[est_notas]\n"
                + "		   )\n"
                + "     VALUES\n"
                + "           (\n"
                + "	       GETDATE() \n"
                //   + "	      ,GETDATE() \n"
                + "           ,'" + obj.getC_usuario() + "'\n"
                + "           ,'" + obj.getC_usuario() + "'\n"
                + "           ,'" + obj.getC_agencia() + "'\n"
                + "           ,'" + obj.getNombre_corto() + "'\n"
                + "           , '" + obj.getTarea() + "'\n"
                + "           ,'B'\n"
                + "           ,'" + obj.getEstado() + "'\n"
                + "           ,'" + obj.getId_req_padre() + "'\n"
                + "           ,'" + obj.getC_area() + "'\n"
                + "           ,'" + obj.getObjetoTarea() + "'\n"
                + "           ,'" + obj.getNotas() + "'\n"
                + "		   )";
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);

            ///  st.setString(1, cat.getDETALLE());
            st.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
            //////RequestContext context = RequestContext.getCurrentInstance();
            //PrimeFaces.current().executeScript("PF('wdlDescargaProce').hide();");
            PrimeFaces.current().executeScript("PF('wdlDescargaProce').hide();");

            st.close();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + e));
            System.err.println("Error al insertar " + e + "\n" + sql);

        } finally {
            this.Cerrar();

        }

    }

    public void insertarEstructuraNueva(Requerimientos obj) {

        String acti_usuarios = "";
        String tm[] = obj.getActividades_usuarios();
        for (int i = 0; i < tm.length; i++) {
            acti_usuarios = acti_usuarios + " " + tm[i].trim();
        }

        String usuarios_utilizan = "";
        for (int i = 0; i < obj.getDes_usuarios_utilizan().size(); i++) {
            String tmp = obj.getDes_usuarios_utilizan().get(i);
            if (tmp.equalsIgnoreCase(" ") || tmp.isEmpty() || tmp.length() > 0) {

                usuarios_utilizan = usuarios_utilizan + tmp.trim() + " ";

            }
        }

        String sql = "INSERT INTO [dbo].[GP_REQUERIMIENTO]\n"
                + "           ([fecha_creado]\n"
                + "           ,[c_usuario_sol]\n"
                + "           ,[nombre]\n"
                + "           ,[c_desarrollador]\n"
                + "           ,[ambiente]\n"
                + "           ,[id_req_padre]\n"
                + "           ,[tipo_registro]\n"
                + "           ,[estado]\n"
                + "           ,[c_area]\n"
                + "           ,[c_agencia]\n"
                // + "           ,[est_nuevo]\n"
                //  + "           ,[est_anterior]\n"
                + "           ,[est_tarea]\n"
                + "           ,[est_objeto_tarea]\n"
                + "           ,[est_des_usuario_utilizan]\n"
                + "           ,[est_actividades_usuarios]\n"
                + "           ,[est_comentario]\n"
                + "           ,[est_notas],[scriptSQL])\n"
                + "     VALUES\n"
                + "           (\n"
                + "		GETDATE()\n"
                + "           , '" + obj.getC_usuario() + "'\n"
                + "           , '" + obj.getNombre_corto() + "'\n"
                + "           , '" + obj.getC_usuario() + "'\n"
                + "           , '" + obj.getAmbiente() + "'\n"
                + "           , '" + obj.getId_req_padre() + "'\n"
                + "           , 'A' \n"
                + "           , '" + obj.getEstado() + "' \n"
                + "           , '" + obj.getC_area() + "' \n"
                + "           , '" + obj.getC_agencia() + "' \n"
                // + "           , '" + (obj.isVisibleBtIndicador1() == true ? 1 : 0) + "'\n"
                // + "           , '" + (obj.isVisibleBtIndicador2() == true ? 1 : 0) + "'\n"
                + "           , '" + obj.getTarea() + "'\n"
                + "           , '" + obj.getObjetoTarea() + "'\n"
                + "           , '" + usuarios_utilizan + "'\n"
                + "           , '" + acti_usuarios + "'\n"
                + "           , '" + obj.getComentario() + "'\n"
                + "           , '" + obj.getNotas() + "',?\n"
                + "		   ) ";
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);
             st.setString(1, obj.getScriptSQL());
            st.executeUpdate();
            st.close();
            /*try {
                // System.out.println("script:"+obj.getScriptSQL());
                if (!obj.getScriptSQL().isEmpty()) {

                    obj.setId(Integer.parseInt(obtenerUltimaSec(obj)));

                    //System.out.println("script:"+obj.getScriptSQL());
                    //System.out.println("id:"+obj.getId());
                    sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                            + "   SET \n"
                            + "       [scriptSQL] = ? \n"
                            + " WHERE id = ? ";
                    this.Conectar();
                    st = this.getCon().prepareStatement(sql);
                    st.setString(1, obj.getScriptSQL());
                    st.setInt(2, obj.getId());

                    st.executeUpdate();
                    st.close();
                    this.Cerrar();
                }
            } catch (Exception e) {

                System.out.println("" + e + " sql:" + sql);
            }
            */
            try {
                 insertaTabla(obj);
            } catch (Exception e) {
            }
           

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
            ////RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlCargaProce').hide();");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + e));
            System.err.println("Error al insertar " + e + "\n" + sql);

        } finally {
            this.Cerrar();

        }

    }

    public void updateEstructuraNueva(Requerimientos obj) {

        String acti_usuarios = "";
        String tm[] = obj.getActividades_usuarios();
        for (int i = 0; i < tm.length; i++) {
            acti_usuarios = acti_usuarios + " " + tm[i].trim();
        }

        String usuarios_utilizan = "";
        for (int i = 0; i < obj.getDes_usuarios_utilizan().size(); i++) {
            String tmp = obj.getDes_usuarios_utilizan().get(i);
            if (tmp.equalsIgnoreCase(" ") || tmp.isEmpty() || tmp.length() > 0) {

                usuarios_utilizan = usuarios_utilizan + tmp.trim() + " ";

            }
        }

        String sql = "UPDATE [dbo].[GP_REQUERIMIENTO] SET \n"
                + "            [ambiente]='" + obj.getAmbiente() + "'\n"
                + "           ,[tipo_registro]='" + obj.getTipo_registro() + "'\n"
                + "           ,[estado]='" + obj.getEstado() + "'\n"
                // + "           ,[est_nuevo]\n"
                //  + "           ,[est_anterior]\n"
                + "           ,[est_tarea]='" + obj.getTarea() + "'\n"
                + "           ,[est_objeto_tarea]='" + obj.getObjetoTarea() + "'\n"
                + "           ,[est_des_usuario_utilizan]='" + usuarios_utilizan + "'\n"
                + "           ,[est_actividades_usuarios]='" + acti_usuarios + "'\n"
                + "           ,[est_comentario]='" + obj.getComentario() + "'\n"
                + "           ,[est_notas]='" + obj.getNotas() + "'\n"
                + "           ,[scriptSQL]= ? \n"
                + "    WHERE id='" + obj.getId() + "'";
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);
            st.setString(1, obj.getScriptSQL());
            st.executeUpdate();
            st.close();

            insertaTabla(obj);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
            ////RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlCargaProce').hide();");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + e));
            System.err.println("actualizar al insertar " + e + "\n" + sql);

        } finally {
            this.Cerrar();

        }

    }

    public void finEstructura(Requerimientos obj) {

        String sql = "UPDATE [dbo].[GP_REQUERIMIENTO] SET \n"
                + "           [estado]='" + obj.getEstado() + "'\n"
                + "    WHERE id='" + obj.getId() + "'";
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);
            st.executeUpdate();
            st.close();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Finalizado Correctamente"));
            ////RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlCargaProce').hide();");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + e));
            System.err.println("actualizar al insertar " + e + "\n" + sql);

        } finally {
            this.Cerrar();

        }

    }

    public void insertaTabla(Requerimientos obj) {

        if (obj.getLstTabla().size() <= 0) {
            return;
        }

        String sql = "DELETE FROM [dbo].[GP_REQ_TABLA] WHERE ID='" + obj.getId() + "'";

        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);
            st.executeUpdate();
            st.close();

            sql = "INSERT INTO [dbo].[GP_REQ_TABLA]\n"
                    + "           ([idReq]\n"
                    + "           ,[n_campo]\n"
                    + "           ,[nombre_campo]\n"
                    + "           ,[tipo_campo]\n"
                    + "           ,[tamano_campo]\n"
                    + "           ,[contraint_campo])\n"
                    + "     VALUES\n ";

            TablaSQL tbl;
            for (int i = 0; i < obj.getLstTabla().size(); i++) {
                tbl = obj.getLstTabla().get(i);

                sql = sql + "           ('" + obj.getId() + "'\n"
                        + "           ,'" + (i + 1) + "'\n"
                        + "           ,'" + tbl.getNombre() + "'\n"
                        + "           ,'" + tbl.getTipo() + "'\n"
                        + "           ,'" + tbl.getTamano() + "'\n"
                        + "           ,'" + tbl.getConstraint() + "'),";

            }

            sql = sql.substring(0, sql.length() - 1);
            st = this.getCon().prepareStatement(sql);
            st.executeUpdate();
            st.close();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar tabla" + e));
            System.err.println("Error al insertar " + e + "\n" + sql);

        } finally {
            this.Cerrar();

        }

    }

    public String insertarArchivoTOP_DESCARGA(Archivo obj) {

        //String tem_tipo=obj.getTipo_archivo().replaceAll(";", " ");
        String ID = "0";
        /* for (int i = 0; i < obj.getTipo_comando().length; i++) {
                String tm[] = obj.getTipo_comando();
                tipopermiso = tipopermiso + " " + tm[i];
            }*/

        String fecha_var1 = "";

        if (obj.getTipo_proceso().equalsIgnoreCase("DESCARGA")) {
            fecha_var1 = "           ,[FECHA_PIDE]\n";

        } else {
            fecha_var1 = "           ,[FECHA_PIDE_SUBIDA]\n";
        }

        String sql = "INSERT INTO [dbo].[GP_ARCHIVOS_TOP]\n"
                + "           ([ID_REQUERIMIENTO]\n"
                + "           ,[NOMBRE_ARCHIVO]\n"
                + "           ,[TIPO_ARCHIVO]\n"
                + "           ,[TIPO_PROCESO]\n"
                + "           ,[COMENTARIO]\n"
                + fecha_var1
                + "           ,[C_USUARIO_SOL]\n"
                + "           ,[RAIZ]\n"
                + "           ,[FOLDER]\n"
                + "           ,[NUEVO1]\n"
                + "           ,[NUEVO2]\n"
                + "           ,[ANTERIOR1]\n"
                + "           ,[ANTERIOR2]\n"
                + "           ,[ESTADO])\n"
                + "     VALUES\n"
                + "           ('" + obj.getIdReq() + "'\n"
                + "           ,'" + obj.getNombre_archivo() + "'\n"
                + "           ,'" + obj.getTipo_archivo() + "'\n"
                + "           ,'" + obj.getTipo_proceso() + "'\n"
                + "           ,'" + obj.getComentario() + "'\n"
                + "           ,GETDATE()\n"
                + "           ,'" + obj.getC_usuario_sol() + "'\n"
                + "           ,'" + obj.getRuta_raiz() + "'\n"
                + "           ,'" + obj.getRuta_folder() + "'\n"
                + "           ," + (obj.isVisibleBtIndicador1() == true ? 1 : 0) + "\n"
                + "           ," + (obj.isVisibleBtIndicador2() == true ? 1 : 0) + "\n"
                + "           ," + (obj.isVisibleBtIndicador3() == true ? 1 : 0) + "\n"
                + "           ," + (obj.isVisibleBtIndicador4() == true ? 1 : 0) + "\n"
                + "           ,1)";
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);

            ///  st.setString(1, cat.getDETALLE());
            st.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
            ////RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlDescarga').hide();PF('wdlCargarNuevo').hide();");

            st.close();

            try {
                ID = m.obtenerString("select ISNULL(MAX(ID_ARCHIVO),0) ID from GP_ARCHIVOS_TOP where ID_REQUERIMIENTO='" + obj.getIdReq() + "'");
            } catch (Exception e) {
                System.out.println("Error obtienendo ultimo ID \n" + e);
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + e));
            System.err.println("Error al insertar " + e + "\n" + sql);

        } finally {
            this.Cerrar();

        }

        return ID;

    }

    public void modificarArchivoTOP_DESCARGA(Archivo obj, String proceso) {

        String fecha = "";
        if (proceso.equalsIgnoreCase("FINALIZAR")) {
            fecha = "      ,[FECHA_FINALIZADO] = GETDATE()\n";
        } else {
            fecha = "      ,[FECHA_PIDE_SUBIDA] = GETDATE()\n";
        }

        String sql = "UPDATE [dbo].[GP_ARCHIVOS_TOP]\n"
                + "   SET \n"
                + "       [TIPO_PROCESO] = '" + obj.getTipo_proceso() + "'\n"
                + "      ,[COMENTARIO] = COMENTARIO+'" + obj.getComentario_nuevo() + "'\n"
                + fecha
                //+ "      ,[C_USUARIO_PROCESA] = '"+obj.getC_usuario_procesa()+"'\n"
                + "      ,[ESTADO] = '" + obj.getEstado() + "'\n"
                + "      ,[NUEVO1] = '" + (obj.isVisibleBtIndicador1() == true ? 1 : 0) + "'\n"
                + "      ,[NUEVO2] = '" + (obj.isVisibleBtIndicador2() == true ? 1 : 0) + "'\n"
                + "      ,[RAIZ] = '" + obj.getRuta_raiz() + "'\n"
                + "      ,[FOLDER] = '" + obj.getRuta_folder() + "'\n"
                + " WHERE ID_ARCHIVO='" + obj.getIdArchivo() + "'";
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);

            ///  st.setString(1, cat.getDETALLE());
            st.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Actualizado Correctamente"));
            ////RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlProcesar').hide();");

            st.close();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al Actualizar" + e));
            System.err.println("Error al modificar " + e + "\n" + sql);

        } finally {
            this.Cerrar();

        }

    }

    public List<Archivo> listarArchivosTOP(String id_req) {

        List<Archivo> lista = null;

        ResultSet rs;

        String sql = "SELECT [ID_ARCHIVO]\n"
                + "      ,[ID_REQUERIMIENTO]\n"
                + "      ,[NOMBRE_ARCHIVO]\n"
                + "      ,[TIPO_ARCHIVO]\n"
                + "      ,[TIPO_PROCESO]\n"
                + "      ,[COMENTARIO]\n"
                + "      ,[FECHA_PIDE]\n"
                + "      ,[FECHA_ENVIA]\n"
                + "      ,[FECHA_SUBE]\n"
                + "      ,[FECHA_FINALIZADO]\n"
                + "      ,[C_USUARIO_SOL]\n"
                + "      ,[C_USUARIO_PROCESA]\n"
                + "      ,[RAIZ]\n"
                + "      ,[FOLDER]\n"
                + "      ,[ESTADO]\n"
                + "      ,[NUEVO1]\n"
                + "      ,[NUEVO2]\n"
                + "      ,[ANTERIOR1]\n"
                + "      ,[ANTERIOR2]"
                + "  FROM [SGR].[dbo].[GP_ARCHIVOS_TOP] WHERE ID_REQUERIMIENTO='" + id_req + "' and ESTADO in(1,2,3) or (CONVERT(DATE, FECHA_SUBE)= CONVERT(DATE, GETDATE()) and ID_REQUERIMIENTO='" + id_req + "') and estado<>0";

        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            Archivo file;
            while (rs.next()) {
                file = new Archivo();
                file.setIdArchivo(rs.getInt("ID_ARCHIVO"));
                file.setIdReq(rs.getString("ID_REQUERIMIENTO"));
                file.setNombre_archivo(rs.getString("NOMBRE_ARCHIVO"));
                file.setTipo_archivo(rs.getString("TIPO_ARCHIVO"));
                file.setTipo_proceso(rs.getString("TIPO_PROCESO"));
                file.setComentario(rs.getString("COMENTARIO"));
                file.setRuta_raiz(rs.getString("RAIZ"));
                file.setRuta_folder(rs.getString("FOLDER"));
                file.setEstado(rs.getInt("ESTADO"));
                file.setFecha_pide(rs.getString("FECHA_PIDE"));
                file.setFecha_envia(rs.getString("FECHA_ENVIA"));
                file.setFecha_sube(rs.getString("FECHA_SUBE"));
                file.setFecha_completado(rs.getString("FECHA_FINALIZADO"));
                file.setC_usuario_sol(rs.getString("C_USUARIO_SOL"));
                file.setC_usuario_procesa(rs.getString("C_USUARIO_PROCESA"));

                file.setVisibleBtIndicador1(rs.getBoolean("NUEVO1"));
                file.setVisibleBtIndicador2(rs.getBoolean("NUEVO2"));
                file.setVisibleBtIndicador3(rs.getBoolean("ANTERIOR1"));
                file.setVisibleBtIndicador4(rs.getBoolean("ANTERIOR2"));

                String compatible[] = file.getTipo_archivo().split(";");
                if (file.isVisibleBtIndicador1()) {
                    for (int i = 0; i < compatible.length; i++) {

                        if (i == 0) {
                            file.setArchivo3(compatible[0]);
                        }
                        if (i == 1) {
                            file.setArchivo4(compatible[1]);
                        }
                    }

                }

                if (file.isVisibleBtIndicador3()) {
                    for (int i = 0; i < compatible.length; i++) {

                        if (i == 0) {
                            file.setArchivo1(compatible[0]);
                        }
                        if (i == 1) {
                            file.setArchivo2(compatible[1]);
                        }
                    }

                }

                lista.add(file);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listar doc" + e);

        } finally {
            this.Cerrar();
        }

        return lista;

    }

    public List<Requerimientos> listarSolicitudEstructura(String user, String id) {

        List<Requerimientos> lista = null;

        ResultSet rs;

        String sql = "SELECT [id]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_creado],103)+' '+CONVERT(varchar,[fecha_creado],108) ,'1900-01-01 00:00:00') [fecha_creado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_autorizado],103)+' '+CONVERT(varchar,[fecha_autorizado],108) ,'1900-01-01 00:00:00') [fecha_autorizado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_asigno_desarrollo],103)+' '+CONVERT(varchar,[fecha_asigno_desarrollo],108) ,'1900-01-01 00:00:00') [fecha_asigno_desarrollo]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_completado],103)+' '+CONVERT(varchar,[fecha_completado],108) ,'1900-01-01 00:00:00') [fecha_completado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_aceptado],103)+' '+CONVERT(varchar,[fecha_aceptado],108) ,'1900-01-01 00:00:00') [fecha_aceptado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_finalizado],103)+' '+CONVERT(varchar,[fecha_finalizado],108) ,'1900-01-01 00:00:00') [fecha_finalizado]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_rechazado],103)+' '+CONVERT(varchar,[fecha_rechazado],108) ,'1900-01-01 00:00:00') [fecha_rechazado]\n"
                + "      ,[c_usuario_sol]\n"
                + "      ,[c_agencia]\n"
                + "      ,R.[nombre]\n"
                + "      ,[c_desarrollador]\n"
                + "      ,[fecha_autorizado_area]\n"
                + "      ,[c_usuario_area]\n"
                + "      ,[c_usuario_jefe_p]\n"
                + "      ,[c_area]\n"
                + "      ,[ambiente]\n"
                + "      ,[c_dba]\n"
                + "      ,[fecha_inicio_permiso]\n"
                + "      ,[fecha_fin_permiso]\n"
                + "      ,[id_req_padre]\n"
                + "      ,[tipo_registro]\n"
                + "      ,R.[estado]\n"
                + "      ,[sub_tipo]\n"
                + "	 ,E.Nombre as d_usuario\n"
                + "      ,R.[nombre]\n"
                + "	 ,E2.Nombre as d_desarrollador\n"
                // + "      ,[est_nuevo]\n"
                // + "      ,[est_anterior]\n"
                + "      ,[est_tarea]\n"
                // + "      ,[est_tipo_archivo]\n"
                + "      ,[est_objeto_tarea]\n"
                + "      ,[est_des_usuario_utilizan]\n"
                + "      ,[est_actividades_usuarios]\n"
                + "      ,[est_comentario]\n"
                + "      ,[est_notas]\n"
                + "      ,[scriptSQL]\n"
                + "      ,[scriptSQLAnterior]\n"
                + "  FROM [SGR].[dbo].[GP_REQUERIMIENTO] R "
                + "  inner join [SGR].[dbo].Empleados E on R.c_usuario_sol=E.Empleado_Id \n"
                + "  left join [SGR].[dbo].Empleados E2 on R.c_desarrollador=E2.Empleado_Id "
                + " where tipo_registro in('A','B','U') and R.estado not in('0','Finalizado')  and  c_usuario_sol='" + user + "' and id_req_padre='" + id + "'";

        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            Requerimientos obj;
            while (rs.next()) {
                obj = new Requerimientos();
                obj.setId(rs.getInt("id"));
                obj.setFecha(rs.getString("fecha_creado"));
                obj.setNombre_corto(rs.getString("nombre"));
                obj.setFecha_autorizado(rs.getString("fecha_autorizado"));
                obj.setFecha_asginado_dev(rs.getString("fecha_asigno_desarrollo"));
                obj.setFecha_completado(rs.getString("fecha_completado"));
                obj.setFecha_aceptado(rs.getString("fecha_aceptado"));
                obj.setFecha_finalizado(rs.getString("fecha_finalizado"));
                obj.setFecha_autorizo_area(rs.getString("fecha_autorizado_area"));
                obj.setFecha_rechazo(rs.getString("fecha_rechazado"));
                obj.setC_developer(rs.getString("c_desarrollador"));
                obj.setD_developer(rs.getString("d_desarrollador"));
                obj.setC_usuario(rs.getString("c_usuario_sol"));
                obj.setD_usuario(rs.getString("d_usuario"));
                obj.setEstado(rs.getString("estado"));
                obj.setTipo_registro(rs.getString("tipo_registro"));

                obj.setTarea(rs.getString("est_tarea"));
                obj.setObjetoTarea(rs.getString("est_objeto_tarea"));
                //obj.setDes_usuarios_utilizan(rs.getString("est_des_usuario_utilizan"));
                obj.setComentario(rs.getString("est_comentario"));
                obj.setNotas(rs.getString("est_notas"));
                obj.setScriptSQL(rs.getString("scriptSQL"));
                obj.setScriptSQLAnterior(rs.getString("scriptSQLAnterior"));
                try {
                    obj.setActividades_usuarios(rs.getString("est_actividades_usuarios").split(" "));
                } catch (Exception e) {
                    //System.out.println("aplcat " + e);
                }
                try {

                    //String tmpStr=rs.getString("est_des_usuario_utilizan");
                    //String arr1[]=tmpStr.split(" ");
                    obj.setDes_usuarios_utilizan(Arrays.asList(rs.getString("est_des_usuario_utilizan").split(" ")));

                } catch (Exception e) {
                    //System.out.println("aplcat " + e);
                }

                lista.add(obj);
            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listar estructura sol" + e + " " + sql);
        } finally {
            this.Cerrar();
        }

        return lista;

    }

    ConexionOracle conOracle = new ConexionOracle();

    public List<TablaSQL> listarEstructuraTabla(Requerimientos obj, int t) {

        List<TablaSQL> lista = null;

        String sql = "select \n"
                + "       column_name\n"
                + "       ,data_type\n"
                + "       ,CASE WHEN char_length = 0  THEN \n"
                + "                  CASE WHEN data_scale = 0 THEN\n"
                + "                     TO_CHAR(data_precision)\n"
                + "                     ELSE\n"
                + "                       CASE WHEN data_precision IS NULL THEN \n"
                + "                         ' ' \n"
                + "                         ELSE\n"
                + "                            CONCAT(CONCAT(TO_CHAR(data_precision),','),TO_CHAR(data_scale)) \n"
                + "                       END\n"
                + "                     END\n"
                + "             ELSE  TO_CHAR(data_length) \n"
                + "        END tamano\n"
                + "--       ,data_precision\n"
                + "--     ,data_scale\n"
                + "      -- ,CASE WHEN nullable = 'Y'\n"
                + "       ,CASE WHEN column_name IN(SELECT col.COLUMN_NAME \n"
                + "                         FROM all_cons_columns col \n"
                + "                         INNER JOIN all_constraints pk ON col.TABLE_NAME=pk.TABLE_NAME \n"
                + "                          WHERE col.table_name=tab.TABLE_NAME\n"
                + "                                AND pk.constraint_type = 'P' \n"
                + "                                AND col.CONSTRAINT_NAME=pk.CONSTRAINT_NAME\n"
                + "                    ) THEN 'PK' \n"
                + "               ELSE  \n"
                + "                   CASE WHEN column_name IN(SELECT col.COLUMN_NAME \n"
                + "                         FROM all_cons_columns col \n"
                + "                         INNER JOIN all_constraints pk ON col.TABLE_NAME=pk.TABLE_NAME \n"
                + "                          WHERE col.table_name=tab.TABLE_NAME\n"
                + "                                AND pk.constraint_type = 'R' \n"
                + "                                AND col.CONSTRAINT_NAME=pk.CONSTRAINT_NAME\n"
                + "                    ) THEN 'FK' \n"
                + "                    ELSE\n"
                + "                      CASE WHEN nullable='Y' THEN ' ' ELSE 'NN' END END\n"
                + "                    \n"
                + "        END constra--nullable\n"
                + "--       ,char_col_decl_length\n"
                + "--       ,char_length \n"
                + "--, nullable\n"
                + "from all_tab_columns tab \n"
                + "where UPPER(table_name) = '" + obj.getNombre_corto().toUpperCase() + "' ORDER BY column_id";

        String data[] = conOracle.traeArrayX(sql, t);
        lista = new ArrayList();
        TablaSQL file;
        for (int i = 0; i < data.length; i++) {
            String valores[] = data[i].split("__");
            file = new TablaSQL();
            file.setIdReq("" + obj.getId());
            file.setNombre(valores[0].trim());
            file.setTipo(valores[1].trim());
            file.setTamano(valores[2].trim());
            file.setConstraint(valores[3].trim());
            lista.add(file);
        }

        return lista;

    }

    public List<TablaSQL> obtenerEstructuraTabla(Requerimientos obj) {

        List<TablaSQL> lista = null;

        String sql = "SELECT [id]\n"
                + "      ,[idReq]\n"
                + "      ,[nombre_campo]\n"
                + "      ,[tipo_campo]\n"
                + "      ,[tamano_campo]\n"
                + "      ,[contraint_campo]\n"
                + "  from [SGR].[dbo].[GP_REQ_TABLA] where idReq='" + obj.getId() + "' order by id";

        try {

            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);
            ResultSet rs;
            rs = st.executeQuery();

            lista = new ArrayList();
            TablaSQL file;
            while (rs.next()) {
                file = new TablaSQL();
                file.setId_campo(rs.getString("id"));
                file.setIdReq(rs.getString("idReq"));
                file.setNombre(rs.getString("nombre_campo"));
                file.setTipo(rs.getString("tipo_campo"));
                file.setTamano(rs.getString("tamano_campo"));
                file.setConstraint(rs.getString("contraint_campo"));
                lista.add(file);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println("error sql " + e + " " + sql);
        } finally {
            this.Cerrar();
        }
        return lista;

    }

    public List<Daily> listarDiario(String c_desarrollador) {

        List<Daily> lista = null;

        String sql = "SELECT [id]\n"
                + "	 ,CONVERT(varchar,[fecha],103) fechax \n"
                // + "      ,[fecha]\n"
                + "      ,[c_desarrollador]\n"
                + "     ,(select nombre from [SGR].[dbo].Empleados where Empleado_Id=D.[c_desarrollador]) nombre"
                + "      ,[detalle]\n"
                + "  FROM [SGR].[dbo].[GP_DAILY] D\n"
                + "  where \n"
                + "  [c_desarrollador]='" + c_desarrollador + "'   and \n"
                + "  fecha>=DATEADD(DAY,-30, GETDATE()) \n"
                + "  order by fecha desc; ";

        try {

            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);
            ResultSet rs;
            rs = st.executeQuery();

            lista = new ArrayList();
            Daily file;
            while (rs.next()) {
                file = new Daily();
                file.setId(rs.getInt("id"));
                file.setFecha(rs.getString("fechax"));
                file.setC_desarrolador(rs.getString("c_desarrollador"));
                file.setD_desarrolador(rs.getString("nombre"));
                file.setDetalle(rs.getString("detalle"));
                lista.add(file);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println("error sql " + e + " " + sql);
        } finally {
            this.Cerrar();
        }
        return lista;

    }


    public void insertarFinalizacion(Aceptacion obj) {

        String existe = "select count(*) from [dbo].[GP_REQUERIMIENTO] where ID='" + obj.getId() + "';";
        existe = m.obtenerString(existe);
        if (existe.trim().equalsIgnoreCase("0")) {

            String aplicativo = "";
            for (int i = 0; i < obj.getAplicativo().length; i++) {
                String tm[] = obj.getAplicativo();
                aplicativo = aplicativo + " " + tm[i];
            }
            String aplicativo_primario = "";
            for (int i = 0; i < obj.getAplicativo_primario().length; i++) {
                String tm[] = obj.getAplicativo_primario();
                aplicativo_primario = aplicativo_primario + " " + tm[i];
            }
            String tipo_archivo = "";
            for (int i = 0; i < obj.getTipo_archivo().length; i++) {
                String tm[] = obj.getTipo_archivo();
                tipo_archivo = tipo_archivo + " " + tm[i];
            }
            String origen_falla = "";
            for (int i = 0; i < obj.getOrigen_problema().length; i++) {
                String tm[] = obj.getOrigen_problema();
                origen_falla = origen_falla + " " + tm[i];
            }

            String sql = "INSERT INTO [dbo].[GP_FINALIZACION]\n"
                    + "           (\n"
                    + "		    [id_requerimiento]\n"
                    + "           ,[fecha_completado]\n"
                    + "           ,[c_agencia]\n"
                    + "           ,[c_usuario]\n"
                    + "           ,[descripcion_causa]\n"
                    + "           ,[descripcion_solucion]\n"
                    + "           ,[aplicativo]\n"
                    + "           ,[aplicativo_primario]\n"
                    + "           ,[tipo_archivo]\n"
                    + "           ,[origen_problema]\n"
                    + "           ,[especifique_otro]\n"
                    + "           ,[especifique_otro_primario]\n"
                    + "           ,[especifique_otro_tipo_archivo]\n"
                    + "           ,[especifique_otro_origen_falla]\n"
                    + "           ,[ubicacion_archivo]\n"
                    + "           ,[persona_pruebas]\n"
                    + "           ,[duracion_pruebas]\n"
                    + "           ,[c_area]\n"
                    + "           ,[c_developer]\n"
                    + "           ,[estado]\n"
                    + "		   )\n"
                    + "     VALUES\n"
                    + "           (\n"
                    + "		    '" + obj.getId_req_padre() + "'\n"
                    + "           , GETDATE()\n"
                    + "           ,'" + obj.getC_agencia() + "'\n"
                    + "           ,'" + obj.getC_usuario() + "'\n"
                    + "           ,'" + obj.getDescripcion_causa() + "'\n"
                    + "           ,'" + obj.getDescripcion_solucion() + "'\n"
                    + "           ,'" + aplicativo + "'\n"
                    + "           ,'" + aplicativo_primario + "'\n"
                    + "           ,'" + tipo_archivo + "'\n"
                    + "           ,'" + origen_falla + "'\n"
                    + "           ,'" + obj.getEspecifique_otro() + "'\n"
                    + "           ,'" + obj.getEspecifique_otro_primario() + "'\n"
                    + "           ,'" + obj.getEspecifique_otro_tipo_archivo() + "'\n"
                    + "           ,'" + obj.getEspecifique_otro_origen() + "'\n"
                    + "           ,'" + obj.getUbicacion_archivo() + "'\n"
                    + "           ,'" + obj.getPersona_pruebas() + "'\n"
                    + "           ,'" + obj.getDuraccion_pruebas() + "'\n"
                    + "           ,'" + obj.getC_area() + "'\n"
                    + "           ,'" + obj.getC_developer() + "'\n"
                    + "           ,'" + Constantes.estado_completado + "'\n"
                    + "		   )";
            try {
                this.Conectar();
                PreparedStatement st = this.getCon().prepareStatement(sql);

                ///  st.setString(1, cat.getDETALLE());
                st.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
                ////RequestContext context = RequestContext.getCurrentInstance();
                PrimeFaces.current().executeScript("PF('wdlCompletado').hide();");

                st.close();
                /*try {
                obj.setId(Integer.parseInt(obtenerUltimaSec(obj)));
                insertarDoc(obj);
            } catch (Exception e) {
                System.out.println("Error obtienendo ultimo ID para vincular Documentos\n" + e);
            }*/

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + e));
                System.err.println("Error al insertar " + e + "\n" + sql);

            } finally {
                this.Cerrar();

            }

        } else {
            System.err.println("ya existe ");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Esta Ayuda ya esta Guardada"));
        }

    }

    public void insertarDoc(Requerimientos obj) {
        if (obj.getLstDocument().isEmpty()) {
            return;
        }

        this.Conectar();
        PreparedStatement st;
        MyFile tmp;
        for (int i = 0; i < obj.getLstDocument().size(); i++) {

            tmp = obj.getLstDocument().get(i);

            if (!tmp.isExiste()) {
                String sql = "INSERT INTO [dbo].[GP_DOCUMENTOS]\n"
                        + "           ([ID_REQUERIMIENTO]\n"
                        + "           ,[NOMBRE]\n"
                        + "           ,[RAIZ]\n"
                        + "           ,[FOLDER]\n"
                        + "           ,[ESTADO])\n"
                        + "     VALUES\n"
                        + "           ('" + obj.getId() + "'\n"
                        + "           ,'" + tmp.getNombre() + "'\n"
                        + "           ,'" + tmp.getRuta_raiz() + "'\n"
                        + "           ,'" + tmp.getRuta_folder() + "'\n"
                        + "           ,'1')";

                try {

                    st = this.getCon().prepareStatement(sql);
                    st.executeUpdate();
                    st.close();

                } catch (Exception e) {
                    System.out.println("Error al vincular documento " + e);
                }
            } else {
                String sql = "UPDATE [SGR].[dbo].[GP_DOCUMENTOS] SET\n"
                        + "		ESTADO='" + tmp.getEstado() + "'\n"
                        + "WHERE ID_DOC='" + tmp.getId_doc() + "';";

                try {

                    st = this.getCon().prepareStatement(sql);
                    st.executeUpdate();
                    st.close();

                } catch (Exception e) {
                    System.out.println("Error al vincular documento " + e);
                }

            }

        }
        this.Cerrar();

    }

    public void insertarDiario(Daily obj) {

        String existe = m.obtenerString("Select id from [dbo].[GP_DAILY] "
                + " where c_desarrollador='" + obj.getC_desarrolador() + "' "
                + " and CONVERT(varchar,fecha,103)=CONVERT(varchar,GETDATE(),103) ");
        String sql = "";
        if (existe.equalsIgnoreCase("0")) {
            sql = "INSERT INTO [dbo].[GP_DAILY]\n"
                    + "           (\n"
                    + "		   [fecha]\n"
                    + "           ,[c_desarrollador]\n"
                    + "           ,[detalle]\n"
                    + "		   )\n"
                    + "     VALUES\n"
                    + "           (\n"
                    + "		    GETDATE()\n"
                    + "           ,'" + obj.getC_desarrolador() + "'\n"
                    + "           ,'<br><pre class=\"ql-syntax\" spellcheck=\"false\">ENVIADO "+sdf_f.format(new Date())+" a las " + sdf_h.format(new Date()) + "  </pre><br>" + obj.getDetalle() + "'\n"
                    + "		   )";

        } else {

            sql = "UPDATE [dbo].[GP_DAILY]\n"
                    + "   SET \n"
                    + "      [detalle] = detalle + '<br><pre class=\"ql-syntax\" spellcheck=\"false\">SIGUIENTE ENTRADA "+sdf_f.format(new Date())+" a las " + sdf_h.format(new Date()) + "  </pre><br>" + obj.getDetalle() + "'\n"
                    + " WHERE id='" + existe + "'";
        }
        //System.out.println("SQL UPDATe" + sql);

        try {

            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);

            st.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
            //////RequestContext context = RequestContext.getCurrentInstance();
            //PrimeFaces.current().executeScript("PF('wdlRequerimiento').hide();PF('wdlCaso').hide();PF('wdlEjecutaSQL').hide();PF('wdlPermiso').hide();");

            st.close();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al Guardar" + e));
            System.err.println("Error al guardar " + e + "" + sql);

        } finally {
            this.Cerrar();
        }

    }

    public void modificarReq_Caso(Requerimientos obj) {

        try {
            String sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                    + "   SET \n"
                    + "       [fecha_completado] = GETDATE()\n"
                    + "      ,[estado] = '" + obj.getEstado() + "'\n"
                    + " WHERE ID='" + obj.getId() + "'";
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);

            st.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
            ////RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlRequerimiento').hide();PF('wdlCaso').hide();PF('wdlEjecutaSQL').hide();PF('wdlPermiso').hide();");

            st.close();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
            //System.err.println("Error al modificar " + e);

        } finally {
            this.Cerrar();
        }

    }

    public void modificarCasoAceptar(Requerimientos obj) {

        String sql1 = "Select count(*) from [dbo].[GP_REQUERIMIENTO] where id='" + obj.getId() + "' AND estado='" + Constantes.estado_en_desarrollo + "'";
        String existe = m.obtenerString(sql1);

        if (!existe.trim().equalsIgnoreCase("0")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Este caso ya fue tomado por otro Desarrollador"));
            ////RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlCaso').hide();");
            return;
        }
        try {
            String sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                    + "   SET \n"
                    + "       [fecha_asigno_desarrollo] = GETDATE()\n"
                    + "      ,[c_desarrollador] = '" + obj.getC_developer() + "' \n"
                    + "      ,[estado] = '" + obj.getEstado() + "'\n"
                    + " WHERE ID='" + obj.getId() + "'";
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);

            st.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
            ////RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlCaso').hide();");

            st.close();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
            //System.err.println("Error al modificar " + e);

        } finally {
            this.Cerrar();
        }
    }

    
    public void modificarCasoRechazar(Requerimientos obj) {

   
        try {
            String sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                    + "   SET \n"
                    + "       [fecha_rechazado] = GETDATE()\n"
                    + "      ,[c_desarrollador] = '" + obj.getC_developer() + "' \n"
                    + "      ,[estado] = '" + obj.getEstado() + "'\n"
                    + " WHERE ID='" + obj.getId() + "'";
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);

            st.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
            ////RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlCaso').hide();");

            st.close();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
            //System.err.println("Error al modificar " + e);

        } finally {
            this.Cerrar();
        }
    }
    
    
    public void modificarCaso(Requerimientos obj) {

        //Valida estado antes de modificar
        String existe = "select count(*) from [dbo].[GP_REQUERIMIENTO] where ID='" + obj.getId() + "' AND fecha_asigno_desarrollo IS NOT NULL;";
        existe = m.obtenerString(existe);
        if (existe.trim().equalsIgnoreCase("0")) {
            try {
                String aplicativo = "";
                for (int i = 0; i < obj.getAplicativo().length; i++) {
                    String tm[] = obj.getAplicativo();
                    aplicativo = aplicativo + " " + tm[i];
                }

                String sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                        + "   SET   \n"
                        + "          [aplicativo]='" + aplicativo + "'\n"
                        + "         ,[especifique_otros]='" + obj.getEspecifique_otro() + "'\n"
                        + "         ,[descripcion]='" + obj.getDescripcion() + "'\n"
                        + "         ,[nombre]='" + obj.getNombre_corto() + "'\n"
                        + " WHERE ID='" + obj.getId() + "'";
                this.Conectar();
                PreparedStatement st = this.getCon().prepareStatement(sql);

                st.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
                ////RequestContext context = RequestContext.getCurrentInstance();
                PrimeFaces.current().executeScript("PF('wdlCaso').hide();");

                st.close();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
                //System.err.println("Error al modificar " + e);

            } finally {
                this.Cerrar();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "AVISO", "REQUERIMIENTO YA ESTA AUTORIZADO"));
            ////RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlCaso').hide();");
        }
        try {
            insertarDoc(obj);

        } catch (Exception e) {
            System.out.println("Error obtienendo ultimo ID para vincular Documentos\n" + e);
        }

    }

}
