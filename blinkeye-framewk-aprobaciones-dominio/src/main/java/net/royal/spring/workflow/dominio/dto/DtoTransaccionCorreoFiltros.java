package net.royal.spring.workflow.dominio.dto;

public class DtoTransaccionCorreoFiltros {

	private String tipo_consulta;
	private Integer flujo_id;
	private String tipoExportar;
	
	private String proceso_id;
	private Integer version_id;
	private Integer transaccion_id;
	
	private String transaccionUUID;
	private String procesoUUID;
	
	public String getTipo_consulta() {
		return tipo_consulta;
	}

	public void setTipo_consulta(String tipo_consulta) {
		this.tipo_consulta = tipo_consulta;
	}
	public String getProceso_id() {
		return proceso_id;
	}
	public void setProceso_id(String proceso_id) {
		this.proceso_id = proceso_id;
	}
	public Integer getVersion_id() {
		return version_id;
	}
	public void setVersion_id(Integer version_id) {
		this.version_id = version_id;
	}
	public Integer getTransaccion_id() {
		return transaccion_id;
	}
	public void setTransaccion_id(Integer transaccion_id) {
		this.transaccion_id = transaccion_id;
	}
	public Integer getFlujo_id() {
		return flujo_id;
	}

	public void setFlujo_id(Integer flujo_id) {
		this.flujo_id = flujo_id;
	}

	public String getTipoExportar() {
		return tipoExportar;
	}

	public void setTipoExportar(String tipoExportar) {
		this.tipoExportar = tipoExportar;
	}
	
	public String getTransaccionUUID() {
		return transaccionUUID;
	}

	public void setTransaccionUUID(String transaccionUUID) {
		this.transaccionUUID = transaccionUUID;
	}

	public String getProcesoUUID() {
		return procesoUUID;
	}

	public void setProcesoUUID(String procesoUUID) {
		this.procesoUUID = procesoUUID;
	}

}
