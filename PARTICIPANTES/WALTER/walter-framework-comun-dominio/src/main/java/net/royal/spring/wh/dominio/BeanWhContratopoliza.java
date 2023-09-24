package net.royal.spring.wh.dominio;

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
 * @tabla dbo.WH_ContratoPoliza
*/
@Entity
@Table(name = "WH_CONTRATOPOLIZA")
public class BeanWhContratopoliza extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanWhContratopolizaPk pk;

	@Size(min = 0, max = 2)
	@Column(name = "POLIZATIPO", length = 2, nullable = true)
	private String polizatipo;

	@Size(min = 0, max = 20)
	@Column(name = "POLIZANUMERO", length = 20, nullable = true)
	private String polizanumero;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHADESDE", nullable = true)
	private java.util.Date fechadesde;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHAHASTA", nullable = true)
	private java.util.Date fechahasta;

	@Column(name = "ASEGURADORA", nullable = true)
	private Integer aseguradora;

	@Column(name = "PORCENTAJECONTRATO", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal porcentajecontrato;

	@Column(name = "MONTOPOLIZA", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal montopoliza;

	@Size(min = 0, max = 1)
	@Column(name = "CLAUSULASESPECIALESFLAG", length = 1, nullable = true)
	private String clausulasespecialesflag;

	@Size(min = 0, max = 1)
	@Column(name = "RECIBOSPAGOFLAG", length = 1, nullable = true)
	private String recibospagoflag;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Column(name = "PERSONACORREO", nullable = true)
	private Integer personacorreo;

	@Size(min = 0, max = 255)
	@Column(name = "COMENTARIO", length = 255, nullable = true)
	private String comentario;


	public BeanWhContratopoliza() {
		pk = new BeanWhContratopolizaPk();
	}


	public BeanWhContratopoliza(BeanWhContratopolizaPk pk) {
		this.pk = pk;
	}

	public BeanWhContratopolizaPk getPk() {
		return pk;
	}

	public void setPk(BeanWhContratopolizaPk pk) {
		this.pk = pk;
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

}
