package net.royal.spring.seguridad.servicio.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.seguridad.dao.impl.SeguridadperfilusuarioComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanSeguridadperfilusuario;
import net.royal.spring.seguridad.dominio.BeanSeguridadperfilusuarioPk;
import net.royal.spring.seguridad.dominio.filtro.FiltroComunPaginacionPerfilUsuario;
import net.royal.spring.seguridad.servicio.validar.SeguridadperfilusuarioServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioSeguridadperfilusuario")
public class SeguridadperfilusuarioServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSeguridadperfilusuario";

	@Autowired
	private SeguridadperfilusuarioComunDaoImpl seguridadperfilusuarioDao;

	@Autowired
	private SeguridadperfilusuarioServicioValidar validar;

	@Transactional
	public BeanSeguridadperfilusuario coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSeguridadperfilusuario seguridadperfilusuario) throws UException {
		seguridadperfilusuario = validar.prepararInsertar(usuarioActual, seguridadperfilusuario);
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, seguridadperfilusuario);
		if (!lst.isEmpty())
			throw new UException(lst);
		return seguridadperfilusuarioDao.coreInsertar(seguridadperfilusuario);
	}

	@Transactional
	public BeanSeguridadperfilusuario coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSeguridadperfilusuario seguridadperfilusuario) throws UException {
		seguridadperfilusuario = validar.prepararActualizar(usuarioActual, seguridadperfilusuario);
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, seguridadperfilusuario);
		if (!lst.isEmpty())
			throw new UException(lst);
	    seguridadperfilusuario = seguridadperfilusuarioDao.coreActualizar(seguridadperfilusuario);
		return seguridadperfilusuario;
	}

	@Transactional
	public BeanSeguridadperfilusuario coreAnular(SeguridadUsuarioActual usuarioActual, BeanSeguridadperfilusuario seguridadperfilusuario) throws UException {
		seguridadperfilusuario = validar.prepararAnular(usuarioActual, seguridadperfilusuario);
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, seguridadperfilusuario);
		if (!lst.isEmpty())
			throw new UException(lst);
		return seguridadperfilusuarioDao.coreActualizar(seguridadperfilusuario);
	}

	public BeanSeguridadperfilusuario coreAnular(SeguridadUsuarioActual usuarioActual, BeanSeguridadperfilusuarioPk pk) throws UException {
		BeanSeguridadperfilusuario bean = seguridadperfilusuarioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanSeguridadperfilusuario coreAnular(SeguridadUsuarioActual usuarioActual, String pperfil,String pusuario) throws UException {
		return coreAnular(usuarioActual,new BeanSeguridadperfilusuarioPk( pperfil, pusuario));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSeguridadperfilusuario seguridadperfilusuario) throws UException {
		seguridadperfilusuario = validar.prepararEliminar(usuarioActual, seguridadperfilusuario);
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, seguridadperfilusuario);
		if (!lst.isEmpty())
			throw new UException(lst);
		seguridadperfilusuarioDao.eliminar(seguridadperfilusuario);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSeguridadperfilusuarioPk pk) throws UException {
		BeanSeguridadperfilusuario seguridadperfilusuario = seguridadperfilusuarioDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,seguridadperfilusuario);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pperfil,String pusuario) throws UException {
		coreEliminar(usuarioActual,new BeanSeguridadperfilusuarioPk( pperfil, pusuario));
	}

	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void coreEliminarPruebas() throws UException {
		Criteria cri = seguridadperfilusuarioDao.getCriteria();
		cri.add(Restrictions.eq("creacionUsuario","PRUEBA-USUARIO"));
		List lst = seguridadperfilusuarioDao.listarPorCriterios(cri);
		seguridadperfilusuarioDao.eliminar(lst);
	}
	public DominioPaginacion listar(FiltroComunPaginacionPerfilUsuario filtro) {
		return seguridadperfilusuarioDao.listar(filtro);
	}	
	public DominioPaginacion listaruserbyperfil(FiltroComunPaginacionPerfilUsuario filtro) {
		return seguridadperfilusuarioDao.listaruserbyperfil(filtro);
	}

}
