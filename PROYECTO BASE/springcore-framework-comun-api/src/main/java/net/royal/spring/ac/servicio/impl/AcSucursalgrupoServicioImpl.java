package net.royal.spring.ac.servicio.impl;

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
import net.royal.spring.ac.dao.impl.AcSucursalgrupoDaoImpl;

import net.royal.spring.ac.dominio.BeanAcSucursalgrupo;
import net.royal.spring.ac.dominio.BeanAcSucursalgrupoPk;
import net.royal.spring.ac.dominio.dto.DtoComunAcSucursalgrupo;
import net.royal.spring.ac.servicio.validar.AcSucursalgrupoServicioValidar;


@Service (value = "BeanServicioAcSucursalgrupo")
public class AcSucursalgrupoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAcSucursalgrupo";
	private static Logger logger = LogManager.getLogger(AcSucursalgrupoServicioImpl.class);

	@Autowired
	private AcSucursalgrupoDaoImpl acSucursalgrupoDao;

	@Autowired
	private AcSucursalgrupoServicioValidar validar;

	@Transactional
	public DtoComunAcSucursalgrupo coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupo dto) throws Exception {
		BeanAcSucursalgrupo acSucursalgrupo = dto.obtenerBeanRegistrar();
		acSucursalgrupo = coreInsertar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursalgrupo coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		// valores por defecto - preparando objeto
		acSucursalgrupo = validar.prepararInsertar(usuarioActual, acSucursalgrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, acSucursalgrupo);
		if (!lst.isEmpty()) {
			acSucursalgrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursalgrupo.setTransaccionListaMensajes(lst);
			return acSucursalgrupo;
		}
		
		// transaccion
		acSucursalgrupo = acSucursalgrupoDao.coreInsertar(acSucursalgrupo);
		acSucursalgrupo.setTransaccionEstado(DominioTransaccion.OK);
		acSucursalgrupo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acSucursalgrupo;
	}

	@Transactional
	public DtoComunAcSucursalgrupo coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupo dto) throws Exception {
		BeanAcSucursalgrupo acSucursalgrupo = acSucursalgrupoDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = dto.obtenerBeanActualizar(acSucursalgrupo);
		acSucursalgrupo = coreActualizar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunAcSucursalgrupo coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupo dto) throws Exception {
		BeanAcSucursalgrupo acSucursalgrupo = dto.obtenerBean();
		acSucursalgrupo = coreActualizar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursalgrupo coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		// valores por defecto - preparando objeto
		acSucursalgrupo = validar.prepararActualizar(usuarioActual, acSucursalgrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, acSucursalgrupo);
		if (!lst.isEmpty()) {
			acSucursalgrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursalgrupo.setTransaccionListaMensajes(lst);
			return acSucursalgrupo;
		}
		
		// transaccion
	    acSucursalgrupo = acSucursalgrupoDao.coreActualizar(acSucursalgrupo);
		acSucursalgrupo.setTransaccionEstado(DominioTransaccion.OK);
		acSucursalgrupo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acSucursalgrupo;
	}

	@Transactional
	public DtoComunAcSucursalgrupo coreAnularPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupo dto) throws Exception {
		BeanAcSucursalgrupo acSucursalgrupo = acSucursalgrupoDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = coreAnular(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunAcSucursalgrupo coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupo dto) throws Exception {
		BeanAcSucursalgrupo acSucursalgrupo = dto.obtenerBean();
		acSucursalgrupo = coreAnular(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursalgrupo coreAnular(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		// valores por defecto - preparando objeto
		acSucursalgrupo = validar.prepararAnular(usuarioActual, acSucursalgrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, acSucursalgrupo);
		if (!lst.isEmpty()) {
			acSucursalgrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursalgrupo.setTransaccionListaMensajes(lst);
			return acSucursalgrupo;
		}
		
		// transaccion
	    acSucursalgrupo.setEstado("I");
	    acSucursalgrupo = acSucursalgrupoDao.coreActualizar(acSucursalgrupo);
		acSucursalgrupo.setTransaccionEstado(DominioTransaccion.OK);
		acSucursalgrupo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acSucursalgrupo;
	}

	public BeanAcSucursalgrupo coreAnular(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupoPk pk) throws Exception {
		BeanAcSucursalgrupo bean = acSucursalgrupoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanAcSucursalgrupo coreAnular(SeguridadUsuarioActual usuarioActual, String psucursalgrupo) throws Exception {
		return coreAnular(usuarioActual,new BeanAcSucursalgrupoPk( psucursalgrupo));
	}

	@Transactional
	public DtoComunAcSucursalgrupo coreActivarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupo dto) throws Exception {
		BeanAcSucursalgrupo acSucursalgrupo = acSucursalgrupoDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = coreActivar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunAcSucursalgrupo coreActivar(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupo dto) throws Exception {
		BeanAcSucursalgrupo acSucursalgrupo = dto.obtenerBean();
		acSucursalgrupo = coreActivar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursalgrupo coreActivar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		// valores por defecto - preparando objeto
		acSucursalgrupo = validar.prepararActivar(usuarioActual, acSucursalgrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActivar(usuarioActual, acSucursalgrupo);
		if (!lst.isEmpty()) {
			acSucursalgrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursalgrupo.setTransaccionListaMensajes(lst);
			return acSucursalgrupo;
		}
		
		// transaccion
	    acSucursalgrupo.setEstado("A");
	    acSucursalgrupo = acSucursalgrupoDao.coreActualizar(acSucursalgrupo);
		acSucursalgrupo.setTransaccionEstado(DominioTransaccion.OK);
		acSucursalgrupo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acSucursalgrupo;
	}

	public BeanAcSucursalgrupo coreActivar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupoPk pk) throws Exception {
		BeanAcSucursalgrupo bean = acSucursalgrupoDao.obtenerPorId(pk);
		return coreActivar(usuarioActual,bean);
	}

	public BeanAcSucursalgrupo coreActivar(SeguridadUsuarioActual usuarioActual, String psucursalgrupo) throws Exception {
		return coreActivar(usuarioActual,new BeanAcSucursalgrupoPk( psucursalgrupo));
	}

	@Transactional
	public DtoComunAcSucursalgrupo coreEliminarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupo dto) throws Exception {
		BeanAcSucursalgrupo acSucursalgrupo = acSucursalgrupoDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = coreEliminar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunAcSucursalgrupo coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunAcSucursalgrupo dto) throws Exception {
		BeanAcSucursalgrupo acSucursalgrupo = dto.obtenerBean();
		acSucursalgrupo = coreEliminar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcSucursalgrupo coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		// valores por defecto - preparando objeto
		acSucursalgrupo = validar.prepararEliminar(usuarioActual, acSucursalgrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, acSucursalgrupo);
		if (!lst.isEmpty()) {
			acSucursalgrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acSucursalgrupo.setTransaccionListaMensajes(lst);
			return acSucursalgrupo;
		}
		
		// transaccion
		acSucursalgrupoDao.eliminar(acSucursalgrupo);
		acSucursalgrupo=new BeanAcSucursalgrupo();
		acSucursalgrupo.setTransaccionEstado(DominioTransaccion.OK);
		return acSucursalgrupo;
	}

	public BeanAcSucursalgrupo coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupoPk pk) throws Exception {
		BeanAcSucursalgrupo acSucursalgrupo = acSucursalgrupoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,acSucursalgrupo);
	}

	public BeanAcSucursalgrupo coreEliminar(SeguridadUsuarioActual usuarioActual, String psucursalgrupo) throws Exception {
		return coreEliminar(usuarioActual,new BeanAcSucursalgrupoPk( psucursalgrupo));
	}

}
