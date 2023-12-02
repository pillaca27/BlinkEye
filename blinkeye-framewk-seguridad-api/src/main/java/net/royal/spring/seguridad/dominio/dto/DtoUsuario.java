package net.royal.spring.seguridad.dominio.dto;

import java.util.Date;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;

public class DtoUsuario {
	private String usuario;
	private String claveOld;
	private String claveNueva;
	private String claveRep;
	private Date fechapassword;
	private String situacion;
	private Integer li_retorno;
	private Integer il_vigencia;
	private List<DominioMensajeUsuario> listaErrores;
	private String informacionConexion;
	private String mostrarPantallaCambioClave;
	private String puedePasar;
	private String aplicacioncodigo;
	private String ultimoperiodocontable;
	private String claveOriginal;
	private Boolean ib_cambiarClave;
	private String claveDefault;
	private String compania;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClaveOld() {
		return claveOld;
	}

	public void setClaveOld(String claveOld) {
		this.claveOld = claveOld;
	}

	public String getClaveNueva() {
		return claveNueva;
	}

	public void setClaveNueva(String claveNueva) {
		this.claveNueva = claveNueva;
	}

	public String getClaveRep() {
		return claveRep;
	}

	public void setClaveRep(String claveRep) {
		this.claveRep = claveRep;
	}

	public Date getFechapassword() {
		return fechapassword;
	}

	public void setFechapassword(Date fechapassword) {
		this.fechapassword = fechapassword;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public Integer getLi_retorno() {
		return li_retorno;
	}

	public void setLi_retorno(Integer li_retorno) {
		this.li_retorno = li_retorno;
	}

	public Integer getIl_vigencia() {
		return il_vigencia;
	}

	public void setIl_vigencia(Integer il_vigencia) {
		this.il_vigencia = il_vigencia;
	}

	public List<DominioMensajeUsuario> getListaErrores() {
		return listaErrores;
	}

	public void setListaErrores(List<DominioMensajeUsuario> listaErrores) {
		this.listaErrores = listaErrores;
	}

	public String getInformacionConexion() {
		return informacionConexion;
	}

	public void setInformacionConexion(String informacionConexion) {
		this.informacionConexion = informacionConexion;
	}

	public String getMostrarPantallaCambioClave() {
		return mostrarPantallaCambioClave;
	}

	public void setMostrarPantallaCambioClave(String mostrarPantallaCambioClave) {
		this.mostrarPantallaCambioClave = mostrarPantallaCambioClave;
	}

	public String getPuedePasar() {
		return puedePasar;
	}

	public void setPuedePasar(String puedePasar) {
		this.puedePasar = puedePasar;
	}

	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}

	public String getUltimoperiodocontable() {
		return ultimoperiodocontable;
	}

	public void setUltimoperiodocontable(String ultimoperiodocontable) {
		this.ultimoperiodocontable = ultimoperiodocontable;
	}

	public String getClaveOriginal() {
		return claveOriginal;
	}

	public void setClaveOriginal(String claveOriginal) {
		this.claveOriginal = claveOriginal;
	}

	public Boolean getIb_cambiarClave() {
		return ib_cambiarClave;
	}

	public void setIb_cambiarClave(Boolean ib_cambiarClave) {
		this.ib_cambiarClave = ib_cambiarClave;
	}

	public String getClaveDefault() {
		return claveDefault;
	}

	public void setClaveDefault(String claveDefault) {
		this.claveDefault = claveDefault;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}
}
