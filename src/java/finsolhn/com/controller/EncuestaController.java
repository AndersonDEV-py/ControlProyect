
/*
 * CLASE ENCUESTA CONTROLLER
 * BEAN =  ENCUESTA.XHTML
 * 
 */
package finsolhn.com.controller;


import finsolhn.com.dao.EncuestaDAO3;
import finsolhn.com.dao.MasterPrintDAO;
import finsolhn.com.data.ClEncuesta;
import finsolhn.com.data.ClEncuestaPregunta;
import finsolhn.com.data.ClEncuestaPreguntaOpcion;
import finsolhn.com.ejb.ClEncuestaFacadeLocal;
import finsolhn.com.model.EncuestaCuentas;
import finsolhn.com.model.EncuestasCreadas;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author ds010106
 */

@Named("encuestaController")
@ViewScoped

public class EncuestaController extends DataGeneralController{
    
//Clase que contiene las funciones de lecturas de datos a BD
EncuestaDAO3 dao3= new EncuestaDAO3();

//Listar Encuestas
private List<ClEncuesta> lstEncuesta;
//Listar Cuentas
private List<EncuestaCuentas> lstCuentas;
//Listar Encuestas Creadas
private List<EncuestasCreadas> lstEncuestasCreadas;
//Listar todas las preguntas de la encuesta seleccionada
private List<ClEncuestaPregunta> lstEncuestaPregunta;
//Listar todas las opciones de preguntas de la encuesta seleccionada
private List<ClEncuestaPreguntaOpcion> lstEncuestaPO;
//Lista filtro de las opciones de la pregunta
private List<ClEncuestaPreguntaOpcion> opciones;
//Lista informacion Cliente
private List<String> infoCliente;
//Lista que almacena las opciones no elegidas para luego ser eliminadas del map de justificaciones
private List<Integer> opcionesNoElegidas = new ArrayList();
//Objeto que obtiene del dialogo la cuenta seleccionada del cliente
private EncuestaCuentas objeto=new EncuestaCuentas();
//Objeto de la clase encuesta
private ClEncuesta clencuesta;
//Strings
private String nombreC="",nAgencia="",nombreJA="",email="",fecha,filtroEncuesta="",filtroTEncuesta="",msj,texto,codCliente="";
//Integers
private Integer telefono,celular,find=0;
//Dates
private Date filtroFechaR1;
private Date filtroFechaR2;
//Boolean
private Boolean continuar;
/*Maps: mapVal=Contiene las respuestas seleccionadas mapJus:Contiene las justificaciones de las respuestas seleccionadas 
        confirmacion: almacena lo retornado desde encuestaDao3.insertarDatos */
private Map<String, Object> mapVal;
private Map<Integer,String> mapJus= new HashMap();
private Map<String,String> confirmacion=new HashMap();

//Objeto de la clase ClEncuestaFacade
@EJB
ClEncuestaFacadeLocal encuestaEJB;

//FUNCIONES SET Y GET DE LAS VARIABLES
    public Map<String, Object> getMapVal() {
        return mapVal;
    }

    public void setMapVal(Map<String, Object> mapVal) {
        this.mapVal = mapVal;
    }

    public Map<Integer, String> getMapJus() {
        return mapJus;
    }

    public void setMapJus(Map<Integer, String> mapJus) {
        this.mapJus = mapJus;
    }
    
    public String getnAgencia() {
        return nAgencia;
    }

    public void setnAgencia(String nAgencia) {
        this.nAgencia = nAgencia;
    }
     
    public String getTexto() {
       return texto;
    }

    public void setTexto(String texto) {
       this.texto = texto;
    }

    public List<ClEncuestaPregunta> getLstEncuestaPregunta() {
        return lstEncuestaPregunta;
    }

