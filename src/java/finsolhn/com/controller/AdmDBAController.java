/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.controller;

import finsolhn.com.dao.AdmDBADAO;
import finsolhn.com.dao.AdmDesarrolloDAO;
import finsolhn.com.dao.AdmUsuariosDAO;
import finsolhn.com.dao.MasterPrintDAO;
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
/*import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;*/
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
 * 
 */
@Named("admDBAController")
//@RequestScoped
//@ApplicationScoped
@ViewScoped
//@SessionScoped
//@RequestScoped 
public class AdmDBAController extends DataGeneralController {
    //variables y objetos
    AdmDBADAO dao = new AdmDBADAO();
   
    private Requerimientos objeto = new Requerimientos();
    private Requerimientos objetoSecundario = new Requerimientos();
    //private List<Requerimientos> lstReq;
    //private List<Requerimientos> lstCasos;
    private List<Requerimientos> lstAyudaSQL;
    private List<Requerimientos> lstAyudaPermiso;
    
    private List<Requerimientos> lstHistorial;

    private Date fechaR1;
    private Date fechaR2;
    private Date fechaC1;
    private Date fechaC2;
    private String fil_tipo="";
    private String fil_estado="";

    public List<Requerimientos> getLstHistorial() {
        return lstHistorial;
    }

