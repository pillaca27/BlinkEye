package net.royal.spring.workflow.dao.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WhContrato;
import net.royal.spring.workflow.dominio.WhContratoPk;

@Repository
public class WhContratoDaoImpl extends GenericoDaoImpl<WhContrato, WhContratoPk> {

	private static Logger logger = LogManager.getLogger(WhContrato.class);

	public WhContratoDaoImpl() {
		super("whcontrato");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WhContrato obtenerPorId(String pcompaniasocio, String pnumerocontrato) {
		return obtenerPorId(new WhContratoPk(pcompaniasocio, pnumerocontrato));
	}

	public WhContrato coreInsertar(WhContrato bean) {
		// TODO WhContrato.Insertar Datos

		this.registrar(bean);
		return bean;
	}

	public WhContrato coreInsertar(SeguridadUsuarioActual usuarioActual, WhContrato bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public WhContrato coreActualizar(SeguridadUsuarioActual usuarioActual, WhContrato bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public WhContrato coreActualizar(WhContrato bean) {
		this.actualizar(bean);
		return bean;
	}

}
