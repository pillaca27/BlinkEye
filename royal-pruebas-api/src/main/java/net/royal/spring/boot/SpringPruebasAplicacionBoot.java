package net.royal.spring.boot;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.royal.spring.framework.core.UAplicacion;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.filter.GenericoSeguridadFiltroPrivado;
import net.royal.spring.framework.web.filter.GenericoSeguridadFiltroPublico;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan({ "net.royal.spring", "net.royal.publico" })
@EnableSwagger2
public class SpringPruebasAplicacionBoot extends SpringBootServletInitializer {
	private static Logger logger = LogManager.getLogger(SpringPruebasAplicacionBoot.class);

	@Value("${securityServer}")
	private String securityServer;

	public static final Contact DEFAULT_CONTACT = new Contact("Royal Systems", "http://vm-rsnt-srv-23/",
			"nt.pruebas.1@gmail.com");

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("spring-framework-comun-api",
			"spring-framework-comun-api", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
			"http://www.apache.org/licenses/LICENSE-2.0");

	public SpringPruebasAplicacionBoot() throws IOException {
		String recursoRuta = UFile.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		Properties recursoPropiedad = UPropiedades.abrir(recursoRuta, ConstanteBoot.PROPERTIES_GLOBAL);
		String logPath = UAplicacion.obtenerParamatrosLog4j2(recursoPropiedad, SpringPruebasConstanteBoot.NOMBRE,
				recursoRuta);
		Configurator.initialize(null, logPath);
	}

	public static void main(String[] args) throws IOException {
		String recursoRuta = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		Properties recursoPropiedad = UPropiedades.abrir(recursoRuta, ConstanteBoot.PROPERTIES_GLOBAL);
		SpringApplication app = new SpringApplication(SpringPruebasAplicacionBoot.class);

		Map<String, Object> prop = UAplicacion.obtenerParamatrosDefecto(recursoPropiedad,
				SpringPruebasConstanteBoot.NOMBRE, recursoRuta);
		app.setDefaultProperties(prop);
		app.run(args);

		logger.debug("SPRING PRUEBAS API : INICIADO");
	}

	@Bean
	public FilterRegistrationBean<GenericoSeguridadFiltroPrivado> setSecurityFilter() {
		FilterRegistrationBean<GenericoSeguridadFiltroPrivado> registration = new FilterRegistrationBean<GenericoSeguridadFiltroPrivado>();
		registration.setFilter(new GenericoSeguridadFiltroPrivado());
		registration.addUrlPatterns("/spring/gth/*");
		registration.setName("SecurityFilter");
		registration.setOrder(1);
		return registration;
	}

	@Bean
	public FilterRegistrationBean<GenericoSeguridadFiltroPublico> setFilterPublic() {
		FilterRegistrationBean<GenericoSeguridadFiltroPublico> registration = new FilterRegistrationBean<GenericoSeguridadFiltroPublico>();
		registration.setFilter(new GenericoSeguridadFiltroPublico());
		registration.addUrlPatterns("/publico/*");
		registration.setName("FilterPublic");
		registration.setOrder(2);
		return registration;
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer init() {
		return new Jackson2ObjectMapperBuilderCustomizer() {
			public void customize(Jackson2ObjectMapperBuilder builder) {
				// builder.timeZone(TimeZone.getDefault());
				builder.timeZone(TimeZone.getTimeZone("America/Lima"));
			}
		};
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).select()
				.apis(RequestHandlerSelectors.basePackage("net.royal")).build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	@Bean
	public String securityServer() {
		return securityServer;
	}

}
