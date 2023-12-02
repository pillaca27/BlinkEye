package net.royal.spring.seguridad.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.util.UString;

public class DtoAutorizacion {
	private String aplicaciongrupo;
	
	private String aplicacioncodigo;
	private String aplicacionNombre;	
	private String aplicacionImagen;
	private Integer aplicacionOrden;
	
	private String grupo;
	private String grupoNombre;
	private String grupoImagen;
	private Integer grupoOrden;
	
	
	private String concepto;
	private Integer conceptoOrden;
	private String conceptoNombre;
	private String webpage;
	private String webaction;
	private String imagen;
	private String webflag;
	private String tipoDeAcceso;
	private Integer cantidad;
	private String flgAgregar;
	private String flgModificar;
	private String flgBorrar;
	private String flgAprobar;
	private String parametrotexto;
	private BigDecimal parametrovalor;
	private String rutaayuda;
	
	
	public BigDecimal getParametrovalor() {
		return parametrovalor;
	}

	public void setParametrovalor(BigDecimal parametrovalor) {
		this.parametrovalor = parametrovalor;
	}

	public String getParametrotexto() {
		return parametrotexto;
	}

	public void setParametrotexto(String parametrotexto) {
		this.parametrotexto = parametrotexto;
	}

	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getAplicacionNombre() {
		return aplicacionNombre;
	}

	public void setAplicacionNombre(String aplicacionNombre) {
		this.aplicacionNombre = aplicacionNombre;
	}

	public String getGrupoNombre() {
		return grupoNombre;
	}

	public void setGrupoNombre(String grupoNombre) {
		this.grupoNombre = grupoNombre;
	}

	public String getGrupoImagen() {
		return grupoImagen;
	}

	public void setGrupoImagen(String grupoImagen) {
		this.grupoImagen = grupoImagen;
	}

	public String getConceptoNombre() {
		return conceptoNombre;
	}

	public void setConceptoNombre(String conceptoNombre) {
		this.conceptoNombre = conceptoNombre;
	}

	public String getWebpage() {
		return webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	public String getWebaction() {
		return webaction;
	}

	public void setWebaction(String webaction) {
		this.webaction = webaction;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getWebflag() {
		return webflag;
	}

	public void setWebflag(String webflag) {
		this.webflag = webflag;
	}

	public String getTipoDeAcceso() {
		if (UString.esNuloVacio(tipoDeAcceso))
			tipoDeAcceso="N";
		return tipoDeAcceso;
	}

	public void setTipoDeAcceso(String tipoDeAcceso) {
		this.tipoDeAcceso = tipoDeAcceso;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getFlgAgregar() {
		if (UString.esNuloVacio(flgAgregar))
			flgAgregar="N";
		return flgAgregar;
	}

	public void setFlgAgregar(String flgAgregar) {
		this.flgAgregar = flgAgregar;
	}

	public String getFlgModificar() {
		if (UString.esNuloVacio(flgModificar))
			flgModificar="N";
		return flgModificar;
	}

	public void setFlgModificar(String flgModificar) {
		this.flgModificar = flgModificar;
	}

	public String getFlgBorrar() {
		if (UString.esNuloVacio(flgBorrar))
			flgBorrar="N";
		return flgBorrar;
	}

	public void setFlgBorrar(String flgBorrar) {
		this.flgBorrar = flgBorrar;
	}

	public String getFlgAprobar() {
		if (UString.esNuloVacio(flgAprobar))
			flgAprobar="N";
		return flgAprobar;
	}

	public void setFlgAprobar(String flgAprobar) {
		this.flgAprobar = flgAprobar;
	}

	public String getAplicaciongrupo() {
		return aplicaciongrupo;
	}

	public void setAplicaciongrupo(String aplicaciongrupo) {
		this.aplicaciongrupo = aplicaciongrupo;
	}

	public String getAplicacionImagen() {
		return aplicacionImagen;
	}

	public void setAplicacionImagen(String aplicacionImagen) {
		this.aplicacionImagen = aplicacionImagen;
	}

	public Integer getGrupoOrden() {
		return grupoOrden;
	}

	public void setGrupoOrden(Integer grupoOrden) {
		this.grupoOrden = grupoOrden;
	}

	public Integer getConceptoOrden() {
		return conceptoOrden;
	}

	public void setConceptoOrden(Integer conceptoOrden) {
		this.conceptoOrden = conceptoOrden;
	}

	public Integer getAplicacionOrden() {
		return aplicacionOrden;
	}

	public void setAplicacionOrden(Integer aplicacionOrden) {
		this.aplicacionOrden = aplicacionOrden;
	}

	public Integer getOrden() {
		return aplicacionOrden.intValue();
	}

	public void setOrden(Integer aplicacionOrden) {
		this.aplicacionOrden = aplicacionOrden;
	}

	public String getRutaayuda() {
		return rutaayuda;
	}

	public void setRutaayuda(String rutaayuda) {
		this.rutaayuda = rutaayuda;
	}
	
}


