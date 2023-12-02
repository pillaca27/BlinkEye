package net.royal.spring.framework.web.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.StoredProcedureQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exolab.castor.types.DateTime;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import net.royal.spring.framework.constante.ConstanteDatos.SORT_ORDER;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;

@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
public class GenericoBase {

	private static Logger logger = LogManager.getLogger(GenericoBase.class);

	private static final String DIALECTO_ACTUAL = "MSSQL";
	private static String DIALECTO_SESSION = null;

	private GenericoBase() {
	}

	/*public static String nombreQueryDialecto(Session session, String nombreQuery) {
		return nombreQueryDialecto(session,nombreQuery,"");
	}*/
	
	public static String nombreQueryDialecto(Session session, String nombreQuery) {
		try {
			if (DIALECTO_SESSION == null) {
				Map map = session.getSessionFactory().getProperties();
				Object obj = map.get("hibernate.dialect");

				switch (obj.toString()) {
				case "org.hibernate.dialect.Oracle10gDialect":
					DIALECTO_SESSION = "ORACLE";
					break;
				case "org.hibernate.dialect.MySQL5Dialect":
					DIALECTO_SESSION = "MYSQL";
					break;
				case "org.hibernate.dialect.SQLServer2008Dialect":
					DIALECTO_SESSION = "MSSQL";
					break;
				default:

					break;
				}
			}

			if (!DIALECTO_ACTUAL.equals(DIALECTO_SESSION.toUpperCase())) {
				return nombreQuery;
			}
		} catch (Exception e) {
			return nombreQuery;
		}
		return nombreQuery;
	}

	public static Boolean validarAlias(String nombreAlias, String nombreQuery) {
		if (nombreAlias == null)
			return true;
		if (nombreQuery == null)
			return true;
		int indicador = nombreQuery.indexOf(nombreAlias + ".");
		if (indicador == -1) {
			logger.error("El nombre del alias:(" + nombreAlias + "), no se encuentra en el query:(" + nombreQuery
					+ "). Verifique el nombre de los metodos en el archivo xml donde se encuentra la sentencia sql.");
			return false;
		}
		return true;
	}

	public static Integer contar(String nombreQuery, List<DominioParametroPersistencia> parametros, Session session,
			String nombreAlias) {
		/* DIALECTO */
		nombreQuery = nombreQueryDialecto(session, nombreQuery);

		if (!validarAlias(nombreAlias, nombreQuery))
			return null;

		Integer cantReg;
		StringBuilder queryS = new StringBuilder();
		queryS.append(session.getNamedQuery(nombreQuery).getQueryString());
		Query query = session.createSQLQuery(queryS.toString());
		query = getNamedQueryByPatametersSet(query, parametros);
		
		//logger.debug(nombreQuery);
		//logger.debug(queryS.toString());
		
		Object resultado;
		resultado = query.uniqueResult();
		if (resultado == null)
			cantReg = 0;
		else
			cantReg = Integer.parseInt(resultado.toString());
		return cantReg;
	}

	public static Integer contar(List<DominioParametroPersistencia> parametros, Session session,
			Class persistentClass) {
		Integer cantRegistros;
		StringBuilder queryS = new StringBuilder();
		queryS.append("SELECT e FROM " + persistentClass.getName() + " e");
		Query query = session.createSQLQuery(queryS.toString());
		query = getNamedQueryByPatametersSet(query, parametros);
		cantRegistros = Integer.parseInt(query.uniqueResult().toString());
		return cantRegistros;
	}

