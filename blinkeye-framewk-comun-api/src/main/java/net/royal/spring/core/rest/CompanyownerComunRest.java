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
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.comun.boot.SpringComunConstanteBoot;
import net.royal.spring.core.dao.impl.CompanyownerrecursoDaoImpl;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.dominio.BeanCompanyownerrecurso;
import net.royal.spring.core.dominio.BeanCompanyownerrecursoPk;
import net.royal.spring.core.dominio.dto.DtoComunAfemst;
import net.royal.spring.core.dominio.dto.DtoComunCompanyowner;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosheader;
import net.royal.spring.core.dominio.filtro.FiltroComunCompanyowner;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.constante.ConstanteDatos;
import net.royal.spring.framework.modelo.CompanyownerrecursoTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/companyownercomun")
@CrossOrigin(origins = "*")
public class CompanyownerComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(CompanyownerComunRest.class);
	
	@Autowired
	private CompanyownerrecursoDaoImpl dao;
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public CompanyownerComunRest() {
		super("companyowner");
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Companias activas | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "99-COMPW-CLISTA", tags= {"CORE", "COMPANIA"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() throws Exception 
	{
		logger.debug("companyowner.listar");		
		List datos = this.listarPorQuery(DtoTabla.class, "companyowner.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
		
	/**
	 * ARMAS MIGRADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Companias activas | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "99-COMPW-CLIACT", tags= {"CORE", "COMPANIA"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaractivos() throws Exception 
	{
		logger.debug("EmpleadomastRest.listaractivos");		
		List datos = this.listarPorQuery(DtoTabla.class, "companyowner.listaractivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}	
	
	@ApiOperation(notes = "-Descripcion: Listado de Companias activas | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "99-COMPW-CLIACT", tags= {"CORE", "COMPANIA"})
	@Transactional
	@GetMapping(value = "/listaractivosP", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaractivosP() throws Exception 
	{
		logger.debug("EmpleadomastRest.listaractivosP");		
		List datos = this.listarPorQuery(DtoTabla.class, "companyowner.listaractivosP");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}	
	
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenertabla(@RequestBody DtoTabla pk) throws Exception 
	{
		logger.debug("EmpleadomastRest.obtenertabla");	
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, pk.getCodigo()));
		List datos = this.listarPorQuery(DtoTabla.class, "companyowner.obtenertabla", parametros);
		DtoTabla dato = new DtoTabla();
		if(datos != null) {
			if(datos.size() > 0) {
				dato = (DtoTabla) datos.get(0);		
			}
		}		
		return new ResponseEntity<DtoTabla>(dato, HttpStatus.OK);
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
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_description", String.class, filtro.getNombre()));
		List datos = this.listarPorQuery(DtoTabla.class, "companyowner.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompanyowner> obtenerdto(@RequestBody DtoComunCompanyowner pk) throws Exception {
		logger.debug("hrgradoinstruccion.obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, pk.getCompanyowner()));
		List datos = this.listarPorQuery(DtoComunCompanyowner.class, "companyowner.obtenerdto",parametros);
		DtoComunCompanyowner dto;
		if (datos.size()==1) {
			dto = (DtoComunCompanyowner)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunCompanyowner();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunCompanyowner>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunCompanyowner>> listardtofiltros(@RequestBody DtoComunCompanyowner filtro) throws Exception {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getCompanyowner()))
			filtro.setCompanyowner(null);
		if (UString.esNuloVacio(filtro.getDescription()))
			filtro.setDescription(null);
		else
			filtro.setDescription(filtro.getDescription().toUpperCase());		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_description", String.class, filtro.getDescription()));
		List datos = this.listarPorQuery(DtoComunCompanyowner.class, "companyowner.listardtofiltros",parametros);
		return new ResponseEntity<List<DtoComunCompanyowner>>(datos,HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Companias por usuario actual logueado de la tabla companyowner | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "99-COMPW-C0002", tags= {"CORE", "COMPANIA"})
	@Transactional
	@GetMapping(value = "/listarporusuarioactual", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarCompaniasPorSeguridad() throws Exception 
	{
		logger.debug("EmpleadomastRest.listarCompaniasPorSeguridad");
		logger.error("FALTA IMPLEMENTAR como en el modulo de seguridad no esta filtrando por seguridad");
		String idUsuario = getUsuarioActual().getUsuario();
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("par_usuario", String.class, idUsuario));
		List datos = this.listarPorQuery(DtoTabla.class, "companyowner.listarCompaniasPorSeguridad", parametros);		
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "-Descripcion: Listado de Compania Socio por usuario actual logueado de la tabla companyowner | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "99-COMPW-C0002", tags= {"CORE", "COMPANIASOCIO"})
	@Transactional
	@GetMapping(value = "/listarcompaniasocioporusuarioactual", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarCompaniasocioPorSeguridad() throws Exception 
	{
		logger.debug("EmpleadomastRest.listarCompaniasPorSeguridad");
		logger.error("FALTA IMPLEMENTAR como en el modulo de seguridad no esta filtrando por seguridad");
		String idUsuario = getUsuarioActual().getUsuario();
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("par_usuario", String.class, idUsuario));
		List datos = this.listarPorQuery(DtoTabla.class, "companyowner.listarCompanyownerPorSeguridad", parametros);		
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * MALCAN VALIDADO
	 * COMPANYOWNER COMUN SELECTOR
	 * -Descripcion: Listado de Compania por filtro | Parametros de entrada: id del companyowner, nombre de la compania | Parametros de salida: codigo, nombre
	 */
	@Transactional
	@PostMapping(value = "/listarcompanyownerporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarCompanyownerPorFiltro(@RequestBody FiltroComunCompanyowner filtro)
    {
        logger.debug("CompanyownerComunRest.listarcompanyownerporfiltro");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        
        if(UString.estaVacio(filtro.getCompanyowner()))
        	filtro.setCompanyowner(null);
        if(UString.estaVacio(filtro.getDescription()))
        	filtro.setDescription(null);

        parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));
        parametros.add(new DominioParametroPersistencia("p_description", String.class, filtro.getDescription()));
        parametros.add(new DominioParametroPersistencia("p_usuario", String.class, this.getUsuarioActual().getUsuario()));
        //parametros.add(new DominioParametroPersistencia("p_estado", String.class, "A"));

        cantidadEncontrados = this.contar("companyowner.contarcompanyownerporfiltro", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "companyowner.listarcompanyownerporfiltro", 
        		DtoComunCompanyowner.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
	
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
}
