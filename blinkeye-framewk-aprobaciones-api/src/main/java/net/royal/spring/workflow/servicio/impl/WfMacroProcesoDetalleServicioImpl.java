package net.royal.spring.workflow.servicio.impl;

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
import net.royal.spring.workflow.dao.impl.WfMacroProcesoDetalleDaoImpl;

import net.royal.spring.workflow.dominio.WfMacroProcesoDetalle;
import net.royal.spring.workflow.dominio.WfMacroProcesoDetallePk;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProcesoDetalle;
import net.royal.spring.workflow.servicio.validar.WfMacroProcesoDetalleServicioValidar;

@Service (value = "BeanServicioWfMacroProcesoDetalle")
public class WfMacroProcesoDetalleServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioWfMacroProcesoDetalle";
	private static Logger logger = LogManager.getLogger(WfMacroProcesoDetalleServicioImpl.class);

	@Autowired
	private WfMacroProcesoDetalleDaoImpl wfMacroProcesoDetalleDao;

	@Autowired
	private WfMacroProcesoDetalleServicioValidar validar;

	@Transactional
	public DtoWfMacroProcesoDetalle coreInsertar(SeguridadUsuarioActual usuarioActual,DtoWfMacroProcesoDetalle dto) throws UException {
		
		WfMacroProcesoDetalle wfMacroProcesoDetalle = dto.obtenerBean();
		wfMacroProcesoDetalle = coreInsertar(usuarioActual, wfMacroProcesoDetalle);
		dto.setTransaccionEstado(wfMacroProcesoDetalle.getTransaccionEstado());
		dto.setTransaccionListaMensajes(wfMacroProcesoDetalle.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public WfMacroProcesoDetalle coreInsertar(SeguridadUsuarioActual usuarioActual,WfMacroProcesoDetalle wfMacroProcesoDetalle) throws UException {
		// valores por defecto - preparando objeto
		wfMacroProcesoDetalle = validar.prepararInsertar(usuarioActual, wfMacroProcesoDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, wfMacroProcesoDetalle);
		if (!lst.isEmpty()) {
			wfMacroProcesoDetalle.setTransaccionEstado(DominioTransaccion.VALIDACION);
			wfMacroProcesoDetalle.setTransaccionListaMensajes(lst);
			return wfMacroProcesoDetalle;
		}
		
		// transaccion
		wfMacroProcesoDetalle = wfMacroProcesoDetalleDao.coreInsertar(wfMacroProcesoDetalle);
		wfMacroProcesoDetalle.setTransaccionEstado(DominioTransaccion.OK);
		wfMacroProcesoDetalle.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return wfMacroProcesoDetalle;
	}

	@Transactional
	public DtoWfMacroProcesoDetalle coreActualizar(SeguridadUsuarioActual usuarioActual,DtoWfMacroProcesoDetalle dto) throws UException {
		WfMacroProcesoDetalle wfMacroProcesoDetalle = dto.obtenerBean();
		wfMacroProcesoDetalle = coreActualizar(usuarioActual, wfMacroProcesoDetalle);
		dto.setTransaccionEstado(wfMacroProcesoDetalle.getTransaccionEstado());
		dto.setTransaccionListaMensajes(wfMacroProcesoDetalle.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public WfMacroProcesoDetalle coreActualizar(SeguridadUsuarioActual usuarioActual, WfMacroProcesoDetalle wfMacroProcesoDetalle) throws UException {
		// valores por defecto - preparando objeto
		wfMacroProcesoDetalle = validar.prepararActualizar(usuarioActual, wfMacroProcesoDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, wfMacroProcesoDetalle);
		if (!lst.isEmpty()) {
			wfMacroProcesoDetalle.setTransaccionEstado(DominioTransaccion.VALIDACION);
			wfMacroProcesoDetalle.setTransaccionListaMensajes(lst);
			return wfMacroProcesoDetalle;
		}
		
		// transaccion
	    wfMacroProcesoDetalle = wfMacroProcesoDetalleDao.coreActualizar(wfMacroProcesoDetalle);
		wfMacroProcesoDetalle.setTransaccionEstado(DominioTransaccion.OK);
		wfMacroProcesoDetalle.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return wfMacroProcesoDetalle;
	}

	@Transactional
	public DtoWfMacroProcesoDetalle coreEliminar(SeguridadUsuarioActual usuarioActual,DtoWfMacroProcesoDetalle dto) throws UException {
		WfMacroProcesoDetalle wfMacroProcesoDetalle = dto.obtenerBean();
		wfMacroProcesoDetalle = coreEliminar(usuarioActual, wfMacroProcesoDetalle);
		dto.setTransaccionEstado(wfMacroProcesoDetalle.getTransaccionEstado());
		dto.setTransaccionListaMensajes(wfMacroProcesoDetalle.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public WfMacroProcesoDetalle coreEliminar(SeguridadUsuarioActual usuarioActual, WfMacroProcesoDetalle wfMacroProcesoDetalle) throws UException {
		// valores por defecto - preparando objeto
		wfMacroProcesoDetalle = validar.prepararEliminar(usuarioActual, wfMacroProcesoDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, wfMacroProcesoDetalle);
		if (!lst.isEmpty()) {
			wfMacroProcesoDetalle.setTransaccionEstado(DominioTransaccion.VALIDACION);
			wfMacroProcesoDetalle.setTransaccionListaMensajes(lst);
			return wfMacroProcesoDetalle;
		}
		
		// transaccion
		wfMacroProcesoDetalleDao.eliminar(wfMacroProcesoDetalle);
		wfMacroProcesoDetalle=new WfMacroProcesoDetalle();
		wfMacroProcesoDetalle.setTransaccionEstado(DominioTransaccion.OK);
		return wfMacroProcesoDetalle;
	}

	public WfMacroProcesoDetalle coreEliminar(SeguridadUsuarioActual usuarioActual, WfMacroProcesoDetallePk pk) throws UException {
		WfMacroProcesoDetalle wfMacroProcesoDetalle = wfMacroProcesoDetalleDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,wfMacroProcesoDetalle);
	}

	public WfMacroProcesoDetalle coreEliminar(SeguridadUsuarioActual usuarioActual, String pmacroProcesoId,String pprocesoOrigenId,String pprocesoDestinoId) throws UException {
		return coreEliminar(usuarioActual,new WfMacroProcesoDetallePk( pmacroProcesoId, pprocesoOrigenId, pprocesoDestinoId));
	}

}
