package net.royal.spring.contabilidad.dominio.dto;

import net.royal.spring.contabilidad.dominio.BeanAcCostcenterdestvalid;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAcCostcenterdestvalid extends DominioTransaccion{
	private String costcenter;
	private String costcenterdestination;
	
	private String status;
	private String lastuser;
	private java.util.Date lastdate;
	
 
	private String accion;
	private String descripcion;
	
	
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public DtoComunAcCostcenterdestvalid() {}
	public DtoComunAcCostcenterdestvalid(String costcenter,String costcenterdestination) {
		this.costcenter=costcenter;
		this.costcenterdestination=costcenterdestination;
	}
	
	public String getCostcenter() {
		return costcenter;
	}
	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}
	public String getCostcenterdestination() {
		return costcenterdestination;
	}
	public void setCostcenterdestination(String costcenterdestination) {
		this.costcenterdestination = costcenterdestination;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastuser() {
		return lastuser;
	}
	public void setLastuser(String lastuser) {
		this.lastuser = lastuser;
	}
	public java.util.Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(java.util.Date lastdate) {
		this.lastdate = lastdate;
	}
	
	public BeanAcCostcenterdestvalid obtenerBean() {
		BeanAcCostcenterdestvalid bean = new BeanAcCostcenterdestvalid();
		return obtenerBean(bean);
	}

	public BeanAcCostcenterdestvalid obtenerBean(BeanAcCostcenterdestvalid bean) {
		if (bean == null)
			bean = new BeanAcCostcenterdestvalid();

		bean.getPk().setCostcenter(costcenter);
		bean.getPk().setCostcenterdestination(costcenterdestination);
		bean.setStatus(status);
		bean.setLastuser(lastuser);
		bean.setLastdate(lastdate);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}
}
