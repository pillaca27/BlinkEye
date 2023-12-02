package net.royal.spring.workflow.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DtoReemplazo {
	private Integer secuencia;
	private String antiguoNombre;
	private String nuevoNombre;
	private Date desde;
	private Date hasta;
	private String observacion;
	private String estado;
	private BigDecimal ROWNUM_;
	private String tipo;

	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

	public String getAntiguoNombre() {
		return antiguoNombre;
	}

	public void setAntiguoNombre(String antiguoNombre) {
		this.antiguoNombre = antiguoNombre;
	}

	public String getNuevoNombre() {
		return nuevoNombre;
	}

	public void setNuevoNombre(String nuevoNombre) {
		this.nuevoNombre = nuevoNombre;
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

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
