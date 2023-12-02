package net.royal.spring.workflow.servicio.impl;

import static net.royal.spring.workflow.boot.SpringWorkflowConstanteBoot.WF_TIPOREPOSITORIO;
import static net.royal.spring.workflow.boot.SpringWorkflowConstanteBoot.WF_TIPOREPOSITORIO_FS;
import static net.royal.spring.workflow.boot.SpringWorkflowConstanteBoot.WF_TIPOREPOSITORIO_SY;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.cfg.PropertiesConfigSource.Parse;

import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.modelo.ErrorTransaccion;
import net.royal.spring.framework.modelo.ReporteArchivoTransaccion;
import net.royal.spring.framework.modelo.ReporteTransaccion;
import net.royal.spring.framework.modelo.WorkFlowAdjunto;
import net.royal.spring.framework.modelo.WorkFlowAprobadorTransaccion;
import net.royal.spring.framework.modelo.WorkFlowResultado;
import net.royal.spring.framework.modelo.WorkFlowTransaccion;
import net.royal.spring.framework.modelo.correo.EmailDestino;
import net.royal.spring.framework.modelo.correo.EmailDestino.tipo_destino;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioAdjunto;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
//import net.royal.spring.framework.soltrak.LoginSoltrakRequest;
//import net.royal.spring.framework.soltrak.LoginSoltrakResponse;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.constante.ConstanteFiltro;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.workflow.boot.SpringWorkflowConstanteBoot;
import net.royal.spring.workflow.dao.impl.FsDocumentoDaoImpl;
import net.royal.spring.workflow.dao.impl.SyDocumentoAnexosDaoImpl;
import net.royal.spring.workflow.dao.impl.WfFirmaDaoImpl;
import net.royal.spring.workflow.dao.impl.WfFirmaDocumentoDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesoDocumentosDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesoEstadosDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesoFlujoConfiguracionesDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesoFlujoNivelAccionesDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesoFlujoNivelAprobadoresDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesoFlujoNivelDocumentosDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesoFlujosDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesoFlujosNivelesDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesoRolDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesoVariablesDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesoVersionesDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesosDaoImpl;
import net.royal.spring.workflow.dao.impl.WfTransaccionPlanificacionDaoImpl;
import net.royal.spring.workflow.dao.impl.WfTransaccionalertaDaoImpl;
import net.royal.spring.workflow.dao.impl.WfTransaccionaprobadorDaoImpl;
import net.royal.spring.workflow.dao.impl.WfTransaccionesDaoImpl;
import net.royal.spring.workflow.dominio.FsDocumento;
import net.royal.spring.workflow.dominio.FsDocumentoPk;
import net.royal.spring.workflow.dominio.SyDocumentoAnexos;
import net.royal.spring.workflow.dominio.WfFirma;
import net.royal.spring.workflow.dominio.WfFirmaDocumento;
import net.royal.spring.workflow.dominio.WfFirmaDocumentoPk;
import net.royal.spring.workflow.dominio.WfFirmaPk;
import net.royal.spring.workflow.dominio.WfProcesoDocumentos;
import net.royal.spring.workflow.dominio.WfProcesoEstados;
import net.royal.spring.workflow.dominio.WfProcesoEstadosPk;
import net.royal.spring.workflow.dominio.WfProcesoFlujoConfiguraciones;
import net.royal.spring.workflow.dominio.WfProcesoFlujoNivelAcciones;
import net.royal.spring.workflow.dominio.WfProcesoFlujoNivelAprobadores;
import net.royal.spring.workflow.dominio.WfProcesoFlujoNivelAprobadoresPk;
import net.royal.spring.workflow.dominio.WfProcesoFlujoNivelDocumentos;
import net.royal.spring.workflow.dominio.WfProcesoFlujos;
import net.royal.spring.workflow.dominio.WfProcesoFlujosNiveles;
import net.royal.spring.workflow.dominio.WfProcesoFlujosNivelesPk;
import net.royal.spring.workflow.dominio.WfProcesoRol;
import net.royal.spring.workflow.dominio.WfProcesoVariables;
import net.royal.spring.workflow.dominio.WfProcesoVersiones;
import net.royal.spring.workflow.dominio.WfProcesoVersionesPk;
import net.royal.spring.workflow.dominio.WfProcesos;
import net.royal.spring.workflow.dominio.WfProcesosPk;
import net.royal.spring.workflow.dominio.WfTransaccionalerta;
import net.royal.spring.workflow.dominio.WfTransaccionaprobador;
import net.royal.spring.workflow.dominio.WfTransacciones;
import net.royal.spring.workflow.dominio.WfTransaccionesPk;
import net.royal.spring.workflow.dominio.WfTransaccionplanificacion;
import net.royal.spring.workflow.dominio.WfTransaccionplanificacionPk;
import net.royal.spring.workflow.dominio.dto.DtoAprobacionAcciones;
import net.royal.spring.workflow.dominio.dto.DtoExportarWf;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfFlujoAccion;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfFlujoAprobador;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfFlujoConfiguracion;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfFlujoNivel;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfFlujoNivelDocumento;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProceso;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProcesoDocumento;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProcesoEstado;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProcesoFlujo;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProcesoRol;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProcesoVariable;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProcesoVersion;
import net.royal.spring.workflow.dominio.dto.DtoFirmaCarga;
import net.royal.spring.workflow.dominio.dto.DtoFirmaData;
import net.royal.spring.workflow.dominio.dto.DtoFirmaDocumento;
import net.royal.spring.workflow.dominio.dto.DtoFirmaDocumentoGenerico;
import net.royal.spring.workflow.dominio.dto.DtoFirmaParametros;
import net.royal.spring.workflow.dominio.dto.DtoFlujo;
import net.royal.spring.workflow.dominio.dto.DtoFlujoAdjunto;
import net.royal.spring.workflow.dominio.dto.DtoFlujoAdjuntoValidar;
import net.royal.spring.workflow.dominio.dto.DtoFlujoConfiguracion;
import net.royal.spring.workflow.dominio.dto.DtoFlujoEjecutar;
import net.royal.spring.workflow.dominio.dto.DtoFlujoSolicitud;
import net.royal.spring.workflow.dominio.dto.DtoFlujoTransaccionRequest;
import net.royal.spring.workflow.dominio.dto.DtoFlujoTransaccionResponse;
import net.royal.spring.workflow.dominio.dto.DtoJerarquiaMacroProceso;
import net.royal.spring.workflow.dominio.dto.DtoNotificacionMasiva;
import net.royal.spring.workflow.dominio.dto.DtoOrigenDatos;
import net.royal.spring.workflow.dominio.dto.DtoPersonaNotificar;
import net.royal.spring.workflow.dominio.dto.DtoPlanificacionGenerar;
import net.royal.spring.workflow.dominio.dto.DtoSeguimiento;
import net.royal.spring.workflow.dominio.dto.DtoSeguimientoVistaAvanzada;
import net.royal.spring.workflow.dominio.dto.DtoSelectorDinamico;
import net.royal.spring.workflow.dominio.dto.DtoSolicitudDetalleReporte;
import net.royal.spring.workflow.dominio.dto.DtoTransaccionValidaciones;
import net.royal.spring.workflow.dominio.dto.DtoTransaccionVistaAvanzada;
import net.royal.spring.workflow.dominio.dto.DtoWfEstado;
import net.royal.spring.workflow.dominio.dto.DtoWfFlujo;
import net.royal.spring.workflow.dominio.dto.DtoWfFlujoConfiguracion;
import net.royal.spring.workflow.dominio.dto.DtoWfFlujoNivel;
import net.royal.spring.workflow.dominio.dto.DtoWfFlujoNivelAccion;
import net.royal.spring.workflow.dominio.dto.DtoWfFlujoNivelAprobador;
import net.royal.spring.workflow.dominio.dto.DtoWfFlujoNivelDocumento;
import net.royal.spring.workflow.dominio.dto.DtoWfFormularioDatos;
import net.royal.spring.workflow.dominio.dto.DtoWfPlanificacion;
import net.royal.spring.workflow.dominio.dto.DtoWfPlanificacionEtapa;
import net.royal.spring.workflow.dominio.dto.DtoWfProceso;
import net.royal.spring.workflow.dominio.dto.DtoWfRol;
import net.royal.spring.workflow.dominio.dto.DtoWfTipoDocumento;
import net.royal.spring.workflow.dominio.dto.DtoWfTransaccionTemp;
import net.royal.spring.workflow.dominio.dto.DtoWfVariable;
import net.royal.spring.workflow.dominio.dto.DtoWfVistaAdminConfiguracionDocumentos;
import net.royal.spring.workflow.dominio.dto.Dtoregistroproveedor;
import net.royal.spring.workflow.dominio.filtro.FiltroSolicitudes;
import net.royal.spring.workflow.dominio.filtro.FiltroSyDocumentos;
import net.royal.spring.workflow.dominio.filtro.FiltroTransaccion;

@Service
public class WfTransaccionesServicioImpl extends GenericoServicioImpl {

	private static Logger logger = LogManager.getLogger(WfTransaccionesServicioImpl.class);

	@Autowired
	private WfMotorMacroTransaccionesServicioImpl wfMotorMacroTransaccionesServicio;

	@Autowired
	private WfFirmaDaoImpl wfFirmaDaoImpl;

	@Autowired
	private WfFirmaDocumentoDaoImpl wfFirmaDocumentoDaoImpl;

	@Autowired
	private WfTransaccionesDaoImpl dao;

	@Autowired
	private WfProcesosDaoImpl wfProcesoDaoImpl;

	@Autowired
	private WfProcesoVersionesDaoImpl wfProcesoVersionDaoImpl;

	@Autowired
	private WfProcesoDocumentosDaoImpl wfProcesoDocumentoDaoImpl;

	@Autowired
	private WfProcesoEstadosDaoImpl wfProcesoEstadoDaoImpl;

	@Autowired
	private WfProcesoVariablesDaoImpl wfProcesoVariableDaoImpl;

	@Autowired
	private WfProcesoFlujosDaoImpl wfProcesoFlujoDaoImpl;

	@Autowired
	private WfProcesoFlujoConfiguracionesDaoImpl wfProcesoFlujoConfiguracionDaoImpl;

	@Autowired
	private WfProcesoFlujoNivelAccionesDaoImpl wfProcesoFlujoNivelAccionDaoImpl;

	@Autowired
	private WfProcesoFlujoNivelAprobadoresDaoImpl wfProcesoFlujoNivelAprobadorDaoImpl;

	@Autowired
	private WfProcesoFlujoNivelDocumentosDaoImpl wfProcesoFlujoNivelDocumentoDaoImpl;

	@Autowired
	private WfProcesoFlujosNivelesDaoImpl wfProcesoFlujoNivelDaoImpl;

	@Autowired
	private FsDocumentoDaoImpl fsDocumentoDaoImpl;

	@Autowired
	private WfTransaccionaprobadorDaoImpl wfTransaccionaprobadorDaoImpl;

	@Autowired
	private SyDocumentoAnexosDaoImpl syDocumentoAnexosDaoImpl;

	@Autowired
	private WfTransaccionalertaDaoImpl wfTransaccionalertaDaoImpl;

	@Autowired
	private WfTransaccionPlanificacionDaoImpl wfTransaccionPlanificacionDaoImpl;

	@Autowired
	private WfProcesoRolDaoImpl wfProcesoRolDaoImpl;

	@Transactional(readOnly = true)
	public DtoWfProceso obtenerProcesoPorId(String uuid) throws IOException {

		DtoWfProceso dto = dao.obtenerProcesoPorId(uuid);
		if (dto == null) {
			return new DtoWfProceso();
		}

		String proceso = dto.getProceso();
		Integer version = dto.getVersion();
		List<DtoWfTipoDocumento> tipoDocumentoBeanRaw = dao.obtenerTipoDocumentoProceso(proceso);

		String rutaServer = dao.obtenerParametroValorExplicacion("RUTAADJUN", "SY");

		for (DtoWfTipoDocumento row : tipoDocumentoBeanRaw) {
			if (!UString.estaVacio(row.getRutaplantilla())) {
				String tmp_ = rutaServer + File.separator + dto.getProceso() + File.separator + dto.getVersion()
						+ File.separator + row.getRutaplantilla();
				byte[] contenido = null;
				if (new File(tmp_).exists()) {
					contenido = UFile.obtenerArregloByte(tmp_);
				}
				row.setArchivoString(org.apache.commons.codec.binary.Base64.encodeBase64String(contenido));
				row.setArchivoString(UFile.obtenerNombreWebDescargar(row.getArchivoString()));

			}
		}

		List<DtoWfVariable> variableBeanRaw = dao.obtenerVariableProceso(proceso);
		List<DtoWfEstado> estadoBeanRaw = dao.obtenerEstadoProceso(proceso);

		List<DtoWfFlujo> flujoBeanRaw = dao.obtenerFlujoProceso(proceso, version);
		List<DtoWfFlujoNivel> nivelBeanRaw = dao.obtenerNivelProceso(proceso, version);
		List<DtoWfFlujoConfiguracion> configuracionBeanRaw = dao.obtenerConfiguracionProceso(proceso, version);
		List<DtoWfFlujoNivelDocumento> documentoNivelRaw = dao.obtenerDocumentoNivelProceso(proceso, version);
		List<DtoWfFlujoNivelAprobador> aprobadorNivelRaw = dao.obtenerAprobadorNivelProceso(proceso, version,
				dto.getSegmentocodigotabla());
		List<DtoWfFlujoNivelAccion> accionNivelRaw = dao.obtenerAccionNivelProceso(proceso, version);
		List<DtoWfRol> rolesRaw = dao.obtenerRolProceso(proceso, version);

		dto.setEstados(estadoBeanRaw);
		dto.setTipodocumentos(tipoDocumentoBeanRaw);
		dto.setVariables(variableBeanRaw);
		dto.setFlujos(flujoBeanRaw);
		dto.setRoles(rolesRaw);

		for (DtoWfFlujo flujo : flujoBeanRaw) {
			flujo.setConfiguraciones(configuracionBeanRaw.stream()
					.filter(x -> x.getFlujo().intValue() == flujo.getFlujo().intValue()).collect(Collectors.toList()));
			flujo.setNiveles(nivelBeanRaw.stream().filter(x -> x.getFlujo().intValue() == flujo.getFlujo().intValue())
					.collect(Collectors.toList()));
			for (DtoWfFlujoNivel nivel : flujo.getNiveles()) {
				nivel.setDocumentos(documentoNivelRaw.stream()
						.filter(x -> x.getFlujo().intValue() == flujo.getFlujo().intValue()
								&& x.getNivel().intValue() == nivel.getNivel().intValue())
						.collect(Collectors.toList()));
				nivel.setAprobadores(aprobadorNivelRaw.stream()
						.filter(x -> x.getFlujo().intValue() == flujo.getFlujo().intValue()
								&& x.getNivel().intValue() == nivel.getNivel().intValue())
						.collect(Collectors.toList()));
				nivel.setAcciones(accionNivelRaw.stream()
						.filter(x -> x.getFlujo().intValue() == flujo.getFlujo().intValue()
								&& x.getNivel().intValue() == nivel.getNivel().intValue())
						.collect(Collectors.toList()));
			}
		}

		return dto;
	}

	public List<DtoWfProceso> listarProcesos(DtoTabla filtro) {
		return dao.listarProcesos(filtro);
	}

	@Transactional
	public DtoTabla registrarProceso(DtoWfProceso bean, SeguridadUsuarioActual usuarioActual, boolean registrarProceso,
			boolean nuevaVersion) throws UException {

		bean.setProceso(bean.getProceso().trim());

		Date hoy = new Date();
		String usuario = usuarioActual.getUsuario();

		WfProcesoVersiones procesoVersion = null;

		if (registrarProceso) {
			if (wfProcesoDaoImpl.obtenerPorId(new WfProcesosPk(bean.getProceso())) != null) {
				throw new UException("El proceso ya existe", tipo_mensaje.ERROR);
			}

			WfProcesos proceso = new WfProcesos();
			proceso.getPk().setProcesoid(bean.getProceso());
			proceso.setNombre(bean.getDescripcion());
			proceso.setEstado(bean.getEstado());
			proceso.setAplicacionid(bean.getAplicacion());
			proceso.setCreacionfecha(hoy);
			proceso.setCreacionusuario(usuario);

			wfProcesoDaoImpl.registrarProceso(proceso);

			procesoVersion = new WfProcesoVersiones();
			procesoVersion.getPk().setProcesoid(bean.getProceso());
			procesoVersion.getPk().setVersionid(1);
			procesoVersion.setNombre(bean.getDescripcion());
			procesoVersion.setEstado(bean.getEstado());
			procesoVersion.setCreacionfecha(hoy);
			procesoVersion.setCreacionusuario(usuario);
			procesoVersion.setWebcomponente(bean.getComponenteweb());
			procesoVersion.setApi(bean.getApi());
			procesoVersion.setSpver(bean.getSpver());
			procesoVersion.setOrigendatosid(bean.getOrigen());
			procesoVersion.setSegmentocodigotabla(bean.getSegmentocodigotabla());
			procesoVersion.setFlgplanificaciongenerar(bean.isFlagPlanificacionGenerar() ? "S" : "N");
			procesoVersion.setAdministradorId(bean.getAdministradorId());
			procesoVersion.setNivelestadoidinicial(bean.getNivelestadoidinicial());
			procesoVersion.setComunicacionFlgAlerta(bean.isFlagComunicacionAlerta() ? "S" : "N");
			procesoVersion.setFlgCorreoNiveles(bean.isFlagCorreoNiveles() ? "S" : "N");
			procesoVersion.setUuid(UUID.randomUUID().toString());

			wfProcesoVersionDaoImpl.registrar(procesoVersion);

		} else if (nuevaVersion) {
			WfProcesos proceso = wfProcesoDaoImpl.obtenerPorId(new WfProcesosPk(bean.getProceso()));
			if (proceso != null) {
				proceso.setNombre(bean.getDescripcion());
				proceso.setEstado(bean.getEstado());
				proceso.setAplicacionid(bean.getAplicacion());
				proceso.setModificacionfecha(hoy);
				proceso.setModificacionusuario(usuario);

				wfProcesoDaoImpl.actualizar(proceso);

				procesoVersion = new WfProcesoVersiones();
				procesoVersion.getPk().setProcesoid(bean.getProceso());
				procesoVersion.getPk().setVersionid(wfProcesoVersionDaoImpl.generarVersion(bean.getProceso()));
				procesoVersion.setNombre(bean.getDescripcion());
				procesoVersion.setEstado(bean.getEstado());
				procesoVersion.setCreacionfecha(hoy);
				procesoVersion.setCreacionusuario(usuario);
				procesoVersion.setWebcomponente(bean.getComponenteweb());
				procesoVersion.setApi(bean.getApi());
				procesoVersion.setSpver(bean.getSpver());
				procesoVersion.setOrigendatosid(bean.getOrigen());
				procesoVersion.setSegmentocodigotabla(bean.getSegmentocodigotabla());
				procesoVersion.setFlgplanificaciongenerar(bean.isFlagPlanificacionGenerar() ? "S" : "N");
				procesoVersion.setAdministradorId(bean.getAdministradorId());
				procesoVersion.setNivelestadoidinicial(bean.getNivelestadoidinicial());
				procesoVersion.setComunicacionFlgAlerta(bean.isFlagComunicacionAlerta() ? "S" : "N");
				procesoVersion.setFlgCorreoNiveles(bean.isFlagCorreoNiveles() ? "S" : "N");

				wfProcesoVersionDaoImpl.registrar(procesoVersion);
			}
		} else {
			WfProcesos proceso = wfProcesoDaoImpl.obtenerPorId(new WfProcesosPk(bean.getProceso()));
			if (proceso != null) {
				proceso.setNombre(bean.getDescripcion());
				proceso.setEstado(bean.getEstado());
				proceso.setAplicacionid(bean.getAplicacion());
				proceso.setModificacionfecha(hoy);
				proceso.setModificacionusuario(usuario);

				procesoVersion = wfProcesoVersionDaoImpl
						.obtenerPorId(new WfProcesoVersionesPk(bean.getProceso(), bean.getVersion()));
				procesoVersion.setNombre(bean.getDescripcion());
				procesoVersion.setWebcomponente(bean.getComponenteweb());
				procesoVersion.setApi(bean.getApi());
				procesoVersion.setSpver(bean.getSpver());
				procesoVersion.setOrigendatosid(bean.getOrigen());
				procesoVersion.setSegmentocodigotabla(bean.getSegmentocodigotabla());
				procesoVersion.setFlgplanificaciongenerar(bean.isFlagPlanificacionGenerar() ? "S" : "N");
				procesoVersion.setAdministradorId(bean.getAdministradorId());
				procesoVersion.setNivelestadoidinicial(bean.getNivelestadoidinicial());
				procesoVersion.setComunicacionFlgAlerta(bean.isFlagComunicacionAlerta() ? "S" : "N");
				procesoVersion.setFlgCorreoNiveles(bean.isFlagCorreoNiveles() ? "S" : "N");

				procesoVersion.setModificacionfecha(hoy);
				procesoVersion.setModificacionusuario(usuario);
				wfProcesoDaoImpl.actualizar(proceso);
			}
		}

		// borrar carpeta de proceso
		String rutaServer = dao.obtenerParametroValorExplicacion("RUTAADJUN", "SY");

		if (!UString.estaVacio(rutaServer)) {
			rutaServer = rutaServer + bean.getProceso() + File.separator + procesoVersion.getPk().getVersionid();
			if (new File(rutaServer).exists()) {
				new File(rutaServer).delete();
			}
			new File(rutaServer).mkdirs();
		}

		// alejo

		for (DtoWfRol dto : bean.getRoles()) {
			WfProcesoRol estado = new WfProcesoRol();
			estado.getPk().setProcesoid(bean.getProceso());
			estado.getPk().setVersionid(procesoVersion.getPk().getVersionid());
			estado.getPk().setTipoAprobadorId(dto.getCodigo());
			estado.setNombre(dto.getDescripcion());
			estado.setCreacionfecha(hoy);
			estado.setCreacionusuario(usuario);
			wfProcesoRolDaoImpl.registrar(estado);
		}

		for (DtoWfTipoDocumento dto : bean.getTipodocumentos()) {
			WfProcesoDocumentos doc = new WfProcesoDocumentos();
			doc.getPk().setProcesoid(bean.getProceso());
			doc.getPk().setTipodocumentoid(dto.getTipodocumento());
			doc.setGrupo(dto.getGrupo());
			doc.setRutaplantilla(dto.getRutaplantilla());
			doc.setEstado("A");
			doc.setCreacionfecha(hoy);
			doc.setCreacionusuario(usuario);
			wfProcesoDocumentoDaoImpl.registrar(doc);
			// escribir en disco
			if (!UString.estaVacio(dto.getArchivoString())) {
				try {
					Files.write(Paths.get(rutaServer + File.separator + dto.getRutaplantilla()),
							Base64.getDecoder().decode(dto.getArchivoString()));
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Error al escribir la plantilla");
				}
			}

		}

		for (DtoWfEstado dto : bean.getEstados()) {
			WfProcesoEstados estado = new WfProcesoEstados();
			estado.getPk().setProcesoid(bean.getProceso());
			estado.getPk().setEstadoid(dto.getEstado());
			estado.setNombre(dto.getDescripcion());
			estado.setEstado("A");
			estado.setTipoId(dto.getTipo());
			estado.setCreacionfecha(hoy);
			estado.setCreacionusuario(usuario);
			wfProcesoEstadoDaoImpl.registrar(estado);
		}

		for (DtoWfVariable dto : bean.getVariables()) {
			WfProcesoVariables variable = new WfProcesoVariables();
			variable.getPk().setProcesoid(bean.getProceso());
			variable.getPk().setVariableid(dto.getVariable());
			variable.setEstado("A");
			variable.setCreacionfecha(hoy);
			variable.setCreacionusuario(usuario);
			wfProcesoVariableDaoImpl.registrar(variable);
		}

		for (DtoWfFlujo dto : bean.getFlujos()) {
			WfProcesoFlujos flujo = new WfProcesoFlujos();
			flujo.getPk().setProcesoid(procesoVersion.getPk().getProcesoid());
			flujo.getPk().setVersionid(procesoVersion.getPk().getVersionid());
			flujo.getPk().setFlujoid(dto.getFlujo().intValue());
			flujo.setNombre(dto.getDescripcion());
			flujo.setEstado("A");
			flujo.setCreacionfecha(hoy);
			flujo.setCreacionusuario(usuario);
			wfProcesoFlujoDaoImpl.registrar(flujo);

			int iCon = 1;

			for (DtoWfFlujoConfiguracion dtoConfig : dto.getConfiguraciones()) {

				String comparacion = dtoConfig.getComparacion();

				WfProcesoFlujoConfiguraciones config = new WfProcesoFlujoConfiguraciones();
				config.getPk().setProcesoid(procesoVersion.getPk().getProcesoid());
				config.getPk().setVersionid(procesoVersion.getPk().getVersionid());
				config.getPk().setFlujoid(flujo.getPk().getFlujoid());
				config.getPk().setConfiguracionid(iCon);
				config.setVariableid(dtoConfig.getVariable());
				config.setComparacion(comparacion.equals("BW") ? ">" : dtoConfig.getComparacion());
				String valores = "";
				for (String valor : dtoConfig.getValores()) {
					valores = valores + valor + ";";
				}
				if (valores.length() > 0) {
					valores = valores.substring(0, valores.length() - 1);
				}
				if (comparacion.equals("BW")) {
					config.setValores(dtoConfig.getValores().get(0));
				} else {
					config.setValores(valores);
				}
				config.setRelacion(null);
				config.setEstado("A");
				config.setCreacionfecha(hoy);
				config.setCreacionusuario(usuario);
				wfProcesoFlujoConfiguracionDaoImpl.registrar(config);
				iCon++;

				if (comparacion.equals("BW")) {
					WfProcesoFlujoConfiguraciones config2 = new WfProcesoFlujoConfiguraciones();
					config2.getPk().setProcesoid(procesoVersion.getPk().getProcesoid());
					config2.getPk().setVersionid(procesoVersion.getPk().getVersionid());
					config2.getPk().setFlujoid(flujo.getPk().getFlujoid());
					config2.getPk().setConfiguracionid(iCon);
					config2.setVariableid(dtoConfig.getVariable());
					config2.setComparacion(comparacion.equals("BW") ? "<=" : dtoConfig.getComparacion());
					config2.setValores(dtoConfig.getValores().get(1));
					config2.setRelacion(iCon - 1);
					config2.setEstado("A");
					config2.setCreacionfecha(hoy);
					config2.setCreacionusuario(usuario);
					wfProcesoFlujoConfiguracionDaoImpl.registrar(config2);
					iCon++;
				}
			}

			// int iNivel = 1;
			// Que se guie del nivel configurado en angular, caso nivel 0

			for (DtoWfFlujoNivel dtoNivel : dto.getNiveles()) {
				WfProcesoFlujosNiveles nivel = new WfProcesoFlujosNiveles();
				nivel.getPk().setProcesoid(procesoVersion.getPk().getProcesoid());
				nivel.getPk().setVersionid(procesoVersion.getPk().getVersionid());
				nivel.getPk().setFlujoid(flujo.getPk().getFlujoid());
				nivel.getPk().setNivelid(dtoNivel.getNivel());
				nivel.setEstado("A");
				nivel.setEstadoid(dtoNivel.getEstado());
				nivel.setFlgcorreojefe(dtoNivel.isCorreojefe() ? "S" : "N");
				nivel.setFlgcorreosolicitante(dtoNivel.isCorreosolicitante() ? "S" : "N");

				nivel.setFlgbotonaprobar(dtoNivel.isBtnAprobar() ? "S" : "N");
				nivel.setFlgbotondevolver(dtoNivel.isBtnDevolver() ? "S" : "N");
				nivel.setFlgbotonrechazar(dtoNivel.isBtnRechazar() ? "S" : "N");
				nivel.setFlgnotificar(dtoNivel.isBtnNotificar() ? "S" : "N");

				nivel.setFlgplanificacioneditable(dtoNivel.isAuxFlagPlanificacionEditar() ? "S" : "N");
				nivel.setFlgplanificacionver(dtoNivel.isAuxFlagPlanificacionVer() ? "S" : "N");
				nivel.setNombre(dtoNivel.getNombre());
				nivel.setDuracioncantidad(dtoNivel.getDuracioncantidad());
				nivel.setDuraciontipo(dtoNivel.getDuraciontipo());
				/****/
				nivel.setPlanificacionTag(dtoNivel.getPlanificacionTag());
				nivel.setFlgPlanificacionValidar(dtoNivel.isAuxFlgPlanificacionValidar() ? "S" : "N");

				nivel.setCorreootros(dtoNivel.getCorreootros());
				nivel.setSpvalidar(dtoNivel.getSpvalidar());
				nivel.setSpaprobar(dtoNivel.getSpaprobar());
				nivel.setSprechazar(dtoNivel.getSprechazar());
				nivel.setSpdevolver(dtoNivel.getSpdevolver());
				nivel.setApi(dtoNivel.getApi());
				nivel.setOrigendatosid(dtoNivel.getOrigenDatos());
				nivel.setWebcomponente(dtoNivel.getComponenteweb());
				nivel.setCreacionfecha(hoy);
				nivel.setCreacionusuario(usuario);
				nivel.setTipoaprobadorid(dtoNivel.getTipoaprobador());
				nivel.setCondicionaprobacionid(dtoNivel.getCondicionaprobacion());

				nivel.setDocumentoFlgColumnaGrupo(dtoNivel.isAuxDocumentoFlgColumnaGrupo() ? "S" : "N");
				nivel.setDocumentoFlgBotonNuevo(dtoNivel.isAuxDocumentoFlgColumnaNuevo() ? "S" : "N");

				nivel.setFlgAprobarComentarioDetallado(dtoNivel.isAuxFlgComentarioDetalladoAprobar() ? "S" : "N");
				nivel.setFlgRechazarComentarioDetallado(dtoNivel.isAuxFlgComentarioDetalladoRechazar() ? "S" : "N");
				nivel.setFlgDevolverComentarioDetallado(dtoNivel.isAuxFlgComentarioDetalladoDevolver() ? "S" : "N");

				nivel.setFlgCorreoPersonaReferencia(dtoNivel.isAuxFlgCorreoPersonaReferencia() ? "S" : "N");
				nivel.setFlgCorreoTransaccion(dtoNivel.isAuxFlgCorreoTransaccion() ? "S" : "N");
				nivel.setFlgCorreoPersona(dtoNivel.isAuxFlgCorreoPersona() ? "S" : "N");

				nivel.setFlgAprobarComentario(dtoNivel.isAuxFlgAprobarComentario() ? "S" : "N");

				nivel.setFlgCorreoAdjuntarDocumentos(dtoNivel.isAuxFlgCorreoAdjuntarDocumentos() ? "S" : "N");

				wfProcesoFlujoNivelDaoImpl.registrar(nivel);
				// iNivel++;

				for (DtoWfFlujoNivelDocumento dtoDoc : dtoNivel.getDocumentos()) {
					WfProcesoFlujoNivelDocumentos documento = new WfProcesoFlujoNivelDocumentos();
					documento.getPk().setProcesoid(procesoVersion.getPk().getProcesoid());
					documento.getPk().setVersionid(procesoVersion.getPk().getVersionid());
					documento.getPk().setFlujoid(flujo.getPk().getFlujoid());
					documento.getPk().setNivelid(nivel.getPk().getNivelid());
					documento.getPk().setTipodocumentoid(dtoDoc.getTipodocumento());
					documento.setFlgrequerido(dtoDoc.isRequerido() ? "S" : "N");
					documento.setFlgfirmaelectronica(dtoDoc.isFirmadigital() ? "S" : "N");
					documento.setFlgfirmaimagen(dtoDoc.isFirmaimagen() ? "S" : "N");
					documento.setFlgeditable(dtoDoc.isEditable() ? "S" : "N");
					documento.setEstado("A");
					documento.setCreacionfecha(hoy);
					documento.setCreacionusuario(usuario);
					wfProcesoFlujoNivelDocumentoDaoImpl.registrar(documento);
				}

				for (DtoWfFlujoNivelAprobador dtoApr : dtoNivel.getAprobadores()) {
					WfProcesoFlujoNivelAprobadores aprobador = new WfProcesoFlujoNivelAprobadores();
					aprobador.getPk().setProcesoid(procesoVersion.getPk().getProcesoid());
					aprobador.getPk().setVersionid(procesoVersion.getPk().getVersionid());
					aprobador.getPk().setFlujoid(flujo.getPk().getFlujoid());
					aprobador.getPk().setNivelid(nivel.getPk().getNivelid());
					aprobador.getPk().setPersonaid(dtoApr.getEmpleado().intValue());
					aprobador.setEstado("A");
					String valores = "";
					for (String valor : dtoApr.getValores()) {
						valores = valores + valor + ";";
					}
					if (valores.length() > 0) {
						valores = valores.substring(0, valores.length() - 1);
					}
					aprobador.setSegmentoaprobar(valores);
					aprobador.setCreacionfecha(hoy);
					aprobador.setCreacionusuario(usuario);
					wfProcesoFlujoNivelAprobadorDaoImpl.registrar(aprobador);
				}

				for (DtoWfFlujoNivelAccion dtoAcc : dtoNivel.getAcciones()) {
					WfProcesoFlujoNivelAcciones accion = new WfProcesoFlujoNivelAcciones();
					accion.getPk().setProcesoid(procesoVersion.getPk().getProcesoid());
					accion.getPk().setVersionid(procesoVersion.getPk().getVersionid());
					accion.getPk().setFlujoid(flujo.getPk().getFlujoid());
					accion.getPk().setNivelid(nivel.getPk().getNivelid());
					accion.getPk().setAccionid(dtoAcc.getAccion());
					accion.setNombre(dtoAcc.getNombre());
					accion.setAccionwf(dtoAcc.getAccionwf());
					accion.setNivelDestinoId(dtoAcc.getNivelDestinoId());
					accion.setEstado("A");
					accion.setEstadosubaccion(dtoAcc.getSubaccion());
					accion.setCreacionfecha(hoy);
					accion.setCreacionusuario(usuario);
					wfProcesoFlujoNivelAccionDaoImpl.registrar(accion);
				}

			}
		}

		return new DtoTabla();
	}

