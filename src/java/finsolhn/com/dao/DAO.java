
package finsolhn.com.dao;

import finsolhn.com.model.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.sql.DataSource;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.commons.dbcp.BasicDataSource;
import org.primefaces.PrimeFaces;


/**
 *
 * @author rcardona
 */
public class DAO {
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");//("yyyy-MM-dd");
    SimpleDateFormat sdf_f = new SimpleDateFormat("dd/MM/yyyy");//("yyyy-MM-dd");
    SimpleDateFormat sdf_h = new SimpleDateFormat("HH:mm");//("yyyy-MM-dd");
    
    private DataSource source;
    //String host_server = "192.168.0.27";
    private static final String host_server = "192.168.0.198";
    //String host_server = "10.16.10.10";
    /*
    private String usuario     = "ferazo";
    private String clave       = "Sistemas.2016";*/  
    /*String usuario     = "USR_MASTER";
    String clave       = "$**T3cn0l0g1@CM.2016**$";*/
    
    private static String usuario     = "HD";
    private static String clave       = "root";
    private static String base_datos  = "SGR";
    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String url = "jdbc:sqlserver://"+host_server+":1433;database="+base_datos;
    Connection con=null;
    
    
    public String Conectar()
    {
        //validaSesion();
        String resultadoCon="";
        BasicDataSource bds =new BasicDataSource();
        bds.setDriverClassName(driver);
        bds.setUsername(usuario);
        bds.setPassword(clave);
        /*url = "jdbc:sqlserver://"+host_server+":1433;database="+base_datos;
        bds.setUrl(url);*/
        bds.setMaxActive(50);
        FacesContext context=null;
        Login usr_sess=null;
        try {            
            context = FacesContext.getCurrentInstance();
            usr_sess = (Login) context.getExternalContext().getSessionMap().get("USUARIO");
        
            
        } catch (Exception e) {
            //e.printStackTrace();
        }
        
        if(usr_sess!=null){
           /// bds.setUsername(usr_sess.getUsuario());
           /// bds.setPassword(usr_sess.getClave());    
           /// this.base_datos=usr_sess.getBase_datos();
           /// this.host_server=usr_sess.getHost_server();
            //System.out.println("Usuario de la Sesion: "+usr_sess.getUsuario());
        }
        
        url = "jdbc:sqlserver://"+host_server+":1433;database="+base_datos;
        bds.setUrl(url);
        
        /*
        System.out.println("SE CONECTO el Usuario: "+usuario);
        System.out.println("AL SERVIDOR: "+host_server);
        System.out.println("Y BASE DE DATOS: "+base_datos);*/
        //System.out.println("USUARIO QUE SE CONECTO A LA BDD: "+bds.getUsername());
        /*try
        {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            try
            {
                Class.forName(driver);
            }catch(ClassNotFoundException ex1)
            {
                System.out.println(ex1.getMessage());
            }
            url = "jdbc:sqlserver://"+host_server+":1433;database="+base_datos;
            con = DriverManager.getConnection(url,usuario,clave);
    	    //	url="jdbc:sqlserver://192.168.0.101:1433;integratedSecurity=true;database="+base_datos;
            resultadoCon="OK";
    	}catch(Exception ex2)
        {
            resultadoCon="ERR";
            System.err.println("Error al abrir conexion:"+ex2);
            mensajeDialogoFatal("Conexion", "Error al conectar "+ex2);
    	}*/
        boolean conecto=false;
        try {
            conecto=(boolean) context.getExternalContext().getSessionMap().get("conecto");            
        } catch (Exception e) {
            if(conecto!=false)
            e.printStackTrace();
        }
        //System.out.println("Valor de Conecto: "+conecto);
        if(conecto)
        {
            this.source=(DataSource) context.getExternalContext().getSessionMap().get("con"); 
            //System.out.println("conectado");
        }else            
        {
            source=bds;
            //System.out.println("no conectado");
        }
        //source=bds;
       try {
            con=source.getConnection();
            if(con!=null )
            {
                /*System.out.println("SE CONECTO el Usuario: "+usuario);
                System.out.println("AL SERVIDOR: "+host_server);
                System.out.println("Y BASE DE DATOS: "+base_datos);*/
                resultadoCon="OK";   
                if(conecto==false)
                {
                    //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("con", source);
                    //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("conecto", true);
                }
                
            }
        } catch (SQLException e) {            
            System.out.println("NO SE CONECTO "+e);
        }/*finally
        {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("NO SE CONECTO "+e);
            }
        }*/
       return resultadoCon;
    }
    /*
    private Connection coneccion_tmp;

    public Connection getConeccion_tmp() {
        return coneccion_tmp;
    }

    public void setConeccion_tmp(Connection coneccion_tmp) {
       
    }
   
    //private String host_server = "10.16.10.10";
    String host_server = "192.168.0.27";
    String usuario     = "sa";
    String clave       = "*2009";
    //String usuario     = "USR_MASTER";
    //String clave       = "$**T3cn0l0g1@CM.2016**$";
    private String base_datos  = "CREDISOL_OPDF";
    String url; 
    
    public String ConectarUsuario()
    {
        String resultadoCon="";
       try
        {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            try
            {
                Class.forName(driver);
            }catch(ClassNotFoundException ex1)
            {
                System.out.println(ex1.getMessage());
            }
            url = "jdbc:sqlserver://"+host_server+":1433;database="+base_datos;
            coneccion_tmp = DriverManager.getConnection(url,usuario,clave);
    	    //	url="jdbc:sqlserver://192.168.0.101:1433;integratedSecurity=true;database="+base_datos;
            resultadoCon="OK";
    	}catch(Exception ex2)
        {   
            CerrarConecction();
            resultadoCon="ERR";
            System.err.println("Error al abrir conexion:"+ex2);
            mensajeDialogoFatal("Conexion", "Error al conectar "+ex2);
    	}
       return resultadoCon;
    }
    

    
    public void CerrarConecction()
    {
        
        try{
            if(coneccion_tmp!=null)
            {
                if(!coneccion_tmp.isClosed())
                {
                       coneccion_tmp.close();
                }
            }
        }catch(Exception e)
        {
            System.err.println("Error al cerrar conexion:"+e);
        }
        

    }
    
    public void Conectar()
    {
        
    }*/
     public void Cerrar()
     {
         try{
            if(con!=null)
            {
                if(!con.isClosed())
                {
                    con.close();
                }
            }
        }catch(SQLException e)
        {
            System.err.println("Error al cerrar conexion:"+e);
        }
        
         /*try{
            if(conne!=null)
            {
                if(!conne.isClosed())
                {
                       conne.close();
                       ds.getConnection().close();
                }
            }
        }catch(Exception e)
        {
            System.err.println("Error al cerrar conexionPool:"+e);
        }*/
     }
    public String getDataBaseDefault()
    {
        return base_datos;
    }
    private Connection conne;
    private DataSource ds;
    
    public Connection getCon()
    {
        return con;
    }
    /*
    public Connection getCon()
    {
        try{
            
            Context ctx = new InitialContext();
             ds = (DataSource) ctx.lookup("jdbc/credisol_base");
            conne=ds.getConnection();
            return conne;
        }catch(Exception e)
        {   Cerrar();
            System.err.println("erro "+e);
        }
        return null;
    }*/
    
    
    
    public void mensajeDialogoInfo(String title,String msj)
    {
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msj);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,title, msj);
        PrimeFaces.current().dialog().showMessageDynamic(message);
        
    }
    public void mensajeDialogoError(String title,String msj)
    {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msj);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
    public void mensajeDialogoWaning(String title,String msj)
    {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, title, msj);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    } 
     public void mensajeDialogoFatal(String title,String msj)
    {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, title, msj);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public String getHost_server() {
        return host_server;
    }



    public DataSource getSource() {
        return source;
    }

    public void setSource(DataSource source) {
        this.source = source;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getBase_datos() {
        return base_datos;
    }

    public void setBase_datos(String base_datos) {
        this.base_datos = base_datos;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Connection getConne() {
        return conne;
    }

    public void setConne(Connection conne) {
        this.conne = conne;
    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }
    
    
   
    
}
