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
 * @tabla dbo.Banco
*/
public class BeanBancoPk implements java.io.Serializable{



	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "BANCO", length = 3, nullable = false)
	private String banco;


	public BeanBancoPk() {
	}

	public BeanBancoPk(String pbanco) {
this.banco = pbanco;
	}

	/**
	 * 
	 * 
	 * @campo Banco
	*/
	public String getBanco() {
		return banco;
	}

	/**
	 * 
	 * 
	 * @campo Banco
	*/
	public void setBanco(String banco) {
		this.banco = banco;
	}

}
