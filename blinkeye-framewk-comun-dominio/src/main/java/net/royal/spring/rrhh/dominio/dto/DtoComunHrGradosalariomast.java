package net.royal.spring.rrhh.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunHrGradosalariomast extends DominioTransaccion{
	private String gradosalario;
	private String descripcionlocal;
	private String descripcionextranjera;
	private BigDecimal salariominimo;
	private BigDecimal salariomaximo;
	private BigDecimal salariopromedio;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String cargo;
	private String cargonombre;
	
	public String getGradosalario() {
		return gradosalario;
	}
	public void setGradosalario(String gradosalario) {
		this.gradosalario = gradosalario;
	}
	public String getDescripcionlocal() {
		return descripcionlocal;
	}
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	public BigDecimal getSalariominimo() {
		return salariominimo;
	}
	public void setSalariominimo(BigDecimal salariominimo) {
		this.salariominimo = salariominimo;
	}
	public BigDecimal getSalariomaximo() {
		return salariomaximo;
	}
	public void setSalariomaximo(BigDecimal salariomaximo) {
		this.salariomaximo = salariomaximo;
	}
	public BigDecimal getSalariopromedio() {
		return salariopromedio;
	}
	public void setSalariopromedio(BigDecimal salariopromedio) {
		this.salariopromedio = salariopromedio;
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
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCargonombre() {
		return cargonombre;
	}
	public void setCargonombre(String cargonombre) {
		this.cargonombre = cargonombre;
	}
	
}
