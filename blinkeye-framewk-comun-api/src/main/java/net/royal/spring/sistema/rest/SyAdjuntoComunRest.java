package net.royal.spring.sistema.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.sistema.dao.impl.SyAdjuntoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyAdjunto;
import net.royal.spring.sistema.dominio.BeanSyAdjuntoPk;
import net.royal.spring.sistema.servicio.impl.SyAdjuntoServicioImpl;
import net.royal.spring.sistema.servicio.validar.SyAdjuntoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/sistema/syadjunto")
@CrossOrigin(origins = "*")
public class SyAdjuntoComunRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(SyAdjuntoComunRest.class);

	@Autowired
	private SyAdjuntoServicioImpl servicio;

	@Autowired
	private SyAdjuntoServicioValidar validar;

	@Autowired
	private SyAdjuntoDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody BeanSyAdjunto bean) throws Exception {
		logger.debug("SyAdjuntoRest.validar");
		logger.debug(accion);
		logger.debug(bean);
		List<DominioMensajeUsuario> lst = validar.core(this.getUsuarioActual(), accion, bean);
		logger.debug(lst.size());
		if (lst.isEmpty())
		    return new ResponseEntity<List<DominioMensajeUsuario>>(HttpStatus.OK);
		return new ResponseEntity<List<DominioMensajeUsuario>>(lst, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value="/obtenerporid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSyAdjunto> obtenerPorId(@RequestBody BeanSyAdjuntoPk pk) throws Exception {
		logger.debug("SyAdjuntoRest.obtenerPorId");
		BeanSyAdjunto bean = consulta.obtenerPorId(pk);
		if (bean==null)
		    return new ResponseEntity<BeanSyAdjunto>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<BeanSyAdjunto>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSyAdjunto> registrar(@RequestBody BeanSyAdjunto bean) throws Exception {
		logger.debug("SyAdjuntoRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<BeanSyAdjunto>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSyAdjunto> actualizar(@RequestBody BeanSyAdjunto bean) throws Exception {
		logger.debug("SyAdjuntoRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<BeanSyAdjunto>(bean, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/registrarLstWH",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSyAdjunto> registrarLstWH(@RequestBody List<BeanSyAdjunto>  bean) throws Exception {
		logger.debug("SyAdjuntoRest.registrar");
		BeanSyAdjunto beans =  servicio.coreInsertarLstWH(this.getUsuarioActual(),bean);
		return new ResponseEntity<BeanSyAdjunto>(beans, HttpStatus.CREATED);
	}
	
	@Transactional
	@PostMapping(value = "/registrarWH",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSyAdjunto> registrarWH(@RequestBody BeanSyAdjunto bean) throws Exception {
		logger.debug("SyAdjuntoRest.registrar");
		bean =  servicio.coreInsertarWH(this.getUsuarioActual(),bean);
		return new ResponseEntity<BeanSyAdjunto>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizarWH",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSyAdjunto> actualizarWH(@RequestBody BeanSyAdjunto bean) throws Exception {
		logger.debug("SyAdjuntoRest.actualizar");
		bean = servicio.coreActualizarWH(this.getUsuarioActual(),bean);
		return new ResponseEntity<BeanSyAdjunto>(bean, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/eliminarWH", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminarWH(@RequestBody BeanSyAdjunto bean) throws Exception {
		logger.debug("SyAdjuntoRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminarWH(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@Transactional
	@PostMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody BeanSyAdjunto bean) throws Exception {
		logger.debug("SyAdjuntoRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	
	@Transactional
	@PostMapping(value = "/listar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BeanSyAdjunto>> listar(@RequestBody BeanSyAdjunto bean) throws Exception {
		logger.debug("SyAdjuntoRest.listar");
		List<BeanSyAdjunto> lst =  servicio.listar(this.getUsuarioActual(),bean);
		return new ResponseEntity<List<BeanSyAdjunto>>(lst, HttpStatus.CREATED);
	}
	
	@GetMapping("/descargarArchivo")
	public ResponseEntity<byte[]> downloadFile(@RequestParam String clavetabla, @RequestParam String nombretabla, @RequestParam Integer secuencia)
	throws Exception {
		
		BeanSyAdjuntoPk pk = new BeanSyAdjuntoPk();
		pk.setClavetabla(clavetabla);
		pk.setNombretabla(nombretabla);
		pk.setSecuencia(secuencia);
		
		BeanSyAdjunto archivo = consulta.obtenerPorId(pk);

		if (UValidador.esNulo(archivo)) {
			throw new UException("No Existe Archivo para Descargar");
		}

		if (UValidador.esNulo(archivo.getArchivodata())) {
			throw new UException("No Existe Archivo para Descargar");
		}

		byte[] isr = archivo.getArchivodata();

		int index = archivo.getArchivo().lastIndexOf('.');
		String extension = archivo.getArchivo().substring(index + 1).toLowerCase();

		String mimeType = null;

		switch (extension) {
		case "doc":
			mimeType = "application/msword";
			break;
		case "docx":
			mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
			break;
		case "jpeg":
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

		String fileName = "archivo";
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.add("Content-Type", mimeType);
		respHeaders.setContentLength(isr.length);
		respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
		return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
		
		
	}
	
}
