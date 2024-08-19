/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.model;

import finsolhn.com.data.AdDirecciones;
import finsolhn.com.util.Constantes;
import static finsolhn.com.util.Constantes.sdf;
import static finsolhn.com.util.Constantes.sdf_dmy;

import java.io.Serializable;
import java.util.Date;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author ds010106
 */
public class ActividadesMoviles implements Serializable {

    private String secuencia;
    private String idCliente;
    private String nombre;
    private String prestamo;
    private String saldosJtsOid;
    private String tipoVisita;
    private String resultadoVisita;
    private String comentario;
    private Date fechaRegistro;
    private Date fechaProvisita;
    private Date horaProVisita;
    private Date fechaCompleto;
    private Date horaInicioVisita;
    private Date horaFinVisita;
    private String usrAgenda;
    private String usrVisito;
    private String estado_crud;
    private String nombreNuevo;
    private String telefonoNuevo;
    private String direccioNnuevo;
    private String moneda;
    private Double montoDesembolso;
    private Double capital;
    private Double interes;
    private String formaPago;
    private int nCuotas;
    private int cuotasRestantes;
    private Double cuotaVigente;
    private Double cuotaAtrasada;
    private Date fechaDesembolso;
    private Date fechaVence;
    private Date fechaProxima;
    private int diasAtraso;
    private String nomAsesor;
    private String idAgencia;
    private String nomAgencia;
    private Date fechaUltimaVisita;
    private String latVisita = "15.537659";
    private String lonVisita = "-88.028006";
    private Date fechaCompromiso;
    private Date fechaUltimoPago;
    private String porpagado = "";
    private String idClienteAsesor = "";
    private String cuentaAsesor = "";
    private String empleadoId = "";
    private String idgestor = "";
    
    private AdDirecciones objetoDir = new AdDirecciones();

    ///////////colores
    private String colorBack_Est;//fondo estructuras
    private String colorBack_R; //fondo estado req y casos
    private String colorBack_T; //fondo por tipo Caso o Estr
    private String colorBack_P; //fondo para permiso obj

    private static String gris = "#9E9DAB";
    private static String amarillo = "#F7DF31";
    private static String azul = "#06F2F9";
    private static String verde = "#AEF77F";
    private static String rojo = "#FC5342";

    private static String naranja = "#F8AC30";
    private static String naranja0 = "#F4C477";
    private static String azul2 = "#00B6BC";
    private static String morado = "#D098F7";
    private static String morado0 = "#E4C7F8";
    private static String verde2 = "#7FCA4F";

    public ActividadesMoviles() {
        //iniMap();
    }

    public String getFechaHoraProVisita() {
        if (fechaProvisita == null) {
            return "";
        }
        return Constantes.sdf_dmy.format(fechaProvisita) + " " + Constantes.sdf_ham.format(horaProVisita);
    }

    private String getPorcentajePagado() {

        if (capital == null || montoDesembolso == null) {
            return "";
        }

        if (getCapital() <= 0 || getMontoDesembolso() <= 0) {
            return "";
        }
        double v = Math.abs(((getCapital() * 100) / getMontoDesembolso()) - 100);

        return " | Pagado:(" + Math.round(v) + "%) |";

    }

    public String getColorBack_P() {

        return colorBack_P;
    }

    public void setColorBack_P(String colorBack_P) {
        this.colorBack_P = colorBack_P;
    }

    public String getColorBack_T() {
        /*
        if(tipo_registro.equalsIgnoreCase("C"))
        {
            colorBack_T=azul;
        }else if(tipo_registro.equalsIgnoreCase("A"))
        {
            colorBack_T=naranja;
        }
        else if(tipo_registro.equalsIgnoreCase("B"))
        {
            colorBack_T=naranja0;
        }else if(tipo_registro.equalsIgnoreCase("R")){
            colorBack_T=azul2;
        }else if(tipo_registro.equalsIgnoreCase("E"))
        {
            colorBack_T=morado;
        }else if(tipo_registro.equalsIgnoreCase("P"))
        {
            colorBack_T=morado0;
            if(estado.equalsIgnoreCase(Constantes.estado_ini_permiso))
            {
                colorBack_T=amarillo;
            }else if(estado.equalsIgnoreCase(Constantes.estado_completado_dba))
            {
                colorBack_T=gris;
            }
            
            
        }
        
        
         */
        return colorBack_T;
    }