	@Transactional
	public DtoTabla actualizaProceso(DtoWfProceso bean, SeguridadUsuarioActual usuarioActual, boolean esnuevaversion)
			throws UException {
		eliminarProceso(bean.getProceso(), bean.getVersion().intValue());
		return registrarProceso(bean, usuarioActual, false, esnuevaversion);
	}

	private void eliminarProceso(String proceso, Integer version) {
		dao.eliminarProceso(proceso, version);
	}

	public List<DtoTabla> listarSelector(String id) {
		return dao.listarSelector(id);
	}

	public DominioPaginacion transaccionListarAprobacion(FiltroSolicitudes filtro,
			SeguridadUsuarioActual usuarioActual) {
		return dao.transaccionListarAprobacion(usuarioActual, filtro);

	}

	public List<DominioMensajeUsuario> transaccionEjecutar(DtoFlujoEjecutar ejecutar,
			SeguridadUsuarioActual usuarioActual) throws Exception {
		logger.debug("transaccionEjecutar");
		logger.debug(ejecutar.getListaSolicitudes().size());
		List<DominioMensajeUsuario> mensajes = new ArrayList<DominioMensajeUsuario>();
		Date hoy = new Date();

		for (DtoFlujoSolicitud solicitud : ejecutar.getListaSolicitudes()) {

			String accionEjecutada = null;

			WfTransacciones wfTransaccion = dao.obtenerPorUUID(solicitud.getUuid());
			// como ya no viene, ponerlo por si abajo lo usan
			solicitud.setTransaccion(wfTransaccion.getTransaccionid());

			// Validar si el nivel de la solicitud ya cambio de nivel
			int nivelBD = wfTransaccion.getNivelid() == null ? 0 : wfTransaccion.getNivelid().intValue();
			if (solicitud.getNivelActual().intValue() != nivelBD) {
				mensajes.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "La transaccion ya ha sido revisada"));
				return mensajes;
			}

			logger.debug("solicitud :: " + wfTransaccion.getTransaccionid());

			WfProcesoVersiones wfProcesoVersion = wfProcesoVersionDaoImpl
					.obtenerPorId(new WfProcesoVersionesPk(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid()));
			int nivelPrevio = wfTransaccion.getNivelid().intValue();

			logger.debug("wfTransaccion :: " + wfTransaccion);
			logger.debug("nivelPrevio :: " + nivelPrevio);

			Integer nivelActualDevol = wfTransaccion.getNivelid() + 1;

			wfTransaccion.setModificacionusuario(usuarioActual.getUsuario());
			wfTransaccion.setModificacionfecha(hoy);

			DtoTransaccionValidaciones dtoTransaccionAccionesAdicionales = dao
					.obtenerAccionesAdicionales(wfTransaccion.getTransaccionid(), wfTransaccion.getNivelid());

			if (UString.estaVacio(dtoTransaccionAccionesAdicionales.getApi())) {
				dtoTransaccionAccionesAdicionales.setApi(
						dao.obtenerApiCabecera(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid()).getApi());
			}

			logger.debug("dtoTransaccionAccionesAdicionales :: " + dtoTransaccionAccionesAdicionales.getApi());

			mensajes = validarTransaccion(ejecutar.getAccion(), ejecutar.getSubaccion(), solicitud.getAdjuntos(),
					wfTransaccion, dtoTransaccionAccionesAdicionales, usuarioActual);

			logger.debug("validarTransaccion :: " + mensajes.size());

			if (mensajes.size() > 0) {
				return mensajes;
			}

			logger.debug("ejecutar.getAccion() :: " + ejecutar.getAccion());
			logger.debug("ejecutar.getSubaccion() :: " + ejecutar.getSubaccion());

			switch (ejecutar.getAccion()) {

			case "APROBAR":
				accionEjecutada = aprobarTransaccion(ejecutar.getSubaccion(), wfTransaccion,
						dtoTransaccionAccionesAdicionales, solicitud.getObservaciones(), usuarioActual);
				break;
			case "DEVOLVER":
				accionEjecutada = devolverTransaccion(ejecutar.getSubaccion(), wfTransaccion,
						dtoTransaccionAccionesAdicionales, solicitud.getObservaciones(), usuarioActual);
				break;
			case "RECHAZAR":
				accionEjecutada = rechazarTransaccion(ejecutar.getSubaccion(), wfTransaccion,
						dtoTransaccionAccionesAdicionales, solicitud.getObservaciones(), usuarioActual);
				break;

			default:
				break;
			}

			dtoTransaccionAccionesAdicionales = dao.obtenerAccionesAdicionales(wfTransaccion.getTransaccionid(),
					wfTransaccion.getNivelid());

			if (UString.estaVacio(dtoTransaccionAccionesAdicionales.getApi())) {
				dtoTransaccionAccionesAdicionales.setApi(
						dao.obtenerApiCabecera(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid()).getApi());
			}

			List<DominioParametroPersistencia> metadatosAdicionales = new ArrayList<DominioParametroPersistencia>();
			metadatosAdicionales.add(new DominioParametroPersistencia("p_usuario_accion_anterior", String.class,
					usuarioActual.getUsuario()));
			metadatosAdicionales
					.add(new DominioParametroPersistencia("p_motivo", String.class, solicitud.getObservaciones()));

			if (nivelPrevio != wfTransaccion.getNivelid().intValue()) {
				// Enviar al nivel los adjuntos
				// Configs nivel actual
				WfProcesoFlujosNiveles nivelNConfigs = wfProcesoFlujoNivelDaoImpl.obtenerPorId(
						new WfProcesoFlujosNivelesPk(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid(),
								wfTransaccion.getFlujoid(), wfTransaccion.getNivelid() + 1));

				String mandarAdjuntos = "N";

				if (nivelNConfigs != null) {
					mandarAdjuntos = nivelNConfigs.getFlgCorreoAdjuntarDocumentos() == null ? "N"
							: nivelNConfigs.getFlgCorreoAdjuntarDocumentos().trim();
				}

				enviarCorreo(dtoTransaccionAccionesAdicionales.getApi(), accionEjecutada, wfTransaccion,
						ejecutar.getAccion().equals("DEVOLVER") ? nivelActualDevol : wfTransaccion.getNivelid(),
						metadatosAdicionales, solicitud,
						(mandarAdjuntos.equals("S") ? solicitud.getAdjuntos() : new ArrayList<DtoFlujoAdjunto>()),
						wfProcesoVersion, ejecutar.getAccion());
			}

