/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsolhn.com.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ds010106
 */
@Entity
@Table(name = "ad_conozca_cliente", catalog = "ad_testing", schema = "agenda_digital", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"liteuid"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdConozcaCliente.findAll", query = "SELECT a FROM AdConozcaCliente a"),
    @NamedQuery(name = "AdConozcaCliente.findByLiteuid", query = "SELECT a FROM AdConozcaCliente a WHERE a.liteuid = :liteuid"),
    @NamedQuery(name = "AdConozcaCliente.findByFecharegistro", query = "SELECT a FROM AdConozcaCliente a WHERE a.fecharegistro = :fecharegistro"),
    @NamedQuery(name = "AdConozcaCliente.findByUsuariotopaz", query = "SELECT a FROM AdConozcaCliente a WHERE a.usuariotopaz = :usuariotopaz"),
    @NamedQuery(name = "AdConozcaCliente.findByIdcliente", query = "SELECT a FROM AdConozcaCliente a WHERE a.idcliente = :idcliente"),
    @NamedQuery(name = "AdConozcaCliente.findByTipocliente", query = "SELECT a FROM AdConozcaCliente a WHERE a.tipocliente = :tipocliente"),
    @NamedQuery(name = "AdConozcaCliente.findByTipodocumento", query = "SELECT a FROM AdConozcaCliente a WHERE a.tipodocumento = :tipodocumento"),
    @NamedQuery(name = "AdConozcaCliente.findByIdentidad", query = "SELECT a FROM AdConozcaCliente a WHERE a.identidad = :identidad"),
    @NamedQuery(name = "AdConozcaCliente.findByFechavencimiento", query = "SELECT a FROM AdConozcaCliente a WHERE a.fechavencimiento = :fechavencimiento"),
    @NamedQuery(name = "AdConozcaCliente.findByTipodocumentojuridico", query = "SELECT a FROM AdConozcaCliente a WHERE a.tipodocumentojuridico = :tipodocumentojuridico"),
    @NamedQuery(name = "AdConozcaCliente.findByNrodocumentojuridico", query = "SELECT a FROM AdConozcaCliente a WHERE a.nrodocumentojuridico = :nrodocumentojuridico"),
    @NamedQuery(name = "AdConozcaCliente.findByRazonsocialjuridico", query = "SELECT a FROM AdConozcaCliente a WHERE a.razonsocialjuridico = :razonsocialjuridico"),
    @NamedQuery(name = "AdConozcaCliente.findByTiemporelacioninstitucion", query = "SELECT a FROM AdConozcaCliente a WHERE a.tiemporelacioninstitucion = :tiemporelacioninstitucion"),
    @NamedQuery(name = "AdConozcaCliente.findByParterelacionada", query = "SELECT a FROM AdConozcaCliente a WHERE a.parterelacionada = :parterelacionada"),
    @NamedQuery(name = "AdConozcaCliente.findByTitularrepresentante", query = "SELECT a FROM AdConozcaCliente a WHERE a.titularrepresentante = :titularrepresentante"),
    @NamedQuery(name = "AdConozcaCliente.findByFirmaautorizada", query = "SELECT a FROM AdConozcaCliente a WHERE a.firmaautorizada = :firmaautorizada"),
    @NamedQuery(name = "AdConozcaCliente.findByProcedenciacliente", query = "SELECT a FROM AdConozcaCliente a WHERE a.procedenciacliente = :procedenciacliente"),
    @NamedQuery(name = "AdConozcaCliente.findByNombre1", query = "SELECT a FROM AdConozcaCliente a WHERE a.nombre1 = :nombre1"),
    @NamedQuery(name = "AdConozcaCliente.findByNombre2", query = "SELECT a FROM AdConozcaCliente a WHERE a.nombre2 = :nombre2"),
    @NamedQuery(name = "AdConozcaCliente.findByApellido1", query = "SELECT a FROM AdConozcaCliente a WHERE a.apellido1 = :apellido1"),
    @NamedQuery(name = "AdConozcaCliente.findByApellido2", query = "SELECT a FROM AdConozcaCliente a WHERE a.apellido2 = :apellido2"),
    @NamedQuery(name = "AdConozcaCliente.findByFechanacimiento", query = "SELECT a FROM AdConozcaCliente a WHERE a.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "AdConozcaCliente.findByGeneros", query = "SELECT a FROM AdConozcaCliente a WHERE a.generos = :generos"),
    @NamedQuery(name = "AdConozcaCliente.findByNiveleducativo", query = "SELECT a FROM AdConozcaCliente a WHERE a.niveleducativo = :niveleducativo"),
    @NamedQuery(name = "AdConozcaCliente.findByProfesionofisio", query = "SELECT a FROM AdConozcaCliente a WHERE a.profesionofisio = :profesionofisio"),
    @NamedQuery(name = "AdConozcaCliente.findByNacionalidad", query = "SELECT a FROM AdConozcaCliente a WHERE a.nacionalidad = :nacionalidad"),
    @NamedQuery(name = "AdConozcaCliente.findByDepartamentonac", query = "SELECT a FROM AdConozcaCliente a WHERE a.departamentonac = :departamentonac"),
    @NamedQuery(name = "AdConozcaCliente.findByMunicipionac", query = "SELECT a FROM AdConozcaCliente a WHERE a.municipionac = :municipionac"),
    @NamedQuery(name = "AdConozcaCliente.findByEstadocivil", query = "SELECT a FROM AdConozcaCliente a WHERE a.estadocivil = :estadocivil"),
    @NamedQuery(name = "AdConozcaCliente.findByPersonasdependen", query = "SELECT a FROM AdConozcaCliente a WHERE a.personasdependen = :personasdependen"),
    @NamedQuery(name = "AdConozcaCliente.findByDireccionpais", query = "SELECT a FROM AdConozcaCliente a WHERE a.direccionpais = :direccionpais"),
    @NamedQuery(name = "AdConozcaCliente.findByDirecciondepartamento", query = "SELECT a FROM AdConozcaCliente a WHERE a.direcciondepartamento = :direcciondepartamento"),
    @NamedQuery(name = "AdConozcaCliente.findByDireccionmunicipio", query = "SELECT a FROM AdConozcaCliente a WHERE a.direccionmunicipio = :direccionmunicipio"),
    @NamedQuery(name = "AdConozcaCliente.findByDireccionsector", query = "SELECT a FROM AdConozcaCliente a WHERE a.direccionsector = :direccionsector"),
    @NamedQuery(name = "AdConozcaCliente.findByDireccionbarrio", query = "SELECT a FROM AdConozcaCliente a WHERE a.direccionbarrio = :direccionbarrio"),
    @NamedQuery(name = "AdConozcaCliente.findByDireccionavenida", query = "SELECT a FROM AdConozcaCliente a WHERE a.direccionavenida = :direccionavenida"),
    @NamedQuery(name = "AdConozcaCliente.findByDireccioncalle", query = "SELECT a FROM AdConozcaCliente a WHERE a.direccioncalle = :direccioncalle"),
    @NamedQuery(name = "AdConozcaCliente.findByDireccionbloque", query = "SELECT a FROM AdConozcaCliente a WHERE a.direccionbloque = :direccionbloque"),
    @NamedQuery(name = "AdConozcaCliente.findByDireccioncasano", query = "SELECT a FROM AdConozcaCliente a WHERE a.direccioncasano = :direccioncasano"),
    @NamedQuery(name = "AdConozcaCliente.findByDireccioncodigopostal", query = "SELECT a FROM AdConozcaCliente a WHERE a.direccioncodigopostal = :direccioncodigopostal"),
    @NamedQuery(name = "AdConozcaCliente.findByPuntoreferencia", query = "SELECT a FROM AdConozcaCliente a WHERE a.puntoreferencia = :puntoreferencia"),
    @NamedQuery(name = "AdConozcaCliente.findByDirecciontelefonofijo", query = "SELECT a FROM AdConozcaCliente a WHERE a.direcciontelefonofijo = :direcciontelefonofijo"),
    @NamedQuery(name = "AdConozcaCliente.findByDirecciontelefonomovil", query = "SELECT a FROM AdConozcaCliente a WHERE a.direcciontelefonomovil = :direcciontelefonomovil"),
    @NamedQuery(name = "AdConozcaCliente.findByDireccioncorreo", query = "SELECT a FROM AdConozcaCliente a WHERE a.direccioncorreo = :direccioncorreo"),
    @NamedQuery(name = "AdConozcaCliente.findByDirecciontipovivienda", query = "SELECT a FROM AdConozcaCliente a WHERE a.direcciontipovivienda = :direcciontipovivienda"),
    @NamedQuery(name = "AdConozcaCliente.findByDirecciontiempohabitacion", query = "SELECT a FROM AdConozcaCliente a WHERE a.direcciontiempohabitacion = :direcciontiempohabitacion"),
    @NamedQuery(name = "AdConozcaCliente.findByDirecciontipozona", query = "SELECT a FROM AdConozcaCliente a WHERE a.direcciontipozona = :direcciontipozona"),
    @NamedQuery(name = "AdConozcaCliente.findByArrendatarionombre", query = "SELECT a FROM AdConozcaCliente a WHERE a.arrendatarionombre = :arrendatarionombre"),
    @NamedQuery(name = "AdConozcaCliente.findByArrendatariotelefonofijo", query = "SELECT a FROM AdConozcaCliente a WHERE a.arrendatariotelefonofijo = :arrendatariotelefonofijo"),
    @NamedQuery(name = "AdConozcaCliente.findByArrendatariotelefonomovil", query = "SELECT a FROM AdConozcaCliente a WHERE a.arrendatariotelefonomovil = :arrendatariotelefonomovil"),
    @NamedQuery(name = "AdConozcaCliente.findByGpslatitud", query = "SELECT a FROM AdConozcaCliente a WHERE a.gpslatitud = :gpslatitud"),
    @NamedQuery(name = "AdConozcaCliente.findByGpslongitud", query = "SELECT a FROM AdConozcaCliente a WHERE a.gpslongitud = :gpslongitud"),
    @NamedQuery(name = "AdConozcaCliente.findByGpsfecha", query = "SELECT a FROM AdConozcaCliente a WHERE a.gpsfecha = :gpsfecha"),
    @NamedQuery(name = "AdConozcaCliente.findByConyuguenombre1", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyuguenombre1 = :conyuguenombre1"),
    @NamedQuery(name = "AdConozcaCliente.findByConyuguenombre2", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyuguenombre2 = :conyuguenombre2"),
    @NamedQuery(name = "AdConozcaCliente.findByConyugueapellido1", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyugueapellido1 = :conyugueapellido1"),
    @NamedQuery(name = "AdConozcaCliente.findByConyugueapellido2", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyugueapellido2 = :conyugueapellido2"),
    @NamedQuery(name = "AdConozcaCliente.findByConyugueniveleducativo", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyugueniveleducativo = :conyugueniveleducativo"),
    @NamedQuery(name = "AdConozcaCliente.findByConyuguefechanacimiento", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyuguefechanacimiento = :conyuguefechanacimiento"),
    @NamedQuery(name = "AdConozcaCliente.findByConyuguetiempounidos", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyuguetiempounidos = :conyuguetiempounidos"),
    @NamedQuery(name = "AdConozcaCliente.findByConyuguetelefono", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyuguetelefono = :conyuguetelefono"),
    @NamedQuery(name = "AdConozcaCliente.findByConyugueactividadeconomica", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyugueactividadeconomica = :conyugueactividadeconomica"),
    @NamedQuery(name = "AdConozcaCliente.findByConyuguenegociodistinto", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyuguenegociodistinto = :conyuguenegociodistinto"),
    @NamedQuery(name = "AdConozcaCliente.findByConyuguenombrenegocio", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyuguenombrenegocio = :conyuguenombrenegocio"),
    @NamedQuery(name = "AdConozcaCliente.findByConyugueidentidad", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyugueidentidad = :conyugueidentidad"),
    @NamedQuery(name = "AdConozcaCliente.findByConyuguetipodocumento", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyuguetipodocumento = :conyuguetipodocumento"),
    @NamedQuery(name = "AdConozcaCliente.findByConyugueescliente", query = "SELECT a FROM AdConozcaCliente a WHERE a.conyugueescliente = :conyugueescliente"),
    @NamedQuery(name = "AdConozcaCliente.findByRcPrestamo", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcPrestamo = :rcPrestamo"),
    @NamedQuery(name = "AdConozcaCliente.findByRcMontoesperado", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcMontoesperado = :rcMontoesperado"),
    @NamedQuery(name = "AdConozcaCliente.findByRcCuotasabono", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcCuotasabono = :rcCuotasabono"),
    @NamedQuery(name = "AdConozcaCliente.findByRcCuentasahorro", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcCuentasahorro = :rcCuentasahorro"),
    @NamedQuery(name = "AdConozcaCliente.findByRcDepositos", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcDepositos = :rcDepositos"),
    @NamedQuery(name = "AdConozcaCliente.findByRcRetiros", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcRetiros = :rcRetiros"),
    @NamedQuery(name = "AdConozcaCliente.findByRcCertificadoaplazo", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcCertificadoaplazo = :rcCertificadoaplazo"),
    @NamedQuery(name = "AdConozcaCliente.findByRcCertificadomontoesperado", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcCertificadomontoesperado = :rcCertificadomontoesperado"),
    @NamedQuery(name = "AdConozcaCliente.findByRcServicios", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcServicios = :rcServicios"),
    @NamedQuery(name = "AdConozcaCliente.findByRcMontoremesas", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcMontoremesas = :rcMontoremesas"),
    @NamedQuery(name = "AdConozcaCliente.findByRcMontogiros", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcMontogiros = :rcMontogiros"),
    @NamedQuery(name = "AdConozcaCliente.findByRcOperacionesinternacionales", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcOperacionesinternacionales = :rcOperacionesinternacionales"),
    @NamedQuery(name = "AdConozcaCliente.findByRcOtrosmontos", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcOtrosmontos = :rcOtrosmontos"),
    @NamedQuery(name = "AdConozcaCliente.findByRcInstrumentosmonetarios", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcInstrumentosmonetarios = :rcInstrumentosmonetarios"),
    @NamedQuery(name = "AdConozcaCliente.findByRcInstrumentosmonetariosefectivo", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcInstrumentosmonetariosefectivo = :rcInstrumentosmonetariosefectivo"),
    @NamedQuery(name = "AdConozcaCliente.findByRcInstrumentosmonetarioscheques", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcInstrumentosmonetarioscheques = :rcInstrumentosmonetarioscheques"),
    @NamedQuery(name = "AdConozcaCliente.findByRcInstrumentosmonetariostransferencias", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcInstrumentosmonetariostransferencias = :rcInstrumentosmonetariostransferencias"),
    @NamedQuery(name = "AdConozcaCliente.findByRcInstrumentosmonetariosmixto", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcInstrumentosmonetariosmixto = :rcInstrumentosmonetariosmixto"),
    @NamedQuery(name = "AdConozcaCliente.findByRcInstrumentosmonetariosremesas", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcInstrumentosmonetariosremesas = :rcInstrumentosmonetariosremesas"),
    @NamedQuery(name = "AdConozcaCliente.findByRcInstrumentosmonetariosgiros", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcInstrumentosmonetariosgiros = :rcInstrumentosmonetariosgiros"),
    @NamedQuery(name = "AdConozcaCliente.findByRcCanales", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcCanales = :rcCanales"),
    @NamedQuery(name = "AdConozcaCliente.findByRcCanalesagenciafinsol", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcCanalesagenciafinsol = :rcCanalesagenciafinsol"),
    @NamedQuery(name = "AdConozcaCliente.findByRcCanalesagenciabancaria", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcCanalesagenciabancaria = :rcCanalesagenciabancaria"),
    @NamedQuery(name = "AdConozcaCliente.findByRcCanalesagencianobancaria", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcCanalesagencianobancaria = :rcCanalesagencianobancaria"),
    @NamedQuery(name = "AdConozcaCliente.findByRcCanalesbancos", query = "SELECT a FROM AdConozcaCliente a WHERE a.rcCanalesbancos = :rcCanalesbancos"),
    @NamedQuery(name = "AdConozcaCliente.findByNegnombrenegocio", query = "SELECT a FROM AdConozcaCliente a WHERE a.negnombrenegocio = :negnombrenegocio"),
    @NamedQuery(name = "AdConozcaCliente.findByNegtiponegocio", query = "SELECT a FROM AdConozcaCliente a WHERE a.negtiponegocio = :negtiponegocio"),
    @NamedQuery(name = "AdConozcaCliente.findByNegsectornegocio", query = "SELECT a FROM AdConozcaCliente a WHERE a.negsectornegocio = :negsectornegocio"),
    @NamedQuery(name = "AdConozcaCliente.findByNegactividadprincipal", query = "SELECT a FROM AdConozcaCliente a WHERE a.negactividadprincipal = :negactividadprincipal"),
    @NamedQuery(name = "AdConozcaCliente.findByNegcategoriaactividad", query = "SELECT a FROM AdConozcaCliente a WHERE a.negcategoriaactividad = :negcategoriaactividad"),
    @NamedQuery(name = "AdConozcaCliente.findByNegrtn", query = "SELECT a FROM AdConozcaCliente a WHERE a.negrtn = :negrtn"),
    @NamedQuery(name = "AdConozcaCliente.findByNegactividadinterna", query = "SELECT a FROM AdConozcaCliente a WHERE a.negactividadinterna = :negactividadinterna"),
    @NamedQuery(name = "AdConozcaCliente.findByNegempleados", query = "SELECT a FROM AdConozcaCliente a WHERE a.negempleados = :negempleados"),
    @NamedQuery(name = "AdConozcaCliente.findByNegclasificacionempresa", query = "SELECT a FROM AdConozcaCliente a WHERE a.negclasificacionempresa = :negclasificacionempresa"),
    @NamedQuery(name = "AdConozcaCliente.findByNegsectorinterno", query = "SELECT a FROM AdConozcaCliente a WHERE a.negsectorinterno = :negsectorinterno"),
    @NamedQuery(name = "AdConozcaCliente.findByNegtelefononegocio", query = "SELECT a FROM AdConozcaCliente a WHERE a.negtelefononegocio = :negtelefononegocio"),
    @NamedQuery(name = "AdConozcaCliente.findByNeganosoperacion", query = "SELECT a FROM AdConozcaCliente a WHERE a.neganosoperacion = :neganosoperacion"),
    @NamedQuery(name = "AdConozcaCliente.findByNegfechainioperacion", query = "SELECT a FROM AdConozcaCliente a WHERE a.negfechainioperacion = :negfechainioperacion"),
    @NamedQuery(name = "AdConozcaCliente.findByNegexperienciaenactividad", query = "SELECT a FROM AdConozcaCliente a WHERE a.negexperienciaenactividad = :negexperienciaenactividad"),
    @NamedQuery(name = "AdConozcaCliente.findByNegcapitalactual", query = "SELECT a FROM AdConozcaCliente a WHERE a.negcapitalactual = :negcapitalactual"),
    @NamedQuery(name = "AdConozcaCliente.findByNegpermisooperacion", query = "SELECT a FROM AdConozcaCliente a WHERE a.negpermisooperacion = :negpermisooperacion"),
    @NamedQuery(name = "AdConozcaCliente.findByNegempleadosmujertes", query = "SELECT a FROM AdConozcaCliente a WHERE a.negempleadosmujertes = :negempleadosmujertes"),
    @NamedQuery(name = "AdConozcaCliente.findByNegempleadoshombres", query = "SELECT a FROM AdConozcaCliente a WHERE a.negempleadoshombres = :negempleadoshombres"),
    @NamedQuery(name = "AdConozcaCliente.findByNegventasmensuales", query = "SELECT a FROM AdConozcaCliente a WHERE a.negventasmensuales = :negventasmensuales"),
    @NamedQuery(name = "AdConozcaCliente.findByNegventasanuales", query = "SELECT a FROM AdConozcaCliente a WHERE a.negventasanuales = :negventasanuales"),
    @NamedQuery(name = "AdConozcaCliente.findByNegrangoingresos", query = "SELECT a FROM AdConozcaCliente a WHERE a.negrangoingresos = :negrangoingresos"),
    @NamedQuery(name = "AdConozcaCliente.findByNegsuseciongerencial", query = "SELECT a FROM AdConozcaCliente a WHERE a.negsuseciongerencial = :negsuseciongerencial"),
    @NamedQuery(name = "AdConozcaCliente.findByNegafluenciaclientes", query = "SELECT a FROM AdConozcaCliente a WHERE a.negafluenciaclientes = :negafluenciaclientes"),
    @NamedQuery(name = "AdConozcaCliente.findByNegterminoscompra", query = "SELECT a FROM AdConozcaCliente a WHERE a.negterminoscompra = :negterminoscompra"),
    @NamedQuery(name = "AdConozcaCliente.findByNegterminosventa", query = "SELECT a FROM AdConozcaCliente a WHERE a.negterminosventa = :negterminosventa"),
    @NamedQuery(name = "AdConozcaCliente.findByNegotrosingresosdetalle", query = "SELECT a FROM AdConozcaCliente a WHERE a.negotrosingresosdetalle = :negotrosingresosdetalle"),
    @NamedQuery(name = "AdConozcaCliente.findByNegotosingresosmonto", query = "SELECT a FROM AdConozcaCliente a WHERE a.negotosingresosmonto = :negotosingresosmonto"),
    @NamedQuery(name = "AdConozcaCliente.findByNegcoincidedireccionnegociocondomicilio", query = "SELECT a FROM AdConozcaCliente a WHERE a.negcoincidedireccionnegociocondomicilio = :negcoincidedireccionnegociocondomicilio"),
    @NamedQuery(name = "AdConozcaCliente.findByNegpais", query = "SELECT a FROM AdConozcaCliente a WHERE a.negpais = :negpais"),
    @NamedQuery(name = "AdConozcaCliente.findByNegdepartamento", query = "SELECT a FROM AdConozcaCliente a WHERE a.negdepartamento = :negdepartamento"),
    @NamedQuery(name = "AdConozcaCliente.findByNegmunicipio", query = "SELECT a FROM AdConozcaCliente a WHERE a.negmunicipio = :negmunicipio"),
    @NamedQuery(name = "AdConozcaCliente.findByNegsector", query = "SELECT a FROM AdConozcaCliente a WHERE a.negsector = :negsector"),
    @NamedQuery(name = "AdConozcaCliente.findByNegbarrio", query = "SELECT a FROM AdConozcaCliente a WHERE a.negbarrio = :negbarrio"),
    @NamedQuery(name = "AdConozcaCliente.findByNegetapa", query = "SELECT a FROM AdConozcaCliente a WHERE a.negetapa = :negetapa"),
    @NamedQuery(name = "AdConozcaCliente.findByNegavenida", query = "SELECT a FROM AdConozcaCliente a WHERE a.negavenida = :negavenida"),
    @NamedQuery(name = "AdConozcaCliente.findByNegcalle", query = "SELECT a FROM AdConozcaCliente a WHERE a.negcalle = :negcalle"),
    @NamedQuery(name = "AdConozcaCliente.findByNegbloque", query = "SELECT a FROM AdConozcaCliente a WHERE a.negbloque = :negbloque"),
    @NamedQuery(name = "AdConozcaCliente.findByNegnumerocasa", query = "SELECT a FROM AdConozcaCliente a WHERE a.negnumerocasa = :negnumerocasa"),
    @NamedQuery(name = "AdConozcaCliente.findByNegtipozona", query = "SELECT a FROM AdConozcaCliente a WHERE a.negtipozona = :negtipozona"),
    @NamedQuery(name = "AdConozcaCliente.findByNeglatitud", query = "SELECT a FROM AdConozcaCliente a WHERE a.neglatitud = :neglatitud"),
    @NamedQuery(name = "AdConozcaCliente.findByNeglongitud", query = "SELECT a FROM AdConozcaCliente a WHERE a.neglongitud = :neglongitud"),
    @NamedQuery(name = "AdConozcaCliente.findByNegfechacapturaubicacion", query = "SELECT a FROM AdConozcaCliente a WHERE a.negfechacapturaubicacion = :negfechacapturaubicacion"),
    @NamedQuery(name = "AdConozcaCliente.findByNegpuntoreferencia", query = "SELECT a FROM AdConozcaCliente a WHERE a.negpuntoreferencia = :negpuntoreferencia"),
    @NamedQuery(name = "AdConozcaCliente.findByNegtipolocal", query = "SELECT a FROM AdConozcaCliente a WHERE a.negtipolocal = :negtipolocal"),
    @NamedQuery(name = "AdConozcaCliente.findByNegnombrearrendador", query = "SELECT a FROM AdConozcaCliente a WHERE a.negnombrearrendador = :negnombrearrendador"),
    @NamedQuery(name = "AdConozcaCliente.findByNegtelefonoarrendador", query = "SELECT a FROM AdConozcaCliente a WHERE a.negtelefonoarrendador = :negtelefonoarrendador"),
    @NamedQuery(name = "AdConozcaCliente.findByNegproveedores", query = "SELECT a FROM AdConozcaCliente a WHERE a.negproveedores = :negproveedores"),
    @NamedQuery(name = "AdConozcaCliente.findByEncfuncionespublicas", query = "SELECT a FROM AdConozcaCliente a WHERE a.encfuncionespublicas = :encfuncionespublicas"),
    @NamedQuery(name = "AdConozcaCliente.findByEncconyugueep", query = "SELECT a FROM AdConozcaCliente a WHERE a.encconyugueep = :encconyugueep"),
    @NamedQuery(name = "AdConozcaCliente.findByEncpatrimoniootrocj", query = "SELECT a FROM AdConozcaCliente a WHERE a.encpatrimoniootrocj = :encpatrimoniootrocj"),
    @NamedQuery(name = "AdConozcaCliente.findByEncnombrepatrimoniootrocj", query = "SELECT a FROM AdConozcaCliente a WHERE a.encnombrepatrimoniootrocj = :encnombrepatrimoniootrocj"),
    @NamedQuery(name = "AdConozcaCliente.findByEncdoblenacionalidad", query = "SELECT a FROM AdConozcaCliente a WHERE a.encdoblenacionalidad = :encdoblenacionalidad"),
    @NamedQuery(name = "AdConozcaCliente.findByEncresidenciaus", query = "SELECT a FROM AdConozcaCliente a WHERE a.encresidenciaus = :encresidenciaus"),
    @NamedQuery(name = "AdConozcaCliente.findByEncdiretelus", query = "SELECT a FROM AdConozcaCliente a WHERE a.encdiretelus = :encdiretelus"),
    @NamedQuery(name = "AdConozcaCliente.findByEncpasaportegreencard", query = "SELECT a FROM AdConozcaCliente a WHERE a.encpasaportegreencard = :encpasaportegreencard"),
    @NamedQuery(name = "AdConozcaCliente.findByEncnacidous", query = "SELECT a FROM AdConozcaCliente a WHERE a.encnacidous = :encnacidous"),
    @NamedQuery(name = "AdConozcaCliente.findByEncreligion", query = "SELECT a FROM AdConozcaCliente a WHERE a.encreligion = :encreligion"),
    @NamedQuery(name = "AdConozcaCliente.findByEncreligionotros", query = "SELECT a FROM AdConozcaCliente a WHERE a.encreligionotros = :encreligionotros"),
    @NamedQuery(name = "AdConozcaCliente.findByEstadoCrud", query = "SELECT a FROM AdConozcaCliente a WHERE a.estadoCrud = :estadoCrud"),
    @NamedQuery(name = "AdConozcaCliente.findByFechafirmada", query = "SELECT a FROM AdConozcaCliente a WHERE a.fechafirmada = :fechafirmada")})
public class AdConozcaCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "liteuid", nullable = false, length = 60)
    private String liteuid;
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @Size(max = 30)
    @Column(name = "usuariotopaz", length = 30)
    private String usuariotopaz;
    @Column(name = "idcliente")
    private Long idcliente;
    @Size(max = 20)
    @Column(name = "tipocliente", length = 20)
    private String tipocliente;
    @Size(max = 20)
    @Column(name = "tipodocumento", length = 20)
    private String tipodocumento;
    @Size(max = 30)
    @Column(name = "identidad", length = 30)
    private String identidad;
    @Column(name = "fechavencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechavencimiento;
    @Size(max = 20)
    @Column(name = "tipodocumentojuridico", length = 20)
    private String tipodocumentojuridico;
    @Size(max = 30)
    @Column(name = "nrodocumentojuridico", length = 30)
    private String nrodocumentojuridico;
    @Size(max = 100)
    @Column(name = "razonsocialjuridico", length = 100)
    private String razonsocialjuridico;
    @Size(max = 20)
    @Column(name = "tiemporelacioninstitucion", length = 20)
    private String tiemporelacioninstitucion;
    @Size(max = 20)
    @Column(name = "parterelacionada", length = 20)
    private String parterelacionada;
    @Size(max = 20)
    @Column(name = "titularrepresentante", length = 20)
    private String titularrepresentante;
    @Size(max = 10)
    @Column(name = "firmaautorizada", length = 10)
    private String firmaautorizada;
    @Size(max = 50)
    @Column(name = "procedenciacliente", length = 50)
    private String procedenciacliente;
    @Size(max = 50)
    @Column(name = "nombre1", length = 50)
    private String nombre1;
    @Size(max = 50)
    @Column(name = "nombre2", length = 50)
    private String nombre2;
    @Size(max = 50)
    @Column(name = "apellido1", length = 50)
    private String apellido1;
    @Size(max = 50)
    @Column(name = "apellido2", length = 50)
    private String apellido2;
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Size(max = 20)
    @Column(name = "generos", length = 20)
    private String generos;
    @Size(max = 20)
    @Column(name = "niveleducativo", length = 20)
    private String niveleducativo;
    @Size(max = 20)
    @Column(name = "profesionofisio", length = 20)
    private String profesionofisio;
    @Size(max = 20)
    @Column(name = "nacionalidad", length = 20)
    private String nacionalidad;
    @Size(max = 20)
    @Column(name = "departamentonac", length = 20)
    private String departamentonac;
    @Size(max = 20)
    @Column(name = "municipionac", length = 20)
    private String municipionac;
    @Size(max = 20)
    @Column(name = "estadocivil", length = 20)
    private String estadocivil;
    @Size(max = 20)
    @Column(name = "personasdependen", length = 20)
    private String personasdependen;
    @Size(max = 20)
    @Column(name = "direccionpais", length = 20)
    private String direccionpais;
    @Size(max = 20)
    @Column(name = "direcciondepartamento", length = 20)
    private String direcciondepartamento;
    @Size(max = 20)
    @Column(name = "direccionmunicipio", length = 20)
    private String direccionmunicipio;
    @Size(max = 20)
    @Column(name = "direccionsector", length = 20)
    private String direccionsector;
    @Size(max = 20)
    @Column(name = "direccionbarrio", length = 20)
    private String direccionbarrio;
    @Size(max = 30)
    @Column(name = "direccionavenida", length = 30)
    private String direccionavenida;
    @Size(max = 30)
    @Column(name = "direccioncalle", length = 30)
    private String direccioncalle;
    @Size(max = 30)
    @Column(name = "direccionbloque", length = 30)
    private String direccionbloque;
    @Size(max = 30)
    @Column(name = "direccioncasano", length = 30)
    private String direccioncasano;
    @Size(max = 20)
    @Column(name = "direccioncodigopostal", length = 20)
    private String direccioncodigopostal;
    @Size(max = 100)
    @Column(name = "puntoreferencia", length = 100)
    private String puntoreferencia;
    @Size(max = 20)
    @Column(name = "direcciontelefonofijo", length = 20)
    private String direcciontelefonofijo;
    @Size(max = 20)
    @Column(name = "direcciontelefonomovil", length = 20)
    private String direcciontelefonomovil;
    @Size(max = 50)
    @Column(name = "direccioncorreo", length = 50)
    private String direccioncorreo;
    @Size(max = 20)
    @Column(name = "direcciontipovivienda", length = 20)
    private String direcciontipovivienda;
    @Size(max = 20)
    @Column(name = "direcciontiempohabitacion", length = 20)
    private String direcciontiempohabitacion;
    @Size(max = 20)
    @Column(name = "direcciontipozona", length = 20)
    private String direcciontipozona;
    @Size(max = 100)
    @Column(name = "arrendatarionombre", length = 100)
    private String arrendatarionombre;
    @Size(max = 20)
    @Column(name = "arrendatariotelefonofijo", length = 20)
    private String arrendatariotelefonofijo;
    @Size(max = 20)
    @Column(name = "arrendatariotelefonomovil", length = 20)
    private String arrendatariotelefonomovil;
    @Size(max = 30)
    @Column(name = "gpslatitud", length = 30)
    private String gpslatitud;
    @Size(max = 30)
    @Column(name = "gpslongitud", length = 30)
    private String gpslongitud;
    @Column(name = "gpsfecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gpsfecha;
    @Size(max = 50)
    @Column(name = "conyuguenombre1", length = 50)
    private String conyuguenombre1;
    @Size(max = 50)
    @Column(name = "conyuguenombre2", length = 50)
    private String conyuguenombre2;
    @Size(max = 50)
    @Column(name = "conyugueapellido1", length = 50)
    private String conyugueapellido1;
    @Size(max = 50)
    @Column(name = "conyugueapellido2", length = 50)
    private String conyugueapellido2;
    @Size(max = 20)
    @Column(name = "conyugueniveleducativo", length = 20)
    private String conyugueniveleducativo;
    @Column(name = "conyuguefechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date conyuguefechanacimiento;
    @Size(max = 20)
    @Column(name = "conyuguetiempounidos", length = 20)
    private String conyuguetiempounidos;
    @Size(max = 20)
    @Column(name = "conyuguetelefono", length = 20)
    private String conyuguetelefono;
    @Size(max = 20)
    @Column(name = "conyugueactividadeconomica", length = 20)
    private String conyugueactividadeconomica;
    @Size(max = 20)
    @Column(name = "conyuguenegociodistinto", length = 20)
    private String conyuguenegociodistinto;
    @Size(max = 50)
    @Column(name = "conyuguenombrenegocio", length = 50)
    private String conyuguenombrenegocio;
    @Size(max = 50)
    @Column(name = "conyugueidentidad", length = 50)
    private String conyugueidentidad;
    @Size(max = 50)
    @Column(name = "conyuguetipodocumento", length = 50)
    private String conyuguetipodocumento;
    @Size(max = 50)
    @Column(name = "conyugueescliente", length = 50)
    private String conyugueescliente;
    @Size(max = 20)
    @Column(name = "rc_prestamo", length = 20)
    private String rcPrestamo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rc_montoesperado", precision = 12, scale = 2)
    private BigDecimal rcMontoesperado;
    @Column(name = "rc_cuotasabono", precision = 12, scale = 2)
    private BigDecimal rcCuotasabono;
    @Size(max = 20)
    @Column(name = "rc_cuentasahorro", length = 20)
    private String rcCuentasahorro;
    @Column(name = "rc_depositos", precision = 12, scale = 2)
    private BigDecimal rcDepositos;
    @Column(name = "rc_retiros", precision = 12, scale = 2)
    private BigDecimal rcRetiros;
    @Size(max = 20)
    @Column(name = "rc_certificadoaplazo", length = 20)
    private String rcCertificadoaplazo;
    @Column(name = "rc_certificadomontoesperado", precision = 12, scale = 2)
    private BigDecimal rcCertificadomontoesperado;
    @Size(max = 20)
    @Column(name = "rc_servicios", length = 20)
    private String rcServicios;
    @Column(name = "rc_montoremesas", precision = 12, scale = 2)
    private BigDecimal rcMontoremesas;
    @Column(name = "rc_montogiros", precision = 12, scale = 2)
    private BigDecimal rcMontogiros;
    @Size(max = 20)
    @Column(name = "rc_operacionesinternacionales", length = 20)
    private String rcOperacionesinternacionales;
    @Column(name = "rc_otrosmontos", precision = 12, scale = 2)
    private BigDecimal rcOtrosmontos;
    @Size(max = 20)
    @Column(name = "rc_instrumentosmonetarios", length = 20)
    private String rcInstrumentosmonetarios;
    @Size(max = 20)
    @Column(name = "rc_instrumentosmonetariosefectivo", length = 20)
    private String rcInstrumentosmonetariosefectivo;
    @Size(max = 20)
    @Column(name = "rc_instrumentosmonetarioscheques", length = 20)
    private String rcInstrumentosmonetarioscheques;
    @Size(max = 20)
    @Column(name = "rc_instrumentosmonetariostransferencias", length = 20)
    private String rcInstrumentosmonetariostransferencias;
    @Size(max = 20)
    @Column(name = "rc_instrumentosmonetariosmixto", length = 20)
    private String rcInstrumentosmonetariosmixto;
    @Size(max = 20)
    @Column(name = "rc_instrumentosmonetariosremesas", length = 20)
    private String rcInstrumentosmonetariosremesas;
    @Size(max = 20)
    @Column(name = "rc_instrumentosmonetariosgiros", length = 20)
    private String rcInstrumentosmonetariosgiros;
    @Size(max = 20)
    @Column(name = "rc_canales", length = 20)
    private String rcCanales;
    @Size(max = 20)
    @Column(name = "rc_canalesagenciafinsol", length = 20)
    private String rcCanalesagenciafinsol;
    @Size(max = 20)
    @Column(name = "rc_canalesagenciabancaria", length = 20)
    private String rcCanalesagenciabancaria;
    @Size(max = 20)
    @Column(name = "rc_canalesagencianobancaria", length = 20)
    private String rcCanalesagencianobancaria;
    @Size(max = 20)
    @Column(name = "rc_canalesbancos", length = 20)
    private String rcCanalesbancos;
    @Size(max = 50)
    @Column(name = "negnombrenegocio", length = 50)
    private String negnombrenegocio;
    @Size(max = 25)
    @Column(name = "negtiponegocio", length = 25)
    private String negtiponegocio;
    @Size(max = 20)
    @Column(name = "negsectornegocio", length = 20)
    private String negsectornegocio;
    @Size(max = 20)
    @Column(name = "negactividadprincipal", length = 20)
    private String negactividadprincipal;
    @Size(max = 40)
    @Column(name = "negcategoriaactividad", length = 40)
    private String negcategoriaactividad;
    @Size(max = 50)
    @Column(name = "negrtn", length = 50)
    private String negrtn;
    @Size(max = 60)
    @Column(name = "negactividadinterna", length = 60)
    private String negactividadinterna;
    @Column(name = "negempleados")
    private Integer negempleados;
    @Size(max = 20)
    @Column(name = "negclasificacionempresa", length = 20)
    private String negclasificacionempresa;
    @Size(max = 20)
    @Column(name = "negsectorinterno", length = 20)
    private String negsectorinterno;
    @Size(max = 20)
    @Column(name = "negtelefononegocio", length = 20)
    private String negtelefononegocio;
    @Size(max = 20)
    @Column(name = "neganosoperacion", length = 20)
    private String neganosoperacion;
    @Column(name = "negfechainioperacion")
    @Temporal(TemporalType.DATE)
    private Date negfechainioperacion;
    @Size(max = 100)
    @Column(name = "negexperienciaenactividad", length = 100)
    private String negexperienciaenactividad;
    @Column(name = "negcapitalactual", precision = 12, scale = 2)
    private BigDecimal negcapitalactual;
    @Size(max = 20)
    @Column(name = "negpermisooperacion", length = 20)
    private String negpermisooperacion;
    @Column(name = "negempleadosmujertes")
    private Integer negempleadosmujertes;
    @Column(name = "negempleadoshombres")
    private Integer negempleadoshombres;
    @Column(name = "negventasmensuales", precision = 12, scale = 2)
    private BigDecimal negventasmensuales;
    @Column(name = "negventasanuales", precision = 12, scale = 2)
    private BigDecimal negventasanuales;
    @Size(max = 20)
    @Column(name = "negrangoingresos", length = 20)
    private String negrangoingresos;
    @Size(max = 20)
    @Column(name = "negsuseciongerencial", length = 20)
    private String negsuseciongerencial;
    @Size(max = 20)
    @Column(name = "negafluenciaclientes", length = 20)
    private String negafluenciaclientes;
    @Size(max = 20)
    @Column(name = "negterminoscompra", length = 20)
    private String negterminoscompra;
    @Size(max = 20)
    @Column(name = "negterminosventa", length = 20)
    private String negterminosventa;
    @Size(max = 100)
    @Column(name = "negotrosingresosdetalle", length = 100)
    private String negotrosingresosdetalle;
    @Column(name = "negotosingresosmonto", precision = 12, scale = 2)
    private BigDecimal negotosingresosmonto;
    @Size(max = 20)
    @Column(name = "negcoincidedireccionnegociocondomicilio", length = 20)
    private String negcoincidedireccionnegociocondomicilio;
    @Size(max = 20)
    @Column(name = "negpais", length = 20)
    private String negpais;
    @Size(max = 20)
    @Column(name = "negdepartamento", length = 20)
    private String negdepartamento;
    @Size(max = 20)
    @Column(name = "negmunicipio", length = 20)
    private String negmunicipio;
    @Size(max = 20)
    @Column(name = "negsector", length = 20)
    private String negsector;
    @Size(max = 20)
    @Column(name = "negbarrio", length = 20)
    private String negbarrio;
    @Size(max = 30)
    @Column(name = "negetapa", length = 30)
    private String negetapa;
    @Size(max = 30)
    @Column(name = "negavenida", length = 30)
    private String negavenida;
    @Size(max = 30)
    @Column(name = "negcalle", length = 30)
    private String negcalle;
    @Size(max = 30)
    @Column(name = "negbloque", length = 30)
    private String negbloque;
    @Size(max = 30)
    @Column(name = "negnumerocasa", length = 30)
    private String negnumerocasa;
    @Size(max = 30)
    @Column(name = "negtipozona", length = 30)
    private String negtipozona;
    @Size(max = 30)
    @Column(name = "neglatitud", length = 30)
    private String neglatitud;
    @Size(max = 30)
    @Column(name = "neglongitud", length = 30)
    private String neglongitud;
    @Column(name = "negfechacapturaubicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date negfechacapturaubicacion;
    @Size(max = 100)
    @Column(name = "negpuntoreferencia", length = 100)
    private String negpuntoreferencia;
    @Size(max = 20)
    @Column(name = "negtipolocal", length = 20)
    private String negtipolocal;
    @Size(max = 100)
    @Column(name = "negnombrearrendador", length = 100)
    private String negnombrearrendador;
    @Size(max = 20)
    @Column(name = "negtelefonoarrendador", length = 20)
    private String negtelefonoarrendador;
    @Size(max = 20)
    @Column(name = "negproveedores", length = 20)
    private String negproveedores;
    @Size(max = 20)
    @Column(name = "encfuncionespublicas", length = 20)
    private String encfuncionespublicas;
    @Size(max = 20)
    @Column(name = "encconyugueep", length = 20)
    private String encconyugueep;
    @Size(max = 20)
    @Column(name = "encpatrimoniootrocj", length = 20)
    private String encpatrimoniootrocj;
    @Size(max = 20)
    @Column(name = "encnombrepatrimoniootrocj", length = 20)
    private String encnombrepatrimoniootrocj;
    @Size(max = 20)
    @Column(name = "encdoblenacionalidad", length = 20)
    private String encdoblenacionalidad;
    @Size(max = 20)
    @Column(name = "encresidenciaus", length = 20)
    private String encresidenciaus;
    @Size(max = 20)
    @Column(name = "encdiretelus", length = 20)
    private String encdiretelus;
    @Size(max = 20)
    @Column(name = "encpasaportegreencard", length = 20)
    private String encpasaportegreencard;
    @Size(max = 20)
    @Column(name = "encnacidous", length = 20)
    private String encnacidous;
    @Size(max = 20)
    @Column(name = "encreligion", length = 20)
    private String encreligion;
    @Size(max = 50)
    @Column(name = "encreligionotros", length = 50)
    private String encreligionotros;
    @Size(max = 20)
    @Column(name = "estado_crud", length = 20)
    private String estadoCrud;
    @Size(max = 30)
    @Column(name = "fechafirmada", length = 30)
    private String fechafirmada;

    public AdConozcaCliente() {
    }

    public AdConozcaCliente(String liteuid) {
        this.liteuid = liteuid;
    }

    public String getLiteuid() {
        return liteuid;
    }

    public void setLiteuid(String liteuid) {
        this.liteuid = liteuid;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getUsuariotopaz() {
        return usuariotopaz;
    }

    public void setUsuariotopaz(String usuariotopaz) {
        this.usuariotopaz = usuariotopaz;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public String getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(String tipocliente) {
        this.tipocliente = tipocliente;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public String getTipodocumentojuridico() {
        return tipodocumentojuridico;
    }

    public void setTipodocumentojuridico(String tipodocumentojuridico) {
        this.tipodocumentojuridico = tipodocumentojuridico;
    }

    public String getNrodocumentojuridico() {
        return nrodocumentojuridico;
    }

    public void setNrodocumentojuridico(String nrodocumentojuridico) {
        this.nrodocumentojuridico = nrodocumentojuridico;
    }

    public String getRazonsocialjuridico() {
        return razonsocialjuridico;
    }

    public void setRazonsocialjuridico(String razonsocialjuridico) {
        this.razonsocialjuridico = razonsocialjuridico;
    }

    public String getTiemporelacioninstitucion() {
        return tiemporelacioninstitucion;
    }

    public void setTiemporelacioninstitucion(String tiemporelacioninstitucion) {
        this.tiemporelacioninstitucion = tiemporelacioninstitucion;
    }

    public String getParterelacionada() {
        return parterelacionada;
    }

    public void setParterelacionada(String parterelacionada) {
        this.parterelacionada = parterelacionada;
    }

    public String getTitularrepresentante() {
        return titularrepresentante;
    }

    public void setTitularrepresentante(String titularrepresentante) {
        this.titularrepresentante = titularrepresentante;
    }

    public String getFirmaautorizada() {
        return firmaautorizada;
    }

    public void setFirmaautorizada(String firmaautorizada) {
        this.firmaautorizada = firmaautorizada;
    }

    public String getProcedenciacliente() {
        return procedenciacliente;
    }

    public void setProcedenciacliente(String procedenciacliente) {
        this.procedenciacliente = procedenciacliente;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public String getNiveleducativo() {
        return niveleducativo;
    }

    public void setNiveleducativo(String niveleducativo) {
        this.niveleducativo = niveleducativo;
    }

    public String getProfesionofisio() {
        return profesionofisio;
    }

    public void setProfesionofisio(String profesionofisio) {
        this.profesionofisio = profesionofisio;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDepartamentonac() {
        return departamentonac;
    }

    public void setDepartamentonac(String departamentonac) {
        this.departamentonac = departamentonac;
    }

    public String getMunicipionac() {
        return municipionac;
    }

    public void setMunicipionac(String municipionac) {
        this.municipionac = municipionac;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getPersonasdependen() {
        return personasdependen;
    }

    public void setPersonasdependen(String personasdependen) {
        this.personasdependen = personasdependen;
    }

    public String getDireccionpais() {
        return direccionpais;
    }

    public void setDireccionpais(String direccionpais) {
        this.direccionpais = direccionpais;
    }

    public String getDirecciondepartamento() {
        return direcciondepartamento;
    }

    public void setDirecciondepartamento(String direcciondepartamento) {
        this.direcciondepartamento = direcciondepartamento;
    }

    public String getDireccionmunicipio() {
        return direccionmunicipio;
    }

    public void setDireccionmunicipio(String direccionmunicipio) {
        this.direccionmunicipio = direccionmunicipio;
    }

    public String getDireccionsector() {
        return direccionsector;
    }

    public void setDireccionsector(String direccionsector) {
        this.direccionsector = direccionsector;
    }

    public String getDireccionbarrio() {
        return direccionbarrio;
    }

    public void setDireccionbarrio(String direccionbarrio) {
        this.direccionbarrio = direccionbarrio;
    }

    public String getDireccionavenida() {
        return direccionavenida;
    }

    public void setDireccionavenida(String direccionavenida) {
        this.direccionavenida = direccionavenida;
    }

    public String getDireccioncalle() {
        return direccioncalle;
    }

    public void setDireccioncalle(String direccioncalle) {
        this.direccioncalle = direccioncalle;
    }

    public String getDireccionbloque() {
        return direccionbloque;
    }

    public void setDireccionbloque(String direccionbloque) {
        this.direccionbloque = direccionbloque;
    }

    public String getDireccioncasano() {
        return direccioncasano;
    }

    public void setDireccioncasano(String direccioncasano) {
        this.direccioncasano = direccioncasano;
    }

    public String getDireccioncodigopostal() {
        return direccioncodigopostal;
    }

    public void setDireccioncodigopostal(String direccioncodigopostal) {
        this.direccioncodigopostal = direccioncodigopostal;
    }

    public String getPuntoreferencia() {
        return puntoreferencia;
    }

    public void setPuntoreferencia(String puntoreferencia) {
        this.puntoreferencia = puntoreferencia;
    }

    public String getDirecciontelefonofijo() {
        return direcciontelefonofijo;
    }

    public void setDirecciontelefonofijo(String direcciontelefonofijo) {
        this.direcciontelefonofijo = direcciontelefonofijo;
    }

    public String getDirecciontelefonomovil() {
        return direcciontelefonomovil;
    }

    public void setDirecciontelefonomovil(String direcciontelefonomovil) {
        this.direcciontelefonomovil = direcciontelefonomovil;
    }

    public String getDireccioncorreo() {
        return direccioncorreo;
    }

    public void setDireccioncorreo(String direccioncorreo) {
        this.direccioncorreo = direccioncorreo;
    }

    public String getDirecciontipovivienda() {
        return direcciontipovivienda;
    }

    public void setDirecciontipovivienda(String direcciontipovivienda) {
        this.direcciontipovivienda = direcciontipovivienda;
    }

    public String getDirecciontiempohabitacion() {
        return direcciontiempohabitacion;
    }

    public void setDirecciontiempohabitacion(String direcciontiempohabitacion) {
        this.direcciontiempohabitacion = direcciontiempohabitacion;
    }

    public String getDirecciontipozona() {
        return direcciontipozona;
    }

    public void setDirecciontipozona(String direcciontipozona) {
        this.direcciontipozona = direcciontipozona;
    }

    public String getArrendatarionombre() {
        return arrendatarionombre;
    }

    public void setArrendatarionombre(String arrendatarionombre) {
        this.arrendatarionombre = arrendatarionombre;
    }

    public String getArrendatariotelefonofijo() {
        return arrendatariotelefonofijo;
    }

    public void setArrendatariotelefonofijo(String arrendatariotelefonofijo) {
        this.arrendatariotelefonofijo = arrendatariotelefonofijo;
    }

    public String getArrendatariotelefonomovil() {
        return arrendatariotelefonomovil;
    }

    public void setArrendatariotelefonomovil(String arrendatariotelefonomovil) {
        this.arrendatariotelefonomovil = arrendatariotelefonomovil;
    }

    public String getGpslatitud() {
        return gpslatitud;
    }

    public void setGpslatitud(String gpslatitud) {
        this.gpslatitud = gpslatitud;
    }

    public String getGpslongitud() {
        return gpslongitud;
    }

    public void setGpslongitud(String gpslongitud) {
        this.gpslongitud = gpslongitud;
    }

    public Date getGpsfecha() {
        return gpsfecha;
    }

    public void setGpsfecha(Date gpsfecha) {
        this.gpsfecha = gpsfecha;
    }

    public String getConyuguenombre1() {
        return conyuguenombre1;
    }

    public void setConyuguenombre1(String conyuguenombre1) {
        this.conyuguenombre1 = conyuguenombre1;
    }

    public String getConyuguenombre2() {
        return conyuguenombre2;
    }

    public void setConyuguenombre2(String conyuguenombre2) {
        this.conyuguenombre2 = conyuguenombre2;
    }

    public String getConyugueapellido1() {
        return conyugueapellido1;
    }

    public void setConyugueapellido1(String conyugueapellido1) {
        this.conyugueapellido1 = conyugueapellido1;
    }

    public String getConyugueapellido2() {
        return conyugueapellido2;
    }

    public void setConyugueapellido2(String conyugueapellido2) {
        this.conyugueapellido2 = conyugueapellido2;
    }

    public String getConyugueniveleducativo() {
        return conyugueniveleducativo;
    }

    public void setConyugueniveleducativo(String conyugueniveleducativo) {
        this.conyugueniveleducativo = conyugueniveleducativo;
    }

    public Date getConyuguefechanacimiento() {
        return conyuguefechanacimiento;
    }

    public void setConyuguefechanacimiento(Date conyuguefechanacimiento) {
        this.conyuguefechanacimiento = conyuguefechanacimiento;
    }

    public String getConyuguetiempounidos() {
        return conyuguetiempounidos;
    }

    public void setConyuguetiempounidos(String conyuguetiempounidos) {
        this.conyuguetiempounidos = conyuguetiempounidos;
    }

    public String getConyuguetelefono() {
        return conyuguetelefono;
    }

    public void setConyuguetelefono(String conyuguetelefono) {
        this.conyuguetelefono = conyuguetelefono;
    }

    public String getConyugueactividadeconomica() {
        return conyugueactividadeconomica;
    }

    public void setConyugueactividadeconomica(String conyugueactividadeconomica) {
        this.conyugueactividadeconomica = conyugueactividadeconomica;
    }

    public String getConyuguenegociodistinto() {
        return conyuguenegociodistinto;
    }

    public void setConyuguenegociodistinto(String conyuguenegociodistinto) {
        this.conyuguenegociodistinto = conyuguenegociodistinto;
    }

    public String getConyuguenombrenegocio() {
        return conyuguenombrenegocio;
    }

    public void setConyuguenombrenegocio(String conyuguenombrenegocio) {
        this.conyuguenombrenegocio = conyuguenombrenegocio;
    }

    public String getConyugueidentidad() {
        return conyugueidentidad;
    }

    public void setConyugueidentidad(String conyugueidentidad) {
        this.conyugueidentidad = conyugueidentidad;
    }

    public String getConyuguetipodocumento() {
        return conyuguetipodocumento;
    }

    public void setConyuguetipodocumento(String conyuguetipodocumento) {
        this.conyuguetipodocumento = conyuguetipodocumento;
    }

    public String getConyugueescliente() {
        return conyugueescliente;
    }

    public void setConyugueescliente(String conyugueescliente) {
        this.conyugueescliente = conyugueescliente;
    }

    public String getRcPrestamo() {
        return rcPrestamo;
    }

    public void setRcPrestamo(String rcPrestamo) {
        this.rcPrestamo = rcPrestamo;
    }

    public BigDecimal getRcMontoesperado() {
        return rcMontoesperado;
    }

    public void setRcMontoesperado(BigDecimal rcMontoesperado) {
        this.rcMontoesperado = rcMontoesperado;
    }

    public BigDecimal getRcCuotasabono() {
        return rcCuotasabono;
    }

    public void setRcCuotasabono(BigDecimal rcCuotasabono) {
        this.rcCuotasabono = rcCuotasabono;
    }

    public String getRcCuentasahorro() {
        return rcCuentasahorro;
    }

    public void setRcCuentasahorro(String rcCuentasahorro) {
        this.rcCuentasahorro = rcCuentasahorro;
    }

    public BigDecimal getRcDepositos() {
        return rcDepositos;
    }

    public void setRcDepositos(BigDecimal rcDepositos) {
        this.rcDepositos = rcDepositos;
    }

    public BigDecimal getRcRetiros() {
        return rcRetiros;
    }

    public void setRcRetiros(BigDecimal rcRetiros) {
        this.rcRetiros = rcRetiros;
    }

    public String getRcCertificadoaplazo() {
        return rcCertificadoaplazo;
    }

    public void setRcCertificadoaplazo(String rcCertificadoaplazo) {
        this.rcCertificadoaplazo = rcCertificadoaplazo;
    }

    public BigDecimal getRcCertificadomontoesperado() {
        return rcCertificadomontoesperado;
    }

    public void setRcCertificadomontoesperado(BigDecimal rcCertificadomontoesperado) {
        this.rcCertificadomontoesperado = rcCertificadomontoesperado;
    }

    public String getRcServicios() {
        return rcServicios;
    }

    public void setRcServicios(String rcServicios) {
        this.rcServicios = rcServicios;
    }

    public BigDecimal getRcMontoremesas() {
        return rcMontoremesas;
    }

    public void setRcMontoremesas(BigDecimal rcMontoremesas) {
        this.rcMontoremesas = rcMontoremesas;
    }

    public BigDecimal getRcMontogiros() {
        return rcMontogiros;
    }

    public void setRcMontogiros(BigDecimal rcMontogiros) {
        this.rcMontogiros = rcMontogiros;
    }

    public String getRcOperacionesinternacionales() {
        return rcOperacionesinternacionales;
    }

    public void setRcOperacionesinternacionales(String rcOperacionesinternacionales) {
        this.rcOperacionesinternacionales = rcOperacionesinternacionales;
    }

    public BigDecimal getRcOtrosmontos() {
        return rcOtrosmontos;
    }

    public void setRcOtrosmontos(BigDecimal rcOtrosmontos) {
        this.rcOtrosmontos = rcOtrosmontos;
    }

    public String getRcInstrumentosmonetarios() {
        return rcInstrumentosmonetarios;
    }

    public void setRcInstrumentosmonetarios(String rcInstrumentosmonetarios) {
        this.rcInstrumentosmonetarios = rcInstrumentosmonetarios;
    }

    public String getRcInstrumentosmonetariosefectivo() {
        return rcInstrumentosmonetariosefectivo;
    }

    public void setRcInstrumentosmonetariosefectivo(String rcInstrumentosmonetariosefectivo) {
        this.rcInstrumentosmonetariosefectivo = rcInstrumentosmonetariosefectivo;
    }

    public String getRcInstrumentosmonetarioscheques() {
        return rcInstrumentosmonetarioscheques;
    }

    public void setRcInstrumentosmonetarioscheques(String rcInstrumentosmonetarioscheques) {
        this.rcInstrumentosmonetarioscheques = rcInstrumentosmonetarioscheques;
    }

    public String getRcInstrumentosmonetariostransferencias() {
        return rcInstrumentosmonetariostransferencias;
    }

    public void setRcInstrumentosmonetariostransferencias(String rcInstrumentosmonetariostransferencias) {
        this.rcInstrumentosmonetariostransferencias = rcInstrumentosmonetariostransferencias;
    }

    public String getRcInstrumentosmonetariosmixto() {
        return rcInstrumentosmonetariosmixto;
    }

    public void setRcInstrumentosmonetariosmixto(String rcInstrumentosmonetariosmixto) {
        this.rcInstrumentosmonetariosmixto = rcInstrumentosmonetariosmixto;
    }

    public String getRcInstrumentosmonetariosremesas() {
        return rcInstrumentosmonetariosremesas;
    }

    public void setRcInstrumentosmonetariosremesas(String rcInstrumentosmonetariosremesas) {
        this.rcInstrumentosmonetariosremesas = rcInstrumentosmonetariosremesas;
    }

    public String getRcInstrumentosmonetariosgiros() {
        return rcInstrumentosmonetariosgiros;
    }

    public void setRcInstrumentosmonetariosgiros(String rcInstrumentosmonetariosgiros) {
        this.rcInstrumentosmonetariosgiros = rcInstrumentosmonetariosgiros;
    }

    public String getRcCanales() {
        return rcCanales;
    }

    public void setRcCanales(String rcCanales) {
        this.rcCanales = rcCanales;
    }

    public String getRcCanalesagenciafinsol() {
        return rcCanalesagenciafinsol;
    }

    public void setRcCanalesagenciafinsol(String rcCanalesagenciafinsol) {
        this.rcCanalesagenciafinsol = rcCanalesagenciafinsol;
    }

    public String getRcCanalesagenciabancaria() {
        return rcCanalesagenciabancaria;
    }

    public void setRcCanalesagenciabancaria(String rcCanalesagenciabancaria) {
        this.rcCanalesagenciabancaria = rcCanalesagenciabancaria;
    }

    public String getRcCanalesagencianobancaria() {
        return rcCanalesagencianobancaria;
    }

    public void setRcCanalesagencianobancaria(String rcCanalesagencianobancaria) {
        this.rcCanalesagencianobancaria = rcCanalesagencianobancaria;
    }

    public String getRcCanalesbancos() {
        return rcCanalesbancos;
    }

    public void setRcCanalesbancos(String rcCanalesbancos) {
        this.rcCanalesbancos = rcCanalesbancos;
    }

    public String getNegnombrenegocio() {
        return negnombrenegocio;
    }

    public void setNegnombrenegocio(String negnombrenegocio) {
        this.negnombrenegocio = negnombrenegocio;
    }

    public String getNegtiponegocio() {
        return negtiponegocio;
    }

    public void setNegtiponegocio(String negtiponegocio) {
        this.negtiponegocio = negtiponegocio;
    }

    public String getNegsectornegocio() {
        return negsectornegocio;
    }

    public void setNegsectornegocio(String negsectornegocio) {
        this.negsectornegocio = negsectornegocio;
    }

    public String getNegactividadprincipal() {
        return negactividadprincipal;
    }

    public void setNegactividadprincipal(String negactividadprincipal) {
        this.negactividadprincipal = negactividadprincipal;
    }

    public String getNegcategoriaactividad() {
        return negcategoriaactividad;
    }

    public void setNegcategoriaactividad(String negcategoriaactividad) {
        this.negcategoriaactividad = negcategoriaactividad;
    }

    public String getNegrtn() {
        return negrtn;
    }

    public void setNegrtn(String negrtn) {
        this.negrtn = negrtn;
    }

    public String getNegactividadinterna() {
        return negactividadinterna;
    }

    public void setNegactividadinterna(String negactividadinterna) {
        this.negactividadinterna = negactividadinterna;
    }

    public Integer getNegempleados() {
        return negempleados;
    }

    public void setNegempleados(Integer negempleados) {
        this.negempleados = negempleados;
    }

    public String getNegclasificacionempresa() {
        return negclasificacionempresa;
    }

    public void setNegclasificacionempresa(String negclasificacionempresa) {
        this.negclasificacionempresa = negclasificacionempresa;
    }

    public String getNegsectorinterno() {
        return negsectorinterno;
    }

    public void setNegsectorinterno(String negsectorinterno) {
        this.negsectorinterno = negsectorinterno;
    }

    public String getNegtelefononegocio() {
        return negtelefononegocio;
    }

    public void setNegtelefononegocio(String negtelefononegocio) {
        this.negtelefononegocio = negtelefononegocio;
    }

    public String getNeganosoperacion() {
        return neganosoperacion;
    }

    public void setNeganosoperacion(String neganosoperacion) {
        this.neganosoperacion = neganosoperacion;
    }

    public Date getNegfechainioperacion() {
        return negfechainioperacion;
    }

    public void setNegfechainioperacion(Date negfechainioperacion) {
        this.negfechainioperacion = negfechainioperacion;
    }

    public String getNegexperienciaenactividad() {
        return negexperienciaenactividad;
    }

    public void setNegexperienciaenactividad(String negexperienciaenactividad) {
        this.negexperienciaenactividad = negexperienciaenactividad;
    }

    public BigDecimal getNegcapitalactual() {
        return negcapitalactual;
    }

    public void setNegcapitalactual(BigDecimal negcapitalactual) {
        this.negcapitalactual = negcapitalactual;
    }

    public String getNegpermisooperacion() {
        return negpermisooperacion;
    }

    public void setNegpermisooperacion(String negpermisooperacion) {
        this.negpermisooperacion = negpermisooperacion;
    }

    public Integer getNegempleadosmujertes() {
        return negempleadosmujertes;
    }

    public void setNegempleadosmujertes(Integer negempleadosmujertes) {
        this.negempleadosmujertes = negempleadosmujertes;
    }

    public Integer getNegempleadoshombres() {
        return negempleadoshombres;
    }

    public void setNegempleadoshombres(Integer negempleadoshombres) {
        this.negempleadoshombres = negempleadoshombres;
    }

    public BigDecimal getNegventasmensuales() {
        return negventasmensuales;
    }

    public void setNegventasmensuales(BigDecimal negventasmensuales) {
        this.negventasmensuales = negventasmensuales;
    }

    public BigDecimal getNegventasanuales() {
        return negventasanuales;
    }

    public void setNegventasanuales(BigDecimal negventasanuales) {
        this.negventasanuales = negventasanuales;
    }

    public String getNegrangoingresos() {
        return negrangoingresos;
    }

    public void setNegrangoingresos(String negrangoingresos) {
        this.negrangoingresos = negrangoingresos;
    }

    public String getNegsuseciongerencial() {
        return negsuseciongerencial;
    }

    public void setNegsuseciongerencial(String negsuseciongerencial) {
        this.negsuseciongerencial = negsuseciongerencial;
    }

    public String getNegafluenciaclientes() {
        return negafluenciaclientes;
    }

    public void setNegafluenciaclientes(String negafluenciaclientes) {
        this.negafluenciaclientes = negafluenciaclientes;
    }

    public String getNegterminoscompra() {
        return negterminoscompra;
    }

    public void setNegterminoscompra(String negterminoscompra) {
        this.negterminoscompra = negterminoscompra;
    }

    public String getNegterminosventa() {
        return negterminosventa;
    }

    public void setNegterminosventa(String negterminosventa) {
        this.negterminosventa = negterminosventa;
    }

    public String getNegotrosingresosdetalle() {
        return negotrosingresosdetalle;
    }

    public void setNegotrosingresosdetalle(String negotrosingresosdetalle) {
        this.negotrosingresosdetalle = negotrosingresosdetalle;
    }

    public BigDecimal getNegotosingresosmonto() {
        return negotosingresosmonto;
    }

    public void setNegotosingresosmonto(BigDecimal negotosingresosmonto) {
        this.negotosingresosmonto = negotosingresosmonto;
    }

    public String getNegcoincidedireccionnegociocondomicilio() {
        return negcoincidedireccionnegociocondomicilio;
    }

    public void setNegcoincidedireccionnegociocondomicilio(String negcoincidedireccionnegociocondomicilio) {
        this.negcoincidedireccionnegociocondomicilio = negcoincidedireccionnegociocondomicilio;
    }

    public String getNegpais() {
        return negpais;
    }

    public void setNegpais(String negpais) {
        this.negpais = negpais;
    }

    public String getNegdepartamento() {
        return negdepartamento;
    }

    public void setNegdepartamento(String negdepartamento) {
        this.negdepartamento = negdepartamento;
    }

    public String getNegmunicipio() {
        return negmunicipio;
    }

    public void setNegmunicipio(String negmunicipio) {
        this.negmunicipio = negmunicipio;
    }

    public String getNegsector() {
        return negsector;
    }

    public void setNegsector(String negsector) {
        this.negsector = negsector;
    }

    public String getNegbarrio() {
        return negbarrio;
    }

    public void setNegbarrio(String negbarrio) {
        this.negbarrio = negbarrio;
    }

    public String getNegetapa() {
        return negetapa;
    }

    public void setNegetapa(String negetapa) {
        this.negetapa = negetapa;
    }

    public String getNegavenida() {
        return negavenida;
    }

    public void setNegavenida(String negavenida) {
        this.negavenida = negavenida;
    }

    public String getNegcalle() {
        return negcalle;
    }

    public void setNegcalle(String negcalle) {
        this.negcalle = negcalle;
    }

    public String getNegbloque() {
        return negbloque;
    }

    public void setNegbloque(String negbloque) {
        this.negbloque = negbloque;
    }

    public String getNegnumerocasa() {
        return negnumerocasa;
    }

    public void setNegnumerocasa(String negnumerocasa) {
        this.negnumerocasa = negnumerocasa;
    }

    public String getNegtipozona() {
        return negtipozona;
    }

    public void setNegtipozona(String negtipozona) {
        this.negtipozona = negtipozona;
    }

    public String getNeglatitud() {
        return neglatitud;
    }

    public void setNeglatitud(String neglatitud) {
        this.neglatitud = neglatitud;
    }

    public String getNeglongitud() {
        return neglongitud;
    }

    public void setNeglongitud(String neglongitud) {
        this.neglongitud = neglongitud;
    }

    public Date getNegfechacapturaubicacion() {
        return negfechacapturaubicacion;
    }

    public void setNegfechacapturaubicacion(Date negfechacapturaubicacion) {
        this.negfechacapturaubicacion = negfechacapturaubicacion;
    }

    public String getNegpuntoreferencia() {
        return negpuntoreferencia;
    }

    public void setNegpuntoreferencia(String negpuntoreferencia) {
        this.negpuntoreferencia = negpuntoreferencia;
    }

    public String getNegtipolocal() {
        return negtipolocal;
    }

    public void setNegtipolocal(String negtipolocal) {
        this.negtipolocal = negtipolocal;
    }

    public String getNegnombrearrendador() {
        return negnombrearrendador;
    }

    public void setNegnombrearrendador(String negnombrearrendador) {
        this.negnombrearrendador = negnombrearrendador;
    }

    public String getNegtelefonoarrendador() {
        return negtelefonoarrendador;
    }

    public void setNegtelefonoarrendador(String negtelefonoarrendador) {
        this.negtelefonoarrendador = negtelefonoarrendador;
    }

    public String getNegproveedores() {
        return negproveedores;
    }

    public void setNegproveedores(String negproveedores) {
        this.negproveedores = negproveedores;
    }

    public String getEncfuncionespublicas() {
        return encfuncionespublicas;
    }

    public void setEncfuncionespublicas(String encfuncionespublicas) {
        this.encfuncionespublicas = encfuncionespublicas;
    }

    public String getEncconyugueep() {
        return encconyugueep;
    }

    public void setEncconyugueep(String encconyugueep) {
        this.encconyugueep = encconyugueep;
    }

    public String getEncpatrimoniootrocj() {
        return encpatrimoniootrocj;
    }

    public void setEncpatrimoniootrocj(String encpatrimoniootrocj) {
        this.encpatrimoniootrocj = encpatrimoniootrocj;
    }

    public String getEncnombrepatrimoniootrocj() {
        return encnombrepatrimoniootrocj;
    }

    public void setEncnombrepatrimoniootrocj(String encnombrepatrimoniootrocj) {
        this.encnombrepatrimoniootrocj = encnombrepatrimoniootrocj;
    }

    public String getEncdoblenacionalidad() {
        return encdoblenacionalidad;
    }

    public void setEncdoblenacionalidad(String encdoblenacionalidad) {
        this.encdoblenacionalidad = encdoblenacionalidad;
    }

    public String getEncresidenciaus() {
        return encresidenciaus;
    }

    public void setEncresidenciaus(String encresidenciaus) {
        this.encresidenciaus = encresidenciaus;
    }

    public String getEncdiretelus() {
        return encdiretelus;
    }

    public void setEncdiretelus(String encdiretelus) {
        this.encdiretelus = encdiretelus;
    }

    public String getEncpasaportegreencard() {
        return encpasaportegreencard;
    }

    public void setEncpasaportegreencard(String encpasaportegreencard) {
        this.encpasaportegreencard = encpasaportegreencard;
    }

    public String getEncnacidous() {
        return encnacidous;
    }

    public void setEncnacidous(String encnacidous) {
        this.encnacidous = encnacidous;
    }

    public String getEncreligion() {
        return encreligion;
    }

    public void setEncreligion(String encreligion) {
        this.encreligion = encreligion;
    }

    public String getEncreligionotros() {
        return encreligionotros;
    }

    public void setEncreligionotros(String encreligionotros) {
        this.encreligionotros = encreligionotros;
    }

    public String getEstadoCrud() {
        return estadoCrud;
    }

    public void setEstadoCrud(String estadoCrud) {
        this.estadoCrud = estadoCrud;
    }

    public String getFechafirmada() {
        return fechafirmada;
    }

    public void setFechafirmada(String fechafirmada) {
        this.fechafirmada = fechafirmada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (liteuid != null ? liteuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdConozcaCliente)) {
            return false;
        }
        AdConozcaCliente other = (AdConozcaCliente) object;
        if ((this.liteuid == null && other.liteuid != null) || (this.liteuid != null && !this.liteuid.equals(other.liteuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finsolhn.com.data.AdConozcaCliente[ liteuid=" + liteuid + " ]";
    }
    
}
