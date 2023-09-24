package net.royal.spring.framework.modelo.generico;

import java.io.File;
import java.net.URLConnection;

public class DominioArchivo {
	private String mimeContentType;
	private String nombreArchivo;
	private byte[] archivoAdjuntoBytes;
	private String archivoAdjuntoBase64;
	private String rutaCompletaArchivo;
	

	public DominioArchivo() {
	}

	public DominioArchivo(String nombreArchivo, byte[] archivoAdjunto) {
		this.setNombreArchivo(nombreArchivo);
		this.setArchivoAdjuntoBytes(archivoAdjunto);
	}

	public DominioArchivo(String nombreArchivo, String rutaCompletaArchivo, byte[] archivoAdjunto) {
		this.setNombreArchivo(nombreArchivo);
		this.setArchivoAdjuntoBytes(archivoAdjunto);
		this.setRutaCompletaArchivo(rutaCompletaArchivo);
	}
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	public String getRutaCompletaArchivo() {
		return rutaCompletaArchivo;
	}

	public void setRutaCompletaArchivo(String rutaCompletaArchivo) {
		this.rutaCompletaArchivo = rutaCompletaArchivo;
	}

	public String getArchivoAdjuntoBase64() {
		return archivoAdjuntoBase64;
	}

	public void setArchivoAdjuntoBase64(String archivoAdjuntoBase64) {
		this.archivoAdjuntoBase64 = archivoAdjuntoBase64;
	}

	public byte[] getArchivoAdjuntoBytes() {
		return archivoAdjuntoBytes;
	}

	public void setArchivoAdjuntoBytes(byte[] archivoAdjuntoBytes) {
		this.archivoAdjuntoBytes = archivoAdjuntoBytes;
	}

	public String getMimeContentType() {
		return mimeContentType;
	}

	public void setMimeContentType(String mimeContentType) {
		this.mimeContentType = mimeContentType;
	}
	
}
