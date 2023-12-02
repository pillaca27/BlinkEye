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
 * 
 * 
 * @tabla dbo.RTPS_EmpleadoEstablecimientoEx
*/
@Entity
@Table(name = "RTPS_EmpleadoEstablecimientoEx")
public class BeanRtpsEmpleadoEstablecimientoExterno extends DominioTransaccion implements java.io.Serializable{
	@EmbeddedId
	private BeanRtpsEmpleadoEstablecimientoExternoPk pk;

//	@Column(name = "empleado", length = 4, nullable = true)
//	private Integer empleado;
//
//	@Size(min = 0, max = 8)
//	@Column(name = "companiasocio", length = 8, nullable = true)
//	private String companiasocio;

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

	public BeanRtpsEmpleadoEstablecimientoExterno() {
		pk = new BeanRtpsEmpleadoEstablecimientoExternoPk();
	}


	public BeanRtpsEmpleadoEstablecimientoExterno(BeanRtpsEmpleadoEstablecimientoExternoPk pk) {
		this.pk = pk;
	}

	public BeanRtpsEmpleadoEstablecimientoExternoPk getPk() {
		return pk;
	}

	public void setPk(BeanRtpsEmpleadoEstablecimientoExternoPk pk) {
		this.pk = pk;
	}

//	/**
//	 * 
//	 * 
//	 * @campo Empleado
//	*/
//	public Integer getEmpleado() {
//		return empleado;
//	}
//
//	/**
//	 * 
//	 * 
//	 * @campo Empleado
//	*/
//	public void setEmpleado(Integer empleado) {
//		this.empleado = empleado;
//	}
//	/**
//	 * 
//	 * 
//	 * @campo CompaniaSocio
//	*/
//	public String getCompaniasocio() {
//		return companiasocio;
//	}
//
//	/**
//	 * 
//	 * 
//	 * @campo CompaniaSocio
//	*/
//	public void setCompaniasocio(String companiasocio) {
//		this.companiasocio = companiasocio;
//	}
	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}


}
