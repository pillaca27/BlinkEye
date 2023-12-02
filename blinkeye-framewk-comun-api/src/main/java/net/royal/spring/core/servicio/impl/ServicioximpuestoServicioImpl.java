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
import net.royal.spring.core.dao.impl.ServicioximpuestoDaoImpl;

import net.royal.spring.core.dominio.BeanServicioximpuesto;
import net.royal.spring.core.dominio.BeanServicioximpuestoPk;
import net.royal.spring.core.dominio.dto.DtoComunServicioximpuesto;
import net.royal.spring.core.servicio.validar.ServicioximpuestoServicioValidar;

@Service (value = "BeanServicioServicioximpuesto")
public class ServicioximpuestoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioServicioximpuesto";
	private static Logger logger = LogManager.getLogger(ServicioximpuestoServicioImpl.class);

	@Autowired
	private ServicioximpuestoDaoImpl servicioximpuestoDao;

	@Autowired
	private ServicioximpuestoServicioValidar validar;

	@Transactional
	public DtoComunServicioximpuesto coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunServicioximpuesto dto) throws UException {
		BeanServicioximpuesto servicioximpuesto = dto.obtenerBean();
		servicioximpuesto = coreInsertar(usuarioActual, servicioximpuesto);
		dto.setTransaccionEstado(servicioximpuesto.getTransaccionEstado());
		dto.setTransaccionListaMensajes(servicioximpuesto.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanServicioximpuesto coreInsertar(SeguridadUsuarioActual usuarioActual,BeanServicioximpuesto servicioximpuesto) throws UException {
		// valores por defecto - preparando objeto
		servicioximpuesto = validar.prepararInsertar(usuarioActual, servicioximpuesto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, servicioximpuesto);
		if (!lst.isEmpty()) {
			servicioximpuesto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			servicioximpuesto.setTransaccionListaMensajes(lst);
			return servicioximpuesto;
		}
		
		// transaccion
		servicioximpuesto = servicioximpuestoDao.coreInsertar(servicioximpuesto);
		servicioximpuesto.setTransaccionEstado(DominioTransaccion.OK);
		servicioximpuesto.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return servicioximpuesto;
	}

	@Transactional
	public DtoComunServicioximpuesto coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunServicioximpuesto dto) throws UException {
		BeanServicioximpuesto servicioximpuesto = dto.obtenerBean();
		servicioximpuesto = coreActualizar(usuarioActual, servicioximpuesto);
		dto.setTransaccionEstado(servicioximpuesto.getTransaccionEstado());
		dto.setTransaccionListaMensajes(servicioximpuesto.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanServicioximpuesto coreActualizar(SeguridadUsuarioActual usuarioActual, BeanServicioximpuesto servicioximpuesto) throws UException {
		// valores por defecto - preparando objeto
		servicioximpuesto = validar.prepararActualizar(usuarioActual, servicioximpuesto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, servicioximpuesto);
		if (!lst.isEmpty()) {
			servicioximpuesto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			servicioximpuesto.setTransaccionListaMensajes(lst);
			return servicioximpuesto;
		}
		
		// transaccion
	    servicioximpuesto = servicioximpuestoDao.coreActualizar(servicioximpuesto);
		servicioximpuesto.setTransaccionEstado(DominioTransaccion.OK);
		servicioximpuesto.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return servicioximpuesto;
	}

	@Transactional
	public DtoComunServicioximpuesto coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunServicioximpuesto dto) throws UException {
		BeanServicioximpuesto servicioximpuesto = dto.obtenerBean();
		servicioximpuesto = coreEliminar(usuarioActual, servicioximpuesto);
		dto.setTransaccionEstado(servicioximpuesto.getTransaccionEstado());
		dto.setTransaccionListaMensajes(servicioximpuesto.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanServicioximpuesto coreEliminar(SeguridadUsuarioActual usuarioActual, BeanServicioximpuesto servicioximpuesto) throws UException {
		// valores por defecto - preparando objeto
		servicioximpuesto = validar.prepararEliminar(usuarioActual, servicioximpuesto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, servicioximpuesto);
		if (!lst.isEmpty()) {
			servicioximpuesto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			servicioximpuesto.setTransaccionListaMensajes(lst);
			return servicioximpuesto;
		}
		
		// transaccion
		servicioximpuestoDao.eliminar(servicioximpuesto);
		servicioximpuesto=new BeanServicioximpuesto();
		servicioximpuesto.setTransaccionEstado(DominioTransaccion.OK);
		return servicioximpuesto;
	}

	public BeanServicioximpuesto coreEliminar(SeguridadUsuarioActual usuarioActual, BeanServicioximpuestoPk pk) throws UException {
		BeanServicioximpuesto servicioximpuesto = servicioximpuestoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,servicioximpuesto);
	}

	public BeanServicioximpuesto coreEliminar(SeguridadUsuarioActual usuarioActual, String ptiposervicio,String pimpuesto) throws UException {
		return coreEliminar(usuarioActual,new BeanServicioximpuestoPk( ptiposervicio, pimpuesto));
	}

}
