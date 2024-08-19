/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.dao;


import finsolhn.com.model.Login;
import finsolhn.com.model.Menu;
import finsolhn.com.model.MenuOpcion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author rcardona
 */
public class LoginDAO3 extends DAO3 {

    public boolean obtenerUsuario(Login user) throws SQLException{
/*
        this.usuario = user.getUsuario();
        this.clave = user.getClave();*/
        //this.setUsuario(user.getUsuario());
        //this.setClave(user.getClave());
        //this.setBase_datos(user.getBase_datos());
        //this.setHost_server(user.getHost_server());

        boolean existe=false;
        
         ResultSet rs;
        try {
            this.Conectar();
          String sql_ver_existe="select * FROM ad_usuarios where usuariotopaz = '"+user.getUsuario()+"' and contrasenia = MD5('"+user.getClave()+"')";
           // System.out.println(""+sql_ver_existe);
          PreparedStatement st = this.getCon().prepareCall(sql_ver_existe);

          rs = st.executeQuery();
          if(rs.next())
          {
              
            existe=true;  
          }
          rs.close();
          st.close();
            
        } catch (Exception e) {
            System.err.println(""+e);
        }finally {
           this.Cerrar();
        }
        
        
        /* if (!user.getUsuario().equalsIgnoreCase("RCARDONA")) {
            this.clave = user.getClave();
        }*/
       /* if (this.Conectar().equalsIgnoreCase("OK")) {
            return true;
        } else {
            return false;
        }*/
       
        return existe;
    }

    public List<Menu> listarMenu(Login user) {
        List<Menu> lista = null;

        ResultSet rs;
        lista = null;
        /*
        try {
            this.Conectar();
            String sql = "SELECT distinct M.[c_menu]\n"
                    + "         ,M.[descripcion]\n"
                    + "         ,M.[orden]\n"
                    + "        ,M.[estado]\n"
                    + "     FROM [CREDISOL_BUSINESS].[dbo].[ERP_MENU] M INNER JOIN [CREDISOL_BUSINESS].[dbo].[ERP_OPCIONES_MENU] O\n"
                    + "     on M.c_menu=O.c_menu inner join [CREDISOL_BUSINESS].[dbo].[ERP_OPCIONES_USUARIO] U\n"
                    + "  on O.c_opcion=U.c_opcion\n"
                    + "WHERE M.estado=1 and O.estado=1 and U.b_activo=1 and U.c_usuario='" + user.getUsuario() + "' ORDER BY M.ORDEN";
            PreparedStatement st = this.getCon().prepareCall(sql);

            rs = st.executeQuery();
            ////            System.out.println(sql);
            lista = new ArrayList();
            while (rs.next()) {
                Menu obj = new Menu();
                obj.setC_menu(rs.getInt("c_menu"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setOrden(rs.getInt("orden"));
                obj.setEstado(rs.getInt("estado"));
                lista.add(obj);
            }
            
            st.close();
            rs.close();
        } catch (Exception e) {
            //System.err.println("error listas menu" + e);
        } finally {
           this.Cerrar();
        }
        */
        
        lista = new ArrayList();
        Menu obj = new Menu();
                obj.setC_menu(1);
                obj.setDescripcion("Menu 1");
                obj.setOrden(1);
                obj.setEstado(1);
        //lista.add(obj);
        
         obj = new Menu();
                obj.setC_menu(2);
                obj.setDescripcion("Movil");
                obj.setOrden(2);
                obj.setEstado(1);
        lista.add(obj);
        
        return lista;
    }

