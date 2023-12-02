package net.royal.spring.sistema.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

@Entity
@Table(name = "SY_DEFINICIONPROPIEDAD")
public class BeanSyDefinicionpropiedad extends DominioTransaccion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BeanSyDefinicionpropiedadPk pk;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

	@Column(name = "TIPODATO", nullable = true)
	private String tipodato;

	@Column(name = "DEFINICION_PADRE", nullable = false)
	private Integer definicionpadre;

	public BeanSyDefinicionpropiedad() {
		pk = new BeanSyDefinicionpropiedadPk();
	}

	public BeanSyDefinicionpropiedad(BeanSyDefinicionpropiedadPk pk) {
		this.pk = pk;
	}

	public BeanSyDefinicionpropiedadPk getPk() {
		return pk;
	}

	public void setPk(BeanSyDefinicionpropiedadPk pk) {
		this.pk = pk;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipodato() {
		return tipodato;
	}

	public void setTipodato(String tipodato) {
		this.tipodato = tipodato;
	}

	public Integer getDefinicionpadre() {
		return definicionpadre;
	}

	public void setDefinicionpadre(Integer definicionpadre) {
		this.definicionpadre = definicionpadre;
	}

}
