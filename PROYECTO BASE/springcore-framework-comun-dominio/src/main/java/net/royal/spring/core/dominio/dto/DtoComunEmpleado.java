package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DtoComunEmpleado {

	private BigDecimal empleado;
	private String companiasocio;
	private String nombrecompleto;
	private String centrocostosdescripcion;
	private String correointerno;
	private Date fechanacimiento;
	private Date fechaaniversario;
	private BigDecimal ROWNUM_;

	public BigDecimal getEmpleado() {
		return empleado;
	}

	public void setEmpleado(BigDecimal empleado) {
		this.empleado = empleado;
	}

	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public String getNombrecompleto() {
		return nombrecompleto;
	}

	public void setNombrecompleto(String nombrecompleto) {
		this.nombrecompleto = nombrecompleto;
	}

	public String getCentrocostosdescripcion() {
		return centrocostosdescripcion;
	}

	public void setCentrocostosdescripcion(String centrocostosdescripcion) {
		this.centrocostosdescripcion = centrocostosdescripcion;
	}

	public String getCorreointerno() {
		return correointerno;
	}

	public void setCorreointerno(String correointerno) {
		this.correointerno = correointerno;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public Date getFechaaniversario() {
		return fechaaniversario;
	}

	public void setFechaaniversario(Date fechaaniversario) {
		this.fechaaniversario = fechaaniversario;
	}

	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

}
