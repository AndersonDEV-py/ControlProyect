/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.controller;

import finsolhn.com.dao.AdmUsuariosDAO;
import finsolhn.com.dao.MasterPrintDAO;
import finsolhn.com.model.Aceptacion;
import finsolhn.com.model.MyFile;
import finsolhn.com.model.Requerimientos;
import finsolhn.com.util.Constantes;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.faces.application.FacesMessage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseId;
import jakarta.servlet.ServletContext;
import org.primefaces.PrimeFaces;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rcardona
 */
@Named("admUsuariosController")
//@RequestScoped
//@ApplicationScoped
@ViewScoped
//@SessionScoped
//@RequestScoped 
public class AdmUsuariosController extends DataGeneralController {

    //variables y objetos
    AdmUsuariosDAO dao = new AdmUsuariosDAO();

    private Requerimientos objeto = new Requerimientos();
    private Requerimientos objetoSecundario = new Requerimientos();
    private Aceptacion objetoFinalizacion = new Aceptacion();
 //private Requerimientos objetoEst = new Requerimientos();
 
    private List<Requerimientos> lstReq;
    private List<Requerimientos> lstCasos;
    private List<Requerimientos> lstAprobarPend;
    private List<Aceptacion> lstAceptarPend;
   /// private List<Requerimientos> lstEstructurasSol;
    
    private List<Requerimientos> lstDocuemtacion;

    
    public List<Requerimientos> getLstDocuemtacion() {
        return lstDocuemtacion;
    }

    public void setLstDocuemtacion(List<Requerimientos> lstDocuemtacion) {
        this.lstDocuemtacion = lstDocuemtacion;
    }

    public Aceptacion getObjetoFinalizacion() {
        return objetoFinalizacion;
    }

    public void setObjetoFinalizacion(Aceptacion objetoFinalizacion) {
        this.objetoFinalizacion = objetoFinalizacion;
    }

    public Requerimientos getObjetoSecundario() {
        return objetoSecundario;
    }

    public void setObjetoSecundario(Requerimientos objetoSecundario) {
        this.objetoSecundario = objetoSecundario;
    }

    public Requerimientos getObjeto() {
        return objeto;
    }

    public void setObjeto(Requerimientos objeto) {
        this.objeto = objeto;
    }

    public List<Requerimientos> getLstReq() {
        return lstReq;
    }

    public void setLstReq(List<Requerimientos> lstReq) {
        this.lstReq = lstReq;
    }

    public List<Requerimientos> getLstCasos() {
        return lstCasos;
    }

    public void setLstCasos(List<Requerimientos> lstCasos) {
        this.lstCasos = lstCasos;
    }

    public List<Requerimientos> getLstAprobarPend() {
        return lstAprobarPend;
    }

    public void setLstAprobarPend(List<Requerimientos> lstAprobarPend) {
        this.lstAprobarPend = lstAprobarPend;
    }

    public List<Aceptacion> getLstAceptarPend() {
        return lstAceptarPend;
    }

