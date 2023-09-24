package net.royal.spring.ac.rest;

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

import net.royal.spring.ac.dao.impl.AcSucursalgrupoDaoImpl;
import net.royal.spring.ac.dominio.BeanAcSucursalgrupo;
import net.royal.spring.ac.dominio.BeanAcSucursalgrupoPk;
import net.royal.spring.ac.dominio.dto.DtoComunAcSucursalgrupo;
import net.royal.spring.ac.dominio.filtro.FiltroComunAcSucursalgrupo;
import net.royal.spring.ac.servicio.impl.AcSucursalgrupoServicioImpl;
import net.royal.spring.ac.servicio.validar.AcSucursalgrupoServicioValidar;
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
@RequestMapping("/spring/ac/acsucursalgrupo")
@CrossOrigin(origins = "*")
public class AcSucursalgrupoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AcSucursalgrupoComunRest.class);

	@Autowired
	private AcSucursalgrupoServicioImpl servicio;

	@Autowired
	private AcSucursalgrupoServicioValidar validar;

	@Autowired
	private AcSucursalgrupoDaoImpl acSucursalgrupoDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AcSucursalgrupoComunRest() {
		super("acsucursalgrupo");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAcSucursalgrupo> validar(@Validated @PathVariable String accion, @RequestBody DtoAcSucursalgrupo dto) throws Exception {
		logger.debug("AcSucursalgrupoRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoAcSucursalgrupo>(dto, HttpStatus.OK);
	}*/

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcSucursalgrupo> obtenerDto(@RequestBody DtoComunAcSucursalgrupo pk) throws Exception {
		logger.debug("AcSucursalgrupoRest.obtenerDtoPorUuid");
		DtoComunAcSucursalgrupo dto = acSucursalgrupoDao.obtenerDtoPorUuid(pk.getUuid());
		return new ResponseEntity<DtoComunAcSucursalgrupo>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcSucursalgrupo> registrar(@RequestBody DtoComunAcSucursalgrupo dto) throws Exception {
		logger.debug("AcSucursalgrupoRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunAcSucursalgrupo>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcSucursalgrupo> actualizar(@RequestBody DtoComunAcSucursalgrupo dto) throws Exception {
		logger.debug("AcSucursalgrupoRest.actualizar");
		dto = servicio.coreActualizarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunAcSucursalgrupo>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcSucursalgrupo> anular(@RequestBody DtoComunAcSucursalgrupo dto) throws Exception {
		logger.debug("AcSucursalgrupoRest.anularPorUuid");
		dto = servicio.coreAnularPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunAcSucursalgrupo>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/activar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcSucursalgrupo> activar(@RequestBody DtoComunAcSucursalgrupo dto) throws Exception {
		logger.debug("AcSucursalgrupoRest.activarPorUuid");
		dto = servicio.coreActivarPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunAcSucursalgrupo>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcSucursalgrupo> eliminar(@RequestBody DtoComunAcSucursalgrupo dto) throws Exception {
		logger.debug("AcSucursalgrupoRest.eliminarPorUuid");
		dto = servicio.coreEliminarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunAcSucursalgrupo>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("AcSucursalgrupoRest.listar");
		// TODO AcSucursalgrupoRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "acsucursalgrupo.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("AcSucursalgrupoRest.listaractivos");
		// TODO AcSucursalgrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "acsucursalgrupo.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoAcSucursalgrupo>> listarDtoFiltros(@RequestBody DtoAcSucursalgrupo filtro) throws Exception {
		logger.debug("AcSucursalgrupoRest.listardtofiltros");
        List datos = acSucursalgrupoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoAcSucursalgrupo>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoAcSucursalgrupo>> listarDtoActivos(@RequestBody DtoAcSucursalgrupo filtro) throws Exception {
		logger.debug("AcSucursalgrupoRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = acSucursalgrupoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoAcSucursalgrupo>>(datos, HttpStatus.OK);
	}*/

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunAcSucursalgrupo filtro) throws Exception {
		logger.debug("AcSucursalgrupoRest.listarPaginado");
		DominioPaginacion paginacion = acSucursalgrupoDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("AcSucursalgrupoRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = acSucursalgrupoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("AcSucursalgrupoRest.listaCortaPorNombre");
		List datos = acSucursalgrupoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

}
