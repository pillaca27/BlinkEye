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

import net.royal.spring.ac.dao.impl.AcSucursalgrupomayorDaoImpl;
import net.royal.spring.ac.dominio.BeanAcSucursalgrupomayor;
import net.royal.spring.ac.dominio.BeanAcSucursalgrupomayorPk;
import net.royal.spring.ac.dominio.dto.DtoComunAcSucursalgrupomayor;
import net.royal.spring.ac.dominio.filtro.FiltroComunAcSucursalgrupomayor;
import net.royal.spring.ac.servicio.impl.AcSucursalgrupomayorServicioImpl;
import net.royal.spring.ac.servicio.validar.AcSucursalgrupomayorServicioValidar;
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
@RequestMapping("/spring/ac/acsucursalgrupomayor")
@CrossOrigin(origins = "*")
public class AcSucursalgrupomayorComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AcSucursalgrupomayorComunRest.class);

	@Autowired
	private AcSucursalgrupomayorServicioImpl servicio;

	@Autowired
	private AcSucursalgrupomayorServicioValidar validar;

	@Autowired
	private AcSucursalgrupomayorDaoImpl acSucursalgrupomayorDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AcSucursalgrupomayorComunRest() {
		super("acsucursalgrupomayor");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAcSucursalgrupomayor> validar(@Validated @PathVariable String accion, @RequestBody DtoAcSucursalgrupomayor dto) throws Exception {
		logger.debug("AcSucursalgrupomayorRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoAcSucursalgrupomayor>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAcSucursalgrupomayor> obtenerDto(@RequestBody DtoAcSucursalgrupomayor pk) throws Exception {
		logger.debug("AcSucursalgrupomayorRest.obtenerDtoPorUuid");
		DtoAcSucursalgrupomayor dto = acSucursalgrupomayorDao.obtenerDtoPorUuid(pk.getUuid());
		return new ResponseEntity<DtoAcSucursalgrupomayor>(dto,HttpStatus.OK);
	}*/

	/*@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAcSucursalgrupomayor> registrar(@RequestBody DtoAcSucursalgrupomayor dto) throws Exception {
		logger.debug("AcSucursalgrupomayorRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoAcSucursalgrupomayor>(dto, HttpStatus.CREATED);
	}*/

	/*@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAcSucursalgrupomayor> actualizar(@RequestBody DtoAcSucursalgrupomayor dto) throws Exception {
		logger.debug("AcSucursalgrupomayorRest.actualizar");
		dto = servicio.coreActualizarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoAcSucursalgrupomayor>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAcSucursalgrupomayor> anular(@RequestBody DtoAcSucursalgrupomayor dto) throws Exception {
		logger.debug("AcSucursalgrupomayorRest.anularPorUuid");
		dto = servicio.coreAnularPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoAcSucursalgrupomayor>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/activar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAcSucursalgrupomayor> activar(@RequestBody DtoAcSucursalgrupomayor dto) throws Exception {
		logger.debug("AcSucursalgrupomayorRest.activarPorUuid");
		dto = servicio.coreActivarPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoAcSucursalgrupomayor>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAcSucursalgrupomayor> eliminar(@RequestBody DtoAcSucursalgrupomayor dto) throws Exception {
		logger.debug("AcSucursalgrupomayorRest.eliminarPorUuid");
		dto = servicio.coreEliminarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoAcSucursalgrupomayor>(dto,HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("AcSucursalgrupomayorRest.listar");
		// TODO AcSucursalgrupomayorRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "acsucursalgrupomayor.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("AcSucursalgrupomayorRest.listaractivos");
		// TODO AcSucursalgrupomayorRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "acsucursalgrupomayor.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoAcSucursalgrupomayor>> listarDtoFiltros(@RequestBody DtoAcSucursalgrupomayor filtro) throws Exception {
		logger.debug("AcSucursalgrupomayorRest.listardtofiltros");
        List datos = acSucursalgrupomayorDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoAcSucursalgrupomayor>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoAcSucursalgrupomayor>> listarDtoActivos(@RequestBody DtoAcSucursalgrupomayor filtro) throws Exception {
		logger.debug("AcSucursalgrupomayorRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = acSucursalgrupomayorDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoAcSucursalgrupomayor>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroAcSucursalgrupomayor filtro) throws Exception {
		logger.debug("AcSucursalgrupomayorRest.listarPaginado");
		DominioPaginacion paginacion = acSucursalgrupomayorDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("AcSucursalgrupomayorRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = acSucursalgrupomayorDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("AcSucursalgrupomayorRest.listaCortaPorNombre");
		List datos = acSucursalgrupomayorDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

}
