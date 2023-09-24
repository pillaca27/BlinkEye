package net.royal.spring.rrhh.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Hr_EmpleadoCapacitacion")
public class BeanHrEmpleadoCapacitacion implements Serializable {

	@EmbeddedId
	private BeanHrEmpleadoCapacitacionPk pk;
	
	@Column(name = "UltimoUsuario")
	private String ultimoUsuario;
	
	@Column(name = "UltimaFechaModif")
	private Date ultimaFechaModif;

	public BeanHrEmpleadoCapacitacion() {
		this.pk = new BeanHrEmpleadoCapacitacionPk();
	}

	public String getUltimoUsuario() {
		return ultimoUsuario;
	}

	public void setUltimoUsuario(String ultimoUsuario) {
		this.ultimoUsuario = ultimoUsuario;
	}

	public Date getUltimaFechaModif() {
		return ultimaFechaModif;
	}

	public void setUltimaFechaModif(Date ultimaFechaModif) {
		this.ultimaFechaModif = ultimaFechaModif;
	}

	public BeanHrEmpleadoCapacitacionPk getPk() {
		return pk;
	}

	public void setPk(BeanHrEmpleadoCapacitacionPk pk) {
		this.pk = pk;
	}

}
