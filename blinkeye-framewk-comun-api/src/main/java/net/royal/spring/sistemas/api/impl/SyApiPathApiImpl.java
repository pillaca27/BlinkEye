package net.royal.spring.sistemas.api.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.web.api.impl.GenericoApiImpl;
import net.royal.spring.sistema.dominio.dto.DtoComunWsApipath;

@Repository
public class SyApiPathApiImpl extends GenericoApiImpl<DtoComunWsApipath> {

	public SyApiPathApiImpl() throws Exception {
		super(GenericoApiImpl.proxyComun, "spring/sistema/syapi", new ParameterizedTypeReference<List<DtoComunWsApipath>>() {
		});
	}

	public DtoComunWsApipath registrarPath(DtoComunWsApipath dto) throws Exception {
		return this.put("registrarPath", dto);
	}

}
