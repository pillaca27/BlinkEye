package net.royal.spring.core.dominio;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * Maestro de zona de despacho
 * 
 * @tabla SPRING.ZONADESPACHO
*/
@Entity
@Table(name = "ZONADESPACHO")
public class BeanZonadespacho extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanZonadespachoPk pk;

	@Column(name = "TRANSPORTISTA", precision = 0,scale =0, nullable = true)
	private java.math.BigDecimal transportista;

	@Size(min = 0, max = 100)
	@Column(name = "DESCRIPCION", length = 100, nullable = true)
	private String descripcion;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;


	public BeanZonadespacho() {
		pk = new BeanZonadespachoPk();
	}


	public BeanZonadespacho(BeanZonadespachoPk pk) {
		this.pk = pk;
	}

	public BeanZonadespachoPk getPk() {
		return pk;
	}

	public void setPk(BeanZonadespachoPk pk) {
		this.pk = pk;
	}
	/**
	 * codigo transportista
	 * 
	 * @campo TRANSPORTISTA
	*/
	public java.math.BigDecimal getTransportista() {
		return transportista;
	}

	/**
	 * codigo transportista
	 * 
	 * @campo TRANSPORTISTA
	*/
	public void setTransportista(java.math.BigDecimal transportista) {
		this.transportista = transportista;
	}
	/**
	 * descriptcion de transportista
	 * 
	 * @campo DESCRIPCION
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * descriptcion de transportista
	 * 
	 * @campo DESCRIPCION
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * reservado
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * reservado
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * ultima fecha de modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * ultima fecha de modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * ultimo usuario de modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * ultimo usuario de modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}

}
