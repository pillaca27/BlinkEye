package net.royal.spring.framework.modelo.seguridad;

import java.math.BigDecimal;
import java.util.List;

public class SeguridadMenuItem {
	private String id;
	private String label;
	private String icon;
	private String routerLink;
	private String action;
	private Integer orden;
		
	private String aplicacioncodigo;
	private String aplicacionNombre;
	private Integer aplicacionOrden;
	private String aplicacionImagen;

	private String grupoCodigo;
	private String grupoNombre;
	private Integer grupoOrden;
	private String grupoImagen;
	
	private String grupoPadreCodigo;
	private String grupoPadreNombre;
	private Integer grupoPadreOrden;
	private String grupoPadreImagen;

	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}

	/*
	 * viene de la tabla SeguridadConcepto Es Maestro? S (Si) N (No)
	 */
	private String tipoDeAcceso;
	/*
	 * viene de la tabla SEGURIDADAUTORIZACIONES, puede Agregar? S(Si) N(No)
	 */
	private Boolean flgAgregar;
	private String opcionagregarflag;
	
	/*
	 * viene de la tabla SEGURIDADAUTORIZACIONES, puede Modificar? S(Si) N(No)
	 */
	private Boolean flgModificar;
	private String opcionmodificarflag;
	
	/*
	 * viene de la tabla SEGURIDADAUTORIZACIONES, puede Borrar? S(Si) N(No)
	 */
	private Boolean flgBorrar;
	private String opcionborrarflag;
	
	private Boolean flgAprobar;
	private String opcionaprobarflag;
	
	private List<SeguridadMenuItem> items;

	private String urlAyuda;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRouterLink() {
		return routerLink;
	}

	public void setRouterLink(String routerLink) {
		this.routerLink = routerLink;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<SeguridadMenuItem> getItems() {
		return items;
	}

	public void setItems(List<SeguridadMenuItem> items) {
		this.items = items;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getTipoDeAcceso() {
		return tipoDeAcceso;
	}

	public void setTipoDeAcceso(String tipoDeAcceso) {
		this.tipoDeAcceso = tipoDeAcceso;
	}

	public Boolean getFlgAgregar() {
		return flgAgregar;
	}

	public void setFlgAgregar(Boolean flgAgregar) {
		this.flgAgregar = flgAgregar;
	}

	public Boolean getFlgModificar() {
		return flgModificar;
	}

	public void setFlgModificar(Boolean flgModificar) {
		this.flgModificar = flgModificar;
	}

	public Boolean getFlgBorrar() {
		return flgBorrar;
	}

	public void setFlgBorrar(Boolean flgBorrar) {
		this.flgBorrar = flgBorrar;
	}

	public String getUrlAyuda() {
		return urlAyuda;
	}

	public void setUrlAyuda(String urlAyuda) {
		this.urlAyuda = urlAyuda;
	}

	public String getOpcionagregarflag() {
		return opcionagregarflag;
	}

	public void setOpcionagregarflag(String opcionagregarflag) {
		this.opcionagregarflag = opcionagregarflag;
	}

	public String getOpcionmodificarflag() {
		return opcionmodificarflag;
	}

	public void setOpcionmodificarflag(String opcionmodificarflag) {
		this.opcionmodificarflag = opcionmodificarflag;
	}

	public String getOpcionborrarflag() {
		return opcionborrarflag;
	}

	public void setOpcionborrarflag(String opcionborrarflag) {
		this.opcionborrarflag = opcionborrarflag;
	}

	public String getAplicacionNombre() {
		return aplicacionNombre;
	}

	public void setAplicacionNombre(String aplicacionNombre) {
		this.aplicacionNombre = aplicacionNombre;
	}

	public Integer getAplicacionOrden() {
		return aplicacionOrden;
	}

	public void setAplicacionOrden(Integer aplicacionOrden) {
		this.aplicacionOrden = aplicacionOrden;
	}

	public String getAplicacionImagen() {
		return aplicacionImagen;
	}

	public void setAplicacionImagen(String aplicacionImagen) {
		this.aplicacionImagen = aplicacionImagen;
	}

	public String getGrupoCodigo() {
		return grupoCodigo;
	}

	public void setGrupoCodigo(String grupoCodigo) {
		this.grupoCodigo = grupoCodigo;
	}

	public String getGrupoNombre() {
		return grupoNombre;
	}

	public void setGrupoNombre(String grupoNombre) {
		this.grupoNombre = grupoNombre;
	}

	public Integer getGrupoOrden() {
		return grupoOrden;
	}

	public void setGrupoOrden(Integer grupoOrden) {
		this.grupoOrden = grupoOrden;
	}

	public String getGrupoImagen() {
		return grupoImagen;
	}

	public void setGrupoImagen(String grupoImagen) {
		this.grupoImagen = grupoImagen;
	}

	public String getGrupoPadreCodigo() {
		return grupoPadreCodigo;
	}

	public void setGrupoPadreCodigo(String grupoPadreCodigo) {
		this.grupoPadreCodigo = grupoPadreCodigo;
	}

	public String getGrupoPadreNombre() {
		return grupoPadreNombre;
	}

	public void setGrupoPadreNombre(String grupoPadreNombre) {
		this.grupoPadreNombre = grupoPadreNombre;
	}

	public Integer getGrupoPadreOrden() {
		return grupoPadreOrden;
	}

	public void setGrupoPadreOrden(Integer grupoPadreOrden) {
		this.grupoPadreOrden = grupoPadreOrden;
	}

	public String getGrupoPadreImagen() {
		return grupoPadreImagen;
	}

	public void setGrupoPadreImagen(String grupoPadreImagen) {
		this.grupoPadreImagen = grupoPadreImagen;
	}

	public Boolean getFlgAprobar() {
		return flgAprobar;
	}

	public void setFlgAprobar(Boolean flgAprobar) {
		this.flgAprobar = flgAprobar;
	}

	public String getOpcionaprobarflag() {
		return opcionaprobarflag;
	}

	public void setOpcionaprobarflag(String opcionaprobarflag) {
		this.opcionaprobarflag = opcionaprobarflag;
	}
	
}

