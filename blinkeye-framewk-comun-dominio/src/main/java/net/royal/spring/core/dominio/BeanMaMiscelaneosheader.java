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
 * Maestro miscelaneos header
 * 
 * @tabla MA_MISCELANEOSHEADER
*/
@Entity
@Table(name = "MA_MISCELANEOSHEADER")
public class BeanMaMiscelaneosheader extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanMaMiscelaneosheaderPk pk;

	@Size(min = 0, max = 40)
	@Column(name = "DESCRIPCIONLOCAL", length = 40, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 40)
	@Column(name = "DESCRIPCIONEXTRANJERA", length = 40, nullable = true)
	private String descripcionextranjera;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;
	
	@Size(min = 0, max = 36)
	@Column(name = "UUID", length = 36, nullable = true)
	private String uuid;


	public BeanMaMiscelaneosheader() {
		pk = new BeanMaMiscelaneosheaderPk();
	}


	public BeanMaMiscelaneosheader(BeanMaMiscelaneosheaderPk pk) {
		this.pk = pk;
	}

	public BeanMaMiscelaneosheaderPk getPk() {
		return pk;
	}

	public void setPk(BeanMaMiscelaneosheaderPk pk) {
		this.pk = pk;
	}
	/**
	 * Descripcion Local
	 * 
	 * @campo DESCRIPCIONLOCAL
	*/
	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	/**
	 * Descripcion Local
	 * 
	 * @campo DESCRIPCIONLOCAL
	*/
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	/**
	 * Descripcion Extranjera
	 * 
	 * @campo DESCRIPCIONEXTRANJERA
	*/
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}

	/**
	 * Descripcion Extranjera
	 * 
	 * @campo DESCRIPCIONEXTRANJERA
	*/
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	/**
	 * A=Activo, I=Inactivo
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * A=Activo, I=Inactivo
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * Ultimo Usuario de Modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * Ultimo Usuario de Modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * Ultima Fecha de Modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * Ultima Fecha de Modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
