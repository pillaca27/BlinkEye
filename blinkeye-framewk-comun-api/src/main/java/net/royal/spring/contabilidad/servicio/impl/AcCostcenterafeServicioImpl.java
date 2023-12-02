package net.royal.spring.contabilidad.servicio.impl;

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
import net.royal.spring.contabilidad.dao.impl.AcCostcenterafeDaoImpl;

import net.royal.spring.contabilidad.dominio.BeanAcCostcenterafe;
import net.royal.spring.contabilidad.dominio.BeanAcCostcenterafePk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenterafe;
import net.royal.spring.contabilidad.servicio.validar.AcCostcenterafeServicioValidar;

@Service (value = "BeanServicioAcCostcenterafe")
public class AcCostcenterafeServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAcCostcenterafe";
	private static Logger logger = LogManager.getLogger(AcCostcenterafeServicioImpl.class);

	@Autowired
	private AcCostcenterafeDaoImpl acCostcenterafeDao;

	@Autowired
	private AcCostcenterafeServicioValidar validar;

	@Transactional
	public DtoComunAcCostcenterafe coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcenterafe dto) throws UException {
		BeanAcCostcenterafe acCostcenterafe = dto.obtenerBean();
		acCostcenterafe = coreInsertar(usuarioActual, acCostcenterafe);
		dto.setTransaccionEstado(acCostcenterafe.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcenterafe.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcenterafe coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterafe acCostcenterafe) throws UException {
		// valores por defecto - preparando objeto
		acCostcenterafe = validar.prepararInsertar(usuarioActual, acCostcenterafe);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, acCostcenterafe);
		if (!lst.isEmpty()) {
			acCostcenterafe.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcenterafe.setTransaccionListaMensajes(lst);
			return acCostcenterafe;
		}
		
		// transaccion
		acCostcenterafe = acCostcenterafeDao.coreInsertar(acCostcenterafe);
		acCostcenterafe.setTransaccionEstado(DominioTransaccion.OK);
		acCostcenterafe.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acCostcenterafe;
	}

	@Transactional
	public DtoComunAcCostcenterafe coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcenterafe dto) throws UException {
		BeanAcCostcenterafe acCostcenterafe = dto.obtenerBean();
		acCostcenterafe = coreActualizar(usuarioActual, acCostcenterafe);
		dto.setTransaccionEstado(acCostcenterafe.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcenterafe.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcenterafe coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcCostcenterafe acCostcenterafe) throws UException {
		// valores por defecto - preparando objeto
		acCostcenterafe = validar.prepararActualizar(usuarioActual, acCostcenterafe);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, acCostcenterafe);
		if (!lst.isEmpty()) {
			acCostcenterafe.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcenterafe.setTransaccionListaMensajes(lst);
			return acCostcenterafe;
		}
		
		// transaccion
	    acCostcenterafe = acCostcenterafeDao.coreActualizar(acCostcenterafe);
		acCostcenterafe.setTransaccionEstado(DominioTransaccion.OK);
		acCostcenterafe.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acCostcenterafe;
	}

	@Transactional
	public DtoComunAcCostcenterafe coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcenterafe dto) throws UException {
		BeanAcCostcenterafe acCostcenterafe = dto.obtenerBean();
		acCostcenterafe = coreEliminar(usuarioActual, acCostcenterafe);
		dto.setTransaccionEstado(acCostcenterafe.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcenterafe.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcenterafe coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcCostcenterafe acCostcenterafe) throws UException {
		// valores por defecto - preparando objeto
		acCostcenterafe = validar.prepararEliminar(usuarioActual, acCostcenterafe);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, acCostcenterafe);
		if (!lst.isEmpty()) {
			acCostcenterafe.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcenterafe.setTransaccionListaMensajes(lst);
			return acCostcenterafe;
		}
		
		// transaccion
		acCostcenterafeDao.eliminar(acCostcenterafe);
		acCostcenterafe=new BeanAcCostcenterafe();
		acCostcenterafe.setTransaccionEstado(DominioTransaccion.OK);
		return acCostcenterafe;
	}

	public BeanAcCostcenterafe coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcCostcenterafePk pk) throws UException {
		BeanAcCostcenterafe acCostcenterafe = acCostcenterafeDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,acCostcenterafe);
	}

	public BeanAcCostcenterafe coreEliminar(SeguridadUsuarioActual usuarioActual, String pcostcenter,String pafe) throws UException {
		return coreEliminar(usuarioActual,new BeanAcCostcenterafePk( pcostcenter, pafe));
	}

}
