package net.royal.spring.core.api.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import net.royal.spring.core.dominio.dto.DtoComunEmpleadomast;
import net.royal.spring.core.dominio.dto.DtoComunMaUnidadnegocio;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.web.api.impl.GenericoApiImpl;

@Repository
public class EmpleadomastApiImpl extends GenericoApiImpl<DtoComunEmpleadomast> {

	private static Logger logger = LogManager.getLogger(EmpleadomastApiImpl.class);

	public EmpleadomastApiImpl() throws Exception {
		super(GenericoApiImpl.proxyComun, "spring/core/empleadomastcomun",
				new ParameterizedTypeReference<List<DtoComunEmpleadomast>>() {
				});
	}

	public Integer obtenerMaxEmpleado(String codigousuario) throws Exception {
		
		DtoComunEmpleadomast dto= new DtoComunEmpleadomast();
		dto.setCodigousuario(codigousuario);
		
		BigDecimal obj=this.putToBigDecimal("obtenerMaxEmpleado", dto);
		
		Integer retorno=UBigDecimal.esCeroOrNulo(obj)? 0:obj.intValue();
		
		return retorno;
	}
	
	
}
