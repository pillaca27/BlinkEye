package net.royal.spring.proveedores.dominio;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/** 
 * 
 * 
*/
@Entity
@Table(name = "GP_RPACTIVIDADECONOMICA")
public class BeanGpRpActividadEconomica extends DominioTransaccion implements java.io.Serializable{
	
	@Id
	@Column(name = "SECUENCIAID")
	private Integer secuenciaid;
		
	@Column(name = "REGREGISTROID")
	private Integer regregistroid;
	
	@Size(min = 0, max = 4)
	@Column(name = "TIPOACTIVIDAD", length = 4, nullable = true)
	private String tipoActividad;
	
	@Size(min = 0, max = 200)
	@Column(name = "DESCRIPCION", length = 200, nullable = true)
	private String descripcion;
	
	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;
	
	@Size(min = 0, max = 20)
	@Column(name = "TIPOCATEGORIA", length = 20, nullable = true)
	private String tipocategoria;
	

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	
	
	public String getTipocategoria() {
		return tipocategoria;
	}

	public void setTipocategoria(String tipocategoria) {
		this.tipocategoria = tipocategoria;
	}

	public Integer getSecuenciaid() {
		return secuenciaid;
	}

	public void setSecuenciaid(Integer secuenciaid) {
		this.secuenciaid = secuenciaid;
	}

	public Integer getRegregistroid() {
		return regregistroid;
	}

	public void setRegregistroid(Integer regregistroid) {
		this.regregistroid = regregistroid;
	}

	public String getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}

	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}

	
	
	
}
