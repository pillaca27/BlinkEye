package net.royal.spring.core.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dao.impl.EmpleadomastDaoImpl;
import net.royal.spring.core.dominio.BeanEmpleadomast;
import net.royal.spring.core.dominio.BeanEmpleadomastPk;
import net.royal.spring.core.dominio.dto.DtoComunEmpleadomast;
import net.royal.spring.core.servicio.validar.EmpleadomastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioEmpleadomast")
public class EmpleadomastServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioEmpleadomast";
	private static Logger logger = LogManager.getLogger(EmpleadomastServicioImpl.class);

	@Autowired
	private EmpleadomastDaoImpl empleadomastDao;

	@Autowired
	private EmpleadomastServicioValidar validar;

	@Transactional
	public DtoComunEmpleadomast coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunEmpleadomast dto) throws UException {
		BeanEmpleadomast empleadomast = dto.obtenerBean();
		empleadomast = coreInsertar(usuarioActual, empleadomast);
		dto.setTransaccionEstado(empleadomast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(empleadomast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanEmpleadomast coreInsertar(SeguridadUsuarioActual usuarioActual,BeanEmpleadomast empleadomast) throws UException {
		// valores por defecto - preparando objeto
		empleadomast = validar.prepararInsertar(usuarioActual, empleadomast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, empleadomast);
		if (!lst.isEmpty()) {
			empleadomast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			empleadomast.setTransaccionListaMensajes(lst);
			return empleadomast;
		}
		
		// transaccion
		empleadomast = empleadomastDao.coreInsertar(empleadomast);
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_empleado", Integer.class, empleadomast.getPk().getEmpleado()));
		empleadomastDao.ejecutarPorQuery("empleadomast.actualizarempleado", parametros);
		
		empleadomast.setTransaccionEstado(DominioTransaccion.OK);
		empleadomast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return empleadomast;
	}

	@Transactional
	public DtoComunEmpleadomast coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunEmpleadomast dto) throws UException {
		BeanEmpleadomast empleadomast = dto.obtenerBean();
		empleadomast = coreActualizar(usuarioActual, empleadomast);
		dto.setTransaccionEstado(empleadomast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(empleadomast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanEmpleadomast coreActualizar(SeguridadUsuarioActual usuarioActual, BeanEmpleadomast empleadomast) throws UException {
		// valores por defecto - preparando objeto
		empleadomast = validar.prepararActualizar(usuarioActual, empleadomast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, empleadomast);
		if (!lst.isEmpty()) {
			empleadomast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			empleadomast.setTransaccionListaMensajes(lst);
			return empleadomast;
		}
		
		// transaccion
	    empleadomast = empleadomastDao.coreActualizar(empleadomast);
		empleadomast.setTransaccionEstado(DominioTransaccion.OK);
		empleadomast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return empleadomast;
	}


	@Transactional
	public DtoComunEmpleadomast coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunEmpleadomast dto) throws UException {
		BeanEmpleadomast empleadomast = dto.obtenerBean();
		empleadomast = coreAnular(usuarioActual, empleadomast);
		dto.setTransaccionEstado(empleadomast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(empleadomast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanEmpleadomast coreAnular(SeguridadUsuarioActual usuarioActual, BeanEmpleadomast empleadomast) throws UException {
		// valores por defecto - preparando objeto
		empleadomast = validar.prepararAnular(usuarioActual, empleadomast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, empleadomast);
		if (!lst.isEmpty()) {
			empleadomast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			empleadomast.setTransaccionListaMensajes(lst);
			return empleadomast;
		}
		
		// transaccion
	    empleadomast.setEstado("I");
	    empleadomast = empleadomastDao.coreActualizar(empleadomast);
		empleadomast.setTransaccionEstado(DominioTransaccion.OK);
		empleadomast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return empleadomast;
	}

	public BeanEmpleadomast coreAnular(SeguridadUsuarioActual usuarioActual, BeanEmpleadomastPk pk) throws UException {
		BeanEmpleadomast bean = empleadomastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanEmpleadomast coreAnular(SeguridadUsuarioActual usuarioActual, Integer pempleado,String pcompaniasocio) throws UException {
		return coreAnular(usuarioActual,new BeanEmpleadomastPk( pempleado, pcompaniasocio));
	}

	@Transactional
	public DtoComunEmpleadomast coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunEmpleadomast dto) throws UException {
		BeanEmpleadomast empleadomast = dto.obtenerBean();
		empleadomast = coreEliminar(usuarioActual, empleadomast);
		
		Integer id= dto.getEmpleado();
		if(id != null && empleadomast.getTransaccionEstado().equals("OK")) {		
			List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
			parametros.add(new DominioParametroPersistencia("p_empleado", Integer.class, id));
			empleadomastDao.ejecutarPorQuery("empleadomast.actualizarpersona", parametros);
		}
		
		dto.setTransaccionEstado(empleadomast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(empleadomast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanEmpleadomast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanEmpleadomast empleadomast) throws UException {
		// valores por defecto - preparando objeto
		empleadomast = validar.prepararEliminar(usuarioActual, empleadomast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, empleadomast);
		if (!lst.isEmpty()) {
			empleadomast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			empleadomast.setTransaccionListaMensajes(lst);
			return empleadomast;
		}
		
		// transaccion
		empleadomastDao.eliminar(empleadomast);
		empleadomast=new BeanEmpleadomast();
		empleadomast.setTransaccionEstado(DominioTransaccion.OK);
		return empleadomast;
	}

	public BeanEmpleadomast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanEmpleadomastPk pk) throws UException {
		BeanEmpleadomast empleadomast = empleadomastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,empleadomast);
	}

	public BeanEmpleadomast coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pempleado,String pcompaniasocio) throws UException {
		return coreEliminar(usuarioActual,new BeanEmpleadomastPk( pempleado, pcompaniasocio));
	}

}
