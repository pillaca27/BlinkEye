package net.royal.spring.sy.servicio.impl;

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
import net.royal.spring.sy.dao.impl.SyProcesomstDaoImpl;
import net.royal.spring.sy.dominio.BeanSyProcesomst;
import net.royal.spring.sy.dominio.BeanSyProcesomstPk;
import net.royal.spring.sy.dominio.dto.DtoComunSyProcesomst;
import net.royal.spring.sy.servicio.validar.SyProcesomstServicioValidar;

@Service (value = "BeanServicioSyProcesomst")
public class SyProcesomstServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSyProcesomst";
	private static Logger logger = LogManager.getLogger(SyProcesomstServicioImpl.class);

	@Autowired
	private SyProcesomstDaoImpl syProcesomstDao;

	@Autowired
	private SyProcesomstServicioValidar validar;

	@Transactional
	public DtoComunSyProcesomst coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunSyProcesomst dto) throws Exception {
		BeanSyProcesomst syProcesomst = dto.obtenerBean();
		syProcesomst = coreInsertar(usuarioActual, syProcesomst);
		dto.setTransaccionEstado(syProcesomst.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syProcesomst.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyProcesomst coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyProcesomst syProcesomst) throws Exception {
		// valores por defecto - preparando objeto
		syProcesomst = validar.prepararInsertar(usuarioActual, syProcesomst);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, syProcesomst);
		if (!lst.isEmpty()) {
			syProcesomst.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syProcesomst.setTransaccionListaMensajes(lst);
			return syProcesomst;
		}
		
		// transaccion
		syProcesomst = syProcesomstDao.coreInsertar(syProcesomst);
		syProcesomst.setTransaccionEstado(DominioTransaccion.OK);
		syProcesomst.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syProcesomst;
	}

	@Transactional
	public DtoComunSyProcesomst coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunSyProcesomst dto) throws Exception {
		BeanSyProcesomst syProcesomst = dto.obtenerBean();
		syProcesomst = coreActualizar(usuarioActual, syProcesomst);
		dto.setTransaccionEstado(syProcesomst.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syProcesomst.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyProcesomst coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyProcesomst syProcesomst) throws Exception {
		// valores por defecto - preparando objeto
		syProcesomst = validar.prepararActualizar(usuarioActual, syProcesomst);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, syProcesomst);
		if (!lst.isEmpty()) {
			syProcesomst.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syProcesomst.setTransaccionListaMensajes(lst);
			return syProcesomst;
		}
		
		// transaccion
	    syProcesomst = syProcesomstDao.coreActualizar(syProcesomst);
		syProcesomst.setTransaccionEstado(DominioTransaccion.OK);
		syProcesomst.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syProcesomst;
	}


	@Transactional
	public DtoComunSyProcesomst coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunSyProcesomst dto) throws Exception {
		BeanSyProcesomst syProcesomst = dto.obtenerBean();
		syProcesomst = coreAnular(usuarioActual, syProcesomst);
		dto.setTransaccionEstado(syProcesomst.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syProcesomst.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyProcesomst coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyProcesomst syProcesomst) throws Exception {
		// valores por defecto - preparando objeto
		syProcesomst = validar.prepararAnular(usuarioActual, syProcesomst);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, syProcesomst);
		if (!lst.isEmpty()) {
			syProcesomst.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syProcesomst.setTransaccionListaMensajes(lst);
			return syProcesomst;
		}
		
		// transaccion
	    syProcesomst.setEstado("I");
	    syProcesomst = syProcesomstDao.coreActualizar(syProcesomst);
		syProcesomst.setTransaccionEstado(DominioTransaccion.OK);
		syProcesomst.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syProcesomst;
	}

	public BeanSyProcesomst coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyProcesomstPk pk) throws Exception {
		BeanSyProcesomst bean = syProcesomstDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanSyProcesomst coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pprocesocodigo) throws Exception {
		return coreAnular(usuarioActual,new BeanSyProcesomstPk( paplicacioncodigo, pprocesocodigo));
	}

	@Transactional
	public DtoComunSyProcesomst coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunSyProcesomst dto) throws Exception {
		BeanSyProcesomst syProcesomst = dto.obtenerBean();
		syProcesomst = coreEliminar(usuarioActual, syProcesomst);
		dto.setTransaccionEstado(syProcesomst.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syProcesomst.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyProcesomst coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyProcesomst syProcesomst) throws Exception {
		// valores por defecto - preparando objeto
		syProcesomst = validar.prepararEliminar(usuarioActual, syProcesomst);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, syProcesomst);
		if (!lst.isEmpty()) {
			syProcesomst.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syProcesomst.setTransaccionListaMensajes(lst);
			return syProcesomst;
		}
		
		// transaccion
		syProcesomstDao.eliminar(syProcesomst);
		syProcesomst=new BeanSyProcesomst();
		syProcesomst.setTransaccionEstado(DominioTransaccion.OK);
		return syProcesomst;
	}

	public BeanSyProcesomst coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyProcesomstPk pk) throws Exception {
		BeanSyProcesomst syProcesomst = syProcesomstDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,syProcesomst);
	}

	public BeanSyProcesomst coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pprocesocodigo) throws Exception {
		return coreEliminar(usuarioActual,new BeanSyProcesomstPk( paplicacioncodigo, pprocesocodigo));
	}

}
