package net.royal.spring.contabilidad.dominio.dto;

import net.royal.spring.contabilidad.dominio.BeanAcCostcenteraccount;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAcCostcenteraccount extends DominioTransaccion{
	private String costcenter;
	private String account;
	
	private String status;
	private String lastuser;
	private java.util.Date lastdate;
	
 
	private String descripcion;
	private String accion;
 
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public DtoComunAcCostcenteraccount() {}
	public DtoComunAcCostcenteraccount(String costcenter,String account) {
		this.costcenter=costcenter;
		this.account=account;
	}
	
	public String getCostcenter() {
		return costcenter;
	}
	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	
	public BeanAcCostcenteraccount obtenerBean() {
		BeanAcCostcenteraccount bean = new BeanAcCostcenteraccount();
		return obtenerBean(bean);
	}

	public BeanAcCostcenteraccount obtenerBean(BeanAcCostcenteraccount bean) {
		if (bean == null)
			bean = new BeanAcCostcenteraccount();

		bean.getPk().setCostcenter(costcenter);
		bean.getPk().setAccount(account);
		bean.setStatus(status);
		bean.setLastuser(lastuser);
		bean.setLastdate(lastdate);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}
}
