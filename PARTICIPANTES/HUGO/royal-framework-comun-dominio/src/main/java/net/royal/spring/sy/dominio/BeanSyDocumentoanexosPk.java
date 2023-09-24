package net.royal.spring.sy.dominio;

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
 * @tabla dbo.SY_DocumentoAnexos
*/
public class BeanSyDocumentoanexosPk implements java.io.Serializable{



	@Size(min = 0, max = 8)
	@NotNull
	@NotEmpty
	@Column(name = "COMPANIASOCIO", length = 8, nullable = false)
	private String companiasocio;

	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "MODULO", length = 2, nullable = false)
	private String modulo;

	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "TIPODOCUMENTO", length = 2, nullable = false)
	private String tipodocumento;

	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "NUMERODOCUMENTO", length = 20, nullable = false)
	private String numerodocumento;

	@NotNull
	@Column(name = "LINEA", nullable = false)
	private Integer linea;

	@NotNull
	@Column(name = "SECUENCIA", nullable = false)
	private Integer secuencia;


	public BeanSyDocumentoanexosPk() {
	}

	public BeanSyDocumentoanexosPk(String pcompaniasocio,String pmodulo,String ptipodocumento,String pnumerodocumento,Integer plinea,Integer psecuencia) {
this.companiasocio = pcompaniasocio;
		this.modulo = pmodulo;
		this.tipodocumento = ptipodocumento;
		this.numerodocumento = pnumerodocumento;
		this.linea = plinea;
		this.secuencia = psecuencia;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public String getCompaniasocio() {
		return companiasocio;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	/**
	 * 
	 * 
	 * @campo Modulo
	*/
	public String getModulo() {
		return modulo;
	}

	/**
	 * 
	 * 
	 * @campo Modulo
	*/
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	/**
	 * 
	 * 
	 * @campo TipoDocumento
	*/
	public String getTipodocumento() {
		return tipodocumento;
	}

	/**
	 * 
	 * 
	 * @campo TipoDocumento
	*/
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	/**
	 * 
	 * 
	 * @campo NumeroDocumento
	*/
	public String getNumerodocumento() {
		return numerodocumento;
	}

	/**
	 * 
	 * 
	 * @campo NumeroDocumento
	*/
	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}
	/**
	 * 
	 * 
	 * @campo Linea
	*/
	public Integer getLinea() {
		return linea;
	}

	/**
	 * 
	 * 
	 * @campo Linea
	*/
	public void setLinea(Integer linea) {
		this.linea = linea;
	}
	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public Integer getSecuencia() {
		return secuencia;
	}

	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

}
