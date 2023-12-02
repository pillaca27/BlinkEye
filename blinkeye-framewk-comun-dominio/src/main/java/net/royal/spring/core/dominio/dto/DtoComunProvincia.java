package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanProvincia;

import java.util.ArrayList;
import java.util.List;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;


/**
 * 
 * 
 * @tabla dbo.Provincia
*/
public class DtoComunProvincia extends DominioTransaccion implements java.io.Serializable{


	private String departamento;
	private String provincia;
	private String descripcioncorta;
	private String descripcionlarga;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	private List<DtoComunZonapostal> zonaPostal= new ArrayList<DtoComunZonapostal>();
	private String accion;
	private String pais;
	
	
	

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public List<DtoComunZonapostal> getZonaPostal() {
		return zonaPostal;
	}

	public void setZonaPostal(List<DtoComunZonapostal> zonaPostal) {
		this.zonaPostal = zonaPostal;
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
	public BeanProvincia obtenerBean() {
		BeanProvincia bean = new BeanProvincia();
		return obtenerBean(bean);
	}

	public BeanProvincia obtenerBean(BeanProvincia bean) {
		if (bean == null)
			bean = new BeanProvincia();

		bean.getPk().setDepartamento(departamento);
		bean.getPk().setProvincia(provincia);
		bean.setDescripcioncorta(descripcioncorta);
		bean.setDescripcionlarga(descripcionlarga);
		bean.setEstado(estado);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setUltimousuario(ultimousuario);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());
		
		 bean.setPais(pais);

		return bean;
	}

}
