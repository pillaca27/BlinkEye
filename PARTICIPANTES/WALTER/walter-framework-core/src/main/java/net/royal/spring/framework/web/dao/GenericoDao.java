package net.royal.spring.framework.web.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;

@SuppressWarnings("rawtypes")
public interface GenericoDao<T, ID extends Serializable> {

	public Criteria getCriteria();

	public Criteria getCriteria(Class clazz);

	public ID registrar(T entity);

	public void actualizar(T entity);

	public void eliminar(T entity);
	
	public void eliminar(List<T> entitys);

	public void registrarOactualizar(T entity);

	public T obtenerPorId(ID id, boolean lock);

	public T obtenerPorId(ID id);

	public T obtenerPorUuid(String uuid);
	
	public T findId(ID key);

	public Object obtenerPorQuery(String namedQuery, List<DominioParametroPersistencia> parameters, Class clazz);

	public Object obtenerPorQuery(String nombreQuery);

	public Object obtenerPorQuery(String nombreQuery, List<DominioParametroPersistencia> parametros);

	public void ejecutarPorQuery(String nombreQuery, List<DominioParametroPersistencia> parametros);

	public void ejecutarPorSentenciaSQL(StringBuilder sentenciaSQL);

	public void ejecutarPorSentenciaSQL(StringBuilder sentenciaSQL, List<DominioParametroPersistencia> parametros);

	public List<T> listarTodos();

	public List<Class> listarTodos(Class clazz);

	public List<T> listarPorEjemplo(T exampleInstance, String... excludeProperty);

	public List<Class> listarPorHqlQuery(Class clazz, String nombreHqlQuery);

	public List<Class> listarPorHqlQuery(Class clazz, String nombreHqlQuery,
			List<DominioParametroPersistencia> parametros);

	public List<Class> listarPorQuery(Class clazz, String namedQuery);

	public List<Class> listarPorQuery(Class clazz, String namedQuery, List<DominioParametroPersistencia> parameters);

	public List<T> listarConPaginacion(Criteria criteria, DominioPaginacion paginacion);

	public List<T> listarConPaginacion(DominioPaginacion parametroPaginacion,
			List<DominioParametroPersistencia> parametros);

	public List<T> listarConPaginacion(DominioPaginacion parametroPaginacion,
			List<DominioParametroPersistencia> parametros, String nombreQuery);

	public List<Class> listarConPaginacion(DominioPaginacion parametroPaginacion,
			List<DominioParametroPersistencia> parametros, String nombreQuery, Class clazz);

	public List<Class> listarConPaginacion(DominioPaginacion parametroPaginacion,
			List<DominioParametroPersistencia> parametros, String nombreQuery, Class clazz,
			String condicionesConsulta);

	public List<T> listarPorSentenciaSQL(StringBuilder sentenciaSQL);

	public List<T> listarPorSentenciaSQL(StringBuilder sentenciaSQL, List<DominioParametroPersistencia> parametros);

	public List<Class> listarPorSentenciaSQL(StringBuilder sentenciaSQL, List<DominioParametroPersistencia> parametros,
			Class clazz);

	public List<T> listarPorCriterios(Class clazz, Criterion... criterion);

	public List<T> listarPorCriterios(Criterion... criterion);

	public List<T> listarPorCriterios(Criteria criteria);

	public List listarPorSentenciaSQLGenerica(StringBuilder sentenciaSQL);

	public List listarPorSentenciaSQLGenerica(StringBuilder sentenciaSQL,
			List<DominioParametroPersistencia> parametros);

	public Integer contar(String nombreQuery);

	public Integer contar(String nombreQuery, List<DominioParametroPersistencia> parametros);

	public Integer contar(List<DominioParametroPersistencia> parametros);

	public BigDecimal contarPorSentenciaSQL(StringBuilder sentenciaSQL);

	public Integer contarPorSentenciasSQL(StringBuilder sentenciaSQL);

	public Integer contarPorSentenciasSQL(StringBuilder sentenciaSQL, List<DominioParametroPersistencia> parametros);

	public BigDecimal contarPorSentenciaSQL(StringBuilder sentenciaSQL, List<DominioParametroPersistencia> parametros);

	public String obtenerSentenciaSqlPorQuery(String nombreQuery);

	public Session getSesionActual();

	public void setSesionActual(Session sessionInterna);

	public byte[] ejecutarReporteComoPDF(String rutaArchivoReporteFuente, Map parametros,String cadenaConexion) throws Exception;
	
	public byte[] ejecutarReporteComoPDF(String rutaArchivoReporteFuente, Map parametros) throws Exception;

	public byte[] ejecutarReporteComoPDFDesdeLista(String rutaArchivoReporteFuente, Map parametros, List lista);

	public Integer incrementarInteger(Criteria cri);

	public BigDecimal incrementarBigDecimal(Criteria cri);

	public Integer contarPorCriteria(Criteria criteria);

	public SessionFactory getSessionFactory();

	public List listarPorQueryProcedimientoAlmacenado(String nombreQuery,
			List<DominioParametroPersistencia> parameters);
}
