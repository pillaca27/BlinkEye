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
import net.royal.spring.seguridad.dao.impl.SeguridadautorizacionreporteComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizacionreporte;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizacionreportePk;
import net.royal.spring.seguridad.servicio.validar.SeguridadautorizacionreporteServicioValidar;

@Service (value = "BeanServicioSeguridadautorizacionreporte")
public class SeguridadautorizacionreporteServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSeguridadautorizacionreporte";
	
	@Autowired
	private SeguridadautorizacionreporteComunDaoImpl seguridadautorizacionreporteDao;

	@Autowired
	private SeguridadautorizacionreporteServicioValidar validar;

	@Transactional
	public BeanSeguridadautorizacionreporte coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSeguridadautorizacionreporte seguridadautorizacionreporte) throws UException {
		seguridadautorizacionreporte = validar.prepararInsertar(usuarioActual, seguridadautorizacionreporte);
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, seguridadautorizacionreporte);
		if (!lst.isEmpty())
			throw new UException(lst);
		return seguridadautorizacionreporteDao.coreInsertar(seguridadautorizacionreporte);
	}

	@Transactional
	public BeanSeguridadautorizacionreporte coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizacionreporte seguridadautorizacionreporte) throws UException {
		seguridadautorizacionreporte = validar.prepararActualizar(usuarioActual, seguridadautorizacionreporte);
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, seguridadautorizacionreporte);
		if (!lst.isEmpty())
			throw new UException(lst);
	    seguridadautorizacionreporte = seguridadautorizacionreporteDao.coreActualizar(seguridadautorizacionreporte);
		return seguridadautorizacionreporte;
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizacionreporte seguridadautorizacionreporte) throws UException {
		seguridadautorizacionreporte = validar.prepararEliminar(usuarioActual, seguridadautorizacionreporte);
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, seguridadautorizacionreporte);
		if (!lst.isEmpty())
			throw new UException(lst);
		seguridadautorizacionreporteDao.eliminar(seguridadautorizacionreporte);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizacionreportePk pk) throws UException {
		BeanSeguridadautorizacionreporte seguridadautorizacionreporte = seguridadautorizacionreporteDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,seguridadautorizacionreporte);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pusuario,String paplicacioncodigo,String preportecodigo) throws UException {
		coreEliminar(usuarioActual,new BeanSeguridadautorizacionreportePk( pusuario, paplicacioncodigo, preportecodigo));
	}

	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void coreEliminarPruebas() throws UException {
		Criteria cri = seguridadautorizacionreporteDao.getCriteria();
		cri.add(Restrictions.eq("creacionUsuario","PRUEBA-USUARIO"));
		List lst = seguridadautorizacionreporteDao.listarPorCriterios(cri);
		seguridadautorizacionreporteDao.eliminar(lst);
	}
	/************/
	@Transactional
	public BeanSeguridadautorizacionreporte coreRegistrarOactualizar(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizacionreporte seguridadautorizacionreporte) throws UException {
		seguridadautorizacionreporte = validar.prepararActualizar(usuarioActual, seguridadautorizacionreporte);
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, seguridadautorizacionreporte);
		if (!lst.isEmpty())
			throw new UException(lst);
	    seguridadautorizacionreporte = seguridadautorizacionreporteDao.coreRegistrarOactualizar(seguridadautorizacionreporte);
		return seguridadautorizacionreporte;
	}
}
