package net.royal.spring.sistema.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class BeanSyDefinicionpropiedadPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "ID_API", nullable = false)
	private Integer idapi;

	@NotNull
	@Column(name = "ID_DEFINICION", nullable = false)
	private Integer iddefinicion;

	@NotNull
	@Column(name = "ID_PROPIEDAD", nullable = false)
	private Integer idpropiedad;

	public BeanSyDefinicionpropiedadPk() {
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

	public Integer getIdpropiedad() {
		return idpropiedad;
	}

	public void setIdpropiedad(Integer idpropiedad) {
		this.idpropiedad = idpropiedad;
	}

}
