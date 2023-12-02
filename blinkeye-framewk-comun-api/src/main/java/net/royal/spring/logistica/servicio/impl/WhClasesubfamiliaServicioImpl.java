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
import net.royal.spring.logistica.dao.impl.WhClasesubfamiliaDaoImpl;

import net.royal.spring.logistica.dominio.BeanWhClasesubfamilia;
import net.royal.spring.logistica.dominio.BeanWhClasesubfamiliaPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasesubfamilia;
import net.royal.spring.logistica.servicio.validar.WhClasesubfamiliaServicioValidar;

@Service (value = "BeanServicioWhClasesubfamilia")
public class WhClasesubfamiliaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioWhClasesubfamilia";
	private static Logger logger = LogManager.getLogger(WhClasesubfamiliaServicioImpl.class);

	@Autowired
	private WhClasesubfamiliaDaoImpl whClasesubfamiliaDao;

	@Autowired
	private WhClasesubfamiliaServicioValidar validar;

	@Transactional
	public DtoComunWhClasesubfamilia coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunWhClasesubfamilia dto) throws UException {
		BeanWhClasesubfamilia whClasesubfamilia = dto.obtenerBean();
		whClasesubfamilia = coreInsertar(usuarioActual, whClasesubfamilia);
		dto.setTransaccionEstado(whClasesubfamilia.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whClasesubfamilia.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhClasesubfamilia coreInsertar(SeguridadUsuarioActual usuarioActual,BeanWhClasesubfamilia whClasesubfamilia) throws UException {
		// valores por defecto - preparando objeto
		whClasesubfamilia = validar.prepararInsertar(usuarioActual, whClasesubfamilia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, whClasesubfamilia);
		if (!lst.isEmpty()) {
			whClasesubfamilia.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whClasesubfamilia.setTransaccionListaMensajes(lst);
			return whClasesubfamilia;
		}
		
		// transaccion
		whClasesubfamilia = whClasesubfamiliaDao.coreInsertar(whClasesubfamilia);
		whClasesubfamilia.setTransaccionEstado(DominioTransaccion.OK);
		whClasesubfamilia.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whClasesubfamilia;
	}

	@Transactional
	public DtoComunWhClasesubfamilia coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunWhClasesubfamilia dto) throws UException {
		BeanWhClasesubfamilia whClasesubfamilia = dto.obtenerBean();
		whClasesubfamilia = coreActualizar(usuarioActual, whClasesubfamilia);
		dto.setTransaccionEstado(whClasesubfamilia.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whClasesubfamilia.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhClasesubfamilia coreActualizar(SeguridadUsuarioActual usuarioActual, BeanWhClasesubfamilia whClasesubfamilia) throws UException {
		// valores por defecto - preparando objeto
		whClasesubfamilia = validar.prepararActualizar(usuarioActual, whClasesubfamilia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, whClasesubfamilia);
		if (!lst.isEmpty()) {
			whClasesubfamilia.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whClasesubfamilia.setTransaccionListaMensajes(lst);
			return whClasesubfamilia;
		}
		
		// transaccion
	    whClasesubfamilia = whClasesubfamiliaDao.coreActualizar(whClasesubfamilia);
		whClasesubfamilia.setTransaccionEstado(DominioTransaccion.OK);
		whClasesubfamilia.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whClasesubfamilia;
	}


	@Transactional
	public DtoComunWhClasesubfamilia coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunWhClasesubfamilia dto) throws UException {
		BeanWhClasesubfamilia whClasesubfamilia = dto.obtenerBean();
		whClasesubfamilia = coreAnular(usuarioActual, whClasesubfamilia);
		dto.setTransaccionEstado(whClasesubfamilia.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whClasesubfamilia.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhClasesubfamilia coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhClasesubfamilia whClasesubfamilia) throws UException {
		// valores por defecto - preparando objeto
		whClasesubfamilia = validar.prepararAnular(usuarioActual, whClasesubfamilia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, whClasesubfamilia);
		if (!lst.isEmpty()) {
			whClasesubfamilia.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whClasesubfamilia.setTransaccionListaMensajes(lst);
			return whClasesubfamilia;
		}
		
		// transaccion
	    whClasesubfamilia.setEstado("I");
	    whClasesubfamilia = whClasesubfamiliaDao.coreActualizar(whClasesubfamilia);
		whClasesubfamilia.setTransaccionEstado(DominioTransaccion.OK);
		whClasesubfamilia.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whClasesubfamilia;
	}

	public BeanWhClasesubfamilia coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhClasesubfamiliaPk pk) throws UException {
		BeanWhClasesubfamilia bean = whClasesubfamiliaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanWhClasesubfamilia coreAnular(SeguridadUsuarioActual usuarioActual, String plinea,String pfamilia,String psubfamilia) throws UException {
		return coreAnular(usuarioActual,new BeanWhClasesubfamiliaPk( plinea, pfamilia, psubfamilia));
	}

	@Transactional
	public DtoComunWhClasesubfamilia coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunWhClasesubfamilia dto) throws UException {
		BeanWhClasesubfamilia whClasesubfamilia = dto.obtenerBean();
		whClasesubfamilia = coreEliminar(usuarioActual, whClasesubfamilia);
		dto.setTransaccionEstado(whClasesubfamilia.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whClasesubfamilia.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhClasesubfamilia coreEliminar(SeguridadUsuarioActual usuarioActual, BeanWhClasesubfamilia whClasesubfamilia) throws UException {
		// valores por defecto - preparando objeto
		whClasesubfamilia = validar.prepararEliminar(usuarioActual, whClasesubfamilia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, whClasesubfamilia);
		if (!lst.isEmpty()) {
			whClasesubfamilia.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whClasesubfamilia.setTransaccionListaMensajes(lst);
			return whClasesubfamilia;
		}
		
		// transaccion
		whClasesubfamiliaDao.eliminar(whClasesubfamilia);
		whClasesubfamilia=new BeanWhClasesubfamilia();
		whClasesubfamilia.setTransaccionEstado(DominioTransaccion.OK);
		return whClasesubfamilia;
	}

	public BeanWhClasesubfamilia coreEliminar(SeguridadUsuarioActual usuarioActual, BeanWhClasesubfamiliaPk pk) throws UException {
		BeanWhClasesubfamilia whClasesubfamilia = whClasesubfamiliaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,whClasesubfamilia);
	}

	public BeanWhClasesubfamilia coreEliminar(SeguridadUsuarioActual usuarioActual, String plinea,String pfamilia,String psubfamilia) throws UException {
		return coreEliminar(usuarioActual,new BeanWhClasesubfamiliaPk( plinea, pfamilia, psubfamilia));
	}

}
