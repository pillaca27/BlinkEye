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
import net.royal.spring.core.dao.impl.MaMiscelaneosheaderDaoImpl;

import net.royal.spring.core.dominio.BeanMaMiscelaneosheader;
import net.royal.spring.core.dominio.BeanMaMiscelaneosheaderPk;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosheader;
import net.royal.spring.core.servicio.validar.MaMiscelaneosheaderServicioValidar;

@Service (value = "BeanServicioMaMiscelaneosheader")
public class MaMiscelaneosheaderServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioMaMiscelaneosheader";
	private static Logger logger = LogManager.getLogger(MaMiscelaneosheaderServicioImpl.class);

	@Autowired
	private MaMiscelaneosheaderDaoImpl maMiscelaneosheaderDao;

	@Autowired
	private MaMiscelaneosheaderServicioValidar validar;

	@Transactional
	public DtoComunMaMiscelaneosheader coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunMaMiscelaneosheader dto) throws UException {
		BeanMaMiscelaneosheader maMiscelaneosheader = dto.obtenerBean();
		maMiscelaneosheader = coreInsertar(usuarioActual, maMiscelaneosheader);
		dto.setTransaccionEstado(maMiscelaneosheader.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maMiscelaneosheader.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaMiscelaneosheader coreInsertar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosheader maMiscelaneosheader) throws UException {
		// valores por defecto - preparando objeto
		maMiscelaneosheader = validar.prepararInsertar(usuarioActual, maMiscelaneosheader);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, maMiscelaneosheader);
		if (!lst.isEmpty()) {
			maMiscelaneosheader.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maMiscelaneosheader.setTransaccionListaMensajes(lst);
			return maMiscelaneosheader;
		}
		
		// transaccion
		maMiscelaneosheader = maMiscelaneosheaderDao.coreInsertar(maMiscelaneosheader);
		maMiscelaneosheader.setTransaccionEstado(DominioTransaccion.OK);
		maMiscelaneosheader.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maMiscelaneosheader;
	}

	@Transactional
	public DtoComunMaMiscelaneosheader coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunMaMiscelaneosheader dto) throws UException {
		BeanMaMiscelaneosheader maMiscelaneosheader = dto.obtenerBean();
		maMiscelaneosheader = coreActualizar(usuarioActual, maMiscelaneosheader);
		dto.setTransaccionEstado(maMiscelaneosheader.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maMiscelaneosheader.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaMiscelaneosheader coreActualizar(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosheader maMiscelaneosheader) throws UException {
		// valores por defecto - preparando objeto
		maMiscelaneosheader = validar.prepararActualizar(usuarioActual, maMiscelaneosheader);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, maMiscelaneosheader);
		if (!lst.isEmpty()) {
			maMiscelaneosheader.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maMiscelaneosheader.setTransaccionListaMensajes(lst);
			return maMiscelaneosheader;
		}
		
		// transaccion
	    maMiscelaneosheader = maMiscelaneosheaderDao.coreActualizar(maMiscelaneosheader);
		maMiscelaneosheader.setTransaccionEstado(DominioTransaccion.OK);
		maMiscelaneosheader.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maMiscelaneosheader;
	}


	@Transactional
	public DtoComunMaMiscelaneosheader coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunMaMiscelaneosheader dto) throws UException {
		BeanMaMiscelaneosheader maMiscelaneosheader = dto.obtenerBean();
		maMiscelaneosheader = coreAnular(usuarioActual, maMiscelaneosheader);
		dto.setTransaccionEstado(maMiscelaneosheader.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maMiscelaneosheader.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaMiscelaneosheader coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosheader maMiscelaneosheader) throws UException {
		// valores por defecto - preparando objeto
		maMiscelaneosheader = validar.prepararAnular(usuarioActual, maMiscelaneosheader);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, maMiscelaneosheader);
		if (!lst.isEmpty()) {
			maMiscelaneosheader.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maMiscelaneosheader.setTransaccionListaMensajes(lst);
			return maMiscelaneosheader;
		}
		
		// transaccion
	    maMiscelaneosheader = maMiscelaneosheaderDao.coreActualizar(maMiscelaneosheader);
		maMiscelaneosheader.setTransaccionEstado(DominioTransaccion.OK);
		maMiscelaneosheader.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maMiscelaneosheader;
	}

	public BeanMaMiscelaneosheader coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosheaderPk pk) throws UException {
		BeanMaMiscelaneosheader bean = maMiscelaneosheaderDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanMaMiscelaneosheader coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pcodigotabla,String pcompania) throws UException {
		return coreAnular(usuarioActual,new BeanMaMiscelaneosheaderPk( paplicacioncodigo, pcodigotabla, pcompania));
	}

	@Transactional
	public DtoComunMaMiscelaneosheader coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunMaMiscelaneosheader dto) throws UException {
		BeanMaMiscelaneosheader maMiscelaneosheader = dto.obtenerBean();
		maMiscelaneosheader = coreEliminar(usuarioActual, maMiscelaneosheader);
		dto.setTransaccionEstado(maMiscelaneosheader.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maMiscelaneosheader.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaMiscelaneosheader coreEliminar(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosheader maMiscelaneosheader) throws UException {
		// valores por defecto - preparando objeto
		maMiscelaneosheader = validar.prepararEliminar(usuarioActual, maMiscelaneosheader);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, maMiscelaneosheader);
		if (!lst.isEmpty()) {
			maMiscelaneosheader.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maMiscelaneosheader.setTransaccionListaMensajes(lst);
			return maMiscelaneosheader;
		}
		
		// transaccion
		maMiscelaneosheaderDao.eliminar(maMiscelaneosheader);
		maMiscelaneosheader=new BeanMaMiscelaneosheader();
		maMiscelaneosheader.setTransaccionEstado(DominioTransaccion.OK);
		return maMiscelaneosheader;
	}

	public BeanMaMiscelaneosheader coreEliminar(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosheaderPk pk) throws UException {
		BeanMaMiscelaneosheader maMiscelaneosheader = maMiscelaneosheaderDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,maMiscelaneosheader);
	}

	public BeanMaMiscelaneosheader coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pcodigotabla,String pcompania) throws UException {
		return coreEliminar(usuarioActual,new BeanMaMiscelaneosheaderPk( paplicacioncodigo, pcodigotabla, pcompania));
	}

}
