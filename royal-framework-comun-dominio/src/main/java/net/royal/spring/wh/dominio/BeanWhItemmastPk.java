package net.royal.spring.wh.dominio;

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
 * @tabla dbo.WH_ItemMast
*/
public class BeanWhItemmastPk implements java.io.Serializable{



	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "ITEM", length = 20, nullable = false)
	private String item;


	public BeanWhItemmastPk() {
	}

	public BeanWhItemmastPk(String pitem) {
this.item = pitem;
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

}
