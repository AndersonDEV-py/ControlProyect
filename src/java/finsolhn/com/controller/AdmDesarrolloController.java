/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.controller;

import finsolhn.com.dao.AdmDesarrolloDAO;
import finsolhn.com.dao.AdmUsuariosDAO;
import finsolhn.com.dao.MasterPrintDAO;
import finsolhn.com.model.Aceptacion;
import finsolhn.com.model.Archivo;
import finsolhn.com.model.Daily;
import finsolhn.com.model.ModelTipoArchivo;
import finsolhn.com.model.MyFile;
import finsolhn.com.model.Requerimientos;
import finsolhn.com.model.TablaSQL;
import finsolhn.com.util.Constantes;
import finsolhn.com.util.SendMailEstilo;
import finsolhn.com.util.SendMailNormal;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.faces.event.AjaxBehaviorEvent;
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


import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;



/**
 *
 * @author rcardona
 *
 */
@Named("admDesarrolloController")
//@RequestScoped
//@ApplicationScoped
@ViewScoped
//@SessionScoped
//@RequestScoped 
public class AdmDesarrolloController extends DataGeneralController {

    //variables y objetos
    AdmDesarrolloDAO dao = new AdmDesarrolloDAO();

    private Requerimientos objeto = new Requerimientos();
    private Requerimientos objetoSecundario = new Requerimientos();
    private Requerimientos objetoEst = new Requerimientos();
    private Aceptacion objetoFinalizacion = new Aceptacion();
    private Archivo arch = new Archivo();
    private Daily diario=new Daily();
    

    private List<Requerimientos> lstReq;
    private List<Requerimientos> lstCasos;
    private List<Requerimientos> lstAyuda;
    private List<Requerimientos> lstHistorial;
    private List<Requerimientos> lstDocuemtacion;
    private List<Requerimientos> lstEstructurasSol;
    private List<Archivo> lstArchivos;
    private List<ModelTipoArchivo> lstTipo = Constantes.lstTipoArchivos;
    private List<ModelTipoArchivo> lstTipoBD = Constantes.lstTipoArchivosBase;
    private List<Daily> lstDiarios;
    
    private String Procesando = "";
    private String T = "";

    private String fil_nombre_proye = "";
    private String fil_estado = "";

    private String fil_tipo = "";
    private Date fechaR1;
    private Date fechaR2;
    private Date fechaA1;
    private Date fechaA2;
    private Date fechaC1;
    private Date fechaC2;

    //botones carga archivo versinado
    private boolean visibleBt1;
    private boolean visibleBt2;
    


    private boolean bt_finalizar;
    private boolean bt_cancelar;
    private boolean bt_resubir;
    
    private String tituloProcesa="PROCESAR";

    private int tipo_conOracle;

    public List<Daily> getLstDiarios() {
        return lstDiarios;
    }

    public void setLstDiarios(List<Daily> lstDiarios) {
        this.lstDiarios = lstDiarios;
    }

    
    
    
    public Daily getDiario() {
        return diario;
    }

    public void setDiario(Daily diario) {
        this.diario = diario;
    }

    
    
    
    public List<Requerimientos> getLstEstructurasSol() {
        return lstEstructurasSol;
    }

    public void setLstEstructurasSol(List<Requerimientos> lstEstructurasSol) {
        this.lstEstructurasSol = lstEstructurasSol;
    }

    
    
    public int getTipo_conOracle() {
        return tipo_conOracle;
    }

    public void setTipo_conOracle(int tipo_conOracle) {
        this.tipo_conOracle = tipo_conOracle;
    }
    
    
    
    public Requerimientos getObjetoEst() {
        return objetoEst;
    }

    public void setObjetoEst(Requerimientos objetoEst) {
        this.objetoEst = objetoEst;
    }

    
    
    
    public List<ModelTipoArchivo> getLstTipoBD() {
        return lstTipoBD;
    }

    public void setLstTipoBD(List<ModelTipoArchivo> lstTipoBD) {
        this.lstTipoBD = lstTipoBD;
    }

    
    
    public String getTituloProcesa() {
        return tituloProcesa;
    }

    public void setTituloProcesa(String tituloProcesa) {
        this.tituloProcesa = tituloProcesa;
    }
    
    
    public boolean isBt_finalizar() {
        return bt_finalizar;
    }

    public void setBt_finalizar(boolean bt_finalizar) {
        this.bt_finalizar = bt_finalizar;
    }

    public boolean isBt_cancelar() {
        return bt_cancelar;
    }

    public void setBt_cancelar(boolean bt_cancelar) {
        this.bt_cancelar = bt_cancelar;
    }

    public boolean isBt_resubir() {
        return bt_resubir;
    }

    public void setBt_resubir(boolean bt_resubir) {
        this.bt_resubir = bt_resubir;
    }
    
    
    
    public List<ModelTipoArchivo> getLstTipo() {
        return lstTipo;
    }

    public void setLstTipo(List<ModelTipoArchivo> lstTipo) {
        this.lstTipo = lstTipo;
    }

    public Archivo getArch() {
        return arch;
    }

    public void setArch(Archivo arch) {
        this.arch = arch;
    }

    public List<Archivo> getLstArchivos() {
        return lstArchivos;
    }

    public void setLstArchivos(List<Archivo> lstArchivos) {
        this.lstArchivos = lstArchivos;
    }

    public List<Requerimientos> getLstDocuemtacion() {
        return lstDocuemtacion;
    }

    public void setLstDocuemtacion(List<Requerimientos> lstDocuemtacion) {
        this.lstDocuemtacion = lstDocuemtacion;
    }

    public List<Requerimientos> getLstHistorial() {
        return lstHistorial;
    }

