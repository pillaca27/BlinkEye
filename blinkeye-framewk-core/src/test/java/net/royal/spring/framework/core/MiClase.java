package net.royal.spring.framework.core;

import java.math.BigDecimal;
import java.util.Date;

public class MiClase {
	private MiClasePk pk;
	private String id;
	private String nombre;
	private String dni;
	
	private Integer edad;
	private Integer hijos;
	
	private BigDecimal sueldo;
	private BigDecimal descuento;
	
	private Date fechaNacimiento;
	private Date fechaAniversario;
	
	private double impuesto;
	private double igv;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public BigDecimal getSueldo() {
		return sueldo;
	}
	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Integer getHijos() {
		return hijos;
	}
	public void setHijos(Integer hijos) {
		this.hijos = hijos;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	public Date getFechaAniversario() {
		return fechaAniversario;
	}
	public void setFechaAniversario(Date fechaAniversario) {
		this.fechaAniversario = fechaAniversario;
	}
	public double getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}
	public double getIgv() {
		return igv;
	}
	public void setIgv(double igv) {
		this.igv = igv;
	}
	public MiClasePk getPk() {
		return pk;
	}
	public void setPk(MiClasePk pk) {
		this.pk = pk;
	}	
}
