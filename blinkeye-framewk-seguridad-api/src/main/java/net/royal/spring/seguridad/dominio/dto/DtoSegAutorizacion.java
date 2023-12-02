package net.royal.spring.seguridad.dominio.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.util.UBoolean;

public class DtoSegAutorizacion implements java.io.Serializable{

	private String sidId;
	private String aplicacionId;
	private String contenedorId;
	private String componenteId;
	private String usuarioId;
	
	private String flgNuevo;
	private String flgEditar;
	private String flgEliminar;
	private String flgAprobar;
	private String estadoId;
	
	/*private String flgVer;	
	private String flgAnular;	
	private String flgImprimir;
	private String flgExportar;
	private String flgOpcion1;
	private String flgOpcion2;
	private String flgOpcion3;	
	*/
	
	/*private String creacionUsuario;
	private java.util.Date creacionFecha;
	private String creacionTerminal;
	private String modificacionTerminal;*/
	private String modificacionUsuario;
	private java.util.Date modificacionFecha;
	

	/* auxiliares para devolver todos los nombres */
	private String auxAplicacionNombre;
	private String auxContenedorNombre;
	private String auxComponenteNombre;
	private String auxUsuarioNombre;
	
	private Integer auxAplicacionOrden;
	private Integer auxContenedorOrden;
	private Integer auxComponenteOrden;
	
	private String auxAplicacionImagen;
	private String auxContenedorImagen;
	private String auxComponenteImagen;
	
	private String auxComponenteRutaWeb;
	
	private String auxContenedorPadreId;
	private String auxContenedorPadreNombre;
	private Integer auxContenedorPadreOrden;
	private String auxContenedorPadreImagen;
	
	/* auxiliares para dar permisos */
	private boolean auxFlgNuevo;
	private boolean auxFlgEditar;
	private boolean auxFlgVer;
	private boolean auxFlgEliminar;
	private boolean auxFlgAnular;	
	private boolean auxFlgImprimir;
	private boolean auxFlgExportar;
	private boolean auxFlgOpcion1;
	private boolean auxFlgOpcion2;
	private boolean auxFlgOpcion3;	
	private boolean auxEstadoId;
	private boolean auxFlgAprobar;
	
	private String auxAplicacionWebPage;
	
	private List<DtoSegAutorizacion> listaPermisos;
	
	//* lo que debio ser transaccion *//
	@Transient
	private String auxFlgPreparado = "N";

	@Transient
	private String auxFlgValidado = "N";
	
	@Transient
	private String transaccionEstado = "OK";

	@Transient
	private List<DominioMensajeUsuario> transaccionListaMensajes = new ArrayList<>();
	private String uuid;
	
	private String contenedorWebGrupo;
	private String componenteWebGrupo;
	private String auxContenedorPadreWebGrupo;
	
	private String auxComponenteAction;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * 
	 * 
	 * @campo SID_ID
	*/
	public String getSidId() {
		return sidId;
	}

	/**
	 * 
	 * 
	 * @campo SID_ID
	*/
	public void setSidId(String sidId) {
		this.sidId = sidId;
	}
	/**
	 * 
	 * 
	 * @campo APLICACION_ID
	*/
	public String getAplicacionId() {
		return aplicacionId;
	}

