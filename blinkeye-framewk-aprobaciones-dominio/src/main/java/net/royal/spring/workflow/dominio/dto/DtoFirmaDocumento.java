package net.royal.spring.workflow.dominio.dto;

import java.math.BigDecimal;
import java.sql.Blob;

public class DtoFirmaDocumento {
	private Integer id;
	private Integer nroFirma;
	private String nombre;
	private String archivo;
	private byte[] blob;

	public Integer getId() {
		return id;
	}

	public void setId(Object id) {
		if (id instanceof BigDecimal) {
			this.id = ((BigDecimal) id).intValue();
		} else {
			this.id = (Integer) id;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getBlob() {
		return blob;
	}

	public void setBlob(byte[] blob) {
		this.blob = blob;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public Integer getNroFirma() {
		return nroFirma;
	}

	public void setNroFirma(Object nroFirma) {
		if (nroFirma instanceof BigDecimal) {
			this.nroFirma = ((BigDecimal) nroFirma).intValue();
		} else {
			this.nroFirma = (Integer) nroFirma;
		}
	}

}
