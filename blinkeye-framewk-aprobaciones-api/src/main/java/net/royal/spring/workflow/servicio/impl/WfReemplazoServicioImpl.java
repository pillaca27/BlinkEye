package net.royal.spring.workflow.servicio.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.workflow.dao.impl.WfReemplazoDaoImpl;
import net.royal.spring.workflow.dominio.WfReemplazo;
import net.royal.spring.workflow.dominio.WfReemplazoPk;
import net.royal.spring.workflow.dominio.filtro.FiltroReemplazo;

@Service(value = "BeanServicioWfReemplazo")
public class WfReemplazoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioWfReemplazo";

	@Autowired
	private WfReemplazoDaoImpl dao;

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, DominioPaginacion paginacion,
			FiltroReemplazo filtro) {
		return dao.listarPaginado(usuarioActual, paginacion, filtro);
	}

	@Transactional
	public WfReemplazoPk registrar(SeguridadUsuarioActual usuarioActual, WfReemplazo bean) {
		bean.setPreparadopor(usuarioActual.getUsuario());
		bean.setFechapreparacion(new Date());
		bean.getPk().setSecuencia(dao.generarSecuencia());
		return dao.registrar(bean);
	}

	@Transactional
	public void reemplazarNivelesAprobacion(WfReemplazo bean, SeguridadUsuarioActual usuarioActual) {
		if (bean.getTipo().equals("D")) {
			dao.reemplazarNivelesAprobacion(bean, usuarioActual);
		}
	}

	@Transactional
	public WfReemplazoPk actualizar(SeguridadUsuarioActual usuarioActual, WfReemplazo bean) {
		bean.setModificadopor(usuarioActual.getUsuario());
		bean.setFechamodificacion(new Date());
		dao.actualizar(bean);
		return bean.getPk();
	}

	@Transactional
	public WfReemplazo obtenerPorId(WfReemplazoPk pk) {
		WfReemplazo bean = dao.obtenerPorId(pk);
		bean.setAntiguoNombre(dao.obtenerNombreCompletoPersona(bean.getEmpleadoAntiguo()));
		bean.setNuevoNombre(dao.obtenerNombreCompletoPersona(bean.getEmpleadoNuevo()));
		return bean;
	}

}
