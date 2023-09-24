package net.royal.spring.sg.rest;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.sg.dao.impl.SeguridadautorizacionesDaoImpl;
import net.royal.spring.sg.dominio.dto.DtoComunSeguridadautorizaciones;

@RestController
@RequestMapping("/spring/seguridad/seguridadautorizacionescomun")
@CrossOrigin(origins = "*")
public class SeguridadautorizacionesComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SeguridadautorizacionesComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SeguridadautorizacionesComunRest() {
		super("seguridadautorizaciones");
	}
	
	@Autowired
	private SeguridadautorizacionesDaoImpl seguridadautorizacionesDao;
	
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Seguridad Autorizaciones | Parametros de entrada : DtoComunSeguridadautorizaciones | Parametros de salida: DtoComunSeguridadautorizaciones", 
			value = "SG-AUTORIZ-C0001", tags= {"CORE", "SEGURIDAD AUTORIZACIONES"})	
	@Transactional
	@PutMapping(value = "/listarporaplicacionusuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSeguridadautorizaciones>> listarPorAplicacionUsuario(@RequestBody DtoComunSeguridadautorizaciones filtro) throws Exception {
		filtro.setEstado(null);
		return new ResponseEntity<List<DtoComunSeguridadautorizaciones>>(listarPorAplicacionUsuarioCore(filtro), HttpStatus.OK );
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Seguridad Autorizaciones | Parametros de entrada : DtoComunSeguridadautorizaciones | Parametros de salida: DtoComunSeguridadautorizaciones", 
			value = "SG-AUTORIZ-C0002", tags= {"CORE", "SEGURIDAD AUTORIZACIONES"})	
	@Transactional
	@PutMapping(value = "/listaractivosporaplicacionusuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSeguridadautorizaciones>> listarActivosPorAplicacionUsuario(@RequestBody DtoComunSeguridadautorizaciones filtro) throws Exception {
		return new ResponseEntity<List<DtoComunSeguridadautorizaciones>>(listarPorAplicacionUsuarioCore(filtro), HttpStatus.OK );
	}
	
	public List<DtoComunSeguridadautorizaciones> listarPorAplicacionUsuarioCore(@RequestBody DtoComunSeguridadautorizaciones filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getAplicacioncodigo()));
        parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getUsuario()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunSeguridadautorizaciones.class, "seguridadautorizaciones.listarCore", parametros);
		return datos;
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSeguridadautorizaciones> obtenerDto(@RequestBody DtoComunSeguridadautorizaciones pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, pk.getGrupo()));
		parametros.add(new DominioParametroPersistencia("p_concepto", String.class, pk.getConcepto()));
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, pk.getUsuario()));
		List datos = this.listarPorQuery(DtoComunSeguridadautorizaciones.class, "seguridadautorizaciones.obtenerDto",parametros);
		DtoComunSeguridadautorizaciones dto;
		if (datos.size()==1) {
			dto = (DtoComunSeguridadautorizaciones)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunSeguridadautorizaciones();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunSeguridadautorizaciones>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/usuarioautorizadoaplicacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSeguridadautorizaciones> usuarioAutorizadoAplicacion(@RequestBody DtoComunSeguridadautorizaciones pk) throws Exception {
		logger.debug("usuarioAutorizadoAplicacion");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, pk.getGrupo()));
		parametros.add(new DominioParametroPersistencia("p_concepto", String.class, pk.getConcepto()));
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, pk.getUsuario()));
		List datos = this.listarPorQuery(DtoComunSeguridadautorizaciones.class, "seguridadautorizaciones.usuarioAutorizadoAplicacion",parametros);
		DtoComunSeguridadautorizaciones dto;
		if (datos.size()==1) {
			dto = (DtoComunSeguridadautorizaciones)datos.get(0);			
			if (UString.esNuloVacio(dto.getAplicacioncodigo()) ) {
				dto.setTransaccionEstado(DominioTransaccion.ERROR);
			}else {
				dto.setAplicacioncodigo(pk.getAplicacioncodigo());
				dto.setTransaccionEstado(DominioTransaccion.OK);	
			}
		}else {
			dto = new DtoComunSeguridadautorizaciones();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunSeguridadautorizaciones>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "f_sql_read_user_autorizacion", 
			value = "", tags= {"SEGURIDAD", "SEGURIDAD AUTORIZACIONES"})	
	@Transactional
	@PutMapping(value="/tieneautorizacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> tieneAutorizacion(@RequestBody DtoComunSeguridadautorizaciones pk) throws Exception {
		logger.debug("tieneAutorizacion");
		String resp = seguridadautorizacionesDao.tieneAutorizacion(pk);
		return new ResponseEntity<String>(resp,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/tieneAcceso", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> tieneAcceso(@RequestBody DtoTabla dt) throws Exception {			
		
		DtoTabla retorno = new DtoTabla();
		retorno.setCodigo("N");
				
		DtoComunSeguridadautorizaciones pk = new DtoComunSeguridadautorizaciones(dt.getCodigo(),dt.getDescripcion(),dt.getNombre(),getUsuarioActual().getUsuario());
		
		String resp = seguridadautorizacionesDao.tieneAutorizacion(pk);

		if ("S".equals(resp)) {
			retorno.setCodigo("S");
		}
		return new ResponseEntity<DtoTabla>(retorno, HttpStatus.OK);
	}
}
