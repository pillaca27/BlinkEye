package net.royal.spring.ac.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.royal.spring.ac.dominio.dto.DtoComunAcCostcentermst;
import net.royal.spring.ac.dominio.filtro.FiltroComunAcCostcentermst;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.MiscelaneosHeaderTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/contabilidad/accostcentermstcomun")
@CrossOrigin(origins = "*")
public class AcCostcentermstComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AcCostcentermstComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AcCostcentermstComunRest() {
		super("accostcentermst");
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado de Centros de costos | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "AC-CCOSTOS-LISTAR", tags = {"CONTABILIDAD", "CENTRO DE COSTO"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoTabla> listar() {	
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List datos = this.listarPorQuery(DtoTabla.class, "accostcentermst.listar", parametros);
		return datos;
	}*/
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado de Centros de costos activos | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "AC-CCOSTOS-LISACT", tags = {"CONTABILIDAD", "CENTRO DE COSTO"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoTabla> listaractivos() {	
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List datos = this.listarPorQuery(DtoTabla.class, "accostcentermst.listaractivos", parametros);
		return datos;
	}*/
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	/*@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Periodo "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AC-CCOSTOS-OBTTAB", tags = {"CONTABILIDAD", "CENTRO DE COSTO"})
	@Transactional
	@PutMapping(value="/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla pk) throws Exception {
		logger.debug("AsPeriodoComunRest.obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, pk.getCodigo()));
		List datos = this.listarPorQuery(DtoTabla.class, "accostcentermst.obtenertabla",parametros);
		DtoTabla dto = null;
		if (datos.size()==1)
			dto = (DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto,HttpStatus.OK);
	}*/
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	/*@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AC-CCOSTOS-OBTDTO", tags = {"CONTABILIDAD", "CENTRO DE COSTO"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcCostcentermst> obtenerDto(@RequestBody DtoComunAcCostcentermst pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunAcCostcentermst dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunAcCostcentermst>(dto,HttpStatus.OK);
	}
	
	public DtoComunAcCostcentermst obtenerDtoCore(DtoComunAcCostcentermst pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, pk.getCostcenter()));
		List datos = this.listarPorQuery(DtoComunAcCostcentermst.class, "accostcentermst.obtenerdto",parametros);
		DtoComunAcCostcentermst dto;
		if (datos.size()==1) {
			dto = (DtoComunAcCostcentermst)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAcCostcentermst();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}*/
	
	/*@Transactional
	@PutMapping(value="/obtenerlocalname", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerLocalname(@RequestBody DtoComunAcCostcentermst pk) throws Exception {
		logger.debug("obtenerlocalname");
		DtoComunAcCostcentermst dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getLocalname(),HttpStatus.OK);
	}*/
		
	/*@Transactional
	@PutMapping(value="/obtenerinternalnumber", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerInternalnumber(@RequestBody DtoComunAcCostcentermst pk) throws Exception {
		logger.debug("obtenerinternalnumber");
		DtoComunAcCostcentermst dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getInternalnumber(),HttpStatus.OK);
	}*/
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 */
	/*@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AC-CCOSTOS-LISDTOFIL", tags = {"CONTABILIDAD", "CENTRO DE COSTO"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAcCostcentermst>> listarDtoFiltros(@RequestBody DtoComunAcCostcentermst filtro) {
		logger.debug("AsConceptoaccesoComunRest.listarfiltros");
		if (UString.esNuloVacio(filtro.getCostcenter()))
			filtro.setCostcenter(null);
		if (UString.esNuloVacio(filtro.getLocalname()))
			filtro.setLocalname(null);
		else
			filtro.setLocalname(filtro.getLocalname().toUpperCase());
		if (UString.esNuloVacio(filtro.getStatus()))
			filtro.setStatus(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
        parametros.add(new DominioParametroPersistencia("p_localname", String.class, filtro.getLocalname()));
        parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getStatus()));
        List datos = this.listarPorQuery(DtoComunAcCostcentermst.class, "accostcentermst.listardtofiltros", parametros);
		return new ResponseEntity<List<DtoComunAcCostcentermst>>(datos, HttpStatus.OK);
	}*/
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado paginado Centros de costos autorizado | Filtros: DtoAcCostcentermstFiltroPaginado | Parametros de salida: costcenter, localname, expensefinanceflag, expenseadministrativeflag, expensesalesflag, expenseproductionflag", 
			value = "AC-CCOSTOS-C0002", tags = {"CONTABILIDAD", "CENTRO DE COSTO"})
	@Transactional
	@PutMapping(value = "/listarautorizados",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarAutorizados(@RequestBody FiltroComunAcCostcentermst filtro)
    {
		logger.debug("listar filtro paginado : Autorizados");
		SeguridadUsuarioActual usuarioActual = this.getUsuarioActual();
		
		if (UString.esNuloVacio(filtro.getIdUsuario()));
			filtro.setIdUsuario(usuarioActual.getUsuario());
		
		if (UString.esNuloVacio(filtro.getCostcenter())){
			filtro.setCostcenter(null);
		}
			
		if (UString.esNuloVacio(filtro.getLocalname())){
			filtro.setLocalname(null);
		}else{
			filtro.setLocalname(filtro.getLocalname().toUpperCase());
		}				
		if (UString.esNuloVacio(filtro.getIdCompania())){
			filtro.setIdCompania(usuarioActual.getCompaniaCodigo());	
		}			
		if (UString.esNuloVacio(filtro.getIdCompania())){					
			filtro.setIdCompania("01000000");}
		
		if (UString.esNuloVacio(filtro.getStatus()))					
			filtro.setStatus("A");
		logger.debug(filtro.getIdCompania());
		logger.debug(usuarioActual.getUsuario());
		
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getLocalname()));		
		parametros.add(new DominioParametroPersistencia("p_id_compania", String.class, filtro.getIdCompania()));
		parametros.add(new DominioParametroPersistencia("p_id_usuario", String.class, filtro.getIdUsuario()));
		parametros.add(new DominioParametroPersistencia("p_id_estado", String.class, filtro.getStatus()));
		
		Integer contador = 0;		
		List lista = new ArrayList<>();
		
		if(filtro.getPrincipales().equals("S")) {
			contador = this.contar("accostcentermst.paginadoAutorizadosContarPrincipales", parametros);
	        lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "accostcentermst.paginadoAutorizadosListarPrincipales", DtoComunAcCostcentermst.class);
		}
		else if(filtro.getPrincipales().equals("G")) {
			contador = this.contar("accostcentermst.paginadoAutorizadosContarPrincipalesGenerados", parametros);
	        lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "accostcentermst.paginadoAutorizadosListarPrincipalesGenerados", DtoComunAcCostcentermst.class);
		}
		else {
			contador = this.contar("accostcentermst.paginadoAutorizadosContar", parametros);
	        lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "accostcentermst.paginadoAutorizadosListar", DtoComunAcCostcentermst.class);	
		}				
        
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
        filtro.getPaginacion().setPaginacionListaResultado(lista);
        logger.debug(contador);
        logger.debug(lista.size());        
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado paginado Centros de costos supervisados | Filtros: DtoAcCostcentermstFiltroPaginado | Parametros de salida: costcenter, localname, expensefinanceflag, expenseadministrativeflag, expensesalesflag, expenseproductionflag", 
			value = "AC-CCOSTOS-C0003", tags = {"CONTABILIDAD", "CENTRO DE COSTO"})
	@Transactional
	@PutMapping(value = "/listarsupervisados",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarSupervisados(@RequestBody FiltroComunAcCostcentermst filtro) throws UException
    {
		logger.debug("listar filtro paginado Supervisados");		
		SeguridadUsuarioActual usuarioActual = this.getUsuarioActual();
		
		if (UString.esNuloVacio(filtro.getIdAplicacion()))
			throw new UException("Enviar el Id Aplicacion",tipo_mensaje.ERROR);
		
		if (UString.esNuloVacio(filtro.getIdUsuario()));
			filtro.setIdUsuario(usuarioActual.getUsuario());
		
		String flgSupervisor=null;
		if (filtro.getIdAplicacion().equals("WH") || filtro.getIdAplicacion().equals("AP")) {
			List<DominioParametroPersistencia> paraauto=new ArrayList<DominioParametroPersistencia>();
			paraauto.add(new DominioParametroPersistencia("p_id_aplicacion", String.class, filtro.getIdAplicacion()));
			paraauto.add(new DominioParametroPersistencia("p_id_grupo", String.class, "SYSTEM"));
			paraauto.add(new DominioParametroPersistencia("p_id_concepto", String.class, "CCSUP"));
			paraauto.add(new DominioParametroPersistencia("p_id_usuario", String.class, filtro.getIdUsuario()));
			
			Integer contador = this.contar("accostcentermst.contarAutorizaciones", paraauto); 
			if (contador>0)
				flgSupervisor = "S";
			else
				flgSupervisor = "N";
		}else {
			flgSupervisor = "S";
		}		
		
		if (UString.esNuloVacio(filtro.getCostcenter()))
			filtro.setCostcenter(null);
		if (UString.esNuloVacio(filtro.getLocalname())){
			filtro.setLocalname(null);
		}else{
			filtro.setLocalname(filtro.getLocalname().toUpperCase());
		}

		if (UString.esNuloVacio(filtro.getIdCompania()))
			filtro.setIdCompania(usuarioActual.getCompaniaCodigo());
		if (UString.esNuloVacio(filtro.getIdCompania()))
			filtro.setIdCompania("01000000");
			
		if (UString.esNuloVacio(filtro.getIdPersona()));
			filtro.setIdPersona(usuarioActual.getPersonaId().intValue());		
		if (UString.esNuloVacio(filtro.getStatus()))
				filtro.setStatus(null);
		logger.debug(filtro.getIdCompania());
		logger.debug(filtro.getIdPersona());
		logger.debug(filtro.getIdUsuario());
		
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getLocalname()));
		parametros.add(new DominioParametroPersistencia("p_id_compania", String.class, filtro.getIdCompania()));
		parametros.add(new DominioParametroPersistencia("p_flg_supervisor", String.class, flgSupervisor));
		parametros.add(new DominioParametroPersistencia("p_id_persona", Integer.class, filtro.getIdPersona()));
		parametros.add(new DominioParametroPersistencia("p_id_estado", String.class, filtro.getStatus()));
		
		Integer contador = 0;
		List lista = new ArrayList<>();
		
		if(filtro.getPrincipales().equals("S")) {
			contador = this.contar("accostcentermst.paginadoSupervisadosContarPrincipales", parametros);
	        lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "accostcentermst.paginadoSupervisadosListarPrincipales", DtoComunAcCostcentermst.class);
		}
		else if(filtro.getPrincipales().equals("G")) {
			contador = this.contar("accostcentermst.paginadoSupervisadosContarPrincipalesGenerados", parametros);
	        lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "accostcentermst.paginadoSupervisadosListarPrincipalesGenerados", DtoComunAcCostcentermst.class);
		}
		else {
			contador = this.contar("accostcentermst.paginadoSupervisadosContar", parametros);
	        lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "accostcentermst.paginadoSupervisadosListar", DtoComunAcCostcentermst.class);	
		}
				
        
        
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
        filtro.getPaginacion().setPaginacionListaResultado(lista);
        logger.debug(contador);
        logger.debug(lista.size());        
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }*/
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado paginado Centros de costos por empleados | Filtros: DtoAcCostcentermstFiltroPaginado | Parametros de salida: costcenter, localname, expensefinanceflag, expenseadministrativeflag, expensesalesflag, expenseproductionflag", 
			value = "AC-CCOSTOS-C0004", tags = {"CONTABILIDAD", "CENTRO DE COSTO"})
	@Transactional
	@PutMapping(value = "/listarempleados",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarEmpleados(@RequestBody FiltroComunAcCostcentermst filtro)
    {
		logger.debug("listar filtro paginado : Empleados");
		SeguridadUsuarioActual usuarioActual = this.getUsuarioActual();
		
		if (UString.esNuloVacio(filtro.getIdUsuario()));
			filtro.setIdUsuario(usuarioActual.getUsuario());
		
		if (UString.esNuloVacio(filtro.getCostcenter()));
			filtro.setCostcenter(null);
		if (UString.esNuloVacio(filtro.getLocalname())){
			filtro.setLocalname(null);
		}else{
			filtro.setLocalname(filtro.getLocalname().toUpperCase());
		}
		
		if (UString.esNuloVacio(filtro.getIdCompania()));
			filtro.setIdCompania(usuarioActual.getCompaniaCodigo());
		if (UString.esNuloVacio(filtro.getIdCompania()));
			filtro.setIdCompania("01000000");
		
		logger.debug(filtro.getIdCompania());
		logger.debug(usuarioActual.getUsuario());
		String flgRevision=null;
		String flgAprobacion=null;
		String flgPreparacion=null;
		if (UString.obtenerSinNulo(filtro.getValor1()).equals("REVIEW"))
			flgRevision="S";
		if (UString.obtenerSinNulo(filtro.getValor1()).equals("APPROVE"))
			flgAprobacion="S";
		if (UString.obtenerSinNulo(filtro.getValor1()).equals("ADD"))
			flgPreparacion="S";
		if (UString.esNuloVacio(filtro.getStatus()))					
			filtro.setStatus(null);
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getLocalname()));		
		parametros.add(new DominioParametroPersistencia("p_id_compania", String.class, filtro.getIdCompania()));
		parametros.add(new DominioParametroPersistencia("p_id_persona", String.class, filtro.getIdPersona()));
		
		parametros.add(new DominioParametroPersistencia("p_flg_revision", String.class, flgRevision));
		parametros.add(new DominioParametroPersistencia("p_flg_aprobacion", String.class, flgAprobacion));
		parametros.add(new DominioParametroPersistencia("p_flg_preparacion", String.class, flgPreparacion));
		parametros.add(new DominioParametroPersistencia("p_id_estado", String.class, filtro.getStatus()));			
		Integer contador = this.contar("accostcentermst.paginadoEmpleadosContar", parametros);
        List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "accostcentermst.paginadoEmpleadosListar", DtoComunAcCostcentermst.class);        
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
        filtro.getPaginacion().setPaginacionListaResultado(lista);
        logger.debug(contador);
        logger.debug(lista.size());        
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }*/
	
	
	
	@ApiOperation(notes = "-Descripcion: Listado paginado Centros de costos Todos | Filtros: DtoAcCostcentermstFiltroPaginado | Parametros de salida: costcenter, localname, expensefinanceflag, expenseadministrativeflag, expensesalesflag, expenseproductionflag", 
			value = "AC-CCOSTOS-TODOS", tags = {"CONTABILIDAD", "CENTRO DE COSTO"})
	@Transactional
	@PutMapping(value = "/listarTodos",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarTodos(@RequestBody FiltroComunAcCostcentermst filtro)
    {
		logger.debug("listar filtro paginado : Empleados");
		SeguridadUsuarioActual usuarioActual = this.getUsuarioActual();
		
		//filtro.getPaginacion().setPaginacionRegistrosPorPagina(10000);
		System.out.println("filtro.getCostcenter(): "+filtro.getCostcenter());
		
		if (UString.estaVacio(filtro.getIdUsuario()))
			filtro.setIdUsuario(usuarioActual.getUsuario());
		if (UString.estaVacio(filtro.getCostcenter()))
			filtro.setCostcenter(null);
		if (UString.estaVacio(filtro.getLocalname())){
			filtro.setLocalname(null);
		}else{
			filtro.setLocalname(filtro.getLocalname().toUpperCase());
		}
		
		if (UString.estaVacio(filtro.getIdCompania()))
			filtro.setIdCompania(usuarioActual.getCompaniaCodigo());
		if (UString.estaVacio(filtro.getIdCompania()))
			filtro.setIdCompania("01000000");
		
		logger.debug(filtro.getIdCompania());
		logger.debug(usuarioActual.getUsuario());
		/*String flgRevision=null;
		String flgAprobacion=null;
		String flgPreparacion=null;
		if (UString.obtenerSinNulo(filtro.getValor1()).equals("REVIEW"))
			flgRevision="S";
		if (UString.obtenerSinNulo(filtro.getValor1()).equals("APPROVE"))
			flgAprobacion="S";
		if (UString.obtenerSinNulo(filtro.getValor1()).equals("ADD"))
			flgPreparacion="S";*/
		if (UString.esNuloVacio(filtro.getStatus()))					
			filtro.setStatus(null);
		
		System.out.println("filtro.getCostcenter(): "+filtro.getCostcenter());
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getLocalname()));		
		/*parametros.add(new DominioParametroPersistencia("p_id_compania", String.class, filtro.getIdCompania()));
		parametros.add(new DominioParametroPersistencia("p_id_persona", String.class, filtro.getIdPersona()));
		
		parametros.add(new DominioParametroPersistencia("p_flg_revision", String.class, flgRevision));
		parametros.add(new DominioParametroPersistencia("p_flg_aprobacion", String.class, flgAprobacion));
		parametros.add(new DominioParametroPersistencia("p_flg_preparacion", String.class, flgPreparacion));
			*/		
		parametros.add(new DominioParametroPersistencia("p_id_estado", String.class, filtro.getStatus()));
		Integer contador = this.contar("accostcentermst.paginadoTodosContar", parametros);
        List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "accostcentermst.paginadoTodosListar", DtoComunAcCostcentermst.class);        
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
        filtro.getPaginacion().setPaginacionListaResultado(lista);
        logger.debug(contador);
        logger.debug(lista.size());        
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
	
	@Transactional
	@PutMapping(value = "/listarActivosPorTipo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivosPorTipo(@RequestBody DtoTabla filtro) { 
	logger.debug("listar Activos accostcentermst.listaractivosPorTipo");
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		 parametros.add(new DominioParametroPersistencia("p_tipo", String.class, filtro.getCodigo()));
        
        List datos = this.listarPorQuery(DtoTabla.class, "accostcentermst.listaractivosPorTipo", parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK); 
	}
	
	
}
