package net.royal.spring.framework.web.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;

public class GenericoJdbcDaoImpl {


	private Connection dbConnection = null;

	private String dbServerURL;
	private String dbUser;
	private String dbPassword;
	private String dbDriver;

	public ResultSet ejecutarLista(String query) throws SQLException {
		return ejecutarLista(query, null);
	}

	public ResultSet ejecutarLista(String query, List<DominioParametroPersistencia> parametros) throws SQLException {
		if (parametros == null)
			parametros = new ArrayList<DominioParametroPersistencia>();

		StringBuilder sb = new StringBuilder(query);
		Boolean res = Boolean.FALSE;
		// logger.debug(sb.toString());
		sb = GenericoBase.getNamedQueryByPatametersSet(sb, parametros);
		// logger.debug(sb.toString());
		getDBConnection();
		PreparedStatement preparedStatement = null;
		preparedStatement = dbConnection.prepareStatement(sb.toString());
		return preparedStatement.executeQuery();
	}

	public void cerrarConection() {
	}

	public void cerrarConsulta(ResultSet rs) throws SQLException {
		rs.close();
	}

	public Boolean ejecutar(String query, List<DominioParametroPersistencia> parametros) throws SQLException {
		StringBuilder sb = new StringBuilder(query);
		Boolean res = Boolean.FALSE;
		// logger.debug(sb.toString());
		sb = GenericoBase.getNamedQueryByPatametersSet(sb, parametros);
		// logger.debug(sb.toString());
		getDBConnection();
		PreparedStatement preparedStatement = null;
		preparedStatement = dbConnection.prepareStatement(sb.toString());
		res = preparedStatement.execute();
		cerrarConection();
		return res;
	}

	public void asignar(String dbDriver, String dbServerURL, String dbUser, String dbPassword) {
		this.dbDriver = dbDriver;
		this.dbServerURL = dbServerURL;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
	}

	public Connection getDBConnection() {
		// logger.debug(dbServerURL);
		// logger.debug(dbUser);
		// logger.debug(dbPassword);
		// logger.debug(dbDriver);

		dbConnection = null;
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(dbServerURL, dbUser, dbPassword);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
}
