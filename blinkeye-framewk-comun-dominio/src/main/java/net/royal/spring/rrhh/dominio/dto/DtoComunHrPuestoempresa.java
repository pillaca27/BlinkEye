package net.royal.spring.rrhh.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;

public class DtoComunHrPuestoempresa extends DominioTransaccion {
	private Integer codigopuesto;
	private String gradosalario;
	private String descripcion;
	private String descripcioningles;
	private String comentarios;
	private String estado;
	private String codigocap;
	private String descripcioncap;
	private String categoriafuncional;
	
	private Integer tiemporeclutar;
	private Integer peso;

	private String descripciondesc;
	private String categorianombre;
	private String gradosalarionombre;
	private String unidadorganigramanombre;
	private Integer unidadorganigrama;
	private String orden;
	private List<DtoTabla> unidades;

	private BigDecimal salariominimo;
	
	public DtoComunHrPuestoempresa() {
		this.unidades = new ArrayList<DtoTabla>();
	}
	public DtoComunHrPuestoempresa(Integer codigopuesto) {
		this.codigopuesto=codigopuesto;
		this.unidades = new ArrayList<DtoTabla>();
	}
	
	public Integer getCodigopuesto() {
		return codigopuesto;
	}

	public void setCodigopuesto(Integer codigopuesto) {
		this.codigopuesto = codigopuesto;
	}

	public String getGradosalario() {
		return gradosalario;
	}

	public void setGradosalario(String gradosalario) {
		this.gradosalario = gradosalario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcioningles() {
		return descripcioningles;
	}

	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigocap() {
		return codigocap;
	}

	public void setCodigocap(String codigocap) {
		this.codigocap = codigocap;
	}

	public String getDescripcioncap() {
		return descripcioncap;
	}

	public void setDescripcioncap(String descripcioncap) {
		this.descripcioncap = descripcioncap;
	}

	public String getCategoriafuncional() {
		return categoriafuncional;
	}

	public void setCategoriafuncional(String categoriafuncional) {
		this.categoriafuncional = categoriafuncional;
	}
	
	public Integer getTiemporeclutar() {
		return tiemporeclutar;
	}
	
	public void setTiemporeclutar(Integer tiemporeclutar) {
		this.tiemporeclutar = tiemporeclutar;
	}
	
	public Integer getPeso() {
		return peso;
	}
	
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	public String getDescripciondesc() {
		return descripciondesc;
	}
	
	public void setDescripciondesc(String descripciondesc) {
		this.descripciondesc = descripciondesc;
	}
	
	public String getCategorianombre() {
		return categorianombre;
	}
	
	public void setCategorianombre(String categorianombre) {
		this.categorianombre = categorianombre;
	}
	
	public String getGradosalarionombre() {
		return gradosalarionombre;
	}
	
	public void setGradosalarionombre(String gradosalarionombre) {
		this.gradosalarionombre = gradosalarionombre;
	}
	
	public String getUnidadorganigramanombre() {
		return unidadorganigramanombre;
	}
	
	public void setUnidadorganigramanombre(String unidadorganigramanombre) {
		this.unidadorganigramanombre = unidadorganigramanombre;
	}
	
	public Integer getUnidadorganigrama() {
		return unidadorganigrama;
	}
	
	public void setUnidadorganigrama(Integer unidadorganigrama) {
		this.unidadorganigrama = unidadorganigrama;
	}
	
	public String getOrden() {
		return orden;
	}
	
	public void setOrden(String orden) {
		this.orden = orden;
	}
	
	public List<DtoTabla> getUnidades() {
		return unidades;
	}
	
	public void setUnidades(List<DtoTabla> unidades) {
		this.unidades = unidades;
	}
	
	public BigDecimal getSalariominimo() {
		return salariominimo;
	}
	
	public void setSalariominimo(BigDecimal salariominimo) {
		this.salariominimo = salariominimo;
	}
	
}
