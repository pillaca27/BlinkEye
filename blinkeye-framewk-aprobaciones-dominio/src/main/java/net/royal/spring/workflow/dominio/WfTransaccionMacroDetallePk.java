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
 * @tabla SPRING.WF_TRANSACCION_MACRO_DETALLE
*/
public class WfTransaccionMacroDetallePk implements java.io.Serializable{



	@NotNull
	@Column(name = "TRANSACCION_MACRO_ID", nullable = false)
	private Integer transaccionMacroId;

	@NotNull
	@Column(name = "TRANSACCION_MACRO_DETALLE_ID", nullable = false)
	private Integer transaccionMacroDetalleId;


	public WfTransaccionMacroDetallePk() {
	}

	public WfTransaccionMacroDetallePk(Integer ptransaccionMacroId,Integer ptransaccionMacroDetalleId) {
this.transaccionMacroId = ptransaccionMacroId;
		this.transaccionMacroDetalleId = ptransaccionMacroDetalleId;
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
	/**
	 *  
	 * 
	 * @campo TRANSACCION_MACRO_DETALLE_ID
	*/
	public Integer getTransaccionMacroDetalleId() {
		return transaccionMacroDetalleId;
	}

	/**
	 *  
	 * 
	 * @campo TRANSACCION_MACRO_DETALLE_ID
	*/
	public void setTransaccionMacroDetalleId(Integer transaccionMacroDetalleId) {
		this.transaccionMacroDetalleId = transaccionMacroDetalleId;
	}

}
