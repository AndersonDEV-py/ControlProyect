package finsolhn.com.controller;

import finsolhn.com.dao.ActividadesMovilDAO2;
import finsolhn.com.dao.ActividadesMovilDAO3;
import finsolhn.com.dao.MasterPrintDAO;
import finsolhn.com.data.AdAgencias;
import finsolhn.com.data.AdDirecciones;
import finsolhn.com.data.AdUsuarios;
import finsolhn.com.data.AdVVisitas;
import finsolhn.com.ejb.AdClientesFacadeLocal;
import finsolhn.com.ejb.AdDireccionesFacadeLocal;
import finsolhn.com.ejb.AdPrestamosFacadeLocal;
import finsolhn.com.ejb.AdUsuariosFacadeLocal;
import finsolhn.com.ejb.AdUsuariosxagenciaFacadeLocal;
import finsolhn.com.ejb.AdVVisitasFacadeLocal;
import finsolhn.com.ejb.AdVisitasFacadeLocal;
import finsolhn.com.model.ActividadesMoviles;
import finsolhn.com.model.ResultadosVisitas;
import finsolhn.com.model.TiposVisita;
import finsolhn.com.util.Constantes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
/*import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;*/
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.axes.radial.RadialScales;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearAngleLines;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearPointLabels;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.bubble.BubbleChartDataSet;
import org.primefaces.model.charts.bubble.BubbleChartModel;
import org.primefaces.model.charts.data.BubblePoint;
import org.primefaces.model.charts.data.NumericPoint;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.elements.Elements;
import org.primefaces.model.charts.optionconfig.elements.ElementsLine;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.optionconfig.tooltip.Tooltip;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.primefaces.model.charts.radar.RadarChartDataSet;
import org.primefaces.model.charts.radar.RadarChartModel;
import org.primefaces.model.charts.radar.RadarChartOptions;
import org.primefaces.model.charts.scatter.ScatterChartModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

//Autor: Ingeniero Kevin A. Santos


@Named("actividadesMovilController")
@ViewScoped

public class ActividadesMovilController extends DataGeneralController {

    //Variables y objetos
    ActividadesMovilDAO3 dao = new ActividadesMovilDAO3();
    ActividadesMovilDAO2 dao2 = new ActividadesMovilDAO2();

    @EJB
    AdUsuariosFacadeLocal usuarioEJB;
    @EJB
    AdVVisitasFacadeLocal visitasEJB;
    //AdVisitasFacadeLocal visitasEJB;
    @EJB
    AdDireccionesFacadeLocal direccionesEJB;
    @EJB
    AdUsuariosxagenciaFacadeLocal usuarioXagenciaEJB;
    @EJB
    AdPrestamosFacadeLocal prestamosEJB;
    @EJB
    AdClientesFacadeLocal clientesEJB;

    private AdVVisitas objeto = new AdVVisitas();
    private List<AdVVisitas> lstActividades;
    private List<ActividadesMoviles> lstSinActividades;

    private List<AdDirecciones> lstDirecciones = new ArrayList();
    private List<TiposVisita> lstFiltroTipos = new ArrayList();
    private List<ResultadosVisitas> lstResutados = new ArrayList();
    private List<ResultadosVisitas> lstFiltrpResutados = new ArrayList();
    private List<AdUsuarios> lstAsesores;
    private List<AdUsuarios> lstAsesores2;
    private List<AdAgencias> lstAgencias;

    String operacion = "";
    private String texto = "";
    private String textoGraficos = "";
    
    private String filtroAgencia;
    private String filtroAsesor;
    private String filtroNombre;
    private String filtroTipoV;
    private String filtroResultadoV="SIN_PROCESAR";
    private Date filtroFechaR1;
    private Date filtroFechaR2;
    private Date filtroFechaC1;
    private Date filtroFechaC2;

    /////tab2
    private String filtroAgencia2;
    private String filtroAsesor2;
    private String filtroNombre2;
    private Date filtroFecha_t2;
    private boolean checkOmitir = true;

    //Listar usuarios
    public void listarActi(String v) {

        String filtroAgenciaX = "";
        String filtroAsesorX = "";
        String filtroTipoVX = "";
        String filtroResultadoVX = "";
        
        //Gestionar filtro por agencias
        String where = " ";//" and s.sucursal='"+getUser().getC_agencia()+"'";
        if (filtroAgencia != null && filtroAgencia.trim().equalsIgnoreCase("0") == false && filtroAgencia.trim().equalsIgnoreCase("100") == false) {
            filtroAgenciaX = filtroAgencia;
        } else {
          
            String ids = "   ";
            for (AdAgencias lstAgenciasDe : lstAgencias) {
                ids = ids + "" + lstAgenciasDe.getAgenciaid() + ",";
            }
            filtroAgenciaX = ids.substring(0, ids.length() - 1);
        }
        try {
            if (v.equalsIgnoreCase("f")) {
                PrimeFaces.current().executeScript("PF('statusDialog').show();");
           
                filtroResultadoV = "SIN_PROCESAR";
                where = "" + " AND a.fecharegistro>=:fecharegistro ";
                if (filtroAsesor != null && filtroAsesor.equalsIgnoreCase("0") == false) {
                    filtroAsesorX = "'" + filtroAsesor + "'";
                }

                lstActividades = visitasEJB.listarVisitas(
                        v,
                        filtroAgenciaX,
                        filtroAsesorX,
                        filtroNombre,
                        filtroTipoV,
                        filtroResultadoV,
                        filtroFechaR1,
                        filtroFechaR2,
                        filtroFechaC1,
                        filtroFechaC2);

                 PrimeFaces.current().executeScript("PF('statusDialog').hide();");
                 System.out.println("Pagina OK");
            } else {
                //Filtro asesor
                if (filtroAsesor != null && filtroAsesor.equalsIgnoreCase("0") == false) {
                    filtroAsesorX =  filtroAsesor ;
                } else {
                    String ids = " ";
                    for (AdUsuarios lstAsesor : lstAsesores) {
                        ids = ids + "" + lstAsesor.getUsuariotopaz() + ",";
                    }
                    filtroAsesorX = ids.substring(0, ids.length() - 1);
                }
                //Filtro Tipo Visita
                if (filtroTipoV != null && filtroTipoV.isEmpty() == false) {
                    filtroTipoVX =  filtroTipoV ;
                }
                ///Filtro Tipo de Resultado en la visita
                if (filtroResultadoV != null && filtroResultadoV.isEmpty() == false) {
                    
                    
                    if (filtroResultadoV.equalsIgnoreCase("SIN_PROCESAR")) {
                        filtroResultadoVX = "";
                    } else {
                        filtroResultadoVX = filtroResultadoV ;
                    }
                }
                System.out.println("AG:"+filtroAgenciaX);
                System.out.println("AS:"+filtroAsesorX);
                //Mostrar visitas acorde a los filtros
                lstActividades = visitasEJB.listarVisitas(
                        v,
                        filtroAgenciaX,
                        filtroAsesorX,
                        filtroNombre,
                        filtroTipoVX,
                        filtroResultadoVX,
                        filtroFechaR1,
                        filtroFechaR2,
                        filtroFechaC1,
                        filtroFechaC2);
                
                System.out.println("CANT R:"+lstActividades.size());
            }

        } catch (Exception e) {
            System.err.println("error: " + e);
        }
        
        if (lstActividades != null) {
            /* if (lstActividades.size()==15000){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "AVISO", "Limitado a 15,000 Registros"));
             }
                     
                     
            if (lstActividades.size() > 0) {
                lstDirecciones = direccionesEJB.listarDireccionesByIdClientes(codigosClientes(lstActividades));
            }
            */
            createDonutModel();
            createBarModel();
        }

        try {
            /*for (int i = 0; i < lstActividades.size(); i++) {

                lstActividades.get(i).setObjetoDir(obtenerDirByCL(lstActividades.get(i)));
            }*/
        } catch (Exception e) {
            System.err.println("ActividadesController Direcciones no se cargaron");
        }

    }