    public void setLstEncuestaPregunta(List<ClEncuestaPregunta> lstEncuestaPregunta) {
        this.lstEncuestaPregunta = lstEncuestaPregunta;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getFind() {
        return find;
    }

    public void setFind(Integer find) {
        this.find = find;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFiltroEncuesta() { 
        
        return filtroEncuesta;
    }

    public void setFiltroEncuesta(String filtroEncuesta) {
        this.filtroEncuesta = filtroEncuesta;
    }
    
     public String getFiltroTEncuesta() {
        return filtroTEncuesta;
    }

    public void setFiltroTEncuesta(String filtroTEncuesta) {
        this.filtroTEncuesta = filtroTEncuesta;
    }
   
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getNombreC() {
        return nombreC;
    }

    public String getNombreJA() {
        return nombreJA;
    }
    
    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }
    
     public List<ClEncuestaPreguntaOpcion> getLstEncuestaPO() {
        return lstEncuestaPO;
    }

    public void setLstEncuestaPO(List<ClEncuestaPreguntaOpcion> lstEncuestaPO) {
        this.lstEncuestaPO = lstEncuestaPO;
    }
    
    public List<EncuestaCuentas> getLstCuentas() {
        return lstCuentas;
    }
   
    public void setLstCuentas(List<EncuestaCuentas> lstCuentas) {
        this.lstCuentas = lstCuentas;
    }
    
    public EncuestaCuentas getObjeto() {
        return objeto;
    }

    public void setObjeto(EncuestaCuentas objeto) {
        this.objeto = objeto;
    }
    
    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }
    
    public void setLstEncuesta(List<ClEncuesta> lstEncuesta) {
        this.lstEncuesta = lstEncuesta;
    }

    public List<EncuestasCreadas> getLstEncuestasCreadas() {
        return lstEncuestasCreadas;
    }

    public void setLstEncuestasCreadas(List<EncuestasCreadas> lstEncuestasCreadas) {
        this.lstEncuestasCreadas = lstEncuestasCreadas;
    }

    public Date getFiltroFechaR1() {
        return filtroFechaR1;
    }

    public void setFiltroFechaR1(Date filtroFechaR1) {
        this.filtroFechaR1 = filtroFechaR1;
    }

    public Date getFiltroFechaR2() {
        return filtroFechaR2;
    }

    public void setFiltroFechaR2(Date filtroFechaR2) {
        this.filtroFechaR2 = filtroFechaR2;
    }
    
    
    
    //Obtener Encuestas desde dao3
    public List<ClEncuesta> getLstEncuesta() {
        //se inicializa la lista de las encuestas con las encuestas que se obtienen de la bd
        lstEncuesta = dao3.listarEncuestas();
        return lstEncuesta;
    }
    
    //Constructor Clase EncuestaController
    public EncuestaController()
    {
        if (getUser().getUsuario().isEmpty()) {
            super.verificarSession();
            System.out.println("****"+getClass().getSimpleName()+" Usuario:*"+ getUser().getUsuario() + "* " + getUser().getNombre());
        }
    }
    
    //Metodo de inicializacion de las clases ClEncuesta y el map mapVal
    //@PostConstruct la anotacion, especifica que se ejecutara exactamente despues del constructor
    @PostConstruct
    public void ini(){
        this.clencuesta= new ClEncuesta();
        mapVal = new LinkedHashMap<>();
        
        //inicializamos la lista con las encuestas 
        //lstEncuestasCreadas=dao3.listarEncuestasCreadas(getUser().getC_agencia(),getUser().getC_puesto(),"","");
    }
    
    
    //Funcion: Busca las encuestas creadas 
     public List<EncuestasCreadas> buscarLstEncuestasCreadas() {
        
        //Formato de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        //Variable que obtiene la fecha actual
        Date myDate = new Date();
        
        //Variables que guardan fechas String
        String FechaR1,FechaR2;
        
        //Si fecha 1 no es null 
        if(filtroFechaR1 != null)
        {
            //Inicializamos la fecha con formato 
            FechaR1 = "'"+sdf.format(filtroFechaR1)+"'";
        }
        else{
            //Si no se ingreso una fecha se envia la fecha de hoy
            FechaR1="'"+sdf.format(myDate)+"'";
        }
        //Si fecha 2 no es null 
        if(filtroFechaR2 != null)
        {
            //Inicializamos la fecha con formato 
           FechaR2 = "'"+sdf.format(filtroFechaR2)+"'";
        }        
        else{
            //Si no se ingreso una fecha se envia la fecha de hoy
            FechaR2="'"+sdf.format(myDate)+"'";
        }

        //obtencion de las encuestas
        lstEncuestasCreadas=dao3.listarEncuestasCreadas(getUser().getC_agencia(),getUser().getC_puesto(),FechaR1,FechaR2);
         
         //retornarmos lista
        return lstEncuestasCreadas;
    }
    
