package net.royal.spring.contabilidad.dominio.lista;

import net.royal.spring.contabilidad.dominio.BeanAcSucursal;

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
public class DtlComunAcSucursal extends DominioTransaccion implements java.io.Serializable{


	private String sucursal;
	private String sucursalgrupo;
	private String descripcionlocal;
	private String descripcioningles;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String cuentacontabledefecto;
	private String almacencodigo;
	private String racionperfil;
	private String departamento;
	private String provincia;
	private String codigopostal;
	private String pais;
	private String direccion;
	private Integer responsable;
	private String estadodescripcion;
	private String fechadescripcion;
	private String grupo;
	private String uuid;	
	
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getEstadodescripcion() {
		return estadodescripcion;
	}

	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}

	public String getFechadescripcion() {
		return fechadescripcion;
	}

	public void setFechadescripcion(String fechadescripcion) {
		this.fechadescripcion = fechadescripcion;
	}
	/**
	 * 
	 * 
	 * @campo Sucursal
	*/
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * 
	 * 
	 * @campo Sucursal
	*/
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
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