    //Listado de codigos de los clientes
    private List<String> codigosClientes(List<ActividadesMoviles> lista) {

        List<String> lstidclientes=new ArrayList();

        for (ActividadesMoviles lst : lista) {
            lstidclientes.add(lst.getIdCliente());
        }
        return lstidclientes;
    }

    //Obtener direccion
    private AdDirecciones obtenerDirByCL(ActividadesMoviles act) {
        AdDirecciones d = new AdDirecciones();
        if (act.getIdCliente().equalsIgnoreCase("0")) {

            d.setReferenciadir(act.getDireccioNnuevo());
            d.setTelefonomovil(act.getTelefonoNuevo());
            d.setBarrio(act.getDireccioNnuevo());
        } else {
            int indiceCasa = 0;
            for (int i = 0; i < lstDirecciones.size(); i++) {

                if (Long.toString(lstDirecciones.get(i).getAdDireccionesPK().getIdcliente()).equalsIgnoreCase(act.getIdCliente().trim())
                        && lstDirecciones.get(i).getAdDireccionesPK().getFormato().equalsIgnoreCase("Negocio")) {
                    d = lstDirecciones.get(i);
                   return d;
                }
                if (Long.toString(lstDirecciones.get(i).getAdDireccionesPK().getIdcliente()).equalsIgnoreCase(act.getIdCliente().trim())
                        && lstDirecciones.get(i).getAdDireccionesPK().getFormato().equalsIgnoreCase("Personal")) {
                    indiceCasa = i;
                }
            }
            if (d.getIddepartamento() == null) {
                d = lstDirecciones.get(indiceCasa); 
            }

        }

        return d;
    }

    //Establecer el tipo de operación a realizar
    public void seleccionar(AdVVisitas obj, String operacion) {

        System.out.println("SELEC:"+obj.getNombre());
        this.objeto = obj;
        this.operacion = operacion;
        if (operacion.equalsIgnoreCase("I")) {
            texto = "AGREGAR";

        } else {
            texto = "MODIFICAR";

        }

        PrimeFaces.current().executeScript("PF('wdlActividades').show();");
        
    }

    //Variables base del mapa
    private MapModel simpleModel;
    private Marker marker;
    private double latIni = 14.4041703;
    private double lonIni = -86.7056069;
    private int zoom = 7;

    //Ver en el mapa donde se realizara las visitas
    public void verUbicacionesVisitas() {
        /*
        zoom = 7;
        latIni = 14.4041703;
        lonIni = -86.7056069;
        simpleModel = new DefaultMapModel();
        for (int i = 0; i < lstActividades.size(); i++) {
             if(i==30) break;
             
             if(i==0){
                 latIni=Double.parseDouble(lstActividades.get(i).getLatVisita());
                 lonIni= Double.parseDouble(lstActividades.get(i).getLonVisita());
                 zoom=14;
             }
             
            if(lstActividades.get(i).getResultadoVisita()!=null)
            if(lstActividades.get(i).getResultadoVisita().isEmpty())
            {
                
            }else{
                LatLng coord1 = new LatLng( Double.parseDouble(lstActividades.get(i).getLatVisita()), Double.parseDouble(lstActividades.get(i).getLonVisita()));
                
                String nombre=(lstActividades.get(i).getNombre().isEmpty()?lstActividades.get(i).getNombreNuevo():lstActividades.get(i).getNombre());
                marker=new Marker(coord1, nombre);

                simpleModel.addOverlay(marker);
            }

        }*/

    }

