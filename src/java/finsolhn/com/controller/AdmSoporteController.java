/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.controller;

import finsolhn.com.dao.AdmSoporteDAO;
import finsolhn.com.dao.AdmUsuariosDAO;
import finsolhn.com.dao.MasterPrintDAO;
import finsolhn.com.model.Aceptacion;
import finsolhn.com.model.Archivo;
import finsolhn.com.model.ModelTipoArchivo;
import finsolhn.com.model.MyFile;
import finsolhn.com.model.Requerimientos;
import finsolhn.com.util.Constantes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
//import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;


/**
 *
 * @author rcardona
 */
@Named("AdmSoporteController")
//@RequestScoped
//@ApplicationScoped
@ViewScoped
//@SessionScoped
//@RequestScoped 
public class AdmSoporteController extends DataGeneralController {

    //variables y objetos
    AdmSoporteDAO dao = new AdmSoporteDAO();

    private Archivo arch = new Archivo();
  
    private List<Archivo> lstArchivos;
    private List<ModelTipoArchivo> lstTipo = Constantes.lstTipoArchivos;

    
    //botones carga archivo versinado
    private boolean visibleBt1;
    private boolean visibleBt2;

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

    public List<ModelTipoArchivo> getLstTipo() {
        return lstTipo;
    }

    public void setLstTipo(List<ModelTipoArchivo> lstTipo) {
        this.lstTipo = lstTipo;
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
    
    
    
    
    public AdmSoporteController() {
        if (getUser().getUsuario().isEmpty()) {
            super.verificarSession();
            System.err.println("****HOLA  Usuario:*" + getUser().getUsuario() + "* " + getUser().getNombre());
        }
        listarArchivos();
       // listarReq("f");


    }
    

    public void seleccionado(Archivo obj)
    {
        arch=obj;
        listenerComboTipo();
        
        //RequestContext context = RequestContext.getCurrentInstance();
        if(arch.getTipo_proceso().equalsIgnoreCase("DESCARGA") && arch.getEstado()!=2)
        {
            PrimeFaces.current().executeScript("PF('wdlRespuesta').show();");
        }else{
            PrimeFaces.current().executeScript("PF('wdlRespuesta2').show();");
        }
       
    }
    
        

    public void agregarArchivoLista(String proceso) throws Exception {
        
        
        if(arch.getEstado()==4)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Esta solicitud esta denegada"));
            return;
        }
        
        
        
        if(proceso.equalsIgnoreCase("DENEGADO"))
        {
           if(arch.getComentario_nuevo().equalsIgnoreCase(""))
           {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Para degenar tienen que ingresar un comentario"));
                return;
           }
            
            
          String msj=Constantes.M3_mensaje+"\nNOMBRE SOLICITUD:"+arch.getNombre_archivo()+"\nRESPUESTA:"+getUser().getNombre();
           
            
          enviarCorreoDirecto(arch.getC_usuario_sol(), Constantes.A1_asunto, msj);
            
          arch.setEstado(4);//denegado
          arch.setVisibleBtIndicador3(false);
          arch.setVisibleBtIndicador4(false);
        }else if(proceso.equalsIgnoreCase("ENVIO")){
                        
           
             ///valida que no este en uso el archivo
            String sqlVerSiEsta="SELECT count(*) FROM [SGR].[dbo].[GP_ARCHIVOS_TOP] where estado not in(0,4) and nombre_archivo='"+arch.getNombre_archivo()+"' and ID_ARCHIVO<>'"+arch.getIdArchivo()+"'";
            String existe=dao.m.obtenerString(sqlVerSiEsta);
            if(!existe.trim().equalsIgnoreCase("0"))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No puede enviar este Archivo porque tiene un proceso Pendiente"));
                return;
            }
            
            if(arch.isVisibleBtIndicador3()==false || (arch.isVisibleBtIndicador4()==false && arch.getTipo_archivo().split(";").length>1)  )
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Tiene que cargar los archivos solicitados"));
                return;
            }
            String msj=Constantes.M12_mensaje.replace("<desa>", getUser().getNombre()).
                        replace("<numero>", arch.getIdArchivo()+"").
                        replace("<nombre_req>", arch.getNombre_archivo());
           
            
            enviarCorreoDirecto(arch.getC_usuario_sol(), Constantes.A1_asunto, msj);
            
