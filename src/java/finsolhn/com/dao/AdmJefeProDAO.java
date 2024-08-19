/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.dao;

import finsolhn.com.model.Aceptacion;
import finsolhn.com.model.Daily;
import finsolhn.com.model.Empleado;
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
public class AdmJefeProDAO extends DAO {

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

    public List<Requerimientos> listarRequerimientos(String where,String tip) {
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
                + "  where  tipo_registro='"+tip+"' "+where;

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
                   // System.out.println("aplcat " + e);
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
                + "  left join [SGR].[dbo].Empleados E2 on R.c_desarrollador=E2.Empleado_Id  where R.tipo_registro in('E','P','A') AND R.estado='"+Constantes.estado_sin_autortizar+"'  "+where;
        
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

    public List<Aceptacion> listarCompletados(String where) {
        List<Aceptacion> lista = null;

        ResultSet rs;

        String sql = "SELECT [id_aceptado]\n"
                + "      ,[id_requerimiento]  \n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha],103)+' '+CONVERT(varchar,[fecha],108),'1900-01-01 00:00:00') [fecha]\n"
                + "	  ,ISNULL(CONVERT(varchar,[fecha_completado],103)+' '+CONVERT(varchar,[fecha_completado],108),'1900-01-01 00:00:00') [fecha_completado]\n"
                + "      ,[c_usuario]\n"
                + "	  ,E.Nombre as d_usuario\n"
                + "      ,[descripcion_causa]\n"
                + "      ,[descripcion_solucion]\n"
                + "      ,[observacion_pruebas]\n"
                + "      ,[aplicativo]\n"
                + "      ,[aplicativo_primario]\n"
                + "      ,[tipo_archivo]\n"
                + "      ,[origen_problema]\n"
                + "      ,[especifique_otro]\n"
                + "      ,[especifique_otro_primario]\n"
                + "      ,[especifique_otro_tipo_archivo]\n"
                + "      ,[especifique_otro_origen_falla]\n"
                + "      ,[ubicacion_archivo]\n"
                + "      ,[persona_pruebas]\n"
                + "      ,[duracion_pruebas]\n"
                + "      ,[resolucion]\n"
                + "      ,[c_area]\n"
                + "      ,[c_developer]\n"
                + "	  ,E2.Nombre as d_developer\n"
                + "      ,F.[estado]\n"
                +",[descripcion_acepto]\n" 
                +",[descripcion_rechazo]\n" 
                +",[descripcion_reformula]"
                + "      ,(select nombre from SGR.dbo.GP_REQUERIMIENTO where id=F.id_requerimiento) nombre_req \n"
                + "  FROM [SGR].[dbo].[GP_FINALIZACION] F  \n"
                + "  inner join [SGR].[dbo].Empleados E on F.c_usuario=E.Empleado_Id \n"
                + "  left join [SGR].[dbo].Empleados E2 on F.[c_developer]=E2.Empleado_Id \n"
                + "  where F.Estado='" + Constantes.estado_aceptado + "'";
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            Aceptacion obj;
            while (rs.next()) {
                obj = new Aceptacion();
                obj.setId(rs.getInt("id_aceptado"));
                obj.setId_req_padre(rs.getString("id_requerimiento"));
                obj.setFecha(rs.getString("fecha"));
                obj.setFecha_completado(rs.getString("fecha_completado"));
                obj.setC_usuario(rs.getString("c_usuario"));
                obj.setD_usuario(rs.getString("d_usuario"));

                obj.setDescripcion_causa(rs.getString("descripcion_causa"));
                obj.setDescripcion_solucion(rs.getString("descripcion_solucion"));
                obj.setObservacion_pruebas(rs.getString("observacion_pruebas"));

                try {
                    obj.setAplicativo(rs.getString("aplicativo").split(" "));
                } catch (Exception e) {
                    System.out.println("aplcat " + e);
                }
                try {
                    obj.setAplicativo_primario(rs.getString("aplicativo_primario").split(" "));
                } catch (Exception e) {
                    System.out.println("aplica2 " + e);

                }
                try {
                    obj.setTipo_archivo(rs.getString("tipo_archivo").split(" "));
                } catch (Exception e) {
                    System.out.println("tipo ar " + e);

                }
                try {
                    obj.setOrigen_problema(rs.getString("origen_problema").split(" "));
                } catch (Exception e) {
                    System.out.println("origen p " + e);

                }

                obj.setEspecifique_otro(rs.getString("especifique_otro"));
                obj.setEspecifique_otro_primario(rs.getString("especifique_otro_primario"));
                obj.setEspecifique_otro_tipo_archivo(rs.getString("especifique_otro_tipo_archivo"));
                obj.setEspecifique_otro_origen(rs.getString("especifique_otro_origen_falla"));
                obj.setUbicacion_archivo(rs.getString("ubicacion_archivo"));
                obj.setPersona_pruebas(rs.getString("persona_pruebas"));
                obj.setDuraccion_pruebas(rs.getString("duracion_pruebas"));
                obj.setResolucion(rs.getString("resolucion"));
                obj.setC_area(rs.getString("c_area"));
                obj.setC_developer(rs.getString("c_developer"));
                obj.setD_developer(rs.getString("d_developer"));
                obj.setEstado(rs.getString("estado"));
                obj.setDescripcion_acepto(rs.getString("descripcion_acepto"));
                obj.setDescripcion_rechazo(rs.getString("descripcion_rechazo"));
                obj.setDescripcion_refurmulo(rs.getString("descripcion_reformula"));
                obj.setNombre(rs.getString("nombre_req"));
                lista.add(obj);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error listas completados" + e);

        } finally {
            this.Cerrar();
        }

        return lista;
    }

    
    public List<Requerimientos> listarDocumentacion(Aceptacion objA) {
        List<Requerimientos> lista = null;

        ResultSet rs;

        String sql = "SELECT [id]\n"
                + "	  ,[id_req_padre]\n"
                + "      ,[nombre]      \n"
                + "      ,[tipo_registro]\n"
                + "      ,[estado]\n"
                + "  FROM [SGR].[dbo].[GP_REQUERIMIENTO]\n"
                + "  where id='"+objA.getId_req_padre()+"' or id_req_padre='"+objA.getId_req_padre()+"' and fecha_rechazado is null";

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
    
        
    public List<Requerimientos> listarDocumentacionRequerimiento(Requerimientos objA) {
        List<Requerimientos> lista = null;

        ResultSet rs;

        String sql = "SELECT [id]\n"
                + "	  ,[id_req_padre]\n"
                + "      ,[nombre]      \n"
                + "      ,[tipo_registro]\n"
                + "      ,[estado]\n"
                + "  FROM [SGR].[dbo].[GP_REQUERIMIENTO]\n"
                + "  where id_req_padre='"+objA.getId()+"' and fecha_rechazado is null";

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
    
    public void req_finalizado(Requerimientos req, Aceptacion ace) {

        //Valida estado antes de modificar
        String fecha = "";
        if (req.getEstado().equalsIgnoreCase("Rechazado")) {
            fecha = "       [fecha_rechazado] = GETDATE() \n";
        } else {
            fecha = "       [fecha_finalizado] = GETDATE() \n";
        }

        try {
            String sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                    + "   SET \n"
                    + fecha
                    + "      ,[estado] = '" + req.getEstado() + "'\n"
                    + "      ,[c_usuario_jefe_p] = '" + req.getC_usuario_jefe()+ "'\n"
                    + " WHERE ID='" + req.getId() + "'";
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);

            st.executeUpdate();
            
            sql = "UPDATE [dbo].[GP_FINALIZACION]\n"
                    + "   SET  \n"
                    + "       [estado] = '" + ace.getEstado() + "'\n"
                    + "      ,[fecha_finalizado] = GETDATE() \n"
                    + "      ,[c_usuario_jefe] = '" + ace.getC_usuario_jefe() + "'\n"
                    + " WHERE id_aceptado='" + ace.getId() + "'";
            this.Conectar();
            st = this.getCon().prepareStatement(sql);

            st.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('wdlEjecutaSQL').hide();PF('wdlPermiso').hide();PF('wdlCompletado').hide();");
            PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').hide();PF('wdlPermiso').hide();PF('wdlCompletado').hide();");

            st.close();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
            //System.err.println("Error al modificar " + e);

        } finally {
            this.Cerrar();
        }

    }

    public List<Empleado> listarDeveloper() {

        List<Empleado> lista = null;

        ResultSet rs;

        String sql = "SELECT [Empleado_Id]\n"
                + "      ,[Nombre]\n"
                + "  FROM [SGR].[dbo].[Empleados]\n"
                + "  where Puesto_Id = 5 and Estado='A'";

        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            Empleado emp;
            while (rs.next()) {
                emp = new Empleado();
                emp.setId_empleado(rs.getString("Empleado_Id"));
                emp.setNombre(rs.getString("Nombre"));
                lista.add(emp);

            }
            rs.close();
            st.close();
            //System.out.println("Total res:" + c);
        } catch (Exception e) {
            System.err.println("error developer " + e);

        } finally {
            this.Cerrar();
        }

        return lista;

    }
    public void autorizacion_req(Requerimientos obj) {

        //Valida estado antes de modificar
        
            String fecha="";
            if(obj.getEstado().equalsIgnoreCase("Rechazado"))
                fecha= "       [fecha_rechazado] = GETDATE() \n";
            else
                fecha= "       [fecha_autorizado] = GETDATE() \n";
            
        
            try {
                String sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                        + "   SET \n"
                        +         fecha
                        + "      ,[prioridad] = '" + obj.getPrioridad() + "'\n"
                        + "      ,[nuevo] = '" + obj.getNuevo() + "'\n"
                        + "      ,[descripcion] = '" + obj.getDescripcion() + "'\n"
                        + "      ,[nombre] = '" + obj.getNombre_corto() + "'\n"
                        + "      ,[estado] = '" + obj.getEstado() + "'\n"
                        + "      ,[c_usuario_jefe_p] = '" + obj.getC_usuario_jefe()+ "'\n"
                        + " WHERE ID='" + obj.getId() + "'";
                this.Conectar();
                PreparedStatement st = this.getCon().prepareStatement(sql);

                st.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
                //RequestContext context = RequestContext.getCurrentInstance();
                //context.execute("PF('wdlRequerimiento').hide();");
                PrimeFaces.current().executeScript("PF('wdlRequerimiento').hide();");
                st.close();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
                //System.err.println("Error al modificar " + e);

            } finally {
                this.Cerrar();
            }
    

    }
    public void autorizacion_ayuda(Requerimientos obj) {

        //Valida estado antes de modificar
        
            String fecha="";
            if(obj.getEstado().equalsIgnoreCase("Rechazado"))
                fecha= "       [fecha_rechazado] = GETDATE() \n";
            else
                fecha= "       [fecha_autorizado] = GETDATE() \n";
            
        
            try {
                String sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                        + "   SET \n"
                        +         fecha
                        + "      ,[estado] = '" + obj.getEstado() + "'\n"
                        + "      ,[c_usuario_jefe_p] = '" + obj.getC_usuario_jefe()+ "'\n"
                        + " WHERE ID='" + obj.getId() + "'";
                this.Conectar();
                PreparedStatement st = this.getCon().prepareStatement(sql);

                st.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
                //RequestContext context = RequestContext.getCurrentInstance();
                //context.execute("PF('wdlEjecutaSQL').hide();PF('wdlPermiso').hide();PF('wdlCargaProce').hide();");
                PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').hide();PF('wdlPermiso').hide();PF('wdlCargaProce').hide();");
                
                st.close();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
                //System.err.println("Error al modificar " + e);

            } finally {
                this.Cerrar();
            }
    

    }


    public void asignarDev(Requerimientos obj) {

        //Valida estado antes de modificar
        
        
            try {
                String sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                        + "   SET \n"
                        + "       [fecha_asigno_desarrollo] = GETDATE() \n"
                        + "      ,[estado] = '" + obj.getEstado() + "'\n"
                        + "      ,[c_desarrollador] = '" + obj.getC_developer()+ "'\n"
                        + " WHERE ID='" + obj.getId() + "'";
                this.Conectar();
                PreparedStatement st = this.getCon().prepareStatement(sql);

                st.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
                //RequestContext context = RequestContext.getCurrentInstance();
                //context.execute("PF('wdlRequerimiento').hide();");
                PrimeFaces.current().executeScript("PF('wdlRequerimiento').hide();");
                

                st.close();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
                //System.err.println("Error al modificar " + e);

            } finally {
                this.Cerrar();
            }
    

    }
        


    public void insertarReq(Requerimientos obj) {

        if (obj.getPrioridad().isEmpty()
                || obj.getNuevo().isEmpty()
                || obj.getDescripcion().trim().isEmpty()
                || obj.getNombre_corto().trim().isEmpty()) {
            //RequestContext context = RequestContext.getCurrentInstance();

            //System.err.println("esta null: ");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
            return;
        }

        String sql = "INSERT INTO [dbo].[GP_REQUERIMIENTO]\n"
                + "           ([fecha_creado]\n"
                + "           ,[circular]\n"
                + "           ,[prioridad]\n"
                + "           ,[nuevo]\n"
                + "           ,[c_usuario_sol]\n"
                + "           ,[c_agencia]\n"
                + "           ,[descripcion]\n"
                + "           ,[nombre]\n"
                + "           ,[tipo_registro]\n"
                + "           )\n"
                + "     VALUES\n"
                + "           (GETDATE()\n"
                + "           ,'" + obj.getCircular() + "'\n"
                + "           ,'" + obj.getPrioridad() + "'\n"
                + "           ,'" + obj.getNuevo() + "'\n"
                + "           ,'" + obj.getC_usuario() + "'\n"
                + "           ,'" + obj.getC_agencia() + "'\n"
                + "           ,'" + obj.getDescripcion() + "'\n"
                + "           ,'" + obj.getNombre_corto() + "'\n"
                + "           ,'R'\n"
                + ")";
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);

            ///  st.setString(1, cat.getDETALLE());
            st.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('wdlRequerimiento').hide();");
            PrimeFaces.current().executeScript("PF('wdlRequerimiento').hide();");

            st.close();
            try {
                obj.setId(Integer.parseInt(obtenerUltimaSec(obj)));
                insertarDoc(obj);
            } catch (Exception e) {
                System.out.println("Error obtienendo ultimo ID para vincular Documentos\n" + e);
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + e));
            System.err.println("Error al insertar " + e + "\n" + sql);

        } finally {
            this.Cerrar();

        }
    }

    public void insertarCasoPrueba(Requerimientos obj) {

        for (int i = 0; i < obj.getAplicativo().length; i++) {
            String tm[] = obj.getAplicativo();
            System.out.println("APL" + tm[i]);
        }

    }

    public void insertarCaso(Requerimientos obj) {

        if (obj.getAplicativo().length == 0 || obj.getDescripcion().trim().isEmpty()
                || obj.getNombre_corto().trim().isEmpty()) {
            //RequestContext context = RequestContext.getCurrentInstance();

            //System.err.println("esta null: ");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
            return;
        }

        String aplicativo = "";
        for (int i = 0; i < obj.getAplicativo().length; i++) {
            String tm[] = obj.getAplicativo();
            aplicativo = aplicativo + " " + tm[i];
        }

        String sql = "INSERT INTO [dbo].[GP_REQUERIMIENTO]\n"
                + "           ([fecha_creado]\n"
                + "           ,[c_usuario_sol]\n"
                + "           ,[c_agencia]\n"
                + "	      ,[aplicativo]\n"
                + "           ,[especifique_otros]\n"
                + "           ,[descripcion]\n"
                + "           ,[nombre]\n"
                + "           ,[c_area]\n"
                + "           ,[tipo_registro]\n"
                + "		   )\n"
                + "     VALUES\n"
                + "           (\n"
                + "	       GETDATE() \n"
                + "           ,'" + obj.getC_usuario() + "'\n"
                + "           ,'" + obj.getC_agencia() + "'\n"
                + "	      ,'" + aplicativo.trim() + "'\n"
                + "           ,'" + obj.getEspecifique_otro() + "'\n"
                + "           ,'" + obj.getDescripcion() + "'\n"
                + "           ,'" + obj.getNombre_corto() + "'\n"
                + "           ,'" + obj.getC_area() + "'\n"
                + "           ,'C'\n"
                + "		   )";
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);

            ///  st.setString(1, cat.getDETALLE());
            st.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('wdlCaso').hide();");
            PrimeFaces.current().executeScript("PF('wdlCaso').hide();");

            st.close();
            try {
                obj.setId(Integer.parseInt(obtenerUltimaSec(obj)));
                insertarDoc(obj);
            } catch (Exception e) {
                System.out.println("Error obtienendo ultimo ID para vincular Documentos\n" + e);
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + e));
            System.err.println("Error al insertar " + e + "\n" + sql);

        } finally {
            this.Cerrar();

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

    public void modificarReq(Requerimientos obj) {

        //Valida estado antes de modificar
        String existe = "select count(*) from [dbo].[GP_REQUERIMIENTO] where ID='" + obj.getId() + "' AND fecha_autorizado IS NOT NULL;";
        existe = m.obtenerString(existe);
        if (existe.trim().equalsIgnoreCase("0")) {
            try {
                String sql = "UPDATE [dbo].[GP_REQUERIMIENTO]\n"
                        + "   SET \n"
                        + "       [circular] = '" + obj.getCircular() + "'\n"
                        + "      ,[prioridad] = '" + obj.getPrioridad() + "'\n"
                        + "      ,[nuevo] = '" + obj.getNuevo() + "'\n"
                        + "      ,[descripcion] = '" + obj.getDescripcion() + "'\n"
                        + "      ,[nombre] = '" + obj.getNombre_corto() + "'\n"
                        + " WHERE ID='" + obj.getId() + "'";
                this.Conectar();
                PreparedStatement st = this.getCon().prepareStatement(sql);

                st.executeUpdate();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
                //RequestContext context = RequestContext.getCurrentInstance();
                //context.execute("PF('wdlRequerimiento').hide();");
                PrimeFaces.current().executeScript("PF('wdlRequerimiento').hide();");

                st.close();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al modificar" + e));
                //System.err.println("Error al modificar " + e);

            } finally {
                this.Cerrar();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "AVISO", "REQUERIMIENTO YA ESTA AUTORIZADO"));
//            RequestContext context = RequestContext.getCurrentInstance();
//            context.execute("PF('wdlRequerimiento').hide();");
            PrimeFaces.current().executeScript("PF('wdlRequerimiento').hide();");
        }
        try {
            insertarDoc(obj);

        } catch (Exception e) {
            System.out.println("Error obtienendo ultimo ID para vincular Documentos\n" + e);
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
                //context.execute("PF('wdlCaso').hide();");
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
            //context.execute("PF('wdlCaso').hide();");
            PrimeFaces.current().executeScript("PF('wdlCaso').hide();");
        }
        try {
            insertarDoc(obj);

        } catch (Exception e) {
            System.out.println("Error obtienendo ultimo ID para vincular Documentos\n" + e);
        }

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
   
    public List<Daily> listarDiario(Daily obj) { 

        List<Daily> lista = null;

        String sql = "SELECT [id]\n"
                + "	 ,CONVERT(varchar,[fecha],103) fechax \n"
                + "      ,[c_desarrollador]\n"
                + "     ,(select nombre from [SGR].[dbo].Empleados where Empleado_Id=D.[c_desarrollador]) nombre"
                + "      ,[detalle]\n"
                + "  FROM [SGR].[dbo].[GP_DAILY] D\n"
                + "  where \n"
                + "  CONVERT(varchar,[fecha],103)='"+obj.getFecha()+"' \n"
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
        
        
    public List<Daily> listarDiarioFechas() {

        List<Daily> lista = null;

        String sql = 
                  "SELECT top 30-- [id]\n"
                + "      CONVERT(varchar,[fecha],103)  [fechax]\n"
                + "  FROM [SGR].[dbo].[GP_DAILY] D\n"
                + "  group by fecha\n"
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
                file.setDetalle("");
                file.setFecha(rs.getString("fechax"));
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
    
    
    public void insertarRespuestaDiario(Daily obj,String nombre) {

      
        String sql = "";
    

            sql = "UPDATE [dbo].[GP_DAILY]\n"
                    + "   SET \n"
                    + "      [detalle] = detalle + '<br><pre class=\"ql-syntax\" spellcheck=\"false\">RESPUESTA "+nombre+" "+sdf_f.format(new Date())+" a las " + sdf_h.format(new Date()) + "  </pre><br>" + obj.getDetalle() + "'\n"
                    + " WHERE id='" + obj.getId() + "'";
      
        //System.out.println("SQL UPDATe" + sql);

        try {

            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);

            st.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('wdlRequerimiento').hide();PF('wdlCaso').hide();PF('wdlEjecutaSQL').hide();PF('wdlPermiso').hide();");
            st.close();
            
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('wdlDiario').hide();");
            PrimeFaces.current().executeScript("PF('wdlDiario').hide();");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al Guardar" + e));
            System.err.println("Error al guardar " + e + "" + sql);

        } finally {
            this.Cerrar();
        }

    }
    

}
