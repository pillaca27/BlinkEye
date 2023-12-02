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
import net.royal.spring.core.dao.impl.ZonadespachoDaoImpl;

import net.royal.spring.core.dominio.BeanZonadespacho;
import net.royal.spring.core.dominio.BeanZonadespachoPk;
import net.royal.spring.core.dominio.dto.DtoComunZonadespacho;
import net.royal.spring.core.servicio.validar.ZonadespachoServicioValidar;

@Service (value = "BeanServicioZonadespacho")
public class ZonadespachoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioZonadespacho";
	private static Logger logger = LogManager.getLogger(ZonadespachoServicioImpl.class);

	@Autowired
	private ZonadespachoDaoImpl zonadespachoDao;

	@Autowired
	private ZonadespachoServicioValidar validar;

	@Transactional
	public DtoComunZonadespacho coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunZonadespacho dto) throws UException {
		BeanZonadespacho zonadespacho = dto.obtenerBean();
		zonadespacho = coreInsertar(usuarioActual, zonadespacho);
		dto.setTransaccionEstado(zonadespacho.getTransaccionEstado());
		dto.setTransaccionListaMensajes(zonadespacho.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanZonadespacho coreInsertar(SeguridadUsuarioActual usuarioActual,BeanZonadespacho zonadespacho) throws UException {
		// valores por defecto - preparando objeto
		zonadespacho = validar.prepararInsertar(usuarioActual, zonadespacho);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, zonadespacho);
		if (!lst.isEmpty()) {
			zonadespacho.setTransaccionEstado(DominioTransaccion.VALIDACION);
			zonadespacho.setTransaccionListaMensajes(lst);
			return zonadespacho;
		}
		
		// transaccion
		zonadespacho = zonadespachoDao.coreInsertar(zonadespacho);
		zonadespacho.setTransaccionEstado(DominioTransaccion.OK);
		zonadespacho.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return zonadespacho;
	}

	@Transactional
	public DtoComunZonadespacho coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunZonadespacho dto) throws UException {
		BeanZonadespacho zonadespacho = dto.obtenerBean();
		zonadespacho = coreActualizar(usuarioActual, zonadespacho);
		dto.setTransaccionEstado(zonadespacho.getTransaccionEstado());
		dto.setTransaccionListaMensajes(zonadespacho.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanZonadespacho coreActualizar(SeguridadUsuarioActual usuarioActual, BeanZonadespacho zonadespacho) throws UException {
		// valores por defecto - preparando objeto
		zonadespacho = validar.prepararActualizar(usuarioActual, zonadespacho);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, zonadespacho);
		if (!lst.isEmpty()) {
			zonadespacho.setTransaccionEstado(DominioTransaccion.VALIDACION);
			zonadespacho.setTransaccionListaMensajes(lst);
			return zonadespacho;
		}
		
		// transaccion
	    zonadespacho = zonadespachoDao.coreActualizar(zonadespacho);
		zonadespacho.setTransaccionEstado(DominioTransaccion.OK);
		zonadespacho.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return zonadespacho;
	}


	@Transactional
	public DtoComunZonadespacho coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunZonadespacho dto) throws UException {
		BeanZonadespacho zonadespacho = dto.obtenerBean();
		zonadespacho = coreAnular(usuarioActual, zonadespacho);
		dto.setTransaccionEstado(zonadespacho.getTransaccionEstado());
		dto.setTransaccionListaMensajes(zonadespacho.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanZonadespacho coreAnular(SeguridadUsuarioActual usuarioActual, BeanZonadespacho zonadespacho) throws UException {
		// valores por defecto - preparando objeto
		zonadespacho = validar.prepararAnular(usuarioActual, zonadespacho);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, zonadespacho);
		if (!lst.isEmpty()) {
			zonadespacho.setTransaccionEstado(DominioTransaccion.VALIDACION);
			zonadespacho.setTransaccionListaMensajes(lst);
			return zonadespacho;
		}
		
		// transaccion
	    zonadespacho.setEstado("I");
	    zonadespacho = zonadespachoDao.coreActualizar(zonadespacho);
		zonadespacho.setTransaccionEstado(DominioTransaccion.OK);
		zonadespacho.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return zonadespacho;
	}

	public BeanZonadespacho coreAnular(SeguridadUsuarioActual usuarioActual, BeanZonadespachoPk pk) throws UException {
		BeanZonadespacho bean = zonadespachoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanZonadespacho coreAnular(SeguridadUsuarioActual usuarioActual, String pzonadespacho) throws UException {
		return coreAnular(usuarioActual,new BeanZonadespachoPk( pzonadespacho));
	}

	@Transactional
	public DtoComunZonadespacho coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunZonadespacho dto) throws UException {
		BeanZonadespacho zonadespacho = dto.obtenerBean();
		zonadespacho = coreEliminar(usuarioActual, zonadespacho);
		dto.setTransaccionEstado(zonadespacho.getTransaccionEstado());
		dto.setTransaccionListaMensajes(zonadespacho.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanZonadespacho coreEliminar(SeguridadUsuarioActual usuarioActual, BeanZonadespacho zonadespacho) throws UException {
		// valores por defecto - preparando objeto
		zonadespacho = validar.prepararEliminar(usuarioActual, zonadespacho);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, zonadespacho);
		if (!lst.isEmpty()) {
			zonadespacho.setTransaccionEstado(DominioTransaccion.VALIDACION);
			zonadespacho.setTransaccionListaMensajes(lst);
			return zonadespacho;
		}
		
		// transaccion
		zonadespachoDao.eliminar(zonadespacho);
		zonadespacho=new BeanZonadespacho();
		zonadespacho.setTransaccionEstado(DominioTransaccion.OK);
		return zonadespacho;
	}

	public BeanZonadespacho coreEliminar(SeguridadUsuarioActual usuarioActual, BeanZonadespachoPk pk) throws UException {
		BeanZonadespacho zonadespacho = zonadespachoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,zonadespacho);
	}

	public BeanZonadespacho coreEliminar(SeguridadUsuarioActual usuarioActual, String pzonadespacho) throws UException {
		return coreEliminar(usuarioActual,new BeanZonadespachoPk( pzonadespacho));
	}

}
