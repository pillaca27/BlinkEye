package net.royal.spring.sg.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.sg.dominio.dto.DtoComunUsuario;
import net.royal.spring.sg.dominio.filtro.FiltroComunUsuario;
import net.royal.spring.sg.dominio.lista.DtlComunUsuario;

@RestController
@RequestMapping("/spring/seguridad/usuariocomun")
@CrossOrigin(origins = "*")
public class UsuarioComunRest extends GenericoHibernateRest {
	private static Logger logger = LogManager.getLogger(UsuarioComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public UsuarioComunRest() {
		super("usuario");
	}
	
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "usuario.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "usuario.listaractivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		logger.debug("obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "usuario.obtenerTabla", parametros);
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
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "usuario.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunUsuario> obtenerDto(@RequestBody DtoComunUsuario pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, pk.getUsuario()));
		List datos = this.listarPorQuery(DtoComunUsuario.class, "usuario.obtenerDto",parametros);
		DtoComunUsuario dto;
		if (datos.size()==1) {
			dto = (DtoComunUsuario)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunUsuario();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunUsuario>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunUsuario>> listarDtoFiltros(@RequestBody DtoComunUsuario filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getUsuario()))
			filtro.setUsuario(null);
		if (UString.esNuloVacio(filtro.getUsuarioperfil()))
			filtro.setUsuarioperfil(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		else
			filtro.setNombre(filtro.getNombre().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getUsuario()));
        parametros.add(new DominioParametroPersistencia("p_perfilusuario", String.class, filtro.getUsuarioperfil()));
        parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunUsuario.class, "usuario.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunUsuario>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunUsuario filtro) throws Exception {
		logger.debug("Usuario.listarPaginado");
		
		if (UString.esNuloVacio(filtro.getTipo())) {
			filtro.setTipo(null);	
		}

		if (UString.esNuloVacio(filtro.getNombre())) {
			filtro.setNombre(null);
		}else {
			filtro.setNombre(filtro.getNombre().toUpperCase());	
		}
		
		
		if (UString.esNuloVacio(filtro.getUsuario())) {
			filtro.setUsuario(null);
		}else {
			filtro.setUsuario(filtro.getUsuario().toUpperCase());	
		}
		
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_tipo", String.class, filtro.getTipo()));
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getUsuario()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		
		Integer registros = this.contar("usuario.contarl", parametros);
		List paginacion = this.listarConPaginacion(filtro.getPaginacion(), parametros, "usuario.listarpaginadol", DtlComunUsuario.class);
		
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(paginacion);
		
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
		
	}

}
