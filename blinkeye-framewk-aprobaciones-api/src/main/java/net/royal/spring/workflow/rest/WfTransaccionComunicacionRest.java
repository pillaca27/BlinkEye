package net.royal.spring.workflow.rest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.modelo.WorkFlowResultado;
import net.royal.spring.framework.modelo.WorkFlowTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.workflow.dao.impl.SyDocumentoAnexosDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesoVersionesDaoImpl;
import net.royal.spring.workflow.dao.impl.WfProcesosDaoImpl;
import net.royal.spring.workflow.dao.impl.WfTransaccionComunicacionDaoImpl;
import net.royal.spring.workflow.dao.impl.WfTransaccionalertaDaoImpl;
import net.royal.spring.workflow.dominio.SyDocumentoAnexos;
import net.royal.spring.workflow.dominio.WfProcesoVersiones;
import net.royal.spring.workflow.dominio.WfProcesoVersionesPk;
import net.royal.spring.workflow.dominio.WfTransaccionComunicacion;
import net.royal.spring.workflow.dominio.WfTransaccionComunicacionPk;
import net.royal.spring.workflow.dominio.WfTransaccionalerta;
import net.royal.spring.workflow.dominio.WfTransacciones;
import net.royal.spring.workflow.servicio.impl.WfTransaccionesServicioImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spring/workflow/wftransaccioncomunicacion")
public class WfTransaccionComunicacionRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(WfTransaccionComunicacionRest.class);

	@Autowired
	private WfTransaccionComunicacionDaoImpl wfTransaccionComunicacionDaoImpl;

	@Autowired
	private WfTransaccionesServicioImpl wfTransaccionesServicioImpl;

	@Autowired
	private SyDocumentoAnexosDaoImpl syDocumentoAnexosDaoImpl;

	@Autowired
	private WfProcesosDaoImpl wfProcesosDaoImpl;

	@Autowired
	private WfProcesoVersionesDaoImpl wfProcesoVersionesDaoImpl;

	@Autowired
	private WfTransaccionalertaDaoImpl wfTransaccionalertaDaoImpl;

	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerEmisores", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> obtenerEmisores(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<List<DtoTabla>>(wfTransaccionComunicacionDaoImpl.obtenerEmisores(dt.getId()),
				HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/obtenerConversacionesXEmisor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<WfTransaccionComunicacion>> obtenerConversacionesXEmisor(@RequestBody DtoTabla dt)
			throws Exception {

		Integer emisor = null;

		if (!UString.estaVacio(dt.getCodigo())) {
			emisor = Integer.parseInt(dt.getCodigo());
		}

		return new ResponseEntity<List<WfTransaccionComunicacion>>(
				wfTransaccionComunicacionDaoImpl.obtenerConversacionesXEmisor(dt.getId(), emisor, getUsuarioActual()),
				HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/enviarMensaje", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WfTransaccionComunicacion> enviarMensaje(@RequestBody WfTransaccionComunicacion bean)
			throws Exception {

		SeguridadUsuarioActual usuarioActual = getUsuarioActual();

		bean = wfTransaccionComunicacionDaoImpl.enviarMensaje(bean, usuarioActual);

		WfTransacciones beanTransaccion = wfTransaccionesServicioImpl.obtenerPorId(bean.getPk().getTransaccionId());

		if (bean.getTipoMensajeid().equals("ADJ")) {
			String rutaServer = wfTransaccionesServicioImpl.parametroObtenerExplicacion("SY", "DIRFILE");
			String rutaServerOriginal = new String(rutaServer);
			if (!new File(rutaServer + "TRANSACCION").exists()) {
				new File(rutaServer + "TRANSACCION").mkdir();
			}
			if (!new File(rutaServer + "TRANSACCION" + File.separator + bean.getPk().getTransaccionId()).exists()) {
				new File(rutaServer + "TRANSACCION" + File.separator + bean.getPk().getTransaccionId()).mkdir();
			}
			String tmp_ = bean.getPk().getTransaccionId() + File.separator + "COMUNICACION";
			if (!new File(rutaServer + tmp_).exists()) {
				new File(rutaServer + tmp_).mkdir();
			}
			rutaServer = rutaServer + "TRANSACCION" + tmp_ + File.separator;
			String rutaDocumento;
			String nombreDocumento = bean.getMensaje();
			rutaDocumento = rutaServer + nombreDocumento;
			String bin = bean.getAuxContenido().substring(bean.getAuxContenido().indexOf(",") + 1);
			bean.setAuxContenido(bin);
			logger.debug("Paths.get(rutaDocumento) :: " + Paths.get(rutaDocumento));
			Files.write(Paths.get(rutaDocumento), Base64.getDecoder().decode(bean.getAuxContenido()));
			SyDocumentoAnexos documento = new SyDocumentoAnexos();
			documento.getPk().setModulo("WC");
			documento.getPk().setLinea(bean.getPk().getTransaccionId());
			documento.getPk().setSecuencia(syDocumentoAnexosDaoImpl.obtenerSecuencia("WC", bean.getPk().getTransaccionId()));
			documento.getPk().setTipoDocumento(beanTransaccion.getProcesoid());
			documento.getPk().setNumeroDocumento(beanTransaccion.getReferencia());
			documento.getPk().setCompaniaSocio(beanTransaccion.getCompaniaSocioId());
			documento.setFecha(new Date());
			documento.setRutaArchivo(rutaDocumento.replace(rutaServerOriginal, ""));
			documento.setEstado("A");
			documento.setUltimoUsuario(usuarioActual.getUsuario());
			documento.setUltimaFechaModif(new Date());
			documento.setArchivo(nombreDocumento);
						
			documento.setWorkFlowProcesoId(beanTransaccion.getProcesoid());
			documento.setWorkFlowTransaccionId(bean.getPk().getTransaccionId());
			
			syDocumentoAnexosDaoImpl.registrar(documento);
		}

		// la notificacion no puede ser generada porque el receptor se define cuando
		// responden

		return new ResponseEntity<WfTransaccionComunicacion>(bean, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/responderMensaje", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WfTransaccionComunicacion> responderMensaje(@RequestBody WfTransaccionComunicacion bean)
			throws Exception {

		SeguridadUsuarioActual usuarioActual = getUsuarioActual();

		WfTransaccionComunicacion mensaje = wfTransaccionComunicacionDaoImpl.responderMensaje(bean, usuarioActual);

		// se actualiza el estado del id padre
		WfTransaccionComunicacion padre = wfTransaccionComunicacionDaoImpl.obtenerPorId(
				new WfTransaccionComunicacionPk(bean.getPk().getTransaccionId(), bean.getComunicacionPadreId()));
		padre.setEstado("RESP");
		padre.setModificacionFecha(new Date());
		padre.setModificacionUsuario(usuarioActual.getUsuario());
		// guardamos como receptor al usuario que le ha respondido
		padre.setReceptorId(usuarioActual.getPersonaId());
		wfTransaccionComunicacionDaoImpl.actualizar(bean);

		// validar si genera notificacion
		WfTransacciones beanTransaccion = wfTransaccionesServicioImpl.obtenerPorId(bean.getPk().getTransaccionId());
		WfProcesoVersiones beanProcesoVersion = wfProcesoVersionesDaoImpl
				.obtenerPorId(new WfProcesoVersionesPk(beanTransaccion.getProcesoid(), beanTransaccion.getVersionid()));

		if (UBoolean.validarFlag(beanProcesoVersion.getComunicacionFlgAlerta())) {
			// generar notificacion
			WfTransaccionalerta alerta = new WfTransaccionalerta();
			// ahora es con identity
			// alerta.getPk().setAlertaId(wfTransaccionalertaDaoImpl.generarId());
			alerta.setNombre(beanTransaccion.getReferencia() + " ha recibido un mensaje");
			alerta.setLink(null);
			alerta.setPersonaId(padre.getEmisorId());// se envia la alerta de respuesta al emisor
			alerta.setProcesoId(beanTransaccion.getProcesoid());
			alerta.setTransaccionId(beanTransaccion.getTransaccionid());
			alerta.setAccion("COMUNICACION");
			alerta.setSubaccion(null);
			alerta.setReferencia(beanTransaccion.getReferencia());
			alerta.setEstado("A");
			alerta.setCreacionFecha(new Date());
			alerta.setCreacionUsuario(usuarioActual.getUsuario());
			wfTransaccionalertaDaoImpl.registrar(alerta);
		}

		return new ResponseEntity<WfTransaccionComunicacion>(mensaje, HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/verAdjunto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> verAdjunto(@RequestBody WfTransaccionComunicacion bean) throws Exception {
		DtoTabla dto = new DtoTabla();
		String rutaServer = wfTransaccionesServicioImpl.parametroObtenerExplicacion("SY", "DIRFILE");

		String tmp_ = rutaServer + "TRANSACCION" + File.separator + bean.getPk().getTransaccionId() + File.separator
				+ "COMUNICACION" + File.separator + bean.getMensaje().trim();
		logger.debug("tmp_ :: " + tmp_);
		byte[] contenido = null;
		if (new File(tmp_).exists()) {
			logger.debug("EXISTE");
			contenido = UFile.obtenerArregloByte(tmp_);
		}
		dto.setDescripcion(org.apache.commons.codec.binary.Base64.encodeBase64String(contenido));
		logger.debug(dto.getDescripcion().length());
		dto.setNombre(Paths.get(tmp_).getFileName().toString());

		dto.setNombre(UFile.obtenerNombreWebDescargar(dto.getNombre()));

		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/validarPropiedario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkFlowResultado> validarPropiedarioId(@RequestBody WorkFlowTransaccion request)
			throws Exception {
		WorkFlowResultado res = this.wfTransaccionesServicioImpl.validarPropiedarioIdUsuarioActual(request);
		return new ResponseEntity<WorkFlowResultado>(res, HttpStatus.OK);
	}

}
