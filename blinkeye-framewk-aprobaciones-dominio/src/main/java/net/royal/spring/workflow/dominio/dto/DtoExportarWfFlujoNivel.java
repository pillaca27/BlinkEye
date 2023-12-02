package net.royal.spring.workflow.dominio.dto;

public class DtoExportarWfFlujoNivel {

	private String procesoId;
	private Integer versionId;
	private Integer flujoId;
	private Integer nivelId;
	private String tipoAprobadorId;
	private String estadoId;
	private String flgCorreoJefe;

	private String flgCorreoSolicitante;
	private String correoOtros;
	private String spValidar;
	private String spRechazar;
	private String spAprobar;

	private String spDevolver;
	private String api;
	private String webComponente;
	private Integer origenDatosId;
	private String condicionAprobacionId;

	private String flgBotonAprobar;
	private String flgBotonRechazar;
	private String flgBotonDevolver;
	private String flgNotificar;
	private String nombre;

	private String duracionTipo;
	private Integer duracionCantidad;
	private String flgPlanificacionVer;
	private String flgPlanificacionEditable;
	private String flgPlanificacionValidar;

	private String planificacionTag;
	private String documentoFlgBotonNuevo;
	private String documentoFlgColumnaGrupo;
	private String flgAprobarComentarioDetallado;
	private String flgRechazarComentarioDetallado;

	private String flgDevolverComentarioDetallado;

	private String flgCorreoPersonaReferencia;

	private String flgCorreoTransaccion;
	
	private String flgCorreoPersona;
	
	private String flgAprobarComentario;
	
	private String flgCorreoAdjuntarDocumentos;
	
	private String flgBotonSaltar;

	public Integer getFlujoId() {
		return flujoId;
	}

	public void setFlujoId(Integer flujoId) {
		this.flujoId = flujoId;
	}

	public Integer getNivelId() {
		return nivelId;
	}

	public void setNivelId(Integer nivelId) {
		this.nivelId = nivelId;
	}

	public String getTipoAprobadorId() {
		return tipoAprobadorId;
	}

