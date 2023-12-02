package net.royal.spring.workflow.dominio.dto;

import java.util.ArrayList;
import java.util.List;

public class DtoWfFlujoNivel {

	private String proceso;
	private Integer flujo;
	private Integer nivel;
	private Integer origenDatos;
	private String estado;
	private boolean correojefe;
	private boolean correosolicitante;
	private boolean btnAprobar;
	private boolean btnRechazar;
	private boolean btnDevolver;
	private boolean btnNotificar;

	private boolean auxDocumentoFlgColumnaNuevo;
	private boolean auxDocumentoFlgColumnaGrupo;

	private String correootros;
	private String spvalidar;
	private String sprechazar;
	private String spaprobar;
	private String spdevolver;
	private String documentoFlgColumnaNuevo;
	private String documentoFlgColumnaGrupo;
	private String api;
	private String componenteweb;
	private String tipoaprobador;
	private String condicionaprobacion;
	private List<DtoWfFlujoNivelAprobador> aprobadores;
	private List<DtoWfFlujoNivelAccion> acciones;
	private List<DtoWfFlujoNivelDocumento> documentos;

	private String jefeAux;
	private String soliAux;

	private String btnAprobarAux;
	private String btnDevolverAux;
	private String btnRechazarAux;
	private String btnNotificarAux;

	private String nombre;
	private String duraciontipo;
	private Integer duracioncantidad;
	private String flagPlanificacionVer;
	private String flagPlanificacionEditar;
	private boolean auxFlagPlanificacionVer;
	private boolean auxFlagPlanificacionEditar;
	private String planificacionTag;
	private String flgPlanificacionValidar;
	private boolean auxFlgPlanificacionValidar;
	
	private String flgComentarioDetalladoAprobar;
	private String flgComentarioDetalladoRechazar;
	private String flgComentarioDetalladoDevolver;
	
	private boolean auxFlgComentarioDetalladoAprobar;
	private boolean auxFlgComentarioDetalladoRechazar;
	private boolean auxFlgComentarioDetalladoDevolver;
	
	private boolean auxFlgCorreoPersonaReferencia;
	private String flgCorreoPersonaReferencia;
	
	private boolean auxFlgCorreoTransaccion;
	private String flgCorreoTransaccion;
	
	private boolean auxFlgCorreoPersona;
	private String flgCorreoPersona;
	
	private boolean auxFlgAprobarComentario;
	private String flgAprobarComentario;
	
	private boolean auxFlgCorreoAdjuntarDocumentos;
	private String flgCorreoAdjuntarDocumentos;

	private String tienePlantillaAprobar;
	private String tienePlantillaRechazar;
	private String tienePlantillaDevolver;
	private String tienePlantillaSeguimiento;
	
	private boolean auxFlgBotonSaltar;
	private String flgBotonSaltar;

