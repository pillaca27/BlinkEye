package net.royal.spring.workflow.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "SY_DocumentoAnexos")
public class SyDocumentoAnexos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SyDocumentoAnexosPk pk;

	@Column(name = "Fecha")
	private Date fecha;

	@Column(name = "Descripcion")
	private String descripcion;

	@Column(name = "Comentario")
	private String comentario;

	@Column(name = "RutaArchivo")
	private String rutaArchivo;

	@Column(name = "Estado")
	private String estado;

	@Column(name = "Archivo")
	private String archivo;

	@Column(name = "ProcesoTipoDocumentoId")
	private String procesoTipoDocumentoId;

	@Column(name = "Concepto")
	private String concepto;

	@Column(name = "UltimoUsuario")
	private String ultimoUsuario;

	@Column(name = "UltimaFechaModif")
	private Date ultimaFechaModif;
	
	@Column(name = "FlgVigencia")
	private String flgVigencia;
	
	@Column(name = "FechaVigenciaFin")
	private Date fechaVigenciaFin;
	
	@Column(name = "CantidadFirmas")
	private Integer cantidadFirmas;
	
	@Column(name = "FlgVer")
	private String flgVer;

	/****/
	@Column(name = "AutorId")
	private Integer autorId;
	
	@Column(name = "AutorNombre")
	private String autorNombre;
	
	@Column(name = "AutorDocumento")
	private String autorDocumento;
	
	@Column(name = "AutorCorreo")
	private String autorCorreo;
	/****/
	
	@Column(name = "CodigoTexto1Id")
	private String codigoTexto1Id;
	
	@Column(name = "CodigoTexto2Id")
	private String codigoTexto2Id;
	
	@Column(name = "CodigoTexto3Id")
	private String codigoTexto3Id;
	
	@Column(name = "CodigoTexto4Id")
	private String codigoTexto4Id;
	
	@Column(name = "CodigoTexto5Id")
	private String codigoTexto5Id;
	
	@Column(name = "CodigoTexto6Id")
	private String codigoTexto6Id;
	
	/****/
	
	@Column(name = "CodigoNumero1Id")
	private Integer codigoNumero1Id;
	
	@Column(name = "CodigoNumero2Id")
	private Integer codigoNumero2Id;
	
	@Column(name = "CodigoNumero3Id")
	private Integer codigoNumero3Id;
	
	@Column(name = "CodigoNumero4Id")
	private Integer codigoNumero4Id;
	
	@Column(name = "CodigoNumero5Id")
	private Integer codigoNumero5Id;
	
	@Column(name = "CodigoNumero6Id")
	private Integer codigoNumero6Id;
	
	/****/
	@Column(name = "WorkFlowTransaccionId")
	private Integer workFlowTransaccionId;
	
	@Column(name = "WorkFlowProcesoId")
	private String workFlowProcesoId;
	
	@Column(name = "WorkFlowVersionId")
	private Integer workFlowVersionId;
	
	@Column(name = "WorkFlowFlujoId")
	private Integer workFlowFlujoId;
	
	/****/
	
	@Column(name = "DocumentoGeneradoEstadoId")
	private String documentoGeneradoEstadoId;
	
	@Column(name = "DocumentoGeneradoMensaje")
	private String documentoGeneradoMensaje;
	
	@Column(name = "DocumentoGeneradoFecha")
	private Date documentoGeneradoFecha;
	
	@Column(name = "FirmaElectronicaEstadoId")
	private String firmaElectronicaEstadoId;
	
	@Column(name = "FirmaElectronicaMensaje")
	private String firmaElectronicaMensaje;
	
	@Column(name = "FirmaElectronicaFecha")
	private Date firmaElectronicaFecha;
	
	@Column(name = "EnvioCorreoEstadoId")
	private String envioCorreoEstadoId;
	
	@Column(name = "EnvioCorreoMensaje")
	private String envioCorreoMensaje;
	
	@Column(name = "EnvioCorreoFecha")
	private Date envioCorreoFecha;
	
	/****/
	@Column(name = "Tamanio")
	private Integer tamanio;
	
	@Column(name = "ClaveDocumento")
	private String claveDocumento;
	
	@Column(name = "Tags")
	private String tags;
	
	@Column(name = "RutaCompleta")
	private String rutaCompleta;
	
	@Column(name = "NombreGenerado")
	private String nombreGenerado;
	
	@Column(name = "UnidadReplicacionId")
	private String unidadReplicacionId;
	
	@Column(name = "SucursalId")
	private String sucursalId;
	
	@Column(name = "PeriodoId")
	private String periodoId;
	
	@Column(name = "CentroCostoId")
	private String centroCostoId;
	
	@Column(name = "UnidadNegocioId")
	private String unidadNegocioId;
	
	@Column(name = "TipoContenidoId")
	private String tipoContenidoId;
	
	@Column(name = "TipoMimeId")
	private String tipoMimeId;
	
	@Column(name = "Extension")
	private String extension;
	
	public SyDocumentoAnexos() {
		pk = new SyDocumentoAnexosPk();
	}

	public SyDocumentoAnexosPk getPk() {
		return pk;
	}

	public void setPk(SyDocumentoAnexosPk pk) {
		this.pk = pk;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getProcesoTipoDocumentoId() {
		return procesoTipoDocumentoId;
	}

	public void setProcesoTipoDocumentoId(String procesoTipoDocumentoId) {
		this.procesoTipoDocumentoId = procesoTipoDocumentoId;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getUltimoUsuario() {
		return ultimoUsuario;
	}

	public void setUltimoUsuario(String ultimoUsuario) {
		this.ultimoUsuario = ultimoUsuario;
	}

	public Date getUltimaFechaModif() {
		return ultimaFechaModif;
	}

	public void setUltimaFechaModif(Date ultimaFechaModif) {
		this.ultimaFechaModif = ultimaFechaModif;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public String getAutorNombre() {
		return autorNombre;
	}

	public void setAutorNombre(String autorNombre) {
		this.autorNombre = autorNombre;
	}

	public String getAutorDocumento() {
		return autorDocumento;
	}

	public void setAutorDocumento(String autorDocumento) {
		this.autorDocumento = autorDocumento;
	}

	public String getAutorCorreo() {
		return autorCorreo;
	}

	public void setAutorCorreo(String autorCorreo) {
		this.autorCorreo = autorCorreo;
	}

	public String getCodigoTexto1Id() {
		return codigoTexto1Id;
	}

	public void setCodigoTexto1Id(String codigoTexto1Id) {
		this.codigoTexto1Id = codigoTexto1Id;
	}

	public String getCodigoTexto2Id() {
		return codigoTexto2Id;
	}

	public void setCodigoTexto2Id(String codigoTexto2Id) {
		this.codigoTexto2Id = codigoTexto2Id;
	}

	public String getCodigoTexto3Id() {
		return codigoTexto3Id;
	}

	public void setCodigoTexto3Id(String codigoTexto3Id) {
		this.codigoTexto3Id = codigoTexto3Id;
	}

	public String getCodigoTexto4Id() {
		return codigoTexto4Id;
	}

	public void setCodigoTexto4Id(String codigoTexto4Id) {
		this.codigoTexto4Id = codigoTexto4Id;
	}

	public String getCodigoTexto5Id() {
		return codigoTexto5Id;
	}

	public void setCodigoTexto5Id(String codigoTexto5Id) {
		this.codigoTexto5Id = codigoTexto5Id;
	}

	public String getCodigoTexto6Id() {
		return codigoTexto6Id;
	}

	public void setCodigoTexto6Id(String codigoTexto6Id) {
		this.codigoTexto6Id = codigoTexto6Id;
	}

	public Integer getCodigoNumero1Id() {
		return codigoNumero1Id;
	}

	public void setCodigoNumero1Id(Integer codigoNumero1Id) {
		this.codigoNumero1Id = codigoNumero1Id;
	}

	public Integer getCodigoNumero2Id() {
		return codigoNumero2Id;
	}

	public void setCodigoNumero2Id(Integer codigoNumero2Id) {
		this.codigoNumero2Id = codigoNumero2Id;
	}

	public Integer getCodigoNumero3Id() {
		return codigoNumero3Id;
	}

	public void setCodigoNumero3Id(Integer codigoNumero3Id) {
		this.codigoNumero3Id = codigoNumero3Id;
	}

	public Integer getCodigoNumero4Id() {
		return codigoNumero4Id;
	}

	public void setCodigoNumero4Id(Integer codigoNumero4Id) {
		this.codigoNumero4Id = codigoNumero4Id;
	}

	public Integer getCodigoNumero5Id() {
		return codigoNumero5Id;
	}

	public void setCodigoNumero5Id(Integer codigoNumero5Id) {
		this.codigoNumero5Id = codigoNumero5Id;
	}

	public Integer getCodigoNumero6Id() {
		return codigoNumero6Id;
	}

	public void setCodigoNumero6Id(Integer codigoNumero6Id) {
		this.codigoNumero6Id = codigoNumero6Id;
	}

	public Integer getWorkFlowTransaccionId() {
		return workFlowTransaccionId;
	}

	public void setWorkFlowTransaccionId(Integer workFlowTransaccionId) {
		this.workFlowTransaccionId = workFlowTransaccionId;
	}

	public String getWorkFlowProcesoId() {
		return workFlowProcesoId;
	}

	public void setWorkFlowProcesoId(String workFlowProcesoId) {
		this.workFlowProcesoId = workFlowProcesoId;
	}

	public Integer getWorkFlowVersionId() {
		return workFlowVersionId;
	}

	public void setWorkFlowVersionId(Integer workFlowVersionId) {
		this.workFlowVersionId = workFlowVersionId;
	}

	public Integer getWorkFlowFlujoId() {
		return workFlowFlujoId;
	}

	public void setWorkFlowFlujoId(Integer workFlowFlujoId) {
		this.workFlowFlujoId = workFlowFlujoId;
	}

	public String getDocumentoGeneradoEstadoId() {
		return documentoGeneradoEstadoId;
	}

	public void setDocumentoGeneradoEstadoId(String documentoGeneradoEstadoId) {
		this.documentoGeneradoEstadoId = documentoGeneradoEstadoId;
	}

	public String getDocumentoGeneradoMensaje() {
		return documentoGeneradoMensaje;
	}

	public void setDocumentoGeneradoMensaje(String documentoGeneradoMensaje) {
		this.documentoGeneradoMensaje = documentoGeneradoMensaje;
	}

	public Date getDocumentoGeneradoFecha() {
		return documentoGeneradoFecha;
	}

	public void setDocumentoGeneradoFecha(Date documentoGeneradoFecha) {
		this.documentoGeneradoFecha = documentoGeneradoFecha;
	}

	public String getFirmaElectronicaEstadoId() {
		return firmaElectronicaEstadoId;
	}

	public void setFirmaElectronicaEstadoId(String firmaElectronicaEstadoId) {
		this.firmaElectronicaEstadoId = firmaElectronicaEstadoId;
	}

	public String getFirmaElectronicaMensaje() {
		return firmaElectronicaMensaje;
	}

	public void setFirmaElectronicaMensaje(String firmaElectronicaMensaje) {
		this.firmaElectronicaMensaje = firmaElectronicaMensaje;
	}

	public Date getFirmaElectronicaFecha() {
		return firmaElectronicaFecha;
	}

	public void setFirmaElectronicaFecha(Date firmaElectronicaFecha) {
		this.firmaElectronicaFecha = firmaElectronicaFecha;
	}

	public String getEnvioCorreoEstadoId() {
		return envioCorreoEstadoId;
	}

	public void setEnvioCorreoEstadoId(String envioCorreoEstadoId) {
		this.envioCorreoEstadoId = envioCorreoEstadoId;
	}

	public String getEnvioCorreoMensaje() {
		return envioCorreoMensaje;
	}

	public void setEnvioCorreoMensaje(String envioCorreoMensaje) {
		this.envioCorreoMensaje = envioCorreoMensaje;
	}

	public Date getEnvioCorreoFecha() {
		return envioCorreoFecha;
	}

	public void setEnvioCorreoFecha(Date envioCorreoFecha) {
		this.envioCorreoFecha = envioCorreoFecha;
	}

	public Integer getTamanio() {
		return tamanio;
	}

	public void setTamanio(Integer tamanio) {
		this.tamanio = tamanio;
	}

	public String getClaveDocumento() {
		return claveDocumento;
	}

	public void setClaveDocumento(String claveDocumento) {
		this.claveDocumento = claveDocumento;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getRutaCompleta() {
		return rutaCompleta;
	}

	public void setRutaCompleta(String rutaCompleta) {
		this.rutaCompleta = rutaCompleta;
	}

	public String getNombreGenerado() {
		return nombreGenerado;
	}

	public void setNombreGenerado(String nombreGenerado) {
		this.nombreGenerado = nombreGenerado;
	}

	public String getUnidadReplicacionId() {
		return unidadReplicacionId;
	}

	public void setUnidadReplicacionId(String unidadReplicacionId) {
		this.unidadReplicacionId = unidadReplicacionId;
	}

	public String getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(String sucursalId) {
		this.sucursalId = sucursalId;
	}

	public String getPeriodoId() {
		return periodoId;
	}

	public void setPeriodoId(String periodoId) {
		this.periodoId = periodoId;
	}

	public String getCentroCostoId() {
		return centroCostoId;
	}

	public void setCentroCostoId(String centroCostoId) {
		this.centroCostoId = centroCostoId;
	}

	public String getUnidadNegocioId() {
		return unidadNegocioId;
	}

	public void setUnidadNegocioId(String unidadNegocioId) {
		this.unidadNegocioId = unidadNegocioId;
	}

	public String getTipoContenidoId() {
		return tipoContenidoId;
	}

	public void setTipoContenidoId(String tipoContenidoId) {
		this.tipoContenidoId = tipoContenidoId;
	}

	public String getTipoMimeId() {
		return tipoMimeId;
	}

	public void setTipoMimeId(String tipoMimeId) {
		this.tipoMimeId = tipoMimeId;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFlgVigencia() {
		return flgVigencia;
	}

	public void setFlgVigencia(String flgVigencia) {
		this.flgVigencia = flgVigencia;
	}

	public Date getFechaVigenciaFin() {
		return fechaVigenciaFin;
	}

	public void setFechaVigenciaFin(Date fechaVigenciaFin) {
		this.fechaVigenciaFin = fechaVigenciaFin;
	}

	public Integer getCantidadFirmas() {
		return cantidadFirmas;
	}

	public void setCantidadFirmas(Integer cantidadFirmas) {
		this.cantidadFirmas = cantidadFirmas;
	}

	public String getFlgVer() {
		return flgVer;
	}

	public void setFlgVer(String flgVer) {
		this.flgVer = flgVer;
	}

}
