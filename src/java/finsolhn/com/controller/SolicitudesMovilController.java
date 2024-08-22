/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.controller;


import finsolhn.com.dao.ActividadesMovilDAO2;
import finsolhn.com.dao.ActividadesMovilDAO3;
import finsolhn.com.dao.MasterPrintDAO;
import finsolhn.com.data.AdAgencias;
import finsolhn.com.data.AdEquifax;
import finsolhn.com.data.AdSolicitudes;

import finsolhn.com.data.AdUsuarios;
import finsolhn.com.data.AdVSolicitudes;
import finsolhn.com.ejb.AdEquifaxFacade;
import finsolhn.com.ejb.AdEquifaxFacadeLocal;
import finsolhn.com.ejb.AdSolicitudesFacadeLocal;
import finsolhn.com.ejb.AdUsuariosFacadeLocal;
import finsolhn.com.ejb.AdUsuariosxagenciaFacadeLocal;
import finsolhn.com.ejb.AdVSolicitudesFacadeLocal;
import finsolhn.com.util.Constantes;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Collections;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author rcardona
 *
 */
@Named("SolicitudesMovilController")
//@RequestScoped
//@ApplicationScoped
@ViewScoped
//@SessionScoped
//@RequestScoped 
public class SolicitudesMovilController extends DataGeneralController {

    //variables y objetos
    ActividadesMovilDAO3 dao = new ActividadesMovilDAO3();
    ActividadesMovilDAO2 dao2 = new ActividadesMovilDAO2();

    @EJB
    AdUsuariosFacadeLocal usuarioEJB;
    @EJB
    AdVSolicitudesFacadeLocal solicitudesEJB;
    @EJB
    AdUsuariosxagenciaFacadeLocal usuarioXagenciaEJB;
    @EJB
    AdSolicitudesFacadeLocal solEJB;
    @EJB
    AdEquifaxFacadeLocal equifaxEJB;

    private AdVSolicitudes objeto = new AdVSolicitudes();
    private AdSolicitudes objSol = new AdSolicitudes();
    private List<AdVSolicitudes> lstSolicitudes;
    
    private List<AdUsuarios> lstAsesores;
    private List<AdAgencias> lstAgencias;

    //metodo sirve para insertar o reprogramar una visita
    String operacion = "";
    private String texto = "";
    private boolean visibleMotivos=false;
    private boolean disableBtnAutDevRech=false;
    private boolean disableBtnDevolver=false;
    
    private String rutaPdfEquifax="..\\resources\\pdfnofound.pdf";
    
    private String filtroAgencia;
    private String filtroAsesor;
    private String filtroNombre;
    private String filtroEstado;
    private String filtroRechazo="";
    private Date filtroFechaR1;
    private Date filtroFechaR2;
   
