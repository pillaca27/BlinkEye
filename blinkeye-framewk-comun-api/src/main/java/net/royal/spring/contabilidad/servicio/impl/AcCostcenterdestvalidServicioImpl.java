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
import net.royal.spring.contabilidad.dao.impl.AcCostcenterdestvalidDaoImpl;

import net.royal.spring.contabilidad.dominio.BeanAcCostcenterdestvalid;
import net.royal.spring.contabilidad.dominio.BeanAcCostcenterdestvalidPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenterdestvalid;
import net.royal.spring.contabilidad.servicio.validar.AcCostcenterdestvalidServicioValidar;

@Service (value = "BeanServicioAcCostcenterdestvalid")
public class AcCostcenterdestvalidServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAcCostcenterdestvalid";
	private static Logger logger = LogManager.getLogger(AcCostcenterdestvalidServicioImpl.class);

	@Autowired
	private AcCostcenterdestvalidDaoImpl acCostcenterdestvalidDao;

	@Autowired
	private AcCostcenterdestvalidServicioValidar validar;

	@Transactional
	public DtoComunAcCostcenterdestvalid coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcenterdestvalid dto) throws UException {
		BeanAcCostcenterdestvalid acCostcenterdestvalid = dto.obtenerBean();
		acCostcenterdestvalid = coreInsertar(usuarioActual, acCostcenterdestvalid);
		dto.setTransaccionEstado(acCostcenterdestvalid.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcenterdestvalid.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcenterdestvalid coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterdestvalid acCostcenterdestvalid) throws UException {
		// valores por defecto - preparando objeto
		acCostcenterdestvalid = validar.prepararInsertar(usuarioActual, acCostcenterdestvalid);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, acCostcenterdestvalid);
		if (!lst.isEmpty()) {
			acCostcenterdestvalid.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcenterdestvalid.setTransaccionListaMensajes(lst);
			return acCostcenterdestvalid;
		}
		
		// transaccion
		acCostcenterdestvalid = acCostcenterdestvalidDao.coreInsertar(acCostcenterdestvalid);
		acCostcenterdestvalid.setTransaccionEstado(DominioTransaccion.OK);
		acCostcenterdestvalid.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acCostcenterdestvalid;
	}

	@Transactional
	public DtoComunAcCostcenterdestvalid coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcenterdestvalid dto) throws UException {
		BeanAcCostcenterdestvalid acCostcenterdestvalid = dto.obtenerBean();
		acCostcenterdestvalid = coreActualizar(usuarioActual, acCostcenterdestvalid);
		dto.setTransaccionEstado(acCostcenterdestvalid.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcenterdestvalid.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcenterdestvalid coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcCostcenterdestvalid acCostcenterdestvalid) throws UException {
		// valores por defecto - preparando objeto
		acCostcenterdestvalid = validar.prepararActualizar(usuarioActual, acCostcenterdestvalid);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, acCostcenterdestvalid);
		if (!lst.isEmpty()) {
			acCostcenterdestvalid.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcenterdestvalid.setTransaccionListaMensajes(lst);
			return acCostcenterdestvalid;
		}
		
		// transaccion
	    acCostcenterdestvalid = acCostcenterdestvalidDao.coreActualizar(acCostcenterdestvalid);
		acCostcenterdestvalid.setTransaccionEstado(DominioTransaccion.OK);
		acCostcenterdestvalid.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acCostcenterdestvalid;
	}

	@Transactional
	public DtoComunAcCostcenterdestvalid coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcenterdestvalid dto) throws UException {
		BeanAcCostcenterdestvalid acCostcenterdestvalid = dto.obtenerBean();
		acCostcenterdestvalid = coreEliminar(usuarioActual, acCostcenterdestvalid);
		dto.setTransaccionEstado(acCostcenterdestvalid.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcenterdestvalid.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcenterdestvalid coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcCostcenterdestvalid acCostcenterdestvalid) throws UException {
		// valores por defecto - preparando objeto
		acCostcenterdestvalid = validar.prepararEliminar(usuarioActual, acCostcenterdestvalid);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, acCostcenterdestvalid);
		if (!lst.isEmpty()) {
			acCostcenterdestvalid.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcenterdestvalid.setTransaccionListaMensajes(lst);
			return acCostcenterdestvalid;
		}
		
		// transaccion
		acCostcenterdestvalidDao.eliminar(acCostcenterdestvalid);
		acCostcenterdestvalid=new BeanAcCostcenterdestvalid();
		acCostcenterdestvalid.setTransaccionEstado(DominioTransaccion.OK);
		return acCostcenterdestvalid;
	}

	public BeanAcCostcenterdestvalid coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcCostcenterdestvalidPk pk) throws UException {
		BeanAcCostcenterdestvalid acCostcenterdestvalid = acCostcenterdestvalidDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,acCostcenterdestvalid);
	}

	public BeanAcCostcenterdestvalid coreEliminar(SeguridadUsuarioActual usuarioActual, String pcostcenter,String pcostcenterdestination) throws UException {
		return coreEliminar(usuarioActual,new BeanAcCostcenterdestvalidPk( pcostcenter, pcostcenterdestination));
	}

}
