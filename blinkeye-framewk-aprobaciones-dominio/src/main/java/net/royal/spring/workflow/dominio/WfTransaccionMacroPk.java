package net.royal.spring.workflow.dominio;

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
 * @tabla SPRING.WF_TRANSACCION_MACRO
*/
public class WfTransaccionMacroPk implements java.io.Serializable{



	@NotNull
	@Column(name = "TRANSACCION_MACRO_ID", nullable = false)
	private Integer transaccionMacroId;


	public WfTransaccionMacroPk() {
	}

	public WfTransaccionMacroPk(Integer ptransaccionMacroId) {
this.transaccionMacroId = ptransaccionMacroId;
	}

	/**
	 *  
	 * 
	 * @campo TRANSACCION_MACRO_ID
	*/
	public Integer getTransaccionMacroId() {
		return transaccionMacroId;
	}

	/**
	 *  
	 * 
	 * @campo TRANSACCION_MACRO_ID
	*/
	public void setTransaccionMacroId(Integer transaccionMacroId) {
		this.transaccionMacroId = transaccionMacroId;
	}

}
