package net.royal.spring.controlpatrimonial.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunFaActivo {
	private String companiasocio;
	private String activo;
	private String busquedalocal;
	private String codigointerno;
	private String numeroplaca;	
	private String estado;
	private DominioPaginacion paginacion=new DominioPaginacion();
	
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getBusquedalocal() {
		return busquedalocal;
	}

	public void setBusquedalocal(String busquedalocal) {
		this.busquedalocal = busquedalocal;
	}

	public String getCodigointerno() {
		return codigointerno;
	}

	public void setCodigointerno(String codigointerno) {
		this.codigointerno = codigointerno;
	}

	public String getNumeroplaca() {
		return numeroplaca;
	}

	public void setNumeroplaca(String numeroplaca) {
		this.numeroplaca = numeroplaca;
	}

	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
