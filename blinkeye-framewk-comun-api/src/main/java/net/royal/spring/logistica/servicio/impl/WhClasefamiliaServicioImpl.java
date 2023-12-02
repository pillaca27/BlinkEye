package net.royal.spring.logistica.servicio.impl;

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
import net.royal.spring.logistica.dao.impl.WhClasefamiliaDaoImpl;

import net.royal.spring.logistica.dominio.BeanWhClasefamilia;
import net.royal.spring.logistica.dominio.BeanWhClasefamiliaPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasefamilia;
import net.royal.spring.logistica.servicio.validar.WhClasefamiliaServicioValidar;

@Service (value = "BeanServicioWhClasefamilia")
public class WhClasefamiliaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioWhClasefamilia";
	private static Logger logger = LogManager.getLogger(WhClasefamiliaServicioImpl.class);

	@Autowired
	private WhClasefamiliaDaoImpl whClasefamiliaDao;

	@Autowired
	private WhClasefamiliaServicioValidar validar;

	@Transactional
	public DtoComunWhClasefamilia coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunWhClasefamilia dto) throws UException {
		BeanWhClasefamilia whClasefamilia = dto.obtenerBean();
		whClasefamilia = coreInsertar(usuarioActual, whClasefamilia);
		dto.setTransaccionEstado(whClasefamilia.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whClasefamilia.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhClasefamilia coreInsertar(SeguridadUsuarioActual usuarioActual,BeanWhClasefamilia whClasefamilia) throws UException {
		// valores por defecto - preparando objeto
		whClasefamilia = validar.prepararInsertar(usuarioActual, whClasefamilia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, whClasefamilia);
		if (!lst.isEmpty()) {
			whClasefamilia.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whClasefamilia.setTransaccionListaMensajes(lst);
			return whClasefamilia;
		}
		
		// transaccion
		whClasefamilia.getDescripcionlocal().toUpperCase();
		whClasefamilia = whClasefamiliaDao.coreInsertar(whClasefamilia);
		whClasefamilia.setTransaccionEstado(DominioTransaccion.OK);
		whClasefamilia.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whClasefamilia;
	}

	@Transactional
	public DtoComunWhClasefamilia coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunWhClasefamilia dto) throws UException {
		BeanWhClasefamilia whClasefamilia = dto.obtenerBean();
		whClasefamilia = coreActualizar(usuarioActual, whClasefamilia);
		dto.setTransaccionEstado(whClasefamilia.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whClasefamilia.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhClasefamilia coreActualizar(SeguridadUsuarioActual usuarioActual, BeanWhClasefamilia whClasefamilia) throws UException {
		// valores por defecto - preparando objeto
		whClasefamilia = validar.prepararActualizar(usuarioActual, whClasefamilia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, whClasefamilia);
		if (!lst.isEmpty()) {
			whClasefamilia.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whClasefamilia.setTransaccionListaMensajes(lst);
			return whClasefamilia;
		}
		
		// transaccion
	    whClasefamilia = whClasefamiliaDao.coreActualizar(whClasefamilia);
		whClasefamilia.setTransaccionEstado(DominioTransaccion.OK);
		whClasefamilia.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whClasefamilia;
	}


	@Transactional
	public DtoComunWhClasefamilia coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunWhClasefamilia dto) throws UException {
		BeanWhClasefamilia whClasefamilia = dto.obtenerBean();
		whClasefamilia = coreAnular(usuarioActual, whClasefamilia);
		dto.setTransaccionEstado(whClasefamilia.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whClasefamilia.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhClasefamilia coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhClasefamilia whClasefamilia) throws UException {
		// valores por defecto - preparando objeto
		whClasefamilia = validar.prepararAnular(usuarioActual, whClasefamilia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, whClasefamilia);
		if (!lst.isEmpty()) {
			whClasefamilia.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whClasefamilia.setTransaccionListaMensajes(lst);
			return whClasefamilia;
		}
		
		// transaccion
	    whClasefamilia.setEstado("I");
	    whClasefamilia = whClasefamiliaDao.coreActualizar(whClasefamilia);
		whClasefamilia.setTransaccionEstado(DominioTransaccion.OK);
		whClasefamilia.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whClasefamilia;
	}

	public BeanWhClasefamilia coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhClasefamiliaPk pk) throws UException {
		BeanWhClasefamilia bean = whClasefamiliaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanWhClasefamilia coreAnular(SeguridadUsuarioActual usuarioActual, String plinea,String pfamilia) throws UException {
		return coreAnular(usuarioActual,new BeanWhClasefamiliaPk( plinea, pfamilia));
	}

	@Transactional
	public DtoComunWhClasefamilia coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunWhClasefamilia dto) throws UException {
		BeanWhClasefamilia whClasefamilia = dto.obtenerBean();
		whClasefamilia = coreEliminar(usuarioActual, whClasefamilia);
		dto.setTransaccionEstado(whClasefamilia.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whClasefamilia.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhClasefamilia coreEliminar(SeguridadUsuarioActual usuarioActual, BeanWhClasefamilia whClasefamilia) throws UException {
		// valores por defecto - preparando objeto
		whClasefamilia = validar.prepararEliminar(usuarioActual, whClasefamilia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, whClasefamilia);
		if (!lst.isEmpty()) {
			whClasefamilia.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whClasefamilia.setTransaccionListaMensajes(lst);
			return whClasefamilia;
		}
		
		// transaccion
		whClasefamiliaDao.eliminar(whClasefamilia);
		whClasefamilia=new BeanWhClasefamilia();
		whClasefamilia.setTransaccionEstado(DominioTransaccion.OK);
		return whClasefamilia;
	}

	public BeanWhClasefamilia coreEliminar(SeguridadUsuarioActual usuarioActual, BeanWhClasefamiliaPk pk) throws UException {
		BeanWhClasefamilia whClasefamilia = whClasefamiliaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,whClasefamilia);
	}

	public BeanWhClasefamilia coreEliminar(SeguridadUsuarioActual usuarioActual, String plinea,String pfamilia) throws UException {
		return coreEliminar(usuarioActual,new BeanWhClasefamiliaPk( plinea, pfamilia));
	}

}
