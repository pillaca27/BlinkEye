package net.royal.spring.sistema.servicio.impl;

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
import net.royal.spring.sistema.dao.impl.SyPreferencesDaoImpl;

import net.royal.spring.sistema.dominio.BeanSyPreferences;
import net.royal.spring.sistema.dominio.BeanSyPreferencesPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyPreferences;
import net.royal.spring.sistema.servicio.validar.SyPreferencesServicioValidar;

@Service (value = "BeanServicioSyPreferences")
public class SyPreferencesServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSyPreferences";
	private static Logger logger = LogManager.getLogger(SyPreferencesServicioImpl.class);

	@Autowired
	private SyPreferencesDaoImpl syPreferencesDao;

	@Autowired
	private SyPreferencesServicioValidar validar;

	@Transactional
	public DtoComunSyPreferences coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunSyPreferences dto) throws Exception {
		BeanSyPreferences syPreferences = dto.obtenerBean();
		syPreferences = coreInsertar(usuarioActual, syPreferences);
		dto.setTransaccionEstado(syPreferences.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syPreferences.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyPreferences coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyPreferences syPreferences) throws Exception {
		// valores por defecto - preparando objeto
		syPreferences = validar.prepararInsertar(usuarioActual, syPreferences);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, syPreferences);
		if (!lst.isEmpty()) {
			syPreferences.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syPreferences.setTransaccionListaMensajes(lst);
			return syPreferences;
		}
		
		// transaccion
		syPreferences = syPreferencesDao.coreInsertar(syPreferences);
		syPreferences.setTransaccionEstado(DominioTransaccion.OK);
		syPreferences.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syPreferences;
	}

	@Transactional
	public DtoComunSyPreferences coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunSyPreferences dto) throws Exception {
		BeanSyPreferences syPreferences = dto.obtenerBean();
		syPreferences = coreActualizar(usuarioActual, syPreferences);
		dto.setTransaccionEstado(syPreferences.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syPreferences.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyPreferences coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyPreferences syPreferences) throws Exception {
		// valores por defecto - preparando objeto
		syPreferences = validar.prepararActualizar(usuarioActual, syPreferences);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, syPreferences);
		if (!lst.isEmpty()) {
			syPreferences.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syPreferences.setTransaccionListaMensajes(lst);
			return syPreferences;
		}
		
		// transaccion
	    syPreferences = syPreferencesDao.coreActualizar(syPreferences);
		syPreferences.setTransaccionEstado(DominioTransaccion.OK);
		syPreferences.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syPreferences;
	}

	@Transactional
	public DtoComunSyPreferences coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunSyPreferences dto) throws Exception {
		BeanSyPreferences syPreferences = dto.obtenerBean();
		syPreferences = coreEliminar(usuarioActual, syPreferences);
		dto.setTransaccionEstado(syPreferences.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syPreferences.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyPreferences coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyPreferences syPreferences) throws Exception {
		// valores por defecto - preparando objeto
		syPreferences = validar.prepararEliminar(usuarioActual, syPreferences);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, syPreferences);
		if (!lst.isEmpty()) {
			syPreferences.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syPreferences.setTransaccionListaMensajes(lst);
			return syPreferences;
		}
		
		// transaccion
		syPreferencesDao.eliminar(syPreferences);
		syPreferences=new BeanSyPreferences();
		syPreferences.setTransaccionEstado(DominioTransaccion.OK);
		return syPreferences;
	}

	public BeanSyPreferences coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyPreferencesPk pk) throws Exception {
		BeanSyPreferences syPreferences = syPreferencesDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,syPreferences);
	}

	public BeanSyPreferences coreEliminar(SeguridadUsuarioActual usuarioActual, String pusuario,String ppreference) throws Exception {
		return coreEliminar(usuarioActual,new BeanSyPreferencesPk( pusuario, ppreference));
	}

}