	public DtoWfFlujoNivel() {
		this.aprobadores = new ArrayList<DtoWfFlujoNivelAprobador>();
		this.documentos = new ArrayList<DtoWfFlujoNivelDocumento>();
		this.acciones = new ArrayList<DtoWfFlujoNivelAccion>();
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public Integer getFlujo() {
		return flujo;
	}

	public void setFlujo(Integer flujo) {
		this.flujo = flujo;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public boolean isCorreojefe() {
		return correojefe;
	}

	public void setCorreojefe(boolean correojefe) {
		this.correojefe = correojefe;
	}

	public boolean isCorreosolicitante() {
		return correosolicitante;
	}

	public void setCorreosolicitante(boolean correosolicitante) {
		this.correosolicitante = correosolicitante;
	}

	public String getCorreootros() {
		return correootros;
	}

	public void setCorreootros(String correootros) {
		this.correootros = correootros;
	}

	public List<DtoWfFlujoNivelAprobador> getAprobadores() {
		return aprobadores;
	}

	public void setAprobadores(List<DtoWfFlujoNivelAprobador> aprobadores) {
		this.aprobadores = aprobadores;
	}

	public List<DtoWfFlujoNivelDocumento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DtoWfFlujoNivelDocumento> documentos) {
		this.documentos = documentos;
	}

	public String getJefeAux() {
		return jefeAux;
	}

	public void setJefeAux(String jefeAux) {
		this.jefeAux = jefeAux;
	}

	public String getSoliAux() {
		return soliAux;
	}

	public void setSoliAux(String soliAux) {
		this.soliAux = soliAux;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSpvalidar() {
		return spvalidar;
	}

	public void setSpvalidar(String spvalidar) {
		this.spvalidar = spvalidar;
	}

	public String getSprechazar() {
		return sprechazar;
	}

	public void setSprechazar(String sprechazar) {
		this.sprechazar = sprechazar;
	}

	public String getSpaprobar() {
		return spaprobar;
	}

	public void setSpaprobar(String spaprobar) {
		this.spaprobar = spaprobar;
	}

	public String getSpdevolver() {
		return spdevolver;
	}

	public void setSpdevolver(String spdevolver) {
		this.spdevolver = spdevolver;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getComponenteweb() {
		return componenteweb;
	}

	public void setComponenteweb(String componenteweb) {
		this.componenteweb = componenteweb;
	}

	public List<DtoWfFlujoNivelAccion> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<DtoWfFlujoNivelAccion> acciones) {
		this.acciones = acciones;
	}

	public Integer getOrigenDatos() {
		return origenDatos;
	}

	public void setOrigenDatos(Integer origenDatos) {
		this.origenDatos = origenDatos;
	}

	public String getTipoaprobador() {
		return tipoaprobador;
	}

	public void setTipoaprobador(String tipoaprobador) {
		this.tipoaprobador = tipoaprobador;
	}

	public String getCondicionaprobacion() {
		return condicionaprobacion;
	}

	public void setCondicionaprobacion(String condicionaprobacion) {
		this.condicionaprobacion = condicionaprobacion;
	}

	public boolean isBtnAprobar() {
		return btnAprobar;
	}

	public void setBtnAprobar(boolean btnAprobar) {
		this.btnAprobar = btnAprobar;
	}

	public boolean isBtnRechazar() {
		return btnRechazar;
	}

	public void setBtnRechazar(boolean btnRechazar) {
		this.btnRechazar = btnRechazar;
	}

	public boolean isBtnDevolver() {
		return btnDevolver;
	}

	public void setBtnDevolver(boolean btnDevolver) {
		this.btnDevolver = btnDevolver;
	}

	public boolean isBtnNotificar() {
		return btnNotificar;
	}

	public void setBtnNotificar(boolean btnNotificar) {
		this.btnNotificar = btnNotificar;
	}

	public String getBtnAprobarAux() {
		return btnAprobarAux;
	}

	public void setBtnAprobarAux(String btnAprobarAux) {
		this.btnAprobarAux = btnAprobarAux;
	}

	public String getBtnDevolverAux() {
		return btnDevolverAux;
	}

	public void setBtnDevolverAux(String btnDevolverAux) {
		this.btnDevolverAux = btnDevolverAux;
	}

	public String getBtnRechazarAux() {
		return btnRechazarAux;
	}

	public void setBtnRechazarAux(String btnRechazarAux) {
		this.btnRechazarAux = btnRechazarAux;
	}

	public String getBtnNotificarAux() {
		return btnNotificarAux;
	}

	public void setBtnNotificarAux(String btnNotificarAux) {
		this.btnNotificarAux = btnNotificarAux;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDuraciontipo() {
		return duraciontipo;
	}

	public void setDuraciontipo(String duraciontipo) {
		this.duraciontipo = duraciontipo;
	}

	public Integer getDuracioncantidad() {
		return duracioncantidad;
	}

	public void setDuracioncantidad(Integer duracioncantidad) {
		this.duracioncantidad = duracioncantidad;
	}

	public String getFlagPlanificacionVer() {
		return flagPlanificacionVer;
	}

	public void setFlagPlanificacionVer(String flagPlanificacionVer) {
		this.flagPlanificacionVer = flagPlanificacionVer;
	}

	public String getFlagPlanificacionEditar() {
		return flagPlanificacionEditar;
	}

	public void setFlagPlanificacionEditar(String flagPlanificacionEditar) {
		this.flagPlanificacionEditar = flagPlanificacionEditar;
	}

	public boolean isAuxFlagPlanificacionVer() {
		return auxFlagPlanificacionVer;
	}

	public void setAuxFlagPlanificacionVer(boolean auxFlagPlanificacionVer) {
		this.auxFlagPlanificacionVer = auxFlagPlanificacionVer;
	}

	public boolean isAuxFlagPlanificacionEditar() {
		return auxFlagPlanificacionEditar;
	}

	public void setAuxFlagPlanificacionEditar(boolean auxFlagPlanificacionEditar) {
		this.auxFlagPlanificacionEditar = auxFlagPlanificacionEditar;
	}

	public String getPlanificacionTag() {
		return planificacionTag;
	}

	public void setPlanificacionTag(String planificacionTag) {
		this.planificacionTag = planificacionTag;
	}

	public String getFlgPlanificacionValidar() {
		return flgPlanificacionValidar;
	}

	public void setFlgPlanificacionValidar(String flgPlanificacionValidar) {
		this.flgPlanificacionValidar = flgPlanificacionValidar;
	}

	public boolean isAuxFlgPlanificacionValidar() {
		return auxFlgPlanificacionValidar;
	}

	public void setAuxFlgPlanificacionValidar(boolean auxFlgPlanificacionValidar) {
		this.auxFlgPlanificacionValidar = auxFlgPlanificacionValidar;
	}

	public boolean isAuxDocumentoFlgColumnaNuevo() {
		return auxDocumentoFlgColumnaNuevo;
	}

	public void setAuxDocumentoFlgColumnaNuevo(boolean auxDocumentoFlgColumnaNuevo) {
		this.auxDocumentoFlgColumnaNuevo = auxDocumentoFlgColumnaNuevo;
	}

	public String getDocumentoFlgColumnaNuevo() {
		return documentoFlgColumnaNuevo;
	}

	public void setDocumentoFlgColumnaNuevo(String documentoFlgColumnaNuevo) {
		this.documentoFlgColumnaNuevo = documentoFlgColumnaNuevo;
	}

	public String getDocumentoFlgColumnaGrupo() {
		return documentoFlgColumnaGrupo;
	}

	public void setDocumentoFlgColumnaGrupo(String documentoFlgColumnaGrupo) {
		this.documentoFlgColumnaGrupo = documentoFlgColumnaGrupo;
	}

	public boolean isAuxDocumentoFlgColumnaGrupo() {
		return auxDocumentoFlgColumnaGrupo;
	}

	public void setAuxDocumentoFlgColumnaGrupo(boolean auxDocumentoFlgColumnaGrupo) {
		this.auxDocumentoFlgColumnaGrupo = auxDocumentoFlgColumnaGrupo;
	}

	public String getFlgComentarioDetalladoAprobar() {
		return flgComentarioDetalladoAprobar;
	}

	public void setFlgComentarioDetalladoAprobar(String flgComentarioDetalladoAprobar) {
		this.flgComentarioDetalladoAprobar = flgComentarioDetalladoAprobar;
	}

	public String getFlgComentarioDetalladoRechazar() {
		return flgComentarioDetalladoRechazar;
	}

	public void setFlgComentarioDetalladoRechazar(String flgComentarioDetalladoRechazar) {
		this.flgComentarioDetalladoRechazar = flgComentarioDetalladoRechazar;
	}

	public String getFlgComentarioDetalladoDevolver() {
		return flgComentarioDetalladoDevolver;
	}

	public void setFlgComentarioDetalladoDevolver(String flgComentarioDetalladoDevolver) {
		this.flgComentarioDetalladoDevolver = flgComentarioDetalladoDevolver;
	}

	public boolean isAuxFlgComentarioDetalladoAprobar() {
		return auxFlgComentarioDetalladoAprobar;
	}

	public void setAuxFlgComentarioDetalladoAprobar(boolean auxFlgComentarioDetalladoAprobar) {
		this.auxFlgComentarioDetalladoAprobar = auxFlgComentarioDetalladoAprobar;
	}

	public boolean isAuxFlgComentarioDetalladoRechazar() {
		return auxFlgComentarioDetalladoRechazar;
	}

	public void setAuxFlgComentarioDetalladoRechazar(boolean auxFlgComentarioDetalladoRechazar) {
		this.auxFlgComentarioDetalladoRechazar = auxFlgComentarioDetalladoRechazar;
	}

	public boolean isAuxFlgComentarioDetalladoDevolver() {
		return auxFlgComentarioDetalladoDevolver;
	}

	public void setAuxFlgComentarioDetalladoDevolver(boolean auxFlgComentarioDetalladoDevolver) {
		this.auxFlgComentarioDetalladoDevolver = auxFlgComentarioDetalladoDevolver;
	}

	public boolean isAuxFlgCorreoPersonaReferencia() {
		return auxFlgCorreoPersonaReferencia;
	}

	public void setAuxFlgCorreoPersonaReferencia(boolean auxFlgCorreoPersonaReferencia) {
		this.auxFlgCorreoPersonaReferencia = auxFlgCorreoPersonaReferencia;
	}

	public String getFlgCorreoPersonaReferencia() {
		return flgCorreoPersonaReferencia;
	}

	public void setFlgCorreoPersonaReferencia(String flgCorreoPersonaReferencia) {
		this.flgCorreoPersonaReferencia = flgCorreoPersonaReferencia;
	}

	public boolean isAuxFlgCorreoTransaccion() {
		return auxFlgCorreoTransaccion;
	}

	public void setAuxFlgCorreoTransaccion(boolean auxFlgCorreoTransaccion) {
		this.auxFlgCorreoTransaccion = auxFlgCorreoTransaccion;
	}

	public String getFlgCorreoTransaccion() {
		return flgCorreoTransaccion;
	}

	public void setFlgCorreoTransaccion(String flgCorreoTransaccion) {
		this.flgCorreoTransaccion = flgCorreoTransaccion;
	}

	public boolean isAuxFlgCorreoPersona() {
		return auxFlgCorreoPersona;
	}

	public void setAuxFlgCorreoPersona(boolean auxFlgCorreoPersona) {
		this.auxFlgCorreoPersona = auxFlgCorreoPersona;
	}

	public String getFlgCorreoPersona() {
		return flgCorreoPersona;
	}

	public void setFlgCorreoPersona(String flgCorreoPersona) {
		this.flgCorreoPersona = flgCorreoPersona;
	}

	public boolean isAuxFlgAprobarComentario() {
		return auxFlgAprobarComentario;
	}

	public void setAuxFlgAprobarComentario(boolean auxFlgAprobarComentario) {
		this.auxFlgAprobarComentario = auxFlgAprobarComentario;
	}

	public String getFlgAprobarComentario() {
		return flgAprobarComentario;
	}

	public void setFlgAprobarComentario(String flgAprobarComentario) {
		this.flgAprobarComentario = flgAprobarComentario;
	}

	public boolean isAuxFlgCorreoAdjuntarDocumentos() {
		return auxFlgCorreoAdjuntarDocumentos;
	}

	public void setAuxFlgCorreoAdjuntarDocumentos(boolean auxFlgCorreoAdjuntarDocumentos) {
		this.auxFlgCorreoAdjuntarDocumentos = auxFlgCorreoAdjuntarDocumentos;
	}

	public String getFlgCorreoAdjuntarDocumentos() {
		return flgCorreoAdjuntarDocumentos;
	}

	public void setFlgCorreoAdjuntarDocumentos(String flgCorreoAdjuntarDocumentos) {
		this.flgCorreoAdjuntarDocumentos = flgCorreoAdjuntarDocumentos;
	}

	public String getTienePlantillaAprobar() {
		return tienePlantillaAprobar;
	}

	public void setTienePlantillaAprobar(String tienePlantillaAprobar) {
		this.tienePlantillaAprobar = tienePlantillaAprobar;
	}

	public String getTienePlantillaRechazar() {
		return tienePlantillaRechazar;
	}

	public void setTienePlantillaRechazar(String tienePlantillaRechazar) {
		this.tienePlantillaRechazar = tienePlantillaRechazar;
	}

	public String getTienePlantillaDevolver() {
		return tienePlantillaDevolver;
	}

	public void setTienePlantillaDevolver(String tienePlantillaDevolver) {
		this.tienePlantillaDevolver = tienePlantillaDevolver;
	}

	public String getTienePlantillaSeguimiento() {
		return tienePlantillaSeguimiento;
	}

	public void setTienePlantillaSeguimiento(String tienePlantillaSeguimiento) {
		this.tienePlantillaSeguimiento = tienePlantillaSeguimiento;
	}

	public boolean isAuxFlgBotonSaltar() {
		return auxFlgBotonSaltar;
	}

	public void setAuxFlgBotonSaltar(boolean auxFlgBotonSaltar) {
		this.auxFlgBotonSaltar = auxFlgBotonSaltar;
	}

	public String getFlgBotonSaltar() {
		return flgBotonSaltar;
	}

	public void setFlgBotonSaltar(String flgBotonSaltar) {
		this.flgBotonSaltar = flgBotonSaltar;
	}
	
}
