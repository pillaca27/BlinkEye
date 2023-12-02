package net.royal.spring.framework.core;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lowagie.text.pdf.PdfReader;

import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UString;

public class UWebServer {

	private static final Log LOGGER = LogFactory.getLog(UWebServer.class);

	public static String rutaFisicaWebServer() {		
		return rutaFisicaWeb("webserver");
	}
	public static String rutaFisicaWebApp() {		
		String rutaWS = System.getProperty("user.dir");
		String sos = System.getProperty("os.name");
		//String rutaIo = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
		//String rutaPA = Paths.get("").toAbsolutePath().toString();
		//System.out.println("rutaWS:"+rutaWS);
		//System.out.println("rutaIo:"+rutaIo);
		//System.out.println("rutaPA:"+rutaPA);
		//System.out.println("sos:"+sos);
		
		String search = File.separator + "bin";
		int index = rutaWS.lastIndexOf(search);
		if (index>-1) {
			//System.out.println("cuando es bin:windows");
			rutaWS = rutaWS.substring(0, index);
			// esto se aplica cuando va en un tomcat
			//System.out.println(rutaWS);
			return rutaWS;
		}
		
		//System.out.println("h3 -api:windows");
		search = "-api";
		index = rutaWS.lastIndexOf(search);
		if (index>-1) {
			//System.out.println("cuando es -api:windows");
			index = rutaWS.lastIndexOf(File.separator);
			rutaWS = rutaWS.substring(0, index);
			// esto se aplica cuando va en un tomcat
			//System.out.println(rutaWS);
			return rutaWS;
		}
		
		//System.out.println("h4 TOMCAT");
		search = "TOMCAT";
		index = rutaWS.toUpperCase().lastIndexOf(search);
		if (index>-1) {
			//System.out.println("cuando es TOMCAT");
			rutaWS = rutaWS + File.separator + "webapps";
			//System.out.println(rutaWS);
			return rutaWS;
		}
		
		//System.out.println("h5 jar eclipse:boot");
		int jarIndex = rutaWS.lastIndexOf(File.separator);
		if (jarIndex>-1) {
			//System.out.println("cuando es un jar eclipse:boot");
			// esto se aplica cuando es un jar
			if (sos.contains("Windows")) {
				//System.out.println("windows");
				rutaWS = rutaWS.substring(0, jarIndex);
				//System.out.println(rutaWS);
			}	else {
				//System.out.println("linux");
			}
			return rutaWS;
		}
		return rutaWS;
	}
	private static String rutaFisicaWeb(String tipo) {		
		//System.out.println("h1 main");
		String sos = System.getProperty("os.name");
		//System.out.println("sos");
		//System.out.println(sos);
		//System.out.println(System.getProperty("os.arch"));
		if (UString.esNuloVacio(tipo))
			return null;
		String rutaWS = System.getProperty("user.dir");
		//System.out.println("rutaWS");
		//System.out.println(rutaWS);
		if (tipo.equals("webserver")) {
			//System.out.println("h2 webserver devolviendo");
			//System.out.println(rutaWS);
			String search = File.separator + "bin";
			int index = rutaWS.lastIndexOf(search);
			if (index>-1) {
				//System.out.println("cuando es bin:windows");
				rutaWS = rutaWS.substring(0, index);
				// esto se aplica cuando va en un tomcat
				//System.out.println(rutaWS);
				return rutaWS;
			}
			
			//System.out.println("h3 -api:windows");
			search = "-api";
			index = rutaWS.lastIndexOf(search);
			if (index>-1) {
				//System.out.println("cuando es -api:windows");
				index = rutaWS.lastIndexOf(File.separator);
				rutaWS = rutaWS.substring(0, index);
				// esto se aplica cuando va en un tomcat
				//System.out.println(rutaWS);
				return rutaWS;
			}
			
			//System.out.println("h4 TOMCAT");
			search = "TOMCAT";
			index = rutaWS.toUpperCase().lastIndexOf(search);
			if (index>-1) {
				//System.out.println("cuando es TOMCAT");
				//System.out.println(rutaWS);
				return rutaWS;
			}
			
			//System.out.println("h5 jar eclipse:boot");
			int jarIndex = rutaWS.lastIndexOf(File.separator);
			if (jarIndex>-1) {
				//System.out.println("cuando es un jar eclipse:boot");
				// esto se aplica cuando es un jar
				if (sos.contains("Windows")) {
					//System.out.println("windows");
					rutaWS = rutaWS.substring(0, jarIndex);
					//System.out.println(rutaWS);
				}	else {
					//System.out.println("linux");
				}
				return rutaWS;
			}
			return rutaWS;
		}
		if (tipo.equals("webcontext")) {
			
		}
		if (tipo.equals("webapp")) {
			
		}
		return null;
	}
		
}

