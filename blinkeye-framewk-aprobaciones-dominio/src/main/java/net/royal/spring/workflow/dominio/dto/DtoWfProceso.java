package net.royal.spring.workflow.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoWfProceso extends DominioTransaccion{

	private String uuid;
	private String auxEsNuevaVersion;
	private String proceso;
	private String descripcion;
	private String estado;
	private String aplicacion;
	private String aplicacionAux;
	private String componenteweb;
	private String api;
	private String spver;
	private Integer origen;
	private String segmentocodigotabla;
	private String nivelestadoidinicial;

	private boolean flagPlanificacionGenerar;
	private String flgPlanificacionGenerarString;
	private String administradorNombre;
	private Integer administradorId;

	private Integer version;

	private List<DtoWfVariable> variables;
	private List<DtoWfRol> roles;
	private List<DtoWfEstado> estados;
	private List<DtoWfTipoDocumento> tipodocumentos;
	private List<DtoWfFlujo> flujos;

	private boolean flagComunicacionAlerta;
	private String flagComunicacionAlertaString;

	private boolean flagCorreoNiveles;
	private String flagCorreoNivelesString;

	private String tienePlantillaAprobar;
	private String tienePlantillaRechazar;
	private String tienePlantillaDevolver;
	private String tienePlantillaGuardar;
	private String tienePlantillaSeguimiento;
	private String tienePlantillaAlerta;

	private Date desde;
	private Date hasta;

	public DtoWfProceso() {
		this.variables = new ArrayList<DtoWfVariable>();
		this.estados = new ArrayList<DtoWfEstado>();
		this.tipodocumentos = new ArrayList<DtoWfTipoDocumento>();
		this.flujos = new ArrayList<DtoWfFlujo>();
		this.roles = new ArrayList<DtoWfRol>();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public List<DtoWfVariable> getVariables() {
		return variables;
	}

	public void setVariables(List<DtoWfVariable> variables) {
		this.variables = variables;
	}

	public List<DtoWfEstado> getEstados() {
		return estados;
	}

	public void setEstados(List<DtoWfEstado> estados) {
		this.estados = estados;
	}

	public List<DtoWfTipoDocumento> getTipodocumentos() {
		return tipodocumentos;
	}

	public void setTipodocumentos(List<DtoWfTipoDocumento> tipodocumentos) {
		this.tipodocumentos = tipodocumentos;
	}

	public List<DtoWfFlujo> getFlujos() {
		return flujos;
	}

	public void setFlujos(List<DtoWfFlujo> flujos) {
		this.flujos = flujos;
	}

	public String getAplicacionAux() {
		return aplicacionAux;
	}

	public void setAplicacionAux(String aplicacionAux) {
		this.aplicacionAux = aplicacionAux;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getComponenteweb() {
		return componenteweb;
	}

	public void setComponenteweb(String componenteweb) {
		this.componenteweb = componenteweb;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getSpver() {
		return spver;
	}

	public void setSpver(String spver) {
		this.spver = spver;
	}

	public Integer getOrigen() {
		return origen;
	}

	public void setOrigen(Integer origen) {
		this.origen = origen;
	}

	public String getSegmentocodigotabla() {
		return segmentocodigotabla;
	}

	public void setSegmentocodigotabla(String segmentocodigotabla) {
		this.segmentocodigotabla = segmentocodigotabla;
	}

	public boolean isFlagPlanificacionGenerar() {
		return flagPlanificacionGenerar;
	}

	public void setFlagPlanificacionGenerar(boolean flagPlanificacionGenerar) {
		this.flagPlanificacionGenerar = flagPlanificacionGenerar;
	}

	public String getFlgPlanificacionGenerarString() {
		return flgPlanificacionGenerarString;
	}

	public void setFlgPlanificacionGenerarString(String flgPlanificacionGenerarString) {
		this.flgPlanificacionGenerarString = flgPlanificacionGenerarString;
	}

	public String getAdministradorNombre() {
		return administradorNombre;
	}

	public void setAdministradorNombre(String administradorNombre) {
		this.administradorNombre = administradorNombre;
	}

	public Integer getAdministradorId() {
		return administradorId;
	}

	public void setAdministradorId(Integer administradorId) {
		this.administradorId = administradorId;
	}

	public String getNivelestadoidinicial() {
		return nivelestadoidinicial;
	}

	public void setNivelestadoidinicial(String nivelestadoidinicial) {
		this.nivelestadoidinicial = nivelestadoidinicial;
	}

	public boolean isFlagComunicacionAlerta() {
		return flagComunicacionAlerta;
	}

	public void setFlagComunicacionAlerta(boolean flagComunicacionAlerta) {
		this.flagComunicacionAlerta = flagComunicacionAlerta;
	}

	public String getFlagComunicacionAlertaString() {
		return flagComunicacionAlertaString;
	}

	public void setFlagComunicacionAlertaString(String flagComunicacionAlertaString) {
		this.flagComunicacionAlertaString = flagComunicacionAlertaString;
	}

	public List<DtoWfRol> getRoles() {
		return roles;
	}

	public void setRoles(List<DtoWfRol> roles) {
		this.roles = roles;
	}

	public String getAuxEsNuevaVersion() {
		return auxEsNuevaVersion;
	}

	public void setAuxEsNuevaVersion(String auxEsNuevaVersion) {
		this.auxEsNuevaVersion = auxEsNuevaVersion;
	}

	public boolean isFlagCorreoNiveles() {
		return flagCorreoNiveles;
	}

	public void setFlagCorreoNiveles(boolean flagCorreoNiveles) {
		this.flagCorreoNiveles = flagCorreoNiveles;
	}

	public String getFlagCorreoNivelesString() {
		return flagCorreoNivelesString;
	}

	public void setFlagCorreoNivelesString(String flagCorreoNivelesString) {
		this.flagCorreoNivelesString = flagCorreoNivelesString;
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

	public String getTienePlantillaGuardar() {
		return tienePlantillaGuardar;
	}

	public void setTienePlantillaGuardar(String tienePlantillaGuardar) {
		this.tienePlantillaGuardar = tienePlantillaGuardar;
	}

	public String getTienePlantillaSeguimiento() {
		return tienePlantillaSeguimiento;
	}

	public void setTienePlantillaSeguimiento(String tienePlantillaSeguimiento) {
		this.tienePlantillaSeguimiento = tienePlantillaSeguimiento;
	}

	public String getTienePlantillaAlerta() {
		return tienePlantillaAlerta;
	}

	public void setTienePlantillaAlerta(String tienePlantillaAlerta) {
		this.tienePlantillaAlerta = tienePlantillaAlerta;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

}
