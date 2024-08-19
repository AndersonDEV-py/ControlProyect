/*
 * CLASE ENCUESTADAO3
 * EXTIENDE= DAO3
 * INVOCACION =  ENCUESTACONTROLLER
 * 
 */
package finsolhn.com.dao;

import finsolhn.com.controller.EncuestaController;
import finsolhn.com.data.ClEncuesta;
import finsolhn.com.data.ClEncuestaPregunta;
import finsolhn.com.data.ClEncuestaPreguntaOpcion;
import finsolhn.com.model.EncuestaCuentas;
import finsolhn.com.model.EncuestasCreadas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;


/**
 *
 * @author ds010106
 * 
 */
public class EncuestaDAO3 extends DAO3 {

    //Conexion Oracle
    private MetodosSQL2 m = new MetodosSQL2();
    //Conexion Postgrest
    private MetodosSQL3 m3 = new MetodosSQL3();
   // private ClEncuesta clencuesta;

    
    //Funcion que enlista todas las encuestas
    //Invocacion = EncuestaController.getLstEncuesta
    public List<ClEncuesta> listarEncuestas() {
        
        //Lista con Modelo ClEncuesta
        List<ClEncuesta> lista = new ArrayList();
        //Declaracion de objeto tipo ClEncuesta
        ClEncuesta obj;
        
        //Primer objeto, de la lista
        obj = new ClEncuesta();
        obj.setCodEncuesta(0);
        obj.setNombre("Ninguna");
        lista.add(obj);
        
         //Declaracion de variable de iteracion de datos
        ResultSet rs;

        //Creacion query
        String sql = "SELECT \n"
                +"EN.COD_ENCUESTA, \n"
                +"EN.NOMBRE, \n"
                +"EN.TIPO_ENCUESTA \n"
                +"FROM CL_ENCUESTA EN \n"
                +"WHERE \n"
                +"EN.ESTADO='1' \n"
                +"ORDER BY EN.COD_ENCUESTA ASC ";
           
        try {
            //Conexion
            m.Conectar();
            
            //Declaracion de variable de ejecucion
            PreparedStatement st = m.getCon().prepareCall(sql);
            
            //Ejecucucion de Query
            rs = st.executeQuery();
            
           //Iteracion en los datos devueltos
            while (rs.next()) {
                //Seteo de funciones set del modelo ClEncuesta
                obj = new ClEncuesta();
                obj.setCodEncuesta(rs.getInt(1));
                obj.setNombre(rs.getString(2));
                obj.setTipoEncuesta(rs.getString(3));
                
                //Agregar elementos a la lista
                lista.add(obj);

            }
            //Cerrar variables
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("error listar encuestas" + e+"\n"+sql);
        } finally {
            //Cerrar conexion
            m.Cerrar();
        }
        
        //Retorno de lista
        return lista;
    }
    
