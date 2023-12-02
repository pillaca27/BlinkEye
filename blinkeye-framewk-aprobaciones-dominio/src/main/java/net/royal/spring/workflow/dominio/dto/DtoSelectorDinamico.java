package net.royal.spring.workflow.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;

public class DtoSelectorDinamico extends DtoTabla {
	private DominioPaginacion paginacion;

	public DtoSelectorDinamico() {
		this.paginacion = new DominioPaginacion();
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

}
