package net.royal.spring.dt.servicio.impl;

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
import net.royal.spring.dt.dao.impl.MicrosuenosDaoImpl;

import net.royal.spring.dt.dominio.Microsuenos;
import net.royal.spring.dt.dominio.MicrosuenosPk;
import net.royal.spring.dt.dominio.dto.DtoMicrosuenos;

@Service (value = "BeanServicioMicrosuenos")
public class MicrosuenosServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioMicrosuenos";
	private static Logger logger = LogManager.getLogger(MicrosuenosServicioImpl.class);

	@Autowired
	private MicrosuenosDaoImpl microsuenosDao;

	@Autowired
	private MicrosuenosServicioValidar validar;

	@Transactional
	public DtoMicrosuenos coreInsertar(SeguridadUsuarioActual usuarioActual,DtoMicrosuenos dto) throws Exception {
		Microsuenos microsuenos = dto.obtenerBeanRegistrar();
		microsuenos = coreInsertar(usuarioActual, microsuenos);
		dto.setTransaccionEstado(microsuenos.getTransaccionEstado());
		dto.setTransaccionListaMensajes(microsuenos.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Microsuenos coreInsertar(SeguridadUsuarioActual usuarioActual,Microsuenos microsuenos) throws Exception {
		// valores por defecto - preparando objeto
		microsuenos = validar.prepararInsertar(usuarioActual, microsuenos);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, microsuenos);
		if (!lst.isEmpty()) {
			microsuenos.setTransaccionEstado(DominioTransaccion.VALIDACION);
			microsuenos.setTransaccionListaMensajes(lst);
			return microsuenos;
		}
		
		// transaccion
		microsuenos = microsuenosDao.coreInsertar(microsuenos);
		microsuenos.setTransaccionEstado(DominioTransaccion.OK);
		microsuenos.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return microsuenos;
	}

	@Transactional
	public DtoMicrosuenos coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoMicrosuenos dto) throws Exception {
		Microsuenos microsuenos = microsuenosDao.obtenerPorId(dto.getIdMicrosueno());
		microsuenos = dto.obtenerBeanActualizar(microsuenos);
		microsuenos = coreActualizar(usuarioActual, microsuenos);
		dto.setTransaccionEstado(microsuenos.getTransaccionEstado());
		dto.setTransaccionListaMensajes(microsuenos.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoMicrosuenos coreActualizar(SeguridadUsuarioActual usuarioActual,DtoMicrosuenos dto) throws Exception {
		Microsuenos microsuenos = dto.obtenerBean();
		microsuenos = coreActualizar(usuarioActual, microsuenos);
		dto.setTransaccionEstado(microsuenos.getTransaccionEstado());
		dto.setTransaccionListaMensajes(microsuenos.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Microsuenos coreActualizar(SeguridadUsuarioActual usuarioActual, Microsuenos microsuenos) throws Exception {
		// valores por defecto - preparando objeto
		microsuenos = validar.prepararActualizar(usuarioActual, microsuenos);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, microsuenos);
		if (!lst.isEmpty()) {
			microsuenos.setTransaccionEstado(DominioTransaccion.VALIDACION);
			microsuenos.setTransaccionListaMensajes(lst);
			return microsuenos;
		}
		
		// transaccion
	    microsuenos = microsuenosDao.coreActualizar(microsuenos);
		microsuenos.setTransaccionEstado(DominioTransaccion.OK);
		microsuenos.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return microsuenos;
	}

	@Transactional
	public DtoMicrosuenos coreEliminar(SeguridadUsuarioActual usuarioActual,DtoMicrosuenos dto) throws Exception {
		Microsuenos microsuenos = dto.obtenerBean();
		microsuenos = coreEliminar(usuarioActual, microsuenos);
		dto.setTransaccionEstado(microsuenos.getTransaccionEstado());
		dto.setTransaccionListaMensajes(microsuenos.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Microsuenos coreEliminar(SeguridadUsuarioActual usuarioActual, Microsuenos microsuenos) throws Exception {
		// valores por defecto - preparando objeto
		microsuenos = validar.prepararEliminar(usuarioActual, microsuenos);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, microsuenos);
		if (!lst.isEmpty()) {
			microsuenos.setTransaccionEstado(DominioTransaccion.VALIDACION);
			microsuenos.setTransaccionListaMensajes(lst);
			return microsuenos;
		}
		
		// transaccion
		microsuenosDao.eliminar(microsuenos);
		microsuenos=new Microsuenos();
		microsuenos.setTransaccionEstado(DominioTransaccion.OK);
		return microsuenos;
	}

	public Microsuenos coreEliminar(SeguridadUsuarioActual usuarioActual, MicrosuenosPk pk) throws Exception {
		Microsuenos microsuenos = microsuenosDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,microsuenos);
	}

	public Microsuenos coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidMicrosueno) throws Exception {
		return coreEliminar(usuarioActual,new MicrosuenosPk( pidMicrosueno));
	}

}