    //Funcion llamada desde GENERAR ENCUESTA
    //Nuevo: Se limpian resgistros anteriores, se leen las preguntas y las opciones de la encuesta seleccionada
    public void nuevo()
    {
        //Limpiar registros
        limpiarRegistros();
        //Objeto de tipo PrimeFaces 
        PrimeFaces current = PrimeFaces.current();
        //Creacion de variable tipo FacesMessage 
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje", " No ha seleccionada ninguna Encuesta"+ "");
        
        //Validacion de que la encuesta no sea 0
        if(filtroEncuesta.equals("0"))
        {
             current.executeScript("PF('wdlEncuesta').hide();");
             current.dialog().showMessageDynamic(message); /*Comentado por kevin anderson*/
        }else
        {
            //Ejecucion del dialogo
             PrimeFaces.current().executeScript("PF('wdlEncuesta').show()");
            //PrimeFaces.current().executeScript("PF('wdlPdf').show()");
            
             //Obtencion de la fecha actual
             Date myDate = new Date();
             //Seteo de la fecha
             this.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(myDate));
             
             //Funcion que genera datos de la encuesta seleccionada
             //Parametros: 1 - Filtro encuesta seleccionada
             this.getDatosEncuesta(Integer.valueOf(filtroEncuesta));    
        }
    }
    
    //Funcion: Se obtienen los datos necesarios de la encuesta seleccionada
    //Parametros: 1 - Filtro encuesta seleccionada
    public void getDatosEncuesta(int filtro)
    {
        //Filtrado de la datos de la encuesta
        lstEncuesta.stream().filter(lista -> (lista.getCodEncuesta()== filtro)).map(lista -> {
            //Seteo del nombre de la encuesta seleccionada
            this.setTexto(lista.getNombre());
            return lista;
            }).forEachOrdered(lista -> {
            //Seteo del tipo de encuesta
            this.setFiltroTEncuesta(lista.getTipoEncuesta());
         });

        //Obtencion de las preguntas
         lstEncuestaPregunta=dao3.listarPreguntasE(filtro);  
       //Obtencion de las opciones de las preguntas  
         lstEncuestaPO=dao3.listarOpciones(filtro);   
    }
    
    //Funcion: se obtiene la informacion del cliente. Es llamada desde el boton btmConsultar 
    public void buscarCliente()
    {
        //System.out.println("Si entro aqui");
        //Objeto FacesMessage para mostrar mensaje error
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje", " No se encontro ningun cliente con ese Codigo"+ "");

        //Obtencion datos del cliente, Datos seteados en lista
        //Parametros 1: cod cliente ingresado
        infoCliente=dao3.buscarCL(getCodCliente());
        
        //Validacion de datos cliente
        if(infoCliente.isEmpty())
        {
            PrimeFaces.current().dialog().showMessageDynamic(message);/*Comentado por kevin*/
        }else{
            //Seteo de variables con informacion del objeto infoCliente
            nombreC=infoCliente.get(0);//nombre cliente
            nAgencia=infoCliente.get(1)+" - "+infoCliente.get(2);// Cod Agencia - Nombre Agencia
            nombreJA=infoCliente.get(3);// Jefe de agencia
            //Deshabilitar item cliente
           PrimeFaces.current().executeScript("PF('itCodCliente').disable(false);");
        }
    }
    
    //Funcion : Aplica filtro para obtener las opciones de la pregunta, retorna las opciones en un objeto. Es llamada en los elementos selectItems
    //Parametros : 1- Id de la pregunta
    public List<ClEncuestaPreguntaOpcion> obtenerOpciones(int codPRegunta)
    {
        //Aplica filtro de las opciones segun id de la pregunta
         opciones =  lstEncuestaPO.stream().filter(p-> p.getCodEncuestaPregunta()==codPRegunta).collect(Collectors.toList());
                // System.out.println(opciones);
         return opciones;
    }
       
    //Funcion: Muestra el dialogo de cuentas del cliente
    public void abrirDialogoCuentas()
    {
        //Valida si se ha ingresado un cliente
        if(codCliente==null || nombreC.isEmpty())
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje", " No se ha ingresado Codigo de Cliente"+ "");
            PrimeFaces.current().dialog().showMessageDynamic(message); /*Comentado por kevin anderson*/
        }else{
            //Obtener las cuentas del cliente
            //Parametros : 1- cod cliente 2- tipo de encuesta
            try {
                lstCuentas=dao3.buscarCuentas(codCliente, filtroTEncuesta);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            
            //Muestra dialogo
            PrimeFaces.current().executeScript("PF('wdlListaCuentas').show();");
        }
    }
   
    
   //Funcion: obtiene los datos de la cuenta seleccionada en el dialogo, oculta el dialogo, deshabilita item del cliente, Es llamada en el dialogo dlgwdlListaCuentas
    //Parametros 1: Objeto cuentas
    public void seleccionar(EncuestaCuentas obj)
    {
        //Seteo de objeto
        this.objeto=obj;
        
        //Mostrar dialogo encuesta
        PrimeFaces.current().executeScript("PF('wdlEncuesta').show();");
        //Deshabilitar item Cliente
        PrimeFaces.current().executeScript("PF('itCodCliente').disable(false);");
    }


    //Funcion: Limpia los registros de las variables,maps u otros elementos
    private void limpiarRegistros() {
        
        //Clean objeto cuentas
        objeto.setCuenta("");
        objeto.setNombreProducto("");
        objeto.setProducto("");
        objeto.setUsuarioTopaz("");
        objeto.setNombreUsuario("");
      
        //Clean variables String
        nombreC="";
        nAgencia="";
        nombreJA="";
        email="";
        
        //Clean Maps
        mapVal.clear();
        mapJus.clear();
        confirmacion.clear();
        
        //Clean variables Int
        telefono=null;
        celular=null;
        codCliente=null; 
    }
    
    //Funcion: Se limpian los datos vacios, null de los maps, se llaman a las funciones de validaciones de datos. Es llamada desde el boton btmGuardar
    public void validacionDatos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {        
        /*
        mapVal: key: cod pregunta
                value: cod pregunta opcion
      
        mapJus: key: cod pregunta opcion
                value: texto justificacion
      */         
        
         //Elimino valores Nulos
         //Se eliminan dichos valores debido que al iterar en el map y existe un valor null, la iteracion dispara una excepcion java.lang.NullPointerException
         while (mapVal.values().remove(null));
        
        //Elimina valores vacios de los String[]
         mapVal.forEach((key, value)->{
            String tipoDato= value.getClass().getSimpleName();
            if(tipoDato.equals("String[]")){
                String [] lista=  (String[]) value;
                if(lista.length==0)
                {
                   mapVal.put(key,""); 
                }
            }      
         });
         
         //Elimina valores vacios
         while (mapVal.values().remove(""));
         while (mapJus.values().remove(""));
         
         //Elimina justificaciones del mapJus que no pertenecen a la respuestas elegidas
         opcionesNoSeleccionadas();

         //Validacion de que las opciones marcadas que requieran justificacion contengan justfificacion correpondiente
         //Si no existe error se ejecuta el envio de datos a guardar
         validacionJustificaciones();

    }
    
    //Funcion: Elimina justificaciones del mapJus que no pertenecen a la respuestas elegidas
    public void opcionesNoSeleccionadas()
    {
        
        /*
        mapVal: key: cod pregunta
                value: cod pregunta opcion
      
        mapJus: key: cod pregunta opcion
                value: texto justificacion
      */
        
        //Clean Lista que almacenara las key de las justificaciones que no deben estar ingresadas
        opcionesNoElegidas.clear();
        
        //Iteracion del map donde se guardan las justificaciones
        mapJus.forEach((keyJus,valueJus)->
         {
             System.out.println("key jus" + keyJus);
             
             //Seteo Variable
             find=0;
             
             //Iteracion de map donde se guardan la opciones respuesta
             mapVal.forEach((keys,values)->{
                 
                //Se obtiene el tipo de dato que contiene el valor. Se trata de identificar si es un arreglo, lista, string,etc.
                String tipoDato= values.getClass().getSimpleName();
                
                //Se identifica si el value contiene letras
                boolean letterValue=values.toString().chars().anyMatch(Character::isLetter);
                
                //Se identifica si el value contiene blankspace
                boolean spaceValue=values.toString().chars().anyMatch(Character::isSpaceChar);
                
                //Se valida si el tipo de value es String[] debido a que necesita otro tipo de lectura
                 if(tipoDato.equals("String[]"))
                 {
                     //Seteo de variable 
                     String [] lista=  (String[]) values;
                     
                     //Iteracion de datos
                    for(String cadena:lista) 
                    {
                        //Si el valor de keyJus == a una opcion seleccionada, se setea una variable con  indicando que la justificacion si debe de guardarse
                        if(keyJus == Integer.valueOf(cadena))
                        {
                            find=1;
                            //System.out.println("Entro");
                        }
                    }
                 }
                 //Se valida que el value no sean letras ni espacios
                 else if(letterValue==false && spaceValue==false)
                 {    
                    //Si el valor de keyJus == a una opcion seleccionada, se setea una variable con  indicando que la justificacion si debe de guardarse
                     if(keyJus == Integer.valueOf((String)values))
                     {
                         find=1;
                        //System.out.println("Entro");
                     }
                 }
             });
             //Valida si el keyJus fue encontrado, si no fue encotrado se guarda su valor en la lista para posteriormente ser liminado
             if(find==0)
             {
                 opcionesNoElegidas.add(keyJus);
             }
         });
        
        //Iteracion de la lista de las justificaciones que deben ser eliminadas
        for(Integer eliminar: opcionesNoElegidas)
        {
            mapJus.remove(eliminar);
        } 
    }
    
    //Funcion: Validacion de que las opciones marcadas que requieran justificacion contengan justfificacion correpondiente
    //Si no existe error se ejecuta el envio de datos a guardar
    public void validacionJustificaciones() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        /*
        mapVal: key: cod pregunta
                value: cod pregunta opcion
      
        mapJus: key: cod pregunta opcion
                value: texto justificacion
      */   
        
        //Seteo de varibale en true, si no llega a existir error se genera el guardado
        continuar=true;
        //System.out.println("Recorriendo justificaciones");
        //mapJus.forEach((key,value)->{System.out.println("key "+key+" value "+value);});

        //Iteracion de map donde se guardan las opciones de las respuestas elegidas
        mapVal.forEach((key , value) ->{
         //System.out.println("key: " +key +" value: "+value);
         
         //Se obtiene el tipo de dato que contiene el valor. Se trata de identificar si es un arreglo, lista, string,etc.
         String tipoDato= value.getClass().getSimpleName();
         
         //Se identifica si el value contiene letras
         boolean letterValue=value.toString().chars().anyMatch(Character::isLetter);
         
         //Se identifica si el value contiene blankspace
         boolean spaceValue=value.toString().chars().anyMatch(Character::isSpaceChar);
         //System.out.println(letterValue+" "+spaceValue);
         
         List<ClEncuestaPregunta> nombrePregunta;

         //Se valida si el tipo de value es String[] debido a que necesita otro tipo de lectura
         if(tipoDato.equals("String[]"))
         {
                //Seteo de variable
               String [] lista=  (String[]) value;
               
               //Iteracion de datos
               for(String cadena:lista) 
               {
                   //Iteracion en lista de las opciones de las preguntas de la encuesta, se aplica un filtro para solo iterar en las opciones que requieran justificacion
                    //System.out.println("Respuesta Lista "+cadena);
                    for(ClEncuestaPreguntaOpcion opcionesPreguntas :lstEncuestaPO.stream().filter(p-> p.getJustificacion()==1).collect(Collectors.toList()))
                    {
                        //Si la cod de la opcion Iterada== al cod de la opcion del map Principal
                        //Es decir si el cod de la opcion que se esta iterando es encontrada en el map principal, significa que esa opcion fue elegida como respuesta,
                        //y al ser una opcion que requiere justificacion deberia existir el codigo de la opcion en el map de justificaciones mapJus
                       // System.out.println("opcion "+opcionesPreguntas.getCodPreguntaOpcion());
                       if(opcionesPreguntas.getCodPreguntaOpcion() == Integer.valueOf(cadena))
                        {
                            //Se procede a buscar le key en el map de justificaciones
                            //System.out.println("Entro");
                            if(!mapJus.containsKey(Integer.valueOf(cadena)))
                            { 
                                //Obtener nombre de la pregunta
                                 nombrePregunta=lstEncuestaPregunta.stream().filter(p->p.getCodEncuestaPregunta().equalsIgnoreCase(String.valueOf(opcionesPreguntas.getCodEncuestaPregunta()))).collect(Collectors.toList());
                                //Si no se encuentra en el map se procede a mostrar un mensaje 
                                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "La pregunta : "+nombrePregunta.get(0)+" , requiere justificacion"));
                                //Seteo en falso para que no guarde informacion
                                continuar=false;
                                return;
                            }
                        }
                    }
                }  
               //Se valida que el value no sean letras ni espacios
         }else if(letterValue==false && spaceValue==false)
                {
                    //Iteracion en lista de las opciones de las preguntas de la encuesta, se aplica un filtro para solo iterar en las opciones que requieran justificacion
                    //System.out.println("Respuesta Normal");
                    for(ClEncuestaPreguntaOpcion opcionesPreguntas :lstEncuestaPO.stream().filter(p-> p.getJustificacion()==1).collect(Collectors.toList()))
                    {
                        //Si la cod de la opcion Iterada== al cod de la opcion del map Principal
                        //Es decir si el cod de la opcion que se esta iterando es encontrada en el map principal, significa que esa opcion fue elegida como respuesta,
                        //y al ser una opcion que requiere justificacion deberia existir el codigo de la opcion en el map de justificaciones mapJus
                        if(opcionesPreguntas.getCodPreguntaOpcion()==Integer.valueOf((String) value))
                        {
                            //Se procede a buscar le key en el map de justificaciones
                            if(!mapJus.containsKey(Integer.valueOf((String) value)))
                            { 
                                //Obtener nombre de la pregunta
                                nombrePregunta=lstEncuestaPregunta.stream().filter(p->p.getCodEncuestaPregunta().equalsIgnoreCase(String.valueOf(opcionesPreguntas.getCodEncuestaPregunta()))).collect(Collectors.toList());
                                //Si no se encuentra en el map se procede a mostrar un mensaje
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "La pregunta : "+nombrePregunta.get(0)+" , requiere justificacion"));
                                //Seteo en falso para que no guarde informacion 
                                continuar=false;
                                 return;
                            }
                        }
                    }
                }         
        });

      //Si la variable == true se procede al guardado de la informacion
      if(continuar == true)
      {
          //Funcion que envia datos a guardar
         insertarInformacion(); 
      }
    }
    
    //Funcion: Valida que los datos del encabezado esten ingresados, se agregan los valores restantes al map principal
    @SuppressWarnings("empty-statement")
    public void insertarInformacion() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException  
    {
        /*
        mapVal: key: cod pregunta
                value: cod pregunta opcion
      
        mapJus: key: cod pregunta opcion
                value: texto justificacion
        */
        System.out.println("codCliente "+codCliente);
        //Validacion que Campos Obligatorios no esten Vacios
        if(codCliente==null)
        {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "AVISO", "No ha ingresado Código de Cliente"));
           return;
        }
        if(objeto.getCuenta().isEmpty())
        {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "AVISO", "No ha ingresado ninguna Cuenta del Cliente"));
           return;
        }
        
        //mapJus.forEach((key,value) -> System.out.print("key "+key+" Value "+value));

        //Valido que el Map Principal no este vacio    
        if(mapVal.isEmpty())
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede enviar la encuesta sin contestar. Conteste las preguntas");
            PrimeFaces.current().dialog().showMessageDynamic(message); /*Comentado por Kevin Anderson*/
        }
        else
        {
            //Se agregan los valores del encabezado faltantes
            mapVal.put("Encuesta", Integer.valueOf(filtroEncuesta));
            mapVal.put("Cliente",codCliente);
            mapVal.put("Cuenta",objeto.getCuenta());
            mapVal.put("Fecha",fecha);
            mapVal.put("Telefono", telefono);
            mapVal.put("Celular", celular);
            mapVal.put("Email", email);
            mapVal.put("User", getUser().getUsuario());
            
            //Invocacion de funcion que envio los datos a guardar
            //Se setea el map confirmacion que almacenara lo retornado por la funcion
            //Parametros: 1-Map Principal 2-Map Justificaciones
            confirmacion=dao3.insertarDatos(mapVal,mapJus);
            
            //confirmacion.forEach((key,value) -> System.out.print("key "+key+" Value "+value));
            
            
            //Si la funcion retorna true
            if(confirmacion.get("completado") == "true")
            {
                //Mensaje de confirmacion
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
                PrimeFaces.current().executeScript("PF('wdlEncuesta').hide();");
                //Se muestra el dlg para impresion    
                PrimeFaces.current().executeScript("PF('wdlPdf').show();");

            }else
            {
                 //Mensaje de error
                 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje", " No se pudo Guardar la informacion ");
                 PrimeFaces.current().dialog().showMessageDynamic(message); /*Comentado por Kevin Anderson*/
            }
        }
    }
    
    //Funcion: Invoca la funcion de impresion de formatos
    public void verDocumentoPDF() throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        
        //System.out.println("Compromiso ID: |"+id);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        
        
        //confirmacion.forEach((key,value) -> System.out.print("key "+key+" Value "+value));
        
        //Declaracion de variable
        String nombreRep="";
        
        //Declaracion de map para envio de parametros
        Map parameter = new HashMap();
        
        //El key del map sera los nombres que tienen los parametros en ireport
        parameter.put("codEncuestaCliente", String.valueOf(confirmacion.get("id")));
        parameter.put("codIdTipoEncuesta", filtroEncuesta);
        
        //Seteo de variable con el nombre del formato
        nombreRep="RespuestasEncuesta";
        
        //Envio de datos a impresion
        MasterPrintDAO print = new MasterPrintDAO();
        print.generarPDFEncuesta(nombreRep, parameter);

    }
    
    //Funcion: Invoca la funcion de impresion de formatos desde la pantalla Listar encuestas creadas    
    public void verDocumentoPDFLista(String codEncuestaCliente,String codIdEncuesta) throws ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        
        //System.out.println("Compromiso ID: |"+id);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        
        
        //Declaracion de variable
        String nombreRep="";
        
        //Declaracion de map para envio de parametros
        Map parameter = new HashMap();
        
        //El key del map sera los nombres que tienen los parametros en ireport
        parameter.put("codEncuestaCliente", codEncuestaCliente);
        parameter.put("codIdTipoEncuesta", codIdEncuesta);
        
        //Seteo de variable con el nombre del formato
        nombreRep="RespuestasEncuesta";
        
        //Envio de datos a impresion
        MasterPrintDAO print = new MasterPrintDAO();
        print.generarPDFEncuesta(nombreRep, parameter);

    }
        
}
