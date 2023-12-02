package net.royal.spring.sistema.dominio.filtro;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunApi {

	private Integer api;
	private String ruta;
	private DominioPaginacion paginacion;

	private BigDecimal ROWNUM_;

	public FiltroComunApi() {
		this.paginacion = new DominioPaginacion();
	}

	public Integer getApi() {
		return api;
	}

	public void setApi(Integer api) {
		this.api = api;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

}
