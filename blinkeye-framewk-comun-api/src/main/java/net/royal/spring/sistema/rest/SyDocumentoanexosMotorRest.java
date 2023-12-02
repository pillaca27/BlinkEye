package net.royal.spring.sistema.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.sistema.dao.impl.SyDocumentoanexosDaoImpl;
import net.royal.spring.sistema.dominio.dto.DtoComunSyDocumentoCabecera;
import net.royal.spring.sistema.dominio.dto.DtoComunSyDocumentoanexos;
import net.royal.spring.sistema.servicio.impl.SyDocumentoanexosMotorServicioImpl;
import net.royal.spring.sistema.servicio.impl.SyDocumentoanexosServicioImpl;
import net.royal.spring.sistema.servicio.validar.SyDocumentoanexosServicioValidar;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/sistema/sydocumentoanexosmotor")
@CrossOrigin(origins = "*")
public class SyDocumentoanexosMotorRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SyDocumentoanexosMotorRest.class);

	@Autowired
	private SyDocumentoanexosMotorServicioImpl servicio;

	@Autowired
	private SyDocumentoanexosServicioValidar validar;

	@Autowired
	private SyDocumentoanexosDaoImpl syDocumentoanexosDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SyDocumentoanexosMotorRest() {
		super("sydocumentoanexos");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> validar(@Validated @PathVariable String accion, @RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> obtenerDto(@RequestBody DtoComunSyDocumentoanexos pk) throws Exception {
		logger.debug("SyDocumentoanexosRest.obtenerDto");
		DtoComunSyDocumentoanexos dto = syDocumentoanexosDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> registrar(@RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> actualizar(@RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> anular(@RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> eliminar(@RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyDocumentoanexos>> listarDtoFiltros(@RequestBody DtoComunSyDocumentoanexos filtro) throws Exception {
		logger.debug("SyDocumentoanexosRest.listardtofiltros");
        List datos = syDocumentoanexosDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunSyDocumentoanexos>>(datos, HttpStatus.OK);
	}
	*/
	/***********/
	@Transactional
	@PutMapping(value = "/listarporcabecera", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyDocumentoanexos>> listarPorCabecera(@RequestBody DtoComunSyDocumentoCabecera filtro) throws Exception {
		logger.debug("SyDocumentoanexosRest.listarPorCabecera");
        // armas implementa listado
		
		List<DtoComunSyDocumentoanexos> lista = servicio.listarPorCabecera(filtro, getUsuarioActual());
		
		return new ResponseEntity<List<DtoComunSyDocumentoanexos>>(lista, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/listarporcabeceraWh", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyDocumentoanexos>> listarporcabeceraWh(@RequestBody DtoComunSyDocumentoCabecera filtro) throws Exception {
		logger.debug("SyDocumentoanexosRest.listarporcabeceraWh");
        // armas implementa listado
		
		List<DtoComunSyDocumentoanexos> lista = servicio.listarPorCabeceraWh(filtro, getUsuarioActual());
		
		return new ResponseEntity<List<DtoComunSyDocumentoanexos>>(lista, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/registrartemporal",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> registrarTemporal(@RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.registrarTemporal");
		dto =  servicio.registrarTemporal(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizarTemporal",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> actualizarTemporal(@RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.actualizar");
		dto = servicio.actualizarTemporal(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/eliminartemporal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> eliminarTemporal(@RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.eliminarTemporal");
		dto = servicio.eliminarTemporal(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto,HttpStatus.OK);
	}
	
	
	@Transactional
	@PutMapping(value = "/eliminartemporalMasivo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyDocumentoanexos>> eliminartemporalMasivo(@RequestBody List<DtoComunSyDocumentoanexos> lsdto) throws Exception {
		logger.debug("SyDocumentoanexosRest.eliminarTemporal");			
		
		for (DtoComunSyDocumentoanexos dtoSyDocumentoanexos : lsdto) {			
			dtoSyDocumentoanexos = servicio.eliminarTemporal(this.getUsuarioActual(), dtoSyDocumentoanexos);
		}
		
		return new ResponseEntity<List<DtoComunSyDocumentoanexos>>(lsdto,HttpStatus.OK);
	}
	
	
	@Transactional
	@PutMapping(value = "/procesaradjuntos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoCabecera> procesarAdjuntos(@RequestBody DtoComunSyDocumentoCabecera dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.procesarAdjuntos");		
		dto = servicio.procesarAdjuntos(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoCabecera>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/validarProcesaradjuntos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoCabecera> validarProcesaradjuntos(@RequestBody DtoComunSyDocumentoCabecera dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.procesarAdjuntos");		
		dto = servicio.validarProcesaradjuntos(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoCabecera>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/procesaradjuntosoc", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoCabecera> procesaradjuntosoc(@RequestBody DtoComunSyDocumentoCabecera dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.procesaradjuntosoc");		
		dto = servicio.procesarAdjuntosOrdenCompra(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoCabecera>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/procesaradjuntonot", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoCabecera> procesaradjuntonot(@RequestBody DtoComunSyDocumentoCabecera dto) throws Exception {	
		return new ResponseEntity<DtoComunSyDocumentoCabecera>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/procesaradjuntoscopy", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoCabecera> procesaradjuntoscopy(@RequestBody DtoComunSyDocumentoCabecera dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.procesaradjuntoscopy");		
		dto = servicio.procesaradjuntoscopy(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoCabecera>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping("/descargar")
	public ResponseEntity<byte[]> descargar(@RequestBody DtoComunSyDocumentoanexos bean) throws Exception {
		if (bean == null) {
			logger.error("Item no enviado");
			throw new UException(this.getMsjUsuarioError("aggeadjunto.adjuntoitem.notnull"));
		}

		byte[] isr = null;
		if (!UString.estaVacio(bean.getAuxRutaTemporal())) {
			String rutaTmp = servicio.rutaTemporal();
			String rutaCompletaTemp = rutaTmp + File.separator + bean.getAuxRutaTemporal();
			logger.debug("rutaCompletaTemp:"+rutaCompletaTemp);
			isr = UFile.obtenerArregloByte(rutaCompletaTemp);
		}else if (!UString.estaVacio(bean.getRutaarchivo())) {
			String rutaRaiz = servicio.rutaRaiz();
			String rutaCompletaFinal = rutaRaiz + File.separator + bean.getRutaarchivo();
			
			//if(bean.getModulo().equals("WH") && bean.getTipodocumento().equals("RQ")) {
				//rutaCompletaFinal = bean.getRutaarchivo();
    		//}
			
			isr = UFile.obtenerArregloByte(rutaCompletaFinal);
		}
		
		if (isr == null) {
			logger.error("No tiene adjunto el Item seleccionado");
			throw new UException(this.getMsjUsuarioError("aggeadjunto.adjuntoitembyte.notnull"));
		}
		
		
		String nombreArchivo = bean.getArchivo();
		nombreArchivo = nombreArchivo.trim();
		
		int index = nombreArchivo.lastIndexOf('.');
		String extension = nombreArchivo.substring(index + 1).toLowerCase();

		String mimeType = null;

		switch (extension) {
		case "doc":
			mimeType = "application/msword";
			break;
		case "docx":
			mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
			break;
		case "jpeg":
			mimeType = "image/jpeg";
			break;
		case "jpg":
			mimeType = "image/jpeg";
			break;
		case "png":
			mimeType = "image/png";
			break;
		case "json":
			mimeType = "application/json";
			break;
		case "pdf":
			mimeType = "application/pdf";
			break;
		case "ppt":
			mimeType = "application/vnd.ms-powerpoint";
			break;
		case "xls":
			mimeType = "application/vnd.ms-excel";
			break;
		case "xlsx":
			mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
			break;
		case "txt":
			mimeType = "text/plain";
			break;
		default:
			break;
		}

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.add("Content-Type", mimeType);
		respHeaders.setContentLength(isr.length);
		respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo.trim());
		return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping("/descargarOrdenCompra")
	public ResponseEntity<byte[]> descargarOrdenCompra(@RequestBody DtoComunSyDocumentoanexos bean) throws Exception {
		if (bean == null) {
			logger.error("Item no enviado");
			throw new UException(this.getMsjUsuarioError("aggeadjunto.adjuntoitem.notnull"));
		}

		byte[] isr = null;
		if (!UString.estaVacio(bean.getAuxRutaTemporal())) {
			String rutaTmp = servicio.rutaTemporal();
			String rutaCompletaTemp = rutaTmp + File.separator + bean.getAuxRutaTemporal();
			logger.debug("rutaCompletaTemp:"+rutaCompletaTemp);
			isr = UFile.obtenerArregloByte(rutaCompletaTemp);
		}else if (!UString.estaVacio(bean.getRutaarchivo())) {
			String rutaRaiz = servicio.rutaRaiz();
			String rutaCompletaFinal = rutaRaiz + File.separator + bean.getRutaarchivo();
			isr = UFile.obtenerArregloByte(rutaCompletaFinal);
		}
		
		if (isr == null) {
			logger.error("No tiene adjunto el Item seleccionado");
			throw new UException(this.getMsjUsuarioError("aggeadjunto.adjuntoitembyte.notnull"));
		}
		
		
		String nombreArchivo = bean.getArchivo();
		nombreArchivo = nombreArchivo.trim();
		
		int index = nombreArchivo.lastIndexOf('.');
		String extension = nombreArchivo.substring(index + 1).toLowerCase();

		String mimeType = null;

		switch (extension) {
		case "doc":
			mimeType = "application/msword";
			break;
		case "docx":
			mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
			break;
		case "jpeg":
			mimeType = "image/jpeg";
			break;
		case "jpg":
			mimeType = "image/jpeg";
			break;
		case "png":
			mimeType = "image/png";
			break;
		case "json":
			mimeType = "application/json";
			break;
		case "pdf":
			mimeType = "application/pdf";
			break;
		case "ppt":
			mimeType = "application/vnd.ms-powerpoint";
			break;
		case "xls":
			mimeType = "application/vnd.ms-excel";
			break;
		case "xlsx":
			mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
			break;
		case "txt":
			mimeType = "text/plain";
			break;
		default:
			break;
		}

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.add("Content-Type", mimeType);
		respHeaders.setContentLength(isr.length);
		respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo.trim());
		return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
	}
	
	
	@Transactional
	@PutMapping("/descargarplantilla")
	public ResponseEntity<byte[]> descargarPlantilla(@RequestBody DtoComunSyDocumentoanexos bean) throws Exception {
		if (bean == null) {
			logger.error("Plantilla no enviado");
			throw new UException(this.getMsjUsuarioError("aggeadjunto.adjuntoitem.notnull"));
		}

		byte[] isr = null;
		if (!UString.estaVacio(bean.getAuxRutaPlantilla())) {
			String rutaRaiz = servicio.rutaRaiz();
			String rutaCompletaFinal = rutaRaiz + File.separator + bean.getAuxRutaPlantilla();
			logger.error("rutaCompletaFinal:"+rutaCompletaFinal);
			isr = UFile.obtenerArregloByte(rutaCompletaFinal);
		}
		
		if (isr == null) {
			logger.error("Plantilla no encontrada");
			throw new UException(this.getMsjUsuarioError("Plantilla no encontrada"));
		}
		
		
		String nombreArchivo = UFile.extraerNombreArchivo(bean.getAuxRutaPlantilla());		
		int index = nombreArchivo.lastIndexOf('.');
		String extension = nombreArchivo.substring(index + 1).toLowerCase();

		String mimeType = null;

		switch (extension) {
		case "doc":
			mimeType = "application/msword";
			break;
		case "docx":
			mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
			break;
		case "jpeg":
			mimeType = "image/jpeg";
			break;
		case "jpg":
			mimeType = "image/jpeg";
			break;
		case "png":
			mimeType = "image/png";
			break;
		case "json":
			mimeType = "application/json";
			break;
		case "pdf":
			mimeType = "application/pdf";
			break;
		case "ppt":
			mimeType = "application/vnd.ms-powerpoint";
			break;
		case "xls":
			mimeType = "application/vnd.ms-excel";
			break;
		case "xlsx":
			mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
			break;
		case "txt":
			mimeType = "text/plain";
			break;
		default:
			break;
		}

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.add("Content-Type", mimeType);
		respHeaders.setContentLength(isr.length);
		respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo);
		return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/validarArchivo",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> validarArchivo(@RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.validarArchivo");
		dto = servicio.validarArchivo(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto, HttpStatus.OK);
	}
	
}
