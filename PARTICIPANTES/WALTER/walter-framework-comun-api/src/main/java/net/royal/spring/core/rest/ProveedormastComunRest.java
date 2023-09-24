package net.royal.spring.core.rest;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.core.dao.impl.ProveedormastDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunProveedormast;
import net.royal.spring.core.servicio.impl.ProveedormastServicioImpl;
import net.royal.spring.core.servicio.validar.ProveedormastServicioValidar;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/proveedormastcomun")
@CrossOrigin(origins = "*")
public class ProveedormastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(ProveedormastComunRest.class);

	@Autowired
	private ProveedormastServicioImpl servicio;

	@Autowired
	private ProveedormastServicioValidar validar;

	@Autowired
	private ProveedormastDaoImpl proveedormastDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ProveedormastComunRest() {
		super("proveedormast");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoProveedormast> validar(@Validated @PathVariable String accion, @RequestBody DtoProveedormast dto) throws Exception {
		logger.debug("ProveedormastRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoProveedormast>(dto, HttpStatus.OK);
	}*/

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunProveedormast> obtenerDto(@RequestBody DtoComunProveedormast pk) throws Exception {
		logger.debug("ProveedormastRest.obtenerDto");
		DtoComunProveedormast dto = proveedormastDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunProveedormast>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunProveedormast> registrar(@RequestBody DtoComunProveedormast dto) throws Exception {
		logger.debug("ProveedormastRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunProveedormast>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunProveedormast> actualizar(@RequestBody DtoComunProveedormast dto) throws Exception {
		logger.debug("ProveedormastRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunProveedormast>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunProveedormast> eliminar(@RequestBody DtoComunProveedormast dto) throws Exception {
		logger.debug("ProveedormastRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunProveedormast>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("ProveedormastRest.listar");
		// TODO ProveedormastRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "proveedormast.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("ProveedormastRest.listaractivos");
		// TODO ProveedormastRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "proveedormast.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunProveedormast>> listarDtoFiltros(@RequestBody DtoComunProveedormast filtro) throws Exception {
		logger.debug("ProveedormastRest.listardtofiltros");
        List datos = proveedormastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunProveedormast>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunProveedormast>> listarDtoActivos(@RequestBody DtoComunProveedormast filtro) throws Exception {
		logger.debug("ProveedormastRest.listardtoactivos");
		List datos = proveedormastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunProveedormast>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunProveedormast filtro) throws Exception {
		logger.debug("ProveedormastRest.listarPaginado");
		DominioPaginacion paginacion = proveedormastDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}*/

}
