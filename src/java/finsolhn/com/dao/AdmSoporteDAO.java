/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.dao;

import finsolhn.com.model.Aceptacion;
import finsolhn.com.model.Archivo;
import finsolhn.com.model.MyFile;
import finsolhn.com.model.Requerimientos;
import finsolhn.com.util.Constantes;
import java.sql.Connection;

//import java.sql.Date;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;



/**
 *
 * @author rcardona
 */
public class AdmSoporteDAO extends DAO {

    //@Resource(mappedName = "jdbc/credisol_base")
    //@Resource(mappedName = "jdbc/datasource_credisol")
    //private DataSource ds;
    public MetodosSQL m = new MetodosSQL();
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//("dd/MM/yyyy");
    // SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");//("yyyy-MM-dd");

    public void modificarArchivoTOP_DESCARGA(Archivo obj) {


        String fecha="";
        if(obj.getTipo_proceso().equalsIgnoreCase("DESCARGA") && obj.getEstado()==2)
        {
           fecha="      ,[FECHA_ENVIA] = GETDATE()\n";   
        }else {
           fecha="      ,[FECHA_SUBE] = GETDATE()\n";
        }

        
        String sql = "UPDATE [dbo].[GP_ARCHIVOS_TOP]\n"
                + "   SET \n"
                + "       [TIPO_PROCESO] = '"+obj.getTipo_proceso()+"'\n"
                + "      ,[COMENTARIO] = COMENTARIO+'"+obj.getComentario_nuevo()+"'\n"
                +         fecha
                + "      ,[C_USUARIO_PROCESA] = '"+obj.getC_usuario_procesa()+"'\n"
                + "      ,[ESTADO] = '"+obj.getEstado()+"'\n"
                + "      ,[ANTERIOR1] = '"+(obj.isVisibleBtIndicador3() == true ? 1 : 0)+"'\n"
                + "      ,[ANTERIOR2] = '"+(obj.isVisibleBtIndicador4() == true ? 1 : 0)+"'\n"
                + "      ,[RAIZ] = '"+obj.getRuta_raiz()+"'\n"
                + "      ,[FOLDER] = '"+obj.getRuta_folder()+"'\n"
                + " WHERE ID_ARCHIVO='"+obj.getIdArchivo()+"'";
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(sql);

            ///  st.setString(1, cat.getDETALLE());
            st.executeUpdate();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Actualizado Correctamente"));
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('wdlRespuesta').hide();PF('wdlRespuesta2').hide();");
            PrimeFaces.current().executeScript("PF('wdlRespuesta').hide();PF('wdlRespuesta2').hide();");
            
            st.close();

            

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al Actualizar" + e));
            System.err.println("Error al modificar " + e + "\n" + sql);

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

        String sql = "INSERT INTO [dbo].[GP_ARCHIVOS_TOP]\n"
                + "           ([ID_REQUERIMIENTO]\n"
                + "           ,[NOMBRE_ARCHIVO]\n"
                + "           ,[TIPO_ARCHIVO]\n"
                + "           ,[TIPO_PROCESO]\n"
                + "           ,[COMENTARIO]\n"
                + "           ,[FECHA_PIDE]\n"
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
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('wdlDescarga').hide();");
            PrimeFaces.current().executeScript("PF('wdlDescarga').hide();");

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

    public List<Archivo> listarArchivosTOP() {

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
                + "      ,(select nombre from  [SGR].[dbo].Empleados where Empleado_Id=A.C_USUARIO_SOL) d_usuario_sol"
                + "  FROM [SGR].[dbo].[GP_ARCHIVOS_TOP] A WHERE TIPO_ARCHIVO<>'.SQL' and ESTADO in(1,2,3) or CONVERT(DATE, FECHA_SUBE)= CONVERT(DATE, GETDATE()) and estado<>0";

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
                file.setD_usuario_sol(rs.getString("d_usuario_sol"));

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

}