    public void setColorBack_T(String colorBack_T) {
        this.colorBack_T = colorBack_T;
    }

    public String getColorBack_R() {

        return colorBack_R;
    }

    public void setColorBack_R(String colorBack_R) {
        this.colorBack_R = colorBack_R;
    }

    public String getColorBack_Est() {
        /*
        if( estado.equalsIgnoreCase(Constantes.estado_en_dba) 
                && tipo_registro.equalsIgnoreCase("B"))
        {
            colorBack_Est=gris;
        }else if(estado.equalsIgnoreCase(Constantes.estado_completado_dba)==false 
                && tipo_registro.equalsIgnoreCase("A"))
        {
            colorBack_Est=amarillo;
        }else if(estado.equalsIgnoreCase(Constantes.estado_completado_dba) 
                && tipo_registro.equalsIgnoreCase("U"))
        {
            colorBack_Est=azul;
        }else if(estado.equalsIgnoreCase(Constantes.estado_completado_dba) 
                && tipo_registro.equalsIgnoreCase("A") )
        {
            colorBack_Est=verde;
                    
        }else if(estado.equalsIgnoreCase(Constantes.estado_rechazado))
        {
            colorBack_Est=rojo;
        }

         */
        return colorBack_Est;
    }

    public void setColorBack_Est(String colorBack_Est) {
        this.colorBack_Est = colorBack_Est;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
    }

    public void setSaldosJtsOid(String saldosJtsOid) {
        this.saldosJtsOid = saldosJtsOid;
    }

    public void setTipoVisita(String tipoVisita) {
        this.tipoVisita = tipoVisita;
    }