    public void setLstHistorial(List<Requerimientos> lstHistorial) {
        this.lstHistorial = lstHistorial;
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

    public String getFil_tipo() {
        return fil_tipo;
    }

    public void setFil_tipo(String fil_tipo) {
        this.fil_tipo = fil_tipo;
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

    public String getT() {
        return T;
    }

    public void setT(String T) {
        this.T = T;
    }

    public String getProcesando() {
        return Procesando;
    }

    public void setProcesando(String Procesando) {
        this.Procesando = Procesando;
    }

    public Aceptacion getObjetoFinalizacion() {
        return objetoFinalizacion;
    }

    public void setObjetoFinalizacion(Aceptacion objetoFinalizacion) {
        this.objetoFinalizacion = objetoFinalizacion;
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

    public List<Requerimientos> getLstAyuda() {
        return lstAyuda;
    }

    public void setLstAyuda(List<Requerimientos> lstAyuda) {
        this.lstAyuda = lstAyuda;
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

    public Requerimientos getObjetoSecundario() {
        return objetoSecundario;
    }

    public void setObjetoSecundario(Requerimientos objetoSecundario) {
        this.objetoSecundario = objetoSecundario;
    }

    public boolean isVisibleBt1() {
        return visibleBt1;
    }

    public void setVisibleBt1(boolean visibleBt1) {
        this.visibleBt1 = visibleBt1;
    }

    public boolean isVisibleBt2() {
        return visibleBt2;
    }

    public void setVisibleBt2(boolean visibleBt2) {
        this.visibleBt2 = visibleBt2;
    }

    public boolean isBt_daily() {
        return bt_daily;
    }

    public void setBt_daily(boolean bt_daily) {
        this.bt_daily = bt_daily;
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

    public void actualizarEstadoCompletado() {

        System.out.println("solucion:" + objetoFinalizacion.getDescripcion_solucion() + " AG:" + objetoFinalizacion.getC_agencia());
        if (objetoFinalizacion.getDescripcion_solucion() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Ingrese el detalle de la solucion"));
            return;
        }

        try {

            if (T.equalsIgnoreCase("R") || T.equalsIgnoreCase("C")) {
                objeto.setEstado(Constantes.estado_completado);
                dao.modificarReq_Caso(objeto);
            }
            dao.insertarFinalizacion(objetoFinalizacion);
            
            String msj=Constantes.M7_mensaje.replace("<desa>", getUser().getNombre()).
                        replace("<nombre_req>", objeto.getNombre_corto()).
                    replace("<numero>",objeto.getId()+"");
            enviarCorreoPuesto(Constantes.apr_operaciones, Constantes.A1_asunto, msj);
            
        } catch (Exception e) {
            System.err.println("" + e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
        }

    }

    public void actualizarAyudaEstadoCompletado() {

        try {

            objetoSecundario.setEstado(Constantes.estado_finalizado);
            dao.modificarReq_Caso(objetoSecundario);

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
        }

    }

    public void aceptarCaso() {

        try {

            objeto.setEstado(Constantes.estado_en_desarrollo);
            objeto.setC_developer(getUser().getUsuario());
            dao.modificarCasoAceptar(objeto);
            listarReq("x");
            
            String msj=Constantes.M6_mensaje.replace("<desa>", getUser().getNombre()).
                        replace("<nombre_req>", objeto.getNombre_corto()).
                    replace("<numero>",objeto.getId()+"");
            enviarCorreoDirecto(objeto.getC_usuario(), Constantes.A1_asunto, msj);
            
            
        } catch (Exception e) {
            System.out.println("EXEPTION:"+e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
        }

    }
        public void rechazarCaso() {

        try {

            objeto.setEstado(Constantes.estado_rechazado);
            objeto.setC_developer(getUser().getUsuario());
            dao.modificarCasoRechazar(objeto);
            listarReq("x");
            
            String msj=Constantes.M3_mensaje+"\nNombre del Caso:"+objeto.getNombre_corto();
            enviarCorreoDirecto(objeto.getC_usuario(), Constantes.A1_asunto, msj);
            
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
        }

    }
    public void insertarCasoE() {

        //revisa si es una insercion
        // if (operacion.equalsIgnoreCase("I")) {
        try {
            if (objetoSecundario.getCant_reg_afec() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Cantidad de Registros Afectados no puede ser menor o igual a 0"));
                return;
            }
            dao.insertarCasoEjecucion(objetoSecundario);
            listarReq("x");
            
              String msj=Constantes.M5_mensaje.replace("<solicita>", getUser().getNombre()).
                        replace("<nombre_req>", objetoSecundario.getNombre_corto()+" - "+objeto.getNombre_corto());
                   
            enviarCorreoPuesto(Constantes.apr_operaciones, Constantes.A1_asunto, msj);
            
            
            //agrega objeto a la tabla requerimientos
            //lstReq.add(objeto);
        } catch (Exception e) {
            System.out.println("Bean inser err " + e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
        }
        /* } else { 
            try {
               // dao.modificarCaso(objeto);
              
            } catch (Exception e) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
            }
        }*/
    }

    public void insertarCasoP() {

        //revisa si es una insercion
        // if (operacion.equalsIgnoreCase("I")) {
        try {

            dao.insertarCasoPermiso(objetoSecundario);
            listarReq("x");
            String msj=Constantes.M5_mensaje.replace("<solicita>", getUser().getNombre()).
                        replace("<nombre_req>", objetoSecundario.getNombre_corto()+" - "+objeto.getNombre_corto());
                   
            enviarCorreoPuesto(Constantes.apr_operaciones, Constantes.A1_asunto, msj);
            
            //agrega objeto a la tabla requerimientos
            //lstReq.add(objeto);
        } catch (Exception e) {
            System.out.println("Bean inser err " + e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
        }
        /* } else {  
            try {
                
               // dao.modificarCaso(objeto);
              
            } catch (Exception e) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
            }
        }*/
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
                    lstReq = dao.listarMisRequerimientos(getUser().getUsuario());
                    lstCasos = dao.listarCasos(getUser().getUsuario());
                    lstAyuda = dao.listarAyduas(getUser().getUsuario());
                }
            } else {
                lstReq = dao.listarMisRequerimientos(getUser().getUsuario());
                lstCasos = dao.listarCasos(getUser().getUsuario());
                lstAyuda = dao.listarAyduas(getUser().getUsuario());

                String where = " ";
                if (fil_nombre_proye != null) {
                    where = where + " AND R.nombre like '%" + fil_nombre_proye + "%'";
                }
                if (fil_estado != null) {
                    where = where + " AND R.estado like '%" + fil_estado + "%'";
                }

                if (fechaR1 != null && fechaR2 != null) {
                    where = where + " AND R.[fecha_creado] >= '" + sdf_ymd.format(fechaR1) + " 00:00' AND R.[fecha_creado] <= '" + sdf_ymd.format(fechaR2) + " 23:59'";
                }

                if (fechaA1 != null && fechaA2 != null) {
                    where = where + " AND R.[fecha_asigno_desarrollo] >= '" + sdf_ymd.format(fechaA1) + " 00:00' AND R.[fecha_asigno_desarrollo] <= '" + sdf_ymd.format(fechaA2) + " 23:59'";
                }

                if (fechaC1 != null && fechaC2 != null) {
                    where = where + " AND R.[fecha_completado] >= '" + sdf_ymd.format(fechaC1) + " 00:00' AND R.[fecha_completado] <= '" + sdf_ymd.format(fechaC2) + " 23:59'";
                }

                where = where + " and R.c_desarrollador='" + getUser().getUsuario() + "' ";

                lstHistorial = dao.listarHistorial(where, fil_tipo);
                //si es ejecutado desde un boton
                System.err.println("v: " + v);
            }

        } catch (Exception e) {
            System.err.println("error: " + e);
        }
    }

    public AdmDesarrolloController() {
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

    public boolean disable_bt_completar() {

        if (objeto.getEstado() != null) {

            if (objeto.getEstado().equalsIgnoreCase(Constantes.estado_en_desarrollo)) {
                return false;
            }

        }

        return true;
    }

    public boolean disable_bt_aceptar_c() {

        if (objeto.getEstado() != null) {
            if (objeto.getEstado().equalsIgnoreCase(Constantes.estado_en_espera)) {
                return false;
            }

        }

        return true;
    }

    public boolean disable_bt_completar_c() {

        if (objeto.getEstado() != null) {

            if (objeto.getEstado().equalsIgnoreCase(Constantes.estado_en_desarrollo)
                    && objeto.getC_developer().equalsIgnoreCase(getUser().getUsuario())) {
                return false;
            }

        }

        return true;
    }

    public boolean disable_bt_completar_ayuda() {

        if (objetoSecundario.getEstado() != null) {

            if (objetoSecundario.getEstado().equalsIgnoreCase(Constantes.estado_completado_dba)) {
                return false;
            }

        }

        return true;
    }

    
    public void guardarDiario() throws Exception
    {
        if(diario.getDetalle().isEmpty())
        {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Llenar lo solicitado"));
            return;
        }
        
        dao.insertarDiario(diario);
        llenarListaDiario();
        bt_daily=false;
        
        String msj=Constantes.M8_mensaje.replace("<desa>", getUser().getNombre()).
                        replace("<fecha>", sdf.format(new Date()));
        enviarCorreoPuesto(Constantes.apr_ti, Constantes.A1_asunto, msj);
    }
    
    private boolean bt_daily=true;
    public void nuevoDiario() {
        diario = new Daily();
        diario.setC_desarrolador(getUser().getUsuario());
        diario.setD_desarrolador(getUser().getNombre());
        diario.setDetalle("");
        llenarListaDiario();
        bt_daily=true;
    }
    public void obtenerDaily(Daily obj) {
        
        diario=obj;
        bt_daily=false;
    }
    
    public void llenarListaDiario()
    {
        lstDiarios=dao.listarDiario(getUser().getUsuario());
    }
    
    public void nuevo() {
        objeto = new Requerimientos();
        objeto.setC_usuario(getUser().getUsuario());
        objeto.setD_usuario(getUser().getNombre());
        objeto.setC_agencia(getUser().getC_agencia());
        objeto.setD_agencia(getUser().getD_agencia());

        Date objDate = new Date();
        objeto.setFecha(sdf.format(objDate));
        operacion = "I";

    }

    public void modificar(Requerimientos select) {
        objetoSecundario = null;

        Procesando = "";
        objeto = select;
        operacion = "U";
        objeto.setLstDocument(dao.listarDocumentos(objeto));

    }

    public void ver_modificar(Requerimientos select) {

        Procesando = "VER";
        objeto = select;
        operacion = "U";
        objeto.setLstDocument(dao.listarDocumentos(objeto));
        //RequestContext context = RequestContext.getCurrentInstance();

        if (objeto.getTipo_registro().equalsIgnoreCase("R")) {
            PrimeFaces.current().executeScript("PF('wdlRequerimiento').show();");
        } else {
            PrimeFaces.current().executeScript("PF('wdlCaso').show();");
        }
    }

    public void nueva_Ayuda(String nombre) {

        objetoSecundario = new Requerimientos();
        objetoSecundario.setId_req_padre("" + objeto.getId());
        objetoSecundario.setC_area(objeto.getC_area());
        objetoSecundario.setC_usuario(getUser().getUsuario());
        objetoSecundario.setD_usuario(getUser().getNombre());
        objetoSecundario.setC_agencia(getUser().getC_agencia());
        objetoSecundario.setD_agencia(getUser().getD_agencia());
        objetoSecundario.setNombre_corto(nombre);
        Date objDate = new Date();
        objetoSecundario.setFecha(sdf.format(objDate));
        operacion = "I";
        if (nombre.equalsIgnoreCase("Permiso Sobre Objeto")) {
            objetoSecundario.setDescripcion("\n\n\n\nUSUARIO: ");
        }
    }
    
    public void nueva_Ayuda_Estructura() {

        objetoEst = new Requerimientos();
        objetoEst.setId_req_padre("" + objeto.getId());
        objetoEst.setC_area(objeto.getC_area());
        objetoEst.setC_usuario(getUser().getUsuario());
        objetoEst.setD_usuario(getUser().getNombre());
        objetoEst.setC_agencia(getUser().getC_agencia());
        objetoEst.setD_agencia(getUser().getD_agencia());
        
        //objetoEst.setTipo_registro("A");
        
        
        Date objDate = new Date();
        objetoEst.setFecha(sdf.format(objDate));
        operacion = "I";
        
    }
    public void nuevo_Campo() {

        if(objetoEst.getObjetoTarea().equalsIgnoreCase("Tabla"))
        {
            objetoEst.setTbl(new TablaSQL());
            objetoEst.getTbl().setNombre("");
            objetoEst.getTbl().setTipo("");
            objetoEst.getTbl().setTamano("");
            objetoEst.getTbl().setConstraint("");
            
            //RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlCampo').show();");
            
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Tiene que seleccionar Objeto Tabla"));
        }
        
    }
    
    public void deleteRow() {
        if(objetoEst.getTbl()!=null)
        {
            objetoEst.getLstTabla().remove(objetoEst.getTbl());
        }
        
    }
    
    public void obtenerTablaEstructura()
    {
        if(objetoEst.getObjetoTarea().equalsIgnoreCase("Tabla"))
        {
            objetoEst.setLstTabla(dao.listarEstructuraTabla(objetoEst,tipo_conOracle));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Tiene que seleccionar Objeto Tabla"));
        }
        
        
        
    }
    

    public void ver_ayuda_enviada(Requerimientos select) {
        Procesando = "";
        objetoSecundario = select;
        operacion = "U";
        objeto = dao.obtenerObjetoReq(objetoSecundario.getId_req_padre());
        objeto.setLstDocument(dao.listarDocumentos(objeto));
        //RequestContext context = RequestContext.getCurrentInstance();
        if (objetoSecundario.getTipo_registro().equalsIgnoreCase("E")) {
            PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').show();");
        } else {
            PrimeFaces.current().executeScript("PF('wdlPermiso').show();");
        }

        //objeto.setLstDocument(dao.listarDocumentos(objeto));
    }

    public void ver_ayuda_Origen() {
        /*
        operacion="U";
        objeto=dao.obtenerObjetoReq(objetoSecundario.getId_req_padre());
         */
        System.out.println("origen p " + objeto.getTipo_registro());
        //RequestContext context = RequestContext.getCurrentInstance();

        if (objeto.getTipo_registro().equalsIgnoreCase("R")) {
            PrimeFaces.current().executeScript("PF('wdlRequerimiento').show();");
        } else {
            PrimeFaces.current().executeScript("PF('wdlCaso').show();");
        }
        System.out.println("origen p " + objeto.getTipo_registro());
        //objeto.setLstDocument(dao.listarDocumentos(objeto));

    }

    public void ver_Origen_Completar() {
        /*
        operacion="U";
        objeto=dao.obtenerObjetoReq(objetoSecundario.getId_req_padre());
         */
        //RequestContext context = RequestContext.getCurrentInstance();

        if (objeto.getTipo_registro().equalsIgnoreCase("R")) {
            PrimeFaces.current().executeScript("PF('wdlRequerimiento').show();");
        } else if (objeto.getTipo_registro().equalsIgnoreCase("C")) {
            if (objetoSecundario != null && objetoSecundario.getTipo_registro() != null) {
                if (objetoSecundario.getTipo_registro().equals("E")) {
                    PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').show();");
                } else if (objetoSecundario.getTipo_registro().equals("P")) {
                    PrimeFaces.current().executeScript("PF('wdlPermiso').show();");
                } else {
                    PrimeFaces.current().executeScript("PF('wdlCaso').show();");
                }
            } else {
                PrimeFaces.current().executeScript("PF('wdlCaso').show();");
            }

        } else if (objeto.getTipo_registro().equalsIgnoreCase("E")) {
            //if(objetoSecundario.getTipo_registro().equals("C"))
            //PrimeFaces.current().executeScript("PF('wdlCaso').show();");

        } else if (objeto.getTipo_registro().equalsIgnoreCase("P")) {

        }

        //objeto.setLstDocument(dao.listarDocumentos(objeto));
    }

    public void nuevo_Completar(String tr) {

        T = tr;
        System.out.println("tipo " + objeto.getTipo_registro());
//        System.out.println("tipo2 "+objetoSecundario.getTipo_registro());

        objetoFinalizacion = new Aceptacion();
        objetoFinalizacion.setId_req_padre("" + objeto.getId());
        objetoFinalizacion.setC_usuario(objeto.getC_usuario());
        objetoFinalizacion.setD_usuario(objeto.getD_usuario());
        objetoFinalizacion.setC_area(objeto.getC_area());
        objetoFinalizacion.setC_agencia(objeto.getC_agencia());
        objetoFinalizacion.setD_agencia(objeto.getD_agencia());
        objetoFinalizacion.setC_developer(objeto.getC_developer());
        objetoFinalizacion.setD_developer(objeto.getD_developer());
        objetoFinalizacion.setDescripcion_causa(objeto.getDescripcion());
        objetoFinalizacion.setAplicativo(objeto.getAplicativo());
        objetoFinalizacion.setEspecifique_otro(objeto.getEspecifique_otro());
        objetoFinalizacion.setFecha(objeto.getFecha());

        // System.out.println(""+objeto.getNombre_corto()+"  "+objetoSecundario.getNombre_corto());
        Date objDate = new Date();
        objetoFinalizacion.setFecha_completado(sdf.format(objDate));
        operacion = "I";
        Procesando = "COMPLETAR";
        System.out.println("completar");

    }

    ////////VER ARCHIVO EXTERNO @RequestScoped 
    public StreamedContent mostrarImagen(String nombreArchivo) throws IOException {

        ByteArrayOutputStream out = null;
        //deprecate buscar remplazp
        /*if (nombreArchivo == null) {
            out = traerArchivo("C:/temp/", nombreArchivo);
            InputStream myInputStream2 = new ByteArrayInputStream(out.toByteArray());
            return new DefaultStreamedContent(myInputStream2);
        }
        if (nombreArchivo.length() > 0) {
            out = traerArchivo("C:/temp/", nombreArchivo);
            InputStream myInputStream2 = new ByteArrayInputStream(out.toByteArray());
            return new DefaultStreamedContent(myInputStream2);

        } else {
            out = traerArchivo("C:/temp/", nombreArchivo);
            InputStream myInputStream2 = new ByteArrayInputStream(out.toByteArray());
            return new DefaultStreamedContent(myInputStream2);
        }*/
        
        return null;
    }

    public ByteArrayOutputStream traerArchivo(String ruta, String nombre_archivo) {
        ByteArrayOutputStream out = null;
        String path = ruta + nombre_archivo;
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
        return out;
    }

    ////////////////////// FIN VER ARCHIVO EXTERNO 
    public void listarDocumentacionRequerimiento() {
        lstDocuemtacion = dao.listarDocumentacionRequerimiento(objeto);
    }

    public void verFormadoFin2PDF(String n) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        ////////System.out.println("cliente "+objeto.getD_asoc_negocio());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String rutaImg = servletContext.getRealPath("//resources//images") + "/";
        String rutaJasper = servletContext.getRealPath("//jasper") + "/";

        String id = dao.m.obtenerString("select isnull(id_aceptado,0) from [dbo].[GP_FINALIZACION] where id_requerimiento='" + objeto.getId() + "'");
        System.out.println("N " + id);
        Map parameter = new HashMap();
        parameter.put("id", Integer.parseInt(id));
        //parameter.put("nom_cliente", objeto.getC_asoc_negocio()+"  "+ objeto.getD_asoc_negocio());
        parameter.put("logo", rutaImg);
        parameter.put("SUBREPORT_DIR", "" + rutaJasper + "\\");

        MasterPrintDAO print = new MasterPrintDAO();
        print.generarPDF(n, parameter, "");

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
            System.err.println("ID" + req.getId());
            objetoSecundario = dao.obtenerObjetoReq("" + req.getId());
            System.err.println("AM" + objetoSecundario.getAmbiente());
            //objetoSecundario.setLstDocument(dao.listarDocumentos(objetoSecundario));
            PrimeFaces.current().executeScript("PF('wdlEjecutaSQL').show();");
            //if(objetoSecundario.getTipo_registro().equals("C"))
            //PrimeFaces.current().executeScript("PF('wdlCaso').show();");

        } else if (req.getTipo_registro().equalsIgnoreCase("P")) {
            objetoSecundario = dao.obtenerObjetoReq("" + req.getId());
            //objetoSecundario.setLstDocument(dao.listarDocumentos(objetoSecundario));
            PrimeFaces.current().executeScript("PF('wdlPermiso').show();");

        }else if (req.getTipo_registro().equalsIgnoreCase("A") || req.getTipo_registro().equalsIgnoreCase("U")) {
            objetoEst = dao.obtenerObjetoReq("" + req.getId());
            objetoEst.setTipo_registro("x");
            objetoEst.setEstado("x");
            objetoEst.setLstTabla(dao.obtenerEstructuraTabla(req));
            PrimeFaces.current().executeScript("PF('wdlCargaProce').show();");

        }

        //objeto.setLstDocument(dao.listarDocumentos(objeto));
    }

    public void nuevoArchivoDescargaCarga() {

        arch = new Archivo();
        arch.setNombre_archivo("");
        arch.setTipo_archivo("");
        arch.setComentario("");
    }

    public void agregarArchivoLista(String proceso) throws Exception {
        if (lstArchivos == null) {
            lstArchivos = new ArrayList();
        }

        arch.setEstado(1);
        arch.setIdArchivo(lstArchivos.size() + 1);
        arch.setC_usuario_sol(getUser().getUsuario());
        arch.setIdReq("" + objeto.getId());
        arch.setTipo_proceso(proceso);

        String IDT = dao.insertarArchivoTOP_DESCARGA(arch);
        if (!IDT.trim().equalsIgnoreCase("0")) {
            arch.setIdArchivo(Integer.parseInt(IDT));
            lstArchivos.add(arch);
        }
        String msj=Constantes.M0_mensaje.replace("<solicita>", getUser().getNombre()).
                        replace("<nombre_req>", objeto.getNombre_corto()+" - "+proceso+": "+arch.getNombre_archivo());
        enviarCorreoPuesto(Constantes.adm_sop, Constantes.A1_asunto, msj);

    }
    
    public void agregarCampo()
    {
        if(objetoEst.getLstTabla()==null)
        {
            objetoEst.setLstTabla(new ArrayList());
            
        }
        
        objetoEst.getLstTabla().add(objetoEst.getTbl());
        
    }
    
    public void insertarEstructura(String d) {

        //revisa si es una insercion
        // if (operacion.equalsIgnoreCase("I")) {
        try {
            
            if(d.equalsIgnoreCase("D"))
            {   System.out.println("GUARDANDO..");
                objetoEst.setTarea("Modificar");
                objetoEst.setEstado(Constantes.estado_en_dba);
                dao.insertarEstructura(objetoEst);
                System.out.println("GUARDADO.");
                String msj=Constantes.M5_mensaje.replace("<solicita>", getUser().getNombre()).
                        replace("<nombre_req>", objeto.getNombre_corto()+" - DESCARGA SCRIPT: "+objetoEst.getNombre_corto());
                enviarCorreoPuesto(Constantes.adm_db, Constantes.A1_asunto, msj);
                System.out.println("ok.");
            }else{
                try {
                    objetoEst.setEstado(Constantes.estado_sin_autortizar_area);
                    System.out.println("tipo:"+objetoEst.getTipo_registro());
                } catch (Exception e) {
                      System.out.println("null estado. "+e );
                }
                
                
                if(objetoEst.getNombre_corto().isEmpty() || objetoEst.getTarea().isEmpty() || objetoEst.getAmbiente().isEmpty() || objetoEst.getObjetoTarea().isEmpty() 
                        || objetoEst.getDes_usuarios_utilizan().size()==0 || objetoEst.getActividades_usuarios().length<1)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
                    return;
                }
                try {
                    if(objetoEst.getScriptSQL().isEmpty())
                    {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Llene el script de lo que necesita"));
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("script null"+e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Llene el script de lo que necesita"));
                    return;
                }
              
                try {
                    
                
                    if(objetoEst.getObjetoTarea().equalsIgnoreCase("Tabla"))
                    {

                        if(objetoEst.getLstTabla().isEmpty())
                        {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "La Tabla esta vacia"));
                            return;
                        }
                    }

                } catch (Exception e) {
                    System.out.println("tarea null"+e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "La Tabla esta vacia"));
                    return;
                }
                
               
                if(objetoEst.getTipo_registro()!=null && objetoEst.getTipo_registro().equalsIgnoreCase("U"))
                {
                   // objetoEst.setEstado(Constantes.estado_sin_autortizar_area);
                    objetoEst.setTipo_registro("A");
                    dao.updateEstructuraNueva(objetoEst);
                    
                    String msj=Constantes.M5_mensaje.replace("<solicita>", getUser().getNombre()).
                        replace("<nombre_req>", objeto.getNombre_corto()+" - CARGA ESTRUCTURA EXISTENTE: "+objetoEst.getNombre_corto());
                    enviarCorreoPuesto(Constantes.apr_operaciones, Constantes.A1_asunto, msj);
                
                }else{
                    dao.insertarEstructuraNueva(objetoEst);
                    
                    String msj=Constantes.M5_mensaje.replace("<solicita>", getUser().getNombre()).
                        replace("<nombre_req>", objeto.getNombre_corto()+" - CARGA ESTRUCTURA NUEVA: "+objetoEst.getNombre_corto());
                    enviarCorreoPuesto(Constantes.apr_operaciones, Constantes.A1_asunto, msj);
                }
               
              
            }
            
            //listarReq("x");
            //agrega objeto a la tabla requerimientos
            //lstReq.add(objeto);
        } catch (Exception e) {
            System.out.println("Bean inser err " + e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
        }
        
        listarSolicitudEstructuras();
        /* } else {  
            try {
                
               // dao.modificarCaso(objeto);
              
            } catch (Exception e) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
            }
        }*/
    }
    
    public void finalizarEstructura() {

        //revisa si es una insercion
        // if (operacion.equalsIgnoreCase("I")) {
        try {
            objetoEst.setEstado(Constantes.estado_finalizado);
            
            dao.finEstructura(objetoEst);
  
        } catch (Exception e) {
            System.out.println("finalizar err " + e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error"));
        }
        
        listarSolicitudEstructuras();
     
    }
    
    
    public void procesar_Archivo(String proceso) throws Exception
    {
        if(proceso.equalsIgnoreCase("CANCELADO"))
        {
           if(arch.getComentario_nuevo().equalsIgnoreCase(""))
           {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Para cancelar tienen que ingresar un comentario"));
                return;
           }
          arch.setEstado(4);//denegado
          //arch.setVisibleBtIndicador3(false);
          //arch.setVisibleBtIndicador4(false);
        }else if(proceso.equalsIgnoreCase("SUBIR"))
        {
            if(arch.isVisibleBtIndicador1()==false || (arch.isVisibleBtIndicador2()==false && arch.getTipo_archivo().split(";").length>1)  )
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Tiene que cargar los archivos solicitados"));
                return;
            }
            if(arch.getTipo_proceso().equalsIgnoreCase("DESCARGA"))
            {
                arch.setTipo_proceso("CARGA_MODIFICADO");
                arch.setEstado(2);
                
            }else if(arch.getTipo_proceso().equalsIgnoreCase("CARGA_NUEVO"))
            {
                arch.setEstado(1);
            }else if(arch.getTipo_proceso().equalsIgnoreCase("CARGA_MODIFICADO"))
            {
                  arch.setEstado(2);
            }
            
            String msj=Constantes.M0_mensaje.replace("<solicita>", getUser().getNombre()).
                        replace("<nombre_req>", objeto.getNombre_corto()+" - "+proceso+": "+arch.getNombre_archivo());
            enviarCorreoPuesto(Constantes.adm_sop, Constantes.A1_asunto, msj);
        
            //arch.setEstado(2);
        }else if(proceso.equalsIgnoreCase("FINALIZAR"))
        {
            
            arch.setEstado(0);
            lstArchivos.remove(arch);
        }
        
        if(arch.getComentario_nuevo().length()>1)
           arch.setComentario_nuevo("/R1/"+arch.getComentario_nuevo());
        
        
        dao.modificarArchivoTOP_DESCARGA(arch,proceso);
        //Notificar();
        
       
        
    }
    
    public void Notificar()
    {
        String summary="Hola";
        String detail="holaxxxx";
        String CHANNEL="/notify";
        /*
        EventBus evenBus=EventBusFactory.getDefault().eventBus();
        evenBus.publish(CHANNEL,new FacesMessage( StringEscapeUtils.escapeHtml(summary)  , StringEscapeUtils.escapeHtml(detail)  ));
        */
       /*FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario: ", detail);
       PushContext pushContext = PushContextFactory.getDefault().getPushContext();
       pushContext.push("/notify",  msg);
       System.out.println("......");
       FacesContext.getCurrentInstance().addMessage(null, msg);*/
    }
    

    public void listarArchivos() {
        try {

            lstArchivos=dao.listarArchivosTOP(objeto.getId()+"");
        } catch (Exception e) {
            System.out.println("Bean listar archivos err " + e);
            
        }
    }
    
    
    public void listarSolicitudEstructuras() {
        try {

            lstEstructurasSol=dao.listarSolicitudEstructura(getUser().getUsuario(),""+objeto.getId());
        } catch (Exception e) {
            System.out.println("Bean listar archivos err " + e);
            
        }
    }
    
    
    public void seleccionado(Archivo obj)
    {
        arch=obj;
        listenerComboTipo2();
        visibleBt1=true;
        visibleBt2=true;
            
        bt_resubir=true;
        bt_finalizar=true;
        bt_cancelar=true;
        
        if(arch.getEstado()==1 && arch.getTipo_proceso().equalsIgnoreCase("DESCARGA"))
        {
            tituloProcesa="SOLICITUD DE DESCARGA";
            visibleBt1=false;
            visibleBt2=false;
            bt_resubir=false;
            bt_finalizar=false;
            bt_cancelar=true;
            //colorBack=gris;
        }else if(arch.getEstado()==2 && arch.getTipo_proceso().equalsIgnoreCase("DESCARGA"))
        {   
            tituloProcesa="RESPUESTA DE: SOLICITUD DE DESCARGA";
            visibleBt1=true;
            visibleBt2=true;
            bt_resubir=true;
            bt_finalizar=false;
            bt_cancelar=true;
           // colorBack=amarillo;
           listenerComboTipo2();
        }else if(arch.getEstado()==1 && arch.getTipo_proceso().equalsIgnoreCase("CARGA_NUEVO"))
        {
            tituloProcesa="SOLICITUD DE CARGAR ARCHIVO NUEVO";
            visibleBt1=true;
            visibleBt2=true;
            bt_resubir=true;
            bt_finalizar=false;
            bt_cancelar=true;
            //colorBack=azul;
            listenerComboTipo2();
        }else if(arch.getEstado()==2 && arch.getTipo_proceso().equalsIgnoreCase("CARGA_MODIFICADO"))
        {
            tituloProcesa="SOLICITUD DE CARGAR ARCHIVO EXISTENTE";
            visibleBt1=true;
            visibleBt2=true;
            bt_resubir=true;
            bt_finalizar=false;
            bt_cancelar=true;
           //colorBack=amarillo; 
           listenerComboTipo2();
        }else if(arch.getEstado()==3)
        {   
            tituloProcesa="FINALIZAR SOLICITUD COMPLETADA";
            visibleBt1=true;
            visibleBt2=true;
            bt_resubir=true;
            bt_finalizar=true;
            bt_cancelar=false;
            //colorBack=verde;
            listenerComboTipo2();
                    
        }else if(arch.getEstado()==4)
        {
            tituloProcesa="SOLICITUD RECHAZADA";
            visibleBt1=false;
            visibleBt2=false;
            bt_resubir=false;
            bt_finalizar=false;
            bt_cancelar=false;
            //colorBack=rojo;
            arch.setVisibleBtIndicador1(false);
            arch.setVisibleBtIndicador2(false);
        }

        /*
        if(arch.getTipo_proceso().equalsIgnoreCase("DESCARGA")  )
        {
            visibleBt1=false;
            visibleBt2=false;
            
            bt_resubir=false;
            bt_finalizar=false;
            if(arch.getEstado()==2)
            {
                bt_resubir=true;
                visibleBt1=false;
                visibleBt2=false;
            }    
           bt_cancelar=true;
        }else{
             
            //PrimeFaces.current().executeScript("PF('wdlRespuesta2').show();");
        }*/
        
        //RequestContext context = RequestContext.getCurrentInstance();
        PrimeFaces.current().executeScript("PF('wdlProcesar').show();");
       
    }
    
    
    public void seleccionarEstructura(Requerimientos obj)
    {
        objetoEst=obj;
        objetoEst.setLstTabla(dao.obtenerEstructuraTabla(obj));
    }
    public void listenerComboTipo()
    {
        if(arch.getTipo_archivo()!=null)
        {
            if(arch.getTipo_archivo().indexOf(';')==-1)
            {
                visibleBt1=true;
                visibleBt2=false;
            }else{
                visibleBt1=true;
                visibleBt2=true;
            }
            
            arch.setArchivo3("");
            arch.setArchivo4("");
        }else{
            visibleBt1=false;
            visibleBt2=false;
        }
        
        
        arch.setVisibleBtIndicador1(false);
        arch.setVisibleBtIndicador2(false);
        
        
        
    }
    public void listenerComboTipo2()
    {
        if(arch.getTipo_archivo()!=null)
        {
            if(arch.getTipo_archivo().indexOf(';')==-1)
            {
                visibleBt1=true;
                visibleBt2=false;
            }else{
                visibleBt1=true;
                visibleBt2=true;
            }
            
        }else{
            visibleBt1=false;
            visibleBt2=false;
        }
  
    }

    /*  public void insertarArchivo() 
    {
        
         //revisa si es una insercion
       // if (operacion.equalsIgnoreCase("I")) {
            
           
            try {
                
                dao.insertarCasoPermiso(objetoSecundario);
                listarReq("x");
                //agrega objeto a la tabla requerimientos
                //lstReq.add(objeto);
            } catch (Exception e) {
                 System.out.println("Bean inser err "+e);
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
            }
     
    }*/
    public void verRequerimeintoPDF(String n) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        ////////System.out.println("cliente "+objeto.getD_asoc_negocio());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        //String rutaImg = servletContext.getRealPath("//jasper//images")+"/";

        Map parameter = new HashMap();
        parameter.put("id", objeto.getId());
        //parameter.put("nom_cliente", objeto.getC_asoc_negocio()+"  "+ objeto.getD_asoc_negocio());
        //parameter.put("logo", rutaImg);

        MasterPrintDAO print = new MasterPrintDAO();
        print.generarPDF(n, parameter, "");

    }

    public void verFormadoSQLPermisoPDF(String n) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        ////////System.out.println("cliente "+objeto.getD_asoc_negocio());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String rutaImg = servletContext.getRealPath("//resources//images") + "/";

        Map parameter = new HashMap();
        parameter.put("id", objetoSecundario.getId());
        //parameter.put("nom_cliente", objeto.getC_asoc_negocio()+"  "+ objeto.getD_asoc_negocio());
        parameter.put("logo", rutaImg);

        MasterPrintDAO print = new MasterPrintDAO();
        print.generarPDF(n, parameter, "");

    }
    
    public String encuentraNombre(File f,String ruta)
    {
        String nombre=f.getName();
             
        String soloNombre=nombre.substring(0, nombre.lastIndexOf('.'));
        String extension=nombre.substring(nombre.lastIndexOf('.'),nombre.length());
        
        String destino=ruta+f.getName();
        if(f.exists())
        {
            
            boolean existe=false;
            int con=1;
            do{
               nombre=soloNombre+"_BAK"+con+extension;
               
               //soloNombre=nombre.substring(0, nombre.lastIndexOf('.'));
               //extension=nombre.substring(nombre.lastIndexOf('.'),nombre.length());
               //nombre=soloNombre+extension;
               
               destino=ruta+nombre;
               File archivoExiste = new File(destino);
               if(archivoExiste.exists())
               {
                   existe=true;
               }else{
                   existe=false;
                   
               }
               con++; 
            }while(existe);
            

           
        }    
    
        
        
        return nombre;
    }
        
    public void handleFileUpload(FileUploadEvent event) throws FileNotFoundException, IOException {
        
        
        
        /*arch.file3=event.getFile();*/ /*Comentado ´por Kevin Anderson*/
         
         
        if(!arch.file3.getFileName().substring(0, arch.file3.getFileName().lastIndexOf('.')).equalsIgnoreCase(arch.getNombre_archivo()))
        {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El nombre del archivo es diferente"));
             mensajeDialogoError("Error", "Comprobar el nombre del archivo");
             return;
        }
        String extension=arch.file3.getFileName().substring(arch.file3.getFileName().lastIndexOf('.'),arch.file3.getFileName().length());
         
        String compatible[]=arch.getTipo_archivo().split(";");
        boolean validaExtension=false;
        for (int i = 0; i < compatible.length; i++) {
            if(extension.equalsIgnoreCase(compatible[i]))
               validaExtension=true; 
        }
        
        if(!validaExtension)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El tipo de archivo no es valido"));
             mensajeDialogoError("Error", "Comprobar el tipo de archivo ("+arch.getTipo_archivo()+")");
             return;
        }
        if(extension.equalsIgnoreCase(arch.getArchivo4()) )
        {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El archivo duplicado"));
             mensajeDialogoError("Error", "Comprobar si esta duplicado");
             return;
        }
         
         
        if (arch.file3 != null) {
         
            String raiz=Constantes.ruta_raiz_versiones;
            String folder=Constantes.folder_despues;
            String nombreTemporal=arch.file3.getFileName();
            String nombreFinal=arch.file3.getFileName();
            String destino=raiz+folder+nombreFinal;
            
            System.out.println(destino);
            
            

            //destino=raiz+folder+nombreTemporal;

            File archivoExiste = new File(destino);
            if (archivoExiste.exists()) {

                nombreTemporal=encuentraNombre(archivoExiste, raiz+folder );
                destino=raiz+folder+nombreTemporal;

                File archivoNuevoNombre = new File(destino);
                archivoExiste.renameTo(archivoNuevoNombre);
                
                destino=raiz+folder+nombreFinal;
            }
                
         
            
            OutputStream out = new FileOutputStream(new File(destino));
            
            InputStream in = arch.file3.getInputStream();
            int read=0;
            byte[] bytes = new byte[1024]; 
            
            while ( (read=in.read(bytes)) != -1  ) {                
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();
            arch.setRuta_raiz(raiz);
            arch.setRuta_folder(folder);
            arch.setArchivo3(extension);
            arch.setVisibleBtIndicador1(true);
            ////////////////////////////////////////////////////////////
            //String nombre_ext_Img[]=file.getFileName().split(".");
            /*
            MyFile mf=new MyFile();
            mf.setNombre(nombreFinal);
            mf.setRuta_raiz(raiz);
            mf.setRuta_folder(folder);
            mf.setFile(file);
            mf.setEstado(1);
            mf.setExiste(false);
            mf.setUsuario(c_usuario);
            mf.setD_usuario(d_usuario);
            lstDocument.add(mf);
            
            */
            /*FacesMessage msg = new FacesMessage("Compleado", event.getFile().getFileName() + " cargado.");
            FacesContext.getCurrentInstance().addMessage(null, msg);*/ /*Comentado ´por Kevin Anderson*/
            
            //RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlFileUpload').hide();");
             
        }

    }

    public void handleFileUpload2(FileUploadEvent event) throws FileNotFoundException, IOException {
        
        
        
         /*arch.file4=event.getFile();*/ /*Comentado ´por Kevin Anderson*/
         
         
        if(!arch.file4.getFileName().substring(0, arch.file4.getFileName().lastIndexOf('.')).equalsIgnoreCase(arch.getNombre_archivo()))
        {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El nombre del archivo es diferente"));
             mensajeDialogoError("Error", "Comprobar el nombre del archivo");
             return;
        }
        String extension=arch.file4.getFileName().substring(arch.file4.getFileName().lastIndexOf('.'),arch.file4.getFileName().length());
         
        String compatible[]=arch.getTipo_archivo().split(";");
        boolean validaExtension=false;
        for (int i = 0; i < compatible.length; i++) {
            if(extension.equalsIgnoreCase(compatible[i]))
               validaExtension=true; 
        }
        
        if(!validaExtension)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El tipo de archivo no es valido"));
             mensajeDialogoError("Error", "Comprobar el tipo de archivo ("+arch.getTipo_archivo()+")");
             return;
        }
        if(extension.equalsIgnoreCase(arch.getArchivo3()) )
        {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El archivo duplicado"));
             mensajeDialogoError("Error", "Comprobar si esta duplicado");
             return;
        }
         
         
         
         
        if (arch.file4 != null) {
         
            String raiz=Constantes.ruta_raiz_versiones;
            String folder=Constantes.folder_despues;
            String nombreTemporal=arch.file4.getFileName();
            String nombreFinal=arch.file4.getFileName();
            String destino=raiz+folder+nombreFinal;
            
            System.out.println(destino);
            
            File archivoExiste = new File(destino);
            if (archivoExiste.exists()) {

                nombreTemporal=encuentraNombre(archivoExiste, raiz+folder );
                destino=raiz+folder+nombreTemporal;

                File archivoNuevoNombre = new File(destino);
                archivoExiste.renameTo(archivoNuevoNombre);
                
                destino=raiz+folder+nombreFinal;
            }
                
         
            
            
            
            OutputStream out = new FileOutputStream(new File(destino));
            
            InputStream in = arch.file4.getInputStream();
            int read=0;
            byte[] bytes = new byte[1024]; 
            
            while ( (read=in.read(bytes)) != -1  ) {                
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();
        
            arch.setRuta_raiz(raiz);
            arch.setRuta_folder(folder);
            arch.setArchivo4(extension);
            arch.setVisibleBtIndicador2(true);
             
            ////////////////////////////////////////////////////////////
            //String nombre_ext_Img[]=file.getFileName().split(".");
            /*
            MyFile mf=new MyFile();
            mf.setNombre(nombreFinal);
            mf.setRuta_raiz(raiz);
            mf.setRuta_folder(folder);
            mf.setFile(file);
            mf.setEstado(1);
            mf.setExiste(false);
            mf.setUsuario(c_usuario);
            mf.setD_usuario(d_usuario);
            lstDocument.add(mf);
            
            */
            /*FacesMessage msg = new FacesMessage("Compleado", event.getFile().getFileName() + " cargado.");
            FacesContext.getCurrentInstance().addMessage(null, msg);*/ /*Comentado ´por Kevin Anderson*/

            //RequestContext context = RequestContext.getCurrentInstance();
            PrimeFaces.current().executeScript("PF('wdlFileUpload2').hide();");
             
        }

    }
    
    
    
    

    public void enviarCorreoPrueba() throws Exception {
        // SendMailEstilo mail=new SendMailEstilo();
        SendMailNormal mail = new SendMailNormal();
        mail.Enviar("rcardona@finsolhn.com", "Asunto Prueba", "Mensaje De Prueba");
    }

    private boolean isPosBack() {
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();
        return rpta;
    }

}
