package net.royal.spring.framework.web.database;

import java.security.Key;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;

import com.itextpdf.text.pdf.codec.Base64;

import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBootDB;

public class HibernateCore {
	private static Logger logger = LogManager.getLogger(HibernateCore.class);

	public static String ENCRYPT_KEY = "1234567890123456";

	public static String decrypt(String encrypted) throws Exception {
		byte[] encryptedBytes = Base64.decode(encrypted.replace("\n", ""));

		Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, aesKey);

		String decrypted = new String(cipher.doFinal(encryptedBytes));

		return decrypted;
	}

	protected DataSource crearDataSource(Properties configuracion) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		String modoConexion = configuracion.getProperty(ConstanteBootDB.DB_MODO);
		String jndi = configuracion.getProperty(ConstanteBootDB.DB_JNDI);
		
		if (UString.estaVacio(modoConexion)) {
			modoConexion = "P";
		}

		if (modoConexion.equals("J")&& !UString.estaVacio(jndi)) {
			DataSource ds = null;
			try {
				ds = (DataSource) new JndiTemplate().lookup(jndi);
				System.out.println("Leyendo JNDI : " + jndi);
			} catch (NamingException e) {
				System.out.println(e.getMessage());
			}
			return ds;
		} else {
			dataSource.setDriverClassName(configuracion.getProperty(ConstanteBootDB.DB_DRIVER));
			String encriptado = configuracion.getProperty(ConstanteBootDB.DB_ENCRIPTADO);
			if (UString.estaVacio(encriptado)) {
				encriptado = "N";
			}
			if (encriptado.equals("S")) {
				try {
					dataSource.setUrl(decrypt(configuracion.getProperty(ConstanteBootDB.DB_URL)));
					dataSource.setUsername(decrypt(configuracion.getProperty(ConstanteBootDB.DB_USERNAME)));
					dataSource.setPassword(decrypt(configuracion.getProperty(ConstanteBootDB.DB_PASSWORD)));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				dataSource.setUrl(configuracion.getProperty(ConstanteBootDB.DB_URL));
				dataSource.setUsername(configuracion.getProperty(ConstanteBootDB.DB_USERNAME));
				dataSource.setPassword(configuracion.getProperty(ConstanteBootDB.DB_PASSWORD));
			}
		}
		return dataSource;
	}

	protected Properties crearHibernateProperties(Properties configuracion) {
		// System.err.println("***** lectura de propeade");
		Properties p = new Properties();
		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.HIBERNATE_DIALECT)))
			p.put(ConstanteBootDB.HIBERNATE_DIALECT, configuracion.getProperty(ConstanteBootDB.HIBERNATE_DIALECT));
		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.HIBERNATE_SHOW_SQL)))
			p.put(ConstanteBootDB.HIBERNATE_SHOW_SQL, configuracion.getProperty(ConstanteBootDB.HIBERNATE_SHOW_SQL));
		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.HIBERNATE_HBM2DDL_AUTO)))
			p.put(ConstanteBootDB.HIBERNATE_HBM2DDL_AUTO,
					configuracion.getProperty(ConstanteBootDB.HIBERNATE_HBM2DDL_AUTO));
		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.HIBERNATE_MULTITENANCY_TYPE)))
			p.put(ConstanteBootDB.HIBERNATE_MULTITENANCY_TYPE,
					configuracion.getProperty(ConstanteBootDB.HIBERNATE_MULTITENANCY_TYPE));
		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.HIBERNATE_MULTITENANCY_RESOLVER)))
			p.put(ConstanteBootDB.HIBERNATE_MULTITENANCY_RESOLVER, CurrentTenantIdentifierResolverImpl.class);
		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.HIBERNATE_MULTITENANCY_PROVIDER)))
			p.put(ConstanteBootDB.HIBERNATE_MULTITENANCY_PROVIDER, MultiTenantConnectionProvider.class);

		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.C3P0_MIN_SIZE)))
			p.put(ConstanteBootDB.C3P0_MIN_SIZE, configuracion.getProperty(ConstanteBootDB.C3P0_MIN_SIZE));

		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.C3P0_MAX_SIZE)))
			p.put(ConstanteBootDB.C3P0_MAX_SIZE, configuracion.getProperty(ConstanteBootDB.C3P0_MAX_SIZE));

		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.C3P0_ACQUIRE_INREMENT)))
			p.put(ConstanteBootDB.C3P0_ACQUIRE_INREMENT,
					configuracion.getProperty(ConstanteBootDB.C3P0_ACQUIRE_INREMENT));

		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.C3P0_MAX_STATEMENTS)))
			p.put(ConstanteBootDB.C3P0_MAX_STATEMENTS, configuracion.getProperty(ConstanteBootDB.C3P0_MAX_STATEMENTS));

		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.C3P0_IDLE_TEST_PERIOD)))
			p.put(ConstanteBootDB.C3P0_IDLE_TEST_PERIOD,
					configuracion.getProperty(ConstanteBootDB.C3P0_IDLE_TEST_PERIOD));

		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.C3P0_INITIAL_POOL_SIZE)))
			p.put(ConstanteBootDB.C3P0_INITIAL_POOL_SIZE,
					configuracion.getProperty(ConstanteBootDB.C3P0_INITIAL_POOL_SIZE));

		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.C3P0_MAX_CONNECTION_AGE)))
			p.put(ConstanteBootDB.C3P0_MAX_CONNECTION_AGE,
					configuracion.getProperty(ConstanteBootDB.C3P0_MAX_CONNECTION_AGE));

		if (!UString.esNuloVacio(configuracion.getProperty(ConstanteBootDB.C3P0_TIMEOUT)))
			p.put(ConstanteBootDB.C3P0_TIMEOUT, configuracion.getProperty(ConstanteBootDB.C3P0_TIMEOUT));

		logger.debug(p);
		return p;
	}
}
