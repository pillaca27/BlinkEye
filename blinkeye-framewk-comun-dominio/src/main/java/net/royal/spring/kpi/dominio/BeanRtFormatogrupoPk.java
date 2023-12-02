package net.royal.spring.kpi.dominio;

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
 * @tabla dbo.RT_FormatoGrupo
*/
public class BeanRtFormatogrupoPk implements java.io.Serializable{



	@NotNull
	@Column(name = "FMTCODIGO", nullable = false)
	private Integer fmtcodigo;

	@NotNull
	@Column(name = "GRPCODIGO", nullable = false)
	private Integer grpcodigo;


	public BeanRtFormatogrupoPk() {
	}

	public BeanRtFormatogrupoPk(Integer pfmtcodigo,Integer pgrpcodigo) {
this.fmtcodigo = pfmtcodigo;
		this.grpcodigo = pgrpcodigo;
	}

	/**
	 * 
	 * 
	 * @campo FmtCodigo
	*/
	public Integer getFmtcodigo() {
		return fmtcodigo;
	}

	/**
	 * 
	 * 
	 * @campo FmtCodigo
	*/
	public void setFmtcodigo(Integer fmtcodigo) {
		this.fmtcodigo = fmtcodigo;
	}
	/**
	 * 
	 * 
	 * @campo GrpCodigo
	*/
	public Integer getGrpcodigo() {
		return grpcodigo;
	}

	/**
	 * 
	 * 
	 * @campo GrpCodigo
	*/
	public void setGrpcodigo(Integer grpcodigo) {
		this.grpcodigo = grpcodigo;
	}

}
