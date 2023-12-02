package net.royal.spring.core.api.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import net.royal.spring.core.dominio.dto.DtoComunMaUnidadnegocio;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.api.impl.GenericoApiImpl;

@Repository
public class MaUnidadnegocioApiImpl extends GenericoApiImpl<DtoComunMaUnidadnegocio> {

	private static Logger logger = LogManager.getLogger(MaUnidadnegocioApiImpl.class);

	public MaUnidadnegocioApiImpl() throws Exception {
		super(GenericoApiImpl.proxyComun, "spring/core/maunidadnegociocomun",
				new ParameterizedTypeReference<List<DtoComunMaUnidadnegocio>>() {
				});
	}

	public List<DtoTabla> listaractivos() throws Exception {
		return this.getToListaDtoTabla("listaractivos");
	}
}