   //Funcion que enlista las preguntas de la encuesta seleccionada
    //Parametros 1- Cod Encuesta
    //Invocacion:EncuestaController.getDatosEncuesta
   public List<ClEncuestaPregunta> listarPreguntasE(Integer idEncuesta)
    { 
        //Lista con Modelo ClEncuestaPregunta
        List<ClEncuestaPregunta> listaP=new ArrayList();
        //Declaracion de objeto tipo ClEncuestaPregunta
        ClEncuestaPregunta obj;
         //Declaracion de variable de iteracion de datos
        ResultSet rs;
        
        //Declaracion de Query
        // Se filtra por parametro enviado #1
        String sql="SELECT \n"
                +"EP.COD_ENCUESTA_PREGUNTA,\n"
                +"EP.COD_ENCUESTA,\n"
                +"EP.DETALLE_PREGUNTA,\n"
                +"EP.COD_TIPO_PREGUNTA,\n"
                +"EP.CANT_RESPUESTA_REQ,\n"
                +"EP.ORDEN,\n"
                +"TP.TIPO,\n"
                +"EP.OBLIGATORIA"
                +" FROM CL_ENCUESTA_PREGUNTA EP\n"
                +"INNER JOIN CL_TIPO_PREGUNTA TP ON TP.COD_TIPO_PREGUNTA=EP.COD_TIPO_PREGUNTA\n"
                +"WHERE EP.COD_ENCUESTA="+ idEncuesta+"\n"
                +"ORDER BY EP.ORDEN ASC";
        try {
            //Conexion
            m.Conectar();
            
            //Declaracion de variable de ejecucion
            PreparedStatement st = m.getCon().prepareCall(sql);
            
            //Ejecucion query
            rs = st.executeQuery();
           

           //Iteracion en los datos devueltos
            while (rs.next()) {
                //Seteo de funciones del modelo ClEncuestaPregunta
                obj = new ClEncuestaPregunta();
                obj.setCodEncuestaPregunta(rs.getString(1));
                obj.setDetallePregunta(rs.getString(3));
                obj.setCodTipoPregunta(rs.getInt(4));
                obj.setCantRespuestaReq(rs.getShort(5));
                obj.setNombreTipoPregunta(rs.getString(7));
                obj.setObligatoria(rs.getString(8));
                
                //Agregar elemenos a la lista
                listaP.add(obj);
            }
            //Cerrar variables
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.err.println("error pregunta" + e+"\n"+sql);

        } finally {
            //Cerrar conexiones
            m.Cerrar();
        }
        
        //Retornar lista
       return listaP;
    };
   
   
   //Funcion: Busca informacion del cliente seleccionado
   //Parametros: 1-Cod Cliente
   //Invocacion: EncuestaController.buscarCliente
   public List<String> buscarCL(String codCliente)
   {
       //Declaracion lista modelo String
       List<String> lista=new ArrayList();
       
       //Declaracion de variable de iteracion de datos
       ResultSet rs;
        
       //Declaracion de query
       //Se filtra informacion del cliente por parametro #1
        String sql="SELECT CL.C1000,CL.C1960,SUC.C6020 AS Nombre,JA.NOMBRE JefeAgencia FROM CL_CLIENTES CL "
                    +"INNER JOIN TC_SUCURSALES SUC ON SUC.C5834=CL.C1960 "
                    +"LEFT JOIN vcr_jefes_agencia JA ON JA.CODAGENCIA=CL.C1960 AND JA.CODCARGO=63 "
                    +"WHERE CL.C0902="+codCliente+" and CL.TZ_LOCK=0";
        
        System.out.println(sql);
        try {
            //Conexion
            m.Conectar();
            
            //Declaracion de variable de ejecucion
            PreparedStatement st = m.getCon().prepareCall(sql);
            
            //Ejecucion query
            rs = st.executeQuery();
            
           //Iteracion en los datos devueltos 
           while(rs.next())
           {
               //Argrerar objetos a la lista
               lista.add(rs.getString(1));
               lista.add(rs.getString(2));
               lista.add(rs.getString(3));
               lista.add(rs.getString(4));
           }
           //Cerrar variables
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("error listar info Cliente" + e+"\n"+sql);

        } finally {
            //Cerrar conexion
            m.Cerrar();
        }
        
      //Retornar lista  
      return lista;     
    };
   
