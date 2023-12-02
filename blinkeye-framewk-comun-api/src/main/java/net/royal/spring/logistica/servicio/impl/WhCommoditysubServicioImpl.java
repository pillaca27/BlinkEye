package net.royal.spring.logistica.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.logistica.dao.impl.WhCommodityDaoImpl;
import net.royal.spring.logistica.dao.impl.WhCommoditysubDaoImpl;
import net.royal.spring.logistica.dominio.BeanWhCommodity;
import net.royal.spring.logistica.dominio.BeanWhCommoditysub;
import net.royal.spring.logistica.dominio.BeanWhCommoditysubPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCommodity;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCommoditysub;
import net.royal.spring.logistica.servicio.validar.WhCommoditysubServicioValidar;

@Service (value = "BeanServicioWhCommoditysub")
public class WhCommoditysubServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioWhCommoditysub";
	private static Logger logger = LogManager.getLogger(WhCommoditysubServicioImpl.class);

	@Autowired
	private WhCommoditysubDaoImpl whCommoditysubDao;
	
	@Autowired
	private WhCommodityDaoImpl whCommodityDao;

	@Autowired
	private WhCommoditysubServicioValidar validar;
	
	@Transactional
	public DtoComunWhCommodity coreCopiarCommodity(SeguridadUsuarioActual usuarioActual,DtoComunWhCommodity whCommoditysub) throws UException {
		// valores por defecto - preparando objeto
		whCommoditysub = validar.prepararInsertarCommodity(usuarioActual, whCommoditysub);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertarCommunity(usuarioActual, whCommoditysub);
		if (!lst.isEmpty()) {
			whCommoditysub.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whCommoditysub.setTransaccionListaMensajes(lst);
			return whCommoditysub;
		}
		
		// transaccion
		whCommoditysub.setUltimafechamodif(new Date());
		whCommoditysub.setUltimousuario(usuarioActual.getUsuario());
		whCommoditysub.setDescripcionlocal(whCommoditysub.getDescripcionlocal().toUpperCase());
		whCommoditysub.obtenerBean(whCommodityDao.coreInsertar(whCommoditysub.obtenerBean()));
		if(whCommoditysub.getLstCommodityDetalle().size() >0) {
			for (DtoComunWhCommoditysub detalle : whCommoditysub.getLstCommodityDetalle()) {
				    detalle.setCommodity01(whCommoditysub.getCommodity01());
					detalle.setCommodity(whCommoditysub.getCommodity01().concat(detalle.getCommodity02()));
					
					detalle.setDescripcionlocal(detalle.getDescripcionlocal().toUpperCase());
					this.coreInsertar(usuarioActual,detalle);
			}
		}
		
		whCommoditysub.setTransaccionEstado(DominioTransaccion.OK);
		whCommoditysub.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whCommoditysub;
	}

	@Transactional
	public DtoComunWhCommodity coreInsertarCommodity(SeguridadUsuarioActual usuarioActual,DtoComunWhCommodity whCommoditysub) throws UException {
		// valores por defecto - preparando objeto
		whCommoditysub = validar.prepararInsertarCommodity(usuarioActual, whCommoditysub);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertarCommunity(usuarioActual, whCommoditysub);
		if (!lst.isEmpty()) {
			whCommoditysub.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whCommoditysub.setTransaccionListaMensajes(lst);
			return whCommoditysub;
		}
		
		// transaccion
		whCommoditysub.setUltimafechamodif(new Date());
		whCommoditysub.setUltimousuario(usuarioActual.getUsuario());
		whCommoditysub.setDescripcionlocal(whCommoditysub.getDescripcionlocal().toUpperCase());
		whCommoditysub.obtenerBean(whCommodityDao.coreInsertar(whCommoditysub.obtenerBean()));
		if(whCommoditysub.getLstCommodityDetalle().size() >0) {
			for (DtoComunWhCommoditysub detalle : whCommoditysub.getLstCommodityDetalle()) {
					detalle.setCommodity(whCommoditysub.getCommodity01().concat(detalle.getCommodity02()));
					detalle.setDescripcionlocal(detalle.getDescripcionlocal().toUpperCase());
					this.coreInsertar(usuarioActual,detalle);
			}
		}
		
		whCommoditysub.setTransaccionEstado(DominioTransaccion.OK);
		whCommoditysub.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whCommoditysub;
	}
	
	@Transactional
	public DtoComunWhCommodity coreActualizarCommodity(SeguridadUsuarioActual usuarioActual,DtoComunWhCommodity whCommoditysub) throws UException {
		// valores por defecto - preparando objeto
		whCommoditysub = validar.prepararActualizarCommodity(usuarioActual, whCommoditysub);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizarCommodity(usuarioActual, whCommoditysub);
		if (!lst.isEmpty()) {
			whCommoditysub.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whCommoditysub.setTransaccionListaMensajes(lst);
			return whCommoditysub;
		}
		
		// transaccion
		whCommoditysub.setUltimafechamodif(new Date());
		whCommoditysub.setUltimousuario(usuarioActual.getUsuario());
		whCommoditysub.setDescripcionlocal(whCommoditysub.getDescripcionlocal().toUpperCase());
		whCommoditysub.obtenerBean(whCommodityDao.coreActualizar(whCommoditysub.obtenerBean()));
		if(whCommoditysub.getLstCommodityDetalle().size() >0) {
			for (DtoComunWhCommoditysub detalle : whCommoditysub.getLstCommodityDetalle()) {
				
				if(detalle.getAccion().equals("N")) {
					detalle.setCommodity(detalle.getCommodity01().concat(detalle.getCommodity02()));
					detalle.setDescripcionlocal(detalle.getDescripcionlocal().toUpperCase());
					logger.debug("nuevo");
					this.coreInsertar(usuarioActual,detalle);
				}
				else if(detalle.getAccion().equals("A")) {
					detalle.setCommodity(detalle.getCommodity01().concat(detalle.getCommodity02()));
					detalle.setDescripcionlocal(detalle.getDescripcionlocal().toUpperCase());
					logger.debug("update");
					this.coreActualizar(usuarioActual, detalle);
				}
				else if(detalle.getAccion().equals("E")) {
					logger.debug("delete");
					this.coreEliminar(usuarioActual, detalle);
				}
			}
		}
		
		
		whCommoditysub.setTransaccionEstado(DominioTransaccion.OK);
		whCommoditysub.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whCommoditysub;
	}
	
	@Transactional
	public DtoComunWhCommoditysub coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunWhCommoditysub dto) throws UException {
		BeanWhCommoditysub whCommoditysub = dto.obtenerBean();
		whCommoditysub = coreInsertar(usuarioActual, whCommoditysub);
		dto.setTransaccionEstado(whCommoditysub.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whCommoditysub.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhCommoditysub coreInsertar(SeguridadUsuarioActual usuarioActual,BeanWhCommoditysub whCommoditysub) throws UException {
		// valores por defecto - preparando objeto
		whCommoditysub = validar.prepararInsertar(usuarioActual, whCommoditysub);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, whCommoditysub);
		if (!lst.isEmpty()) {
			whCommoditysub.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whCommoditysub.setTransaccionListaMensajes(lst);
			return whCommoditysub;
		}
		
		// transaccion
		whCommoditysub = whCommoditysubDao.coreInsertar(whCommoditysub);
		whCommoditysub.setTransaccionEstado(DominioTransaccion.OK);
		whCommoditysub.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whCommoditysub;
	}

	@Transactional
	public DtoComunWhCommoditysub coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunWhCommoditysub dto) throws UException {
		BeanWhCommoditysub whCommoditysub = dto.obtenerBean();
		whCommoditysub = coreActualizar(usuarioActual, whCommoditysub);
		dto.setTransaccionEstado(whCommoditysub.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whCommoditysub.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhCommoditysub coreActualizar(SeguridadUsuarioActual usuarioActual, BeanWhCommoditysub whCommoditysub) throws UException {
		// valores por defecto - preparando objeto
		whCommoditysub = validar.prepararActualizar(usuarioActual, whCommoditysub);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, whCommoditysub);
		if (!lst.isEmpty()) {
			whCommoditysub.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whCommoditysub.setTransaccionListaMensajes(lst);
			return whCommoditysub;
		}
		
		// transaccion
	    whCommoditysub = whCommoditysubDao.coreActualizar(whCommoditysub);
		whCommoditysub.setTransaccionEstado(DominioTransaccion.OK);
		whCommoditysub.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whCommoditysub;
	}


	@Transactional
	public DtoComunWhCommoditysub coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunWhCommoditysub dto) throws UException {
		BeanWhCommoditysub whCommoditysub = dto.obtenerBean();
		whCommoditysub = coreAnular(usuarioActual, whCommoditysub);
		dto.setTransaccionEstado(whCommoditysub.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whCommoditysub.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhCommoditysub coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhCommoditysub whCommoditysub) throws UException {
		// valores por defecto - preparando objeto
		whCommoditysub = validar.prepararAnular(usuarioActual, whCommoditysub);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, whCommoditysub);
		if (!lst.isEmpty()) {
			whCommoditysub.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whCommoditysub.setTransaccionListaMensajes(lst);
			return whCommoditysub;
		}
		
		// transaccion
	    whCommoditysub.setEstado("I");
	    whCommoditysub = whCommoditysubDao.coreActualizar(whCommoditysub);
		whCommoditysub.setTransaccionEstado(DominioTransaccion.OK);
		whCommoditysub.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whCommoditysub;
	}

	public BeanWhCommoditysub coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhCommoditysubPk pk) throws UException {
		BeanWhCommoditysub bean = whCommoditysubDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanWhCommoditysub coreAnular(SeguridadUsuarioActual usuarioActual, String pcommodity01,String pcommodity02) throws UException {
		return coreAnular(usuarioActual,new BeanWhCommoditysubPk( pcommodity01, pcommodity02));
	}

	@Transactional
	public DtoComunWhCommodity coreEliminarCommodity(SeguridadUsuarioActual usuarioActual,DtoComunWhCommodity dto) throws UException {

		List<DominioParametroPersistencia> parametrosDetalle = new ArrayList<DominioParametroPersistencia>();
		parametrosDetalle.add(new DominioParametroPersistencia("p_commodity01", String.class, dto.getCommodity01()));
		List lstDetalle = whCommodityDao.listarPorQuery(DtoComunWhCommoditysub.class, "whcommodity.obtenerDetalleDto", parametrosDetalle);
		dto.setLstCommodityDetalle(lstDetalle);
		
		if(lstDetalle.size()>0) {
			for (DtoComunWhCommoditysub detalle : dto.getLstCommodityDetalle()) {
				BeanWhCommoditysub whCommoditysub = detalle.obtenerBean();
				whCommoditysub = coreEliminar(usuarioActual, whCommoditysub);
				detalle.setTransaccionEstado(whCommoditysub.getTransaccionEstado());
				detalle.setTransaccionListaMensajes(whCommoditysub.getTransaccionListaMensajes());
			}
		}

		BeanWhCommodity whCommodity = dto.obtenerBean();
		whCommodity = coreEliminarCabecera(usuarioActual, whCommodity);
		dto.obtenerBean().setTransaccionEstado(whCommodity.getTransaccionEstado());
		dto.obtenerBean().setTransaccionListaMensajes(whCommodity.getTransaccionListaMensajes());
		
		return dto;
	}
	
	@Transactional
	public DtoComunWhCommoditysub coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunWhCommoditysub dto) throws UException {
		BeanWhCommoditysub whCommoditysub = dto.obtenerBean();
		whCommoditysub = coreEliminar(usuarioActual, whCommoditysub);
		dto.setTransaccionEstado(whCommoditysub.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whCommoditysub.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhCommodity coreEliminarCabecera(SeguridadUsuarioActual usuarioActual, BeanWhCommodity whCommoditysub) throws UException {
		// valores por defecto - preparando objeto
		whCommoditysub = validar.prepararEliminarCabecera(usuarioActual, whCommoditysub);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminarCabecera(usuarioActual, whCommoditysub);
		if (!lst.isEmpty()) {
			whCommoditysub.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whCommoditysub.setTransaccionListaMensajes(lst);
			return whCommoditysub;
		}
		
		// transaccion
		whCommodityDao.eliminar(whCommoditysub);
		whCommoditysub=new BeanWhCommodity();
		whCommoditysub.setTransaccionEstado(DominioTransaccion.OK);
		return whCommoditysub;
	}
	
	@Transactional
	public BeanWhCommoditysub coreEliminar(SeguridadUsuarioActual usuarioActual, BeanWhCommoditysub whCommoditysub) throws UException {
		// valores por defecto - preparando objeto
		logger.debug("preparar " +whCommoditysub.getCommodity());
		whCommoditysub = validar.prepararEliminar(usuarioActual, whCommoditysub);
		
		// validaciones de negocio
		
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, whCommoditysub);
		if (!lst.isEmpty()) {
			whCommoditysub.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whCommoditysub.setTransaccionListaMensajes(lst);
			return whCommoditysub;
		}
		
		logger.debug("eliminarCommodity " +whCommoditysub.getCommodity());
		// transaccion
		whCommoditysubDao.eliminar(whCommoditysub);
		whCommoditysub=new BeanWhCommoditysub();
		whCommoditysub.setTransaccionEstado(DominioTransaccion.OK);
		return whCommoditysub;
	}

	public BeanWhCommoditysub coreEliminar(SeguridadUsuarioActual usuarioActual, BeanWhCommoditysubPk pk) throws UException {
		BeanWhCommoditysub whCommoditysub = whCommoditysubDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,whCommoditysub);
	}

	public BeanWhCommoditysub coreEliminar(SeguridadUsuarioActual usuarioActual, String pcommodity01,String pcommodity02) throws UException {
		return coreEliminar(usuarioActual,new BeanWhCommoditysubPk( pcommodity01, pcommodity02));
	}

}
