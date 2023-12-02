package net.royal.spring.sistema.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class BeanSyApiPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "ID_API", nullable = false)
	private Integer idapi;

	public BeanSyApiPk() {
	}

	public BeanSyApiPk(Integer idapi) {
		this.idapi = idapi;
	}

	public Integer getIdapi() {
		return idapi;
	}

	public void setIdapi(Integer idapi) {
		this.idapi = idapi;
	}

}
