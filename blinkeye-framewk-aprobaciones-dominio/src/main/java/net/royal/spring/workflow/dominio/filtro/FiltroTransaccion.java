package net.royal.spring.workflow.dominio.filtro;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;

public class FiltroTransaccion extends DtoTabla {

	private static final long serialVersionUID = 1L;
	private Integer transaccionId;
	private Date desde;
	private Date hasta;
	private String proceso;
	private String aplicacion;
	private String referencia;
	private DominioPaginacion paginacion;
	private String compania;
	private String area;
	private Double montodesde;
	private Double montohasta;
	private String estadoProceso;
	private Integer idPersonaSolicitante2;
	private Integer solicitante;
	private String nombreSolicitante;

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Double getMontodesde() {
		return montodesde;
	}

	public void setMontodesde(Double montodesde) {
		this.montodesde = montodesde;
	}

	public Double getMontohasta() {
		return montohasta;
	}

	public void setMontohasta(Double montohasta) {
		this.montohasta = montohasta;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	public Integer getIdPersonaSolicitante2() {
		return idPersonaSolicitante2;
	}

	public void setIdPersonaSolicitante2(Integer idPersonaSolicitante2) {
		this.idPersonaSolicitante2 = idPersonaSolicitante2;
	}

	public Integer getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}

	public Integer getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Integer solicitante) {
		this.solicitante = solicitante;
	}

	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

}
