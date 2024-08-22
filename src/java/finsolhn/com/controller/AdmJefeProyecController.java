/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.controller;

import finsolhn.com.dao.AdmJefeProDAO;
import finsolhn.com.dao.AdmUsuariosDAO;
import finsolhn.com.dao.MasterPrintDAO;
import finsolhn.com.model.Aceptacion;
import finsolhn.com.model.Daily;
import finsolhn.com.model.Empleado;
import finsolhn.com.model.Requerimientos;
import finsolhn.com.util.Constantes;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletContext;
import org.primefaces.PrimeFaces;

import org.primefaces.event.SelectEvent;

/**
 *
 * @author rcardona
 */
@Named("admJefeProyecController")
//@RequestScoped
@ViewScoped
public class AdmJefeProyecController extends DataGeneralController {
    //variables y objetos

    private AdmJefeProDAO dao = new AdmJefeProDAO();

    private Requerimientos objeto = new Requerimientos();
    private Requerimientos objetoSecundario = new Requerimientos();
    private Aceptacion objetoFinalizacion = new Aceptacion();
    private Daily diario=new Daily();
    
    
    private List<Requerimientos> lstReq;

    private List<Requerimientos> lstAprobarPend;
    private List<Aceptacion> lstAceptarPend;
    private List<Daily> lstDiariosFecha;
    private List<Daily> lstDiariosDesa=new ArrayList<Daily>();

    private List<Empleado> lstDeveloper;
    private Date fechaR1;
    private Date fechaR2;
    private Date fechaA1;
    private Date fechaA2;
    private Date fechaC1;
    private Date fechaC2;
    private String fil_c_desarrollador="";
    private String fil_nombre_proye="";
    private String fil_estado="";
    private String fil_prioridad="";
    private String fil_tipo="";

    public Daily getDiario() {
        return diario;
    }

    public void setDiario(Daily diario) {
        this.diario = diario;
    }

    public List<Daily> getLstDiariosFecha() {
        return lstDiariosFecha;
    }

    public void setLstDiariosFecha(List<Daily> lstDiariosFecha) {
        this.lstDiariosFecha = lstDiariosFecha;
    }

    public List<Daily> getLstDiariosDesa() {
        return lstDiariosDesa;
    }

    public void setLstDiariosDesa(List<Daily> lstDiariosDesa) {
        this.lstDiariosDesa = lstDiariosDesa;
    }

    
    
    
    public String getFil_tipo() {
        return fil_tipo;
    }

    public void setFil_tipo(String fil_tipo) {
        this.fil_tipo = fil_tipo;
    }
    

    
    
    
    public List<Empleado> getLstDeveloper() {
        return lstDeveloper;
    }

    public void setLstDeveloper(List<Empleado> lstDeveloper) {
        this.lstDeveloper = lstDeveloper;
    }

    public Date getFechaR1() {
        return fechaR1;
    }

    public void setFechaR1(Date fechaR1) {
        this.fechaR1 = fechaR1;
    }

    public Date getFechaR2() {
        return fechaR2;
    }

    public void setFechaR2(Date fechaR2) {
        this.fechaR2 = fechaR2;
    }

    public Date getFechaA1() {
        return fechaA1;
    }

    public void setFechaA1(Date fechaA1) {
        this.fechaA1 = fechaA1;
    }

    public Date getFechaA2() {
        return fechaA2;
    }

    public void setFechaA2(Date fechaA2) {
        this.fechaA2 = fechaA2;
    }

    public Date getFechaC1() {
        return fechaC1;
    }

    public void setFechaC1(Date fechaC1) {
        this.fechaC1 = fechaC1;
    }

    public Date getFechaC2() {
        return fechaC2;
    }

