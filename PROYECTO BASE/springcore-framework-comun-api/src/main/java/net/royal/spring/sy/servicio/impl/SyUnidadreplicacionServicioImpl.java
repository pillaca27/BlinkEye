package net.royal.spring.sy.servicio.impl;

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
import net.royal.spring.sy.dao.impl.SyUnidadreplicacionDaoImpl;

import net.royal.spring.sy.dominio.BeanSyUnidadreplicacion;
import net.royal.spring.sy.dominio.BeanSyUnidadreplicacionPk;
import net.royal.spring.sy.dominio.dto.DtoComunSyUnidadreplicacion;
import net.royal.spring.sy.servicio.validar.SyUnidadreplicacionServicioValidar;

@Service (value = "BeanServicioSyUnidadreplicacion")
public class SyUnidadreplicacionServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSyUnidadreplicacion";
	private static Logger logger = LogManager.getLogger(SyUnidadreplicacionServicioImpl.class);

	@Autowired
	private SyUnidadreplicacionDaoImpl syUnidadreplicacionDao;

	@Autowired
	private SyUnidadreplicacionServicioValidar validar;

	@Transactional
	public DtoComunSyUnidadreplicacion coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunSyUnidadreplicacion dto) throws UException {
		BeanSyUnidadreplicacion syUnidadreplicacion = dto.obtenerBean();
		syUnidadreplicacion = coreInsertar(usuarioActual, syUnidadreplicacion);
		dto.setTransaccionEstado(syUnidadreplicacion.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syUnidadreplicacion.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyUnidadreplicacion coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyUnidadreplicacion syUnidadreplicacion) throws UException {
		// valores por defecto - preparando objeto
		syUnidadreplicacion = validar.prepararInsertar(usuarioActual, syUnidadreplicacion);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, syUnidadreplicacion);
		if (!lst.isEmpty()) {
			syUnidadreplicacion.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syUnidadreplicacion.setTransaccionListaMensajes(lst);
			return syUnidadreplicacion;
		}
		
		// transaccion
		syUnidadreplicacion = syUnidadreplicacionDao.coreInsertar(syUnidadreplicacion);
		syUnidadreplicacion.setTransaccionEstado(DominioTransaccion.OK);
		syUnidadreplicacion.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syUnidadreplicacion;
	}

	@Transactional
	public DtoComunSyUnidadreplicacion coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunSyUnidadreplicacion dto) throws UException {
		BeanSyUnidadreplicacion syUnidadreplicacion = dto.obtenerBean();
		syUnidadreplicacion = coreActualizar(usuarioActual, syUnidadreplicacion);
		dto.setTransaccionEstado(syUnidadreplicacion.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syUnidadreplicacion.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyUnidadreplicacion coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyUnidadreplicacion syUnidadreplicacion) throws UException {
		// valores por defecto - preparando objeto
		syUnidadreplicacion = validar.prepararActualizar(usuarioActual, syUnidadreplicacion);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, syUnidadreplicacion);
		if (!lst.isEmpty()) {
			syUnidadreplicacion.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syUnidadreplicacion.setTransaccionListaMensajes(lst);
			return syUnidadreplicacion;
		}
		
		// transaccion
	    syUnidadreplicacion = syUnidadreplicacionDao.coreActualizar(syUnidadreplicacion);
		syUnidadreplicacion.setTransaccionEstado(DominioTransaccion.OK);
		syUnidadreplicacion.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syUnidadreplicacion;
	}


	@Transactional
	public DtoComunSyUnidadreplicacion coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunSyUnidadreplicacion dto) throws UException {
		BeanSyUnidadreplicacion syUnidadreplicacion = dto.obtenerBean();
		syUnidadreplicacion = coreAnular(usuarioActual, syUnidadreplicacion);
		dto.setTransaccionEstado(syUnidadreplicacion.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syUnidadreplicacion.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyUnidadreplicacion coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyUnidadreplicacion syUnidadreplicacion) throws UException {
		// valores por defecto - preparando objeto
		syUnidadreplicacion = validar.prepararAnular(usuarioActual, syUnidadreplicacion);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, syUnidadreplicacion);
		if (!lst.isEmpty()) {
			syUnidadreplicacion.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syUnidadreplicacion.setTransaccionListaMensajes(lst);
			return syUnidadreplicacion;
		}
		
		// transaccion
	    syUnidadreplicacion.setEstado("I");
	    syUnidadreplicacion = syUnidadreplicacionDao.coreActualizar(syUnidadreplicacion);
		syUnidadreplicacion.setTransaccionEstado(DominioTransaccion.OK);
		syUnidadreplicacion.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syUnidadreplicacion;
	}

	public BeanSyUnidadreplicacion coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyUnidadreplicacionPk pk) throws UException {
		BeanSyUnidadreplicacion bean = syUnidadreplicacionDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanSyUnidadreplicacion coreAnular(SeguridadUsuarioActual usuarioActual, String punidadreplicacion) throws UException {
		return coreAnular(usuarioActual,new BeanSyUnidadreplicacionPk( punidadreplicacion));
	}

	@Transactional
	public DtoComunSyUnidadreplicacion coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunSyUnidadreplicacion dto) throws UException {
		BeanSyUnidadreplicacion syUnidadreplicacion = dto.obtenerBean();
		syUnidadreplicacion = coreEliminar(usuarioActual, syUnidadreplicacion);
		dto.setTransaccionEstado(syUnidadreplicacion.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syUnidadreplicacion.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyUnidadreplicacion coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyUnidadreplicacion syUnidadreplicacion) throws UException {
		// valores por defecto - preparando objeto
		syUnidadreplicacion = validar.prepararEliminar(usuarioActual, syUnidadreplicacion);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, syUnidadreplicacion);
		if (!lst.isEmpty()) {
			syUnidadreplicacion.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syUnidadreplicacion.setTransaccionListaMensajes(lst);
			return syUnidadreplicacion;
		}
		
		// transaccion
		syUnidadreplicacionDao.eliminar(syUnidadreplicacion);
		syUnidadreplicacion=new BeanSyUnidadreplicacion();
		syUnidadreplicacion.setTransaccionEstado(DominioTransaccion.OK);
		return syUnidadreplicacion;
	}

	public BeanSyUnidadreplicacion coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyUnidadreplicacionPk pk) throws UException {
		BeanSyUnidadreplicacion syUnidadreplicacion = syUnidadreplicacionDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,syUnidadreplicacion);
	}

	public BeanSyUnidadreplicacion coreEliminar(SeguridadUsuarioActual usuarioActual, String punidadreplicacion) throws UException {
		return coreEliminar(usuarioActual,new BeanSyUnidadreplicacionPk( punidadreplicacion));
	}

}
