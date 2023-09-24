package net.royal.spring.core.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.TipopagoDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunTipopago;
import net.royal.spring.core.dominio.filtro.FiltroComunTipopago;
import net.royal.spring.core.servicio.impl.TipopagoServicioImpl;
import net.royal.spring.core.servicio.validar.TipopagoServicioValidar;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/tipopagocomun")
@CrossOrigin(origins = "*")
public class TipopagoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(TipopagoComunRest.class);

	@Autowired
	private TipopagoServicioImpl servicio;

	@Autowired
	private TipopagoServicioValidar validar;

	@Autowired
	private TipopagoDaoImpl tipopagoDao;
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public TipopagoComunRest() {
		super("tipopago");
	}
	
	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "tipopago.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "tipopago.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtenr tabla. Entrada: DtoTabla: codigo. Retorno: DtoTabla",					
			nickname="TIPOPAGO_COBT", value = "Obtenr tabla", tags = {"TIPOPAGO", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tipopago", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "tipopago.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value = "/listarfiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarFiltros(@RequestBody DtoTabla filtro) {
		logger.debug("listar Filtros");
		if (UString.esNuloVacio(filtro.getCodigo()))
			filtro.setCodigo(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		else
			filtro.setNombre(filtro.getNombre().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstadoId()))
			filtro.setEstadoId(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_tipopago", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "tipopago.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto. Entrada: DtoComunTipopago: tipopago. Retorno: DtoComunTipopago",					
			nickname="TIPOPAGO_COBTDTO", value = "Obtener dto", tags = {"TIPOPAGO", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipopago> obtenerDto(@RequestBody DtoComunTipopago pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipopago", String.class, pk.getTipopago()));
		List datos = this.listarPorQuery(DtoComunTipopago.class, "tipopago.obtenerDto",parametros);
		DtoComunTipopago dto;
		if (datos.size()==1) {
			dto = (DtoComunTipopago)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunTipopago();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunTipopago>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunTipopago>> listarDtoFiltros(@RequestBody DtoComunTipopago filtro) {
		logger.debug("listardtofiltros");
        List datos = listarDtoCore(filtro);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunTipopago>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunTipopago>> listarDtoActivos() {
		logger.debug("listardtoactivos");
		DtoComunTipopago filtro=new DtoComunTipopago();
		filtro.setEstado("A");
        List datos = listarDtoCore(filtro);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunTipopago>>(datos, HttpStatus.OK);
	}
	
	public List<DtoComunTipopago> listarDtoCore(DtoComunTipopago filtro) {
		logger.debug("listarDtoCore");
		if (UString.esNuloVacio(filtro.getTipopago()))
			filtro.setTipopago(null);		
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		else
			filtro.setDescripcion(filtro.getDescripcion().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tipopago", String.class, filtro.getTipopago()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunTipopago.class, "tipopago.listardtofiltros", parametros);
        logger.debug(datos.size());
		return datos;
	}*/
	
	
	/*MANTENIMIENTO LEONARDO*/
	
	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipopago> validar(@Validated @PathVariable String accion, @RequestBody DtoComunTipopago dto) throws Exception {
		logger.debug("TipopagoRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunTipopago>(dto, HttpStatus.OK);
	}*/

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipopago> obtenerDto(@RequestBody DtoComunTipopago pk) throws Exception {
		logger.debug("TipopagoRest.obtenerDto");
		DtoComunTipopago dto = tipopagoDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunTipopago>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipopago> registrar(@RequestBody DtoComunTipopago dto) throws Exception {
		logger.debug("TipopagoRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunTipopago>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipopago> actualizar(@RequestBody DtoComunTipopago dto) throws Exception {
		logger.debug("TipopagoRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunTipopago>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipopago> anular(@RequestBody DtoComunTipopago dto) throws Exception {
		logger.debug("TipopagoRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunTipopago>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipopago> eliminar(@RequestBody DtoComunTipopago dto) throws Exception {
		logger.debug("TipopagoRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunTipopago>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("TipopagoRest.listar");
		// TODO TipopagoRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "tipopago.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("TipopagoRest.listaractivos");
		// TODO TipopagoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "tipopago.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunTipopago>> listarDtoFiltros(@RequestBody DtoComunTipopago filtro) throws Exception {
		logger.debug("TipopagoRest.listardtofiltros");
        List datos = tipopagoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunTipopago>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunTipopago>> listarDtoActivos(@RequestBody DtoComunTipopago filtro) throws Exception {
		logger.debug("TipopagoRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = tipopagoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunTipopago>>(datos, HttpStatus.OK);
	}*/

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunTipopago filtro) throws Exception {
		logger.debug("TipopagoRest.listarPaginado");
		DominioPaginacion paginacion = tipopagoDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/exportarTipoPago", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarTipoPago(HttpServletResponse response, @RequestBody FiltroComunTipopago filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = tipopagoDao.exportarTipoPago(filtro);
		String[] arrCabecera = new String[] {"Tipo Pago","Descripci\u00f3n","Estado"};
		String[] arrColumnas = new String[] {"tipopago","descripcion","estadodescripcion"}; 
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Tipos de Pago");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}
}
