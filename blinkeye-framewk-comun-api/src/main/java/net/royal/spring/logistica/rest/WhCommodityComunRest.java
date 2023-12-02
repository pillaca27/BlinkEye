package net.royal.spring.logistica.rest;

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

import net.royal.spring.logistica.dao.impl.WhCommodityDaoImpl;
import net.royal.spring.logistica.dominio.BeanWhCommodity;
import net.royal.spring.logistica.dominio.BeanWhCommodityPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCommodity;
import net.royal.spring.logistica.dominio.filtro.FiltroComunWhCommodity;
import net.royal.spring.logistica.servicio.impl.WhCommodityServicioImpl;
import net.royal.spring.logistica.servicio.validar.WhCommodityServicioValidar;
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
@RequestMapping("/spring/logistica/whcommodity")
@CrossOrigin(origins = "*")
public class WhCommodityComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhCommodityComunRest.class);

	@Autowired
	private WhCommodityServicioImpl servicio;

	@Autowired
	private WhCommodityServicioValidar validar;

	@Autowired
	private WhCommodityDaoImpl whCommodityDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WhCommodityComunRest() {
		super("whcommodity");
	}

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhCommodity> validar(@Validated @PathVariable String accion, @RequestBody DtoComunWhCommodity dto) throws Exception {
		logger.debug("WhCommodityRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunWhCommodity>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhCommodity> obtenerDto(@RequestBody DtoComunWhCommodity pk) throws Exception {
		logger.debug("WhCommodityRest.obtenerDto");
		DtoComunWhCommodity dto = whCommodityDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunWhCommodity>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhCommodity> registrar(@RequestBody DtoComunWhCommodity dto) throws Exception {
		logger.debug("WhCommodityRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunWhCommodity>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhCommodity> actualizar(@RequestBody DtoComunWhCommodity dto) throws Exception {
		logger.debug("WhCommodityRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunWhCommodity>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhCommodity> anular(@RequestBody DtoComunWhCommodity dto) throws Exception {
		logger.debug("WhCommodityRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunWhCommodity>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhCommodity> eliminar(@RequestBody DtoComunWhCommodity dto) throws Exception {
		logger.debug("WhCommodityRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunWhCommodity>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("WhCommodityRest.listar");
		// TODO WhCommodityRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "whcommodity.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("WhCommodityRest.listaractivos");
		// TODO WhCommodityRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "whcommodity.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}


	

	@Transactional
	@PutMapping(value="/listarpaginadoCommodity", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunWhCommodity filtro) throws Exception {
		logger.debug("WhCommodityRest.listarPaginado");
		DominioPaginacion paginacion = whCommodityDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}



}
