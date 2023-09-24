package com.royal.dominio;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.royal.util.DominioTransaccion;

@Entity
@Table(name = "ParametrosMast")
public class Parametrosmast extends DominioTransaccion{

	@EmbeddedId
	private ParametrosmastPk pk;

	@Size(min = 0, max = 100)
	@Column(name = "DESCRIPCIONPARAMETRO", length = 100, nullable = true)
	private String descripcionparametro;

	@Size(min = 0, max = 100)
	@Column(name = "EXPLICACION", length = 150, nullable = true)
	private String explicacion;

	@Size(min = 0, max = 1)
	@Column(name = "TIPODEDATOFLAG", length = 1, nullable = true)
	private String tipodedatoflag;

	@Size(min = 0, max = 100)
	@Column(name = "TEXTO", length = 100, nullable = true)
	private String texto;

	@Column(name = "NUMERO", precision = 19, scale = 4, nullable = true)
	private BigDecimal numero;

	@Column(name = "FECHA", nullable = true)
	private Date fecha;

	@Size(min = 0, max = 1)
	@Column(name = "FINANCECOMUNFLAG", length = 1, nullable = true)
	private String financecomunflag;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private Date ultimafechamodif;
	
	@Size(min = 0, max = 250)
	@Column(name = "EXPLICACIONADICIONAL", length = 250, nullable = true)
	private String explicacionadicional;
	
	@Size(min = 0, max = 10)
	@Column(name = "TEXTO1", length = 10, nullable = true)
	private String texto1;
	
	@Size(min = 0, max = 1)
	@Column(name = "TEXTO2", length = 10, nullable = true)
	private String texto2;
	
	
	
	
	public Parametrosmast() {
		pk= new ParametrosmastPk();
	}

	public ParametrosmastPk getPk() {
		return pk;
	}

	public void setPk(ParametrosmastPk pk) {
		this.pk = pk;
	}

	public String getDescripcionparametro() {
		return descripcionparametro;
	}

	public void setDescripcionparametro(String descripcionparametro) {
		this.descripcionparametro = descripcionparametro;
	}

	public String getExplicacion() {
		return explicacion;
	}

	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}

	public String getTipodedatoflag() {
		return tipodedatoflag;
	}

	public void setTipodedatoflag(String tipodedatoflag) {
		this.tipodedatoflag = tipodedatoflag;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public BigDecimal getNumero() {
		return numero;
	}

	public void setNumero(BigDecimal numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFinancecomunflag() {
		return financecomunflag;
	}

	public void setFinancecomunflag(String financecomunflag) {
		this.financecomunflag = financecomunflag;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}

	public Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}

	public String getExplicacionadicional() {
		return explicacionadicional;
	}

	public void setExplicacionadicional(String explicacionadicional) {
		this.explicacionadicional = explicacionadicional;
	}

	public String getTexto1() {
		return texto1;
	}

	public void setTexto1(String texto1) {
		this.texto1 = texto1;
	}

	public String getTexto2() {
		return texto2;
	}

	public void setTexto2(String texto2) {
		this.texto2 = texto2;
	}
}