    public void setFechaC2(Date fechaC2) {
        this.fechaC2 = fechaC2;
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

    public String getFil_c_desarrollador() {
        return fil_c_desarrollador;
    }

    public void setFil_c_desarrollador(String fil_c_desarrollador) {
        this.fil_c_desarrollador = fil_c_desarrollador;
    }

    public String getFil_nombre_proye() {
        return fil_nombre_proye;
    }

    public void setFil_nombre_proye(String fil_nombre_proye) {
        this.fil_nombre_proye = fil_nombre_proye;
    }

    public String getFil_estado() {
        return fil_estado;
    }

    public void setFil_estado(String fil_estado) {
        this.fil_estado = fil_estado;
    }

    public String getFil_prioridad() {
        return fil_prioridad;
    }

    public void setFil_prioridad(String fil_prioridad) {
        this.fil_prioridad = fil_prioridad;
    }

    public Requerimientos getObjetoSecundario() {
        return objetoSecundario;
    }

    public void setObjetoSecundario(Requerimientos objetoSecundario) {
        this.objetoSecundario = objetoSecundario;
    }

    public Aceptacion getObjetoFinalizacion() {
        return objetoFinalizacion;
    }

    public void setObjetoFinalizacion(Aceptacion objetoFinalizacion) {
        this.objetoFinalizacion = objetoFinalizacion;
    }


    
    
    public boolean disable_bt_autorizar() {
        
        if (objeto.getEstado() != null) {
            if (objeto.getEstado().equalsIgnoreCase(Constantes.estado_sin_autortizar)) {
                return false;
            }

        }

        return true;
    }
    public boolean disable_bt_asignar() {
        
        if (objeto.getEstado() != null) {
            if (objeto.getEstado().equalsIgnoreCase(Constantes.estado_autorizado_ti)) {
                return false;
            }

        }

        return true;
    }
    
    public boolean disable_bt_autorizar_ayuda() {
        
        if (objetoSecundario.getEstado() != null) {
            if (objetoSecundario.getEstado().equalsIgnoreCase(Constantes.estado_sin_autortizar)) {
                return false;
            }

        }

        return true;
    }
    
    
    public void autorizacion_ayuda(String res) throws Exception {
        /*System.out.println(""+res);
        if(validaAprobacionJefe())
        {*/
            objetoSecundario.setC_usuario_jefe(getUser().getUsuario());
            objetoSecundario.setEstado(res);
            dao.autorizacion_ayuda(objetoSecundario);
            if(objetoSecundario.getTipo_registro().equalsIgnoreCase("A") || objetoSecundario.getTipo_registro().equalsIgnoreCase("B") )
            {
                lstAprobarPend.remove(objetoSecundario);
            }
            
            if(res.equalsIgnoreCase(Constantes.estado_rechazado))
            {
                String msj=Constantes.M3_mensaje+"\nNOMBRE REQUERIMIENTO:"+objetoSecundario.getNombre_corto()+"\nRESPUESTA: "+getUser().getNombre();
            
                enviarCorreoPuesto(objetoSecundario.getC_usuario(), Constantes.A1_asunto, msj);
            }else if(res.equalsIgnoreCase(Constantes.estado_autorizado_ti)){
                String msj=Constantes.M0_mensaje.
                replace("<solicita>",objetoSecundario.getD_usuario()).
                replace("<nombre_req>", objetoSecundario.getNombre_corto());
            
                enviarCorreoPuesto(Constantes.adm_db, Constantes.A1_asunto, msj);
            
            }
            
            
        /*}else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Este Perfil no puede Aprobar/Rechazar"));
        }*/
        

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

                dao.insertarReq(objeto);
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

    public void autorizacion(String res) throws Exception {
        
        
        objeto.setC_usuario_jefe(getUser().getUsuario());
        objeto.setEstado(res);
        //System.out.println("test "+objeto.getC_developer());
        dao.autorizacion_req(objeto);
        
         String msj="\n Requerimiento #"+objeto.getId()+"\nNombre Requerimiento:"+objeto.getNombre_corto()+"\nUSUARIO RESPUESTA:"+getUser().getNombre();
        if(res.equalsIgnoreCase(Constantes.estado_autorizado_ti))
        {
            enviarCorreoDirecto(objeto.getC_usuario().toString(), Constantes.A1_asunto, Constantes.M2_mensaje+msj);
        }else if(res.equalsIgnoreCase(Constantes.estado_rechazado)){
            enviarCorreoDirecto(objeto.getC_usuario().toString(), Constantes.A1_asunto, Constantes.M3_mensaje+msj);
        }
            
        
    }
    
    
    public void asignaDeveloper() throws Exception {
        
        if(objeto.getC_developer()==null)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Seleccione el Desarrollador"));
            System.out.println("ESTA NULL DEV");
        }else{
            
            objeto.setEstado(Constantes.estado_en_desarrollo);
            dao.asignarDev(objeto);
            String msj=Constantes.M4_mensaje.
                    replace("<numero>", objeto.getId()+"").
                    replace("<solicita>",objeto.getD_usuario()).
                    replace("<nombre_req>", objeto.getNombre_corto());
            
            enviarCorreoDirecto(objeto.getC_developer(), Constantes.A1_asunto, msj);
            
        }
        
    }

    public void eliminarVisita(Requerimientos obj) {
        /* try {

            objeto = obj;
            dao.eliminarVisita(objeto);
            lstObjetos.add(objeto);
            lstObjetosPendientes.remove(objeto);
           
        } catch (Exception e) {

        }*/
    }

    //listar todos mi requerimientos
    public void listarReq(String v) {

        try {//se verifica que no sea un POSTBACK
            if (v.equalsIgnoreCase("f")) {
                if (!isPosBack()) {

                    //si no es posback se ejecuta el metodo
                    lstReq = dao.listarRequerimientos("","R");
                    lstAprobarPend = dao.listarAyudas("");
                    lstAceptarPend = dao.listarCompletados("");
                    lstDiariosFecha=dao.listarDiarioFechas();
                }
            } else {
                //si es ejecutado desde un boton
                
                
                String where=" ";
                if(fil_nombre_proye!=null)
                {
                   where=where+" AND R.nombre like '%"+fil_nombre_proye+"%'";
                }
                if(fil_estado!=null)
                {
                    where=where+" AND R.estado like '%"+fil_estado+"%'";
                }
                if(fil_prioridad!=null)
                {
                    where=where+" AND R.prioridad like '%"+fil_prioridad+"%'";
                }
                if(fil_c_desarrollador!=null)
                {
                    where=where+" AND R.c_desarrollador like '%"+fil_c_desarrollador+"%'";
                }
                
                if(fechaR1!=null && fechaR2!=null)
                {
                    where=where+" AND R.[fecha_creado] >= '"+sdf_ymd.format(fechaR1)+" 00:00' AND R.[fecha_creado] <= '"+sdf_ymd.format(fechaR2)+" 23:59'";
                }
                
                if(fechaA1!=null && fechaA2!=null)
                {
                    where=where+" AND R.[fecha_asigno_desarrollo] >= '"+sdf_ymd.format(fechaA1)+" 00:00' AND R.[fecha_asigno_desarrollo] <= '"+sdf_ymd.format(fechaA2)+" 23:59'";
                }
                
                                
                if(fechaC1!=null && fechaC2!=null)
                {
                    where=where+" AND R.[fecha_completado] >= '"+sdf_ymd.format(fechaC1)+" 00:00' AND R.[fecha_completado] <= '"+sdf_ymd.format(fechaC2)+" 23:59'";
                }
                 
                System.out.println("where "+where);
                lstReq = dao.listarRequerimientos(where,fil_tipo);
                lstAprobarPend = dao.listarAyudas("");
                lstAceptarPend = dao.listarCompletados("");
                lstDiariosFecha=dao.listarDiarioFechas();
            }

        } catch (Exception e) {
            System.err.println("error: " + e);
        }
    }
    
    

    public void modificar(Requerimientos select) {
        
        objeto = select;
        operacion = "U";
        objeto.setLstDocument(dao.listarDocumentos(objeto));
        //RequestContext context = RequestContext.getCurrentInstance();  
        if(objeto.getTipo_registro().equalsIgnoreCase("R"))
        {
            PrimeFaces.current().executeScript("PF('wdlRequerimiento').show();");
        }else{
            PrimeFaces.current().executeScript("PF('wdlCaso').show();");
        }
    }

    public void ver_ayuda_enviada(Requerimientos select)
    {
        objetoSecundario=select;
        operacion="U";
        objeto=dao.obtenerObjetoReq(objetoSecundario.getId_req_padre());
        objeto.setLstDocument(dao.listarDocumentos(objeto));
        //RequestContext context = RequestContext.getCurrentInstance();
        if(objetoSecundario.getTipo_registro().equalsIgnoreCase("E"))
        {
            PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').show();");
        }else if (objetoSecundario.getTipo_registro().equalsIgnoreCase("P")){
            PrimeFaces.current().executeScript("PF('wdlPermiso').show();");
        }else if(objetoSecundario.getTipo_registro().equalsIgnoreCase("A"))
        {
            objetoSecundario.setLstTabla(dao.obtenerEstructuraTabla(select));
            PrimeFaces.current().executeScript("PF('wdlCargaProce').show();");
        }
        
        //objeto.setLstDocument(dao.listarDocumentos(objeto));
        
    }
    
    public void ver_ayuda_Origen()
    {
        /*
        operacion="U";
        objeto=dao.obtenerObjetoReq(objetoSecundario.getId_req_padre());
       */ 
        //RequestContext context = RequestContext.getCurrentInstance();
         
        if(objeto.getTipo_registro().equalsIgnoreCase("R"))
        {
            PrimeFaces.current().executeScript("PF('wdlRequerimiento').show();");
        }else{
            PrimeFaces.current().executeScript("PF('wdlCaso').show();");
        }
       
        //objeto.setLstDocument(dao.listarDocumentos(objeto));
        
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
            System.err.println("ID"+req.getId());
            objetoSecundario = dao.obtenerObjetoReq("" + req.getId());
            System.err.println("AM"+objetoSecundario.getAmbiente());
            //objetoSecundario.setLstDocument(dao.listarDocumentos(objetoSecundario));
            PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').show();");
            //if(objetoSecundario.getTipo_registro().equals("C"))
            //PrimeFaces.current().executeScript("PF('wdlCaso').show();");

        } else if (req.getTipo_registro().equalsIgnoreCase("P")) {
            objetoSecundario = dao.obtenerObjetoReq("" + req.getId());
            //objetoSecundario.setLstDocument(dao.listarDocumentos(objetoSecundario));
            PrimeFaces.current().executeScript("PF('wdlPermiso').show();");

        }else if (req.getTipo_registro().equalsIgnoreCase("A") || req.getTipo_registro().equalsIgnoreCase("U")) {
            objetoSecundario = dao.obtenerObjetoReq("" + req.getId());
            
            objetoSecundario.setLstTabla(dao.obtenerEstructuraTabla(req));
            PrimeFaces.current().executeScript("PF('wdlCargaProce').show();");

        }

        //objeto.setLstDocument(dao.listarDocumentos(objeto));
    }

