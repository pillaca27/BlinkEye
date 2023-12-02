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
import net.royal.spring.contabilidad.dao.impl.AcSucursalDaoImpl;

import net.royal.spring.contabilidad.dominio.BeanAcSucursal;
import net.royal.spring.contabilidad.dominio.BeanAcSucursalPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcSucursal;
import net.royal.spring.contabilidad.servicio.validar.AcSucursalServicioValidar;

@Service (value = "BeanServicioAcSucursal")
public class AcSucursalServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAcSucursal";
	private static Logger logger = LogManager.getLogger(AcSucursalServicioImpl.class);

	@Autowired
	private AcSucursalDaoImpl acSucursalDao;

	@Autowired
	private AcSucursalServicioValidar validar;
	
	@Transactional
	public DtoComunAcSucursal coreEliminarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursal dto) throws Exception {
		BeanAcSucursal acSucursalgrupo = acSucursalDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = coreEliminar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}


	@Transactional
	public DtoComunAcSucursal coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursal dto) throws Exception {
		BeanAcSucursal acSucursalgrupo = acSucursalDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = dto.obtenerBeanActualizar(acSucursalgrupo);
		acSucursalgrupo = coreActualizar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	public DtoComunAcSucursal  coreAnularPorUuid(SeguridadUsuarioActual usuarioActual, DtoComunAcSucursal  dto)
			throws Exception {
		BeanAcSucursal bean = acSucursalDao.obtenerPorUuid(dto.getUuid());
		bean = validar.prepararAuditoria(usuarioActual, bean);
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, bean);
		if (!lst.isEmpty()) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.setTransaccionListaMensajes(lst);
			return dto;
		}
		bean.setEstado("N");
		bean = acSucursalDao.coreActualizar(bean);
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	@Transactional
	public DtoComunAcSucursal coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursal dto) throws UException {
		BeanAcSucursal acSucursal = dto.obtenerBean();
		acSucursal = coreInsertar(usuarioActual, acSucursal);
		dto.setTransaccionEstado(acSucursal.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursal.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursal coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcSucursal acSucursal) throws UException {
		// valores por defecto - preparando objeto
		acSucursal = validar.prepararInsertar(usuarioActual, acSucursal);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, acSucursal);
		if (!lst.isEmpty()) {
			acSucursal.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursal.setTransaccionListaMensajes(lst);
			return acSucursal;
		}
		
		// transaccion
		acSucursal = acSucursalDao.coreInsertar(acSucursal);
		acSucursal.setTransaccionEstado(DominioTransaccion.OK);
		acSucursal.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acSucursal;
	}

	@Transactional
	public DtoComunAcSucursal coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursal dto) throws UException {
		BeanAcSucursal acSucursal = dto.obtenerBean();
		acSucursal = coreActualizar(usuarioActual, acSucursal);
		dto.setTransaccionEstado(acSucursal.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursal.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursal coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcSucursal acSucursal) throws UException {
		// valores por defecto - preparando objeto
		acSucursal = validar.prepararActualizar(usuarioActual, acSucursal);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, acSucursal);
		if (!lst.isEmpty()) {
			acSucursal.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursal.setTransaccionListaMensajes(lst);
			return acSucursal;
		}
		
		// transaccion
	    acSucursal = acSucursalDao.coreActualizar(acSucursal);
		acSucursal.setTransaccionEstado(DominioTransaccion.OK);
		acSucursal.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acSucursal;
	}


	@Transactional
	public DtoComunAcSucursal coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursal dto) throws UException {
		BeanAcSucursal acSucursal = dto.obtenerBean();
		acSucursal = coreAnular(usuarioActual, acSucursal);
		dto.setTransaccionEstado(acSucursal.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursal.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursal coreAnular(SeguridadUsuarioActual usuarioActual, BeanAcSucursal acSucursal) throws UException {
		// valores por defecto - preparando objeto
		acSucursal = validar.prepararAnular(usuarioActual, acSucursal);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, acSucursal);
		if (!lst.isEmpty()) {
			acSucursal.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursal.setTransaccionListaMensajes(lst);
			return acSucursal;
		}
		
		// transaccion
	    acSucursal.setEstado("I");
	    acSucursal = acSucursalDao.coreActualizar(acSucursal);
		acSucursal.setTransaccionEstado(DominioTransaccion.OK);
		acSucursal.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acSucursal;
	}

	public BeanAcSucursal coreAnular(SeguridadUsuarioActual usuarioActual, BeanAcSucursalPk pk) throws UException {
		BeanAcSucursal bean = acSucursalDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanAcSucursal coreAnular(SeguridadUsuarioActual usuarioActual, String psucursal) throws UException {
		return coreAnular(usuarioActual,new BeanAcSucursalPk( psucursal));
	}

	@Transactional
	public DtoComunAcSucursal coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursal dto) throws UException {
		BeanAcSucursal acSucursal = dto.obtenerBean();
		acSucursal = coreEliminar(usuarioActual, acSucursal);
		dto.setTransaccionEstado(acSucursal.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursal.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursal coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcSucursal acSucursal) throws UException {
		// valores por defecto - preparando objeto
		acSucursal = validar.prepararEliminar(usuarioActual, acSucursal);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, acSucursal);
		if (!lst.isEmpty()) {
			acSucursal.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursal.setTransaccionListaMensajes(lst);
			return acSucursal;
		}
		
		// transaccion
		acSucursalDao.eliminar(acSucursal);
		acSucursal=new BeanAcSucursal();
		acSucursal.setTransaccionEstado(DominioTransaccion.OK);
		return acSucursal;
	}

	public BeanAcSucursal coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalPk pk) throws UException {
		BeanAcSucursal acSucursal = acSucursalDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,acSucursal);
	}

	public BeanAcSucursal coreEliminar(SeguridadUsuarioActual usuarioActual, String psucursal) throws UException {
		return coreEliminar(usuarioActual,new BeanAcSucursalPk( psucursal));
	}

}