			if (accionEjecutada.equals("RECHAZAR")) {
				// cuando se rechaza, se busca el estado en
				// select ESTADO_ID from sgworkflowsys.WF_PROCESO_ESTADO where PROCESO_ID = 'T1'
				// AND TIPO_ID = 'RECH'
				String estadoRechazado = wfProcesoEstadoDaoImpl.obtenerEstadoCesado(wfTransaccion.getProcesoid());

				String mandarAdjuntos = "N";

				enviarCorreo(dtoTransaccionAccionesAdicionales.getApi(), accionEjecutada, wfTransaccion, 0,
						metadatosAdicionales, solicitud,
						(mandarAdjuntos.equals("S") ? solicitud.getAdjuntos() : new ArrayList<DtoFlujoAdjunto>()),
						wfProcesoVersion, ejecutar.getAccion());

				logger.debug("estado rechazado() " + estadoRechazado);

				if (!UString.estaVacio(estadoRechazado)) {
					wfTransaccion.setNivelEstadoId(estadoRechazado);
				}
				// DARIO se pone a null dado que ya no debe salir en otra bandeja
				wfTransaccion.setNivelEstadoSiguienteId(null);
			} else if (accionEjecutada.equals("DEVOLVER") && UString.estaVacio(ejecutar.getSubaccion())) {
				logger.debug("DEVOLVER !!!!!!!!!");
				String est = dao.obtenerAnteriorEstadoInterno(wfTransaccion.getTransaccionid(),
						wfTransaccion.getProcesoid(), nivelPrevio);
				logger.debug("setNivelEstadoId :: " + est);
				// String nivelActual = dao.obtenerEstadoActualNivel(wfTransaccion);
				String nivelActual = wfTransaccion.getNivelEstadoId();
				logger.debug("setNivelEstadoSiguienteId :: " + nivelActual);
				wfTransaccion.setNivelEstadoId(est);
				wfTransaccion.setNivelEstadoSiguienteId(nivelActual);
			} else {
				// actualizar sl estado
				logger.debug("obtenerEstadoSiguienteNivel(proceso) :: " + wfTransaccion.getProcesoid());
				logger.debug("obtenerEstadoSiguienteNivel(version) :: " + wfTransaccion.getVersionid());
				logger.debug("obtenerEstadoSiguienteNivel(flujo) :: " + wfTransaccion.getFlujoid());
				logger.debug("obtenerEstadoSiguienteNivel(nivel) :: " + wfTransaccion.getNivelid());
				String nivelActual = dao.obtenerEstadoActualNivel(wfTransaccion);
				String nivelSiguiente = dao.obtenerEstadoSiguienteNivel(wfTransaccion);
				logger.debug("obtenerEstadoActualNivel() :: " + nivelActual);
				logger.debug("obtenerEstadoSiguienteNivel() :: " + nivelSiguiente);
				wfTransaccion.setNivelEstadoId(nivelActual);
				wfTransaccion.setNivelEstadoSiguienteId(nivelSiguiente);
			}

		}

		return mensajes;

	}

	private List<DominioMensajeUsuario> validarTransaccion(String accion, String subaccion,
			List<DtoFlujoAdjunto> adjuntos, WfTransacciones transaccion,
			DtoTransaccionValidaciones dtoTransaccionValidaciones, SeguridadUsuarioActual usuarioActual) {

		List<DominioMensajeUsuario> validacionesAdjuntos = validarAdjuntos(accion, adjuntos, transaccion);

		if (validacionesAdjuntos.size() > 0) {
			return validacionesAdjuntos;
		}

		List<DominioMensajeUsuario> validacionesSP = validarSP(accion, subaccion,
				dtoTransaccionValidaciones.getSpvalidar(), transaccion);

		if (validacionesSP.size() > 0) {
			return validacionesSP;
		}

		List<DominioMensajeUsuario> validacionesAPI = validarAPI(accion, subaccion, dtoTransaccionValidaciones.getApi(),
				transaccion);

		if (validacionesAPI.size() > 0) {
			return validacionesAPI;
		}

		return new ArrayList<DominioMensajeUsuario>();
	}

	private List<DominioMensajeUsuario> validarAdjuntos(String accion, List<DtoFlujoAdjunto> adjuntos,
			WfTransacciones transaccion) {

		List<DominioMensajeUsuario> mensajes = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals("APROBAR")) {

			List<DtoFlujoAdjuntoValidar> lst = obtenerTipoDocumentosPorNivel(transaccion.getProcesoid(),
					transaccion.getVersionid(), transaccion.getFlujoid().intValue(),
					transaccion.getNivelid().intValue() + 1);

			for (DtoFlujoAdjuntoValidar validacion : lst) {

				String tipoDocumento = validacion.getTipodocumento();
				String requerido = validacion.getRequerido();
				String firmaElectronica = validacion.getFirmaelectronica();
				String firmaImagen = validacion.getFirmaimagen();

				if (UString.estaVacio(requerido)) {
					requerido = "N";
				}

				if (UString.estaVacio(firmaElectronica)) {
					firmaElectronica = "N";
				}

				if (UString.estaVacio(firmaImagen)) {
					firmaImagen = "N";
				}

				if (requerido.equals("S") && adjuntos.stream().filter(x -> x.getTipodocumento().equals(tipoDocumento))
						.collect(Collectors.toList()).size() == 0) {
					mensajes.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA,
							"Se debe incluir un adjunto de tipo " + validacion.getTipodocumentodescripcion()));
				}

				if (firmaElectronica.equals("S") && adjuntos.stream()
						.filter(x -> x.getTipodocumento().equals(tipoDocumento) && x.getFirmaElectronica().equals("S"))
						.collect(Collectors.toList()).size() == 0) {
					mensajes.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "El adjunto de tipo "
							+ validacion.getTipodocumentodescripcion() + " debe estar firmado electronicamente"));
				}

				if (firmaImagen.equals("S") && adjuntos.stream()
						.filter(x -> x.getTipodocumento().equals(tipoDocumento) && x.getFirmaImagen().equals("S"))
						.collect(Collectors.toList()).size() == 0) {
					mensajes.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "El adjunto de tipo "
							+ validacion.getTipodocumentodescripcion() + " debe estar firmado por imagen"));
				}
			}

			// Validacion por criterio enviado de tipo: APDC (Aprobacion documentaria)
			// si es AP no valida nada
			// si es FE deben estar los docs firmados electronicamente
			// si es FI deben estar los docs firmados por imagen

			Map<String, String> parametros = null;
			List<DtoFlujoConfiguracion> criteriosPorEvaluarPorRequest = new ArrayList<DtoFlujoConfiguracion>();
			try {
				parametros = new ObjectMapper().readValue(transaccion.getCriterios(), Map.class);
			} catch (Exception e) {
				parametros = new HashMap<>();
				e.printStackTrace();
			}

			for (Map.Entry<String, String> item : parametros.entrySet()) {
				// Si tiene el criterio
				if (item.getKey().equals("APDC")) {
					String modoEvaluacion = item.getValue();
					for (DtoFlujoAdjunto adj : adjuntos) {
						if (modoEvaluacion.equals("AP")) {
							// Nada
						} else if (modoEvaluacion.equals("FE")) {
							if (!adj.getFirmaElectronica().equals("S")) {
								mensajes.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "El adjunto de "
										+ adj.getArchivonombre() + " debe estar firmado electronicamente"));
							}
						} else if (modoEvaluacion.equals("FI")) {
							if (!adj.getFirmaImagen().equals("S")) {
								mensajes.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA,
										"El adjunto de " + adj.getArchivonombre() + " debe estar firmado por imagen"));
							}
						}
					}
				}
			}

			if (mensajes.size() > 0) {
				return mensajes;
			}
		}

		return mensajes;
	}

	public List<DtoFlujoAdjuntoValidar> obtenerTipoDocumentosPorNivel(String proceso, Integer version, Integer flujo,
			Integer nivel) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, flujo));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, nivel));

		List validacionesAjuntos = dao.listarPorQuery(DtoFlujoAdjuntoValidar.class,
				"wftransacciones.validacionesAjuntos", parametros);
		return (List<DtoFlujoAdjuntoValidar>) validacionesAjuntos;
	}

	public List<DtoFlujoAdjuntoValidar> obtenerTipoDocumentosPorNivelPadre(String proceso, Integer version,
			Integer flujo, Integer nivel) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, flujo));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, nivel));

		List validacionesAjuntos = dao.listarPorQuery(DtoFlujoAdjuntoValidar.class,
				"wftransacciones.validacionesAjuntosPadre", parametros);
		return (List<DtoFlujoAdjuntoValidar>) validacionesAjuntos;
	}

	private List<DominioMensajeUsuario> validarSP(String accion, String subaccion, String sp,
			WfTransacciones transaccion) {
		List<DominioMensajeUsuario> validaciones = new ArrayList<DominioMensajeUsuario>();
		if (UString.estaVacio(sp)) {
			return validaciones;
		}

		String uuld = UUID.randomUUID().toString().substring(0, 20);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(
				new DominioParametroPersistencia("p_transaccion", BigDecimal.class, transaccion.getTransaccionid()));
		parametros.add(new DominioParametroPersistencia("p_uuid", String.class, uuld));
		parametros.add(new DominioParametroPersistencia("p_accion", String.class, accion));
		parametros.add(new DominioParametroPersistencia("p_subaccion", String.class, subaccion));

		sp = "CALL " + sp + "(:p_uuid, :p_accion, :p_subaccion, :p_transaccion)";
		dao.ejecutarPorSentenciaSQL(new StringBuilder(sp), parametros);

		List<DominioParametroPersistencia> parametrosUUID = new ArrayList<DominioParametroPersistencia>();
		parametrosUUID.add(new DominioParametroPersistencia("p_uuid", String.class, uuld));

		List ls = dao.listarPorQuery(DtoTabla.class, "wftransacciones.obtenervalidaciones", parametrosUUID);

		dao.ejecutarPorQuery("wftransacciones.eliminarvalidaciones", parametrosUUID);

		if (ls.size() > 0) {
			for (Object object : ls) {
				DtoTabla dt = (DtoTabla) object;
				validaciones.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, dt.getDescripcion()));
			}
		}

		return validaciones;
	}

	private List<DominioMensajeUsuario> validarAPI(String accion, String subaccion, String api,
			WfTransacciones transaccion) {
		List<DominioMensajeUsuario> validaciones = new ArrayList<DominioMensajeUsuario>();
		if (UString.estaVacio(api)) {
			return validaciones;
		}

		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(api + "/wfvalidar");
			WorkFlowResultado workFlowTransaccion = new WorkFlowResultado();
			workFlowTransaccion.setTransaccionid(transaccion.getTransaccionid().intValue());
			workFlowTransaccion.setAccion(accion);
			workFlowTransaccion.setSubaccion(subaccion);
			workFlowTransaccion.setCodigoproceso(transaccion.getProcesoid());
			workFlowTransaccion.setNivel(transaccion.getNivelid());
			HttpEntity<WorkFlowResultado> request = new HttpEntity<WorkFlowResultado>(workFlowTransaccion,
					this.getHeaders());
			ResponseEntity<List> result = restTemplate.exchange(uri, HttpMethod.POST, request, List.class);
			validaciones = (List<DominioMensajeUsuario>) result.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			validaciones.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
		}

		return validaciones;
	}

	public String aprobarTransaccion(String subaccion, WfTransacciones wfTransaccion,
			DtoTransaccionValidaciones dtoTransaccionAccionesAdicionales, String motivoAprobacion,
			SeguridadUsuarioActual usuarioActual) throws Exception {

		// campo de seguimiento
		if (!UString.estaVacio(motivoAprobacion)) {
			String observaciones = UString.trimSinNulo(wfTransaccion.getObservacionaprobacion());
			observaciones = UString.trimSinNulo(usuarioActual.getUsuario()) + ": "
					+ UString.trimSinNulo(motivoAprobacion) + "\n";
			wfTransaccion.setObservacionaprobacion(observaciones);
		}
		// campo de seguimiento

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, wfTransaccion.getProcesoid()));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, wfTransaccion.getVersionid()));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, wfTransaccion.getFlujoid()));
		List maxx = dao.listarPorQuery(DtoWfFlujoNivel.class, "wftransacciones.aprobartope", parametros);

		logger.debug("wftransaccion.aprobartope() :: " + maxx);

		int max = 0;
		String aprobacionfinal = "N";

		if (maxx.size() > 0) {
			max = ((DtoWfFlujoNivel) maxx.get(0)).getNivel().intValue();
		}

		String sp = dtoTransaccionAccionesAdicionales.getSpaprobar();
		String apiaccion = aprobacionfinal.equals("S") ? SpringWorkflowConstanteBoot.WF_ACCION_APROBAR
				: SpringWorkflowConstanteBoot.WF_ACCION_SEGUIMIENTO;

		// si hay subaccion, se va a cambiar el nivel actual para que sea uno menor al
		// destino
		if (!UString.estaVacio(subaccion)) {
			Integer nivelDestino = dao.obtenerDestino(wfTransaccion, subaccion);
			if (nivelDestino != null) {

				registrarSeguimiento(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid(),
						wfTransaccion.getFlujoid(), wfTransaccion.getTransaccionid().intValue(), "A",
						wfTransaccion.getModificacionusuario(), "Aprobado Nivel " + (wfTransaccion.getNivelid() + 1)
								+ ", " + UString.obtenerSinNulo(motivoAprobacion),
						(wfTransaccion.getNivelid() + 1), null);

				ejecutarSP(sp, wfTransaccion.getTransaccionid(), apiaccion, subaccion, wfTransaccion.getNivelid(),
						dtoTransaccionAccionesAdicionales, wfTransaccion, usuarioActual, motivoAprobacion,
						wfTransaccion.getFlujoid(), wfTransaccion.getNivelEstadoId(), null);

				ejecutarAPI(dtoTransaccionAccionesAdicionales.getApi(), apiaccion.toLowerCase(),
						wfTransaccion.getTransaccionid(), subaccion, wfTransaccion.getProcesoid(), motivoAprobacion,
						null);

				// colocando nivel
				wfTransaccion.setNivelid(nivelDestino);

				// si es el ultimo, se aprueba
				if (nivelDestino == max) {
					wfTransaccion.setEstado("A");
					return "APROBAR";
				} else {
					return "SEGUIMIENTO";
				}
			}
		}

		// CONDICION APROBACION INICIO
		String segmentoAprobado = "";
		String segmentoAprobadoCodigos = "";
		String segmentoNecesarioParaAvanzar = "";
		boolean haAvanzado = true;

		Integer actualNivelId = wfTransaccion.getNivelid();
		String actualNivelEstadoId = wfTransaccion.getNivelEstadoId();

		WfProcesoVersiones beanprocesoversion = wfProcesoVersionDaoImpl
				.obtenerPorId(new WfProcesoVersionesPk(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid()));

		DtoWfProceso dtoProceso = dao.obtenerProcesoPorId(beanprocesoversion.getUuid());

		WfProcesoFlujosNiveles nivelaprobar = wfProcesoFlujoNivelDaoImpl
				.obtenerPorId(new WfProcesoFlujosNivelesPk(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid(),
						wfTransaccion.getFlujoid().intValue(), wfTransaccion.getNivelid().intValue() + 1));

		if (nivelaprobar == null) {
			nivelaprobar = new WfProcesoFlujosNiveles();
		}

		String tipoAprobador = nivelaprobar.getTipoaprobadorid();

		if (UString.estaVacio(tipoAprobador)) {
			tipoAprobador = SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_PERS;
		}

		String condicionAprobacion = nivelaprobar.getCondicionaprobacionid();

		if (UString.estaVacio(condicionAprobacion)) {
			condicionAprobacion = SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_SOLOUNO;
		}

		switch (condicionAprobacion) {
		case SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_SOLOUNO:
			// Se avanza al tener la aprobacion actual
			wfTransaccion.setNivelid((wfTransaccion.getNivelid().intValue() + 1));
			break;
		case SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_ALMENOS:
			// Se avanza al tener la aprobacion actual, no se tiene campo para definir al
			// menos cuentos, por default se considera 1
			wfTransaccion.setNivelid((wfTransaccion.getNivelid().intValue() + 1));
			break;
		case SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_TODOS:
			// Se avanza al tener todas las aprobaciones del nivel

			boolean tieneTodasLasAprobaciones = true;

			if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_PERS)) {
				// Buscar a todos los aprobadores

				List<WfProcesoFlujoNivelAprobadores> aprobadores = wfProcesoFlujoNivelAprobadorDaoImpl.getCriteria()
						.add(Restrictions.eq("pk.procesoid", wfTransaccion.getProcesoid()))
						.add(Restrictions.eq("pk.versionid", wfTransaccion.getVersionid()))
						.add(Restrictions.eq("pk.flujoid", wfTransaccion.getFlujoid().intValue()))
						.add(Restrictions.eq("pk.nivelid", wfTransaccion.getNivelid().intValue() + 1)).list();

				// obtener las aprobaciones del seguimiento
				List<DtoTabla> aprobacionSeguimiento = dao.obtenerSeguimientoAprobacionesXNivel(
						wfTransaccion.getTransaccionid(), wfTransaccion.getNivelid().intValue() + 1);

				DtoTabla actual = new DtoTabla();
				actual.setId(usuarioActual.getPersonaId());
				aprobacionSeguimiento.add(actual);

				// Validar si todos han aprobado

				for (WfProcesoFlujoNivelAprobadores aprobador : aprobadores) {
					if (aprobacionSeguimiento.stream()
							.filter(x -> x.getId().intValue() == aprobador.getPk().getPersonaid().intValue())
							.collect(Collectors.toList()).size() == 0) {
						tieneTodasLasAprobaciones = false;
					}
				}

			} else if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_JEIN)) {
				// Solo es un aprobador por nivel
				tieneTodasLasAprobaciones = true;
			} else if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_TRAP)) {
				// Solo es un aprobador por nivel
				// pos no, ahora en trasnaccion_aprobador pueden haber mas de un apuntando al
				// mismo nivel

				// BUSCAR A TODOS LOS APROBADORES
				List<WfTransaccionaprobador> aprobadores1 = wfTransaccionaprobadorDaoImpl.getCriteria()
						.add(Restrictions.eq("pk.transaccionid", wfTransaccion.getTransaccionid()))
						.add(Restrictions.eq("pk.nivelid", wfTransaccion.getNivelid().intValue() + 1)).list();

				List<WfTransaccionaprobador> aprobadores2 = wfTransaccionaprobadorDaoImpl.getCriteria()
						.add(Restrictions.eq("pk.transaccionid", wfTransaccion.getTransaccionid()))
						.add(Restrictions.eq("pk.nivelid", -1)).list();

				List<WfTransaccionaprobador> aprobadores = new ArrayList<WfTransaccionaprobador>();

				aprobadores.addAll(aprobadores1);
				aprobadores.addAll(aprobadores2);

				// obtener las aprobaciones del seguimiento
				List<DtoTabla> aprobacionSeguimiento = dao.obtenerSeguimientoAprobacionesXNivel(
						wfTransaccion.getTransaccionid(), wfTransaccion.getNivelid().intValue() + 1);

				DtoTabla actual = new DtoTabla();
				actual.setId(usuarioActual.getPersonaId());
				aprobacionSeguimiento.add(actual);

				// Validar si todos han aprobado

				for (WfTransaccionaprobador aprobador : aprobadores) {
					if (aprobacionSeguimiento.stream()
							.filter(x -> x.getId().intValue() == aprobador.getPersonaid().intValue())
							.collect(Collectors.toList()).size() == 0) {
						tieneTodasLasAprobaciones = false;
					}
				}

			} else if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_SOLI)) {
				// Solo es un aprobador por nivel
				tieneTodasLasAprobaciones = true;
			} else if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_REFE)) {
				// Solo es un aprobador por nivel
				tieneTodasLasAprobaciones = true;
			}

			if (tieneTodasLasAprobaciones) {
				wfTransaccion.setNivelid((wfTransaccion.getNivelid().intValue() + 1));
			}

			break;
		case SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_TODOSUNAPR:
			// Solo avanza cuando se tengan aprobados todos los segmentos pendientes
			String segmentosAprobados_ = UString.trimSinNulo(wfTransaccion.getSegmentoAprobado());
			String segmentosAprobados = UString.trimSinNulo(wfTransaccion.getSegmentoAprobado());
			segmentoNecesarioParaAvanzar = wfTransaccion.getSegmentoPendiente();

			String segmentoAprobadorActual = null;

			if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_PERS)) {
				WfProcesoFlujoNivelAprobadores wfProcesoFlujoNivelAprobador = wfProcesoFlujoNivelAprobadorDaoImpl
						.obtenerPorId(new WfProcesoFlujoNivelAprobadoresPk(wfTransaccion.getProcesoid(),
								wfTransaccion.getVersionid(), wfTransaccion.getFlujoid().intValue(),
								wfTransaccion.getNivelid().intValue() + 1, usuarioActual.getPersonaId().intValue()));
				segmentoAprobadorActual = wfProcesoFlujoNivelAprobador.getSegmentoaprobar();

				// Solo considerar los tabs que el usuario ha enviado
				String temp = "";
				for (String t : segmentoAprobadorActual.split(";")) {
					if (segmentoNecesarioParaAvanzar.indexOf(t) > -1) {
						temp += t + ";";
					}
				}

				if (temp.length() > 0) {
					temp = temp.substring(0, temp.length() - 1);
				}

				segmentoAprobadorActual = temp;

			} else if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_JEIN)) {
				// Si es jefe, no se tiene configuracion de segmentos entonces aprueba todos los
				// segmentos
				segmentoAprobadorActual = wfTransaccion.getSegmentoPendiente();
			} else if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_TRAP)) {
				// Si es manual por transaccion, no se tiene configuracion de segmentos entonces
				// aprueba todos los
				// segmentos
				segmentoAprobadorActual = wfTransaccion.getSegmentoPendiente();
			} else if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_SOLI)) {
				// Si es manual por transaccion, no se tiene configuracion de segmentos entonces
				// aprueba todos los
				// segmentos
				segmentoAprobadorActual = wfTransaccion.getSegmentoPendiente();
			} else if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_REFE)) {
				// Si es manual por transaccion, no se tiene configuracion de segmentos entonces
				// aprueba todos los
				// segmentos
				segmentoAprobadorActual = wfTransaccion.getSegmentoPendiente();
			}

			if (UString.estaVacio(segmentoAprobadorActual)) {
				segmentoAprobadorActual = "";
			}

			segmentoAprobadoCodigos = segmentoAprobadorActual;
			segmentosAprobados = segmentosAprobados + ";" + segmentoAprobadorActual;

			wfTransaccion.setSegmentoAprobado(segmentosAprobados);

			// Validar si el segmento aprobado cumple con segmento pendiente

			boolean cumple = true;

			for (String tabNecesarioParaAvanzar : segmentoNecesarioParaAvanzar.split(";")) {
				if (segmentosAprobados.indexOf(tabNecesarioParaAvanzar) == -1) {
					cumple = false;
					haAvanzado = false;
				}
			}

			if (cumple) {
				wfTransaccion.setNivelid((wfTransaccion.getNivelid().intValue() + 1));
			} else {
				logger.debug(
						"El aprobador actual, no cumple los segmentos necesarios para avanzar al siguiente nivel, se mantendra el nivel actual y guardara el seguimiento");
				logger.debug("Segmento necesario : " + segmentoNecesarioParaAvanzar);
				logger.debug("Segmento antes aprobacion: " + segmentosAprobados_);
				logger.debug("Segmento aprobador actual : " + segmentoAprobadorActual);
				logger.debug("Segmento luego aprobacion : " + segmentosAprobados);
			}

			String segmentoTabla = dtoProceso.getSegmentocodigotabla();

			if (!UString.estaVacio(segmentoTabla)) {
				for (String sAprobados : segmentoAprobadorActual.split(";")) {
					if (!UString.estaVacio(sAprobados)) {
						List<DominioParametroPersistencia> parametros2 = new ArrayList<DominioParametroPersistencia>();
						parametros2.add(new DominioParametroPersistencia("p_codigotabla", String.class, segmentoTabla));
						parametros2.add(new DominioParametroPersistencia("p_valor", String.class, sAprobados));
						List lst2 = dao.listarPorQuery(DtoTabla.class, "wftransacciones.obtenerNombreSegmento",
								parametros2);
						segmentoAprobado = segmentoAprobado + ((DtoTabla) lst2.get(0)).getDescripcion() + "; ";
					}
				}

				if (segmentoAprobado.length() > 0) {
					segmentoAprobado = segmentoAprobado.substring(0, segmentoAprobado.length() - 2);
				}
			} else {
				segmentoAprobado = segmentoAprobadorActual;
			}

			break;
		}

		// CONDICION APROBACION FIN

		logger.debug("aprobarTransaccion() :: " + wfTransaccion.getNivelid());

		if (wfTransaccion.getNivelid().intValue() == max) {
			wfTransaccion.setEstado("A");
			aprobacionfinal = "S";
			// uis aca falto poner esto
			apiaccion = SpringWorkflowConstanteBoot.WF_ACCION_APROBAR;
		}

		logger.debug("aprobacionfinal :: " + aprobacionfinal);

		int nivelSeguimiento = wfTransaccion.getNivelid().intValue();

		if (!UString.estaVacio(segmentoAprobado)) {
			if (!haAvanzado) {
				nivelSeguimiento++;
			}
		}

		registrarSeguimiento(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid(), wfTransaccion.getFlujoid(),
				wfTransaccion.getTransaccionid().intValue(), "A", wfTransaccion.getModificacionusuario(),
				"Aprobado Nivel " + (nivelSeguimiento) + ", " + UString.obtenerSinNulo(motivoAprobacion),
				(nivelSeguimiento), segmentoAprobado);

		logger.debug("registrarSeguimiento()");

		logger.debug("sp() " + sp);
		logger.debug("apiaccion() " + apiaccion);
		logger.debug("subaccion() " + subaccion);

		ejecutarSP(sp, wfTransaccion.getTransaccionid(), apiaccion, subaccion, actualNivelId,
				dtoTransaccionAccionesAdicionales, wfTransaccion, usuarioActual, motivoAprobacion,
				wfTransaccion.getFlujoid(), actualNivelEstadoId, null);

		if (apiaccion.equals("APROBAR")) {
			segmentoAprobadoCodigos = segmentoNecesarioParaAvanzar;
		}

		WorkFlowResultado res = ejecutarAPI(dtoTransaccionAccionesAdicionales.getApi(), apiaccion.toLowerCase(),
				wfTransaccion.getTransaccionid(), subaccion, wfTransaccion.getProcesoid(), motivoAprobacion,
				segmentoAprobadoCodigos);

		String mensajeEjecucion = UString.estaVacio(res.getEstado()) ? "OK" : res.getEstado();
		String acciones[] = mensajeEjecucion.split("\\|");
		for (String accion : acciones) {
			if (!UString.estaVacio(accion)) {
				if (accion.equals("APROBARDIRECTO")) {
					aprobacionfinal = "S";
					wfTransaccion.setEstado("A");
				}
			}
		}

		logger.debug("haAvanzado() " + haAvanzado);

		// si ha avanzado, ver si el nuevo nivel requiere notificacion
		if (haAvanzado && aprobacionfinal.equals("N")) {
			String flgNotificar = nivelaprobar.getFlgnotificar();
			if (UString.estaVacio(flgNotificar)) {
				flgNotificar = "S";
			}

			if (flgNotificar.equals("S")) {

				logger.debug("Eliminando anteriores notificaciones()");
				wfTransaccionalertaDaoImpl.eliminarPorTransaccion(wfTransaccion.getTransaccionid());

				logger.debug("Generando notificaciones()");
				// registrar en sgworkflowsys.WF_TRANSACCION_ALERTA
				String usuario = usuarioActual.getUsuario();
				Date fecha = new Date();

				String accionActual = "";
				// obtener el nombre del estado inicial
				accionActual = wfProcesoEstadoDaoImpl
						.obtenerPorId(new WfProcesoEstadosPk(wfTransaccion.getProcesoid(), nivelaprobar.getEstadoid()))
						.getNombre();
				logger.debug("Accion notificacion : " + accionActual);
				String link = nivelaprobar.getWebcomponente();
				if (UString.estaVacio(link)) {
					link = wfProcesoVersionDaoImpl.obtenerPorId(
							new WfProcesoVersionesPk(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid()))
							.getWebcomponente();
				}
				logger.debug("Link notificacion : " + link);
				// obtener a los aprobadores
				List<DtoTabla> aprobadores = dao.obtenerIdAprobadores(wfTransaccion.getTransaccionid(),
						wfTransaccion.getVersionid(), nivelaprobar.getPk().getNivelid() + 1, wfTransaccion.getFlujoid(),
						wfTransaccion.getProcesoid(), wfTransaccion.getSolicitanteid());
				logger.debug("Aprobadores x notificacion : " + aprobadores.size());
				for (DtoTabla row : aprobadores) {
					if (row != null) {
						WfTransaccionalerta alerta = new WfTransaccionalerta();
						alerta.setNombre(wfTransaccion.getReferencia() + " ha sido " + accionActual);
						alerta.setLink(link);
						alerta.setPersonaId(row.getId());
						alerta.setProcesoId(wfTransaccion.getProcesoid());
						alerta.setTransaccionId(wfTransaccion.getTransaccionid());
						alerta.setAccion("APROBAR");
						alerta.setSubaccion(null);
						alerta.setReferencia(wfTransaccion.getReferencia());
						alerta.setEstado("A");
						alerta.setCreacionFecha(fecha);
						alerta.setCreacionUsuario(usuario);
						wfTransaccionalertaDaoImpl.registrar(alerta);
					}
				}
			}
		}

		return aprobacionfinal.equals("S") ? "APROBAR" : "SEGUIMIENTO";
	}

	private void registrarSeguimiento(String proceso, Integer version, Integer flujo, Integer transaccion,
			String estado, String usuario, String observacion, Integer nivel, String segmentoAprobador) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, flujo));
		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, transaccion));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class,
				obtenerEstado(transaccion, proceso, flujo, estado, version)));
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, usuario));
		parametros.add(new DominioParametroPersistencia("p_observacion", String.class, observacion));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, nivel));
		parametros.add(new DominioParametroPersistencia("p_segmento", String.class, segmentoAprobador));

		List<DominioParametroPersistencia> parametrosSeguimiento = new ArrayList<DominioParametroPersistencia>();
		parametrosSeguimiento.add(new DominioParametroPersistencia("p_transaccion", Integer.class, transaccion));
		Integer nuevaSecuencia = dao.contar("wftransacciones.obtenermaximoseguimiento", parametrosSeguimiento);
		parametros.add(new DominioParametroPersistencia("p_secuencia", Integer.class, nuevaSecuencia));

		dao.ejecutarPorQuery("wftransacciones.registrarSeguimiento", parametros);

	}

	private String obtenerEstado(Integer transaccion, String proceso, Integer flujo, String estado, Integer version) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, flujo));
		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, transaccion));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, estado));

		List ls = dao.listarPorQuery(DtoTabla.class, "wftransacciones.obtenerEstadoAprobacion", parametros);

		return ((DtoTabla) ls.get(0)).getCodigo();

	}