  //Funcion: Enlista las cuentas del cliente segun sea el tipo de encuesta. P cuentas de tipo prestamo(5) y A cuentas de tipo ahorro(incluyendo finsolito),cdp (3 y 4) 
   //Parametros: 1- Cod Cliente 2- Tipo de encuesta
   //Invocacion : EncuestaController.abrirDialogoCuentas
  public List<EncuestaCuentas> buscarCuentas(String codCliente,String TipoEncuesta)
  {
      //Lista con modelo EncuestaCuentas
      List<EncuestaCuentas> informacion=new ArrayList();
      
      //Declaracion de variable de iteracion de datos
      ResultSet rs;
      
      //Declaracion objeto tipo EncuestaCuentas
      EncuestaCuentas obj;
      
      //Declaracion de query
      //Se filtra el codigo de cliente = parametro #1  
      //Se filtran cuentas segun el tipo de encuesta enviado en el parametro #2
      //Se filtran solo las cuentas/prestamos que esten activas o se hayan cancelado en el dia
      String sql="SELECT S.CUENTA,S.PRODUCTO,P.C6251,S.USUTOPAZ,NVL(R.NOMBRE,'TOP') FROM SALDOS S \n"
                 +"LEFT JOIN AU_RELFUNCIONARIOUSR R ON R.USUARIOTOPAZ=S.USUTOPAZ AND R.TZ_LOCK=0 \n"
                 +"INNER JOIN CO_PRODUCTOS P ON P.C6250=S.PRODUCTO AND P.TZ_LOCK=0 \n"
                 +"WHERE S.C1803="+codCliente+" AND S.TZ_LOCK=0 \n"
                 +"AND S.CUENTA NOT IN(SELECT E.CUENTA FROM CL_ENCUESTA_CLIENTE E) \n"
                 +"AND S.C9314"+ (TipoEncuesta.equals("P") ? "=5" : " IN(3,4)")+"\n"
                 +"AND S.C1604"+(TipoEncuesta.equals("P") ? "<0" : ">0")+"\n"
                 +"UNION ALL \n"
                 +"SELECT S.CUENTA,S.PRODUCTO,P.C6251,S.USUTOPAZ,NVL(R.NOMBRE,'TOP') FROM SALDOS S \n"
                 +"LEFT JOIN AU_RELFUNCIONARIOUSR R ON R.USUARIOTOPAZ=S.USUTOPAZ AND R.TZ_LOCK=0 \n"
                 +"INNER JOIN CO_PRODUCTOS P ON P.C6250=S.PRODUCTO AND P.TZ_LOCK=0 \n"
                 +"WHERE S.C1803="+codCliente+" AND S.TZ_LOCK=0 \n"
                 +"AND S.CUENTA NOT IN(SELECT E.CUENTA FROM CL_ENCUESTA_CLIENTE E) \n"
                 +"AND S.C9314"+ (TipoEncuesta.equals("P") ? "=5" : " IN(3,4)")+"\n"
                 +"AND S.C1604=0\n"
                 +"AND (S.C1624=(SELECT FECHAPROCESO FROM PARAMETROS)\n"
                 +"OR MONTHS_BETWEEN((SELECT FECHAPROCESO FROM PARAMETROS),S.C1624)<=36)";
      
      System.out.println(sql);
      
      try{
          //Conexion
            m.Conectar();
            
            //Declaracion de variable de ejecucion
            PreparedStatement st = m.getCon().prepareCall(sql);
            
            //Ejecucion query
            rs = st.executeQuery();
            
           //Iteracion en los datos devueltos 
           while(rs.next())
           {
                //Seteo de funciones set del modelo EncuestaCuentas
               obj=new EncuestaCuentas();
               obj.setCuenta(rs.getString(1));
               obj.setProducto(rs.getString(2));
               obj.setNombreProducto(rs.getString(3));
               obj.setUsuarioTopaz(rs.getString(4));
               obj.setNombreUsuario(rs.getString(5));
               
               //Agregar elementos a la lista
               informacion.add(obj);
           }
           //Cerrar variables
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("Error al listar cuentas Cliente" + e+"\n"+sql);

        } finally {
          //Cerrar conexion
            m.Cerrar();
        }
      
      //Retornar lista
      return informacion;
  }   
  
