package net.royal.spring.core.servicio.impl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "PersonaSunatServicioImpl")
public class PersonaSunatServicioImpl extends GenericoServicioImpl {
public static String SPRING_NOMBRE = "PersonaSunatServicioImpl";
private static Logger logger = LogManager.getLogger(PersonaSunatServicioImpl.class);

		
@Transactional
public String puedeConsultarRuc() throws Exception {
			
	String realizarConsulta = parametroObtenerTexto("SY", "CONSULRUC");
			
	if(UString.estaVacio(realizarConsulta)) {
		realizarConsulta = "N";
	}
			
		return realizarConsulta;
	}
		
}