    public void setLstHistorial(List<Requerimientos> lstHistorial) {
        this.lstHistorial = lstHistorial;
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

    public String getFil_tipo() {
        return fil_tipo;
    }

    public void setFil_tipo(String fil_tipo) {
        this.fil_tipo = fil_tipo;
    }

    public String getFil_estado() {
        return fil_estado;
    }

    public void setFil_estado(String fil_estado) {
        this.fil_estado = fil_estado;
    }
    

    
    
    public Requerimientos getObjeto() {
        return objeto;
    }

    public void setObjeto(Requerimientos objeto) {
        this.objeto = objeto;
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

    public List<Requerimientos> getLstAyudaSQL() {
        return lstAyudaSQL;
    }

    public void setLstAyudaSQL(List<Requerimientos> lstAyudaSQL) {
        this.lstAyudaSQL = lstAyudaSQL;
    }

    public List<Requerimientos> getLstAyudaPermiso() {
        return lstAyudaPermiso;
    }

    public void setLstAyudaPermiso(List<Requerimientos> lstAyudaPermiso) {
        this.lstAyudaPermiso = lstAyudaPermiso;
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
        
        try {

            objeto.setEstado(Constantes.estado_completado);
            dao.modificarReq_Caso(objeto);

        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
        }
        
    }
    public void aceptarCaso() {
         
        try {
            
            objeto.setEstado(Constantes.estado_en_desarrollo);
            objeto.setC_developer(getUser().getUsuario());
            dao.modificarCasoAceptar(objeto);

        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
        }
        
    }


    public void insertarCasoE() {
        
         //revisa si es una insercion
       // if (operacion.equalsIgnoreCase("I")) {
            
           
            try {
                if(objetoSecundario.getCant_reg_afec()<=0 )
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Cantidad de Registros Afectados no puede ser 0"));
                    return;
                }
                dao.insertarCasoEjecucion(objetoSecundario);
                
                //agrega objeto a la tabla requerimientos
                //lstReq.add(objeto);
            } catch (Exception e) {
                 System.out.println("Bean inser err "+e);
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
    public void insertarCasoP() 
    {
        
         //revisa si es una insercion
       // if (operacion.equalsIgnoreCase("I")) {
            
           
            try {
                
                dao.insertarCasoPermiso(objetoSecundario);
                
                //agrega objeto a la tabla requerimientos
                //lstReq.add(objeto);
            } catch (Exception e) {
                 System.out.println("Bean inser err "+e);
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
                   // lstReq = dao.listarMisRequerimientos(getUser().getUsuario());
                   // lstCasos = dao.listarCasos(getUser().getUsuario());
                    String where="and tipo_registro in('E','A','B')";
                    lstAyudaSQL = dao.listarAyudas(where);
                    where="and tipo_registro in('P')";
                    lstAyudaPermiso = dao.listarAyudas(where);
                }
            } else {
                //si es ejecutado desde un boton
                 String where="and tipo_registro in('E','A','B')";
                 lstAyudaSQL = dao.listarAyudas(where);
                 where="and tipo_registro in('P')";
                 lstAyudaPermiso = dao.listarAyudas(where);
                 ///////////////////////////////////////////////////
                 where="";
                 if(fil_tipo.equalsIgnoreCase("T") || fil_tipo.equalsIgnoreCase(""))
                 {
                     where =where+" and R.tipo_registro in('P','E','A','B') ";
                 }else{
                     where =where+" and R.tipo_registro ='"+fil_tipo+"' ";
                 }
                 if(fil_estado!=null && fil_estado.length()>0)
                 {
                    if(fil_estado.equalsIgnoreCase(Constantes.estado_completado_dba))     
                         where=where+" and R.estado in('"+fil_estado+"','"+Constantes.estado_finalizado+"') ";
                    else
                          where=where+" and R.estado like '%"+fil_estado+"%'";
                     
                         
                 }else{
                     where=where+" and R.estado in( '"+Constantes.estado_completado_dba
                             +"','"+Constantes.estado_autorizado_ti
                             +"','"+Constantes.estado_finalizado
                             +"','"+Constantes.estado_en_dba
                             +"','"+Constantes.estado_ini_permiso
                             +"','"+Constantes.estado_rechazado
                             +"','"+Constantes.estado_fin_permiso+"')";
                 }
                 
                 
                 if(fechaR1!=null && fechaR2!=null)
                {
                    where=where+" AND R.[fecha_autorizado] >= '"+sdf_ymd.format(fechaR1)+" 00:00' AND R.[fecha_autorizado] <= '"+sdf_ymd.format(fechaR2)+" 23:59'";
                }
                
            
                                
                if(fechaC1!=null && fechaC2!=null)
                {
                    where=where+" AND R.[fecha_inicio_permiso] >= '"+sdf_ymd.format(fechaC1)+" 00:00' AND R.[fecha_inicio_permiso] <= '"+sdf_ymd.format(fechaC2)+" 23:59'";
                }
                 
                 lstHistorial=dao.listarAyudasHistorial(where);
            }
      
        } catch (Exception e) {
            System.err.println("error: " + e);
        }
    }
    
   

    
    public AdmDBAController()  {
         if (getUser().getUsuario().isEmpty()) {
            super.verificarSession();
            System.err.println("****HOLA  Usuario:*"+getUser().getUsuario()+"* "+getUser().getNombre());  
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
        
        
        if (objetoSecundario.getEstado() != null) {
            
            if (objetoSecundario.getEstado().equalsIgnoreCase(Constantes.estado_en_dba) 
                  ) {
                return false;
            }
            
            

        }

        return true;
    }
    public boolean disable_bt_aceptar_c() {
        
        if (objeto.getEstado() != null) {
            if (objeto.getEstado().equalsIgnoreCase(Constantes.estado_en_espera) 
                  ) {
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
    
    public boolean disable_bt_iniciar_p() {
        
        
        if (objetoSecundario.getEstado() != null) {
            
            if (objetoSecundario.getEstado().equalsIgnoreCase(Constantes.estado_en_dba) 
                  ) {
                return false;
            }
            
            

        }

        return true;
    }
    public boolean disable_bt_finalizar_p() {
        
        
        if (objetoSecundario.getEstado() != null) {
            
            if (objetoSecundario.getEstado().equalsIgnoreCase(Constantes.estado_ini_permiso) 
                  ) {
                return false;
            }
            
            

        }

        return true;
    }
        
    public void nuevo()
    {
        objeto=new Requerimientos();
        objeto.setC_usuario(getUser().getUsuario());
        objeto.setD_usuario(getUser().getNombre());
        objeto.setC_agencia(getUser().getC_agencia());
        objeto.setD_agencia(getUser().getD_agencia());
        
        Date objDate = new Date(); 
        objeto.setFecha(sdf.format(objDate));
        operacion="I";
       
    }

    public void modificar(Requerimientos select)
    {
        objeto=select;
        operacion="U";
        objeto.setLstDocument(dao.listarDocumentos(objeto));
        
    }
    
    public void nueva_Ayuda(String nombre)
    {
        
        objetoSecundario=new Requerimientos();
        objetoSecundario.setId_req_padre(""+objeto.getId());
        objetoSecundario.setC_usuario(getUser().getUsuario());
        objetoSecundario.setD_usuario(getUser().getNombre());
        objetoSecundario.setC_agencia(getUser().getC_agencia());
        objetoSecundario.setD_agencia(getUser().getD_agencia());
        objetoSecundario.setNombre_corto(nombre);
        Date objDate = new Date(); 
        objetoSecundario.setFecha(sdf.format(objDate));
        operacion="I";
       if(nombre.equalsIgnoreCase("Permiso Sobre Objeto"))
       {
           objetoSecundario.setDescripcion("\n\n\n\nUSUARIO: ");
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
        }else if(objetoSecundario.getTipo_registro().equalsIgnoreCase("P")){
            PrimeFaces.current().executeScript("PF('wdlPermiso').show();");
        }else if(objetoSecundario.getTipo_registro().equalsIgnoreCase("A"))
        {
            objetoSecundario.setLstTabla(dao.obtenerEstructuraTabla(select));
            PrimeFaces.current().executeScript("PF('wdlCargaProce').show();");
        }
        else if(objetoSecundario.getTipo_registro().equalsIgnoreCase("B"))
        {
            PrimeFaces.current().executeScript("PF('wdlEnvia').show();");
        }
        aceptar_ayuda();
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
    
    public void aceptar_ayuda() {
        /*System.out.println(""+res);
        if(validaAprobacionJefe())
        {*/
        if(objetoSecundario.getEstado().equalsIgnoreCase(Constantes.estado_autorizado_ti))
        {
            objetoSecundario.setC_dba(getUser().getUsuario());
            objetoSecundario.setEstado(Constantes.estado_en_dba);
            dao.aceptar_ayuda(objetoSecundario);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No puede cambiar este estatus"));
        }
            
        /*}else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Este Perfil no puede Aprobar/Rechazar"));
        }*/
        

    }
    public void ver_origen_estructura()
    {
        objeto = dao.obtenerObjetoReq(""+objetoSecundario.getId_req_padre());
     
    }
        
    public void enviar_Script(String res) throws Exception
    {
        
        if(objetoSecundario.getScriptSQLAnterior().length()<5)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Pege el SCRIPT que decea enviar"));
            return;
        }
        objetoSecundario.setTipo_registro("U");
        objetoSecundario.setC_dba(getUser().getUsuario());
        objetoSecundario.setEstado(res);
        dao.completar_Envio(objetoSecundario);
        listarReq("x");
        
        String msj=Constantes.M11_mensaje.replace("<desa>", getUser().getNombre()).
                        replace("<numero>", objetoSecundario.getId()+"").
                        replace("<nombre_req>", objetoSecundario.getNombre_corto()+" - "+objeto.getNombre_corto());
           
            
        enviarCorreoDirecto(objetoSecundario.getC_usuario(), Constantes.A1_asunto, msj);
   
    }
        
    public void completar_ayuda(String res) throws Exception {
        /*System.out.println(""+res);
        if(validaAprobacionJefe())
        {*/
        if(objetoSecundario.getEstado().equalsIgnoreCase(Constantes.estado_en_dba))
        {
            objetoSecundario.setC_dba(getUser().getUsuario());
            objetoSecundario.setEstado(res);
            dao.completar_ayuda(objetoSecundario);
            listarReq("x");
            
           String msj="";
           if(res.equalsIgnoreCase(Constantes.estado_rechazado))
           {
                msj=Constantes.M3_mensaje+"\nRESPUESTA:"+getUser().getNombre();
           }else if(res.equalsIgnoreCase(Constantes.estado_completado_dba)){
               
                msj=Constantes.M7_mensaje.replace("<desa>", getUser().getNombre()).
                        replace("<numero>", objetoSecundario.getId()+"").
                        replace("<nombre_req>", objetoSecundario.getNombre_corto()+" - "+objeto.getNombre_corto());
           }else if(res.equalsIgnoreCase(Constantes.estado_ini_permiso))
           {
                msj=Constantes.M9_mensaje.replace("<desa>", getUser().getNombre()).
                        replace("<numero>", objetoSecundario.getId()+"").
                        replace("<nombre_req>", objetoSecundario.getNombre_corto()+" - "+objeto.getNombre_corto());
           }
            
            enviarCorreoDirecto(objetoSecundario.getC_usuario(), Constantes.A1_asunto, msj);
            
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No puede cambiar este estatus"));
        }
            
        /*}else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Este Perfil no puede Aprobar/Rechazar"));
        }*/
        

    }
    
    
    
            
    public void completar_ayuda_permiso(String res) throws Exception {
        /*System.out.println(""+res);
        if(validaAprobacionJefe())
        {*/
        if(objetoSecundario.getEstado().equalsIgnoreCase(Constantes.estado_ini_permiso))
        {
            objetoSecundario.setC_dba(getUser().getUsuario());
            objetoSecundario.setEstado(res);
            Date objDate = new Date(); 
            objetoSecundario.setFecha_fin_permiso(sdf.format(objDate));
            dao.completar_ayuda_permiso(objetoSecundario);
            listarReq("x");
            
            
            String msj=Constantes.M10_mensaje.replace("<desa>", getUser().getNombre()).
                        replace("<numero>", objetoSecundario.getId()+"").
                        replace("<nombre_req>", objetoSecundario.getNombre_corto()+" - "+objeto.getNombre_corto());
           
            
            enviarCorreoDirecto(objetoSecundario.getC_usuario(), Constantes.A1_asunto, msj);
            
            
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No puede cambiar este estatus"));
        }
            
        /*}else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Este Perfil no puede Aprobar/Rechazar"));
        }*/
        

    }
    
    ////////VER ARCHIVO EXTERNO @RequestScoped 
    public StreamedContent mostrarImagen(String nombreArchivo) throws IOException {
        
        
        
        ByteArrayOutputStream out = null;
        /* deprecate buscar remplazo
        if (nombreArchivo == null) {
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
