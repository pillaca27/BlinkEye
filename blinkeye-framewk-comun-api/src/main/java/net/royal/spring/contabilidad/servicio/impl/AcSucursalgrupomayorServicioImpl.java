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
import net.royal.spring.contabilidad.dao.impl.AcSucursalgrupomayorDaoImpl;

import net.royal.spring.contabilidad.dominio.BeanAcSucursalgrupomayor;
import net.royal.spring.contabilidad.dominio.BeanAcSucursalgrupomayorPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcSucursalgrupomayor;
import net.royal.spring.contabilidad.servicio.validar.AcSucursalgrupomayorServicioValidar;

@Service (value = "BeanServicioAcSucursalgrupomayor")
public class AcSucursalgrupomayorServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAcSucursalgrupomayor";
	private static Logger logger = LogManager.getLogger(AcSucursalgrupomayorServicioImpl.class);

	@Autowired
	private AcSucursalgrupomayorDaoImpl acSucursalgrupomayorDao;

	@Autowired
	private AcSucursalgrupomayorServicioValidar validar;

	@Transactional
	public DtoComunAcSucursalgrupomayor coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupomayor dto) throws Exception {
		BeanAcSucursalgrupomayor acSucursalgrupomayor = dto.obtenerBeanRegistrar();
		acSucursalgrupomayor = coreInsertar(usuarioActual, acSucursalgrupomayor);
		dto.setTransaccionEstado(acSucursalgrupomayor.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupomayor.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursalgrupomayor coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		// valores por defecto - preparando objeto
		acSucursalgrupomayor = validar.prepararInsertar(usuarioActual, acSucursalgrupomayor);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, acSucursalgrupomayor);
		if (!lst.isEmpty()) {
			acSucursalgrupomayor.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursalgrupomayor.setTransaccionListaMensajes(lst);
			return acSucursalgrupomayor;
		}
		
		// transaccion
		acSucursalgrupomayor = acSucursalgrupomayorDao.coreInsertar(acSucursalgrupomayor);
		acSucursalgrupomayor.setTransaccionEstado(DominioTransaccion.OK);
		acSucursalgrupomayor.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acSucursalgrupomayor;
	}

	@Transactional
	public DtoComunAcSucursalgrupomayor coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupomayor dto) throws Exception {
		BeanAcSucursalgrupomayor acSucursalgrupomayor = acSucursalgrupomayorDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupomayor = dto.obtenerBeanActualizar(acSucursalgrupomayor);
		acSucursalgrupomayor = coreActualizar(usuarioActual, acSucursalgrupomayor);
		dto.setTransaccionEstado(acSucursalgrupomayor.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupomayor.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunAcSucursalgrupomayor coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupomayor dto) throws Exception {
		BeanAcSucursalgrupomayor acSucursalgrupomayor = dto.obtenerBean();
		acSucursalgrupomayor = coreActualizar(usuarioActual, acSucursalgrupomayor);
		dto.setTransaccionEstado(acSucursalgrupomayor.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupomayor.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursalgrupomayor coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		// valores por defecto - preparando objeto
		acSucursalgrupomayor = validar.prepararActualizar(usuarioActual, acSucursalgrupomayor);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, acSucursalgrupomayor);
		if (!lst.isEmpty()) {
			acSucursalgrupomayor.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursalgrupomayor.setTransaccionListaMensajes(lst);
			return acSucursalgrupomayor;
		}
		
		// transaccion
	    acSucursalgrupomayor = acSucursalgrupomayorDao.coreActualizar(acSucursalgrupomayor);
		acSucursalgrupomayor.setTransaccionEstado(DominioTransaccion.OK);
		acSucursalgrupomayor.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acSucursalgrupomayor;
	}

	@Transactional
	public DtoComunAcSucursalgrupomayor coreAnularPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupomayor dto) throws Exception {
		BeanAcSucursalgrupomayor acSucursalgrupomayor = acSucursalgrupomayorDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupomayor = coreAnular(usuarioActual, acSucursalgrupomayor);
		dto.setTransaccionEstado(acSucursalgrupomayor.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupomayor.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunAcSucursalgrupomayor coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupomayor dto) throws Exception {
		BeanAcSucursalgrupomayor acSucursalgrupomayor = dto.obtenerBean();
		acSucursalgrupomayor = coreAnular(usuarioActual, acSucursalgrupomayor);
		dto.setTransaccionEstado(acSucursalgrupomayor.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupomayor.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursalgrupomayor coreAnular(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		// valores por defecto - preparando objeto
		acSucursalgrupomayor = validar.prepararAnular(usuarioActual, acSucursalgrupomayor);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, acSucursalgrupomayor);
		if (!lst.isEmpty()) {
			acSucursalgrupomayor.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursalgrupomayor.setTransaccionListaMensajes(lst);
			return acSucursalgrupomayor;
		}
		
		// transaccion
	    acSucursalgrupomayor.setEstado("I");
	    acSucursalgrupomayor = acSucursalgrupomayorDao.coreActualizar(acSucursalgrupomayor);
		acSucursalgrupomayor.setTransaccionEstado(DominioTransaccion.OK);
		acSucursalgrupomayor.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acSucursalgrupomayor;
	}

	public BeanAcSucursalgrupomayor coreAnular(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupomayorPk pk) throws Exception {
		BeanAcSucursalgrupomayor bean = acSucursalgrupomayorDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanAcSucursalgrupomayor coreAnular(SeguridadUsuarioActual usuarioActual, String psucursalgrupomayor) throws Exception {
		return coreAnular(usuarioActual,new BeanAcSucursalgrupomayorPk( psucursalgrupomayor));
	}

	@Transactional
	public DtoComunAcSucursalgrupomayor coreActivarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupomayor dto) throws Exception {
		BeanAcSucursalgrupomayor acSucursalgrupomayor = acSucursalgrupomayorDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupomayor = coreActivar(usuarioActual, acSucursalgrupomayor);
		dto.setTransaccionEstado(acSucursalgrupomayor.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupomayor.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunAcSucursalgrupomayor coreActivar(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupomayor dto) throws Exception {
		BeanAcSucursalgrupomayor acSucursalgrupomayor = dto.obtenerBean();
		acSucursalgrupomayor = coreActivar(usuarioActual, acSucursalgrupomayor);
		dto.setTransaccionEstado(acSucursalgrupomayor.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupomayor.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursalgrupomayor coreActivar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		// valores por defecto - preparando objeto
		acSucursalgrupomayor = validar.prepararActivar(usuarioActual, acSucursalgrupomayor);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActivar(usuarioActual, acSucursalgrupomayor);
		if (!lst.isEmpty()) {
			acSucursalgrupomayor.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursalgrupomayor.setTransaccionListaMensajes(lst);
			return acSucursalgrupomayor;
		}
		
		// transaccion
	    acSucursalgrupomayor.setEstado("A");
	    acSucursalgrupomayor = acSucursalgrupomayorDao.coreActualizar(acSucursalgrupomayor);
		acSucursalgrupomayor.setTransaccionEstado(DominioTransaccion.OK);
		acSucursalgrupomayor.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acSucursalgrupomayor;
	}

	public BeanAcSucursalgrupomayor coreActivar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupomayorPk pk) throws Exception {
		BeanAcSucursalgrupomayor bean = acSucursalgrupomayorDao.obtenerPorId(pk);
		return coreActivar(usuarioActual,bean);
	}

	public BeanAcSucursalgrupomayor coreActivar(SeguridadUsuarioActual usuarioActual, String psucursalgrupomayor) throws Exception {
		return coreActivar(usuarioActual,new BeanAcSucursalgrupomayorPk( psucursalgrupomayor));
	}

	@Transactional
	public DtoComunAcSucursalgrupomayor coreEliminarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupomayor dto) throws Exception {
		BeanAcSucursalgrupomayor acSucursalgrupomayor = acSucursalgrupomayorDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupomayor = coreEliminar(usuarioActual, acSucursalgrupomayor);
		dto.setTransaccionEstado(acSucursalgrupomayor.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupomayor.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunAcSucursalgrupomayor coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupomayor dto) throws Exception {
		BeanAcSucursalgrupomayor acSucursalgrupomayor = dto.obtenerBean();
		acSucursalgrupomayor = coreEliminar(usuarioActual, acSucursalgrupomayor);
		dto.setTransaccionEstado(acSucursalgrupomayor.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupomayor.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursalgrupomayor coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		// valores por defecto - preparando objeto
		acSucursalgrupomayor = validar.prepararEliminar(usuarioActual, acSucursalgrupomayor);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, acSucursalgrupomayor);
		if (!lst.isEmpty()) {
			acSucursalgrupomayor.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursalgrupomayor.setTransaccionListaMensajes(lst);
			return acSucursalgrupomayor;
		}
		
		// transaccion
		acSucursalgrupomayorDao.eliminar(acSucursalgrupomayor);
		acSucursalgrupomayor=new BeanAcSucursalgrupomayor();
		acSucursalgrupomayor.setTransaccionEstado(DominioTransaccion.OK);
		return acSucursalgrupomayor;
	}

	public BeanAcSucursalgrupomayor coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupomayorPk pk) throws Exception {
		BeanAcSucursalgrupomayor acSucursalgrupomayor = acSucursalgrupomayorDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,acSucursalgrupomayor);
	}

	public BeanAcSucursalgrupomayor coreEliminar(SeguridadUsuarioActual usuarioActual, String psucursalgrupomayor) throws Exception {
		return coreEliminar(usuarioActual,new BeanAcSucursalgrupomayorPk( psucursalgrupomayor));
	}

}
