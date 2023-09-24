package net.royal.spring.core.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.PaisDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunPais;
import net.royal.spring.core.dominio.dto.DtoComunUbigeo;
import net.royal.spring.core.dominio.filtro.FiltroComunPais;
import net.royal.spring.core.dominio.filtro.FiltroComunUbigeo;
import net.royal.spring.core.servicio.impl.PaisServicioImpl;
import net.royal.spring.core.servicio.validar.PaisServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/paiscomun")
@CrossOrigin(origins = "*")
public class PaisComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(PaisComunRest.class);

	@Autowired
	private PaisDaoImpl paisDao;
	
	@Autowired
	private PaisServicioImpl servicio;
	
	@Autowired
	private PaisServicioValidar validar;
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public PaisComunRest() {
		super("pais");
	}
	
	/**
	 * LEONARDO
	 * PAIS COMUN SELECTOR
	 * -Descripcion: Listado de BeanPais por filtro | Parametros de entrada: id del pais, nombre del pais | Parametros de salida: codigo, nombre
	 */
	@Transactional
	@PostMapping(value = "/listarpaisporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarPaisPorFiltro(@RequestBody FiltroComunPais filtro)
    {
        logger.debug("PaisComunRest.listarpaisporfiltro");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        
        if(UString.estaVacio(filtro.getPais()))
        	filtro.setPais(null);
        
        if(UString.estaVacio(filtro.getNombre()))
        	filtro.setNombre(null);

        parametros.add(new DominioParametroPersistencia("p_pais", String.class, filtro.getPais()));
        parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, "A"));

        cantidadEncontrados = this.contar("pais.contarpaisporfiltro", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "pais.listarpaisporfiltro", DtoComunPais.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado de Paises activos de la tabla BeanPais | Sin parametros de entrada | Parametros de salida: codigo, nombre, descripcion (Nacionalidad)", 
			value = "99-PAIS-CLISTA", tags= {"CORE", "PAIS"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar paises");
		List datos = this.listarPorQuery(DtoTabla.class, "pais.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado de Paises activos de la tabla BeanPais | Sin parametros de entrada | Parametros de salida: codigo, nombre, descripcion (Nacionalidad)", 
			value = "99-PAIS-CLIACT", tags= {"CORE", "PAIS"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listar paises");
		List datos = this.listarPorQuery(DtoTabla.class, "pais.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/
	
	/*@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerDtoTablaPorId(@RequestBody DtoTabla filtro) { 
		logger.debug("obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_pais", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "pais.obtenertabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}*/
	
	/*@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPais> obtenerdto(@RequestBody DtoComunPais pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_pais", String.class, pk.getPais()));
		List datos = this.listarPorQuery(DtoComunPais.class, "pais.obtenerdto",parametros);
		DtoComunPais dto;
		if (datos.size()==1) {
			dto = (DtoComunPais)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunPais();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunPais>(dto,HttpStatus.OK);
	}*/
	
	/*@Transactional
	@PutMapping(value="/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunPais>> listardtofiltros(@RequestBody DtoComunPais filtro) throws Exception {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getPais()))
			filtro.setPais(null);
		if (UString.esNuloVacio(filtro.getDescripcioncorta()))
			filtro.setDescripcioncorta(null);
		else
			filtro.setDescripcioncorta(filtro.getDescripcioncorta().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_pais", String.class, filtro.getPais()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getDescripcioncorta()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List datos = this.listarPorQuery(DtoComunPais.class, "pais.listardtofiltros",parametros);
		return new ResponseEntity<List<DtoComunPais>>(datos,HttpStatus.OK);
	}*/
	
	/*
	 * LEONARDO
	 * LISTADO GENERAL
	 * */
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunPais filtro) throws Exception {
		logger.debug("PaisRest.listarPaginado");
		DominioPaginacion paginacion = paisDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPais> registrar(@RequestBody DtoComunPais dto) throws Exception {
		logger.debug("PaisRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunPais>(dto, HttpStatus.CREATED);
	}
	
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPais> actualizar(@RequestBody DtoComunPais dto) throws Exception {
		logger.debug("PaisRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunPais>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPais> anular(@RequestBody DtoComunPais dto) throws Exception {
		logger.debug("PaisRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunPais>(dto, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPais> eliminar(@RequestBody DtoComunPais dto) throws Exception {
		logger.debug("PaisRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunPais>(dto,HttpStatus.OK);
	}
	
}
