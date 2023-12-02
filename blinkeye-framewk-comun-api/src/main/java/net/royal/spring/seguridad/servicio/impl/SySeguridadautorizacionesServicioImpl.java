package net.royal.spring.seguridad.servicio.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.seguridad.dao.impl.SySeguridadautorizacionesComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanSySeguridadautorizaciones;
import net.royal.spring.seguridad.dominio.BeanSySeguridadautorizacionesPk;
import net.royal.spring.seguridad.servicio.validar.SySeguridadautorizacionesServicioValidar;

@Service (value = "BeanServicioSySeguridadautorizaciones")
public class SySeguridadautorizacionesServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSySeguridadautorizaciones";
	
	@Autowired
	private SySeguridadautorizacionesComunDaoImpl sySeguridadautorizacionesDao;

	@Autowired
	private SySeguridadautorizacionesServicioValidar validar;

	@Transactional
	public BeanSySeguridadautorizaciones coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadautorizaciones sySeguridadautorizaciones) throws UException {
		sySeguridadautorizaciones = validar.prepararInsertar(usuarioActual, sySeguridadautorizaciones);
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, sySeguridadautorizaciones);
		if (!lst.isEmpty())
			throw new UException(lst);
		return sySeguridadautorizacionesDao.coreInsertar(sySeguridadautorizaciones);
	}

	@Transactional
	public BeanSySeguridadautorizaciones coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadautorizaciones sySeguridadautorizaciones) throws UException {
		sySeguridadautorizaciones = validar.prepararActualizar(usuarioActual, sySeguridadautorizaciones);
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, sySeguridadautorizaciones);
		if (!lst.isEmpty())
			throw new UException(lst);
	    sySeguridadautorizaciones = sySeguridadautorizacionesDao.coreActualizar(sySeguridadautorizaciones);
		return sySeguridadautorizaciones;
	}

	@Transactional
	public BeanSySeguridadautorizaciones coreAnular(SeguridadUsuarioActual usuarioActual, BeanSySeguridadautorizaciones sySeguridadautorizaciones) throws UException {
		sySeguridadautorizaciones = validar.prepararAnular(usuarioActual, sySeguridadautorizaciones);
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, sySeguridadautorizaciones);
		if (!lst.isEmpty())
			throw new UException(lst);
		return sySeguridadautorizacionesDao.coreActualizar(sySeguridadautorizaciones);
	}

	@Transactional
	public BeanSySeguridadautorizaciones coreAnular(SeguridadUsuarioActual usuarioActual, BeanSySeguridadautorizacionesPk pk) throws UException {
		BeanSySeguridadautorizaciones bean = sySeguridadautorizacionesDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanSySeguridadautorizaciones coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pgrupo,String pconcepto,String pusuario) throws UException {
		return coreAnular(usuarioActual,new BeanSySeguridadautorizacionesPk( paplicacioncodigo, pgrupo, pconcepto, pusuario));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadautorizaciones sySeguridadautorizaciones) throws UException {
		sySeguridadautorizaciones = validar.prepararEliminar(usuarioActual, sySeguridadautorizaciones);
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, sySeguridadautorizaciones);
		if (!lst.isEmpty())
			throw new UException(lst);
		sySeguridadautorizacionesDao.eliminar(sySeguridadautorizaciones);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadautorizacionesPk pk) throws UException {
		BeanSySeguridadautorizaciones sySeguridadautorizaciones = sySeguridadautorizacionesDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,sySeguridadautorizaciones);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pgrupo,String pconcepto,String pusuario) throws UException {
		coreEliminar(usuarioActual,new BeanSySeguridadautorizacionesPk( paplicacioncodigo, pgrupo, pconcepto, pusuario));
	}

	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void coreEliminarPruebas() throws UException {
		Criteria cri = sySeguridadautorizacionesDao.getCriteria();
		cri.add(Restrictions.eq("creacionUsuario","PRUEBA-USUARIO"));
		List lst = sySeguridadautorizacionesDao.listarPorCriterios(cri);
		sySeguridadautorizacionesDao.eliminar(lst);
	}
	/****/
	@Transactional
	public BeanSySeguridadautorizaciones coreRegistrarOactualizar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadautorizaciones sySeguridadautorizaciones) throws UException {
		sySeguridadautorizaciones = validar.prepararActualizar(usuarioActual, sySeguridadautorizaciones);
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, sySeguridadautorizaciones);
		if (!lst.isEmpty())
			throw new UException(lst);
	    sySeguridadautorizaciones = sySeguridadautorizacionesDao.coreRegistrarOactualizar(sySeguridadautorizaciones);
		return sySeguridadautorizaciones;
	}
}
