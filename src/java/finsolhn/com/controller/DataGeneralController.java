/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.controller;

import finsolhn.com.dao.EncuestaDAO3;
import finsolhn.com.dao.LoginDAO3;
import finsolhn.com.dao.MetodosSQL2;
import finsolhn.com.dao.MetodosSQL3;
import finsolhn.com.data.AdUsuarios;
import finsolhn.com.data.ClEncuesta;
import finsolhn.com.ejb.AdUsuariosFacadeLocal;
import finsolhn.com.model.Login;
import finsolhn.com.model.Menu;
import finsolhn.com.model.MenuOpcion;
import finsolhn.com.util.Constantes;
import finsolhn.com.util.SendMailNormal;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/*import jakarta.annotation.PostConstruct; se comento pero hay que ver si es de utilidad despues*/
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.annotation.adapters.HexBinaryAdapter;
import org.primefaces.PrimeFaces;
//import org.primefaces.context.RequestContext;
//import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author rcardona
 */
@Named("dataGeneralController")
//@RequestScoped
//@ViewScoped
@SessionScoped
public class DataGeneralController implements Serializable {

    private String url_actual = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI();

    private String imgLoadin = "/images/cargandofinsolgif.gif";

    
    @EJB
    AdUsuariosFacadeLocal usuarioEJB;
    
    LoginDAO3 dao = new LoginDAO3();
    //declaracion de variables y objetos
    private Login user = new Login();
    //private MetodosSQL m = new MetodosSQL();
    private MetodosSQL2 m = new MetodosSQL2();
    private MetodosSQL3 m3 = new MetodosSQL3();
    private String ip;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");//("yyyy-MM-dd");
    SimpleDateFormat sdf_dmy_ham = new SimpleDateFormat("dd/MM/yyyy h:mm a");//("dd/MM/yyyy h:mm a");
    SimpleDateFormat sdf_ymd = new SimpleDateFormat("yyyy/MM/dd");//("yyyy-MM-dd");
    
    SimpleDateFormat sdf_dmy = new SimpleDateFormat("dd/MM/yyyy h:mm a");//("dd/MM/yyyy");
    SimpleDateFormat sdf_ham = new SimpleDateFormat("h:mm a");//("h:mm a");
    ///menu
    private List<Menu> lstMenu;
    private List<MenuOpcion> lstMenuOpcion;//=new ArrayList();
    private Integer indexMenu=0;
    
    private String claveAnterior;
    private String claveNueva;
    
    @PostConstruct
    public void init() {
        //md5("Finsoltest.2020");
        /*if (!isPosBack()) {

            ////System.out.println("Menu No POSBACK");
            if (user.getUsuario().length() == 0) {
                //System.out.println("Ejecuta INI 2");
            }

        }*/
    }
    private boolean ver=true;
    private String ver2="2";
    private boolean verX=true;
    private String ver2X="2";
    public void clave2()
    {
        
        if(ver2.equalsIgnoreCase("1") || ver2.equalsIgnoreCase("2"))
        {
            ver2="1";
        }else{
            ver2="1";
        }
        if(ver)
        {
            ver=false;
        }else{
            ver=true;
        }
    }
    public void clave2X()
    {
        
        if(ver2X.equalsIgnoreCase("1") || ver2X.equalsIgnoreCase("2"))
        {
            ver2X="1";
        }else{
            ver2X="1";
        }
        if(verX)
        {
            verX=false;
        }else{
            verX=true;
        }
    }



    public boolean isVer() {
        return ver;
    }

    public void setVer(boolean ver) {
        this.ver = ver;
    }

    public String getVer2() {
        return ver2;
    }

    public void setVer2(String ver2) {
        this.ver2 = ver2;
    }

    public boolean isVerX() {
        return verX;
    }

    public void setVerX(boolean verX) {
        this.verX = verX;
    }

    public String getVer2X() {
        return ver2X;
    }

    public void setVer2X(String ver2X) {
        this.ver2X = ver2X;
    }

 
    
    
    public void cambiarClave()
    {
       AdUsuarios tmpuser=new AdUsuarios();
       tmpuser.setUsuariotopaz(getUser().getUsuario());
       tmpuser.setContrasenia(md5(claveAnterior));
       //System.out.println("u:"+getUser().getUsuario()); 
       //System.out.println("a:"+md5(claveAnterior));
       AdUsuarios resul=usuarioEJB.validarUsuariosByUserClave(tmpuser);
       
       if(resul==null)
       {
           
           mensajeDialogoWaning("Error", "La clave Anterior es incorrecta");
           return;
       }
       if(pasaValidacion(claveNueva))
       {
          resul.setContrasenia(md5(claveNueva)); 
          if(resul.getEstado().equalsIgnoreCase("2"))
          {    resul.setEstado("1");
               getUser().setEstado("1");
          }
          
          resul.setUltimocambio(new Date());
          resul.setEstadoCrud("MP");
          
          usuarioEJB.edit(resul);
          System.out.println("clave Actualizada"+resul.getUsuariotopaz());
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Clave Actualizada", "Clave Actualizada"));
          //RequestContext context = RequestContext.getCurrentInstance();
          //context.execute("PF('wdlCambioClave').hide();");
          PrimeFaces.current().executeScript("PF('wdlCambioClave').hide();");
          claveAnterior="";
          claveNueva="";
          
       }
       
    
        
    }

