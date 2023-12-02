package net.royal.spring.core.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
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
import net.royal.spring.core.dao.impl.ZonadespachoDaoImpl;
import net.royal.spring.core.dominio.BeanZonadespacho;
import net.royal.spring.core.dominio.BeanZonadespachoPk;
import net.royal.spring.core.dominio.dto.DtoComunZonadespacho;
import net.royal.spring.core.dominio.filtro.FiltroComunZonadespacho;
import net.royal.spring.core.dominio.lista.DtlComunZonadespacho;
import net.royal.spring.core.servicio.impl.ZonadespachoServicioImpl;
import net.royal.spring.core.servicio.validar.ZonadespachoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/zonadespacho")
@CrossOrigin(origins = "*")
public class ZonadespachoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(ZonadespachoComunRest.class);

	@Autowired
	private ZonadespachoServicioImpl servicio;

	@Autowired
	private ZonadespachoServicioValidar validar;

	@Autowired
	private ZonadespachoDaoImpl zonadespachoDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ZonadespachoComunRest() {
		super("zonadespacho");
	}

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunZonadespacho> validar(@Validated @PathVariable String accion, @RequestBody DtoComunZonadespacho dto) throws Exception {
		logger.debug("ZonadespachoRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunZonadespacho>(dto, HttpStatus.OK);
	}

	@ApiOperation(notes = "Obtener dto. Entrada: DtoZonadespacho. Retorno: DtoZonadespacho",					
			nickname="ZONADESPACHO_COBTDTODES", value = "Obtener dto.", tags = {"ZONADESPACHO", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunZonadespacho> obtenerDto(@RequestBody DtoComunZonadespacho pk) throws Exception {
		logger.debug("ZonadespachoRest.obtenerDto");
		DtoComunZonadespacho dto = zonadespachoDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunZonadespacho>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunZonadespacho> registrar(@RequestBody DtoComunZonadespacho dto) throws Exception {
		logger.debug("ZonadespachoRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunZonadespacho>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunZonadespacho> actualizar(@RequestBody DtoComunZonadespacho dto) throws Exception {
		logger.debug("ZonadespachoRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunZonadespacho>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunZonadespacho> anular(@RequestBody DtoComunZonadespacho dto) throws Exception {
		logger.debug("ZonadespachoRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunZonadespacho>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunZonadespacho> eliminar(@RequestBody DtoComunZonadespacho dto) throws Exception {
		logger.debug("ZonadespachoRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunZonadespacho>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("ZonadespachoRest.listar");
		// TODO ZonadespachoRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "zonadespacho.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("ZonadespachoRest.listaractivos");
		// TODO ZonadespachoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "zonadespacho.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunZonadespacho>> listarDtoFiltros(@RequestBody DtoComunZonadespacho filtro) throws Exception {
		logger.debug("ZonadespachoRest.listardtofiltros");
        List datos = zonadespachoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunZonadespacho>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunZonadespacho>> listarDtoActivos(@RequestBody DtoComunZonadespacho filtro) throws Exception {
		logger.debug("ZonadespachoRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = zonadespachoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunZonadespacho>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunZonadespacho filtro) throws Exception {
		logger.debug("ZonadespachoRest.listarPaginado");
		DominioPaginacion paginacion = zonadespachoDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

}
