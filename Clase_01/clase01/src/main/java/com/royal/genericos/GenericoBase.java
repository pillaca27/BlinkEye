package com.royal.genericos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import com.royal.util.DominioParametroPersistencia;
import com.royal.util.UString;

public class GenericoBase {
	
	private static final String DIALECTO_ACTUAL = "ORACLE";
	private static String DIALECTO_SESSION = null;
	
	public static Boolean validarAlias(String nombreAlias, String nombreQuery) {
		if (nombreAlias == null)
			return true;
		if (nombreQuery == null)
			return true;
		int indicador = nombreQuery.indexOf(nombreAlias + ".");
		if (indicador == -1) {
			System.out.println("El nombre del alias:(" + nombreAlias + "), no se encuentra en el query:(" + nombreQuery
					+ "). Verifique el nombre de los metodos en el archivo xml donde se encuentra la sentencia sql.");
			return false;
		}
		return true;
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

		}
		return new StringBuilder(sql);
	}

	
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
				 return nombreQuery;// + "_" + DIALECTO_SESSION;
				//return DIALECTO_SESSION + "_" + nombreQuery;
			}
		} catch (Exception e) {
			return nombreQuery;
		}
		return nombreQuery;
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


}