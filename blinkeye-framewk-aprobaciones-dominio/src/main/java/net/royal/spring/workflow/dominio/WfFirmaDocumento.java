package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "WF_FIRMA_DOCUMENTO", schema = "sgworkflowsys")
public class WfFirmaDocumento implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfFirmaDocumentoPk pk;

	@Column(name = "ARCHIVO_ORIGINAL", nullable = true)
	private byte[] arhivoOriginal;
	@Column(name = "ARCHIVO_RESULTADO", nullable = true)
	private byte[] archivoResultado;
	@Column(name = "ARCHIVO_NOMBRE", nullable = true)
	private String archivoNombre;
	@Column(name = "DESTINO_ID", nullable = true)
	private String destinoId;
	@Column(name = "DESTINO_CAMPO", nullable = true)
	private String destinoCampo;
	@Column(name = "DESTINO_TABLA", nullable = true)
	private String destinoTabla;
	@Column(name = "DESTINO_ARCHIVO", nullable = true)
	private String destinoArchivo;
	@Column(name = "ID_DESTINO", nullable = true)
	private String idDestino;

	@Column(name = "ESTADO_MENSAJE", nullable = true)
	private String estadoMensaje;

	@Column(name = "ESTADO", length = 4, nullable = true)
	private String estado;

	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 100)
	@Column(name = "ULTIMOUSUARIO", length = 100, nullable = true)
	private String ultimousuario;
	
	@Column(name = "NRO_FIRMA")
	private Integer nroFirma;

	public WfFirmaDocumento() {
		pk = new WfFirmaDocumentoPk();
	}

	public WfFirmaDocumento(WfFirmaDocumentoPk pk) {
		this.pk = pk;
	}

	public WfFirmaDocumentoPk getPk() {
		return pk;
	}

	public void setPk(WfFirmaDocumentoPk pk) {
		this.pk = pk;
	}

	public byte[] getArhivoOriginal() {
		return arhivoOriginal;
	}

	public void setArhivoOriginal(byte[] arhivoOriginal) {
		this.arhivoOriginal = arhivoOriginal;
	}

	public byte[] getArchivoResultado() {
		return archivoResultado;
	}

	public void setArchivoResultado(byte[] archivoResultado) {
		this.archivoResultado = archivoResultado;
	}

	public String getArchivoNombre() {
		return archivoNombre;
	}

	public void setArchivoNombre(String archivoNombre) {
		this.archivoNombre = archivoNombre;
	}

	public String getDestinoId() {
		return destinoId;
	}

	public void setDestinoId(String destinoId) {
		this.destinoId = destinoId;
	}

	public String getDestinoCampo() {
		return destinoCampo;
	}

	public void setDestinoCampo(String destinoCampo) {
		this.destinoCampo = destinoCampo;
	}

	public String getDestinoTabla() {
		return destinoTabla;
	}

	public void setDestinoTabla(String destinoTabla) {
		this.destinoTabla = destinoTabla;
	}

	public String getDestinoArchivo() {
		return destinoArchivo;
	}

	public void setDestinoArchivo(String destinoArchivo) {
		this.destinoArchivo = destinoArchivo;
	}

	public String getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(String idDestino) {
		this.idDestino = idDestino;
	}

	public String getEstadoMensaje() {
		return estadoMensaje;
	}

	public void setEstadoMensaje(String estadoMensaje) {
		this.estadoMensaje = estadoMensaje;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}

	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}

	public Integer getNroFirma() {
		return nroFirma;
	}

	public void setNroFirma(Integer nroFirma) {
		this.nroFirma = nroFirma;
	}

}
