package net.royal.spring.core.dominio.dto;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.core.dominio.BeanDepartamento;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;

public class DtoComunDepartamento  extends DominioTransaccion{
	
	private String departamento;
	private String descripcioncorta;
	private String descripcionlarga;
	private String pais;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private List<DtoComunProvincia> provincias = new ArrayList<DtoComunProvincia>();
	private List<DtoComunZonapostal> distritos= new ArrayList<DtoComunZonapostal>();
	private String accion;
	private String provincia;
	private String uuid;
	public DtoComunDepartamento() {
		this.provincias = new ArrayList<DtoComunProvincia>();
		this.distritos= new ArrayList<DtoComunZonapostal>();
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public List<DtoComunZonapostal> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<DtoComunZonapostal> distritos) {
		this.distritos = distritos;
	}

	public List<DtoComunProvincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<DtoComunProvincia> provincias) {
		this.provincias = provincias;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getDescripcioncorta() {
		return descripcioncorta;
	}
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}
	public String getDescripcionlarga() {
		return descripcionlarga;
	}
	public void setDescripcionlarga(String descripcionlarga) {
		this.descripcionlarga = descripcionlarga;
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
	
	  
		public BeanDepartamento obtenerBean() {
			return obtenerBeanCore(new BeanDepartamento(),ConstantePantallaAccion.NINGUNO);
		}

		public BeanDepartamento obtenerBeanRegistrar() {
			return obtenerBeanCore(new BeanDepartamento(),ConstantePantallaAccion.INSERTAR);
		}

		public BeanDepartamento obtenerBeanActualizar(BeanDepartamento bean) {
			return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
		}

		private BeanDepartamento obtenerBeanCore(BeanDepartamento bean,String tipo) {
			if (UString.esNuloVacio(tipo))
				tipo=ConstantePantallaAccion.NINGUNO;

			bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
			bean.setAuxFlgValidado(this.getAuxFlgValidado());

			switch (tipo) {
			case ConstantePantallaAccion.INSERTAR:
			case ConstantePantallaAccion.NINGUNO:
				bean.getPk().setDepartamento(departamento);
				bean.setDescripcioncorta(descripcioncorta);
				bean.setDescripcionlarga(descripcionlarga);
				bean.setPais(pais);
				bean.setEstado(estado);
				bean.setUltimousuario(ultimousuario);
				bean.setUltimafechamodif(ultimafechamodif);
	 			bean.setUuid(uuid);
				break;
			case ConstantePantallaAccion.ACTUALIZAR:
				//bean.getPk().setDepartamento(departamento);
				bean.setDescripcioncorta(descripcioncorta);
				bean.setDescripcionlarga(descripcionlarga);
				bean.setPais(pais);
				bean.setEstado(estado);
				bean.setUltimousuario(ultimousuario);
				bean.setUltimafechamodif(ultimafechamodif);
	 			//bean.setUuid(uuid);
				break;
			}

			return bean;
		}

}
