package net.royal.spring.presupuesto.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunBuPeriodo extends DominioTransaccion {
	public Integer periodo;
	public String descripcion;
	public Integer anodesde;
	public Integer anohasta;
	public String estado;	
	public String descripcion2;
	public String descripcion3;
	
	@JsonIgnore
	public BigDecimal periodoOracle;
	
	@JsonIgnore
	public BigDecimal anodesdeOracle;
	
	@JsonIgnore
	public BigDecimal anohastaOracle;
	
	public Integer getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getAnodesde() {
		return anodesde;
	}
	public void setAnodesde(Integer anodesde) {
		this.anodesde = anodesde;
	}
	public Integer getAnohasta() {
		return anohasta;
	}
	public void setAnohasta(Integer anohasta) {
		this.anohasta = anohasta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcion2() {
		return descripcion2;
	}
	public void setDescripcion2(String descripcion2) {
		this.descripcion2 = descripcion2;
	}
	public String getDescripcion3() {
		return descripcion3;
	}
	public void setDescripcion3(String descripcion3) {
		this.descripcion3 = descripcion3;
	}
	
	@JsonIgnore
	public BigDecimal getPeriodoOracle() {
		return periodoOracle;
	}
	
	@JsonIgnore
	public void setPeriodoOracle(BigDecimal periodoOracle) {
		this.periodoOracle = periodoOracle;
		if (periodoOracle!=null)
			this.periodo = periodoOracle.intValue(); 
	}
	
	@JsonIgnore
	public BigDecimal getAnodesdeOracle() {
		return anodesdeOracle;
	}
	
	@JsonIgnore
	public void setAnodesdeOracle(BigDecimal anodesdeOracle) {
		this.anodesdeOracle = anodesdeOracle;
		if (anodesdeOracle!=null)
			this.anodesde = anodesdeOracle.intValue(); 
	}
	
	@JsonIgnore
	public BigDecimal getAnohastaOracle() {
		return anohastaOracle;
	}
	
	@JsonIgnore
	public void setAnohastaOracle(BigDecimal anohastaOracle) {
		this.anohastaOracle = anohastaOracle;
		if (anohastaOracle!=null)
			this.anohasta = anohastaOracle.intValue(); 
	}
	
	
}
