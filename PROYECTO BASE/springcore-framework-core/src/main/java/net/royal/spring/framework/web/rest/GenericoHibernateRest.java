package net.royal.spring.framework.web.rest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.web.dao.impl.GenericoBase;
import net.sf.jasperreports.engine.JasperRunManager;

public class GenericoHibernateRest extends GenericoRest {

	private String nombreAlias;
	private Session sessionInterna;
	private SessionFactory factory;

	public GenericoHibernateRest(String nombreAlias) {
		this.nombreAlias = nombreAlias;
	}

	public void setSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSesionActual() {
		if (sessionInterna == null) {
			return factory.getCurrentSession();
		} else {
			return sessionInterna;
		}
	}

	/**********/
	public List<Class> listarPorQuery(Class clazz, String nombreQuery) {

		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return new ArrayList();
		Query query = this.getSesionActual().getNamedQuery(nombreQuery);
		query = query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}

	public List<Class> listarPorQuery(Class clazz, String nombreQuery, List<DominioParametroPersistencia> parameters) {
		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return new ArrayList();
		Query query = this.getSesionActual().getNamedQuery(nombreQuery);
		query = GenericoBase.getNamedQueryByPatametersSet(query, parameters);
		query = query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}

	/********/

	public Integer contar(String nombreQuery) {
		/* DIALECTO */
		return GenericoBase.contar(nombreQuery, null, this.getSesionActual(), nombreAlias);
	}

	public Integer contar(String nombreQuery, List<DominioParametroPersistencia> parametros) {
		/* DIALECTO */
		return GenericoBase.contar(nombreQuery, parametros, this.getSesionActual(), nombreAlias);
	}

	/********/
	public List<Class> listarConPaginacion(DominioPaginacion parametroPaginacion,
			List<DominioParametroPersistencia> parametros, String nombreQuery, Class clazz) {
		/* DIALECTO */
		return GenericoBase.listarConPaginacion(parametroPaginacion, parametros, nombreQuery, clazz, null,
				this.getSesionActual(), nombreAlias);
	}

	public List<Class> listarConPaginacion(DominioPaginacion parametroPaginacion,
			List<DominioParametroPersistencia> parametros, String nombreQuery, Class clazz,
			String condicionesConsulta) {
		/* DIALECTO */
		return GenericoBase.listarConPaginacion(parametroPaginacion, parametros, nombreQuery, clazz,
				condicionesConsulta, this.getSesionActual(), nombreAlias);
	}
	/********/
	
	public String obtenerSentenciaSqlPorQuery(String nombreQuery) {
		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return null;
		StringBuilder queryS = new StringBuilder();
		queryS.append(this.getSesionActual().getNamedQuery(nombreQuery).getQueryString());
		return queryS.toString();
	}
	
	@Transactional(readOnly = true)
	public List<Class> listarPorSentenciaSQL(StringBuilder sentenciaSQL, List<DominioParametroPersistencia> parametros,
			Class clazz) {
		Query query = this.getSesionActual().createSQLQuery(sentenciaSQL.toString());
		query = GenericoBase.getNamedQueryByPatametersSet(query, parametros);
		query = query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}
	
	public void ejecutarPorQuery(String nombreQuery, List<DominioParametroPersistencia> parametros) {
		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return;
		Query query = this.getSesionActual().getNamedQuery(nombreQuery);
		query = GenericoBase.getNamedQueryByPatametersSet(query, parametros);
		query.executeUpdate();
	}

	@Transactional(readOnly = true)
	public byte[] ejecutarReporteComoPDF(String rutaArchivoReporteFuente, Map parametros) throws Exception {
		Connection conexion;
		conexion = ((SessionImpl) getSesionActual()).connection();
		return ejecutarReporteComoPDF(rutaArchivoReporteFuente,parametros,conexion);
	}
	
	private byte[] ejecutarReporteComoPDF(String rutaArchivoReporteFuente, Map parametros,Connection conexion) throws Exception {
		byte[] bytReporte = null;
		bytReporte = JasperRunManager.runReportToPdf(rutaArchivoReporteFuente, parametros, conexion);
		return bytReporte;
	}
}