	public static StringBuilder getNamedQueryByPatametersSet(StringBuilder query,
			List<DominioParametroPersistencia> parameters) {
		if (parameters == null)
			return query;

		String sql = query.toString();
		Object valor = null;

		for (DominioParametroPersistencia item : parameters) {
			Object tipoDatos = item.getClase();
			String parameter = item.getCampo();
			valor = null;

			if (tipoDatos == char.class) {
				valor = item.getValor();
				if (valor == null) {
					sql = sql.replace(":" + parameter, "null");
				} else {
					sql = sql.replace(":" + parameter, (String) valor);
				}
			}
			if (tipoDatos == String.class) {
				valor = item.getValor();
				if (valor == null) {
					sql = sql.replace(":" + parameter, "null");
				} else {
					sql = sql.replace(":" + parameter, "'" + (String) valor + "'");
				}
			}
			if (tipoDatos == Integer.class) {
				Integer integerlocal = null;
				valor = item.getValor();
				if (valor != null)
					integerlocal = (Integer) valor;
				if (integerlocal == null)
					sql = sql.replace(":" + parameter, "null");
				else
					sql = sql.replace(":" + parameter, integerlocal.toString());
				// valorCadena = integerlocal.toString();
				// sql = sql.replace(":"+parameter, valorCadena);

				/*
				 * query.setParameter(parameter, (item.getValor() != null) ? item.getValor() :
				 * null, StandardBasicTypes.INTEGER);
				 */
			}
			/*
			 * if (tipoDatos == Timestamp.class) { query.setParameter(parameter,
			 * (item.getValor() != null) ? item.getValor() : null, StandardBasicTypes.DATE);
			 * } if (tipoDatos == Date.class) { query.setParameter(parameter,
			 * (item.getValor() != null) ? item.getValor() : null, StandardBasicTypes.DATE);
			 * } if (tipoDatos == Integer.class) { query.setParameter(parameter,
			 * (item.getValor() != null) ? item.getValor() : null,
			 * StandardBasicTypes.INTEGER); } if (tipoDatos == Boolean.class) {
			 * query.setParameter(parameter, (item.getValor() != null) ? item.getValor() :
			 * null, StandardBasicTypes.BOOLEAN); } if (tipoDatos == byte[].class) {
			 * query.setParameter(parameter, (item.getValor() != null) ? item.getValor() :
			 * null, StandardBasicTypes.BYTE); } if (tipoDatos == BigDecimal.class) {
			 * query.setParameter(parameter, (item.getValor() != null) ? item.getValor() :
			 * null, StandardBasicTypes.BIG_DECIMAL); } if (tipoDatos == Double.class) {
			 * query.setParameter(parameter, (item.getValor() != null) ? item.getValor() :
			 * null, StandardBasicTypes.DOUBLE); } if (tipoDatos == double.class) {
			 * query.setParameter(parameter, (item.getValor() != null) ? item.getValor() :
			 * null, StandardBasicTypes.DOUBLE); } if (tipoDatos == long.class) {
			 * query.setParameter(parameter, (item.getValor() != null) ? item.getValor() :
			 * null, StandardBasicTypes.LONG); } if (tipoDatos == Long.class) {
			 * query.setParameter(parameter, (item.getValor() != null) ? item.getValor() :
			 * null, StandardBasicTypes.LONG); } if (tipoDatos == String[].class) {
			 * query.setParameterList(parameter, (item.getValor() != null) ?
			 * (String[])item.getValor() : new String[]{""}); }
			 */

		}
		return new StringBuilder(sql);
	}

	public static StoredProcedureQuery getNamedQueryByPatametersSet(StoredProcedureQuery query,
			List<DominioParametroPersistencia> parameters) {
		if (parameters == null)
			return query;

		for (DominioParametroPersistencia item : parameters) {
			String parameter = item.getCampo();
			query.setParameter(parameter, item.getValor());
		}
		return query;
	}