	/**
	 * 
	 * 
	 * @campo APLICACION_ID
	*/
	public void setAplicacionId(String aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	/**
	 * 
	 * 
	 * @campo CONTENEDOR_ID
	*/
	public String getContenedorId() {
		return contenedorId;
	}

	/**
	 * 
	 * 
	 * @campo CONTENEDOR_ID
	*/
	public void setContenedorId(String contenedorId) {
		this.contenedorId = contenedorId;
	}
	/**
	 * 
	 * 
	 * @campo COMPONENTE_ID
	*/
	public String getComponenteId() {
		return componenteId;
	}

	/**
	 * 
	 * 
	 * @campo COMPONENTE_ID
	*/
	public void setComponenteId(String componenteId) {
		this.componenteId = componenteId;
	}
	/**
	 * 
	 * 
	 * @campo USUARIO_ID
	*/
	public String getUsuarioId() {
		return usuarioId;
	}

	/**
	 * 
	 * 
	 * @campo USUARIO_ID
	*/
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	/**
	 * 
	 * 
	 * @campo FLG_NUEVO
	*/
	public String getFlgNuevo() {
		return flgNuevo;
	}

	/**
	 * 
	 * 
	 * @campo FLG_NUEVO
	*/
	public void setFlgNuevo(String flgNuevo) {
		this.flgNuevo = flgNuevo;
		this.auxFlgNuevo = UBoolean.validarFlag(flgNuevo);
	}
	/**
	 * 
	 * 
	 * @campo FLG_EDITAR
	*/
	public String getFlgEditar() {
		return flgEditar;
	}

	/**
	 * 
	 * 
	 * @campo FLG_EDITAR
	*/
	public void setFlgEditar(String flgEditar) {
		this.flgEditar = flgEditar;
		this.auxFlgEditar = UBoolean.validarFlag(flgEditar);
	}
	
	/**
	 * 
	 * 
	 * @campo FLG_ELIMINAR
	*/
	public String getFlgEliminar() {
		return flgEliminar;
	}

	/**
	 * 
	 * 
	 * @campo FLG_ELIMINAR
	*/
	public void setFlgEliminar(String flgEliminar) {
		this.flgEliminar = flgEliminar;
		this.auxFlgEliminar = UBoolean.validarFlag(flgEliminar);
	}
	

	public String getAuxAplicacionNombre() {
		return auxAplicacionNombre;
	}

	public void setAuxAplicacionNombre(String auxAplicacionNombre) {
		this.auxAplicacionNombre = auxAplicacionNombre;
	}

	public String getAuxContenedorNombre() {
		return auxContenedorNombre;
	}

	public void setAuxContenedorNombre(String auxContenedorNombre) {
		this.auxContenedorNombre = auxContenedorNombre;
	}

	public String getAuxComponenteNombre() {
		return auxComponenteNombre;
	}

	public void setAuxComponenteNombre(String auxComponenteNombre) {
		this.auxComponenteNombre = auxComponenteNombre;
	}

	public String getAuxUsuarioNombre() {
		return auxUsuarioNombre;
	}

	public void setAuxUsuarioNombre(String auxUsuarioNombre) {
		this.auxUsuarioNombre = auxUsuarioNombre;
	}

	/**
	 * 
	 * 
	 * @campo ESTADO_ID
	*/
	public String getEstadoId() {
		return estadoId;
	}

	/**
	 * 
	 * 
	 * @campo ESTADO_ID
	*/
	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
		this.auxEstadoId = UBoolean.validarEstado(estadoId);
	}
	
	/**
	 * 
	 * 
	 * @campo MODIFICACION_USUARIO
	*/
	public String getModificacionUsuario() {
		return modificacionUsuario;
	}

	/**
	 * 
	 * 
	 * @campo MODIFICACION_USUARIO
	*/
	public void setModificacionUsuario(String modificacionUsuario) {
		this.modificacionUsuario = modificacionUsuario;
	}
	/**
	 * 
	 * 
	 * @campo MODIFICACION_FECHA
	*/
	public java.util.Date getModificacionFecha() {
		return modificacionFecha;
	}

	/**
	 * 
	 * 
	 * @campo MODIFICACION_FECHA
	*/
	public void setModificacionFecha(java.util.Date modificacionFecha) {
		this.modificacionFecha = modificacionFecha;
	}
	
	public List<DtoSegAutorizacion> getListaPermisos() {
		return listaPermisos;
	}

	public void setListaPermisos(List<DtoSegAutorizacion> listaPermisos) {
		this.listaPermisos = listaPermisos;
	}
	
	public boolean isAuxFlgNuevo() {
		return auxFlgNuevo;
	}

