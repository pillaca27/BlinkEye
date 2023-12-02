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
import net.royal.spring.workflow.dao.impl.WfTransaccionMacroDetalleDaoImpl;

import net.royal.spring.workflow.dominio.WfTransaccionMacroDetalle;
import net.royal.spring.workflow.dominio.WfTransaccionMacroDetallePk;
import net.royal.spring.workflow.dominio.dto.DtoWfTransaccionMacroDetalle;
import net.royal.spring.workflow.servicio.validar.WfTransaccionMacroDetalleServicioValidar;

@Service (value = "BeanServicioWfTransaccionMacroDetalle")
public class WfTransaccionMacroDetalleServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioWfTransaccionMacroDetalle";
	private static Logger logger = LogManager.getLogger(WfTransaccionMacroDetalleServicioImpl.class);

	@Autowired
	private WfTransaccionMacroDetalleDaoImpl wfTransaccionMacroDetalleDao;

	@Autowired
	private WfTransaccionMacroDetalleServicioValidar validar;

	@Transactional
	public DtoWfTransaccionMacroDetalle coreInsertar(SeguridadUsuarioActual usuarioActual,DtoWfTransaccionMacroDetalle dto) throws UException {
		WfTransaccionMacroDetalle wfTransaccionMacroDetalle = dto.obtenerBean();
		wfTransaccionMacroDetalle = coreInsertar(usuarioActual, wfTransaccionMacroDetalle);
		dto.setTransaccionEstado(wfTransaccionMacroDetalle.getTransaccionEstado());
		dto.setTransaccionListaMensajes(wfTransaccionMacroDetalle.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public WfTransaccionMacroDetalle coreInsertar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacroDetalle wfTransaccionMacroDetalle) throws UException {
		// valores por defecto - preparando objeto
		wfTransaccionMacroDetalle = validar.prepararInsertar(usuarioActual, wfTransaccionMacroDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, wfTransaccionMacroDetalle);
		if (!lst.isEmpty()) {
			wfTransaccionMacroDetalle.setTransaccionEstado(DominioTransaccion.VALIDACION);
			wfTransaccionMacroDetalle.setTransaccionListaMensajes(lst);
			return wfTransaccionMacroDetalle;
		}
		
		// transaccion
		wfTransaccionMacroDetalle = wfTransaccionMacroDetalleDao.coreInsertar(wfTransaccionMacroDetalle);
		wfTransaccionMacroDetalle.setTransaccionEstado(DominioTransaccion.OK);
		wfTransaccionMacroDetalle.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return wfTransaccionMacroDetalle;
	}

	@Transactional
	public DtoWfTransaccionMacroDetalle coreActualizar(SeguridadUsuarioActual usuarioActual,DtoWfTransaccionMacroDetalle dto) throws UException {
		WfTransaccionMacroDetalle wfTransaccionMacroDetalle = dto.obtenerBean();
		wfTransaccionMacroDetalle = coreActualizar(usuarioActual, wfTransaccionMacroDetalle);
		dto.setTransaccionEstado(wfTransaccionMacroDetalle.getTransaccionEstado());
		dto.setTransaccionListaMensajes(wfTransaccionMacroDetalle.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public WfTransaccionMacroDetalle coreActualizar(SeguridadUsuarioActual usuarioActual, WfTransaccionMacroDetalle wfTransaccionMacroDetalle) throws UException {
		// valores por defecto - preparando objeto
		wfTransaccionMacroDetalle = validar.prepararActualizar(usuarioActual, wfTransaccionMacroDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, wfTransaccionMacroDetalle);
		if (!lst.isEmpty()) {
			wfTransaccionMacroDetalle.setTransaccionEstado(DominioTransaccion.VALIDACION);
			wfTransaccionMacroDetalle.setTransaccionListaMensajes(lst);
			return wfTransaccionMacroDetalle;
		}
		
		// transaccion
	    wfTransaccionMacroDetalle = wfTransaccionMacroDetalleDao.coreActualizar(wfTransaccionMacroDetalle);
		wfTransaccionMacroDetalle.setTransaccionEstado(DominioTransaccion.OK);
		wfTransaccionMacroDetalle.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return wfTransaccionMacroDetalle;
	}

	@Transactional
	public DtoWfTransaccionMacroDetalle coreEliminar(SeguridadUsuarioActual usuarioActual,DtoWfTransaccionMacroDetalle dto) throws UException {
		WfTransaccionMacroDetalle wfTransaccionMacroDetalle = dto.obtenerBean();
		wfTransaccionMacroDetalle = coreEliminar(usuarioActual, wfTransaccionMacroDetalle);
		dto.setTransaccionEstado(wfTransaccionMacroDetalle.getTransaccionEstado());
		dto.setTransaccionListaMensajes(wfTransaccionMacroDetalle.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public WfTransaccionMacroDetalle coreEliminar(SeguridadUsuarioActual usuarioActual, WfTransaccionMacroDetalle wfTransaccionMacroDetalle) throws UException {
		// valores por defecto - preparando objeto
		wfTransaccionMacroDetalle = validar.prepararEliminar(usuarioActual, wfTransaccionMacroDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, wfTransaccionMacroDetalle);
		if (!lst.isEmpty()) {
			wfTransaccionMacroDetalle.setTransaccionEstado(DominioTransaccion.VALIDACION);
			wfTransaccionMacroDetalle.setTransaccionListaMensajes(lst);
			return wfTransaccionMacroDetalle;
		}
		
		// transaccion
		wfTransaccionMacroDetalleDao.eliminar(wfTransaccionMacroDetalle);
		wfTransaccionMacroDetalle=new WfTransaccionMacroDetalle();
		wfTransaccionMacroDetalle.setTransaccionEstado(DominioTransaccion.OK);
		return wfTransaccionMacroDetalle;
	}

	public WfTransaccionMacroDetalle coreEliminar(SeguridadUsuarioActual usuarioActual, WfTransaccionMacroDetallePk pk) throws UException {
		WfTransaccionMacroDetalle wfTransaccionMacroDetalle = wfTransaccionMacroDetalleDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,wfTransaccionMacroDetalle);
	}

	public WfTransaccionMacroDetalle coreEliminar(SeguridadUsuarioActual usuarioActual, Integer ptransaccionMacroId,Integer ptransaccionMacroDetalleId) throws UException {
		return coreEliminar(usuarioActual,new WfTransaccionMacroDetallePk( ptransaccionMacroId, ptransaccionMacroDetalleId));
	}

}
