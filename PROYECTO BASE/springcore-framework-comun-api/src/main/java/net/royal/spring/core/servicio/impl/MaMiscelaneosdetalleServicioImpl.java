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
import net.royal.spring.core.dao.impl.MaMiscelaneosdetalleDaoImpl;

import net.royal.spring.core.dominio.BeanMaMiscelaneosdetalle;
import net.royal.spring.core.dominio.BeanMaMiscelaneosdetallePk;
import net.royal.spring.core.dominio.BeanParametrosmast;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosdetalle;
import net.royal.spring.core.dominio.dto.DtoComunParametrosmast;
import net.royal.spring.core.servicio.validar.MaMiscelaneosdetalleServicioValidar;

@Service (value = "BeanServicioMaMiscelaneosdetalle")
public class MaMiscelaneosdetalleServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioMaMiscelaneosdetalle";
	private static Logger logger = LogManager.getLogger(MaMiscelaneosdetalleServicioImpl.class);

	@Autowired
	private MaMiscelaneosdetalleDaoImpl maMiscelaneosdetalleDao;

	@Autowired
	private MaMiscelaneosdetalleServicioValidar validar;

	@Transactional
	public DtoComunMaMiscelaneosdetalle coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunMaMiscelaneosdetalle dto) throws UException {
		BeanMaMiscelaneosdetalle maMiscelaneosdetalle = dto.obtenerBean();
		maMiscelaneosdetalle = coreInsertar(usuarioActual, maMiscelaneosdetalle);
		dto.setTransaccionEstado(maMiscelaneosdetalle.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maMiscelaneosdetalle.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaMiscelaneosdetalle coreInsertar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosdetalle maMiscelaneosdetalle) throws UException {
		// valores por defecto - preparando objeto
		maMiscelaneosdetalle = validar.prepararInsertar(usuarioActual, maMiscelaneosdetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, maMiscelaneosdetalle);
		if (!lst.isEmpty()) {
			maMiscelaneosdetalle.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maMiscelaneosdetalle.setTransaccionListaMensajes(lst);
			return maMiscelaneosdetalle;
		}
		
		// transaccion
		maMiscelaneosdetalle = maMiscelaneosdetalleDao.coreInsertar(maMiscelaneosdetalle);
		maMiscelaneosdetalle.setTransaccionEstado(DominioTransaccion.OK);
		maMiscelaneosdetalle.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maMiscelaneosdetalle;
	}

	@Transactional
	public DtoComunMaMiscelaneosdetalle coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunMaMiscelaneosdetalle dto) throws UException {
		BeanMaMiscelaneosdetalle maMiscelaneosdetalle = dto.obtenerBean();
		maMiscelaneosdetalle = coreActualizar(usuarioActual, maMiscelaneosdetalle);
		dto.setTransaccionEstado(maMiscelaneosdetalle.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maMiscelaneosdetalle.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaMiscelaneosdetalle coreActualizar(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosdetalle maMiscelaneosdetalle) throws UException {
		// valores por defecto - preparando objeto
		maMiscelaneosdetalle = validar.prepararActualizar(usuarioActual, maMiscelaneosdetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, maMiscelaneosdetalle);
		if (!lst.isEmpty()) {
			maMiscelaneosdetalle.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maMiscelaneosdetalle.setTransaccionListaMensajes(lst);
			return maMiscelaneosdetalle;
		}
		
		// transaccion
	    maMiscelaneosdetalle = maMiscelaneosdetalleDao.coreActualizar(maMiscelaneosdetalle);
		maMiscelaneosdetalle.setTransaccionEstado(DominioTransaccion.OK);
		maMiscelaneosdetalle.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maMiscelaneosdetalle;
	}


	@Transactional
	public DtoComunMaMiscelaneosdetalle coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunMaMiscelaneosdetalle dto) throws UException {
		BeanMaMiscelaneosdetalle maMiscelaneosdetalle = dto.obtenerBean();
		maMiscelaneosdetalle = coreAnular(usuarioActual, maMiscelaneosdetalle);
		dto.setTransaccionEstado(maMiscelaneosdetalle.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maMiscelaneosdetalle.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaMiscelaneosdetalle coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosdetalle maMiscelaneosdetalle) throws UException {
		// valores por defecto - preparando objeto
		maMiscelaneosdetalle = validar.prepararAnular(usuarioActual, maMiscelaneosdetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, maMiscelaneosdetalle);
		if (!lst.isEmpty()) {
			maMiscelaneosdetalle.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maMiscelaneosdetalle.setTransaccionListaMensajes(lst);
			return maMiscelaneosdetalle;
		}
		
		// transaccion
	    maMiscelaneosdetalle = maMiscelaneosdetalleDao.coreActualizar(maMiscelaneosdetalle);
		maMiscelaneosdetalle.setTransaccionEstado(DominioTransaccion.OK);
		maMiscelaneosdetalle.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maMiscelaneosdetalle;
	}

	public BeanMaMiscelaneosdetalle coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosdetallePk pk) throws UException {
		BeanMaMiscelaneosdetalle bean = maMiscelaneosdetalleDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanMaMiscelaneosdetalle coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pcodigotabla,String pcompania,String pcodigoelemento) throws UException {
		return coreAnular(usuarioActual,new BeanMaMiscelaneosdetallePk( paplicacioncodigo, pcodigotabla, pcompania, pcodigoelemento));
	}

	@Transactional
	public DtoComunMaMiscelaneosdetalle coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunMaMiscelaneosdetalle dto) throws UException {
		BeanMaMiscelaneosdetalle maMiscelaneosdetalle = dto.obtenerBean();
		maMiscelaneosdetalle = coreEliminar(usuarioActual, maMiscelaneosdetalle);
		dto.setTransaccionEstado(maMiscelaneosdetalle.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maMiscelaneosdetalle.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaMiscelaneosdetalle coreEliminar(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosdetalle maMiscelaneosdetalle) throws UException {
		// valores por defecto - preparando objeto
		maMiscelaneosdetalle = validar.prepararEliminar(usuarioActual, maMiscelaneosdetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, maMiscelaneosdetalle);
		if (!lst.isEmpty()) {
			maMiscelaneosdetalle.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maMiscelaneosdetalle.setTransaccionListaMensajes(lst);
			return maMiscelaneosdetalle;
		}
		
		// transaccion
		maMiscelaneosdetalleDao.eliminar(maMiscelaneosdetalle);
		maMiscelaneosdetalle=new BeanMaMiscelaneosdetalle();
		maMiscelaneosdetalle.setTransaccionEstado(DominioTransaccion.OK);
		return maMiscelaneosdetalle;
	}

	public BeanMaMiscelaneosdetalle coreEliminar(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosdetallePk pk) throws UException {
		BeanMaMiscelaneosdetalle maMiscelaneosdetalle = maMiscelaneosdetalleDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,maMiscelaneosdetalle);
	}

	public BeanMaMiscelaneosdetalle coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pcodigotabla,String pcompania,String pcodigoelemento) throws UException {
		return coreEliminar(usuarioActual,new BeanMaMiscelaneosdetallePk( paplicacioncodigo, pcodigotabla, pcompania, pcodigoelemento));
	}

	
	
	
}