  //Funcion: Lista las opciones de las preguntas de la encuesta
  //Parametros: 1: Cod Encuesta
  //Invocacion:EncuestaController.getDatosEncuesta
  public List<ClEncuestaPreguntaOpcion> listarOpciones(Integer filtroEncuesta)
  { 
      //Lista con modelo ClEncuestaPreguntaOpcion
      List<ClEncuestaPreguntaOpcion> informacion= new ArrayList();
      
      //Declaracion de variable de iteracion de datos
      ResultSet rs;
      
      //Declaracion objeto tipo ClEncuestaPreguntaOpcion
      ClEncuestaPreguntaOpcion obj;
      
      //Declaracion de query
      //Se filtran opciones por parametro #1
      String sql="SELECT OP.COD_PREGUNTA_OPCION,OP.COD_ENCUESTA_PREGUNTA,OP.NOMBRE,OP.JUSTIFICACION \n" 
              +"FROM CL_ENCUESTA_PREGUNTA_OPCION OP \n" 
              +"INNER JOIN CL_ENCUESTA_PREGUNTA EP ON EP.COD_ENCUESTA_PREGUNTA=OP.COD_ENCUESTA_PREGUNTA \n" 
              +"WHERE EP.COD_ENCUESTA="+filtroEncuesta+"\n"
              +"ORDER BY EP.COD_ENCUESTA_PREGUNTA,OP.ORDEN";
      try{
          //Conexion
            m.Conectar();
            
            //Declaracion de variable de ejecucion
            PreparedStatement st = m.getCon().prepareCall(sql);
            
            //Ejecucion query
            rs = st.executeQuery();
            
           //Iteracion en los datos devueltos
           while(rs.next())
           {
               //Seteo de funciones set del modelo ClEncuestaPreguntaOpcion
               obj=new ClEncuestaPreguntaOpcion();
               obj.setCodPreguntaOpcion(rs.getInt(1));
               obj.setCodEncuestaPregunta(rs.getShort(2));
               obj.setNombre(rs.getString(3));
               obj.setJustificacion(rs.getShort(4));
               
               //Agregar elementos a la lista
               informacion.add(obj);
           }
           //Cerrar variables
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("error listar info opciones\n" + e+"\n"+sql);

        } finally {
          //Cerrar conexion
            m.Cerrar();
        }
      
      //Retornar lista
      return informacion;
  }
  
