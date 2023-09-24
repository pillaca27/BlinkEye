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
 * @tabla dbo.WH_ItemKit
*/
@Entity
@Table(name = "WH_ITEMKIT")
public class BeanWhItemkit extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanWhItemkitPk pk;

	@Size(min = 0, max = 1)
	@Column(name = "TIENEPRECIOFLAG", length = 1, nullable = true)
	private String tieneprecioflag;

	@Column(name = "CANTIDAD", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal cantidad;


	public BeanWhItemkit() {
		pk = new BeanWhItemkitPk();
	}


	public BeanWhItemkit(BeanWhItemkitPk pk) {
		this.pk = pk;
	}

	public BeanWhItemkitPk getPk() {
		return pk;
	}

	public void setPk(BeanWhItemkitPk pk) {
		this.pk = pk;
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

}
