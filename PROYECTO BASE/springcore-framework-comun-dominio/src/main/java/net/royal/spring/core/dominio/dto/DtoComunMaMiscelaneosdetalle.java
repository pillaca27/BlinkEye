package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanMaMiscelaneosdetalle;

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
 * Maestro miscelaneos detalle
 * 
 * @tabla MA_MISCELANEOSDETALLE
*/
public class DtoComunMaMiscelaneosdetalle extends DominioTransaccion implements java.io.Serializable{
	private String aplicacioncodigo;
	private String codigotabla;
	private String compania;
	private String codigoelemento;
	
	private String descripcionlocal;
	private String descripcionextranjera;
	private Double valornumerico;
	private java.util.Date valorfecha;	
	
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	//////////////////////////////
	private String valorcodigo1;
	private String valorcodigo2;
	private String valorcodigo3;
	private String valorcodigo4;
	private String valorcodigo5;
	private String valorcodigo6;
	private String valorcodigo7;
	private String valorcodigo8;
	private String valorcodigo9;
	private String valorcodigo10;
	
	public DtoComunMaMiscelaneosdetalle(String aplicacioncodigo,String codigotabla,String compania,String codigoelemento) {
		this.aplicacioncodigo=aplicacioncodigo;
		this.codigotabla=codigotabla;
		this.compania=compania;
		this.codigoelemento=codigoelemento;
	}
	public DtoComunMaMiscelaneosdetalle() {}
		
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public String getCodigotabla() {
		return codigotabla;
	}

	public void setCodigotabla(String codigotabla) {
		this.codigotabla = codigotabla;
	}
	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}
	public String getCodigoelemento() {
		return codigoelemento;
	}

	public void setCodigoelemento(String codigoelemento) {
		this.codigoelemento = codigoelemento;
	}
	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}

	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	public Double getValornumerico() {
		return valornumerico;
	}

	public void setValornumerico(Double valornumerico) {
		this.valornumerico = valornumerico;
	}
	
	public java.util.Date getValorfecha() {
		return valorfecha;
	}

	public void setValorfecha(java.util.Date valorfecha) {
		this.valorfecha = valorfecha;
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
	
	
	public String getValorcodigo1() {
		return valorcodigo1;
	}

	public void setValorcodigo1(String valorcodigo1) {
		this.valorcodigo1 = valorcodigo1;
	}

	public String getValorcodigo2() {
		return valorcodigo2;
	}

	public void setValorcodigo2(String valorcodigo2) {
		this.valorcodigo2 = valorcodigo2;
	}

	public String getValorcodigo3() {
		return valorcodigo3;
	}

	public void setValorcodigo3(String valorcodigo3) {
		this.valorcodigo3 = valorcodigo3;
	}

	public String getValorcodigo4() {
		return valorcodigo4;
	}

	public void setValorcodigo4(String valorcodigo4) {
		this.valorcodigo4 = valorcodigo4;
	}

	public String getValorcodigo5() {
		return valorcodigo5;
	}

	public void setValorcodigo5(String valorcodigo5) {
		this.valorcodigo5 = valorcodigo5;
	}

	public String getValorcodigo6() {
		return valorcodigo6;
	}

	public void setValorcodigo6(String valorcodigo6) {
		this.valorcodigo6 = valorcodigo6;
	}

	public String getValorcodigo7() {
		return valorcodigo7;
	}

	public void setValorcodigo7(String valorcodigo7) {
		this.valorcodigo7 = valorcodigo7;
	}

	public String getValorcodigo8() {
		return valorcodigo8;
	}

	public void setValorcodigo8(String valorcodigo8) {
		this.valorcodigo8 = valorcodigo8;
	}

	public String getValorcodigo9() {
		return valorcodigo9;
	}

	public void setValorcodigo9(String valorcodigo9) {
		this.valorcodigo9 = valorcodigo9;
	}

	public String getValorcodigo10() {
		return valorcodigo10;
	}

	public void setValorcodigo10(String valorcodigo10) {
		this.valorcodigo10 = valorcodigo10;
	}


	private DominioPaginacion paginacion;

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public BeanMaMiscelaneosdetalle obtenerBean() {
		BeanMaMiscelaneosdetalle bean = new BeanMaMiscelaneosdetalle();
		return obtenerBean(bean);
	}

	public BeanMaMiscelaneosdetalle obtenerBean(BeanMaMiscelaneosdetalle bean) {
		if (bean == null)
			bean = new BeanMaMiscelaneosdetalle();

		bean.getPk().setAplicacioncodigo(aplicacioncodigo);
		bean.getPk().setCodigotabla(codigotabla);
		bean.getPk().setCompania(compania);
		bean.getPk().setCodigoelemento(codigoelemento);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcionextranjera(descripcionextranjera);
		bean.setValornumerico(valornumerico);		
		bean.setValorfecha(valorfecha);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);		
		bean.setValorcodigo1(valorcodigo1);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
