package finsolhn.com.controller;

import finsolhn.com.dao.UsuariosMovilDAO3;
import finsolhn.com.data.AdAgencias;
import finsolhn.com.data.AdTiposusuarios;
import finsolhn.com.data.AdUsuarios;
import finsolhn.com.data.AdUsuariosxagencia;
import finsolhn.com.ejb.AdAgenciasFacadeLocal;
import finsolhn.com.ejb.AdTiposusuariosFacadeLocal;
import finsolhn.com.ejb.AdUsuariosFacadeLocal;
import finsolhn.com.ejb.AdUsuariosxagenciaFacadeLocal;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

//Autor: Ingeniero Renan Cardona

@Named("usuariosMovilController")
@ViewScoped

public class UsuariosMovilController extends DataGeneralController {

    //Variables y objetos
    UsuariosMovilDAO3 dao3 = new UsuariosMovilDAO3();

    @EJB
    AdUsuariosFacadeLocal usuarioEJB;
    @EJB
    AdAgenciasFacadeLocal agenciasEJB;
    @EJB
    AdUsuariosxagenciaFacadeLocal usuariosXagenciaEJB;
    @EJB
    AdTiposusuariosFacadeLocal tiposuserEJB;

    private String pasTempo;
    private AdUsuarios objeto = new AdUsuarios();
    private List<AdUsuarios> lstUsuarios;
    private List<AdUsuarios> lstUsuariosEmpleados;
    private List<AdAgencias> lstTodasAgencias;
    private List<AdAgencias> lstAgenciasUsr;
    private List<AdTiposusuarios> lstTiposUsr;
    private Short[] selectedAgencias;
    
 
    String operacion = "";
    private String texto = "";

    private String filtroNombre="";
    private String filtroNombre2;
    
    //Buscar empleado por su segundo nombre
    public void buscarEmpleado2() {
        String where = " AND UPPER(RF.Nombre) like UPPER( '%" + filtroNombre2 + "%') ";
        lstUsuariosEmpleados = dao3.listarUsuariosNoRegistrados(where);

    }

    public void prueba() {
        System.out.println("Seleccionadas");
        for (Short selectedAgencia : selectedAgencias) {
            System.out.println("Selec:" + selectedAgencia);
        }
    }
   //Metodo sirve para insertar o actualizar usuario
    public void insertar() {

        //revisa si es una insercion
        if (operacion.equalsIgnoreCase("I")) {

            try {

                if (objeto.getEmpleadoid() == 0)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Seleccione un Empleado"));
                    return;
                }
                if (objeto.getContrasenia().isEmpty()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete la Contraseña"));
                    return;
                }
                ////dao3.insertar(objeto);
                try {

                    objeto.setContrasenia(md5(objeto.getContrasenia()));
                    objeto.setEstadoCrud("NP");

                    usuarioEJB.create(objeto);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Guardado Correctamente"));
                    PrimeFaces.current().executeScript("PF('wdlUsuario').hide();");
                } catch (Exception e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al guardar" + e));
                    System.err.println("Error al insertar " + e);
                }

                //agrega objeto a la tabla requerimientos
                //lstReq.add(objeto);
            } catch (Exception e) {
                System.out.println("Bean inser err " + e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Complete los datos"));
            }
        } else {
            try {
                objeto.setEstadoCrud("MP");
                if (!objeto.getContrasenia().isEmpty()) {
                    if(objeto.getEstado().equalsIgnoreCase("1"))
                       objeto.setEstado("2");
                    
                    objeto.setContrasenia(md5(objeto.getContrasenia()));
                } else {
                    objeto.setContrasenia(pasTempo);
                }
               
                ////dao3.modificar(objeto);
                usuarioEJB.edit(objeto);
                Collections.replaceAll(lstUsuarios, objeto, objeto);
         
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Modificado Correctamente"));
                PrimeFaces.current().executeScript("PF('wdlUsuario').hide();");
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Error al Modificar" + e));
                System.err.println("Error al modificar " + e);
            }
        }

        try {

            usuariosXagenciaEJB.eliminarUserxAgencia(objeto.getUsuariotopaz());

            List<AdUsuariosxagencia> listaTem = new ArrayList();
            for (Short selectedAgencia : selectedAgencias) {

                AdAgencias ag = new AdAgencias();
                ag = agenciasEJB.find(selectedAgencia);

                AdUsuariosxagencia a = new AdUsuariosxagencia();
                //a.setId(0L);
                a.setId(Long.parseLong(usuariosXagenciaEJB.getMaxId()));
                a.setAgenciaid(ag.getAgenciaid());
                a.setUsuariotopaz(objeto.getUsuariotopaz());
                usuariosXagenciaEJB.create(a);

            }
        } catch (Exception e) {
            System.err.println("UserController Actualiza UsrxAgencia " + e);
        }
        if (operacion.equalsIgnoreCase("I"))
        {
            if(filtroNombre==null || filtroNombre.isEmpty())
                listarUs("f");
            else
                listarUs("x");
        }

    }

    
    private boolean verC=true;
    private String ver2C="2";
    
