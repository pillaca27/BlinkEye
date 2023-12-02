package net.royal.spring.logistica.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 
 * 
 * @tabla dbo.WH_Commodity
*/
public class BeanWhCommodityPk implements java.io.Serializable{



	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "COMMODITY01", length = 2, nullable = false)
	private String commodity01;


	public BeanWhCommodityPk() {
	}

	public BeanWhCommodityPk(String pcommodity01) {
this.commodity01 = pcommodity01;
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

}
