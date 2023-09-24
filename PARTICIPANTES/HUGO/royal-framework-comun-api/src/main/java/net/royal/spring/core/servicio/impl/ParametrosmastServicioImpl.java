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
import net.royal.spring.core.dao.impl.ParametrosmastDaoImpl;

import net.royal.spring.core.dominio.BeanParametrosmast;
import net.royal.spring.core.dominio.BeanParametrosmastPk;
import net.royal.spring.core.dominio.dto.DtoComunParametrosmast;
import net.royal.spring.core.servicio.validar.ParametrosmastServicioValidar;

@Service (value = "BeanServicioParametrosmast")
public class ParametrosmastServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioParametrosmast";
	private static Logger logger = LogManager.getLogger(ParametrosmastServicioImpl.class);

	@Autowired
	private ParametrosmastDaoImpl parametrosmastDao;

	@Autowired
	private ParametrosmastServicioValidar validar;

	@Transactional
	public DtoComunParametrosmast coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunParametrosmast dto) throws UException {
		BeanParametrosmast parametrosmast = dto.obtenerBean();
		parametrosmast = coreInsertar(usuarioActual, parametrosmast);
		dto.setTransaccionEstado(parametrosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(parametrosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanParametrosmast coreInsertar(SeguridadUsuarioActual usuarioActual,BeanParametrosmast parametrosmast) throws UException {
		// valores por defecto - preparando objeto
		
		parametrosmast = validar.prepararInsertar(usuarioActual, parametrosmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, parametrosmast);
		if (!lst.isEmpty()) {
			parametrosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			parametrosmast.setTransaccionListaMensajes(lst);
			return parametrosmast;
		}
		
		// transaccion
		parametrosmast = parametrosmastDao.coreInsertar(parametrosmast);
		parametrosmast.setTransaccionEstado(DominioTransaccion.OK);
		parametrosmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return parametrosmast;
	}

	@Transactional
	public DtoComunParametrosmast coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunParametrosmast dto) throws UException {
		BeanParametrosmast parametrosmast = dto.obtenerBean();
		parametrosmast = coreActualizar(usuarioActual, parametrosmast);
		dto.setTransaccionEstado(parametrosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(parametrosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanParametrosmast coreActualizar(SeguridadUsuarioActual usuarioActual, BeanParametrosmast parametrosmast) throws UException {
		// valores por defecto - preparando objeto
		parametrosmast = validar.prepararActualizar(usuarioActual, parametrosmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, parametrosmast);
		if (!lst.isEmpty()) {
			parametrosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			parametrosmast.setTransaccionListaMensajes(lst);
			return parametrosmast;
		}
		
		// transaccion
	    parametrosmast = parametrosmastDao.coreActualizar(parametrosmast);
		parametrosmast.setTransaccionEstado(DominioTransaccion.OK);
		parametrosmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return parametrosmast;
	}


	@Transactional
	public DtoComunParametrosmast coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunParametrosmast dto) throws UException {
		BeanParametrosmast parametrosmast = dto.obtenerBean();
		parametrosmast = coreAnular(usuarioActual, parametrosmast);
		dto.setTransaccionEstado(parametrosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(parametrosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanParametrosmast coreAnular(SeguridadUsuarioActual usuarioActual, BeanParametrosmast parametrosmast) throws UException {
		// valores por defecto - preparando objeto
		parametrosmast = validar.prepararAnular(usuarioActual, parametrosmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, parametrosmast);
		if (!lst.isEmpty()) {
			parametrosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			parametrosmast.setTransaccionListaMensajes(lst);
			return parametrosmast;
		}
		
		// transaccion
	    parametrosmast = parametrosmastDao.coreActualizar(parametrosmast);
		parametrosmast.setTransaccionEstado(DominioTransaccion.OK);
		parametrosmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return parametrosmast;
	}

	public BeanParametrosmast coreAnular(SeguridadUsuarioActual usuarioActual, BeanParametrosmastPk pk) throws UException {
		BeanParametrosmast bean = parametrosmastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanParametrosmast coreAnular(SeguridadUsuarioActual usuarioActual, String pcompaniacodigo,String paplicacioncodigo,String pparametroclave) throws UException {
		return coreAnular(usuarioActual,new BeanParametrosmastPk( pcompaniacodigo, paplicacioncodigo, pparametroclave));
	}

	@Transactional
	public DtoComunParametrosmast coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunParametrosmast dto) throws UException {
		BeanParametrosmast parametrosmast = dto.obtenerBean();
		parametrosmast = coreEliminar(usuarioActual, parametrosmast);
		dto.setTransaccionEstado(parametrosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(parametrosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanParametrosmast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanParametrosmast parametrosmast) throws UException {
		// valores por defecto - preparando objeto
		parametrosmast = validar.prepararEliminar(usuarioActual, parametrosmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, parametrosmast);
		if (!lst.isEmpty()) {
			parametrosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			parametrosmast.setTransaccionListaMensajes(lst);
			return parametrosmast;
		}
		
		// transaccion
		parametrosmastDao.eliminar(parametrosmast);
		parametrosmast=new BeanParametrosmast();
		parametrosmast.setTransaccionEstado(DominioTransaccion.OK);
		return parametrosmast;
	}

	public BeanParametrosmast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanParametrosmastPk pk) throws UException {
		BeanParametrosmast parametrosmast = parametrosmastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,parametrosmast);
	}

	public BeanParametrosmast coreEliminar(SeguridadUsuarioActual usuarioActual, String pcompaniacodigo,String paplicacioncodigo,String pparametroclave) throws UException {
		return coreEliminar(usuarioActual,new BeanParametrosmastPk( pcompaniacodigo, paplicacioncodigo, pparametroclave));
	}

}