    public DataGeneralController() {

        /*if (user.getUsuario().length() == 0) {

            try {
                FacesContext context = FacesContext.getCurrentInstance();
                Login sesion = null;//
                try {
                    sesion = (Login) context.getExternalContext().getSessionMap().get("USUARIO");
                } catch (Exception e) {
                    System.err.println("ERROR con la sesion "+e);
                }

                if (sesion == null) {
                    System.out.println("SE CERRO UNA SESION POR INACTIVIDAD");
                    //context.getExternalContext().redirect("../index.xhtml?faces-redirect=true");
                    try {
                       //context.getExternalContext().redirect("../index.xhtml");
                        //    context.getExternalContext().redirect("/index.xhtml")
                       //verifica();
                    } catch (Exception e) {
                        System.err.println("ERROR con la sesion2 "+e);
                    };
                } else {

                     String uri = ((HttpServletRequest) context.getExternalContext().getRequest()).getRequestURI();
                    if(uri.equalsIgnoreCase("/SistemasWeb/faces/welcomePrimefaces.xhtml"))
                    {
                        RequestContext context2 = RequestContext.getCurrentInstance();
                        context2.execute("PF('sidebar1').show();");  
                    }
                    System.out.println("Ejecuta Contructor 1 sesion de " + user.getUsuario() + "    :" + sesion.getUsuario());
                    user = sesion;
                }
            } catch (Exception e) {
                    System.err.println("Error en la sesion 0 "+e);
            }

        }*/
        ip = getIP();
        //verificarSession();
        if (!isPosBack()) {

        }

    }

    public String getImgLoadin() {
        return imgLoadin;
    }

    public void setImgLoadin(String imgLoadin) {
        this.imgLoadin = imgLoadin;
    }

    public void cerrarSesion() {
        try {
            System.out.println("SALIO DEL SISTEMA: " + getUser().getUsuario() + " | " + getUser().getNombre());
            user.setUsuario("");
            user.setClave("");
            user = null;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO", user);
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("USUARIO");            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("con");// .put("con", source);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("conecto");
            ////System.out.println("Cerrando sesion");
            //return "index.xhtml?faces-redirect=true";

            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect(Constantes.ruta_CONTEXTO);

            /*String uri = ((HttpServletRequest) context.getExternalContext().getRequest()).getRequestURI();
            if (uri.equalsIgnoreCase("/ControlProyect/welcomePrimefaces.xhtml")) {
                 context.getExternalContext().redirect("/ControlProyect/index.xhtml");
            }else{
                context.getExternalContext().redirect("/ControlProyect/");
            }*/
        } catch (Exception e) {
            ////System.err.println("Error al cerrar la sesion");
        }

    }

