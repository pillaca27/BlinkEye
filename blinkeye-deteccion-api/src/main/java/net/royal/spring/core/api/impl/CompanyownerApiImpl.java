package net.royal.spring.core.api.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import net.royal.spring.core.dominio.dto.DtoComunCompanyowner;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.api.impl.GenericoApiImpl;

@Repository
public class CompanyownerApiImpl extends GenericoApiImpl<DtoComunCompanyowner> {

	private static Logger logger = LogManager.getLogger(EmpleadomastApiImpl.class);

	public CompanyownerApiImpl() throws Exception {
		super(GenericoApiImpl.proxyComun, "spring/core/companyownercomun",
				new ParameterizedTypeReference<List<DtoComunCompanyowner>>() {
				});
	}

	public List<DtoTabla> listarporusuarioactual() throws Exception {
		return this.getToListaDtoTabla("listarporusuarioactual");
	}
	
}
