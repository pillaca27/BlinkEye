package net.royal.spring.framework.web.dao.impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import net.royal.spring.framework.constante.ConstanteDatos.SORT_ORDER;
import net.royal.spring.framework.constante.ConstanteDatos.TIPO_OPERACION;
import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.modelo.CorrelativoTransaccion;
import net.royal.spring.framework.modelo.ErrorTransaccion;
import net.royal.spring.framework.modelo.ParametroTransaccion;
import net.royal.spring.framework.modelo.TipoCambioTransaccion;
import net.royal.spring.framework.modelo.WorkFlowTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioOrden;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioLogin;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UObject;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.constante.ConstanteFiltro;
import net.royal.spring.framework.web.dao.GenericoDao;
import net.royal.spring.framework.web.hilo.RegistroErroresHilo;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
public abstract class GenericoDaoImpl<T, ID extends Serializable> extends HibernateTemplate
		implements GenericoDao<T, ID>, Serializable {


	private static final long serialVersionUID = 8704138686206423411L;
	private Class<T> persistentClass;
	private String nombreAlias;
	private Session sessionInterna;
	
	@Autowired
	private ApplicationContext applicationContext;

	public GenericoDaoImpl(String nombreAlias) {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.nombreAlias = nombreAlias;
	}
	public GenericoDaoImpl(SessionFactory factory, String nombreAlias) {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		super.setSessionFactory(factory);
		this.nombreAlias = nombreAlias;
	}
	
	public T findId(ID key) {
		return (T) this.getSesionActual().get(persistentClass, key);
	}

	public ID registrar(T entity) {
		return (ID) this.getSesionActual().save(entity);
	}

	public GenericoDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public Criteria getCriteria() {
		return this.getSesionActual().createCriteria(persistentClass);
	}

	public Criteria getCriteria(Class clazz) {
		return this.getSesionActual().createCriteria(clazz);
	}


	public void actualizar(T entity) {
		this.getSesionActual().update(entity);
	}

	public void eliminar(T entity) {
		this.getSesionActual().delete(entity);
	}
	
	public void eliminar(List<T> entitys) {
		for (T entity : entitys) {
			this.getSesionActual().delete(entity);
		}		
	}

	public void registrarOactualizar(T entity) {
		this.getSesionActual().saveOrUpdate(entity);
	}

	@Transactional(readOnly = true)
	public T obtenerPorId(ID id, boolean lock) {
		T entity = null;
		try {
			if (lock) {
				entity = (T) this.getSesionActual().get(persistentClass, id, LockMode.UPGRADE);
			} else {
				entity = (T) this.getSesionActual().get(persistentClass, id);
			}
		} catch (Exception ex) {
			logger.error(ex);
			return null;
		}
		return entity;
	}

	@Transactional(readOnly = true)
	public T obtenerPorId(ID id) {
		return this.obtenerPorId(id, true);
	}

	@Transactional(readOnly = true)
	public Object obtenerPorQuery(String nombreQuery, List<DominioParametroPersistencia> parameters, Class clazz) {
		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return null;
		Query query = this.getSesionActual().getNamedQuery(nombreQuery);
		query = GenericoBase.getNamedQueryByPatametersSet(query, parameters);
		query = query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.uniqueResult();
	}

	@Transactional(readOnly = true)
	public Object obtenerPorQuery(String nombreQuery) {
		/* DIALECTO */
		return GenericoBase.buscarGenerico(nombreQuery, null, this.getSesionActual(), nombreAlias);
	}

	@Transactional(readOnly = true)
	public Object obtenerPorQuery(String nombreQuery, List<DominioParametroPersistencia> parametros) {
		/* DIALECTO */
		return GenericoBase.buscarGenerico(nombreQuery, parametros, this.getSesionActual(), nombreAlias);
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

	public void ejecutarPorSentenciaSQL(StringBuilder sentenciaSQL) {
		Query query = this.getSesionActual().createSQLQuery(sentenciaSQL.toString());
		query.executeUpdate();
	}

	public void ejecutarPorSentenciaSQL(StringBuilder sentenciaSQL, List<DominioParametroPersistencia> parametros) {
		System.out.println("ejecutando todo 1.0");
		Query query = this.getSesionActual().createSQLQuery(sentenciaSQL.toString());
		System.out.println("ejecutando todo 1.1");
		query = GenericoBase.getNamedQueryByPatametersSet(query, parametros);
		System.out.println("ejecutando todo 1.2");
		System.out.println(query.getQueryString());
		System.out.println(query.toString());
		query.executeUpdate();
	}

	@Transactional(readOnly = true)
	public List<T> listarTodos() {
		return listarPorCriterios();
	}

	@Transactional(readOnly = true)
	public List<Class> listarTodos(Class clazz) {
		return (List<Class>) listarPorCriterios(clazz);
	}

	@Transactional(readOnly = true)
	public List<T> listarPorEjemplo(T exampleInstance, String... excludeProperty) {
		Criteria crit = this.getSesionActual().createCriteria(persistentClass);
		Example example = Example.create(exampleInstance);
		if (excludeProperty != null) {
			for (String exclude : excludeProperty) {
				example.excludeProperty(exclude);
			}
		}
		crit.add(example);
		return crit.list();
	}

	@Transactional(readOnly = true)
	public List<Class> listarPorHqlQuery(Class clazz, String nombreHqlQuery) {
		if (!GenericoBase.validarAlias(nombreAlias, nombreHqlQuery))
			return new ArrayList();
		logger.debug("listarPorHqlQuery");
		String sentenciaHql = this.getSesionActual().getNamedQuery(nombreHqlQuery).getQueryString();
		Query query = this.getSesionActual().createQuery(sentenciaHql);
		query = query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}

	@Transactional(readOnly = true)
	public List<Class> listarPorHqlQuery(Class clazz, String nombreHqlQuery,
			List<DominioParametroPersistencia> parametros) {
		if (!GenericoBase.validarAlias(nombreAlias, nombreHqlQuery))
			return new ArrayList();
		String sentenciaHql = this.getSesionActual().getNamedQuery(nombreHqlQuery).getQueryString();
		Query query = this.getSesionActual().createQuery(sentenciaHql);
		query = GenericoBase.getNamedQueryByPatametersSet(query, parametros);
		query = query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}

	@Transactional(readOnly = true)
	public List<Class> listarPorQuery(Class clazz, String nombreQuery) {

		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return new ArrayList();
		Query query = this.getSesionActual().getNamedQuery(nombreQuery);
		query = query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}

	@Transactional(readOnly = true)
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

	@Transactional(readOnly = true)
	public List listarPorQueryProcedimientoAlmacenado(String nombreQuery,
			List<DominioParametroPersistencia> parameters) {
		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return new ArrayList();

		Query query = this.getSesionActual().getNamedQuery(nombreQuery);
		query = GenericoBase.getNamedQueryByPatametersSet(query, parameters);
		return query.list();
	}

	@Transactional(readOnly = true)
	public List<T> listarConPaginacion(Criteria criteria, DominioPaginacion paginacion) {
		if (UInteger.esCeroOrNulo(paginacion.getPaginacionRegistroInicio()))
			paginacion.setPaginacionRegistroInicio(0);

		if (UInteger.esCeroOrNulo(paginacion.getPaginacionRegistrosPorPagina()))
			paginacion.setPaginacionRegistrosPorPagina(10);

		criteria.setFirstResult(paginacion.getPaginacionRegistroInicio());
		criteria.setMaxResults(paginacion.getPaginacionRegistrosPorPagina());

		logger.debug("listar con pagicacion criteria");

		if (!UString.esNuloVacio(paginacion.getPaginacionOrdenAtributo())) {
			if (UObject.estaVacio(paginacion.getPaginacionOrdenDireccion()))
				paginacion.setPaginacionOrdenDireccion(SORT_ORDER.ASC);
			criteria.addOrder((paginacion.getPaginacionOrdenDireccion() == SORT_ORDER.ASC)
					? Order.asc(paginacion.getPaginacionOrdenAtributo())
					: Order.desc(paginacion.getPaginacionOrdenAtributo()));
		}
		return (List<T>) criteria.list();
	}

	@Transactional(readOnly = true)
	public List<T> listarConPaginacion(DominioPaginacion parametroPaginacion,
			List<DominioParametroPersistencia> parametros) {
		/* DIALECTO */
		return (List<T>) GenericoBase.listarConPaginacion(parametroPaginacion, parametros, null, persistentClass, null,
				this.getSesionActual(), nombreAlias);
	}

	@Transactional(readOnly = true)
	public List<T> listarConPaginacion(DominioPaginacion parametroPaginacion,
			List<DominioParametroPersistencia> parametros, String nombreQuery) {
		/* DIALECTO */
		return (List<T>) GenericoBase.listarConPaginacion(parametroPaginacion, parametros, nombreQuery, persistentClass,
				null, this.getSesionActual(), nombreAlias);
	}

	@Transactional(readOnly = true)
	public List<Class> listarConPaginacion(DominioPaginacion parametroPaginacion,
			List<DominioParametroPersistencia> parametros, String nombreQuery, Class clazz) {
		/* DIALECTO */
		return GenericoBase.listarConPaginacion(parametroPaginacion, parametros, nombreQuery, clazz, null,
				this.getSesionActual(), nombreAlias);
	}

	@Transactional(readOnly = true)
	public List<Class> listarConPaginacion(DominioPaginacion parametroPaginacion,
			List<DominioParametroPersistencia> parametros, String nombreQuery, Class clazz,
			String condicionesConsulta) {
		/* DIALECTO */
		return GenericoBase.listarConPaginacion(parametroPaginacion, parametros, nombreQuery, clazz,
				condicionesConsulta, this.getSesionActual(), nombreAlias);
	}

	@Transactional(readOnly = true)
	public List<T> listarPorSentenciaSQL(StringBuilder sentenciaSQL) {
		Query query = this.getSesionActual().createSQLQuery(sentenciaSQL.toString());
		return query.list();
	}

	@Transactional(readOnly = true)
	public List<T> listarPorSentenciaSQL(StringBuilder sentenciaSQL, List<DominioParametroPersistencia> parametros) {
		Query query = this.getSesionActual().createSQLQuery(sentenciaSQL.toString());
		query = GenericoBase.getNamedQueryByPatametersSet(query, parametros);
		return query.list();
	}

	@Transactional(readOnly = true)
	public List<Class> listarPorSentenciaSQL(StringBuilder sentenciaSQL, List<DominioParametroPersistencia> parametros,
			Class clazz) {
		Query query = this.getSesionActual().createSQLQuery(sentenciaSQL.toString());
		query = GenericoBase.getNamedQueryByPatametersSet(query, parametros);
		query = query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}

	@Transactional(readOnly = true)
	public List<T> listarPorCriterios(Class clazz, Criterion... criterion) {
		Criteria crit = this.getSesionActual().createCriteria(clazz);
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	@Transactional(readOnly = true)
	public List<T> listarPorCriterios(Criterion... criterion) {
		Criteria crit = this.getSesionActual().createCriteria(persistentClass);
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	public List<T> listarPorCriterios(Criteria criteria) {
		return criteria.list();
	}

	public List listarPorSentenciaSQLGenerica(StringBuilder sentenciaSQL) {
		return listarPorSentenciaSQLGenerica(sentenciaSQL, null);
	}

	public List listarPorSentenciaSQLGenerica(StringBuilder sentenciaSQL,
			List<DominioParametroPersistencia> parametros) {
		if (parametros != null) {

			/*
			 * Query query = this.getSesionActual().createSQLQuery(sentenciaSQL.toString());
			 * query = GenericoBase.getNamedQueryByPatametersSet(query, parametros);
			 * sentenciaSQL = new StringBuilder(query.toString());
			 */
			sentenciaSQL = GenericoBase.getNamedQueryByPatametersSet(sentenciaSQL, parametros);
			return GenericoBase.listarPorSentenciaSQLGenerica(sentenciaSQL, this.getConnection());
		} else {
			return GenericoBase.listarPorSentenciaSQLGenerica(sentenciaSQL, this.getConnection());
		}
	}

	@Transactional(readOnly = true)
	public Integer contar(String nombreQuery) {
		/* DIALECTO */
		return GenericoBase.contar(nombreQuery, null, this.getSesionActual(), nombreAlias);
	}

	@Transactional(readOnly = true)
	public Integer contar(String nombreQuery, List<DominioParametroPersistencia> parametros) {
		/* DIALECTO */
		return GenericoBase.contar(nombreQuery, parametros, this.getSesionActual(), nombreAlias);
	}

	@Transactional(readOnly = true)
	public Integer contar(List<DominioParametroPersistencia> parametros) {
		return GenericoBase.contar(parametros, this.getSesionActual(), persistentClass);
	}

	@Transactional(readOnly = true)
	public BigDecimal contarPorSentenciaSQL(StringBuilder sentenciaSQL) {
		return contarPorSentenciaSQL(sentenciaSQL, null);
	}

	@Transactional(readOnly = true)
	public Integer contarPorSentenciasSQL(StringBuilder sentenciaSQL) {
		return contarPorSentenciasSQL(sentenciaSQL, null);
	}

	@Transactional(readOnly = true)
	public Integer contarPorSentenciasSQL(StringBuilder sentenciaSQL, List<DominioParametroPersistencia> parametros) {
		Query query = this.getSesionActual().createSQLQuery(sentenciaSQL.toString());
		query = GenericoBase.getNamedQueryByPatametersSet(query, parametros);
		return (Integer) query.uniqueResult();
	}

	@Transactional(readOnly = true)
	public BigDecimal contarPorSentenciaSQL(StringBuilder sentenciaSQL,
			List<DominioParametroPersistencia> parametros) {
		Query query = this.getSesionActual().createSQLQuery(sentenciaSQL.toString());
		query = GenericoBase.getNamedQueryByPatametersSet(query, parametros);
		return (BigDecimal) query.uniqueResult();
	}

	public String obtenerSentenciaSqlPorQuery(String nombreQuery) {
		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return null;
		StringBuilder queryS = new StringBuilder();
		queryS.append(this.getSesionActual().getNamedQuery(nombreQuery).getQueryString());
		return queryS.toString();
	}

	public Session getSesionActual() {
		if (sessionInterna == null) {
			return super.getSessionFactory().getCurrentSession();
		} else {
			return sessionInterna;
		}
	}

	public void setSesionActual(Session sessionInterna) {
		this.sessionInterna = sessionInterna;
	}

	public Connection getConnection() {
		return ((SessionImpl) this.getSesionActual()).connection();
	}

	@Transactional(readOnly = true)
	public byte[] ejecutarReporteComoPDF(String rutaArchivoReporteFuente, Map parametros,String cadenaConexion) throws Exception {
		Connection conexion;
		conexion = DriverManager.getConnection(cadenaConexion); 
		return ejecutarReporteComoPDF(rutaArchivoReporteFuente,parametros,conexion);
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
	

	@Transactional(readOnly = true)
	public byte[] ejecutarReporteComoPDFDesdeLista(String rutaArchivoReporteFuente, Map parametros, List lista) {
		byte[] bytes2 = null;
		try {
			bytes2 = JasperRunManager.runReportToPdf(rutaArchivoReporteFuente, parametros,
					new JRBeanCollectionDataSource(lista));
		} catch (Exception ex) {
			logger.error(ex);
		}
		return bytes2;
	}

	/**
	 * 
	 * @param cri
	 * @return Integer
	 */
	public Integer incrementarInteger(Criteria cri) {
		/* EN USO */
		List results = cri.list();

		if (results == null)
			return 1;
		if (results.size() == 0)
			return 1;

		Integer obj = (Integer) results.get(0);
		if (obj == null)
			return 1;

		return obj + 1;

		/*
		 * List results = cri.list();
		 * 
		 * Integer total = results.size();
		 * 
		 * if (results.size() == 0) return 1;
		 * 
		 * return total + 1;
		 */
	}

	public Integer incrementarStringInteger(Criteria cri) {

		List results = cri.list();

		if (results == null)
			return 1;
		if (results.size() == 0)
			return 1;

		String objString = (String) results.get(0);

		if (objString == null)
			return 1;

		Integer obj = Integer.parseInt(objString.trim());

		return obj + 1;

	}

	public BigDecimal incrementarBigDecimal(Criteria cri) {
		List results = cri.list();

		if (results == null)
			return new BigDecimal(1);
		if (results.size() == 0)
			return new BigDecimal(1);

		BigDecimal obj = (BigDecimal) results.get(0);
		if (obj == null)
			return new BigDecimal(1);

		return obj.add(new BigDecimal(1));
	}

	public Criteria agregarParametrosACriteria(List<DominioParametroPersistencia> parametros, Criteria criteria) {
		/* EN USO */
		return agregarParametrosACriteria(parametros, criteria, null);
	}

	/**
	 * considerar que el listado que use esta generacion de parametros podria usar
	 * un listado que genere de nuevo order by
	 **/
	public Criteria agregarParametrosACriteria(List<DominioParametroPersistencia> parametros, Criteria criteria,
			DominioOrden filtroOrden) {
		/* EN USO */
		for (DominioParametroPersistencia param : parametros) {
			if (UObject.estaVacio(param.getValor()))
				continue;
			if (UString.estaVacio(param.getValor().toString()))
				continue;

			if (UObject.estaVacio(param.getOperacion()))
				param.setOperacion(TIPO_OPERACION.EQ);

			if (param.getOperacion().equals(TIPO_OPERACION.EQ))
				criteria.add(Restrictions.eq(param.getCampo(), param.getValor()));
			else if (param.getOperacion().equals(TIPO_OPERACION.LIKE))
				criteria.add(Restrictions.like(param.getCampo(), param.getValor().toString(), MatchMode.ANYWHERE)
						.ignoreCase());
		}

		if (filtroOrden != null) {
			if (!UString.esNuloVacio(filtroOrden.getAtributoOrdenar())) {
				if (UObject.estaVacio(filtroOrden.getDireccionOrdenAtributo()))
					filtroOrden.setDireccionOrdenAtributo(SORT_ORDER.ASC);
				criteria.addOrder((filtroOrden.getDireccionOrdenAtributo() == SORT_ORDER.ASC)
						? Order.asc(filtroOrden.getAtributoOrdenar())
						: Order.desc(filtroOrden.getAtributoOrdenar()));
			}
		}

		return criteria;
	}

	public Integer contarPorCriteria(Criteria criteria) {
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		if (count == null)
			return 0;
		return count.intValue();
	}
	
	@Autowired
	public ApplicationContext appContext;

	@Autowired
	private MessageSource messageSource;
	
	public DominioMensajeUsuario getMsjUsuarioError(String msgCode) {
		String msg = getMessage(msgCode);
		DominioMensajeUsuario msj = new DominioMensajeUsuario(tipo_mensaje.ERROR, msg);
		return msj;
	}
	public String getMessage(String msgCode) {
		Locale locale = LocaleContextHolder.getLocale();
		return getMessageBase(msgCode, null, null, locale);
	}
	private String getMessageBase(String msgCode, Object[] params, String defaultMsg, Locale locale) {
		try {
			if (locale == null)
				locale = LocaleContextHolder.getLocale();
			defaultMsg = UString.esNuloVacio(defaultMsg) ? msgCode : defaultMsg;
			String msg = messageSource.getMessage(msgCode, params, defaultMsg, locale);
			return UString.esNuloVacio(msg) ? msgCode : msg;
		} catch (Exception e) {
			logger.error(msgCode);
		}
		return msgCode;
	}
	public List<DominioMensajeUsuario> setMessageError(List<DominioMensajeUsuario> lst,String msg) {
		if (lst==null)
			lst=new ArrayList();
		lst.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, msg));
		return lst;
	}
	/** API - GENERICAS INICIO **/
	@Autowired
	protected HttpServletRequest request;
	protected static Properties propiedades;
	protected static String apiWorkFlow;
	protected static String apiParametroObtener;
	protected static String apiCorrelativoObtener;
	protected static String usuarioSeguroKey;
	public HttpHeaders getHeaders() {
		return getHeaders(null);
	}
	public HttpHeaders getHeaders(SeguridadUsuarioLogin token) {
		HttpHeaders headers = new HttpHeaders();
		String idtoken = request.getHeader(ConstanteFiltro.TOKEN);
		String sid = request.getHeader(ConstanteFiltro.SID);
		if (token != null && UString.esNuloVacio(idtoken)) {
			if (!UString.esNuloVacio(token.getToken())) {
				idtoken = token.getToken();
			}
		}
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.add(ConstanteFiltro.TOKEN, idtoken);
		headers.add(ConstanteFiltro.SID, sid);
		return headers;
	}
	public HttpHeaders getHeadersSeguro() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.add(ConstanteFiltro.TOKEN, "");
		headers.add(ConstanteFiltro.SID, "");
		headers.add(ConstanteFiltro.USUARIO_SEGURO, usuarioSeguroKey);
		return headers;
	}
	protected void leerPropiedades() throws Exception {
		String rutaPropiedades = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		logger.debug(rutaPropiedades);
		propiedades = UPropiedades.getInstance().abrir(rutaPropiedades, ConstanteBoot.PROPERTIES_GLOBAL);
		logger.debug(propiedades);
		if (propiedades != null) {
			apiWorkFlow = UString.obtenerSinNulo(propiedades.getProperty("proxy.framework.workflow"));
			apiParametroObtener = UString.obtenerSinNulo(propiedades.getProperty("parametro.obtener"));
			apiCorrelativoObtener = UString.obtenerSinNulo(propiedades.getProperty("correlativo.obtenernumerocadena"));
			/*apiTipoCambioObtener = UString.obtenerSinNulo(propiedades.getProperty("tipocambio.obtener"));
			apiWorkFlowTransaccionIniciar = UString.obtenerSinNulo(propiedades.getProperty("workflow.transaccion.iniciar"));
			
			apiCorreoEnviar = UString.obtenerSinNulo(propiedades.getProperty("correo.enviar"));
			apiReporteEjecutar = UString.obtenerSinNulo(propiedades.getProperty("reporte.ejecutar"));
			apiReporteEjecutarCadena = UString.obtenerSinNulo(propiedades.getProperty("reporte.ejecutarcadena"));
			rutaTemporalFisica = UString.obtenerSinNulo(propiedades.getProperty("ruta.fisica.temporal"));
			imagenReportes = UString.obtenerSinNulo(propiedades.getProperty("ruta.imagen.logoexportar"));
			
			apiPersonaObtenerDatos = UString.obtenerSinNulo(propiedades.getProperty("persona.obtenerdatos"));
			apiEmpleadoObtenerDatos = UString.obtenerSinNulo(propiedades.getProperty("empleado.obtenerdatos"));
			apiCompanyownerObtenerRecurso = UString.obtenerSinNulo(propiedades.getProperty("companyowner.obtenerecurso"));

			apiCompaniaObtenerNombre = UString.obtenerSinNulo(propiedades.getProperty("compania.obtenertabla"));
			apiMiscelaneoObtenerDescripcion = UString.obtenerSinNulo(propiedades.getProperty("miscelaneo.obtenerdescripcion"));

			apiObtenerTransaccion = UString.obtenerSinNulo(propiedades.getProperty("workflow.obtenertransaccion"));
			apiAnularTransaccionDesdeSolicitud = UString.obtenerSinNulo(propiedades.getProperty("workflow.anulartransacciondesdesolicitud"));
			apiMiscelaneoListar = UString.obtenerSinNulo(propiedades.getProperty("miscelaneo.listar"));
			apiHrCentroestudiosobtener = UString.obtenerSinNulo(propiedades.getProperty("centroestudios.obtener"));
			apiHrCursodescripcionobtener = UString.obtenerSinNulo(propiedades.getProperty("cursodescripcion.obtener"));*/			
		}
	}
	public BigDecimal parametroObtenerNumero(ParametroTransaccion bean) throws Exception {
		bean = parametroObtener(bean);
		return bean.getNumero();
	}

	public Date parametroObtenerFecha(ParametroTransaccion bean) throws Exception {
		bean = parametroObtener(bean);
		return bean.getFecha();
	}

	public String parametroObtenerTexto(ParametroTransaccion bean) throws Exception {
		bean = parametroObtener(bean);
		return bean.getTexto();
	}

	public String parametroObtenerExplicacion(ParametroTransaccion bean) throws Exception {
		bean = parametroObtener(bean);
		return bean.getExplicacion();
	}
	
	public String parametroObtenerDescripcion(ParametroTransaccion bean) throws Exception {
		bean = parametroObtener(bean);
		return bean.getDescripcionparametro();
	}
	public ParametroTransaccion parametroObtener(ParametroTransaccion bean) throws Exception {
		ParametroTransaccion res = new ParametroTransaccion();
		try {
			if (UString.esNuloVacio(apiParametroObtener))
				leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiParametroObtener);
			logger.debug(uri);

			HttpEntity<ParametroTransaccion> request = new HttpEntity<ParametroTransaccion>(bean, this.getHeaders());
			ResponseEntity<ParametroTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					ParametroTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiParametroObtener:" + UString.obtenerSinNulo(apiParametroObtener));
			res.setTransaccionEstado(TipoCambioTransaccion.ERROR);
			//res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return res;
	}
	
	
	public CorrelativoTransaccion correlativoObtener(CorrelativoTransaccion bean) throws Exception {
		CorrelativoTransaccion res = new CorrelativoTransaccion();
		try {
			if (apiCorrelativoObtener == null)
				leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiCorrelativoObtener);
			logger.debug(uri);

			HttpEntity<CorrelativoTransaccion> request = new HttpEntity<CorrelativoTransaccion>(bean, this.getHeaders());
			ResponseEntity<CorrelativoTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					CorrelativoTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiCorrelativoObtener:" + UString.obtenerSinNulo(apiCorrelativoObtener));
			res.setTransaccionEstado(TipoCambioTransaccion.ERROR);
			//res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return res;
	}
	
	public String parametroObtenerExplicacion(String aplicacioncodigo, String parametroclave) throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(null, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return bean.getExplicacion();
	}
	public BigDecimal parametroObtenerNumero(String aplicacioncodigo, String parametroclave) throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(null, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return bean.getNumero();		
	}
	public String parametroObtenerTexto(String aplicacioncodigo, String parametroclave) throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(null, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return bean.getTexto();
	}
	/**/
	public String parametroObtenerExplicacion(String aplicacioncodigo, String parametroclave,String companiacodigo) throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(companiacodigo, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return bean.getExplicacion();
	}
	public BigDecimal parametroObtenerNumero(String aplicacioncodigo, String parametroclave,String companiacodigo) throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(companiacodigo, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return bean.getNumero();		
	}
	public Integer parametroObtenerNumeroEntero(String aplicacioncodigo, String parametroclave, String companiacodigo)
			throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(companiacodigo, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return UBigDecimal.obtenerValorSinNulo(bean.getNumero()).intValue();
	}
	public String parametroObtenerTexto(String aplicacioncodigo, String parametroclave,String companiacodigo) throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(companiacodigo, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return bean.getTexto();
	}
	
	public String correlativoObtenerNumeroTexto(String companiacodigo, String comprobante, String serie, String ceros, Integer cerosIzquierda) throws Exception {
		CorrelativoTransaccion bean = new CorrelativoTransaccion(companiacodigo, comprobante, serie, cerosIzquierda);
		bean = correlativoObtener(bean);
		return bean.getNumeroGeneradoCadena();
	}
	public WorkFlowTransaccion workflowSiguienteEstado(WorkFlowTransaccion bean) throws Exception {
		try {
			if (apiWorkFlow == null)
				this.leerPropiedades();

			String apiUrl = apiWorkFlow + "spring/workflow/wftransaccion/obtenerEstadoSiguienteExterno";
			
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiUrl);
			logger.debug(apiUrl);

			HttpEntity<WorkFlowTransaccion> request = new HttpEntity<WorkFlowTransaccion>(bean, this.getHeaders());
			ResponseEntity<WorkFlowTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					WorkFlowTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("workflowSiguienteEstado:" + UString.obtenerSinNulo(apiWorkFlow));
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			//bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return bean;
	}
	public String workflowSiguienteEstado(Integer transaccionId) throws Exception {
		WorkFlowTransaccion res = workflowSiguienteEstado(new WorkFlowTransaccion(transaccionId) );
		return res.getNivelSiguienteEstadoId();
	}
	
	public void registrarError(ErrorTransaccion bean) {
		try {
			String rutaPropiedades = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
			Properties propiedades;
			propiedades = UPropiedades.getInstance().abrir(rutaPropiedades, ConstanteBoot.PROPERTIES_GLOBAL);
			String apiErrorRegistrar = UString.obtenerSinNulo(propiedades.getProperty("servicio.spring.comun.error.registrar"));
			usuarioSeguroKey = propiedades.getProperty("seguridad.usuarioseguro");
			RegistroErroresHilo hilo = new RegistroErroresHilo(apiErrorRegistrar, bean, this.getHeadersSeguro());
			hilo.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** API - GENERICAS FIN **/
	
	@Override
	@Transactional(readOnly = true)
	public T obtenerPorUuid(String uuid) {
		T entity = null;
		Criteria crit = this.getSesionActual().createCriteria(persistentClass);
		crit.add(Restrictions.eq("uuid",uuid));		
		entity = (T) crit.uniqueResult();	
		if (entity!=null) {
			//this.getSesionActual().detach(entity);
			this.getSesionActual().evict(entity);	
		}		
		return entity;
	}
}
