package net.royal.spring.workflow.boot;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.constante.ConstanteBootDB;
import net.royal.spring.framework.web.database.GenericoHibernateConfiguration;
import net.royal.spring.framework.web.database.HibernateCore;

@Configuration
public class SpringWorkflowHibernateConfiguration extends HibernateCore {
	private static Logger logger = LogManager.getLogger(GenericoHibernateConfiguration.class);
	private static String recursoRuta;
	private static Properties recursoPropiedad;

	@Autowired
	private ResourceLoader rl;

	@Bean(value = "sessionFactory")
	public LocalSessionFactoryBean getSessionFactory() throws IOException {
		Properties configuracion = null;
		String bdPorDefecto = null;

		/*
		 * Agregado solo para los casos Unit Testing
		 */
		if (recursoPropiedad == null) {
			recursoRuta = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
			recursoPropiedad = UPropiedades.getInstance().abrir(recursoRuta, ConstanteBoot.PROPERTIES_GLOBAL);
			logger.debug(recursoRuta);
			logger.debug(recursoPropiedad);
		}

		bdPorDefecto = recursoPropiedad.getProperty(ConstanteBoot.SERVER_BASEDATOS_PORDEFECTO);

		if (!UString.esNuloVacio(bdPorDefecto))
			configuracion = UPropiedades.getInstance().abrir(recursoRuta, bdPorDefecto + ".properties");

		if (configuracion == null)
			configuracion = UPropiedades.getInstance().abrir(recursoRuta,
					ConstanteBootDB.DATABASE_GENERICO + ".properties");

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource(configuracion));
		sessionFactory.setPackagesToScan(configuracion.getProperty(ConstanteBootDB.SPRING_PACKAGES_TO_SCAN));
		sessionFactory.setMappingLocations(loadResources());
		sessionFactory.setHibernateProperties(crearHibernateProperties(configuracion));
		return sessionFactory;
	}

	private Resource[] loadResources() {
		Resource[] resources = null;
		try {
			resources = ResourcePatternUtils.getResourcePatternResolver(rl)
					.getResources("classpath*:**/hibernate/**/*.hbm.xml");
		} catch (IOException e) {

			e.printStackTrace();
		}
		return resources;
	}

	@Bean
	public DataSource dataSource(Properties configuracion) {
		return crearDataSource(configuracion);
	}

	@Bean
	public HibernateTransactionManager transactionManager() throws IOException {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(getSessionFactory().getObject());
		return txManager;
	}

	@Bean
	public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}
}