    //listar usuarios
    public void listarActi(String v) {

        String filtroAgenciaX = "";
        String filtroAsesorX = "";
        String filtroEstadoX = "";
        String filtroRechazoX = "";

        String where = " ";//" and s.sucursal='"+getUser().getC_agencia()+"'";
        if (filtroAgencia != null && filtroAgencia.trim().equalsIgnoreCase("0") == false && filtroAgencia.trim().equalsIgnoreCase("100") == false) {
            ///where = where + " AND (s.idagencia='" + filtroAgencia + "' OR (MV.IDCLIENTE=0 and (SELECT agenciaid FROM ad_usuarios WHERE usuariotopaz=MV.usragenda)='" + filtroAgencia + "' ) )  ";
            filtroAgenciaX = ""+filtroAgencia+"";
        } else {
            //(Si selecciona todos en agencia)compara si el puesto del usuario es gerente negocios(11)/jefe distrito(60)
            // if (getUser().getC_puesto().equalsIgnoreCase("60") || getUser().getC_puesto().equalsIgnoreCase("11")) {

            ///where = where + " And  (s.idagencia in(" + ids.substring(0, ids.length() - 1) + ")  OR  MV.IDCLIENTE=0) ";
            //}
            String ids = "   ";
            for (AdAgencias lstAgenciasDe : lstAgencias) {
                ids = ids + "" + lstAgenciasDe.getAgenciaid() + ",";
            }
            filtroAgenciaX = ids.substring(0, ids.length() - 1);
        }
        try {
             
                //String where="";
                ///////Filtro Asesor
                if (filtroAsesor != null && filtroAsesor.equalsIgnoreCase("0") == false) {
                    ///where = where + " AND MV.usragenda='" + filtroAsesor + "'";
                    filtroAsesorX = "" + filtroAsesor + "";
                } else {
                    String ids = " ";
                    for (AdUsuarios lstAsesor : lstAsesores) {
                        ids = ids + "" + lstAsesor.getUsuariotopaz() + ",";
                    }
                    filtroAsesorX = ids.substring(0, ids.length() - 1);
                }
                //Filtro Tipo Visita
                if (filtroEstado != null && filtroEstado.isEmpty() == false) {
                    ///where = where + " AND MV.tipovisita='" + filtroEstado + "'";
                    filtroEstadoX = "" + filtroEstado + "";
                }
                ///Filtro Tipo de Resultado en la visita
                if (filtroRechazo != null && filtroRechazo.isEmpty() == false) {
                    ///where = where + " AND MV.resultadovisita='" + filtroRechazo + "'";
                        filtroRechazoX = "" + filtroRechazo + "";
                }
                //System.out.println("TIPO:"+getUser().getTipousuario());
                if(getUser().getTipousuario().equalsIgnoreCase("6"))
                    filtroEstadoX=Constantes.ESTADO_SOL_FINALIZADA;
                System.out.println("AG:"+filtroAgenciaX);    
               lstSolicitudes=solicitudesEJB.findWithParam(
                       filtroAgenciaX
                       ,filtroAsesorX
                       ,filtroEstadoX
                       ,filtroNombre
                       ,filtroRechazoX
                       ,filtroFechaR1
                       ,filtroFechaR2);
            

        } catch (Exception e) {
            System.err.println("error: " + e);
        }
       

       

    }

    
     public void seleccionar(AdVSolicitudes obj, String operacion) {
    
        this.objeto = obj;//solicitudesEJB.find(obj.getLiteuidsol()) ;
        this.objSol=solEJB.find(objeto.getLiteuidsol());
        // this.objetoDir=obtenerDirByCL(objeto.getIdCliente());
        this.operacion = operacion;
        disableAprobacion();
        
        //RequestContext context = RequestContext.getCurrentInstance();
        //context.execute("PF('wdlActividades').show();");
        PrimeFaces.current().executeScript("PF('wdlSolicitudes').show();");
        
    }
    
    private void disableAprobacion()
    {
        //System.out.println("TipoUser:"+getUser().getTipousuario()+" "+getUser().getC_puesto());
        System.out.println("Estado:"+objeto.getEstadosolicitud());
        if(objeto.getEstadosolicitud().equalsIgnoreCase(Constantes.ESTADO_SOL_COMPLETADA))
        {
            disableBtnAutDevRech=false;
        }else{
            disableBtnAutDevRech=true;
        }
                
        if( disableBtnAutDevRech==false 
                && getUser().getC_puesto().equalsIgnoreCase("63") && getUser().getTipousuario().equalsIgnoreCase("3"))
        {
            disableBtnAutDevRech=false;
        }else{
            disableBtnAutDevRech=true;
        }
        ///Boton Devolver
        if( disableBtnAutDevRech==false 
                && (objeto.getComentariojefe()==null || objeto.getComentariojefe().isEmpty()))
        {
             disableBtnDevolver=false;
        }else{
            
            disableBtnDevolver=true;
            if(disableBtnAutDevRech==false)
                objeto.setComentariojefe("");
        }
        
        
        
    }
    
    /*
    private MapModel simpleModel;
    private Marker marker;
    private double latIni = 14.4041703;
    private double lonIni = -86.7056069;
    private int zoom = 7;
    */
  

    public SolicitudesMovilController() {
        if (getUser().getUsuario().isEmpty()) {
            super.verificarSession();
            System.out.println("****"+getClass().getSimpleName()+" Usuario:*"+ getUser().getUsuario() + "* " + getUser().getNombre());
        }

    }

    @PostConstruct
    public void ini() {
      
        
        try {
            lstAgencias = usuarioXagenciaEJB.findAgenciasByUsuario(getUser().getUsuario(), getUser());//usuarioEJB.find(getUser().getUsuario()).getAdUsuariosxagenciaList();
        } catch (Exception e) {
            System.err.println("Error listar agencias " + e);
        }

        filtroAgencia = (lstAgencias.size() > 0) ? Short.toString(lstAgencias.get(0).getAgenciaid()) : "0";
        listarAsesor();
        filtroAsesor = (lstAsesores.size() > 0) ? lstAsesores.get(0).getUsuariotopaz() : "0";
       
        

    }

