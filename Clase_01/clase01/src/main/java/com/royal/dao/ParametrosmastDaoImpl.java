package com.royal.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.royal.dominio.Parametrosmast;
import com.royal.dominio.ParametrosmastPk;
import com.royal.dominio.dto.DtoParametros;
import com.royal.dominio.dto.FiltroParametros;
import com.royal.genericos.GenericoDaoImpl;
import com.royal.util.DominioParametroPersistencia;
import com.royal.util.SeguridadUsuarioActual;
import com.royal.util.UString;

@Repository
public class ParametrosmastDaoImpl extends GenericoDaoImpl<Parametrosmast, ParametrosmastPk>{
	public ParametrosmastDaoImpl() {
		super("parametrosmast");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Parametrosmast obtenerPorId(String companiacodigo, String aplicacioncodigo, String parametroclave) {
		return obtenerPorId(new ParametrosmastPk(companiacodigo, aplicacioncodigo, parametroclave));
	}

	public Parametrosmast coreInsertar(Parametrosmast bean) {
		this.registrar(bean);
		return bean;
	}

	public Parametrosmast coreInsertar(SeguridadUsuarioActual usuarioActual, Parametrosmast bean, String estado) {
		if (UString.estaVacio(estado))
			estado = "A";
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public Parametrosmast coreActualizar(SeguridadUsuarioActual usuarioActual, Parametrosmast bean, String estado) {
		if (UString.estaVacio(estado))
			estado = "A";
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	@Transactional
	public Parametrosmast coreActualizar(Parametrosmast bean) {
		this.actualizar(bean);
		return bean;
	}

	public List<DtoParametros> listarParametros(FiltroParametros filtros) throws Exception {

		if (UString.estaVacio(filtros.getAplicacioncodigo())) {
			filtros.setAplicacioncodigo(null);
		}
		if (UString.estaVacio(filtros.getCompaniacodigo())) {
			filtros.setCompaniacodigo(null);
		}
		if (UString.estaVacio(filtros.getParametroclave())) {
			filtros.setParametroclave(null);
		}
		if (UString.estaVacio(filtros.getDescripcion())) {
			filtros.setDescripcion(null);
		}

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("par_aplicacion", String.class, filtros.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("par_compania", String.class, filtros.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("par_parametros", String.class, filtros.getParametroclave()));
		parametros.add(new DominioParametroPersistencia("par_descripcion", String.class, filtros.getDescripcion()));

		List datos = this.listarPorQuery(DtoParametros.class, "parametrosmast.listar", parametros);

		return datos;
	}
}