    public boolean verificaAccesoOpcionUsuario(String opcion) {
        FacesContext context = FacesContext.getCurrentInstance();
        Login usr = (Login) context.getExternalContext().getSessionMap().get("USUARIO");
        //System.out.println("Tamaño de Lista: " + usr.getLstMenuOpcion().size());
        
        if(user!=null)
        if(user.getEstado().equalsIgnoreCase("2")){
                    mensajeDialogoWaning("Sistema", "Se recomienda cambio de clave  Otros Usuarios saben su contraseña :O");
        }
        
        boolean bn = false;
        for (int x = 0; x < usr.getLstMenuOpcion().size(); x++) {

            // System.out.println("1 Comparando: "+"/ControlProyect/faces/"+usr.getLstMenuOpcion().get(x).getUrl());
            // System.out.println(">2 Comparando: "+opcion);
            if (opcion.equalsIgnoreCase(Constantes.ruta_CONTEXTO+"faces/" + usr.getLstMenuOpcion().get(x).getUrl())) {
                
                System.out.println("TIENE ACCESO-VALIDACION#2");
                bn = true;
                break;
            }
        }
        
        
        if ( (Constantes.ruta_CONTEXTO+"welcomePrimefaces.xhtml").equalsIgnoreCase(opcion)
                || (Constantes.ruta_CONTEXTO+"faces/welcomePrimefaces.xhtml").equalsIgnoreCase(opcion)) {
            bn = true;
        }

        return bn;
    }
    public void verificarSessionX(){}
    //verifica constantemente si existe una sesion activa
    public void verificarSession() {
        /* sin uso
        String url = ((HttpServletRequest) context.getExternalContext().getRequest()).getRequestURI();
                    
                            confirmaSesion();
        System.out.println(url);*/

        if (!isPosBack()) {
            //confirmaSesion();
            //System.out.println("Verficar Sesion 3");
            try {  //se obtiene la sesion que contiene el objeto con los datos del usuario
                FacesContext context = FacesContext.getCurrentInstance();
                Login sesion = (Login) context.getExternalContext().getSessionMap().get("USUARIO");
                //MenuOpcion menuo = (MenuOpcion) context.getExternalContext().getSessionMap().get("idEncueta");
               
                //si no existe la sesion redirecciona la pagina hacia el index
                if (sesion == null) {
                    System.out.println("SE CERRO UNA SESION POR INACTIVIDAD");

                    user = null;
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO", user);
                    //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("USUARIO");            
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("con");// .put("con", source);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("conecto");
                    context.getExternalContext().redirect("../index.xhtml?faces-redirect=true");
                    //context.getExternalContext().redirect("/ControlProyect/");
                    //return;
                } else if (!isPosBack()) {
                    String uri = ((HttpServletRequest) context.getExternalContext().getRequest()).getRequestURI();
                    System.out.println(uri);

                    if (context.getExternalContext().getSessionMap().isEmpty()) {
                        System.out.println("esta vacia");
                        context.getExternalContext().redirect("../access.xhtml");
                    }

                    if (uri.equalsIgnoreCase(Constantes.ruta_CONTEXTO+"faces/welcomePrimefaces.xhtml")
                            || uri.equalsIgnoreCase(Constantes.ruta_CONTEXTO+"welcomePrimefaces.xhtml")) {

                        //Se desahabilito RequestContext en Primefaces 7.0
                        // RequestContext context2 = RequestContext.getCurrentInstance();
                        // context2.execute("PF('sidebar1').show();");
                        PrimeFaces.current().executeScript("PF('sidebar1').show();");

                    }

                    user = sesion;

                    //System.out.println(uri);
                    if (!isPosBack()) {
                        if (verificaAccesoOpcionUsuario(uri)) {
                            System.out.println("TIENE ACCESO-VALIDACION#1");
                        } else {
                            System.out.println("NO TIENE ACCESO");
                            //FacesContext context = FacesContext.getCurrentInstance();
                            context.getExternalContext().redirect("../access.xhtml");
                        }
                    }
                    // System.err.println("nombre "+user.getNombre());
                } else {
                    //System.out.println("General POSBACK");

                }

            } catch (Exception e) {
                //System.out.println(e);
            }

        }

    }

    public void cargarCombo() {

    }

    public boolean sesionActiva() {
        FacesContext context = FacesContext.getCurrentInstance();
        Login sesion = (Login) context.getExternalContext().getSessionMap().get("USUARIO");
        //si no existe la sesion redirecciona la pagina hacia el index
        if (sesion == null) {
            return false;
        } else {
            return true;
        }
    }

    public void redireccionar() throws IOException {
        // System.out.println("***REDIRECCIONADO: "+getUser().getUsuario()+" | "+getUser().getNombre());
        //  FacesContext context = FacesContext.getCurrentInstance();
        //  context.getExternalContext().redirect("http://192.168.0.27:8080/SistemasWeb/");
    }

