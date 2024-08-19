/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.dao;

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
public class AdmDBADAO extends DAO {

    //@Resource(mappedName = "jdbc/credisol_base")
    //@Resource(mappedName = "jdbc/datasource_credisol")
    //private DataSource ds;
    private MetodosSQL m = new MetodosSQL();
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
                + "  FROM [SGR].[dbo].[GP_REQUERIMIENTO] R \n"
                + "  inner join [SGR].[dbo].Empleados E on R.c_usuario_sol=E.Empleado_Id \n"
                + "  left join [SGR].[dbo].Empleados E2 on R.c_desarrollador=E2.Empleado_Id where fecha_rechazado is null and tipo_registro='R' and  c_desarrollador='" + user + "'";

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
                + "  FROM [SGR].[dbo].[GP_REQUERIMIENTO] R \n"
                + "  inner join [SGR].[dbo].Empleados E on R.c_usuario_sol=E.Empleado_Id \n"
                + "  left join [SGR].[dbo].Empleados E2 on R.c_desarrollador=E2.Empleado_Id  where  fecha_completado is null and fecha_rechazado is null and tipo_registro='C' ";

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
                    System.out.println("Error obteniendo aplicativo.." + e);
                }
                obj.setEspecifique_otro(rs.getString("especifique_otros"));
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
                + "  FROM [SGR].[dbo].[GP_REQUERIMIENTO] R \n"
                + "  inner join [SGR].[dbo].Empleados E on R.c_usuario_sol=E.Empleado_Id \n"
                + "  left join [SGR].[dbo].Empleados E2 on R.c_desarrollador=E2.Empleado_Id where id='"+id+"'  ";

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
                
                try {
                    obj.setAplicativo(rs.getString("aplicativo").split(" "));
                } catch (Exception e) {
                    //System.out.println("Error obteniendo aplicativo.." + e);
                }
                obj.setPrioridad(rs.getString("prioridad"));
                obj.setNuevo(rs.getString("nuevo"));
                obj.setCircular(rs.getString("circular"));
                
                obj.setEspecifique_otro(rs.getString("especifique_otros"));
                obj.setC_area(rs.getString("c_area"));
                obj.setC_agencia(rs.getString("c_agencia"));
                obj.setD_agencia(rs.getString("d_agencia").replaceAll("AGENCIA ", ""));

                obj.setEstado(rs.getString("estado"));

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

    public List<Requerimientos> listarAyudas(String where) {
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
                + "      ,ISNULL(CONVERT(varchar,[fecha_inicio_permiso],103)+' '+CONVERT(varchar,[fecha_inicio_permiso],108),'') [fecha_inicio_permiso]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_fin_permiso],103)+' '+CONVERT(varchar,[fecha_fin_permiso],108),'') [fecha_fin_permiso]\n"
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
                + "  left join [SGR].[dbo].Empleados E2 on R.c_desarrollador=E2.Empleado_Id  where  fecha_completado is null and fecha_rechazado is null and R.estado in('"+Constantes.estado_autorizado_ti+"','"+Constantes.estado_en_dba+"','"+Constantes.estado_ini_permiso+"')  "+where;

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
                obj.setFecha_inicio_permiso(rs.getString("fecha_inicio_permiso"));
                //obj.setFecha_fin_permiso(rs.getString("fecha_fin_permiso"));
                //obj.setC_developer(rs.getString("c_desarrollador"));
                //obj.setD_developer(rs.getString("d_desarrollador"));
                obj.setC_dba(rs.getString("c_dba"));
                obj.setD_dba(rs.getString("d_dba"));
                obj.setC_usuario(rs.getString("c_usuario_sol"));
                obj.setD_usuario(rs.getString("d_usuario"));

                try {
                    if(obj.getFecha_autorizado().trim().equalsIgnoreCase("1900-01-01 00:00:00"))
                    {
                        obj.setFecha_autorizado(obj.getFecha());
                    }
                } catch (Exception e) {
                }
                
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

                
                      obj.setTarea(rs.getString("est_tarea"));
                obj.setObjetoTarea(rs.getString("est_objeto_tarea"));
                obj.setComentario(rs.getString("est_comentario"));
                obj.setNotas(rs.getString("est_notas"));
                obj.setScriptSQL(rs.getString("scriptSQL"));
                try {
                    obj.setActividades_usuarios(rs.getString("est_actividades_usuarios").split(" "));
                } catch (Exception e) {
                    //System.out.println("aplcat " + e);
                }
                try {
                     
                    //String tmpStr=rs.getString("est_des_usuario_utilizan");
                    //String arr1[]=tmpStr.split(" ");
                    obj.setDes_usuarios_utilizan(Arrays.asList (rs.getString("est_des_usuario_utilizan").split(" ")));
                    
                } catch (Exception e) {
                    //System.out.println("aplcat " + e);
                }

                
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
    
    public List<Requerimientos> listarAyudasHistorial(String where) {
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
                + "      ,ISNULL(CONVERT(varchar,[fecha_inicio_permiso],103)+' '+CONVERT(varchar,[fecha_inicio_permiso],108),'') [fecha_inicio_permiso]\n"
                + "      ,ISNULL(CONVERT(varchar,[fecha_fin_permiso],103)+' '+CONVERT(varchar,[fecha_fin_permiso],108),'') [fecha_fin_permiso]\n"
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
                 + "      ,[id_req_padre]\n"
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
                + "  left join [SGR].[dbo].Empleados E2 on R.c_desarrollador=E2.Empleado_Id Where 1=1  "+where;

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
                obj.setFecha_inicio_permiso(rs.getString("fecha_inicio_permiso"));
                //obj.setFecha_fin_permiso(rs.getString("fecha_fin_permiso"));
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

                
                 obj.setId_req_padre(rs.getString("id_req_padre"));
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
    public void completar_Envio(Requerimientos obj) {

        //Valida estado antes de modificar
        
          
        
            try {
                String sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                        + "   SET \n"
                        + "      [estado] = '" + obj.getEstado() + "'\n"
                    //    + "      ,[c_dba] = '" + obj.getC_dba()+ "'\n"
                        + "      ,[tipo_registro] = '" + obj.getTipo_registro()+ "'\n"
                        + "      ,[scriptSQLAnterior] = ?\n"
                        + " WHERE ID='" + obj.getId() + "'";
                this.Conectar();
                PreparedStatement st = this.getCon().prepareStatement(sql);

                st.setString(1, obj.getScriptSQLAnterior());
                
                st.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
                //RequestContext context = RequestContext.getCurrentInstance();
                PrimeFaces.current().executeScript("PF('wdlEnvia').hide();");

                st.close();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
                //System.err.println("Error al modificar " + e);

            } finally {
                this.Cerrar();
            }
    

    }
    public void completar_ayuda(Requerimientos obj) {

        //Valida estado antes de modificar
        
            String fecha="";
            if(obj.getEstado().equalsIgnoreCase("Rechazado"))
                fecha= "       [fecha_rechazado] = GETDATE() \n";
            else
                fecha= "       [fecha_inicio_permiso] = GETDATE() \n";
            
        
            try {
                String sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                        + "   SET \n"
                        +         fecha
                        + "      ,[estado] = '" + obj.getEstado() + "'\n"
                        + "      ,[c_dba] = '" + obj.getC_dba()+ "'\n"
                        + " WHERE ID='" + obj.getId() + "'";
                this.Conectar();
                PreparedStatement st = this.getCon().prepareStatement(sql);

                st.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
                //RequestContext context = RequestContext.getCurrentInstance();
                PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').hide();PF('wdlPermiso').hide();PF('wdlCargaProce').hide();PF('wdlEnvia').hide();");

                st.close();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
                //System.err.println("Error al modificar " + e);

            } finally {
                this.Cerrar();
            }
    

    }
        
        public void completar_ayuda_permiso(Requerimientos obj) {

        //Valida estado antes de modificar
        
            try {
                String sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                        + "   SET \n"
                        + "       [fecha_fin_permiso] = GETDATE()   "
                        + "      ,[estado] = '" + obj.getEstado() + "'\n"
                        + "      ,[c_dba] = '" + obj.getC_dba()+ "'\n"
                        + " WHERE ID='" + obj.getId() + "'";
                this.Conectar();
                PreparedStatement st = this.getCon().prepareStatement(sql);

                st.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
                //RequestContext context = RequestContext.getCurrentInstance();
                PrimeFaces.current().executeScript("PF('wdlPermiso').hide();");

                st.close();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
                //System.err.println("Error al modificar " + e);

            } finally {
                this.Cerrar();
            }
    

    }    

    public void aceptar_ayuda(Requerimientos obj) {

        //Valida estado antes de modificar
        
            try {
                String sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                        + "   SET \n"
                        + "      [estado] = '" + obj.getEstado() + "'\n"
                        + " WHERE ID='" + obj.getId() + "'";
                this.Conectar();
                PreparedStatement st = this.getCon().prepareStatement(sql);

                st.executeUpdate();

                //RequestContext context = RequestContext.getCurrentInstance();
                
                st.close();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al actualizar" + e));
                //System.err.println("Error al modificar " + e);

            } finally {
                this.Cerrar();
            }
    

    }


    public void insertarCasoEjecucion(Requerimientos obj) {

        String existe = "select count(*) from [dbo].[GP_REQUERIMIENTO] where ID='" + obj.getId() + "';";
        System.err.println("sql "+existe );
        if (existe.trim().equalsIgnoreCase("0")) {
            String tipocomando = "";
            for (int i = 0; i < obj.getTipo_comando().length; i++) {
                String tm[] = obj.getTipo_comando();
                tipocomando = tipocomando + " " + tm[i];
            }

            String sql = "INSERT INTO [dbo].[GP_REQUERIMIENTO]\n"
                    + "           ([fecha_creado]\n"
                    + "           ,[c_usuario_sol]\n"
                    + "           ,[c_agencia]\n"
                    + "	      ,[tipo_comando]\n"
                    + "           ,[ambiente]\n"
                    + "           ,[descripcion]\n"
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
                    + "           ,'" + obj.getC_agencia() + "'\n"
                    + "	      ,'" + tipocomando.trim() + "'\n"
                    + "           ,'" + obj.getAmbiente() + "'\n"
                    + "           ,'" + obj.getDescripcion() + "'\n"
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
                //RequestContext context = RequestContext.getCurrentInstance();
                PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').hide();");

                st.close();
                /* try {
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
            System.err.println("ya existe " );
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
                    + "           ,[c_agencia]\n"
                    + "	      ,[tipo_comando]\n"
                    + "           ,[ambiente]\n"
                    + "           ,[descripcion]\n"
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
                    + "           ,'" + obj.getC_agencia() + "'\n"
                    + "	      ,'" + tipopermiso.trim() + "'\n"
                    + "           ,'" + obj.getAmbiente() + "'\n"
                    + "           ,'" + obj.getDescripcion() + "'\n"
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
                //RequestContext context = RequestContext.getCurrentInstance();
                PrimeFaces.current().executeScript("PF('wdlPermiso').hide();");

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

        }else{
             System.err.println("ya existe " );
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
            //RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlRequerimiento').hide();PF('wdlCaso').hide();");

            st.close();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
            //System.err.println("Error al modificar " + e);

        } finally {
            this.Cerrar();
        }

    }

    public void modificarCasoAceptar(Requerimientos obj) {

        String existe = m.ejecutar("Select count(*) from [dbo].[GP_REQUERIMIENTO] where id='" + obj.getId() + "' AND estado='" + Constantes.estado_completado + "'");

        if (!existe.trim().equalsIgnoreCase("0")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Este caso ya fue tomado por otro Desarrollador"));
            //RequestContext context = RequestContext.getCurrentInstance();
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
            //RequestContext context = RequestContext.getCurrentInstance();
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
                //RequestContext context = RequestContext.getCurrentInstance();
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
            //RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlCaso').hide();");
        }
        try {
            insertarDoc(obj);

        } catch (Exception e) {
            System.out.println("Error obtienendo ultimo ID para vincular Documentos\n" + e);
        }

    }
    
    
    
    public List<TablaSQL> obtenerEstructuraTabla(Requerimientos obj) {

        List<TablaSQL> lista = null;

        String sql = "SELECT [id]\n"
                + "      ,[idReq]\n"
                + "      ,[nombre_campo]\n"
                + "      ,[tipo_campo]\n"
                + "      ,[tamano_campo]\n"
                + "      ,[contraint_campo]\n"
                + "  from [SGR].[dbo].[GP_REQ_TABLA] where idReq='"+obj.getId()+"' order by id";

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
            System.out.println("error sql "+e+" "+sql);
        }finally{
            this.Cerrar();
        }
        return lista;

    }
    

}
