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
import net.royal.spring.core.dao.impl.DepartmentmstDaoImpl;

import net.royal.spring.core.dominio.BeanDepartmentmst;
import net.royal.spring.core.dominio.BeanDepartmentmstPk;
import net.royal.spring.core.dominio.dto.DtoComunDepartmentmst;
import net.royal.spring.core.servicio.validar.DepartmentmstServicioValidar;

@Service (value = "BeanServicioDepartmentmst")
public class DepartmentmstServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioDepartmentmst";
	private static Logger logger = LogManager.getLogger(DepartmentmstServicioImpl.class);

	@Autowired
	private DepartmentmstDaoImpl departmentmstDao;

	@Autowired
	private DepartmentmstServicioValidar validar;

	@Transactional
	public DtoComunDepartmentmst coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunDepartmentmst dto) throws UException {
		BeanDepartmentmst departmentmst = dto.obtenerBean();
		departmentmst = coreInsertar(usuarioActual, departmentmst);
		dto.setTransaccionEstado(departmentmst.getTransaccionEstado());
		dto.setTransaccionListaMensajes(departmentmst.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanDepartmentmst coreInsertar(SeguridadUsuarioActual usuarioActual,BeanDepartmentmst departmentmst) throws UException {
		// valores por defecto - preparando objeto
		departmentmst = validar.prepararInsertar(usuarioActual, departmentmst);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, departmentmst);
		if (!lst.isEmpty()) {
			departmentmst.setTransaccionEstado(DominioTransaccion.VALIDACION);
			departmentmst.setTransaccionListaMensajes(lst);
			return departmentmst;
		}
		
		// transaccion
		departmentmst = departmentmstDao.coreInsertar(departmentmst);
		departmentmst.setTransaccionEstado(DominioTransaccion.OK);
		departmentmst.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return departmentmst;
	}

	@Transactional
	public DtoComunDepartmentmst coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunDepartmentmst dto) throws UException {
		BeanDepartmentmst departmentmst = dto.obtenerBean();
		departmentmst = coreActualizar(usuarioActual, departmentmst);
		dto.setTransaccionEstado(departmentmst.getTransaccionEstado());
		dto.setTransaccionListaMensajes(departmentmst.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanDepartmentmst coreActualizar(SeguridadUsuarioActual usuarioActual, BeanDepartmentmst departmentmst) throws UException {
		// valores por defecto - preparando objeto
		departmentmst = validar.prepararActualizar(usuarioActual, departmentmst);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, departmentmst);
		if (!lst.isEmpty()) {
			departmentmst.setTransaccionEstado(DominioTransaccion.VALIDACION);
			departmentmst.setTransaccionListaMensajes(lst);
			return departmentmst;
		}
		
		// transaccion
	    departmentmst = departmentmstDao.coreActualizar(departmentmst);
		departmentmst.setTransaccionEstado(DominioTransaccion.OK);
		departmentmst.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return departmentmst;
	}

	@Transactional
	public DtoComunDepartmentmst coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunDepartmentmst dto) throws UException {
		BeanDepartmentmst departmentmst = dto.obtenerBean();
		departmentmst = coreEliminar(usuarioActual, departmentmst);
		dto.setTransaccionEstado(departmentmst.getTransaccionEstado());
		dto.setTransaccionListaMensajes(departmentmst.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanDepartmentmst coreEliminar(SeguridadUsuarioActual usuarioActual, BeanDepartmentmst departmentmst) throws UException {
		// valores por defecto - preparando objeto
		departmentmst = validar.prepararEliminar(usuarioActual, departmentmst);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, departmentmst);
		if (!lst.isEmpty()) {
			departmentmst.setTransaccionEstado(DominioTransaccion.VALIDACION);
			departmentmst.setTransaccionListaMensajes(lst);
			return departmentmst;
		}
		
		// transaccion
		departmentmstDao.eliminar(departmentmst);
		departmentmst=new BeanDepartmentmst();
		departmentmst.setTransaccionEstado(DominioTransaccion.OK);
		return departmentmst;
	}

	public BeanDepartmentmst coreEliminar(SeguridadUsuarioActual usuarioActual, BeanDepartmentmstPk pk) throws UException {
		BeanDepartmentmst departmentmst = departmentmstDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,departmentmst);
	}

	public BeanDepartmentmst coreEliminar(SeguridadUsuarioActual usuarioActual, String pdepartment) throws UException {
		return coreEliminar(usuarioActual,new BeanDepartmentmstPk( pdepartment));
	}

}
