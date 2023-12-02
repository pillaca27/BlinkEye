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
import net.royal.spring.core.dao.impl.TipocambiomastDaoImpl;

import net.royal.spring.core.dominio.BeanTipocambiomast;
import net.royal.spring.core.dominio.BeanTipocambiomastPk;
import net.royal.spring.core.dominio.dto.DtoComunTipocambiomast;
import net.royal.spring.core.servicio.validar.TipocambiomastServicioValidar;

@Service (value = "BeanServicioTipocambiomast")
public class TipocambiomastServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioTipocambiomast";
	private static Logger logger = LogManager.getLogger(TipocambiomastServicioImpl.class);

	@Autowired
	private TipocambiomastDaoImpl tipocambiomastDao;

	@Autowired
	private TipocambiomastServicioValidar validar;
	
	
	@Transactional
	public DtoComunTipocambiomast coreEliminarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunTipocambiomast dto) throws Exception {
		BeanTipocambiomast acSucursalgrupo = tipocambiomastDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = coreEliminar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}


	@Transactional
	public DtoComunTipocambiomast coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunTipocambiomast dto) throws Exception {
		BeanTipocambiomast acSucursalgrupo = tipocambiomastDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = dto.obtenerBeanActualizar(acSucursalgrupo);
		acSucursalgrupo = coreActualizar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	public DtoComunTipocambiomast  coreAnularPorUuid(SeguridadUsuarioActual usuarioActual, DtoComunTipocambiomast  dto)
			throws Exception {
		BeanTipocambiomast bean = tipocambiomastDao.obtenerPorUuid(dto.getUuid());
		bean = validar.prepararAuditoria(usuarioActual, bean);
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, bean);
		if (!lst.isEmpty()) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.setTransaccionListaMensajes(lst);
			return dto;
		}
		bean.setEstado("N");
		bean = tipocambiomastDao.coreActualizar(bean);
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	@Transactional
	public DtoComunTipocambiomast coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunTipocambiomast dto) throws UException {
		BeanTipocambiomast tipocambiomast = dto.obtenerBean();				
		tipocambiomast = coreInsertar(usuarioActual, tipocambiomast);
		dto.setTransaccionEstado(tipocambiomast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(tipocambiomast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanTipocambiomast coreInsertar(SeguridadUsuarioActual usuarioActual,BeanTipocambiomast tipocambiomast) throws UException {
		// valores por defecto - preparando objeto
		tipocambiomast = validar.prepararInsertar(usuarioActual, tipocambiomast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, tipocambiomast);
		if (!lst.isEmpty()) {
			tipocambiomast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			tipocambiomast.setTransaccionListaMensajes(lst);
			return tipocambiomast;
		}
		
		// transaccion
		tipocambiomast = tipocambiomastDao.coreInsertar(tipocambiomast);
		tipocambiomast.setTransaccionEstado(DominioTransaccion.OK);
		tipocambiomast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return tipocambiomast;
	}

	@Transactional
	public DtoComunTipocambiomast coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunTipocambiomast dto) throws UException {
		BeanTipocambiomast tipocambiomast = dto.obtenerBean();
		tipocambiomast = coreActualizar(usuarioActual, tipocambiomast);
		dto.setTransaccionEstado(tipocambiomast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(tipocambiomast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanTipocambiomast coreActualizar(SeguridadUsuarioActual usuarioActual, BeanTipocambiomast tipocambiomast) throws UException {
		// valores por defecto - preparando objeto
		tipocambiomast = validar.prepararActualizar(usuarioActual, tipocambiomast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, tipocambiomast);
		if (!lst.isEmpty()) {
			tipocambiomast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			tipocambiomast.setTransaccionListaMensajes(lst);
			return tipocambiomast;
		}
		
		// transaccion
	    tipocambiomast = tipocambiomastDao.coreActualizar(tipocambiomast);
		tipocambiomast.setTransaccionEstado(DominioTransaccion.OK);
		tipocambiomast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return tipocambiomast;
	}


	@Transactional
	public DtoComunTipocambiomast coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunTipocambiomast dto) throws UException {
		BeanTipocambiomast tipocambiomast = dto.obtenerBean();
		tipocambiomast = coreAnular(usuarioActual, tipocambiomast);
		dto.setTransaccionEstado(tipocambiomast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(tipocambiomast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanTipocambiomast coreAnular(SeguridadUsuarioActual usuarioActual, BeanTipocambiomast tipocambiomast) throws UException {
		// valores por defecto - preparando objeto
		tipocambiomast = validar.prepararAnular(usuarioActual, tipocambiomast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, tipocambiomast);
		if (!lst.isEmpty()) {
			tipocambiomast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			tipocambiomast.setTransaccionListaMensajes(lst);
			return tipocambiomast;
		}
		
		// transaccion
		tipocambiomast.setEstado("I");
	    tipocambiomast = tipocambiomastDao.coreActualizar(tipocambiomast);
		tipocambiomast.setTransaccionEstado(DominioTransaccion.OK);
		tipocambiomast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return tipocambiomast;
	}

	public BeanTipocambiomast coreAnular(SeguridadUsuarioActual usuarioActual, BeanTipocambiomastPk pk) throws UException {
		BeanTipocambiomast bean = tipocambiomastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanTipocambiomast coreAnular(SeguridadUsuarioActual usuarioActual, String pmonedacodigo,String pmonedacambiocodigo,java.util.Date pfechacambio) throws UException {
		return coreAnular(usuarioActual,new BeanTipocambiomastPk( pmonedacodigo, pmonedacambiocodigo, pfechacambio));
	}

	@Transactional
	public DtoComunTipocambiomast coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunTipocambiomast dto) throws UException {
		BeanTipocambiomast tipocambiomast = dto.obtenerBean();
		tipocambiomast = coreEliminar(usuarioActual, tipocambiomast);
		dto.setTransaccionEstado(tipocambiomast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(tipocambiomast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanTipocambiomast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanTipocambiomast tipocambiomast) throws UException {
		// valores por defecto - preparando objeto
		tipocambiomast = validar.prepararEliminar(usuarioActual, tipocambiomast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, tipocambiomast);
		if (!lst.isEmpty()) {
			tipocambiomast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			tipocambiomast.setTransaccionListaMensajes(lst);
			return tipocambiomast;
		}
		
		// transaccion
		tipocambiomastDao.eliminar(tipocambiomast);
		tipocambiomast=new BeanTipocambiomast();
		tipocambiomast.setTransaccionEstado(DominioTransaccion.OK);
		return tipocambiomast;
	}

	public BeanTipocambiomast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanTipocambiomastPk pk) throws UException {
		BeanTipocambiomast tipocambiomast = tipocambiomastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,tipocambiomast);
	}

	public BeanTipocambiomast coreEliminar(SeguridadUsuarioActual usuarioActual, String pmonedacodigo,String pmonedacambiocodigo,java.util.Date pfechacambio) throws UException {
		return coreEliminar(usuarioActual,new BeanTipocambiomastPk( pmonedacodigo, pmonedacambiocodigo, pfechacambio));
	}

}
