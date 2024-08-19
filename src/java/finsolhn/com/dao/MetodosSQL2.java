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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetodosSQL2 extends DAO2{
    
    
    public String obtenerString(String sql)
    { 
        String valor="";
        ResultSet rs;
        try{
            //this.ConectarUsuario();
            this.Conectar();
            PreparedStatement st=this.getCon().prepareStatement(sql);
            // Statement st = this.getConeccion_tmp().createStatement();
            //rs=st.executeQuery(sql);
            rs=st.executeQuery();
            while(rs.next())
            {
                valor=rs.getString(1);
                        
            }
            st.close();
            rs.close();
        }catch(Exception e)
        {
            System.out.println("Error al obtener dato:"+e+"\n"+sql);
        }finally
        {
           
           // this.CerrarConecction();
           this.Cerrar();
        }
        if(valor.length()==0)
            valor="0";
        return valor;
    }
    public boolean obtenerBoolean(String sql)
    { 
        boolean valor=false;
        ResultSet rs;
        try{
            this.Conectar();
            Statement st = this.getCon().createStatement();
            
            rs=st.executeQuery(sql);
            
            while(rs.next())
            {
                valor=rs.getBoolean(1);
                        
            }
            
             st.close();
            rs.close();
        }catch(Exception e)
        {
            System.out.println("Error al obtener dato:"+e+"\n"+sql);
        }finally
        {
            this.Cerrar();
        }
        
            
        return valor;
    }


    public String[] traeArray0(int col, String sql) {

        String A[] = new String[1];
        int i = 0;
        try {
            this.Conectar();
            //PreparedStatement st=this.getCon().prepareStatement(sql);
            Statement st = this.getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                i++;
            }

            A = new String[i];
            i = 0;

            rs = st.executeQuery(sql);
            int n = 1;
            while (rs.next()) {

                for (int x = 1; x <= col; x++) {
                    if (x == 1) {
                        A[i] = rs.getString(x);
                    } else {
                        A[i] = A[i] + " _ " + rs.getString(x);
                    }
                }
                i++;
            }
             st.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error trae array0 \n" + ex.toString()+"\n"+sql);
        }finally{
            this.Cerrar();
        }
        return A;
    }
        public String[] traeArrayX(String sql) {

        String A[] = new String[1];
        int i = 0;
        Statement st = null;
        try {
             this.Conectar();
            
            st = this.getCon().createStatement();
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
               // cerrar();
                if(st!=null)
                st.close();
            } catch (SQLException ex) {
                //Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return A;
    }
    
    public String ejecutar(String sql)
    { 
        String valor="";
        try{
            this.Conectar();
            Statement st = this.getCon().createStatement();
            st.execute(sql);
            valor="1";
            
             st.close();
            
        }catch(Exception e)
        {     System.out.println("Error al ejecutar:"+e+"\n"+sql);
            valor=""+e;
        }finally
        {
            this.Cerrar();
        }
        
        return valor;
    }
    
    
    
}
