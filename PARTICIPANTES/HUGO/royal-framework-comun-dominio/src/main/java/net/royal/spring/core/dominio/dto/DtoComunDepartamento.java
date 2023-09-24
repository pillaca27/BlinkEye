package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.royal.spring.core.dominio.BeanDepartamento;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunDepartamento  extends DominioTransaccion{
	
	private String departamento;
	private String descripcioncorta;
	private String descripcionlarga;
	private String pais;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private List<DtoComunProvincia> provincias = new ArrayList<DtoComunProvincia>();
	private List<DtoComunZonapostal> distritos= new ArrayList<DtoComunZonapostal>();
	private String accion;
	private String provincia;
	
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public List<DtoComunZonapostal> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<DtoComunZonapostal> distritos) {
		this.distritos = distritos;
	}

	public List<DtoComunProvincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<DtoComunProvincia> provincias) {
		this.provincias = provincias;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getDescripcioncorta() {
		return descripcioncorta;
	}
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}
	public String getDescripcionlarga() {
		return descripcionlarga;
	}
	public void setDescripcionlarga(String descripcionlarga) {
		this.descripcionlarga = descripcionlarga;
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
	
	public BeanDepartamento obtenerBean() {
		BeanDepartamento bean = new BeanDepartamento();
		return obtenerBean(bean);
	}

	public BeanDepartamento obtenerBean(BeanDepartamento bean) {
		if (bean == null)
			bean = new BeanDepartamento();

		bean.getPk().setDepartamento(departamento);
		bean.setDescripcioncorta(descripcioncorta);
		bean.setDescripcionlarga(descripcionlarga);
		bean.setPais(pais);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}
}