	public static Query getNamedQueryByPatametersSet(Query query, List<DominioParametroPersistencia> parameters) {
		if (parameters == null)
			return query;

		for (DominioParametroPersistencia item : parameters) {
			Object tipoDatos = item.getClase();
			String parameter = item.getCampo();

			if (UString.existeEnLista(query.getNamedParameters(), parameter)) {
				if (tipoDatos == char.class) {
					query.setParameter(parameter, (item.getValor() != null) ? item.getValor() : null,
							StandardBasicTypes.STRING);
				}
				if (tipoDatos == String.class) {
					query.setParameter(parameter, (item.getValor() != null) ? item.getValor() : null,
							StandardBasicTypes.STRING);
				}
				if (tipoDatos == Timestamp.class) {
					query.setParameter(parameter, (item.getValor() != null) ? item.getValor() : null,
							StandardBasicTypes.DATE);
				}
				if (tipoDatos == Date.class) {
					query.setParameter(parameter, (item.getValor() != null) ? item.getValor() : null,
							StandardBasicTypes.DATE);
				}
				if (tipoDatos == DateTime.class) {
					query.setParameter(parameter, (item.getValor() != null) ? item.getValor() : null,
							StandardBasicTypes.TIMESTAMP);
				}
				if (tipoDatos == Integer.class) {
					query.setParameter(parameter, (item.getValor() != null) ? item.getValor() : null,
							StandardBasicTypes.INTEGER);
				}
				if (tipoDatos == Boolean.class) {
					query.setParameter(parameter, (item.getValor() != null) ? item.getValor() : null,
							StandardBasicTypes.BOOLEAN);
				}
				if (tipoDatos == byte[].class) {
					query.setParameter(parameter, (item.getValor() != null) ? item.getValor() : null,
							StandardBasicTypes.BYTE);
				}
				if (tipoDatos == BigDecimal.class) {
					query.setParameter(parameter, (item.getValor() != null) ? item.getValor() : null,
							StandardBasicTypes.BIG_DECIMAL);
				}
				if (tipoDatos == Double.class) {
					query.setParameter(parameter, (item.getValor() != null) ? item.getValor() : null,
							StandardBasicTypes.DOUBLE);
				}
				if (tipoDatos == double.class) {
					query.setParameter(parameter, (item.getValor() != null) ? item.getValor() : null,
							StandardBasicTypes.DOUBLE);
				}
				if (tipoDatos == long.class) {
					query.setParameter(parameter, (item.getValor() != null) ? item.getValor() : null,
							StandardBasicTypes.LONG);
				}
				if (tipoDatos == Long.class) {
					query.setParameter(parameter, (item.getValor() != null) ? item.getValor() : null,
							StandardBasicTypes.LONG);
				}
				if (tipoDatos == String[].class) {
					query.setParameterList(parameter,
							(item.getValor() != null) ? (String[]) item.getValor() : new String[] { "" });
				}
			} else {
				//logger.info("=======> parametro no encontrado :" + parameter);
			}
		}
		return query;
	}

