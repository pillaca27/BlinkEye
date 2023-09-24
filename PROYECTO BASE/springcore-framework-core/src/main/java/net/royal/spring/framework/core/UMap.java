package net.royal.spring.framework.core;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;

import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UByte;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UString;

public class UMap {
	
	public static String reemplazarValores(Map parametros, String cadena) throws Exception {
		cadena = UString.obtenerSinNulo(cadena);		
		Iterator entries = parametros.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			if (value != null) {
				String strValor = UMap.obtenerValor(key,value);					
				cadena = cadena.replace("[" + key + "]", strValor);
			}
		}		
		return cadena;
	}
	
	public static byte[] obtenerBinarioParseado(byte[] rb, Map<String, Object> parametros) throws Exception {
		if (parametros == null)
			parametros = new HashMap();
		if (rb==null)
			return rb;
		
		String cuerpoCorreo = UByte.convertirString(rb);
		Iterator entries = parametros.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			if (value != null) {
				String strValor = obtenerValor(key,value);
				cuerpoCorreo = cuerpoCorreo.replace("[" + key + "]", strValor);
			}
			else {
				cuerpoCorreo = cuerpoCorreo.replace("[" + key + "]", "");	
			}
		}
		return cuerpoCorreo.getBytes();
	}
	
	public static String obtenerValor(String key,Object value) throws Exception {
		//logger.debug("value.getClass().getName():"+value.getClass().getName());		
		//logger.debug(value.getClass().getName());
		//logger.debug(value);		
		try {
			if(value == null) {
				return "";
			}			
			switch (value.getClass().getName()) { 
			case "java.lang.String":
				return (String) value;
			case "java.lang.Integer":
				return String.format("%,d", value);					
			case "java.math.BigDecimal":
				return UBigDecimal.convertirCadena((BigDecimal)value, "#,###,###.0000");
			case "double":
				return (String) value;
			case "java.lang.Double":
				double dnum = (Double)value;
				return Double.toString(dnum);
			case "java.util.Date":
				return UFechaHora.convertirFechaCadena((Date)value,"dd/MM/yyyy hh:mm:ss aaa");
			case "java.lang.Long":
				try {
					long lo = (Long)value;
					return Long.toString(lo);
				}catch (Exception e) {
					//logger.error("obtenerValor:["+key+"](error)-java.lang.Long");
					//logger.debug(e.getMessage());
				}
			case "java.util.LinkedHashMap":
				//logger.error("obtenerValor:["+key+"](no se puede convertir)-java.util.LinkedHashMap");
				return "";
			default:
				//logger.error("obtenerValor:["+key+"](sin tipo)-????");
				return (String) value;
			}
			
		}catch (Exception e) {
			//logger.error("obtenerValor:["+key+"](error al convertir)-????");
			//logger.debug(e.getMessage());
			e.printStackTrace();
			return "";
		}		
	}
	
}
