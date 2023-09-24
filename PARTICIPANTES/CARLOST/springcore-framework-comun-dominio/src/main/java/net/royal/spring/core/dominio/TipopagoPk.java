package net.royal.spring.core.dominio;

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
 * @tabla dbo.TipoPago
*/
public class TipopagoPk implements java.io.Serializable{



	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "TIPOPAGO", length = 2, nullable = false)
	private String tipopago;


	public TipopagoPk() {
	}

	public TipopagoPk(String ptipopago) {
this.tipopago = ptipopago;
	}

	/**
	 * 
	 * 
	 * @campo TipoPago
	*/
	public String getTipopago() {
		return tipopago;
	}

	/**
	 * 
	 * 
	 * @campo TipoPago
	*/
	public void setTipopago(String tipopago) {
		this.tipopago = tipopago;
	}

}
