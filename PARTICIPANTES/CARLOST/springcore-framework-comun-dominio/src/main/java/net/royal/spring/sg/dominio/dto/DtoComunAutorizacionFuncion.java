package net.royal.spring.sg.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.sg.dominio.BeanSeguridadautorizaciones;

public class DtoComunAutorizacionFuncion {
	
	//OUT
	private String seleccionar;
	private String aplicacion;
	private String grupo;
	private String concepto;
	private String usuario;
	private String grupoDescripcion;
	private String objetoAutorizar;
	private String flgAgregar;
	private String flgModificar;
	private String flgAprobar;
	private String flgBorrar;
	
	private String ultimoUsuario;
	private String ultimaFecha;
	private String usernew;
	public BigDecimal ROWNUM_ ;
	
	//INT
	private boolean flgAgregarboolean;
	private boolean flgModificarboolean;
	private boolean flgBorrabooleanr;
	
	private boolean flgAprobarboolean;
	private boolean flgVerDocsboolean;
	private boolean flgEnviarCorreoleanr;
	private boolean flgOtrosbooleanr;
	  
	private String topico;
 
	 
	public String getTopico() {
		return topico;
	}
	public void setTopico(String topico) {
		this.topico = topico;
	}
	public String getFlgAprobar() {
		return flgAprobar;
	}
	public void setFlgAprobar(String flgAprobar) {
		this.flgAprobar = flgAprobar;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public boolean isFlgAgregarboolean() {
		return flgAgregarboolean;
	}
	public void setFlgAgregarboolean(boolean flgAgregarboolean) {
		this.flgAgregarboolean = flgAgregarboolean;
	}
	public boolean isFlgModificarboolean() {
		return flgModificarboolean;
	}
	public void setFlgModificarboolean(boolean flgModificarboolean) {
		this.flgModificarboolean = flgModificarboolean;
	}
	public boolean isFlgBorrabooleanr() {
		return flgBorrabooleanr;
	}
	public void setFlgBorrabooleanr(boolean flgBorrabooleanr) {
		this.flgBorrabooleanr = flgBorrabooleanr;
	}
	public String getUsernew() {
		return usernew;
	}
	public void setUsernew(String usernew) {
		this.usernew = usernew;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	public String getSeleccionar() {
		return seleccionar;
	}
	public void setSeleccionar(String seleccionar) {
		this.seleccionar = seleccionar;
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
	public String getGrupoDescripcion() {
		return grupoDescripcion;
	}
	public void setGrupoDescripcion(String grupoDescripcion) {
		this.grupoDescripcion = grupoDescripcion;
	}
	public String getObjetoAutorizar() {
		return objetoAutorizar;
	}
	public void setObjetoAutorizar(String objetoAutorizar) {
		this.objetoAutorizar = objetoAutorizar;
	}
	public String getFlgAgregar() {
		return flgAgregar;
	}
	public void setFlgAgregar(String flgAgregar) {
		this.flgAgregar = flgAgregar;
	}
	public String getFlgModificar() {
		return flgModificar;
	}
	public void setFlgModificar(String flgModificar) {
		this.flgModificar = flgModificar;
	}
	public String getFlgBorrar() {
		return flgBorrar;
	}
	public void setFlgBorrar(String flgBorrar) {
		this.flgBorrar = flgBorrar;
	}
	public String getUltimoUsuario() {
		return ultimoUsuario;
	}
	public void setUltimoUsuario(String ultimoUsuario) {
		this.ultimoUsuario = ultimoUsuario;
	}
	 
	
	public String getUltimaFecha() {
		return ultimaFecha;
	}
	public void setUltimaFecha(String ultimaFecha) {
		this.ultimaFecha = ultimaFecha;
	}
	public BeanSeguridadautorizaciones obtenerBean(SeguridadUsuarioActual usuarioActual,DtoComunAutorizacion dtoAutorizacion){
		BeanSeguridadautorizaciones bean=new BeanSeguridadautorizaciones();
		
		bean.getPk().setAplicacioncodigo(dtoAutorizacion.getAplicacioncodigo());
		bean.getPk().setUsuario(dtoAutorizacion.getUsuario());
		
		bean.getPk().setGrupo(grupo);
		bean.getPk().setConcepto(concepto);
		bean.setOpcionagregarflag(flgAgregar);
		bean.setOpcionmodificarflag(flgModificar);
		bean.setOpcionborrarflag(flgBorrar);
		bean.setEstado(seleccionar);
				
		bean.setUltimousuario(usuarioActual.getUsuario());
		bean.setUltimafechamodif(new Date());
		
		return bean;
	}


	public boolean isFlgAprobarboolean() {
		return flgAprobarboolean;
	}
	public void setFlgAprobarboolean(boolean flgAprobarboolean) {
		this.flgAprobarboolean = flgAprobarboolean;
	}
	public boolean isFlgVerDocsboolean() {
		return flgVerDocsboolean;
	}
	public void setFlgVerDocsboolean(boolean flgVerDocsboolean) {
		this.flgVerDocsboolean = flgVerDocsboolean;
	}
	public boolean isFlgEnviarCorreoleanr() {
		return flgEnviarCorreoleanr;
	}
	public void setFlgEnviarCorreoleanr(boolean flgEnviarCorreoleanr) {
		this.flgEnviarCorreoleanr = flgEnviarCorreoleanr;
	}
	public boolean isFlgOtrosbooleanr() {
		return flgOtrosbooleanr;
	}
	public void setFlgOtrosbooleanr(boolean flgOtrosbooleanr) {
		this.flgOtrosbooleanr = flgOtrosbooleanr;
	}
}
