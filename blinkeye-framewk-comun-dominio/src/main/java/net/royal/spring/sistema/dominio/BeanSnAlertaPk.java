package net.royal.spring.sistema.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class BeanSnAlertaPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "ALERTAID", nullable = false)
	private Integer alertaId;

	public BeanSnAlertaPk() {
	}

	public Integer getAlertaId() {
		return alertaId;
	}

	public void setAlertaId(Integer alertaId) {
		this.alertaId = alertaId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
