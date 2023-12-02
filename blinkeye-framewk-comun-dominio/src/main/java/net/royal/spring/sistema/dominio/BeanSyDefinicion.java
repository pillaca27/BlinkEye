package net.royal.spring.sistema.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

@Entity
@Table(name = "SY_DEFINICION")
public class BeanSyDefinicion extends DominioTransaccion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BeanSyDefinicionPk pk;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

	public BeanSyDefinicion() {
		pk = new BeanSyDefinicionPk();
	}

	public BeanSyDefinicion(BeanSyDefinicionPk pk) {
		this.pk = pk;
	}

	public BeanSyDefinicionPk getPk() {
		return pk;
	}

	public void setPk(BeanSyDefinicionPk pk) {
		this.pk = pk;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
