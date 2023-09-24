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
import net.royal.spring.core.dao.impl.AplicacionesmastDaoImpl;

import net.royal.spring.core.dominio.BeanAplicacionesmast;
import net.royal.spring.core.dominio.BeanAplicacionesmastPk;
import net.royal.spring.core.dominio.dto.DtoComunAplicacionesmast;
import net.royal.spring.core.servicio.validar.AplicacionesmastServicioValidar;

@Service (value = "BeanServicioAplicacionesmast")
public class AplicacionesmastServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAplicacionesmast";
	private static Logger logger = LogManager.getLogger(AplicacionesmastServicioImpl.class);

	@Autowired
	private AplicacionesmastDaoImpl aplicacionesmastDao;

	@Autowired
	private AplicacionesmastServicioValidar validar;

	@Transactional
	public DtoComunAplicacionesmast coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunAplicacionesmast dto) throws UException {
		BeanAplicacionesmast aplicacionesmast = dto.obtenerBean();
		aplicacionesmast = coreInsertar(usuarioActual, aplicacionesmast);
		dto.setTransaccionEstado(aplicacionesmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(aplicacionesmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAplicacionesmast coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAplicacionesmast aplicacionesmast) throws UException {
		// valores por defecto - preparando objeto
		aplicacionesmast = validar.prepararInsertar(usuarioActual, aplicacionesmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, aplicacionesmast);
		if (!lst.isEmpty()) {
			aplicacionesmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			aplicacionesmast.setTransaccionListaMensajes(lst);
			return aplicacionesmast;
		}
		
		// transaccion
		aplicacionesmast = aplicacionesmastDao.coreInsertar(aplicacionesmast);
		aplicacionesmast.setTransaccionEstado(DominioTransaccion.OK);
		aplicacionesmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return aplicacionesmast;
	}

	@Transactional
	public DtoComunAplicacionesmast coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunAplicacionesmast dto) throws UException {
		BeanAplicacionesmast aplicacionesmast = dto.obtenerBean();
		aplicacionesmast = coreActualizar(usuarioActual, aplicacionesmast);
		dto.setTransaccionEstado(aplicacionesmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(aplicacionesmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAplicacionesmast coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmast aplicacionesmast) throws UException {
		// valores por defecto - preparando objeto
		aplicacionesmast = validar.prepararActualizar(usuarioActual, aplicacionesmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, aplicacionesmast);
		if (!lst.isEmpty()) {
			aplicacionesmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			aplicacionesmast.setTransaccionListaMensajes(lst);
			return aplicacionesmast;
		}
		
		// transaccion
	    aplicacionesmast = aplicacionesmastDao.coreActualizar(aplicacionesmast);
		aplicacionesmast.setTransaccionEstado(DominioTransaccion.OK);
		aplicacionesmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return aplicacionesmast;
	}


	@Transactional
	public DtoComunAplicacionesmast coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunAplicacionesmast dto) throws UException {
		BeanAplicacionesmast aplicacionesmast = dto.obtenerBean();
		aplicacionesmast = coreAnular(usuarioActual, aplicacionesmast);
		dto.setTransaccionEstado(aplicacionesmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(aplicacionesmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAplicacionesmast coreAnular(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmast aplicacionesmast) throws UException {
		// valores por defecto - preparando objeto
		aplicacionesmast = validar.prepararAnular(usuarioActual, aplicacionesmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, aplicacionesmast);
		if (!lst.isEmpty()) {
			aplicacionesmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			aplicacionesmast.setTransaccionListaMensajes(lst);
			return aplicacionesmast;
		}
		
		// transaccion
	    aplicacionesmast.setEstado("I");
	    aplicacionesmast = aplicacionesmastDao.coreActualizar(aplicacionesmast);
		aplicacionesmast.setTransaccionEstado(DominioTransaccion.OK);
		aplicacionesmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return aplicacionesmast;
	}

	public BeanAplicacionesmast coreAnular(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmastPk pk) throws UException {
		BeanAplicacionesmast bean = aplicacionesmastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanAplicacionesmast coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo) throws UException {
		return coreAnular(usuarioActual,new BeanAplicacionesmastPk( paplicacioncodigo));
	}

	@Transactional
	public DtoComunAplicacionesmast coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunAplicacionesmast dto) throws UException {
		BeanAplicacionesmast aplicacionesmast = dto.obtenerBean();
		aplicacionesmast = coreEliminar(usuarioActual, aplicacionesmast);
		dto.setTransaccionEstado(aplicacionesmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(aplicacionesmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAplicacionesmast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmast aplicacionesmast) throws UException {
		// valores por defecto - preparando objeto
		aplicacionesmast = validar.prepararEliminar(usuarioActual, aplicacionesmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, aplicacionesmast);
		if (!lst.isEmpty()) {
			aplicacionesmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			aplicacionesmast.setTransaccionListaMensajes(lst);
			return aplicacionesmast;
		}
		
		// transaccion
		aplicacionesmastDao.eliminar(aplicacionesmast);
		aplicacionesmast=new BeanAplicacionesmast();
		aplicacionesmast.setTransaccionEstado(DominioTransaccion.OK);
		return aplicacionesmast;
	}

	public BeanAplicacionesmast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmastPk pk) throws UException {
		BeanAplicacionesmast aplicacionesmast = aplicacionesmastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,aplicacionesmast);
	}

	public BeanAplicacionesmast coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo) throws UException {
		return coreEliminar(usuarioActual,new BeanAplicacionesmastPk( paplicacioncodigo));
	}

}
