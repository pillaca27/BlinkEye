package net.royal.spring.seguridad.servicio.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.seguridad.dao.impl.SeguridadautorizacionesComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizaciones;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizacionesPk;
import net.royal.spring.seguridad.dominio.dto.DtoComunSeguridadTabsMenu;
import net.royal.spring.seguridad.dominio.filtro.FiltroComunPaginacionAutorizacionConcepto;
import net.royal.spring.seguridad.dominio.filtro.FiltroComunSeguridadTabsMenu;
import net.royal.spring.seguridad.servicio.validar.SeguridadautorizacionesServicioValidar;

@Service (value = "BeanServicioSeguridadautorizaciones")
public class SeguridadautorizacionesServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSeguridadautorizaciones";

	@Autowired
	private SeguridadautorizacionesComunDaoImpl seguridadautorizacionesDao;

	@Autowired
	private SeguridadautorizacionesServicioValidar validar;

	@Transactional
	public BeanSeguridadautorizaciones coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSeguridadautorizaciones seguridadautorizaciones) throws UException {
		seguridadautorizaciones = validar.prepararInsertar(usuarioActual, seguridadautorizaciones);
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, seguridadautorizaciones);
		if (!lst.isEmpty())
			throw new UException(lst);
		return seguridadautorizacionesDao.coreInsertar(seguridadautorizaciones);
	}

	@Transactional
	public BeanSeguridadautorizaciones coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizaciones seguridadautorizaciones) throws UException {
		seguridadautorizaciones = validar.prepararActualizar(usuarioActual, seguridadautorizaciones);
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, seguridadautorizaciones);
		if (!lst.isEmpty())
			throw new UException(lst);
	    seguridadautorizaciones = seguridadautorizacionesDao.coreActualizar(seguridadautorizaciones);
		return seguridadautorizaciones;
	}

	@Transactional
	public BeanSeguridadautorizaciones coreAnular(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizaciones seguridadautorizaciones) throws UException {
		seguridadautorizaciones = validar.prepararAnular(usuarioActual, seguridadautorizaciones);
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, seguridadautorizaciones);
		if (!lst.isEmpty())
			throw new UException(lst);
		return seguridadautorizacionesDao.coreActualizar(seguridadautorizaciones);
	}

	public BeanSeguridadautorizaciones coreAnular(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizacionesPk pk) throws UException {
		BeanSeguridadautorizaciones bean = seguridadautorizacionesDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanSeguridadautorizaciones coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pgrupo,String pconcepto,String pusuario) throws UException {
		return coreAnular(usuarioActual,new BeanSeguridadautorizacionesPk( paplicacioncodigo, pgrupo, pconcepto, pusuario));
	}
	
	public DominioPaginacion listarFunciones(FiltroComunPaginacionAutorizacionConcepto filtro) {

		return seguridadautorizacionesDao.listarFunciones(filtro);
	}
	public DominioPaginacion listarConceptos(FiltroComunPaginacionAutorizacionConcepto filtro) {

		return seguridadautorizacionesDao.listarConceptos(filtro);
	}
	public DominioPaginacion listarreportesseguridad(FiltroComunPaginacionAutorizacionConcepto filtro) {

		return seguridadautorizacionesDao.listarreportesseguridad(filtro);
	}
	
	
	
	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizaciones seguridadautorizaciones) throws UException {
		seguridadautorizaciones = validar.prepararEliminar(usuarioActual, seguridadautorizaciones);
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, seguridadautorizaciones);
		if (!lst.isEmpty())
			throw new UException(lst);
		seguridadautorizacionesDao.eliminar(seguridadautorizaciones);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizacionesPk pk) throws UException {
		BeanSeguridadautorizaciones seguridadautorizaciones = seguridadautorizacionesDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,seguridadautorizaciones);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pgrupo,String pconcepto,String pusuario) throws UException {
		coreEliminar(usuarioActual,new BeanSeguridadautorizacionesPk( paplicacioncodigo, pgrupo, pconcepto, pusuario));
	}

	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void coreEliminarPruebas() throws UException {
		Criteria cri = seguridadautorizacionesDao.getCriteria();
		cri.add(Restrictions.eq("creacionUsuario","PRUEBA-USUARIO"));
		List lst = seguridadautorizacionesDao.listarPorCriterios(cri);
		seguridadautorizacionesDao.eliminar(lst);
	}

	/******/
	@Transactional
	public BeanSeguridadautorizaciones coreRegistrarOactualizar(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizaciones seguridadautorizaciones) throws UException {
		seguridadautorizaciones = validar.prepararActualizar(usuarioActual, seguridadautorizaciones);
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, seguridadautorizaciones);
		if (!lst.isEmpty())
			throw new UException(lst);
	    seguridadautorizaciones = seguridadautorizacionesDao.coreRegistrarOactualizar(seguridadautorizaciones);
		return seguridadautorizaciones;
	}
	
	@Transactional
	public DtoComunSeguridadTabsMenu obtenerSeguridadTabsMenu(FiltroComunSeguridadTabsMenu filtro, boolean retornarVacio) {
		return seguridadautorizacionesDao.obtenerSeguridadTabsMenu(filtro, retornarVacio);
	}
	@Transactional
	public DtoComunSeguridadTabsMenu obtenerSeguridadTabsMenuGTH(FiltroComunSeguridadTabsMenu filtro) {
		return seguridadautorizacionesDao.obtenerSeguridadTabsMenuGTH(filtro);
	}
	
}
