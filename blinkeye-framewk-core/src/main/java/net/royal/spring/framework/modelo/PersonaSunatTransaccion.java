package net.royal.spring.framework.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class PersonaSunatTransaccion extends DominioTransaccion{
	//private Integer tipoRespuesta;
	//private String mensajeRespuesta;
	private String ruc;
	private String tipoContribuyente;
	private String nombreComercial;
	private String fechaInscripcion;
	private String fechaInicioActividades;
	private String estadoContribuyente;
	private String condicionContribuyente;
	private String domicilioFiscal;
	private String sistemaEmisionComprobante;
	private String actividadComercioExterior;
	private String sistemaContabilidiad;
	private List<String> actividadesEconomicas;
	private List<String> comprobantesPago;
	private List<String> sistemaEmisionElectronica;
	private String emisorElectronicoDesde;
	private String comprobantesElectronicos;
	private String afiliadoPLEDesde;
	private List<String> padrones;

	public void setPadrones(List<String> padrones) {
		this.padrones = padrones;
	}
	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTipoContribuyente() {
		return tipoContribuyente;
	}

	public void setTipoContribuyente(String tipoContribuyente) {
		this.tipoContribuyente = tipoContribuyente;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public String getFechaInicioActividades() {
		return fechaInicioActividades;
	}

	public void setFechaInicioActividades(String fechaInicioActividades) {
		this.fechaInicioActividades = fechaInicioActividades;
	}

	public String getEstadoContribuyente() {
		return estadoContribuyente;
	}

	public void setEstadoContribuyente(String estadoContribuyente) {
		this.estadoContribuyente = estadoContribuyente;
	}

	public String getCondicionContribuyente() {
		return condicionContribuyente;
	}

	public void setCondicionContribuyente(String condicionContribuyente) {
		this.condicionContribuyente = condicionContribuyente;
	}

	public String getDomicilioFiscal() {
		return domicilioFiscal;
	}

	public void setDomicilioFiscal(String domicilioFiscal) {
		this.domicilioFiscal = domicilioFiscal;
	}

	public String getSistemaEmisionComprobante() {
		return sistemaEmisionComprobante;
	}

	public void setSistemaEmisionComprobante(String sistemaEmisionComprobante) {
		this.sistemaEmisionComprobante = sistemaEmisionComprobante;
	}

	public String getActividadComercioExterior() {
		return actividadComercioExterior;
	}

	public void setActividadComercioExterior(String actividadComercioExterior) {
		this.actividadComercioExterior = actividadComercioExterior;
	}

	public String getSistemaContabilidiad() {
		return sistemaContabilidiad;
	}

	public void setSistemaContabilidiad(String sistemaContabilidiad) {
		this.sistemaContabilidiad = sistemaContabilidiad;
	}

	public List<String> getActividadesEconomicas() {
		return actividadesEconomicas;
	}

	public void setActividadesEconomicas(List<String> actividadesEconomicas) {
		this.actividadesEconomicas = actividadesEconomicas;
	}

	public List<String> getComprobantesPago() {
		return comprobantesPago;
	}

	public void setComprobantesPago(List<String> comprobantesPago) {
		this.comprobantesPago = comprobantesPago;
	}

	public List<String> getPadrones() {
		return padrones;
	}

	public String getComprobantesElectronicos() {
		return comprobantesElectronicos;
	}

	public List<String> getSistemaEmisionElectronica() {
		return sistemaEmisionElectronica;
	}

	public void setSistemaEmisionElectronica(List<String> sistemaEmisionElectronica) {
		this.sistemaEmisionElectronica = sistemaEmisionElectronica;
	}

	public String getEmisorElectronicoDesde() {
		return emisorElectronicoDesde;
	}

	public void setEmisorElectronicoDesde(String emisorElectronicoDesde) {
		this.emisorElectronicoDesde = emisorElectronicoDesde;
	}

	public void setComprobantesElectronicos(String comprobantesElectronicos) {
		this.comprobantesElectronicos = comprobantesElectronicos;
	}

	public String getAfiliadoPLEDesde() {
		return afiliadoPLEDesde;
	}

	public void setAfiliadoPLEDesde(String afiliadoPLEDesde) {
		this.afiliadoPLEDesde = afiliadoPLEDesde;
	}

	@Override
	public String toString() {
		return "DatosRUC [ruc=" + ruc
				+ ", tipoContribuyente=" + tipoContribuyente + ", nombreComercial=" + nombreComercial
				+ ", fechaInscripcion=" + fechaInscripcion + ", fechaInicioActividades=" + fechaInicioActividades
				+ ", estadoContribuyente=" + estadoContribuyente + ", condicionContribuyente=" + condicionContribuyente
				+ ", domicilioFiscal=" + domicilioFiscal + ", sistemaEmisionComprobante=" + sistemaEmisionComprobante
				+ ", actividadComercioExterior=" + actividadComercioExterior + ", sistemaContabilidiad="
				+ sistemaContabilidiad + ", actividadesEconomicas=" + actividadesEconomicas + ", comprobantesPago="
				+ comprobantesPago + ", sistemaEmisionElectronica=" + sistemaEmisionElectronica
				+ ", emisorElectronicoDesde=" + emisorElectronicoDesde + ", comprobantesElectronicos="
				+ comprobantesElectronicos + ", afiliadoPLEDesde=" + afiliadoPLEDesde + ", padrones=" + padrones + "]";
	}
}
