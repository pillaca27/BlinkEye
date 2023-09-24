package net.royal.spring.core.rest;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dominio.dto.DtoComunAfemst;
import net.royal.spring.core.dominio.dto.DtoComunPais;
import net.royal.spring.core.dominio.filtro.FiltroComunAfemst;
import net.royal.spring.core.dominio.filtro.FiltroComunPais;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/afemstcomun")
@CrossOrigin(origins = "*")
public class AfemstComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AfemstComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AfemstComunRest() {
		super("afemst");
	}
	
	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado de Paises activos de la tabla afemst | Sin parametros de entrada | Parametros de salida: codigo, nombre, descripcion (Nacionalidad)", 
			value = "99-PROJECTO-CLISTA", tags= {"CORE", "PROJECTO"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar paises");
		List datos = this.listarPorQuery(DtoTabla.class, "afemst.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado de Paises activos de la tabla afemst | Sin parametros de entrada | Parametros de salida: codigo, nombre, descripcion (Nacionalidad)", 
			value = "99-PROJECTO-CLIACT", tags= {"CORE", "PROJECTO"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listar paises");
		List datos = this.listarPorQuery(DtoTabla.class, "afemst.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Obtener Por id afemst | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "99-PROJECTO-COBTDTO", tags = {"CORE", "PROJECTO"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerDtoTablaPorId(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_afe", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "afemst.obtenerDtoTablaPorId", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}*/
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	/*@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla HR_GradoInstruccion "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoComunHrGradoinstruccion", 
			value = "99-PROJECTO-COBTENER", tags = {"CORE", "PROJECTO"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAfemst> obtenerdto(@RequestBody DtoComunAfemst pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunAfemst dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunAfemst>(dto,HttpStatus.OK);
	}*/
	
	/*@ApiOperation(notes = "Obtener local name. Entrada: DtoComunAfemst. Retorno: String",					
			nickname="AFE_MST_COBTNAME", value = "Obtener local name", tags = {"AFE_MST", "LISTAR"})
	@Transactional
	@PutMapping(value="/obtenerlocalname", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerLocalname(@RequestBody DtoComunAfemst pk) throws Exception {
		logger.debug("obtenerlocalname");
		DtoComunAfemst dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getLocalname(),HttpStatus.OK);
	}*/
	
	/*@ApiOperation(notes = "Obtener estados. Entrada: DtoComunAfemst. Retorno: String",					
			nickname="AFE_MST_COBTSTA", value = "Obtener estados", tags = {"AFE_MST", "LISTAR"})
	@Transactional
	@PutMapping(value="/obtenerstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerStatus(@RequestBody DtoComunAfemst pk) throws Exception {
		logger.debug("obtenerstatus");
		DtoComunAfemst dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getStatus(),HttpStatus.OK);
	}
	
	public DtoComunAfemst obtenerDtoCore(DtoComunAfemst pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_afe", String.class, pk.getAfe()));
		List datos = this.listarPorQuery(DtoComunAfemst.class, "afemst.obtenerdto",parametros);
		DtoComunAfemst dto;
		if (datos.size()==1) {
			dto = (DtoComunAfemst)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAfemst();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}*/
	
	/*@ApiOperation(notes = "Listar dto filtros. Entrada: DtoComunAfemst. Retorno: List DtoComunAfemst",					
			nickname="AFE_MST_CLISTFIL", value = "Obtener estados", tags = {"AFE_MST", "LISTAR"})
	@Transactional
	@PutMapping(value="/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAfemst>> listardtofiltros(@RequestBody DtoComunAfemst filtro) throws Exception {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getAfe()))
			filtro.setAfe(null);
		if (UString.esNuloVacio(filtro.getLocalname()))
			filtro.setLocalname(null);
		else
			filtro.setLocalname(filtro.getLocalname().toUpperCase());
		if (UString.esNuloVacio(filtro.getCompanyowner()))
			filtro.setCompanyowner(null);
		if (UString.esNuloVacio(filtro.getStatus()))
			filtro.setStatus(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_afe", String.class, filtro.getAfe()));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_localname", String.class, filtro.getLocalname()));
		parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getStatus()));
		List datos = this.listarPorQuery(DtoComunAfemst.class, "afemst.listardtofiltros",parametros);
		return new ResponseEntity<List<DtoComunAfemst>>(datos,HttpStatus.OK);
	}*/


	/*@ApiOperation(notes = "Listar paginado. Entrada: FiltroComunAfemst. Retorno: DominioPaginacion",					
			nickname="AFE_MST_CLISTPAG", value = "Listar paginado", tags = {"AFE_MST", "LISTAR"})
	@Transactional
	@PutMapping(value = "/listarpaginado",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarpaginado(@RequestBody FiltroComunAfemst filtro)
    {
		logger.debug("listar Proyectos filtro paginado");
		
		if (UString.esNuloVacio(filtro.getAfe()))
			filtro.setAfe(null);
		if (UString.esNuloVacio(filtro.getLocalname()))
			filtro.setLocalname(null);
		if (UString.esNuloVacio(filtro.getAfetype()))
			filtro.setAfetype(null);
		if (UString.esNuloVacio(filtro.getStatus()))
			filtro.setStatus(null);
			
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", BigDecimal.class, filtro.getCompania()));
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getAfe()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getLocalname()));
		parametros.add(new DominioParametroPersistencia("p_tipo", String.class, filtro.getAfetype()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getStatus()));
		
		Integer contador = this.contar("afemst.paginadoContar", parametros);
        List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "afemst.paginadolistar", DtoComunAfemst.class);
        
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
        filtro.getPaginacion().setPaginacionListaResultado(lista);
        logger.debug(contador);
        logger.debug(lista.size());
        
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }*/

	
	/**
	 * LEONARDO
	 * PROYECTO COMUN SELECTOR
	 * -Descripcion: Listado de Proyecto por filtro | Parametros de entrada: id del proyecto, nombre del proyecto | Parametros de salida: afe, localname, companyowner, companyowner nombre
	 */
	@Transactional
	@PostMapping(value = "/listarproyectoporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarProyectoPorFiltro(@RequestBody FiltroComunAfemst filtro)
    {
        logger.debug("PaisComunRest.listarproyectoporfiltro");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        
        if(UString.estaVacio(filtro.getAfe()))
        	filtro.setAfe(null);
        
        if(UString.estaVacio(filtro.getLocalname()))
        	filtro.setLocalname(null);

        parametros.add(new DominioParametroPersistencia("p_afe", String.class, filtro.getAfe()));
        parametros.add(new DominioParametroPersistencia("p_localname", String.class, filtro.getLocalname()));
        //parametros.add(new DominioParametroPersistencia("p_estado", String.class, "A"));

        cantidadEncontrados = this.contar("afemst.contarproyectoporfiltro", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "afemst.listarproyectoporfiltro", DtoComunAfemst.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
}
