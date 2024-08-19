/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.dao;

/**
 *
 * @author rcardona
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.driver.OracleDriver;

public class ConexionOracle {

    //Usuario de la base de datos
    private final String USUARIO = "TOPAZ";
    //Contraseña del usuario de la base de datos
    private final String PASS = "bdp2020";
    //SID de la base de datos, este lo registramos en la instalacion
    private final String SID = "pruebas";
    //Host donde se encuentra la base de datos, para nuesto caso como es local
    //se indica que esta en localhost
    private final String HOST = "192.168.0.3";
    //El puerto 1521 es el estandar para este tipo de instalaciones a menos que
    //se indicque lo contrario
    private final int PUERTO = 1521;
    //Objeto donde se almacenara nuestra conexion
    private Connection connection;

    ///////////////////CON DESA/////////////////////////
    //Usuario de la base de datos
    private final String USUARIO2 = "TOPAZ";
    //Contraseña del usuario de la base de datos
    private final String PASS2 = "abd20161";
    //SID de la base de datos, este lo registramos en la instalacion
    private final String SID2 = "nodo";
    //Host donde se encuentra la base de datos, para nuesto caso como es local
    //se indica que esta en localhost
    private final String HOST2 = "192.168.0.174";
    
    
    
    public Connection getConnection() {
        return connection;
    }

    /*
     * Instanciamos un objeto de tipo OracleDriver para regitrarlo y posterior uso
     * este objeto lo provee el driver que agregamos al principio
     */
    public void registrarDriver() throws SQLException {
        OracleDriver oracleDriver = new oracle.jdbc.driver.OracleDriver();
        DriverManager.registerDriver(oracleDriver);
    }

    /*
     * Procedemos a realizar nuestra conexion a la base datos, para esto nos
     * aseguramos que el objeto este null o que este cerrada la conexion.
     * 
     * cadanaConexion: es un string que se contruye a partir de los atributos
     * definidos.
     * 
     * Finalmente obtenemos la conexion.  El metodo "getConnection"
     * lanza una excepcion la cual propagamos "throws SQLException".
     */
    public void conectar(int t) throws SQLException {
        //System.out.println(connection);
        if(t==1)
        {
            if (connection == null || connection.isClosed() == true) {System.out.println("Abriendo CONEXION Test");
                String cadenaConexion = "jdbc:oracle:thin:@" + HOST + ":" + PUERTO + ":" + SID;
                registrarDriver();
                connection = DriverManager.getConnection(cadenaConexion, USUARIO, PASS);
            }
        }else{
           if (connection == null || connection.isClosed() == true) {System.out.println("Abriendo CONEXION Desa");
                String cadenaConexion = "jdbc:oracle:thin:@" + HOST2 + ":" + PUERTO + ":" + SID2;
                registrarDriver();
                connection = DriverManager.getConnection(cadenaConexion, USUARIO2, PASS2);
            }
        }
        
    }

    /*
     * Con este metodo cerramos la conexion una vez hayamos terminado de usar la
     * base de datos
     */
    public void cerrar() throws SQLException {
        try {
            if (connection != null && connection.isClosed() == false) {
            connection.close();
        }
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexion Oracle");
        }
        
    }

    /*
     * Main para comprobar que funciona, aqui hacemos un select a una tabla del
     * sistema para obtener la version.
     */
    public String traeDato(String sql,int t) {
        String res = "";
        Statement st=null;
        try {

            if (connection == null || connection.isClosed() == true) {
                conectar(t);
            }
            
            st = connection.createStatement();
            ResultSet rset = st.executeQuery(sql);
            
            while (rset.next()) {
                res=rset.getString(1);
                //System.out.println(rset.getString(1));   // Print col 1
            }
             
            
        } catch (Exception e) {
            System.out.println("Error al ejecutar SQL:\n"+sql+"\n"+e.toString());
        }finally{
           
            try {
               // cerrar();
                if(st!=null)
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
        }

        return res;
    }
    
    public String[] traeArrayX(String sql,int t) {

        String A[] = new String[1];
        int i = 0;
        Statement st = null;
        try {
            if (connection == null || connection.isClosed() == true) {
                conectar(t);
            }
            
            st = connection.createStatement();
            ResultSet rset = st.executeQuery(sql);
            int col = rset.getMetaData().getColumnCount();
            while (rset.next()) {
                i++;
            }
            
            A = new String[i];
            i = 0;

            rset = st.executeQuery(sql);
            
            while (rset.next()) {

                for (int x = 1; x <= col; x++) {
                    if (x == 1) {
                        A[i] = rset.getString(x);
                    } else {
                        A[i] = A[i] + " __ " + rset.getString(x);
                    }
                }
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar SQL:\n"+sql+"\n"+e.toString());
             A = new String[0];
        }finally{
             try {
                cerrar();
                if(st!=null)
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return A;
    }
    
    
    
    
    
    public boolean ejecutar(String sql,int t) {
        boolean estado = true;
        Statement st=null;
        try {
            if (connection == null || connection.isClosed() == true) {
                conectar(t);
            }
            //connection.setAutoCommit(false);
            st = connection.createStatement();
            st.execute(sql);
            //st.executeUpdate(sql);
           // connection.commit();
        } catch (SQLException e) {
            //connection.rollback();
            estado = false;
            System.out.println("Error al ejecutar SQL:\n"+sql+"\n"+e.toString());
        }finally{
             try {
               // cerrar();
                if(st!=null)
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return estado;
    }
        
    /*
    public static void main(String args[]) {

        ConexionOracle conexionOracle = new ConexionOracle();
        try {
            conexionOracle.conectar();
            Connection conn = conexionOracle.getConnection();
            
            //connection = conexionOracle.getConnection();
                 
            // driver@machineName:port:SID           ,  userid,  password
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT c6251 FROM co_productos WHERE c6250=1555000001");
            while (rset.next()) {
                System.out.println(rset.getString(1));   // Print col 1
            }
            stmt.close();
            conexionOracle.cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    public String getSID() {
        return SID;
    }

    public String getHOST() {
        return HOST;
    }
}