  /*Funcion: Invoca la funcion de guardado de datos, retorna un map con las key: completado: con el valor falso si no se guardo informacion en la bd y 
    true si el guardado en la bd fue exitoso; id: con el valor RETURN_GENERATED_KEYS asignado a la encuesta
    Parametros: 1 Map principal con respuestas 2-Map con justificaciones
    Invocacion:EncuestaController.insertarInformacion*/
  public Map<String,String> insertarDatos(Map<String, Object> mapVal,Map<Integer,String> mapJus) throws SQLException
  {
      //Declaracion de variable
       Map<String,String> completado;
      try
      {
          //Abrimos conexcion
          m.Conectar(); 
          //Seteamos el comit en falso, lo cual significa que hara comit hasta que nosotros lo hagamos manuealmente
          //Con esta linea prevenimos que se ingresen datos en tablas cuando den error trasacciones posteriores, es decir no se guardada nada en bd
          //hasta que todas las trasacciones hayan pasado con true
          m.getCon().setAutoCommit(false);
          //Invocacamos funcion de guardado
          //Parametros: 1 Map principal con respuestas 2-Map con justificaciones
         completado= transaccion(mapVal,mapJus);
      }
      finally
      {
          //Cerramos conexion
          m.getCon().close();
          m.Cerrar();
          System.out.println("Cerrando Conexiones");
      }
      
      //Retornamo map
      return completado;
  }
  
  
  /*Funcion: Insercion de datos en BD,retorna un map con las key: completado: con el valor falso si no se guardo informacion en la bd y 
    true si el guardado en la bd fue exitoso; id: con el valor RETURN_GENERATED_KEYS asignado a la encuesta
    Parametros: 1 Map principal con respuestas 2-Map con justificaciones*/
  public Map<String,String> transaccion(Map<String, Object> mapVal,Map<Integer,String> mapJus) throws SQLException
  {
      /*
        mapVal: key: cod pregunta
                value: cod pregunta opcion
      
        mapJus: key: cod pregunta opcion
                value: texto justificacion
      */
      
      //Declaracion de variables de iteracion de datos
      ResultSet rs,rsp;
      
      //Declaracion de variable: Almacena el RETURN_GENERATED_KEYS de la insercion a la tabla CL_ENCUESTA_CLIENTE
      int idEncuesta;
      
      //Declaracion map que se retornara
      Map<String,String> retorno = new HashMap();
      //mapJus.forEach((key,value) ->{System.out.println("key "+key+" value "+value);});
      
      //Declaracion de variables: Almacenan si el valor de respuesta del map princial  contiene letras o espacios en blanco
      boolean letterValue,spaceValue,completado;
      
      //Declaracion de Variable: Almacena que tipo de dato es el valor de respuesta de map principal
      String tipoDato;
      
      //Declaracion de variables: Variable de ejecucion
      PreparedStatement psEncuesta=null,psRespuestas=null,psResOpcion=null,psTcDirecciones=null;
      
      //Variables que se solicitara que retorne la ejecucion
      String returnID[] = {"cod_encuesta_cliente"};
      String returnIDP[] = {"cod_respuesta"};
      
      //Sentencias SQL
      final String sqlEncuestaCliente=("INSERT INTO CL_ENCUESTA_CLIENTE(FECHA, COD_ENCUESTA, COD_CLIENTE, CUENTA, TELEFONO, CELULAR, E_MAIL,USUARIO) VALUES(To_date(current_date,'dd-MM-yy'),?,?,?,?,?,?,?)");  
      final String sqlEncuestaRespuesta=("INSERT INTO CL_ENCUESTA_RESPUESTA (COD_ENCUESTA_CLIENTE, COD_ENCUESTA_PREGUNTA) VALUES (?, ?)");
      final String sqlEncuestaResOpcion=("INSERT INTO CL_ENCUESTA_RESPUESTA_OPCION(COD_RESPUESTA, COD_PREGUNTA_OPCION, JUSTIFICACION) VALUES (?, ?, ?)");
      final String sqlTcDirecciones=("UPDATE TC_DIRECCIONES DIR SET DIR.CELULAR= ? "+(mapVal.get("Email").equals("") ? "" :", DIR.EMAIL=?")+" WHERE DIR.ID= ? AND (DIR.FORMATO='C' OR DIR.FORMATO = 'P') AND DIR.TIPODIRECCION = 'R'");
      
      try
      {
        //INSERCION: ENCABEZADO ENCUESTA: TABLA CL_ENCUESTA_CLIENTE
          //Sintaxis prepareStatement: 1- Query que se ejecutara 2- Lista de campos que esperamos que nos retorne la ejecucion
          psEncuesta = m.getCon().prepareStatement(sqlEncuestaCliente,returnID);
          
          //Ingreso de datos que se enviaran
          psEncuesta.setInt(1,(Integer)mapVal.get("Encuesta"));
          psEncuesta.setLong(2, Long.valueOf((String)mapVal.get("Cliente")));
          psEncuesta.setLong(3, Long.valueOf((String)mapVal.get("Cuenta")));
          //Si el dato es null, se agrega la propiedad null
          if(mapVal.get("Telefono")==null)
          {
              psEncuesta.setNull(4, Types.INTEGER);
          }
          else{
              psEncuesta.setInt(4, (int) mapVal.get("Telefono"));
          }
          //Continuacion de ingreso de datos
          psEncuesta.setInt(5, (int) mapVal.get("Celular"));
          psEncuesta.setString(6,(String)mapVal.get("Email"));
          psEncuesta.setString(7, (String)mapVal.get("User"));
          
          //Ejecutamos Sentencia
          psEncuesta.executeUpdate();
          
          //Lectura de la campos devueltos por la ejecucion 
          //Este campo nos devuelve el ID que fue otorgado por la senquencia CL_S_ENC_CLI a la Encuesta
          rs = psEncuesta.getGeneratedKeys();
          rs.next();
          idEncuesta=rs.getInt(1);
          
          //Actualizamos los datos del cliente celular e Email en la tabla TC_DIRECCIONES 
          if(mapVal.containsKey("Celular"))
          {
              //Preparacion prepareStatement:
              psTcDirecciones=m.getCon().prepareStatement(sqlTcDirecciones);
              
              //Se ingresa el primer dato
              psTcDirecciones.setInt(1,(int) mapVal.get("Celular"));
              
              //Se evalua si el key email es igual a vacio
              if(mapVal.get("Email").equals(""))
              {
                  //Si es vacio, solo se envia el parametro codigo de cliente
                  psTcDirecciones.setLong(2, Long.valueOf((String)mapVal.get("Cliente")));
              }
              else
              {
                  //Si no lo es igual a vacio, se envia el dato del email y el codigo del cliente
                  psTcDirecciones.setString(2,(String)mapVal.get("Email"));
                  psTcDirecciones.setLong(3, Long.valueOf((String)mapVal.get("Cliente")));
              }
              //Se ejecuta el update
              psTcDirecciones.executeUpdate();
          }
          
          
          System.out.println("Inserted record's ID: " + rs.getInt(1));
          
            //Limpiamos el Map con las claves que no necesitamos
            mapVal.remove("Encuesta");
            mapVal.remove("Cliente");
            mapVal.remove("Cuenta");
            mapVal.remove("Fecha");
            mapVal.remove("Telefono");
            mapVal.remove("Celular");
            mapVal.remove("Email");
            mapVal.remove("User");
            
            
        //INSERCION DE RESPUESTAS: TABLA CL_ENCUESTA_RESPUESTA  
        
          //Sintaxis prepareStatement: 1- Query que se ejecutara 2- Lista de campos que esperamos que nos retorne la ejecucion
          psRespuestas=m.getCon().prepareStatement(sqlEncuestaRespuesta,returnIDP);
          
          //Ingreso de datos que se enviaran. Iteracion sobre el map principal
          /*En esta iteracion se ingresa una a una las respuestas, el flujo de insercion es : Insercion de respuesta CL_ENCUESTA_RESPUESTA, obtenemos el RETURN_GENERATED_KEYS
            otorgado por la secuencia CL_S_ENC_RES, revisamos si existe justificacion de esa respuesta en el map de justifiaciones(mapJus) si existe insertamos la justificacion en la tabla
            en CL_ENCUESTA_RESPUESTA_OPCION con el RETURN_GENERATED_KEYS otorgado para esa respuesta*/
          for (Map.Entry<String, Object> entry : mapVal.entrySet()) 
          {
               //Validamos si el value del map contiene letras o espacios
               letterValue=entry.getValue().toString().chars().anyMatch(Character::isLetter);
               spaceValue=entry.getValue().toString().chars().anyMatch(Character::isSpaceChar);
               
               //Validamos si el value del map es tipo arreglo, debido a que necesita otro tipo de iteracion sus datos
               tipoDato= entry.getValue().getClass().getSimpleName();
               
               System.out.println("llave "+entry.getKey());
                 
               //Seteamos en primera posicion el id de la encuesta 
                 psRespuestas.setInt(1, idEncuesta);
                 
               //Seteamos en segunda posicion el valor del key: el cual en nuestro map principal es el codigo de la pregunta  
                 psRespuestas.setInt(2,Integer.valueOf(entry.getKey()));
                 
               //Ejecutamos Sentencia  
                 psRespuestas.executeUpdate();
                 
               ////Lectura de la campos devueltos por la ejecucion 
               //Este campo nos devuelve el ID que fue otorgado por la senquencia CL_S_ENC_RES a la Respuesta
                 rsp=psRespuestas.getGeneratedKeys();
                 rsp.next();
                 System.out.println("Inserted record's ID: " + rsp.getInt(1));
                 
                 
                 //INSERCION DE OPCIONES : TABLA CL_ENCUESTA_RESPUESTA_OPCION
                 
                 //Sintaxis prepareStatement: 1- Query que se ejecutara
                 psResOpcion=m.getCon().prepareStatement(sqlEncuestaResOpcion);
                 
                //Si el value es tipo lista 
                if(tipoDato.equals("String[]"))
                {
                    System.out.println("Lista");
                    
                    //Seteamos variable con la lista almacenada en value
                    String [] lista=  (String[]) entry.getValue();
                    
                    //Iteramos la lista
                    for(String cadena:lista) 
                    {
                       System.out.println("Valor lista "+cadena);
                       
                       //Seteamos en primera posicion el codigo RETURN_GENERATED_KEYS de la respuesta 
                       psResOpcion.setInt(1, rsp.getInt(1)); 
                       
                       //Seteamos en segunda posicion el codigo del la opcion elegida como respuesta
                       psResOpcion.setInt(2, Integer.valueOf(cadena));
                       
                       //Validamos si existe justificacion para esa opcion
                        if(mapJus.containsKey(Integer.valueOf(cadena)))
                        { 
                            System.out.println("Si tiene justi "+mapJus.get(Integer.valueOf(cadena)));
                            
                            //Si existe justificacion seteamos en la posicion 3 el dato
                            psResOpcion.setString(3, mapJus.get(Integer.valueOf(cadena)));
                        }
                        else
                        {
                            //Si no existe justifiacion seteamos la posicion 3 en null
                          psResOpcion.setNull(3, Types.VARCHAR);
                        }
                        
                        //Ejecutamos sentencia 
                      psResOpcion.executeQuery();  
                    }  
                }
                else
                {
                    System.out.println("Dato normal");
                    
                    //Seteamos en primera posicion el codigo RETURN_GENERATED_KEYS de la respuesta 
                    psResOpcion.setInt(1, rsp.getInt(1));
                    
                    //Validamos el valor de las variables
                    //Especificamente buscamos aquellas respuestas a las preguntas que son respuesta texto
                    if(letterValue==true || spaceValue==true)
                    {
                        System.out.println("text Area");
                        //Seteamos en segunda posicion null, estas preguntas no tienen opciones
                        psResOpcion.setNull(2, Types.INTEGER);
                        //Seteamos en tercera posicion el value 
                        psResOpcion.setString(3, (String) entry.getValue());
                    }
                    else
                    {
                        //Seteamos en primera posicion el codigo RETURN_GENERATED_KEYS de la respuesta 
                        psResOpcion.setInt(2, Integer.valueOf((String) entry.getValue()));
                        
                        //Validamos si existe justificacion para esa opcion
                        if(mapJus.containsKey(Integer.valueOf((String) entry.getValue())))
                        {
                            System.out.println("tiene jus "+ mapJus.get(Integer.valueOf((String) entry.getValue())));
                            
                            //Si existe justificacion seteamos en la posicion 3 el dato
                            psResOpcion.setString(3, mapJus.get(Integer.valueOf((String) entry.getValue())));
                        }
                        else
                        { 
                            //Si no existe justifiacion seteamos la posicion 3 en null
                            psResOpcion.setNull(3, Types.VARCHAR);
                        }
                    } 
                   
                   //Ejecutamos sentencia  
                   psResOpcion.executeUpdate();
                }
          }
          
          System.out.println("Ejecutandose");
          
          //Se comitean los datos
          //Si existio una exepcion en alguna de las transaccionees anteriores, los datos no se comitean
          //Esta opcion solo se ejecuta si todas las trasacciones anteriores no generaron exepciones
          m.getCon().commit();
          
          //Seteo map que se retorna, completado: true si no hubieron exepciones id: id encuesta
          retorno.put("completado", "true");
          retorno.put("id", String.valueOf(idEncuesta));
          completado=true;
      }catch(SQLException e){
          //Captura de exepciones
          
          //Se ejecuta rorollback, eliminacion de elementos insertados
          m.getCon().rollback();
          //Impresion de exepcion LOG GLASSFISH
          e.printStackTrace();
          
          //Impresion de erro en pantalla, message parte superior derecha
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + e));
          
