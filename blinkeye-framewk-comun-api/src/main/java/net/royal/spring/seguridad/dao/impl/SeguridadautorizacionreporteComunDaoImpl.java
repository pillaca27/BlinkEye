package net.royal.spring.seguridad.dao.impl;


import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizacionreporte;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizacionreportePk;

@Repository
public class SeguridadautorizacionreporteComunDaoImpl extends GenericoDaoImpl<BeanSeguridadautorizacionreporte, BeanSeguridadautorizacionreportePk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	public SeguridadautorizacionreporteComunDaoImpl() {
		super("seguridadautorizacionreporte");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSeguridadautorizacionreporte obtenerPorId(String pusuario,String paplicacioncodigo,String preportecodigo) {
		return obtenerPorId(new BeanSeguridadautorizacionreportePk( pusuario, paplicacioncodigo, preportecodigo));
	}

	public BeanSeguridadautorizacionreporte coreInsertar(BeanSeguridadautorizacionreporte bean) {
		this.registrar(bean);
		return bean;
	}

	public BeanSeguridadautorizacionreporte coreInsertar(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizacionreporte bean) {
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public BeanSeguridadautorizacionreporte coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizacionreporte bean) {
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public BeanSeguridadautorizacionreporte coreActualizar(BeanSeguridadautorizacionreporte bean) {
		this.actualizar(bean);
		return bean;
	}
	public BeanSeguridadautorizacionreporte coreRegistrarOactualizar(BeanSeguridadautorizacionreporte bean) {
		this.registrarOactualizar(bean);
		return bean;
	}
}
