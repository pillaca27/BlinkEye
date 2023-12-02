package net.royal.spring.seguridad.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * Maestro usuario del sistema
 * 
 * @tabla SPRING.USUARIO
 */
@Entity
@Table(name = "USUARIO")
public class BeanUsuario  extends DominioTransaccion{
	
	@EmbeddedId
	private BeanUsuarioPk pk;

	@Size(min = 0, max = 2)
	@Column(name = "USUARIOPERFIL", length = 2, nullable = true)
	private String usuarioperfil;

	@Size(min = 0, max = 30)
	@Column(name = "NOMBRE", length = 30, nullable = true)
	private String nombre;

	@Size(min = 0, max = 100)
	@Column(name = "CLAVE", length = 100, nullable = true)
	private String clave;

	@Size(min = 0, max = 1)
	@Column(name = "EXPIRARPASSWORDFLAG", length = 1, nullable = true)
	private String expirarpasswordflag;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHAEXPIRACION", nullable = true)
	private java.util.Date fechaexpiracion;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMOLOGIN", nullable = true)
	private java.util.Date ultimologin;

	@Column(name = "NUMEROLOGINSDISPONIBLE", nullable = true)
	private Integer numerologinsdisponible;
	
	@Column(name = "NUMEROLOGINSUSADOS", nullable = true)
	private Integer numerologinsusados;

	@Size(min = 0, max = 20)
	@Column(name = "SQLLOGIN", length = 20, nullable = true)
	private String sqllogin;

	@Size(min = 0, max = 10)
	@Column(name = "SQLPASSWORD", length = 10, nullable = true)
	private String sqlpassword;

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

	@Size(min = 0, max = 20)
	@Column(name = "USUARIORED", length = 20, nullable = true)
	private String usuariored;

	/*@Size(min = 0, max = 10)
	@Column(name = "HORAINICIO", length = 10, nullable = true)
	private String horainicio;

	@Size(min = 0, max = 10)
	@Column(name = "HORAFIN", length = 10, nullable = true)
	private String horafin;

	@Size(min = 0, max = 10)
	@Column(name = "HORAINICIOAP", length = 10, nullable = true)
	private String horainicioap;

	@Size(min = 0, max = 10)
	@Column(name = "HORAFINAP", length = 10, nullable = true)
	private String horafinap;

	@Size(min = 0, max = 128)
	@Column(name = "VALUEHSH", length = 128, nullable = true)
	private String valuehsh;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHACREACION", nullable = true)
	private java.util.Date fechacreacion;

	@Size(min = 0, max = 20)
	@Column(name = "USUARIOCREACION", length = 20, nullable = true)
	private String usuariocreacion;

	@Size(min = 0, max = 1)
	@Column(name = "FORCELOGONSPRINGFLAG", length = 1, nullable = true)
	private String forcelogonspringflag;*/

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHAPASSWORD", nullable = true)
	private java.util.Date fechapassword;

	@Size(min = 0, max = 1)
	@Column(name = "SITUACION", length = 1, nullable = true)
	private String situacion;

	@Column(name = "PERSONANUMERO", nullable = true)
	private Integer personanumero;
	
	public BeanUsuario() {
		pk = new BeanUsuarioPk();
	}

	public BeanUsuario(BeanUsuarioPk pk) {
		this.pk = pk;
	}

	public BeanUsuarioPk getPk() {
		return pk;
	}

	public void setPk(BeanUsuarioPk pk) {
		this.pk = pk;
	}

	/**
	 * Perfil del usuario
	 * 
	 * @campo USUARIOPERFIL
	 */
	public String getUsuarioperfil() {
		return usuarioperfil;
	}

	/**
	 * Perfil del usuario
	 * 
	 * @campo USUARIOPERFIL
	 */
	public void setUsuarioperfil(String usuarioperfil) {
		this.usuarioperfil = usuarioperfil;
	}

	/**
	 * Nombre del Usuario
	 * 
	 * @campo NOMBRE
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Nombre del Usuario
	 * 
	 * @campo NOMBRE
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Clave del usuario
	 * 
	 * @campo CLAVE
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Clave del usuario
	 * 
	 * @campo CLAVE
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Indica si la clave expira o no
	 * 
	 * @campo EXPIRARPASSWORDFLAG
	 */
	public String getExpirarpasswordflag() {
		return expirarpasswordflag;
	}

