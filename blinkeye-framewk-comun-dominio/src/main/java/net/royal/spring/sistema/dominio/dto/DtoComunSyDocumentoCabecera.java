package net.royal.spring.sistema.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.SY_DocumentoAnexos
*/
public class DtoComunSyDocumentoCabecera extends DominioTransaccion implements java.io.Serializable{

	private String companiasocio;
	private String modulo;
	private String tipodocumento;
	private String numerodocumento;
	private Integer linea;
	private String numerocontrato;
	private String sistemacontratacion;
	private String monedadocumento;
	private BigDecimal preciototal;
	
	private List<DtoTabla>listaExtensiones;
	private List<DtoComunSyDocumentoanexos> listaDocumentos;

	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getNumerodocumento() {
		return numerodocumento;
	}

	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}

	public Integer getLinea() {
		return linea;
	}

	public void setLinea(Integer linea) {
		this.linea = linea;
	}

	public List<DtoComunSyDocumentoanexos> getListaDocumentos() {
		if (listaDocumentos==null)
			listaDocumentos=new ArrayList<DtoComunSyDocumentoanexos>(); 
		return listaDocumentos;
	}

	public void setListaDocumentos(List<DtoComunSyDocumentoanexos> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}

	public String getNumerocontrato() {
		return numerocontrato;
	}

	public void setNumerocontrato(String numerocontrato) {
		this.numerocontrato = numerocontrato;
	}

	public String getSistemacontratacion() {
		return sistemacontratacion;
	}

	public void setSistemacontratacion(String sistemacontratacion) {
		this.sistemacontratacion = sistemacontratacion;
	}

	public String getMonedadocumento() {
		return monedadocumento;
	}

	public void setMonedadocumento(String monedadocumento) {
		this.monedadocumento = monedadocumento;
	}

	public BigDecimal getPreciototal() {
		return preciototal;
	}

	public void setPreciototal(BigDecimal preciototal) {
		this.preciototal = preciototal;
	}

	public List<DtoTabla> getListaExtensiones() {
		return listaExtensiones;
	}

	public void setListaExtensiones(List<DtoTabla> listaExtensiones) {
		this.listaExtensiones = listaExtensiones;
	}	
}
