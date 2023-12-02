package net.royal.spring.core.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.MaMiscelaneosdetalleDaoImpl;
import net.royal.spring.core.dao.impl.MaMiscelaneosheaderDaoImpl;
import net.royal.spring.core.dominio.BeanMaMiscelaneosheader;
import net.royal.spring.core.dominio.BeanMaMiscelaneosheaderPk;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosdetalle;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosheader;
import net.royal.spring.core.dominio.filtro.FiltroComunMaMiscelaneosheader;
import net.royal.spring.core.servicio.impl.MaMiscelaneosdetalleServicioImpl;
import net.royal.spring.core.servicio.impl.MaMiscelaneosheaderServicioImpl;
import net.royal.spring.core.servicio.validar.MaMiscelaneosdetalleServicioValidar;
import net.royal.spring.core.servicio.validar.MaMiscelaneosheaderServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyReporte;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/mamiscelaneosheadercomun")
@CrossOrigin(origins = "*")
public class MaMiscelaneosheaderComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(MaMiscelaneosheaderComunRest.class);

	@Autowired
	private MaMiscelaneosheaderServicioImpl servicio;

	@Autowired
	private MaMiscelaneosheaderServicioValidar validar;

	@Autowired
	private MaMiscelaneosheaderDaoImpl maMiscelaneosheaderDao;
	
	@Autowired
	private MaMiscelaneosdetalleServicioImpl maMiscelaneosdetalleServicioImpl;
	
	@Autowired
	private MaMiscelaneosdetalleDaoImpl maMiscelaneosdetalleDaoImpl;
	
	@Autowired
	private MaMiscelaneosdetalleServicioValidar maMiscelaneosdetalleValidar;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public MaMiscelaneosheaderComunRest() {
		super("mamiscelaneosheader");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaMiscelaneosheader> validar(@Validated @PathVariable String accion, @RequestBody DtoComunMaMiscelaneosheader dto) throws Exception {
		logger.debug("MaMiscelaneosheaderRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunMaMiscelaneosheader>(dto, HttpStatus.OK);
	}*/
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Miscelaneos activos | Parametros de entrada: DtoComunMaMiscelaneosheader | Parametros de salida: DtoComunMaMiscelaneosheader", 
			value = "99-MISHEA-COBTDT", tags = {"CORE", "MISCELANEOS HEADER"})
	@Transactional
	@PutMapping(value="/obtenerdtoporid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaMiscelaneosheader> obtenerDtoPorId(@RequestBody DtoComunMaMiscelaneosheader pk) throws Exception {
		logger.debug("MaMiscelaneosheaderRest.obtenerDtoPorId");
		DtoComunMaMiscelaneosheader dto = maMiscelaneosheaderDao.obtenerDtoPorId(pk);
		if (dto==null) {
		    dto = new DtoComunMaMiscelaneosheader();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return new ResponseEntity<DtoComunMaMiscelaneosheader>(dto,HttpStatus.OK);
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<DtoComunMaMiscelaneosheader>(dto,HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Miscelaneos activos | Parametros de entrada: DtoComunMaMiscelaneosheader | Parametros de salida: DtoComunMaMiscelaneosheader", 
			value = "99-MISHEA-COBTDT", tags = {"CORE", "MISCELANEOS HEADER"})
	@Transactional
	@PutMapping(value="/obtenerdtoporUuid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaMiscelaneosheader> obtenerDtoPorUuid(@RequestBody DtoComunMaMiscelaneosheader pk) throws Exception {
		logger.debug("MaMiscelaneosheaderRest.obtenerDtoPorUuid");
		DtoComunMaMiscelaneosheader dto = maMiscelaneosheaderDao.obtenerDtoPorUuid(pk.getUuid());
		if (dto==null) {
		    dto = new DtoComunMaMiscelaneosheader();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return new ResponseEntity<DtoComunMaMiscelaneosheader>(dto,HttpStatus.OK);
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<DtoComunMaMiscelaneosheader>(dto,HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Miscelaneos activos | Parametros de entrada: DtoComunMaMiscelaneosheader | Parametros de salida: DtoComunMaMiscelaneosheader", 
			value = "99-MISHEA-CLSTPAG", tags = {"CORE", "MISCELANEOS HEADER"})
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunMaMiscelaneosheader filtro) throws Exception {
		logger.debug("MaMiscelaneosheaderRest.listarPaginado");
		DominioPaginacion paginacion = maMiscelaneosheaderDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/exportarMiscelaneos", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarMiscelaneos(HttpServletResponse response, @RequestBody FiltroComunMaMiscelaneosheader filtro) throws Exception
	 {
		DominioPaginacion paginacion = maMiscelaneosheaderDao.exportarMiscelaneos(filtro);
		String[] arrCabecera = new String[] {"Aplicaci\u00f3n","Compa\u00F1\u00EDa","Tabla","Descripci\u00f3n","Estado"};
		String[] arrColumnas = new String[] {"nombreAplicacion", "compania","codigotabla","descripcionlocal","estadodescripcion"}; 
		
		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Miscel\u00E1neos");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}
	

	/**
	 * QQUECHOD VALIDADO
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Registrar Miscelaneos | Parametros de entrada: DtoComunMaMiscelaneosheader | Parametros de salida: DtoComunMaMiscelaneosheader", 
			value = "99-MISHEA-CREGIST", tags = {"CORE", "MISCELANEOS HEADER"})
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaMiscelaneosheader> registrar(@RequestBody DtoComunMaMiscelaneosheader dto) throws Exception {
		logger.debug("MaMiscelaneosheaderRest.registrar");
		List<DominioMensajeUsuario> lst = maMiscelaneosdetalleValidar.validar(getUsuarioActual(), dto);		
		if (!lst.isEmpty()) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.setTransaccionListaMensajes(lst);
			return new ResponseEntity<DtoComunMaMiscelaneosheader>(dto, HttpStatus.CREATED);
		}
		
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		if(dto.getTransaccionListaMensajes().size() > 0) {
			return new ResponseEntity<DtoComunMaMiscelaneosheader>(dto, HttpStatus.CREATED);
		}
		
		dto.setTransaccionEstado(dto.getTransaccionEstado());
		dto.setTransaccionListaMensajes(dto.getTransaccionListaMensajes());
		
		if(dto != null) {
			if(dto.getDetalle() != null) {
				for (DtoComunMaMiscelaneosdetalle det : dto.getDetalle()) {
					
					det.setCompania(dto.getCompania());
					det.setCodigotabla(dto.getCodigotabla());
					det.setAplicacioncodigo(dto.getAplicacioncodigo());
					
					if(det.getAuxAccion().equals("N")) {
						det = maMiscelaneosdetalleServicioImpl.coreInsertar(this.getUsuarioActual(), det);	
					}else if(det.getAuxAccion().equals("A")) {
						DtoComunMaMiscelaneosdetalle det2 = det;
						det2 = maMiscelaneosdetalleDaoImpl.obtenerDtoPorId(det);
						if(det2 != null) {
							det = maMiscelaneosdetalleServicioImpl.coreActualizar(this.getUsuarioActual(), det);	
						}else {
							det = maMiscelaneosdetalleServicioImpl.coreInsertar(this.getUsuarioActual(), det);
						}
					}			   
				}	
			}
		}
		
		return new ResponseEntity<DtoComunMaMiscelaneosheader>(dto, HttpStatus.CREATED);
	}

	/**
	 * QQUECHOD VALIDADO
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Registrar Miscelaneos | Parametros de entrada: DtoComunMaMiscelaneosheader | Parametros de salida: DtoComunMaMiscelaneosheader", 
			value = "99-MISHEA-CACTUL", tags = {"CORE", "MISCELANEOS HEADER"})
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaMiscelaneosheader> actualizar(@RequestBody DtoComunMaMiscelaneosheader dto) throws Exception {
		logger.debug("MaMiscelaneosheaderRest.actualizar");		
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		
		if(dto != null) {
			if(dto.getDetalle() != null) {
				for (DtoComunMaMiscelaneosdetalle det : dto.getDetalle()) {					
					det.setCompania(dto.getCompania());
					det.setCodigotabla(dto.getCodigotabla());
					det.setAplicacioncodigo(dto.getAplicacioncodigo());
					
					if(det.getAuxAccion() != null) {
						
						if(det.getAuxAccion().equals("N")) {
							det = maMiscelaneosdetalleServicioImpl.coreInsertar(this.getUsuarioActual(), det);	
						}else if(det.getAuxAccion().equals("A")) {
							DtoComunMaMiscelaneosdetalle det2 = det;
							det2 = maMiscelaneosdetalleDaoImpl.obtenerDtoPorId(det);
							if(det2 != null) {
								det = maMiscelaneosdetalleServicioImpl.coreActualizar(this.getUsuarioActual(), det);	
							}else {
								det = maMiscelaneosdetalleServicioImpl.coreInsertar(this.getUsuarioActual(), det);
							}						
						}else if(det.getAuxAccion().equals("E")) {
							det = maMiscelaneosdetalleDaoImpl.obtenerDtoPorId(det);
							if(det != null) {
								det = maMiscelaneosdetalleServicioImpl.coreEliminar(this.getUsuarioActual(), det);	
							}						
						}	
						
					}									
				}	
			}
		}
		
		return new ResponseEntity<DtoComunMaMiscelaneosheader>(dto, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Registrar Miscelaneos | Parametros de entrada: DtoComunMaMiscelaneosheader | Parametros de salida: DtoComunMaMiscelaneosheader", 
			value = "99-MISHEA-CANULA", tags = {"CORE", "MISCELANEOS HEADER"})
	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaMiscelaneosheader> anular(@RequestBody DtoComunMaMiscelaneosheader dto) throws Exception {
		logger.debug("MaMiscelaneosheaderRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunMaMiscelaneosheader>(dto, HttpStatus.OK);
	}

	/**
	 * QQUECHOD VALIDADO
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Registrar Miscelaneos | Parametros de entrada: DtoComunMaMiscelaneosheader | Parametros de salida: DtoComunMaMiscelaneosheader", 
			value = "99-MISHEA-CELIMA", tags = {"CORE", "MISCELANEOS HEADER"})
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaMiscelaneosheader> eliminar(@RequestBody DtoComunMaMiscelaneosheader dto) throws Exception {
		logger.debug("MaMiscelaneosheaderRest.eliminar");
		DtoComunMaMiscelaneosdetalle filtro = new DtoComunMaMiscelaneosdetalle();
		filtro.setCompania(dto.getCompania());
		filtro.setAplicacioncodigo(dto.getAplicacioncodigo());
		filtro.setCodigotabla(dto.getCodigotabla());
		List<DtoComunMaMiscelaneosdetalle> listado = maMiscelaneosdetalleDaoImpl.listarDtoPorHeader(filtro);
		
		for (DtoComunMaMiscelaneosdetalle dtoMaMiscelaneosdetalle : listado) {
			maMiscelaneosdetalleServicioImpl.coreEliminar(this.getUsuarioActual(), dtoMaMiscelaneosdetalle);
		}
		
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunMaMiscelaneosheader>(dto,HttpStatus.OK);
	}	

}