    //Mostrar u ocultar clave
    public void clave2C()
    {
        
        if(ver2C.equalsIgnoreCase("1") || ver2C.equalsIgnoreCase("2"))
        {
            ver2C="1";
        }else{
            ver2C="1";
        }
        if(verC)
        {
            verC=false;
        }else{
            verC=true;
        }
    }

 //Establecer u obtener clave   
    public boolean isVerC() {
        return verC;
    }

    public void setVerC(boolean verC) {
        this.verC = verC;
    }

    public String getVer2C() {
        return ver2C;
    }

    public void setVer2C(String ver2C) {
        this.ver2C = ver2C;
    }
    
    
    //Deja en blanco el número del IMEI del usuario
    public void limpiarIMEI() {
        objeto.setImei("");
        System.out.println("imei limpio " + objeto.getImei());
    }

    //Listar usuarios
    public void listarUs(String v) {
        System.out.println("Listando Usuarios");
        try {//se verifica que no sea un POSTBACK
            if (v.equalsIgnoreCase("f")) {
       
                String where = "";
                lstUsuarios = usuarioEJB.findAll();

            } else {
              
                lstUsuarios = usuarioEJB.listarUsuariosByNombreOrId(filtroNombre); 

            }

        } catch (Exception e) {
            System.err.println("error: " + e);
        }
    }

    //Haer una lista con los usuarios no registrados
    public void listarUsSinRegistrar(String v) {
        String where = "";
        lstUsuariosEmpleados = dao3.listarUsuariosNoRegistrados(where);

    }
    
    //Seleccionar usuario
    public void seleccionar(AdUsuarios obj, String operacion) {
       
        this.objeto = obj;
        this.operacion = operacion;
        this.selectedAgencias=null;
        if (operacion.equalsIgnoreCase("I")) {
            texto = "AGREGAR";
            objeto.setCorreo("@gmail.com");
            objeto.setIntentos((short) 0);
            if(objeto.getAgenciaid()!=null)
            {   
                if(selectedAgencias==null){
                    selectedAgencias=new Short[1];
                    selectedAgencias[0]=objeto.getAgenciaid();
                }

            }
        } else {
            this.objeto = usuarioEJB.find(obj.getUsuariotopaz()); 
            this.pasTempo = objeto.getContrasenia();
            texto = "MODIFICAR";
            try {

                lstAgenciasUsr = usuariosXagenciaEJB.findAgenciasByUsuarioGestor(objeto.getUsuariotopaz(),null);
            } catch (Exception e) {
                System.err.println("UsuariosController Error listar agencias del usuario " + e);
            }
            selectedAgencias = new Short[lstAgenciasUsr.size()];
            for (int i = 0; i < lstAgenciasUsr.size(); i++) {
         
                selectedAgencias[i] = lstAgenciasUsr.get(i).getAgenciaid();
            }

        }

        PrimeFaces.current().executeScript("PF('wdlUsuario').show();");
    }

