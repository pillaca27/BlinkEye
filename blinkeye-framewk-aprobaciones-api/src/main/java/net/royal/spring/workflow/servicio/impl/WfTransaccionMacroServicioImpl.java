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
import net.royal.spring.workflow.dao.impl.WfTransaccionMacroDaoImpl;

import net.royal.spring.workflow.dominio.WfTransaccionMacro;
import net.royal.spring.workflow.dominio.WfTransaccionMacroPk;
import net.royal.spring.workflow.dominio.dto.DtoWfTransaccionMacro;
import net.royal.spring.workflow.servicio.validar.WfTransaccionMacroServicioValidar;

@Service (value = "BeanServicioWfTransaccionMacro")
public class WfTransaccionMacroServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioWfTransaccionMacro";
	private static Logger logger = LogManager.getLogger(WfTransaccionMacroServicioImpl.class);

	@Autowired
	private WfTransaccionMacroDaoImpl wfTransaccionMacroDao;

	@Autowired
	private WfTransaccionMacroServicioValidar validar;

	@Transactional
	public DtoWfTransaccionMacro coreInsertar(SeguridadUsuarioActual usuarioActual,DtoWfTransaccionMacro dto) throws UException {
		WfTransaccionMacro wfTransaccionMacro = dto.obtenerBean();
		wfTransaccionMacro = coreInsertar(usuarioActual, wfTransaccionMacro);
		dto.setTransaccionEstado(wfTransaccionMacro.getTransaccionEstado());
		dto.setTransaccionListaMensajes(wfTransaccionMacro.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public WfTransaccionMacro coreInsertar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacro wfTransaccionMacro) throws UException {
		// valores por defecto - preparando objeto
		wfTransaccionMacro = validar.prepararInsertar(usuarioActual, wfTransaccionMacro);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, wfTransaccionMacro);
		if (!lst.isEmpty()) {
			wfTransaccionMacro.setTransaccionEstado(DominioTransaccion.VALIDACION);
			wfTransaccionMacro.setTransaccionListaMensajes(lst);
			return wfTransaccionMacro;
		}
		
		// transaccion
		wfTransaccionMacro = wfTransaccionMacroDao.coreInsertar(wfTransaccionMacro);
		wfTransaccionMacro.setTransaccionEstado(DominioTransaccion.OK);
		wfTransaccionMacro.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return wfTransaccionMacro;
	}

	@Transactional
	public DtoWfTransaccionMacro coreActualizar(SeguridadUsuarioActual usuarioActual,DtoWfTransaccionMacro dto) throws UException {
		WfTransaccionMacro wfTransaccionMacro = dto.obtenerBean();
		wfTransaccionMacro = coreActualizar(usuarioActual, wfTransaccionMacro);
		dto.setTransaccionEstado(wfTransaccionMacro.getTransaccionEstado());
		dto.setTransaccionListaMensajes(wfTransaccionMacro.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public WfTransaccionMacro coreActualizar(SeguridadUsuarioActual usuarioActual, WfTransaccionMacro wfTransaccionMacro) throws UException {
		// valores por defecto - preparando objeto
		wfTransaccionMacro = validar.prepararActualizar(usuarioActual, wfTransaccionMacro);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, wfTransaccionMacro);
		if (!lst.isEmpty()) {
			wfTransaccionMacro.setTransaccionEstado(DominioTransaccion.VALIDACION);
			wfTransaccionMacro.setTransaccionListaMensajes(lst);
			return wfTransaccionMacro;
		}
		
		// transaccion
	    wfTransaccionMacro = wfTransaccionMacroDao.coreActualizar(wfTransaccionMacro);
		wfTransaccionMacro.setTransaccionEstado(DominioTransaccion.OK);
		wfTransaccionMacro.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return wfTransaccionMacro;
	}

	@Transactional
	public DtoWfTransaccionMacro coreEliminar(SeguridadUsuarioActual usuarioActual,DtoWfTransaccionMacro dto) throws UException {
		WfTransaccionMacro wfTransaccionMacro = dto.obtenerBean();
		wfTransaccionMacro = coreEliminar(usuarioActual, wfTransaccionMacro);
		dto.setTransaccionEstado(wfTransaccionMacro.getTransaccionEstado());
		dto.setTransaccionListaMensajes(wfTransaccionMacro.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public WfTransaccionMacro coreEliminar(SeguridadUsuarioActual usuarioActual, WfTransaccionMacro wfTransaccionMacro) throws UException {
		// valores por defecto - preparando objeto
		wfTransaccionMacro = validar.prepararEliminar(usuarioActual, wfTransaccionMacro);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, wfTransaccionMacro);
		if (!lst.isEmpty()) {
			wfTransaccionMacro.setTransaccionEstado(DominioTransaccion.VALIDACION);
			wfTransaccionMacro.setTransaccionListaMensajes(lst);
			return wfTransaccionMacro;
		}
		
		// transaccion
		wfTransaccionMacroDao.eliminar(wfTransaccionMacro);
		wfTransaccionMacro=new WfTransaccionMacro();
		wfTransaccionMacro.setTransaccionEstado(DominioTransaccion.OK);
		return wfTransaccionMacro;
	}

	public WfTransaccionMacro coreEliminar(SeguridadUsuarioActual usuarioActual, WfTransaccionMacroPk pk) throws UException {
		WfTransaccionMacro wfTransaccionMacro = wfTransaccionMacroDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,wfTransaccionMacro);
	}

	public WfTransaccionMacro coreEliminar(SeguridadUsuarioActual usuarioActual, Integer ptransaccionMacroId) throws UException {
		return coreEliminar(usuarioActual,new WfTransaccionMacroPk( ptransaccionMacroId));
	}

}
