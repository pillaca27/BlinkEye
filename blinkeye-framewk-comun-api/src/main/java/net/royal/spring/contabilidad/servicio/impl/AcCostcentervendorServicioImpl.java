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
import net.royal.spring.contabilidad.dao.impl.AcCostcentervendorDaoImpl;

import net.royal.spring.contabilidad.dominio.BeanAcCostcentervendor;
import net.royal.spring.contabilidad.dominio.BeanAcCostcentervendorPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcentervendor;
import net.royal.spring.contabilidad.servicio.validar.AcCostcentervendorServicioValidar;

@Service (value = "BeanServicioAcCostcentervendor")
public class AcCostcentervendorServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAcCostcentervendor";
	private static Logger logger = LogManager.getLogger(AcCostcentervendorServicioImpl.class);

	@Autowired
	private AcCostcentervendorDaoImpl acCostcentervendorDao;

	@Autowired
	private AcCostcentervendorServicioValidar validar;

	@Transactional
	public DtoComunAcCostcentervendor coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcentervendor dto) throws UException {
		BeanAcCostcentervendor acCostcentervendor = dto.obtenerBean();
		acCostcentervendor = coreInsertar(usuarioActual, acCostcentervendor);
		dto.setTransaccionEstado(acCostcentervendor.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcentervendor.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcentervendor coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcCostcentervendor acCostcentervendor) throws UException {
		// valores por defecto - preparando objeto
		acCostcentervendor = validar.prepararInsertar(usuarioActual, acCostcentervendor);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, acCostcentervendor);
		if (!lst.isEmpty()) {
			acCostcentervendor.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcentervendor.setTransaccionListaMensajes(lst);
			return acCostcentervendor;
		}
		
		// transaccion
		acCostcentervendor = acCostcentervendorDao.coreInsertar(acCostcentervendor);
		acCostcentervendor.setTransaccionEstado(DominioTransaccion.OK);
		acCostcentervendor.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acCostcentervendor;
	}

	@Transactional
	public DtoComunAcCostcentervendor coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcentervendor dto) throws UException {
		BeanAcCostcentervendor acCostcentervendor = dto.obtenerBean();
		acCostcentervendor = coreActualizar(usuarioActual, acCostcentervendor);
		dto.setTransaccionEstado(acCostcentervendor.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcentervendor.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcentervendor coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcCostcentervendor acCostcentervendor) throws UException {
		// valores por defecto - preparando objeto
		acCostcentervendor = validar.prepararActualizar(usuarioActual, acCostcentervendor);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, acCostcentervendor);
		if (!lst.isEmpty()) {
			acCostcentervendor.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcentervendor.setTransaccionListaMensajes(lst);
			return acCostcentervendor;
		}
		
		// transaccion
	    acCostcentervendor = acCostcentervendorDao.coreActualizar(acCostcentervendor);
		acCostcentervendor.setTransaccionEstado(DominioTransaccion.OK);
		acCostcentervendor.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acCostcentervendor;
	}

	@Transactional
	public DtoComunAcCostcentervendor coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcentervendor dto) throws UException {
		BeanAcCostcentervendor acCostcentervendor = dto.obtenerBean();
		acCostcentervendor = coreEliminar(usuarioActual, acCostcentervendor);
		dto.setTransaccionEstado(acCostcentervendor.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcentervendor.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcentervendor coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcCostcentervendor acCostcentervendor) throws UException {
		// valores por defecto - preparando objeto
		acCostcentervendor = validar.prepararEliminar(usuarioActual, acCostcentervendor);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, acCostcentervendor);
		if (!lst.isEmpty()) {
			acCostcentervendor.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcentervendor.setTransaccionListaMensajes(lst);
			return acCostcentervendor;
		}
		
		// transaccion
		acCostcentervendorDao.eliminar(acCostcentervendor);
		acCostcentervendor=new BeanAcCostcentervendor();
		acCostcentervendor.setTransaccionEstado(DominioTransaccion.OK);
		return acCostcentervendor;
	}

	public BeanAcCostcentervendor coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcCostcentervendorPk pk) throws UException {
		BeanAcCostcentervendor acCostcentervendor = acCostcentervendorDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,acCostcentervendor);
	}

	public BeanAcCostcentervendor coreEliminar(SeguridadUsuarioActual usuarioActual, String pcostcenter,Integer pvendor) throws UException {
		return coreEliminar(usuarioActual,new BeanAcCostcentervendorPk( pcostcenter, pvendor));
	}

}
