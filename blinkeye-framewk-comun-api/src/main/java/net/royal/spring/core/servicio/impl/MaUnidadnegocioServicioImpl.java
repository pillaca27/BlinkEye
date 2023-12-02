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
import net.royal.spring.core.dao.impl.MaUnidadnegocioDaoImpl;

import net.royal.spring.core.dominio.BeanMaUnidadnegocio;
import net.royal.spring.core.dominio.BeanMaUnidadnegocioPk;
import net.royal.spring.core.dominio.dto.DtoComunMaUnidadnegocio;
import net.royal.spring.core.servicio.validar.MaUnidadnegocioServicioValidar;

@Service (value = "BeanServicioMaUnidadnegocio")
public class MaUnidadnegocioServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioMaUnidadnegocio";
	private static Logger logger = LogManager.getLogger(MaUnidadnegocioServicioImpl.class);

	@Autowired
	private MaUnidadnegocioDaoImpl maUnidadnegocioDao;

	@Autowired
	private MaUnidadnegocioServicioValidar validar;

	@Transactional
	public DtoComunMaUnidadnegocio coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunMaUnidadnegocio dto) throws UException {
		BeanMaUnidadnegocio maUnidadnegocio = dto.obtenerBean();
		maUnidadnegocio = coreInsertar(usuarioActual, maUnidadnegocio);
		dto.setTransaccionEstado(maUnidadnegocio.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maUnidadnegocio.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaUnidadnegocio coreInsertar(SeguridadUsuarioActual usuarioActual,BeanMaUnidadnegocio maUnidadnegocio) throws UException {
		// valores por defecto - preparando objeto
		maUnidadnegocio = validar.prepararInsertar(usuarioActual, maUnidadnegocio);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, maUnidadnegocio);
		if (!lst.isEmpty()) {
			maUnidadnegocio.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maUnidadnegocio.setTransaccionListaMensajes(lst);
			return maUnidadnegocio;
		}
		
		// transaccion
		maUnidadnegocio = maUnidadnegocioDao.coreInsertar(maUnidadnegocio);
		maUnidadnegocio.setTransaccionEstado(DominioTransaccion.OK);
		maUnidadnegocio.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maUnidadnegocio;
	}

	@Transactional
	public DtoComunMaUnidadnegocio coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunMaUnidadnegocio dto) throws Exception {
		BeanMaUnidadnegocio acSucursalgrupo = maUnidadnegocioDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = dto.obtenerBeanActualizar(acSucursalgrupo);
		acSucursalgrupo = coreActualizar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}
	
	@Transactional
	public DtoComunMaUnidadnegocio coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunMaUnidadnegocio dto) throws UException {
		BeanMaUnidadnegocio maUnidadnegocio = dto.obtenerBean();
		maUnidadnegocio = coreActualizar(usuarioActual, maUnidadnegocio);
		dto.setTransaccionEstado(maUnidadnegocio.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maUnidadnegocio.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaUnidadnegocio coreActualizar(SeguridadUsuarioActual usuarioActual, BeanMaUnidadnegocio maUnidadnegocio) throws UException {
		// valores por defecto - preparando objeto
		maUnidadnegocio = validar.prepararActualizar(usuarioActual, maUnidadnegocio);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, maUnidadnegocio);
		if (!lst.isEmpty()) {
			maUnidadnegocio.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maUnidadnegocio.setTransaccionListaMensajes(lst);
			return maUnidadnegocio;
		}
		
		// transaccion
	    maUnidadnegocio = maUnidadnegocioDao.coreActualizar(maUnidadnegocio);
		maUnidadnegocio.setTransaccionEstado(DominioTransaccion.OK);
		maUnidadnegocio.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maUnidadnegocio;
	}


	@Transactional
	public DtoComunMaUnidadnegocio coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunMaUnidadnegocio dto) throws UException {
		BeanMaUnidadnegocio maUnidadnegocio = dto.obtenerBean();
		maUnidadnegocio = coreAnular(usuarioActual, maUnidadnegocio);
		dto.setTransaccionEstado(maUnidadnegocio.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maUnidadnegocio.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaUnidadnegocio coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaUnidadnegocio maUnidadnegocio) throws UException {
		// valores por defecto - preparando objeto
		maUnidadnegocio = validar.prepararAnular(usuarioActual, maUnidadnegocio);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, maUnidadnegocio);
		if (!lst.isEmpty()) {
			maUnidadnegocio.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maUnidadnegocio.setTransaccionListaMensajes(lst);
			return maUnidadnegocio;
		}
		
		// transaccion
	    maUnidadnegocio.setEstado("I");
	    maUnidadnegocio = maUnidadnegocioDao.coreActualizar(maUnidadnegocio);
		maUnidadnegocio.setTransaccionEstado(DominioTransaccion.OK);
		maUnidadnegocio.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maUnidadnegocio;
	}

	public BeanMaUnidadnegocio coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaUnidadnegocioPk pk) throws UException {
		BeanMaUnidadnegocio bean = maUnidadnegocioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanMaUnidadnegocio coreAnular(SeguridadUsuarioActual usuarioActual, String punidadnegocio) throws UException {
		return coreAnular(usuarioActual,new BeanMaUnidadnegocioPk( punidadnegocio));
	}

	@Transactional
	public DtoComunMaUnidadnegocio coreEliminarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunMaUnidadnegocio dto) throws Exception {
		BeanMaUnidadnegocio acSucursalgrupo = maUnidadnegocioDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = coreEliminar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}
	
	@Transactional
	public DtoComunMaUnidadnegocio coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunMaUnidadnegocio dto) throws UException {
		BeanMaUnidadnegocio maUnidadnegocio = dto.obtenerBean();
		maUnidadnegocio = coreEliminar(usuarioActual, maUnidadnegocio);
		dto.setTransaccionEstado(maUnidadnegocio.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maUnidadnegocio.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaUnidadnegocio coreEliminar(SeguridadUsuarioActual usuarioActual, BeanMaUnidadnegocio maUnidadnegocio) throws UException {
		// valores por defecto - preparando objeto
		maUnidadnegocio = validar.prepararEliminar(usuarioActual, maUnidadnegocio);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, maUnidadnegocio);
		if (!lst.isEmpty()) {
			maUnidadnegocio.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maUnidadnegocio.setTransaccionListaMensajes(lst);
			return maUnidadnegocio;
		}
		
		// transaccion
		maUnidadnegocioDao.eliminar(maUnidadnegocio);
		maUnidadnegocio=new BeanMaUnidadnegocio();
		maUnidadnegocio.setTransaccionEstado(DominioTransaccion.OK);
		return maUnidadnegocio;
	}

	public BeanMaUnidadnegocio coreEliminar(SeguridadUsuarioActual usuarioActual, BeanMaUnidadnegocioPk pk) throws UException {
		BeanMaUnidadnegocio maUnidadnegocio = maUnidadnegocioDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,maUnidadnegocio);
	}

	public BeanMaUnidadnegocio coreEliminar(SeguridadUsuarioActual usuarioActual, String punidadnegocio) throws UException {
		return coreEliminar(usuarioActual,new BeanMaUnidadnegocioPk( punidadnegocio));
	}

}
