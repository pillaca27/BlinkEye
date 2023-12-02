package net.royal.spring.core.dominio.filtro;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunPersonamastclis001 {
	private BigDecimal persona;
	private String busqueda;

	private String documento;
	private String documentofiscal;
	private String documentoidentidad;
	private String estado;
	private String localidad;
	private String actividadeconomica;

	private String centrocosto;

	private String evalacionCompania;
	private Integer evaluacionId;

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getActividadeconomica() {
		return actividadeconomica;
	}

	public void setActividadeconomica(String actividadeconomica) {
		this.actividadeconomica = actividadeconomica;
	}

	private DominioPaginacion paginacion;

	public BigDecimal getPersona() {
		return persona;
	}

	public void setPersona(BigDecimal persona) {
		this.persona = persona;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDocumentofiscal() {
		return documentofiscal;
	}

	public void setDocumentofiscal(String documentofiscal) {
		this.documentofiscal = documentofiscal;
	}

	public String getDocumentoidentidad() {
		return documentoidentidad;
	}

	public void setDocumentoidentidad(String documentoidentidad) {
		this.documentoidentidad = documentoidentidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public String getCentrocosto() {
		return centrocosto;
	}

	public void setCentrocosto(String centrocosto) {
		this.centrocosto = centrocosto;
	}

	public String getEvalacionCompania() {
		return evalacionCompania;
	}

	public void setEvalacionCompania(String evalacionCompania) {
		this.evalacionCompania = evalacionCompania;
	}

	public Integer getEvaluacionId() {
		return evaluacionId;
	}

	public void setEvaluacionId(Integer evaluacionId) {
		this.evaluacionId = evaluacionId;
	}

}
