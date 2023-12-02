package net.royal.spring.kpi.dominio;

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
 * @tabla dbo.RT_FormatoGrupo
*/
@Entity
@Table(name = "RT_FORMATOGRUPO")
public class BeanRtFormatogrupo extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanRtFormatogrupoPk pk;

	@Size(min = 0, max = 50)
	@NotNull
	@NotEmpty
	@Column(name = "GRPDESCRIPCION", length = 50, nullable = false)
	private String grpdescripcion;

	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "GRPUSUARIO", length = 20, nullable = false)
	private String grpusuario;

	@NotNull
	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "GRPULTIMAMODIFICACION", nullable = false)
	private java.util.Date grpultimamodificacion;

	@Column(name = "GRPORDEN", nullable = true)
	private Integer grporden;

	@Size(min = 0, max = 1)
	@Column(name = "FLAGDASHBOARD", length = 1, nullable = true)
	private String flagdashboard;


	public BeanRtFormatogrupo() {
		pk = new BeanRtFormatogrupoPk();
	}


	public BeanRtFormatogrupo(BeanRtFormatogrupoPk pk) {
		this.pk = pk;
	}

	public BeanRtFormatogrupoPk getPk() {
		return pk;
	}

	public void setPk(BeanRtFormatogrupoPk pk) {
		this.pk = pk;
	}
	/**
	 * 
	 * 
	 * @campo GrpDescripcion
	*/
	public String getGrpdescripcion() {
		return grpdescripcion;
	}

	/**
	 * 
	 * 
	 * @campo GrpDescripcion
	*/
	public void setGrpdescripcion(String grpdescripcion) {
		this.grpdescripcion = grpdescripcion;
	}
	/**
	 * 
	 * 
	 * @campo GrpUsuario
	*/
	public String getGrpusuario() {
		return grpusuario;
	}

	/**
	 * 
	 * 
	 * @campo GrpUsuario
	*/
	public void setGrpusuario(String grpusuario) {
		this.grpusuario = grpusuario;
	}
	/**
	 * 
	 * 
	 * @campo GrpUltimaModificacion
	*/
	public java.util.Date getGrpultimamodificacion() {
		return grpultimamodificacion;
	}

	/**
	 * 
	 * 
	 * @campo GrpUltimaModificacion
	*/
	public void setGrpultimamodificacion(java.util.Date grpultimamodificacion) {
		this.grpultimamodificacion = grpultimamodificacion;
	}
	/**
	 * 
	 * 
	 * @campo GrpOrden
	*/
	public Integer getGrporden() {
		return grporden;
	}

	/**
	 * 
	 * 
	 * @campo GrpOrden
	*/
	public void setGrporden(Integer grporden) {
		this.grporden = grporden;
	}
	/**
	 * 
	 * 
	 * @campo FlagDashboard
	*/
	public String getFlagdashboard() {
		return flagdashboard;
	}

	/**
	 * 
	 * 
	 * @campo FlagDashboard
	*/
	public void setFlagdashboard(String flagdashboard) {
		this.flagdashboard = flagdashboard;
	}

}
