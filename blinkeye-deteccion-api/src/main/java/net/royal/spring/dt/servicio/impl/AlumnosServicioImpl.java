package net.royal.spring.dt.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.dt.dao.impl.AlumnosDaoImpl;

import net.royal.spring.dt.dominio.Alumnos;
import net.royal.spring.dt.dominio.AlumnosPk;
import net.royal.spring.dt.dominio.dto.DtoAlumnos;

@Service (value = "BeanServicioAlumnos")
public class AlumnosServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAlumnos";
	private static Logger logger = LogManager.getLogger(AlumnosServicioImpl.class);

	@Autowired
	private AlumnosDaoImpl alumnosDao;

	@Autowired
	private AlumnosServicioValidar validar;

	@Transactional
	public DtoAlumnos coreInsertar(SeguridadUsuarioActual usuarioActual,DtoAlumnos dto) throws Exception {
		Alumnos alumnos = dto.obtenerBeanRegistrar();
		alumnos = coreInsertar(usuarioActual, alumnos);
		dto.setTransaccionEstado(alumnos.getTransaccionEstado());
		dto.setTransaccionListaMensajes(alumnos.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Alumnos coreInsertar(SeguridadUsuarioActual usuarioActual,Alumnos alumnos) throws Exception {
		// Persona: Crystal
		// Cambio: valores por defecto - preparando objeto
		alumnos = validar.prepararInsertar(usuarioActual, alumnos);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, alumnos);
		if (!lst.isEmpty()) {
			alumnos.setTransaccionEstado(DominioTransaccion.VALIDACION);
			alumnos.setTransaccionListaMensajes(lst);
			return alumnos;
		}
		
		// transaccion
		alumnos = alumnosDao.coreInsertar(alumnos);
		alumnos.setTransaccionEstado(DominioTransaccion.OK);
		alumnos.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return alumnos;
	}

	@Transactional
	public DtoAlumnos coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoAlumnos dto) throws Exception {
		Alumnos alumnos = alumnosDao.obtenerPorId(dto.getIdAlumno());
		alumnos = dto.obtenerBeanActualizar(alumnos);
		alumnos = coreActualizar(usuarioActual, alumnos);
		dto.setTransaccionEstado(alumnos.getTransaccionEstado());
		dto.setTransaccionListaMensajes(alumnos.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoAlumnos coreActualizar(SeguridadUsuarioActual usuarioActual,DtoAlumnos dto) throws Exception {
		Alumnos alumnos = dto.obtenerBean();
		alumnos = coreActualizar(usuarioActual, alumnos);
		dto.setTransaccionEstado(alumnos.getTransaccionEstado());
		dto.setTransaccionListaMensajes(alumnos.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Alumnos coreActualizar(SeguridadUsuarioActual usuarioActual, Alumnos alumnos) throws Exception {
		// valores por defecto - preparando objeto
		alumnos = validar.prepararActualizar(usuarioActual, alumnos);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, alumnos);
		if (!lst.isEmpty()) {
			alumnos.setTransaccionEstado(DominioTransaccion.VALIDACION);
			alumnos.setTransaccionListaMensajes(lst);
			return alumnos;
		}
		
		// transaccion
	    alumnos = alumnosDao.coreActualizar(alumnos);
		alumnos.setTransaccionEstado(DominioTransaccion.OK);
		alumnos.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return alumnos;
	}

	@Transactional
	public DtoAlumnos coreEliminar(SeguridadUsuarioActual usuarioActual,DtoAlumnos dto) throws Exception {
		Alumnos alumnos = dto.obtenerBean();
		alumnos = coreEliminar(usuarioActual, alumnos);
		dto.setTransaccionEstado(alumnos.getTransaccionEstado());
		dto.setTransaccionListaMensajes(alumnos.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Alumnos coreEliminar(SeguridadUsuarioActual usuarioActual, Alumnos alumnos) throws Exception {
		// valores por defecto - preparando objeto
		alumnos = validar.prepararEliminar(usuarioActual, alumnos);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, alumnos);
		if (!lst.isEmpty()) {
			alumnos.setTransaccionEstado(DominioTransaccion.VALIDACION);
			alumnos.setTransaccionListaMensajes(lst);
			return alumnos;
		}
		
		// transaccion
		alumnosDao.eliminar(alumnos);
		alumnos=new Alumnos();
		alumnos.setTransaccionEstado(DominioTransaccion.OK);
		return alumnos;
	}

	public Alumnos coreEliminar(SeguridadUsuarioActual usuarioActual, AlumnosPk pk) throws Exception {
		Alumnos alumnos = alumnosDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,alumnos);
	}

	public Alumnos coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidAlumno) throws Exception {
		return coreEliminar(usuarioActual,new AlumnosPk( pidAlumno));
	}
	
	/* 
	 * INICIO: CRYSTAL
	 * esto metodo .....
	 */
	
		//se agregó para mejorar la detecion qu....
	/* 
	 * FIN: CRYSTAL
	 * Que realiza el metodo
	 */

}
