package net.royal.spring.seguridad.dominio.dto;

import java.util.Date;

import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.seguridad.dominio.BeanSySeguridadautorizaciones;

public class DtoComunAutorizacionConcepto {
	private String seleccionar;
	private String aplicacioncodigo;
	private String grupo;
	private String concepto;
	private String grupoDescripcion;
	private String objetoAutorizar;
	private String ultimoUsuario;
	private String usuariocodigo;
	private String usernew;

	public String getUsernew() {
		return usernew;
	}

	public void setUsernew(String usernew) {
		this.usernew = usernew;
	}

	private Date ultimaFecha;

	public String getUsuariocodigo() {
		return usuariocodigo;
	}

	public void setUsuariocodigo(String usuariocodigo) {
		this.usuariocodigo = usuariocodigo;
	}

	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
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

	public String getUltimoUsuario() {
		return ultimoUsuario;
	}

	public void setUltimoUsuario(String ultimoUsuario) {
		this.ultimoUsuario = ultimoUsuario;
	}

	public Date getUltimaFecha() {
		return ultimaFecha;
	}

	public void setUltimaFecha(Date ultimaFecha) {
		this.ultimaFecha = ultimaFecha;
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

	public BeanSySeguridadautorizaciones obtenerBean(SeguridadUsuarioActual usuarioActual, DtoComunAutorizacion dtoAutorizacion) {
		BeanSySeguridadautorizaciones bean = new BeanSySeguridadautorizaciones();

		bean.getPk().setAplicacioncodigo(dtoAutorizacion.getAplicacioncodigo());
		bean.getPk().setUsuario(dtoAutorizacion.getUsuario());

		bean.getPk().setGrupo(grupo);
		bean.getPk().setConcepto(concepto);
		bean.setEstado(seleccionar);

		bean.setUltimousuario(usuarioActual.getUsuario());
		bean.setUltimafechamodif(new Date());

		return bean;
	}
}
