package net.royal.spring.framework.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UAplicacion {
	
	public static Map<String, Object> obtenerParamatrosDefecto(Properties prop,String modulo,String recursoRuta) {
		String port = prop.getProperty("port."+modulo);
		String log4j2 = prop.getProperty("log4j2."+modulo);
		log4j2 = recursoRuta + File.separator + log4j2;
		
		Map<String, Object> defaultProperties = new HashMap<>();
		defaultProperties.put("server.port", port);
		defaultProperties.put("logging.config", log4j2);		
		defaultProperties.put("server.error.include-message", "always");
		return defaultProperties;
	}
	
	public static String obtenerParamatrosLog4j2(Properties prop,String modulo,String recursoRuta) {
		Map<String, Object> defaultProperties = new HashMap<>();		
		String port = prop.getProperty("port."+modulo);
		String logj2 = prop.getProperty("log4j2."+modulo);
		logj2 = recursoRuta + File.separator + logj2;				
		return logj2;
	}
	
}
