package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanZonapostal;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.ZonaPostal
*/
public class DtoComunZonapostal extends DominioTransaccion implements java.io.Serializable{


	private String departamento;
	private String provincia;
	private String codigopostal;
	private String descripcioncorta;
	private String descripcionlarga;
	private Integer cobrador;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	private String accion;
 
	private String pais;
	private String ubigeo;
	
	

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * 
	 * 
	 * @campo Departamento
	*/
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * 
	 * 
	 * @campo Departamento
	*/
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	/**
	 * 
	 * 
	 * @campo Provincia
	*/
	public String getProvincia() {
		return provincia;
	}

	/**
	 * 
	 * 
	 * @campo Provincia
	*/
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**
	 * 
	 * 
	 * @campo CodigoPostal
	*/
	public String getCodigopostal() {
		return codigopostal;
	}

	/**
	 * 
	 * 
	 * @campo CodigoPostal
	*/
	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionCorta
	*/
	public String getDescripcioncorta() {
		return descripcioncorta;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionCorta
	*/
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionLarga
	*/
	public String getDescripcionlarga() {
		return descripcionlarga;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionLarga
	*/
	public void setDescripcionlarga(String descripcionlarga) {
		this.descripcionlarga = descripcionlarga;
	}
	/**
	 * 
	 * 
	 * @campo Cobrador
	*/
	public Integer getCobrador() {
		return cobrador;
	}

	/**
	 * 
	 * 
	 * @campo Cobrador
	*/
	public void setCobrador(Integer cobrador) {
		this.cobrador = cobrador;
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
	public BeanZonapostal obtenerBean() {
		BeanZonapostal bean = new BeanZonapostal();
		return obtenerBean(bean);
	}

	public BeanZonapostal obtenerBean(BeanZonapostal bean) {
		if (bean == null)
			bean = new BeanZonapostal();

		bean.getPk().setDepartamento(departamento);
		bean.getPk().setProvincia(provincia);
		bean.getPk().setCodigopostal(codigopostal);
		bean.setDescripcioncorta(descripcioncorta);
		bean.setDescripcionlarga(descripcionlarga);
		bean.setCobrador(cobrador);
		bean.setEstado(estado);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setUltimousuario(ultimousuario);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		bean.setUbigeo(ubigeo);
		bean.setPais(pais);
		return bean;
	}

}
