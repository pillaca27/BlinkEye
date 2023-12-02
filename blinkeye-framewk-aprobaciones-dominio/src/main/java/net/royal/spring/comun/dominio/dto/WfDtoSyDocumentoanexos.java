package net.royal.spring.comun.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;

public class WfDtoSyDocumentoanexos  extends DominioTransaccion implements java.io.Serializable{

	private String companiasocio;
	private String modulo;
	private String tipodocumento;
	private String numerodocumento;
	private Integer linea;
	private Integer secuencia;
	private java.util.Date fecha;
	private String descripcion;
	private String comentario;
	private String rutaarchivo;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String archivo;
	private String procesoTipoDocumentoId;
	private String archivostring;
	private String flgactualizar;
	private String nombreanteerior;
	private String auxRutaTemporal;
	private String auxRutaPlantilla;
	private String auxFlgAdjuntoObligatorio = "S";
	private String auxFlgNoConsiderarValidacion;
	private String  validarporrango;
	private BigDecimal montorango1inicio;
	private BigDecimal montorango1fin;
	
	public String getAuxFlgNoConsiderarValidacion() {
		return auxFlgNoConsiderarValidacion;
	}

	public void setAuxFlgNoConsiderarValidacion(String auxFlgNoConsiderarValidacion) {
		this.auxFlgNoConsiderarValidacion = auxFlgNoConsiderarValidacion;
	}

	private String concepto;
	
	private String auxConfirmar = "N";
	private String auxConfirmarMensaje;
	
	public String getNombreanteerior() {
		return nombreanteerior;
	}

	public void setNombreanteerior(String nombreanteerior) {
		this.nombreanteerior = nombreanteerior;
	}

	public String getArchivostring() {
		return archivostring;
	}

	public void setArchivostring(String archivostring) {
		this.archivostring = archivostring;
	}

	public String getFlgactualizar() {
		return flgactualizar;
	}

	public void setFlgactualizar(String flgactualizar) {
		this.flgactualizar = flgactualizar;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public String getCompaniasocio() {
		return companiasocio;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	/**
	 * 
	 * 
	 * @campo Modulo
	*/
	public String getModulo() {
		return modulo;
	}

	/**
	 * 
	 * 
	 * @campo Modulo
	*/
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	/**
	 * 
	 * 
	 * @campo TipoDocumento
	*/
	public String getTipodocumento() {
		return tipodocumento;
	}

	/**
	 * 
	 * 
	 * @campo TipoDocumento
	*/
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	/**
	 * 
	 * 
	 * @campo NumeroDocumento
	*/
	public String getNumerodocumento() {
		return numerodocumento;
	}

	/**
	 * 
	 * 
	 * @campo NumeroDocumento
	*/
	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}
	/**
	 * 
	 * 
	 * @campo Linea
	*/
	public Integer getLinea() {
		return linea;
	}

	/**
	 * 
	 * 
	 * @campo Linea
	*/
	public void setLinea(Integer linea) {
		this.linea = linea;
	}
	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public Integer getSecuencia() {
		return secuencia;
	}

	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	/**
	 * 
	 * 
	 * @campo Fecha
	*/
	public java.util.Date getFecha() {
		return fecha;
	}

	/**
	 * 
	 * 
	 * @campo Fecha
	*/
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * 
	 * 
	 * @campo Comentario
	*/
	public String getComentario() {
		return comentario;
	}

	/**
	 * 
	 * 
	 * @campo Comentario
	*/
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	/**
	 * 
	 * 
	 * @campo RutaArchivo
	*/
	public String getRutaarchivo() {
		return rutaarchivo;
	}

	/**
	 * 
	 * 
	 * @campo RutaArchivo
	*/
	public void setRutaarchivo(String rutaarchivo) {
		this.rutaarchivo = rutaarchivo;
	}
	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * 
	 * 
	 * @campo Archivo
	*/
	public String getArchivo() {
		return archivo;
	}

	/**
	 * 
	 * 
	 * @campo Archivo
	*/
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}


	public String getAuxRutaTemporal() {
		return auxRutaTemporal;
	}

	public void setAuxRutaTemporal(String auxRutaTemporal) {
		this.auxRutaTemporal = auxRutaTemporal;
	}
	
	public String getProcesoTipoDocumentoId() {
		return procesoTipoDocumentoId;
	}

	public void setProcesoTipoDocumentoId(String procesoTipoDocumentoId) {
		this.procesoTipoDocumentoId = procesoTipoDocumentoId;
	}
	
	public String getAuxRutaPlantilla() {
		return auxRutaPlantilla;
	}

	public void setAuxRutaPlantilla(String auxRutaPlantilla) {
		this.auxRutaPlantilla = auxRutaPlantilla;
	}	

	public String getAuxFlgAdjuntoObligatorio() {
		if (UString.esNuloVacio(auxFlgAdjuntoObligatorio))
			auxFlgAdjuntoObligatorio="S";
		return auxFlgAdjuntoObligatorio;
	}

	public void setAuxFlgAdjuntoObligatorio(String auxFlgAdjuntoObligatorio) {
		this.auxFlgAdjuntoObligatorio = auxFlgAdjuntoObligatorio;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getAuxConfirmar() {
		return auxConfirmar;
	}

	public void setAuxConfirmar(String auxConfirmar) {
		this.auxConfirmar = auxConfirmar;
	}

	public String getAuxConfirmarMensaje() {
		return auxConfirmarMensaje;
	}

	public void setAuxConfirmarMensaje(String auxConfirmarMensaje) {
		this.auxConfirmarMensaje = auxConfirmarMensaje;
	}

	public String getValidarporrango() {
		return validarporrango;
	}

	public void setValidarporrango(String validarporrango) {
		this.validarporrango = validarporrango;
	}

	public BigDecimal getMontorango1inicio() {
		return montorango1inicio;
	}

	public void setMontorango1inicio(BigDecimal montorango1inicio) {
		this.montorango1inicio = montorango1inicio;
	}

	public BigDecimal getMontorango1fin() {
		return montorango1fin;
	}

	public void setMontorango1fin(BigDecimal montorango1fin) {
		this.montorango1fin = montorango1fin;
	}

}
