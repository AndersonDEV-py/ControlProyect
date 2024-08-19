/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finsolhn.com.util;

/**
 *
 * @author DS010108
 */
import finsolhn.com.model.Archivo;
import finsolhn.com.model.ModelTipoArchivo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ds010106
 */
public interface Constantes {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");//("yyyy-MM-dd");
    SimpleDateFormat sdf_dmy = new SimpleDateFormat("dd/MM/yyyy");//("dd/MM/yyyy");
    SimpleDateFormat sdf_ham = new SimpleDateFormat("h:mm a");//("h:mm a");
    SimpleDateFormat sdf_dmy_ham = new SimpleDateFormat("dd/MM/yyyy h:mm a");//("dd/MM/yyyy");
   
    SimpleDateFormat sdf_ymd = new SimpleDateFormat("yyyy/MM/dd");//("yyyy-MM-dd");
   
    public static final String ruta_CONTEXTO="/ControlProyect/";
    
    public static final String ruta_logo="//resources//images";
    public static final String ruta_jasper="//jasper-agenda//";
    public static final String ruta_jasper_encuesta="//jasper-encuesta//";
    public static final String ruta_firmas="F:\\";
    //public static final String ruta_firmas="C:\\firmasAPP\\";
    
    public static final String ruta_raiz_doc="C:\\tempSGR\\";
    public static final String ruta_raiz_versiones="C:\\tempSGR\\Control_Versiones\\";
    public static final String folder_antes="Antes\\";
    public static final String folder_despues="Despues\\";
    
    public static String estado_sin_autortizar_area="Sin Autorizar Area";
    public static String estado_sin_autortizar="Sin Autorizar TI";
    public static String estado_autorizado_ti="Autorizado TI";
    //public static String estado_autorizado_area="Autorizado Area";
    public static String estado_en_espera="En Espera";
    public static String estado_en_desarrollo="En Desarrollo";
    public static String estado_completado="Completado";
    public static String estado_aceptado="Aceptado";
    public static String estado_finalizado="Finalizado";
    public static String estado_rechazado="Rechazado";
    
    public static String estado_en_dba="En Desarrollo DBA";
    public static String estado_completado_dba="Completado DBA";
    public static String estado_ini_permiso="Permiso Ini";
    public static String estado_fin_permiso="Permiso Fin";
    
    public static String usr_correo="helpdesksgr@finsolhn.com";
    public static String pass_correo="Finsol2015";
    
   
    ///Agenda Solicitude Estados
    final public static String ESTADO_SOL_NUEVA="NUEVA";
    final public static String ESTADO_SOL_COMPLETADA="COMPLETADA";
    final public static String ESTADO_SOL_DEVOLUCION="DEVOLUCION";
    final public static String ESTADO_SOL_RECHAZADA="RECHAZADA";
    final public static String ESTADO_SOL_FINALIZADA="FINALIZADA";
    
    final public static String RECHAZO_ERROR="Ingresada_Por_Error";
    final public static String RECHAZO_NOCUMPLE="No_Cumple_Requisitos";
    final public static String RECHAZO_NOREF="Malas_Referencias";
    final public static String RECHAZO_NOACEPTO="Cliente_No_Quiere_Continuar";
    final static String RECHAZO_JEFE="Rechazada_Por_Jefe";
    
    
    
    public List<ModelTipoArchivo> lstTipoArchivos=new ArrayList<ModelTipoArchivo>(){ {
        //add(new ModelTipoArchivo(0,"PROCEDIMIENTO",".SQL"));
        add(new ModelTipoArchivo(1,"OPERACION",".OPE;.RSP"));
        add(new ModelTipoArchivo(2,"FORMULARIO",".FRM"));
        add(new ModelTipoArchivo(3,"JASPER",".JASPER;.JRXML"));
        
    
        }   
    };
    public List<ModelTipoArchivo> lstTipoArchivosBase=new ArrayList<ModelTipoArchivo>(){ {
        add(new ModelTipoArchivo(1,"SCRIPT PROCEDIMIENTO","Procedimiento"));
        add(new ModelTipoArchivo(2,"SCRIPT TABLA","Tabla"));
           
        }   
    };
    
    List<Integer> miLista = new ArrayList<Integer>() {{ add(3); add(1); add(4); }};
    
    public String analista="5";public static String[] analista_array=analista.split(",");
    public String jefes_ti="1,2";public static String[] jefes_ti_array=jefes_ti.split(",");
    //public String adm_db="3,4";public static String[] adm_dba_array=adm_db.split(",");
    public String adm_db="54,68";public static String[] adm_dba_array=adm_db.split(",");
    
    public static String aprueban="2,1,301,303,302,1203,1201,601,5";
    public static String[] aprueban_array=aprueban.split(",");
    
    
    
    public static String arribaDeAsesor="1,11,20,60,63,63,67,102,53,19,91";
    public static String[] arribaDeAsesor_array=arribaDeAsesor.split(",");
    
    
    ///para correos
    public static String apr_operaciones="302,303";public static String[] apr_ope_array=apr_operaciones.split(",");
    public static String apr_ti="2";public static String[] apr_ti_array=apr_ti.split(",");
    public static String adm_sop="6,403";public static String[] adm_sop_array=adm_sop.split(",");
    
    
    public String A1_asunto="SGR - Notificacion  "+sdf.format(new Date());
    
    public static String M0_mensaje="Se Solicita de su Atencion en el Sistema de Requerimientos.\nNombre Requerimiento: <nombre_req>\nSolicitado Por: <solicita>";
    public static String M1_mensaje="Se Solicita de su Aprobacion en el Sistema de Requerimientos.\nNombre Requerimiento: <nombre_req>\nSolicitado Por: <solicita>";
    public static String M2_mensaje="Solicitud Aprobada.\n";
    public static String M3_mensaje="Solicitud Rechazada.\n";
    public static String M4_mensaje="Se te ha asignado un Requerimiento.\nNumero#<numero>\nSolicitado Por: <solicita>\nNombre Requerimiento: <nombre_req> ";
    public static String M5_mensaje="Se ha Recibido un caso de ayuda\nSolicitado Por: <solicita>\nNombre Requerimiento: <nombre_req> ";
    public static String M6_mensaje="<desa> ha Aceptado tu caso de ayuda\nNumero#: <numero>\nNombre Requerimiento: <nombre_req> ";
    public static String M7_mensaje="<desa> ha completado la solicitud\nNumero#: <numero>\nNombre Requerimiento: <nombre_req> ";
    public static String M8_mensaje="<desa> ha actualizado su Daily <fecha> ";
    public static String M9_mensaje="<desa> ha aprobado el permiso\nNumero#: <numero>\nNombre Requerimiento: <nombre_req> ";
    public static String M10_mensaje="<desa> ha finalizado el permiso\nNumero#: <numero>\nNombre Requerimiento: <nombre_req> ";
    public static String M11_mensaje="<desa> envio el script que solicitaste\nNumero#: <numero>\nNombre Requerimiento: <nombre_req> ";
    public static String M12_mensaje="<desa> envio el archivo que solicitaste\nNumero#: <numero>\nNombre Requerimiento: <nombre_req> ";
    public static String M13_mensaje="<jefe> ha reaccionado a tu Daily\n<fecha> ";
    
}

