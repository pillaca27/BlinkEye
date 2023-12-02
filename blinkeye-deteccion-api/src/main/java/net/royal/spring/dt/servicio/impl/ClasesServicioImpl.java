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
import net.royal.spring.dt.dao.impl.ClasesDaoImpl;

import net.royal.spring.dt.dominio.Clases;
import net.royal.spring.dt.dominio.ClasesPk;
import net.royal.spring.dt.dominio.dto.DtoClases;

@Service (value = "BeanServicioClases")
public class ClasesServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioClases";
	private static Logger logger = LogManager.getLogger(ClasesServicioImpl.class);

	@Autowired
	private ClasesDaoImpl clasesDao;

	@Autowired
	private ClasesServicioValidar validar;

	@Transactional
	public DtoClases coreInsertar(SeguridadUsuarioActual usuarioActual,DtoClases dto) throws Exception {
		Clases clases = dto.obtenerBeanRegistrar();
		clases = coreInsertar(usuarioActual, clases);
		dto.setTransaccionEstado(clases.getTransaccionEstado());
		dto.setTransaccionListaMensajes(clases.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Clases coreInsertar(SeguridadUsuarioActual usuarioActual,Clases clases) throws Exception {
		// valores por defecto - preparando objeto
		clases = validar.prepararInsertar(usuarioActual, clases);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, clases);
		if (!lst.isEmpty()) {
			clases.setTransaccionEstado(DominioTransaccion.VALIDACION);
			clases.setTransaccionListaMensajes(lst);
			return clases;
		}
		
		// transaccion
		clases = clasesDao.coreInsertar(clases);
		clases.setTransaccionEstado(DominioTransaccion.OK);
		clases.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return clases;
	}

	@Transactional
	public DtoClases coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoClases dto) throws Exception {
		Clases clases = clasesDao.obtenerPorId(dto.getIdClase());
		clases = dto.obtenerBeanActualizar(clases);
		clases = coreActualizar(usuarioActual, clases);
		dto.setTransaccionEstado(clases.getTransaccionEstado());
		dto.setTransaccionListaMensajes(clases.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoClases coreActualizar(SeguridadUsuarioActual usuarioActual,DtoClases dto) throws Exception {
		Clases clases = dto.obtenerBean();
		clases = coreActualizar(usuarioActual, clases);
		dto.setTransaccionEstado(clases.getTransaccionEstado());
		dto.setTransaccionListaMensajes(clases.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Clases coreActualizar(SeguridadUsuarioActual usuarioActual, Clases clases) throws Exception {
		// valores por defecto - preparando objeto
		clases = validar.prepararActualizar(usuarioActual, clases);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, clases);
		if (!lst.isEmpty()) {
			clases.setTransaccionEstado(DominioTransaccion.VALIDACION);
			clases.setTransaccionListaMensajes(lst);
			return clases;
		}
		
		// transaccion
	    clases = clasesDao.coreActualizar(clases);
		clases.setTransaccionEstado(DominioTransaccion.OK);
		clases.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return clases;
	}

	@Transactional
	public DtoClases coreEliminar(SeguridadUsuarioActual usuarioActual,DtoClases dto) throws Exception {
		Clases clases = dto.obtenerBean();
		clases = coreEliminar(usuarioActual, clases);
		dto.setTransaccionEstado(clases.getTransaccionEstado());
		dto.setTransaccionListaMensajes(clases.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Clases coreEliminar(SeguridadUsuarioActual usuarioActual, Clases clases) throws Exception {
		// valores por defecto - preparando objeto
		clases = validar.prepararEliminar(usuarioActual, clases);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, clases);
		if (!lst.isEmpty()) {
			clases.setTransaccionEstado(DominioTransaccion.VALIDACION);
			clases.setTransaccionListaMensajes(lst);
			return clases;
		}
		
		// transaccion
		clasesDao.eliminar(clases);
		clases=new Clases();
		clases.setTransaccionEstado(DominioTransaccion.OK);
		return clases;
	}

	public Clases coreEliminar(SeguridadUsuarioActual usuarioActual, ClasesPk pk) throws Exception {
		Clases clases = clasesDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,clases);
	}

	public Clases coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidClase) throws Exception {
		return coreEliminar(usuarioActual,new ClasesPk( pidClase));
	}

}