    public void nuevo() {
        objeto = new AdVSolicitudes();
        //

        operacion = "I";

    }

  
    public void onClickllenarFiltroEstado() {
       
        if(filtroEstado.equalsIgnoreCase(Constantes.ESTADO_SOL_RECHAZADA))
        {
          visibleMotivos=true;      
        }else{
          visibleMotivos=false;      
        }
        //PrimeFaces.current().ajax().update("rechazo");
    }

    public void listarAsesor() {

        /*if(getUser().getC_puesto().equalsIgnoreCase("81") || getUser().getC_puesto().equalsIgnoreCase("16"))
        {
            filtroAsesor=getUser().getUsuario();
        }else{
            
        }*/
        //lstAsesores = dao2.listarAsesores(where, getUser());
        String filtroAgenciaX = "";
        String filtroAgenciaXZona = "";
        if (filtroAgencia != null && (filtroAgencia.trim().equalsIgnoreCase("0") == false && filtroAgencia.trim().equalsIgnoreCase("100") == false /*|| filtroAgencia.trim().equalsIgnoreCase("101") == false || filtroAgencia.trim().equalsIgnoreCase("102") == false*/ ) ) {
            ///where = where + " AND (s.idagencia='" + filtroAgencia + "' OR (MV.IDCLIENTE=0 and (SELECT agenciaid FROM ad_usuarios WHERE usuariotopaz=MV.usragenda)='" + filtroAgencia + "' ) )  ";
            filtroAgenciaX = filtroAgencia;
            filtroAgenciaXZona = (filtroAgencia.equalsIgnoreCase("101")?"'NORTE'":(filtroAgencia.equalsIgnoreCase("102")?"'CENTRO-SUR'":"'NORTE','CENTRO-SUR'"));
        } else {
            
            
            //if(filtroAgenciaX.equalsIgnoreCase(""))
            
            //(Si selecciona todos en agencia)compara si el puesto del usuario es gerente negocios(11)/jefe distrito(60)
            // if (getUser().getC_puesto().equalsIgnoreCase("60") || getUser().getC_puesto().equalsIgnoreCase("11")) {
            String ids = "   "; String idsZona = "   ";
            for (AdAgencias lstAgenciasDe : lstAgencias) {
                ids = ids + " " + lstAgenciasDe.getAgenciaid() + ",";
                idsZona=idsZona + " '" + lstAgenciasDe.getZona() + "',";
            }
            filtroAgenciaX = ids.substring(0, ids.length() - 1);
            filtroAgenciaXZona = idsZona.substring(0, idsZona.length() - 1);
            ///where = where + " And  (s.idagencia in(" + ids.substring(0, ids.length() - 1) + ")  OR  MV.IDCLIENTE=0) ";
            //}
        }
        String where ="";    
        where = " and a.agenciaid in(" + filtroAgenciaX + ") and a.tipousuario<>2 ";
        if(filtroAgencia.equalsIgnoreCase("100") || filtroAgencia.equalsIgnoreCase("101") || filtroAgencia.equalsIgnoreCase("102"))
        {
            if(getUser().getTipousuario().equalsIgnoreCase("2") || getUser().getTipousuario().equalsIgnoreCase("5") 
                    || getUser().getC_puesto().equalsIgnoreCase("53") || getUser().getC_puesto().equalsIgnoreCase("20") 
                    || getUser().getC_puesto().equalsIgnoreCase("38"))
            {
                //if(filtroAgenciaXZona.equalsIgnoreCase("100") || filtroAgenciaXZona.equalsIgnoreCase("0"))
                //    filtroAgenciaXZona="'NORTE','CENTRO-SUR'";
                //if(filtroAgenciaX.equalsIgnoreCase("100"))
                    where = " and a.tipousuario='2' and a.agenciaid in(select b.agenciaid from AdAgencias b where b.zona in("+filtroAgenciaXZona+") )";
                
               // where = " and codigogestor!='' and a.zona in(" + filtroAgenciaXZona + ") a.puestoid in(91,31)";
            }
          
        }else{
            
        }
        
        

        lstAsesores = usuarioEJB.listarUsuariosAsesoresByAgencia(where, getUser());
    }

    public void resultadoJefe(String res)
    {
        if(objeto.getComentariojefe()==null || objeto.getComentariojefe().trim().isEmpty())
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR", "Ingrese un comentario sobre la Solicitud\n"+res));
        }else{
            objSol.setEstadosolicitud(res);
            objSol.setComentariojefe(objeto.getComentariojefe());
            objSol.setEstadoCrud("MO");
            solEJB.edit(objSol);
            objeto.setEstadosolicitud("**"+objSol.getEstadosolicitud()+"**");
            Collections.replaceAll(lstSolicitudes, objeto, objeto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Actualizado Correctamente"));
            //PrimeFaces.current().ajax().update("msj");
            PrimeFaces.current().executeScript("PF('wdlSolicitudes').hide();");
        }
        
    }
    