    //Mostrar dialogo Lista de Empleados
    public void abrirDialogoListaEmpleados() {
        if (operacion.equalsIgnoreCase("I")) {
            System.out.println("Lista Empleados");
            listarUsSinRegistrar("");
            PrimeFaces.current().executeScript("PF('wdlListaUsuario').show();");
        }

    }

    //Mostrar dialogo Lista de agencias
    public void abrirDialogoListaAgencias() {

        PrimeFaces.current().executeScript("PF('wdlAgenciasUsuario').show();");

    }

    //Mostrar la cantidad de agencias seleccionadas
    public int seleccionadasLength() {
        if (selectedAgencias != null) {
            return selectedAgencias.length;
        }

        return 0;
    }

    //Verificar que usuario esta iniciando la sesión
    public UsuariosMovilController() {
        if (getUser().getUsuario().isEmpty()) {
            super.verificarSession();
            System.out.println("****"+getClass().getSimpleName()+" Usuario:*"+ getUser().getUsuario() + "* " + getUser().getNombre());
        }

    }

    //Llenar la lista de agencias y de usuarios
    @PostConstruct
    public void ini() {
        listarUs("f");
        try {
            lstTodasAgencias = agenciasEJB.findAll();
            System.out.println("lstTodasAgencias " + lstTodasAgencias.size());
           lstTiposUsr=tiposuserEJB.findAll();
           
        } catch (Exception e) {
            System.err.println("UsuariosController Error listar agencias,tipos del usuario " + e);
        }
    }

    //Establecer datos basicos del usuario nuevo en un objeto
    public void nuevo() {
        objeto = new AdUsuarios();
        objeto.setEmpleadoid(0);
        objeto.setNombre("");
        objeto.setCorreo("@gmail.com");
        objeto.setIntentos((short) 0);
        operacion = "I";

    }

//Obtener o establecer datos varios
    public UsuariosMovilDAO3 getDao() {
        return dao3;
    }

    public void setDao(UsuariosMovilDAO3 dao3) {
        this.dao3 = dao3;
    }

    public AdUsuarios getObjeto() {
        return objeto;
    }

    public void setObjeto(AdUsuarios objeto) {
        this.objeto = objeto;
    }

    public List<AdUsuarios> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(List<AdUsuarios> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
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

    public List<AdUsuarios> getLstUsuariosEmpleados() {
        return lstUsuariosEmpleados;
    }

    public void setLstUsuariosEmpleados(List<AdUsuarios> lstUsuariosEmpleados) {
        this.lstUsuariosEmpleados = lstUsuariosEmpleados;
    }

    public String getFiltroNombre() {
        return filtroNombre;
    }

    public void setFiltroNombre(String filtroNombre) {
        this.filtroNombre = filtroNombre;
    }

    public String getFiltroNombre2() {
        return filtroNombre2;
    }

    public void setFiltroNombre2(String filtroNombre2) {
        this.filtroNombre2 = filtroNombre2;
    }

    public Short[] getSelectedAgencias() {
        return selectedAgencias;
    }

    public void setSelectedAgencias(Short[] selectedAgencias) {
        this.selectedAgencias = selectedAgencias;
    }

    public List<AdAgencias> getLstTodasAgencias() {
        return lstTodasAgencias;
    }

    public void setLstTodasAgencias(List<AdAgencias> lstTodasAgencias) {
        this.lstTodasAgencias = lstTodasAgencias;
    }

    public List<AdAgencias> getLstAgenciasUsr() {
        return lstAgenciasUsr;
    }

    public void setLstAgenciasUsr(List<AdAgencias> lstAgenciasUsr) {
        this.lstAgenciasUsr = lstAgenciasUsr;
    }
    
    //Verificar si la página recargo
    private boolean isPosBack() {
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();
        return rpta;
    }

    public List<AdTiposusuarios> getLstTiposUsr() {
        return lstTiposUsr;
    }

    public void setLstTiposUsr(List<AdTiposusuarios> lstTiposUsr) {
        this.lstTiposUsr = lstTiposUsr;
    }

}
