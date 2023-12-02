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
 * @tabla SPRING.WF_MACRO_PROCESO
*/
public class WfMacroProcesoPk implements java.io.Serializable{



	@Size(min = 0, max = 4)
	@NotNull
	@NotEmpty
	@Column(name = "MACRO_PROCESO_ID", length = 4, nullable = false)
	private String macroProcesoId;


	public WfMacroProcesoPk() {
	}

	public WfMacroProcesoPk(String pmacroProcesoId) {
this.macroProcesoId = pmacroProcesoId;
	}

	/**
	 *  
	 * 
	 * @campo MACRO_PROCESO_ID
	*/
	public String getMacroProcesoId() {
		return macroProcesoId;
	}

	/**
	 *  
	 * 
	 * @campo MACRO_PROCESO_ID
	*/
	public void setMacroProcesoId(String macroProcesoId) {
		this.macroProcesoId = macroProcesoId;
	}

}
