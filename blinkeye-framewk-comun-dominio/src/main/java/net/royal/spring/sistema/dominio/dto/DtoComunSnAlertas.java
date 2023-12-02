package net.royal.spring.sistema.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

public class DtoComunSnAlertas {
	private Integer alertaid;
	private Integer personaId;
	private String link;
	private String estado;
	private String nombre;
	private String accion;
	
	
	private String codigoProcesoWf;
	private String accionWf;
	private String CodigoExternoId;
	private Integer TransaccionWfId;
	
	private Date fecha;
	
	private BigDecimal ROWNUM_;
	
	

	public Integer getAlertaid() {
		return alertaid;
	}

	public void setAlertaid(Integer alertaid) {
		this.alertaid = alertaid;
	}

	public Integer getPersonaId() {
		return personaId;
	}

	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getCodigoProcesoWf() {
		return codigoProcesoWf;
	}

	public void setCodigoProcesoWf(String codigoProcesoWf) {
		this.codigoProcesoWf = codigoProcesoWf;
	}

	public String getAccionWf() {
		return accionWf;
	}

	public void setAccionWf(String accionWf) {
		this.accionWf = accionWf;
	}

	public String getCodigoExternoId() {
		return CodigoExternoId;
	}

	public void setCodigoExternoId(String codigoExternoId) {
		CodigoExternoId = codigoExternoId;
	}

	public Integer getTransaccionWfId() {
		return TransaccionWfId;
	}

	public void setTransaccionWfId(Integer transaccionWfId) {
		TransaccionWfId = transaccionWfId;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
