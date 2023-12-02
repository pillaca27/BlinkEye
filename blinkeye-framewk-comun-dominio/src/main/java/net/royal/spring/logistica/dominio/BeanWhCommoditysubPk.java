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
 * @tabla dbo.WH_CommoditySub
*/
public class BeanWhCommoditysubPk implements java.io.Serializable{



	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "COMMODITY01", length = 2, nullable = false)
	private String commodity01;

	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "COMMODITY02", length = 3, nullable = false)
	private String commodity02;


	public BeanWhCommoditysubPk() {
	}

	public BeanWhCommoditysubPk(String pcommodity01,String pcommodity02) {
this.commodity01 = pcommodity01;
		this.commodity02 = pcommodity02;
	}

	/**
	 * 
	 * 
	 * @campo Commodity01
	*/
	public String getCommodity01() {
		return commodity01;
	}

	/**
	 * 
	 * 
	 * @campo Commodity01
	*/
	public void setCommodity01(String commodity01) {
		this.commodity01 = commodity01;
	}
	/**
	 * 
	 * 
	 * @campo Commodity02
	*/
	public String getCommodity02() {
		return commodity02;
	}

	/**
	 * 
	 * 
	 * @campo Commodity02
	*/
	public void setCommodity02(String commodity02) {
		this.commodity02 = commodity02;
	}

}
