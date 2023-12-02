package net.royal.spring.contabilidad.dominio;

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
 * @tabla dbo.AC_Sucursal
*/
@Entity
@Table(name = "AC_SUCURSAL")
public class BeanAcSucursal extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanAcSucursalPk pk;

	@Size(min = 0, max = 4)
	@Column(name = "SUCURSALGRUPO", length = 4, nullable = true)
	private String sucursalgrupo;

	@Size(min = 0, max = 500)
	@Column(name = "DESCRIPCIONLOCAL", length = 500, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 30)
	@Column(name = "DESCRIPCIONINGLES", length = 30, nullable = true)
	private String descripcioningles;

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
	@Column(name = "CUENTACONTABLEDEFECTO", length = 20, nullable = true)
	private String cuentacontabledefecto;

	@Size(min = 0, max = 10)
	@Column(name = "ALMACENCODIGO", length = 10, nullable = true)
	private String almacencodigo;

	@Size(min = 0, max = 4)
	@Column(name = "RACIONPERFIL", length = 4, nullable = true)
	private String racionperfil;

	@Size(min = 0, max = 3)
	@Column(name = "DEPARTAMENTO", length = 3, nullable = true)
	private String departamento;

	@Size(min = 0, max = 3)
	@Column(name = "PROVINCIA", length = 3, nullable = true)
	private String provincia;

	@Size(min = 0, max = 3)
	@Column(name = "CODIGOPOSTAL", length = 3, nullable = true)
	private String codigopostal;

	@Size(min = 0, max = 4)
	@Column(name = "PAIS", length = 4, nullable = true)
	private String pais;

	@Size(min = 0, max = 5000)
	@Column(name = "DIRECCION", length = 5000, nullable = true)
	private String direccion;

	@Column(name = "RESPONSABLE", nullable = true)
	private Integer responsable;

	@Size(min = 0, max = 36)
	@Column(name = "UUID", length = 36, nullable = true)
	private String uuid;	

	
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public BeanAcSucursal() {
		pk = new BeanAcSucursalPk();
	}


	public BeanAcSucursal(BeanAcSucursalPk pk) {
		this.pk = pk;
	}

	public BeanAcSucursalPk getPk() {
		return pk;
	}

	public void setPk(BeanAcSucursalPk pk) {
		this.pk = pk;
	}
	/**
	 * 
	 * 
	 * @campo SucursalGrupo
	*/
	public String getSucursalgrupo() {
		return sucursalgrupo;
	}

	/**
	 * 
	 * 
	 * @campo SucursalGrupo
	*/
	public void setSucursalgrupo(String sucursalgrupo) {
		this.sucursalgrupo = sucursalgrupo;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionLocal
	*/
	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionLocal
	*/
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionIngles
	*/
	public String getDescripcioningles() {
		return descripcioningles;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionIngles
	*/
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
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
	/**
	 * 
	 * 
	 * @campo CuentaContableDefecto
	*/
	public String getCuentacontabledefecto() {
		return cuentacontabledefecto;
	}

	/**
	 * 
	 * 
	 * @campo CuentaContableDefecto
	*/
	public void setCuentacontabledefecto(String cuentacontabledefecto) {
		this.cuentacontabledefecto = cuentacontabledefecto;
	}
	/**
	 * 
	 * 
	 * @campo AlmacenCodigo
	*/
	public String getAlmacencodigo() {
		return almacencodigo;
	}

	/**
	 * 
	 * 
	 * @campo AlmacenCodigo
	*/
	public void setAlmacencodigo(String almacencodigo) {
		this.almacencodigo = almacencodigo;
	}
	/**
	 * 
	 * 
	 * @campo RacionPerfil
	*/
	public String getRacionperfil() {
		return racionperfil;
	}

	/**
	 * 
	 * 
	 * @campo RacionPerfil
	*/
	public void setRacionperfil(String racionperfil) {
		this.racionperfil = racionperfil;
	}
	/**
	 * 
	 * 
	 * @campo departamento
	*/
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * 
	 * 
	 * @campo departamento
	*/
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	/**
	 * 
	 * 
	 * @campo provincia
	*/
	public String getProvincia() {
		return provincia;
	}

	/**
	 * 
	 * 
	 * @campo provincia
	*/
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**
	 * 
	 * 
	 * @campo codigopostal
	*/
	public String getCodigopostal() {
		return codigopostal;
	}

	/**
	 * 
	 * 
	 * @campo codigopostal
	*/
	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}
	/**
	 * 
	 * 
	 * @campo pais
	*/
	public String getPais() {
		return pais;
	}

	/**
	 * 
	 * 
	 * @campo pais
	*/
	public void setPais(String pais) {
		this.pais = pais;
	}
	/**
	 * 
	 * 
	 * @campo DIRECCION
	*/
	public String getDireccion() {
		return direccion;
	}

	/**
	 * 
	 * 
	 * @campo DIRECCION
	*/
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * 
	 * 
	 * @campo RESPONSABLE
	*/
	public Integer getResponsable() {
		return responsable;
	}

	/**
	 * 
	 * 
	 * @campo RESPONSABLE
	*/
	public void setResponsable(Integer responsable) {
		this.responsable = responsable;
	}

}