    public List<MenuOpcion> listarMenuOpcion(Login user) {
        List<MenuOpcion> lista = null;
        ResultSet rs;
        lista = null;
        /*
        try {
            String sql = "  SELECT O.[c_opcion]\n"
                    + "       ,O.[descripcion]\n"
                    + "       ,O.c_menu\n"
                    + "       ,(select descripcion from [CREDISOL_BUSINESS].[dbo].[ERP_MENU] where c_menu=O.c_menu) descripcion_padre\n"
                    + "       ,(select orden from [CREDISOL_BUSINESS].[dbo].[ERP_MENU] where c_menu=O.c_menu) orden_padre\n"
                    + "       ,O.es_titulo\n"
                    + "       ,O.c_titulo\n"
                    + "       ,O.url\n"
                    + "       ,orden\n"
                    + "  FROM [CREDISOL_BUSINESS].[dbo].[ERP_OPCIONES_MENU]  O inner join [CREDISOL_BUSINESS].[dbo].[ERP_OPCIONES_USUARIO] U\n"
                    + "  on O.c_opcion=U.c_opcion \n"
                    + "  where  O.estado=1 and U.c_usuario='"+user.getUsuario()+"' and U.b_activo=1\n"
                    + "  UNION ALL\n"
                    + "  SELECT distinct  O.[c_opcion]\n"
                    + "       ,O.[descripcion]\n"
                    + "       ,O.c_menu\n"
                    + "       ,(select descripcion from [CREDISOL_BUSINESS].[dbo].[ERP_MENU] where c_menu=O.c_menu) descripcion_padre\n"
                    + "       ,(select orden from [CREDISOL_BUSINESS].[dbo].[ERP_MENU] where c_menu=O.c_menu) orden_padre\n"
                    + "       ,O.es_titulo\n"
                    + "       ,O.c_titulo\n"
                    + "       ,O.url\n"
                    + "       ,O.orden\n"
                    + "  FROM [CREDISOL_BUSINESS].[dbo].[ERP_OPCIONES_MENU]  O inner join \n"
                    + "  (SELECT O.[c_opcion]\n"
                    + "       ,O.[descripcion]\n"
                    + "       ,O.c_menu\n"
                    + "       ,(select descripcion from [CREDISOL_BUSINESS].[dbo].[ERP_MENU] where c_menu=O.c_menu) descripcion_padre\n"
                    + "       ,(select orden from [CREDISOL_BUSINESS].[dbo].[ERP_MENU] where c_menu=O.c_menu) orden_padre\n"
                    + "       ,O.es_titulo\n"
                    + "       ,O.c_titulo\n"
                    + "       ,O.url\n"
                    + "       ,orden\n"
                    + "  FROM [CREDISOL_BUSINESS].[dbo].[ERP_OPCIONES_MENU]  O inner join [CREDISOL_BUSINESS].[dbo].[ERP_OPCIONES_USUARIO] U\n"
                    + "  on O.c_opcion=U.c_opcion \n"
                    + "  where  O.estado=1 and U.c_usuario='"+user.getUsuario()+"' and U.b_activo=1) U on O.c_opcion=U.c_titulo";
           this.Conectar();
            PreparedStatement st = this.getCon().prepareCall(sql);

            ////            System.out.println("SQL 2" + sql);

            rs = st.executeQuery();

            lista = new ArrayList();
            while (rs.next()) {
                MenuOpcion obj = new MenuOpcion();
                obj.setC_opcion(rs.getInt("c_opcion"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setC_menu(rs.getInt("c_menu"));
                obj.setDescripcionPadre(rs.getString("descripcion_padre"));
                obj.setEs_titulo(rs.getInt("es_titulo"));
                obj.setC_titulo(rs.getInt("c_titulo"));
                obj.setUrl(rs.getString("url"));
                obj.setOrden(rs.getInt("orden"));
                obj.setEstado(1);
                ////System.out.println("r:" + obj.getResultado());
                lista.add(obj);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            //System.err.println("error listas menu opcion " + e);
        } finally {
           this.Cerrar();
        }
        */
        
        
        lista = new ArrayList();
        MenuOpcion obj = new MenuOpcion();
        obj.setC_opcion(1);
        obj.setDescripcion("Usuarios Internos");
        obj.setC_menu(1);
        obj.setDescripcionPadre("Menu p");
        obj.setEs_titulo(0);
        obj.setC_titulo(0);
        obj.setUrl("gestion_proyect/administracion_usuarios.xhtml");
        obj.setOrden(1);
        obj.setEstado(1);
        lista.add(obj);
        
        
        obj = new MenuOpcion();
        obj.setC_opcion(2);
        obj.setDescripcion("Jefe Proyectos");
        obj.setC_menu(1);
        obj.setDescripcionPadre("Menu p");
        obj.setEs_titulo(0);
        obj.setC_titulo(0);
        obj.setUrl("gestion_proyect/administracion_jefe_proy.xhtml");
        obj.setOrden(1);
        obj.setEstado(1);
        lista.add(obj);
        
        obj = new MenuOpcion();
        obj.setC_opcion(3);
        obj.setDescripcion("Desarrolladores");
        obj.setC_menu(1);
        obj.setDescripcionPadre("Menu p");
        obj.setEs_titulo(0);
        obj.setC_titulo(0);
        obj.setUrl("gestion_proyect/administracion_desarrollo.xhtml");
        obj.setOrden(1);
        obj.setEstado(1);
        lista.add(obj);
        
        obj = new MenuOpcion();
        obj.setC_opcion(4);
        obj.setDescripcion("DBA's");
        obj.setC_menu(1);
        obj.setDescripcionPadre("Menu p");
        obj.setEs_titulo(0);
        obj.setC_titulo(0);
        obj.setUrl("gestion_proyect/administracion_dba.xhtml");
        obj.setOrden(1);
        obj.setEstado(1);
        lista.add(obj);
        
        return lista;
    }
    public boolean validaAgencia(String c_usuario,String c_agencia)
    {
        /*
        boolean bn=false;
        String sql="SELECT        COUNT(*) AS PERMISO\n" +
        "FROM            erp_usuario_agencia\n" +
        "WHERE        (c_usuario = '"+c_usuario+"') "
                + "AND (b_registro_activo = 1) "
                + "AND (c_agencia = "+c_agencia+")";
        ResultSet rs;
        try{
            this.Conectar();
            PreparedStatement st=this.getCon().prepareStatement(sql);
            rs=st.executeQuery();
            while(rs.next())
            {
                if(rs.getInt("PERMISO")>=1)
                    bn=true;
                
            }
            rs.close();
            st.close();
        }catch(SQLException e)
        {
            System.out.println("Error :"+e);
        } finally {
           this.Cerrar();
        }
        return bn;
        */
        return true;
    }
    /* 
   public String leerID(Persona per)
    { 
        Persona pers=null;
        ResultSet rs;
        try{
            this.Conectar();
            PreparedStatement st=this.getCon().prepareStatement("SELECT CODIGO,NOMBRE,SEXO FROM TABLA WHERE CODIGO=? ORDER BY CODIGO");
            st.setString(1, per.getCodigo());
            rs=st.executeQuery();
            
            while(rs.next())
            {
                pers=new Persona();
                pers.setCodigo(rs.getString("CODIGO"));
                pers.setNombre(rs.getString("NOMBRE"));
                pers.setSexo(rs.getString("SEXO"));
                        
            }
            
        }catch(Exception e)
        {
            //System.out.println("Error al obtener dato:"+e);
        }
        
        return pers;
    }*/
}
