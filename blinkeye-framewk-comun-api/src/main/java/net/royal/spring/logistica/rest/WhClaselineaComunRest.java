package net.royal.spring.logistica.rest;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClaselinea;
import net.royal.spring.logistica.dominio.dto.DtoComunWhItemmast;
import net.royal.spring.logistica.dominio.filtro.FiltroComunWhItemmast;
import net.royal.spring.logistica.servicio.impl.WhClaselineaComunServicioImpl;
import net.royal.spring.util.dominio.dto.DtoComunArbol;

@RestController
@RequestMapping("/spring/logistica/whclaselineacomun")
@CrossOrigin(origins = "*")
public class WhClaselineaComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhClaselineaComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WhClaselineaComunRest() {
		super("whclaselinea");
	}
	
	@Autowired
	private WhClaselineaComunServicioImpl whClaselineaServicioImpl;
	
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "whclaselinea.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "whclaselinea.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		logger.debug("obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "whclaselinea.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value = "/listarfiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarFiltros(@RequestBody DtoTabla filtro) {
		logger.debug("listarfiltros");
		if (UString.esNuloVacio(filtro.getCodigo()))
			filtro.setCodigo(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		else
			filtro.setNombre(filtro.getNombre().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstadoId()))
			filtro.setEstadoId(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "whclaselinea.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	public DtoComunWhClaselinea obtenerDtoCore(DtoComunWhClaselinea pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, pk.getLinea()));
		List datos = this.listarPorQuery(DtoComunWhClaselinea.class, "whclaselinea.obtenerDto",parametros);
		DtoComunWhClaselinea dto;
		if (datos.size()==1) {
			dto = (DtoComunWhClaselinea)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunWhClaselinea();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	
	@ApiOperation(notes = "Obtener dto. Entrada: DtoComunWhClaselinea. Retorno: DtoComunWhClaselinea",					
			nickname="WH_CLASEFAMILIA-OBT", value = "Obtener dto.", tags = {"WH_CLASEFAMILIA", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhClaselinea> obtenerDto(@RequestBody DtoComunWhClaselinea pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunWhClaselinea dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunWhClaselinea>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto descripcion local. Entrada: DtoComunWhClaselinea. Retorno: String",					
			nickname="WH_CLASEFAMILIA-OBTDESC", value = "Obtener dto.", tags = {"WH_CLASEFAMILIA", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdescripcionlocal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerDescripcionlocal(@RequestBody DtoComunWhClaselinea pk) throws Exception {
		logger.debug("obtenerdescripcionlocal");
		DtoComunWhClaselinea dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getDescripcionlocal(),HttpStatus.OK);
	}
	
	
	//ARBOL
	@Transactional
	@GetMapping(value = "/listarTipoArbol", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunArbol>> listarTipoArbol() {
		List<DtoComunArbol> bean = whClaselineaServicioImpl.cargarTree();
		if (bean == null)
			return new ResponseEntity<List<DtoComunArbol>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<DtoComunArbol>>(bean, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listarTipoArbolServicios", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunArbol>> listarTipoArbolServicios() {
		List<DtoComunArbol> bean = whClaselineaServicioImpl.cargarTreeServicios();
		if (bean == null)
			return new ResponseEntity<List<DtoComunArbol>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<DtoComunArbol>>(bean, HttpStatus.OK);
	}
	
	//EDGAR LUQUE - SELECTOR NUEVO DE LINEA-FAMILIA
	@ApiOperation(notes = "LISTADO DE LINEA FAMILIA. Entrada: FiltroComunWhItemmast. Retorno: DominioPaginacion",					
			nickname="WH_CLASEFAMILIA-OBTDESC", value = "LISTADO DE LINEA FAMILIA", tags = {"WH_CLASEFAMILIA", "OBTENER"})
	@Transactional
	@PutMapping(value = "/listarLineaFamilia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarLineaFamilia(@RequestBody FiltroComunWhItemmast filtro) {
		logger.debug("listarLineaFamilia");

		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);

		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_descri", String.class, filtro.getDescripcionlocal()));	
		Integer contador = this.contar("whclaselinea.contarLineaFamilia", parametros);
        List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "whclaselinea.listarLineaFamilia", DtoComunWhItemmast.class);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
        filtro.getPaginacion().setPaginacionListaResultado(lista);
        logger.debug(contador.intValue());
        logger.debug(lista.size());        
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
		
		
	}
	
}
