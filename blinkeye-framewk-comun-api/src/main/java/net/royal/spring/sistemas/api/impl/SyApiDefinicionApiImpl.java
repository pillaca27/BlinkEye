package net.royal.spring.sistemas.api.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.web.api.impl.GenericoApiImpl;
import net.royal.spring.sistema.dominio.dto.DtoComunWsDefinicion;

@Repository
public class SyApiDefinicionApiImpl extends GenericoApiImpl<DtoComunWsDefinicion> {

	public SyApiDefinicionApiImpl() throws Exception {
		super(GenericoApiImpl.proxyComun,
				"spring/sistema/syapi",
				new ParameterizedTypeReference<List<DtoComunWsDefinicion>>() {
				});
	}

	public DtoComunWsDefinicion registrarDefinicion(DtoComunWsDefinicion dto) throws Exception {
		return this.put("registrarDefinicion", dto);
	}

}
