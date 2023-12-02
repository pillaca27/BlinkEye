package net.royal.spring.logistica.dominio.dto;

import net.royal.spring.logistica.dominio.BeanWhItemkit;

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
 * @tabla dbo.WH_ItemKit
 */
public class DtoComunWhItemkit extends DominioTransaccion implements java.io.Serializable {

	private String item;
	private String itemsubordinado;
	private String tieneprecioflag;
	private java.math.BigDecimal cantidad;
	private String accion;
	private String descripcionlocal;
	
	

	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
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
	 * @campo Item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * 
	 * 
	 * @campo Item
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * 
	 * 
	 * @campo ItemSubordinado
	 */
	public String getItemsubordinado() {
		return itemsubordinado;
	}

	/**
	 * 
	 * 
	 * @campo ItemSubordinado
	 */
	public void setItemsubordinado(String itemsubordinado) {
		this.itemsubordinado = itemsubordinado;
	}

	/**
	 * 
	 * 
	 * @campo TienePrecioFlag
	 */
	public String getTieneprecioflag() {
		return tieneprecioflag;
	}

	/**
	 * 
	 * 
	 * @campo TienePrecioFlag
	 */
	public void setTieneprecioflag(String tieneprecioflag) {
		this.tieneprecioflag = tieneprecioflag;
	}

	/**
	 * 
	 * 
	 * @campo Cantidad
	 */
	public java.math.BigDecimal getCantidad() {
		return cantidad;
	}

	/**
	 * 
	 * 
	 * @campo Cantidad
	 */
	public void setCantidad(java.math.BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BeanWhItemkit obtenerBean() {
		BeanWhItemkit bean = new BeanWhItemkit();
		return obtenerBean(bean);
	}

	public BeanWhItemkit obtenerBean(BeanWhItemkit bean) {
		if (bean == null)
			bean = new BeanWhItemkit();

		bean.getPk().setItem(item);
		bean.getPk().setItemsubordinado(itemsubordinado);
		bean.setTieneprecioflag(tieneprecioflag);
		bean.setCantidad(cantidad);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