	/**
	 * Indica si la clave expira o no
	 * 
	 * @campo EXPIRARPASSWORDFLAG
	 */
	public void setExpirarpasswordflag(String expirarpasswordflag) {
		this.expirarpasswordflag = expirarpasswordflag;
	}

	/**
	 * Fecha de expiracion de la clave
	 * 
	 * @campo FECHAEXPIRACION
	 */
	public java.util.Date getFechaexpiracion() {
		return fechaexpiracion;
	}

	/**
	 * Fecha de expiracion de la clave
	 * 
	 * @campo FECHAEXPIRACION
	 */
	public void setFechaexpiracion(java.util.Date fechaexpiracion) {
		this.fechaexpiracion = fechaexpiracion;
	}

	/**
	 * Ultimo Login
	 * 
	 * @campo ULTIMOLOGIN
	 */
	public java.util.Date getUltimologin() {
		return ultimologin;
	}

	/**
	 * Ultimo Login
	 * 
	 * @campo ULTIMOLOGIN
	 */
	public void setUltimologin(java.util.Date ultimologin) {
		this.ultimologin = ultimologin;
	}

	/**
	 * Numero de Logins Disponibles
	 * 
	 * @campo NUMEROLOGINSDISPONIBLE
	 */
	public Integer getNumerologinsdisponible() {
		return numerologinsdisponible;
	}

	/**
	 * Numero de Logins Disponibles
	 * 
	 * @campo NUMEROLOGINSDISPONIBLE
	 */
	public void setNumerologinsdisponible(Integer numerologinsdisponible) {
		this.numerologinsdisponible = numerologinsdisponible;
	}

	/**
	 * Numero de Logins Usados
	 * 
	 * @campo NUMEROLOGINSUSADOS
	 */
	public Integer getNumerologinsusados() {
		return numerologinsusados;
	}

	/**
	 * Numero de Logins Usados
	 * 
	 * @campo NUMEROLOGINSUSADOS
	 */
	public void setNumerologinsusados(Integer numerologinsusados) {
		this.numerologinsusados = numerologinsusados;
	}

	/**
	 * Login de SQL
	 * 
	 * @campo SQLLOGIN
	 */
	public String getSqllogin() {
		return sqllogin;
	}

	/**
	 * Login de SQL
	 * 
	 * @campo SQLLOGIN
	 */
	public void setSqllogin(String sqllogin) {
		this.sqllogin = sqllogin;
	}

	/**
	 * Clave de SQL
	 * 
	 * @campo SQLPASSWORD
	 */
	public String getSqlpassword() {
		return sqlpassword;
	}

	/**
	 * Clave de SQL
	 * 
	 * @campo SQLPASSWORD
	 */
	public void setSqlpassword(String sqlpassword) {
		this.sqlpassword = sqlpassword;
	}

	/**
	 * A = Activo, I = Inactivo
	 * 
	 * @campo ESTADO
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * A = Activo, I = Inactivo
	 * 
	 * @campo ESTADO
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Es el Codigo del ultimo usuario que realizo modificaciones a este registro
	 * 
	 * @campo ULTIMOUSUARIO
	 */
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * Es el Codigo del ultimo usuario que realizo modificaciones a este registro
	 * 
	 * @campo ULTIMOUSUARIO
	 */
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}

	/**
	 * Reservado
	 * 
	 * @campo ULTIMAFECHAMODIF
	 */
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * Reservado
	 * 
	 * @campo ULTIMAFECHAMODIF
	 */
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}

	/**
	 * Reservado
	 * 
	 * @campo USUARIORED
	 */
	public String getUsuariored() {
		return usuariored;
	}

	/**
	 * Reservado
	 * 
	 * @campo USUARIORED
	 */
	public void setUsuariored(String usuariored) {
		this.usuariored = usuariored;
	}

	/**
	 * Fecha del paasword
	 * 
	 * @campo FECHAPASSWORD
	 */
	public java.util.Date getFechapassword() {
		return fechapassword;
	}

	/**
	 * Fecha del paasword
	 * 
	 * @campo FECHAPASSWORD
	 */
	public void setFechapassword(java.util.Date fechapassword) {
		this.fechapassword = fechapassword;
	}

	/**
	 * Situacion del empleado
	 * 
	 * @campo SITUACION
	 */
	public String getSituacion() {
		return situacion;
	}

	/**
	 * Situacion del empleado
	 * 
	 * @campo SITUACION
	 */
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public Integer getPersonanumero() {
		return personanumero;
	}

	public void setPersonanumero(Integer personanumero) {
		this.personanumero = personanumero;
	}
	
	
}
