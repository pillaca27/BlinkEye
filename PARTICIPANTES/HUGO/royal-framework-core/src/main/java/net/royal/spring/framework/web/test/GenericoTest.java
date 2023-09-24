package net.royal.spring.framework.web.test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioLogin;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.constante.ConstanteFiltro;

public class GenericoTest {
	private static Logger logger = LogManager.getLogger(GenericoTest.class);
	
	public static String apilogin;
	public static String api;
	private static String server;
	public static String nombreServicio;
	private static String recursoRuta;
	private static Properties prop;
	
	//public static UsuarioSeguridad usuarioSeguridad;
	public static SeguridadUsuarioActual usuarioActual;
	public static String idSid = "AAEZ";
	public static String idUsuario = "001";
	public static String idClave = "123";
	public static String idAplicacion = "BP";
	public static SeguridadUsuarioLogin springToken=null;
	
	public GenericoTest() throws Exception{
		String sRuta = System.getProperty("user.dir");
		String sRuta2 = UWebServer.rutaFisicaWebApp();
				
		recursoRuta = sRuta2 + File.separator + ConstanteBoot.CARPETA_PROPERTIES;		
		System.out.println(recursoRuta);
		prop = UPropiedades.getInstance().abrir(recursoRuta,ConstanteBoot.PROPERTIES_GLOBAL);		
		System.out.println(prop);
		
		if (prop!=null){
			apilogin = prop.getProperty("proxy.seguridad.autorizacion") + "/spring/seguridad/autorizacion/login";
		}
	}
	
	public GenericoTest(String nombreServicio) throws Exception{
		recursoRuta = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;		
		System.out.println(recursoRuta);
		logger.debug(recursoRuta);
		prop = UPropiedades.getInstance().abrir(recursoRuta,ConstanteBoot.PROPERTIES_GLOBAL);		
		System.out.println(prop);
		logger.debug(prop);
		
		if (prop!=null){
			apilogin = prop.getProperty("proxy.seguridad.autorizacion") + "/spring/seguridad/autorizacion/login";
		}
		this.nombreServicio = nombreServicio;
		logger.debug(apilogin);
		server = prop.getProperty("port." + nombreServicio);
		server = "http://localhost:" + server;
		logger.debug(server);
	}
	
	
	public String getServer(){
		if (prop!=null){
			server = prop.getProperty("port." + nombreServicio);
			server = "http://localhost:" + server;			
			return server;	
		}else{
			return "http://localhost:8070";	
		}		
	}
	public HttpHeaders getHeaders(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		if (springToken!=null){
			if ( !UString.esNuloVacio(springToken.getToken())) {
				headers.add(ConstanteFiltro.TOKEN, springToken.getToken());
			}
				
		}
		return headers;
	}
	public SeguridadUsuarioActual crearUsuarioActual() {
		return null;
	}
	public SeguridadUsuarioActual crearUsuarioSeguridad() {
		SeguridadUsuarioActual seg=new SeguridadUsuarioActual();
		if (prop!=null){
			seg.setSid(prop.getProperty("test.usuario.sid"));
			seg.setToken(prop.getProperty("test.usuario.token"));			
			seg.setUsuario(prop.getProperty("test.usuario.login"));
			seg.setClave(prop.getProperty("test.usuario.clave"));			
			seg.setAplicacionCodigo(prop.getProperty("test.usuario.aplicacionid"));
			seg.setCompaniaCodigo(prop.getProperty("test.usuario.companiaid"));
			//seg.setSucursalId(prop.getProperty("test.usuario.sucursalid"));
			//seg.setCajaId(prop.getProperty("test.usuario.cajaid"));			
		}
		return seg;
	}
	
	public ResponseEntity<String> apiEliminarPruebas() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(this.getServer() + api + "/eliminarpruebas");
		HttpEntity request = new HttpEntity(null, this.getHeaders());
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.DELETE, request, String.class);
		return result;
	}
	
	public ResponseEntity<SeguridadUsuarioLogin> apiLogin(SeguridadUsuarioLogin usuLocal) throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();
		if (usuLocal==null)
			usuLocal = this.crearUsuarioSeguridad();		
		HttpEntity<SeguridadUsuarioLogin> req = new HttpEntity<SeguridadUsuarioLogin>(usuLocal, this.getHeaders());
		ResponseEntity<SeguridadUsuarioLogin> resLogin = restTemplate.exchange(apilogin, HttpMethod.POST, req, SeguridadUsuarioLogin.class);
		springToken = resLogin.getBody();
		return resLogin;
	}
}
