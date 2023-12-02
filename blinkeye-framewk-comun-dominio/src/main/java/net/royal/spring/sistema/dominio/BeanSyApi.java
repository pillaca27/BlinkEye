package net.royal.spring.sistema.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

@Entity
@Table(name = "SY_API")
public class BeanSyApi extends DominioTransaccion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BeanSyApiPk pk;

	@Column(name = "HOST", nullable = true)
	private String host;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

	@Column(name = "FECHA", nullable = true)
	private java.util.Date fecha;

	public BeanSyApi() {
		pk = new BeanSyApiPk();
	}

	public BeanSyApi(BeanSyApiPk pk) {
		this.pk = pk;
	}

	public BeanSyApiPk getPk() {
		return pk;
	}

	public void setPk(BeanSyApiPk pk) {
		this.pk = pk;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public java.util.Date getFecha() {
		return fecha;
	}

	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}

}
