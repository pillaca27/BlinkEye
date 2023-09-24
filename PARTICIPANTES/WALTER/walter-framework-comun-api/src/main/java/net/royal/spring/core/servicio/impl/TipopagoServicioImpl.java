package net.royal.spring.core.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dao.impl.TipopagoDaoImpl;
import net.royal.spring.core.dominio.BeanTipopago;
import net.royal.spring.core.dominio.TipopagoPk;
import net.royal.spring.core.dominio.dto.DtoComunTipopago;
import net.royal.spring.core.servicio.validar.TipopagoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioTipopago")
public class TipopagoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioTipopago";
	private static Logger logger = LogManager.getLogger(TipopagoServicioImpl.class);

	@Autowired
	private TipopagoDaoImpl tipopagoDao;

	@Autowired
	private TipopagoServicioValidar validar;

	@Transactional
	public DtoComunTipopago coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunTipopago dto) throws UException {
		BeanTipopago tipopago = dto.obtenerBean();
		tipopago = coreInsertar(usuarioActual, tipopago);
		dto.setTransaccionEstado(tipopago.getTransaccionEstado());
		dto.setTransaccionListaMensajes(tipopago.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanTipopago coreInsertar(SeguridadUsuarioActual usuarioActual,BeanTipopago tipopago) throws UException {
		// valores por defecto - preparando objeto
		tipopago = validar.prepararInsertar(usuarioActual, tipopago);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, tipopago);
		if (!lst.isEmpty()) {
			tipopago.setTransaccionEstado(DominioTransaccion.VALIDACION);
			tipopago.setTransaccionListaMensajes(lst);
			return tipopago;
		}
		
		// transaccion
		tipopago = tipopagoDao.coreInsertar(tipopago);
		tipopago.setTransaccionEstado(DominioTransaccion.OK);
		tipopago.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return tipopago;
	}

	@Transactional
	public DtoComunTipopago coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunTipopago dto) throws UException {
		BeanTipopago tipopago = dto.obtenerBean();
		tipopago = coreActualizar(usuarioActual, tipopago);
		dto.setTransaccionEstado(tipopago.getTransaccionEstado());
		dto.setTransaccionListaMensajes(tipopago.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanTipopago coreActualizar(SeguridadUsuarioActual usuarioActual, BeanTipopago tipopago) throws UException {
		// valores por defecto - preparando objeto
		tipopago = validar.prepararActualizar(usuarioActual, tipopago);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, tipopago);
		if (!lst.isEmpty()) {
			tipopago.setTransaccionEstado(DominioTransaccion.VALIDACION);
			tipopago.setTransaccionListaMensajes(lst);
			return tipopago;
		}
		
		// transaccion
	    tipopago = tipopagoDao.coreActualizar(tipopago);
		tipopago.setTransaccionEstado(DominioTransaccion.OK);
		tipopago.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return tipopago;
	}


	@Transactional
	public DtoComunTipopago coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunTipopago dto) throws UException {
		BeanTipopago tipopago = dto.obtenerBean();
		tipopago = coreAnular(usuarioActual, tipopago);
		dto.setTransaccionEstado(tipopago.getTransaccionEstado());
		dto.setTransaccionListaMensajes(tipopago.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanTipopago coreAnular(SeguridadUsuarioActual usuarioActual, BeanTipopago tipopago) throws UException {
		// valores por defecto - preparando objeto
		tipopago = validar.prepararAnular(usuarioActual, tipopago);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, tipopago);
		if (!lst.isEmpty()) {
			tipopago.setTransaccionEstado(DominioTransaccion.VALIDACION);
			tipopago.setTransaccionListaMensajes(lst);
			return tipopago;
		}
		
		// transaccion
	    tipopago.setEstado("I");
	    tipopago = tipopagoDao.coreActualizar(tipopago);
		tipopago.setTransaccionEstado(DominioTransaccion.OK);
		tipopago.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return tipopago;
	}

	public BeanTipopago coreAnular(SeguridadUsuarioActual usuarioActual, TipopagoPk pk) throws UException {
		BeanTipopago bean = tipopagoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanTipopago coreAnular(SeguridadUsuarioActual usuarioActual, String ptipopago) throws UException {
		return coreAnular(usuarioActual,new TipopagoPk( ptipopago));
	}

	@Transactional
	public DtoComunTipopago coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunTipopago dto) throws UException {
		BeanTipopago tipopago = dto.obtenerBean();
		tipopago = coreEliminar(usuarioActual, tipopago);
		dto.setTransaccionEstado(tipopago.getTransaccionEstado());
		dto.setTransaccionListaMensajes(tipopago.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanTipopago coreEliminar(SeguridadUsuarioActual usuarioActual, BeanTipopago tipopago) throws UException {
		// valores por defecto - preparando objeto
		tipopago = validar.prepararEliminar(usuarioActual, tipopago);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, tipopago);
		if (!lst.isEmpty()) {
			tipopago.setTransaccionEstado(DominioTransaccion.VALIDACION);
			tipopago.setTransaccionListaMensajes(lst);
			return tipopago;
		}
		
		// transaccion
		tipopagoDao.eliminar(tipopago);
		tipopago=new BeanTipopago();
		tipopago.setTransaccionEstado(DominioTransaccion.OK);
		return tipopago;
	}

	public BeanTipopago coreEliminar(SeguridadUsuarioActual usuarioActual, TipopagoPk pk) throws UException {
		BeanTipopago tipopago = tipopagoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,tipopago);
	}

	public BeanTipopago coreEliminar(SeguridadUsuarioActual usuarioActual, String ptipopago) throws UException {
		return coreEliminar(usuarioActual,new TipopagoPk( ptipopago));
	}

}
