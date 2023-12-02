package net.royal.spring.seguridad.servicio.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dao.impl.AplicacionesmastDaoImpl;
import net.royal.spring.core.dominio.BeanAplicacionesmast;
import net.royal.spring.core.dominio.BeanAplicacionesmastPk;
import net.royal.spring.core.servicio.validar.AplicacionesmastServicioValidar;
import net.royal.spring.framework.core.UException;
 
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.seguridad.dominio.filtro.FiltroComunPaginacionApliacionesMast;

@Service (value = "BeanServicioAplicacionesmastSeg")
public class AplicacionesmastServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAplicacionesmastSeg";
	 
	@Autowired
	private AplicacionesmastDaoImpl aplicacionesmastDao;

	@Autowired
	private AplicacionesmastServicioValidar validar;

	@Transactional
	public BeanAplicacionesmast coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAplicacionesmast aplicacionesmast) throws UException {
		aplicacionesmast = validar.prepararInsertar(usuarioActual, aplicacionesmast);
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, aplicacionesmast);
		if (!lst.isEmpty())
			throw new UException(lst);
		return aplicacionesmastDao.coreInsertar(aplicacionesmast);
	}

	@Transactional
	public BeanAplicacionesmast coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmast aplicacionesmast) throws UException {
		aplicacionesmast = validar.prepararActualizar(usuarioActual, aplicacionesmast);
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, aplicacionesmast);
		if (!lst.isEmpty())
			throw new UException(lst);
	    aplicacionesmast = aplicacionesmastDao.coreActualizar(aplicacionesmast);
		return aplicacionesmast;
	}

	@Transactional
	public BeanAplicacionesmast coreAnular(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmast aplicacionesmast) throws UException {
		aplicacionesmast = validar.prepararAnular(usuarioActual, aplicacionesmast);
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, aplicacionesmast);
		if (!lst.isEmpty())
			throw new UException(lst);
		return aplicacionesmastDao.coreActualizar(aplicacionesmast);
	}

	@Transactional
	public BeanAplicacionesmast coreAnular(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmastPk pk) throws UException {
		BeanAplicacionesmast bean = aplicacionesmastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	
 
	public BeanAplicacionesmast coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo) throws UException {
		return coreAnular(usuarioActual,new BeanAplicacionesmastPk( paplicacioncodigo));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmast aplicacionesmast) throws UException {
		aplicacionesmast = validar.prepararEliminar(usuarioActual, aplicacionesmast);
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, aplicacionesmast);
		if (!lst.isEmpty())
			throw new UException(lst);
		aplicacionesmastDao.eliminar(aplicacionesmast);
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmastPk pk) throws UException {
		BeanAplicacionesmast aplicacionesmast = aplicacionesmastDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,aplicacionesmast);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo) throws UException {
		coreEliminar(usuarioActual,new BeanAplicacionesmastPk( paplicacioncodigo));
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public void coreEliminarPruebas() throws UException {
		Criteria cri = aplicacionesmastDao.getCriteria();
		cri.add(Restrictions.eq("creacionUsuario","PRUEBA-USUARIO"));
		List lst = aplicacionesmastDao.listarPorCriterios(cri);
		aplicacionesmastDao.eliminar(lst);
	}

	public DominioPaginacion listar(FiltroComunPaginacionApliacionesMast filtro) {

		return aplicacionesmastDao.listar(filtro);
	}
	public DominioPaginacion listarTipoDet(FiltroComunPaginacionApliacionesMast filtro) {

		return aplicacionesmastDao.listarTipodet(filtro);
	}

}
