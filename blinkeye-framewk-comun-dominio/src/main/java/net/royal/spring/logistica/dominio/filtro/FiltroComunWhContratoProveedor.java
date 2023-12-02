package net.royal.spring.logistica.dominio.filtro;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunWhContratoProveedor {
	private Integer idEmpleado;
	private String compania;
	private String numerocontrato;
	private DominioPaginacion paginacion;
	private Date hasta;
	private String w_tipo01;
	private String w_tipo02;
	private String numeroorden;
	private Integer evaluacionid;
	private Integer comprador;
	private String codigocosto;
	private Date fechahasta;
	private Date fechadesde;

	public String getCodigocosto() {
		return codigocosto;
	}

	public void setCodigocosto(String codigocosto) {
		this.codigocosto = codigocosto;
	}

	public Integer getComprador() {
		return comprador;
	}

	public void setComprador(Integer comprador) {
		this.comprador = comprador;
	}

	public Integer getEvaluacionid() {
		return evaluacionid;
	}

	public void setEvaluacionid(Integer evaluacionid) {
		this.evaluacionid = evaluacionid;
	}

	public String getNumeroorden() {
		return numeroorden;
	}

	public void setNumeroorden(String numeroorden) {
		this.numeroorden = numeroorden;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getNumerocontrato() {
		return numerocontrato;
	}

	public void setNumerocontrato(String numerocontrato) {
		this.numerocontrato = numerocontrato;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public String getW_tipo01() {
		return w_tipo01;
	}

	public void setW_tipo01(String w_tipo01) {
		this.w_tipo01 = w_tipo01;
	}

	public String getW_tipo02() {
		return w_tipo02;
	}

	public void setW_tipo02(String w_tipo02) {
		this.w_tipo02 = w_tipo02;
	}

	public Date getFechahasta() {
		return fechahasta;
	}

	public void setFechahasta(Date fechahasta) {
		this.fechahasta = fechahasta;
	}

	public Date getFechadesde() {
		return fechadesde;
	}

	public void setFechadesde(Date fechadesde) {
		this.fechadesde = fechadesde;
	}

}
