package net.royal.spring.seguridad.dominio;

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

import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * Maestro usuario del sistema
 * 
 * @tabla SGAGROSYS.USUARIO
 */
@Entity
@Table(name = "USUARIO")
public class Usuario implements java.io.Serializable {

	@EmbeddedId
	private UsuarioPk pk;

	@Column(name = "USUARIOPERFIL", nullable = true)
	private String usuarioperfil;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

	@Column(name = "CLAVE", nullable = true)
	private String clave;

	@Size(min = 0, max = 1)
	@Column(name = "EXPIRARPASSWORDFLAG", length = 1, nullable = true)
	private String expirarpasswordflag;

	@Column(name = "FECHAEXPIRACION", nullable = true)
	private java.util.Date fechaexpiracion;

	@Column(name = "ULTIMOLOGIN", nullable = true)
	private java.util.Date ultimologin;

	@Column(name = "NUMEROLOGINSDISPONIBLE", nullable = true)
	private Integer numerologinsdisponible;

	@Column(name = "NUMEROLOGINSUSADOS", nullable = true)
	private Integer numerologinsusados;

	@Column(name = "SQLLOGIN", nullable = true)
	private String sqllogin;

	@Column(name = "SQLPASSWORD", nullable = true)
	private String sqlpassword;

	@Column(name = "ESTADO", nullable = true)
	private String estado;
	
	@Column(name = "USUARIORED", nullable = true)
	private String usuariored;

	@Column(name = "ULTIMOUSUARIO", nullable = true)
	private String ultimousuario;

	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Transient
	private Boolean auxFlgPreparado = Boolean.FALSE;

	public Usuario() {
		pk = new UsuarioPk();
	}

	public Usuario(UsuarioPk pk) {
		this.pk = pk;
	}

	public UsuarioPk getPk() {
		return pk;
	}

	public void setPk(UsuarioPk pk) {
		this.pk = pk;
	}

	public String getUsuarioperfil() {
		return usuarioperfil;
	}

	public void setUsuarioperfil(String usuarioperfil) {
		this.usuarioperfil = usuarioperfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getExpirarpasswordflag() {
		return expirarpasswordflag;
	}

	public void setExpirarpasswordflag(String expirarpasswordflag) {
		this.expirarpasswordflag = expirarpasswordflag;
	}

	public java.util.Date getFechaexpiracion() {
		return fechaexpiracion;
	}

	public void setFechaexpiracion(java.util.Date fechaexpiracion) {
		this.fechaexpiracion = fechaexpiracion;
	}

	public java.util.Date getUltimologin() {
		return ultimologin;
	}

	public void setUltimologin(java.util.Date ultimologin) {
		this.ultimologin = ultimologin;
	}

	public Integer getNumerologinsdisponible() {
		return numerologinsdisponible;
	}

	public void setNumerologinsdisponible(Integer numerologinsdisponible) {
		this.numerologinsdisponible = numerologinsdisponible;
	}

	public Integer getNumerologinsusados() {
		return numerologinsusados;
	}

	public void setNumerologinsusados(Integer numerologinsusados) {
		this.numerologinsusados = numerologinsusados;
	}

	public String getSqllogin() {
		return sqllogin;
	}

	public void setSqllogin(String sqllogin) {
		this.sqllogin = sqllogin;
	}

	public String getSqlpassword() {
		return sqlpassword;
	}

	public void setSqlpassword(String sqlpassword) {
		this.sqlpassword = sqlpassword;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public Boolean getAuxFlgPreparado() {
		return auxFlgPreparado;
	}

	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}

	public String getUsuariored() {
		return usuariored;
	}

	public void setUsuariored(String usuariored) {
		this.usuariored = usuariored;
	}
}
