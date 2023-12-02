package net.royal.spring.core.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
import net.royal.spring.core.dao.impl.MaPersonagrupoDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonagrupo;
import net.royal.spring.core.dominio.filtro.FiltroComunMaPersonagrupo;
import net.royal.spring.core.servicio.impl.MaPersonagrupoServicioImpl;
import net.royal.spring.core.servicio.validar.MaPersonagrupoServicioValidar;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.logistica.dominio.dto.DtoComunWhContrato;

@RestController
@RequestMapping("/spring/core/mapersonagrupocomun")
@CrossOrigin(origins = "*")
public class MaPersonagrupoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(MaPersonagrupoComunRest.class);

	@Autowired
	private MaPersonagrupoServicioImpl servicio;

	@Autowired
	private MaPersonagrupoServicioValidar validar;

	@Autowired
	private MaPersonagrupoDaoImpl maPersonagrupoDao;
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public MaPersonagrupoComunRest() {
		super("mapersonagrupo");
	}
	
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener tabla. Entrada: DtoTabla: codigo. Retorno: DtoTabla",					
			nickname="MA_PERSONAGRUPO_COBT", value = "Obtener tabla", tags = {"MA_PERSONAGRUPO", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		logger.debug("obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_personagrupo", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.obtenerTabla", parametros);
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
		parametros.add(new DominioParametroPersistencia("p_personagrupo", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/************************************************************************************************/
	
	
	
	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaPersonagrupo> validar(@Validated @PathVariable String accion, @RequestBody DtoComunMaPersonagrupo dto) throws Exception {
		logger.debug("MaPersonagrupoRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunMaPersonagrupo>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * OBTENER OBJETO MAPERSONAGRUPO
	 * 
	 * */
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaPersonagrupo> obtenerDto(@RequestBody DtoComunMaPersonagrupo pk) throws Exception {
		logger.debug("MaPersonagrupoRest.obtenerDto");
		DtoComunMaPersonagrupo dto = maPersonagrupoDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunMaPersonagrupo>(dto,HttpStatus.OK);
	}

	/*
	 * LEONARDO
	 * REGISTRA MAPERSONAGRUPO (TIPO PERSONA)
	 * 
	 * */
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaPersonagrupo> registrar(@RequestBody DtoComunMaPersonagrupo dto) throws Exception {
		logger.debug("MaPersonagrupoRest.registrar");
		dto =  servicio.coreInsertar(dto.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunMaPersonagrupo>(dto, HttpStatus.CREATED);
	}

	/*
	 * LEONARDO
	 * ACTUALIZA MAPERSONAGRUPO (TIPO PERSONA)
	 * 
	 * */
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaPersonagrupo> actualizar(@RequestBody DtoComunMaPersonagrupo dto) throws Exception {
		logger.debug("MaPersonagrupoRest.actualizar");
		dto = servicio.coreActualizar(dto.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunMaPersonagrupo>(dto, HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaPersonagrupo> anular(@RequestBody DtoComunMaPersonagrupo dto) throws Exception {
		logger.debug("MaPersonagrupoRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunMaPersonagrupo>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * ELIMINA MAPERSONAGRUPO (TIPO PERSONA)
	 * 
	 * */
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaPersonagrupo> eliminar(@RequestBody DtoComunMaPersonagrupo dto) throws Exception {
		logger.debug("MaPersonagrupoRest.eliminar");
		dto = servicio.coreEliminar(dto.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunMaPersonagrupo>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("MaPersonagrupoRest.listar");
		// TODO MaPersonagrupoRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("MaPersonagrupoRest.listaractivos");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunMaPersonagrupo>> listarDtoFiltros(@RequestBody DtoComunMaPersonagrupo filtro) throws Exception {
		logger.debug("MaPersonagrupoRest.listardtofiltros");
        List datos = maPersonagrupoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunMaPersonagrupo>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunMaPersonagrupo>> listarDtoActivos(@RequestBody DtoComunMaPersonagrupo filtro) throws Exception {
		logger.debug("MaPersonagrupoRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = maPersonagrupoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunMaPersonagrupo>>(datos, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * LISTADO PAGINADO GENERAL
	 * 
	 * */
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunMaPersonagrupo filtro) throws Exception {
		logger.debug("MaPersonagrupoRest.listarPaginado");
		DominioPaginacion paginacion = maPersonagrupoDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	/*
	 * LEONARDO
	 * EXPORTAR MAPERSONAGRUPO (TIPO PERSONA)
	 * 
	 * */
	@Transactional
	@PostMapping(value = "/exportarTipoPersona", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarTipoPersona(HttpServletResponse response, @RequestBody FiltroComunMaPersonagrupo filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = maPersonagrupoDao.exportarTipoPersona(filtro);
		String[] arrCabecera = new String[] {"Grupo","Descripci\u00f3n Local","Estado","\u00DAltimo Usuario","Ult. Fecha Modificaci\u00f3n"};
		String[] arrColumnas = new String[] { "personagrupo", "descripcionlocal", "estadodescripcion", "ultimousuario","ultimafechamodifdescripcion"}; 
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Tipos de Personas");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}
	
	/* COMUNES PARA WH */
	
	@Transactional //COMUN GRUPO PERSONAS
	@GetMapping(value = "/listargrupopersonas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listargrupopersonas() {
		logger.debug("MaPersonagrupoRest.listargrupopersonas");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listargrupopersonas");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //COMUN MONEDAS
	@GetMapping(value = "/listarMonedas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarMonedas() {
		logger.debug("MaPersonagrupoRest.listarMonedas");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarMonedas");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //COMUN TIPO DE SERVICIO
	@GetMapping(value = "/listarTipoServicio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarTipoServicio() {
		logger.debug("MaPersonagrupoRest.listarTipoServicio");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarTipoServicio");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //COMUN FORMA DE PAGO
	@GetMapping(value = "/listarFormaPago", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarFormaPago() {
		logger.debug("MaPersonagrupoRest.listarFormaPago");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarFormaPago");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //COMUN TIPO DOCUMENTO
	@GetMapping(value = "/listarTipoDocumento", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarTipoDocumento() {
		logger.debug("MaPersonagrupoRest.listarTipoDocumento");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarTipoDocumento");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //COMUN TIPO PAGO
	@GetMapping(value = "/listarTipoPago", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarTipoPago() {
		logger.debug("MaPersonagrupoRest.listarTipoPago");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarTipoPago");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //COMUN TIPO DE PAGO (DETRACCIONES)
	@GetMapping(value = "/listarDetracciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarDetracciones() {
		logger.debug("MaPersonagrupoRest.listarDetracciones");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarDetracciones");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //COMUN IMPUESTO
	@GetMapping(value = "/listarImpuesto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarImpuesto() {
		logger.debug("MaPersonagrupoRest.listarImpuesto");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarImpuesto");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //COMUN DEPARTAMENTOS
	@GetMapping(value = "/listarDepartamentos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarDepartamentos() {
		logger.debug("MaPersonagrupoRest.listarDepartamentos");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarDepartamentos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional // DEPARTAMENTOS
	@GetMapping(value = "/listarDepartamental", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarDepartamental() {
		logger.debug("MaPersonagrupoRest.listarDepartamental");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarDepartamental");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	
	@Transactional //COMUN RESPONSABLES(\u00C1REA SUPERVISORA)
	@GetMapping(value = "/listarResponsables", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarResponsables() {
		logger.debug("MaPersonagrupoRest.listarDepartamentos");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarResponsables");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //COMUN BANCOS
	@GetMapping(value = "/listarBancos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarBancos() {
		logger.debug("MaPersonagrupoRest.listarBancos");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarBancos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //REGIMEN FISCAL
	@GetMapping(value = "/listarRegimenFiscal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarRegimenFiscal() {
		logger.debug("MaPersonagrupoRest.listarRegimenFiscal");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarRegimenFiscal");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //TIPO VOUCHER
	@GetMapping(value = "/listarTipoVoucher", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarTipoVoucher() {
		logger.debug("MaPersonagrupoRest.listarTipoVoucher");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarTipoVoucher");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //CODIGO SUNAT
	@GetMapping(value = "/listarSunat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarSunat() {
		logger.debug("MaPersonagrupoRest.listarSunat");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarSunat");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //ELEMENT
	@GetMapping(value = "/listarElement", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarElement() {
		logger.debug("MaPersonagrupoRest.listarElement");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarElement");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //PRIME
	@PutMapping(value = "/listarPrime", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarPrime(@RequestBody DtoTabla dt ) {
		logger.debug("MaPersonagrupoRest.listarPrime");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Banco.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_digito", String.class, dt.getCodigo()));
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarPrime",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //ReferenceFieldMst
	@GetMapping(value = "/listarReferenceFieldMst", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarReferenceFieldMst() {
		logger.debug("MaPersonagrupoRest.listarReferenceFieldMst");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarReferenceFieldMst");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //PRIME TYPE
	@GetMapping(value = "/listarPrimeType", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarPrimeType() {
		logger.debug("MaPersonagrupoRest.listarPrimeType");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarPrimeType");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //PRIME PADRE
	@PutMapping(value = "/listarPrimePadre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarPrimePadre(@RequestBody DtoTabla dt) {
		logger.debug("MaPersonagrupoRest.listarPrimePadre");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Banco.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_padre", String.class, dt.getCodigo()));
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarPrimePadre",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //COMUN CENTRO COSTO MAYOR
	@GetMapping(value = "/listarCostCenterMajor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarCostCenterMajor() {
		logger.debug("MaPersonagrupoRest.listarCostCenterMajor");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarCostCenterMajor");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //ELEMENT
	@GetMapping(value = "/listarGrupoSucursal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarGrupoSucursal() {
		logger.debug("MaPersonagrupoRest.listarGrupoSucursal");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarGrupoSucursal");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //TIPO PROYECTO
	@GetMapping(value = "/listarTipoProyecto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarTipoProyecto() {
		logger.debug("MaPersonagrupoRest.listarTipoProyecto");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarTipoProyecto");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //GRUPO PROYECTO
	@GetMapping(value = "/listarGrupoProyecto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarGrupoProyecto() {
		logger.debug("MaPersonagrupoRest.listarGrupoProyecto");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarGrupoProyecto");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //SUCURSAL
	@GetMapping(value = "/listarSucusal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarSucusal() {
		logger.debug("MaPersonagrupoRest.listarSucusal");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarSucusal");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //CLASIFICACION
	@GetMapping(value = "/listarClasificacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarClasificacion() {
		logger.debug("MaPersonagrupoRest.listarClasificacion");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarClasificacion");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //COSTO DESTINO
	@GetMapping(value = "/listarCostoDestino", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarCostoDestino() {
		logger.debug("MaPersonagrupoRest.listarCostoDestino");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarCostoDestino");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //VALIDACION FAMILIA
	@PutMapping(value = "/validacionLinea", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> validacionLinea(@RequestBody DtoTabla dt) {
		logger.debug("MaPersonagrupoRest.validacionLinea");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Banco.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, dt.getCodigo()));
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.validacionLinea",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	
	@Transactional //VALIDACION FAMILIA
	@PutMapping(value = "/validacionFamilia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> validacionFamilia(@RequestBody DtoTabla dt ) {
		logger.debug("MaPersonagrupoRest.validacionFamilia");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Banco.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_linea", String.class,dt.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class,dt.getDescripcion()));
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.validacionFamilia",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //VALIDACION SUBFAMILIA
	@PutMapping(value = "/validacionSubFamilia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> validacionSubFamilia(@RequestBody DtoTabla dt) {
		logger.debug("MaPersonagrupoRest.validacionSubFamilia");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Banco.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, dt.getCodigo() ));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, dt.getDescripcion()  ));
		parametros.add(new DominioParametroPersistencia("p_subfamilia", String.class, dt.getNombre() ));
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.validacionSubFamilia",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //MARCAS
	@GetMapping(value = "/listarMarcas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarMarcas() {
		logger.debug("MaPersonagrupoRest.listarMarcas");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarMarcas");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //MODELOS
	@GetMapping(value = "/listarModelos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarModelos() {
		logger.debug("MaPersonagrupoRest.listarModelos");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarModelos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //PROCEDENCIA
	@GetMapping(value = "/listarProcedencia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarProcedencia() {
		logger.debug("MaPersonagrupoRest.listarProcedencia");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarProcedencia");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //COD ABC
	@GetMapping(value = "/listarAbc", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarAbc() {
		logger.debug("MaPersonagrupoRest.listarAbc");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarAbc");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //VALIDAR CUENTAS
	@PutMapping(value = "/validarCuentas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> validarCuentas(@RequestBody DtoTabla dt) {
		logger.debug("MaPersonagrupoRest.validarCuentas");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Banco.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_cuenta", String.class, dt.getCodigo() ));
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.validarCuentas",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //OBTENER CUENTAS LINEA-FAMILIA
	@PutMapping(value = "/obtenerCuentaLineaFamilia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> obtenerCuentaLineaFamilia(@RequestBody DtoTabla dt) {
		logger.debug("MaPersonagrupoRest.validarCuentas");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Banco.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, dt.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class,dt.getDescripcion()));
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.obtenerCuentaLineaFamilia",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //OBTENER DATOS PROVEEDOR
	@PutMapping(value = "/SetProveedor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> SetProveedor(@RequestBody DtoTabla dt) {
		logger.debug("MaPersonagrupoRest.SetProveedor");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Banco.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_proveeedor", String.class, dt.getCodigo() ));
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.setProveedor",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional // TIPO SERVICIO (IMPORTES)
	@GetMapping(value = "/listarImportesOC", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarImportesOC() {
		logger.debug("MaPersonagrupoRest.listarImportesOC");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarImportesOC");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //CLASIFICACIONES
	@GetMapping(value = "/listarClasificacionWH", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarClasificacionWH() {
		logger.debug("MaPersonagrupoRest.listarClasificacionWH");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarClasificacionWH");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //TIPO DE CAMBIO
	@PutMapping(value = "/tipoCambio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> tipoCambio(@RequestBody DtoTabla dt) {
		logger.debug("MaPersonagrupoRest.tipoCambio");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_fecha", String.class, dt.getCodigo()));
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.tipoCambio",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //ALMACENCES
	@GetMapping(value = "/listarAlmacenes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarAlmacenes() {
		logger.debug("MaPersonagrupoRest.listarAlmacenes");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class,this.getUsuarioActual().getUsuario()));
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarAlmacenes",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //RESPONSABLES - CONTRATO
	@GetMapping(value = "/listarResponsablesContrato", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarResponsables_contratos() {
		logger.debug("MaPersonagrupoRest.listarResponsables");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_usuario", Integer.class,this.getUsuarioActual().getPersonaId()));
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarResponsablesContrato",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //CLASIFICACION - CONTRATO
	@GetMapping(value = "/listarClasificacionContrato", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarClasificacionContrato() {
		logger.debug("MaPersonagrupoRest.listarClasificacionContrato");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarClasificacionContrato");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //TIPO - CONTRATO
	@GetMapping(value = "/listarTipoContrato", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarTipoContrato() {
		logger.debug("MaPersonagrupoRest.listarTipoContrato");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarTipoContrato");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //CLASIFICACION - DEFAULT CONTRATO
	@GetMapping(value = "/listarClasificacionwh", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarClasificacionwh() {
		logger.debug("MaPersonagrupoRest.listarClasificacionwh");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarClasificacionwh");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //TIPO - LICITACION
	@GetMapping(value = "/listarTipolicitacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarTipolicitacion() {
		logger.debug("MaPersonagrupoRest.listarTipolicitacion");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarTipolicitacion");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //COMPRADOR
	@GetMapping(value = "/listarComprador", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarComprador() {
		logger.debug("MaPersonagrupoRest.listarComprador");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarComprador");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //TIPO ITEM
	@GetMapping(value = "/listarTipoItem", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarTipoItem() {
		logger.debug("MaPersonagrupoRest.listarTipoItem");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarTipoItem");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //TIPO POLIZA
	@GetMapping(value = "/listarMotivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarMotivos() {
		logger.debug("MaPersonagrupoRest.listarMotivos");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarPoliza");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //TIPO ADENDA
	@GetMapping(value = "/listarTipoAdenda", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarTipoAdenda() {
		logger.debug("MaPersonagrupoRest.listarTipoAdenda");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarTipoAdenda");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //MODALIDAD CONTRATACION
	@GetMapping(value = "/listarModalidadContratacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarModalidadContratacion() {
		logger.debug("MaPersonagrupoRest.listarModalidadContratacion");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarModalidadContratacion");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //UNIDADES
	@GetMapping(value = "/listarUnidades", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarUnidades() {
		logger.debug("MaPersonagrupoRest.listarUnidades");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarUnidades");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //CATEGORIAS
	@GetMapping(value = "/listarCategorias", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarCategorias() {
		logger.debug("MaPersonagrupoRest.listarCategorias");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarCategorias");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //CONTRATOS ORDEN COMPRA
	@GetMapping(value = "/listarContratosOrdenCompra", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhContrato>> listarContratosOrdenCompra() {
		logger.debug("MaPersonagrupoRest.listarContratosOrdenCompra");
		List datos = this.listarPorQuery(DtoComunWhContrato.class, "mapersonagrupo.listarContratosOrdenCompra");
		return new ResponseEntity<List<DtoComunWhContrato>>(datos, HttpStatus.OK);
	}
	

	@Transactional //ESTADO EM
	@GetMapping(value = "/listarEstadosWF", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarEstadosWF() {
		logger.debug("MaPersonagrupoRest.listarEstadosWF");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarEstadosWF");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //ESTADO GP
	@GetMapping(value = "/listarEstadosGP", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarEstadosGP() {
		logger.debug("MaPersonagrupoRest.listarEstadosGP");
		List datos = this.listarPorQuery(DtoTabla.class, "mapersonagrupo.listarEstadosGP");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
}