	public static List listarPorSentenciaSQLGenerica(StringBuilder sentenciaSQL, Connection connection) {
		ResultSet rs;
		List listaResultado = new ArrayList();
		List listaHeader = new ArrayList();
		List listaTabla = new ArrayList();
		
		Statement st=null;
		try {
			 st = connection.createStatement();
			// logger.debug(sentenciaSQL);
			rs = st.executeQuery(sentenciaSQL.toString());
			ResultSetMetaData metaDatos = rs.getMetaData();
			int numeroColumnas = metaDatos.getColumnCount();
			for (int i = 0; i < numeroColumnas; i++) {
				// logger.debug(metaDatos.getColumnLabel(i + 1).toUpperCase());
				listaHeader.add(metaDatos.getColumnLabel(i + 1).toUpperCase());
			}
			while (rs.next()) {
				List listaValores = new ArrayList();
				for (int i = 1; i <= numeroColumnas; i++) {
					listaValores.add(rs.getObject(i));
				}
				listaTabla.add(listaValores);
			}
			rs.close();
			st.close();
		} catch (Exception ex) {
			logger.error(ex);
		}finally {
			// finally block used to close resources
			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	

		listaResultado.add(listaHeader);
		listaResultado.add(listaTabla);
		// logger.debug("fin de ejecucion de sentencia sql");
		return listaResultado;
	}

	public static Object buscarGenerico(String nombreQuery, List<DominioParametroPersistencia> parametros,
			Session session, String nombreAlias) {

		/* DIALECTO */
		nombreQuery = nombreQueryDialecto(session, nombreQuery);

		if (!validarAlias(nombreAlias, nombreQuery))
			return null;
		StringBuilder queryS = new StringBuilder();
		queryS.append(session.getNamedQuery(nombreQuery).getQueryString());
		Query query = session.createSQLQuery(queryS.toString());
		query = getNamedQueryByPatametersSet(query, parametros);
		return query.uniqueResult();
	}

	public static String getWhereClause(List<DominioParametroPersistencia> parametros, StringBuilder sentenciaSQL) {
		String and = " AND ";
		StringBuilder sentenciaWhere = new StringBuilder();
		boolean primerFiltro;

		if (parametros == null)
			return sentenciaWhere.toString();
		if (parametros.isEmpty())
			return sentenciaWhere.toString();

		String sqlTemp = sentenciaSQL.toString().toUpperCase();
		int indexOfWhere = sqlTemp.indexOf("WHERE");

		if (indexOfWhere == -1) {
			sentenciaWhere.append(" WHERE ");
			primerFiltro = true;
		} else {
			primerFiltro = false;
		}

		for (DominioParametroPersistencia item : parametros) {
			Object tipoDatos = item.getClase();
			String parameter = item.getCampo();
			if ((tipoDatos == char.class) && (item.getValor() != null)) {
				sentenciaWhere.append(primerFiltro == Boolean.TRUE ? "" : and);
				sentenciaWhere.append(" " + parameter + " = '" + item.getValor() + "' ");
			}
			if (tipoDatos == Timestamp.class) {
				// pendiente de implementacion
			}
			if ((tipoDatos == BigDecimal.class) && (item.getValor() != null)) {
				sentenciaWhere.append(primerFiltro == Boolean.TRUE ? "" : and);
				sentenciaWhere.append(" " + parameter + " = " + item.getValor() + " ");
			}
			if ((tipoDatos == Integer.class) && (item.getValor() != null)) {
				sentenciaWhere.append(primerFiltro == Boolean.TRUE ? "" : and);
				sentenciaWhere.append(" " + parameter + " = " + item.getValor() + " ");
			}
			if ((tipoDatos == String.class) && (item.getValor() != null)) {
				sentenciaWhere.append(primerFiltro == Boolean.TRUE ? "" : and);
				sentenciaWhere.append(" " + parameter + " = '" + item.getValor() + "' ");
			}
			if ((tipoDatos == Double.class) && (item.getValor() != null)) {
				sentenciaWhere.append(primerFiltro == Boolean.TRUE ? "" : and);
				sentenciaWhere.append(" " + parameter + " = " + item.getValor() + " ");
			}
			if ((tipoDatos == double.class) && (item.getValor() != null)) {
				sentenciaWhere.append(primerFiltro == Boolean.TRUE ? "" : and);
				sentenciaWhere.append(" " + parameter + " = " + item.getValor() + " ");
			}
			if ((tipoDatos == Boolean.class) && (item.getValor() != null)) {
				sentenciaWhere.append(primerFiltro == Boolean.TRUE ? "" : and);
				if (((Boolean) tipoDatos) == Boolean.TRUE)
					sentenciaWhere.append(" " + parameter + " = " + item.getValor() + " ");
				else
					sentenciaWhere.append(" " + parameter + " = " + item.getValor() + " ");
			}
			if (tipoDatos == Date.class) {
				// pendiente de implementacion
			}
		}
		return sentenciaWhere.toString();
	}

	public static List<Class> listarConPaginacion(DominioPaginacion parametroPaginacion,
			List<DominioParametroPersistencia> parametros, String nombreQuery, Class clazz, String condicionesConsulta,
			Session session, String nombreAlias) {

		/* DIALECTO */
		nombreQuery = nombreQueryDialecto(session, nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return new ArrayList();

		StringBuilder queryS = new StringBuilder();

		if (nombreQuery == null)
			queryS.append("SELECT e FROM " + clazz.getName() + " e");
		else
			queryS.append(session.getNamedQuery(nombreQuery).getQueryString());

		if (condicionesConsulta != null)
			queryS.append(condicionesConsulta);

		String sortOrderString = "ASC";
		if (parametroPaginacion.getPaginacionOrdenDireccion() == SORT_ORDER.DESC)
			sortOrderString = "DESC";

		if (parametroPaginacion.getPaginacionOrdenAtributo() != null) {
			queryS.append(" ORDER BY " + parametroPaginacion.getPaginacionOrdenAtributo() + " " + sortOrderString);
		}

		Query q = session.createSQLQuery(queryS.toString());

		q = GenericoBase.getNamedQueryByPatametersSet(q, parametros);

		if (UInteger.esCeroOrNulo(parametroPaginacion.getPaginacionRegistrosPorPagina()))
			parametroPaginacion.setPaginacionRegistrosPorPagina(50);
		if (UInteger.esCeroOrNulo(parametroPaginacion.getPaginacionRegistroInicio()))
			parametroPaginacion.setPaginacionRegistroInicio(0);

		if (parametroPaginacion.getPaginacionRegistrosPorPagina() > 0) {
			q.setMaxResults(parametroPaginacion.getPaginacionRegistrosPorPagina());
		}

		if (parametroPaginacion.getPaginacionRegistroInicio() > 0) {
			q.setFirstResult(parametroPaginacion.getPaginacionRegistroInicio());
		}

		q = q.setResultTransformer(Transformers.aliasToBean(clazz));

		List lstResultado = q.list();

		return lstResultado;
	}
	
	
	
}
