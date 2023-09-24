package net.royal.spring.seguridad.dominio.dto;

import java.math.BigDecimal;
import java.util.List;

public class DtoSeguridadgrupo {

	private String descripcion;
	private String aplicacioncodigo;
	private String grupo;
	private String concepto;
	private String icono;
	//private BigDecimal orden;
	private BigDecimal aplicacionOrden;
	private BigDecimal grupoOrden;
	private BigDecimal conceptoOrden;
	
	private String webgrupo;
	private String id;
	private Integer orden;
	
	
	
	
	
	
	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getWebgrupo() {
		return webgrupo;
	}

	public void setWebgrupo(String webgrupo) {
		this.webgrupo = webgrupo;
	}



	private List<DtoSeguridadconcepto> conceptos;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public List<DtoSeguridadconcepto> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<DtoSeguridadconcepto> conceptos) {
		this.conceptos = conceptos;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public BigDecimal getAplicacionOrden() {
		return aplicacionOrden;
	}

	public void setAplicacionOrden(BigDecimal aplicacionOrden) {
		this.aplicacionOrden = aplicacionOrden;
	}

	public BigDecimal getGrupoOrden() {
		return grupoOrden;
	}

	public void setGrupoOrden(BigDecimal grupoOrden) {
		this.grupoOrden = grupoOrden;
	}

	public BigDecimal getConceptoOrden() {
		return conceptoOrden;
	}

	public void setConceptoOrden(BigDecimal conceptoOrden) {
		this.conceptoOrden = conceptoOrden;
	}

	/*public BigDecimal getOrden() {
		return orden;
	}

	public void setOrden(BigDecimal orden) {
		this.orden = orden;
	}*/
	
}