//	private void ejecutarSP(String sp, BigDecimal transaccion, String subaccion) {
//		if (UString.estaVacio(sp)) {
//			return;
//		}
//		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
//		parametros.add(new DominioParametroPersistencia("p_transaccion", BigDecimal.class, transaccion));
//		sp = "CALL " + sp + "(:p_subaccion, :p_transaccion)";
//
//		logger.debug("sp() " + sp);
//
//		dao.ejecutarPorSentenciaSQL(new StringBuilder(sp), parametros);
//
//		logger.debug("dao.ejecutarPorSentenciaSQL()");
//	}

	private void ejecutarSP(String sp, Integer transaccion, String accion, String subaccion, Integer nivel,
			DtoTransaccionValidaciones adc, WfTransacciones wfTransaccion, SeguridadUsuarioActual usuarioActual,
			String observacion, Integer flujoId, String nivelEstadoId, Integer nivelId) throws Exception {
		if (UString.estaVacio(sp)) {
			return;
		}
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_accion", String.class, accion));
		parametros.add(new DominioParametroPersistencia("p_subaccion", String.class, subaccion));
		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, transaccion));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, nivel));
		parametros.add(new DominioParametroPersistencia("p_nivelestadoid", String.class, nivelEstadoId));
		parametros.add(new DominioParametroPersistencia("p_flujoid", Integer.class, flujoId));
		parametros.add(new DominioParametroPersistencia("p_comentario", String.class, observacion));
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, usuarioActual.getUsuario()));

		if (UBigDecimal.esCeroOrNulo(adc.getOrigenDatos())) {
			// sp = "CALL " + sp + "(:p_accion, :p_subaccion, :p_transaccion, :p_nivel)";
			sp = "EXEC " + sp + " '" + accion + "', '" + UString.obtenerSinNulo(subaccion) + "','" + transaccion
					+ "', '" + UInteger.obtenerValorEnteroSinNulo(flujoId) + "' ";
			sp = sp + ", '" + UInteger.obtenerValorEnteroSinNulo(nivel) + "','" + UString.obtenerSinNulo(nivelEstadoId)
					+ "'";
			sp = sp + ", '" + UString.obtenerSinNulo(observacion) + "','"
					+ UString.obtenerSinNulo(usuarioActual.getUsuario()) + "'";
			logger.debug("sp(): " + sp);
			dao.ejecutarPorSentenciaSQL(new StringBuilder(sp));
			logger.debug("dao.ejecutarPorSentenciaSQL()");
		} else {
			List<DominioParametroPersistencia> parametrosOrigenExterno = new ArrayList<DominioParametroPersistencia>();
			parametrosOrigenExterno.add(new DominioParametroPersistencia("personaIdActual", Integer.class,
					usuarioActual.getPersonaId().intValue()));
			parametrosOrigenExterno
					.add(new DominioParametroPersistencia("usuarioActual", String.class, usuarioActual.getUsuario()));
			parametrosOrigenExterno.add(new DominioParametroPersistencia("idTransaccion", Integer.class, transaccion));
			parametrosOrigenExterno.add(new DominioParametroPersistencia("comentarios", String.class, observacion));
			parametrosOrigenExterno.add(new DominioParametroPersistencia("nivelAprobacion", Integer.class, nivel));
			parametrosOrigenExterno
					.add(new DominioParametroPersistencia("codigoProceso", String.class, wfTransaccion.getProcesoid()));

			parametrosOrigenExterno.add(new DominioParametroPersistencia("p_accion", String.class, accion));
			parametrosOrigenExterno.add(new DominioParametroPersistencia("p_subaccion", String.class, subaccion));

			String compania = "";
			Map<String, String> xx = new ObjectMapper().readValue(wfTransaccion.getCriterios(), Map.class);
			for (Entry<String, String> item : xx.entrySet()) {
				logger.debug("key " + item.getKey() + " value " + item.getValue());
				if (item.getKey().equals("COMP")) {
					compania = item.getValue();
				}
			}
			parametrosOrigenExterno
					.add(new DominioParametroPersistencia("companiaCodigoProceso", String.class, compania));

			for (DominioParametroPersistencia row : parametrosOrigenExterno) {
				if (row.getClase() == String.class) {
					sp = sp.replace(":" + row.getCampo(), "'" + row.getValor() + "'");
				}
				if (row.getClase() == Integer.class) {
					sp = sp.replace(":" + row.getCampo(), "" + row.getValor() + "");
				}
			}
			ejecutarSentenciaExterna(adc.getCadenaconexion(), adc.getDriver(), sp);
		}

	}

	public void ejecutarSentenciaExterna(String cadena, String driver, String sp) throws Exception {
		Class.forName(driver);
		Connection conexion = DriverManager.getConnection(cadena);
		PreparedStatement sentencia = conexion.prepareStatement(sp);
		sentencia.executeUpdate();
	}

	private WorkFlowResultado ejecutarAPI(String api, String accion, Integer transaccion, String subaccion,
			String proceso, String observacion, String segmentos) {
		WorkFlowResultado workFlowTransaccion = new WorkFlowResultado();
		workFlowTransaccion.setTransaccionid(transaccion.intValue());
		workFlowTransaccion.setAccion(accion);
		workFlowTransaccion.setSubaccion(subaccion);
		workFlowTransaccion.setCodigoproceso(proceso);
		workFlowTransaccion.setObservaciones(observacion);
		workFlowTransaccion.setSegmentosAprobados(segmentos);

		if (UString.estaVacio(api)) {
			workFlowTransaccion.setEstado("OK");
			return workFlowTransaccion;
		}
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(api + "/wf" + accion);
			logger.debug("workFlowTransaccion() URL" + api + "/wf" + accion);
			HttpEntity<WorkFlowResultado> request = new HttpEntity<WorkFlowResultado>(workFlowTransaccion,
					this.getHeaders());
			workFlowTransaccion = restTemplate.exchange(uri, HttpMethod.POST, request, WorkFlowResultado.class)
					.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String estado = workFlowTransaccion.getEstado();

		logger.debug("workFlowTransaccion() " + (UString.estaVacio(estado) ? "" : workFlowTransaccion.getEstado()));

		return workFlowTransaccion;
	}

	public String devolverTransaccion(String subaccion, WfTransacciones wfTransaccion,
			DtoTransaccionValidaciones dtoTransaccionAccionesAdicionales, String motivoDevolucion,
			SeguridadUsuarioActual usuarioActual) throws Exception {

		// campo de seguimiento
		if (!UString.estaVacio(motivoDevolucion)) {
			String observaciones = UString.trimSinNulo(wfTransaccion.getObservacionaprobacion());
			observaciones = UString.trimSinNulo(usuarioActual.getUsuario()) + ": "
					+ UString.trimSinNulo(motivoDevolucion) + "\n";
			wfTransaccion.setObservacionaprobacion(observaciones);
		}
		// campo de seguimiento

		// si hay subaccion, se va a cambiar el nivel actual para que sea uno menor al
		// destino
		if (!UString.estaVacio(subaccion)) {
			Integer nivelDestino = dao.obtenerDestino(wfTransaccion, subaccion);
			if (nivelDestino != null) {

				registrarSeguimiento(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid(),
						wfTransaccion.getFlujoid(), wfTransaccion.getTransaccionid().intValue(), "A",
						wfTransaccion.getModificacionusuario(), "Devuelto Nivel " + (wfTransaccion.getNivelid() + 1)
								+ ", " + UString.obtenerSinNulo(motivoDevolucion),
						(wfTransaccion.getNivelid() + 1), null);

				ejecutarSP(dtoTransaccionAccionesAdicionales.getSpdevolver(), wfTransaccion.getTransaccionid(),
						SpringWorkflowConstanteBoot.WF_ACCION_DEVOLVER, subaccion, wfTransaccion.getNivelid(),
						dtoTransaccionAccionesAdicionales, wfTransaccion, usuarioActual, motivoDevolucion,
						wfTransaccion.getFlujoid(), wfTransaccion.getNivelEstadoId(), null);

				ejecutarAPI(dtoTransaccionAccionesAdicionales.getApi(), "devolver", wfTransaccion.getTransaccionid(),
						subaccion, wfTransaccion.getProcesoid(), motivoDevolucion, null);

				// colocando nivel
				wfTransaccion.setNivelid(nivelDestino);

				return "DEVOLVER";
			}
		}

		boolean terminarFlujo = false;
		Integer actualNivelId = wfTransaccion.getNivelid();
		String actualNivelEstadoId = wfTransaccion.getNivelEstadoId();

		WfProcesoFlujosNiveles nivelDevolviendo = wfProcesoFlujoNivelDaoImpl
				.obtenerPorId(new WfProcesoFlujosNivelesPk(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid(),
						wfTransaccion.getFlujoid().intValue(), wfTransaccion.getNivelid().intValue() + 1));

		String condicionAprobacion = nivelDevolviendo.getCondicionaprobacionid();

		if (UString.estaVacio(condicionAprobacion)) {
			condicionAprobacion = SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_SOLOUNO;
		}

		switch (condicionAprobacion) {
		case SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_SOLOUNO:
			break;
		case SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_ALMENOS:
			break;
		case SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_TODOS:
			break;
		case SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_TODOSUNAPR:
			terminarFlujo = true;
			break;
		}

		if (terminarFlujo) {
			// Se mantiene el nivel actual
			// Se termina la transaccion
			wfTransaccion.setEstado("R");
		} else {
			if (wfTransaccion.getNivelid().intValue() > 0) {
				wfTransaccion.setNivelid((wfTransaccion.getNivelid().intValue() - 1));
			}
		}

		registrarSeguimiento(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid(), wfTransaccion.getFlujoid(),
				wfTransaccion.getTransaccionid().intValue(), "D", wfTransaccion.getModificacionusuario(),
				"Devuelto Nivel " + (wfTransaccion.getNivelid().intValue() + 1) + ", "
						+ UString.obtenerSinNulo(motivoDevolucion),
				(wfTransaccion.getNivelid().intValue() + 1), null);

		ejecutarSP(dtoTransaccionAccionesAdicionales.getSpdevolver(), wfTransaccion.getTransaccionid(),
				SpringWorkflowConstanteBoot.WF_ACCION_DEVOLVER, subaccion, actualNivelId,
				dtoTransaccionAccionesAdicionales, wfTransaccion, usuarioActual, motivoDevolucion,
				wfTransaccion.getFlujoid(), actualNivelEstadoId, null);

		ejecutarAPI(dtoTransaccionAccionesAdicionales.getApi(), "devolver", wfTransaccion.getTransaccionid(), subaccion,
				wfTransaccion.getProcesoid(), motivoDevolucion, null);

		return "DEVOLVER";
	}

	public String rechazarTransaccion(String subaccion, WfTransacciones wfTransaccion,
			DtoTransaccionValidaciones dtoTransaccionAccionesAdicionales, String motivoRechazo,
			SeguridadUsuarioActual usuarioActual) throws Exception {

		// campo de seguimiento
		if (!UString.estaVacio(motivoRechazo)) {
			String observaciones = UString.trimSinNulo(wfTransaccion.getObservacionaprobacion());
			observaciones = UString.trimSinNulo(usuarioActual.getUsuario()) + ": " + UString.trimSinNulo(motivoRechazo)
					+ "\n";
			wfTransaccion.setObservacionaprobacion(observaciones);
		}
		// campo de seguimiento

		// CONDICION RECHAZO / APROBACION INICIO
		boolean ejecutarRechazo = false;
		boolean ejecutarAprobacion = false;
		String segmentoRechazado = "";
		Integer actualNivelId = wfTransaccion.getNivelid();
		String actualNivelEstadoId = wfTransaccion.getNivelEstadoId();

		WfProcesoVersiones beanprocesoversion = wfProcesoVersionDaoImpl
				.obtenerPorId(new WfProcesoVersionesPk(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid()));

		DtoWfProceso dtoProceso = dao.obtenerProcesoPorId(beanprocesoversion.getUuid());

		WfProcesoFlujosNiveles nivelRechazando = wfProcesoFlujoNivelDaoImpl
				.obtenerPorId(new WfProcesoFlujosNivelesPk(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid(),
						wfTransaccion.getFlujoid().intValue(), wfTransaccion.getNivelid().intValue() + 1));

		String tipoAprobador = nivelRechazando.getTipoaprobadorid();

		if (UString.estaVacio(tipoAprobador)) {
			tipoAprobador = SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_PERS;
		}

		String condicionAprobacion = nivelRechazando.getCondicionaprobacionid();

		if (UString.estaVacio(condicionAprobacion)) {
			condicionAprobacion = SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_SOLOUNO;
		}

		switch (condicionAprobacion) {
		case SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_SOLOUNO:
			// Se rechaza por default
			ejecutarRechazo = true;
			break;
		case SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_ALMENOS:
			// Se rechaza por default
			ejecutarRechazo = true;
			break;
		case SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_TODOS:
			// Se rechaza por default
			ejecutarRechazo = true;
			break;
		case SpringWorkflowConstanteBoot.WF_CONDICIONAPROBACION_TODOSUNAPR:

			// Se rechazara solo si ya no tiene pendientes

			String segmentoPendiente = wfTransaccion.getSegmentoPendiente();

			String segmentoAprobadorActual = null;

			if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_PERS)) {
				WfProcesoFlujoNivelAprobadores wfProcesoFlujoNivelAprobador = wfProcesoFlujoNivelAprobadorDaoImpl
						.obtenerPorId(new WfProcesoFlujoNivelAprobadoresPk(wfTransaccion.getProcesoid(),
								wfTransaccion.getVersionid(), wfTransaccion.getFlujoid().intValue(),
								wfTransaccion.getNivelid().intValue() + 1, usuarioActual.getPersonaId().intValue()));
				segmentoAprobadorActual = wfProcesoFlujoNivelAprobador.getSegmentoaprobar();

				// Solo considerar los tabs que el usuario ha enviado
				String temp = "";
				for (String t : segmentoAprobadorActual.split(";")) {
					if (segmentoPendiente.indexOf(t) > -1) {
						temp += t + ";";
					}
				}

				if (temp.length() > 0) {
					temp = temp.substring(0, temp.length() - 1);
				}

				segmentoAprobadorActual = temp;

			} else if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_JEIN)) {
				// Si es jefe, no se tiene configuracion de segmentos entonces aprueba todos los
				// segmentos
				segmentoAprobadorActual = wfTransaccion.getSegmentoPendiente();
			} else if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_TRAP)) {
				// Si es manual por transaccion, no se tiene configuracion de segmentos entonces
				// aprueba todos los
				// segmentos
				segmentoAprobadorActual = wfTransaccion.getSegmentoPendiente();
			} else if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_SOLI)) {
				// Si es manual por transaccion, no se tiene configuracion de segmentos entonces
				// aprueba todos los
				// segmentos
				segmentoAprobadorActual = wfTransaccion.getSegmentoPendiente();
			} else if (tipoAprobador.equals(SpringWorkflowConstanteBoot.WF_TIPOAPROBADOR_REFE)) {
				// Si es manual por transaccion, no se tiene configuracion de segmentos entonces
				// aprueba todos los
				// segmentos
				segmentoAprobadorActual = wfTransaccion.getSegmentoPendiente();
			}

			if (UString.estaVacio(segmentoAprobadorActual)) {
				segmentoAprobadorActual = "";
			}

			// Al segmento pendiente, quitarle los tabs del aprobador actual, pero si otro
			// aprobador ya lo aprobo no quitarlo
			List<String> lstSegmentosPendientes = new ArrayList<String>();

			for (String tabPendiente : segmentoPendiente.split(";")) {
				lstSegmentosPendientes.add(tabPendiente);
			}

			for (String tabDelAprobadorActual : segmentoAprobadorActual.split(";")) {
				// Se quita solo si no fue aprobado anteriormente
				if (UString.trimSinNulo(wfTransaccion.getSegmentoAprobado()).indexOf(tabDelAprobadorActual) == -1) {
					lstSegmentosPendientes = lstSegmentosPendientes.stream()
							.filter(x -> !x.equals(tabDelAprobadorActual)).collect(Collectors.toList());
				}
			}

			// Asignar el nuevo pendiente
			String _ts = "";
			for (String lsP : lstSegmentosPendientes) {
				_ts = _ts + lsP + ";";
			}
			if (_ts.length() > 0) {
				_ts = _ts.substring(0, _ts.length() - 1);
			}
			wfTransaccion.setSegmentoPendiente(_ts);

			// Obtener la descripcion de los tabs rechazados
			String segmentoTabla = dtoProceso.getSegmentocodigotabla();
			if (!UString.estaVacio(segmentoTabla)) {
				for (String sAprobados : segmentoAprobadorActual.split(";")) {
					List<DominioParametroPersistencia> parametros2 = new ArrayList<DominioParametroPersistencia>();
					parametros2.add(new DominioParametroPersistencia("p_codigotabla", String.class, segmentoTabla));
					parametros2.add(new DominioParametroPersistencia("p_valor", String.class, sAprobados));
					List lst2 = dao.listarPorQuery(DtoTabla.class, "wftransacciones.obtenerNombreSegmento",
							parametros2);
					segmentoRechazado = segmentoRechazado + ((DtoTabla) lst2.get(0)).getDescripcion() + "; ";
				}

				if (segmentoRechazado.length() > 0) {
					segmentoRechazado = segmentoRechazado.substring(0, segmentoRechazado.length() - 2);
				}
			} else {
				segmentoRechazado = segmentoAprobadorActual;
			}

			// Si luego de quitar los segmentos actuales, se queda vacio entonces rechazar
			if (UString.estaVacio(wfTransaccion.getSegmentoPendiente())) {
				ejecutarRechazo = true;
			}

			// Y SI LO RECHAZO Y AHORA LO PENDIENTE ES SUFICIENTE PARA APROBAR ??
			// :oooooooooooooooooooooooooo

			boolean cumple = true;

			for (String tabNecesarioParaAvanzar : wfTransaccion.getSegmentoPendiente().split(";")) {
				if (UString.trimSinNulo(wfTransaccion.getSegmentoAprobado()).indexOf(tabNecesarioParaAvanzar) == -1) {
					cumple = false;
				}
			}

			if (cumple && !ejecutarRechazo) {
				ejecutarAprobacion = true;
			}

			break;
		}

		// CONDICION APROBACION FIN

		if (ejecutarAprobacion) {
			// Caso cuando el aprobador rechaza, pero desencadena una aprobacion
			return aprobarTransaccion(subaccion, wfTransaccion, dtoTransaccionAccionesAdicionales, motivoRechazo,
					usuarioActual);
		}

		if (ejecutarRechazo) {
			ejecutarSP(dtoTransaccionAccionesAdicionales.getSprechazar(), wfTransaccion.getTransaccionid(),
					SpringWorkflowConstanteBoot.WF_ACCION_RECHAZAR, subaccion, actualNivelId,
					dtoTransaccionAccionesAdicionales, wfTransaccion, usuarioActual, motivoRechazo,
					wfTransaccion.getFlujoid(), actualNivelEstadoId, null);

			WorkFlowResultado res = ejecutarAPI(dtoTransaccionAccionesAdicionales.getApi(), "rechazar",
					wfTransaccion.getTransaccionid(), subaccion, wfTransaccion.getProcesoid(), motivoRechazo, null);

			String mensajeEjecucion = UString.estaVacio(res.getEstado()) ? "OK" : res.getEstado();

			String acciones[] = mensajeEjecucion.split("\\|");

			boolean rechazar = true;
			boolean retroceder = false;

			for (String accion : acciones) {
				if (!UString.estaVacio(accion)) {
					if (accion.equals("NORECHAZAR")) {
						rechazar = false;
					}
					if (accion.equals("RETROCEDERNIVEL")) {
						retroceder = true;
					}
				}
			}

			if (rechazar) {
				wfTransaccion.setEstado("R");
			}

			if (retroceder) {
				int nivel = wfTransaccion.getNivelid().intValue();
				nivel--;
				wfTransaccion.setNivelid((nivel == 0 ? 0 : nivel));
			}

			motivoRechazo = UString.estaVacio(motivoRechazo)
					? ("Rechazado Nivel " + (wfTransaccion.getNivelid().intValue() + 1))
					: motivoRechazo;

		}

		registrarSeguimiento(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid(), wfTransaccion.getFlujoid(),
				wfTransaccion.getTransaccionid().intValue(), "R", wfTransaccion.getModificacionusuario(), motivoRechazo,
				(wfTransaccion.getNivelid().intValue() + 1), segmentoRechazado);

		return "RECHAZAR";
	}

	private void enviarCorreo(String api, String accion, WfTransacciones wfTransaccion, Integer nivelAprobadores,
			List<DominioParametroPersistencia> metadatosAdicionales, DtoFlujoSolicitud solicitud,
			List<DtoFlujoAdjunto> adjuntos, WfProcesoVersiones WfProcesoVersiones, String workFlowAccion) {

		logger.debug("enviarCorreo:" + wfTransaccion.getTransaccionid());
		logger.debug("enviarCorreo:" + accion);
		logger.debug("enviarCorreo:" + nivelAprobadores);
		logger.debug("enviarCorreo:" + api);

		Integer nivelActual = nivelAprobadores;
		if (solicitud != null && UBoolean.validarFlag(solicitud.getConEmailPreparado())) {
			// cuando ya me mandan el correo y solo queda enviar
			try {
				solicitud.getEmail().setTraceReferencia(wfTransaccion.getTransaccionid().toString());

				solicitud.getEmail().setProcesoId(wfTransaccion.getProcesoid());
				solicitud.getEmail().setTransaccionId(wfTransaccion.getTransaccionid());
				solicitud.getEmail().setReferenciaId(wfTransaccion.getTransaccionid().toString());
				solicitud.getEmail().setAccionId(accion);

				correoEnviar(solicitud.getEmail());
				logger.debug("correo enviado : ");
			} catch (Exception e) {
				logger.debug("Error al enviar correo " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			// armando el correo
			// "APROBA"
			// "SEGUIM"
			// "RECHAZ"
			// "DEVOLV"
			nivelAprobadores = nivelAprobadores + 1;

			// si estamos en el ultmio nivel, notificar al solicitante

			List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
			parametros.add(new DominioParametroPersistencia("p_proceso", String.class, wfTransaccion.getProcesoid()));
			parametros.add(new DominioParametroPersistencia("p_version", Integer.class, wfTransaccion.getVersionid()));
			parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, wfTransaccion.getFlujoid()));
			List maxx = dao.listarPorQuery(DtoWfFlujoNivel.class, "wftransacciones.aprobartope", parametros);

			int max = 0;
			String aprobacionfinal = "N";

			if (maxx.size() > 0) {
				max = ((DtoWfFlujoNivel) maxx.get(0)).getNivel().intValue();
			}

			if (nivelAprobadores > max) {
				aprobacionfinal = "S";
				DtoTransaccionValidaciones dtoTransaccionAccionesAdicionales = dao
						.obtenerAccionesAdicionales(wfTransaccion.getTransaccionid(), nivelAprobadores - 2);
				if (UString.estaVacio(dtoTransaccionAccionesAdicionales.getApi())) {
					dtoTransaccionAccionesAdicionales.setApi(dao
							.obtenerApiCabecera(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid()).getApi());
				}
				api = dtoTransaccionAccionesAdicionales.getApi();

			}

			// 1 obtener destinatarios
			List<DtoTabla> destinatarios = new ArrayList<DtoTabla>();

			logger.debug("aprobacionfinal :" + aprobacionfinal);
			if (aprobacionfinal.equals("S")) {
				destinatarios = dao.obtenerCorreoSolicitante(wfTransaccion.getSolicitanteid());
			} else {
				destinatarios = dao.obtenerDestinatarios(wfTransaccion, nivelAprobadores, accion);
			}

			// ALEJANDRO: CAMBIA nivelActual X nivelAprobadores

			// }

			logger.debug("destinatarios transaccion_id:" + wfTransaccion.getTransaccionid().toString());
			logger.debug("destinatarios:" + destinatarios.size());
			logger.debug("destinatarios nivelActual:" + nivelActual);
			logger.debug("destinatarios nivelAprobadores:" + nivelAprobadores);
			if (destinatarios.size() == 0) {
				return;
			}

			for (DtoTabla dtoTabla : destinatarios) {
				logger.debug(dtoTabla.getDescripcion());
			}

			Map<String, Object> metadatos = new HashMap<String, Object>();

			// Map<String, String> metadatos = new HashMap<String, String>();

			if (!UString.estaVacio(api)) {
				// 2 obtener metadatos
				logger.debug("obtenerMetadatosAPI");
				metadatos = obtenerMetadatosAPI(api, armarMetadatos(wfTransaccion, accion));
			}
			logger.debug("metadatos api :" + metadatos.size());

			// 2.1 agregar metadatos adicionales
			if (metadatosAdicionales != null) {
				for (DominioParametroPersistencia dato : metadatosAdicionales) {
					metadatos.put(dato.getCampo(), dato.getValor());
				}
			}
			logger.debug("metadatos + adicionales " + metadatos.size());
			metadatos.put("p_system_receptor_nombre", ",");
			// 3 obtener plantilla
			ReporteTransaccion reporteParametro = new ReporteTransaccion();
			logger.debug("--->>>metadatos");
			logger.debug(metadatos);
			WfProcesos proceso = wfProcesoDaoImpl.obtenerPorId(new WfProcesosPk(wfTransaccion.getProcesoid()));
			reporteParametro.setAplicacionCodigo(proceso.getAplicacionid());
			reporteParametro.setReporteCodigo(wfTransaccion.getProcesoid().substring(0, 2));
			logger.debug("reporteParametro.getAplicacionCodigo() 1:" + reporteParametro.getAplicacionCodigo());
			logger.debug("reporteParametro.getReporteCodigo()    1:" + reporteParametro.getReporteCodigo());
			reporteParametro.setParametros(metadatos);
			reporteParametro.setCompaniaSocio("999999");
			reporteParametro.setPeriodo("999999");
			logger.debug("reporteParametro.version=" + accion.substring(0, 6));
			reporteParametro.setVersion(accion.substring(0, 6));
			reporteParametro.setWorkFlowNivel(nivelActual);
			reporteParametro.setWorkFlowAccion(workFlowAccion);
			reporteParametro.setWorkFlowFlgCorreoNiveles(WfProcesoVersiones.getFlgCorreoNiveles());
			// ENVIO FLUJO ID
			reporteParametro.setWorkFlowFlujo(wfTransaccion.getFlujoid());

			try {
				ReporteTransaccion reporte = reporteEjecutarWorkFlow(reporteParametro);
				String mensajeReporte = UString.estaVacio(reporte.getTransaccionEstado()) ? ""
						: reporte.getTransaccionEstado();
				if (mensajeReporte.equals(DominioTransaccion.ERROR)) {
					logger.debug(reporte.getTransaccionListaMensajes().get(0));
					return;
				}
				logger.debug("reporteEjecutar");
				// 4 enviar correo
				EmailTransaccion email = new EmailTransaccion();
				email.setTraceReferencia(wfTransaccion.getTransaccionid().toString());
				email.setCuerpoCorreoBase64(Base64.getEncoder().encodeToString(reporte.getResultadoCuerpoBinario()));
				List<EmailDestino> destinos = new ArrayList<EmailDestino>();
				for (DtoTabla destinatario : destinatarios) {
					logger.debug("tipo fuente correo : " + destinatario.getCodigo());
					String dest[] = destinatario.getDescripcion().split(";");
					for (String d : dest) {
						if (!UString.estaVacio(d)) {
							EmailDestino destino = new EmailDestino();
							logger.debug("      destino : " + d);
							destino.setCorreoDestino(d);
							destino.setDestino(tipo_destino.TO);
							destinos.add(destino);
						}
					}
				}
				email.setListaCorreoDestino(destinos);
				email.setAsunto(reporte.getResultadoAsunto());
				// Agregando adjuntos
				for (DtoFlujoAdjunto adjunto : adjuntos) {
					logger.debug("WF Adjuntando documentos");
					DominioAdjunto ad = new DominioAdjunto();
					adjunto.setArchivostring(null);
					adjunto.setArchivostring(verAdjunto(adjunto, false).getArchivostring());
					if (adjunto.getArchivostring() != null) {
						logger.debug("Archivo con cuerpo 64: " + adjunto.getArchivonombre());
						ad.setArchivoAdjuntoBase64(adjunto.getArchivostring());
						ad.setNombreArchivo(adjunto.getArchivonombre());
						email.getListaCorreoAdjunto().add(ad);
					} else {
						logger.debug("Archivo sin cuerpo 64: " + adjunto.getArchivonombre());
					}
				}
				try {
					if (email != null) {
						email.setProcesoId(wfTransaccion.getProcesoid());
						email.setTransaccionId(wfTransaccion.getTransaccionid());
						email.setReferenciaId(wfTransaccion.getTransaccionid().toString());
						email.setAccionId(accion);
					}
					if (solicitud != null) {
						if (solicitud.getEmail() != null) {
							solicitud.getEmail().setProcesoId(wfTransaccion.getProcesoid());
							solicitud.getEmail().setTransaccionId(wfTransaccion.getTransaccionid());
							solicitud.getEmail().setReferenciaId(wfTransaccion.getTransaccionid().toString());
							solicitud.getEmail().setAccionId(accion);
						}
					}
					if (email != null)
						correoEnviar(email);
					logger.debug("correo enviado");
				} catch (Exception e) {
					logger.debug("Error al enviar correo " + e.getMessage());
					e.printStackTrace();
				}
			} catch (Exception e) {
				logger.debug("Error al generar reporte" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/*
	 * private ClientHttpRequestFactory getClientHttpRequestFactory() { int timeout
	 * = 5000; HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new
	 * HttpComponentsClientHttpRequestFactory();
	 * clientHttpRequestFactory.setConnectTimeout(timeout); return
	 * clientHttpRequestFactory; }
	 */

	// private HashMap<String, Object> obtenerMetadatosAPI(String api, Integer
	// transaccion) {
	public HashMap<String, Object> obtenerMetadatosAPI(String api, WorkFlowResultado workFlowTransaccion) {
		if (UString.estaVacio(api)) {
			return new HashMap<String, Object>();
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(api + "/wfobtenermetadata");
			// WorkFlowResultado workFlowTransaccion = new WorkFlowResultado();
			// workFlowTransaccion.setTransaccionid(transaccion.intValue());
			HttpEntity<WorkFlowResultado> request = new HttpEntity<WorkFlowResultado>(workFlowTransaccion,
					this.getHeaders());
			logger.debug(api + "/wfobtenermetadata");
			map = (HashMap<String, Object>) restTemplate.exchange(uri, HttpMethod.POST, request, Map.class).getBody();
			map = wfProcesoDaoImpl.agregarParametros(true, map, workFlowTransaccion);
		} catch (Exception e) {
			map = wfProcesoDaoImpl.agregarParametros(false, map, workFlowTransaccion);
			e.printStackTrace();
		}
		return map;
	}

	public DominioPaginacion transaccionListarSolicitante(FiltroTransaccion filtro,
			SeguridadUsuarioActual usuarioActual) {
		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.estaVacio(filtro.getReferencia())) {
			filtro.setReferencia(null);
		}
		if (UString.estaVacio(filtro.getAplicacion())) {
			filtro.setAplicacion(null);
		}
		if (UString.estaVacio(filtro.getProceso())) {
			filtro.setProceso(null);
		}
		if (UString.estaVacio(filtro.getCompania())) {
			filtro.setCompania(null);
		}
		if (UString.estaVacio(filtro.getArea())) {
			filtro.setArea(null);
		}

		if (UInteger.esCeroOrNulo(filtro.getIdPersonaSolicitante2()))
			filtro.setIdPersonaSolicitante2(null);

		if (filtro.getMontodesde() == null)
			filtro.setMontodesde(0.0);

		if (filtro.getMontohasta() == null)
			filtro.setMontohasta(999999999.0);

		parametros.add(new DominioParametroPersistencia("p_est_proceso", String.class, filtro.getEstadoProceso()));

		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompania()));
		parametros.add(new DominioParametroPersistencia("p_area", String.class, filtro.getArea()));
		parametros.add(new DominioParametroPersistencia("p_montoDesde", Double.class, filtro.getMontodesde()));
		parametros.add(new DominioParametroPersistencia("p_montoHasta", Double.class, filtro.getMontohasta()));

		parametros.add(new DominioParametroPersistencia("p_referencia", String.class, filtro.getReferencia()));
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getAplicacion()));
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, filtro.getProceso()));
		parametros.add(new DominioParametroPersistencia("p_desde", Date.class, filtro.getDesde()));
		parametros.add(new DominioParametroPersistencia("p_hasta", Date.class, filtro.getHasta()));
		parametros.add(
				new DominioParametroPersistencia("p_solicitante", Integer.class, getUsuarioActual().getPersonaId()));
		parametros.add(
				new DominioParametroPersistencia("p_2solicitante", Integer.class, filtro.getIdPersonaSolicitante2()));

		cantidadEncontrados = dao.contar("wftransacciones.transaccionListarSolicitanteContar", parametros);

		List lstResultado = dao.listarConPaginacion(filtro.getPaginacion(), parametros,
				"wftransacciones.transaccionListarSolicitantePaginacion", DtoFlujoSolicitud.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);

		return filtro.getPaginacion();
	}

	public WorkFlowResultado registrarExternoCriteriosArmados(DtoFlujoTransaccionRequest request,
			SeguridadUsuarioActual usuarioActual, WorkFlowResultado workFlowResultado,
			List<DominioParametroPersistencia> metadatosIniciales) {
		try {
			DtoFlujoTransaccionResponse transaccionResponse = transaccionIniciar(request, usuarioActual,
					metadatosIniciales);
			if (transaccionResponse.getResultado().equals("0")) {
				workFlowResultado.setEstado("ERROR");
				workFlowResultado.setMensaje(transaccionResponse.getMensaje());
			} else {
				workFlowResultado.setEstado("OK");
				workFlowResultado.setMensaje("Se ha registrado la transaccion");
				workFlowResultado.setCodigoproceso(transaccionResponse.getProceso());
				workFlowResultado.setTransaccionid(transaccionResponse.getTransaccion());
			}
		} catch (Exception e) {
			workFlowResultado.setEstado("ERROR");
			workFlowResultado.setMensaje(e.getMessage());
			e.printStackTrace();
		}

		return workFlowResultado;
	}

	public BigDecimal obtenerConversion(String monedaActual, String monedaDestino, BigDecimal monto,
			BigDecimal tipocambio) {

		logger.debug("obtenerConversion " + monedaActual + '-' + monedaDestino);
		logger.debug(tipocambio);
		logger.debug(monto);

		if (monto == null) {
			return null;
		}

		Double montoI = monto.doubleValue();
		Double tc = tipocambio.doubleValue();

		if (monedaActual.equals(monedaDestino)) {
			return monto;
		}

		if (monedaActual.equals("LO")) {
			montoI = montoI / tc;
		} else {
			montoI = montoI * tc;
		}

		logger.debug(montoI);

		return new BigDecimal(montoI);

	}

	public String armarCriterios(WorkFlowTransaccion request, SeguridadUsuarioActual usuarioActual) {
		String criterios = "";
		if (!UString.estaVacio(request.getCompaniasocioId())) {
			criterios = criterios + "\"COMP\":\"" + request.getCompaniasocioId() + "\", ";
		}
		if (!UString.estaVacio(request.getSucursalId())) {
			criterios = criterios + "\"SUCU\":\"" + request.getSucursalId() + "\", ";
		}
		if (request.getMonto() != null) {
			criterios = criterios + "\"MONT\":\"" + request.getMonto() + "\", ";
		}
		if (!UString.estaVacio(request.getMoneda())) {
			criterios = criterios + "\"MOND\":\"" + request.getMoneda() + "\", ";
		}
		if (!UString.estaVacio(request.getAreaRevisoraId())) {
			criterios = criterios + "\"AREV\":\"" + request.getAreaRevisoraId() + "\", ";
		}
		if (!UString.estaVacio(request.getProyectoId())) {
			criterios = criterios + "\"AFEM\":\"" + request.getProyectoId() + "\", ";
		}
		if (!UString.estaVacio(request.getMotivoReclutamiento())) {
			criterios = criterios + "\"MORE\":\"" + request.getMotivoReclutamiento() + "\", ";
		}
		if (!UString.estaVacio(request.getAsArea())) {
			criterios = criterios + "\"AREA\":\"" + request.getAsArea() + "\", ";
		}
		if (!UString.estaVacio(request.getUnidadOperativa())) {
			criterios = criterios + "\"UNOP\":\"" + request.getUnidadOperativa() + "\", ";
		}
		if (!UString.estaVacio(request.getTipoDocumento())) {
			criterios = criterios + "\"TIDC\":\"" + request.getTipoDocumento() + "\", ";
		}
		/** TIPO DE PERSONA **/
		if (!UString.estaVacio(request.getTipoPersona())) {
			criterios = criterios + "\"TIPE\":\"" + request.getTipoPersona() + "\", ";
		}
		if (!UString.estaVacio(request.getTipoAdjudicacion())) {
			criterios = criterios + "\"TADJ\":\"" + request.getTipoAdjudicacion() + "\", ";
		}
		/** TIPO DE UTILIZCION **/
		if (!UString.estaVacio(request.getTipoUtilizacion())) {
			criterios = criterios + "\"TUTL\":\"" + request.getTipoUtilizacion() + "\", ";
		}

		if (request.getMetadatos() != null) {
			for (DominioParametroPersistencia row : request.getMetadatos()) {
				criterios = criterios + "\"" + row.getCampo() + "\":\"" + row.getValor() + "\", ";
			}
			request.getMetadatos().add(new DominioParametroPersistencia("p_usuario_accion_anterior", String.class,
					usuarioActual.getUsuario()));
		}

		if (UString.estaVacio(criterios)) {
			criterios = "{}";
		} else {
			criterios = criterios.substring(0, criterios.length() - 2);
			criterios = "{" + criterios + "}";
		}
		return criterios;
	}

	public WorkFlowResultado registrarExterno(WorkFlowTransaccion request, SeguridadUsuarioActual usuarioActual)
			throws Exception {
		// TODO Colocar como parametro o en la pantalla de manteniento de proceos
		String monedaProceso = "EX";

		WorkFlowResultado workFlowResultado = new WorkFlowResultado();
		DtoFlujoTransaccionRequest transaccionRequest = new DtoFlujoTransaccionRequest();
		transaccionRequest.setListaAprobador(request.getListaAprobador());
		transaccionRequest.setSegmentoEnviado(request.getSegmentoEnviado());

		// TipoCambioTransaccion tipoCambioTransaccion = new TipoCambioTransaccion();
		// tipoCambioTransaccion.setFecha(new Date());
		// tipoCambioTransaccion.setTipo("EX");
		// tipoCambioTransaccion.setTipoCambio("LO");

		// tipoCambioTransaccion = tipoCambioObtener(tipoCambioTransaccion);

		// logger.debug(tipoCambioTransaccion.getTransaccionEstado());

		// if
		// (!tipoCambioTransaccion.getTransaccionEstado().equals(DominioTransaccion.OK))
		// {
		// workFlowResultado.setEstado("ERR");
		// workFlowResultado.setMensaje("No se tienen configurado el tipo de cambio");
		// return workFlowResultado;
		// }

		// request.setMonto(obtenerConversion(request.getMoneda(), monedaProceso,
		// request.getMonto(),
		// tipoCambioTransaccion.getFactorcompra()));

		request.setMonto(request.getMonto());

		transaccionRequest.setSolicitante(request.getEmpleadosolicitanteId());
		transaccionRequest.setReferencia(request.getDocumentoReferencia());
		transaccionRequest.setReferenciaPadre(request.getDocumentoReferenciaPadre());
		transaccionRequest.setProceso(request.getProcesoId());

		transaccionRequest.setCompaniaid(request.getCompaniasocioId());
		transaccionRequest.setCentrocostosid(request.getCentrocostoId());
		transaccionRequest.setSucursalid(request.getSucursalId());
		transaccionRequest.setProyectoid(request.getProyectoId());
		transaccionRequest.setMonedaid(request.getMoneda());
		transaccionRequest.setAreaid(request.getAreaRevisoraId());
		transaccionRequest.setMonto(request.getMonto());
		transaccionRequest.setPersonaReferencia(request.getPersonaReferenciaid());
		transaccionRequest.setPropietarioId(request.getPropietarioId());
		transaccionRequest.setPropietarioCodigo(request.getPropietarioCodigo());
		transaccionRequest.setMotivoReclutamiento(request.getMotivoReclutamiento());
		transaccionRequest.setTipoUtilizacion(request.getTipoUtilizacion());

		String criterios = armarCriterios(request, usuarioActual);

		transaccionRequest.setCriterios(criterios);

		transaccionRequest.setAdjuntos(new ArrayList<DtoFlujoAdjunto>());

		if (request.getListaAdjuntos() != null) {
			for (DominioAdjunto row : request.getListaAdjuntos()) {
				DtoFlujoAdjunto adj = new DtoFlujoAdjunto();
				adj.setFlagVer(row.getFlagVer());
				adj.setArchivonombre(row.getNombreArchivo());
				adj.setArchivostring(row.getArchivoAdjuntoBase64());
				adj.setTipodocumento(row.getTipoDocumento());
				transaccionRequest.getAdjuntos().add(adj);
			}
		}

		workFlowResultado = registrarExternoCriteriosArmados(transaccionRequest, usuarioActual, workFlowResultado,
				request.getMetadatos());

//		wfMotorMacroTransaccionesServicio.incluirEnMacroNuevaTransaccion(usuarioActual, request, workFlowResultado);

		return workFlowResultado;
	}

	@SuppressWarnings("unchecked")
	public DtoFlujoTransaccionResponse transaccionIniciar(DtoFlujoTransaccionRequest request,
			SeguridadUsuarioActual usuarioActual, List<DominioParametroPersistencia> metadatosIniciales)
			throws UException, IOException {

		DtoFlujoTransaccionResponse response = new DtoFlujoTransaccionResponse();

		Integer ultimaVersion = dao.obtenerVersion(request.getProceso());

		WfProcesoVersiones beanProcesoVersion = wfProcesoVersionDaoImpl
				.obtenerPorId(new WfProcesoVersionesPk(request.getProceso(), ultimaVersion));

		List<DtoFlujo> flujosDisponibles = dao.listarFlujosPorProceso(request.getProceso(), ultimaVersion);

		List<DtoFlujoConfiguracion> criteriosEvaluarPorFlujo = null;
		List<DtoFlujoConfiguracion> criteriosPorEvaluarPorRequest = new ArrayList<DtoFlujoConfiguracion>();
		Map<String, String> parametros = null;
		DtoFlujo flujoSeleccionado = null;

		if (flujosDisponibles.size() == 0) {
			response.setResultado("0");
			response.setMensaje("No se tienen flujos configurados para el proceso");
			return response;
		}

		try {
			parametros = new ObjectMapper().readValue(request.getCriterios(), Map.class);
			for (Map.Entry<String, String> item : parametros.entrySet()) {
				criteriosPorEvaluarPorRequest.add(new DtoFlujoConfiguracion(item.getKey(), item.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultado("0");
			response.setMensaje("La lista de criterios no se puede procesar, debido a un error de formato");
			return response;
		}

		if (criteriosPorEvaluarPorRequest.size() == 0) {
			response.setResultado("0");
			response.setMensaje("No se han enviado los criterios para evaluar");
			return response;
		}

		for (DtoFlujo flujo : flujosDisponibles) {

			criteriosEvaluarPorFlujo = dao.listarCriteriosFlujoPorProceso(request.getProceso(), ultimaVersion,
					flujo.getFlujo());

			boolean valida = true;

			for (DtoFlujoConfiguracion criterioEvaluar : criteriosEvaluarPorFlujo) {

				DtoFlujoConfiguracion valorPorComparar = criteriosPorEvaluarPorRequest.stream().filter(
						criterioPorEvaluar -> criterioPorEvaluar.getVariable().equals(criterioEvaluar.getVariable()))
						.findAny().orElse(new DtoFlujoConfiguracion());

				switch (criterioEvaluar.getComparacion()) {
				case ">":
					valida = valida && comparacionMayorQue(criterioEvaluar.getValores(), valorPorComparar.getValor())
							? true
							: false;
					break;
				case "<=":
					valida = valida
							&& comparacionMenorIgualQue(criterioEvaluar.getValores(), valorPorComparar.getValor())
									? true
									: false;
					break;
				case "IN":
					valida = valida && comparacionDentroDe(criterioEvaluar.getValores(), valorPorComparar.getValor())
							? true
							: false;
					break;
				default:
					throw new UException("El comparador " + criterioEvaluar.getComparacion() + " no se puede evaluar",
							tipo_mensaje.ERROR);
				}

			}

			if (valida) {
				if (flujoSeleccionado != null) {
					response.setResultado("0");
					response.setMensaje("Mas de un flujo coincide con los criterios enviados, "
							+ flujoSeleccionado.getDescripcion() + ", " + flujo.getDescripcion());
					return response;
				}
				flujoSeleccionado = flujo;
			}

		}

		if (flujoSeleccionado == null) {
			response.setResultado("0");
			response.setMensaje("Ninguno de los flujos coincide con los criterios enviados");
			return response;
		}

		// Validar que los adjuntos enviados sean los necesarios si es que tiene nivel 0

		List<DtoFlujoAdjuntoValidar> lstDocumentosNivel0 = obtenerTipoDocumentosPorNivel(request.getProceso(),
				ultimaVersion, flujoSeleccionado.getFlujo(), 0);

		String tiposRequeridos = "";

		for (DtoFlujoAdjuntoValidar documentoRequerido : lstDocumentosNivel0) {
			String tipoDocumento = documentoRequerido.getTipodocumento();
			String requerido = documentoRequerido.getRequerido();
			if (UString.estaVacio(requerido)) {
				requerido = "N";
			}
			if (requerido.equals("S") && request.getAdjuntos().stream()
					.filter(x -> x.getTipodocumento().equals(tipoDocumento)).collect(Collectors.toList()).size() == 0) {
				tiposRequeridos = tiposRequeridos + documentoRequerido.getTipodocumentodescripcion() + ", ";
			}
		}

		if (!UString.estaVacio(tiposRequeridos)) {
			tiposRequeridos = tiposRequeridos.substring(0, tiposRequeridos.length() - 2);
			response.setResultado("0");
			response.setMensaje("Se debe incluir adjunto(s) de tipo: " + tiposRequeridos);
			return response;
		}

		/*
		 * Integer transaccionGenerada =
		 * dao.generarTransaccionPorFlujoProceso(request.getProceso(), ultimaVersion,
		 * flujoSeleccionado.getFlujo());
		 */
		Integer transaccionGenerada = null;

		String nivelSiguiente = dao.obtenerEstadoSiguienteNivel(request.getProceso(), ultimaVersion,
				flujoSeleccionado.getFlujo(), 0);

		logger.debug("nivelSiguiente() " + nivelSiguiente);

		transaccionGenerada = dao.insertarTransaccion(request, ultimaVersion, flujoSeleccionado.getFlujo(),
				transaccionGenerada, usuarioActual.getUsuario(), request.getSegmentoEnviado(),
				beanProcesoVersion.getNivelestadoidinicial(), nivelSiguiente);
		logger.debug("transaccionGenerada:" + transaccionGenerada);

		response.setResultado("1");
		response.setMensaje("Se ha seleccionado el flujo " + flujoSeleccionado.getDescripcion());
		response.setProceso(request.getProceso());
		response.setFlujo(flujoSeleccionado.getFlujo());
		response.setTransaccion(transaccionGenerada);

		registrarSeguimiento(response.getProceso(), ultimaVersion, response.getFlujo(), response.getTransaccion(), "S",
				usuarioActual.getUsuario(), "Registrado", 0, null);

		if (request.getAdjuntos() != null) {
			for (DtoFlujoAdjunto row : request.getAdjuntos()) {
				row.setTransaccion(transaccionGenerada);
				row.settCompania(request.getCompaniaid());
				row.settProceso(request.getProceso());
				row.settReferencia(request.getReferencia());
				registrarAdjunto(row, usuarioActual, transaccionGenerada);
			}
		}

		if (request.getListaAprobador() != null) {
			int i = 1;
			for (WorkFlowAprobadorTransaccion aprobador : request.getListaAprobador()) {
				WfTransaccionaprobador wfTransaccionaprobador = new WfTransaccionaprobador();
				wfTransaccionaprobador.setCreacionfecha(new Date());
				wfTransaccionaprobador.setCreacionusuario(usuarioActual.getUsuario());
				wfTransaccionaprobador.setPersonaid(aprobador.getPersonaId());
				wfTransaccionaprobador.getPk().setTransaccionid(new BigDecimal(transaccionGenerada));
				wfTransaccionaprobador.getPk().setNivelid(aprobador.getNivelId());
				wfTransaccionaprobador.getPk().setDetalleid(new BigDecimal(i));
				wfTransaccionaprobadorDaoImpl.registrar(wfTransaccionaprobador);
				i++;
			}
		}

		WfTransacciones wfTransaccion = dao.obtenerPorId(transaccionGenerada);

		DtoTransaccionValidaciones dtoTransaccionAccionesAdicionales = dao
				.obtenerAccionesAdicionales(wfTransaccion.getTransaccionid(), wfTransaccion.getNivelid().intValue());

		if (UString.estaVacio(dtoTransaccionAccionesAdicionales.getApi())) {
			dtoTransaccionAccionesAdicionales.setApi(
					dao.obtenerApiCabecera(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid()).getApi());
		}

		// Configs nivel 0
		WfProcesoFlujosNiveles nivel0Configs = wfProcesoFlujoNivelDaoImpl.obtenerPorId(new WfProcesoFlujosNivelesPk(
				wfTransaccion.getProcesoid(), ultimaVersion, flujoSeleccionado.getFlujo(), 0));

		String mandarAdjuntos = "N";

		if (nivel0Configs != null) {
			mandarAdjuntos = nivel0Configs.getFlgCorreoAdjuntarDocumentos() == null ? "N"
					: nivel0Configs.getFlgCorreoAdjuntarDocumentos().trim();
		}

		try {
			// Aca mandamos los adjuntos, si es que tiene el flag

			enviarCorreo(dtoTransaccionAccionesAdicionales.getApi(), "GUARDA", wfTransaccion, 0, metadatosIniciales,
					null, (mandarAdjuntos.equals("S") ? request.getAdjuntos() : new ArrayList<DtoFlujoAdjunto>()),
					beanProcesoVersion, "APROBA");				
		} catch (Exception e) {
			logger.debug("error al enviar correo de guarda" + e.getMessage());
			e.printStackTrace();
		}

		// inicio generar primera notificacion
		WfProcesoFlujosNiveles nivelaprobar = wfProcesoFlujoNivelDaoImpl.obtenerPorId(new WfProcesoFlujosNivelesPk(
				wfTransaccion.getProcesoid(), ultimaVersion, flujoSeleccionado.getFlujo(), 1));

		if (UBoolean.validarFlag(nivelaprobar.getFlgnotificar())) {
			logger.debug("Generando notificacion inicial ()");
			// registrar en sgworkflowsys.WF_TRANSACCION_ALERTA
			String usuario = usuarioActual.getUsuario();
			Date fecha = new Date();
//			Integer id = wfTransaccionalertaDaoImpl.generarId();
			// obtener el nombre del estado inicial
			logger.debug("Accion notificacion : " + "Registrado");
			String link = nivelaprobar.getWebcomponente();
			if (UString.estaVacio(link)) {
				link = beanProcesoVersion.getWebcomponente();
			}

			// links proveedor
			if (wfTransaccion.getProcesoid().equals("EM")) {
				String compania = parametros.get("COMP");
				String cotizacionNumero = parametros.get("p_nro_cotizacion");
				link = "whquotation-vendor-edit/3/{\"modoInput\":\"PR\", \"cotizacionnumero\":\"" + cotizacionNumero
						+ "\", \"companiasocio\":\"" + compania + "\"}/1";
			} else if (wfTransaccion.getProcesoid().equals("CP")) {

				String compania = parametros.get("COMP");
				String nroConvocatoria = parametros.get("p_nro_convocatoria");
				String nroVentaBase = parametros.get("p_nroVentaBase");
				String tipoAdj = parametros.get("p_tipoAdj");
				String tipoProceso = parametros.get("p_tipoProceso");

				link = "licitacionprecios-mantenimiento/{\"par_accion\":\"IngresoPrecios\", \"par_proceso\":\"IN\", \"companiasocio\":\""
						+ compania + "\", \"nroconvocatoria\":" + nroConvocatoria + ", \"nroventabase\":" + nroVentaBase
						+ ", \"tipoadj\":\"" + tipoAdj + "\", \"tipoproceso\":\"" + tipoProceso + "\"}";
			} else if (wfTransaccion.getProcesoid().equals("SP")) {
				String compania = parametros.get("COMP");
				String subastaNumero = parametros.get("p_numeroSubasta");
				link = "subastas-puja/{\"companiasocio\":\"" + compania + "\", \"subastanumero\":\"" + subastaNumero
						+ "\"}/V";
			}

			logger.debug("Link notificacion : " + link);
			// obtener a los aprobadores
			List<DtoTabla> aprobadores = dao.obtenerIdAprobadores(wfTransaccion.getTransaccionid(),
					wfTransaccion.getVersionid(), 1, wfTransaccion.getFlujoid(), wfTransaccion.getProcesoid(),
					wfTransaccion.getSolicitanteid());
			logger.debug("Aprobadores x notificacion : " + aprobadores.size());
			for (DtoTabla row : aprobadores) {
				if (row != null) {
					WfTransaccionalerta alerta = new WfTransaccionalerta();
//					alerta.getPk().setAlertaId(id);
					alerta.setNombre(wfTransaccion.getReferencia() + " ha sido Registrado");
					alerta.setLink(link);
					alerta.setPersonaId(row.getId());
					alerta.setProcesoId(wfTransaccion.getProcesoid());
					alerta.setTransaccionId(wfTransaccion.getTransaccionid());
					alerta.setAccion("APROBAR");
					alerta.setSubaccion(null);
					alerta.setReferencia(wfTransaccion.getReferencia());
					alerta.setEstado("A");
					alerta.setCreacionFecha(fecha);
					alerta.setCreacionUsuario(usuario);
					wfTransaccionalertaDaoImpl.registrar(alerta);
//					id++;
				}
			}
		}
		// fin generar primera notificacion

		// si el proceso tiene planificacion, guardar en WF_TRANSACCION_PLANIFICACION x
		// cada nivel asociado a su flujo

		if (UBoolean.validarFlag(beanProcesoVersion.getFlgplanificaciongenerar())) {
			logger.debug("GENERANDO PLANIFICACION");

			List<DtoPlanificacionGenerar> planificacionRaw = dao.generarPlanificacion(wfTransaccion.getProcesoid(),
					ultimaVersion, flujoSeleccionado.getFlujo());
			logger.debug("CANTIDAD PLANIFICACION : " + planificacionRaw.size());

			int i = 1;
			for (DtoPlanificacionGenerar dto : planificacionRaw) {
				WfTransaccionplanificacion pBean = new WfTransaccionplanificacion();
				pBean.getPk().setTransaccionId(transaccionGenerada);
				pBean.getPk().setPlanificacionId(i);
				pBean.setFlujoId(flujoSeleccionado.getFlujo());
				pBean.setNivelId(dto.getNivel());
				pBean.setDuracionTipo(dto.getTipo());
				pBean.setDuracionCantidad(dto.getCantidad());
				pBean.setFechahoraInicio(dto.getInicio());
				pBean.setFechahoraInicioCalculado(dto.getInicio());
				pBean.setFechahoraFin(dto.getFin());
				pBean.setFechahoraFinCalculado(dto.getFin());
				pBean.setTipoGeneracionId("A");
				pBean.setEstado("A");
				pBean.setCreacionUsuario(usuarioActual.getUsuario());
				pBean.setCreacionFecha(new Date());
				wfTransaccionPlanificacionDaoImpl.registrar(pBean);
				i++;
			}

		}

		return response;
	}

	private boolean comparacionDentroDe(String valoresString, String valorString) {
		if (UString.estaVacio(valorString)) {
			return false;
		}
		if (UString.estaVacio(valoresString)) {
			return false;
		}

		List<String> valoresPosibles = Arrays.asList(valoresString.split(";"));

		String encontradoEnValoresPosibles = valoresPosibles.stream()
				.filter(valorPosible -> valorPosible.equals(valorString)).findAny().orElse(null);

		if (encontradoEnValoresPosibles != null) {
			return true;
		}

		return false;
	}

	private boolean comparacionMenorIgualQue(String valoresString, String valorString) throws UException {
		if (UString.estaVacio(valorString)) {
			return false;
		}
		if (UString.estaVacio(valoresString)) {
			return false;
		}

		double valor = 0;
		double valorComparar = 0;

		try {
			valor = Double.parseDouble(valorString);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UException("El valor enviado '" + valorString + "' no puede ser evaluado como un numero",
					tipo_mensaje.ERROR);
		}

		boolean valida = true;

		for (String comparar : valoresString.split(";")) {

			try {
				valorComparar = Double.parseDouble(comparar);
			} catch (Exception e) {
				e.printStackTrace();
				throw new UException(
						"El valor del flujo '" + valorString + "' no puede ser evaluado como un n\u00FAmero",
						tipo_mensaje.ERROR);
			}

			valida = valida && (valor <= valorComparar) ? true : false;
		}

		return valida;
	}

	private boolean comparacionMayorQue(String valoresString, String valorString) throws UException {
		if (UString.estaVacio(valorString)) {
			return false;
		}
		if (UString.estaVacio(valoresString)) {
			return false;
		}

		double valor = 0;
		double valorComparar = 0;

		try {
			valor = Double.parseDouble(valorString);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UException("El valor enviado '" + valorString + "' no puede ser evaluado como un n\u00FAmero",
					tipo_mensaje.ERROR);
		}

		boolean valida = true;

		for (String comparar : valoresString.split(";")) {

			try {
				valorComparar = Double.parseDouble(comparar);
			} catch (Exception e) {
				e.printStackTrace();
				throw new UException(
						"El valor del flujo '" + valorString + "' no puede ser evaluado como un n\u00FAmero",
						tipo_mensaje.ERROR);
			}

			valida = valida && (valor > valorComparar) ? true : false;
		}

		return valida;
	}

	public DtoAprobacionAcciones obtenerAccionesAprobacion(String transaccionUUID, Integer personaId) {
		return dao.obtenerAccionesAprobacion(transaccionUUID, personaId);
	}

	public List<DtoSeguimiento> listarSeguimientoWF(Integer transaccion) {
		WfTransacciones wfTransacciones = dao.obtenerPorId((transaccion));

		List<DominioParametroPersistencia> parametros = new ArrayList();
		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, transaccion));
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, wfTransacciones.getProcesoid()));
		parametros.add(new DominioParametroPersistencia("p_flujo", BigDecimal.class, wfTransacciones.getFlujoid()));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, wfTransacciones.getVersionid()));
		List lstResultado = dao.listarPorQuery(DtoSeguimiento.class, "wftransacciones.listarSeguimientoWF", parametros);
		return (List<DtoSeguimiento>) lstResultado;
	}

	public List<DtoFlujoAdjunto> obtenerAdjuntosAprobacion(String transaccionUUID) {

		WfTransacciones wfTransaccion = dao.obtenerPorUUID(transaccionUUID);

		List<DominioParametroPersistencia> parametros = new ArrayList();

		parametros.add(
				new DominioParametroPersistencia("p_transaccion", Integer.class, wfTransaccion.getTransaccionid()));
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, wfTransaccion.getProcesoid()));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, wfTransaccion.getVersionid()));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, wfTransaccion.getFlujoid()));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, wfTransaccion.getNivelid()));

		List lstResultado = new ArrayList();

		if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_FS)) {
			lstResultado = dao.listarPorQuery(DtoFlujoAdjunto.class, "wftransacciones.obtenerAdjuntosAprobacionFS",
					parametros);
		} else if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_SY)) {
			lstResultado = dao.listarPorQuery(DtoFlujoAdjunto.class, "wftransacciones.obtenerAdjuntosAprobacionSY",
					parametros);
		}

		return (List<DtoFlujoAdjunto>) lstResultado;
	}

	public List<DtoFlujoAdjunto> obtenerAdjuntosAprobacionPadre(Integer transaccionid) {

		WfTransacciones wfTransaccion = dao.obtenerPorId(transaccionid);

		List<DominioParametroPersistencia> parametros = new ArrayList();

		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, transaccionid));
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, wfTransaccion.getProcesoid()));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, wfTransaccion.getVersionid()));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, wfTransaccion.getFlujoid()));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, wfTransaccion.getNivelid()));

		List lstResultado = new ArrayList();

		if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_FS)) {
			lstResultado = dao.listarPorQuery(DtoFlujoAdjunto.class, "wftransacciones.obtenerAdjuntosAprobacionFS",
					parametros);
		} else if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_SY)) {
			lstResultado = dao.listarPorQuery(DtoFlujoAdjunto.class, "wftransacciones.obtenerAdjuntosAprobacionSY",
					parametros);
		}

		return (List<DtoFlujoAdjunto>) lstResultado;
	}

	public DtoFlujoAdjunto registrarAdjunto(DtoFlujoAdjunto bean, SeguridadUsuarioActual usuarioActual,
			Integer transaccionId) throws IOException {

		if (transaccionId == null) {
			WfTransacciones beanWF = dao.obtenerPorUUID(bean.getTransaccionUUID());
			bean.setTransaccion(beanWF.getTransaccionid());
		} else {
			bean.setTransaccion(transaccionId);
		}

		String rutaServer = dao.obtenerParametroValorExplicacion("DIRFILE", "SY") + File.separator;
		String rutaServerOriginal = new String(rutaServer);

		String document3 = "";
		logger.debug("registrarAdjunto().rutaServer :: " + rutaServer);

		if (bean == null) {
			logger.error("no se envio adjuntos");
			return null;
		}

		if (UString.esNuloVacio(bean.getArchivostring())) {
			logger.error("no se envio contenido del archivo adjunto");
			logger.error(bean.getArchivonombre());
			logger.error(bean.getTipodocumento());
			return null;
		}

		Integer secuenciaGenerada = 1;

		if (!UString.estaVacio(rutaServer)) {

			if (!new File(rutaServer + "TRANSACCION").exists()) {
				new File(rutaServer + "TRANSACCION").mkdir();
			}
			if (!new File(rutaServer + "TRANSACCION" + File.separator + bean.getTransaccion()).exists()) {
				new File(rutaServer + "TRANSACCION" + File.separator + bean.getTransaccion()).mkdir();
			}

			String tmp_ = "TRANSACCION" + File.separator + bean.getTransaccion() + File.separator + "SEGUIMIENTO";

			if (!new File(rutaServer + tmp_).exists()) {
				new File(rutaServer + tmp_).mkdir();
			}

			rutaServer = rutaServer + tmp_ + File.separator;
			String rutaDocumento;
			bean.setArchivonombre(UFile.quitarCaracteresExtranos(bean.getArchivonombre()));
			String nombreDocumento = bean.getArchivonombre();

			rutaDocumento = rutaServer + nombreDocumento;

			if (bean.getArchivostring().endsWith("ERROR COM")) {
				String rutaSpring = "C:\\Spring" + File.separator + bean.getArchivonombre();
				byte[] contenido = null;
				if (new File(rutaSpring).exists()) {
					contenido = UFile.obtenerArregloByte(rutaSpring);
				}
				bean.setArchivostring(org.apache.commons.codec.binary.Base64.encodeBase64String(contenido));
				bean.setArchivostring(UFile.obtenerNombreWebDescargar(bean.getArchivostring()));
			}

			String bin = bean.getArchivostring().substring(bean.getArchivostring().indexOf(",") + 1);

			bean.setArchivostring(bin);

			try {

				logger.debug("Paths.get(rutaDocumento) :: " + Paths.get(rutaDocumento));

				Files.write(Paths.get(rutaDocumento), Base64.getDecoder().decode(bean.getArchivostring()));
				bean.setRutaadjunto(rutaDocumento);
				bean.setArchivostring(null);
				bean.setArchivonombre(null);

				if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_FS)) {
					FsDocumento documento = new FsDocumento();
					secuenciaGenerada = fsDocumentoDaoImpl.obtenerSecuencia();
					documento.getPk().setDocumentoId(secuenciaGenerada);
					documento.setWorkflowtransaccionid(bean.getTransaccion());
					documento.setTipodocumentoid(bean.getTipodocumento());
					documento.setNombrereal(nombreDocumento);
					documento.setCodigonumero1id(bean.getCantidadfirmas());
					documento.setCodigotexto1id(bean.getFlagVer());
					documento.setCreacionusuario(usuarioActual.getUsuario());
					documento.setCreacionfecha(new Date());
					documento.setModificacionusuario(usuarioActual.getUsuario());
					documento.setModificacionfecha(new Date());
					fsDocumentoDaoImpl.registrar(documento);
				} else if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_SY)) {
					SyDocumentoAnexos documento = new SyDocumentoAnexos();

					documento.getPk().setModulo("WF");
					documento.getPk().setLinea(bean.getTransaccion());
					secuenciaGenerada = syDocumentoAnexosDaoImpl.obtenerSecuencia("WF", bean.getTransaccion());
					documento.getPk().setSecuencia(secuenciaGenerada);
					documento.getPk().setTipoDocumento(bean.gettProceso());
					documento.getPk().setNumeroDocumento(bean.gettReferencia());
					documento.getPk().setCompaniaSocio(bean.gettCompania());

					documento.setFecha(new Date());
					//documento.setComentario("0"); // cantidad firmas
					//documento.setConcepto(bean.getFlagVer()); // ver
					documento.setRutaArchivo(rutaDocumento.replace(rutaServerOriginal, ""));
					documento.setEstado("A");
					documento.setUltimoUsuario(usuarioActual.getUsuario());
					documento.setUltimaFechaModif(new Date());
					documento.setArchivo(nombreDocumento);
					document3 = documento.getArchivo();
					
					documento.setFlgVigencia(UString.estaVacio(bean.getFlgVigencia()) ? "N" : bean.getFlgVigencia());
					documento.setFechaVigenciaFin(bean.getFechaVigenciaFin());
					documento.setCantidadFirmas(0);
					documento.setFlgVer(bean.getFlagVer());
					documento.setProcesoTipoDocumentoId(bean.getTipodocumento());
					documento.setWorkFlowProcesoId(bean.gettProceso());
					documento.setWorkFlowTransaccionId(bean.getTransaccion());
					
					syDocumentoAnexosDaoImpl.registrar(documento);
				}
				bean.setArchivonombre(nombreDocumento);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		DtoFlujoAdjunto asd = new DtoFlujoAdjunto();
		asd.setFecha(new Date());
		asd.setUsuario(usuarioActual.getUsuario());
		asd.setArchivonombre(document3);
		asd.setSecuencia(secuenciaGenerada);
		return asd;
	}

	public DtoFlujoAdjunto actualizarAdjunto(DtoFlujoAdjunto bean, SeguridadUsuarioActual usuarioActual) {

		WfTransacciones beanWF = dao.obtenerPorUUID(bean.getTransaccionUUID());
		bean.setTransaccion(beanWF.getTransaccionid());

		String archivoPrevio = null;
		SyDocumentoAnexos adjuntoSY = null;
		FsDocumento adjuntoFS = null;

		if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_FS)) {
			adjuntoFS = fsDocumentoDaoImpl.obtenerPorId(new FsDocumentoPk(bean.getSecuencia()));
			archivoPrevio = adjuntoFS.getNombrereal();
			adjuntoFS.setCodigonumero1id(0);
			adjuntoFS.setModificacionfecha(new Date());
			adjuntoFS.setModificacionusuario(usuarioActual.getUsuario());
			adjuntoFS.setNombrereal(bean.getArchivonombre());
		} else if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_SY)) {
			adjuntoSY = syDocumentoAnexosDaoImpl.obtenerPorTransaccionSecuencia("WF", bean.getTransaccion(),
					bean.getSecuencia());
			archivoPrevio = adjuntoSY.getArchivo();
			adjuntoSY.setComentario("0");
			adjuntoSY.setUltimoUsuario(usuarioActual.getUsuario());
			adjuntoSY.setUltimaFechaModif(new Date());
			adjuntoSY.setArchivo(bean.getArchivonombre());
		}

		String rutaServer = dao.obtenerParametroValorExplicacion("DIRFILE", "SY") + File.separator;
		String rutaServerOriginal = new String(rutaServer);

		if (!UString.estaVacio(rutaServer)) {

			if (!new File(rutaServer + "TRANSACCION").exists()) {
				new File(rutaServer + "TRANSACCION").mkdir();
			}
			if (!new File(rutaServer + "TRANSACCION" + File.separator + bean.getTransaccion()).exists()) {
				new File(rutaServer + "TRANSACCION" + File.separator + bean.getTransaccion()).mkdir();
			}

			String tmp_ = "TRANSACCION" + File.separator + bean.getTransaccion() + File.separator + "SEGUIMIENTO";

			if (!new File(rutaServer + tmp_).exists()) {
				new File(rutaServer + tmp_).mkdir();
			}

			rutaServer = rutaServer + tmp_ + File.separator;
			String rutaDocumento;
			try {
				bean.setArchivonombre(UFile.quitarCaracteresExtranos(bean.getArchivonombre()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String nombreDocumento = bean.getArchivonombre();

			rutaDocumento = rutaServer + nombreDocumento;

			String bin = bean.getArchivostring().substring(bean.getArchivostring().indexOf(",") + 1);

			bean.setArchivostring(bin);

			try {
				Files.write(Paths.get(rutaDocumento), Base64.getDecoder().decode(bean.getArchivostring()));
				// Files.delete(Paths.get(rutaServer + archivoPrevio));

				if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_FS)) {

				} else if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_SY)) {
					if (adjuntoSY != null) {
						adjuntoSY.setRutaArchivo(rutaDocumento.replace(rutaServerOriginal, ""));
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		DtoFlujoAdjunto asd = new DtoFlujoAdjunto();
		asd.setFecha(new Date());
		asd.setUsuario(usuarioActual.getUsuario());
		return asd;
	}

	public DtoFlujoAdjunto eliminarAdjunto(DtoFlujoAdjunto bean) {

		WfTransacciones beanWf = dao.obtenerPorUUID(bean.getTransaccionUUID());
		bean.setTransaccion(beanWf.getTransaccionid());

		String archivoPrevio = null;

		if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_FS)) {
			FsDocumento adjuntos = fsDocumentoDaoImpl.obtenerPorId(new FsDocumentoPk(bean.getSecuencia()));
			archivoPrevio = adjuntos.getNombrereal();
			if (adjuntos != null) {
				fsDocumentoDaoImpl.eliminar(adjuntos);
			}
		} else if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_SY)) {
			SyDocumentoAnexos adjunto = syDocumentoAnexosDaoImpl.obtenerPorTransaccionSecuencia("WF",
					bean.getTransaccion(), bean.getSecuencia());
			archivoPrevio = adjunto.getArchivo();
			if (adjunto != null) {
				syDocumentoAnexosDaoImpl.eliminar(adjunto);
			}
		}

		String rutaServer = dao.obtenerParametroValorExplicacion("DIRFILE", "SY") + File.separator;
		String tmp_ = "TRANSACCION" + File.separator + bean.getTransaccion() + File.separator + "SEGUIMIENTO";
		rutaServer = rutaServer + tmp_ + File.separator;
		String rutaDocumento;
		String nombreDocumento = archivoPrevio;
		rutaDocumento = rutaServer + nombreDocumento;

		if (!UString.estaVacio(rutaDocumento)) {
			rutaDocumento = rutaDocumento.trim();
			File file = new File(rutaDocumento);
			try {
				Files.deleteIfExists(file.toPath());
			} catch (IOException e) {
				logger.debug(e.getMessage());
				e.printStackTrace();
			}
		}

		return new DtoFlujoAdjunto();
	}

	public DtoFlujoAdjunto verAdjunto(DtoFlujoAdjunto bean, Boolean esHtml) throws IOException {

		WfTransacciones beanWf = dao.obtenerPorUUID(bean.getTransaccionUUID());
		bean.setTransaccion(beanWf.getTransaccionid());

		String nombre;
		if (UString.esNuloVacio(bean.getArchivonombre())) {
			nombre = UFile.archivoUnico();
		} else {
			nombre = bean.getArchivonombre().trim();
		}

		String rutaServer = dao.obtenerParametroValorExplicacion("DIRFILE", "SY");
		String tmp_ = rutaServer + "TRANSACCION" + File.separator + bean.getTransaccion() + File.separator
				+ "SEGUIMIENTO" + File.separator + bean.getArchivonombre().trim();

		logger.debug("tmp_ :: " + tmp_);

		byte[] contenido = null;
		if (new File(tmp_).exists()) {
			logger.debug("EXISTE");
			contenido = UFile.obtenerArregloByte(tmp_);
		}
		if (esHtml) {
			bean.setArchivostring(new String(contenido));
		} else {
			logger.debug("NO HTML");
			bean.setArchivostring(org.apache.commons.codec.binary.Base64.encodeBase64String(contenido));
			bean.setArchivostring(UFile.obtenerNombreWebDescargar(bean.getArchivostring()));
			logger.debug(bean.getArchivostring().length());
			if (bean.getArchivostring().length() > 50) {
				logger.debug(bean.getArchivostring().substring(0, 25));
			}
		}
		bean.setArchivonombre(Paths.get(tmp_).getFileName().toString());
		bean.setArchivonombre(UFile.obtenerNombreWebDescargar(bean.getArchivonombre()));
		return bean;
	}

	public WorkFlowAdjunto verAdjuntoWf(WorkFlowAdjunto bean, Boolean esHtml) throws IOException {
		String rutaServer = dao.obtenerParametroValorExplicacion("DIRFILE", "SY");
		String tmp_ = rutaServer + "TRANSACCION" + File.separator + bean.getTransaccion() + File.separator
				+ "SEGUIMIENTO" + File.separator + bean.getArchivonombre().trim();

		logger.debug("tmp_ :: " + tmp_);

		byte[] contenido = null;
		if (new File(tmp_).exists()) {
			logger.debug("EXISTE");
			contenido = UFile.obtenerArregloByte(tmp_);
		}
		if (esHtml) {
			bean.setArchivostring(new String(contenido));
		} else {
			logger.debug("NO HTML");
			bean.setArchivostring(org.apache.commons.codec.binary.Base64.encodeBase64String(contenido));
			bean.setArchivostring(UFile.obtenerNombreWebDescargar(bean.getArchivostring()));
			logger.debug(bean.getArchivostring().length());
			if (bean.getArchivostring().length() > 50) {
				logger.debug(bean.getArchivostring().substring(0, 25));
			}
		}
		bean.setArchivonombre(Paths.get(tmp_).getFileName().toString());

		bean.setArchivonombre(UFile.obtenerNombreWebDescargar(bean.getArchivonombre()));

		return bean;
	}

	public List<DtoTabla> listarDocumentoRequeridos(String transaccionUUID, SeguridadUsuarioActual usuarioActual) {

		WfTransacciones bean = dao.obtenerPorUUID(transaccionUUID);
		List<DtoTabla> ls = new ArrayList<DtoTabla>();

		// Le toca aprobar?
		boolean esAprobadorActual = false;
		DtoAprobacionAcciones dtoAprobacionAcciones = obtenerAccionesAprobacion(bean.getUuid(),
				usuarioActual.getPersonaId());

		if (UBoolean.validarFlag(dtoAprobacionAcciones.getPuedeAprobar())
				|| UBoolean.validarFlag(dtoAprobacionAcciones.getPuedeDevolver())
				|| UBoolean.validarFlag(dtoAprobacionAcciones.getPuedeRechazar())) {
			esAprobadorActual = true;
		}

		if (esAprobadorActual) {
			// Si le toca traer los clasicos del nivel
			List<DtoFlujoAdjuntoValidar> lst = obtenerTipoDocumentosPorNivel(bean.getProcesoid(), bean.getVersionid(),
					bean.getFlujoid().intValue(), bean.getNivelid().intValue() + 1);
			for (DtoFlujoAdjuntoValidar row : lst) {
				DtoTabla d = new DtoTabla();
				d.setCodigo(row.getTipodocumento());
				d.setDescripcion(row.getTipodocumentodescripcion());
				d.setEstadoId(row.getGrupo());
				d.setEstadoNombre(row.getPlantilla());
				d.setNombre(row.getRequerido());
				ls.add(d);
			}
		} else {
			// Sino Traer todas sus interacciones donde puede adjuntar
			// Pero solo las que tengan secuencia
			List<DtoFlujoAdjunto> adj = obtenerAdjuntosEditable(transaccionUUID, -1, usuarioActual.getPersonaId());
			// Aca eliminar las secuencias y los duplicados
			for (DtoFlujoAdjunto row : adj) {
				DtoTabla d = new DtoTabla();
				d.setCodigo(row.getTipodocumento());
				d.setDescripcion(row.getTipodocumentodescripcion());
				d.setEstadoId(row.getGrupo());
				d.setEstadoNombre(row.getPlantilla());
				d.setNombre(row.getAuxRequerido());
				// Aca quitamos los duplicados ya que solo nos interesa el tipodoc
				if (ls.stream().filter(x -> x.getCodigo().equals(row.getTipodocumento())).collect(Collectors.toList())
						.size() == 0) {
					ls.add(d);
				}
			}
		}

		// Traer los adjuntados antes, caso proceso documentario
		Map<String, String> parametros = null;
		List<DtoFlujoConfiguracion> criteriosPorEvaluarPorRequest = new ArrayList<DtoFlujoConfiguracion>();
		try {
			parametros = new ObjectMapper().readValue(bean.getCriterios(), Map.class);
		} catch (Exception e) {
			parametros = new HashMap<>();
			e.printStackTrace();
		}

		for (Map.Entry<String, String> item : parametros.entrySet()) {
			// Si tiene el criterio
			if (item.getKey().equals("APDC")) {
				//Aca enviar los tipos que ya estan adjuntos en la transaccion
				List<DtoFlujoAdjunto> adj = dao.listarTipoAdjuntosPorTransaccion(bean);
				for (DtoFlujoAdjunto row : adj) {
					DtoTabla d = new DtoTabla();
					d.setCodigo(row.getTipodocumento());
					d.setDescripcion(row.getTipodocumentodescripcion());
					d.setEstadoId(row.getGrupo());
					d.setEstadoNombre(row.getPlantilla());
					d.setNombre(row.getAuxRequerido());
					// Aca quitamos los duplicados ya que solo nos interesa el tipodoc
					if (ls.stream().filter(x -> x.getCodigo().equals(row.getTipodocumento())).collect(Collectors.toList())
							.size() == 0) {
						ls.add(d);
					}
				}
				return ls;
			}
		}

		return ls;
	}

	public List<DtoTabla> listarDocumentoRequeridosCero(WfTransacciones bean, SeguridadUsuarioActual usuarioActual) {

		List<DtoTabla> ls = new ArrayList<DtoTabla>();
		// Si le toca traer los clasicos del nivel
		List<DtoFlujoAdjuntoValidar> lst = obtenerTipoDocumentosPorNivel(bean.getProcesoid(), bean.getVersionid(),
				bean.getFlujoid().intValue(), 0);
		for (DtoFlujoAdjuntoValidar row : lst) {
			DtoTabla d = new DtoTabla();
			d.setCodigo(row.getTipodocumento());
			d.setDescripcion(row.getTipodocumentodescripcion());
			d.setEstadoId(row.getGrupo());
			d.setEstadoNombre(row.getPlantilla());
			d.setNombre(row.getRequerido());
			ls.add(d);
		}
		return ls;
	}

	public List<DtoTabla> listarDocumentoRequeridosPadre(Integer transaccionid, SeguridadUsuarioActual usuarioActual)
			throws Exception {

		WfTransacciones bean = dao.obtenerPorId(transaccionid);
		List<DtoTabla> ls = new ArrayList<DtoTabla>();
		List<DtoTabla> lsRaw = new ArrayList<DtoTabla>();

		// Si le toca traer los clasicos del nivel
		List<DtoFlujoAdjuntoValidar> lst = obtenerTipoDocumentosPorNivelPadre(bean.getProcesoid(), bean.getVersionid(),
				bean.getFlujoid().intValue(), bean.getNivelid().intValue() + 1);
		for (DtoFlujoAdjuntoValidar row : lst) {
			DtoTabla d = new DtoTabla();
			d.setCodigo(row.getTipodocumento());
			d.setDescripcion(row.getTipodocumentodescripcion());
			d.setEstadoId(row.getGrupo());
			d.setEstadoNombre(row.getPlantilla());
			d.setNombre(row.getRequerido());

			if (lsRaw.stream().filter(x -> x.getCodigo().equals(row.getTipodocumento())).collect(Collectors.toList())
					.size() == 0) {
				lsRaw.add(d);
			}
		}

		String tiposDocumentos = parametroObtenerTexto("WF", "DOCWFPROV");

		if (UString.estaVacio(tiposDocumentos)) {
			tiposDocumentos = "";
		}

		String dest[] = tiposDocumentos.split(";");
		for (String t : dest) {
			if (!UString.estaVacio(t)) {
				t = t.trim();
				for (DtoTabla ad : lsRaw) {
					if (ad.getCodigo().equals(t)) {
						ls.add(ad);
					}

				}
			}
		}

		return ls;
	}

	public List<DtoTabla> listarDocumentoTodos(Integer transaccionid, SeguridadUsuarioActual usuarioActual) {

		WfTransacciones bean = dao.obtenerPorId(transaccionid);
		List<DtoTabla> ls = new ArrayList<DtoTabla>();

		// Le toca aprobar?
		boolean esAprobadorActual = false;
		DtoAprobacionAcciones dtoAprobacionAcciones = obtenerAccionesAprobacion(bean.getUuid(),
				usuarioActual.getPersonaId());

		if (UBoolean.validarFlag(dtoAprobacionAcciones.getPuedeAprobar())
				|| UBoolean.validarFlag(dtoAprobacionAcciones.getPuedeDevolver())
				|| UBoolean.validarFlag(dtoAprobacionAcciones.getPuedeRechazar())) {
			esAprobadorActual = true;
		}

		// Si le toca traer los clasicos del nivel
		List<DtoFlujoAdjuntoValidar> lst = obtenerTipoDocumentosPorNivel(bean.getProcesoid(), bean.getVersionid(),
				bean.getFlujoid().intValue(), bean.getNivelid().intValue() + 1);
		for (DtoFlujoAdjuntoValidar row : lst) {
			DtoTabla d = new DtoTabla();
			d.setCodigo(row.getTipodocumento());
			d.setDescripcion(row.getTipodocumentodescripcion());
			d.setEstadoId(row.getGrupo());
			d.setEstadoNombre(row.getPlantilla());
			d.setNombre(row.getRequerido());
			ls.add(d);
		}

		return ls;
	}

	public DtoTabla escribirImagenPDF(DtoFlujoAdjunto dto, SeguridadUsuarioActual usuarioActual) throws Exception {
//		String rutaObjeto = null;
//		Integer cantidadFirmas = null;
//		String nombreReal = null;
//
//		FsDocumento fsDoc = null;
//		SyDocumentoAnexos syDoc = null;
//
//		if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_FS)) {
//			fsDoc = fsDocumentoDaoImpl.obtenerPorId(new FsDocumentoPk(dto.getSecuencia()));
//			nombreReal = fsDoc.getNombrereal();
//			cantidadFirmas = fsDoc.getCodigonumero1id();
//
//		} else if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_SY)) {
//			syDoc = syDocumentoAnexosDaoImpl.obtenerPorTransaccionSecuencia("WF", dto.getTransaccion(),
//					dto.getSecuencia());
//			nombreReal = syDoc.getArchivo();
//			try {
//				cantidadFirmas = Integer.parseInt(syDoc.getComentario().trim());
//
//			} catch (Exception e) {
//				cantidadFirmas = 0;
//			}
//		}
//
//		String rutaServer = dao.obtenerParametroValorExplicacion("RUTAADJUN", "SY");
//		rutaObjeto = rutaServer + File.separator + dto.getTransaccion() + File.separator + "SEGUIMIENTO"
//				+ File.separator + nombreReal;
//		byte[] contenido = null;
//
//		// 1. obtener
//		if (new File(rutaObjeto).exists()) {
//			contenido = UFile.obtenerArregloByte(rutaObjeto);
//		}
//
//		// 2. firmar
//		String bin = dto.getBase64Image().substring(dto.getBase64Image().indexOf(",") + 1);
//		dto.setBase64Image(bin);
//		Image img = Image.getInstance(Base64.getDecoder().decode(dto.getBase64Image()));
//
//		img.setAbsolutePosition(dto.getX(), dto.getY());
//		img.scaleAbsolute(dto.getW(), dto.getH());
//
//		OutputStream fout = new ByteArrayOutputStream();
//
//		PdfReader pdfReader = new PdfReader(contenido);
//
//		PdfStamper pdfStamper = new PdfStamper(pdfReader, fout);
//
//		PdfContentByte content = pdfStamper.getOverContent(1);
//
//		content.addImage(img);
//
//		pdfStamper.close();
//
//		pdfReader.close();
//
//		fout.close();
//
//		contenido = ((ByteArrayOutputStream) fout).toByteArray();
//
//		// 3. escribir
//		Files.write(Paths.get(rutaObjeto), contenido);
//
//		// 4. actualizar cantidadfirmas
//		Integer firmas = (cantidadFirmas == null ? 0 : cantidadFirmas.intValue()) + 1;
//		if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_FS)) {
//			fsDoc.setCodigonumero1id(firmas);
//			fsDoc.setModificacionfecha(new Date());
//			fsDoc.setModificacionusuario(usuarioActual.getUsuario());
//			fsDocumentoDaoImpl.actualizar(fsDoc);
//		} else if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_SY)) {
//			syDoc.setComentario(firmas.toString());
//			syDoc.setUltimaFechaModif(new Date());
//			syDoc.setUltimoUsuario(usuarioActual.getUsuario());
//			syDocumentoAnexosDaoImpl.actualizar(syDoc);
//		}
//
//		// 5. retornar firmado, en pantalla actualizar el base64 y la cantidad de firmas
//		DtoTabla dtoTabla = new DtoTabla();
//		dtoTabla.setDescripcion(org.apache.commons.codec.binary.Base64.encodeBase64String(contenido));
//		dtoTabla.setId(firmas);
//		dtoTabla.setEstadoId(usuarioActual.getUsuario());
//
//		// 6. historial de firmas
//		registrarSeguimientoFirmas(dto.getSecuencia().intValue(), "DIGI", usuarioActual.getPersonaId().intValue(),
//				usuarioActual.getUsuario());
//		return dtoTabla;
		return null;
	}

	public DtoTabla firmar(List<DtoFlujoAdjunto> adjuntosFirmar, SeguridadUsuarioActual usuarioActual)
			throws Exception {
		String rutaServer = dao.obtenerParametroValorExplicacion("DIRFILE", "SY");
		List<DtoFirmaDocumentoGenerico> documentosGenericos = new ArrayList<>();
		for (DtoFlujoAdjunto adjPk : adjuntosFirmar) {
			String rutaObjeto = rutaServer + "TRANSACCION" + File.separator + adjPk.getTransaccion() + File.separator
					+ "SEGUIMIENTO" + File.separator + adjPk.getArchivonombre();
			DtoFirmaDocumentoGenerico gen = new DtoFirmaDocumentoGenerico();
			gen.setNroFirma(adjPk.getCantidadfirmas() == null ? 0 : adjPk.getCantidadfirmas());

			gen.setRutaAdjunto(rutaObjeto);
			if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_FS)) {
				gen.setTabla("FS_DOCUMENTO");
				gen.setTablaPk("DOCUMENTO_ID");
				gen.setPkAdjuntoString(adjPk.getSecuencia().toString());
			} else if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_SY)) {
				gen.setTabla("SY_DocumentoAnexos");
				gen.setTablaPk("MODULO|LINEA|SECUENCIA");
				gen.setPkAdjuntoString("WF|" + adjPk.getTransaccion() + "|" + adjPk.getSecuencia().toString());
			}
			documentosGenericos.add(gen);
		}
		return

		firmarGenerico(documentosGenericos, usuarioActual);
	}

	private DtoTabla firmarGenerico(List<DtoFirmaDocumentoGenerico> adjuntosFirmar,
			SeguridadUsuarioActual usuarioActual) throws Exception {

		// Variables properties
		String recursoRuta = UFile.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		Properties recursoPropiedad = UPropiedades.getInstance().abrir(recursoRuta, ConstanteBoot.PROPERTIES_GLOBAL);

		String JAR = parametroObtenerExplicacion("WF", "FIRMAJAR", "999999");

		if (UString.estaVacio(JAR)) {
			JAR = "firmaWF2.jar";
		} else {
			JAR = UString.trimSinNulo(JAR);
		}

		// "C:\\Program Files\\Apache Software Foundation\\Tomcat
		// 8.5\\webapps\\firma\\upload\\";
		String RUTA_JNPLS = recursoPropiedad.getProperty("ruta.webapp.firma.upload");

		// "jnlp://localhost:9090/firma/upload/";
		String URL_JNPL = "jnlp://" + recursoPropiedad.getProperty("url.firma.upload");

		// "http://localhost:9090/firma/upload/";
		String HTTP_JNPL = "http://" + recursoPropiedad.getProperty("url.firma.upload");

		// "http://localhost:8093/spring/sistema/WfFirma/obtenerDatos";
		String URL_OBTENER_DATOS = recursoPropiedad.getProperty("url.firma.obtenerdatos");

		// "http://localhost:8093/spring/sistema/WfFirma/confirmarDatos";
		String URL_CONFIRMAS_DATOS = recursoPropiedad.getProperty("url.firma.confirmardatos");

		BigDecimal ID_FIRMA = wfFirmaDaoImpl.generarId();
		WfFirma firma = new WfFirma();
		firma.setEstado("PEND");
		firma.getPk().setIdFirma(ID_FIRMA);
		wfFirmaDaoImpl.registrar(firma);

		Integer i = 1;

		for (DtoFirmaDocumentoGenerico adjGenerico : adjuntosFirmar) {
			WfFirmaDocumento firmaDocumento = new WfFirmaDocumento();
			firmaDocumento.getPk().setIdFirma(ID_FIRMA);
			firmaDocumento.getPk().setIdDocumento(new BigDecimal(i));
			Path ruta = Paths.get(adjGenerico.getRutaAdjunto());
			firmaDocumento.setArchivoNombre(ruta.getFileName().toString());
			firmaDocumento.setArhivoOriginal(UFile.obtenerArregloByte(adjGenerico.getRutaAdjunto()));
			firmaDocumento.setIdDestino("FILE");
			firmaDocumento.setDestinoArchivo(adjGenerico.getRutaAdjunto());
			firmaDocumento.setEstado("PEND");
			firmaDocumento.setNroFirma(adjGenerico.getNroFirma());
			firmaDocumento.setDestinoTabla(adjGenerico.getTabla());
			firmaDocumento.setDestinoCampo(adjGenerico.getTablaPk());
			firmaDocumento.setDestinoId(adjGenerico.getPkAdjuntoString());
			wfFirmaDocumentoDaoImpl.registrar(firmaDocumento);
			i++;
		}

		List<DtoTabla> params = new ArrayList<DtoTabla>();
		params.add(new DtoTabla("", ID_FIRMA.toString()));
		params.add(new DtoTabla("", usuarioActual.getToken()));
		params.add(new DtoTabla("", URL_OBTENER_DATOS));
		params.add(new DtoTabla("", URL_CONFIRMAS_DATOS));
		DtoTabla dto = new DtoTabla();
		String nombreGenerado = usuarioActual.getUsuario() + ".jnlp";
		String parametros = "";
		for (DtoTabla dtoTabla : params) {
			parametros = parametros + "<argument>" + dtoTabla.getNombre() + "</argument>";
		}
		String plantilla = "<?xml version=\"1.0\" encoding=\"utf-8\"?> " + "<jnlp spec=\"1.5.0+\" codebase=\""
				+ HTTP_JNPL + "\" href=\"" + nombreGenerado + "\">" + "            <information>"
				+ "                <title>Inicio</title>" + "                <vendor>Royal Systems S.A.C.</vendor>"
				+ "            </information><security><all-permissions/></security>"
				+ "            <update check=\"timeout\" policy=\"always\"/>" + "            <resources>"
				+ "                <java version=\"1.7+\"/>" + "                <jar href=\"" + JAR
				+ "\" download=\"eager\" main=\"true\" />" + "            </resources>	"
				+ "            <application-desc>" + "                " + parametros + ""
				+ "            </application-desc>" + "            <update check=\"background\"/>" + "        </jnlp>";
		Files.write(Paths.get(RUTA_JNPLS + nombreGenerado), plantilla.getBytes());
		dto.setDescripcion(URL_JNPL + nombreGenerado);
		dto.setCodigo("" + ID_FIRMA.intValue());
		return dto;
	}

	public List<DtoFlujoAdjunto> consultarFinFirma(Integer idFirma, String transaccionUUID,
			SeguridadUsuarioActual usuarioActual) throws IOException {

		List<DtoFlujoAdjunto> documentosDto = new ArrayList<DtoFlujoAdjunto>();
		WfFirma procesoFirma = wfFirmaDaoImpl.obtenerPorId(new WfFirmaPk(new BigDecimal(idFirma)));
		if (procesoFirma.getEstado().equals("TERM")) {
			String rutaServer = dao.obtenerParametroValorExplicacion("DIRFILE", "SY");
			WfFirmaDocumento documento = wfFirmaDocumentoDaoImpl
					.obtenerPorId(new WfFirmaDocumentoPk(new BigDecimal(idFirma), new BigDecimal(1)));
			if (documento != null) {
				String tabla = documento.getDestinoTabla();
				String ids[] = documento.getDestinoId().split("\\|");

				WfTransacciones beanWF = dao.obtenerPorUUID(transaccionUUID);

				if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_FS)) {
					List<FsDocumento> documentosFirmados = fsDocumentoDaoImpl
							.listarPorTransaccion(beanWF.getTransaccionid());
					for (FsDocumento item : documentosFirmados) {
						if (!UString.estaVacio(item.getNombrereal())) {
							byte[] contenido = null;
							String rutaObjeto = rutaServer + "TRANSACCION" + File.separator + Integer.parseInt(ids[0])
									+ File.separator + "SEGUIMIENTO" + File.separator + item.getNombrereal();
							if (new File(rutaObjeto).exists()) {
								contenido = UFile.obtenerArregloByte(rutaObjeto);
							}
							DtoFlujoAdjunto dto = new DtoFlujoAdjunto();
							dto.setSecuencia(item.getPk().getDocumentoId());
							dto.setArchivostring(org.apache.commons.codec.binary.Base64.encodeBase64String(contenido));
							dto.setCantidadfirmas(item.getCodigonumero1id());
							dto.setFecha(new Date());
							documentosDto.add(dto);
						}
					}
				} else if (WF_TIPOREPOSITORIO.equals(WF_TIPOREPOSITORIO_SY)) {
					List<SyDocumentoAnexos> documentosFirmados = syDocumentoAnexosDaoImpl
							.listarPorTransaccion(beanWF.getTransaccionid());
					for (SyDocumentoAnexos item : documentosFirmados) {
						if (!UString.estaVacio(item.getArchivo())) {
							byte[] contenido = null;
							String rutaObjeto = rutaServer + File.separator + Integer.parseInt(ids[1]) + File.separator
									+ "SEGUIMIENTO" + File.separator + item.getArchivo();
							if (new File(rutaObjeto).exists()) {
								contenido = UFile.obtenerArregloByte(rutaObjeto);
							}
							DtoFlujoAdjunto dto = new DtoFlujoAdjunto();
							dto.setSecuencia(item.getPk().getSecuencia());
							dto.setArchivostring(org.apache.commons.codec.binary.Base64.encodeBase64String(contenido));
							dto.setArchivostring(UFile.obtenerNombreWebDescargar(dto.getArchivostring()));
							dto.setCantidadfirmas(Integer.parseInt(item.getComentario()));
							dto.setFecha(new Date());
							documentosDto.add(dto);
						}
					}
				}

			}
		}
		return documentosDto;
	}

	@Transactional(readOnly = true)
	public DtoFirmaData obtenerDatos(DtoFirmaCarga dtoFirmaCarga) throws Exception {
		DtoFirmaData dto = new DtoFirmaData();

		List<DominioParametroPersistencia> parametros = new ArrayList();

		if (dtoFirmaCarga.getIdFirma() == -1) {
			Integer idUltimo = wfFirmaDaoImpl.generarId().intValue() - 1;
			dtoFirmaCarga.setIdFirma(idUltimo);
		}

		dto.setIdFirma(dtoFirmaCarga.getIdFirma());

		parametros.add(new DominioParametroPersistencia("p_id", Integer.class, dtoFirmaCarga.getIdFirma()));

		List lstDocumentos = dao.listarPorQuery(DtoFirmaDocumento.class, "wftransacciones.listarDocumentosFirma",
				parametros);

		for (DtoFirmaDocumento object : (List<DtoFirmaDocumento>) lstDocumentos) {
			if (object.getBlob() != null) {
				if (object.getBlob().length > 0) {
					byte[] da = object.getBlob();// object.getBlob().getBytes(1, (int) object.getBlob().length());
					object.setArchivo(org.apache.commons.codec.binary.Base64.encodeBase64String(da));
					// object.getBlob().free();
					object.setBlob(null);
				}
			}

			object.setBlob(null);
		}

		List lstParametros = dao.listarPorQuery(DtoFirmaParametros.class, "wftransacciones.listarParametrosFirma",
				parametros);

		for (DtoFirmaParametros object : (List<DtoFirmaParametros>) lstParametros) {
			if (object.getBlob() != null) {
				if (object.getBlob().length > 0) {
					byte[] da = object.getBlob();// object.getBlob().getBytes(1, (int) object.getBlob().length());
					object.setValorBlob(org.apache.commons.codec.binary.Base64.encodeBase64String(da));
					// object.getBlob().free();
					object.setBlob(null);
				}
			}

			object.setBlob(null);
		}

		dto.setDocumentos(lstDocumentos);
		dto.setParametros(lstParametros);

		return dto;
	}

	@Transactional
	public DtoFirmaCarga confirmarDatos(DtoFirmaData firmaCarga, SeguridadUsuarioActual seguridadUsuarioActual)
			throws Exception {
		Integer contadorFirmados = 0;
		for (DtoFirmaDocumento documentoFirmado : firmaCarga.getDocumentos()) {

			WfFirmaDocumento documento = wfFirmaDocumentoDaoImpl.obtenerPorId(new WfFirmaDocumentoPk(
					new BigDecimal(firmaCarga.getIdFirma()), new BigDecimal(documentoFirmado.getId())));

			boolean escrito = false;

			if (documento.getIdDestino().equals("FILE")) {
				byte[] dato = Base64.getDecoder().decode(documentoFirmado.getArchivo());
				Files.write(Paths.get(documento.getDestinoArchivo()), dato);
				escrito = true;
			}

			// ACTUALIZO EL CAMPO DE LA TABLA ORIGINAL(CANTIDAD_FIRMAS)
			StringBuilder seccionWhere = new StringBuilder("");
			String campos[] = documento.getDestinoCampo().split("\\|");
			String valores[] = documento.getDestinoId().split("\\|");

			for (int i = 0; i < campos.length; i++) {
				seccionWhere.append(" AND ").append(String.format("%s = '%s'", campos[i], valores[i]));
			}

			String estructuraUpdate = "UPDATE %s SET Comentario = ISNULL(cast(rtrim(Comentario) as int), 0)+1, UltimaFechaModif = GETDATE(), UltimoUsuario = '%s' WHERE 1=1 %s";

			StringBuilder sentenciaUpdate = new StringBuilder(String.format(estructuraUpdate,
					documento.getDestinoTabla(), seguridadUsuarioActual.getUsuario(), seccionWhere.toString()));

			logger.debug(sentenciaUpdate.toString());

			if (escrito) {
				documento.setArchivoResultado(null);
				documento.setEstado("FIRM");
				wfFirmaDocumentoDaoImpl.actualizar(documento);
			}

			wfFirmaDocumentoDaoImpl.ejecutarPorSentenciaSQL(sentenciaUpdate);

			// registrarSeguimientoFirmas(Integer.parseInt(valores[0]), "ELEC",
			// seguridadUsuarioActual.getPersonaId().intValue(),
			// seguridadUsuarioActual.getUsuario());

			contadorFirmados++;
		}

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_id", Integer.class, firmaCarga.getIdFirma()));
		dao.ejecutarPorQuery("wftransacciones.actualizarEstadoCargaFirma", parametros);

		return new DtoFirmaCarga();
	}

	public List<DtoTabla> listarAplicacionPorUsuario(String usuario) {
		return dao.listarAplicacionPorUsuario(usuario);
	}

	public List<DtoTabla> listarMiscelaneosActivos(String aplicacionCodigo, String codigoTabla) {
		return dao.listarMiscelaneosActivos(aplicacionCodigo, codigoTabla);

	}

	public List<DtoTabla> listarCompaniasActivas() {
		return dao.listarCompaniasActivas();
	}

	public List<DtoTabla> filtrarEmpleados(String busqueda) {
		return dao.filtrarEmpleados(busqueda);
	}

	@Transactional(readOnly = true)
	public List<DtoWfTransaccionTemp> listarTransaccionesPendientes() {
		return dao.listarTransaccionesPendientes();
	}

	@Transactional
	public void registrarIntentoTransaccionesPendientes(DtoWfTransaccionTemp dtoWfTransaccionTemp) {
		dao.registrarIntentoTransaccionesPendientes(dtoWfTransaccionTemp);
	}

	@Transactional
	public void asociarTransaccionSolicitudOriginal(String sentencia, Integer transaccionid) {
		System.out.println(String.format(sentencia, transaccionid));
		dao.ejecutarPorSentenciaSQL(new StringBuilder(String.format(sentencia, transaccionid)));
	}

	@Transactional
	public void transaccionGenerada(Integer transaccionId) {
		dao.transaccionGenerada(transaccionId);
	}

	public WfTransacciones obtenerPorId(Integer transaccionid) {
		return dao.obtenerPorId(transaccionid);
	}

	public WfTransacciones obtenerPorId(String transaccionUUID) {
		return dao.obtenerPorUUID(transaccionUUID);
	}

	public List<DtoFlujoAdjunto> obtenerCabecera(String transaccionUUID) {

		WfTransacciones bean = dao.obtenerPorUUID(transaccionUUID);
		WfProcesos proceso = wfProcesoDaoImpl.obtenerPorId(new WfProcesosPk(bean.getProcesoid()));
		WfProcesoVersiones procesoVersion = wfProcesoVersionDaoImpl
				.obtenerPorId(new WfProcesoVersionesPk(bean.getProcesoid(), bean.getVersionid()));
		List<DtoFlujoAdjunto> ls2 = new ArrayList<DtoFlujoAdjunto>();
//		List<FsDocumento> lst = fsDocumentoDaoImpl.obtenerCabecera(pk);

		// Si tiene configurado el sp_ver
		if (!UString.estaVacio(procesoVersion.getSpver())) {

			Map<String, String> metadatos = new HashMap<String, String>();
			List<DtoSolicitudDetalleReporte> metadatosDetalles = new ArrayList<DtoSolicitudDetalleReporte>();
			DtoSolicitudDetalleReporte metadatosDetalleActual = new DtoSolicitudDetalleReporte();
			metadatosDetalleActual.setClave("dummy");
			List<DtoWfFormularioDatos> datosRaw = new ArrayList<DtoWfFormularioDatos>();
			try {

				if (procesoVersion.getOrigendatosid() != null) {
					// Extracci\u00F3n externa
					DtoOrigenDatos dtoOrigenDatos = dao.obtenerOrigenDatos(procesoVersion.getOrigendatosid());

					Class.forName(dtoOrigenDatos.getDriver());
					Connection conexion = DriverManager.getConnection(dtoOrigenDatos.getCadenaconexion());
					PreparedStatement ps = conexion.prepareStatement("EXEC " + procesoVersion.getSpver() + " ?");
					ps.setInt(1, bean.getTransaccionid());
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						String clave = UString.obtenerSinNulo(rs.getString(1)).trim();
						String valor = UString.obtenerSinNulo(rs.getString(2)).trim();
						String clase = UString.obtenerSinNulo(rs.getString(3)).trim();
						DtoWfFormularioDatos dtotabla = new DtoWfFormularioDatos();
						dtotabla.setClave(clave);
						dtotabla.setValor(valor);
						dtotabla.setClase(clase);
						datosRaw.add(dtotabla);
					}

					rs.close();
					ps.close();
					conexion.close();
				} else {
					// TODO TESTEAR EN ORACLE

				}

				// Armar detalles
				for (DtoWfFormularioDatos dtoTabla : datosRaw) {
					String clave = UString.obtenerSinNulo(dtoTabla.getClave()).trim();
					String valor = UString.obtenerSinNulo(dtoTabla.getValor()).trim();
					String clase = UString.obtenerSinNulo(dtoTabla.getClase()).trim();

					if (!clave.startsWith("p_detalle")) {
						metadatos.put(clave, valor);
					} else {
						if (!clave.startsWith(metadatosDetalleActual.getClave())) {
							metadatosDetalleActual = new DtoSolicitudDetalleReporte();
							metadatosDetalleActual.setClave(clave.substring(0, clave.indexOf("[") - 1));
							metadatosDetalles.add(metadatosDetalleActual);
						}

						DtoWfFormularioDatos registro = new DtoWfFormularioDatos();
						registro.setSecuencia(clave.substring(clave.indexOf("[") + 1, clave.lastIndexOf("]")));
						registro.setClave("p" + clave.substring(clave.lastIndexOf("]") + 1, clave.length()));
						registro.setValor(valor);
						registro.setClase(clase);

						metadatosDetalleActual.getMetadatos().add(registro);
					}

				}

				if (metadatosDetalles.size() > 0) {
					for (DtoSolicitudDetalleReporte dtoSolicitudDetalleReporte : metadatosDetalles) {
						HashMap<String, List<DtoWfFormularioDatos>> registrosAgrupados = new HashMap<String, List<DtoWfFormularioDatos>>();
						for (DtoWfFormularioDatos subDato : dtoSolicitudDetalleReporte.getMetadatos()) {
							if (!registrosAgrupados.containsKey(subDato.getSecuencia())) {
								List<DtoWfFormularioDatos> list = new ArrayList<DtoWfFormularioDatos>();
								list.add(subDato);
								registrosAgrupados.put(subDato.getSecuencia(), list);
							} else {
								registrosAgrupados.get(subDato.getSecuencia()).add(subDato);
							}
						}
						StringBuilder dN = new StringBuilder("");
						for (Entry<String, List<DtoWfFormularioDatos>> registroAgrupado : registrosAgrupados
								.entrySet()) {
							dN.append("<tr>");
							for (DtoWfFormularioDatos subDato : registroAgrupado.getValue()) {
								dN.append("<td " + "class=\"" + subDato.getClase() + "\"" + ">")
										.append(UString.obtenerSinNulo(subDato.getValor())).append("</td>");
							}
							dN.append("</tr>");
						}
						metadatos.put(dtoSolicitudDetalleReporte.getClave(), dN.toString());
					}
				}

				logger.debug("reporteEjecutar");

				ReporteTransaccion reporteParametro = new ReporteTransaccion();
				reporteParametro.setAplicacionCodigo(proceso.getAplicacionid());
				reporteParametro.setReporteCodigo(proceso.getPk().getProcesoid().substring(0, 2));

				logger.debug("reporteParametro.getAplicacionCodigo() 2:" + reporteParametro.getAplicacionCodigo());
				logger.debug("reporteParametro.getReporteCodigo()    2:" + reporteParametro.getReporteCodigo());

				reporteParametro.setParametros(metadatos);
				reporteParametro.setCompaniaSocio("999999");
				reporteParametro.setPeriodo("999999");
				reporteParametro.setVersion("FORMUL");
				reporteParametro.setReporteTipo("HTML");
				// reporteParametro.setWorkFlowNivel(bean.getNivelid());
				// reporteParametro.setWorkFlowAccion(workFlowAccion);
				// reporteParametro.setWorkFlowFlgCorreoNiveles(procesoVersion.getFlgCorreoNiveles());

				ReporteTransaccion reporte = reporteEjecutarWorkFlow(reporteParametro);
				String mensajeReporte = UString.estaVacio(reporte.getTransaccionEstado()) ? ""
						: reporte.getTransaccionEstado();
				if (mensajeReporte.equals(DominioTransaccion.OK)) {
					DtoFlujoAdjunto adj = new DtoFlujoAdjunto();
					adj.setFlagVer("S");
					String archivoString = Base64.getEncoder().encodeToString(reporte.getResultadoCuerpoBinario());
					String nombreFormulario = "Formulario Spring.html";
					adj.setArchivonombre(nombreFormulario);
					adj.setArchivostring(archivoString);
					adj.setTipodocumento("81"); // Tipo - Solicitud
					adj.setTransaccion(bean.getTransaccionid());
					adj.setFlagVer("S");
					// Se registra
//						SeguridadUsuarioActual seguridadUsuarioActual = new SeguridadUsuarioActual();
//						seguridadUsuarioActual.setUsuario("ROYAL");
//						registrarAdjunto(adj, seguridadUsuarioActual);
					// se agrega a la lista para que se muestre
					adj.setArchivonombre(nombreFormulario);
					adj.setArchivostring(new String(reporte.getResultadoCuerpoBinario()));
					ls2.add(adj);

				} else {
					logger.debug(reporte.getTransaccionListaMensajes().get(0));
				}

			} catch (Exception e) {
				logger.debug("error " + e.getMessage());
				e.printStackTrace();
			}

		}