    public AdVSolicitudes getObjeto() {
        return objeto;
    }

    public void setObjeto(AdVSolicitudes objeto) {
        this.objeto = objeto;
    }

    public List<AdVSolicitudes> getLstSolicitudes() {
        return lstSolicitudes;
    }

    public void setLstSolicitudes(List<AdVSolicitudes> lstSolicitudes) {
        this.lstSolicitudes = lstSolicitudes;
    }

    public String getRutaPdfEquifax() {
        return rutaPdfEquifax;
    }

    public void setRutaPdfEquifax(String rutaPdfEquifax) {
        this.rutaPdfEquifax = rutaPdfEquifax;
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

    public String getFiltroNombre() {
        return filtroNombre;
    }

    public void setFiltroNombre(String filtroNombre) {
        this.filtroNombre = filtroNombre;
    }

    public String getFiltroEstado() {
        return filtroEstado;
    }

    public void setFiltroEstado(String filtroEstado) {
        this.filtroEstado = filtroEstado;
    }

    public String getFiltroRechazo() {
        return filtroRechazo;
    }

    public void setFiltroRechazo(String filtroRechazo) {
        this.filtroRechazo = filtroRechazo;
    }

    public List<AdUsuarios> getLstAsesores() {
        return lstAsesores;
    }

    public void setLstAsesores(List<AdUsuarios> lstAsesores) {
        this.lstAsesores = lstAsesores;
    }

    public String getFiltroAsesor() {
        return filtroAsesor;
    }

    public void setFiltroAsesor(String filtroAsesor) {
        this.filtroAsesor = filtroAsesor;
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

    
    public List<AdAgencias> getLstAgencias() {
        return lstAgencias;
    }

    public void setLstAgencias(List<AdAgencias> lstAgencias) {
        this.lstAgencias = lstAgencias;
    }

    public String getFiltroAgencia() {
        return filtroAgencia;
    }

    public void setFiltroAgencia(String filtroAgencia) {
        this.filtroAgencia = filtroAgencia;
    }

    public boolean isVisibleMotivos() {
        return visibleMotivos;
    }

    public void setVisibleMotivos(boolean visibleMotivos) {
        this.visibleMotivos = visibleMotivos;
    }

    public boolean isDisableBtnAutDevRech() {
        return disableBtnAutDevRech;
    }

    public void setDisableBtnAutDevRech(boolean disableBtnAutDevRech) {
        this.disableBtnAutDevRech = disableBtnAutDevRech;
    }

    public boolean isDisableBtnDevolver() {
        return disableBtnDevolver;
    }

    public void setDisableBtnDevolver(boolean disableBtnDevolver) {
        this.disableBtnDevolver = disableBtnDevolver;
    }

  
    
       
    
    public void verDocumentoPDF(String id,String nombrereporte) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
            
        String nombreRep=nombrereporte;
        Map parameter = new HashMap();
        parameter.put("liteuid", id);
        parameter.put("pathfirma", Constantes.ruta_firmas);
        MasterPrintDAO print = new MasterPrintDAO();
        print.generarPDF(nombreRep, parameter, "");
    }
    
    
    public void generarPDFEquifax(String identidad)
    {
        rutaPdfEquifax="../resources/pdfnofound.pdf";
        System.out.println(""+identidad+" "+objeto.getIdentidad());
        AdEquifax equifax=equifaxEJB.findByIdentidad(objeto.getIdentidad());
        
        if(equifax!=null)
        {
            String pdfName = getUser().getUsuario()+"_Equifax.pdf";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String mPath = servletContext.getRealPath(Constantes.ruta_jasper);            
           
            //System.out.println("Ruta:"+mPath+"\\"+pdfName);
            File file = new File(mPath+"\\"+pdfName);
            
            try(FileOutputStream fos = new FileOutputStream(file);) {
                    byte[] decoder = Base64.getDecoder().decode(equifax.getPdffile());
                    fos.write(decoder);
                    rutaPdfEquifax="..\\jasper-agenda\\"+pdfName;
                }catch (Exception e){
                    System.out.println("Error generarr Equiax "+e);
             }
                 // rutaPdfEquifax="..\\jasper-agenda\\"+pdfName;
        }else{
            System.out.println("Identidad Null Equiax");
        }
               
        
        

    }
        
   
    private boolean isPosBack() {
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();
        return rpta;
    }

        
    
}
