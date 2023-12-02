package net.royal.spring.tesoreria.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunApGastoclasificacion extends DominioTransaccion {
	private String clasificacion;
	private String aplicacionflag;
	private String descripcionlocal;
	private String descripcioningles;
	private String viajeflag;
	private String importacionflag;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String requiereadelantoflag;
	private String viajetipopersonausuario;
	private String cuentacontablelocal;
	private String cuentacontabledolares;
	
	/*private String clasificacion;
	private String aplicacionflag;
	private String descripcionlocal;
	private String estado;
	private String viajeflag;
	private String importacionflag;*/
	/* solo sirve para la paginacion */
	@JsonIgnore
	private BigDecimal ROWNUM_;
	
	public DtoComunApGastoclasificacion() {}
	public DtoComunApGastoclasificacion(String clasificacion) {
		this.clasificacion=clasificacion;
	}
	public DtoComunApGastoclasificacion(String clasificacion,String aplicacionflag) {
		this.clasificacion=clasificacion;
		this.aplicacionflag=aplicacionflag;
	}
	
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public String getAplicacionflag() {
		return aplicacionflag;
	}
	public void setAplicacionflag(String aplicacionflag) {
		this.aplicacionflag = aplicacionflag;
	}
	public String getDescripcionlocal() {
		return descripcionlocal;
	}
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getViajeflag() {
		return viajeflag;
	}
	public void setViajeflag(String viajeflag) {
		this.viajeflag = viajeflag;
	}
	public String getImportacionflag() {
		return importacionflag;
	}
	public void setImportacionflag(String importacionflag) {
		this.importacionflag = importacionflag;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public String getDescripcioningles() {
		return descripcioningles;
	}
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getRequiereadelantoflag() {
		return requiereadelantoflag;
	}
	public void setRequiereadelantoflag(String requiereadelantoflag) {
		this.requiereadelantoflag = requiereadelantoflag;
	}
	public String getViajetipopersonausuario() {
		return viajetipopersonausuario;
	}
	public void setViajetipopersonausuario(String viajetipopersonausuario) {
		this.viajetipopersonausuario = viajetipopersonausuario;
	}
	public String getCuentacontablelocal() {
		return cuentacontablelocal;
	}
	public void setCuentacontablelocal(String cuentacontablelocal) {
		this.cuentacontablelocal = cuentacontablelocal;
	}
	public String getCuentacontabledolares() {
		return cuentacontabledolares;
	}
	public void setCuentacontabledolares(String cuentacontabledolares) {
		this.cuentacontabledolares = cuentacontabledolares;
	}
	
	
}