    public void listarDocumentacion() {
        lstDocuemtacion = dao.listarDocumentacion(objetoFinalizacion);
    }
    
    public void listarDocumentacionRequerimiento() {
        lstDocuemtacion = dao.listarDocumentacionRequerimiento(objeto);
    }
        
    
    private List<Requerimientos> lstDocuemtacion;

    public List<Requerimientos> getLstDocuemtacion() {
        return lstDocuemtacion;
    }

    public void setLstDocuemtacion(List<Requerimientos> lstDocuemtacion) {
        this.lstDocuemtacion = lstDocuemtacion;
    }
    
    
    public void actualizarEstadoCompletado() {

        if (validaAprobacionJefe()) {
            try {

                objeto.setEstado(Constantes.estado_finalizado);
                objeto.setC_usuario_jefe(getUser().getUsuario());
                objetoFinalizacion.setC_usuario_jefe(getUser().getUsuario());
                objetoFinalizacion.setEstado(Constantes.estado_finalizado);

                dao.req_finalizado(objeto, objetoFinalizacion);

                listarReq("x");
            } catch (Exception e) {
                System.err.println("" + e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Este Perfil no puede finalizar un Requerimiento"));
        }

    }
        
    public void ver_origen_estructura()
    {
        objeto = dao.obtenerObjetoReq(""+objetoSecundario.getId_req_padre());
     
    }
    
    public void obtenerDaily(Daily obj) {
        
        diario=obj;
        
    }
    
   /* public void llenarListaDiario()
    {
        lstDiariosFecha=dao.listarDiario(getUser().getUsuario());
    }
    */
  /*  public void seleccionarFechaDiario(Daily obj)
    {
        this.diario=obj;
        llenarListaDiarioDesarrollador();
    }*/
    private void llenarListaDiarioDesarrollador()
    {
        lstDiariosDesa=dao.listarDiario(diario);
    }
    public void seleccionarDesaDiario(Daily obj)
    {
        this.diario=obj;
        //llenarListaDiarioDesarrollador();
    }
    
    public void respuestaDiario() {
       
        
       if(diario.getDetalle().isEmpty())
        {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Seleccione un Daily"));
            return;
        } 
        diario.setDetalle("");
        //RequestContext context = RequestContext.getCurrentInstance();
        PrimeFaces.current().executeScript("PF('wdlDiario').show();");
        
        
    }
    public void guardarDiario() throws Exception
    {
        if(diario.getDetalle().isEmpty())
        {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Llenar lo solicitado"));
            return;
        }   
        dao.insertarRespuestaDiario(diario,getUser().getNombre());
        llenarListaDiarioDesarrollador();
        
        String msj=Constantes.M13_mensaje.
        replace("<jefe>",getUser().getNombre()).
        replace("<fecha>", sdf.format(new Date()));

        enviarCorreoDirecto(diario.getC_desarrolador(), Constantes.A1_asunto, msj);
        
    }
    
    public void onRowSelect(SelectEvent event) {
        
       /*diario=(Daily)event.getObject();*/ /*Comentado ´por Kevin Anderson*/
        
       
        llenarListaDiarioDesarrollador();
       // FacesMessage msg = new FacesMessage("Car Selected", diario.getD_desarrolador());
       // FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    /*private void ob()
    {
        lstDiariosDesa=dao.listarDiario(diario);
    }*/
    public AdmJefeProyecController() {
        if (getUser().getUsuario().isEmpty()) {
            super.verificarSession();
            ////System.err.println("****HOLA  Usuario:*"+getUser().getUsuario()+"*");  
        }

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
        listarReq("f");
        lstDeveloper = dao.listarDeveloper();
        //listarAgencias();

    }

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
    public void verFormadoFin2PDF(String n) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
       
    
                ////////System.out.println("cliente "+objeto.getD_asoc_negocio());
 
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();      
                String rutaImg = servletContext.getRealPath("//resources//images")+"/";
                String rutaJasper = servletContext.getRealPath("//jasper")+"/";


                String id=dao.m.obtenerString("select isnull(id_aceptado,0) from [dbo].[GP_FINALIZACION] where id_requerimiento='"+objeto.getId()+"'");
                System.out.println("N "+id);  
                Map parameter = new HashMap();
                parameter.put("id", Integer.parseInt(id));
                //parameter.put("nom_cliente", objeto.getC_asoc_negocio()+"  "+ objeto.getD_asoc_negocio());
                parameter.put("logo", rutaImg);
                parameter.put("SUBREPORT_DIR", ""+rutaJasper+"\\");
                
               MasterPrintDAO print=new MasterPrintDAO();
               print.generarPDF(n, parameter, "");

   
    }
    public void verRequerimeintoPDF(String n) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
  
            
 
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();      
                //String rutaImg = servletContext.getRealPath("//jasper//images")+"/";


                Map parameter = new HashMap();
                parameter.put("id", objeto.getId());
                //parameter.put("nom_cliente", objeto.getC_asoc_negocio()+"  "+ objeto.getD_asoc_negocio());
                //parameter.put("logo", rutaImg);

               MasterPrintDAO print=new MasterPrintDAO();
               print.generarPDF(n, parameter, "");
  
   
    }
    public void verFormadoSQLPermisoPDF(String n) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
       
    
                ////////System.out.println("cliente "+objeto.getD_asoc_negocio());
 
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();      
                String rutaImg = servletContext.getRealPath("//resources//images")+"/";


                Map parameter = new HashMap();
                parameter.put("id", objetoSecundario.getId());
                //parameter.put("nom_cliente", objeto.getC_asoc_negocio()+"  "+ objeto.getD_asoc_negocio());
                parameter.put("logo", rutaImg);

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
