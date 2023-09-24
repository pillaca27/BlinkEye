package net.royal.spring.core.servicio.impl;

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
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;

import net.royal.spring.core.dominio.BeanCorrelativosmast;
import net.royal.spring.core.dominio.BeanCorrelativosmastPk;
import net.royal.spring.core.dominio.dto.DtoComunCorrelativosmast;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;

@Service (value = "BeanServicioCorrelativosmast")
public class CorrelativosmastServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioCorrelativosmast";
	private static Logger logger = LogManager.getLogger(CorrelativosmastServicioImpl.class);

	@Autowired
	private CorrelativosmastDaoImpl correlativosmastDao;

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Transactional
	public DtoComunCorrelativosmast coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunCorrelativosmast dto) throws UException {
		BeanCorrelativosmast correlativosmast = dto.obtenerBean();
		correlativosmast = coreInsertar(usuarioActual, correlativosmast);
		dto.setTransaccionEstado(correlativosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(correlativosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanCorrelativosmast coreInsertar(SeguridadUsuarioActual usuarioActual,BeanCorrelativosmast correlativosmast) throws UException {
		// valores por defecto - preparando objeto
		correlativosmast = validar.prepararInsertar(usuarioActual, correlativosmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, correlativosmast);
		if (!lst.isEmpty()) {
			correlativosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			correlativosmast.setTransaccionListaMensajes(lst);
			return correlativosmast;
		}
		
		// transaccion
		correlativosmast = correlativosmastDao.coreInsertar(correlativosmast);
		correlativosmast.setTransaccionEstado(DominioTransaccion.OK);
		correlativosmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return correlativosmast;
	}

	@Transactional
	public DtoComunCorrelativosmast coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunCorrelativosmast dto) throws UException {
		BeanCorrelativosmast correlativosmast = dto.obtenerBean();
		correlativosmast = coreActualizar(usuarioActual, correlativosmast);
		dto.setTransaccionEstado(correlativosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(correlativosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanCorrelativosmast coreActualizar(SeguridadUsuarioActual usuarioActual, BeanCorrelativosmast correlativosmast) throws UException {
		// valores por defecto - preparando objeto
		correlativosmast = validar.prepararActualizar(usuarioActual, correlativosmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, correlativosmast);
		if (!lst.isEmpty()) {
			correlativosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			correlativosmast.setTransaccionListaMensajes(lst);
			return correlativosmast;
		}
		
		// transaccion
	    correlativosmast = correlativosmastDao.coreActualizar(correlativosmast);
		correlativosmast.setTransaccionEstado(DominioTransaccion.OK);
		correlativosmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return correlativosmast;
	}


	@Transactional
	public DtoComunCorrelativosmast coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunCorrelativosmast dto) throws UException {
		BeanCorrelativosmast correlativosmast = dto.obtenerBean();
		correlativosmast = coreAnular(usuarioActual, correlativosmast);
		dto.setTransaccionEstado(correlativosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(correlativosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanCorrelativosmast coreAnular(SeguridadUsuarioActual usuarioActual, BeanCorrelativosmast correlativosmast) throws UException {
		// valores por defecto - preparando objeto
		correlativosmast = validar.prepararAnular(usuarioActual, correlativosmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, correlativosmast);
		if (!lst.isEmpty()) {
			correlativosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			correlativosmast.setTransaccionListaMensajes(lst);
			return correlativosmast;
		}
		
		// transaccion
	    correlativosmast = correlativosmastDao.coreActualizar(correlativosmast);
		correlativosmast.setTransaccionEstado(DominioTransaccion.OK);
		correlativosmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return correlativosmast;
	}

	public BeanCorrelativosmast coreAnular(SeguridadUsuarioActual usuarioActual, BeanCorrelativosmastPk pk) throws UException {
		BeanCorrelativosmast bean = correlativosmastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanCorrelativosmast coreAnular(SeguridadUsuarioActual usuarioActual, String pcompaniacodigo,String ptipocomprobante,String pserie) throws UException {
		return coreAnular(usuarioActual,new BeanCorrelativosmastPk( pcompaniacodigo, ptipocomprobante, pserie));
	}

	@Transactional
	public DtoComunCorrelativosmast coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunCorrelativosmast dto) throws UException {
		BeanCorrelativosmast correlativosmast = dto.obtenerBean();
		correlativosmast = coreEliminar(usuarioActual, correlativosmast);
		dto.setTransaccionEstado(correlativosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(correlativosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanCorrelativosmast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanCorrelativosmast correlativosmast) throws UException {
		// valores por defecto - preparando objeto
		correlativosmast = validar.prepararEliminar(usuarioActual, correlativosmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, correlativosmast);
		if (!lst.isEmpty()) {
			correlativosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			correlativosmast.setTransaccionListaMensajes(lst);
			return correlativosmast;
		}
		
		// transaccion
		correlativosmastDao.eliminar(correlativosmast);
		correlativosmast=new BeanCorrelativosmast();
		correlativosmast.setTransaccionEstado(DominioTransaccion.OK);
		return correlativosmast;
	}

	public BeanCorrelativosmast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanCorrelativosmastPk pk) throws UException {
		BeanCorrelativosmast correlativosmast = correlativosmastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,correlativosmast);
	}

	public BeanCorrelativosmast coreEliminar(SeguridadUsuarioActual usuarioActual, String pcompaniacodigo,String ptipocomprobante,String pserie) throws UException {
		return coreEliminar(usuarioActual,new BeanCorrelativosmastPk( pcompaniacodigo, ptipocomprobante, pserie));
	}

}
