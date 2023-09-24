package net.royal.spring.hr.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunOrganigramaPuestos {

	private String companiaSocio;
	private String unidadNegocio;
	private Integer unidadOriginal;
	private Integer puestoOriginal;
	private Integer anio;
	private Integer secuencia;
	private String evento;
	private Integer numero;
	private Integer codigo;
	private String nombre;
	private DominioPaginacion paginacion;

	public FiltroComunOrganigramaPuestos() {
		this.paginacion = new DominioPaginacion();
	}

	public String getCompaniaSocio() {
		return companiaSocio;
	}

	public void setCompaniaSocio(String companiaSocio) {
		this.companiaSocio = companiaSocio;
	}

	public String getUnidadNegocio() {
		return unidadNegocio;
	}

	public void setUnidadNegocio(String unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}

	public Integer getUnidadOriginal() {
		return unidadOriginal;
	}

	public void setUnidadOriginal(Integer unidadOriginal) {
		this.unidadOriginal = unidadOriginal;
	}

	public Integer getPuestoOriginal() {
		return puestoOriginal;
	}

	public void setPuestoOriginal(Integer puestoOriginal) {
		this.puestoOriginal = puestoOriginal;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