          //Seteo de map que se retorna,conpletado: false si hubieron exepciones
          retorno.put("completado", "false");
      } 
      catch(NumberFormatException n)
      {
          //Se ejecuta rorollback, eliminacion de elementos insertados
          m.getCon().rollback();
          n.printStackTrace();
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + n));
          
          //Seteo de map que se retorna,conpletado: false si hubieron exepciones
          retorno.put("completado", "false");
      }
      catch(NullPointerException nu)
      {
          //Se ejecuta rorollback, eliminacion de elementos insertados
          m.getCon().rollback();
          nu.printStackTrace();
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + nu));
          
          //Seteo de map que se retorna,conpletado: false si hubieron exepciones
          retorno.put("completado", "false");
      }
      finally{
          //Cerrado de variable si son diferentes a null
          if(psEncuesta != null){
               psEncuesta.close();
          }
          if(psRespuestas !=null)
          {
              psRespuestas.close();
          }
          
          if(psResOpcion !=null)
          {
              psResOpcion.close();
          }
      }
      
      //Retorno de map
      return retorno;
  }
   //Funcion: Enlista las encuestas creadas.
  //Parametros : Cod agencia, cod puesto, fecha desde, fecha hasta
  //Invocacion: EncuestaController.buscarLstEncuestasCreadas
  public List<EncuestasCreadas> listarEncuestasCreadas(String agencia,String puesto,String filtroFechaR1,String filtroFechaR2)
  { 
        //Declaracion lista modelo String
       List<EncuestasCreadas> lista=new ArrayList();
       
       //Declaracion de variable de iteracion de datos
       ResultSet rs;
       
       //Declaracion objeto tipo EncuestasCreadas
       EncuestasCreadas obj;
        
       //Declaracion de query
       //Se filtra informacion del cliente por parametro #1
        String sql="SELECT EC.COD_ENCUESTA_CLIENTE,\n" +
                    "EC.FECHA,\n" +
                    "EC.COD_CLIENTE,\n" +
                    "EC.CUENTA,\n"+
                    "EC.COD_ENCUESTA,\n"+
                    "E.NOMBRE,\n"+
                    "EC.USUARIO \n" +
                    "FROM CL_ENCUESTA_CLIENTE EC\n" +
                    "INNER JOIN AU_RELFUNCIONARIOUSR RF ON RF.USUARIOTOPAZ=EC.USUARIO\n" +
                    "INNER JOIN CL_ENCUESTA E ON E.COD_ENCUESTA=EC.COD_ENCUESTA\n"+
                    "INNER JOIN RH_V_EMPLEADOS_ACTIVOS RHE ON RHE.codigo_empleado=RF.CODFUNCIONARIO\n" +
                    "WHERE\n"+
                    "EC.FECHA >=to_date("+filtroFechaR1+",'dd-MM-yyyy')\n"+
                    "AND EC.FECHA <=to_date("+filtroFechaR2+",'dd-MM-yyyy')\n"+
                    (puesto.equalsIgnoreCase("98") ? "" :"AND RHE.codigo_agencia="+agencia+"\n") +
                    "ORDER BY EC.COD_ENCUESTA_CLIENTE DESC";
        
        System.out.println(sql);
        try {
            //Conexion
            m.Conectar();
            
            //Declaracion de variable de ejecucion
            PreparedStatement st = m.getCon().prepareCall(sql);
            
            //Ejecucion query
            rs = st.executeQuery();
            
           //Iteracion en los datos devueltos 
           while(rs.next())
           {
               //Seteo de funcion set del modelo EncuestasCreadas
               obj= new EncuestasCreadas();
               obj.setCodigoEncuestaCliente(rs.getString(1));
               obj.setFecha(rs.getDate(2));
               obj.setCodCliente(rs.getString(3));
               obj.setCuenta(rs.getString(4));
               obj.setCodEncuesta(rs.getString(5));
               obj.setTipoEncuesta(rs.getString(6));
               obj.setUsuario(rs.getString(7));
               
               //Agregar elementos a la lista
               lista.add(obj);   
           }
           //Cerrar variables
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("error listar encuestas creadas" + e+"\n"+sql);

        } finally {
            //Cerrar conexion
            m.Cerrar();
        }
        
      //Retornar lista  
      return lista;    
  };
  
}     


       /* mapVal.forEach((key, value)->{
            String tipoDato= value.getClass().getSimpleName();

            System.out.println("Pregunta "+key+" respuestas:");
            if(tipoDato.equals("String[]")){
                
                String [] lista=  (String[]) value;
               for(String cadena:lista) {
                    System.out.println(cadena);
                 }   
            }else{
                System.out.println(value);
            }      
         });}*/
