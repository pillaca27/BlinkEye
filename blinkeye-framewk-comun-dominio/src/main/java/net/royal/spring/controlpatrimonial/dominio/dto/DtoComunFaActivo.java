package net.royal.spring.controlpatrimonial.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunFaActivo extends DominioTransaccion {
	private String activo;
	private String busquedalocal;
	private String codigointerno;
	private String ubicacion;	
	private BigDecimal empleadoacargo;
	private String centrocostos;
	private String numeroplaca;
	private String referencia01;
	private String vouchergrupo01;
	private String companiasocio;	
	private String sucursal;
	private String unidadnegocio;
		
	@JsonIgnore
	private BigDecimal ROWNUM_;
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getBusquedalocal() {
		return busquedalocal;
	}

	public void setBusquedalocal(String busquedalocal) {
		this.busquedalocal = busquedalocal;
	}

	public String getCodigointerno() {
		return codigointerno;
	}

	public void setCodigointerno(String codigointerno) {
		this.codigointerno = codigointerno;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public String getCentrocostos() {
		return centrocostos;
	}

	public void setCentrocostos(String centrocostos) {
		this.centrocostos = centrocostos;
	}

	public String getNumeroplaca() {
		return numeroplaca;
	}

	public void setNumeroplaca(String numeroplaca) {
		this.numeroplaca = numeroplaca;
	}

	public String getReferencia01() {
		return referencia01;
	}

	public void setReferencia01(String referencia01) {
		this.referencia01 = referencia01;
	}

	public String getVouchergrupo01() {
		return vouchergrupo01;
	}

	public void setVouchergrupo01(String vouchergrupo01) {
		this.vouchergrupo01 = vouchergrupo01;
	}


	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}


	public BigDecimal getEmpleadoacargo() {
		return empleadoacargo;
	}

	public void setEmpleadoacargo(BigDecimal empleadoacargo) {
		this.empleadoacargo = empleadoacargo;
	}


	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}


	public String getUnidadnegocio() {
		return unidadnegocio;
	}

	public void setUnidadnegocio(String unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
	}
	
}
