package net.royal.spring.core.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
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
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.core.dao.impl.ProveedormastDaoImpl;

import net.royal.spring.core.dominio.BeanProveedormast;
import net.royal.spring.core.dominio.BeanProveedormastPk;
import net.royal.spring.core.dominio.dto.DtoComunProveedormast;
import net.royal.spring.core.servicio.validar.ProveedormastServicioValidar;

@Service (value = "BeanServicioProveedormast")
public class ProveedormastServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioProveedormast";
	private static Logger logger = LogManager.getLogger(ProveedormastServicioImpl.class);

	@Autowired
	private ProveedormastDaoImpl proveedormastDao;

	@Autowired
	private ProveedormastServicioValidar validar;

	@Transactional
	public DtoComunProveedormast coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunProveedormast dto) throws UException {
		BeanProveedormast proveedormast = dto.obtenerBean();
		proveedormast = coreInsertar(usuarioActual, proveedormast);
		dto.setTransaccionEstado(proveedormast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(proveedormast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanProveedormast coreInsertar(SeguridadUsuarioActual usuarioActual,BeanProveedormast proveedormast) throws UException {
		// valores por defecto - preparando objeto
		proveedormast = validar.prepararInsertar(usuarioActual, proveedormast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, proveedormast);
		if (!lst.isEmpty()) {
			proveedormast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			proveedormast.setTransaccionListaMensajes(lst);
			return proveedormast;
		}
		
		// transaccion
		proveedormast = proveedormastDao.coreInsertar(proveedormast);
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, proveedormast.getPk().getProveedor()));
		proveedormastDao.ejecutarPorQuery("proveedormast.actualizarproveedor", parametros);
		
		proveedormast.setTransaccionEstado(DominioTransaccion.OK);
		proveedormast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return proveedormast;
	}

	@Transactional
	public DtoComunProveedormast coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunProveedormast dto) throws UException {
		BeanProveedormast proveedormast = dto.obtenerBean();
		proveedormast = coreActualizar(usuarioActual, proveedormast);
		dto.setTransaccionEstado(proveedormast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(proveedormast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanProveedormast coreActualizar(SeguridadUsuarioActual usuarioActual, BeanProveedormast proveedormast) throws UException {
		// valores por defecto - preparando objeto
		proveedormast = validar.prepararActualizar(usuarioActual, proveedormast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, proveedormast);
		if (!lst.isEmpty()) {
			proveedormast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			proveedormast.setTransaccionListaMensajes(lst);
			return proveedormast;
		}
		
		// transaccion
	    proveedormast = proveedormastDao.coreActualizar(proveedormast);
		proveedormast.setTransaccionEstado(DominioTransaccion.OK);
		proveedormast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return proveedormast;
	}

	@Transactional
	public DtoComunProveedormast coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunProveedormast dto) throws UException {
		BeanProveedormast proveedormast = dto.obtenerBean();
		Integer id= dto.getProveedor();
		proveedormast = coreEliminar(usuarioActual, proveedormast);
		
		if(id != null) {
			
			List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
			parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, id));

			proveedormastDao.ejecutarPorQuery("proveedormast.actualizarpersona", parametros);
		}
		dto.setTransaccionEstado(proveedormast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(proveedormast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanProveedormast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanProveedormast proveedormast) throws UException {
		// valores por defecto - preparando objeto
		proveedormast = validar.prepararEliminar(usuarioActual, proveedormast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, proveedormast);
		if (!lst.isEmpty()) {
			proveedormast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			proveedormast.setTransaccionListaMensajes(lst);
			return proveedormast;
		}
		
		// transaccion
		proveedormastDao.eliminar(proveedormast);
		proveedormast=new BeanProveedormast();
		proveedormast.setTransaccionEstado(DominioTransaccion.OK);
		return proveedormast;
	}

	public BeanProveedormast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanProveedormastPk pk) throws UException {
		BeanProveedormast proveedormast = proveedormastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,proveedormast);
	}

	public BeanProveedormast coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pproveedor) throws UException {
		return coreEliminar(usuarioActual,new BeanProveedormastPk( pproveedor));
	}

}
