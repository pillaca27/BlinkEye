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
 * @tabla dbo.WH_ContratoRequisicion
*/
@Entity
@Table(name = "WH_CONTRATOREQUISICION")
public class BeanWhContratorequisicion extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanWhContratorequisicionPk pk;

	@Column(name = "CANTIDAD", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal cantidad;


	public BeanWhContratorequisicion() {
		pk = new BeanWhContratorequisicionPk();
	}


	public BeanWhContratorequisicion(BeanWhContratorequisicionPk pk) {
		this.pk = pk;
	}

	public BeanWhContratorequisicionPk getPk() {
		return pk;
	}

	public void setPk(BeanWhContratorequisicionPk pk) {
		this.pk = pk;
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
