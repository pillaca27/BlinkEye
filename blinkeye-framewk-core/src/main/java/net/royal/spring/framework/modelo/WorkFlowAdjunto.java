package net.royal.spring.framework.modelo;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class WorkFlowAdjunto  extends DominioTransaccion {
	private String tCompania;
	private String tProceso;
	private String tReferencia;

	private Integer proceso;
	private Integer flujo;
	private Integer transaccion;
	private Integer secuencia;

	private String tipodocumento;
	private String rutaadjunto;
	private String flagVer;
	private Integer cantidadfirmas;
	private Date fecha;
	private String usuario;
	private String puedeEditar;

	private String grupo;
	private String plantilla;
	
	private String auxSeleccionado = "N";

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	private String archivostring;
	private String archivonombre;

	private String firmaElectronica;
	private String firmaImagen;

	private Integer x;
	private Integer y;
	private Integer w;
	private Integer h;

	private String base64Image;

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public Integer getProceso() {
		return proceso;
	}

	public void setProceso(Integer proceso) {
		this.proceso = proceso;
	}

	public Integer getFlujo() {
		return flujo;
	}

	public void setFlujo(Integer flujo) {
		this.flujo = flujo;
	}

	public Integer getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Integer transaccion) {
		this.transaccion = transaccion;
	}

	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

	public String getRutaadjunto() {
		return rutaadjunto;
	}

	public void setRutaadjunto(String rutaadjunto) {
		this.rutaadjunto = rutaadjunto;
	}

	public String getFlagVer() {
		return flagVer;
	}

	public void setFlagVer(String flagVer) {
		this.flagVer = flagVer;
	}

	public Integer getCantidadfirmas() {
		return cantidadfirmas;
	}

	public void setCantidadfirmas(Integer cantidadfirmas) {
		this.cantidadfirmas = cantidadfirmas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getArchivostring() {
		return archivostring;
	}

	public void setArchivostring(String archivostring) {
		this.archivostring = archivostring;
	}

	public String getArchivonombre() {
		return archivonombre;
	}

	public void setArchivonombre(String archivonombre) {
		this.archivonombre = archivonombre;
	}

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getFirmaElectronica() {
		return firmaElectronica;
	}

	public void setFirmaElectronica(String firmaElectronica) {
		this.firmaElectronica = firmaElectronica;
	}

	public String getFirmaImagen() {
		return firmaImagen;
	}

	public void setFirmaImagen(String firmaImagen) {
		this.firmaImagen = firmaImagen;
	}

	public String getPuedeEditar() {
		return puedeEditar;
	}

	public void setPuedeEditar(String puedeEditar) {
		this.puedeEditar = puedeEditar;
	}

	public String gettCompania() {
		return tCompania;
	}

	public void settCompania(String tCompania) {
		this.tCompania = tCompania;
	}

	public String gettProceso() {
		return tProceso;
	}

	public void settProceso(String tProceso) {
		this.tProceso = tProceso;
	}

	public String gettReferencia() {
		return tReferencia;
	}

	public void settReferencia(String tReferencia) {
		this.tReferencia = tReferencia;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}

	public String getAuxSeleccionado() {
		return auxSeleccionado;
	}

	public void setAuxSeleccionado(String auxSeleccionado) {
		this.auxSeleccionado = auxSeleccionado;
	}
}
