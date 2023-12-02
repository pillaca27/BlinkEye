package net.royal.spring.core.dominio.dto;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.core.dominio.BeanTiposervicio;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;

public class DtoComunTiposervicio extends DominioTransaccion{
	private String tiposervicio;
	private String descripcion;
	private String regimenfiscal;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private byte[] timestamp;
	private String clasificacionfiscal;
	private String estado;
	private String descripcioningles;
	private String uuid;	
	
	
	private List<DtoComunServicioximpuesto> lstDetalle=new ArrayList<DtoComunServicioximpuesto>();
	public List<DtoComunServicioximpuesto> getLstDetalle() {
		return lstDetalle;
	}

	
	public void setLstDetalle(List<DtoComunServicioximpuesto> lstDetalle) {
		this.lstDetalle = lstDetalle;
	}
	
	

	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	/**
	 * 
	 * 
	 * @campo TipoServicio
	*/
	public String getTiposervicio() {
		return tiposervicio;
	}

	/**
	 * 
	 * 
	 * @campo TipoServicio
	*/
	public void setTiposervicio(String tiposervicio) {
		this.tiposervicio = tiposervicio;
	}
	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * 
	 * 
	 * @campo RegimenFiscal
	*/
	public String getRegimenfiscal() {
		return regimenfiscal;
	}

	/**
	 * 
	 * 
	 * @campo RegimenFiscal
	*/
	public void setRegimenfiscal(String regimenfiscal) {
		this.regimenfiscal = regimenfiscal;
	}
	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * 
	 * 
	 * @campo Timestamp
	*/
	public byte[] getTimestamp() {
		return timestamp;
	}

	/**
	 * 
	 * 
	 * @campo Timestamp
	*/
	public void setTimestamp(byte[] timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * 
	 * 
	 * @campo ClasificacionFiscal
	*/
	public String getClasificacionfiscal() {
		return clasificacionfiscal;
	}

	/**
	 * 
	 * 
	 * @campo ClasificacionFiscal
	*/
	public void setClasificacionfiscal(String clasificacionfiscal) {
		this.clasificacionfiscal = clasificacionfiscal;
	}
	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionIngles
	*/
	public String getDescripcioningles() {
		return descripcioningles;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionIngles
	*/
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	public BeanTiposervicio obtenerBean() {
		return obtenerBeanCore(new BeanTiposervicio(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanTiposervicio obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanTiposervicio(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanTiposervicio obtenerBeanActualizar(BeanTiposervicio bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanTiposervicio obtenerBeanCore(BeanTiposervicio bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR:
		case ConstantePantallaAccion.NINGUNO:
			bean.getPk().setTiposervicio(tiposervicio);
			bean.setDescripcion(descripcion);
			bean.setRegimenfiscal(regimenfiscal);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setClasificacionfiscal(clasificacionfiscal);
			bean.setEstado(estado);
			bean.setDescripcioningles(descripcioningles);
			bean.setUuid(uuid);
			break;
		case ConstantePantallaAccion.ACTUALIZAR:
			//bean.getPk().setUnidadnegocio(unidadnegocio);
			bean.setDescripcion(descripcion);
			bean.setRegimenfiscal(regimenfiscal);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setClasificacionfiscal(clasificacionfiscal);
			bean.setEstado(estado);
			bean.setDescripcioningles(descripcioningles);
			//bean.setUuid(uuid);
			break;
		}

		return bean;
	}

}
