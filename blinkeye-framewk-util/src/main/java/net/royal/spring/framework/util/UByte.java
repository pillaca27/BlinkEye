package net.royal.spring.framework.util;

import java.util.Base64;

public class UByte {
	public static String convertirString(byte[] arreglo) {
		if (arreglo == null)
			return null;
		return new String(arreglo);
	}
	
	public static byte[] obtenerArchivoDesde(String cuerpoBase64, byte[] cuerpoBytes) {
		/*if (!UString.esNuloVacio(correo.getCuerpoCorreoBase64())) {
			String sinSalto = correo.getCuerpoCorreoBase64();				
			byte[] addcuerpo = Base64.getDecoder().decode(sinSalto);	//ALEJANDRO, no cambiar			 
			correo.setCuerpoCorreoBytes(addcuerpo);
		} else {
		}*/		
		if (cuerpoBytes!=null)
			return cuerpoBytes;
		
		String sinSalto = cuerpoBase64;
		byte[] addcuerpo = Base64.getDecoder().decode(sinSalto);
		
		return addcuerpo;
	}
}