            arch.setEstado(2);//
        }else if(proceso.equalsIgnoreCase("COMPLETADO"))
        {
            
            if(arch.isVisibleBtIndicador1()==false || (arch.isVisibleBtIndicador2()==false && arch.getTipo_archivo().split(";").length>1))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No puede completar sin recibir los archivos necesarios"));
                return;
            }
            
            
            String msj=Constantes.M7_mensaje.replace("<desa>", getUser().getNombre()).
                        replace("<numero>", arch.getIdArchivo()+"").
                        replace("<nombre_req>", arch.getNombre_archivo());
           
            
            enviarCorreoDirecto(arch.getC_usuario_sol(), Constantes.A1_asunto, msj);
            arch.setEstado(3);//
        }
        
        
        
        arch.setC_usuario_procesa(getUser().getUsuario());
        //  arch.setIdReq("" + objeto.getId());
        //arch.setTipo_proceso(proceso);
        if(arch.getComentario_nuevo().length()>1)
           arch.setComentario_nuevo("/R2/"+arch.getComentario_nuevo());
        //arch.setVisibleBtIndicador1(visibleBt1);
        
        
        dao.modificarArchivoTOP_DESCARGA(arch);
       
        /*if (!IDT.trim().equalsIgnoreCase("0")) {
            arch.setIdArchivo(Integer.parseInt(IDT));
            lstArchivos.add(arch);
        }*/

    }

    public void listarArchivos() {
        try {

            lstArchivos=dao.listarArchivosTOP();
        } catch (Exception e) {
            System.out.println("Bean listar archivos err " + e);
            
        }
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
            
           // arch.setArchivo1("");
           // arch.setArchivo2("");
        }else{
            visibleBt1=false;
            visibleBt2=false;
        }
        
        /*if(arch.getEstado()!=2)
        {
            arch.setVisibleBtIndicador3(false);
            arch.setVisibleBtIndicador4(false);
        }*/
        
        
        
        
    }

    public String encuentraNombre(File f,String ruta)
    {
        String nombre=f.getName();
             
        String soloNombre=nombre.substring(0, nombre.lastIndexOf('.'));
        String extension=nombre.substring(nombre.lastIndexOf('.'),nombre.length());
        
        String destino;
        if(f.exists())
        {
            
            boolean existe;
            int con=1;
            do{
               nombre=soloNombre+"_BAK"+con+extension;
               
               //soloNombre=nombre.substring(0, nombre.lastIndexOf('.'));
               //extension=nombre.substring(nombre.lastIndexOf('.'),nombre.length());
               //nombre=soloNombre+extension;
               
               destino=ruta+nombre;
               File archivoExiste = new File(destino);
               existe = archivoExiste.exists();
               con++; 
            }while(existe);
            

           
        }    
    
        
        
        return nombre;
    }
        
    public void handleFileUpload(FileUploadEvent event) throws FileNotFoundException, IOException {
        
        
        
        /*arch.file_master=event.getFile();*/ /*Comentado ´por Kevin Anderson*/
         
         
        if(!arch.file_master.getFileName().substring(0, arch.file_master.getFileName().lastIndexOf('.')).equalsIgnoreCase(arch.getNombre_archivo()))
        {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El nombre del archivo es diferente"));
             mensajeDialogoError("Error", "Comprobar el nombre del archivo");
             return;
        }
        String extension=arch.file_master.getFileName().substring(arch.file_master.getFileName().lastIndexOf('.'),arch.file_master.getFileName().length());
         
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
        System.out.println("COMPARA: "+arch.file_master.getFileName()+" | "+arch.getArchivo2());
        if(extension.equalsIgnoreCase(arch.getArchivo2()) )
        {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El archivo duplicado"));
             mensajeDialogoError("Error", "Comprobar si esta duplicado");
             return;
        }
         
         
        if (arch.file_master != null) {
         
            String raiz=Constantes.ruta_raiz_versiones;
            String folder=Constantes.folder_antes;
            String nombreTemporal=arch.file_master.getFileName();
            String nombreFinal=arch.file_master.getFileName();
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
            
            InputStream in = arch.file_master.getInputStream();
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
            arch.setArchivo1(extension);
            arch.setVisibleBtIndicador3(true);
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
        
        
        
         /*arch.file_master=event.getFile();*/
         
         
        if(!arch.file_master.getFileName().substring(0, arch.file_master.getFileName().lastIndexOf('.')).equalsIgnoreCase(arch.getNombre_archivo()))
        {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El nombre del archivo es diferente"));
             mensajeDialogoError("Error", "Comprobar el nombre del archivo");
             return;
        }
        String extension=arch.file_master.getFileName().substring(arch.file_master.getFileName().lastIndexOf('.'),arch.file_master.getFileName().length());
         
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
        System.out.println("COMPARA2: "+arch.file_master.getFileName()+" | "+arch.getArchivo1());
        if(extension.equalsIgnoreCase(arch.getArchivo1()) )
        {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El archivo duplicado"));
             mensajeDialogoError("Error", "Comprobar si esta duplicado");
             return;
        }
         
         
         
         
        if (arch.file_master != null) {
         
            String raiz=Constantes.ruta_raiz_versiones;
            String folder=Constantes.folder_antes;
            String nombreTemporal=arch.file_master.getFileName();
            String nombreFinal=arch.file_master.getFileName();
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
            
            InputStream in = arch.file_master.getInputStream();
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
            arch.setArchivo2(extension);
            arch.setVisibleBtIndicador4(true);
             
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
    
    
    
    private boolean isPosBack() {
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();
        return rpta;
    }

}
