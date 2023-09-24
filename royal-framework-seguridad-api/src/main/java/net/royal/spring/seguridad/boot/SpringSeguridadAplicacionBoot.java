package net.royal.spring.seguridad.boot;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
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
import net.royal.spring.framework.web.filter.GenericoSeguridadFiltroAutorizacion;
import net.royal.spring.framework.web.filter.GenericoSeguridadFiltroPublico;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan({ "net.royal.spring", "net.royal.publico", "net.royal.autorizacion" })
@EnableSwagger2
public class SpringSeguridadAplicacionBoot extends SpringBootServletInitializer {
	private static Logger logger = LogManager.getLogger(SpringSeguridadAplicacionBoot.class);

	public static final Contact DEFAULT_CONTACT = new Contact("Royal Systems", "http://vm-rsnt-srv-23/",
			"nt.pruebas.1@gmail.com");

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("spring-framework-seguridad-api",
			"spring-framework-seguridad-api", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
			"http://www.apache.org/licenses/LICENSE-2.0");
	
	public SpringSeguridadAplicacionBoot() throws IOException {
		String recursoRuta = UFile.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		Properties recursoPropiedad = UPropiedades.abrir(recursoRuta, ConstanteBoot.PROPERTIES_GLOBAL);
		String logPath = UAplicacion.obtenerParamatrosLog4j2(recursoPropiedad, SpringSeguridadConstanteBoot.NOMBRE,recursoRuta);
		Configurator.initialize(null,logPath);
	}
	
	public static void main(String[] args) throws IOException {
		String recursoRuta = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		Properties recursoPropiedad = UPropiedades.abrir(recursoRuta, ConstanteBoot.PROPERTIES_GLOBAL);
		SpringApplication app = new SpringApplication(SpringSeguridadAplicacionBoot.class);
		
		Map<String, Object> prop = UAplicacion.obtenerParamatrosDefecto(recursoPropiedad, SpringSeguridadConstanteBoot.NOMBRE,recursoRuta);
		app.setDefaultProperties(prop);
		app.run(args);
		
		logger.debug("SPRING FRAMEWORK SEGURIDAD : INICIADO");
	}

	@Bean
	public FilterRegistrationBean<GenericoSeguridadFiltroAutorizacion> setSeguridadFiltroAutorizado() {
		FilterRegistrationBean<GenericoSeguridadFiltroAutorizacion> registration = new FilterRegistrationBean<GenericoSeguridadFiltroAutorizacion>();
		registration.setFilter(new GenericoSeguridadFiltroAutorizacion());
		registration.addUrlPatterns("/autorizacion/seguridad/login/listarcompaniaslogin");
		registration.addUrlPatterns("/autorizacion/seguridad/login/listarcompaniasporusuario");
		registration.addUrlPatterns("/autorizacion/seguridad/login/ingresar");
		//Esto no debe ser publico
		//registration.addUrlPatterns("/autorizacion/seguridad/login/validartoken");
		registration.addUrlPatterns("/autorizacion/seguridad/login/recuperarclave");
		registration.setName("SeguridadFiltroAutorizacion");
		registration.setOrder(1);
		return registration;
	}

	@Bean
	public FilterRegistrationBean<SeguridadAutorizacionSecurityFilter> setSeguridadFiltroPrivado() {
		FilterRegistrationBean<SeguridadAutorizacionSecurityFilter> registration = new FilterRegistrationBean<SeguridadAutorizacionSecurityFilter>();
		registration.setFilter(new SeguridadAutorizacionSecurityFilter());
		registration.addUrlPatterns("/spring/seguridad/usuario/menucore");
		registration.addUrlPatterns("/spring/seguridad/usuario/obtenermenu"); 
		registration.addUrlPatterns("/spring/seguridad/usuario/obtenermenuporaplicacion");
		registration.addUrlPatterns("/spring/seguridad/usuario/obtenerusuarioactual");
		registration.addUrlPatterns("/spring/seguridad/usuario/cambiarClaveUsuario");
		//Aca es donde va
		registration.addUrlPatterns("/autorizacion/seguridad/login/validartoken");
		registration.setName("SeguridadFiltroPrivado");
		registration.setOrder(2);
		return registration;
	}

	@Bean
	public FilterRegistrationBean<GenericoSeguridadFiltroPublico> setSecurityPublicFilter() {
		FilterRegistrationBean<GenericoSeguridadFiltroPublico> registration = new FilterRegistrationBean<GenericoSeguridadFiltroPublico>();
		registration.setFilter(new GenericoSeguridadFiltroPublico());
		registration.addUrlPatterns("/spring/publico/*");	
		registration.setName("SecurityPublicFilter");
		registration.setOrder(1);
		return registration;
	}
	
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer init() {
		return new Jackson2ObjectMapperBuilderCustomizer() {
			@Override
			public void customize(Jackson2ObjectMapperBuilder builder) {
				//builder.timeZone(TimeZone.getDefault());
				builder.timeZone(TimeZone.getTimeZone("America/Lima"));
			}
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).select()
				.apis(RequestHandlerSelectors.basePackage("net.royal.spring")).build();
	}

}