    public String getData() {
        return "";
    }

    
    public void mensajeDialogoInfo(String title, String msj) {
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msj);
        //PrimeFaces.current().dialog().showMessageDynamic(message);
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,title,msj);
        PrimeFaces.current().dialog().showMessageDynamic(message);/*Comentado por Kevin Anderson*/
        
    }

    public void mensajeDialogoError(String title, String msj) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msj);
        PrimeFaces.current().dialog().showMessageDynamic(message); /*Comentado por Kevin Anderson*/
    }

    public void mensajeDialogoWaning(String title, String msj) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, title, msj);
        PrimeFaces.current().dialog().showMessageDynamic(message); /*Comentado por Kevin Anderson*/
    }

    public void mensajeDialogoFatal(String title, String msj) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, title, msj);
        PrimeFaces.current().dialog().showMessageDynamic(message); /*Comentado por Kevin Anderson*/
    }

    //Crear menu lateral 
    public void cargarMenu() {
        MenuModel model = new DefaultMenuModel();
        //se le agrega el objeto menu al usuario
        user.setModel(model);
        //se recorre los resultados de menu
        for (int i = 0; i < lstMenu.size(); i++) {   //se guarda los datos en un objeto
            Menu temMenu = lstMenu.get(i);
            //se crea un item
            //DefaultSubMenu men = new DefaultSubMenu(temMenu.getDescripcion());
            DefaultSubMenu men = DefaultSubMenu.builder()
                .label(temMenu.getDescripcion())
                .build();
            //prefijo para la ruta
            String prefijoURL = Constantes.ruta_CONTEXTO+"faces/";
            //String prefijoURL = "";
            //se recorre los todos los items para encontrar los que 
            //correspondan al menu que se esta agregando
            for (int i2 = 0; i2 < lstMenuOpcion.size(); i2++) {   //compara para ver si se encuentra a los hijos 
                MenuOpcion opcion = lstMenuOpcion.get(i2);
                if (opcion.getC_menu() == temMenu.getC_menu()) {   //si encuentra, verifica si pertenece a un titulo
                    if (opcion.getC_titulo() == 0) {   //tambien verifica que no sea un titulo
                        //DefaultMenuItem item = new DefaultMenuItem(opcion.getDescripcion());
                        DefaultMenuItem item = DefaultMenuItem.builder()
                            .value(opcion.getDescripcion())
                            /*.icon("pi pi-save")
                            .ajax(false)
                            .command("#{menuView.save}")
                            .update("messages")
                            .url(ip)*/
                            .build();
                        //si no es titulo lo agrega de forma normal
                        if (opcion.getEs_titulo() == 0) {
                            //item.setUrl(prefijoURL + opcion.getUrl());
                            item.setUrl(prefijoURL + opcion.getUrl());
                        } else {//si es titulo le agrega color
                            item.setStyle("color:white;background:gold;");
                        }//agrega icono
                        //item.setIcon("pi pi-home");
                        //agrega el item al menu
                        //men.addElement(item);
                        men.getElements().add(item);
                        //lo marca para no repetirlo en la siguiente busqueda
                        lstMenuOpcion.get(i2).setEstado(0);
                    }//compara su es titulo/separador
                    if (opcion.getEs_titulo() == 1) {   //si es titulo recorre los item para agregar sus correspondientes hijos
                        for (int i3 = 0; i3 < lstMenuOpcion.size(); i3++) {
                            MenuOpcion opcionX = lstMenuOpcion.get(i3);
                            //si encuentra hijos los agrega
                            if (opcion.getC_opcion() == opcionX.getC_titulo() && opcionX.getEstado() == 1) {
                                //DefaultMenuItem itemX = new DefaultMenuItem(opcionX.getDescripcion());
                                DefaultMenuItem itemX = DefaultMenuItem.builder()
                                    .value(opcionX.getDescripcion())
                                    /*.icon("pi pi-save")
                                    .ajax(false)
                                    .command("#{menuView.save}")
                                    .update("messages")
                                    .url(ip)*/
                                    .build();
                                itemX.setUrl(prefijoURL + opcionX.getUrl());
                                //item.setIcon("pi pi-home");
                                //men.addElement(itemX);
                                men.getElements().add(itemX);
                                lstMenuOpcion.get(i3).setEstado(0);
                            }
                        }
                    }
                }
            }//agrega el menu al usuario
            //user.getModel().addElement(men);
            user.getModel().getElements().add(men);
        }

    }

    public MenuOpcion obtnerITem(int id) {
        MenuOpcion tmp = null;
        for (MenuOpcion i : lstMenuOpcion) {
            if (i.getC_menu() == id) {
                tmp = i;
                lstMenuOpcion.remove(i);
                return tmp;
            }
        }

        return tmp;

    }

    public void listarMenu() {
       // System.out.println("listando menu");

        lstMenu = dao.listarMenu(user);
        listarMenuOpcion();
    }

    //Funcion: Agrega los elementos que se mostraran en el menu de los usuarios
    public void listarMenuOpcion() {
       
        //Declaracion de Variable Int
        //Variable que almacenara el nivel de acceso del Usuario
        int nivelAcceso=0;
        
        //Declaracion de Lista tipo MenuOpcion
        List<MenuOpcion> lista = null;
        
        //Inicializacion de Lista
        lista = new ArrayList();
        
        //Declaracion de variable Tipo MenuOpcion
        MenuOpcion obj;
        
        //Se valida si el usuario si existe
        
            System.out.println("Aqui");
            //Si el usuario es DBA o de Tecnologia(Puesto 53)                
            if(validaEsDBA() || getUser().getC_puesto().equalsIgnoreCase("53") || getUser().getC_puesto().equalsIgnoreCase("54") ||  getUser().getC_puesto().equalsIgnoreCase("19") )
            {
                System.out.println("Aqui 2");
                //Es nivel 1
                nivelAcceso=1;
            }
            //Si el usuario es Auxiliar Operativo(Puesto 56) , AUXILIAR DE CALL CENTER (Puesto,98),AUXILIAR DE SERVICIO AL CLIENTE (Puesto,44),SERVICIO AL CLIENTE (Puesto,51)
            else if(getUser().getC_puesto().equalsIgnoreCase("56") || getUser().getC_puesto().equalsIgnoreCase("98") || getUser().getC_puesto().equalsIgnoreCase("44") || getUser().getC_puesto().equalsIgnoreCase("51") || getUser().getC_puesto().equalsIgnoreCase("42") )
                    {
                        //Es nivel 3
                        nivelAcceso=3;
                    }
                else 
                    {
                        //Es nivel 2
                        nivelAcceso=2;
                    } 
        

        //Se valida que valor tiene seteado la variable nivelAcceso, de acuerdo a su valor se agregan las opcione al menu 
        if (nivelAcceso==2) {
            obj = new MenuOpcion();
            obj.setC_opcion(6);
            obj.setDescripcion("Reporte de Actividades");
            obj.setC_menu(2);
            obj.setDescripcionPadre("Menu 2");
            obj.setEs_titulo(0);
            obj.setC_titulo(0);
            obj.setUrl("movil/actividades_movil.xhtml");
            obj.setOrden(1);
            obj.setEstado(1);
            lista.add(obj);
            
                if (getUser().getC_puesto().equalsIgnoreCase("63") || getUser().getC_puesto().equalsIgnoreCase("16"))
                {  //Menu Encuesta
                   obj = new MenuOpcion();
                   obj.setC_opcion(7);
                   obj.setDescripcion("Encuestas");
                   obj.setC_menu(2);
                   obj.setUrl("encuesta/encuesta.xhtml");
                   obj.setDescripcionPadre("Menu 2");
                   obj.setEs_titulo(0);
                   obj.setC_titulo(0);
                   obj.setOrden(1);
                   obj.setEstado(1);
                   lista.add(obj);  
                 }
        }
 
        if (nivelAcceso==1) {
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
            // lista.add(obj);

            obj = new MenuOpcion();
            obj.setC_opcion(6);
            obj.setDescripcion("Usuarios Moviles");
            obj.setC_menu(2);
            obj.setDescripcionPadre("Menu 2");
            obj.setEs_titulo(0);
            obj.setC_titulo(0);
            obj.setUrl("movil/usuarios_movil.xhtml");
            obj.setOrden(1);
            obj.setEstado(1);
            lista.add(obj);

            obj = new MenuOpcion();
            obj.setC_opcion(6);
            obj.setDescripcion("Reporte de Actividades");
            obj.setC_menu(2);
            obj.setDescripcionPadre("Menu 2");
            obj.setEs_titulo(0);
            obj.setC_titulo(0);
            obj.setUrl("movil/actividades_movil.xhtml");
            obj.setOrden(1);
            obj.setEstado(1);
            lista.add(obj);
            
             //Menu Encuesta
            obj = new MenuOpcion();
            obj.setC_opcion(7);
            obj.setDescripcion("Encuestas");
            obj.setC_menu(2);
            obj.setUrl("encuesta/encuesta.xhtml");
            obj.setDescripcionPadre("Menu 2");
            obj.setEs_titulo(0);
            obj.setC_titulo(0);
            obj.setOrden(1);
            obj.setEstado(1);
            lista.add(obj);  
        }   
        
        if(nivelAcceso==3)
        {
           obj = new MenuOpcion();
            obj.setC_opcion(7);
            obj.setDescripcion("Encuestas");
            obj.setC_menu(2);
            obj.setUrl("encuesta/encuesta.xhtml");
            obj.setDescripcionPadre("Menu 2");
           obj.setEs_titulo(0);
           obj.setC_titulo(0);
            obj.setOrden(1);
            obj.setEstado(1);
            lista.add(obj);  
        }
        
        //Seteo de Lista MenuOpcion con los menus agregados a la lista de nuestra validacion
        lstMenuOpcion = lista;
        
        //Agregando al usuario la lista de los menus que tiene acceso
        getUser().setLstMenuOpcion(lstMenuOpcion);
    }

    //Metodos SET y GET correspondientes a sus variables
    public Login getUser() {
        return user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUser(Login user) {
        this.user = user;
    }

    //este metodo devuelve la ruta donde se debe entrar si el usuario y clave 
    public void confirmaSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        Login usr_sess = (Login) context.getExternalContext().getSessionMap().get("USUARIO");
        if (usr_sess != null) {
            /*OJO try {
            //System.out.println("Si hay algo");
                
                System.out.println("AKI X");
                
                context.getExternalContext().redirect("/SistemasWeb/faces/welcomePrimefaces.xhtml");
                
                
                
                //System.out.println("Usuario de la Sesion: "+usr_sess.getUsuario());
            } catch (IOException ex) {
                //Logger.getLogger(DataGeneralController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             */
        } else {
            //System.out.println("No Hay Nada");
            //context.getExternalContext().encodeActionURL("http://stackoverflow.com");
            /*try {
                context.getExternalContext().encodeActionURL("http://stackoverflow.com");
                        //redirect("http://stackoverflow.com"); 
                //context.getExternalContext().redirect("/SistemasWeb/faces/index.xhtml");
                //System.exit(0);
            } catch (IOException ex) {
               Logger.getLogger(DataGeneralController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    }

    public String validarUsuario() throws SQLException {
        String resultado = "";
        //conexion a la base de datos
        //  se establece en mayusculas el nombre usuario
        user.setUsuario(user.getUsuario().toUpperCase());

        ///     user.setBase_datos(user.getHost_server().split("-")[1]);
        ///     user.setHost_server(user.getHost_server().split("-")[0]);
        //se verifica el usuario y contraseña      
        if (user.getClave().length() == 0) {
           // user.setUsuario("RRUIZ");
           // user.setClave("Finsoltest.2020");
        }/*
        if((user.getUsuario().equalsIgnoreCase("FERAZO")
                || user.getUsuario().equalsIgnoreCase("SA")
                || user.getUsuario().equalsIgnoreCase("KMIRANDA")))
        {*/
        if (dao.obtenerUsuario(user)) {//si todo es correcto entra al principal
            /*System.out.println(user.getHost_server());
            System.out.println(user.getBase_datos());*/
            //user.setUsuario("RMURCIA");user.setC_agencia("17");//para simulacion de usuario
            if (dao.validaAgencia(user.getUsuario(), user.getC_agencia())) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO", user);
                ///setPermisos();
                setNombre();
                listarMenu();
                cargarMenu();
                user.setLstMenuOpcion(lstMenuOpcion);
                System.out.println("**ENTRO AL SISTEMA: " + getUser().getUsuario() + " | " + getUser().getNombre() + " AG:" + getUser().getC_agencia() + " PU:" + getUser().getC_puesto());
                //si crea una sesion y se guarda el objeto user(Login) que contiene todos los datos del usuario
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO", user);
                resultado = "welcomePrimefaces?faces-redirect=true";

                if(user.getEstado().equalsIgnoreCase("0"))
                {
                    resultado="";
                    mensajeDialogoWaning("Sistema", "El Usuario esta Inactivo, contacte al Administador");
                }
                
                if(user.getIntentos()>=3)
                {
                    mensajeDialogoWaning("Sistema", "Usuario Bloqueado");
                    resultado="";
                }
                
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "No tiene permisos para ingresar a la agencia seleccionada."));
                //user.setClave("");
                resultado = "";
            }

            //   } 
        } else {
            //si los datos son incorrectos muestra el mensaje
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Usuario o clave incorrectos."));
            user.setClave("");
            resultado = "";
        }
        //devuelve el resultado
        return resultado;
    }

    //obtener IP del cliente
    private String getIP() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }//si es IP LOCAL
        if (ipAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            ipAddress = "LOCALHOST";
        }

        return ipAddress;
    }

    public void setNombre() {
        /*
        String nombreReal = m.obtenerString("SELECT D_USUARIO FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");
        
        
        boolean promotor = m.obtenerBoolean("SELECT isnull(b_promotor,0) FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");
        boolean gerente_ = m.obtenerBoolean("SELECT isnull(b_gerente,0) FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");
        boolean erc = m.obtenerBoolean("SELECT isnull(b_encargado_r,0) FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");
        boolean gerente_zona = m.obtenerBoolean("SELECT isnull(b_gerente_z,0) FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");
        boolean gerente_r = m.obtenerBoolean("SELECT isnull(b_gerente_r,0) FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");
        boolean gerente_g = m.obtenerBoolean("SELECT isnull(b_gerente_g,0) FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");
        boolean sistemas = m.obtenerBoolean("SELECT isnull(b_sistemas,0) FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");
        boolean call_center = m.obtenerBoolean("SELECT isnull(b_call_center,0) FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");
        boolean operativo = m.obtenerBoolean("SELECT isnull(b_operativo,0) FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");
        String siscodigo = m.obtenerString("SELECT isnull(siscodigo,'0') FROM [CREDISOL_BUSINESS].[dbo].[ADC_SIST] WHERE USUARIO_SQL='" + user.getUsuario() + "'");
        user.setNombre(nombreReal.trim());

      
        user.setB_promotor_(promotor);
        user.setB_gerente_(gerente_);
        user.setB_erc_(erc);
        user.setB_g_zona_(gerente_zona);
        user.setB_g_regional_(gerente_r);
        user.setB_g_general_(gerente_g);
        user.setB_sistemas_(sistemas);
        user.setB_call_center_(call_center);
        user.setB_operativo_(operativo);

        user.setSiscodigo(siscodigo);
         */

        String dataUser[] = m3.traeArrayX("SELECT \n"
                + "  nombre\n"
                + ", estado\n"
                + ", puestoid puesto_id\n"
                + ", '0' Nivel\n"
                + ", '0' cod_rol\n"
                + ", correo\n"
                + ", agenciaid sucursal\n"
                + ", agencia d_agencia\n"
                + ", puestoid c_area\n"
                + ", puesto d_area\n"
                + ", puesto d_puesto\n"
                + ", empleadoid \n"
                + ", coalesce(intentos,0) intentos\n"
                + ", tipousuario \n"
                + "FROM ad_usuarios WHERE usuariotopaz='" + user.getUsuario() + "'");

        String nombre = dataUser[0].split("__")[0].trim();
        String estado = dataUser[0].split("__")[1].trim();
        String c_puesto = dataUser[0].split("__")[2].trim();
        String agencia = dataUser[0].split("__")[6].trim();
        String correo = dataUser[0].split("__")[5].trim();
        String d_agencia = dataUser[0].split("__")[7].trim();
        String c_depto = dataUser[0].split("__")[8].trim();
        String d_depto = dataUser[0].split("__")[9].trim();
        String d_puesto = dataUser[0].split("__")[10].trim();
        String empleado_id = dataUser[0].split("__")[11].trim();
        String intentos = dataUser[0].split("__")[12].trim();
        String tipouser = dataUser[0].split("__")[13].trim();

        user.setC_agencia(agencia);
        user.setD_agencia(d_agencia.replaceAll("AGENCIA ", ""));
        user.setNombre(nombre);
        user.setEstado(estado);
        user.setCorreo(correo);
        user.setC_depto(c_depto);
        user.setD_depto(d_depto);
        user.setC_puesto(c_puesto);
        user.setD_puesto(d_puesto);
        user.setSiscodigo(empleado_id);
        try{ user.setIntentos(Integer.parseInt(intentos)); }catch(Exception e){user.setIntentos(0);}
        
        user.setTipousuario(tipouser);
        
        ip = getIP();
        //System.out.println("usuario " + user.getNombre() + " gerente:" + gerente_ + "  promotor:" + promotor + " sistemas:" + sistemas);

    }

    public void setPermisos() {
        /*  System.out.println("asigno perm");
        String gerente = m.obtenerString("SELECT isnull(b_gerente,0) FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");
        String vice_gerente = m.obtenerString("SELECT isnull(b_vice_gerente,0) FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");
        String promotor = m.obtenerString("SELECT isnull(b_promotor,0) FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");
        String sistemas = m.obtenerString("SELECT isnull(b_sistemas,0) FROM ERP_USUARIO WHERE C_USUARIO='" + user.getUsuario() + "'");

        user.setB_gerente(Integer.parseInt(gerente));
        user.setB_vice_gerente(Integer.parseInt(vice_gerente));
        user.setB_promotor(Integer.parseInt(promotor));
        user.setB_sistemas(Integer.parseInt(sistemas));
         */
    }

    private boolean isPosBack() {
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();
        return rpta;
    }

    public String getUrl_actual() {
        return url_actual;
    }

    public void setUrl_actual(String url_actual) {
        this.url_actual = url_actual;
    }

    public boolean validaAprobacionJefe() {
        for (int i = 0; i < Constantes.aprueban_array.length; i++) {
            if (Constantes.aprueban_array[i].trim().equalsIgnoreCase(getUser().getC_puesto())) {
                return true;
            }
        }

        return false;
    }

    public boolean validaEsJefeProyecto() {
        for (int i = 0; i < Constantes.jefes_ti_array.length; i++) {
            if (Constantes.jefes_ti_array[i].trim().equalsIgnoreCase(getUser().getC_puesto())) {
                return true;
            }
        }

        return false;
    }

    public boolean validaEsAnalista() {
        for (int i = 0; i < Constantes.analista_array.length; i++) {
            if (Constantes.analista_array[i].trim().equalsIgnoreCase(getUser().getC_puesto())) {
                return true;
            }
        }

        return false;
    }

    public boolean validaEsDBA() {
        for (int i = 0; i < Constantes.adm_dba_array.length; i++) {
            if (Constantes.adm_dba_array[i].trim().equalsIgnoreCase(getUser().getC_puesto())) {
                return true;
            }
        }

        return false;
    }

    public boolean validaEsUsuario() {
        for (int i = 0; i < Constantes.adm_dba_array.length; i++) {
            if (Constantes.adm_dba_array[i].trim().equalsIgnoreCase(getUser().getC_puesto())) {
                return false;
            }
        }

        for (int i = 0; i < Constantes.analista_array.length; i++) {
            if (Constantes.analista_array[i].trim().equalsIgnoreCase(getUser().getC_puesto())) {
                return false;
            }
        }

        return true;
    }

    public boolean validaEsSupervisaAsesor() {
        for (int i = 0; i < Constantes.arribaDeAsesor_array.length; i++) {
            if (Constantes.arribaDeAsesor_array[i].trim().equalsIgnoreCase(getUser().getC_puesto())) {
                return true;
            }
        }

        return false;
    }

    public boolean validaEsGerente() {
        /* for (int i = 0; i < Constantes.arribaDeAsesor_array.length; i++) {
            if (Constantes.arribaDeAsesor_array[i].trim().equalsIgnoreCase(getUser().getC_puesto())) {
                return true;
            }
        }*/
        //Gerente de Negocio Bertin codigo 11   //programador 53
        if (getUser().getC_puesto().equalsIgnoreCase("11") || getUser().getC_puesto().equalsIgnoreCase("53")) {
            return true;
        }
        return false;
    }
     public boolean validaEsRH() {
        
        //Gerente de RH 38  
        if (getUser().getC_puesto().equalsIgnoreCase("38") ) {
            return true;
        }
        return false;
    }

    public String getClaveAnterior() {
        return claveAnterior;
    }

    public void setClaveAnterior(String claveAnterior) {
        this.claveAnterior = claveAnterior;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public String getSimbolosAut() {
        return simbolosAut;
    }

    public void setSimbolosAut(String simbolosAut) {
        this.simbolosAut = simbolosAut;
    }

    
    
        private int minCaracteres=8;
    private int minMayuscula=0;
    private int minMinuscula=1;
    private int minNumero=1;
    private int minSimbolo=1;
    private String simbolosAut="!,#,$,%,&,/,(,),+,-,*,.,:,;,?,=,{,},[,],@";
    private boolean pasaValidacion(String clave)
    {


        boolean pasa=true;

        if(clave.length()<minCaracteres)
        {
            //mensajeDialogoWaning(clave, ip);
            mensajeDialogoWaning("Requisitos de Clave","Se necesitan al menos "+minCaracteres+" caracteres!\n"+clave.length());
            pasa=false;
            return pasa;
        }
        int contMayuscula=0,conMinuscula=0,conNumero=0,conSimbolo=0;
        for (int i = 0; i <clave.length() ; i++) {
            char letra=clave.charAt(i);
            if(Character.isUpperCase(letra))
                contMayuscula++;
            else if(Character.isLowerCase(letra))
                conMinuscula++;

            if(Character.isDigit(letra))
                conNumero++;

            if(Character.isDigit(letra)==false ||  Character.isLetter(letra)==false )
            {
                String simbol[]=simbolosAut.split(",");
                for (int j = 0; j <simbol.length ; j++) {
                    if(letra==simbol[j].charAt(0))
                        conSimbolo++;
                }
            }

        }
        //Log.i(Utilidades.TAG, "pasaValidacion: Cantidad Total: "+(contMayuscula+(conMinuscula)+conNumero+conSimbolo)+"!="+clave.length());
        if( (contMayuscula+conMinuscula+conNumero+conSimbolo)!=clave.length())
        {
            mensajeDialogoWaning("Requisitos de Clave","Solo puede usar Simbolos que estan en el ejemplo");
            pasa=false;
            return pasa;
        }
        if(contMayuscula<minMayuscula)
        {
            mensajeDialogoWaning("Requisitos de Clave","Necesita al menos ("+minMayuscula+") letras Mayusculas");
            pasa=false;
            return pasa;
        }
        if(conMinuscula<minMinuscula)
        {
            mensajeDialogoWaning("Requisitos de Clave","Necesita al menos ("+minMinuscula+") letras Minusculas");
            pasa=false;
            return pasa;
        }
        if(conNumero<minNumero)
        {
            mensajeDialogoWaning("Requisitos de Clave","Necesita al menos ("+minNumero+") Digitos");
            pasa=false;
            return pasa;
        }
        if(conSimbolo<minSimbolo)
        {
            mensajeDialogoWaning("Requisitos de Clave","Necesita al menos ("+minSimbolo+") Simbolos");
            pasa=false;
            return pasa;
        }

        return pasa;
    }
    
    
    public String md5(String pass) {
        String passwordHash="";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            passwordHash = (new HexBinaryAdapter()).marshal(md.digest(pass.getBytes(Charset.forName("UTF-8"))));
           // System.out.println(pass+"  "+passwordHash);
        } catch (NoSuchAlgorithmException ex) {
            //Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return passwordHash.toLowerCase();
        
    }

    public void enviarCorreoPuesto(String idPuestoPara, String asunto, String msj) throws Exception {

        String sql = "";

        //real
        sql = "select Correo from Empleados where Puesto_Id in(" + idPuestoPara + ")";

        String dat[] = m.traeArrayX(sql);

        if (dat.length < 1) {
            return;
        }

        String destinos = "";
        for (int i = 0; i < dat.length; i++) {
            if (dat[i].contains("@")) {
                destinos = destinos + dat[i] + ",";
            }
        }
        destinos = destinos.substring(0, destinos.length() - 1);

        System.out.println("destinos reales:" + destinos);
        if (idPuestoPara.equalsIgnoreCase(Constantes.apr_operaciones)
                || idPuestoPara.equalsIgnoreCase(Constantes.analista)
                || idPuestoPara.equalsIgnoreCase(Constantes.apr_ti)
                || idPuestoPara.equalsIgnoreCase(Constantes.adm_db)
                || idPuestoPara.equalsIgnoreCase(Constantes.adm_sop)) {
            destinos = "rcardona@finsolhn.com";

        }

        SendMailNormal mail = new SendMailNormal();
        mail.Enviar(destinos, asunto, msj);

    }

    public void enviarCorreoDirecto(String idEmpoleadoPara, String asunto, String msj) throws Exception {

        String sql = "select Correo from Empleados where Empleado_Id='" + idEmpoleadoPara + "'";
        String destino = m.obtenerString(sql).trim();

        if (destino.equalsIgnoreCase("0") || destino.isEmpty()) {
            return;
        }

        SendMailNormal mail = new SendMailNormal();
        mail.Enviar(destino, asunto, msj);

    }
}
/*   if(validaEsJefeProyecto())
        //   {
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
        //  lista.add(obj);
        //   }

        //   if(validaEsAnalista())
        //   {
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
        // lista.add(obj);
        //   }


        obj = new MenuOpcion();
        obj.setC_opcion(5);
        obj.setDescripcion("Soporte/ADM Servidores");
        obj.setC_menu(1);
        obj.setDescripcionPadre("Menu p");
        obj.setEs_titulo(0);
        obj.setC_titulo(0);
        obj.setUrl("gestion_proyect/administracion_soporte.xhtml");
        obj.setOrden(1);
        obj.setEstado(1);
        // lista.add(obj);
*/