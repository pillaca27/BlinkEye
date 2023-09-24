package net.royal.spring.wh.dominio.dto;

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
import net.royal.spring.wh.dominio.BeanWhContratopoliza;

/**
 * 
 * 
 * @tabla dbo.WH_ContratoPoliza
*/
public class DtoComunWhContratopoliza extends DominioTransaccion implements java.io.Serializable{


	private String companiasocio;
	private String numerocontrato;
	private Integer secuencia;
	private String polizatipo;
	private String polizanumero;
	private java.util.Date fechadesde;
	private java.util.Date fechahasta;
	private Integer aseguradora;
	private java.math.BigDecimal porcentajecontrato;
	private java.math.BigDecimal montopoliza;
	private String clausulasespecialesflag;
	private String recibospagoflag;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private Integer personacorreo;
	private String comentario;
	private String accion;
	private String tipoadenda_descri;

	
	
	public String getTipoadenda_descri() {
		return tipoadenda_descri;
	}

	public void setTipoadenda_descri(String tipoadenda_descri) {
		this.tipoadenda_descri = tipoadenda_descri;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
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
	 * @campo NumeroContrato
	*/
	public String getNumerocontrato() {
		return numerocontrato;
	}

	/**
	 * 
	 * 
	 * @campo NumeroContrato
	*/
	public void setNumerocontrato(String numerocontrato) {
		this.numerocontrato = numerocontrato;
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
	/**
	 * 
	 * 
	 * @campo PolizaTipo
	*/
	public String getPolizatipo() {
		return polizatipo;
	}

	/**
	 * 
	 * 
	 * @campo PolizaTipo
	*/
	public void setPolizatipo(String polizatipo) {
		this.polizatipo = polizatipo;
	}
	/**
	 * 
	 * 
	 * @campo PolizaNumero
	*/
	public String getPolizanumero() {
		return polizanumero;
	}

	/**
	 * 
	 * 
	 * @campo PolizaNumero
	*/
	public void setPolizanumero(String polizanumero) {
		this.polizanumero = polizanumero;
	}
	/**
	 * 
	 * 
	 * @campo FechaDesde
	*/
	public java.util.Date getFechadesde() {
		return fechadesde;
	}

	/**
	 * 
	 * 
	 * @campo FechaDesde
	*/
	public void setFechadesde(java.util.Date fechadesde) {
		this.fechadesde = fechadesde;
	}
	/**
	 * 
	 * 
	 * @campo FechaHasta
	*/
	public java.util.Date getFechahasta() {
		return fechahasta;
	}

	/**
	 * 
	 * 
	 * @campo FechaHasta
	*/
	public void setFechahasta(java.util.Date fechahasta) {
		this.fechahasta = fechahasta;
	}
	/**
	 * 
	 * 
	 * @campo Aseguradora
	*/
	public Integer getAseguradora() {
		return aseguradora;
	}

	/**
	 * 
	 * 
	 * @campo Aseguradora
	*/
	public void setAseguradora(Integer aseguradora) {
		this.aseguradora = aseguradora;
	}
	/**
	 * 
	 * 
	 * @campo PorcentajeContrato
	*/
	public java.math.BigDecimal getPorcentajecontrato() {
		return porcentajecontrato;
	}

	/**
	 * 
	 * 
	 * @campo PorcentajeContrato
	*/
	public void setPorcentajecontrato(java.math.BigDecimal porcentajecontrato) {
		this.porcentajecontrato = porcentajecontrato;
	}
	/**
	 * 
	 * 
	 * @campo MontoPoliza
	*/
	public java.math.BigDecimal getMontopoliza() {
		return montopoliza;
	}

	/**
	 * 
	 * 
	 * @campo MontoPoliza
	*/
	public void setMontopoliza(java.math.BigDecimal montopoliza) {
		this.montopoliza = montopoliza;
	}
	/**
	 * 
	 * 
	 * @campo ClausulasEspecialesFlag
	*/
	public String getClausulasespecialesflag() {
		return clausulasespecialesflag;
	}

	/**
	 * 
	 * 
	 * @campo ClausulasEspecialesFlag
	*/
	public void setClausulasespecialesflag(String clausulasespecialesflag) {
		this.clausulasespecialesflag = clausulasespecialesflag;
	}
	/**
	 * 
	 * 
	 * @campo RecibosPagoFlag
	*/
	public String getRecibospagoflag() {
		return recibospagoflag;
	}

	/**
	 * 
	 * 
	 * @campo RecibosPagoFlag
	*/
	public void setRecibospagoflag(String recibospagoflag) {
		this.recibospagoflag = recibospagoflag;
	}
	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * 
	 * 
	 * @campo PersonaCorreo
	*/
	public Integer getPersonacorreo() {
		return personacorreo;
	}

	/**
	 * 
	 * 
	 * @campo PersonaCorreo
	*/
	public void setPersonacorreo(Integer personacorreo) {
		this.personacorreo = personacorreo;
	}
	/**
	 * 
	 * 
	 * @campo Comentario
	*/
	public String getComentario() {
		return comentario;
	}

	/**
	 * 
	 * 
	 * @campo Comentario
	*/
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public BeanWhContratopoliza obtenerBean() {
		BeanWhContratopoliza bean = new BeanWhContratopoliza();
		return obtenerBean(bean);
	}

	public BeanWhContratopoliza obtenerBean(BeanWhContratopoliza bean) {
		if (bean == null)
			bean = new BeanWhContratopoliza();

		bean.getPk().setCompaniasocio(companiasocio);
		bean.getPk().setNumerocontrato(numerocontrato);
		bean.getPk().setSecuencia(secuencia);
		bean.setPolizatipo(polizatipo);
		bean.setPolizanumero(polizanumero);
		bean.setFechadesde(fechadesde);
		bean.setFechahasta(fechahasta);
		bean.setAseguradora(aseguradora);
		bean.setPorcentajecontrato(porcentajecontrato);
		bean.setMontopoliza(montopoliza);
		bean.setClausulasespecialesflag(clausulasespecialesflag);
		bean.setRecibospagoflag(recibospagoflag);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setPersonacorreo(personacorreo);
		bean.setComentario(comentario);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
