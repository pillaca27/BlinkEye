package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunExchangerate  extends DominioTransaccion{	
	private String period;
	private java.math.BigDecimal rate;
	private java.math.BigDecimal monthcloserate;
	private java.math.BigDecimal monthbuyrate;
	private java.math.BigDecimal monthsalerate;
	private java.math.BigDecimal gainandlossrate;
	private java.math.BigDecimal inflationfactor;
	private java.math.BigDecimal inflationfactoracumulated;
	private BigDecimal sbsrate;
	private String lastuser;
	private java.util.Date lastdate;
	private String status;
	
	private BigDecimal ROWNUM_;

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public java.math.BigDecimal getRate() {
		return rate;
	}

	public void setRate(java.math.BigDecimal rate) {
		this.rate = rate;
	}

	public java.math.BigDecimal getMonthcloserate() {
		return monthcloserate;
	}

	public void setMonthcloserate(java.math.BigDecimal monthcloserate) {
		this.monthcloserate = monthcloserate;
	}

	public java.math.BigDecimal getMonthbuyrate() {
		return monthbuyrate;
	}

	public void setMonthbuyrate(java.math.BigDecimal monthbuyrate) {
		this.monthbuyrate = monthbuyrate;
	}

	public java.math.BigDecimal getMonthsalerate() {
		return monthsalerate;
	}

	public void setMonthsalerate(java.math.BigDecimal monthsalerate) {
		this.monthsalerate = monthsalerate;
	}

	public java.math.BigDecimal getGainandlossrate() {
		return gainandlossrate;
	}

	public void setGainandlossrate(java.math.BigDecimal gainandlossrate) {
		this.gainandlossrate = gainandlossrate;
	}

	public java.math.BigDecimal getInflationfactor() {
		return inflationfactor;
	}

	public void setInflationfactor(java.math.BigDecimal inflationfactor) {
		this.inflationfactor = inflationfactor;
	}

	public java.math.BigDecimal getInflationfactoracumulated() {
		return inflationfactoracumulated;
	}

	public void setInflationfactoracumulated(java.math.BigDecimal inflationfactoracumulated) {
		this.inflationfactoracumulated = inflationfactoracumulated;
	}

	public BigDecimal getSbsrate() {
		return sbsrate;
	}

	public void setSbsrate(BigDecimal sbsrate) {
		this.sbsrate = sbsrate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
		
}
