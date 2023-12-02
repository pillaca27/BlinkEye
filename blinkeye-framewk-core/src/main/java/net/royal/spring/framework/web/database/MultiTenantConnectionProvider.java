package net.royal.spring.framework.web.database;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

import com.mchange.v2.c3p0.C3P0Registry;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.PooledDataSource;

import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.util.UPropiedades;

public class MultiTenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	private static final long serialVersionUID = 6241633589847209550L;
	private static Logger logger = LogManager.getLogger(MultiTenantConnectionProvider.class);

	private ComboPooledDataSource defaultDataSource;

	public static final String DEFAULT_DATABASE = "shared";

	private String urlGeneral;
	private String usuarioGeneral;
	private String claveGeneral;
	private String driverGeneral;

	private String springParametroSid;
	private String springParametroUri;
	private String springParametroUsr;
	private String springParametroPwd;
	private String springParametroDri;

	private String springParametroTabla;
	private String springParametroPk;

	private String campos;

	public MultiTenantConnectionProvider() throws IOException {

		cargarDatosBaseDatosMaestra();

		defaultDataSource = new ComboPooledDataSource(DEFAULT_DATABASE);
		defaultDataSource.setJdbcUrl(urlGeneral);
		defaultDataSource.setUser(usuarioGeneral);
		defaultDataSource.setPassword(claveGeneral);

		logger.debug("1.1");
		try {
			defaultDataSource.setDriverClass(driverGeneral);
		} catch (PropertyVetoException e) {
			logger.debug("2.1");
			e.printStackTrace();
		}
		logger.debug("3.1");
	}

	public void cargarDatosBaseDatosMaestra() throws IOException {
		logger.debug("cargarDatosBaseDatosMaestra - inicio");

		Properties appProps = UPropiedades.abrir("bootstrap.properties");

		urlGeneral = appProps.getProperty("spring.datasource.url");
		usuarioGeneral = appProps.getProperty("spring.datasource.username");
		claveGeneral = appProps.getProperty("spring.datasource.password");
		driverGeneral = appProps.getProperty("spring.datasource.driver-class-name");

		springParametroSid = appProps.getProperty("spring.parametro.sid");
		springParametroUri = appProps.getProperty("spring.parametro.uri");
		springParametroUsr = appProps.getProperty("spring.parametro.usr");
		springParametroPwd = appProps.getProperty("spring.parametro.pwd");
		springParametroDri = appProps.getProperty("spring.parametro.dri");

		springParametroTabla = appProps.getProperty("spring.parametro.tab");
		springParametroPk = appProps.getProperty("spring.parametro.prk");

		campos = new StringBuilder(" ").append(springParametroSid).append(",").append(springParametroUri).append(",")
				.append(springParametroUsr).append(",").append(springParametroPwd).append(",")
				.append(springParametroDri).append(" ").toString();

		logger.debug("cargarDatosBaseDatosMaestra - fin");
	}

	@Override
	protected DataSource selectAnyDataSource() {
		return defaultDataSource;
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		// logger.debug("selectDataSource - inicio");
		PooledDataSource pds = C3P0Registry.pooledDataSourceByName(tenantIdentifier);

		ComboPooledDataSource cpds;

		if (pds == null) {

			ClienteDatabase clienteDatabase = getCliente(tenantIdentifier);

			if (clienteDatabase == null)
				return C3P0Registry.pooledDataSourceByName(DEFAULT_DATABASE);

			cpds = new ComboPooledDataSource(tenantIdentifier);
			cpds.setJdbcUrl(clienteDatabase.getUri());
			cpds.setUser(clienteDatabase.getUser());
			cpds.setPassword(clienteDatabase.getPassword());
			try {
				cpds.setDriverClass(clienteDatabase.getDriver());
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			// logger.debug("selectDataSource - fin");
			return cpds;
		}
		// logger.debug("selectDataSource - fin");
		return pds;

	}

	public ClienteDatabase getCliente(String sid) {
		logger.debug("getCliente - inicio");
		ClienteDatabase database = new ClienteDatabase();
		PreparedStatement stmt = null;
		try {

			String query = "select " + campos + " from " + springParametroTabla + " where " + springParametroPk
					+ " = ?";

			stmt = getConnection().prepareStatement(query);
			stmt.setString(1, sid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				database.setSid(rs.getString(1));
				database.setUri(rs.getString(2));
				database.setUser(rs.getString(3));
				database.setPassword(rs.getString(4));
				database.setDriver(rs.getString(5));
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		logger.debug("getCliente - fin");
		return database;
	}

	public Connection getConnection() throws SQLException {
		logger.debug("getConnection - inicio");
		logger.debug(urlGeneral);
		logger.debug(usuarioGeneral);
		logger.debug(claveGeneral);
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", usuarioGeneral);
		connectionProps.put("password", claveGeneral);
		conn = DriverManager.getConnection(urlGeneral, connectionProps);
		logger.debug("getConnection - fin");
		return conn;
	}

}