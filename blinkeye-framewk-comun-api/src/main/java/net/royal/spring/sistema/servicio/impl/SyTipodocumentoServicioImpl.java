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
import net.royal.spring.sistema.dao.impl.SyTipodocumentoDaoImpl;

import net.royal.spring.sistema.dominio.BeanSyTipodocumento;
import net.royal.spring.sistema.dominio.BeanSyTipodocumentoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyTipodocumento;
import net.royal.spring.sistema.servicio.validar.SyTipodocumentoServicioValidar;

@Service(value = "BeanServicioSyTipodocumento")
public class SyTipodocumentoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSyTipodocumento";
	private static Logger logger = LogManager.getLogger(SyTipodocumentoServicioImpl.class);

	@Autowired
	private SyTipodocumentoDaoImpl syTipodocumentoDao;

	@Autowired
	private SyTipodocumentoServicioValidar validar;

	@Transactional
	public DtoComunSyTipodocumento coreActualizar(SeguridadUsuarioActual usuarioActual, DtoComunSyTipodocumento dto)
			throws UException {
		BeanSyTipodocumento sytipodocumento = dto.obtenerBean();
		sytipodocumento = coreActualizar(usuarioActual, sytipodocumento);
		dto.setTransaccionEstado(sytipodocumento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sytipodocumento.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunSyTipodocumento coreInsertar(SeguridadUsuarioActual usuarioActual, DtoComunSyTipodocumento dto)
			throws UException {
		BeanSyTipodocumento sytipodocumento = dto.obtenerBean();
		sytipodocumento = coreInsertar(usuarioActual, sytipodocumento);
		dto.setTransaccionEstado(sytipodocumento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sytipodocumento.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunSyTipodocumento coreAnular(SeguridadUsuarioActual usuarioActual, DtoComunSyTipodocumento dto)
			throws UException {
		BeanSyTipodocumento sytipodocumento = dto.obtenerBean();
		sytipodocumento = coreAnular(usuarioActual, sytipodocumento);
		dto.setTransaccionEstado(sytipodocumento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sytipodocumento.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunSyTipodocumento coreEliminar(SeguridadUsuarioActual usuarioActual, DtoComunSyTipodocumento dto)
			throws UException {
		BeanSyTipodocumento sytipodocumento = dto.obtenerBean();
		coreEliminar(usuarioActual, sytipodocumento);
		dto.setTransaccionEstado(sytipodocumento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sytipodocumento.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyTipodocumento coreInsertar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumento syTipodocumento)
			throws UException {
		// valores por defecto - preparando objeto
		syTipodocumento = validar.prepararInsertar(usuarioActual, syTipodocumento);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, syTipodocumento);
		if (!lst.isEmpty()) {
			syTipodocumento.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syTipodocumento.setTransaccionListaMensajes(lst);
			return syTipodocumento;
		}

		// transaccion
		syTipodocumento = syTipodocumentoDao.coreInsertar(syTipodocumento);
		syTipodocumento.setTransaccionEstado(DominioTransaccion.OK);
		syTipodocumento.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syTipodocumento;
	}

	@Transactional
	public BeanSyTipodocumento coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumento syTipodocumento)
			throws UException {
		// valores por defecto - preparando objeto
		syTipodocumento = validar.prepararActualizar(usuarioActual, syTipodocumento);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, syTipodocumento);
		if (!lst.isEmpty()) {
			syTipodocumento.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syTipodocumento.setTransaccionListaMensajes(lst);
			return syTipodocumento;
		}

		// transaccion
		syTipodocumento = syTipodocumentoDao.coreActualizar(syTipodocumento);
		syTipodocumento.setTransaccionEstado(DominioTransaccion.OK);
		syTipodocumento.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syTipodocumento;

	}

	@Transactional
	public BeanSyTipodocumento coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumento syTipodocumento)
			throws UException {
		// valores por defecto - preparando objeto
		syTipodocumento = validar.prepararAnular(usuarioActual, syTipodocumento);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, syTipodocumento);
		if (!lst.isEmpty()) {
			syTipodocumento.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syTipodocumento.setTransaccionListaMensajes(lst);
			return syTipodocumento;
		}

		// transaccion
		syTipodocumento.setEstado("I");
		syTipodocumento = syTipodocumentoDao.coreActualizar(syTipodocumento);
		syTipodocumento.setTransaccionEstado(DominioTransaccion.OK);
		syTipodocumento.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syTipodocumento;
	}

	public BeanSyTipodocumento coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumentoPk pk) throws UException {
		BeanSyTipodocumento bean = syTipodocumentoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public BeanSyTipodocumento coreAnular(SeguridadUsuarioActual usuarioActual, String ptipodocumentoid) throws UException {
		return coreAnular(usuarioActual, new BeanSyTipodocumentoPk(ptipodocumentoid));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumento syTipodocumento) throws UException {
		// valores por defecto - preparando objeto
		syTipodocumento = validar.prepararEliminar(usuarioActual, syTipodocumento);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, syTipodocumento);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		syTipodocumentoDao.eliminar(syTipodocumento);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumentoPk pk) throws UException {
		BeanSyTipodocumento syTipodocumento = syTipodocumentoDao.obtenerPorId(pk);
		coreEliminar(usuarioActual, syTipodocumento);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String ptipodocumentoid) throws UException {
		coreEliminar(usuarioActual, new BeanSyTipodocumentoPk(ptipodocumentoid));
	}

}
