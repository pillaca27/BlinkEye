package net.royal.spring.sy.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunSyReporte {

	private String aplicacioncodigo;
	private String reportecodigo;
	private String estado;
	private String topico;
	private String nombre;
	private String tiporeporte;
	private String tipoexportar;
	private String usuario;
	
	public String getTipoexportar() {
		return tipoexportar;
	}
	public void setTipoexportar(String tipoexportar) {
		this.tipoexportar = tipoexportar;
	}
	
	private DominioPaginacion paginacion=new DominioPaginacion();

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}

	public String getReportecodigo() {
		return reportecodigo;
	}

	public void setReportecodigo(String reportecodigo) {
		this.reportecodigo = reportecodigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTiporeporte() {
		return tiporeporte;
	}

	public void setTiporeporte(String tiporeporte) {
		this.tiporeporte = tiporeporte;
	}
	public String getTopico() {
		return topico;
	}
	public void setTopico(String topico) {
		this.topico = topico;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