    public void setResultadoVisita(String resultadoVisita) {
        this.resultadoVisita = resultadoVisita;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setFechaProvisita(Date fechaProvisita) {
        this.fechaProvisita = fechaProvisita;
    }

    public void setHoraProVisita(Date horaProVisita) {
        this.horaProVisita = horaProVisita;
    }

    public void setFechaCompleto(Date fechaCompleto) {

        this.fechaCompleto = fechaCompleto;
    }

    public void setHoraInicioVisita(Date horaInicioVisita) {
        this.horaInicioVisita = horaInicioVisita;
    }

    public void setHoraFinVisita(Date horaFinVisita) {
        this.horaFinVisita = horaFinVisita;
    }

    public void setUsrAgenda(String usrAgenda) {
        this.usrAgenda = usrAgenda;
    }

    public void setUsrVisito(String usrVisito) {
        this.usrVisito = usrVisito;
    }

    public void setLatVisita(String latVisita) {
        this.latVisita = latVisita;
    }

    public void setLonVisita(String lonVisita) {
        this.lonVisita = lonVisita;
    }

    public void setEstado_crud(String estado_crud) {
        this.estado_crud = estado_crud;
    }

    public void setNombreNuevo(String nombreNuevo) {
        this.nombreNuevo = nombreNuevo;
    }

    public void setTelefonoNuevo(String telefonoNuevo) {
        this.telefonoNuevo = telefonoNuevo;
    }

    public void setDireccioNnuevo(String direccioNnuevo) {
        this.direccioNnuevo = direccioNnuevo;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setMontoDesembolso(Double montoDesembolso) {
        this.montoDesembolso = montoDesembolso;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public void setCuotasRestantes(int cuotasRestantes) {
        this.cuotasRestantes = cuotasRestantes;
    }

    public void setCuotaVigente(Double cuotaVigente) {
        this.cuotaVigente = cuotaVigente;
    }

    public void setCuotaAtrasada(Double cuotaAtrasada) {
        this.cuotaAtrasada = cuotaAtrasada;
    }

    public void setFechaDesembolso(Date fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }

    public void setFechaVence(Date fechaVence) {
        this.fechaVence = fechaVence;
    }

    public void setFechaProxima(Date fechaProxima) {
        this.fechaProxima = fechaProxima;
    }

    public void setDiasAtraso(int diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    public void setNomAsesor(String nomAsesor) {
        this.nomAsesor = nomAsesor;
    }

    public void setIdAgencia(String idAgencia) {
        this.idAgencia = idAgencia;
    }

    public void setNomAgencia(String nomAgencia) {
        this.nomAgencia = nomAgencia;
    }

    public void setFechaUltimaVisita(Date fechaUltimaVisita) {
        this.fechaUltimaVisita = fechaUltimaVisita;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrestamo() {
        return prestamo;
    }

    public String getSaldosJtsOid() {
        return saldosJtsOid;
    }

    public String getTipoVisita() {
        return tipoVisita;
    }

    public String getResultadoVisita() {
       //  pagagoRecibido(fechaCompleto,fechaCompromiso,fechaUltimoPago );        
        return resultadoVisita;
    }

    public String getComentario() {
        return comentario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public Date getFechaProvisita() {
        return fechaProvisita;
    }

    public Date getHoraProVisita() {
        return horaProVisita;
    }

    public Date getFechaCompleto() {

        return fechaCompleto;
    }

    public Date getHoraInicioVisita() {
        return horaInicioVisita;
    }

    public Date getHoraFinVisita() {
        return horaFinVisita;
    }

    public String getUsrAgenda() {
        return usrAgenda;
    }

    public String getUsrVisito() {
        return usrVisito;
    }

    public String getLatVisita() {
        return latVisita;
    }

    public String getLonVisita() {
        return lonVisita;
    }

    public String getEstado_crud() {
        return estado_crud;
    }

    public String getNombreNuevo() {
        return nombreNuevo;
    }

    public String getTelefonoNuevo() {
        return telefonoNuevo;
    }

    public String getDireccioNnuevo() {
        return direccioNnuevo;
    }

    public String getMoneda() {
        return moneda;
    }

    public Double getMontoDesembolso() {
        return montoDesembolso;
    }

    public Double getCapital() {
        return capital;
    }

    public Double getInteres() {
        return interes;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public int getCuotasRestantes() {
        return cuotasRestantes;
    }

    public Double getCuotaVigente() {
        return cuotaVigente;
    }

    public Double getCuotaAtrasada() {
        return cuotaAtrasada;
    }

    public Date getFechaDesembolso() {
        return fechaDesembolso;
    }

    public Date getFechaVence() {
        return fechaVence;
    }

    public Date getFechaProxima() {
        return fechaProxima;
    }

    public int getDiasAtraso() {
        return diasAtraso;
    }

    public String getNomAsesor() {
        return nomAsesor;
    }

    public String getIdAgencia() {
        return idAgencia;
    }

    public String getNomAgencia() {
        return nomAgencia;
    }

    public Date getFechaUltimaVisita() {
        return fechaUltimaVisita;
    }

    public String getPorpagado() {

        porpagado = getPorcentajePagado();

        return porpagado;
    }

    public void setPorpagado(String porpagado) {
        this.porpagado = porpagado;
    }

    public int getnCuotas() {
        return nCuotas;
    }

    public void setnCuotas(int nCuotas) {
        this.nCuotas = nCuotas;
    }

    public AdDirecciones getObjetoDir() {
        return objetoDir;
    }

    public void setObjetoDir(AdDirecciones objetoDir) {
        this.objetoDir = objetoDir;
    }

    public Date getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(Date fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public Date getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    public void setFechaUltimoPago(Date fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    public String getIdClienteAsesor() {
        return idClienteAsesor;
    }

    public void setIdClienteAsesor(String idClienteAsesor) {
        this.idClienteAsesor = idClienteAsesor;
    }

    public String getCuentaAsesor() {
        return cuentaAsesor;
    }

    public void setCuentaAsesor(String cuentaAsesor) {
        this.cuentaAsesor = cuentaAsesor;
    }

    public String getIdgestor() {
        return idgestor;
    }

    public void setIdgestor(String idgestor) {
        this.idgestor = idgestor;
    }

    public String getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(String empleadoId) {
        this.empleadoId = empleadoId;
    }
    
    
    
    
/*
    public boolean pagagoRecibido(Date fechaVisito,Date fechaCompromiso,Date fechaUltimo ) {

        Date fechaHoy = new Date();
        //Date fechaUltimo=new Date();
        //Date fechaCompromiso=new Date();
        boolean ok = false;
        
        if (fechaUltimo != null || fechaCompromiso != null) {
            //ok=true;
            try {
                
            
            long fechaInicialMs = fechaUltimo.getTime();
            long fechaFinalMs = fechaVisito.getTime();
            long diferencia = fechaFinalMs - fechaInicialMs;
            double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            //Log.i(Utilidades.TAG, "onContinuar: DIAS  "+dias);
            System.out.println("dias para compromiso:"+dias);
            if (dias > 0) {
                //fpago
                fechaInicialMs = fechaCompromiso.getTime();
                fechaFinalMs = fechaUltimo.getTime();
                diferencia = fechaFinalMs - fechaInicialMs;
                double dias2 = Math.floor(diferencia / (1000 * 60 * 60 * 24));
                //Log.i(Utilidades.TAG, "funcionPanelCompromiso P2: " + sdf.format(fechaUltimo) + "(-)" + sdf.format(fechaCompromiso));
                //Log.i(Utilidades.TAG, "onContinuar: DIAS2  " + dias2);
                if (dias2 < 0) {
                    //txtFechaUltimoPago.setBackgroundResource(R.drawable.round_border_red);
                    //txtFechaCompromiso.setBackgroundResource(R.drawable.round_border_red);
                    
                } else {
                    
                    ok=true;
                }

            } else {
                // txtFechaUltimoPago.setBackgroundResource(R.drawable.round_border_red);
                // txtFechaCompromiso.setBackgroundResource(R.drawable.round_border_red);
            }
            } catch (Exception e) {
                System.out.println("Convercion error "+e);
            }

        }

        return ok;

    }*/

    private MapModel simpleModel;
    private Marker marker;

    public void iniMap() {

        simpleModel = new DefaultMapModel();

        if (resultadoVisita != null) {
            if (resultadoVisita.isEmpty()) {

                //RequestContext context = RequestContext.getCurrentInstance();
                //context.execute("PF('wdlUbicacion').hide();");
                PrimeFaces.current().executeScript("PF('wdlUbicacion').hide();");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "La visita no ha sido completada"));
                return;
            }
        }
        if (latVisita.equalsIgnoreCase("0")) {
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('wdlUbicacion').hide();");
            PrimeFaces.current().executeScript("PF('wdlUbicacion').hide();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Hay Ubicacion Registrada para esta visita"));
            return;
        }
        //Shared coordinates
        LatLng coord1 = new LatLng(Double.parseDouble(latVisita), Double.parseDouble(lonVisita));
        //Draggable
        marker = new Marker(coord1, "Finsol");

        simpleModel.addOverlay(marker);

    }

    /*public void updateMapas()
    {
        
        LatLng coord = new LatLng( Double.parseDouble(getLatVisita()), Double.parseDouble(getLonVisita()));
        //Draggable
        marker.setLatlng(coord);
        
       
        
    }*/
    public MapModel getSimpleModel() {
        return simpleModel;
    }

}
