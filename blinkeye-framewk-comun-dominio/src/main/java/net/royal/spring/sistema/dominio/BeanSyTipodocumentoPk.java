package net.royal.spring.sistema.dominio;

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

import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.SY_TipoDocumento
*/
public class BeanSyTipodocumentoPk implements java.io.Serializable{



	@Size(min = 0, max = 4)
	@NotNull
	@NotEmpty
	@Column(name = "TIPODOCUMENTOID", length = 4, nullable = false)
	private String tipodocumentoid;


	public BeanSyTipodocumentoPk() {
	}

	public BeanSyTipodocumentoPk(String ptipodocumentoid) {
this.tipodocumentoid = ptipodocumentoid;
	}

	/**
	 * 
	 * 
	 * @campo TipoDocumentoId
	*/
	public String getTipodocumentoid() {
		return tipodocumentoid;
	}

	/**
	 * 
	 * 
	 * @campo TipoDocumentoId
	*/
	public void setTipodocumentoid(String tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

}
