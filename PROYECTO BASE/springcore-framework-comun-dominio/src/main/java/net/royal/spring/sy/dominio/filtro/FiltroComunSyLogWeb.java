package net.royal.spring.sy.dominio.filtro;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunSyLogWeb {

	private Date fecha;
	private Date desde;
	private Date hasta;
	private String estado;
	private String tipo;
	private String aplicacion;
	private DominioPaginacion paginacion;

	public FiltroComunSyLogWeb() {
		this.paginacion = new DominioPaginacion();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

}
