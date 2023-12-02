package net.royal.spring.core.dominio.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DtoComunTablaBigDecimal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal codigo;
	private String nombre;
	private String descripcion;
	private String estado;

	public DtoComunTablaBigDecimal() {
	}

	public DtoComunTablaBigDecimal(BigDecimal codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public BigDecimal getCodigo() {
		return codigo;
	}

	public void setCodigo(BigDecimal codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
