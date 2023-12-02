package net.royal.spring.sistema.rest;

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

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.sistema.dao.impl.SyTipodocumentoDaoImpl;
import net.royal.spring.sistema.dao.impl.SyTipodocumentoprocesoDaoImpl;
import net.royal.spring.sistema.dominio.dto.DtoComunSyTipodocumento;
import net.royal.spring.sistema.dominio.dto.DtoComunSyTipodocumentoproceso;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyTipoDocumento;
import net.royal.spring.sistema.servicio.impl.SyTipodocumentoServicioImpl;
import net.royal.spring.sistema.servicio.impl.SyTipodocumentoprocesoServicioImpl;
import net.royal.spring.sistema.servicio.validar.SyTipodocumentoServicioValidar;

@RestController
@RequestMapping("/spring/sistema/sytipodocumento")
@CrossOrigin(origins = "*")
public class SyTipodocumentoComunRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(SyTipodocumentoComunRest.class);

	@Autowired
	private SyTipodocumentoServicioImpl servicio;

	@Autowired
	private SyTipodocumentoServicioValidar validar;

	@Autowired
	private SyTipodocumentoDaoImpl consulta;
	
	@Autowired
	private SyTipodocumentoprocesoDaoImpl syTipodocumentoprocesoDaoImpl;
	
	@Autowired
	private SyTipodocumentoprocesoServicioImpl syTipodocumentoprocesoServicioImpl;
	
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunSyTipoDocumento filtro) throws Exception {
		logger.debug("MaMiscelaneosheaderRest.listarPaginado");
		DominioPaginacion paginacion = consulta.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}
	
	@Transactional(readOnly = true)
	@GetMapping(value="/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoComunSyTipodocumento> listaractivos() throws Exception {
		return consulta.listaractivos();
	}

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody DtoComunSyTipodocumento bean) throws Exception {
		logger.debug("SyTipodocumentoRest.validar");
		logger.debug(accion);
		logger.debug(bean);
		List<DominioMensajeUsuario> lst = validar.core(this.getUsuarioActual(), accion, bean);
		logger.debug(lst.size());
		if (lst.isEmpty())
		    return new ResponseEntity<List<DominioMensajeUsuario>>(HttpStatus.OK);
		return new ResponseEntity<List<DominioMensajeUsuario>>(lst, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdtoporid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyTipodocumento> obtenerporid(@RequestBody DtoComunSyTipodocumento pk) throws Exception {
		logger.debug("MaMiscelaneosdetalleRest.obtenerDtoPorId");
		DtoComunSyTipodocumento dto = consulta.obtenerDtoPorId(pk);
		if (dto==null) {
		    dto = new DtoComunSyTipodocumento();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return new ResponseEntity<DtoComunSyTipodocumento>(dto,HttpStatus.OK);
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<DtoComunSyTipodocumento>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/listardtoporheader", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyTipodocumentoproceso>> listarDtoPorHeader(@RequestBody DtoComunSyTipodocumentoproceso filtro) throws Exception {
		logger.debug("MaMiscelaneosdetalleRest.listarPaginado");
		List<DtoComunSyTipodocumentoproceso> listado = syTipodocumentoprocesoDaoImpl.listarDtoPorHeader(filtro);
		return new ResponseEntity<List<DtoComunSyTipodocumentoproceso>>(listado,HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value="/listarProcesos/{aplicacion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<List<DtoTabla>> listarProcesos(@Validated @PathVariable String aplicacion) {
		List<DtoTabla> listado = consulta.listarProcesos(aplicacion);
		return new ResponseEntity<List<DtoTabla>>(listado,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyTipodocumento> registrar(@RequestBody DtoComunSyTipodocumento dto) throws Exception {
		logger.debug("SyTipodocumentoRest.registrar");
		
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		if(dto.getTransaccionListaMensajes().size() > 0) {
			return new ResponseEntity<DtoComunSyTipodocumento>(dto, HttpStatus.CREATED);
		}
		
		dto.setTransaccionEstado(dto.getTransaccionEstado());
		dto.setTransaccionListaMensajes(dto.getTransaccionListaMensajes());
		
		if(dto != null) {
			if(dto.getDetalle() != null) {
				for (DtoComunSyTipodocumentoproceso det : dto.getDetalle()) {
					
					det.setTipodocumentoid(dto.getTipodocumentoid());
					
					if(det.getAuxAccion().equals("N")) {
						det = syTipodocumentoprocesoServicioImpl.coreInsertar(this.getUsuarioActual(), det);	
					}else if(det.getAuxAccion().equals("A")) {
						DtoComunSyTipodocumentoproceso det2 = det;
						det2 = syTipodocumentoprocesoDaoImpl.obtenerDtoPorId(det);
						if(det2 != null) {
							det = syTipodocumentoprocesoServicioImpl.coreActualizar(this.getUsuarioActual(), det);	
						}else {
							det = syTipodocumentoprocesoServicioImpl.coreInsertar(this.getUsuarioActual(), det);
						}
					}			   
				}	
			}
		}
		
		return new ResponseEntity<DtoComunSyTipodocumento>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyTipodocumento> actualizar(@RequestBody DtoComunSyTipodocumento dto) throws Exception {
		logger.debug("SyTipodocumentoRest.actualizar");
		
		logger.debug("MaMiscelaneosheaderRest.actualizar");		
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		
		if(dto != null) {
			if(dto.getDetalle() != null) {
				for (DtoComunSyTipodocumentoproceso det : dto.getDetalle()) {					
					if(det.getAuxAccion() != null) {
						det.setTipodocumentoid(dto.getTipodocumentoid());
						if(det.getAuxAccion().equals("N")) {
							det = syTipodocumentoprocesoServicioImpl.coreInsertar(this.getUsuarioActual(), det);	
						}else if(det.getAuxAccion().equals("A")) {
							DtoComunSyTipodocumentoproceso det2 = det;
							det2 = syTipodocumentoprocesoDaoImpl.obtenerDtoPorId(det);
							if(det2 != null) {
								det = syTipodocumentoprocesoServicioImpl.coreActualizar(this.getUsuarioActual(), det);	
							}else {
								det = syTipodocumentoprocesoServicioImpl.coreInsertar(this.getUsuarioActual(), det);
							}						
						}else if(det.getAuxAccion().equals("E")) {
							det = syTipodocumentoprocesoDaoImpl.obtenerDtoPorId(det);
							if(det != null) {
								det = syTipodocumentoprocesoServicioImpl.coreEliminar(this.getUsuarioActual(), det);	
							}						
						}	
						
					}									
				}	
			}
		}
		
		return new ResponseEntity<DtoComunSyTipodocumento>(dto, HttpStatus.OK);
	}
	

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyTipodocumento> anular(@RequestBody DtoComunSyTipodocumento dto) throws Exception {
		logger.debug("MaMiscelaneosheaderRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunSyTipodocumento>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyTipodocumento> eliminar(@RequestBody DtoComunSyTipodocumento bean) throws Exception {
		logger.debug("SyTipodocumentoRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<DtoComunSyTipodocumento>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<DtoComunSyTipodocumento>(HttpStatus.OK);
		}
	}

}
