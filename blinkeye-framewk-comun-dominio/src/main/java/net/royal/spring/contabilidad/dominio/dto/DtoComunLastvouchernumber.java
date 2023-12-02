package net.royal.spring.contabilidad.dominio.dto;

import net.royal.spring.contabilidad.dominio.BeanLastvouchernumber;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.lastvouchernumber
 */
public class DtoComunLastvouchernumber extends DominioTransaccion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String companyowner;
	private String year;
	private String application;
	private String type;
	private Integer month01;
	private Integer month02;
	private Integer month03;
	private Integer month04;
	private Integer month05;
	private Integer month06;
	private Integer month07;
	private Integer month08;
	private Integer month09;
	private Integer month10;
	private Integer month11;
	private Integer month12;
	private String lastuser;
	private java.util.Date lastdate;

	private String codigovoucher;
	private String secuenciames;
	private Boolean limit;

	public String getSecuenciames() {
		return secuenciames;
	}

	public void setSecuenciames(String secuenciames) {
		this.secuenciames = secuenciames;
	}

	public Boolean getLimit() {
		if (limit == null)
			return false;

		return limit;
	}

	public void setLimit(Boolean limit) {
		this.limit = limit;
	}

	public String getCodigovoucher() {
		return codigovoucher;
	}

	public void setCodigovoucher(String codigovoucher) {
		this.codigovoucher = codigovoucher;
	}

	/**
	 * 
	 * 
	 * @campo companyowner
	 */
	public String getCompanyowner() {
		return companyowner;
	}

	/**
	 * 
	 * 
	 * @campo companyowner
	 */
	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}

	/**
	 * 
	 * 
	 * @campo year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * 
	 * 
	 * @campo year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * 
	 * 
	 * @campo application
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * 
	 * 
	 * @campo application
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * 
	 * 
	 * @campo type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * 
	 * @campo type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * 
	 * @campo month01
	 */
	public Integer getMonth01() {
		return month01;
	}

	/**
	 * 
	 * 
	 * @campo month01
	 */
	public void setMonth01(Integer month01) {
		this.month01 = month01;
	}

	/**
	 * 
	 * 
	 * @campo month02
	 */
	public Integer getMonth02() {
		return month02;
	}

	/**
	 * 
	 * 
	 * @campo month02
	 */
	public void setMonth02(Integer month02) {
		this.month02 = month02;
	}

	/**
	 * 
	 * 
	 * @campo month03
	 */
	public Integer getMonth03() {
		return month03;
	}

	/**
	 * 
	 * 
	 * @campo month03
	 */
	public void setMonth03(Integer month03) {
		this.month03 = month03;
	}

	/**
	 * 
	 * 
	 * @campo month04
	 */
	public Integer getMonth04() {
		return month04;
	}

	/**
	 * 
	 * 
	 * @campo month04
	 */
	public void setMonth04(Integer month04) {
		this.month04 = month04;
	}

	/**
	 * 
	 * 
	 * @campo month05
	 */
	public Integer getMonth05() {
		return month05;
	}

	/**
	 * 
	 * 
	 * @campo month05
	 */
	public void setMonth05(Integer month05) {
		this.month05 = month05;
	}

	/**
	 * 
	 * 
	 * @campo month06
	 */
	public Integer getMonth06() {
		return month06;
	}

	/**
	 * 
	 * 
	 * @campo month06
	 */
	public void setMonth06(Integer month06) {
		this.month06 = month06;
	}

	/**
	 * 
	 * 
	 * @campo month07
	 */
	public Integer getMonth07() {
		return month07;
	}

	/**
	 * 
	 * 
	 * @campo month07
	 */
	public void setMonth07(Integer month07) {
		this.month07 = month07;
	}

	/**
	 * 
	 * 
	 * @campo month08
	 */
	public Integer getMonth08() {
		return month08;
	}

	/**
	 * 
	 * 
	 * @campo month08
	 */
	public void setMonth08(Integer month08) {
		this.month08 = month08;
	}

	/**
	 * 
	 * 
	 * @campo month09
	 */
	public Integer getMonth09() {
		return month09;
	}

	/**
	 * 
	 * 
	 * @campo month09
	 */
	public void setMonth09(Integer month09) {
		this.month09 = month09;
	}

	/**
	 * 
	 * 
	 * @campo month10
	 */
	public Integer getMonth10() {
		return month10;
	}

	/**
	 * 
	 * 
	 * @campo month10
	 */
	public void setMonth10(Integer month10) {
		this.month10 = month10;
	}

	/**
	 * 
	 * 
	 * @campo month11
	 */
	public Integer getMonth11() {
		return month11;
	}

	/**
	 * 
	 * 
	 * @campo month11
	 */
	public void setMonth11(Integer month11) {
		this.month11 = month11;
	}

	/**
	 * 
	 * 
	 * @campo month12
	 */
	public Integer getMonth12() {
		return month12;
	}

	/**
	 * 
	 * 
	 * @campo month12
	 */
	public void setMonth12(Integer month12) {
		this.month12 = month12;
	}

	/**
	 * 
	 * 
	 * @campo lastuser
	 */
	public String getLastuser() {
		return lastuser;
	}

	/**
	 * 
	 * 
	 * @campo lastuser
	 */
	public void setLastuser(String lastuser) {
		this.lastuser = lastuser;
	}

	/**
	 * 
	 * 
	 * @campo lastdate
	 */
	public java.util.Date getLastdate() {
		return lastdate;
	}

	/**
	 * 
	 * 
	 * @campo lastdate
	 */
	public void setLastdate(java.util.Date lastdate) {
		this.lastdate = lastdate;
	}

	public BeanLastvouchernumber obtenerBean() {
		BeanLastvouchernumber bean = new BeanLastvouchernumber();
		return obtenerBean(bean);
	}

	public BeanLastvouchernumber obtenerBean(BeanLastvouchernumber bean) {
		if (bean == null)
			bean = new BeanLastvouchernumber();

		bean.getPk().setCompanyowner(companyowner);
		bean.getPk().setYear(year);
		bean.getPk().setApplication(application);
		bean.getPk().setType(type);
		bean.setMonth01(month01);
		bean.setMonth02(month02);
		bean.setMonth03(month03);
		bean.setMonth04(month04);
		bean.setMonth05(month05);
		bean.setMonth06(month06);
		bean.setMonth07(month07);
		bean.setMonth08(month08);
		bean.setMonth09(month09);
		bean.setMonth10(month10);
		bean.setMonth11(month11);
		bean.setMonth12(month12);
		bean.setLastuser(lastuser);
		bean.setLastdate(lastdate);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
