package net.royal.spring.util.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DtoComunArbol {

	public DtoComunArbol() {
		children = new ArrayList<DtoComunArbol>();
	}

	private String label;
	private String data;
	private String expandedIcon;
	private String collapsedIcon;
	private String companiasocio;
	
	private String descripcionlocal;
	private String descripcionlocal_1;
	private String linea;
	private String familia;
	private String descripcionlocal_2;
	private String subfamilia;
	private String tipo;
	private String lineaCompleta;
	private String familiaCompleta;
	private String subfamiliaCompleta;
	
	
	private String grupolinea;
	private String subfamiliadescripcion;
	private String familiadescripcion;
	private String lineadescripcion;

	private List<DtoComunArbol> children;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getExpandedIcon() {
		return expandedIcon;
	}

	public void setExpandedIcon(String expandedIcon) {
		this.expandedIcon = expandedIcon;
	}

	public String getCollapsedIcon() {
		return collapsedIcon;
	}

	public void setCollapsedIcon(String collapsedIcon) {
		this.collapsedIcon = collapsedIcon;
	}

	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}

	public String getDescripcionlocal_1() {
		return descripcionlocal_1;
	}

	public void setDescripcionlocal_1(String descripcionlocal_1) {
		this.descripcionlocal_1 = descripcionlocal_1;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getDescripcionlocal_2() {
		return descripcionlocal_2;
	}

	public void setDescripcionlocal_2(String descripcionlocal_2) {
		this.descripcionlocal_2 = descripcionlocal_2;
	}

	public String getSubfamilia() {
		return subfamilia;
	}

	public void setSubfamilia(String subfamilia) {
		this.subfamilia = subfamilia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLineaCompleta() {
		return lineaCompleta;
	}

	public void setLineaCompleta(String lineaCompleta) {
		this.lineaCompleta = lineaCompleta;
	}

	public String getFamiliaCompleta() {
		return familiaCompleta;
	}

	public void setFamiliaCompleta(String familiaCompleta) {
		this.familiaCompleta = familiaCompleta;
	}

	public String getSubfamiliaCompleta() {
		return subfamiliaCompleta;
	}

	public void setSubfamiliaCompleta(String subfamiliaCompleta) {
		this.subfamiliaCompleta = subfamiliaCompleta;
	}

	public List<DtoComunArbol> getChildren() {
		return children;
	}

	public void setChildren(List<DtoComunArbol> children) {
		this.children = children;
	}

	public String getGrupolinea() {
		return grupolinea;
	}

	public void setGrupolinea(String grupolinea) {
		this.grupolinea = grupolinea;
	}

	public String getSubfamiliadescripcion() {
		return subfamiliadescripcion;
	}

	public void setSubfamiliadescripcion(String subfamiliadescripcion) {
		this.subfamiliadescripcion = subfamiliadescripcion;
	}

	public String getFamiliadescripcion() {
		return familiadescripcion;
	}

	public void setFamiliadescripcion(String familiadescripcion) {
		this.familiadescripcion = familiadescripcion;
	}

	public String getLineadescripcion() {
		return lineadescripcion;
	}

	public void setLineadescripcion(String lineadescripcion) {
		this.lineadescripcion = lineadescripcion;
	}
	
}
