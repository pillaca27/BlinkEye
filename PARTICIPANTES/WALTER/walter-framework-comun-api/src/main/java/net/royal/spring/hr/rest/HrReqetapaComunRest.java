package net.royal.spring.hr.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.core.dominio.dto.DtoComunPais;
import net.royal.spring.core.dominio.filtro.FiltroComunPais;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.hr.dominio.dto.DtoComunHrReqetapa;
import net.royal.spring.hr.dominio.filtro.FiltroComunHrReqetapa;

@RestController
@RequestMapping("/spring/rrhh/hrreqetapa")
@CrossOrigin(origins = "*")
public class HrReqetapaComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrReqetapaComunRest.class);

	/*@Autowired
	private HrReqetapaServicioImpl servicio;

	@Autowired
	private HrReqetapaServicioValidar validar;

	@Autowired
	private HrReqetapaDaoImpl hrReqetapaDao;*/

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrReqetapaComunRest() {
		super("hrreqetapa");
	}
	
	/**
	 * LEONARDO
	 * ETAPA EVALUACION COMUN SELECTOR
	 * -Descripcion: Listado de Postulantes por filtro | Parametros de entrada: id del pais, nombre del pais | Parametros de salida: codigo, nombre
	 */
	@Transactional
	@PostMapping(value = "/listaretapaevaluacionporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listaretapaevaluacionporfiltro(@RequestBody FiltroComunHrReqetapa filtro)
    {
        logger.debug("HrReqetapaComunRest.listaretapaevaluacionporfiltro");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        
        if(UInteger.esCeroOrNulo(filtro.getEtapa()))
        	filtro.setEtapa(null);
        if(UString.estaVacio(filtro.getDescripcion()))
        	filtro.setDescripcion(null);
        if(UString.estaVacio(filtro.getCompanyowner()))
        	filtro.setCompanyowner(null);

        parametros.add(new DominioParametroPersistencia("p_etapa", Integer.class, filtro.getEtapa()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));
       

        cantidadEncontrados = this.contar("hrreqetapa.contaretapaevaluacionporfiltro", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, 
        		"hrreqetapa.listaretapaevaluacionporfiltro", DtoComunHrReqetapa.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoHrReqetapa> validar(@Validated @PathVariable String accion, @RequestBody DtoHrReqetapa dto) throws Exception {
		logger.debug("HrReqetapaRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoHrReqetapa>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoHrReqetapa> obtenerDto(@RequestBody DtoHrReqetapa pk) throws Exception {
		logger.debug("HrReqetapaRest.obtenerDto");
		DtoHrReqetapa dto = hrReqetapaDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoHrReqetapa>(dto,HttpStatus.OK);
	}*/

	/*@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoHrReqetapa> registrar(@RequestBody DtoHrReqetapa dto) throws Exception {
		logger.debug("HrReqetapaRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoHrReqetapa>(dto, HttpStatus.CREATED);
	}*/

	/*@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoHrReqetapa> actualizar(@RequestBody DtoHrReqetapa dto) throws Exception {
		logger.debug("HrReqetapaRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoHrReqetapa>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoHrReqetapa> anular(@RequestBody DtoHrReqetapa dto) throws Exception {
		logger.debug("HrReqetapaRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoHrReqetapa>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/activar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoHrReqetapa> activar(@RequestBody DtoHrReqetapa dto) throws Exception {
		logger.debug("HrReqetapaRest.activar");
		dto = servicio.coreActivar(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoHrReqetapa>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoHrReqetapa> eliminar(@RequestBody DtoHrReqetapa dto) throws Exception {
		logger.debug("HrReqetapaRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoHrReqetapa>(dto,HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("HrReqetapaRest.listar");
		// TODO HrReqetapaRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "hrreqetapa.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("HrReqetapaRest.listaractivos");
		// TODO HrReqetapaRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "hrreqetapa.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoHrReqetapa>> listarDtoFiltros(@RequestBody DtoHrReqetapa filtro) throws Exception {
		logger.debug("HrReqetapaRest.listardtofiltros");
        List datos = hrReqetapaDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoHrReqetapa>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoHrReqetapa>> listarDtoActivos(@RequestBody DtoHrReqetapa filtro) throws Exception {
		logger.debug("HrReqetapaRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = hrReqetapaDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoHrReqetapa>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroHrReqetapa filtro) throws Exception {
		logger.debug("HrReqetapaRest.listarPaginado");
		DominioPaginacion paginacion = hrReqetapaDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("HrReqetapaRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = hrReqetapaDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("HrReqetapaRest.listaCortaPorNombre");
		List datos = hrReqetapaDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

}
