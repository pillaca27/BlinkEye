package net.royal.spring.asistencia.dominio.dto;

public class DtoComunAsistenciaEmpleadosUltimoDia {
	private String fecha;
	private String desde;
	private String hasta;
	private String conceptosistema;
	private String descripcion;
	private String conceptoacceso;
	private String descripcionlocal;
	private String cantidad;
	public DtoComunAsistenciaEmpleadosUltimoDia() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DtoComunAsistenciaEmpleadosUltimoDia(String fecha, String desde, String hasta, String conceptosistema,
			String descripcion, String conceptoacceso, String descripcionlocal, String cantidad) {
		super();
		this.fecha = fecha;
		this.desde = desde;
		this.hasta = hasta;
		this.conceptosistema = conceptosistema;
		this.descripcion = descripcion;
		this.conceptoacceso = conceptoacceso;
		this.descripcionlocal = descripcionlocal;
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "AsistenciaEmpleadosUltimoDia [fecha=" + fecha + ", desde=" + desde + ", hasta=" + hasta
				+ ", conceptosistema=" + conceptosistema + ", descripcion=" + descripcion + ", conceptoacceso="
				+ conceptoacceso + ", descripcionlocal=" + descripcionlocal + ", cantidad=" + cantidad + "]";
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDesde() {
		return desde;
	}
	public void setDesde(String desde) {
		this.desde = desde;
	}
	public String getHasta() {
		return hasta;
	}
	public void setHasta(String hasta) {
		this.hasta = hasta;
	}
	public String getConceptosistema() {
		return conceptosistema;
	}
	public void setConceptosistema(String conceptosistema) {
		this.conceptosistema = conceptosistema;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getConceptoacceso() {
		return conceptoacceso;
	}
	public void setConceptoacceso(String conceptoacceso) {
		this.conceptoacceso = conceptoacceso;
	}
	public String getDescripcionlocal() {
		return descripcionlocal;
	}
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	
}
