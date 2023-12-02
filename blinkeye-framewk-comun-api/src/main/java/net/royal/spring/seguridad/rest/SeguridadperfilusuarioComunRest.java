package net.royal.spring.seguridad.rest;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.framework.modelo.correo.EmailConfiguracion;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.seguridad.dominio.dto.DtoComunSeguridadperfilusuario;

@RestController
@RequestMapping("/spring/seguridad/seguridadperfilusuariocomun")
@CrossOrigin(origins = "*")
public class SeguridadperfilusuarioComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SeguridadperfilusuarioComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SeguridadperfilusuarioComunRest() {
		super("seguridadperfilusuario");
	}

	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Seguridad Perfil | Parametros de entrada : DtoComunSeguridadperfilusuario | Parametros de salida: DtoComunSeguridadperfilusuario", 
			value = "SG-SEGPERF-C0001", tags= {"CORE", "SEGURIDAD PERFIL"})	
	@Transactional
	@PutMapping(value = "/listarporusuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSeguridadperfilusuario>> listarPorUsuario(@RequestBody DtoComunSeguridadperfilusuario filtro) throws Exception {
		filtro.setEstado(null);
		filtro.setPerfil(null);
		return new ResponseEntity<List<DtoComunSeguridadperfilusuario>>(listarCore(filtro), HttpStatus.OK );
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Seguridad Perfil | Parametros de entrada : DtoComunSeguridadperfilusuario | Parametros de salida: DtoComunSeguridadperfilusuario", 
			value = "SG-SEGPERF-C0002", tags= {"CORE", "SEGURIDAD PERFIL"})
	@Transactional
	@PutMapping(value = "/listaractivosporusuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSeguridadperfilusuario>> listarActivosPorUsuario(@RequestBody DtoComunSeguridadperfilusuario filtro) throws Exception {
		filtro.setEstado("A");
		filtro.setPerfil(null);
		return new ResponseEntity<List<DtoComunSeguridadperfilusuario>>(listarCore(filtro), HttpStatus.OK );
	}
	
	public List<DtoComunSeguridadperfilusuario> listarCore(@RequestBody DtoComunSeguridadperfilusuario filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getUsuario()));
        parametros.add(new DominioParametroPersistencia("p_perfil", String.class, filtro.getPerfil()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunSeguridadperfilusuario.class, "seguridadperfilusuario.listarCore", parametros);
		return datos;
	}
}
