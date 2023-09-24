package net.royal.spring.sg.dao.impl;

import java.util.Date;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sg.dominio.BeanSySeguridadautorizaciones;
import net.royal.spring.sg.dominio.BeanSySeguridadautorizacionesPk;

@Repository
public class SySeguridadautorizacionesDaoImpl extends GenericoDaoImpl<BeanSySeguridadautorizaciones, BeanSySeguridadautorizacionesPk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	public SySeguridadautorizacionesDaoImpl() {
		super("syseguridadautorizaciones");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSySeguridadautorizaciones obtenerPorId(String paplicacioncodigo,String pgrupo,String pconcepto,String pusuario) {
		return obtenerPorId(new BeanSySeguridadautorizacionesPk( paplicacioncodigo, pgrupo, pconcepto, pusuario));
	}

	public BeanSySeguridadautorizaciones coreInsertar(BeanSySeguridadautorizaciones bean) {
		this.registrar(bean);
		return bean;
	}

	public BeanSySeguridadautorizaciones coreInsertar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadautorizaciones bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public BeanSySeguridadautorizaciones coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadautorizaciones bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public BeanSySeguridadautorizaciones coreActualizar(BeanSySeguridadautorizaciones bean) {
		this.actualizar(bean);
		return bean;
	}
	public BeanSySeguridadautorizaciones coreRegistrarOactualizar(BeanSySeguridadautorizaciones bean) {
		this.registrarOactualizar(bean);
		return bean;
	}
}
