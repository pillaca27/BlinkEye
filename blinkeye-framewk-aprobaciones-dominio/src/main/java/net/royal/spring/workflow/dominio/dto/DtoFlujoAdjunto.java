package net.royal.spring.workflow.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.framework.modelo.WorkFlowAdjunto;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoFlujoAdjunto extends DominioTransaccion{

	public DtoFlujoAdjunto() {
	}

	public DtoFlujoAdjunto(WorkFlowAdjunto adj) {
		if (adj==null)
			return;
		tCompania = adj.gettCompania();
		tProceso = adj.gettCompania();
		tReferencia = adj.gettCompania();

		proceso = adj.getProceso();
		flujo = adj.getFlujo();
		transaccion = adj.getTransaccion();
		secuencia = adj.getSecuencia();

		tipodocumento = adj.getTipodocumento();
		rutaadjunto = adj.getRutaadjunto();
		flagVer = adj.getFlagVer();
		cantidadfirmas = adj.getCantidadfirmas();
		fecha = adj.getFecha();
		usuario = adj.getUsuario();
		puedeEditar = adj.getPuedeEditar();

		grupo = adj.getGrupo();
		plantilla = adj.getPlantilla();

		archivostring = adj.getArchivostring();
		archivonombre = adj.getArchivonombre();

		firmaElectronica = adj.getFirmaElectronica();
		firmaImagen = adj.getFirmaImagen();

		x = adj.getX();
		y = adj.getY();
		w = adj.getW();
		h = adj.getH();

		base64Image = adj.getBase64Image();
	}
	
	private String tCompania;
	private String tProceso;
	private String tReferencia;

	private Integer proceso;
	private Integer flujo;
	private Integer transaccion;
	private String transaccionUUID;
	private Integer secuencia;

	private String tipodocumento;
	private String tipodocumentodescripcion;
	private String rutaadjunto;
	private String flagVer;
	private Integer cantidadfirmas;
	private Date fecha;
	private String usuario;
	private String puedeEditar;

	private String grupo;
	private String plantilla;

	private String archivostring;
	private String archivonombre;

	private String firmaElectronica;
	private String firmaImagen;

	private Integer x;
	private Integer y;
	private Integer w;
	private Integer h;

	private String base64Image;
	private String auxRequerido;
	private Integer nivel;
	
	private String flgVerDocumentoGrupo;
	private String flgVerDocumentoNuevo;
	
	private String auxSeleccionado = "N";
	private String estadoTransaccion;
	private Date fechaVigenciaFin;
	private String flgVigencia;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

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

	public String getAuxRequerido() {
		return auxRequerido;
	}

	public void setAuxRequerido(String auxRequerido) {
		this.auxRequerido = auxRequerido;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getFlgVerDocumentoGrupo() {
		return flgVerDocumentoGrupo;
	}

	public void setFlgVerDocumentoGrupo(String flgVerDocumentoGrupo) {
		this.flgVerDocumentoGrupo = flgVerDocumentoGrupo;
	}

	public String getFlgVerDocumentoNuevo() {
		return flgVerDocumentoNuevo;
	}

	public void setFlgVerDocumentoNuevo(String flgVerDocumentoNuevo) {
		this.flgVerDocumentoNuevo = flgVerDocumentoNuevo;
	}

	public String getAuxSeleccionado() {
		return auxSeleccionado;
	}

	public void setAuxSeleccionado(String auxSeleccionado) {
		this.auxSeleccionado = auxSeleccionado;
	}

	public String getEstadoTransaccion() {
		return estadoTransaccion;
	}

	public void setEstadoTransaccion(String estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}

	public String getTipodocumentodescripcion() {
		return tipodocumentodescripcion;
	}

	public void setTipodocumentodescripcion(String tipodocumentodescripcion) {
		this.tipodocumentodescripcion = tipodocumentodescripcion;
	}
	
	public String getTransaccionUUID() {
		return transaccionUUID;
	}

	public void setTransaccionUUID(String transaccionUUID) {
		this.transaccionUUID = transaccionUUID;
	}
	
	public Date getFechaVigenciaFin() {
		return fechaVigenciaFin;
	}

	public void setFechaVigenciaFin(Date fechaVigenciaFin) {
		this.fechaVigenciaFin = fechaVigenciaFin;
	}
	public String getFlgVigencia() {
		return flgVigencia;
	}

	public void setFlgVigencia(String flgVigencia) {
		this.flgVigencia = flgVigencia;
	}

}