	public void setTipoAprobadorId(String tipoAprobadorId) {
		this.tipoAprobadorId = tipoAprobadorId;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public String getFlgCorreoJefe() {
		return flgCorreoJefe;
	}

	public void setFlgCorreoJefe(String flgCorreoJefe) {
		this.flgCorreoJefe = flgCorreoJefe;
	}

	public String getFlgCorreoSolicitante() {
		return flgCorreoSolicitante;
	}

	public void setFlgCorreoSolicitante(String flgCorreoSolicitante) {
		this.flgCorreoSolicitante = flgCorreoSolicitante;
	}

	public String getCorreoOtros() {
		return correoOtros;
	}

	public void setCorreoOtros(String correoOtros) {
		this.correoOtros = correoOtros;
	}

	public String getSpValidar() {
		return spValidar;
	}

	public void setSpValidar(String spValidar) {
		this.spValidar = spValidar;
	}

	public String getSpRechazar() {
		return spRechazar;
	}

	public void setSpRechazar(String spRechazar) {
		this.spRechazar = spRechazar;
	}

	public String getSpDevolver() {
		return spDevolver;
	}

	public void setSpDevolver(String spDevolver) {
		this.spDevolver = spDevolver;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getWebComponente() {
		return webComponente;
	}

	public void setWebComponente(String webComponente) {
		this.webComponente = webComponente;
	}

	public Integer getOrigenDatosId() {
		return origenDatosId;
	}

	public void setOrigenDatosId(Integer origenDatosId) {
		this.origenDatosId = origenDatosId;
	}

	public String getCondicionAprobacionId() {
		return condicionAprobacionId;
	}

	public void setCondicionAprobacionId(String condicionAprobacionId) {
		this.condicionAprobacionId = condicionAprobacionId;
	}

	public String getFlgBotonAprobar() {
		return flgBotonAprobar;
	}

	public void setFlgBotonAprobar(String flgBotonAprobar) {
		this.flgBotonAprobar = flgBotonAprobar;
	}

	public String getFlgBotonRechazar() {
		return flgBotonRechazar;
	}

	public void setFlgBotonRechazar(String flgBotonRechazar) {
		this.flgBotonRechazar = flgBotonRechazar;
	}

	public String getFlgBotonDevolver() {
		return flgBotonDevolver;
	}

	public void setFlgBotonDevolver(String flgBotonDevolver) {
		this.flgBotonDevolver = flgBotonDevolver;
	}

	public String getFlgNotificar() {
		return flgNotificar;
	}

	public void setFlgNotificar(String flgNotificar) {
		this.flgNotificar = flgNotificar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDuracionTipo() {
		return duracionTipo;
	}

	public void setDuracionTipo(String duracionTipo) {
		this.duracionTipo = duracionTipo;
	}

	public Integer getDuracionCantidad() {
		return duracionCantidad;
	}

	public void setDuracionCantidad(Integer duracionCantidad) {
		this.duracionCantidad = duracionCantidad;
	}

	public String getFlgPlanificacionVer() {
		return flgPlanificacionVer;
	}

	public void setFlgPlanificacionVer(String flgPlanificacionVer) {
		this.flgPlanificacionVer = flgPlanificacionVer;
	}

	public String getFlgPlanificacionEditable() {
		return flgPlanificacionEditable;
	}

	public void setFlgPlanificacionEditable(String flgPlanificacionEditable) {
		this.flgPlanificacionEditable = flgPlanificacionEditable;
	}

	public String getFlgPlanificacionValidar() {
		return flgPlanificacionValidar;
	}

	public void setFlgPlanificacionValidar(String flgPlanificacionValidar) {
		this.flgPlanificacionValidar = flgPlanificacionValidar;
	}

	public String getPlanificacionTag() {
		return planificacionTag;
	}

	public void setPlanificacionTag(String planificacionTag) {
		this.planificacionTag = planificacionTag;
	}

	public String getDocumentoFlgBotonNuevo() {
		return documentoFlgBotonNuevo;
	}

	public void setDocumentoFlgBotonNuevo(String documentoFlgBotonNuevo) {
		this.documentoFlgBotonNuevo = documentoFlgBotonNuevo;
	}

	public String getDocumentoFlgColumnaGrupo() {
		return documentoFlgColumnaGrupo;
	}

	public void setDocumentoFlgColumnaGrupo(String documentoFlgColumnaGrupo) {
		this.documentoFlgColumnaGrupo = documentoFlgColumnaGrupo;
	}

	public String getFlgAprobarComentarioDetallado() {
		return flgAprobarComentarioDetallado;
	}

	public void setFlgAprobarComentarioDetallado(String flgAprobarComentarioDetallado) {
		this.flgAprobarComentarioDetallado = flgAprobarComentarioDetallado;
	}

	public String getFlgRechazarComentarioDetallado() {
		return flgRechazarComentarioDetallado;
	}

	public void setFlgRechazarComentarioDetallado(String flgRechazarComentarioDetallado) {
		this.flgRechazarComentarioDetallado = flgRechazarComentarioDetallado;
	}

	public String getFlgDevolverComentarioDetallado() {
		return flgDevolverComentarioDetallado;
	}

	public void setFlgDevolverComentarioDetallado(String flgDevolverComentarioDetallado) {
		this.flgDevolverComentarioDetallado = flgDevolverComentarioDetallado;
	}

	public String getSpAprobar() {
		return spAprobar;
	}

	public void setSpAprobar(String spAprobar) {
		this.spAprobar = spAprobar;
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

	public String getFlgCorreoPersonaReferencia() {
		return flgCorreoPersonaReferencia;
	}

	public void setFlgCorreoPersonaReferencia(String flgCorreoPersonaReferencia) {
		this.flgCorreoPersonaReferencia = flgCorreoPersonaReferencia;
	}

	public String getFlgCorreoTransaccion() {
		return flgCorreoTransaccion;
	}

	public void setFlgCorreoTransaccion(String flgCorreoTransaccion) {
		this.flgCorreoTransaccion = flgCorreoTransaccion;
	}

	public String getFlgCorreoPersona() {
		return flgCorreoPersona;
	}

	public void setFlgCorreoPersona(String flgCorreoPersona) {
		this.flgCorreoPersona = flgCorreoPersona;
	}

	public String getFlgAprobarComentario() {
		return flgAprobarComentario;
	}

	public void setFlgAprobarComentario(String flgAprobarComentario) {
		this.flgAprobarComentario = flgAprobarComentario;
	}

	public String getFlgCorreoAdjuntarDocumentos() {
		return flgCorreoAdjuntarDocumentos;
	}

	public void setFlgCorreoAdjuntarDocumentos(String flgCorreoAdjuntarDocumentos) {
		this.flgCorreoAdjuntarDocumentos = flgCorreoAdjuntarDocumentos;
	}

	public String getFlgBotonSaltar() {
		return flgBotonSaltar;
	}

	public void setFlgBotonSaltar(String flgBotonSaltar) {
		this.flgBotonSaltar = flgBotonSaltar;
	}

}
