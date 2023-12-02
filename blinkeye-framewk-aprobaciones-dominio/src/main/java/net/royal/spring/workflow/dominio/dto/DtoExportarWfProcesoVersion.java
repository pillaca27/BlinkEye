package net.royal.spring.workflow.dominio.dto;

public class DtoExportarWfProcesoVersion {

	private String procesoId;
	private Integer versionId;
	private String nombre;

	private String webComponente;
	private String api;
	private String spVer;
	private Integer origenDatosId;
	private String segmentoCodigoTabla;
	private String flgPlanificacionGenerar;
	private Integer administradorId;
	private String nivelEstadoIdInicial;
	private String comunicacionFlgAlerta;
	private String flgCorreoNiveles;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getProcesoId() {
		return procesoId;
	}

	public void setProcesoId(String procesoId) {
		this.procesoId = procesoId;
	}

	public String getWebComponente() {
		return webComponente;
	}

	public void setWebComponente(String webComponente) {
		this.webComponente = webComponente;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getSpVer() {
		return spVer;
	}

	public void setSpVer(String spVer) {
		this.spVer = spVer;
	}

	public Integer getOrigenDatosId() {
		return origenDatosId;
	}

	public void setOrigenDatosId(Integer origenDatosId) {
		this.origenDatosId = origenDatosId;
	}

	public String getSegmentoCodigoTabla() {
		return segmentoCodigoTabla;
	}

	public void setSegmentoCodigoTabla(String segmentoCodigoTabla) {
		this.segmentoCodigoTabla = segmentoCodigoTabla;
	}

	public String getFlgPlanificacionGenerar() {
		return flgPlanificacionGenerar;
	}

	public void setFlgPlanificacionGenerar(String flgPlanificacionGenerar) {
		this.flgPlanificacionGenerar = flgPlanificacionGenerar;
	}

	public Integer getAdministradorId() {
		return administradorId;
	}

	public void setAdministradorId(Integer administradorId) {
		this.administradorId = administradorId;
	}

	public String getNivelEstadoIdInicial() {
		return nivelEstadoIdInicial;
	}

	public void setNivelEstadoIdInicial(String nivelEstadoIdInicial) {
		this.nivelEstadoIdInicial = nivelEstadoIdInicial;
	}

	public String getComunicacionFlgAlerta() {
		return comunicacionFlgAlerta;
	}

	public void setComunicacionFlgAlerta(String comunicacionFlgAlerta) {
		this.comunicacionFlgAlerta = comunicacionFlgAlerta;
	}

	public String getFlgCorreoNiveles() {
		return flgCorreoNiveles;
	}

	public void setFlgCorreoNiveles(String flgCorreoNiveles) {
		this.flgCorreoNiveles = flgCorreoNiveles;
	}

}
