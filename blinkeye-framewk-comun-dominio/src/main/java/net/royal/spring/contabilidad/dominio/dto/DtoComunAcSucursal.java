package net.royal.spring.contabilidad.dominio.dto;

import net.royal.spring.contabilidad.dominio.BeanAcSucursal;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;

public class DtoComunAcSucursal extends DominioTransaccion{
	private String sucursal;
	private String descripcionlocal;
	private String descripcioningles;
	private String sucursalgrupo;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String cuentacontabledefecto;
	private String companiasocio;
	private String uuid;
 
	private String almacencodigo;
	private String racionperfil;
	private String departamento;
	private String provincia;
	private String codigopostal;
	private String pais;
	private String direccion;
	private Integer responsable;
	
	private String estadodescripcion;
	private String grupo;

	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public DtoComunAcSucursal() {}
	public DtoComunAcSucursal(String sucursal) {
		this.sucursal=sucursal;
	}
	public DtoComunAcSucursal(String sucursal,String sucursalgrupo) {
		this.sucursal=sucursal;
		this.sucursalgrupo=sucursalgrupo;
	}
	
	
	public String getAlmacencodigo() {
		return almacencodigo;
	}
	public void setAlmacencodigo(String almacencodigo) {
		this.almacencodigo = almacencodigo;
	}
	public String getRacionperfil() {
		return racionperfil;
	}
	public void setRacionperfil(String racionperfil) {
		this.racionperfil = racionperfil;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCodigopostal() {
		return codigopostal;
	}
	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getResponsable() {
		return responsable;
	}
	public void setResponsable(Integer responsable) {
		this.responsable = responsable;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getDescripcionlocal() {
		return descripcionlocal;
	}
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	public String getDescripcioningles() {
		return descripcioningles;
	}
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	public String getSucursalgrupo() {
		return sucursalgrupo;
	}
	public void setSucursalgrupo(String sucursalgrupo) {
		this.sucursalgrupo = sucursalgrupo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getCuentacontabledefecto() {
		return cuentacontabledefecto;
	}
	public void setCuentacontabledefecto(String cuentacontabledefecto) {
		this.cuentacontabledefecto = cuentacontabledefecto;
	}
	public String getCompaniasocio() {
		return companiasocio;
	}
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
  
	
	public BeanAcSucursal obtenerBean() {
		return obtenerBeanCore(new BeanAcSucursal(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanAcSucursal obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanAcSucursal(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanAcSucursal obtenerBeanActualizar(BeanAcSucursal bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanAcSucursal obtenerBeanCore(BeanAcSucursal bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR:
		case ConstantePantallaAccion.NINGUNO:
			bean.getPk().setSucursal(sucursal);
			bean.setSucursalgrupo(sucursalgrupo);
			bean.setDescripcionlocal(descripcionlocal);
			bean.setDescripcioningles(descripcioningles);
			bean.setEstado(estado);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setCuentacontabledefecto(cuentacontabledefecto);
			bean.setAlmacencodigo(almacencodigo);
			bean.setRacionperfil(racionperfil);
			bean.setDepartamento(departamento);
			bean.setProvincia(provincia);
			bean.setCodigopostal(codigopostal);
			bean.setPais(pais);
			bean.setDireccion(direccion);
			bean.setResponsable(responsable);
 			bean.setUuid(uuid);
			break;
		case ConstantePantallaAccion.ACTUALIZAR:
			//bean.getPk().setSucursal(sucursal);
			bean.setSucursalgrupo(sucursalgrupo);
			bean.setDescripcionlocal(descripcionlocal);
			bean.setDescripcioningles(descripcioningles);
			bean.setEstado(estado);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setCuentacontabledefecto(cuentacontabledefecto);
			bean.setAlmacencodigo(almacencodigo);
			bean.setRacionperfil(racionperfil);
			bean.setDepartamento(departamento);
			bean.setProvincia(provincia);
			bean.setCodigopostal(codigopostal);
			bean.setPais(pais);
			bean.setDireccion(direccion);
			bean.setResponsable(responsable);
 			//bean.setUuid(uuid);
			break;
		}

		return bean;
	}
	public String getEstadodescripcion() {
		return estadodescripcion;
	}
	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
}
