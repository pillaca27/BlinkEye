package net.royal.spring.framework.modelo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class EmpleadomastTransaccion extends PersonamastTransaccion{
	private Integer empleado;
	private String estadoEmpleado;
	private String estadoEmpleadoNombre;
	private String centrocostos;
	private String centrocostosNombre;	
	private String unidadnegocioasignada;	
	private String unidadnegocioasignadaNombre;
	private String companiasocio;
	private String companiasocioNombre;	
	private String sucursal;	
	private String sucursalnombre;	
	private Date fechaingreso;	
	private Date fechainiciocontrato;	
	private Date fechafincontrato;
	private Integer tipohorario;
	private BigDecimal sueldoactuallocal;
	private Integer codigocargo;
	private String codigocargoNombre;
	private String tipocontrato;
	private String tipocontratoNombre;	
	private String tipoplanilla;
	private String tipoplanillaNombre;
	private String cargo;
	private String cargoNombre;	
	private String deptoorganizacion;
	private String deptoorganizacionNombre;
	private String correointerno;
	
	private String unidadoperativa;	
	private String unidadoperativaNombre;
	private Integer motivocese;
	private Date fechacese;
	private String correoElectronicoOtros;
	
	public EmpleadomastTransaccion() {}
	public EmpleadomastTransaccion(Integer empleado) {
		this.empleado=empleado;
		this.setPersona(empleado);
	}
	public EmpleadomastTransaccion(Integer empleado,String companiasocio) {
		this.empleado=empleado;
		this.companiasocio=companiasocio;
		this.setPersona(empleado);
	}
	
	public String getUnidadoperativa() {
		return unidadoperativa;
	}
	public void setUnidadoperativa(String unidadoperativa) {
		this.unidadoperativa = unidadoperativa;
	}
	public String getUnidadoperativaNombre() {
		return unidadoperativaNombre;
	}
	public void setUnidadoperativaNombre(String unidadoperativaNombre) {
		this.unidadoperativaNombre = unidadoperativaNombre;
	}
	public Integer getMotivocese() {
		return motivocese;
	}
	public void setMotivocese(Integer motivocese) {
		this.motivocese = motivocese;
	}
	public Date getFechacese() {
		return fechacese;
	}
	public void setFechacese(Date fechacese) {
		this.fechacese = fechacese;
	}
	public String getCorreoElectronicoOtros() {
		return correoElectronicoOtros;
	}
	public void setCorreoElectronicoOtros(String correoElectronicoOtros) {
		this.correoElectronicoOtros = correoElectronicoOtros;
	}
	public String getCorreointerno() {
		return correointerno;
	}
	public void setCorreointerno(String correointerno) {
		this.correointerno = correointerno;
	}
	public Integer getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}
	public String getEstadoEmpleado() {
		return estadoEmpleado;
	}
	public void setEstadoEmpleado(String estadoEmpleado) {
		this.estadoEmpleado = estadoEmpleado;
	}
	public String getEstadoEmpleadoNombre() {
		return estadoEmpleadoNombre;
	}
	public void setEstadoEmpleadoNombre(String estadoEmpleadoNombre) {
		this.estadoEmpleadoNombre = estadoEmpleadoNombre;
	}
	public String getCentrocostos() {
		return centrocostos;
	}
	public void setCentrocostos(String centrocostos) {
		this.centrocostos = centrocostos;
	}
	public String getCentrocostosNombre() {
		return centrocostosNombre;
	}
	public void setCentrocostosNombre(String centrocostosNombre) {
		this.centrocostosNombre = centrocostosNombre;
	}
	public String getUnidadnegocioasignada() {
		return unidadnegocioasignada;
	}
	public void setUnidadnegocioasignada(String unidadnegocioasignada) {
		this.unidadnegocioasignada = unidadnegocioasignada;
	}
	public String getUnidadnegocioasignadaNombre() {
		return unidadnegocioasignadaNombre;
	}
	public void setUnidadnegocioasignadaNombre(String unidadnegocioasignadaNombre) {
		this.unidadnegocioasignadaNombre = unidadnegocioasignadaNombre;
	}
	public String getCompaniasocio() {
		return companiasocio;
	}
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	public String getCompaniasocioNombre() {
		return companiasocioNombre;
	}
	public void setCompaniasocioNombre(String companiasocioNombre) {
		this.companiasocioNombre = companiasocioNombre;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getSucursalnombre() {
		return sucursalnombre;
	}
	public void setSucursalnombre(String sucursalnombre) {
		this.sucursalnombre = sucursalnombre;
	}
	public Date getFechaingreso() {
		return fechaingreso;
	}
	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}
	public Date getFechainiciocontrato() {
		return fechainiciocontrato;
	}
	public void setFechainiciocontrato(Date fechainiciocontrato) {
		this.fechainiciocontrato = fechainiciocontrato;
	}
	public Date getFechafincontrato() {
		return fechafincontrato;
	}
	public void setFechafincontrato(Date fechafincontrato) {
		this.fechafincontrato = fechafincontrato;
	}
	public Integer getTipohorario() {
		return tipohorario;
	}
	public void setTipohorario(Integer tipohorario) {
		this.tipohorario = tipohorario;
	}
	public BigDecimal getSueldoactuallocal() {
		return sueldoactuallocal;
	}
	public void setSueldoactuallocal(BigDecimal sueldoactuallocal) {
		this.sueldoactuallocal = sueldoactuallocal;
	}
	public Integer getCodigocargo() {
		return codigocargo;
	}
	public void setCodigocargo(Integer codigocargo) {
		this.codigocargo = codigocargo;
	}
	public String getCodigocargoNombre() {
		return codigocargoNombre;
	}
	public void setCodigocargoNombre(String codigocargoNombre) {
		this.codigocargoNombre = codigocargoNombre;
	}
	public String getTipocontrato() {
		return tipocontrato;
	}
	public void setTipocontrato(String tipocontrato) {
		this.tipocontrato = tipocontrato;
	}
	public String getTipocontratoNombre() {
		return tipocontratoNombre;
	}
	public void setTipocontratoNombre(String tipocontratoNombre) {
		this.tipocontratoNombre = tipocontratoNombre;
	}
	public String getTipoplanilla() {
		return tipoplanilla;
	}
	public void setTipoplanilla(String tipoplanilla) {
		this.tipoplanilla = tipoplanilla;
	}
	public String getTipoplanillaNombre() {
		return tipoplanillaNombre;
	}
	public void setTipoplanillaNombre(String tipoplanillaNombre) {
		this.tipoplanillaNombre = tipoplanillaNombre;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCargoNombre() {
		return cargoNombre;
	}
	public void setCargoNombre(String cargoNombre) {
		this.cargoNombre = cargoNombre;
	}
	public String getDeptoorganizacion() {
		return deptoorganizacion;
	}
	public void setDeptoorganizacion(String deptoorganizacion) {
		this.deptoorganizacion = deptoorganizacion;
	}
	public String getDeptoorganizacionNombre() {
		return deptoorganizacionNombre;
	}
	public void setDeptoorganizacionNombre(String deptoorganizacionNombre) {
		this.deptoorganizacionNombre = deptoorganizacionNombre;
	}
}
