package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunPlancontable extends DominioTransaccion{
	private String plancontable;
	private String descripcionlocal;
	private java.math.BigDecimal digitaccount;
	private java.math.BigDecimal digitprime;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private BigDecimal ROWNUM_;

	public String getPlancontable() {
		return plancontable;
	}

	public void setPlancontable(String plancontable) {
		this.plancontable = plancontable;
	}

	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}

	public java.math.BigDecimal getDigitaccount() {
		return digitaccount;
	}

	public void setDigitaccount(java.math.BigDecimal digitaccount) {
		this.digitaccount = digitaccount;
	}

	public java.math.BigDecimal getDigitprime() {
		return digitprime;
	}

	public void setDigitprime(java.math.BigDecimal digitprime) {
		this.digitprime = digitprime;
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