	public void setAuxFlgNuevo(boolean auxFlgNuevo) {
		this.auxFlgNuevo = auxFlgNuevo;
	}

	public boolean isAuxFlgEditar() {
		return auxFlgEditar;
	}

	public void setAuxFlgEditar(boolean auxFlgEditar) {
		this.auxFlgEditar = auxFlgEditar;
	}

	public boolean isAuxFlgVer() {
		return auxFlgVer;
	}

	public void setAuxFlgVer(boolean auxFlgVer) {
		this.auxFlgVer = auxFlgVer;
	}

	public boolean isAuxFlgEliminar() {
		return auxFlgEliminar;
	}

	public void setAuxFlgEliminar(boolean auxFlgEliminar) {
		this.auxFlgEliminar = auxFlgEliminar;
	}

	public boolean isAuxFlgAnular() {
		return auxFlgAnular;
	}

	public void setAuxFlgAnular(boolean auxFlgAnular) {
		this.auxFlgAnular = auxFlgAnular;
	}

	public boolean isAuxFlgImprimir() {
		return auxFlgImprimir;
	}

	public void setAuxFlgImprimir(boolean auxFlgImprimir) {
		this.auxFlgImprimir = auxFlgImprimir;
	}

	public boolean isAuxFlgExportar() {
		return auxFlgExportar;
	}

	public void setAuxFlgExportar(boolean auxFlgExportar) {
		this.auxFlgExportar = auxFlgExportar;
	}

	public boolean isAuxFlgOpcion1() {
		return auxFlgOpcion1;
	}

	public void setAuxFlgOpcion1(boolean auxFlgOpcion1) {
		this.auxFlgOpcion1 = auxFlgOpcion1;
	}

	public boolean isAuxFlgOpcion2() {
		return auxFlgOpcion2;
	}

	public void setAuxFlgOpcion2(boolean auxFlgOpcion2) {
		this.auxFlgOpcion2 = auxFlgOpcion2;
	}

	public boolean isAuxFlgOpcion3() {
		return auxFlgOpcion3;
	}

	public void setAuxFlgOpcion3(boolean auxFlgOpcion3) {
		this.auxFlgOpcion3 = auxFlgOpcion3;
	}

	public boolean isAuxEstadoId() {
		return auxEstadoId;
	}

	public void setAuxEstadoId(boolean auxEstadoId) {
		this.auxEstadoId = auxEstadoId;
	}
	
	
	public Integer getAuxAplicacionOrden() {
		return auxAplicacionOrden;
	}

	public void setAuxAplicacionOrden(Integer auxAplicacionOrden) {
		this.auxAplicacionOrden = auxAplicacionOrden;
	}

	public Integer getAuxContenedorOrden() {
		return auxContenedorOrden;
	}

	public void setAuxContenedorOrden(Integer auxContenedorOrden) {
		this.auxContenedorOrden = auxContenedorOrden;
	}

	public Integer getAuxComponenteOrden() {
		return auxComponenteOrden;
	}

	public void setAuxComponenteOrden(Integer auxComponenteOrden) {
		this.auxComponenteOrden = auxComponenteOrden;
	}

	public String getAuxAplicacionImagen() {
		return auxAplicacionImagen;
	}

	public void setAuxAplicacionImagen(String auxAplicacionImagen) {
		this.auxAplicacionImagen = auxAplicacionImagen;
	}

	public String getAuxContenedorImagen() {
		return auxContenedorImagen;
	}

	public void setAuxContenedorImagen(String auxContenedorImagen) {
		this.auxContenedorImagen = auxContenedorImagen;
	}

	public String getAuxComponenteImagen() {
		return auxComponenteImagen;
	}

	public void setAuxComponenteImagen(String auxComponenteImagen) {
		this.auxComponenteImagen = auxComponenteImagen;
	}

	public String getAuxComponenteRutaWeb() {
		return auxComponenteRutaWeb;
	}

