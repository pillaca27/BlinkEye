package net.royal.spring.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class UPropiedades {
	
	private static UPropiedades instance = null;

	public static void imprimir(Properties p) {
		Enumeration keys = p.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = (String) p.get(key);
			System.out.println(key + ": " + value);
		}
	}

	public static UPropiedades getInstance() {
		if (instance == null) {
			instance = new UPropiedades();
		}
		return instance;
	}

	public static Properties abrir(String archivoPropiedades) throws IOException {
		Properties prop = new Properties();
		InputStream input = UPropiedades.getInstance().getClass().getClassLoader().getResourceAsStream(archivoPropiedades);
		prop.load(input);
		return prop;
	}
	
	public static Properties abrir(String ruta,String archivoPropiedades) throws IOException {
//		System.err.println("ruta-archivoPropiedades");
//		System.err.println(ruta);
//		System.err.println(archivoPropiedades);
		String pat = UString.obtenerSinNulo(ruta) + File.separator + UString.obtenerSinNulo(archivoPropiedades); 
		File f=new File(pat);
		if (!f.exists())
		{
			System.err.println("no existe : " + ruta + File.separator + archivoPropiedades);
			return abrir(archivoPropiedades);
		}
		Properties pro = new Properties();
		pro.load(new FileInputStream(ruta + File.separator + archivoPropiedades));
		return pro;
	}

	public String obtenerValor(String archivoPropiedades, String key) throws Exception {
		String r = null;
		Properties p = abrir(archivoPropiedades);
		if (p == null)
			return r;
		r = p.getProperty(key);
		return r;
	}
}