//		for (FsDocumento wfTransaccionAdjuntos : lst) {
//			DtoFlujoAdjunto row = new DtoFlujoAdjunto();
//			row.setTransaccion(new BigDecimal(pk.getTransaccionid()));
//			row.setArchivonombre(wfTransaccionAdjuntos.getNombrereal());
//			row = verAdjunto(row, row.getArchivonombre().endsWith(".html"));
//			row.setTipodocumento(wfTransaccionAdjuntos.getTipodocumentoid());
//			ls2.add(row);
//		}
		return ls2;
	}

	public WorkFlowResultado obtenerTransaccion(WorkFlowTransaccion request) {
		WfTransacciones bean = dao.obtenerPorId(request.getTransaccionId());
		WorkFlowResultado workFlowResultado = new WorkFlowResultado();
		workFlowResultado.setNivel(bean.getNivelid());
		return workFlowResultado;
	}

	public WorkFlowResultado anularTransaccionDesdeSolicitud(WorkFlowTransaccion request) {
		WfTransacciones bean = dao.obtenerPorId(request.getTransaccionId());
		bean.setEstado("R");
		dao.actualizar(bean);
		String motivoRechazo = "Anulado por solicitante";
		registrarSeguimiento(bean.getProcesoid(), bean.getVersionid(), bean.getFlujoid(),
				bean.getTransaccionid().intValue(), "R", bean.getModificacionusuario(), motivoRechazo, 999, null);
		WorkFlowResultado workFlowResultado = new WorkFlowResultado();
		return workFlowResultado;
	}

	public List<DtoTabla> listarOrigenes() {
		return dao.listarOrigenes();
	}

	public WorkFlowResultado login_transaccionIniciar(WorkFlowTransaccion workFlowTransaccion) throws Exception {

//		String rutaPropiedades = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
//		Properties propiedades = UPropiedades.getInstance().abrir(rutaPropiedades, ConstanteBoot.PROPERTIES_GLOBAL);
//
//		String urlLogin = UString.obtenerSinNulo(propiedades.getProperty("soltrak.login"));
//		String urlRegistrar = UString.obtenerSinNulo(propiedades.getProperty("soltrak.wf"));
//
//		try {
//			// Login
//			RestTemplate restTemplate = new RestTemplate();
//			URI uri = new URI(urlLogin);
//			LoginSoltrakRequest requestLogin = new LoginSoltrakRequest();
//			requestLogin.setAplicacionCodigo("HR");
//			requestLogin.setCompaniaCodigo("01000000");
//			requestLogin.setUsuarioLogin("MISESF");
//			requestLogin.setUsuarioClave("123");
//			HttpEntity<LoginSoltrakRequest> request = new HttpEntity<LoginSoltrakRequest>(requestLogin,
//					this.getHeaders());
//			ResponseEntity<LoginSoltrakResponse> result = restTemplate.exchange(uri, HttpMethod.POST, request,
//					LoginSoltrakResponse.class);
//			String token = result.getBody().getToken();
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//			headers.add(ConstanteFiltro.TOKEN, token);
//			headers.add(ConstanteFiltro.SID, token);
//
//			// Registrar transaccion
//			uri = new URI(urlRegistrar);
//			HttpEntity<WorkFlowTransaccion> request2 = new HttpEntity<WorkFlowTransaccion>(workFlowTransaccion,
//					headers);
//			ResponseEntity<WorkFlowResultado> result2 = restTemplate.exchange(uri, HttpMethod.POST, request2,
//					WorkFlowResultado.class);
//			return result2.getBody();
//
//		} catch (Exception e) {
//			logger.error("LoginSoltrakResponse:" + e.getMessage());
//			e.printStackTrace();
//			return null;
//		}
		return null;
	}

	public String obtenerPorIdNL(Integer transaccionId) {
		return dao.obtenerPorIdNL(transaccionId);
	}

	public List<DtoSeguimiento> listarSeguimientoGeneralWF(String transaccionUUID) {

		WfTransacciones wfTransacciones = dao.obtenerPorUUID(transaccionUUID);

		List<DominioParametroPersistencia> parametros = new ArrayList();
		parametros.add(new DominioParametroPersistencia("p_referencia", String.class, wfTransacciones.getReferencia()));
		parametros.add(
				new DominioParametroPersistencia("p_transaccion", Integer.class, wfTransacciones.getTransaccionid()));
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, wfTransacciones.getProcesoid()));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, wfTransacciones.getFlujoid()));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, wfTransacciones.getVersionid()));
		List lstResultado = dao.listarPorQuery(DtoSeguimiento.class, "wftransacciones.listarSeguimientoGeneralWF",
				parametros);
		return (List<DtoSeguimiento>) lstResultado;
	}

	public List<DtoWfEstado> listarEstadosPorProceso(String proceso) {
		List<DtoWfEstado> estadoBeanRaw = dao.obtenerEstadoProceso(proceso);
		return estadoBeanRaw;
	}

	public DominioPaginacion listarSelectorpaginado(DtoSelectorDinamico filtro) {
		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.estaVacio(filtro.getCodigo())) {
			filtro.setCodigo(null);
		}
		if (UString.estaVacio(filtro.getDescripcion())) {
			filtro.setDescripcion(null);
		}

		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_tipo", String.class, filtro.getEstadoId()));

		cantidadEncontrados = dao.contar("wftransacciones.paginadocontarSelector", parametros);

		List lstResultado = dao.listarConPaginacion(filtro.getPaginacion(), parametros,
				"wftransacciones.paginadolistarSelector", DtoTabla.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);

		return filtro.getPaginacion();
	}

	public DtoFlujoAdjunto obtenerLlaveParaSyDocumento(String transaccionUUID) {
		DtoFlujoAdjunto dto = new DtoFlujoAdjunto();
		WfTransacciones bean = dao.obtenerPorUUID(transaccionUUID);

		WfProcesoFlujosNiveles beanNivel = wfProcesoFlujoNivelDaoImpl.obtenerPorId(new WfProcesoFlujosNivelesPk(
				bean.getProcesoid(), bean.getVersionid(), bean.getFlujoid(), bean.getNivelid().intValue() + 1));
		if (beanNivel != null) {
			dto.setFlgVerDocumentoGrupo(beanNivel.getDocumentoFlgColumnaGrupo());
			dto.setFlgVerDocumentoNuevo(beanNivel.getDocumentoFlgBotonNuevo());
		} else {
			dto.setFlgVerDocumentoGrupo("N");
			dto.setFlgVerDocumentoNuevo("N");
		}
		dto.settCompania(bean.getCompaniaSocioId());
		dto.settProceso(bean.getProcesoid());
		dto.settReferencia(bean.getReferencia());
		dto.setEstadoTransaccion(bean.getEstado());
		dto.setTransaccion(bean.getTransaccionid());
		return dto;
	}

	public DtoFlujoAdjunto verPlantilla(DtoFlujoAdjunto bean) throws IOException {
		String tmp_ = dao.verPlantilla(bean) + bean.getArchivonombre();
		logger.debug("tmp_-->" + tmp_);
		byte[] contenido = null;
		if (new File(tmp_).exists()) {
			contenido = UFile.obtenerArregloByte(tmp_);
		}
		bean.setArchivonombre(Paths.get(tmp_).getFileName().toString());

		bean.setArchivonombre(UFile.obtenerNombreWebDescargar(bean.getArchivonombre()));

		bean.setArchivostring(org.apache.commons.codec.binary.Base64.encodeBase64String(contenido));
		bean.setArchivostring(UFile.obtenerNombreWebDescargar(bean.getArchivostring()));
		return bean;
	}

	public DtoWfPlanificacion listarPlanificacion(Integer transaccionid, Integer personaId) {
		DtoWfPlanificacion dto = new DtoWfPlanificacion();

		WfTransacciones transaccion = dao.obtenerPorId(transaccionid);

		List<WfProcesoFlujosNiveles> niveles = wfProcesoFlujoNivelDaoImpl.listar(transaccion.getProcesoid(),
				transaccion.getVersionid(), transaccion.getFlujoid());

		WfProcesoFlujosNiveles nivel = null;

		for (WfProcesoFlujosNiveles row : niveles) {
			if (row.getPk().getNivelid().intValue() == (transaccion.getNivelid().intValue() + 1)) {
				nivel = row;
			}
		}
		if (nivel == null) {
			dto.getConfig().setPuedeEditar("N");
			dto.getConfig().setPuedeVer("N");
			return dto;
		}

		dto.getConfig().setPuedeEditar(nivel.getFlgplanificacioneditable());
		dto.getConfig().setPuedeVer(nivel.getFlgplanificacionver());

		if (dto.getConfig().getPuedeEditar() == null) {
			dto.getConfig().setPuedeEditar("N");
		}
		if (dto.getConfig().getPuedeVer() == null) {
			dto.getConfig().setPuedeVer("N");
		}

		if (dto.getConfig().getPuedeEditar().equals("S")) {
			dto.getConfig().setPuedeActualizar("S");
		} else {
			dto.getConfig().setPuedeActualizar("N");
		}

		if (dto.getConfig().getPuedeVer().equals("S")) {
			dto.setLstEtapas(wfTransaccionPlanificacionDaoImpl.listarPorTransaccion(transaccionid));
			for (DtoWfPlanificacionEtapa row : dto.getLstEtapas()) {
				for (WfProcesoFlujosNiveles n : niveles) {
					if (n.getPk().getNivelid().intValue() == row.getNivel().intValue()) {
						row.setNombre(n.getNombre());
					}
				}
			}
		}

		dto.setTransaccion(transaccionid);
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoWfPlanificacion actualizarPlanificacion(DtoWfPlanificacion dto, SeguridadUsuarioActual usuarioActual) {
		for (DtoWfPlanificacionEtapa row : dto.getLstEtapas()) {
			WfTransaccionplanificacion bean = wfTransaccionPlanificacionDaoImpl
					.obtenerPorId(new WfTransaccionplanificacionPk(dto.getTransaccion(), row.getPlanificacionid()));
			bean.setFechahoraInicio(row.getInicio());
			bean.setFechahoraFin(row.getFin());
			bean.setTipoGeneracionId("M");
			bean.setModificacionFecha(new Date());
			bean.setModificacionUsuario(usuarioActual.getUsuario());
			wfTransaccionPlanificacionDaoImpl.actualizar(bean);
		}
		return dto;
	}

	public DtoWfPlanificacion obtenerProyeccionPlanificacion(WorkFlowTransaccion request,
			SeguridadUsuarioActual usuarioActual) throws UException {
		DtoWfPlanificacion dto = new DtoWfPlanificacion();

		dto.getConfig().setPuedeVer("S");
		dto.getConfig().setPuedeEditar("S");
		dto.getConfig().setPuedeActualizar("N");

		String proceso = request.getProcesoId();
		Integer ultimaVersion = dao.obtenerVersion(proceso);

		// obtener el flujo que le corresponde con las variables disponibles
		// inicio obtener flujo
		List<DtoFlujo> flujosDisponibles = dao.listarFlujosPorProceso(proceso, ultimaVersion);

		List<DtoFlujoConfiguracion> criteriosEvaluarPorFlujo = null;
		List<DtoFlujoConfiguracion> criteriosPorEvaluarPorRequest = new ArrayList<DtoFlujoConfiguracion>();
		DtoFlujo flujoSeleccionado = null;

		if (flujosDisponibles.size() == 0) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA,
					"No se tienen flujos configurados para el proceso"));
			return dto;
		}

		String criterios = armarCriterios(request, usuarioActual);

		try {
			Map<String, String> parametros = new ObjectMapper().readValue(criterios, Map.class);
			for (Map.Entry<String, String> item : parametros.entrySet()) {
				criteriosPorEvaluarPorRequest.add(new DtoFlujoConfiguracion(item.getKey(), item.getValue()));
			}
		} catch (Exception e) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA,
					"La lista de criterios no se puede procesar, debido a un error de formato"));
			e.printStackTrace();
			return dto;
		}

		if (criteriosPorEvaluarPorRequest.size() == 0) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA,
					"No se han enviado los criterios para evaluar"));
			return dto;
		}

		for (DtoFlujo flujo : flujosDisponibles) {

			criteriosEvaluarPorFlujo = dao.listarCriteriosFlujoPorProceso(proceso, ultimaVersion, flujo.getFlujo());

			boolean valida = true;

			for (DtoFlujoConfiguracion criterioEvaluar : criteriosEvaluarPorFlujo) {

				DtoFlujoConfiguracion valorPorComparar = criteriosPorEvaluarPorRequest.stream().filter(
						criterioPorEvaluar -> criterioPorEvaluar.getVariable().equals(criterioEvaluar.getVariable()))
						.findAny().orElse(new DtoFlujoConfiguracion());

				switch (criterioEvaluar.getComparacion()) {
				case ">":
					valida = valida && comparacionMayorQue(criterioEvaluar.getValores(), valorPorComparar.getValor())
							? true
							: false;
					break;
				case "<=":
					valida = valida
							&& comparacionMenorIgualQue(criterioEvaluar.getValores(), valorPorComparar.getValor())
									? true
									: false;
					break;
				case "IN":
					valida = valida && comparacionDentroDe(criterioEvaluar.getValores(), valorPorComparar.getValor())
							? true
							: false;
					break;
				default:
					throw new UException("El comparador " + criterioEvaluar.getComparacion() + " no se puede evaluar",
							tipo_mensaje.ERROR);
				}

			}

			if (valida) {
				if (flujoSeleccionado != null) {
					dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
					dto.getTransaccionListaMensajes()
							.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA,
									"Mas de un flujo coincide con los criterios enviados, "
											+ flujoSeleccionado.getDescripcion() + ", " + flujo.getDescripcion()));
					return dto;
				}
				flujoSeleccionado = flujo;
			}

		}

		if (flujoSeleccionado == null) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA,"Ninguno de los flujos coincide con los criterios enviados"));
			return dto;
		}
		// fin obtener flujo

		List<DtoPlanificacionGenerar> planificacionRaw = dao.generarPlanificacion(proceso, ultimaVersion,
				flujoSeleccionado.getFlujo());

		int i = 1;
		for (DtoPlanificacionGenerar row : planificacionRaw) {
			DtoWfPlanificacionEtapa etapa = new DtoWfPlanificacionEtapa();
			etapa.setInicio(row.getInicio());
			etapa.setFin(row.getFin());
			etapa.setNivel(row.getNivel());
			etapa.setNombre(row.getNombre());
			etapa.setPlanificacionid(i);
			dto.getLstEtapas().add(etapa);
			i++;
		}

		return dto;
	}

	public List<DtoFlujoAdjunto> obtenerAdjuntosSoloLectura(String transaccionUUID, Integer personaId) {
		return dao.obtenerAdjuntosSoloLectura(transaccionUUID, -1, personaId);
	}

	public DominioPaginacion transaccionListarAdministrador(FiltroTransaccion filtro,
			SeguridadUsuarioActual usuarioActual) {
		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.estaVacio(filtro.getReferencia())) {
			filtro.setReferencia(null);
		}
		if (UString.estaVacio(filtro.getAplicacion())) {
			filtro.setAplicacion(null);
		}
		if (UString.estaVacio(filtro.getProceso())) {
			filtro.setProceso(null);
		}
		if (UString.estaVacio(filtro.getCompania())) {
			filtro.setCompania(null);
		}

		if (UInteger.esCeroOrNulo(filtro.getTransaccionId())) {
			filtro.setTransaccionId(null);
		}

		if (UInteger.esCeroOrNulo(filtro.getSolicitante())) {
			filtro.setSolicitante(null);
		}

		if (filtro.getMontodesde() == null)
			filtro.setMontodesde(0.0);

		if (filtro.getMontohasta() == null)
			filtro.setMontohasta(999999999.0);

		parametros.add(new DominioParametroPersistencia("p_est_proceso", String.class, filtro.getEstadoProceso()));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompania()));
		parametros.add(new DominioParametroPersistencia("p_montoDesde", Double.class, filtro.getMontodesde()));
		parametros.add(new DominioParametroPersistencia("p_montoHasta", Double.class, filtro.getMontohasta()));
		parametros.add(new DominioParametroPersistencia("p_referencia", String.class, filtro.getReferencia()));
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getAplicacion()));
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, filtro.getProceso()));
		parametros.add(new DominioParametroPersistencia("p_desde", Date.class, filtro.getDesde()));
		parametros.add(new DominioParametroPersistencia("p_hasta", Date.class, filtro.getHasta()));
		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, filtro.getTransaccionId()));
		parametros.add(new DominioParametroPersistencia("p_solicitante", Integer.class, filtro.getSolicitante()));

		cantidadEncontrados = dao.contar("wftransacciones.transaccionListarAdminContar", parametros);

		List lstResultado = dao.listarConPaginacion(filtro.getPaginacion(), parametros,"wftransacciones.transaccionListarAdminPaginacion", DtoFlujoSolicitud.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);

		return filtro.getPaginacion();
	}

	public DtoTransaccionVistaAvanzada obtenerVistaAvanzada(String transaccionUUID) {
		return dao.obtenerVistaAvanzada(transaccionUUID);
	}

	public List<DtoSeguimientoVistaAvanzada> obtenerSeguimientoVistaAvanzada(String transaccionUUID) {
		return dao.obtenerSeguimientoVistaAvanzada(transaccionUUID);
	}

	public DtoWfFlujoNivel obtenerConfiguracionObservaciones(String transaccionUUID) throws Exception {
		WfTransacciones beanTransaccion = dao.obtenerPorUUID(transaccionUUID);
		if (beanTransaccion == null) {
			String ms = "transaccion no encontrada : transaccion id="
					+ UInteger.obtenerValorEnteroSinNulo(beanTransaccion.getTransaccionid()).toString();
			this.ErrorRegistrar("WfTransaccionesServicioImpl.obtenerConfiguracionObservaciones", ms);
			return null;
		}
		Integer nivel = beanTransaccion.getNivelid().intValue() + 1;

		WfProcesoFlujosNiveles beanNivel = wfProcesoFlujoNivelDaoImpl.obtenerPorId(new WfProcesoFlujosNivelesPk(
				beanTransaccion.getProcesoid(), beanTransaccion.getVersionid(), beanTransaccion.getFlujoid(), nivel));

		DtoWfFlujoNivel dtoConfiguracionesNivel = new DtoWfFlujoNivel();
		if (beanNivel == null) {
			String s = "transaccionid="
					+ UInteger.obtenerValorEnteroSinNulo(beanTransaccion.getTransaccionid()).toString();
			s = s + "|beanTransaccion.getNivelid()="
					+ UInteger.obtenerValorEnteroSinNulo(beanTransaccion.getNivelid()).toString();
			s = s + "|nivel=" + UInteger.obtenerValorEnteroSinNulo(nivel).toString();
			this.ErrorRegistrar(
					new ErrorTransaccion("obtenerConfiguracionObservaciones", s, "WfTransaccionesServicioImpl"));
			dtoConfiguracionesNivel.setAuxFlgComentarioDetalladoAprobar(false);
			dtoConfiguracionesNivel.setAuxFlgComentarioDetalladoRechazar(false);
			dtoConfiguracionesNivel.setAuxFlgComentarioDetalladoDevolver(false);
			return dtoConfiguracionesNivel;
		}

		dtoConfiguracionesNivel.setAuxFlgComentarioDetalladoAprobar(
				UBoolean.validarFlag(beanNivel.getFlgAprobarComentarioDetallado()));
		dtoConfiguracionesNivel.setAuxFlgComentarioDetalladoRechazar(
				UBoolean.validarFlag(beanNivel.getFlgRechazarComentarioDetallado()));
		dtoConfiguracionesNivel.setAuxFlgComentarioDetalladoDevolver(
				UBoolean.validarFlag(beanNivel.getFlgDevolverComentarioDetallado()));

		dtoConfiguracionesNivel.setAuxFlgAprobarComentario(UBoolean.validarFlag(beanNivel.getFlgAprobarComentario()));

		return dtoConfiguracionesNivel;
	}

	public EmailTransaccion obtenerCorreoObservacion(String transaccionUUID, String accionWf) {
		EmailTransaccion emailTransaccion = new EmailTransaccion();

		WfTransacciones bTransaccion = dao.obtenerPorUUID(transaccionUUID);
		WfProcesos bProceso = wfProcesoDaoImpl.obtenerPorId(new WfProcesosPk(bTransaccion.getProcesoid()));
		WfProcesoVersiones bProcesoVersion = wfProcesoVersionDaoImpl
				.obtenerPorId(new WfProcesoVersionesPk(bTransaccion.getProcesoid(), bTransaccion.getVersionid()));

		int nivelActual = bTransaccion.getNivelid();
		int nivelDestino = 0;

		// captura ACCION BASE
		String workFlowAccion = accionWf.substring(0, 6);
		if (accionWf.equals("APROBAR")) {
			nivelDestino = nivelActual + 2; // al que sigue, el siguiente
			// Evaluar si sera seguimiento, cuando hay un nivel luego
			if (wfProcesoFlujoNivelDaoImpl.obtenerPorId(new WfProcesoFlujosNivelesPk(bTransaccion.getProcesoid(),
					bTransaccion.getVersionid(), bTransaccion.getFlujoid(), nivelDestino)) != null) {
				accionWf = "SEGUIMIENTO";
			}
		}
		if (accionWf.equals("DEVOLVER")) {
			nivelDestino = nivelActual - 1;
		}

		// ARMANDO DESTINATARIOS
		// Al rechazar el destinatario es el solicitante
		// Al aprobar el destinatario es el solicitante
		// Al devolver los destinatarios son del nivel anterior
		// Al seguimiento los destinatarios son del siguientenivel

		List<DtoTabla> destinatarios = new ArrayList<DtoTabla>();

		////////////////////////////////////////
		WfProcesoFlujosNiveles nivel = wfProcesoFlujoNivelDaoImpl.obtenerPorId(new WfProcesoFlujosNivelesPk(
				bTransaccion.getProcesoid(), bTransaccion.getVersionid(), bTransaccion.getFlujoid(), nivelDestino));

		if (accionWf.equals("APROBAR") || accionWf.equals("RECHAZAR")) {
			destinatarios = dao.obtenerCorreoSolicitante(bTransaccion.getSolicitanteid());
		} else if (accionWf.equals("SEGUIMIENTO") || accionWf.equals("DEVOLVER")) {
			if (accionWf.equals("DEVOLVER")) {
				destinatarios = dao.obtenerDestinatarios(bTransaccion, nivelDestino + 1, accionWf);
			} else {
				destinatarios = dao.obtenerDestinatarios(bTransaccion, nivelDestino, accionWf);
			}
		}

		for (DtoTabla destinatario : destinatarios) {
			String dest[] = destinatario.getDescripcion().split(";");
			for (String d : dest) {
				if (!UString.estaVacio(d)) {
					EmailDestino destino = new EmailDestino();
					destino.setCorreoDestino(d);
					destino.setDestino(tipo_destino.TO);
					emailTransaccion.getListaCorreoDestino().add(destino);
				}
			}
		}

		// ARMANDO REPORTE
		// El api es de la cabecera, cuando del nivel no tiene
		// Traer metadatos

		String api = "";

		if (nivel != null) {
			api = nivel.getApi();
		}

		if (UString.estaVacio(api)) {
			if (bProceso != null) {
				api = bProcesoVersion.getApi();
			}
		}

		Map metadatos = new HashMap<String, String>();

		if (!UString.estaVacio(api)) {
			metadatos = obtenerMetadatosAPI(api, armarMetadatos(bTransaccion, accionWf));
		}

		// 3 obtener plantilla
		ReporteTransaccion reporteParametro = new ReporteTransaccion();

		reporteParametro.setAplicacionCodigo(bProceso.getAplicacionid());
		reporteParametro.setReporteCodigo(bTransaccion.getProcesoid().substring(0, 2));

		logger.debug("reporteParametro.getAplicacionCodigo() 3:" + reporteParametro.getAplicacionCodigo());
		logger.debug("reporteParametro.getReporteCodigo()    3:" + reporteParametro.getReporteCodigo());

		reporteParametro.setParametros(metadatos);
		reporteParametro.setVersion(accionWf.substring(0, 6));
		reporteParametro.setCompaniaSocio("999999");
		reporteParametro.setPeriodo("999999");
		reporteParametro.setWorkFlowNivel(bTransaccion.getNivelid());
		reporteParametro.setWorkFlowAccion(workFlowAccion);
		reporteParametro.setWorkFlowFlgCorreoNiveles(bProcesoVersion.getFlgCorreoNiveles());
		try {
			ReporteTransaccion reporte = reporteEjecutarWorkFlow(reporteParametro);
			String mensajeReporte = UString.estaVacio(reporte.getTransaccionEstado()) ? ""
					: reporte.getTransaccionEstado();
			if (mensajeReporte.equals(DominioTransaccion.ERROR)) {
				logger.debug("Error Api Reportes");
				logger.debug(reporte.getTransaccionListaMensajes().get(0));
			} else {
				emailTransaccion.setCuerpoCorreoBase64(new String(reporte.getResultadoCuerpoBinario()));
				emailTransaccion.setAsunto(reporte.getResultadoAsunto());
			}
			logger.debug("Reporte Generado");
		} catch (Exception e) {
			logger.debug("Error al generar reporte" + e.getMessage());
			e.printStackTrace();
			emailTransaccion.setCuerpoCorreoBase64("No se pudo obtener la plantilla");
			emailTransaccion.setAsunto("Sin Asunto");
		}

		return emailTransaccion;
	}

	public List<DtoTabla> obtenerMetadatos(Integer transaccionid) {
		logger.debug("obtenerMetadatos");
		List<DtoTabla> lst = new ArrayList<DtoTabla>();
		WfTransacciones bTransaccion = dao.obtenerPorId(transaccionid);
		String api = dao.obtenerApiCabecera(bTransaccion.getProcesoid(), bTransaccion.getVersionid()).getApi();
		logger.debug(api);
		if (!UString.estaVacio(api)) {
			HashMap<String, Object> metadatos = obtenerMetadatosAPI(api, armarMetadatos(bTransaccion, null));
			logger.debug("metadatos:" + metadatos);
			for (Map.Entry<String, Object> entry : metadatos.entrySet()) {
				String valor = entry.getValue() == null ? null : entry.getValue().toString();
				lst.add(new DtoTabla(entry.getKey(), valor));
			}
		}
		return lst;
	}

	public DtoTabla obtenerTitleWF(String transaccionUUID) {
		return dao.obtenerTitleWF(transaccionUUID);
	}

	public WorkFlowResultado armarMetadatos(WfTransacciones wfTransaccion, String accion) {
		WorkFlowResultado wrMetadato = new WorkFlowResultado(wfTransaccion.getTransaccionid());
		wrMetadato.setCodigoproceso(wfTransaccion.getProcesoid());
		wrMetadato.setReferencia(wfTransaccion.getReferencia());
		wrMetadato.setSolicitanteId(wfTransaccion.getSolicitanteid());
		wrMetadato.setFechaRegistro(wfTransaccion.getFecharegistro());
		wrMetadato.setAccion(accion);
		return wrMetadato;
	}

	public DtoTabla exportar(String uuid) {
		WfProcesoVersiones p = wfProcesoVersionDaoImpl.obtenerPorUuid(uuid);
		Integer version = p.getPk().getVersionid();
		String proceso = p.getPk().getProcesoid();

		DtoTabla json = new DtoTabla();
		try {
			DtoExportarWf dtoExportarWf = new DtoExportarWf();
			dtoExportarWf.setWfFlujoAcciones(wfProcesoFlujoNivelAccionDaoImpl.exportar(proceso, version));
			dtoExportarWf.setWfFlujoAprobadores(wfProcesoFlujoNivelAprobadorDaoImpl.exportar(proceso, version));
			dtoExportarWf.setWfFlujoConfiguraciones(wfProcesoFlujoConfiguracionDaoImpl.exportar(proceso, version));
			dtoExportarWf.setWfFlujoNiveles(wfProcesoFlujoNivelDaoImpl.exportar(proceso, version));
			dtoExportarWf.setWfFlujoNivelDocumentos(wfProcesoFlujoNivelDocumentoDaoImpl.exportar(proceso, version));
			dtoExportarWf.setWfProcesos(wfProcesoDaoImpl.exportar(proceso, version));
			dtoExportarWf.setWfProcesoDocumentos(wfProcesoDocumentoDaoImpl.exportar(proceso, version));
			dtoExportarWf.setWfProcesoEstados(wfProcesoEstadoDaoImpl.exportar(proceso, version));
			dtoExportarWf.setWfProcesoFlujos(wfProcesoFlujoDaoImpl.exportar(proceso, version));
			dtoExportarWf.setWfProcesoVariables(wfProcesoVariableDaoImpl.exportar(proceso, version));
			dtoExportarWf.setWfProcesoVersiones(wfProcesoVersionDaoImpl.exportar(proceso, version));
			dtoExportarWf.setWfProcesoRoles(wfProcesoRolDaoImpl.exportar(proceso, version));
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValueAsString(dtoExportarWf);
			json.setNombre("WF_" + proceso + "_" + new SimpleDateFormat("ddMMyyyy_hhmss").format(new Date()) + ".json");
			json.setDescripcion(mapper.writeValueAsString(dtoExportarWf));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public DtoTabla importar(DtoTabla json, SeguridadUsuarioActual seguridadUsuarioActual) {
		ObjectMapper mapper = new ObjectMapper();
		DtoTabla d = new DtoTabla();
		try {
			String parsed = new String(Base64.getDecoder().decode(json.getDescripcion()), "UTF-8");
			json.setDescripcion(parsed);
			DtoExportarWf dtoImportarWf = mapper.readValue(json.getDescripcion(), DtoExportarWf.class);

			// si ya existe el proceso, eliminarlo
			String procesoId = dtoImportarWf.getWfProcesoVersiones().get(0).getProcesoId();
			Integer versionId = dtoImportarWf.getWfProcesoVersiones().get(0).getVersionId();

			boolean existeVersion = existeVersion(procesoId, versionId);

			if (existeVersion) {
				eliminarProceso(procesoId, versionId);
			}

			// wfProcesos
			for (DtoExportarWfProceso row : dtoImportarWf.getWfProcesos()) {
				wfProcesoDaoImpl.importar(row, seguridadUsuarioActual, existeVersion);
			}

			// wfProcesoVersiones
			for (DtoExportarWfProcesoVersion row : dtoImportarWf.getWfProcesoVersiones()) {
				wfProcesoVersionDaoImpl.importar(row, seguridadUsuarioActual, existeVersion);
			}

			// wfProcesoRoles
			for (DtoExportarWfProcesoRol row : dtoImportarWf.getWfProcesoRoles()) {
				wfProcesoRolDaoImpl.importar(row, seguridadUsuarioActual);
			}

			// wfProcesoDocumentos
			for (DtoExportarWfProcesoDocumento row : dtoImportarWf.getWfProcesoDocumentos()) {
				wfProcesoDocumentoDaoImpl.importar(row, seguridadUsuarioActual);
			}

			// wfProcesoEstados
			for (DtoExportarWfProcesoEstado row : dtoImportarWf.getWfProcesoEstados()) {
				wfProcesoEstadoDaoImpl.importar(row, seguridadUsuarioActual);
			}

			// wfProcesoVariables
			for (DtoExportarWfProcesoVariable row : dtoImportarWf.getWfProcesoVariables()) {
				wfProcesoVariableDaoImpl.importar(row, seguridadUsuarioActual);
			}

			// wfProcesoFlujos
			for (DtoExportarWfProcesoFlujo row : dtoImportarWf.getWfProcesoFlujos()) {
				wfProcesoFlujoDaoImpl.importar(row, seguridadUsuarioActual);
			}

			// wfFlujoConfiguraciones
			for (DtoExportarWfFlujoConfiguracion row : dtoImportarWf.getWfFlujoConfiguraciones()) {
				wfProcesoFlujoConfiguracionDaoImpl.importar(row, seguridadUsuarioActual);
			}

			// wfFlujoNiveles
			for (DtoExportarWfFlujoNivel row : dtoImportarWf.getWfFlujoNiveles()) {
				wfProcesoFlujoNivelDaoImpl.importar(row, seguridadUsuarioActual);
			}

			// wfFlujoAcciones
			for (DtoExportarWfFlujoAccion row : dtoImportarWf.getWfFlujoAcciones()) {
				wfProcesoFlujoNivelAccionDaoImpl.importar(row, seguridadUsuarioActual);
			}

			// wfFlujoAprobadores
			for (DtoExportarWfFlujoAprobador row : dtoImportarWf.getWfFlujoAprobadores()) {
				wfProcesoFlujoNivelAprobadorDaoImpl.importar(row, seguridadUsuarioActual);
			}

			// wfFlujoNivelDocumentos
			for (DtoExportarWfFlujoNivelDocumento row : dtoImportarWf.getWfFlujoNivelDocumentos()) {
				wfProcesoFlujoNivelDocumentoDaoImpl.importar(row, seguridadUsuarioActual);
			}

		} catch (Exception e) {
			e.printStackTrace();
			d.setDescripcion("Error : " + e.getMessage());
			d.setCodigo("ERROR");
			return d;
		}
		d.setDescripcion("Registro importado");
		d.setCodigo("OK");
		return d;
	}

	private boolean existeVersion(String procesoId, Integer versionId) {
		return dao.existeVersion(procesoId, versionId);
	}

	public DominioPaginacion listarSyTipoDocumentos(FiltroSyDocumentos filtro) {
		return dao.listarSyTipoDocumentos(filtro);
	}

	public List<DtoTabla> syprocesomst() {
		return dao.syprocesomst();
	}

	public List<DtoTabla> sytipodocumento() {
		return dao.sytipodocumento();
	}

	public List<DtoTabla> obtenerTransaccionAprobadorVistaAvanzada(String transaccionUUID) {
		return dao.obtenerTransaccionAprobadorVistaAvanzada(transaccionUUID);
	}

	public List<DtoWfProceso> listarProcesosSinVersion(DtoTabla filtro) {
		return dao.listarProcesosSinVersion(filtro);
	}

	public List<DtoFlujoAdjunto> obtenerAdjuntosEditable(String transaccionUUID, Integer nivel, Integer personaId) {
		return dao.obtenerAdjuntosSoloLectura(transaccionUUID, nivel, personaId);
	}

	public DtoTabla registrarNotificacionMasiva(DtoNotificacionMasiva bean, SeguridadUsuarioActual usuarioActual) {

		Date fecha = new Date();
		String usuario = usuarioActual.getUsuario();

		for (DtoPersonaNotificar row : bean.getTransacciones()) {
			WfTransacciones bTransaccion = dao.obtenerPorId(row.getTransaccionId());
			wfTransaccionalertaDaoImpl.eliminarPorTransaccion(row.getTransaccionId());
			WfTransaccionalerta alerta = new WfTransaccionalerta();
			alerta.setNombre(bean.getMensaje());
			alerta.setPersonaId(row.getPersonaId());
			alerta.setTransaccionId(row.getTransaccionId());
			alerta.setProcesoId(bTransaccion.getProcesoid());
			alerta.setAccion("MENSAJE");
			alerta.setLink(row.getAuxlink());
			alerta.setEstado("A");
			alerta.setCreacionFecha(fecha);
			alerta.setCreacionUsuario(usuario);
			wfTransaccionalertaDaoImpl.registrar(alerta);
		}

		return new DtoTabla();
	}

	public void registrarNuevosAprobadores(Integer transaccionGenerada,
			List<WorkFlowAprobadorTransaccion> listaAprobador, SeguridadUsuarioActual usuarioActual) {
		int i = wfTransaccionaprobadorDaoImpl.obtenerSecuencia(transaccionGenerada);
		for (WorkFlowAprobadorTransaccion aprobador : listaAprobador) {
			WfTransaccionaprobador wfTransaccionaprobador = new WfTransaccionaprobador();
			wfTransaccionaprobador.setCreacionfecha(new Date());
			wfTransaccionaprobador.setCreacionusuario(usuarioActual.getUsuario());
			wfTransaccionaprobador.setPersonaid(aprobador.getPersonaId());
			wfTransaccionaprobador.getPk().setTransaccionid(new BigDecimal(transaccionGenerada));
			wfTransaccionaprobador.getPk().setDetalleid(new BigDecimal(i));
			wfTransaccionaprobador.getPk().setNivelid(aprobador.getNivelId());
			wfTransaccionaprobadorDaoImpl.registrar(wfTransaccionaprobador);
			i++;
		}

	}

	public List<DtoWfVistaAdminConfiguracionDocumentos> obtenerConfiguracionDocumentosVistaAvanzada(
			String transaccionUUID) {
		return dao.obtenerConfiguracionDocumentosVistaAvanzada(transaccionUUID);
	}

	public WorkFlowResultado validarPropiedarioIdUsuarioActual(WorkFlowTransaccion request) throws Exception {
		WorkFlowResultado res = new WorkFlowResultado();
		try {
			Integer contador = dao.validarPropiedarioIdContar(request.getTransaccionId(),
					this.getUsuarioActual().getPersonaId());
			if (contador == 0) {
				res.setEstado(WorkFlowResultado.ERROR);
				res.setMensaje("El usuario actual no es propietario de informacion");
			} else {
				res.setEstado(WorkFlowResultado.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setEstado(WorkFlowResultado.ERROR);
			res.setMensaje("Ocurrio un error");
		}
		return res;
	}

	public List<DtoJerarquiaMacroProceso> listarJerarquiaMacroProceso(String transaccionUUID) {
		List<DominioParametroPersistencia> parametros = new ArrayList();
		WfTransacciones beanWF = dao.obtenerPorUUID(transaccionUUID);
		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, beanWF.getTransaccionid()));
		List lstResultado = dao.listarPorQuery(DtoJerarquiaMacroProceso.class,
				"wftransacciones.listarJerarquiaMacroProceso", parametros);
		return (List<DtoJerarquiaMacroProceso>) lstResultado;
	}

	public List<ReporteArchivoTransaccion> obtenerPlantillasRelacionadas(String uuid) {
		WfProcesoVersiones beanProcesosVersiones = wfProcesoVersionDaoImpl.obtenerPorUuid(uuid);
		List<DominioParametroPersistencia> parametros = new ArrayList();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class,
				beanProcesosVersiones.getPk().getProcesoid()));
		List lstResultado = dao.listarPorQuery(ReporteArchivoTransaccion.class,
				"wftransacciones.obtenerPlantillasRelacionadas", parametros);
		return (List<ReporteArchivoTransaccion>) lstResultado;
	}
}
