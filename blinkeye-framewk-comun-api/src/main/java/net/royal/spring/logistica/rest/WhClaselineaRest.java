package net.royal.spring.logistica.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.royal.spring.logistica.dao.impl.WhClaselineaDaoImpl;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCommoditysub;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClaselinea;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCommodity;
import net.royal.spring.logistica.dominio.filtro.FiltroComunWhClaselinea;
import net.royal.spring.logistica.servicio.impl.WhClaselineaServicioImpl;
import net.royal.spring.logistica.servicio.validar.WhClaselineaServicioValidar;
import net.royal.spring.core.dominio.filtro.FiltroComunCommoditySub;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/logistica/whclaselinea")
@CrossOrigin(origins = "*")
public class WhClaselineaRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhClaselineaRest.class);

	@Autowired
	private WhClaselineaServicioImpl servicio;

	@Autowired
	private WhClaselineaServicioValidar validar;

	@Autowired
	private WhClaselineaDaoImpl whClaselineaDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WhClaselineaRest() {
		super("whclaselinea");
	}

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhClaselinea> validar(@Validated @PathVariable String accion, @RequestBody DtoComunWhClaselinea dto) throws Exception {
		logger.debug("WhClaselineaRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunWhClaselinea>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhClaselinea> obtenerDto(@RequestBody DtoComunWhClaselinea pk) throws Exception {
		logger.debug("WhClaselineaRest.obtenerDto");
		DtoComunWhClaselinea dto = whClaselineaDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunWhClaselinea>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhClaselinea> registrar(@RequestBody DtoComunWhClaselinea dto) throws Exception {
		logger.debug("WhClaselineaRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunWhClaselinea>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhClaselinea> actualizar(@RequestBody DtoComunWhClaselinea dto) throws Exception {
		logger.debug("WhClaselineaRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunWhClaselinea>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhClaselinea> anular(@RequestBody DtoComunWhClaselinea dto) throws Exception {
		logger.debug("WhClaselineaRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunWhClaselinea>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhClaselinea> eliminar(@RequestBody DtoComunWhClaselinea dto) throws Exception {
		logger.debug("WhClaselineaRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunWhClaselinea>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("WhClaselineaRest.listar");
		// TODO WhClaselineaRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "whclaselinea.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("WhClaselineaRest.listaractivos");
		// TODO WhClaselineaRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "whclaselinea.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhClaselinea>> listarDtoFiltros(@RequestBody DtoComunWhClaselinea filtro) throws Exception {
		logger.debug("WhClaselineaRest.listardtofiltros");
        List datos = whClaselineaDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunWhClaselinea>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhClaselinea>> listarDtoActivos(@RequestBody DtoComunWhClaselinea filtro) throws Exception {
		logger.debug("WhClaselineaRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = whClaselineaDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunWhClaselinea>>(datos, HttpStatus.OK);
	}
	
	//EDGAR LUQUE -  LINEA-FAMILIA
		@Transactional
		@PutMapping(value = "/listarLineas", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<DominioPaginacion> listarLineas(@RequestBody FiltroComunWhClaselinea filtro) {
			logger.debug("listarLineas");

			if (UString.esNuloVacio(filtro.getLinea()))
				filtro.setLinea("");

		
			List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
			parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getLinea()));
			
			Integer contador = this.contar("whclaselinea.contarLineas", parametros);
	        List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "whclaselinea.listarLineas", DtoComunWhClaselinea.class);
	        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
	        filtro.getPaginacion().setPaginacionListaResultado(lista);
	        logger.debug(contador.intValue());
	        logger.debug(lista.size());        
			return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
		}
		
		@Transactional
		@PostMapping(value = "/exportarLineas", produces = MediaType.APPLICATION_JSON_VALUE)
		public void exportarLineas(HttpServletResponse response, @RequestBody FiltroComunWhClaselinea filtro) throws Exception
		 {
			DominioPaginacion paginacion = whClaselineaDao.exportarLineas(filtro);
			String[] arrCabecera = new String[] {"Linea","Descripci\u00f3n","Familia","Descripci\u00f3n","Estado"};
			String[] arrColumnas = new String[] {"linea", "descripcionlocal","familia","descripcioningles","estadodescripcion"}; 
			
			DtoWhExportar dtoExportar=new DtoWhExportar();
			dtoExportar.setTitulo("Listado de Maestro de Lineas");
			dtoExportar.setTipoExpotar(filtro.getTipoexportar());
			dtoExportar.setArrCabecera(arrCabecera);
			dtoExportar.setArrColumnas(arrColumnas);
			dtoExportar.setPaginacion(paginacion);
				
			servicio.exportarInformacionWh(response,dtoExportar);
		}
		
		
		//EDGAR LUQUE -  LINEA-FAMILIA OBTENER POR ID
		@Transactional
		@PostMapping(value="/obtenerdtoLineas", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<DtoComunWhClaselinea> obtenerDtoLineas(@RequestBody DtoComunWhClaselinea pk) throws Exception {
			logger.debug("obtenerDtoLineas");
			DtoComunWhClaselinea dto = null;
			if (pk==null) {
			    dto = new DtoComunWhClaselinea();
			    dto.setTransaccionEstado(DominioTransaccion.ERROR);
			    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no enviado"));
			    return new ResponseEntity<DtoComunWhClaselinea>(dto,HttpStatus.OK);
			}
					
				dto = whClaselineaDao.obtenerDto(pk);

			if (dto==null) {
			    dto = new DtoComunWhClaselinea();
			    dto.setTransaccionEstado(DominioTransaccion.ERROR);
			    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
			    return new ResponseEntity<DtoComunWhClaselinea>(dto,HttpStatus.OK);
			}
			dto.setTransaccionEstado(DominioTransaccion.OK);
			return new ResponseEntity<DtoComunWhClaselinea>(dto,HttpStatus.OK);
		}	
		
		@Transactional
		@PostMapping(value="/obtenerdtoSubFamilia", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<DtoComunWhClaselinea> obtenerdtoSubFamilia(@RequestBody DtoComunWhClaselinea pk) throws Exception {
			logger.debug("obtenerDtoLineas");
			DtoComunWhClaselinea dto = null;
			if (pk==null) {
			    dto = new DtoComunWhClaselinea();
			    dto.setTransaccionEstado(DominioTransaccion.ERROR);
			    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no enviado"));
			    return new ResponseEntity<DtoComunWhClaselinea>(dto,HttpStatus.OK);
			}
					
				dto = whClaselineaDao.obtenerDtoSubFamilia(pk);

			if (dto==null) {
			    dto = new DtoComunWhClaselinea();
			    dto.setTransaccionEstado(DominioTransaccion.ERROR);
			    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
			    return new ResponseEntity<DtoComunWhClaselinea>(dto,HttpStatus.OK);
			}
			dto.setTransaccionEstado(DominioTransaccion.OK);
			return new ResponseEntity<DtoComunWhClaselinea>(dto,HttpStatus.OK);
		}	

	//EDGAR LUQUE - LINEA-FAMILIA REGISTER
	@Transactional
	@PostMapping(value = "/registrarLineas",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhClaselinea> registrarCommodity(@RequestBody DtoComunWhClaselinea dto) throws Exception {
		logger.debug("registrarLineas");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunWhClaselinea>(dto, HttpStatus.CREATED);
		
	}
	
	//EDGAR LUQUE - LINEA-FAMILIA UPDATE
	@Transactional
	@PutMapping(value = "/actualizarLineas",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhClaselinea> actualizarCommodity(@RequestBody DtoComunWhClaselinea dto) throws Exception {
		logger.debug("actualizarLineas");
		dto =  servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunWhClaselinea>(dto, HttpStatus.OK);
		
	}
	
	//EDGAR LUQUE - LINEA-FAMILIA DELETE
	@Transactional
	@PostMapping(value = "/eliminarLineas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhClaselinea> eliminarLineas(@RequestBody DtoComunWhClaselinea dto) throws Exception {
		logger.debug("eliminarLineas");
		dto =  servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunWhClaselinea>(dto,HttpStatus.OK);
	}

}
