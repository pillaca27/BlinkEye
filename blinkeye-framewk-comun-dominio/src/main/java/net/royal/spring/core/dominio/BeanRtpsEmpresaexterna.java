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
 * @tabla dbo.RTPS_EmpresaExterna
*/
@Entity
@Table(name = "RTPS_EMPRESAEXTERNA")
public class BeanRtpsEmpresaexterna extends DominioTransaccion implements java.io.Serializable{
	
	@EmbeddedId
	private BeanRtpsEmpresaexternaPk pk;


	@Size(min = 0, max = 12)
	@Column(name = "RUCEMPRESA", length = 12, nullable = true)
	private String rucempresa;

	@Size(min = 0, max = 100)
	@Column(name = "RAZONSOCIAL", length = 100, nullable = true)
	private String razonsocial;

	@Size(min = 0, max = 6)
	@Column(name = "CODIGOACTIVIDAD", length = 6, nullable = true)
	private String codigoactividad;

	@Size(min = 0, max = 1)
	@Column(name = "TIPOEMPRESA", length = 1, nullable = true)
	private String tipoempresa;

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

	public BeanRtpsEmpresaexterna() {
		pk = new BeanRtpsEmpresaexternaPk();
	}


	public BeanRtpsEmpresaexterna(BeanRtpsEmpresaexternaPk pk) {
		this.pk = pk;
	}

	public BeanRtpsEmpresaexternaPk getPk() {
		return pk;
	}

	public void setPk(BeanRtpsEmpresaexternaPk pk) {
		this.pk = pk;
	}

	/**
	 * 
	 * 
	 * @campo RUCEmpresa
	*/
	public String getRucempresa() {
		return rucempresa;
	}

	/**
	 * 
	 * 
	 * @campo RUCEmpresa
	*/
	public void setRucempresa(String rucempresa) {
		this.rucempresa = rucempresa;
	}
	/**
	 * 
	 * 
	 * @campo RazonSocial
	*/
	public String getRazonsocial() {
		return razonsocial;
	}

	/**
	 * 
	 * 
	 * @campo RazonSocial
	*/
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}
	/**
	 * 
	 * 
	 * @campo CodigoActividad
	*/
	public String getCodigoactividad() {
		return codigoactividad;
	}

	/**
	 * 
	 * 
	 * @campo CodigoActividad
	*/
	public void setCodigoactividad(String codigoactividad) {
		this.codigoactividad = codigoactividad;
	}
	/**
	 * 
	 * 
	 * @campo TipoEmpresa
	*/
	public String getTipoempresa() {
		return tipoempresa;
	}

	/**
	 * 
	 * 
	 * @campo TipoEmpresa
	*/
	public void setTipoempresa(String tipoempresa) {
		this.tipoempresa = tipoempresa;
	}
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
