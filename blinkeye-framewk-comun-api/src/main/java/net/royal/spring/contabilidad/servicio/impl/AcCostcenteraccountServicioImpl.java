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
import net.royal.spring.contabilidad.dao.impl.AcCostcenteraccountDaoImpl;

import net.royal.spring.contabilidad.dominio.BeanAcCostcenteraccount;
import net.royal.spring.contabilidad.dominio.BeanAcCostcenteraccountPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenteraccount;
import net.royal.spring.contabilidad.servicio.validar.AcCostcenteraccountServicioValidar;

@Service (value = "BeanServicioAcCostcenteraccount")
public class AcCostcenteraccountServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAcCostcenteraccount";
	private static Logger logger = LogManager.getLogger(AcCostcenteraccountServicioImpl.class);

	@Autowired
	private AcCostcenteraccountDaoImpl acCostcenteraccountDao;

	@Autowired
	private AcCostcenteraccountServicioValidar validar;

	@Transactional
	public DtoComunAcCostcenteraccount coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcenteraccount dto) throws UException {
		BeanAcCostcenteraccount acCostcenteraccount = dto.obtenerBean();
		acCostcenteraccount = coreInsertar(usuarioActual, acCostcenteraccount);
		dto.setTransaccionEstado(acCostcenteraccount.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcenteraccount.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcenteraccount coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenteraccount acCostcenteraccount) throws UException {
		// valores por defecto - preparando objeto
		acCostcenteraccount = validar.prepararInsertar(usuarioActual, acCostcenteraccount);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, acCostcenteraccount);
		if (!lst.isEmpty()) {
			acCostcenteraccount.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcenteraccount.setTransaccionListaMensajes(lst);
			return acCostcenteraccount;
		}
		
		// transaccion
		acCostcenteraccount = acCostcenteraccountDao.coreInsertar(acCostcenteraccount);
		acCostcenteraccount.setTransaccionEstado(DominioTransaccion.OK);
		acCostcenteraccount.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acCostcenteraccount;
	}

	@Transactional
	public DtoComunAcCostcenteraccount coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcenteraccount dto) throws UException {
		BeanAcCostcenteraccount acCostcenteraccount = dto.obtenerBean();
		acCostcenteraccount = coreActualizar(usuarioActual, acCostcenteraccount);
		dto.setTransaccionEstado(acCostcenteraccount.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcenteraccount.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcenteraccount coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcCostcenteraccount acCostcenteraccount) throws UException {
		// valores por defecto - preparando objeto
		acCostcenteraccount = validar.prepararActualizar(usuarioActual, acCostcenteraccount);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, acCostcenteraccount);
		if (!lst.isEmpty()) {
			acCostcenteraccount.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcenteraccount.setTransaccionListaMensajes(lst);
			return acCostcenteraccount;
		}
		
		// transaccion
	    acCostcenteraccount = acCostcenteraccountDao.coreActualizar(acCostcenteraccount);
		acCostcenteraccount.setTransaccionEstado(DominioTransaccion.OK);
		acCostcenteraccount.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acCostcenteraccount;
	}

	@Transactional
	public DtoComunAcCostcenteraccount coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcenteraccount dto) throws UException {
		BeanAcCostcenteraccount acCostcenteraccount = dto.obtenerBean();
		acCostcenteraccount = coreEliminar(usuarioActual, acCostcenteraccount);
		dto.setTransaccionEstado(acCostcenteraccount.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcenteraccount.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcenteraccount coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcCostcenteraccount acCostcenteraccount) throws UException {
		// valores por defecto - preparando objeto
		acCostcenteraccount = validar.prepararEliminar(usuarioActual, acCostcenteraccount);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, acCostcenteraccount);
		if (!lst.isEmpty()) {
			acCostcenteraccount.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcenteraccount.setTransaccionListaMensajes(lst);
			return acCostcenteraccount;
		}
		
		// transaccion
		acCostcenteraccountDao.eliminar(acCostcenteraccount);
		acCostcenteraccount=new BeanAcCostcenteraccount();
		acCostcenteraccount.setTransaccionEstado(DominioTransaccion.OK);
		return acCostcenteraccount;
	}

	public BeanAcCostcenteraccount coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcCostcenteraccountPk pk) throws UException {
		BeanAcCostcenteraccount acCostcenteraccount = acCostcenteraccountDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,acCostcenteraccount);
	}

	public BeanAcCostcenteraccount coreEliminar(SeguridadUsuarioActual usuarioActual, String pcostcenter,String paccount) throws UException {
		return coreEliminar(usuarioActual,new BeanAcCostcenteraccountPk( pcostcenter, paccount));
	}

}
