package net.royal.spring.core.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dao.impl.ZonapostalDaoImpl;
import net.royal.spring.core.dominio.BeanZonapostal;
import net.royal.spring.core.dominio.BeanZonapostalPk;
import net.royal.spring.core.dominio.dto.DtoComunZonapostal;
import net.royal.spring.core.servicio.validar.ZonapostalServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioZonapostal")
public class ZonapostalServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioZonapostal";
	private static Logger logger = LogManager.getLogger(ZonapostalServicioImpl.class);

	@Autowired
	private ZonapostalDaoImpl zonapostalDao;

	@Autowired
	private ZonapostalServicioValidar validar;

	@Transactional
	public DtoComunZonapostal coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunZonapostal dto) throws UException {
		BeanZonapostal zonapostal = dto.obtenerBean();
		zonapostal = coreInsertar(usuarioActual, zonapostal);
		dto.setTransaccionEstado(zonapostal.getTransaccionEstado());
		dto.setTransaccionListaMensajes(zonapostal.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanZonapostal coreInsertar(SeguridadUsuarioActual usuarioActual,BeanZonapostal zonapostal) throws UException {
		// valores por defecto - preparando objeto
		zonapostal = validar.prepararInsertar(usuarioActual, zonapostal);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, zonapostal);
		if (!lst.isEmpty()) {
			zonapostal.setTransaccionEstado(DominioTransaccion.VALIDACION);
			zonapostal.setTransaccionListaMensajes(lst);
			return zonapostal;
		}
		
		// transaccion
		zonapostal = zonapostalDao.coreInsertar(zonapostal);
		zonapostal.setTransaccionEstado(DominioTransaccion.OK);
		zonapostal.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return zonapostal;
	}

	@Transactional
	public DtoComunZonapostal coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunZonapostal dto) throws UException {
		BeanZonapostal zonapostal = dto.obtenerBean();
		zonapostal = coreActualizar(usuarioActual, zonapostal);
		dto.setTransaccionEstado(zonapostal.getTransaccionEstado());
		dto.setTransaccionListaMensajes(zonapostal.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanZonapostal coreActualizar(SeguridadUsuarioActual usuarioActual, BeanZonapostal zonapostal) throws UException {
		// valores por defecto - preparando objeto
		zonapostal = validar.prepararActualizar(usuarioActual, zonapostal);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, zonapostal);
		if (!lst.isEmpty()) {
			zonapostal.setTransaccionEstado(DominioTransaccion.VALIDACION);
			zonapostal.setTransaccionListaMensajes(lst);
			return zonapostal;
		}
		
		// transaccion
	    zonapostal = zonapostalDao.coreActualizar(zonapostal);
		zonapostal.setTransaccionEstado(DominioTransaccion.OK);
		zonapostal.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return zonapostal;
	}


	@Transactional
	public DtoComunZonapostal coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunZonapostal dto) throws UException {
		BeanZonapostal zonapostal = dto.obtenerBean();
		zonapostal = coreAnular(usuarioActual, zonapostal);
		dto.setTransaccionEstado(zonapostal.getTransaccionEstado());
		dto.setTransaccionListaMensajes(zonapostal.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanZonapostal coreAnular(SeguridadUsuarioActual usuarioActual, BeanZonapostal zonapostal) throws UException {
		// valores por defecto - preparando objeto
		zonapostal = validar.prepararAnular(usuarioActual, zonapostal);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, zonapostal);
		if (!lst.isEmpty()) {
			zonapostal.setTransaccionEstado(DominioTransaccion.VALIDACION);
			zonapostal.setTransaccionListaMensajes(lst);
			return zonapostal;
		}
		
		// transaccion
	    zonapostal.setEstado("I");
	    zonapostal = zonapostalDao.coreActualizar(zonapostal);
		zonapostal.setTransaccionEstado(DominioTransaccion.OK);
		zonapostal.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return zonapostal;
	}

	public BeanZonapostal coreAnular(SeguridadUsuarioActual usuarioActual, BeanZonapostalPk pk) throws UException {
		BeanZonapostal bean = zonapostalDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}


	@Transactional
	public DtoComunZonapostal coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunZonapostal dto) throws UException {
		BeanZonapostal zonapostal = dto.obtenerBean();
		zonapostal = coreEliminar(usuarioActual, zonapostal);
		dto.setTransaccionEstado(zonapostal.getTransaccionEstado());
		dto.setTransaccionListaMensajes(zonapostal.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanZonapostal coreEliminar(SeguridadUsuarioActual usuarioActual, BeanZonapostal zonapostal) throws UException {
		// valores por defecto - preparando objeto
		zonapostal = validar.prepararEliminar(usuarioActual, zonapostal);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, zonapostal);
		if (!lst.isEmpty()) {
			zonapostal.setTransaccionEstado(DominioTransaccion.VALIDACION);
			zonapostal.setTransaccionListaMensajes(lst);
			return zonapostal;
		}
		
		// transaccion
		zonapostalDao.eliminar(zonapostal);
		zonapostal=new BeanZonapostal();
		zonapostal.setTransaccionEstado(DominioTransaccion.OK);
		return zonapostal;
	}

	public BeanZonapostal coreEliminar(SeguridadUsuarioActual usuarioActual, BeanZonapostalPk pk) throws UException {
		BeanZonapostal zonapostal = zonapostalDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,zonapostal);
	}


}
