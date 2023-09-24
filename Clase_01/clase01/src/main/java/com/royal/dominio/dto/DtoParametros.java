package com.royal.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.royal.dominio.Parametrosmast;
import com.royal.util.DominioTransaccion;

public class DtoParametros extends DominioTransaccion implements java.io.Serializable {
	
	private String companiacodigo;
	private String aplicacioncodigo;
	private String parametroclave;
	
	private String descripcionparametro;
	private String explicacion;
	private String tipodedatoflag;
	private String texto;
	private BigDecimal numero;
	private Date fecha;
	private String financecomunflag;
	private String estado;
	private String ultimousuario;
	private Date ultimafechamodif;
	private String explicacionadicional;
	private String texto1;
	private String texto2;
	private String accion;
	
	public Parametrosmast obtenerBean() {
		Parametrosmast bean = new Parametrosmast();
		return obtenerBean(bean);
	}

	public Parametrosmast obtenerBean(Parametrosmast bean) {
		if (bean == null)
			bean = new Parametrosmast();

		bean.getPk().setAplicacioncodigo(aplicacioncodigo);
		bean.getPk().setCompaniacodigo(companiacodigo);
		bean.getPk().setParametroclave(parametroclave);
		bean.setDescripcionparametro(descripcionparametro);
		bean.setEstado(estado);
		bean.setExplicacion(explicacion);
		bean.setExplicacionadicional(explicacionadicional);
		bean.setFecha(fecha);
		bean.setFinancecomunflag(financecomunflag);
		bean.setNumero(numero);
		bean.setTexto(texto);
		bean.setTexto1(texto1);
		bean.setTexto2(texto2);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

	
	public String getCompaniacodigo() {
		return companiacodigo;
	}
	public void setCompaniacodigo(String companiacodigo) {
		this.companiacodigo = companiacodigo;
	}
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public String getParametroclave() {
		return parametroclave;
	}
	public void setParametroclave(String parametroclave) {
		this.parametroclave = parametroclave;
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

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
}
