package net.royal.spring.workflow.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.workflow.dominio.filtro.FiltroReemplazo;

public class DtoReemplazoPaginacion {
	private FiltroReemplazo filtro;
	private DominioPaginacion paginacion;

	public DtoReemplazoPaginacion() {
		this.filtro = new FiltroReemplazo();
		this.paginacion = new DominioPaginacion();
	}

	public FiltroReemplazo getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroReemplazo filtro) {
		this.filtro = filtro;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
}
