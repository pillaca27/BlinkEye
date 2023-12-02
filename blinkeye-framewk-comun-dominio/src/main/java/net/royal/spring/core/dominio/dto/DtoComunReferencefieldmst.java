package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunReferencefieldmst  extends DominioTransaccion{
	
	private String referencefield;
	private String localname;
	private String englishname;
	private String screentitle;
	private BigDecimal length;
	private String internalcodeflag;
	private String internalcode;
	private String tablevalidatedflag;
	private String availableflag;
	private String status;
	private String lastuser;
	private Date lastdate;
	
	
	public String getReferencefield() {
		return referencefield;
	}
	public void setReferencefield(String referencefield) {
		this.referencefield = referencefield;
	}
	public String getLocalname() {
		return localname;
	}
	public void setLocalname(String localname) {
		this.localname = localname;
	}
	public String getEnglishname() {
		return englishname;
	}
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	public String getScreentitle() {
		return screentitle;
	}
	public void setScreentitle(String screentitle) {
		this.screentitle = screentitle;
	}
	public BigDecimal getLength() {
		return length;
	}
	public void setLength(BigDecimal length) {
		this.length = length;
	}
	public String getInternalcodeflag() {
		return internalcodeflag;
	}
	public void setInternalcodeflag(String internalcodeflag) {
		this.internalcodeflag = internalcodeflag;
	}
	public String getInternalcode() {
		return internalcode;
	}
	public void setInternalcode(String internalcode) {
		this.internalcode = internalcode;
	}
	public String getTablevalidatedflag() {
		return tablevalidatedflag;
	}
	public void setTablevalidatedflag(String tablevalidatedflag) {
		this.tablevalidatedflag = tablevalidatedflag;
	}
	public String getAvailableflag() {
		return availableflag;
	}
	public void setAvailableflag(String availableflag) {
		this.availableflag = availableflag;
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
	public Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	
	
	
}