    //Crear marcador de posición
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccionado", marker.getTitle()));
    }

    //Mostrar que usuario esta en sesión
    public ActividadesMovilController() {
        if (getUser().getUsuario().isEmpty()) {
            super.verificarSession();
            System.out.println("****"+getClass().getSimpleName()+" Usuario:*"+ getUser().getUsuario() + "* " + getUser().getNombre());
        }

    }

    //Iniciar la pantalla con todos los datos básicos necesarios
    @PostConstruct
    public void ini() {
    
        try {
            lstAgencias = usuarioXagenciaEJB.findAgenciasByUsuarioGestor(getUser().getUsuario(), getUser());//usuarioEJB.find(getUser().getUsuario()).getAdUsuariosxagenciaList();
        } catch (Exception e) {
            System.err.println("Error listar agencias " + e);
        }

        filtroAgencia = (lstAgencias.size() > 0) ? Short.toString(lstAgencias.get(0).getAgenciaid()) : "0";
        filtroAgencia2 = (lstAgencias.size() > 0) ? Short.toString(lstAgencias.get(0).getAgenciaid()) : "0";
        listarAsesor();
        filtroAsesor = (lstAsesores.size() > 0) ? lstAsesores.get(0).getUsuariotopaz() : "0";
        listarAsesor2();
        filtroAsesor2 = (lstAsesores2.size() > 0) ? lstAsesores2.get(0).getUsuariotopaz() : "0";
        llenarResultados();
        
       createDonutModel();
       createBarModel();
      // createPieModel();
      
        Calendar c = Calendar.getInstance();
        Date now = c.getTime();
        c.add(Calendar.DATE, -60);
        Date nowMinus60 = c.getTime();
        filtroFecha_t2 = nowMinus60;
    }

    
    public void nuevo() {
        objeto = new AdVVisitas();
        operacion = "I";

    }

    //Obtener resultados del filtro
    public void onClickllenarFiltroResultados() {
        lstFiltrpResutados.clear();
        for (int i = 0; i < lstResutados.size(); i++) {
            if (lstResutados.get(i).getTipoVisita().equalsIgnoreCase(filtroTipoV)) {
                lstFiltrpResutados.add(lstResutados.get(i));
            }
        }

    }

    //Obtener toda la información referente a un asesor
    public void listarAsesor() {

        String filtroAgenciaX = "";
        String filtroAgenciaXZona = "";
        if (filtroAgencia != null && (filtroAgencia.trim().equalsIgnoreCase("0") == false && filtroAgencia.trim().equalsIgnoreCase("100") == false /*|| filtroAgencia.trim().equalsIgnoreCase("101") == false || filtroAgencia.trim().equalsIgnoreCase("102") == false*/ ) ) {
            filtroAgenciaX = filtroAgencia;
            filtroAgenciaXZona = (filtroAgencia.equalsIgnoreCase("101")?"'NORTE'":(filtroAgencia.equalsIgnoreCase("102")?"'CENTRO-SUR'":"'NORTE','CENTRO-SUR'"));
        } else {
            
            String ids = "   "; String idsZona = "   ";
            for (AdAgencias lstAgenciasDe : lstAgencias) {
                ids = ids + " " + lstAgenciasDe.getAgenciaid() + ",";
                idsZona=idsZona + " '" + lstAgenciasDe.getZona() + "',";
            }
            filtroAgenciaX = ids.substring(0, ids.length() - 1);
            filtroAgenciaXZona = idsZona.substring(0, idsZona.length() - 1);
      
        }
        String where ="";    
        where = " and a.agenciaid in(" + filtroAgenciaX + ") and a.tipousuario<>2 ";
        if(filtroAgencia.equalsIgnoreCase("100") || filtroAgencia.equalsIgnoreCase("101") || filtroAgencia.equalsIgnoreCase("102"))
        {
            if(getUser().getTipousuario().equalsIgnoreCase("2") || getUser().getTipousuario().equalsIgnoreCase("5") 
                    || getUser().getC_puesto().equalsIgnoreCase("53") || getUser().getC_puesto().equalsIgnoreCase("20") 
                    || getUser().getC_puesto().equalsIgnoreCase("38")
                    || getUser().getTipousuario().equalsIgnoreCase("6") )
            {
                    where = " and a.tipousuario='2' and a.agenciaid in(select b.agenciaid from AdAgencias b where b.zona in("+filtroAgenciaXZona+") )";
                
            }
          
        }else{}
  
        lstAsesores = usuarioEJB.listarUsuariosAsesoresByAgencia(where, getUser());
    }
    
    //Obtener toda la información referente a un asesor, pestaña dos
    public void listarAsesor2() {

        String filtroAgenciaX = "";
        String filtroAgenciaXZona = "";
        if (filtroAgencia2 != null && (filtroAgencia2.trim().equalsIgnoreCase("0") == false && filtroAgencia2.trim().equalsIgnoreCase("100") == false /*|| filtroAgencia.trim().equalsIgnoreCase("101") == false || filtroAgencia.trim().equalsIgnoreCase("102") == false*/ )) {
            filtroAgenciaX = filtroAgencia2;
            filtroAgenciaXZona = (filtroAgencia2.equalsIgnoreCase("101")?"'NORTE'":(filtroAgencia2.equalsIgnoreCase("102")?"'CENTRO-SUR'":"'NORTE','CENTRO-SUR'"));
        } else {
            //(Si selecciona todos en agencia)compara si el puesto del usuario es gerente negocios(11)/jefe distrito(60)
           String ids = "   ";String idsZona = "   ";
            for (AdAgencias lstAgenciasDe : lstAgencias) {
                ids = ids + " " + lstAgenciasDe.getAgenciaid() + ",";
                 idsZona=idsZona + " '" + lstAgenciasDe.getZona() + "',";
            }
            filtroAgenciaX = ids.substring(0, ids.length() - 1);
    
        }
        
        String where ="";    
        where = " and a.agenciaid in(" + filtroAgenciaX + ") and a.tipousuario<>2 ";
        if(filtroAgencia2.equalsIgnoreCase("100") || filtroAgencia2.equalsIgnoreCase("101") || filtroAgencia2.equalsIgnoreCase("102"))
        {
            if(getUser().getTipousuario().equalsIgnoreCase("2") || getUser().getTipousuario().equalsIgnoreCase("5") 
                    || getUser().getC_puesto().equalsIgnoreCase("53") || getUser().getC_puesto().equalsIgnoreCase("20") 
                    || getUser().getC_puesto().equalsIgnoreCase("38")
                    || getUser().getTipousuario().equalsIgnoreCase("6"))
            {
                    where = " and a.tipousuario='2' and a.agenciaid in(select b.agenciaid from AdAgencias b where b.zona in("+filtroAgenciaXZona+") )";

            }
          
        }else{}
    
        lstAsesores2 = usuarioEJB.listarUsuariosAsesoresByAgencia(where, getUser());
    }

    //Lenar lista de resultados posibles
    private void llenarResultados() {

        lstResutados.add(new ResultadosVisitas("PROMOCION", "LLENO_SOLICITUD"));
        lstResutados.add(new ResultadosVisitas("PROMOCION", "LLENARA_DESPUES"));
        lstResutados.add(new ResultadosVisitas("PROMOCION", "NO_ACEPTO"));
        lstResutados.add(new ResultadosVisitas("PROMOCION", "NO_LOCALIZADO"));
        lstResutados.add(new ResultadosVisitas("PROMOCION", "VISITA_CANCELADA"));

        lstResutados.add(new ResultadosVisitas("COBRO", "ACEPTO_COMPROMISO"));
        lstResutados.add(new ResultadosVisitas("COBRO", "YA_PAGO"));
        lstResutados.add(new ResultadosVisitas("COBRO", "SE_DEJO_AVISO"));
        lstResutados.add(new ResultadosVisitas("COBRO", "NO_LOCALIZADO"));
        lstResutados.add(new ResultadosVisitas("COBRO", "VISITA_CANCELADA"));

        lstResutados.add(new ResultadosVisitas("CLIENTE_SUBSIGUIENTE", "LLENO_SOLICITUD"));
        lstResutados.add(new ResultadosVisitas("CLIENTE_SUBSIGUIENTE", "LLENARA_DESPUES"));
        lstResutados.add(new ResultadosVisitas("CLIENTE_SUBSIGUIENTE", "NO_ACEPTO"));
        lstResutados.add(new ResultadosVisitas("CLIENTE_SUBSIGUIENTE", "NO_LOCALIZADO"));
        lstResutados.add(new ResultadosVisitas("CLIENTE_SUBSIGUIENTE", "VISITA_CANCELADA"));

        lstResutados.add(new ResultadosVisitas("SEGUIMIENTO_CLIENTE", "AL_DIA"));
        lstResutados.add(new ResultadosVisitas("SEGUIMIENTO_CLIENTE", "INVERSION_REALIZADA"));
        lstResutados.add(new ResultadosVisitas("SEGUIMIENTO_CLIENTE", "INVERSION_NO_REALIZADA"));
        lstResutados.add(new ResultadosVisitas("SEGUIMIENTO_CLIENTE", "NO_LOCALIZADO"));
        lstResutados.add(new ResultadosVisitas("SEGUIMIENTO_CLIENTE", "VISITA_CANCELADA"));

    }

