package net.royal.spring.framework.modelo.generico;

import java.util.List;

public class DominioExportar {
	private String formato;
	private List lstDatos;
	private String[] arrColumnas;

	private String[] arrCabeceras;
	private String titulo;
	private boolean vertical = Boolean.FALSE;
	private String imagenReporte;
	private String imagenFirma;

	public DominioExportar() {}
	public DominioExportar(String formato, List lstDatos, String[] arrColumnas) {
		this.formato=formato;
		this.lstDatos=lstDatos;
		this.arrColumnas=arrColumnas;
	}
	
	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public List getLstDatos() {
		return lstDatos;
	}

	public void setLstDatos(List lstDatos) {
		this.lstDatos = lstDatos;
	}

	public String[] getArrColumnas() {
		return arrColumnas;
	}

	public void setArrColumnas(String[] arrColumnas) {
		this.arrColumnas = arrColumnas;
	}

	public String[] getArrCabeceras() {
		return arrCabeceras;
	}

	public void setArrCabeceras(String[] arrCabeceras) {
		this.arrCabeceras = arrCabeceras;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public boolean isVertical() {
		return vertical;
	}

	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}

	public String getImagenReporte() {
		return imagenReporte;
	}

	public void setImagenReporte(String imagenReporte) {
		this.imagenReporte = imagenReporte;
	}

	public String getImagenFirma() {
		return imagenFirma;
	}

	public void setImagenFirma(String imagenFirma) {
		this.imagenFirma = imagenFirma;
	}
}
