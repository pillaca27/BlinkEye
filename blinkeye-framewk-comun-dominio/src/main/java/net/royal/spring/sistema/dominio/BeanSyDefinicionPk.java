package net.royal.spring.sistema.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class BeanSyDefinicionPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "ID_API", nullable = false)
	private Integer idapi;

	@NotNull
	@Column(name = "ID_DEFINICION", nullable = false)
	private Integer iddefinicion;

	public BeanSyDefinicionPk(Integer idapi, Integer iddefinicion) {
		this.idapi = idapi;
		this.iddefinicion = iddefinicion;
	}

	public BeanSyDefinicionPk() {
	}

	public Integer getIdapi() {
		return idapi;
	}

	public void setIdapi(Integer idapi) {
		this.idapi = idapi;
	}

	public Integer getIddefinicion() {
		return iddefinicion;
	}

	public void setIddefinicion(Integer iddefinicion) {
		this.iddefinicion = iddefinicion;
	}

}
