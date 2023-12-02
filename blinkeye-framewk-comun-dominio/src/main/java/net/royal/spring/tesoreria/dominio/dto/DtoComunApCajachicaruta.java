package net.royal.spring.tesoreria.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunApCajachicaruta extends DominioTransaccion{
	private BigDecimal ruta;
	private String rutainicio;
	private String rutafinal;
	private String descripcion;
	private BigDecimal preciominimo;
	private BigDecimal preciomaximo;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	@JsonIgnore
	private BigDecimal ROWNUM_;

	public BigDecimal getRuta() {
		return ruta;
	}

	public void setRuta(BigDecimal ruta) {
		this.ruta = ruta;
	}

	public String getRutainicio() {
		return rutainicio;
	}

	public void setRutainicio(String rutainicio) {
		this.rutainicio = rutainicio;
	}

	public String getRutafinal() {
		return rutafinal;
	}

	public void setRutafinal(String rutafinal) {
		this.rutafinal = rutafinal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPreciominimo() {
		return preciominimo;
	}

	public void setPreciominimo(BigDecimal preciominimo) {
		this.preciominimo = preciominimo;
	}

	public BigDecimal getPreciomaximo() {
		return preciomaximo;
	}

	public void setPreciomaximo(BigDecimal preciomaximo) {
		this.preciomaximo = preciomaximo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}

	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
	
}