	public void setAuxComponenteRutaWeb(String auxComponenteRutaWeb) {
		this.auxComponenteRutaWeb = auxComponenteRutaWeb;
	}

	/** inicio de transaccion **/
	public String getAuxFlgPreparado() {
		return auxFlgPreparado;
	}

	public void setAuxFlgPreparado(String auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}

	public String getAuxFlgValidado() {
		return auxFlgValidado;
	}

	public void setAuxFlgValidado(String auxFlgValidado) {
		this.auxFlgValidado = auxFlgValidado;
	}
	public String getAuxContenedorPadreId() {
		return auxContenedorPadreId;
	}

	public void setAuxContenedorPadreId(String auxContenedorPadreId) {
		this.auxContenedorPadreId = auxContenedorPadreId;
	}

	public String getTransaccionEstado() {
		return transaccionEstado;
	}

	public void setTransaccionEstado(String transaccionEstado) {
		this.transaccionEstado = transaccionEstado;
	}

	public List<DominioMensajeUsuario> getTransaccionListaMensajes() {
		return transaccionListaMensajes;
	}

	public void setTransaccionListaMensajes(List<DominioMensajeUsuario> transaccionListaMensajes) {
		this.transaccionListaMensajes = transaccionListaMensajes;
	}
	
	public void setAuxFlgValidadoBoolean(Boolean flgValidado) {
		if (flgValidado)
			auxFlgValidado = "S";
		else
			auxFlgValidado = "N";
	}
	/** fin de transaccion **/


	public String getAuxContenedorPadreNombre() {
		return auxContenedorPadreNombre;
	}

	public void setAuxContenedorPadreNombre(String auxContenedorPadreNombre) {
		this.auxContenedorPadreNombre = auxContenedorPadreNombre;
	}

	public Integer getAuxContenedorPadreOrden() {
		return auxContenedorPadreOrden;
	}

	public void setAuxContenedorPadreOrden(Integer auxContenedorPadreOrden) {
		this.auxContenedorPadreOrden = auxContenedorPadreOrden;
	}

	public String getAuxContenedorPadreImagen() {
		return auxContenedorPadreImagen;
	}

	public void setAuxContenedorPadreImagen(String auxContenedorPadreImagen) {
		this.auxContenedorPadreImagen = auxContenedorPadreImagen;
	}

	public String getFlgAprobar() {
		return flgAprobar;
	}

	public void setFlgAprobar(String flgAprobar) {
		this.flgAprobar = flgAprobar;
	}

	public boolean isAuxFlgAprobar() {
		return auxFlgAprobar;
	}

	public void setAuxFlgAprobar(boolean auxFlgAprobar) {
		this.auxFlgAprobar = auxFlgAprobar;
	}

	public String getAuxAplicacionWebPage() {
		return auxAplicacionWebPage;
	}

	public void setAuxAplicacionWebPage(String auxAplicacionWebPage) {
		this.auxAplicacionWebPage = auxAplicacionWebPage;
	}

	public String getContenedorWebGrupo() {
		return contenedorWebGrupo;
	}

	public void setContenedorWebGrupo(String contenedorWebGrupo) {
		this.contenedorWebGrupo = contenedorWebGrupo;
	}

	public String getComponenteWebGrupo() {
		return componenteWebGrupo;
	}

	public void setComponenteWebGrupo(String componenteWebGrupo) {
		this.componenteWebGrupo = componenteWebGrupo;
	}

	public String getAuxContenedorPadreWebGrupo() {
		return auxContenedorPadreWebGrupo;
	}

	public void setAuxContenedorPadreWebGrupo(String auxContenedorPadreWebGrupo) {
		this.auxContenedorPadreWebGrupo = auxContenedorPadreWebGrupo;
	}

	public String getAuxComponenteAction() {
		return auxComponenteAction;
	}

	public void setAuxComponenteAction(String auxComponenteAction) {
		this.auxComponenteAction = auxComponenteAction;
	}

}
