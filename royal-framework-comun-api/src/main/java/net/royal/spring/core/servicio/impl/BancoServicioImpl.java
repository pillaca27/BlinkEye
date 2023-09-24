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
import net.royal.spring.core.dao.impl.BancoDaoImpl;

import net.royal.spring.core.dominio.BeanBanco;
import net.royal.spring.core.dominio.BeanBancoPk;
import net.royal.spring.core.dominio.dto.DtoComunBanco;
import net.royal.spring.core.servicio.validar.BancoServicioValidar;

@Service (value = "BeanServicioBanco")
public class BancoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioBanco";
	private static Logger logger = LogManager.getLogger(BancoServicioImpl.class);

	@Autowired
	private BancoDaoImpl bancoDao;

	@Autowired
	private BancoServicioValidar validar;

	@Transactional
	public DtoComunBanco coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunBanco dto) throws UException {
		BeanBanco banco = dto.obtenerBean();
		banco = coreInsertar(usuarioActual, banco);
		dto.setTransaccionEstado(banco.getTransaccionEstado());
		dto.setTransaccionListaMensajes(banco.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanBanco coreInsertar(SeguridadUsuarioActual usuarioActual,BeanBanco banco) throws UException {
		// valores por defecto - preparando objeto
		banco = validar.prepararInsertar(usuarioActual, banco);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, banco);
		if (!lst.isEmpty()) {
			banco.setTransaccionEstado(DominioTransaccion.VALIDACION);
			banco.setTransaccionListaMensajes(lst);
			return banco;
		}
		
		// transaccion
		banco = bancoDao.coreInsertar(banco);
		banco.setTransaccionEstado(DominioTransaccion.OK);
		banco.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return banco;
	}

	@Transactional
	public DtoComunBanco coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunBanco dto) throws UException {
		BeanBanco banco = dto.obtenerBean();
		banco = coreActualizar(usuarioActual, banco);
		dto.setTransaccionEstado(banco.getTransaccionEstado());
		dto.setTransaccionListaMensajes(banco.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanBanco coreActualizar(SeguridadUsuarioActual usuarioActual, BeanBanco banco) throws UException {
		// valores por defecto - preparando objeto
		banco = validar.prepararActualizar(usuarioActual, banco);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, banco);
		if (!lst.isEmpty()) {
			banco.setTransaccionEstado(DominioTransaccion.VALIDACION);
			banco.setTransaccionListaMensajes(lst);
			return banco;
		}
		
		// transaccion
	    banco = bancoDao.coreActualizar(banco);
		banco.setTransaccionEstado(DominioTransaccion.OK);
		banco.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return banco;
	}


	@Transactional
	public DtoComunBanco coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunBanco dto) throws UException {
		BeanBanco banco = dto.obtenerBean();
		banco = coreAnular(usuarioActual, banco);
		dto.setTransaccionEstado(banco.getTransaccionEstado());
		dto.setTransaccionListaMensajes(banco.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanBanco coreAnular(SeguridadUsuarioActual usuarioActual, BeanBanco banco) throws UException {
		// valores por defecto - preparando objeto
		banco = validar.prepararAnular(usuarioActual, banco);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, banco);
		if (!lst.isEmpty()) {
			banco.setTransaccionEstado(DominioTransaccion.VALIDACION);
			banco.setTransaccionListaMensajes(lst);
			return banco;
		}
		
		// transaccion
	    banco.setEstado("I");
	    banco = bancoDao.coreActualizar(banco);
		banco.setTransaccionEstado(DominioTransaccion.OK);
		banco.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return banco;
	}

	public BeanBanco coreAnular(SeguridadUsuarioActual usuarioActual, BeanBancoPk pk) throws UException {
		BeanBanco bean = bancoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanBanco coreAnular(SeguridadUsuarioActual usuarioActual, String pbanco) throws UException {
		return coreAnular(usuarioActual,new BeanBancoPk( pbanco));
	}

	@Transactional
	public DtoComunBanco coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunBanco dto) throws UException {
		BeanBanco banco = dto.obtenerBean();
		banco = coreEliminar(usuarioActual, banco);
		dto.setTransaccionEstado(banco.getTransaccionEstado());
		dto.setTransaccionListaMensajes(banco.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanBanco coreEliminar(SeguridadUsuarioActual usuarioActual, BeanBanco banco) throws UException {
		// valores por defecto - preparando objeto
		banco = validar.prepararEliminar(usuarioActual, banco);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, banco);
		if (!lst.isEmpty()) {
			banco.setTransaccionEstado(DominioTransaccion.VALIDACION);
			banco.setTransaccionListaMensajes(lst);
			return banco;
		}
		
		// transaccion
		bancoDao.eliminar(banco);
		banco=new BeanBanco();
		banco.setTransaccionEstado(DominioTransaccion.OK);
		return banco;
	}

	public BeanBanco coreEliminar(SeguridadUsuarioActual usuarioActual, BeanBancoPk pk) throws UException {
		BeanBanco banco = bancoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,banco);
	}

	public BeanBanco coreEliminar(SeguridadUsuarioActual usuarioActual, String pbanco) throws UException {
		return coreEliminar(usuarioActual,new BeanBancoPk( pbanco));
	}

}
