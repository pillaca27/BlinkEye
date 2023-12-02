package net.royal.spring.kpi.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.kpi.dominio.BeanRtFormatogrupo;

/**
 * 
 * 
 * @tabla dbo.RT_FormatoGrupo
*/
public class DtoComunRtFormatogrupo extends DominioTransaccion implements java.io.Serializable{


	private Integer fmtcodigo;
	private Integer grpcodigo;
	private String grpdescripcion;
	private String grpusuario;
	private java.util.Date grpultimamodificacion;
	private Integer grporden;
	private String flagdashboard;

	
	private Integer asignado;
	private Boolean asignadoboolean;
	private String flgActualizar;
	
	
	
	public Boolean getAsignadoboolean() {
		return asignadoboolean;
	}

	public void setAsignadoboolean(Boolean asignadoboolean) {
		this.asignadoboolean = asignadoboolean;
	}

	public String getFlgActualizar() {
		return flgActualizar;
	}

	public void setFlgActualizar(String flgActualizar) {
		this.flgActualizar = flgActualizar;
	}

	public Integer getAsignado() {
		return asignado;
	}

	public void setAsignado(Integer asignado) {
		this.asignado = asignado;
	}

	public Integer getFmtcodigo() {
		return fmtcodigo;
	}

	public void setFmtcodigo(Integer fmtcodigo) {
		this.fmtcodigo = fmtcodigo;
	}
	public Integer getGrpcodigo() {
		return grpcodigo;
	}

	public void setGrpcodigo(Integer grpcodigo) {
		this.grpcodigo = grpcodigo;
	}
	public String getGrpdescripcion() {
		return grpdescripcion;
	}

	public void setGrpdescripcion(String grpdescripcion) {
		this.grpdescripcion = grpdescripcion;
	}
	public String getGrpusuario() {
		return grpusuario;
	}

	public void setGrpusuario(String grpusuario) {
		this.grpusuario = grpusuario;
	}
	public java.util.Date getGrpultimamodificacion() {
		return grpultimamodificacion;
	}

	public void setGrpultimamodificacion(java.util.Date grpultimamodificacion) {
		this.grpultimamodificacion = grpultimamodificacion;
	}
	public Integer getGrporden() {
		return grporden;
	}

	public void setGrporden(Integer grporden) {
		this.grporden = grporden;
	}
	public String getFlagdashboard() {
		return flagdashboard;
	}

	public void setFlagdashboard(String flagdashboard) {
		this.flagdashboard = flagdashboard;
	}
	private DominioPaginacion paginacion;

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public BeanRtFormatogrupo obtenerBean() {
		BeanRtFormatogrupo bean = new BeanRtFormatogrupo();
		return obtenerBean(bean);
	}

	public BeanRtFormatogrupo obtenerBean(BeanRtFormatogrupo bean) {
		if (bean == null)
			bean = new BeanRtFormatogrupo();
		if(fmtcodigo != null) {
			bean.getPk().setFmtcodigo(fmtcodigo.intValue());
		}
		if(grpcodigo != null) {
			bean.getPk().setGrpcodigo(grpcodigo.intValue());
		}
	
		bean.setGrpdescripcion(grpdescripcion);
		bean.setGrpusuario(grpusuario);
		bean.setGrpultimamodificacion(grpultimamodificacion);
		if(grporden !=null) {
			bean.setGrporden(grporden.intValue());
		}
	
		bean.setFlagdashboard(flagdashboard);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
