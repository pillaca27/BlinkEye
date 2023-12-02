package net.royal.spring.rrhh.dominio.lista;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.rrhh.dominio.dto.DtoComunHrCursodescripcion;

public class DtlComunHrCursodescripcion extends DtoComunHrCursodescripcion {
	private String estadoNombre;
	private String areaNombre;
	private String tipoNombre;
	
	/* solo sirve para la paginacion */
	@JsonIgnore
	private BigDecimal ROWNUM_;
	
	public String getEstadoNombre() {
		return estadoNombre;
	}
	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}
	public String getAreaNombre() {
		return areaNombre;
	}
	public void setAreaNombre(String areaNombre) {
		this.areaNombre = areaNombre;
	}
	public String getTipoNombre() {
		return tipoNombre;
	}
	public void setTipoNombre(String tipoNombre) {
		this.tipoNombre = tipoNombre;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
}
