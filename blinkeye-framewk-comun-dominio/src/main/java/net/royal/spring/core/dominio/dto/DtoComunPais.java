package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.core.dominio.BeanPais;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;

public class DtoComunPais extends DominioTransaccion{
	private String pais;
	private String descripcioncorta;
	private String descripcionlarga;
	private String descripcionextranjera;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String nombre;
	private String nacionalidad;
	private String codigortps;
	private String uuid;
	private BigDecimal ROWNUM_;
		
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
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
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getCodigortps() {
		return codigortps;
	}
	public void setCodigortps(String codigortps) {
		this.codigortps = codigortps;
	}
	@JsonIgnore
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
	public BeanPais obtenerBean() {
		return obtenerBeanCore(new BeanPais(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanPais obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanPais(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanPais obtenerBeanActualizar(BeanPais bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanPais obtenerBeanCore(BeanPais bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR:
		case ConstantePantallaAccion.NINGUNO:
			bean.getPk().setPais(pais);
			bean.setDescripcioncorta(descripcioncorta);
			bean.setDescripcionlarga(descripcionlarga);
			bean.setDescripcionextranjera(descripcionextranjera);
			bean.setEstado(estado);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setNombre(nombre);
			bean.setNacionalidad(nacionalidad);
			bean.setCodigortps(codigortps);
			bean.setUuid(uuid);
			break;
		case ConstantePantallaAccion.ACTUALIZAR:
			//bean.getPk().setUnidadnegocio(unidadnegocio);
			bean.getPk().setPais(pais);
			bean.setDescripcioncorta(descripcioncorta);
			bean.setDescripcionlarga(descripcionlarga);
			bean.setDescripcionextranjera(descripcionextranjera);
			bean.setEstado(estado);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setNombre(nombre);
			bean.setNacionalidad(nacionalidad);
			bean.setCodigortps(codigortps);
			//bean.setUuid(uuid);
			break;
		}

		return bean;
	}
}
