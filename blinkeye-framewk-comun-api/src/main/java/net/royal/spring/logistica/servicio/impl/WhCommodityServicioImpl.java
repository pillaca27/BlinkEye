package net.royal.spring.logistica.servicio.impl;

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
import net.royal.spring.logistica.dao.impl.WhCommodityDaoImpl;

import net.royal.spring.logistica.dominio.BeanWhCommodity;
import net.royal.spring.logistica.dominio.BeanWhCommodityPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCommodity;
import net.royal.spring.logistica.servicio.validar.WhCommodityServicioValidar;

@Service (value = "BeanServicioWhCommodity")
public class WhCommodityServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioWhCommodity";
	private static Logger logger = LogManager.getLogger(WhCommodityServicioImpl.class);

	@Autowired
	private WhCommodityDaoImpl whCommodityDao;

	@Autowired
	private WhCommodityServicioValidar validar;

	@Transactional
	public DtoComunWhCommodity coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunWhCommodity dto) throws Exception {
		BeanWhCommodity whCommodity = dto.obtenerBean();
		whCommodity = coreInsertar(usuarioActual, whCommodity);
		dto.setTransaccionEstado(whCommodity.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whCommodity.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhCommodity coreInsertar(SeguridadUsuarioActual usuarioActual,BeanWhCommodity whCommodity) throws Exception {
		// valores por defecto - preparando objeto
		whCommodity = validar.prepararInsertar(usuarioActual, whCommodity);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, whCommodity);
		if (!lst.isEmpty()) {
			whCommodity.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whCommodity.setTransaccionListaMensajes(lst);
			return whCommodity;
		}
		
		// transaccion
		whCommodity = whCommodityDao.coreInsertar(whCommodity);
		whCommodity.setTransaccionEstado(DominioTransaccion.OK);
		whCommodity.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whCommodity;
	}

	@Transactional
	public DtoComunWhCommodity coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunWhCommodity dto) throws Exception {
		BeanWhCommodity whCommodity = dto.obtenerBean();
		whCommodity = coreActualizar(usuarioActual, whCommodity);
		dto.setTransaccionEstado(whCommodity.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whCommodity.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhCommodity coreActualizar(SeguridadUsuarioActual usuarioActual, BeanWhCommodity whCommodity) throws Exception {
		// valores por defecto - preparando objeto
		whCommodity = validar.prepararActualizar(usuarioActual, whCommodity);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, whCommodity);
		if (!lst.isEmpty()) {
			whCommodity.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whCommodity.setTransaccionListaMensajes(lst);
			return whCommodity;
		}
		
		// transaccion
	    whCommodity = whCommodityDao.coreActualizar(whCommodity);
		whCommodity.setTransaccionEstado(DominioTransaccion.OK);
		whCommodity.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whCommodity;
	}


	@Transactional
	public DtoComunWhCommodity coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunWhCommodity dto) throws Exception {
		BeanWhCommodity whCommodity = dto.obtenerBean();
		whCommodity = coreAnular(usuarioActual, whCommodity);
		dto.setTransaccionEstado(whCommodity.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whCommodity.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhCommodity coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhCommodity whCommodity) throws Exception {
		// valores por defecto - preparando objeto
		whCommodity = validar.prepararAnular(usuarioActual, whCommodity);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, whCommodity);
		if (!lst.isEmpty()) {
			whCommodity.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whCommodity.setTransaccionListaMensajes(lst);
			return whCommodity;
		}
		
		// transaccion
	    whCommodity.setEstado("I");
	    whCommodity = whCommodityDao.coreActualizar(whCommodity);
		whCommodity.setTransaccionEstado(DominioTransaccion.OK);
		whCommodity.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whCommodity;
	}

	public BeanWhCommodity coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhCommodityPk pk) throws Exception {
		BeanWhCommodity bean = whCommodityDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanWhCommodity coreAnular(SeguridadUsuarioActual usuarioActual, String pcommodity01) throws Exception {
		return coreAnular(usuarioActual,new BeanWhCommodityPk( pcommodity01));
	}

	@Transactional
	public DtoComunWhCommodity coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunWhCommodity dto) throws Exception {
		BeanWhCommodity whCommodity = dto.obtenerBean();
		whCommodity = coreEliminar(usuarioActual, whCommodity);
		dto.setTransaccionEstado(whCommodity.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whCommodity.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhCommodity coreEliminar(SeguridadUsuarioActual usuarioActual, BeanWhCommodity whCommodity) throws Exception {
		// valores por defecto - preparando objeto
		whCommodity = validar.prepararEliminar(usuarioActual, whCommodity);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, whCommodity);
		if (!lst.isEmpty()) {
			whCommodity.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whCommodity.setTransaccionListaMensajes(lst);
			return whCommodity;
		}
		
		// transaccion
		whCommodityDao.eliminar(whCommodity);
		whCommodity=new BeanWhCommodity();
		whCommodity.setTransaccionEstado(DominioTransaccion.OK);
		return whCommodity;
	}

	public BeanWhCommodity coreEliminar(SeguridadUsuarioActual usuarioActual, BeanWhCommodityPk pk) throws Exception {
		BeanWhCommodity whCommodity = whCommodityDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,whCommodity);
	}

	public BeanWhCommodity coreEliminar(SeguridadUsuarioActual usuarioActual, String pcommodity01) throws Exception {
		return coreEliminar(usuarioActual,new BeanWhCommodityPk( pcommodity01));
	}

}
