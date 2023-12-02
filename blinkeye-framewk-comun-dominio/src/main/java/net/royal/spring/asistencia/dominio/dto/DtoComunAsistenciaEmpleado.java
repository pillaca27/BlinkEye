package net.royal.spring.asistencia.dominio.dto;

public class DtoComunAsistenciaEmpleado{
	private String companiasocio;
	private String empleado;
	private String fecha;
	public DtoComunAsistenciaEmpleado() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DtoComunAsistenciaEmpleado(String companiasocio, String empleado, String fecha) {
		super();
		this.companiasocio = companiasocio;
		this.empleado = empleado;
		this.fecha = fecha;
	}
	public String getCompaniasocio() {
		return companiasocio;
	}
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
}