    public void setLstAceptarPend(List<Aceptacion> lstAceptarPend) {
        this.lstAceptarPend = lstAceptarPend;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    /*
   public void verReporte() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
       
        if(lstHistorial.isEmpty())
        {
              //RequestContext context = RequestContext.getCurrentInstance();
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "No hay datos"));
              //////////System.out.println("No tiene historial "+objeto.getD_asoc_negocio());
        }else{
                ////////System.out.println("cliente "+objeto.getD_asoc_negocio());
 
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();      
                String rutaImg = servletContext.getRealPath("//resources//images")+"/";


                Map parameter = new HashMap();
                parameter.put("id", objeto.getC_credito());
                parameter.put("nom_cliente", objeto.getC_asoc_negocio()+"  "+ objeto.getD_asoc_negocio());
                parameter.put("logo", rutaImg);

               MasterPrintDAO print=new MasterPrintDAO();
               print.generarPDF("Historial", parameter, "");
        }
          
       
   
    }*/
    public void sumar() {

        /*      
        tot_clientes_cart = 0;
        tot_saldo_cart = 0;
        if(lstObjetos!=null)
        tot_clientes_mora = lstObjetos.size();
        tot_capital_mora = 0;
        tot_saldo_atrasado = 0;
        tot_dif_mora = 0;
        if(lstObjetosPendientes!=null)
        tot_clientes_pen = lstObjetosPendientes.size();
        tot_capital_pen = 0;
        tot_monto_pen = 0;
        tot_dif_pen = 0;
         if(lstObjetos!=null)
        for (int i = 0; i < lstObjetos.size(); i++) {

            if (lstObjetos.get(0).getC_credito().isEmpty()) {
                tot_clientes_mora = 0;
            }
            tot_clientes_mora=lstObjetos.get(i).getCientes_mora();

            tot_clientes_cart = lstObjetos.get(i).getCartera_clientes();
            tot_saldo_cart = lstObjetos.get(i).getCartera_saldo();

            tot_capital_mora = lstObjetos.get(i).getSaldo_mora();
            tot_saldo_atrasado = tot_saldo_atrasado + lstObjetos.get(i).getMonto_atraso();
            tot_dif_mora = tot_dif_mora + lstObjetos.get(i).getDif_mora();

        }
        if(lstObjetosPendientes!=null)
        for (int i = 0; i < lstObjetosPendientes.size(); i++) {

            if (lstObjetosPendientes.get(0).getC_credito().isEmpty()) {
                tot_clientes_pen = 0;
            }

            tot_capital_pen = tot_capital_pen + lstObjetosPendientes.get(i).getCapital();
            tot_monto_pen = tot_monto_pen + lstObjetosPendientes.get(i).getMonto_atraso();
            tot_dif_pen = tot_dif_pen + lstObjetosPendientes.get(i).getDif_mora();
        }
        
        ////////System.out.println("Tot 1 "+tot_clientes_mora);
        ////////System.out.println("Tot 2 "+tot_clientes_pen);
         */
    }
    //metodo sirve para insertar o reprogramar una visita
    String operacion = "";
    String texto = "";

    public void insertarRequerimiento() {

     
        //revisa si es una insercion
        if (operacion.equalsIgnoreCase("I")) {

            try {
                
                String aut="0";
                
                String msj=Constantes.M1_mensaje.replace("<solicita>", getUser().getNombre()).
                        replace("<nombre_req>", objeto.getNombre_corto());
                if(validaAprobacionJefe())
                {
                    aut=getUser().getUsuario();
                    objeto.setEstado(Constantes.estado_sin_autortizar);
                    enviarCorreoPuesto(Constantes.apr_ti, Constantes.A1_asunto, msj);
                }else{
                    objeto.setEstado(Constantes.estado_sin_autortizar_area);
                    enviarCorreoPuesto(Constantes.apr_operaciones, Constantes.A1_asunto, msj);
                }
                
                dao.insertarReq(objeto,aut);
                listarReq("x");
                
                //agrega objeto a la tabla requerimientos
                //lstReq.add(objeto);
            } catch (Exception e) {
                System.out.println("Bean inser err " + e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
            }
        } else {  //si es una reprogramacion de visita:
            try {
                //ejecuta el metodo para actualizar la fecha de visita

                dao.modificarReq(objeto);

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
            }
        }
    }

    public void insertarCaso() {

        //revisa si es una insercion
        if (operacion.equalsIgnoreCase("I")) {

            try {

                dao.insertarCaso(objeto);
                listarReq("x");
                //agrega objeto a la tabla requerimientos
                //lstReq.add(objeto);
                String msj=Constantes.M5_mensaje.replace("<solicita>", getUser().getNombre()).
                        replace("<nombre_req>", objeto.getNombre_corto());
                enviarCorreoPuesto(Constantes.analista, Constantes.A1_asunto, msj);
                
            } catch (Exception e) {
                System.out.println("Bean inser err " + e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
            }
        } else {  //si es una reprogramacion de visita:
            try {
                //ejecuta el metodo para actualizar la fecha de visita

                dao.modificarCaso(objeto);

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
            }
        }
    }

    //listar todos mi requerimientos
    public void listarReq(String v) {

        try {//se verifica que no sea un POSTBACK
            if (v.equalsIgnoreCase("f")) {
                if (!isPosBack()) {

                    String where = "";
                    //si no es posback se ejecuta el metodo
                    lstReq = dao.listarMisRequerimientos(getUser().getUsuario());
                    lstCasos = dao.listarMisCasos(getUser().getUsuario());
                    where = "and R.c_area='" + getUser().getC_depto() + "'";
                    lstAprobarPend = dao.listarAyudas(where);
                    where = "and F.c_area='" + getUser().getC_depto() + "'";
                    lstAceptarPend = dao.listarCompletados(where);
                }
            } else {
                //si es ejecutado desde un boton
               // System.err.println("v: " + v);
               lstReq = dao.listarMisRequerimientos(getUser().getUsuario());
               lstCasos = dao.listarMisCasos(getUser().getUsuario());
                    
                String where = "and R.c_area='" + getUser().getC_depto() + "'";
                lstAprobarPend = dao.listarAyudas(where);
                where = "and F.c_area='" + getUser().getC_depto() + "'";
                lstAceptarPend = dao.listarCompletados(where);
            }

        } catch (Exception e) {
            System.err.println("error: " + e);
        }
    }

    public AdmUsuariosController() {
        if (getUser().getUsuario().isEmpty()) {
            super.verificarSession();
            System.err.println("****HOLA  Usuario:*" + getUser().getUsuario() + "* " + getUser().getNombre());
        }

        listarReq("f");

        //////////System.err.println("****HOLA  Usuario  "+getUser().getUsuario());
        /* if (lstObjetos == null) {
                lstObjetos = new ArrayList();
                for (int i = 0; i < 8; i++) {
                    ClientesCobro c = new ClientesCobro();
                    lstObjetos.add(c);
                }
            }
                 //si esta vacio rellena con estacios vacios
            if (lstObjetosPendientes == null) {
                lstObjetosPendientes = new ArrayList();
                for (int i = 0; i < 8; i++) {
                    ClientesCobro c = new ClientesCobro();
                    lstObjetosPendientes.add(c);
                }
            }*/
 /*   try {
           obtener_img();
           } catch (FileNotFoundException e) {
           
            System.out.println("ERR "+e);
            }*/
        //listarAgencias();
    }

    public void nuevo() {
        objeto = new Requerimientos();
        objeto.setC_usuario(getUser().getUsuario());
        objeto.setD_usuario(getUser().getNombre());
        objeto.setC_agencia(getUser().getC_agencia());
        objeto.setD_agencia(getUser().getD_agencia());
        objeto.setC_area(getUser().getC_depto());

        Date objDate = new Date();
        objeto.setFecha(sdf.format(objDate));
        operacion = "I";

        System.out.println("user " + getUser().getUsuario() + " _ " + getUser().getNombre());
    }

    public void modificar(Requerimientos select) {
        objeto = select;
        operacion = "U";
        objeto.setLstDocument(dao.listarDocumentos(objeto));
        System.out.println("Nombre REQ" + objeto.getNombre_corto());
    }

    public void ver_ayuda_enviada(Requerimientos select) {
        objetoSecundario = select;
        operacion = "U";
        
        //RequestContext context = RequestContext.getCurrentInstance();
        if (objetoSecundario.getTipo_registro().equalsIgnoreCase("E")) {
            objeto = dao.obtenerObjetoReq(objetoSecundario.getId_req_padre());
            objeto.setLstDocument(dao.listarDocumentos(objeto));
            PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').show();");
        } else if(objetoSecundario.getTipo_registro().equalsIgnoreCase("P")){
            objeto = dao.obtenerObjetoReq(objetoSecundario.getId_req_padre());
            objeto.setLstDocument(dao.listarDocumentos(objeto));
            PrimeFaces.current().executeScript("PF('wdlPermiso').show();");
        }else if(objetoSecundario.getTipo_registro().equalsIgnoreCase("R"))
        {
            objeto = dao.obtenerObjetoReq(""+objetoSecundario.getId());
            objeto.setLstDocument(dao.listarDocumentos(objeto));
            PrimeFaces.current().executeScript("PF('wdlRequerimiento').show();");
            
        }else if(objetoSecundario.getTipo_registro().equalsIgnoreCase("A"))
        {
           // objetoEst=objetoSecundario;
            objetoSecundario.setLstTabla(dao.obtenerEstructuraTabla(select));
            PrimeFaces.current().executeScript("PF('wdlCargaProce').show();");
        }

        //objeto.setLstDocument(dao.listarDocumentos(objeto));
    }

    
    public void ver_origen_estructura()
    {
        objeto = dao.obtenerObjetoReq(""+objetoSecundario.getId_req_padre());
     
    }
    
    public void ver_completado(Aceptacion select) {
        objetoFinalizacion = select;
        operacion = "U";
        objeto = dao.obtenerObjetoReq(objetoFinalizacion.getId_req_padre());
        objeto.setLstDocument(dao.listarDocumentos(objeto));
        //RequestContext context = RequestContext.getCurrentInstance();

        PrimeFaces.current().executeScript("PF('wdlCompletado').show();");

        //objeto.setLstDocument(dao.listarDocumentos(objeto));
    }

    public void ver_Origen_Completar(Requerimientos req) {
        /*
        operacion="U";
        objeto=dao.obtenerObjetoReq(objetoSecundario.getId_req_padre());
         */
        //objeto=req;

        //RequestContext context = RequestContext.getCurrentInstance();

        if (req.getTipo_registro().equalsIgnoreCase("R")) {

            //   objeto=dao.obtenerObjetoReq(""+req.getId());
            //   objeto.setLstDocument(dao.listarDocumentos(objeto));
            PrimeFaces.current().executeScript("PF('wdlRequerimiento').show();");
        } else if (req.getTipo_registro().equalsIgnoreCase("C")) {
            //  objeto=dao.obtenerObjetoReq(""+req.getId());
            //  objeto.setLstDocument(dao.listarDocumentos(objeto));

            PrimeFaces.current().executeScript("PF('wdlCaso').show();");

            /*if(objetoSecundario!=null && objetoSecundario.getTipo_registro()!=null)
            {
                if(objetoSecundario.getTipo_registro().equals("E"))
                {
                    PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').show();");          
                }else if(objetoSecundario.getTipo_registro().equals("P")){
                    PrimeFaces.current().executeScript("PF('wdlPermiso').show();");
                }else{
                    PrimeFaces.current().executeScript("PF('wdlCaso').show();");
                }
            }else{
                    PrimeFaces.current().executeScript("PF('wdlCaso').show();");
            }*/
        } else if (req.getTipo_registro().equalsIgnoreCase("E")) {
            objetoSecundario = dao.obtenerObjetoReq("" + req.getId());
            objetoSecundario.setLstDocument(dao.listarDocumentos(objetoSecundario));
            PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').show();");
            //if(objetoSecundario.getTipo_registro().equals("C"))
            //PrimeFaces.current().executeScript("PF('wdlCaso').show();");

        } else if (req.getTipo_registro().equalsIgnoreCase("P")) {
            objetoSecundario = dao.obtenerObjetoReq("" + req.getId());
            objetoSecundario.setLstDocument(dao.listarDocumentos(objetoSecundario));
            PrimeFaces.current().executeScript("PF('wdlPermiso').show();");

        }

        //objeto.setLstDocument(dao.listarDocumentos(objeto));
    }

    public void listarDocumentacion() {
        lstDocuemtacion = dao.listarDocumentacion(objetoFinalizacion);
    }

    public void ver_ayuda_Origen() {
        /*
        operacion="U";
        objeto=dao.obtenerObjetoReq(objetoSecundario.getId_req_padre());
         */
        //RequestContext context = RequestContext.getCurrentInstance();

        if (objeto.getTipo_registro().equalsIgnoreCase("R")) {
            PrimeFaces.current().executeScript("PF('wdlRequerimiento').show();");
        } else {
            PrimeFaces.current().executeScript("PF('wdlCaso').show();");
        }

        //objeto.setLstDocument(dao.listarDocumentos(objeto));
    }

    public void actualizarEstadoCompletado() {

        if (validaAprobacionJefe()) {
            try {

                objeto.setEstado(Constantes.estado_aceptado);
                objeto.setC_usuario_area(getUser().getUsuario());
                objetoFinalizacion.setC_usuario_area(getUser().getUsuario());
                objetoFinalizacion.setEstado(Constantes.estado_aceptado);

                dao.req_finalizado(objeto, objetoFinalizacion);

                listarReq("x");
                
                String msj=Constantes.M7_mensaje.replace("<desa>", objetoFinalizacion.getD_developer()).replace("<nombre_req>", objeto.getNombre_corto()).replace("<numero>", objeto.getId()+"");
           
                enviarCorreoPuesto(Constantes.apr_ti, Constantes.A1_asunto, msj);
            
            } catch (Exception e) {
                System.err.println("" + e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Este Perfil no puede finalizar un Requerimiento"));
        }

    }

    public boolean disable_bt_autorizar() {

        if (objetoSecundario.getEstado() != null) {
            if (objetoSecundario.getEstado().equalsIgnoreCase(Constantes.estado_sin_autortizar_area)) {
                return false;
            }

        }

        return true;
    }
    public boolean disable_bt_autorizar_2() {

        if (objeto.getEstado() != null && objeto.getTipo_registro() != null) {
            if (objeto.getEstado().equalsIgnoreCase(Constantes.estado_sin_autortizar_area) 
                    && objeto.getTipo_registro().equalsIgnoreCase("R") /*|| objeto.getTipo_registro().equalsIgnoreCase("A") || objeto.getTipo_registro().equalsIgnoreCase("B"))*/ ) {
                return false;
            }

        }

        return true;
    }

    public void autorizacion(String res) throws Exception {
        System.out.println("" + res);
        if (validaAprobacionJefe()) {
            objetoSecundario.setC_usuario_area(getUser().getUsuario());
            objetoSecundario.setEstado(res);
            dao.autorizacion_req(objetoSecundario);
            
            if(res.equalsIgnoreCase(Constantes.estado_rechazado))
            {
                String msj=Constantes.M3_mensaje+"\nRESPUESTA: "+getUser().getNombre();
           
                enviarCorreoDirecto(objetoSecundario.getC_usuario(), Constantes.A1_asunto, msj);
            }else if(res.equalsIgnoreCase(Constantes.estado_sin_autortizar))
            {
                String msj=Constantes.M1_mensaje.replace("<nombre_req>", objeto.getNombre_corto()).replace("<solicita>", objeto.getD_developer());
           
                enviarCorreoPuesto(Constantes.apr_ti, Constantes.A1_asunto, msj);
            
            }
            
            if(objetoSecundario.getTipo_registro().equalsIgnoreCase("A") || objetoSecundario.getTipo_registro().equalsIgnoreCase("B") )
            {
                lstAprobarPend.remove(objetoSecundario);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Este Perfil no puede Aprobar/Rechazar"));
        }

    }
    public void autorizacionREQ(String estado) throws Exception {
        
        if (validaAprobacionJefe()) {
            objeto.setC_usuario_area(getUser().getUsuario());
            objeto.setEstado(estado);
            dao.autorizacion_req_area(objeto);
            
            if(estado.equalsIgnoreCase(Constantes.estado_rechazado))
            {
                String msj=Constantes.M3_mensaje+"\nRESPUESTA:"+getUser().getNombre();
           
                enviarCorreoDirecto(objeto.getC_usuario(), Constantes.A1_asunto, msj);
            }else if(estado.equalsIgnoreCase(Constantes.estado_sin_autortizar))
            {
                String msj=Constantes.M1_mensaje.replace("<nombre_req>", objeto.getNombre_corto()).replace("<solicita>", objeto.getD_usuario());
           
                enviarCorreoPuesto(Constantes.apr_ti, Constantes.A1_asunto, msj);
            }
            
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Este Perfil no puede Aprobar/Rechazar"));
        }

    }

    ////////VER ARCHIVO EXTERNO con @RequestScoped 
 
    public StreamedContent mostrarImagen(String nombreArchivo) throws IOException {
  
        ByteArrayOutputStream out = null;
       /* //deprecate buscar remplazo
        if (nombreArchivo == null) {
            out = traerArchivo("C:/temp/", nombreArchivo);
            InputStream myInputStream2 = new ByteArrayInputStream(out.toByteArray());
            return new DefaultStreamedContent(myInputStream2);
        }
        if (nombreArchivo.length() > 0) {
            try {
                out = traerArchivo("C:/temp/", nombreArchivo);
                InputStream myInputStream2 = new ByteArrayInputStream(out.toByteArray());
                return new DefaultStreamedContent(myInputStream2);
            } catch (Exception e) {
            }
            

        } else {
            out = traerArchivo("C:/temp/", nombreArchivo);
            InputStream myInputStream2 = new ByteArrayInputStream(out.toByteArray());
            return new DefaultStreamedContent(myInputStream2);
        }*/
        return null;
      
    }

    public ByteArrayOutputStream traerArchivo(String ruta, String nombre_archivo) {
        ByteArrayOutputStream out = null;
        /*String path = ruta + nombre_archivo;
        InputStream in = null;
        try {
            File remoteFile = new File(path);
            in = new BufferedInputStream(new FileInputStream(remoteFile));
            out = new ByteArrayOutputStream((int) remoteFile.length());
            byte[] buffer = new byte[4096];
            int len = 0; //Read length
            while ((len = in.read(buffer, 0, buffer.length)) != - 1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            String msg = "ERROR DESCARGANDO ARCHIVO " + e.getMessage();
            System.out.println(msg);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
            }
        }
        */
        
     
       return out;  
        
    }
  
    ////////////////////// FIN VER ARCHIVO EXTERNO 

    
    
        public void verFormadoFinPDF(String n) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
       
    
                ////////System.out.println("cliente "+objeto.getD_asoc_negocio());
 
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();      
                String rutaImg = servletContext.getRealPath("//resources//images")+"/";
                String rutaJasper = servletContext.getRealPath("//jasper")+"/";


                Map parameter = new HashMap();
                parameter.put("id", objetoFinalizacion.getId());
                //parameter.put("nom_cliente", objeto.getC_asoc_negocio()+"  "+ objeto.getD_asoc_negocio());
                parameter.put("logo", rutaImg);
                parameter.put("SUBREPORT_DIR", ""+rutaJasper+"\\");
                
               MasterPrintDAO print=new MasterPrintDAO();
               print.generarPDF(n, parameter, "");

   
    }
    public void verFormatoSubPDF_Sec(String n) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
       
    
                ////////System.out.println("cliente "+objeto.getD_asoc_negocio());
 
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();      
                String rutaImg = servletContext.getRealPath("//resources//images")+"/";
                String rutaJasper = servletContext.getRealPath("//jasper")+"/";


                //String id=dao.m.obtenerString("select isnull(id_aceptado,0) from [dbo].[GP_FINALIZACION] where id_requerimiento='"+objeto.getId()+"'");
                //System.out.println("N "+id);  
                Map parameter = new HashMap();
                parameter.put("id", ""+objetoSecundario.getId());
                //parameter.put("nom_cliente", objeto.getC_asoc_negocio()+"  "+ objeto.getD_asoc_negocio());
                parameter.put("logo", rutaImg);
                parameter.put("SUBREPORT_DIR", ""+rutaJasper+"\\");
                
               MasterPrintDAO print=new MasterPrintDAO();
               print.generarPDF(n, parameter, "");

   
    }
    private boolean isPosBack() {
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();
        return rpta;
    }

}
