package net.royal.spring.contabilidad.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAcAfecompany extends DominioTransaccion{
	// pk
	private String afe;
	private String companyowner;
	
	// campos
	private String status;
	private String lastuser;
	private java.util.Date lastdate;
	
	public DtoComunAcAfecompany() {}
	public DtoComunAcAfecompany(String afe,String companyowner) {
		this.afe=afe;
		this.companyowner=companyowner;
	}
	
	public String getAfe() {
		return afe;
	}
	public void setAfe(String afe) {
		this.afe = afe;
	}
	public String getCompanyowner() {
		return companyowner;
	}
	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
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

	
	
}
