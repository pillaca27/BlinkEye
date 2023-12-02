package net.royal.spring.sistema.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class BeanSyApipathPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "ID_PATH", nullable = false)
	private Integer idpath;

	@NotNull
	@Column(name = "ID_API", nullable = false)
	private Integer idapi;

	public BeanSyApipathPk() {
	}

	public BeanSyApipathPk(Integer idpath, Integer idapi) {
		this.idpath = idpath;
		this.idapi = idapi;
	}

	public Integer getIdpath() {
		return idpath;
	}

	public void setIdpath(Integer idpath) {
		this.idpath = idpath;
	}

	public Integer getIdapi() {
		return idapi;
	}

	public void setIdapi(Integer idapi) {
		this.idapi = idapi;
	}

}
