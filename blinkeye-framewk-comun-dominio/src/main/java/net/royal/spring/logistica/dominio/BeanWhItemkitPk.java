package net.royal.spring.logistica.dominio;

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
public class BeanWhItemkitPk implements java.io.Serializable{



	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "ITEM", length = 20, nullable = false)
	private String item;

	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "ITEMSUBORDINADO", length = 20, nullable = false)
	private String itemsubordinado;


	public BeanWhItemkitPk() {
	}

	public BeanWhItemkitPk(String pitem,String pitemsubordinado) {
this.item = pitem;
		this.itemsubordinado = pitemsubordinado;
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

}