//Obtener o establecer variables    
    public AdVVisitas getObjeto() {
        return objeto;
    }

    public void setObjeto(AdVVisitas objeto) {
        this.objeto = objeto;
    }

    public List<AdVVisitas> getLstActividades() {
        return lstActividades;
    }

    public void setLstActividades(List<AdVVisitas> lstActividades) {
        this.lstActividades = lstActividades;
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

    public String getFiltroTipoV() {
        return filtroTipoV;
    }

    public void setFiltroTipoV(String filtroTipoV) {
        this.filtroTipoV = filtroTipoV;
    }

    public String getFiltroResultadoV() {
        return filtroResultadoV;
    }

    public void setFiltroResultadoV(String filtroResultadoV) {
        this.filtroResultadoV = filtroResultadoV;
    }

    public List<TiposVisita> getLstFiltroTipos() {
        return lstFiltroTipos;
    }

    public void setLstFiltroTipos(List<TiposVisita> lstFiltroTipos) {
        this.lstFiltroTipos = lstFiltroTipos;
    }

    public List<ResultadosVisitas> getLstFiltrpResutados() {
        return lstFiltrpResutados;
    }

    public void setLstFiltrpResutados(List<ResultadosVisitas> lstFiltrpResutados) {
        this.lstFiltrpResutados = lstFiltrpResutados;
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

    public Date getFiltroFechaC1() {
        return filtroFechaC1;
    }

    public void setFiltroFechaC1(Date filtroFechaC1) {
        this.filtroFechaC1 = filtroFechaC1;
    }

    public Date getFiltroFechaC2() {
        return filtroFechaC2;
    }

    public void setFiltroFechaC2(Date filtroFechaC2) {
        this.filtroFechaC2 = filtroFechaC2;
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

    //////////////////////////////////////SIN VISITAR
    
    public void listarSinVisitar() {
       
        String filtroAgenciaX = "";
        String filtroAsesorX = "";

        String where = " ";//" and s.sucursal='"+getUser().getC_agencia()+"'";
       
        if (filtroAgencia2 != null &&  filtroAgencia2.trim().equalsIgnoreCase("0") == false && filtroAgencia2.trim().equalsIgnoreCase("100") == false ) {
           filtroAgenciaX = filtroAgencia2;
        } else {
            String ids = "   ";
            for (AdAgencias lstAgenciasDe : lstAgencias) {
                ids = ids + " " + lstAgenciasDe.getAgenciaid() + ",";
            }
            filtroAgenciaX = ids.substring(0, ids.length() - 1);
        }

        ///////Filtro Asesor
        if (filtroAsesor2 != null && filtroAsesor2.equalsIgnoreCase("0") == false) {
            filtroAsesorX = "'" + filtroAsesor2 + "'";
        } else {}
        if (filtroNombre2 != null && filtroNombre2.isEmpty() == false) {
            where = where + " AND UPPER(c.nombre) like  UPPER('%" + filtroNombre2 + "%') ";
        }

        if (filtroFecha_t2 != null && filtroFecha_t2 != null) {
            where = where + " AND p.cuenta not in(select prestamo from ad_visitas where fechacompleto  >= to_timestamp('" + sdf_ymd.format(filtroFecha_t2) + " 12:00:00 AM', 'yyyy/mm/dd HH:MI:SS AM' )   )";
        } else {
            where = where + " AND p.cuenta not in(select prestamo from ad_visitas)";
        }

        try {
            lstSinActividades = prestamosEJB.listarClienteSinVisitas(filtroAgenciaX, filtroAsesorX, filtroNombre2, filtroFecha_t2);
        } catch (Exception e) {
            System.out.println("Error ActiviadesController Clietnes sin visitar:" + e);
        }
        System.out.println("Cantidad Clietnes sin visitar:" + lstSinActividades.size());

        if (lstSinActividades != null) {
            if (lstSinActividades.size() > 0) {

                lstDirecciones.clear();

            lstDirecciones = direccionesEJB.listarDireccionesByIdClientes(codigosClientes(lstSinActividades));
            try {
                for (int i = 0; i < lstSinActividades.size(); i++) {
                    lstSinActividades.get(i).setObjetoDir(obtenerDirByCL(lstSinActividades.get(i)));

                }
            } catch (Exception e) {
                System.out.println("Actividades Controller  Dir " + e);
            }

            }
        }

        if (lstSinActividades.size() > 0) {}

    }

    public void limpiarFecha() {
        filtroFecha_t2 = null;
    }

    public List<ActividadesMoviles> getLstSinActividades() {
        return lstSinActividades;
    }

    public void setLstSinActividades(List<ActividadesMoviles> lstSinActividades) {
        this.lstSinActividades = lstSinActividades;
    }

    public String getFiltroAgencia2() {
        return filtroAgencia2;
    }

    public void setFiltroAgencia2(String filtroAgencia2) {
        this.filtroAgencia2 = filtroAgencia2;
    }

    public String getFiltroAsesor2() {
        return filtroAsesor2;
    }

    public void setFiltroAsesor2(String filtroAsesor2) {
        this.filtroAsesor2 = filtroAsesor2;
    }

    public String getFiltroNombre2() {
        return filtroNombre2;
    }

    public void setFiltroNombre2(String filtroNombre2) {
        this.filtroNombre2 = filtroNombre2;
    }

    public Date getFiltroFecha_t2() {
        return filtroFecha_t2;
    }

    public void setFiltroFecha_t2(Date filtroFecha_t2) {
        this.filtroFecha_t2 = filtroFecha_t2;
    }

    public boolean isCheckOmitir() {
        return checkOmitir;
    }

    public void setCheckOmitir(boolean checkOmitir) {
        this.checkOmitir = checkOmitir;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public double getLatIni() {
        return latIni;
    }

    public void setLatIni(double latIni) {
        this.latIni = latIni;
    }

    public double getLonIni() {
        return lonIni;
    }

    public void setLonIni(double lonIni) {
        this.lonIni = lonIni;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public List<AdUsuarios> getLstAsesores2() {
        return lstAsesores2;
    }

    public void setLstAsesores2(List<AdUsuarios> lstAsesores2) {
        this.lstAsesores2 = lstAsesores2;
    }
    
    public void verDocumentoPDF(String id,AdVVisitas ob) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        System.out.println("Compromiso ID: |"+id);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        if(ob!=null)
            objeto=ob;

        System.out.println(objeto);
        String nombreRep="";
        Map parameter = new HashMap();
        parameter.put("id", id);
        parameter.put("pathfirma", Constantes.ruta_firmas);
        
        if(objeto.getTipovisita()   .equalsIgnoreCase("NA"))
        {
            nombreRep="compromisoPago";
            parameter.put("nombreusr", getUser().getNombre());

        }else if(objeto.getTipovisita().equalsIgnoreCase("COBRO"))
        {
            nombreRep="compromisoPago2";
        }else  if(objeto.getTipovisita().equalsIgnoreCase("PROMOCION"))
        {
            nombreRep="EncuestaPromocion";
        }else if(objeto.getTipovisita().equalsIgnoreCase("SEGUIMIENTO_CLIENTE"))
        {
            nombreRep="SeguimientoCredito";
        } else if(objeto.getTipovisita().equalsIgnoreCase("CLIENTE_SUBSIGUIENTE"))
        {
            nombreRep="EncuestaPromocionSub";
        }    

        MasterPrintDAO print = new MasterPrintDAO();
        print.generarPDF(nombreRep, parameter, "");

    }
        
   
    private boolean isPosBack() {
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();
        return rpta;
    }

    ///////////////////////////////////////GRAFICO
    
    private PieChartModel pieModel;

    private PolarAreaChartModel polarAreaModel;
    //
    private LineChartModel lineModel;

    private LineChartModel cartesianLinerModel;
    //
    private BarChartModel barModel;

    private BarChartModel barModel2;
    //
    private HorizontalBarChartModel hbarModel;
    //
    private BarChartModel stackedBarModel;

    private BarChartModel stackedGroupBarModel;
    //
    private RadarChartModel radarModel;

    private RadarChartModel radarModel2;
    //
    private BubbleChartModel bubbleModel;
    //
    private BarChartModel mixedModel;
    //
    private DonutChartModel donutModel;
    //
    private ScatterChartModel scatterModel;
    
    //Pastel
    private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(300);
        values.add(50);
        values.add(100);
        values.add(30);
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(255, 105, 85)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Red");
        labels.add("Blue");
        labels.add("Yellow");
        labels.add("XX");
        data.setLabels(labels);

        pieModel.setData(data);
       
    }
        
    private void createPolarAreaModel() {
        polarAreaModel = new PolarAreaChartModel();
        ChartData data = new ChartData();

        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(11);
        values.add(16);
        values.add(7);
        values.add(3);
        values.add(14);
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(75, 192, 192)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(201, 203, 207)");
        bgColors.add("rgb(54, 162, 235)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Red");
        labels.add("Green");
        labels.add("Yellow");
        labels.add("Grey");
        labels.add("Blue");
        data.setLabels(labels);

        polarAreaModel.setData(data);
    }

    public void createLineModel() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("My First Dataset");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        data.addChartDataSet(dataSet);

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);

        //Opciones
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Line Chart");
        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);
    }

    public void createScatterModel() {
        scatterModel = new ScatterChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        values.add(new NumericPoint(-10, 0));
        values.add(new NumericPoint(0, 10));
        values.add(new NumericPoint(10, 5));
        values.add(new NumericPoint(8, 14));
        values.add(new NumericPoint(12, 2));
        values.add(new NumericPoint(13, 7));
        values.add(new NumericPoint(6, 9));
        dataSet.setData(values);
        dataSet.setLabel("Red Dataset");
        dataSet.setBorderColor("rgb(249, 24, 24)");
        dataSet.setShowLine(false);
        data.addChartDataSet(dataSet);

        //Opciones
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Scatter Chart");
        options.setShowLines(false);
        options.setTitle(title);

        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setType("linear");
        linearAxes.setPosition("bottom");
        cScales.addXAxesData(linearAxes);
        options.setScales(cScales);

        scatterModel.setOptions(options);
        scatterModel.setData(data);
    }

    public void createCartesianLinerModel() {
        cartesianLinerModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        values.add(20);
        values.add(50);
        values.add(100);
        values.add(75);
        values.add(25);
        values.add(0);
        dataSet.setData(values);
        dataSet.setLabel("Left Dataset");
        dataSet.setYaxisID("left-y-axis");
        dataSet.setFill(true);

        LineChartDataSet dataSet2 = new LineChartDataSet();
        List<Object> values2 = new ArrayList<>();
        values2.add(0.1);
        values2.add(0.5);
        values2.add(1.0);
        values2.add(2.0);
        values2.add(1.5);
        values2.add(0);
        dataSet2.setData(values2);
        dataSet2.setLabel("Right Dataset");
        dataSet2.setYaxisID("right-y-axis");
        dataSet2.setFill(true);

        data.addChartDataSet(dataSet);
        data.addChartDataSet(dataSet2);

        List<String> labels = new ArrayList<>();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("Mar");
        labels.add("Apr");
        labels.add("May");
        labels.add("Jun");
        data.setLabels(labels);
        cartesianLinerModel.setData(data);

        //Opciones
        LineChartOptions options = new LineChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setId("left-y-axis");
        linearAxes.setPosition("left");
        CartesianLinearAxes linearAxes2 = new CartesianLinearAxes();
        linearAxes2.setId("right-y-axis");
        linearAxes2.setPosition("right");

        cScales.addYAxesData(linearAxes);
        cScales.addYAxesData(linearAxes2);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Cartesian Linear Chart");
        options.setTitle(title);

        cartesianLinerModel.setOptions(options);
    }
    
    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
        int todas = 0;
        int completadas =0;
        
        
        if(lstActividades!=null)
        {
            todas = lstActividades.size();
            completadas =(int) lstActividades.stream().filter(p->p.getFechacompleto()!=null).count();
            
        }else{
            return;
            /*
            Calendar c = Calendar.getInstance();
            Date now = c.getTime();
            c.add(Calendar.DATE, -30);
            Date nowMinus30 = c.getTime();
            if(!filtroAgencia.equals("100") && !filtroAgencia.equals("101") && !filtroAgencia.equals("102"))
            {
                todas=visitasEJB.obtenerVisitasByTipo("",nowMinus30, now,getUser()).size();
                completadas=(int)visitasEJB.obtenerVisitasByTipo("",nowMinus30, now,getUser()).stream().filter(p->p.getFechacompleto()!=null).count();
            }else{
                todas=visitasEJB.obtenerVisitasByTipo("",nowMinus30, now,getUser()).size();
                completadas=(int)visitasEJB.obtenerVisitasByTipo("",nowMinus30, now,getUser()).stream().filter(p->p.getFechacompleto()!=null).count();
            }
            */                        
        }
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Comparativo");

        List<Number> values = new ArrayList<>();
        values.add(todas);
        values.add(completadas);
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Agendadas");
        labels.add("Completadas");
 
        data.setLabels(labels);
        barModel.setData(data);

        //Opciones
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Grafico de Barras");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // Desactivar animación
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }

    public void createBarModel2() {
        barModel2 = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("My First Dataset");
        barDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");
        barDataSet.setBorderColor("rgb(255, 99, 132)");
        barDataSet.setBorderWidth(1);
        List<Number> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        barDataSet.setData(values);

        BarChartDataSet barDataSet2 = new BarChartDataSet();
        barDataSet2.setLabel("My Second Dataset");
        barDataSet2.setBackgroundColor("rgba(255, 159, 64, 0.2)");
        barDataSet2.setBorderColor("rgb(255, 159, 64)");
        barDataSet2.setBorderWidth(1);
        List<Number> values2 = new ArrayList<>();
        values2.add(85);
        values2.add(69);
        values2.add(20);
        values2.add(51);
        values2.add(76);
        values2.add(75);
        values2.add(10);
        barDataSet2.setData(values2);

        data.addChartDataSet(barDataSet);
        data.addChartDataSet(barDataSet2);

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        barModel2.setData(data);

        //Opciones
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        barModel2.setOptions(options);
    }

    public void createHorizontalBarModel() {
        hbarModel = new HorizontalBarChartModel();
        ChartData data = new ChartData();

        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
        hbarDataSet.setLabel("My First Dataset");

        List<Number> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        hbarDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        hbarDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        hbarDataSet.setBorderColor(borderColor);
        hbarDataSet.setBorderWidth(1);

        data.addChartDataSet(hbarDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        hbarModel.setData(data);

        //Opciones
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addXAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Horizontal Bar Chart");
        options.setTitle(title);

        hbarModel.setOptions(options);
    }

    public void createStackedBarModel() {
        stackedBarModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Dataset 1");
        barDataSet.setBackgroundColor("rgb(255, 99, 132)");
        List<Number> dataVal = new ArrayList<>();
        dataVal.add(62);
        dataVal.add(-58);
        dataVal.add(-49);
        dataVal.add(25);
        dataVal.add(4);
        dataVal.add(77);
        dataVal.add(-41);
        barDataSet.setData(dataVal);

        BarChartDataSet barDataSet2 = new BarChartDataSet();
        barDataSet2.setLabel("Dataset 2");
        barDataSet2.setBackgroundColor("rgb(54, 162, 235)");
        List<Number> dataVal2 = new ArrayList<>();
        dataVal2.add(-1);
        dataVal2.add(32);
        dataVal2.add(-52);
        dataVal2.add(11);
        dataVal2.add(97);
        dataVal2.add(76);
        dataVal2.add(-78);
        barDataSet2.setData(dataVal2);

        BarChartDataSet barDataSet3 = new BarChartDataSet();
        barDataSet3.setLabel("Dataset 3");
        barDataSet3.setBackgroundColor("rgb(75, 192, 192)");
        List<Number> dataVal3 = new ArrayList<>();
        dataVal3.add(-44);
        dataVal3.add(25);
        dataVal3.add(15);
        dataVal3.add(92);
        dataVal3.add(80);
        dataVal3.add(-25);
        dataVal3.add(-11);
        barDataSet3.setData(dataVal3);

        data.addChartDataSet(barDataSet);
        data.addChartDataSet(barDataSet2);
        data.addChartDataSet(barDataSet3);

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        stackedBarModel.setData(data);

        //Opciones
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setStacked(true);
        linearAxes.setOffset(true);
        cScales.addXAxesData(linearAxes);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart - Stacked");
        options.setTitle(title);

        Tooltip tooltip = new Tooltip();
        tooltip.setMode("index");
        tooltip.setIntersect(false);
        options.setTooltip(tooltip);

        stackedBarModel.setOptions(options);
    }

    public void createStackedGroupBarModel() {
        stackedGroupBarModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Dataset 1");
        barDataSet.setBackgroundColor("rgb(255, 99, 132)");
        barDataSet.setStack("Stack 0");
        List<Number> dataVal = new ArrayList<>();
        dataVal.add(-32);
        dataVal.add(-70);
        dataVal.add(-33);
        dataVal.add(30);
        dataVal.add(-49);
        dataVal.add(23);
        dataVal.add(-92);
        barDataSet.setData(dataVal);

        BarChartDataSet barDataSet2 = new BarChartDataSet();
        barDataSet2.setLabel("Dataset 2");
        barDataSet2.setBackgroundColor("rgb(54, 162, 235)");
        barDataSet2.setStack("Stack 0");
        List<Number> dataVal2 = new ArrayList<>();
        dataVal2.add(83);
        dataVal2.add(18);
        dataVal2.add(86);
        dataVal2.add(8);
        dataVal2.add(34);
        dataVal2.add(46);
        dataVal2.add(11);
        barDataSet2.setData(dataVal2);

        BarChartDataSet barDataSet3 = new BarChartDataSet();
        barDataSet3.setLabel("Dataset 3");
        barDataSet3.setBackgroundColor("rgb(75, 192, 192)");
        barDataSet3.setStack("Stack 1");
        List<Number> dataVal3 = new ArrayList<>();
        dataVal3.add(-45);
        dataVal3.add(73);
        dataVal3.add(-25);
        dataVal3.add(65);
        dataVal3.add(49);
        dataVal3.add(-18);
        dataVal3.add(46);
        barDataSet3.setData(dataVal3);

        data.addChartDataSet(barDataSet);
        data.addChartDataSet(barDataSet2);
        data.addChartDataSet(barDataSet3);

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        stackedGroupBarModel.setData(data);

        //Opciones
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setStacked(true);
        linearAxes.setOffset(true);
        cScales.addXAxesData(linearAxes);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart - Stacked Group");
        options.setTitle(title);

        Tooltip tooltip = new Tooltip();
        tooltip.setMode("index");
        tooltip.setIntersect(false);
        options.setTooltip(tooltip);

        stackedGroupBarModel.setOptions(options);
    }

    public void createRadarModel() {
        radarModel = new RadarChartModel();
        ChartData data = new ChartData();

        RadarChartDataSet radarDataSet = new RadarChartDataSet();
        radarDataSet.setLabel("My First Dataset");
        radarDataSet.setFill(true);
        radarDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");
        radarDataSet.setBorderColor("rgb(255, 99, 132)");
        radarDataSet.setPointBackgroundColor("rgb(255, 99, 132)");
        radarDataSet.setPointBorderColor("#fff");
        radarDataSet.setPointHoverBackgroundColor("#fff");
        radarDataSet.setPointHoverBorderColor("rgb(255, 99, 132)");
        List<Number> dataVal = new ArrayList<>();
        dataVal.add(65);
        dataVal.add(59);
        dataVal.add(90);
        dataVal.add(81);
        dataVal.add(56);
        dataVal.add(55);
        dataVal.add(40);
        radarDataSet.setData(dataVal);

        RadarChartDataSet radarDataSet2 = new RadarChartDataSet();
        radarDataSet2.setLabel("My Second Dataset");
        radarDataSet2.setFill(true);
        radarDataSet2.setBackgroundColor("rgba(54, 162, 235, 0.2)");
        radarDataSet2.setBorderColor("rgb(54, 162, 235)");
        radarDataSet2.setPointBackgroundColor("rgb(54, 162, 235)");
        radarDataSet2.setPointBorderColor("#fff");
        radarDataSet2.setPointHoverBackgroundColor("#fff");
        radarDataSet2.setPointHoverBorderColor("rgb(54, 162, 235)");
        List<Number> dataVal2 = new ArrayList<>();
        dataVal2.add(28);
        dataVal2.add(48);
        dataVal2.add(40);
        dataVal2.add(19);
        dataVal2.add(96);
        dataVal2.add(27);
        dataVal2.add(100);
        radarDataSet2.setData(dataVal2);

        data.addChartDataSet(radarDataSet);
        data.addChartDataSet(radarDataSet2);

        List<String> labels = new ArrayList<>();
        labels.add("Eating");
        labels.add("Drinking");
        labels.add("Sleeping");
        labels.add("Designing");
        labels.add("Coding");
        labels.add("Cycling");
        labels.add("Running");
        data.setLabels(labels);

        /* Opciones */
        RadarChartOptions options = new RadarChartOptions();
        Elements elements = new Elements();
        ElementsLine elementsLine = new ElementsLine();
        elementsLine.setTension(0);
        elementsLine.setBorderWidth(3);
        elements.setLine(elementsLine);
        options.setElements(elements);

        radarModel.setOptions(options);
        radarModel.setData(data);
    }

    public void createRadarModel2() {
        radarModel2 = new RadarChartModel();
        ChartData data = new ChartData();

        RadarChartDataSet radarDataSet = new RadarChartDataSet();
        radarDataSet.setLabel("P.Practitioner");
        radarDataSet.setBackgroundColor("rgba(102, 153, 204, 0.2)");
        radarDataSet.setBorderColor("rgba(102, 153, 204, 1)");
        radarDataSet.setPointBackgroundColor("rgba(102, 153, 204, 1)");
        radarDataSet.setPointBorderColor("#fff");
        radarDataSet.setPointHoverRadius(5);
        radarDataSet.setPointHoverBackgroundColor("#fff");
        radarDataSet.setPointHoverBorderColor("rgba(102, 153, 204, 1)");
        List<Number> dataVal = new ArrayList<>();
        dataVal.add(2);
        dataVal.add(3);
        dataVal.add(2);
        dataVal.add(1);
        dataVal.add(3);
        radarDataSet.setData(dataVal);

        RadarChartDataSet radarDataSet2 = new RadarChartDataSet();
        radarDataSet2.setLabel("P.Manager");
        radarDataSet2.setBackgroundColor("rgba(255, 204, 102, 0.2)");
        radarDataSet2.setBorderColor("rgba(255, 204, 102, 1)");
        radarDataSet2.setPointBackgroundColor("rgba(255, 204, 102, 1)");
        radarDataSet2.setPointBorderColor("#fff");
        radarDataSet2.setPointHoverRadius(5);
        radarDataSet2.setPointHoverBackgroundColor("#fff");
        radarDataSet2.setPointHoverBorderColor("rgba(255, 204, 102, 1)");
        List<Number> dataVal2 = new ArrayList<>();
        dataVal2.add(2);
        dataVal2.add(3);
        dataVal2.add(3);
        dataVal2.add(2);
        dataVal2.add(3);
        radarDataSet2.setData(dataVal2);

        data.addChartDataSet(radarDataSet);
        data.addChartDataSet(radarDataSet2);

        List<List<String>> labels = new ArrayList<>();
        labels.add(new ArrayList(Arrays.asList("Process", "Excellence")));
        labels.add(new ArrayList(Arrays.asList("Problem", "Solving")));
        labels.add(new ArrayList(Arrays.asList("Facilitation")));
        labels.add(new ArrayList(Arrays.asList("Project", "Mgmt")));
        labels.add(new ArrayList(Arrays.asList("Change", "Mgmt")));
        data.setLabels(labels);

        /* Opciones */
        RadarChartOptions options = new RadarChartOptions();
        RadialScales rScales = new RadialScales();

        RadialLinearAngleLines angelLines = new RadialLinearAngleLines();
        angelLines.setDisplay(true);
        angelLines.setLineWidth(0.5);
        angelLines.setColor("rgba(128, 128, 128, 0.2)");
        rScales.setAngelLines(angelLines);

        RadialLinearPointLabels pointLabels = new RadialLinearPointLabels();
        pointLabels.setFontSize(14);
        pointLabels.setFontStyle("300");
        pointLabels.setFontColor("rgba(204, 204, 204, 1)");
        pointLabels.setFontFamily("Lato, sans-serif");

        RadialLinearTicks ticks = new RadialLinearTicks();
        ticks.setBeginAtZero(true);
        ticks.setMaxTicksLimit(3);
        ticks.setMin(0);
        ticks.setMax(3);
        ticks.setDisplay(false);

        options.setScales(rScales);

        radarModel2.setOptions(options);
        radarModel2.setData(data);
        radarModel2.setExtender("skinRadarChart");
    }

    public void createBubbleModel() {
        bubbleModel = new BubbleChartModel();
        ChartData data = new ChartData();

        BubbleChartDataSet dataSet = new BubbleChartDataSet();
        List<BubblePoint> values = new ArrayList<>();
        values.add(new BubblePoint(20, 30, 15));
        values.add(new BubblePoint(40, 10, 10));
        dataSet.setData(values);
        dataSet.setBackgroundColor("rgb(255, 99, 132)");
        dataSet.setLabel("First Dataset");
        data.addChartDataSet(dataSet);
        bubbleModel.setData(data);
    }

    public void createMixedModel() {
        mixedModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet dataSet = new BarChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(10);
        values.add(20);
        values.add(30);
        values.add(40);
        dataSet.setData(values);
        dataSet.setLabel("Bar Dataset");
        dataSet.setBorderColor("rgb(255, 99, 132)");
        dataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");

        LineChartDataSet dataSet2 = new LineChartDataSet();
        List<Object> values2 = new ArrayList<>();
        values2.add(50);
        values2.add(50);
        values2.add(50);
        values2.add(50);
        dataSet2.setData(values2);
        dataSet2.setLabel("Line Dataset");
        dataSet2.setFill(false);
        dataSet2.setBorderColor("rgb(54, 162, 235)");

        data.addChartDataSet(dataSet);
        data.addChartDataSet(dataSet2);

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        data.setLabels(labels);

        mixedModel.setData(data);

        //Opciones
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);

        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
        mixedModel.setOptions(options);
    }
    //DONA
    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        int cobro = 0;
        int promo =0;
        int seg =0;
        int sub =0;
        
        if(lstActividades!=null)
        {
            cobro = (int)(lstActividades.stream().filter(p->p.getTipovisita().equalsIgnoreCase("COBRO")).count());
            promo =(int) lstActividades.stream().filter(p->p.getTipovisita().equalsIgnoreCase("PROMOCION")).count();
            seg =(int) lstActividades.stream().filter(p->p.getTipovisita().equalsIgnoreCase("SEGUIMIENTO_CLIENTE")).count();
            sub =(int) lstActividades.stream().filter(p->p.getTipovisita().equalsIgnoreCase("CLIENTE_SUBSIGUIENTE")).count();
            textoGraficos=" (En base a filtros)";
        }else{
            return;
            /*
            Calendar c = Calendar.getInstance();
            Date now = c.getTime();
            c.add(Calendar.DATE, -30);
            Date nowMinus30 = c.getTime();
            cobro=visitasEJB.obtenerVisitasByTipo("COBRO",nowMinus30, now,getUser()).size();
            promo=visitasEJB.obtenerVisitasByTipo("PROMOCION",nowMinus30, now,getUser()).size();
            seg=visitasEJB.obtenerVisitasByTipo("SEGUIMIENTO_CLIENTE",nowMinus30, now,getUser()).size();
            sub=visitasEJB.obtenerVisitasByTipo("CLIENTE_SUBSIGUIENTE",nowMinus30, now,getUser()).size();
            textoGraficos=" (Ultimos 30 dias)";
            */
        }
     
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(cobro);
        values.add(promo);
        values.add(seg);
        values.add(sub);
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(25, 25, 112)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(255, 105, 60)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("COBROS");
        labels.add("PROMOCIONES");
        labels.add("SEGUIMIENTO");
        labels.add("CANCELADOS/P.C.");
        data.setLabels(labels);

        donutModel.setData(data);
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public PolarAreaChartModel getPolarAreaModel() {
        return polarAreaModel;
    }

    public void setPolarAreaModel(PolarAreaChartModel polarAreaModel) {
        this.polarAreaModel = polarAreaModel;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public LineChartModel getCartesianLinerModel() {
        return cartesianLinerModel;
    }

    public void setCartesianLinerModel(LineChartModel cartesianLinerModel) {
        this.cartesianLinerModel = cartesianLinerModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public BarChartModel getBarModel2() {
        return barModel2;
    }

    public void setBarModel2(BarChartModel barModel2) {
        this.barModel2 = barModel2;
    }

    public HorizontalBarChartModel getHbarModel() {
        return hbarModel;
    }

    public void setHbarModel(HorizontalBarChartModel hbarModel) {
        this.hbarModel = hbarModel;
    }

    public BarChartModel getStackedBarModel() {
        return stackedBarModel;
    }

    public void setStackedBarModel(BarChartModel stackedBarModel) {
        this.stackedBarModel = stackedBarModel;
    }

    public BarChartModel getStackedGroupBarModel() {
        return stackedGroupBarModel;
    }

    public void setStackedGroupBarModel(BarChartModel stackedGroupBarModel) {
        this.stackedGroupBarModel = stackedGroupBarModel;
    }

    public RadarChartModel getRadarModel() {
        return radarModel;
    }

    public void setRadarModel(RadarChartModel radarModel) {
        this.radarModel = radarModel;
    }

    public RadarChartModel getRadarModel2() {
        return radarModel2;
    }

    public void setRadarModel2(RadarChartModel radarModel2) {
        this.radarModel2 = radarModel2;
    }

    public BubbleChartModel getBubbleModel() {
        return bubbleModel;
    }

    public void setBubbleModel(BubbleChartModel bubbleModel) {
        this.bubbleModel = bubbleModel;
    }

    public BarChartModel getMixedModel() {
        return mixedModel;
    }

    public void setMixedModel(BarChartModel mixedModel) {
        this.mixedModel = mixedModel;
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

    public ScatterChartModel getScatterModel() {
        return scatterModel;
    }

    public void setScatterModel(ScatterChartModel scatterModel) {
        this.scatterModel = scatterModel;
    }

    public String getTextoGraficos() {
        return textoGraficos;
    }

    public void setTextoGraficos(String textoGraficos) {
        this.textoGraficos = textoGraficos;
    }   
}

