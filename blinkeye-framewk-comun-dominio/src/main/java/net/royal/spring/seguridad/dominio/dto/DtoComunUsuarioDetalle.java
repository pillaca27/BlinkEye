package net.royal.spring.seguridad.dominio.dto;

import java.math.BigDecimal;

public class DtoComunUsuarioDetalle {

	private String codigo;
	private String descripcion;
	private Integer asignado;

	private String ungcodigo;
	private BigDecimal modcodigo;

	public String getUngcodigo() {
		return ungcodigo;
	}

	public void setUngcodigo(String ungcodigo) {
		this.ungcodigo = ungcodigo;
	}

	public BigDecimal getModcodigo() {
		return modcodigo;
	}

	public void setModcodigo(BigDecimal modcodigo) {
		this.modcodigo = modcodigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getAsignado() {
		return asignado;
	}

	public void setAsignado(Integer asignado) {
		this.asignado = asignado;
	}

}
