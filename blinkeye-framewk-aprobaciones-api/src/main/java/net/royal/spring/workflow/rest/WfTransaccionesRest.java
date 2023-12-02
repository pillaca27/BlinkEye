package net.royal.spring.workflow.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.WorkFlowAdjunto;
import net.royal.spring.framework.modelo.WorkFlowEnvioCorreo;
import net.royal.spring.framework.modelo.WorkFlowProceso;
import net.royal.spring.framework.modelo.WorkFlowResultado;
import net.royal.spring.framework.modelo.WorkFlowSeguimiento;
import net.royal.spring.framework.modelo.WorkFlowTransaccion;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.workflow.dao.impl.WfProcesoVersionesDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesosDaoImpl;
import net.royal.spring.workflow.dao.impl.WfTransaccionesDaoImpl;
import net.royal.spring.workflow.dominio.WfProcesoVersiones;
import net.royal.spring.workflow.dominio.WfProcesoVersionesPk;
import net.royal.spring.workflow.dominio.WfProcesos;
import net.royal.spring.workflow.dominio.WfProcesosPk;
import net.royal.spring.workflow.dominio.WfTransacciones;
import net.royal.spring.workflow.dominio.WfTransaccionesPk;
import net.royal.spring.workflow.dominio.dto.DtoAprobacionAcciones;
import net.royal.spring.workflow.dominio.dto.DtoFirmaCarga;
import net.royal.spring.workflow.dominio.dto.DtoFirmaData;
import net.royal.spring.workflow.dominio.dto.DtoFlujoAdjunto;
import net.royal.spring.workflow.dominio.dto.DtoFlujoEjecutar;
import net.royal.spring.workflow.dominio.dto.DtoFlujoSolicitud;
import net.royal.spring.workflow.dominio.dto.DtoFlujoTransaccionRequest;
import net.royal.spring.workflow.dominio.dto.DtoFlujoTransaccionResponse;
import net.royal.spring.workflow.dominio.dto.DtoJerarquiaMacroProceso;
import net.royal.spring.workflow.dominio.dto.DtoNotificacionMasiva;
import net.royal.spring.workflow.dominio.dto.DtoSeguimiento;
import net.royal.spring.workflow.dominio.dto.DtoSeguimientoVistaAvanzada;
import net.royal.spring.workflow.dominio.dto.DtoTransaccionCorreoFiltros;
import net.royal.spring.workflow.dominio.dto.DtoTransaccionCorreoListado;
import net.royal.spring.workflow.dominio.dto.DtoTransaccionCorreoListadoHeader;
import net.royal.spring.workflow.dominio.dto.DtoTransaccionVistaAvanzada;
import net.royal.spring.workflow.dominio.dto.DtoWfFlujoNivel;
import net.royal.spring.workflow.dominio.dto.DtoWfPlanificacion;
import net.royal.spring.workflow.dominio.dto.DtoWfVistaAdminConfiguracionDocumentos;
import net.royal.spring.workflow.dominio.filtro.FiltroSolicitudes;
import net.royal.spring.workflow.dominio.filtro.FiltroTransaccion;
import net.royal.spring.workflow.servicio.impl.WfHerramientasServicioImpl;
import net.royal.spring.workflow.servicio.impl.WfTransaccionesServicioImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spring/workflow/wftransaccion")
public class WfTransaccionesRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(WfTransaccionesRest.class);

	@Autowired
	private WfTransaccionesServicioImpl servicio;

	@Autowired
	private WfTransaccionesDaoImpl wfTransaccionesDao;

	@Autowired
	private WfProcesosDaoImpl wfProcesosDao;

	@Autowired
	private WfProcesoVersionesDaoImpl wfProcesoVersionesDaoImpl;

	@Autowired
	private WfHerramientasServicioImpl wfHerramientasServicio;

	@Transactional(readOnly = true)
	@PostMapping(value = "/transaccionListarAprobacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> transaccionListarAprobacion(@RequestBody FiltroSolicitudes filtro)
			throws Exception {
		return new ResponseEntity<DominioPaginacion>(servicio.transaccionListarAprobacion(filtro, getUsuarioActual()),
				HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/transaccionEjecutar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> transaccionEjecutar(@RequestBody DtoFlujoEjecutar ejecutar)
			throws Exception {
		return new ResponseEntity<List<DominioMensajeUsuario>>(
				servicio.transaccionEjecutar(ejecutar, getUsuarioActual()), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/transaccionListarSolicitante", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> transaccionListarSolicitante(@RequestBody FiltroTransaccion filtro)
			throws Exception {
		return new ResponseEntity<DominioPaginacion>(servicio.transaccionListarSolicitante(filtro, getUsuarioActual()),
				HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrarExterno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowResultado> registrarExterno(@RequestBody WorkFlowTransaccion request)
			throws Exception {
		return new ResponseEntity<WorkFlowResultado>(
				servicio.registrarExterno(request,
						request.getUsuarioActual() == null ? getUsuarioActual() : request.getUsuarioActual()),
				HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/seguimientoExterno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowSeguimiento> seguimientoExterno(@RequestBody WorkFlowSeguimiento request)
			throws Exception {
		logger.debug("seguimientoExterno");
		DtoFlujoSolicitud e = new DtoFlujoSolicitud();
		DtoFlujoEjecutar ejecutar = new DtoFlujoEjecutar();
		List<DtoFlujoAdjunto> adjuntos = new ArrayList<DtoFlujoAdjunto>();
		try {
			logger.debug("request");
			logger.debug("accion:" + request.getAccion());
			logger.debug("subacc:" + request.getSubaccion());
			logger.debug("tranid:" + request.getTransaccionId());
			logger.debug("observ:" + request.getObservaciones());

			ejecutar.setAccion(request.getAccion());
			ejecutar.setSubaccion(request.getSubaccion());

			/**/
			for (WorkFlowAdjunto adj : request.getAdjuntos()) {
				adjuntos.add(new DtoFlujoAdjunto(adj));
			}

			e.setTransaccion(request.getTransaccionId());
			e.setObservaciones(request.getObservaciones());
			e.setAdjuntos(adjuntos);
			
			//Malca - Datos adicionales
			DtoTabla bean = wfTransaccionesDao.obtenerWfTransaccion(request.getTransaccionId());
			e.setNivelActual(bean.getId());
			e.setUuid(bean.getCodigo());

			ejecutar.getListaSolicitudes().add(e);

			logger.debug("ejecutar");

			logger.debug("accion:" + ejecutar.getAccion());
			logger.debug("subacc:" + ejecutar.getSubaccion());
			logger.debug("transa:" + ejecutar.getListaSolicitudes().get(0).getTransaccion());
			logger.debug("observ:" + ejecutar.getListaSolicitudes().get(0).getObservaciones());
			logger.debug("uuid:" + ejecutar.getListaSolicitudes().get(0).getUuid());

			List<DominioMensajeUsuario> lst = servicio.transaccionEjecutar(ejecutar,
					request.getUsuarioActual() == null ? getUsuarioActual() : request.getUsuarioActual());
			if (lst.size() == 0) {
				request.setTransaccionEstado(DominioTransaccion.OK);
				request.getTransaccionListaMensajes().clear();

				// Alejandro, si se envia una lista de aprobadores se insertan en
				// wf_transaccionaprobadores
				if (request.getListaAprobador() != null) {
					servicio.registrarNuevosAprobadores(request.getTransaccionId(), request.getListaAprobador(),
							request.getUsuarioActual() == null ? getUsuarioActual() : request.getUsuarioActual());
				}
			} else {
				request.setTransaccionEstado(DominioTransaccion.ERROR);
				request.getTransaccionListaMensajes().addAll(lst);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			request.setTransaccionEstado(DominioTransaccion.ERROR);
			request.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e2));
		}
		return new ResponseEntity<WorkFlowSeguimiento>(request, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/obtenerExterno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowTransaccion> obtenerExterno(@RequestBody WorkFlowTransaccion bean) throws Exception {
		logger.debug("obtenerExterno");
		try {
			WfTransacciones res = wfTransaccionesDao.obtenerPorId(bean.getTransaccionId());
			if (res == null) {
				bean.setTransaccionEstado(DominioTransaccion.ERROR);
				bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No encontrado"));
				return new ResponseEntity<WorkFlowTransaccion>(bean, HttpStatus.OK);
			}
			bean.setTransaccionEstado(DominioTransaccion.OK);
			bean.setNivelEstadoId(res.getNivelEstadoId());
			bean.setNivelId(res.getNivelid());
			bean.setNivelSiguienteEstadoId(res.getNivelEstadoSiguienteId());

		} catch (Exception e2) {
			e2.printStackTrace();
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e2));
		}
		return new ResponseEntity<WorkFlowTransaccion>(bean, HttpStatus.OK);
	}

	/**
	 * Como no mandan la version, se retorna los datos de la ultima version
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@PutMapping(value = "/obtenerProceso", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowProceso> obtenerProceso(@RequestBody WorkFlowProceso bean) throws Exception {
		logger.debug("WorkFlowProceso");
		try {
			Integer ultimaVersion = wfTransaccionesDao.obtenerVersion(bean.getProcesoId());
			WfProcesoVersiones res = wfProcesoVersionesDaoImpl
					.obtenerPorId(new WfProcesoVersionesPk(bean.getProcesoId(), ultimaVersion));
			if (res == null) {
				bean.setTransaccionEstado(DominioTransaccion.ERROR);
				bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No encontrado"));
				return new ResponseEntity<WorkFlowProceso>(bean, HttpStatus.OK);
			}
			bean.setTransaccionEstado(DominioTransaccion.OK);
			bean.setAdministradorId(res.getAdministradorId());
			bean.setNivelEstadoIdInicial(res.getNivelestadoidinicial());
		} catch (Exception e2) {
			e2.printStackTrace();
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e2));
		}
		return new ResponseEntity<WorkFlowProceso>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/obtenerEstadoSiguienteExterno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowTransaccion> obtenerEstadoSiguienteExterno(@RequestBody WorkFlowTransaccion bean)
			throws Exception {
		logger.debug("obtenerEstadoSiguienteExterno");
		try {
			String res = wfTransaccionesDao.obtenerSiguienteEstado(bean.getTransaccionId());
			if (UString.esNuloVacio(res)) {
				WfTransacciones res2 = wfTransaccionesDao.obtenerPorId(bean.getTransaccionId());
				if (res2 == null) {
					bean.setTransaccionEstado(DominioTransaccion.ERROR);
					bean.getTransaccionListaMensajes()
							.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No encontrado"));
				} else {
					res = res2.getNivelEstadoId();
					bean.setNivelSiguienteEstadoId(res);
					bean.setTransaccionEstado(DominioTransaccion.OK);
				}
			} else {
				bean.setNivelSiguienteEstadoId(res);
				bean.setTransaccionEstado(DominioTransaccion.OK);
			}

		} catch (Exception e2) {
			e2.printStackTrace();
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e2));
		}
		return new ResponseEntity<WorkFlowTransaccion>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/obtenerEstadoAnteriorExterno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowTransaccion> obtenerEstadoAnteriorExterno(@RequestBody WorkFlowTransaccion bean)
			throws Exception {
		logger.debug("obtenerEstadoAnteriorExterno");
		try {
			String res = wfTransaccionesDao.obtenerAnteriorEstadoExterno(bean.getTransaccionId());
			logger.debug(res);
			if (UString.esNuloVacio(res)) {
				bean.setTransaccionEstado(DominioTransaccion.ERROR);
				bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No encontrado"));
			} else {
				bean.setNivelAnteriorEstadoId(res);
				bean.setTransaccionEstado(DominioTransaccion.OK);
			}

		} catch (Exception e2) {
			e2.printStackTrace();
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e2));
		}
		return new ResponseEntity<WorkFlowTransaccion>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/obtenerEstadoRechazadoExterno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowTransaccion> obtenerEstadoRechazadoExterno(@RequestBody WorkFlowTransaccion bean)
			throws Exception {
		logger.debug("obtenerEstadoRechazadoExterno");
		try {
			String res = wfTransaccionesDao.obtenerRechazadoEstado(bean.getTransaccionId());
			if (UString.esNuloVacio(res)) {
				WfTransacciones res2 = wfTransaccionesDao.obtenerPorId(bean.getTransaccionId());
				if (res2 == null) {
					bean.setTransaccionEstado(DominioTransaccion.ERROR);
					bean.getTransaccionListaMensajes()
							.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No encontrado"));
				} else {
					res = res2.getNivelEstadoId();
					bean.setNivelRechazadoEstadoId(res);
					bean.setTransaccionEstado(DominioTransaccion.OK);
				}
			} else {
				bean.setNivelRechazadoEstadoId(res);
				bean.setTransaccionEstado(DominioTransaccion.OK);
			}

		} catch (Exception e2) {
			e2.printStackTrace();
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e2));
		}
		return new ResponseEntity<WorkFlowTransaccion>(bean, HttpStatus.OK);
	}

	/**
	 * 
	 * @param bean transaccionId nivelSiguienteEstadoId
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@PutMapping(value = "/saltarNivelExterno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowTransaccion> saltarNivelExterno(@RequestBody WorkFlowTransaccion bean)
			throws Exception {
		logger.debug("obtenerEstadoRechazadoExterno");
		logger.debug("getTransaccionId:" + bean.getTransaccionId());
		logger.debug("getNivelSiguienteEstadoId:" + bean.getNivelSiguienteEstadoId());
		try {
			WfTransacciones res2 = wfTransaccionesDao.obtenerPorId(bean.getTransaccionId());

			if (res2 == null) {
				bean.setTransaccionEstado(DominioTransaccion.ERROR);
				bean.getTransaccionListaMensajes()
						.add(new DominioMensajeUsuario(new Exception("Transaccion no encontrada")));
				return new ResponseEntity<WorkFlowTransaccion>(bean, HttpStatus.OK);
			}

			Integer nivelid = res2.getNivelid();
			String nivelEstadoId = res2.getNivelEstadoId();
			String nivelEstadoSiguienteId = res2.getNivelEstadoSiguienteId();

			List<DtoWfFlujoNivel> lst = wfTransaccionesDao.obtenerNivelAnteriorEstado(bean.getTransaccionId(),
					bean.getNivelSiguienteEstadoId());
			if (lst.size() == 1) {
				nivelid = lst.get(0).getNivel();
				nivelEstadoId = lst.get(0).getEstado();
				nivelEstadoSiguienteId = bean.getNivelSiguienteEstadoId();
				bean.setNivelEstadoId(nivelEstadoId);
			}

			res2.setNivelid(nivelid);
			res2.setNivelEstadoId(nivelEstadoId);
			res2.setNivelEstadoSiguienteId(nivelEstadoSiguienteId);
			wfTransaccionesDao.actualizar(res2);

			bean.setTransaccionEstado(DominioTransaccion.OK);
		} catch (Exception e2) {
			e2.printStackTrace();
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e2));
		}
		return new ResponseEntity<WorkFlowTransaccion>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/enviarCorreoExterno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowEnvioCorreo> enviarCorreoExterno(@RequestBody WorkFlowEnvioCorreo bean)
			throws Exception {
		logger.debug("enviarCorreoExterno");
		bean = wfHerramientasServicio.enviarCorreo(bean, "enviarCorreoExterno");
		return new ResponseEntity<WorkFlowEnvioCorreo>(bean, HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerAccionesAprobacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAprobacionAcciones> obtenerAccionesAprobacion(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<DtoAprobacionAcciones>(
				servicio.obtenerAccionesAprobacion(dt.getCodigo(), getUsuarioActual().getPersonaId()), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/listarSeguimientoWF", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoSeguimiento>> listarSeguimientoWF(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<List<DtoSeguimiento>>(servicio.listarSeguimientoWF(dt.getId()), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/listarSeguimientoGeneralWF", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoSeguimiento>> listarSeguimientoGeneralWF(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<List<DtoSeguimiento>>(servicio.listarSeguimientoGeneralWF(dt.getCodigo()),
				HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerAdjuntosAprobacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoFlujoAdjunto>> obtenerAdjuntosAprobacion(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<List<DtoFlujoAdjunto>>(servicio.obtenerAdjuntosAprobacion(dt.getCodigo()),
				HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerAdjuntosAprobacionPadre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoFlujoAdjunto>> obtenerAdjuntosAprobacionPadre(@RequestBody DtoTabla dt)
			throws Exception {
		return new ResponseEntity<List<DtoFlujoAdjunto>>(servicio.obtenerAdjuntosAprobacionPadre(dt.getId()),
				HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrarAdjunto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoFlujoAdjunto> registrarAdjunto(@RequestBody DtoFlujoAdjunto bean) throws Exception {
		return new ResponseEntity<DtoFlujoAdjunto>(servicio.registrarAdjunto(bean, getUsuarioActual(), null), HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/actualizarAdjunto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoFlujoAdjunto> actualizarAdjunto(@RequestBody DtoFlujoAdjunto bean) throws Exception {
		return new ResponseEntity<DtoFlujoAdjunto>(servicio.actualizarAdjunto(bean, getUsuarioActual()), HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/eliminarAdjunto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoFlujoAdjunto> eliminarAdjunto(@RequestBody DtoFlujoAdjunto bean) throws Exception {
		return new ResponseEntity<DtoFlujoAdjunto>(servicio.eliminarAdjunto(bean), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/verAdjunto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoFlujoAdjunto> verAdjunto(@RequestBody DtoFlujoAdjunto bean) throws Exception {
		return new ResponseEntity<DtoFlujoAdjunto>(servicio.verAdjunto(bean, false), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/verAdjuntoWf", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowAdjunto> verAdjuntoWf(@RequestBody WorkFlowAdjunto bean) throws Exception {
		return new ResponseEntity<WorkFlowAdjunto>(servicio.verAdjuntoWf(bean, false), HttpStatus.OK);
	}

	/**
	 * Si el usuario actual no es el aprobador actual, listar los documentos donde
	 * si es responsable
	 * 
	 * @param transaccionid
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	@PutMapping(value = "/listarDocumentoRequeridos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarDocumentoRequeridos(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<List<DtoTabla>>(
				servicio.listarDocumentoRequeridos(dt.getCodigo(), getUsuarioActual()), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/listarDocumentoRequeridosCero", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarDocumentoRequeridosCero(@RequestBody DtoTabla dto) throws Exception {
		WfTransacciones bean = new WfTransacciones();
		bean.setProcesoid(dto.getCodigo());
		bean.setVersionid(dto.getId());
		bean.setFlujoid(Integer.parseInt(dto.getNombre()));
		return new ResponseEntity<List<DtoTabla>>(servicio.listarDocumentoRequeridosCero(bean, getUsuarioActual()),
				HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/listarDocumentoRequeridosPadre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarDocumentoRequeridosPadre(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<List<DtoTabla>>(
				servicio.listarDocumentoRequeridosPadre(dt.getId(), getUsuarioActual()), HttpStatus.OK);
	}

	/**
	 * Trae todos los documentos
	 * 
	 * @param transaccionid
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	@PutMapping(value = "/listarDocumentoTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarDocumentoTodos(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<List<DtoTabla>>(servicio.listarDocumentoTodos(dt.getId(), getUsuarioActual()),
				HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/escribirImagenPDF", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoTabla escribirImagenPDF(@RequestBody DtoFlujoAdjunto dto) throws Exception {
		return servicio.escribirImagenPDF(dto, getUsuarioActual());
	}

	@Transactional
	@PostMapping(value = "/firmar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> firmar(@RequestBody List<DtoFlujoAdjunto> adjuntosFirmar) throws Exception {
		return new ResponseEntity<DtoTabla>(servicio.firmar(adjuntosFirmar, getUsuarioActual()), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/consultarFinFirma", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> consultarFinFirma(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<List>(servicio.consultarFinFirma(Integer.parseInt(dt.getCodigo()),
				dt.getDescripcion(), getUsuarioActual()), HttpStatus.OK);
	}

	@PostMapping(value = "/obtenerDatos", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoFirmaData obtenerDatos(@RequestBody DtoFirmaCarga firmaCarga) throws Exception {
		return servicio.obtenerDatos(firmaCarga);
	}

	@PostMapping(value = "/confirmarDatos", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoFirmaCarga confirmarDatos(@RequestBody DtoFirmaData firmaCarga) throws Exception {
		return servicio.confirmarDatos(firmaCarga, getUsuarioActual());
	}

	@Transactional
	@GetMapping(value = "/listarAplicacionPorUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarAplicacionPorUsuario() throws Exception {
		return new ResponseEntity<List<DtoTabla>>(servicio.listarAplicacionPorUsuario(getUsuarioActual().getUsuario()),
				HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listarMiscelaneosActivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarMiscelaneosActivos(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<List<DtoTabla>>(
				servicio.listarMiscelaneosActivos(dt.getDescripcion(), dt.getCodigo()), HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listarCompaniasActivas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarCompaniasActivas() throws Exception {
		return new ResponseEntity<List<DtoTabla>>(servicio.listarCompaniasActivas(), HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/filtrarEmpleados", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> filtrarEmpleados(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<List<DtoTabla>>(servicio.filtrarEmpleados(dt.getCodigo()), HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/test0001", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowResultado> test0001(@RequestBody DtoTabla dt) throws Exception {
		WorkFlowTransaccion dto = new WorkFlowTransaccion();
		dto.setEmpleadosolicitanteId(3120); // CGUERREROS
		dto.setDocumentoReferencia(dt.getCodigo());
		dto.setProcesoId("XQ");
		dto.setCompaniasocioId("00000000");
		return new ResponseEntity<WorkFlowResultado>(servicio.workflowRegistrar(dto), HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/transaccionIniciar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoFlujoTransaccionResponse transaccionIniciar(@RequestBody DtoFlujoTransaccionRequest dto)
			throws Exception {
		SeguridadUsuarioActual seguridadUsuarioActual = getUsuarioActual();
		dto.setSolicitante(seguridadUsuarioActual.getPersonaId());
		return servicio.transaccionIniciar(dto, seguridadUsuarioActual, new ArrayList<DominioParametroPersistencia>());
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/obtenerPorId", produces = MediaType.APPLICATION_JSON_VALUE)
	public WfTransacciones obtenerPorId(@RequestBody DtoTabla dto) throws Exception {
		return servicio.obtenerPorId(dto.getCodigo());
	}

	@Transactional
	@PostMapping(value = "/obtenerCabecera", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoFlujoAdjunto> obtenerCabecera(@RequestBody DtoTabla dto) throws Exception {
		return servicio.obtenerCabecera(dto.getCodigo());
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/wfvalidar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> wfvalidar(@RequestBody WorkFlowResultado request)
			throws Exception {
		List<DominioMensajeUsuario> validaciones = new ArrayList<DominioMensajeUsuario>();
		return new ResponseEntity<List<DominioMensajeUsuario>>(validaciones, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/wfseguimiento", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowResultado> wfseguimiento(@RequestBody WorkFlowResultado request) throws Exception {
		request.setEstado(WorkFlowResultado.OK);
		return new ResponseEntity<WorkFlowResultado>(request, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/wfaprobar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowResultado> wfaprobar(@RequestBody WorkFlowResultado request) throws Exception {
		request.setEstado(WorkFlowResultado.OK);
		return new ResponseEntity<WorkFlowResultado>(request, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/wfrechazar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowResultado> wfrechazar(@RequestBody WorkFlowResultado request) throws Exception {
		request.setEstado(WorkFlowResultado.OK);
		return new ResponseEntity<WorkFlowResultado>(request, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/wfdevolver", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowResultado> wfdevolver(@RequestBody WorkFlowResultado request) throws Exception {
		request.setEstado(WorkFlowResultado.OK);
		return new ResponseEntity<WorkFlowResultado>(request, HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/wfobtenermetadata", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map> wfobtenermetadata(@RequestBody WorkFlowResultado request) throws Exception {
		String criterios = servicio.obtenerPorIdNL(request.getTransaccionid());
		if (!UString.estaVacio(criterios)) {
			Map<String, String> parametros = new ObjectMapper().readValue(criterios, Map.class);
			return new ResponseEntity<Map>(parametros, HttpStatus.OK);
		}
		return new ResponseEntity<Map>(new HashMap<String, String>(), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/obtenerTransaccion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowResultado> obtenerTransaccion(@RequestBody WorkFlowTransaccion request)
			throws Exception {
		return new ResponseEntity<WorkFlowResultado>(servicio.obtenerTransaccion(request), HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/anularTransaccionDesdeSolicitud", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowResultado> anularTransaccionDesdeSolicitud(@RequestBody WorkFlowTransaccion request)
			throws Exception {
		return new ResponseEntity<WorkFlowResultado>(servicio.anularTransaccionDesdeSolicitud(request), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@GetMapping(value = "/listarOrigenes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarOrigenes() throws Exception {
		return new ResponseEntity<List<DtoTabla>>(servicio.listarOrigenes(), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerLlaveParaSyDocumento", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoFlujoAdjunto> obtenerLlaveParaSyDocumento(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<DtoFlujoAdjunto>(servicio.obtenerLlaveParaSyDocumento(dt.getCodigo()), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/verPlantilla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoFlujoAdjunto> verPlantilla(@RequestBody DtoFlujoAdjunto bean) throws Exception {
		return new ResponseEntity<DtoFlujoAdjunto>(servicio.verPlantilla(bean), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/listarPlanificacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfPlanificacion> listarPlanificacion(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<DtoWfPlanificacion>(
				servicio.listarPlanificacion(dt.getId(), getUsuarioActual().getPersonaId()), HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/actualizarPlanificacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfPlanificacion> actualizarPlanificacion(@RequestBody DtoWfPlanificacion dto)
			throws Exception {
		return new ResponseEntity<DtoWfPlanificacion>(servicio.actualizarPlanificacion(dto, getUsuarioActual()),
				HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/obtenerProyeccionPlanificacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfPlanificacion> obtenerProyeccionPlanificacion(@RequestBody WorkFlowTransaccion request)
			throws Exception {
		return new ResponseEntity<DtoWfPlanificacion>(
				servicio.obtenerProyeccionPlanificacion(request, getUsuarioActual()), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerAdjuntosSoloLectura", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoFlujoAdjunto>> obtenerAdjuntosSoloLectura(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<List<DtoFlujoAdjunto>>(
				servicio.obtenerAdjuntosSoloLectura(dt.getCodigo(), getUsuarioActual().getPersonaId()), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/transaccionListarAdministrador", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> transaccionListarAdministrador(@RequestBody FiltroTransaccion filtro)
			throws Exception {
		return new ResponseEntity<DominioPaginacion>(
				servicio.transaccionListarAdministrador(filtro, getUsuarioActual()), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/obtenerVistaAvanzada", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoTransaccionVistaAvanzada obtenerVistaAvanzada(@RequestBody DtoTabla dto) throws Exception {
		return servicio.obtenerVistaAvanzada(dto.getCodigo());
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/obtenerSeguimientoVistaAvanzada", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoSeguimientoVistaAvanzada> obtenerSeguimientoVistaAvanzada(@RequestBody DtoTabla dto)
			throws Exception {
		return servicio.obtenerSeguimientoVistaAvanzada(dto.getCodigo());
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerConfiguracionObservaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfFlujoNivel> obtenerConfiguracionObservaciones(@RequestBody DtoTabla dt)
			throws Exception {
		return new ResponseEntity<DtoWfFlujoNivel>(servicio.obtenerConfiguracionObservaciones(dt.getCodigo()),
				HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerCorreoObservacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailTransaccion> obtenerCorreoObservacion(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<EmailTransaccion>(
				servicio.obtenerCorreoObservacion(dt.getDescripcion(), dt.getCodigo()), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerMetadatos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> obtenerMetadatos(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<List<DtoTabla>>(servicio.obtenerMetadatos(dt.getId()), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerTitleWF", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTitleWF(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<DtoTabla>(servicio.obtenerTitleWF(dt.getCodigo()), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/obtenerTransaccionAprobadorVistaAvanzada", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoTabla> obtenerTransaccionAprobadorVistaAvanzada(@RequestBody DtoTabla dto) throws Exception {
		return servicio.obtenerTransaccionAprobadorVistaAvanzada(dto.getCodigo());
	}

	@Transactional
	@PostMapping(value = "/registrarNotificacionMasiva", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> registrarNotificacionMasiva(@RequestBody DtoNotificacionMasiva bean)
			throws Exception {
		return new ResponseEntity<DtoTabla>(servicio.registrarNotificacionMasiva(bean, getUsuarioActual()),
				HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/obtenerConfiguracionDocumentosVistaAvanzada", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoWfVistaAdminConfiguracionDocumentos> obtenerConfiguracionDocumentosVistaAvanzada(
			@RequestBody DtoTabla dto) throws Exception {
		return servicio.obtenerConfiguracionDocumentosVistaAvanzada(dto.getCodigo());
	}

	@Transactional
	@PutMapping(value = "/cambiarNivel", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> cambiarNivel(@RequestBody DtoTabla datos) {
		logger.debug("listardtofiltros");

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(
				new DominioParametroPersistencia("p_transaccion", Integer.class, Integer.parseInt(datos.getCodigo())));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, datos.getId()));
		parametros
				.add(new DominioParametroPersistencia("p_usuario", String.class, this.getUsuarioActual().getUsuario()));

		this.wfTransaccionesDao.ejecutarPorQuery("wftransacciones.cambiarNivel", parametros);

		return new ResponseEntity<DtoTabla>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/validarPropiedario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowResultado> validarPropiedarioId(@RequestBody WorkFlowTransaccion request)
			throws Exception {
		WorkFlowResultado res = this.servicio.validarPropiedarioIdUsuarioActual(request);
		return new ResponseEntity<WorkFlowResultado>(res, HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/listarProcesoCorreos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTransaccionCorreoListadoHeader> listarProcesoCorreos(
			@RequestBody DtoTransaccionCorreoFiltros datos) {
		logger.debug("listarProcesoCorreos");
		String tipo = datos.getTipo_consulta();
		if (UString.esNuloVacio(tipo))
			tipo = "PRO";
		List lista = null;
		logger.debug(tipo);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipo", String.class, tipo));
		parametros.add(new DominioParametroPersistencia("p_procesoUUID", String.class, datos.getProcesoUUID()));
		parametros.add(new DominioParametroPersistencia("p_flujo_id", Integer.class, datos.getFlujo_id()));
		parametros
				.add(new DominioParametroPersistencia("p_transaccionUUID", Integer.class, datos.getTransaccionUUID()));

		if (tipo.equals("TRA"))
			lista = this.wfTransaccionesDao.listarPorQuery(DtoTransaccionCorreoListado.class,
					"wftransacciones.listarProcesoCorreosTRA", parametros);
		else
			lista = this.wfTransaccionesDao.listarPorQuery(DtoTransaccionCorreoListado.class,
					"wftransacciones.listarProcesoCorreosPRO", parametros);

		DtoTransaccionCorreoListadoHeader dto = new DtoTransaccionCorreoListadoHeader();
		dto.setLst(lista);

		if (datos.getProcesoUUID() == null && !UString.estaVacio(datos.getTransaccionUUID())) {
			WfTransacciones tr = wfTransaccionesDao.obtenerPorUUID(datos.getTransaccionUUID());
		}

		WfProcesoVersiones version = wfProcesoVersionesDaoImpl.obtenerPorUuid(datos.getProcesoUUID());

		WfProcesosPk pkProceso = new WfProcesosPk(version.getPk().getProcesoid());
		WfProcesos proceso = wfProcesosDao.obtenerPorId(pkProceso);

		dto.setReportePorNiveles(version.getFlgCorreoNiveles());
		dto.setAplicacion(proceso.getAplicacionid());

		return new ResponseEntity<DtoTransaccionCorreoListadoHeader>(dto, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/exportarProcesoCorreos", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarProcesoCorreos(HttpServletResponse response, @RequestBody DtoTransaccionCorreoFiltros filtro)
			throws Exception {

		logger.debug("listarProcesoCorreos");
		String tipo = filtro.getTipo_consulta();
		if (UString.esNuloVacio(tipo))
			tipo = "PRO";
		List lista = null;
		logger.debug(tipo);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipo", String.class, tipo));
		parametros.add(new DominioParametroPersistencia("p_procesoUUID", String.class, filtro.getProcesoUUID()));
		parametros.add(new DominioParametroPersistencia("p_flujo_id", Integer.class, filtro.getFlujo_id()));
		parametros
				.add(new DominioParametroPersistencia("p_transaccionUUID", String.class, filtro.getTransaccionUUID()));

		if (tipo.equals("TRA"))
			lista = this.wfTransaccionesDao.listarPorQuery(DtoTransaccionCorreoListado.class,
					"wftransacciones.listarProcesoCorreosTRA", parametros);
		else
			lista = this.wfTransaccionesDao.listarPorQuery(DtoTransaccionCorreoListado.class,
					"wftransacciones.listarProcesoCorreosPRO", parametros);

		String[] arrCabecera = new String[] { "flujoId", "nivelId", "tipoAprobadorId", "tipoAprobadorNombre", "accion",
				"estadoId", "estadoNombre", "correoPersona", "correoJefe", "correoSolicitante",
				"correoPersonaReferencia", "correoTransaccion", "correoOtros", "aprobadores", "aprobadoresCorreos" };
		String[] arrColumnas = new String[] { "flujoId", "nivelId", "tipoAprobadorId", "tipoAprobadorNombre", "accion",
				"estadoId", "estadoNombre", "correoPersona", "correoJefe", "correoSolicitante",
				"correoPersonaReferencia", "correoTransaccion", "correoOtros", "aprobadores", "aprobadoresCorreos" };

		DtoWhExportar dtoExportar = new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Requerimientos Asignados");
		dtoExportar.setTipoExpotar(filtro.getTipoExportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setLista(lista);

		servicio.exportarInformacionWh(response, dtoExportar);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/listarJerarquiaMacroProceso", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoJerarquiaMacroProceso> listarJerarquiaMacroProceso(@RequestBody DtoTabla dto) throws Exception {
		return servicio.listarJerarquiaMacroProceso(dto.getCodigo());
	}
}
