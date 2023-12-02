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
import net.royal.spring.core.dao.impl.RtpsEmpresaexternaDaoImpl;
import net.royal.spring.core.dao.impl.RtpsEstablecimientoexternoDaoImpl;
import net.royal.spring.core.dominio.BeanRtpsEmpresaexterna;
import net.royal.spring.core.dominio.BeanRtpsEmpresaexternaPk;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEmpresaexterna;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEstablecimientoexterno;
import net.royal.spring.core.servicio.validar.RtpsEmpresaexternaServicioValidar;

@Service (value = "BeanServicioRtpsEmpresaexterna")
public class RtpsEmpresaexternaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioRtpsEmpresaexterna";
	private static Logger logger = LogManager.getLogger(RtpsEmpresaexternaServicioImpl.class);

	@Autowired
	private RtpsEmpresaexternaDaoImpl rtpsEmpresaexternaDao;

	@Autowired
	private RtpsEmpresaexternaServicioValidar validar;
	
	@Autowired
	private RtpsEstablecimientoexternoServicioImpl serviciodetalle;
	
	@Autowired
	private RtpsEstablecimientoexternoDaoImpl rtpsEstablecimientoexternoDao;
	

	@Transactional
	public DtoComunRtpsEmpresaexterna coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEmpresaexterna dto) throws Exception {
		BeanRtpsEmpresaexterna rtpsEmpresaexterna = dto.obtenerBean();
		rtpsEmpresaexterna = coreInsertar(usuarioActual, rtpsEmpresaexterna,dto );
		dto.setTransaccionEstado(rtpsEmpresaexterna.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEmpresaexterna.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEmpresaexterna coreInsertar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpresaexterna rtpsEmpresaexterna,DtoComunRtpsEmpresaexterna dto) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEmpresaexterna = validar.prepararInsertar(usuarioActual, rtpsEmpresaexterna);
		rtpsEmpresaexterna.getPk().setCodigoautomatico(rtpsEmpresaexternaDao.generarSecuencia());
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, rtpsEmpresaexterna);
		if (!lst.isEmpty()) {
			rtpsEmpresaexterna.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEmpresaexterna.setTransaccionListaMensajes(lst);
			return rtpsEmpresaexterna;
		}
		
		// transaccion
		rtpsEmpresaexterna = rtpsEmpresaexternaDao.coreInsertar(rtpsEmpresaexterna);
		
		
		for (DtoComunRtpsEstablecimientoexterno objestablecimiento : dto.getLstdetestablecimiento()) {
			
			objestablecimiento.setCodigoautomatico(rtpsEstablecimientoexternoDao.generarSecuencia());
			objestablecimiento.setAutomaticoexterno(rtpsEmpresaexterna.getPk().getCodigoautomatico());
			serviciodetalle.coreInsertar(usuarioActual, objestablecimiento);
		}
		
		
		rtpsEmpresaexterna.setTransaccionEstado(DominioTransaccion.OK);
		rtpsEmpresaexterna.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return rtpsEmpresaexterna;
	}

	@Transactional
	public DtoComunRtpsEmpresaexterna coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEmpresaexterna dto) throws Exception {
		BeanRtpsEmpresaexterna rtpsEmpresaexterna = dto.obtenerBean();
		rtpsEmpresaexterna = coreActualizar(usuarioActual, rtpsEmpresaexterna,dto);
		dto.setTransaccionEstado(rtpsEmpresaexterna.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEmpresaexterna.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEmpresaexterna coreActualizar(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpresaexterna rtpsEmpresaexterna,DtoComunRtpsEmpresaexterna dto) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEmpresaexterna = validar.prepararActualizar(usuarioActual, rtpsEmpresaexterna);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, rtpsEmpresaexterna);
		if (!lst.isEmpty()) {
			rtpsEmpresaexterna.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEmpresaexterna.setTransaccionListaMensajes(lst);
			return rtpsEmpresaexterna;
		}
		
		// transaccion
	    rtpsEmpresaexterna = rtpsEmpresaexternaDao.coreActualizar(rtpsEmpresaexterna);
	    
	    
	    
	    for (DtoComunRtpsEstablecimientoexterno bjdetalle : dto.getLstdetestablecimiento()) {
			if (bjdetalle.getAccion().equals("ELIMINAR")) {
				serviciodetalle.coreEliminar(usuarioActual, bjdetalle);
			}
		}
	    
	    for (DtoComunRtpsEstablecimientoexterno bjdetalle : dto.getLstdetestablecimiento()) {
	    	if (bjdetalle.getAccion().equals("NUEVO")) {
	    		
	    		bjdetalle.setCodigoautomatico(rtpsEstablecimientoexternoDao.generarSecuencia());
	    		bjdetalle.setAutomaticoexterno(rtpsEmpresaexterna.getPk().getCodigoautomatico());
	    		serviciodetalle.coreInsertar(usuarioActual,bjdetalle);
	    		
	    	}else if(bjdetalle.getAccion().equals("UPDATE")) {
	    		serviciodetalle.coreActualizar(usuarioActual,bjdetalle);
	    	}
		}
	    
		rtpsEmpresaexterna.setTransaccionEstado(DominioTransaccion.OK);
		rtpsEmpresaexterna.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return rtpsEmpresaexterna;
	}


	@Transactional
	public DtoComunRtpsEmpresaexterna coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEmpresaexterna dto) throws Exception {
		BeanRtpsEmpresaexterna rtpsEmpresaexterna = dto.obtenerBean();
		rtpsEmpresaexterna = coreAnular(usuarioActual, rtpsEmpresaexterna);
		dto.setTransaccionEstado(rtpsEmpresaexterna.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEmpresaexterna.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEmpresaexterna coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpresaexterna rtpsEmpresaexterna) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEmpresaexterna = validar.prepararAnular(usuarioActual, rtpsEmpresaexterna);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, rtpsEmpresaexterna);
		if (!lst.isEmpty()) {
			rtpsEmpresaexterna.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEmpresaexterna.setTransaccionListaMensajes(lst);
			return rtpsEmpresaexterna;
		}
		
		// transaccion
	    rtpsEmpresaexterna.setEstado("I");
	    rtpsEmpresaexterna = rtpsEmpresaexternaDao.coreActualizar(rtpsEmpresaexterna);
		rtpsEmpresaexterna.setTransaccionEstado(DominioTransaccion.OK);
		rtpsEmpresaexterna.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return rtpsEmpresaexterna;
	}

	public BeanRtpsEmpresaexterna coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpresaexternaPk pk) throws Exception {
		BeanRtpsEmpresaexterna bean = rtpsEmpresaexternaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanRtpsEmpresaexterna coreAnular(SeguridadUsuarioActual usuarioActual, Integer pcodigoautomatico) throws Exception {
		return coreAnular(usuarioActual,new BeanRtpsEmpresaexternaPk( pcodigoautomatico));
	}

	@Transactional
	public DtoComunRtpsEmpresaexterna coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEmpresaexterna dto) throws Exception {
		BeanRtpsEmpresaexterna rtpsEmpresaexterna = dto.obtenerBean();
		rtpsEmpresaexterna = coreEliminar(usuarioActual, rtpsEmpresaexterna, dto);
		dto.setTransaccionEstado(rtpsEmpresaexterna.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEmpresaexterna.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEmpresaexterna coreEliminar(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpresaexterna rtpsEmpresaexterna, DtoComunRtpsEmpresaexterna dto ) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEmpresaexterna = validar.prepararEliminar(usuarioActual, rtpsEmpresaexterna);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, rtpsEmpresaexterna);
		if (!lst.isEmpty()) {
			rtpsEmpresaexterna.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEmpresaexterna.setTransaccionListaMensajes(lst);
			return rtpsEmpresaexterna;
		}
		
		for (DtoComunRtpsEstablecimientoexterno bjdetalle : dto.getLstdetestablecimiento()) {
			serviciodetalle.coreEliminar(usuarioActual, bjdetalle);
		}
		
		// transaccion
		rtpsEmpresaexternaDao.eliminar(rtpsEmpresaexterna);
		rtpsEmpresaexterna=new BeanRtpsEmpresaexterna();
		rtpsEmpresaexterna.setTransaccionEstado(DominioTransaccion.OK);
		return rtpsEmpresaexterna;
	}

	public BeanRtpsEmpresaexterna coreEliminar(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpresaexternaPk pk, DtoComunRtpsEmpresaexterna dto ) throws Exception {
		BeanRtpsEmpresaexterna rtpsEmpresaexterna = rtpsEmpresaexternaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,rtpsEmpresaexterna, dto);
	}
	/*
	public RtpsEmpresaexterna coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pcodigoautomatico) throws Exception {
		return coreEliminar(usuarioActual,new RtpsEmpresaexternaPk( pcodigoautomatico));
	}
	*/
}
