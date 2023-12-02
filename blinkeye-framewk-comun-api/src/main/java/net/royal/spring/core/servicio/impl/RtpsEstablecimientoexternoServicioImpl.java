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
import net.royal.spring.core.dao.impl.RtpsEstablecimientoexternoDaoImpl;

import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexternoPk;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEstablecimientoexterno;
import net.royal.spring.core.servicio.validar.RtpsEstablecimientoexternoServicioValidar;

@Service (value = "BeanServicioRtpsEstablecimientoexterno")
public class RtpsEstablecimientoexternoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioRtpsEstablecimientoexterno";
	private static Logger logger = LogManager.getLogger(RtpsEstablecimientoexternoServicioImpl.class);

	@Autowired
	private RtpsEstablecimientoexternoDaoImpl rtpsEstablecimientoexternoDao;

	@Autowired
	private RtpsEstablecimientoexternoServicioValidar validar;

	@Transactional
	public DtoComunRtpsEstablecimientoexterno coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEstablecimientoexterno dto) throws Exception {
		BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno = dto.obtenerBean();
		rtpsEstablecimientoexterno = coreInsertar(usuarioActual, rtpsEstablecimientoexterno);
		dto.setTransaccionEstado(rtpsEstablecimientoexterno.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEstablecimientoexterno.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEstablecimientoexterno coreInsertar(SeguridadUsuarioActual usuarioActual,BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEstablecimientoexterno = validar.prepararInsertar(usuarioActual, rtpsEstablecimientoexterno);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, rtpsEstablecimientoexterno);
		if (!lst.isEmpty()) {
			rtpsEstablecimientoexterno.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEstablecimientoexterno.setTransaccionListaMensajes(lst);
			return rtpsEstablecimientoexterno;
		}
		
		// transaccion
		rtpsEstablecimientoexterno = rtpsEstablecimientoexternoDao.coreInsertar(rtpsEstablecimientoexterno);
		rtpsEstablecimientoexterno.setTransaccionEstado(DominioTransaccion.OK);
		rtpsEstablecimientoexterno.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return rtpsEstablecimientoexterno;
	}

	@Transactional
	public DtoComunRtpsEstablecimientoexterno coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEstablecimientoexterno dto) throws Exception {
		BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno = dto.obtenerBean();
		rtpsEstablecimientoexterno = coreActualizar(usuarioActual, rtpsEstablecimientoexterno);
		dto.setTransaccionEstado(rtpsEstablecimientoexterno.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEstablecimientoexterno.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEstablecimientoexterno coreActualizar(SeguridadUsuarioActual usuarioActual, BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEstablecimientoexterno = validar.prepararActualizar(usuarioActual, rtpsEstablecimientoexterno);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, rtpsEstablecimientoexterno);
		if (!lst.isEmpty()) {
			rtpsEstablecimientoexterno.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEstablecimientoexterno.setTransaccionListaMensajes(lst);
			return rtpsEstablecimientoexterno;
		}
		
		// transaccion
	    rtpsEstablecimientoexterno = rtpsEstablecimientoexternoDao.coreActualizar(rtpsEstablecimientoexterno);
		rtpsEstablecimientoexterno.setTransaccionEstado(DominioTransaccion.OK);
		rtpsEstablecimientoexterno.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return rtpsEstablecimientoexterno;
	}


	@Transactional
	public DtoComunRtpsEstablecimientoexterno coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEstablecimientoexterno dto) throws Exception {
		BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno = dto.obtenerBean();
		rtpsEstablecimientoexterno = coreAnular(usuarioActual, rtpsEstablecimientoexterno);
		dto.setTransaccionEstado(rtpsEstablecimientoexterno.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEstablecimientoexterno.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEstablecimientoexterno coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEstablecimientoexterno = validar.prepararAnular(usuarioActual, rtpsEstablecimientoexterno);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, rtpsEstablecimientoexterno);
		if (!lst.isEmpty()) {
			rtpsEstablecimientoexterno.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEstablecimientoexterno.setTransaccionListaMensajes(lst);
			return rtpsEstablecimientoexterno;
		}
		
		// transaccion
	    rtpsEstablecimientoexterno.setEstado("I");
	    rtpsEstablecimientoexterno = rtpsEstablecimientoexternoDao.coreActualizar(rtpsEstablecimientoexterno);
		rtpsEstablecimientoexterno.setTransaccionEstado(DominioTransaccion.OK);
		rtpsEstablecimientoexterno.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return rtpsEstablecimientoexterno;
	}

	public BeanRtpsEstablecimientoexterno coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEstablecimientoexternoPk pk) throws Exception {
		BeanRtpsEstablecimientoexterno bean = rtpsEstablecimientoexternoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanRtpsEstablecimientoexterno coreAnular(SeguridadUsuarioActual usuarioActual, Integer pcodigoautomatico) throws Exception {
		return coreAnular(usuarioActual,new BeanRtpsEstablecimientoexternoPk( pcodigoautomatico));
	}

	@Transactional
	public DtoComunRtpsEstablecimientoexterno coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEstablecimientoexterno dto) throws Exception {
		BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno = dto.obtenerBean();
		rtpsEstablecimientoexterno = coreEliminar(usuarioActual, rtpsEstablecimientoexterno);
		dto.setTransaccionEstado(rtpsEstablecimientoexterno.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEstablecimientoexterno.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEstablecimientoexterno coreEliminar(SeguridadUsuarioActual usuarioActual, BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEstablecimientoexterno = validar.prepararEliminar(usuarioActual, rtpsEstablecimientoexterno);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, rtpsEstablecimientoexterno);
		if (!lst.isEmpty()) {
			rtpsEstablecimientoexterno.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEstablecimientoexterno.setTransaccionListaMensajes(lst);
			return rtpsEstablecimientoexterno;
		}
		
		// transaccion
		rtpsEstablecimientoexternoDao.eliminar(rtpsEstablecimientoexterno);
		rtpsEstablecimientoexterno=new BeanRtpsEstablecimientoexterno();
		rtpsEstablecimientoexterno.setTransaccionEstado(DominioTransaccion.OK);
		return rtpsEstablecimientoexterno;
	}

	public BeanRtpsEstablecimientoexterno coreEliminar(SeguridadUsuarioActual usuarioActual, BeanRtpsEstablecimientoexternoPk pk) throws Exception {
		BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno = rtpsEstablecimientoexternoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,rtpsEstablecimientoexterno);
	}

	public BeanRtpsEstablecimientoexterno coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pcodigoautomatico) throws Exception {
		return coreEliminar(usuarioActual,new BeanRtpsEstablecimientoexternoPk( pcodigoautomatico));
	}

}
